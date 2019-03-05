package me.ersystem.dto;

import lombok.Data;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@Data
public class IncidentDto {

    private String type;
    private String location;
    private String descrption;
    private int userId;

}
