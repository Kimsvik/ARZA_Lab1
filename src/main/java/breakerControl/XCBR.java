package breakerControl;


import breakerControl.command.DPC;
import breakerControl.status.SPC;
import protection.model.logicalnodes.common.LN;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class XCBR extends LN {
    private DPC Pos = new DPC();
    private SPC BlkOpn = new SPC();
    private SPC BlkCls = new SPC();




    @Override
    public void process() {
        if(!Pos.getCtVal().getValue() && Pos.getStVal().getValue() == 2){
            Pos.getStVal().setValue((byte) 1);
        }
        if(Pos.getCtVal().getValue() && Pos.getStVal().getValue() == 1){
            Pos.getStVal().setValue((byte) 2);
        }
    }
}
