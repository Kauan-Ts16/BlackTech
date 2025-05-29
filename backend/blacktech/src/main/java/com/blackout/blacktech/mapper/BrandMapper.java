package com.blackout.blacktech.mapper;

import com.blackout.blacktech.dto.post.BrandPostDto;
import com.blackout.blacktech.dto.put.BrandPutDto;
import com.blackout.blacktech.model.BrandModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class BrandMapper {

    public static BrandModel toMapper(BrandPostDto input) {
        BrandModel output = new BrandModel();
        output.setCreatedDate(LocalDateTime.now());
        output.setUpdateDate(LocalDateTime.now());
        BeanUtils.copyProperties(input, output);

        return output;
    }

    public static BrandModel toMapper(BrandPutDto input, BrandModel output) {
        BeanUtils.copyProperties(input, output);
        output.setUpdateDate(LocalDateTime.now());

        return output;
    }
}
