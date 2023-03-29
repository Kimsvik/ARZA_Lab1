package protection.model.logicalnodes.protections;

import lombok.Getter;
import lombok.Setter;
import protection.model.dataobjects.measurements.WYE;
import protection.model.dataobjects.protection.ACT;
import protection.model.dataobjects.protection.ING;
import protection.model.dataobjects.settings.ASG;
import protection.model.logicalnodes.common.LN;

public class PTOC extends LN {

    /** Входы */
    @Getter @Setter
    public WYE A = new WYE();

    public ASG StrVal = new ASG();

    public ING OpDlTmms = new ING();

    private double CntA = 0;
    private double CntB = 0;
    private double CntC = 0;

    /** Выходы */
    public final ACT Op = new ACT();



    /** Переменные */
    @Override
    public void process() {

        /** Факт превышения тока срабатывания */
        boolean phsA = A.getPhsA().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue() ;
        boolean phsB = A.getPhsB().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue() ;
        boolean phsC = A.getPhsC().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();

        /** Введение счётчика для выдержек времени */
        if (phsA) { CntA += 1; } else { CntA = 0; }
        if (phsB) { CntB += 1; } else { CntB = 0; }
        if (phsC) { CntC += 1; } else { CntC = 0; }

        Op.getGeneral().setValue(CntA > OpDlTmms.getSetVal() || CntB > OpDlTmms.getSetVal() || CntC > OpDlTmms.getSetVal());
        Op.getPhsA().setValue(CntA > OpDlTmms.getSetVal());
        Op.getPhsB().setValue(CntB > OpDlTmms.getSetVal());
        Op.getPhsC().setValue(CntC > OpDlTmms.getSetVal());
    }

    public ACT getOp() {
        return Op;
    }

    public ASG getStrVal() {
        return StrVal;
    }

    public ING getOpDITmms() {
        return OpDlTmms;
    }
}

