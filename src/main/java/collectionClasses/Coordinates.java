package collectionClasses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Coordinates {
    private Double x; //Значение поля должно быть больше -682, Поле не может быть null
    private double y;
}
