package utils.filters;

import protection.model.dataobjects.measurements.CMV;
import protection.model.dataobjects.measurements.MV;
import protection.model.dataobjects.measurements.Vector;

public class Furier extends Filter {

private int size = 80;

private float[] bufferX = new float[size];
private float[] bufferY = new float[size];

private float sumX = 0;
private float sumY = 0;
private int bCount = 0;
private final float k = (float) 0.025;

private float fx = 0;
private float fy = 0;

private int count = 0;

@Override
public void process(MV sav, CMV result) {

    fx = (float) (sav.getInstMag().getF().getValue() * Math.sin(2*Math.PI * 50 * bCount * 1/50 / size));
    fy = (float) (sav.getInstMag().getF().getValue() * Math.sin(2*Math.PI * 50 * bCount * 1/50 / size));
    sumX += fx - bufferX[bCount];
    sumY += fy - bufferY[bCount];

    bufferX[bCount] = fx;
    bufferY[bCount] = fy;

    bCount++;
    if(bCount >= size) bCount = 0;

    result.getCVal().getMag().getF().setValue(
            Math.sqrt(Math.pow(sumX * k, 2) + Math.pow(sumY * k, 2))
    );

}
}
