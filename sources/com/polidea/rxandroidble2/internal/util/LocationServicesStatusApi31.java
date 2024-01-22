package com.polidea.rxandroidble2.internal.util;

import android.annotation.TargetApi;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
@TargetApi(31)
/* loaded from: classes12.dex */
public class LocationServicesStatusApi31 implements LocationServicesStatus {

    /* renamed from: a  reason: collision with root package name */
    public final CheckerLocationProvider f13519a;
    public final CheckerScanPermission b;
    public final boolean c;
    public final boolean d;

    @Inject
    public LocationServicesStatusApi31(CheckerLocationProvider checkerLocationProvider, CheckerScanPermission checkerScanPermission, @Named("android-wear") boolean z, @Named("nearby-permission-never-for-location") boolean z2) {
        this.f13519a = checkerLocationProvider;
        this.b = checkerScanPermission;
        this.c = z;
        this.d = z2;
    }

    public final boolean a() {
        if (this.c) {
            return false;
        }
        return !this.d;
    }

    @Override // com.polidea.rxandroidble2.internal.util.LocationServicesStatus
    public boolean isLocationPermissionOk() {
        return this.b.isScanRuntimePermissionGranted();
    }

    @Override // com.polidea.rxandroidble2.internal.util.LocationServicesStatus
    public boolean isLocationProviderOk() {
        return !a() || this.f13519a.isLocationProviderEnabled();
    }
}
