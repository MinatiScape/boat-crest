package com.polidea.rxandroidble2.internal.scan;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class AndroidScanObjectsConverter_Factory implements Factory<AndroidScanObjectsConverter> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Integer> f13461a;

    public AndroidScanObjectsConverter_Factory(Provider<Integer> provider) {
        this.f13461a = provider;
    }

    public static AndroidScanObjectsConverter_Factory create(Provider<Integer> provider) {
        return new AndroidScanObjectsConverter_Factory(provider);
    }

    public static AndroidScanObjectsConverter newInstance(int i) {
        return new AndroidScanObjectsConverter(i);
    }

    @Override // bleshadow.javax.inject.Provider
    public AndroidScanObjectsConverter get() {
        return newInstance(this.f13461a.get().intValue());
    }
}
