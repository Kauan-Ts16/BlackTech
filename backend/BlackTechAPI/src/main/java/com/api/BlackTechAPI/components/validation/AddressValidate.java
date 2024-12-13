package com.api.BlackTechAPI.components.validation;

import com.api.BlackTechAPI.dto.post.AddressPost;
import com.api.BlackTechAPI.dto.put.AddressPut;
import com.api.BlackTechAPI.enums.RoleName;
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
        validateUserAddressLimit(userModel);
        validateAddress(addressPost, userModel);
    }

    public void validateDelete(Integer id, UserModel userModel) {
        verifyExists(id);
        validateUser(id, userModel);
    }

    public void validateUpdate(Integer id, AddressPut addressPut) {
        verifyExists(id);
        validateNumber(addressPut.number());
        validatePostalCode(addressPut.postalCode());
    }

    private void validateAddress(AddressPost addressPost, UserModel userModel) {
        validateNumber(addressPost.number());
        validatePostalCode(addressPost.postalCode());
        validateUniqueAddressForUser(addressPost, userModel);
    }

    private void validateNumber(int number) {
        if (number <= 0) {
            throw new ExceptionGeneric("Number is not valid!", "Number must be a positive value.", 400);
        }
    }

    private void validatePostalCode(String postalCode) {
        if (!postalCode.matches("^\\d{5}-\\d{3}$")) {
            throw new ExceptionGeneric("Postal code is not valid!", "Postal code must follow the format XXXXX-XXX.", 400);
        }
    }

    private void validateUniqueAddressForUser(AddressPost addressPost, UserModel userModel) {
        if (addressRepository.existsByNumberAndPostalCodeAndUserModel(addressPost.number(), addressPost.postalCode(), userModel)) {
            throw new ExceptionGeneric("Address already exists for this user!",
                    "This address (postal code and number) is already associated with the user.", 400);
        }
    }

    private void validateUserAddressLimit(UserModel userModel) {
        if (addressRepository.countByUserModel(userModel) >= 2) {
            throw new ExceptionGeneric("Address limit exceeded!", "User can have a maximum 2 address.", 400);
        }
    }

    private void validateUser(Integer id, UserModel userModel) {
        AddressModel addressModel = findById(id);
        boolean isOwner = userModel.getUserId().equals(addressModel.getUserModel().getUserId());
        boolean isAdmin = userModel.getRole().getRoleName() == RoleName.ROLE_ADMIN;

        if (!isOwner && !isAdmin) {
            throw new ExceptionGeneric("Unauthorized action!", "You do not have permission to delete this address.", 403);
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
