package protection.model.dataobjects.measurements;

import lombok.Getter;
import lombok.Setter;
import protection.model.common.DATA;

public class Vector extends DATA {

    @Getter @Setter
    private AnalogueValue mag = new AnalogueValue();

    @Getter @Setter
    private AnalogueValue ang = new AnalogueValue();
}
