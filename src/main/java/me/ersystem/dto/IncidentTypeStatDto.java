package me.ersystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @since 22-Mar-2019
 */
@Data
@AllArgsConstructor
public class IncidentTypeStatDto {

    String type;
    long count;

}
