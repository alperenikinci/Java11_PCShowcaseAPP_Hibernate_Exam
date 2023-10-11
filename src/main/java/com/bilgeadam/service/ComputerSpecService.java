package com.bilgeadam.service;

import com.bilgeadam.repository.ComputerSpecRepository;
import com.bilgeadam.repository.entity.ComputerSpec;
import com.bilgeadam.repository.entity.User;

import java.util.List;
import java.util.Optional;

public class ComputerSpecService {

    private ComputerSpecRepository computerSpecRepository;

    public ComputerSpecService() {
        this.computerSpecRepository = new ComputerSpecRepository();
    }

    public ComputerSpec save(ComputerSpec computerSpec) {
        return computerSpecRepository.save(computerSpec);
    }

    public void update(ComputerSpec computerSpec) {
        computerSpecRepository.update(computerSpec);
    }

    public List<ComputerSpec> findAll() {
        return computerSpecRepository.findAll();
    }

    public Optional<ComputerSpec> findById(Long id) {
        return computerSpecRepository.findById(id);
    }

    public List<ComputerSpec> saveAll(List<ComputerSpec> computerSpecList) {
        return (List<ComputerSpec>) computerSpecRepository.saveAll(computerSpecList);
    }

    public void delete(ComputerSpec computerSpec) {
        computerSpecRepository.delete(computerSpec);
    }

    public void deleteById(Long id) {
        computerSpecRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return computerSpecRepository.existsById(id);
    }

    public List<ComputerSpec> findByColumnNameAndValue(String columnName, String columnValue){
        return computerSpecRepository.findByColumnNameAndValue(columnName, columnValue);
    }

    public List<ComputerSpec> findAllUnassignedSpecsForAUser(User user) {
        return computerSpecRepository.findAllUnassignedSpecsForAUser(user);
    }
}
