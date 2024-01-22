package com.mappls.sdk.maps.snapshotter;

import android.graphics.Bitmap;
import android.graphics.PointF;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.geometry.LatLng;
/* loaded from: classes11.dex */
public class MapSnapshot {

    /* renamed from: a  reason: collision with root package name */
    public Bitmap f12836a;
    public String[] b;
    public boolean c;
    @Keep
    private long nativePtr;

    @Keep
    private MapSnapshot(long j, Bitmap bitmap, String[] strArr, boolean z) {
        this.nativePtr = 0L;
        this.nativePtr = j;
        this.f12836a = bitmap;
        this.b = strArr;
        this.c = z;
    }

    @Keep
    private native void initialize();

    public boolean a() {
        return this.c;
    }

    @Keep
    public native void finalize();

    public String[] getAttributions() {
        return this.b;
    }

    public Bitmap getBitmap() {
        return this.f12836a;
    }

    @NonNull
    @Keep
    public native LatLng latLngForPixel(PointF pointF);

    @NonNull
    @Keep
    public native PointF pixelForLatLng(LatLng latLng);
}
