package protection.model.common;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class DATA {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String reference;

    @Getter @Setter
    private DATA parent;

    @Getter
    private final List<DATA> children = new ArrayList<>();
}
