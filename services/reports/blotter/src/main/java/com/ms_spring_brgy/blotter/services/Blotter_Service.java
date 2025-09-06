package com.ms_spring_brgy.blotter.services;

import com.ms_spring_brgy.blotter.model.Blotter_Model;
import com.ms_spring_brgy.blotter.repository.Blotter_Repository;
import com.ms_spring_brgy.blotter.utils.Crud_Utils;
import com.ms_spring_brgy.blotter.utils.Service_Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Blotter_Service {
    private final Blotter_Repository repo;
    private final Crud_Utils<Blotter_Model> crudUtils;

    //list all blotter
    public List<Blotter_Model> getAllBlotter() {
        return crudUtils.customFindAll();
    }

    //find blotter by id
    public Blotter_Model getBlotterById(int id) {
        return crudUtils.customFindById(id);
    }

    //add blotter
    public Blotter_Model postAddBlotter(Blotter_Model body) {
        return crudUtils.customAdd(body);
    }

    //update blotter
    public Blotter_Model patchUpdateBlotter(Blotter_Model body) {
        Blotter_Model existing = crudUtils.customFindById(body.getId());

        Service_Utils.updateService(existing.getComplainant(), body.getComplainant(), existing::setComplainant);
        Service_Utils.updateService(existing.getDate(), body.getDate(), existing::setDate);
        Service_Utils.updateService(existing.getDetails(), body.getDetails(), existing::setDetails);
        Service_Utils.updateService(existing.getLocation(), body.getLocation(), existing::setLocation);
        Service_Utils.updateService(existing.getRespondent(), body.getRespondent(), existing::setRespondent);
        Service_Utils.updateService(existing.getTime(), body.getTime(), existing::setTime);
        Service_Utils.updateService(existing.getVictim(), body.getVictim(), existing::setVictim);
        Service_Utils.updateService(existing.getStatus(), body.getStatus(), existing::setStatus);
        Service_Utils.updateService(existing.getType(), body.getType(), existing::setType);

        return repo.save(existing);
    }

    //delete blotter
    public String deleteBlotterById(int id) {
        return crudUtils.customDelete(id);
    }
}
