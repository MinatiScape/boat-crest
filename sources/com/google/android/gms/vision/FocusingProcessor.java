package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
/* loaded from: classes10.dex */
public abstract class FocusingProcessor<T> implements Detector.Processor<T> {

    /* renamed from: a  reason: collision with root package name */
    public Detector<T> f10181a;
    public Tracker<T> b;
    public int e;
    public int c = 3;
    public boolean d = false;
    public int f = 0;

    public FocusingProcessor(Detector<T> detector, Tracker<T> tracker) {
        this.f10181a = detector;
        this.b = tracker;
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void receiveDetections(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        if (detectedItems.size() == 0) {
            if (this.f == this.c) {
                this.b.onDone();
                this.d = false;
            } else {
                this.b.onMissing(detections);
            }
            this.f++;
            return;
        }
        this.f = 0;
        if (this.d) {
            T t = detectedItems.get(this.e);
            if (t != null) {
                this.b.onUpdate(detections, t);
                return;
            } else {
                this.b.onDone();
                this.d = false;
            }
        }
        int selectFocus = selectFocus(detections);
        T t2 = detectedItems.get(selectFocus);
        if (t2 == null) {
            StringBuilder sb = new StringBuilder(35);
            sb.append("Invalid focus selected: ");
            sb.append(selectFocus);
            Log.w("FocusingProcessor", sb.toString());
            return;
        }
        this.d = true;
        this.e = selectFocus;
        this.f10181a.setFocus(selectFocus);
        this.b.onNewItem(this.e, t2);
        this.b.onUpdate(detections, t2);
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void release() {
        this.b.onDone();
    }

    public abstract int selectFocus(Detector.Detections<T> detections);

    public final void zza(int i) {
        if (i >= 0) {
            this.c = i;
            return;
        }
        StringBuilder sb = new StringBuilder(28);
        sb.append("Invalid max gap: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }
}
