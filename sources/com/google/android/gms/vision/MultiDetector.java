package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class MultiDetector extends Detector<Object> {
    public List<Detector<? extends Object>> c;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public MultiDetector f10185a = new MultiDetector();

        public Builder add(Detector<? extends Object> detector) {
            this.f10185a.c.add(detector);
            return this;
        }

        public MultiDetector build() {
            if (this.f10185a.c.size() != 0) {
                return this.f10185a;
            }
            throw new RuntimeException("No underlying detectors added to MultiDetector.");
        }
    }

    public MultiDetector() {
        this.c = new ArrayList();
    }

    @Override // com.google.android.gms.vision.Detector
    public SparseArray<Object> detect(Frame frame) {
        SparseArray<Object> sparseArray = new SparseArray<>();
        for (Detector<? extends Object> detector : this.c) {
            SparseArray<? extends Object> detect = detector.detect(frame);
            for (int i = 0; i < detect.size(); i++) {
                int keyAt = detect.keyAt(i);
                if (sparseArray.get(keyAt) == null) {
                    sparseArray.append(keyAt, detect.valueAt(i));
                } else {
                    StringBuilder sb = new StringBuilder(104);
                    sb.append("Detection ID overlap for id = ");
                    sb.append(keyAt);
                    sb.append("  This means that one of the detectors is not using global IDs.");
                    throw new IllegalStateException(sb.toString());
                }
            }
        }
        return sparseArray;
    }

    @Override // com.google.android.gms.vision.Detector
    public boolean isOperational() {
        for (Detector<? extends Object> detector : this.c) {
            if (!detector.isOperational()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.vision.Detector
    public void receiveFrame(Frame frame) {
        for (Detector<? extends Object> detector : this.c) {
            detector.receiveFrame(frame);
        }
    }

    @Override // com.google.android.gms.vision.Detector
    public void release() {
        for (Detector<? extends Object> detector : this.c) {
            detector.release();
        }
        this.c.clear();
    }

    @Override // com.google.android.gms.vision.Detector
    public void setProcessor(Detector.Processor<Object> processor) {
        throw new UnsupportedOperationException("MultiDetector.setProcessor is not supported.  You should set a processor instance on each underlying detector instead.");
    }
}
