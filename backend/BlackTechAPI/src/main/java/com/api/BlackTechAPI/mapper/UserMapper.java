package com.api.BlackTechAPI.mapper;

import com.api.BlackTechAPI.dto.post.UserPost;
import com.api.BlackTechAPI.model.UserModel;
import org.springframework.beans.BeanUtils;

public class UserMapper {

    public static UserModel toMapper(UserPost input) {
        UserModel output = new UserModel();
        BeanUtils.copyProperties(input, output);
        return output;
    }

    public static UserModel toMapper(UserPost input, UserModel output) {
        BeanUtils.copyProperties(input, output);
        return output;
    }
}
