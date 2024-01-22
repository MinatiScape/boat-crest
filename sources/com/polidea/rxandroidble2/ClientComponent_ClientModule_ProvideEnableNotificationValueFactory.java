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
public final class ClientComponent_ClientModule_ProvideEnableNotificationValueFactory implements Factory<byte[]> {

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ClientComponent_ClientModule_ProvideEnableNotificationValueFactory f13347a = new ClientComponent_ClientModule_ProvideEnableNotificationValueFactory();
    }

    public static ClientComponent_ClientModule_ProvideEnableNotificationValueFactory create() {
        return a.f13347a;
    }

    public static byte[] provideEnableNotificationValue() {
        return (byte[]) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.l());
    }

    @Override // bleshadow.javax.inject.Provider
    public byte[] get() {
        return provideEnableNotificationValue();
    }
}
