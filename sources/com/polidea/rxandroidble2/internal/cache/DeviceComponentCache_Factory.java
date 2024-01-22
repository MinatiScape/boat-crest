package com.polidea.rxandroidble2.internal.cache;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
@DaggerGenerated
@ScopeMetadata("com.polidea.rxandroidble2.ClientScope")
@QualifierMetadata
/* loaded from: classes9.dex */
public final class DeviceComponentCache_Factory implements Factory<DeviceComponentCache> {

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final DeviceComponentCache_Factory f13397a = new DeviceComponentCache_Factory();
    }

    public static DeviceComponentCache_Factory create() {
        return a.f13397a;
    }

    public static DeviceComponentCache newInstance() {
        return new DeviceComponentCache();
    }

    @Override // bleshadow.javax.inject.Provider
    public DeviceComponentCache get() {
        return newInstance();
    }
}
