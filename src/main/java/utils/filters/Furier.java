package utils.filters;

import protection.model.dataobjects.measurements.CMV;
import protection.model.dataobjects.measurements.MV;
import protection.model.dataobjects.measurements.Vector;

public class Furier extends Filter {

private int size = 80;
private float[] buffer = new float[size];
private float sum = 0;
private int count = 0;

@Override
public void process(MV sav, CMV result) {

    sum += Math.abs(sav.getInstMag().getF().getValue()) - buffer[count];
    buffer[count] = (float) Math.abs(sav.getInstMag().getF().getValue());
    result.getCVal().getMag().getF().setValue((double) (sum/size));
    if(++count >= size) count = 0;
}
}
