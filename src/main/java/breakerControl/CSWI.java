package breakerControl;

import breakerControl.command.DPC;
import protection.model.logicalnodes.common.LN;
import protection.model.dataobjects.protection.ACT;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLOutput;

@Getter @Setter
public class CSWI extends LN {
    private ACT OpOpn1  = new ACT();
    private ACT OpOpn2  = new ACT();
    private ACT OpOpn3  = new ACT();
    private ACT OpCls1 = new ACT();
    private ACT OpCls2 = new ACT();
    private ACT OpCls3 = new ACT();

    private DPC Pos = new DPC();
    private DPC PosA = new DPC();
    private DPC PosB = new DPC();
    private DPC PosC = new DPC();

    @Override
    public void process() {
        if (OpOpn1.getGeneral().getValue() || OpOpn2.getGeneral().getValue() || OpOpn3.getGeneral().getValue()){
            Pos.getCtVal().setValue(false);
            System.out.println("Bruh");
        }
    }
}

