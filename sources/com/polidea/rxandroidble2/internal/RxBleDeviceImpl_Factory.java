package com.polidea.rxandroidble2.internal;

import android.bluetooth.BluetoothDevice;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.internal.connection.Connector;
import com.polidea.rxandroidble2.internal.util.CheckerConnectPermission;
@ScopeMetadata("com.polidea.rxandroidble2.internal.DeviceScope")
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes9.dex */
public final class RxBleDeviceImpl_Factory implements Factory<c> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<BluetoothDevice> f13391a;
    public final Provider<Connector> b;
    public final Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> c;
    public final Provider<CheckerConnectPermission> d;

    public RxBleDeviceImpl_Factory(Provider<BluetoothDevice> provider, Provider<Connector> provider2, Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> provider3, Provider<CheckerConnectPermission> provider4) {
        this.f13391a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static RxBleDeviceImpl_Factory create(Provider<BluetoothDevice> provider, Provider<Connector> provider2, Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> provider3, Provider<CheckerConnectPermission> provider4) {
        return new RxBleDeviceImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static c newInstance(BluetoothDevice bluetoothDevice, Connector connector, BehaviorRelay<RxBleConnection.RxBleConnectionState> behaviorRelay, CheckerConnectPermission checkerConnectPermission) {
        return new c(bluetoothDevice, connector, behaviorRelay, checkerConnectPermission);
    }

    @Override // bleshadow.javax.inject.Provider
    public c get() {
        return newInstance(this.f13391a.get(), this.b.get(), this.c.get(), this.d.get());
    }
}
