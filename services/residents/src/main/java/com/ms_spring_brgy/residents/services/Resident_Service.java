package com.ms_spring_brgy.residents.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ms_spring_brgy.residents.model.Resident_Model;
import com.ms_spring_brgy.residents.repository.Resident_Repo;
import com.ms_spring_brgy.residents.services.components.Crud_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class Resident_Service {
    private final Resident_Repo repo;
    private final Crud_Service<Resident_Model> crudService;
    private final Cloudinary cloudinary;

    /**
     * Paginate residents
     * @return
     */
    public List<Resident_Model> paginateResidents(int page) {
        return repo.paginateResident(page);
    }

    //find resident by ID
    public Resident_Model getResidentById(int id) {
        return crudService.customFindById(id);
    }

    //add resident
    public Resident_Model postAddResident(MultipartFile imageFile,
                                         Resident_Model resident) {
        try {
            if(!imageFile.isEmpty()) {
                var saveInFolder = ObjectUtils.asMap(
                        "folder", "residents"
                );

                var cloudinaryData = cloudinary
                        .uploader()
                        .upload(imageFile.getBytes(), saveInFolder);

                String url = cloudinaryData.get("secure_url").toString();

                resident.setProfile_image_url(url);
            }

        } catch (Exception e) {
            throw new RuntimeException("Something went wrong setting image profile!");
        }

        return repo.save(resident);
    }

    //add multiple
    public List<Resident_Model> postMultipleUpload(List<MultipartFile> imageFile, List<Resident_Model> body) {
        try {
            for (int i = 0; i < body.size(); i++) {
                if(!imageFile.get(i).isEmpty()) {
                    var cloudinaryData = cloudinary.uploader().upload(imageFile.get(i).getBytes(), ObjectUtils.emptyMap());
                    String url = cloudinaryData.get("secure_url").toString();
                    body.get(i).setProfile_image_url(url);
                }
            }
            return repo.saveAll(body);
        } catch (Exception e) {
            throw new RuntimeException("Something Went Wrong!");
        }
    }

    //update resident
    public Resident_Model patchUpdateResident(MultipartFile file,
                                             Resident_Model resident) {
        try {
            Resident_Model existing = crudService.customFindById(resident.getId());

            if (!file.isEmpty()) {
                Map cloudinaryUpload = cloudinary
                        .uploader()
                        .upload(file.getBytes(), ObjectUtils.emptyMap());
                String url = cloudinaryUpload.get("secure_url").toString();
                existing.setProfile_image_url(url);
            }

            if(!Objects.equals(existing.getFirstname(), resident.getFirstname())) {
                existing.setFirstname(resident.getFirstname());
            }
            if(!Objects.equals(existing.getMiddlename(), resident.getMiddlename())) {
                existing.setMiddlename(resident.getMiddlename());
            }
            if(!Objects.equals(existing.getLastname(), resident.getLastname())) {
                existing.setLastname(resident.getLastname());
            }
            if(!Objects.equals(existing.getGender(), resident.getGender())) {
                existing.setGender(resident.getGender());
            }
            if(!Objects.equals(existing.getBirth_date(), resident.getBirth_date())) {
                existing.setBirth_date(resident.getBirth_date());
            }
            if(!Objects.equals(existing.getBirth_place(), resident.getBirth_place())) {
                existing.setBirth_place(resident.getBirth_place());
            }
            if(!Objects.equals(existing.getCivil_status(), resident.getCivil_status())) {
                existing.setCivil_status(resident.getCivil_status());
            }
            if(!Objects.equals(existing.getAddress(), resident.getAddress())) {
                existing.setAddress(resident.getAddress());
            }

            if(!Objects.equals(existing.getContact_no(), resident.getContact_no())) {
                existing.setContact_no(resident.getContact_no());
            }
            if(!Objects.equals(existing.getVoter_status(), resident.getVoter_status())) {
                existing.setVoter_status(resident.getVoter_status());
            }
            if(!Objects.equals(existing.getCitizenship(), resident.getCitizenship())) {
                existing.setCitizenship(resident.getCitizenship());
            }

            existing.setOSY(resident.getOSY());
            existing.setPWD(resident.getPWD());

            return repo.save(existing);

        } catch (Exception e) {
            throw new RuntimeException("Something Went Wrong!: " + e );
        }
    }

    //delete resident
    public String deleteResidentById(int id) {
        return crudService.customDelete(id);
    }
}
