package com.api.BlackTechAPI.mapper;

import com.api.BlackTechAPI.dto.post.PhonePost;
import com.api.BlackTechAPI.dto.put.PhonePut;
import com.api.BlackTechAPI.model.PhoneModel;
import org.springframework.beans.BeanUtils;

public class PhoneMapper {

    public static PhoneModel toMapper(PhonePost input) {
        PhoneModel output = new PhoneModel();
        BeanUtils.copyProperties(input, output);
        return output;
    }

    public static PhoneModel toMapper(PhonePut input, PhoneModel output) {
        BeanUtils.copyProperties(input, output);
        return output;
    }
}
