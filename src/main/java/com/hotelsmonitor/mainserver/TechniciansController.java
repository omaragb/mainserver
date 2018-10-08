package com.hotelsmonitor.mainserver;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class TechniciansController {
    private TechRepository techRepository;
    private UserRepository userRepository;
    private ServerRepository serverRepository;
    public TechniciansController(TechRepository techRepository, UserRepository userRepository, ServerRepository serverRepository) {
        this.techRepository = techRepository;
        this.userRepository = userRepository;
        this.serverRepository = serverRepository;
    }
    // HttpRequest Get all Technicians
    @GetMapping("/all")
    public List<Technician> getAll(){
        List<Technician> techs = this.techRepository.findAll();
        return techs;
    }
    // HttpRequest add new Technician, also we creat a new User for the Technician
    @PostMapping("/addTech/{techName}/{email}")
    public void addNewTechman(@PathVariable String techName,@PathVariable String email,@RequestBody String password){
        JSONObject passJson = new JSONObject(password);
        this.techRepository.insert(new Technician(techName,password,email));
        this.userRepository.insert(new User(techName,email,Type.TECH,passJson.get("password").toString()));

    }
    // HttpRequest Assign a problem to a technician for which needs a fix by adding the relevant kiosk as a job to the technician jobs queue
    @PostMapping("/assign/{techID}/{macAddress}/{problem}/{date}")
    public void assingTechToFix(@PathVariable String techID,@PathVariable String macAddress,@PathVariable String problem
    ,@PathVariable String date){
        System.out.println(techID + " " + problem);
        Technician technician = this.techRepository.getById(techID);
        technician.addKiosk(new Job(macAddress,problem,date));
        this.techRepository.save(technician);
    }
    // HttpRequest  Delete A technician and delete the user created for him
    @DeleteMapping("/delete/{techId}")
    public void deleteTech(@PathVariable String techId){
        Technician userToDelete = this.techRepository.findById(techId).get();
        String usermailToDelete = userToDelete.getEmail();
        this.techRepository.deleteById(techId);
        this.userRepository.deleteByEmail(usermailToDelete);
    }
    // // HttpRequest  get all the Jobs in some Technician Jobs queue
    @GetMapping("/{email}")
    public List<Job> getKiosksWithProblemsForTech(@PathVariable String email){
        Technician tech = this.techRepository.findByEmail(email);
        return tech.getJobs();
    }
    // HttpRequest Update that some Technician fixed some Kiosk and remove the Job from the techniician jobs queue
    @CrossOrigin
    @PostMapping("/updateKiosk/{email}/{problem}/{macAdd}/{date}")
    public void updateKioskFix(@PathVariable String email,@PathVariable String problem,
                               @PathVariable String macAdd,@PathVariable String date){
        Job j = new Job(macAdd,problem,date);
        /// Update the Technician Jobs in the DB
        Technician technician = this.techRepository.findByEmail(email);

        for(Job jIter : technician.getJobs()){
            if (jIter.equals(j)){
                technician.removeJob(jIter);
                break;
            }
        }
        this.techRepository.save(technician);



    }
}
