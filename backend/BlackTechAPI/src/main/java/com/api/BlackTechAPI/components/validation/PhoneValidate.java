package com.api.BlackTechAPI.components.validation;

import com.api.BlackTechAPI.dto.post.PhonePost;
import com.api.BlackTechAPI.dto.put.PhonePut;
import com.api.BlackTechAPI.enums.RoleName;
import com.api.BlackTechAPI.exception.ExceptionGeneric;
import com.api.BlackTechAPI.model.PhoneModel;
import com.api.BlackTechAPI.model.UserModel;
import com.api.BlackTechAPI.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneValidate {

    @Autowired
    private PhoneRepository phoneRepository;


    public void isValid(PhonePost phonePost, UserModel userModel) {
        validateUserPhoneLimit(userModel);
        validatePhone(phonePost.number(), phonePost.areaCode(), phonePost.countryCode());
    }

    public void validateDelete(Integer id, UserModel userModel) {
        verifyExists(id);
        validateUser(id, userModel);
    }

    public void validateUpdate(Integer id, PhonePut phonePut) {
        verifyExists(id);
        validatePhone(phonePut.number(), phonePut.areaCode(), phonePut.countryCode());
    }

    private void validatePhone(String number, String areaCode, String countryCode) {
        validateNumber(number);
        validateAreaCode(areaCode);
        validateCountryCode(countryCode);
        validateUniqueNumber(number);
    }

    private void validateNumber(String number) {
        if (!number.matches("^\\d{5}-\\d{4}$")) {
            throw new ExceptionGeneric("Phone number is not valid!", "Number must follow the format XXXXX-XXXX and contain exactly 9 digits.", 400);
        }
    }

    private void validateAreaCode(String areaCode) {
        if (!areaCode.matches("^\\d{2}$")) {
            throw new ExceptionGeneric("Area Code is not valid!", "Area Code must contain exactly 2 digits.", 400);
        }
    }

    private void validateCountryCode(String countryCode) {
        if (!countryCode.matches("^\\+\\d{1,3}$")) {
            throw new ExceptionGeneric("Country Code is not valid!", "Country Code must start with a '+' and contain between 1 and 3 digits.", 400);
        }
    }

    private void validateUniqueNumber(String number) {
        if (phoneRepository.existsByNumber(number)) {
            throw new ExceptionGeneric("Phone number already exists!", "The phone number '" + number + "' is already registered in the system.", 400);
        }
    }

    private void validateUserPhoneLimit(UserModel userModel) {
        if (phoneRepository.countByUserModel(userModel) >= 2) {
            throw new ExceptionGeneric("Phone limit exceeded!", "User can have a maximum 2 phone numbers.", 400);
        }
    }

    private void validateUser(Integer id, UserModel userModel) {
        PhoneModel phoneModel = findById(id);
        boolean isOwner = userModel.getUserId().equals(phoneModel.getUserModel().getUserId());
        boolean isAdmin = userModel.getRole().getRoleName() == RoleName.ROLE_ADMIN;

        if (!isOwner && !isAdmin) {
            throw new ExceptionGeneric("Unauthorized action!", "You do not have permission to delete this phone number.", 403);
        }
    }

    public void verifyExists(Integer id) {
        if (!phoneRepository.existsById(id)) {
            throw new ExceptionGeneric("Phone not exists!", "Phone with id: "+ id +" not exists.", 404);
        }
    }

    public PhoneModel findById(Integer id) {
        return phoneRepository.findById(id)
                .orElseThrow(() -> new ExceptionGeneric("Phone not found!", "Phone with id: "+ id +" not found.", 404));
    }
}
