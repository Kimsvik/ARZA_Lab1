import breakerControl.XCBR;
import protection.model.logicalnodes.common.LN;
import protection.model.logicalnodes.gui.NHMI;
import protection.model.logicalnodes.gui.other.NHMISignal;
import protection.model.logicalnodes.input.LCOM;
import breakerControl.CSWI;
import protection.model.logicalnodes.measurements.MMXU;
import protection.model.logicalnodes.protections.PTOC;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static List<LN> lnList = new ArrayList<>();

    public static void main(String[] args) {

        /** LCOM - считывает comtrade-файл */
        LCOM lcom = new LCOM();
        lnList.add(lcom);
        lcom.setFilePath(
                "C:\\Users\\Сергей\\Desktop\\МЭИ\\М2 семестр\\Алгоритмы РЗА\\ЛР1\\Опыты\\Опыты\\Конец линии\\",
                "PhB80"
        );

        /** MMXU - используется для расчета тока, напряжения, мощности в трехфазной системе
         * (МЭК 61850-7-4) */
        MMXU mmxu = new MMXU();
        lnList.add(mmxu);
        mmxu.phsAInst = lcom.OUT.get(0);
        mmxu.phsBInst = lcom.OUT.get(1);
        mmxu.phsCInst = lcom.OUT.get(2);

        /** PTOC - используется для моделирования направленной максимальной токовой защиты с выдержкой времени
         * (МЭК 61850-7-4) */
        PTOC ptoc1 = new PTOC();
        PTOC ptoc2 = new PTOC();
        PTOC ptoc3 = new PTOC();
        lnList.add(ptoc1);
        lnList.add(ptoc2);
        lnList.add(ptoc3);
        ptoc1.StrVal.getSetMag().getF().setValue(2000d); // Задание уставки
        ptoc1.getOpDITmms().setSetVal(25);
        ptoc1.A = mmxu.A; // Сравнение значения тока с уставками
        ptoc2.StrVal.getSetMag().getF().setValue(500d);
        ptoc2.getOpDITmms().setSetVal(50);
        ptoc2.A = mmxu.A;
        ptoc3.StrVal.getSetMag().getF().setValue(293d);
        ptoc3.getOpDITmms().setSetVal(100);
        ptoc3.A = mmxu.A;

        /** CSWI - выдаёт команды на управление выключателем */
        CSWI cswi = new CSWI();
        lnList.add(cswi);
        cswi.setOpOpn1(ptoc1.getOp());
        cswi.setOpOpn2(ptoc2.getOp());
        cswi.setOpOpn3(ptoc3.getOp());
        cswi.getPos().getCtVal().setValue(true);
        cswi.getPos().getStVal().setValue((byte) 2);

        /** XCBR - положение выключателя */
        XCBR xcbr = new XCBR();
        lnList.add(xcbr);
        xcbr.setPos(cswi.getPos());

        /** NHMI - используется для вывода графиков */
        NHMI nhmi = new NHMI();
        lnList.add(nhmi);
        nhmi.addSignals(
                new NHMISignal("InstValuePhsA", lcom.OUT.get(0).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("InstValuePhsB", lcom.OUT.get(1).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("InstValuePhsC", lcom.OUT.get(2).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("FurierValuePhsA", mmxu.A.getPhsA().getCVal().getMag().getF()),
                new NHMISignal("Set1", ptoc1.getStrVal().getSetMag().getF()),
                new NHMISignal("Set2", ptoc2.getStrVal().getSetMag().getF()),
                new NHMISignal("Set3", ptoc3.getStrVal().getSetMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("FurierValuePhsB", mmxu.A.getPhsB().getCVal().getMag().getF()),
                new NHMISignal("Set1", ptoc1.getStrVal().getSetMag().getF()),
                new NHMISignal("Set2", ptoc2.getStrVal().getSetMag().getF()),
                new NHMISignal("Set3", ptoc3.getStrVal().getSetMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("FurierValuePhsC", mmxu.A.getPhsC().getCVal().getMag().getF()),
                new NHMISignal("Set1", ptoc1.getStrVal().getSetMag().getF()),
                new NHMISignal("Set2", ptoc2.getStrVal().getSetMag().getF()),
                new NHMISignal("Set3", ptoc3.getStrVal().getSetMag().getF())
        );

        nhmi.addSignals(
                new NHMISignal("Set1", ptoc1.getOp().getGeneral())
        );
        nhmi.addSignals(
                new NHMISignal("Set2", ptoc2.getOp().getGeneral())
        );
        nhmi.addSignals(
                new NHMISignal("Set3", ptoc3.getOp().getGeneral())
        );
        nhmi.addSignals(
                new NHMISignal("CSWI", cswi.getPos().getCtVal())
        );
        nhmi.addSignals(
                new NHMISignal("Breaker", xcbr.getPos().getStVal())
        );

        while (lcom.hasNextData()) {
            lnList.forEach(LN::process);
        }
    }
}
