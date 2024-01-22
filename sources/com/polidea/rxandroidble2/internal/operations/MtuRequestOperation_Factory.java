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
public final class MtuRequestOperation_Factory implements Factory<MtuRequestOperation> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleGattCallback> f13454a;
    public final Provider<BluetoothGatt> b;
    public final Provider<TimeoutConfiguration> c;
    public final Provider<Integer> d;

    public MtuRequestOperation_Factory(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3, Provider<Integer> provider4) {
        this.f13454a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static MtuRequestOperation_Factory create(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3, Provider<Integer> provider4) {
        return new MtuRequestOperation_Factory(provider, provider2, provider3, provider4);
    }

    public static MtuRequestOperation newInstance(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, TimeoutConfiguration timeoutConfiguration, int i) {
        return new MtuRequestOperation(rxBleGattCallback, bluetoothGatt, timeoutConfiguration, i);
    }

    @Override // bleshadow.javax.inject.Provider
    public MtuRequestOperation get() {
        return newInstance(this.f13454a.get(), this.b.get(), this.c.get(), this.d.get().intValue());
    }
}
