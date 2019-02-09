package me.motyim.freelance.ersystem.service;

import me.motyim.freelance.ersystem.dto.UpdateUserDto;
import me.motyim.freelance.ersystem.dto.UserDto;
import me.motyim.freelance.ersystem.entity.User;
import me.motyim.freelance.ersystem.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@Service
public class UserService {

    @Autowired
    UserRepo repo ;

    @Autowired
    ModelMapper modelMapper ;

    public UserDto login(UserDto userDto){
        Optional<User> user = repo.findOneByUsernameAndPhoneNumber(userDto.getUsername(), userDto.getPhoneNumber());
        if(!user.isPresent())
            throw new RuntimeException("User not found");

        UserDto dto = modelMapper.map(user.get(), UserDto.class);
        return dto;
    }

    public UserDto updateUser(UpdateUserDto dto) {
        User user = modelMapper.map(dto, User.class);
        repo.save(user);
        return modelMapper.map(user,UserDto.class);
    }
}
