package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import com.polidea.rxandroidble2.ClientComponent;
@DaggerGenerated
@ScopeMetadata
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideDeviceSdkFactory implements Factory<Integer> {

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ClientComponent_ClientModule_ProvideDeviceSdkFactory f13344a = new ClientComponent_ClientModule_ProvideDeviceSdkFactory();
    }

    public static ClientComponent_ClientModule_ProvideDeviceSdkFactory create() {
        return a.f13344a;
    }

    public static int provideDeviceSdk() {
        return ClientComponent.ClientModule.i();
    }

    @Override // bleshadow.javax.inject.Provider
    public Integer get() {
        return Integer.valueOf(provideDeviceSdk());
    }
}
