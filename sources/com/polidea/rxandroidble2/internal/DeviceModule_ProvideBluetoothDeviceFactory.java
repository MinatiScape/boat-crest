package com.polidea.rxandroidble2.internal;

import android.bluetooth.BluetoothDevice;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class DeviceModule_ProvideBluetoothDeviceFactory implements Factory<BluetoothDevice> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<String> f13384a;
    public final Provider<RxBleAdapterWrapper> b;

    public DeviceModule_ProvideBluetoothDeviceFactory(Provider<String> provider, Provider<RxBleAdapterWrapper> provider2) {
        this.f13384a = provider;
        this.b = provider2;
    }

    public static DeviceModule_ProvideBluetoothDeviceFactory create(Provider<String> provider, Provider<RxBleAdapterWrapper> provider2) {
        return new DeviceModule_ProvideBluetoothDeviceFactory(provider, provider2);
    }

    public static BluetoothDevice provideBluetoothDevice(String str, RxBleAdapterWrapper rxBleAdapterWrapper) {
        return (BluetoothDevice) Preconditions.checkNotNullFromProvides(DeviceModule.a(str, rxBleAdapterWrapper));
    }

    @Override // bleshadow.javax.inject.Provider
    public BluetoothDevice get() {
        return provideBluetoothDevice(this.f13384a.get(), this.b.get());
    }
}
