package breakerControl.status;

import protection.model.common.DataAttribute;
import lombok.Getter;
import lombok.Setter;

/** Класс SPC (недублированное управление и состояние)
 * (описан в МЭК 61850-7-3) */
@Getter @Setter
public class SPC {
    private DataAttribute<Boolean> stVal = new DataAttribute<>( false);
}
