package protection.model.dataobjects.protection;

import lombok.Getter;
import lombok.Setter;
import protection.model.common.DATA;
import protection.model.common.DataAttribute;

/** Класс ACT (сведения об активации защиты)
 * (описан в МЭК 61850-7-3) */
@Getter @Setter
public class ACT extends DATA {

    private DataAttribute<Boolean> general = new DataAttribute<>(false);
    private DataAttribute<Boolean> phsA = new DataAttribute<>(false);
    private DataAttribute<Boolean> phsB = new DataAttribute<>(false);
    private DataAttribute<Boolean> phsC = new DataAttribute<>(false);
    private DataAttribute<Boolean> neut = new DataAttribute<>(false);

}
