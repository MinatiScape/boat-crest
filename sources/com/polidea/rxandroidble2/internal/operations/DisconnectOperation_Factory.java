package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothManager;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.connection.BluetoothGattProvider;
import com.polidea.rxandroidble2.internal.connection.ConnectionStateChangeListener;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import io.reactivex.Scheduler;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes12.dex */
public final class DisconnectOperation_Factory implements Factory<DisconnectOperation> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<RxBleGattCallback> f13452a;
    public final Provider<BluetoothGattProvider> b;
    public final Provider<String> c;
    public final Provider<BluetoothManager> d;
    public final Provider<Scheduler> e;
    public final Provider<TimeoutConfiguration> f;
    public final Provider<ConnectionStateChangeListener> g;

    public DisconnectOperation_Factory(Provider<RxBleGattCallback> provider, Provider<BluetoothGattProvider> provider2, Provider<String> provider3, Provider<BluetoothManager> provider4, Provider<Scheduler> provider5, Provider<TimeoutConfiguration> provider6, Provider<ConnectionStateChangeListener> provider7) {
        this.f13452a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
        this.e = provider5;
        this.f = provider6;
        this.g = provider7;
    }

    public static DisconnectOperation_Factory create(Provider<RxBleGattCallback> provider, Provider<BluetoothGattProvider> provider2, Provider<String> provider3, Provider<BluetoothManager> provider4, Provider<Scheduler> provider5, Provider<TimeoutConfiguration> provider6, Provider<ConnectionStateChangeListener> provider7) {
        return new DisconnectOperation_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static DisconnectOperation newInstance(RxBleGattCallback rxBleGattCallback, BluetoothGattProvider bluetoothGattProvider, String str, BluetoothManager bluetoothManager, Scheduler scheduler, TimeoutConfiguration timeoutConfiguration, ConnectionStateChangeListener connectionStateChangeListener) {
        return new DisconnectOperation(rxBleGattCallback, bluetoothGattProvider, str, bluetoothManager, scheduler, timeoutConfiguration, connectionStateChangeListener);
    }

    @Override // bleshadow.javax.inject.Provider
    public DisconnectOperation get() {
        return newInstance(this.f13452a.get(), this.b.get(), this.c.get(), this.d.get(), this.e.get(), this.f.get(), this.g.get());
    }
}
