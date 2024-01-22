package com.google.mlkit.vision.common;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_common.zzp;
import java.util.List;
/* loaded from: classes10.dex */
public class Triangle<T> {

    /* renamed from: a  reason: collision with root package name */
    public final zzp f11634a;

    @KeepForSdk
    public Triangle(@NonNull T t, @NonNull T t2, @NonNull T t3) {
        this.f11634a = zzp.zzj(t, t2, t3);
    }

    @NonNull
    public List<T> getAllPoints() {
        return this.f11634a;
    }
}
