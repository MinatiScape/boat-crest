package com.google.android.gms.vision.barcode;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
/* loaded from: classes10.dex */
public final class BarcodeDetector extends Detector<Barcode> {
    public final com.google.android.gms.internal.vision.zzh c;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Context f10189a;
        public com.google.android.gms.internal.vision.zzf b = new com.google.android.gms.internal.vision.zzf();

        public Builder(Context context) {
            this.f10189a = context;
        }

        public BarcodeDetector build() {
            return new BarcodeDetector(new com.google.android.gms.internal.vision.zzh(this.f10189a, this.b));
        }

        public Builder setBarcodeFormats(int i) {
            this.b.zzbm = i;
            return this;
        }
    }

    public BarcodeDetector() {
        throw new IllegalStateException("Default constructor called");
    }

    @Override // com.google.android.gms.vision.Detector
    public final SparseArray<Barcode> detect(Frame frame) {
        Barcode[] zza;
        if (frame != null) {
            zzp zzc = zzp.zzc(frame);
            if (frame.getBitmap() != null) {
                zza = this.c.zza(frame.getBitmap(), zzc);
                if (zza == null) {
                    throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
                }
            } else {
                zza = this.c.zza(frame.getGrayscaleImageData(), zzc);
            }
            SparseArray<Barcode> sparseArray = new SparseArray<>(zza.length);
            for (Barcode barcode : zza) {
                sparseArray.append(barcode.rawValue.hashCode(), barcode);
            }
            return sparseArray;
        }
        throw new IllegalArgumentException("No frame supplied.");
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean isOperational() {
        return this.c.isOperational();
    }

    @Override // com.google.android.gms.vision.Detector
    public final void release() {
        super.release();
        this.c.zzo();
    }

    public BarcodeDetector(com.google.android.gms.internal.vision.zzh zzhVar) {
        this.c = zzhVar;
    }
}
