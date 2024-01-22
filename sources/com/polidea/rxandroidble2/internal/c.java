package com.polidea.rxandroidble2.internal;

import android.bluetooth.BluetoothDevice;
import androidx.annotation.Nullable;
import bleshadow.javax.inject.Inject;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.polidea.rxandroidble2.ConnectionSetup;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.RxBleDevice;
import com.polidea.rxandroidble2.Timeout;
import com.polidea.rxandroidble2.exceptions.BleAlreadyConnectedException;
import com.polidea.rxandroidble2.internal.connection.Connector;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.util.CheckerConnectPermission;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
@DeviceScope
/* loaded from: classes9.dex */
public class c implements RxBleDevice {

    /* renamed from: a  reason: collision with root package name */
    public final BluetoothDevice f13396a;
    public final Connector b;
    public final BehaviorRelay<RxBleConnection.RxBleConnectionState> c;
    public final CheckerConnectPermission d;
    public final AtomicBoolean e = new AtomicBoolean(false);

    @Inject
    public c(BluetoothDevice bluetoothDevice, Connector connector, BehaviorRelay<RxBleConnection.RxBleConnectionState> behaviorRelay, CheckerConnectPermission checkerConnectPermission) {
        this.f13396a = bluetoothDevice;
        this.b = connector;
        this.c = behaviorRelay;
        this.d = checkerConnectPermission;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e() throws Exception {
        this.e.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ObservableSource f(ConnectionSetup connectionSetup) throws Exception {
        if (this.e.compareAndSet(false, true)) {
            return this.b.prepareConnection(connectionSetup).doFinally(new Action() { // from class: com.polidea.rxandroidble2.internal.a
                @Override // io.reactivex.functions.Action
                public final void run() {
                    c.this.e();
                }
            });
        }
        return Observable.error(new BleAlreadyConnectedException(this.f13396a.getAddress()));
    }

    public Observable<RxBleConnection> c(final ConnectionSetup connectionSetup) {
        return Observable.defer(new Callable() { // from class: com.polidea.rxandroidble2.internal.b
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ObservableSource f;
                f = c.this.f(connectionSetup);
                return f;
            }
        });
    }

    public final String d(boolean z) {
        return (!z || this.d.isConnectRuntimePermissionGranted()) ? this.f13396a.getName() : "[NO BLUETOOTH_CONNECT PERMISSION]";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            return this.f13396a.equals(((c) obj).f13396a);
        }
        return false;
    }

    @Override // com.polidea.rxandroidble2.RxBleDevice
    public Observable<RxBleConnection> establishConnection(boolean z) {
        return c(new ConnectionSetup.Builder().setAutoConnect(z).setSuppressIllegalOperationCheck(true).build());
    }

    @Override // com.polidea.rxandroidble2.RxBleDevice
    public BluetoothDevice getBluetoothDevice() {
        return this.f13396a;
    }

    @Override // com.polidea.rxandroidble2.RxBleDevice
    public RxBleConnection.RxBleConnectionState getConnectionState() {
        return this.c.getValue();
    }

    @Override // com.polidea.rxandroidble2.RxBleDevice
    public String getMacAddress() {
        return this.f13396a.getAddress();
    }

    @Override // com.polidea.rxandroidble2.RxBleDevice
    @Nullable
    public String getName() {
        return d(false);
    }

    public int hashCode() {
        return this.f13396a.hashCode();
    }

    @Override // com.polidea.rxandroidble2.RxBleDevice
    public Observable<RxBleConnection.RxBleConnectionState> observeConnectionStateChanges() {
        return this.c.distinctUntilChanged().skip(1L);
    }

    public String toString() {
        return "RxBleDeviceImpl{" + LoggerUtil.commonMacMessage(this.f13396a.getAddress()) + ", name=" + d(true) + '}';
    }

    @Override // com.polidea.rxandroidble2.RxBleDevice
    public Observable<RxBleConnection> establishConnection(boolean z, Timeout timeout) {
        return c(new ConnectionSetup.Builder().setAutoConnect(z).setOperationTimeout(timeout).setSuppressIllegalOperationCheck(true).build());
    }
}
