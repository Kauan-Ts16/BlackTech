package com.api.BlackTechAPI.components.validation;

import com.api.BlackTechAPI.dto.post.AddressPost;
import com.api.BlackTechAPI.dto.put.AddressPut;
import com.api.BlackTechAPI.exception.ExceptionGeneric;
import com.api.BlackTechAPI.model.AddressModel;
import com.api.BlackTechAPI.model.UserModel;
import com.api.BlackTechAPI.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressValidate {

    @Autowired
    private AddressRepository addressRepository;

    public void isValid(AddressPost addressPost, UserModel userModel) {
        validateNumber(addressPost.number());
        validatePostalCode(addressPost.postalCode());
        validateUniqueAddressForUser(addressPost, userModel);
    }

    public void validateDelete(Integer id) {
        verifyExists(id);
    }

    public void validateUpdate(Integer id, AddressPut addressPut) {
        verifyExists(id);
        validateNumber(addressPut.number());
        validatePostalCode(addressPut.postalCode());
    }

    public void validateNumber(int number) {
        if (number <= 0) {
            throw new ExceptionGeneric("Number is not valid!", "Number must be a positive value.", 400);
        }
    }

    public void validatePostalCode(String postalCode) {
        if (!postalCode.matches("^\\d{5}-\\d{3}$")) {
            throw new ExceptionGeneric("Postal code is not valid!", "Postal code must follow the format XXXXX-XXX.", 400);
        }
    }

    public void validateUniqueAddressForUser(AddressPost addressPost, UserModel userModel) {
        if (addressRepository.existsByNumberAndPostalCodeAndUserModel(addressPost.number(), addressPost.postalCode(), userModel)) {
            throw new ExceptionGeneric("Address already exists for this user!",
                    "This address (postal code and number) is already associated with the user.", 400);
        }
    }

    public void verifyExists(Integer id) {
        if (!addressRepository.existsById(id)) {
            throw new ExceptionGeneric("Address not exists!", "Address with id: "+ id +" not exists.", 404);
        }
    }

    public AddressModel findById(Integer id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ExceptionGeneric("Address not found!", "Address with id: "+ id +" not found.", 404));
    }

}
