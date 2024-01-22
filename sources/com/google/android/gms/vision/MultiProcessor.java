package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import java.util.HashSet;
/* loaded from: classes10.dex */
public class MultiProcessor<T> implements Detector.Processor<T> {

    /* renamed from: a  reason: collision with root package name */
    public Factory<T> f10186a;
    public SparseArray<a> b;
    public int c;

    /* loaded from: classes10.dex */
    public static class Builder<T> {

        /* renamed from: a  reason: collision with root package name */
        public MultiProcessor<T> f10187a;

        public Builder(Factory<T> factory) {
            MultiProcessor<T> multiProcessor = new MultiProcessor<>();
            this.f10187a = multiProcessor;
            if (factory != null) {
                multiProcessor.f10186a = factory;
                return;
            }
            throw new IllegalArgumentException("No factory supplied.");
        }

        public MultiProcessor<T> build() {
            return this.f10187a;
        }

        public Builder<T> setMaxGapFrames(int i) {
            if (i >= 0) {
                this.f10187a.c = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(28);
            sb.append("Invalid max gap: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* loaded from: classes10.dex */
    public interface Factory<T> {
        Tracker<T> create(T t);
    }

    /* loaded from: classes10.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        public Tracker<T> f10188a;
        public int b;

        public a(MultiProcessor multiProcessor) {
            this.b = 0;
        }

        public static /* synthetic */ int a(a aVar, int i) {
            aVar.b = 0;
            return 0;
        }

        public static /* synthetic */ int d(a aVar) {
            int i = aVar.b;
            aVar.b = i + 1;
            return i;
        }
    }

    public MultiProcessor() {
        this.b = new SparseArray<>();
        this.c = 3;
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void receiveDetections(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            T valueAt = detectedItems.valueAt(i);
            if (this.b.get(keyAt) == null) {
                a aVar = new a();
                aVar.f10188a = this.f10186a.create(valueAt);
                aVar.f10188a.onNewItem(keyAt, valueAt);
                this.b.append(keyAt, aVar);
            }
        }
        SparseArray<T> detectedItems2 = detections.getDetectedItems();
        HashSet<Integer> hashSet = new HashSet();
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            int keyAt2 = this.b.keyAt(i2);
            if (detectedItems2.get(keyAt2) == null) {
                a valueAt2 = this.b.valueAt(i2);
                a.d(valueAt2);
                if (valueAt2.b >= this.c) {
                    valueAt2.f10188a.onDone();
                    hashSet.add(Integer.valueOf(keyAt2));
                } else {
                    valueAt2.f10188a.onMissing(detections);
                }
            }
        }
        for (Integer num : hashSet) {
            this.b.delete(num.intValue());
        }
        SparseArray<T> detectedItems3 = detections.getDetectedItems();
        for (int i3 = 0; i3 < detectedItems3.size(); i3++) {
            int keyAt3 = detectedItems3.keyAt(i3);
            T valueAt3 = detectedItems3.valueAt(i3);
            a aVar2 = this.b.get(keyAt3);
            a.a(aVar2, 0);
            aVar2.f10188a.onUpdate(detections, valueAt3);
        }
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void release() {
        for (int i = 0; i < this.b.size(); i++) {
            this.b.valueAt(i).f10188a.onDone();
        }
        this.b.clear();
    }
}
