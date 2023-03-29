package breakerControl.status;

import protection.model.common.DataAttribute;
import lombok.Getter;
import lombok.Setter;

/** Класс DPS (дублированное состояние)
 * (описан в МЭК 61850-7-3) */
@Getter @Setter
public class DPS {
    private DataAttribute<Byte> stVal = new DataAttribute<>((byte) 2);
}
