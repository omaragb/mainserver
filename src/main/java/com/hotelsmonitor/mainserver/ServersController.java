package com.hotelsmonitor.mainserver;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/servers")
public class ServersController {
    private ServerRepository serverRepository;

    public ServersController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }
    // HttpRequest Get all local servers in the databas
    @GetMapping("/all")
    public List<Server> getAll(){
        List<Server> hotels = this.serverRepository.findAll();
        return hotels;
    }
    @CrossOrigin
    @GetMapping("/GetServer/{serverIdForAdmin}")
    public Server getServer(@PathVariable String serverIdForAdmin){
        return this.serverRepository.getByName(serverIdForAdmin);
    }
    // HttpRequest  each time a Local servers POST a status update of the Kiosks sstatus, in this method we update the DB according to the status
    //Synchronized, we want a kiosk to be updated at a time , otherwise it creates unwanted copies of servers and kiosks
    @PostMapping("/GetReport")
    synchronized public void getReport(@RequestBody String kioskJson) {
        System.out.println(kioskJson);
        Gson gson = new Gson();
        Kiosk kiosk = gson.fromJson(kioskJson,Kiosk.class);
        System.out.println(kiosk.toString());
        // Simple Exp .. What happens here is we check if we already have the server or not
        // in case we have it :
        //  - we check if the server have the kiosk we want to update its status
        //  - - in case we have it we update it
        //  - - in case we don't we add it to the server
        // in case we dont have the servers we create a new one , add the kiok to the new Server and save it to the DB
        if(this.serverRepository.getByName(kiosk.getServerID()) != null){
            Server server = this.serverRepository.getByName(kiosk.getServerID());
            server.setStatus(); // here we update the servers Status (RED,YELLOW,GREEN)
            Kiosk tempK = null;
            if((tempK = server.getKiosk(kiosk.getMacAddress())) != null ){
                server.getKiosks().remove(tempK);

                server.getKiosks().add(kiosk);
            }
            else {
                server.getKiosks().add(kiosk);
            }
            this.serverRepository.deleteByName(server.getName());
            this.serverRepository.insert(server);
        }
        else {
            Server server = new Server(kiosk.getServerID(),Arrays.asList(kiosk));
            server.setStatus();
            System.err.println(server.getStatus());
            this.serverRepository.insert(server);
        }
    }

    /// Normal MongoDB operations (insert, update, delete) for Servers Collection
    @PutMapping
    public void insert(@RequestBody Server server){
        this.serverRepository.insert(server);
    }
    @PostMapping
    public void update(@RequestBody Server server){
        this.serverRepository.save(server);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.serverRepository.deleteById(id);
    }
//    @GetMapping("/{id}")
//    public Server getById(@PathVariable("id") String id){
//        return this.serverRepository.getById(id);
//    }
}
