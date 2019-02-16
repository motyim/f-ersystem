package me.motyim.freelance.ersystem.dto;

import lombok.Data;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@Data
public class UpdateUserDto {
    private int id;
    private String username;
    private String phoneNumber;
}