package com.example.patients;

import  com.example.patients.entities.Patient;
import  com.example.patients.repositories.PatientRepository;
import  com.example.patients.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Ghninou",new Date(), true,180));
            patientRepository.save(new Patient(null,"Ghita",new Date(), false,188));
            patientRepository.save(new Patient(null,"Assia",new Date(), true,120));
            patientRepository.save(new Patient(null,"Salma",new Date(), false,110));

            patientRepository.findAll().forEach(patient -> {
                System.out.println(patient.getNom());
            });

        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("Ghninou","1234","1234");
            securityService.saveNewUser("karima","1234","1234");
            securityService.saveNewUser("anas","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("Mehdi","USER");
            securityService.addRoleToUser("Ghninou","ADMIN");
            securityService.addRoleToUser("karima","USER");
            securityService.addRoleToUser("anas","USER");
        };
    }


}
