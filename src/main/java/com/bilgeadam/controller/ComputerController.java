package com.bilgeadam.controller;


import com.bilgeadam.enums.ESpecType;
import com.bilgeadam.repository.entity.BaseEntity;
import com.bilgeadam.repository.entity.Computer;
import com.bilgeadam.repository.entity.ComputerSpec;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.service.ComputerService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ComputerController {
    Scanner scanner;
    ComputerService computerService;
    ComputerSpecController computerSpecController;

    public ComputerController (){
        this.scanner = new Scanner(System.in);
        this.computerService = new ComputerService();
        this.computerSpecController = new ComputerSpecController();
    }

    public Computer createComputer(User user) {
        String name = "";
        Boolean dataIntegrity = false;
        Computer computer = null;
        ComputerSpec computerSpec = null;
        List<String> computerNames = computerService.findAll().stream().map(c->c.getName()).collect(Collectors.toList());
        List<ComputerSpec> computerSpecList = computerSpecController.findAllUnassignedSpecsForAUser(user);
        do {
            System.out.print("Lutfen bilgisayariniz icin bir isim giriniz :  ");
            name = scanner.nextLine();
            if (computerNames.contains(name)){
                System.out.println("Girdiginiz isimde bir bilgisayar daha once olusturulmustur. Lutfen baska bir isim giriniz...");
                dataIntegrity = false;
            } else {
                computer = Computer.builder()
                        .userId(user.getId())
                        .name(name)
                        .baseEntity(BaseEntity.builder().build())
                        .build();
                computer = computerService.save(computer);

                computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.GPU).forEach(System.out::println);
                if(!computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.GPU).collect(Collectors.toList()).isEmpty()){
                    System.out.print("Lutfen bilgisayariniza eklemek istediginiz GPU'yu seciniz : ");
                    Long gpuId = Long.parseLong(scanner.nextLine());
                    //GPU SAVE
                    computerSpec = computerSpecList.stream().filter(c -> c.getId()==gpuId).collect(Collectors.toList()).get(0);
                    computerSpec.setComputerId(computer.getId());
                    computerSpecController.update(computerSpec);
                }
                computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.CPU).forEach(System.out::println);
                if (!computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.CPU).collect(Collectors.toList()).isEmpty()) {
                    System.out.print("Lutfen bilgisayariniza eklemek istediginiz CPU'yu secin : ");
                    Long cpuId = Long.parseLong(scanner.nextLine());
                    //CPU SAVE
                    computerSpec = computerSpecList.stream().filter(c -> c.getId() == cpuId).collect(Collectors.toList()).get(0);
                    computerSpec.setComputerId(computer.getId());
                    computerSpecController.update(computerSpec);
                }

                computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.MOTHERBOARD).forEach(System.out::println);
                if (!computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.MOTHERBOARD).collect(Collectors.toList()).isEmpty()){
                    System.out.print("Lutfen bilgisayariniza eklemek istediginiz ANAKART'i secin : ");
                    Long motherBoardId = Long.parseLong(scanner.nextLine());
                    //MOTHERBOARD SAVE
                    computerSpec = computerSpecList.stream().filter(c -> c.getId()==motherBoardId).collect(Collectors.toList()).get(0);
                    computerSpec.setComputerId(computer.getId());
                    computerSpecController.update(computerSpec);
                }
                computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.RAM).forEach(System.out::println);
                if(!computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.RAM).collect(Collectors.toList()).isEmpty()) {
                    System.out.print("Lutfen bilgisayariniza eklemek istediginiz RAM'i secin : ");
                    Long ramId = Long.parseLong(scanner.nextLine());
                    //RAM SAVE
                    computerSpec = computerSpecList.stream().filter(c -> c.getId() == ramId).collect(Collectors.toList()).get(0);
                    computerSpec.setComputerId(computer.getId());
                    computerSpecController.update(computerSpec);
                }

                computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.PSU).forEach(System.out::println);
                if(!computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.PSU).collect(Collectors.toList()).isEmpty()){
                    System.out.print("Lutfen bilgisayariniza eklemek istediginiz GUC UNITESI'ni secin : ");
                    Long psuId = Long.parseLong(scanner.nextLine());
                    //PSU SAVE
                    computerSpec = computerSpecList.stream().filter(c -> c.getId()==psuId).collect(Collectors.toList()).get(0);
                    computerSpec.setComputerId(computer.getId());
                    computerSpecController.update(computerSpec);
                }
                computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.CASE).forEach(System.out::println);
                if (!computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.CASE).collect(Collectors.toList()).isEmpty()){
                    System.out.print("Lutfen bilgisayariniza eklemek istediginiz KASA'yi secin : ");
                    Long caseId = Long.parseLong(scanner.nextLine());
                    //CASE SAVE
                    computerSpec = computerSpecList.stream().filter(c -> c.getId()==caseId).collect(Collectors.toList()).get(0);
                    computerSpec.setComputerId(computer.getId());
                    computerSpecController.update(computerSpec);
                }
                computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.SSD).forEach(System.out::println);
                if (!computerSpecList.stream().filter(c -> c.getSpecType()== ESpecType.SSD).collect(Collectors.toList()).isEmpty()){
                    System.out.print("Lutfen bilgisayariniza eklemek istediginiz SSD'yi secin : ");
                    Long ssdId = Long.parseLong(scanner.nextLine());
                    //SSD SAVE
                    computerSpec = computerSpecList.stream().filter(c -> c.getId()==ssdId).collect(Collectors.toList()).get(0);
                    computerSpec.setComputerId(computer.getId());
                    computerSpecController.update(computerSpec);
                }
                dataIntegrity = true;
            }
        }while (!dataIntegrity);
        return computer;
    }

    public List<Computer> findAllByUser(User user) {
        return computerService.findAllByUser(user);
    }
}
