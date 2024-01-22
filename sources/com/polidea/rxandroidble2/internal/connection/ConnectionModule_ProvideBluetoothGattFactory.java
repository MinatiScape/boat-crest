package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class ConnectionModule_ProvideBluetoothGattFactory implements Factory<BluetoothGatt> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<BluetoothGattProvider> f13402a;

    public ConnectionModule_ProvideBluetoothGattFactory(Provider<BluetoothGattProvider> provider) {
        this.f13402a = provider;
    }

    public static ConnectionModule_ProvideBluetoothGattFactory create(Provider<BluetoothGattProvider> provider) {
        return new ConnectionModule_ProvideBluetoothGattFactory(provider);
    }

    public static BluetoothGatt provideBluetoothGatt(BluetoothGattProvider bluetoothGattProvider) {
        return (BluetoothGatt) Preconditions.checkNotNullFromProvides(ConnectionModule.c(bluetoothGattProvider));
    }

    @Override // bleshadow.javax.inject.Provider
    public BluetoothGatt get() {
        return provideBluetoothGatt(this.f13402a.get());
    }
}
