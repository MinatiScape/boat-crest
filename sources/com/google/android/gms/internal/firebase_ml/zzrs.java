package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
/* loaded from: classes7.dex */
public final class zzrs implements zzpy {
    public final List<zzku> features;
    public final zzkz imageContext;
    public final byte[] zzbrd;
    public final float zzbre;

    public zzrs(@NonNull byte[] bArr, float f, @NonNull List<zzku> list, @Nullable zzkz zzkzVar) {
        this.zzbrd = bArr;
        this.zzbre = f;
        this.features = list;
        this.imageContext = zzkzVar;
    }
}
