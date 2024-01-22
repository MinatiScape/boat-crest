package com.polidea.rxandroidble2.internal.util;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
/* loaded from: classes12.dex */
public class LocationServicesStatusApi23 implements LocationServicesStatus {

    /* renamed from: a  reason: collision with root package name */
    public final CheckerLocationProvider f13517a;
    public final CheckerScanPermission b;
    public final boolean c;
    public final int d;
    public final int e;

    @Inject
    public LocationServicesStatusApi23(CheckerLocationProvider checkerLocationProvider, CheckerScanPermission checkerScanPermission, @Named("target-sdk") int i, @Named("device-sdk") int i2, @Named("android-wear") boolean z) {
        this.f13517a = checkerLocationProvider;
        this.b = checkerScanPermission;
        this.d = i;
        this.e = i2;
        this.c = z;
    }

    public final boolean a() {
        return !this.c && (this.e >= 29 || this.d >= 23);
    }

    @Override // com.polidea.rxandroidble2.internal.util.LocationServicesStatus
    public boolean isLocationPermissionOk() {
        return this.b.isScanRuntimePermissionGranted();
    }

    @Override // com.polidea.rxandroidble2.internal.util.LocationServicesStatus
    public boolean isLocationProviderOk() {
        return !a() || this.f13517a.isLocationProviderEnabled();
    }
}
