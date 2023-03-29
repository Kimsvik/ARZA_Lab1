package protection.model.logicalnodes.measurements;

import lombok.Getter;
import lombok.Setter;
import protection.model.dataobjects.measurements.MV;
import protection.model.dataobjects.measurements.WYE;
import protection.model.logicalnodes.common.LN;
import utils.filters.Filter;
import utils.filters.Furier;
import utils.filters.MeanFilter;

public class MMXU extends LN {

    /** Входы */
    public MV phsAInst = new MV();
    public MV phsBInst = new MV();
    public MV phsCInst = new MV();

    /** Выходы */
    @Getter @Setter
    public final WYE A = new WYE();


    /** Переменные */
    private final Filter phsACurrent = new Furier();
    private final Filter phsBCurrent = new Furier();
    private final Filter phsCCurrent = new Furier();


    @Override
    public void process() {
        phsACurrent.process(phsAInst, A.getPhsA());
        phsBCurrent.process(phsBInst, A.getPhsB());
        phsCCurrent.process(phsCInst, A.getPhsC());
    }
}
