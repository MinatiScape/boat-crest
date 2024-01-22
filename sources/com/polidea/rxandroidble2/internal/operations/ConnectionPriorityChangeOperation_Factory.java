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
@QualifierMetadata
/* loaded from: classes12.dex */
public final class ConnectionPriorityChangeOperation_Factory implements Factory<ConnectionPriorityChangeOperation> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleGattCallback> f13450a;
    public final Provider<BluetoothGatt> b;
    public final Provider<TimeoutConfiguration> c;
    public final Provider<Integer> d;
    public final Provider<TimeoutConfiguration> e;

    public ConnectionPriorityChangeOperation_Factory(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3, Provider<Integer> provider4, Provider<TimeoutConfiguration> provider5) {
        this.f13450a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
        this.e = provider5;
    }

    public static ConnectionPriorityChangeOperation_Factory create(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3, Provider<Integer> provider4, Provider<TimeoutConfiguration> provider5) {
        return new ConnectionPriorityChangeOperation_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static ConnectionPriorityChangeOperation newInstance(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, TimeoutConfiguration timeoutConfiguration, int i, TimeoutConfiguration timeoutConfiguration2) {
        return new ConnectionPriorityChangeOperation(rxBleGattCallback, bluetoothGatt, timeoutConfiguration, i, timeoutConfiguration2);
    }

    @Override // bleshadow.javax.inject.Provider
    public ConnectionPriorityChangeOperation get() {
        return newInstance(this.f13450a.get(), this.b.get(), this.c.get(), this.d.get().intValue(), this.e.get());
    }
}
