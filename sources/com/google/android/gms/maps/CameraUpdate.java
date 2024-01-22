package com.google.android.gms.maps;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes10.dex */
public final class CameraUpdate {

    /* renamed from: a  reason: collision with root package name */
    public final IObjectWrapper f10039a;

    public CameraUpdate(IObjectWrapper iObjectWrapper) {
        this.f10039a = (IObjectWrapper) Preconditions.checkNotNull(iObjectWrapper);
    }

    @NonNull
    public final IObjectWrapper zza() {
        return this.f10039a;
    }
}
