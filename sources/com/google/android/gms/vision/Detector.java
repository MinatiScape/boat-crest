package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Frame;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public abstract class Detector<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Object f10179a = new Object();
    @GuardedBy("processorLock")
    public Processor<T> b;

    /* loaded from: classes10.dex */
    public static class Detections<T> {

        /* renamed from: a  reason: collision with root package name */
        public final SparseArray<T> f10180a;
        public final Frame.Metadata b;
        public final boolean c;

        public Detections(SparseArray<T> sparseArray, Frame.Metadata metadata, boolean z) {
            this.f10180a = sparseArray;
            this.b = metadata;
            this.c = z;
        }

        public boolean detectorIsOperational() {
            return this.c;
        }

        public SparseArray<T> getDetectedItems() {
            return this.f10180a;
        }

        public Frame.Metadata getFrameMetadata() {
            return this.b;
        }
    }

    /* loaded from: classes10.dex */
    public interface Processor<T> {
        void receiveDetections(Detections<T> detections);

        void release();
    }

    public abstract SparseArray<T> detect(Frame frame);

    public boolean isOperational() {
        return true;
    }

    public void receiveFrame(Frame frame) {
        Frame.Metadata metadata = new Frame.Metadata(frame.getMetadata());
        metadata.zzd();
        Detections<T> detections = new Detections<>(detect(frame), metadata, isOperational());
        synchronized (this.f10179a) {
            Processor<T> processor = this.b;
            if (processor != null) {
                processor.receiveDetections(detections);
            } else {
                throw new IllegalStateException("Detector processor must first be set with setProcessor in order to receive detection results.");
            }
        }
    }

    public void release() {
        synchronized (this.f10179a) {
            Processor<T> processor = this.b;
            if (processor != null) {
                processor.release();
                this.b = null;
            }
        }
    }

    public boolean setFocus(int i) {
        return true;
    }

    public void setProcessor(Processor<T> processor) {
        synchronized (this.f10179a) {
            Processor<T> processor2 = this.b;
            if (processor2 != null) {
                processor2.release();
            }
            this.b = processor;
        }
    }
}
