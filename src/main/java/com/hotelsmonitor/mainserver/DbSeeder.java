package com.hotelsmonitor.mainserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private ServerRepository serverRepository;

    public DbSeeder(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Kiosk k1 = new Kiosk("macAdd1","Heloton,Beersheva",
                Arrays.asList(Devices.PAINTER,Devices.AC,Devices.CASH_READER,Devices.CREDIT_READER),new ArrayList<>());
        Kiosk k2 = new Kiosk("macAdd2","Heloton,TelAviv",
                Arrays.asList(Devices.PAINTER,Devices.AC,Devices.CASH_READER,Devices.CREDIT_READER),Arrays.asList("Painter"));
        Kiosk k3 = new Kiosk("macAdd3","Shraton,Tel Aviv",
                Arrays.asList(Devices.PAINTER,Devices.AC,Devices.CASH_READER,Devices.CREDIT_READER),new ArrayList<>());
        Kiosk k4 = new Kiosk("macAdd4","Sheraton,Cairo",
                Arrays.asList(Devices.PAINTER,Devices.AC,Devices.CASH_READER,Devices.CREDIT_READER),new ArrayList<>());
        Server server1 =  new Server("server1",Arrays.asList(k1,k2));
        Server server2 =  new Server("server2",Arrays.asList(k3,k4));
        //drop the fuck everything
        this.serverRepository.deleteAll();
        //add to the DB
        List<Server> servers = Arrays.asList(server1,server2);
        this.serverRepository.saveAll(servers);
    }
}
