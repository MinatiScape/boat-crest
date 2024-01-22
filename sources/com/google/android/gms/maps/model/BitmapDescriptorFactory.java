package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class BitmapDescriptorFactory {
    public static final float HUE_AZURE = 210.0f;
    public static final float HUE_BLUE = 240.0f;
    public static final float HUE_CYAN = 180.0f;
    public static final float HUE_GREEN = 120.0f;
    public static final float HUE_MAGENTA = 300.0f;
    public static final float HUE_ORANGE = 30.0f;
    public static final float HUE_RED = 0.0f;
    public static final float HUE_ROSE = 330.0f;
    public static final float HUE_VIOLET = 270.0f;
    public static final float HUE_YELLOW = 60.0f;

    /* renamed from: a  reason: collision with root package name */
    public static com.google.android.gms.internal.maps.zzi f10072a;

    public static com.google.android.gms.internal.maps.zzi a() {
        return (com.google.android.gms.internal.maps.zzi) Preconditions.checkNotNull(f10072a, "IBitmapDescriptorFactory is not initialized");
    }

    @NonNull
    public static BitmapDescriptor defaultMarker() {
        try {
            return new BitmapDescriptor(a().zzd());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @NonNull
    public static BitmapDescriptor fromAsset(@NonNull String str) {
        Preconditions.checkNotNull(str, "assetName must not be null");
        try {
            return new BitmapDescriptor(a().zzf(str));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @NonNull
    public static BitmapDescriptor fromBitmap(@NonNull Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap, "image must not be null");
        try {
            return new BitmapDescriptor(a().zzg(bitmap));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @NonNull
    public static BitmapDescriptor fromFile(@NonNull String str) {
        Preconditions.checkNotNull(str, "fileName must not be null");
        try {
            return new BitmapDescriptor(a().zzh(str));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @NonNull
    public static BitmapDescriptor fromPath(@NonNull String str) {
        Preconditions.checkNotNull(str, "absolutePath must not be null");
        try {
            return new BitmapDescriptor(a().zzi(str));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @NonNull
    public static BitmapDescriptor fromResource(int i) {
        try {
            return new BitmapDescriptor(a().zzj(i));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public static void zza(com.google.android.gms.internal.maps.zzi zziVar) {
        if (f10072a != null) {
            return;
        }
        f10072a = (com.google.android.gms.internal.maps.zzi) Preconditions.checkNotNull(zziVar, "delegate must not be null");
    }

    @NonNull
    public static BitmapDescriptor defaultMarker(float f) {
        try {
            return new BitmapDescriptor(a().zze(f));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
