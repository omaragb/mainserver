package com.hotelsmonitor.mainserver;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class TechniciansController {
    private TechRepository techRepository;
    private ServerRepository serverRepository;
    public TechniciansController(TechRepository techRepository) {
        this.techRepository = techRepository;
    }
    @GetMapping("/all")
    public List<Technician> getAll(){
        List<Technician> techs = this.techRepository.findAll();
        return techs;
    }
    @GetMapping("/getFree")
    public Technician getFreeTech(){
        Technician tech = null;
        int i = 100000;
        for(Technician t : this.techRepository.findAll())
        {
            if(t.getJobs().size() < i){
                i = t.getJobs().size();
                tech = t;
            }
        }
        return tech;
    }
    @PostMapping
    public void addNewTechman(@RequestBody Technician techman){
        this.techRepository.insert(techman);
    }
    @PostMapping("/assign")
    public void assingTechToFix(@PathVariable String techID,@RequestBody Kiosk kiosk){
        this.techRepository.findById(techID).get().addKiosk(kiosk);
    }
    @DeleteMapping("/{id}")
    public void deleteTech(@PathVariable String techID){
        this.techRepository.deleteById(techID);
    }
    @GetMapping("/{techID}")
    public List<Kiosk> getKiosksWithProblemsForTech(@PathVariable String techID){
        Technician tech = this.techRepository.findById(techID).get();
        return tech.getJobs();
    }
    @PostMapping("/updateKiosk/{techID}")
    public void updateKioskFix(@PathVariable String techID,@RequestBody Kiosk kiosk){
        this.techRepository.findById(techID).get().getJobs().remove(kiosk);
        for(Kiosk k :this.serverRepository.findById(kiosk.getServerID()).get().getKiosks()){
            if (k.getMacAddress().equals(kiosk.getMacAddress())) {
                this.serverRepository.findById(kiosk.getServerID()).get().getKiosks().remove(k);
                this.serverRepository.findById(kiosk.getServerID()).get().getKiosks().add(kiosk);
                break;
            }
        }
    }
}
