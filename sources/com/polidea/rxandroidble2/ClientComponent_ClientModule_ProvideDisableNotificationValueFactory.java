package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import com.polidea.rxandroidble2.ClientComponent;
@DaggerGenerated
@ScopeMetadata
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideDisableNotificationValueFactory implements Factory<byte[]> {

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ClientComponent_ClientModule_ProvideDisableNotificationValueFactory f13345a = new ClientComponent_ClientModule_ProvideDisableNotificationValueFactory();
    }

    public static ClientComponent_ClientModule_ProvideDisableNotificationValueFactory create() {
        return a.f13345a;
    }

    public static byte[] provideDisableNotificationValue() {
        return (byte[]) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.j());
    }

    @Override // bleshadow.javax.inject.Provider
    public byte[] get() {
        return provideDisableNotificationValue();
    }
}
