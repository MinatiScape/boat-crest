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
public final class ClientComponent_ClientModule_ProvideEnableIndicationValueFactory implements Factory<byte[]> {

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ClientComponent_ClientModule_ProvideEnableIndicationValueFactory f13346a = new ClientComponent_ClientModule_ProvideEnableIndicationValueFactory();
    }

    public static ClientComponent_ClientModule_ProvideEnableIndicationValueFactory create() {
        return a.f13346a;
    }

    public static byte[] provideEnableIndicationValue() {
        return (byte[]) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.k());
    }

    @Override // bleshadow.javax.inject.Provider
    public byte[] get() {
        return provideEnableIndicationValue();
    }
}
