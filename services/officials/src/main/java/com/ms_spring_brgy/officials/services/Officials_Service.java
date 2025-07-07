package com.ms_spring_brgy.officials.services;

import com.ms_spring_brgy.officials.enums.Position;
import com.ms_spring_brgy.officials.model.Official_Model;
import com.ms_spring_brgy.officials.repository.Officials_Repository;
import com.ms_spring_brgy.officials.services.components.Crud_Service;
import com.ms_spring_brgy.officials.services.components.Service_Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class Officials_Service {
    private final Officials_Repository repo;
    private final Crud_Service<Official_Model> crudService;
    // list all officials
    public List<Official_Model> getAllOfficials() {
        return crudService.customFindAll();
    }

    //find official by id
    public Official_Model getOfficialById(int id) {
        return crudService.customFindById(id);
    }

    //add official
    public Official_Model postAddOfficial(Official_Model body) {
        return repo.save(body);
    }

    //update official
    public Official_Model patchUpdateOfficial(Official_Model body) {
        Official_Model exist = crudService.customFindById(body.getId());

        Service_Component.updateService(exist.getTerm_end(), body.getTerm_end(), exist::setTerm_end);
        Service_Component.updateService(exist.getTerm_start(), body.getTerm_start(), exist::setTerm_start);
        Service_Component.updateService(exist.getResident_id(), body.getResident_id(), exist::setResident_id);
        Service_Component.updateService(exist.getPosition(), body.getPosition(), exist::setPosition);

        return repo.save(exist);
    }

    //delete by id
    public String deleteOfficialById(int id) {
        return crudService.customDelete(id);
    }

    //find captain
    public Official_Model findCaptain() {
        return repo.findAll()
                .stream().filter(cap -> cap.getPosition() != null && cap.getPosition() == Position.PUNONG_BARANGAY)
                .findFirst()
                .orElse(null);

    }
//    public String captain_name() {
//        Official_Model captain = findCaptain();
//        return Optional.ofNullable(captain)
//                .map(Official_Model::getResident_id)
//                .map(res_name -> Stream.of(res_name.getFirstname(), res_name.getMiddlename(), res_name.getLastname())
//                        .filter(Objects::nonNull)
//                        .filter(res -> !res.isEmpty())
//                        .collect(Collectors.joining(" "))
//                ).orElse("");
//    }

//    public String captain_position() {
//        Official_Model captain = findCaptain();
//        return Optional.ofNullable(captain)
//                .map(Official_Model::getPosition)
//                .map(OfficialsPositionModel::getPosition)
//                .orElse("");
//    }
}
