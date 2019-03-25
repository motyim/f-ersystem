package me.ersystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @since 09-Feb-19
 */
@Data
@ApiModel("User")
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @ApiModelProperty(hidden = true)
    private int id;
    private String username;
    private String phoneNumber;
    private String email;
    private int code;
}
