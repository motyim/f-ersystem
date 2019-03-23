package me.ersystem.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@Data
public class IncidentResponse {
    private Integer id;
    private String type;
    private Date date;
    private String status;
}
