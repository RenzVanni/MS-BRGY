package com.ms_spring_brgy.incident.service;

import com.ms_spring_brgy.incident.model.Incident_Model;
import com.ms_spring_brgy.incident.repository.Incident_Repository;
import com.ms_spring_brgy.incident.utils.Crud_Utils;
import com.ms_spring_brgy.incident.utils.Service_Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Incident_Service {
    private final Incident_Repository repo;
    private final Crud_Utils<Incident_Model> crudUtils;

    //find all incident
    public List<Incident_Model> getAllIncident() {
        return crudUtils.customFindAll();
    }

    //find by id service
    public Incident_Model getFindById(int id) {
        return crudUtils.customFindById(id);
    }

    //add incident service
    public Incident_Model postAddIncident(Incident_Model body) {
        return crudUtils.customAdd(body);
    }

    //update service
    public Incident_Model patchUpdate(Incident_Model body) {
        Incident_Model incident = crudUtils.customFindById(body.getId());

        Service_Utils.updateService(incident.getComplainant_name(), body.getComplainant_name(), incident::setComplainant_name);
        Service_Utils.updateService(incident.getComplainant_address(), body.getComplainant_address(), incident::setComplainant_address);
        Service_Utils.updateService(incident.getComplainant_birthdate(), body.getComplainant_birthdate(), incident::setComplainant_birthdate);
        Service_Utils.updateService(incident.getComplainant_contactNo(), body.getComplainant_contactNo(), incident::setComplainant_contactNo);

        Service_Utils.updateService(incident.getRespondent_name(), body.getRespondent_name(), incident::setRespondent_name);
        Service_Utils.updateService(incident.getRespondent_address(), body.getRespondent_address(), incident::setRespondent_address);
        Service_Utils.updateService(incident.getRespondent_birthdate(), body.getRespondent_birthdate(), incident::setRespondent_birthdate);
        Service_Utils.updateService(incident.getRespondent_contactNo(), body.getRespondent_contactNo(), incident::setRespondent_contactNo);

        Service_Utils.updateService(incident.getWitness_name(), body.getWitness_name(), incident::setWitness_name);
        Service_Utils.updateService(incident.getWitness_address(), body.getWitness_address(), incident::setWitness_address);
        Service_Utils.updateService(incident.getWitness_birthdate(), body.getWitness_birthdate(), incident::setWitness_birthdate);
        Service_Utils.updateService(incident.getWitness_contactNo(), body.getWitness_contactNo(), incident::setWitness_contactNo);

        Service_Utils.updateService(incident.getSettlement(), body.getSettlement(), incident::setSettlement);
        Service_Utils.updateService(incident.getRecommendations(), body.getRecommendations(), incident::setRecommendations);

        Service_Utils.updateService(incident.getDate(), body.getDate(), incident::setDate);
        Service_Utils.updateService(incident.getTime(), body.getTime(), incident::setTime);
        Service_Utils.updateService(incident.getLocation(), body.getLocation(), incident::setLocation);

        Service_Utils.updateService(incident.getType(), body.getType(), incident::setType);
        Service_Utils.updateService(incident.getAction(), body.getAction(), incident::setAction);
        Service_Utils.updateService(incident.getOfficial_id(), body.getOfficial_id(), incident::setOfficial_id);

        return repo.save(incident);
    }

    //delete service
    public String deleteService(int id) {
        return crudUtils.customDelete(id);
    }
}
