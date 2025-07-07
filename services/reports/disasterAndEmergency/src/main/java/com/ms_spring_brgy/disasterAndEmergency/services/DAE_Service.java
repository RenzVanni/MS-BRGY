package com.ms_spring_brgy.disasterAndEmergency.services;

import com.ms_spring_brgy.disasterAndEmergency.model.DAE_Model;
import com.ms_spring_brgy.disasterAndEmergency.repository.DAE_Repository;
import com.ms_spring_brgy.utils.Crud_Utils;
import com.ms_spring_brgy.utils.Service_Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DAE_Service {
    private final DAE_Repository repo;
    private final Crud_Utils<DAE_Model> crudUtils;

    //find all disaster and emergency
    public List<DAE_Model> getAllDaE() {
        return crudUtils.customFindAll();
    }

    //find by id service
    public DAE_Model getFindById(int id) {
        return crudUtils.customFindById(id);
    }

    //add Disaster and Emergency service
    public DAE_Model postAddDaE(DAE_Model body) {
        return crudUtils.customAdd(body);
    }

    //update service
    public DAE_Model patchUpdate(DAE_Model body) {
        DAE_Model exist = crudUtils.customFindById(body.getId());

        Service_Utils.updateService(exist.getReporting_name(), body.getReporting_name(), exist::setReporting_name);
        Service_Utils.updateService(exist.getReporting_contactNo(), body.getReporting_contactNo(), exist::setReporting_contactNo);

        Service_Utils.updateService(exist.getDate(), body.getDate(), exist::setDate);
        Service_Utils.updateService(exist.getTime(), body.getTime(), exist::setTime);
        Service_Utils.updateService(exist.getLocation(), body.getLocation(), exist::setLocation);

        Service_Utils.updateService(exist.getAffected_area(), body.getAffected_area(), exist::setAffected_area);
        Service_Utils.updateService(exist.getDamage_assessment(), body.getDamage_assessment(), exist::setDamage_assessment);

        Service_Utils.updateService(exist.getInjured(), body.getInjured(), exist::setInjured);
        Service_Utils.updateService(exist.getMissing(), body.getMissing(), exist::setMissing);
        Service_Utils.updateService(exist.getDisplaced(), body.getDisplaced(), exist::setDisplaced);
        Service_Utils.updateService(exist.getCasualties(), body.getCasualties(), exist::setCasualties);

        Service_Utils.updateService(exist.getNeeds_and_assistance(), body.getNeeds_and_assistance(), exist::setNeeds_and_assistance);
        Service_Utils.updateService(exist.getFollow_up_action(), body.getFollow_up_action(), exist::setFollow_up_action);

        Service_Utils.updateService(exist.getType(), body.getType(), exist::setType);
        Service_Utils.updateService(exist.getAction_preparedness(), body.getAction_preparedness(), exist::setAction_preparedness);
        Service_Utils.updateService(exist.getAction_recovery(), body.getAction_recovery(), exist::setAction_recovery);
        Service_Utils.updateService(exist.getAction_response(), body.getAction_response(), exist::setAction_response);
        Service_Utils.updateService(exist.getAgencies(), body.getAgencies(), exist::setAgencies);

        return repo.save(exist);
    }

    //delete service
    public String deleteService(int id) {
        return crudUtils.customDelete(id);
    }
}
