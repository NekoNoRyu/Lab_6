package collectionClasses;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class Person {
    private String groupAdminName; //Поле не может быть null, Строка не может быть пустой
    private ZonedDateTime groupAdminBirthday; //Поле не может быть null
    private int groupAdminWeight; //Значение поля должно быть больше 0
}
