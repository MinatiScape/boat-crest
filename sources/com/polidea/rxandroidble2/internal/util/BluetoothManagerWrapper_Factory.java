package com.polidea.rxandroidble2.internal.util;

import android.bluetooth.BluetoothManager;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class BluetoothManagerWrapper_Factory implements Factory<BluetoothManagerWrapper> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<BluetoothManager> f13501a;

    public BluetoothManagerWrapper_Factory(Provider<BluetoothManager> provider) {
        this.f13501a = provider;
    }

    public static BluetoothManagerWrapper_Factory create(Provider<BluetoothManager> provider) {
        return new BluetoothManagerWrapper_Factory(provider);
    }

    public static BluetoothManagerWrapper newInstance(BluetoothManager bluetoothManager) {
        return new BluetoothManagerWrapper(bluetoothManager);
    }

    @Override // bleshadow.javax.inject.Provider
    public BluetoothManagerWrapper get() {
        return newInstance(this.f13501a.get());
    }
}
