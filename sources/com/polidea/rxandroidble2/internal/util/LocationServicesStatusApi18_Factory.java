package com.polidea.rxandroidble2.internal.util;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
@DaggerGenerated
@ScopeMetadata
@QualifierMetadata
/* loaded from: classes12.dex */
public final class LocationServicesStatusApi18_Factory implements Factory<LocationServicesStatusApi18> {

    /* loaded from: classes12.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final LocationServicesStatusApi18_Factory f13516a = new LocationServicesStatusApi18_Factory();
    }

    public static LocationServicesStatusApi18_Factory create() {
        return a.f13516a;
    }

    public static LocationServicesStatusApi18 newInstance() {
        return new LocationServicesStatusApi18();
    }

    @Override // bleshadow.javax.inject.Provider
    public LocationServicesStatusApi18 get() {
        return newInstance();
    }
}
