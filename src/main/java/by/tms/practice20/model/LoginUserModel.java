package by.tms.practice20.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

//Так как валидируя юзера, мы должны вводить все поля, а при логине, мы вводим только
//username and password. Следовательно мы можем сделать модель - частичное представление бизнес сущности,
//нужную для воссоздания или преобразования исходящих данных
@Data
public class LoginUserModel { //Это отельный класс, чтобы сагрегировать какую-то информацию, которая не подходит
    //ни к одному объекту в реальной жизни. (Под запрос подстроиться)

    @NotBlank
    @NotEmpty
    private String username;

    @NotBlank
    @NotEmpty
    private String password;
}
