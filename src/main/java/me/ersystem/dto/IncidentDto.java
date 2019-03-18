package me.ersystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@Data
public class IncidentDto {
    private int id;
    private String type;
    private String location;
    private String descrption;
    private Date date;
    private String status;
    private int userId;
    private String photo;
    private String lat;
    private String lng;
}
