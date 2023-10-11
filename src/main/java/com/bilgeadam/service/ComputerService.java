package com.bilgeadam.service;

import com.bilgeadam.repository.ComputerRepository;
import com.bilgeadam.repository.entity.Computer;
import com.bilgeadam.repository.entity.User;

import java.util.List;
import java.util.Optional;

public class ComputerService {

    private ComputerRepository computerRepository;

    public ComputerService() {
        this.computerRepository = new ComputerRepository();
    }

    public Computer save(Computer computer) {
        return computerRepository.save(computer);
    }

    public void update(Computer computer) {
        computerRepository.update(computer);
    }

    public List<Computer> findAll() {
        return computerRepository.findAll();
    }

    public Optional<Computer> findById(Long id) {
        return computerRepository.findById(id);
    }

    public List<Computer> saveAll(List<Computer> computerList) {
        return (List<Computer>) computerRepository.saveAll(computerList);
    }

    public void delete(Computer computer) {
        computerRepository.delete(computer);
    }

    public void deleteById(Long id) {
        computerRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return computerRepository.existsById(id);
    }
    public List<Computer> findByColumnNameAndValue(String columnName, String columnValue){
        return computerRepository.findByColumnNameAndValue(columnName, columnValue);
    }

    public List<Computer> findAllByUser(User user) {
        return computerRepository.findAllByUser(user);
    }
}
