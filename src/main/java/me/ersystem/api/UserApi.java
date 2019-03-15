package me.ersystem.api;

import me.ersystem.dto.UserDto;
import me.ersystem.dto.UpdateUserDto;
import me.ersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@RestController
@RequestMapping("user")
public class UserApi {

    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto){
        UserDto dto = service.login(userDto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("singup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto){
        UserDto savedUser = service.addUser(userDto);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto dto){
        UserDto user = service.updateUser(dto);
        return ResponseEntity.ok(user);
    }

}
