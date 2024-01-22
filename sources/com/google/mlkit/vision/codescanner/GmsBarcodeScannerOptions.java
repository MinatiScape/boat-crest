package com.google.mlkit.vision.codescanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.mlkit.vision.barcode.common.Barcode;
/* loaded from: classes10.dex */
public class GmsBarcodeScannerOptions {

    /* renamed from: a  reason: collision with root package name */
    public final int f11630a;
    public final boolean b;
    public final boolean c;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f11631a = 0;
        public boolean b;
        public boolean c;

        @NonNull
        public Builder allowManualInput() {
            this.b = true;
            return this;
        }

        @NonNull
        public GmsBarcodeScannerOptions build() {
            return new GmsBarcodeScannerOptions(this.f11631a, this.b, this.c, null);
        }

        @NonNull
        public Builder enableAutoZoom() {
            this.c = true;
            return this;
        }

        @NonNull
        public Builder setBarcodeFormats(@Barcode.BarcodeFormat int i, @NonNull @Barcode.BarcodeFormat int... iArr) {
            this.f11631a = i;
            for (int i2 : iArr) {
                this.f11631a = i2 | this.f11631a;
            }
            return this;
        }
    }

    public /* synthetic */ GmsBarcodeScannerOptions(int i, boolean z, boolean z2, zza zzaVar) {
        this.f11630a = i;
        this.b = z;
        this.c = z2;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GmsBarcodeScannerOptions) {
            GmsBarcodeScannerOptions gmsBarcodeScannerOptions = (GmsBarcodeScannerOptions) obj;
            return this.f11630a == gmsBarcodeScannerOptions.f11630a && this.b == gmsBarcodeScannerOptions.b && this.c == gmsBarcodeScannerOptions.c;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.f11630a), Boolean.valueOf(this.b), Boolean.valueOf(this.c));
    }

    public final int zza() {
        return this.f11630a;
    }

    public final boolean zzb() {
        return this.c;
    }

    public final boolean zzc() {
        return this.b;
    }
}
