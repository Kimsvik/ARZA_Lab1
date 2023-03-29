package protection.model.dataobjects.settings;

import lombok.Getter;
import protection.model.common.DATA;
import protection.model.dataobjects.measurements.AnalogueValue;

public class ASG extends DATA {

    @Getter
    private final AnalogueValue setMag = new AnalogueValue();
}
