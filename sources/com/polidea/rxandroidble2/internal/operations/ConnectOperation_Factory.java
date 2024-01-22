package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothDevice;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.connection.BluetoothGattProvider;
import com.polidea.rxandroidble2.internal.connection.ConnectionStateChangeListener;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.util.BleConnectionCompat;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class ConnectOperation_Factory implements Factory<ConnectOperation> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<BluetoothDevice> f13449a;
    public final Provider<BleConnectionCompat> b;
    public final Provider<RxBleGattCallback> c;
    public final Provider<BluetoothGattProvider> d;
    public final Provider<TimeoutConfiguration> e;
    public final Provider<Boolean> f;
    public final Provider<ConnectionStateChangeListener> g;

    public ConnectOperation_Factory(Provider<BluetoothDevice> provider, Provider<BleConnectionCompat> provider2, Provider<RxBleGattCallback> provider3, Provider<BluetoothGattProvider> provider4, Provider<TimeoutConfiguration> provider5, Provider<Boolean> provider6, Provider<ConnectionStateChangeListener> provider7) {
        this.f13449a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
        this.e = provider5;
        this.f = provider6;
        this.g = provider7;
    }

    public static ConnectOperation_Factory create(Provider<BluetoothDevice> provider, Provider<BleConnectionCompat> provider2, Provider<RxBleGattCallback> provider3, Provider<BluetoothGattProvider> provider4, Provider<TimeoutConfiguration> provider5, Provider<Boolean> provider6, Provider<ConnectionStateChangeListener> provider7) {
        return new ConnectOperation_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static ConnectOperation newInstance(BluetoothDevice bluetoothDevice, BleConnectionCompat bleConnectionCompat, RxBleGattCallback rxBleGattCallback, BluetoothGattProvider bluetoothGattProvider, TimeoutConfiguration timeoutConfiguration, boolean z, ConnectionStateChangeListener connectionStateChangeListener) {
        return new ConnectOperation(bluetoothDevice, bleConnectionCompat, rxBleGattCallback, bluetoothGattProvider, timeoutConfiguration, z, connectionStateChangeListener);
    }

    @Override // bleshadow.javax.inject.Provider
    public ConnectOperation get() {
        return newInstance(this.f13449a.get(), this.b.get(), this.c.get(), this.d.get(), this.e.get(), this.f.get().booleanValue(), this.g.get());
    }
}
