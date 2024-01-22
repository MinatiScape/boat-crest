package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.VisibleRegion;
/* loaded from: classes10.dex */
public final class Projection {

    /* renamed from: a  reason: collision with root package name */
    public final IProjectionDelegate f10043a;

    public Projection(IProjectionDelegate iProjectionDelegate) {
        this.f10043a = iProjectionDelegate;
    }

    @NonNull
    public LatLng fromScreenLocation(@NonNull Point point) {
        Preconditions.checkNotNull(point);
        try {
            return this.f10043a.fromScreenLocation(ObjectWrapper.wrap(point));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @NonNull
    public VisibleRegion getVisibleRegion() {
        try {
            return this.f10043a.getVisibleRegion();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @NonNull
    public Point toScreenLocation(@NonNull LatLng latLng) {
        Preconditions.checkNotNull(latLng);
        try {
            return (Point) ObjectWrapper.unwrap(this.f10043a.toScreenLocation(latLng));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
