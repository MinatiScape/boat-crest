package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes10.dex */
public final class BitmapDescriptor {

    /* renamed from: a  reason: collision with root package name */
    public final IObjectWrapper f10071a;

    public BitmapDescriptor(@NonNull IObjectWrapper iObjectWrapper) {
        this.f10071a = (IObjectWrapper) Preconditions.checkNotNull(iObjectWrapper);
    }

    @NonNull
    public final IObjectWrapper zza() {
        return this.f10071a;
    }
}
