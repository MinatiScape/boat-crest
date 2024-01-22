package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideBluetoothManagerFactory implements Factory<BluetoothManager> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f13340a;

    public ClientComponent_ClientModule_ProvideBluetoothManagerFactory(Provider<Context> provider) {
        this.f13340a = provider;
    }

    public static ClientComponent_ClientModule_ProvideBluetoothManagerFactory create(Provider<Context> provider) {
        return new ClientComponent_ClientModule_ProvideBluetoothManagerFactory(provider);
    }

    public static BluetoothManager provideBluetoothManager(Context context) {
        return (BluetoothManager) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.e(context));
    }

    @Override // bleshadow.javax.inject.Provider
    public BluetoothManager get() {
        return provideBluetoothManager(this.f13340a.get());
    }
}
