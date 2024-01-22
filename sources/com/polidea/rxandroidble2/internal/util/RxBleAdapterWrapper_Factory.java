package com.polidea.rxandroidble2.internal.util;

import android.bluetooth.BluetoothAdapter;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class RxBleAdapterWrapper_Factory implements Factory<RxBleAdapterWrapper> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<BluetoothAdapter> f13523a;

    public RxBleAdapterWrapper_Factory(Provider<BluetoothAdapter> provider) {
        this.f13523a = provider;
    }

    public static RxBleAdapterWrapper_Factory create(Provider<BluetoothAdapter> provider) {
        return new RxBleAdapterWrapper_Factory(provider);
    }

    public static RxBleAdapterWrapper newInstance(BluetoothAdapter bluetoothAdapter) {
        return new RxBleAdapterWrapper(bluetoothAdapter);
    }

    @Override // bleshadow.javax.inject.Provider
    public RxBleAdapterWrapper get() {
        return newInstance(this.f13523a.get());
    }
}
