package breakerControl.command;

import protection.model.common.DataAttribute;
import lombok.Getter;
import lombok.Setter;

/** Класс DPC (дублированное управление и состояние)
 * (описан в МЭК 61850-7-3) */
@Setter @Getter
public class DPC {
    private DataAttribute<Byte> stVal = new DataAttribute<>((byte) 0);
    private DataAttribute<Boolean> ctVal = new DataAttribute<>(false);
}
