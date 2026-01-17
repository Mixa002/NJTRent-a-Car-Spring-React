package com.rentacar.backend.config;

import com.rentacar.backend.model.*;
import com.rentacar.backend.model.enums.StatusAutomobila;
import com.rentacar.backend.model.enums.VrstaGoriva;
import com.rentacar.backend.model.enums.VrstaMenjaca;
import com.rentacar.backend.repository.AutomobilRepository;
import com.rentacar.backend.repository.EkspozituraRepository;
import com.rentacar.backend.repository.GradRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class DataInitializer implements CommandLineRunner {

    private final GradRepository gradRepo;
    private final EkspozituraRepository expoRepo;
    private final AutomobilRepository autoRepo;

    public DataInitializer(
            GradRepository gradRepo,
            EkspozituraRepository expoRepo,
            AutomobilRepository autoRepo) {
        this.gradRepo = gradRepo;
        this.expoRepo = expoRepo;
        this.autoRepo = autoRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if(gradRepo.count() > 0){
            return;
        }
        Grad beograd = new Grad();
        beograd.setNaziv("Beograd");
        beograd.setPostanskiBroj("11000");

        Grad nis = new Grad();
        nis.setNaziv("Nis");
        nis.setPostanskiBroj("18000");

        Grad leskovac = new Grad();
        leskovac.setNaziv("Leskovac");
        leskovac.setPostanskiBroj("16000");

        gradRepo.save(beograd);
        gradRepo.save(nis);
        gradRepo.save(leskovac);


        Ekspozitura bgCentar = new Ekspozitura();
        bgCentar.setNaziv("Beograd Centar");
        bgCentar.setAdresa("Knez Mihajlova 1");
        bgCentar.setGrad(beograd);

        Ekspozitura bgCvetko = new Ekspozitura();
        bgCvetko.setNaziv("Beograd Cvetkova Pijaca");
        bgCvetko.setAdresa("Odeska 21");
        bgCvetko.setGrad(beograd);

        Ekspozitura nisDelta = new Ekspozitura();
        nisDelta.setNaziv("Nis Delta Planet");
        nisDelta.setAdresa("Bulevar Nemanjica 11");
        nisDelta.setGrad(nis);

        Ekspozitura leskovacSajam = new Ekspozitura();
        leskovacSajam.setNaziv("Leskovac Sajam");
        leskovacSajam.setAdresa("Jovana Cvijica 6");
        leskovacSajam.setGrad(leskovac);

        beograd.getEkspoziture().add(bgCentar);
        beograd.getEkspoziture().add(bgCvetko);
        nis.getEkspoziture().add(nisDelta);
        leskovac.getEkspoziture().add(leskovacSajam);

        expoRepo.save(bgCentar);
        expoRepo.save(bgCvetko);
        expoRepo.save(nisDelta);
        expoRepo.save(leskovacSajam);


        Automobil a1 = new Automobil();
        a1.setRegBroj("BG-101-AA");
        a1.setMarka("Toyota");
        a1.setModel("Corolla");
        a1.setGodiste(2020);
        a1.setCenaPoDanu(new BigDecimal("45.00"));
        a1.setMenjac(VrstaMenjaca.MANUELNI);
        a1.setGorivo(VrstaGoriva.BENZIN);
        a1.setStatus(StatusAutomobila.DOSTUPAN);
        a1.setSlikaURL("https://example.com/corolla.jpg");
        a1.setTrenutnaEkspozitura(bgCentar);

        Automobil a2 = new Automobil();
        a2.setRegBroj("BG-202-BB");
        a2.setMarka("Volkswagen");
        a2.setModel("Golf");
        a2.setGodiste(2019);
        a2.setCenaPoDanu(new BigDecimal("40.00"));
        a2.setMenjac(VrstaMenjaca.MANUELNI);
        a2.setGorivo(VrstaGoriva.DIZEL);
        a2.setStatus(StatusAutomobila.DOSTUPAN);
        a2.setSlikaURL("https://example.com/golf.jpg");
        a2.setTrenutnaEkspozitura(bgCvetko);

        Automobil a3 = new Automobil();
        a3.setRegBroj("NI-303-CC");
        a3.setMarka("Skoda");
        a3.setModel("Octavia");
        a3.setGodiste(2021);
        a3.setCenaPoDanu(new BigDecimal("50.00"));
        a3.setMenjac(VrstaMenjaca.AUTOMATIK);
        a3.setGorivo(VrstaGoriva.DIZEL);
        a3.setStatus(StatusAutomobila.DOSTUPAN);
        a3.setSlikaURL("https://example.com/octavia.jpg");
        a3.setTrenutnaEkspozitura(nisDelta);

        autoRepo.save(a1);
        autoRepo.save(a2);
        autoRepo.save(a3);


    }
}
