package me.ersystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author MotYim mohamed.motyim@gmail.com
 * @since 22-Mar-2019
 */
@Data
@AllArgsConstructor
public class IncidentTypeStatDto {

    String type;
    long count;

}
