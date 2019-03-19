package me.ersystem.dto;

import lombok.Data;

/**
 * @author MotYim mohamed.motyim@gmail.com
 * @since 19-Mar-2019
 */
@Data
public class UploadImageDto {
    String encodedImage;
    String imageType;
    int incidentID;
}
