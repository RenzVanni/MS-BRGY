package com.ms_spring_brgy.user.services;

import com.ms_spring_brgy.user.model.Account_Model;
import com.ms_spring_brgy.user.repository.Account_Repo;
import com.ms_spring_brgy.user.services.components.Crud_Service;
import com.ms_spring_brgy.user.services.components.Service_Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Account_Service {
    private final Account_Repo repo;
    private final Crud_Service<Account_Model> crudService;

    public Account_Service(Account_Repo repo, Crud_Service<Account_Model> crudService) {
        this.repo = repo;
        this.crudService = crudService;
    }

    //find all account
    public List<Account_Model> findAllAccount() {
        return crudService.customFindAll();
    }

    //find by id
    public Account_Model getUserById(int id) {
        return crudService.customFindById(id);
    }

    //update account
    public Account_Model patchUpdateAccount(Account_Model body) {
        Account_Model exist = crudService.customFindById(body.getId());

        Service_Component.updateService(exist.getEmail(), body.getEmail(), exist::setEmail);
        Service_Component.updateService(exist.getUsername(), body.getUsername(), exist::setUsername);
        Service_Component.updateService(exist.getPassword(), body.getPassword(), exist::setPassword);
        Service_Component.updateService(exist.getResident_id(), body.getResident_id(), exist::setResident_id);

        return repo.save(exist);
    }

    //delete by id
    public String deleteAccountById(int id) {
        return crudService.customDelete(id);
    }
}
