package com.google.firebase.ml.vision.barcode;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.firebase_ml.zztg;
import com.google.android.gms.internal.firebase_ml.zzvh;
import com.google.android.gms.internal.firebase_ml.zzwz;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class FirebaseVisionBarcodeDetectorOptions {
    @SuppressLint({"UseSparseArrays"})
    public static final Map<Integer, zzvh> b;

    /* renamed from: a  reason: collision with root package name */
    public final int f11430a;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f11431a = 0;

        @NonNull
        public FirebaseVisionBarcodeDetectorOptions build() {
            return new FirebaseVisionBarcodeDetectorOptions(this.f11431a);
        }

        @NonNull
        public Builder setBarcodeFormats(@FirebaseVisionBarcode.BarcodeFormat int i, @NonNull @FirebaseVisionBarcode.BarcodeFormat int... iArr) {
            this.f11431a = i;
            if (iArr != null) {
                for (int i2 : iArr) {
                    this.f11431a = i2 | this.f11431a;
                }
            }
            return this;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put(1, zzvh.CODE_128);
        hashMap.put(2, zzvh.CODE_39);
        hashMap.put(4, zzvh.CODE_93);
        hashMap.put(8, zzvh.CODABAR);
        hashMap.put(16, zzvh.DATA_MATRIX);
        hashMap.put(32, zzvh.EAN_13);
        hashMap.put(64, zzvh.EAN_8);
        hashMap.put(128, zzvh.ITF);
        hashMap.put(256, zzvh.QR_CODE);
        hashMap.put(512, zzvh.UPC_A);
        hashMap.put(1024, zzvh.UPC_E);
        hashMap.put(2048, zzvh.PDF417);
        hashMap.put(4096, zzvh.AZTEC);
    }

    public FirebaseVisionBarcodeDetectorOptions(int i) {
        this.f11430a = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof FirebaseVisionBarcodeDetectorOptions) && this.f11430a == ((FirebaseVisionBarcodeDetectorOptions) obj).f11430a;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.f11430a));
    }

    public final int zzqh() {
        return this.f11430a;
    }

    public final zztg.zza zzqi() {
        ArrayList arrayList = new ArrayList();
        if (this.f11430a == 0) {
            arrayList.addAll(b.values());
        } else {
            for (Map.Entry<Integer, zzvh> entry : b.entrySet()) {
                if ((this.f11430a & entry.getKey().intValue()) != 0) {
                    arrayList.add(entry.getValue());
                }
            }
        }
        return (zztg.zza) ((zzwz) zztg.zza.zzrq().zzz(arrayList).zzvb());
    }
}
