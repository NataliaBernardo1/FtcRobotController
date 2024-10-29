package UtilityClasses;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

public class opencvPipeline extends OpenCvPipeline {
    public Mat wholeScreen;
    public Mat subWholeScreenMat;

    @Override
    public Mat processFrame(Mat input) {
        return input;
    }
}