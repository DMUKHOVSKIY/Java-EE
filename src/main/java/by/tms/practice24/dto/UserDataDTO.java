package by.tms.practice24.dto;

import by.tms.practice24.entity.AppUserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Simon Pirko on 12.09.22
 */
@Data
@NoArgsConstructor
public class UserDataDTO { //можем заметить, что  тут нет id
	//Это класс, в котором находится набор полей, которым невозможно приписать никакой entity
	//Просто обычный объект для переноса данных
	//В реальной жизни нам нужно получать DTO из Json и конвертировать его в какую-то модель, либо entity
	//Для этого мы должны создавать свои собственные классы Mapper-ы и конвертировать из например
	// UserDataDTO в обычные entity, но существуют автоматические мапперы


	private String username;
	private String email;
	private String password;
	List<AppUserRole> appUserRoles;

}
