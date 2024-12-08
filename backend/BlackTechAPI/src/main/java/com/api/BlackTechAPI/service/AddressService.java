package com.api.BlackTechAPI.service;

import com.api.BlackTechAPI.components.validation.AddressValidate;
import com.api.BlackTechAPI.dto.post.AddressPost;
import com.api.BlackTechAPI.dto.put.AddressPut;
import com.api.BlackTechAPI.mapper.AddressMapper;
import com.api.BlackTechAPI.model.AddressModel;
import com.api.BlackTechAPI.model.UserModel;
import com.api.BlackTechAPI.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressValidate addressValidate;


    @Transactional
    public AddressModel save (AddressPost addressPost, UserModel userModel) {
        addressValidate.isValid(addressPost, userModel);
        AddressModel addressModel = AddressMapper.toMapper(addressPost);
        addressModel.setUserModel(userModel);

        return addressRepository.save(addressModel);
    }

    @Transactional
    public void delete(Integer id) {
        addressValidate.validateDelete(id);
        addressRepository.deleteById(id);
    }

    public AddressModel update(Integer id, AddressPut addressPut) {
        addressValidate.validateUpdate(id, addressPut);
        AddressModel addressModel = AddressMapper.toMapper(addressPut, findById(id));

        return addressRepository.save(addressModel);
    }

    public AddressModel findById(Integer id) {
        return addressValidate.findById(id);
    }

    public List<AddressModel> findAllByUserModel(UserModel userModel) {
        return addressRepository.findAllByUserModel(userModel);
    }

    public List<AddressModel> findAll() {
        return addressRepository.findAll();
    }
}
