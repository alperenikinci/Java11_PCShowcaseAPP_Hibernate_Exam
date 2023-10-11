package com.bilgeadam.controller;

import com.bilgeadam.enums.ESpecType;
import com.bilgeadam.repository.entity.BaseEntity;
import com.bilgeadam.repository.entity.ComputerSpec;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.service.ComputerSpecService;

import java.util.List;
import java.util.Scanner;

public class ComputerSpecController {
    ComputerSpecService computerSpecService;
    Scanner scanner;

    public ComputerSpecController(){
        this.computerSpecService = new ComputerSpecService();
        this.scanner = new Scanner(System.in);
    }

    public ComputerSpec createSpec(User user) {
        ESpecType[] allValues = ESpecType.values();
        int i = 1;
        for(ESpecType value : allValues){
            System.out.println(i + " - "+value );
            i++;
        }
        System.out.print("Lutfen olusturmak istediginiz bilesenin numarasini seciniz : ");
        Integer index = Integer.parseInt(scanner.nextLine());

        if(index >= 1 && index <= allValues.length){
            ESpecType selectedType = allValues[index-1];
            System.out.print("Lutfen olusturmak istediginiz bilesene bir isim veriniz : ");
            String name = scanner.nextLine();
            ComputerSpec computerSpec = ComputerSpec.builder()
                    .baseEntity(BaseEntity.builder().build())
                    .name(name)
                    .specType(selectedType)
                    .userId(user.getId())
                    .build();
            return computerSpecService.save(computerSpec);
        } else {
            System.out.println("Gecersiz numara girdiniz..! ");
            return null;
        }
    }

    public List<ComputerSpec> findAllUnassignedSpecsForAUser(User user) {
        return computerSpecService.findAllUnassignedSpecsForAUser(user);
    }

    public ComputerSpec save(ComputerSpec computerSpec) {
        return computerSpecService.save(computerSpec);
    }

    public void update(ComputerSpec computerSpec) {
        computerSpecService.update(computerSpec);
    }
}
