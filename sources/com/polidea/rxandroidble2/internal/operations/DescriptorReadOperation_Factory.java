package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattDescriptor;
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
public final class DescriptorReadOperation_Factory implements Factory<DescriptorReadOperation> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleGattCallback> f13451a;
    public final Provider<BluetoothGatt> b;
    public final Provider<TimeoutConfiguration> c;
    public final Provider<BluetoothGattDescriptor> d;

    public DescriptorReadOperation_Factory(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3, Provider<BluetoothGattDescriptor> provider4) {
        this.f13451a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static DescriptorReadOperation_Factory create(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3, Provider<BluetoothGattDescriptor> provider4) {
        return new DescriptorReadOperation_Factory(provider, provider2, provider3, provider4);
    }

    public static DescriptorReadOperation newInstance(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, TimeoutConfiguration timeoutConfiguration, BluetoothGattDescriptor bluetoothGattDescriptor) {
        return new DescriptorReadOperation(rxBleGattCallback, bluetoothGatt, timeoutConfiguration, bluetoothGattDescriptor);
    }

    @Override // bleshadow.javax.inject.Provider
    public DescriptorReadOperation get() {
        return newInstance(this.f13451a.get(), this.b.get(), this.c.get(), this.d.get());
    }
}
