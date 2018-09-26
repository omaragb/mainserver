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
    public DbSeeder(ServerRepository serverRepository,TechRepository techRepository) {
        this.serverRepository = serverRepository;
        this.techRepository = techRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Kiosk k1 = new Kiosk("macAdd1","Heloton,Beersheva",
                Arrays.asList(Devices.PAINTER,Devices.AC,Devices.CASH_READER,Devices.CREDIT_READER),Arrays.asList(new KioskExceptions("YELLOW",
                "Fucking","12/8/2018-20:48")));
        Kiosk k2 =new Kiosk("macAdd1","Heloton,Beersheva",
                Arrays.asList(Devices.PAINTER,Devices.AC,Devices.CASH_READER,Devices.CREDIT_READER),new ArrayList<>());
        Kiosk k3 = new Kiosk("macAdd3","Shraton,Tel Aviv",
                Arrays.asList(Devices.PAINTER,Devices.AC,Devices.CASH_READER,Devices.CREDIT_READER),new ArrayList<>());
        Kiosk k4 = new Kiosk("macAdd4","Sheraton,Cairo",
                Arrays.asList(Devices.PAINTER,Devices.AC,Devices.CASH_READER,Devices.CREDIT_READER),new ArrayList<>());
        Server server1 =  new Server("server1",Arrays.asList(k1,k2));
        Server server2 =  new Server("server2",Arrays.asList(k3,k4));
        Server server3 =  new Server("server2",Arrays.asList(k3,k4));
        Technician t = new Technician("Abed1","abed@1.com");
        t.addKiosk(k1);
        this.techRepository.deleteAll();
        this.techRepository.insert(t);
        this.techRepository.insert(new Technician("Abed2","abed@2.com"));
        this.techRepository.insert(new Technician("Abed3","abed@3.com"));
        this.techRepository.insert(new Technician("Abed4","abed@4.com"));
        //drop the fuck everything
        this.serverRepository.deleteAll();
        //add to the DB
        List<Server> servers = Arrays.asList(server1,server2,server3);
        this.serverRepository.saveAll(servers);
    }
}
