package com.google.mlkit.vision.barcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.mlkit.vision.barcode.common.Barcode;
import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public class BarcodeScannerOptions {

    /* renamed from: a  reason: collision with root package name */
    public final int f11606a;
    public final boolean b;
    @Nullable
    public final Executor c;
    @Nullable
    public final ZoomSuggestionOptions d;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f11607a = 0;
        public boolean b;
        @Nullable
        public Executor c;
        @Nullable
        public ZoomSuggestionOptions d;

        @NonNull
        public BarcodeScannerOptions build() {
            return new BarcodeScannerOptions(this.f11607a, this.b, this.c, this.d, null);
        }

        @NonNull
        public Builder enableAllPotentialBarcodes() {
            this.b = true;
            return this;
        }

        @NonNull
        public Builder setBarcodeFormats(@Barcode.BarcodeFormat int i, @NonNull @Barcode.BarcodeFormat int... iArr) {
            this.f11607a = i;
            if (iArr != null) {
                for (int i2 : iArr) {
                    this.f11607a = i2 | this.f11607a;
                }
            }
            return this;
        }

        @NonNull
        public Builder setExecutor(@NonNull Executor executor) {
            this.c = executor;
            return this;
        }

        @NonNull
        public Builder setZoomSuggestionOptions(@NonNull ZoomSuggestionOptions zoomSuggestionOptions) {
            this.d = zoomSuggestionOptions;
            return this;
        }
    }

    public /* synthetic */ BarcodeScannerOptions(int i, boolean z, Executor executor, ZoomSuggestionOptions zoomSuggestionOptions, zza zzaVar) {
        this.f11606a = i;
        this.b = z;
        this.c = executor;
        this.d = zoomSuggestionOptions;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BarcodeScannerOptions) {
            BarcodeScannerOptions barcodeScannerOptions = (BarcodeScannerOptions) obj;
            return this.f11606a == barcodeScannerOptions.f11606a && this.b == barcodeScannerOptions.b && Objects.equal(this.c, barcodeScannerOptions.c) && Objects.equal(this.d, barcodeScannerOptions.d);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.f11606a), Boolean.valueOf(this.b), this.c, this.d);
    }

    public final int zza() {
        return this.f11606a;
    }

    @Nullable
    public final ZoomSuggestionOptions zzb() {
        return this.d;
    }

    @Nullable
    public final Executor zzc() {
        return this.c;
    }

    public final boolean zzd() {
        return this.b;
    }
}
