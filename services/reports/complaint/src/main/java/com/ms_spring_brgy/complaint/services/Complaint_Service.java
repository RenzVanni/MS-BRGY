package com.ms_spring_brgy.complaint.services;

import com.ms_spring_brgy.complaint.model.Complaint_Model;
import com.ms_spring_brgy.complaint.repository.Complaint_Repository;
import com.ms_spring_brgy.utils.Crud_Utils;
import com.ms_spring_brgy.utils.Service_Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Complaint_Service {
    private final Complaint_Repository repo;
    private final Crud_Utils<Complaint_Model> crudUtils;

    //find all complain
    public List<Complaint_Model> getAllComplain() {
        return crudUtils.customFindAll();
    }

    //find by id service
    public Complaint_Model getFindById(int id) {
        return crudUtils.customFindById(id);
    }

    //add complaint service
    public Complaint_Model postAddComplaint(Complaint_Model body) {
        return crudUtils.customAdd(body);
    }

    //update controller
    public Complaint_Model patchUpdate(Complaint_Model body) {
        Complaint_Model complaint = crudUtils.customFindById(body.getId());

        Service_Utils.updateService(complaint.getComplainant_name(), body.getComplainant_name(), complaint::setComplainant_name);
        Service_Utils.updateService(complaint.getComplainant_address(), body.getComplainant_address(), complaint::setComplainant_address);
        Service_Utils.updateService(complaint.getComplainant_birthdate(), body.getComplainant_birthdate(), complaint::setComplainant_birthdate);
        Service_Utils.updateService(complaint.getComplainant_contactNo(), body.getComplainant_contactNo(), complaint::setComplainant_contactNo);

        Service_Utils.updateService(complaint.getRespondent_name(), body.getRespondent_name(), complaint::setRespondent_name);
        Service_Utils.updateService(complaint.getRespondent_address(), body.getRespondent_address(), complaint::setRespondent_address);
        Service_Utils.updateService(complaint.getRespondent_birthdate(), body.getRespondent_birthdate(), complaint::setRespondent_birthdate);
        Service_Utils.updateService(complaint.getRespondent_contactNo(), body.getRespondent_contactNo(), complaint::setRespondent_contactNo);

        Service_Utils.updateService(complaint.getWitness_name(), body.getWitness_name(), complaint::setWitness_name);
        Service_Utils.updateService(complaint.getWitness_address(), body.getWitness_address(), complaint::setWitness_address);
        Service_Utils.updateService(complaint.getWitness_birthdate(), body.getWitness_birthdate(), complaint::setWitness_birthdate);
        Service_Utils.updateService(complaint.getWitness_contactNo(), body.getWitness_contactNo(), complaint::setWitness_contactNo);

        Service_Utils.updateService(complaint.getDate(), body.getDate(), complaint::setDate);
        Service_Utils.updateService(complaint.getTime(), body.getTime(), complaint::setTime);
        Service_Utils.updateService(complaint.getLocation(), body.getLocation(), complaint::setLocation);

        Service_Utils.updateService(complaint.getStatement_of_complaint(), body.getStatement_of_complaint(), complaint::setStatement_of_complaint);
        Service_Utils.updateService(complaint.getEvidence(), body.getEvidence(), complaint::setEvidence);

        Service_Utils.updateService(complaint.getType_id(), body.getType_id(), complaint::setType_id);
        Service_Utils.updateService(complaint.getAction_id(), body.getAction_id(), complaint::setAction_id);
        Service_Utils.updateService(complaint.getRelief_id(), body.getRelief_id(), complaint::setRelief_id);

        return repo.save(complaint);
    }

    //delete service
    public String deleteService(int id) {
        return crudUtils.customDelete(id);
    }
}
