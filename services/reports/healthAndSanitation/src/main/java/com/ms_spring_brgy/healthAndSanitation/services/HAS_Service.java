package com.ms_spring_brgy.healthAndSanitation.services;

import com.ms_spring_brgy.healthAndSanitation.model.HAS_Model;
import com.ms_spring_brgy.healthAndSanitation.repository.HAS_Repository;
import com.ms_spring_brgy.healthAndSanitation.utils.Crud_Utils;
import com.ms_spring_brgy.healthAndSanitation.utils.Service_Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HAS_Service {
    private final HAS_Repository repo;
    private final Crud_Utils<HAS_Model> crudUtils;

    //find all health and sanitation
    public List<HAS_Model> getAllHaS() {
        return crudUtils.customFindAll();
    }

    //find by id service
    public HAS_Model getFindById(int id) {
        return crudUtils.customFindById(id);
    }

    //add Health and Sanitation service
    public HAS_Model postAddHaS(HAS_Model body) {
        return crudUtils.customAdd(body);
    }

    //update service
    public HAS_Model patchUpdate(HAS_Model body) {
        HAS_Model exist = crudUtils.customFindById(body.getId());

        Service_Utils.updateService(exist.getReporting_name(), body.getReporting_name(), exist::setReporting_name);
        Service_Utils.updateService(exist.getReporting_contactNo(), body.getReporting_contactNo(), exist::setReporting_contactNo);

        Service_Utils.updateService(exist.getDate(), body.getDate(), exist::setDate);
        Service_Utils.updateService(exist.getTime(), body.getTime(), exist::setTime);
        Service_Utils.updateService(exist.getLocation(), body.getLocation(), exist::setLocation);

        Service_Utils.updateService(exist.getDescription(), body.getDescription(), exist::setDescription);
        Service_Utils.updateService(exist.getCondition(), body.getCondition(), exist::setCondition);
        Service_Utils.updateService(exist.getRecommendations(), body.getRecommendations(), exist::setRecommendations);
        Service_Utils.updateService(exist.getEndorsement(), body.getEndorsement(), exist::setEndorsement);

        Service_Utils.updateService(exist.getConcern(), body.getConcern(), exist::setConcern);
        Service_Utils.updateService(exist.getAction(), body.getAction(), exist::setAction);

        return repo.save(exist);
    }

    //delete service
    public String deleteService(int id) {
        return crudUtils.customDelete(id);
    }
}
