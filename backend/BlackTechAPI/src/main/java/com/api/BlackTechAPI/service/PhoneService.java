package com.api.BlackTechAPI.service;

import com.api.BlackTechAPI.components.validation.PhoneValidate;
import com.api.BlackTechAPI.dto.post.PhonePost;
import com.api.BlackTechAPI.dto.put.PhonePut;
import com.api.BlackTechAPI.mapper.PhoneMapper;
import com.api.BlackTechAPI.model.PhoneModel;
import com.api.BlackTechAPI.model.UserModel;
import com.api.BlackTechAPI.repository.PhoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private PhoneValidate phoneValidate;


    @Transactional
    public PhoneModel save(PhonePost phonePost, UserModel userModel) {
        phoneValidate.isValid(phonePost);
        PhoneModel phoneModel = PhoneMapper.toMapper(phonePost);
        phoneModel.setUserModel(userModel);

        return phoneRepository.save(phoneModel);
    }

    @Transactional
    public void delete(Integer id, UserModel userModel) {
        phoneValidate.validateDelete(id, userModel);
        phoneRepository.deleteById(id);
    }

    public PhoneModel update(Integer id, PhonePut phonePut) {
        phoneValidate.validateUpdate(id, phonePut);
        PhoneModel phoneModel = PhoneMapper.toMapper(phonePut, findById(id));

        return phoneRepository.save(phoneModel);
    }

    public PhoneModel findById(Integer id) {
        return phoneValidate.findById(id);
    }

    public List<PhoneModel> findAllByUserModel(UserModel userModel) {
        return phoneRepository.findAllByUserModel(userModel);
    }

    public List<PhoneModel> findAll() {
        return phoneRepository.findAll();
    }
}
