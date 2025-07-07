package com.ms_spring_brgy.user.services.components;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class Crud_Service<T> {
    private final JpaRepository<T, Integer> repo;


    public Crud_Service(JpaRepository<T, Integer> repo) {
        this.repo = repo;
    }

    // find all method
    public List<T> customFindAll() {
        List<T> response = repo.findAll();

        if(response.isEmpty()) {
            return Collections.emptyList();
        }
        return response;
    }

    // find by id method
    public T customFindById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));
    }

    //save method
    public T customAdd(T model) {
        return repo.save(model);
    }


    //delete by id method
    public String customDelete(int id) {
        repo.deleteById(id);
        return "Delete Successfully";
    }
}
