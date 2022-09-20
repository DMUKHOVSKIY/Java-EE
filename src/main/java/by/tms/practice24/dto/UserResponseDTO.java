package by.tms.practice24.dto;

import by.tms.practice24.entity.AppUserRole;
import lombok.Data;

import java.util.List;

/**
 * @author Simon Pirko on 12.09.22
 */
@Data
public class UserResponseDTO {

	private Integer id;
	private String username;
	private String email;
	List<AppUserRole> appUserRoles;

}
