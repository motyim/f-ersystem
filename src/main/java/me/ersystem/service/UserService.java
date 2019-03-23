package me.ersystem.service;

import com.sun.xml.internal.bind.v2.TODO;
import me.ersystem.dto.UpdateUserDto;
import me.ersystem.dto.UserDto;
import me.ersystem.entity.User;
import me.ersystem.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@Service
public class UserService {

    @Autowired
    UserRepo repo ;

    @Autowired
    MailService mailService;

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

    public UserDto addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setCode(new Random().nextInt(999-100) + 100);
        User savedUser = repo.save(user);
        // TODO: 3/22/2019 Uncommecnt this one
//        new Thread(() -> mailService.sendMail(user.getEmail(),"Activation Code","You Activaiton code is : "+user.getCode())).run();
        return modelMapper.map(savedUser,UserDto.class);
    }
}
