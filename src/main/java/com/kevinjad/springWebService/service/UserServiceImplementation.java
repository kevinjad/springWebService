package com.kevinjad.springWebService.service;

import com.kevinjad.springWebService.UserRepository;
import com.kevinjad.springWebService.entity.UserEntity;
import com.kevinjad.springWebService.shared.dto.UserDto;
import com.kevinjad.springWebService.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto user) {
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("Email Already Exist");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        userEntity.setUserId(utils.generateUserId(30));
        userEntity.setEncryptedPassword("123");
        UserEntity storedUser = userRepository.save(userEntity);
        UserDto returnUserDto = new UserDto();
        BeanUtils.copyProperties(storedUser,returnUserDto);

        return returnUserDto;
    }
}
