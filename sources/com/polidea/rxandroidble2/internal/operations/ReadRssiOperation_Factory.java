package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class ReadRssiOperation_Factory implements Factory<ReadRssiOperation> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleGattCallback> f13457a;
    public final Provider<BluetoothGatt> b;
    public final Provider<TimeoutConfiguration> c;

    public ReadRssiOperation_Factory(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3) {
        this.f13457a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static ReadRssiOperation_Factory create(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3) {
        return new ReadRssiOperation_Factory(provider, provider2, provider3);
    }

    public static ReadRssiOperation newInstance(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, TimeoutConfiguration timeoutConfiguration) {
        return new ReadRssiOperation(rxBleGattCallback, bluetoothGatt, timeoutConfiguration);
    }

    @Override // bleshadow.javax.inject.Provider
    public ReadRssiOperation get() {
        return newInstance(this.f13457a.get(), this.b.get(), this.c.get());
    }
}
