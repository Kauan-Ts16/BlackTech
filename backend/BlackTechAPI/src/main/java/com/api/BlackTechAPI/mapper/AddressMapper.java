package com.api.BlackTechAPI.mapper;

import com.api.BlackTechAPI.dto.post.AddressPost;
import com.api.BlackTechAPI.dto.put.AddressPut;
import com.api.BlackTechAPI.model.AddressModel;
import org.springframework.beans.BeanUtils;

public class AddressMapper {

    public static AddressModel toMapper(AddressPost input) {
        AddressModel output = new AddressModel();
        BeanUtils.copyProperties(input, output);
        return output;
    }

    public static AddressModel toMapper(AddressPut input, AddressModel output) {
        BeanUtils.copyProperties(input, output);
        return output;
    }
}
