package com.hotelsmonitor.mainserver;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servers")
public class ServersController {
    private ServerRepository serverRepository;

    public ServersController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }
    @GetMapping("/all")
    public List<Server> getAll(){
        List<Server> hotels = this.serverRepository.findAll();
        return hotels;
    }
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
    @GetMapping("/{id}")
    public Server getById(@PathVariable("id") String id){
        return this.serverRepository.getById(id);
    }
}
