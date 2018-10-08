package com.hotelsmonitor.mainserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private ServerRepository serverRepository;
    private TechRepository techRepository;
    private UserRepository userRepository;
    public DbSeeder(ServerRepository serverRepository,TechRepository techRepository,UserRepository userRepository) {
        this.serverRepository = serverRepository;
        this.techRepository = techRepository;
        this.userRepository = userRepository;
    }


    // we Create here Objects for checking everything
    @Override
    public void run(String... args) throws Exception {
            Kiosk k1 = new Kiosk("macAdd1", "Heloton,Beersheva",
                     Arrays.asList(new KioskExceptions("YELLOW",
                    "Printer Error", "12/8/2018-20:48"),
                             new KioskExceptions("RED",
                                     "Scanner Exploded !", "18/8/2018-20:48")));
            Kiosk k2 = new Kiosk("macAdd2", "Heloton,Beersheva",
                    new ArrayList<>());
            Kiosk k3 = new Kiosk("macAdd3", "Shraton,Tel Aviv",
                    new ArrayList<>());
            Kiosk k4 = new Kiosk("macAdd4", "Sheraton,Cairo",
                    new ArrayList<>());
            Server server1 = new Server("server1", Arrays.asList(k1, k2));
            Server server2 = new Server("server2", Arrays.asList(k3, k4));
            Server server3 = new Server("server2", Arrays.asList(k3, k4));
            Technician t = new Technician("Abed1","12345", "abed@1.com");
            t.addKiosk(new Job(k1.getMacAddress(),"0x9001","17-5-2018"));
            this.techRepository.deleteAll();
            this.techRepository.save(t);
            this.techRepository.save(new Technician("Abed2","12345", "abed@2.com"));
            this.techRepository.save(new Technician("Abed3","12345", "abed@3.com"));
            this.techRepository.save(new Technician("Abed4","12345", "abed@4.com"));
            this.userRepository.deleteAll();
            this.userRepository.save(new User("Mohammed", "wattk308@gmail.com", Type.SYS_ADMIN, "1234"));
            this.userRepository.save(new User("abed", "abed@gmail.com", Type.LOCAL_ADMIN, "1234"));
            this.userRepository.save(new User("khaled", "khaled@gmail.com", Type.TECH, "1234"));
            this.userRepository.save(new User("Abed1","abed@1.com",Type.TECH,"12345"));
            //drop the fuck everything
            this.serverRepository.deleteAll();
            //add to the DB
            List<Server> servers = Arrays.asList(server1, server2, server3);
            this.serverRepository.saveAll(servers);

    }
}
