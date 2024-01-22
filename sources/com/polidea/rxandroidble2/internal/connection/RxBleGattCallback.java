package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;
import com.polidea.rxandroidble2.ConnectionParameters;
import com.polidea.rxandroidble2.HiddenBluetoothGattCallback;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleGattCharacteristicException;
import com.polidea.rxandroidble2.exceptions.BleGattDescriptorException;
import com.polidea.rxandroidble2.exceptions.BleGattException;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.util.ByteAssociation;
import com.polidea.rxandroidble2.internal.util.CharacteristicChangedEvent;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@ConnectionScope
/* loaded from: classes12.dex */
public class RxBleGattCallback {

    /* renamed from: a  reason: collision with root package name */
    public final Scheduler f13425a;
    public final BluetoothGattProvider b;
    public final h c;
    public final l d;
    public final PublishRelay<RxBleConnection.RxBleConnectionState> e = PublishRelay.create();
    public final c<RxBleDeviceServices> f = new c<>();
    public final c<ByteAssociation<UUID>> g = new c<>();
    public final c<ByteAssociation<UUID>> h = new c<>();
    public final Relay<CharacteristicChangedEvent> i = PublishRelay.create().toSerialized();
    public final c<ByteAssociation<BluetoothGattDescriptor>> j = new c<>();
    public final c<ByteAssociation<BluetoothGattDescriptor>> k = new c<>();
    public final c<Integer> l = new c<>();
    public final c<Integer> m = new c<>();
    public final c<ConnectionParameters> n = new c<>();
    public final Function<BleGattException, Observable<?>> o = new a(this);
    public final BluetoothGattCallback p = new b();

    /* loaded from: classes12.dex */
    public class a implements Function<BleGattException, Observable<?>> {
        public a(RxBleGattCallback rxBleGattCallback) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Observable<?> apply(BleGattException bleGattException) {
            return Observable.error(bleGattException);
        }
    }

    /* loaded from: classes12.dex */
    public class b extends BluetoothGattCallback {
        public b() {
        }

        public final boolean a(int i) {
            return i == 0 || i == 3;
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            LoggerUtil.logCallback("onCharacteristicChanged", bluetoothGatt, bluetoothGattCharacteristic, true);
            RxBleGattCallback.this.d.a(bluetoothGatt, bluetoothGattCharacteristic);
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (RxBleGattCallback.this.i.hasObservers()) {
                RxBleGattCallback.this.i.accept(new CharacteristicChangedEvent(bluetoothGattCharacteristic.getUuid(), Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getValue()));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LoggerUtil.logCallback("onCharacteristicRead", bluetoothGatt, i, bluetoothGattCharacteristic, true);
            RxBleGattCallback.this.d.g(bluetoothGatt, bluetoothGattCharacteristic, i);
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (!RxBleGattCallback.this.g.a() || RxBleGattCallback.d(RxBleGattCallback.this.g, bluetoothGatt, bluetoothGattCharacteristic, i, BleGattOperationType.CHARACTERISTIC_READ)) {
                return;
            }
            RxBleGattCallback.this.g.f13427a.accept(new ByteAssociation<>(bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic.getValue()));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LoggerUtil.logCallback("onCharacteristicWrite", bluetoothGatt, i, bluetoothGattCharacteristic, false);
            RxBleGattCallback.this.d.k(bluetoothGatt, bluetoothGattCharacteristic, i);
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (!RxBleGattCallback.this.h.a() || RxBleGattCallback.d(RxBleGattCallback.this.h, bluetoothGatt, bluetoothGattCharacteristic, i, BleGattOperationType.CHARACTERISTIC_WRITE)) {
                return;
            }
            RxBleGattCallback.this.h.f13427a.accept(new ByteAssociation<>(bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic.getValue()));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            LoggerUtil.logCallback("onConnectionStateChange", bluetoothGatt, i, i2);
            RxBleGattCallback.this.d.b(bluetoothGatt, i, i2);
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            RxBleGattCallback.this.b.updateBluetoothGatt(bluetoothGatt);
            if (a(i2)) {
                RxBleGattCallback.this.c.b(new BleDisconnectedException(bluetoothGatt.getDevice().getAddress(), i));
            } else if (i != 0) {
                RxBleGattCallback.this.c.c(new BleGattException(bluetoothGatt, i, BleGattOperationType.CONNECTION_STATE));
            }
            RxBleGattCallback.this.e.accept(RxBleGattCallback.b(i2));
        }

        public void onConnectionUpdated(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4) {
            LoggerUtil.logConnectionUpdateCallback("onConnectionUpdated", bluetoothGatt, i4, i, i2, i3);
            RxBleGattCallback.this.d.f(bluetoothGatt, i, i2, i3, i4);
            if (!RxBleGattCallback.this.n.a() || RxBleGattCallback.c(RxBleGattCallback.this.n, bluetoothGatt, i4, BleGattOperationType.CONNECTION_PRIORITY_CHANGE)) {
                return;
            }
            RxBleGattCallback.this.n.f13427a.accept(new ConnectionParametersImpl(i, i2, i3));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            LoggerUtil.logCallback("onDescriptorRead", bluetoothGatt, i, bluetoothGattDescriptor, true);
            RxBleGattCallback.this.d.c(bluetoothGatt, bluetoothGattDescriptor, i);
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            if (!RxBleGattCallback.this.j.a() || RxBleGattCallback.e(RxBleGattCallback.this.j, bluetoothGatt, bluetoothGattDescriptor, i, BleGattOperationType.DESCRIPTOR_READ)) {
                return;
            }
            RxBleGattCallback.this.j.f13427a.accept(new ByteAssociation<>(bluetoothGattDescriptor, bluetoothGattDescriptor.getValue()));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            LoggerUtil.logCallback("onDescriptorWrite", bluetoothGatt, i, bluetoothGattDescriptor, false);
            RxBleGattCallback.this.d.d(bluetoothGatt, bluetoothGattDescriptor, i);
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            if (!RxBleGattCallback.this.k.a() || RxBleGattCallback.e(RxBleGattCallback.this.k, bluetoothGatt, bluetoothGattDescriptor, i, BleGattOperationType.DESCRIPTOR_WRITE)) {
                return;
            }
            RxBleGattCallback.this.k.f13427a.accept(new ByteAssociation<>(bluetoothGattDescriptor, bluetoothGattDescriptor.getValue()));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            LoggerUtil.logCallback("onMtuChanged", bluetoothGatt, i2, i);
            RxBleGattCallback.this.d.e(bluetoothGatt, i, i2);
            super.onMtuChanged(bluetoothGatt, i, i2);
            if (!RxBleGattCallback.this.m.a() || RxBleGattCallback.c(RxBleGattCallback.this.m, bluetoothGatt, i2, BleGattOperationType.ON_MTU_CHANGED)) {
                return;
            }
            RxBleGattCallback.this.m.f13427a.accept(Integer.valueOf(i));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            LoggerUtil.logCallback("onReadRemoteRssi", bluetoothGatt, i2, i);
            RxBleGattCallback.this.d.h(bluetoothGatt, i, i2);
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
            if (!RxBleGattCallback.this.l.a() || RxBleGattCallback.c(RxBleGattCallback.this.l, bluetoothGatt, i2, BleGattOperationType.READ_RSSI)) {
                return;
            }
            RxBleGattCallback.this.l.f13427a.accept(Integer.valueOf(i));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            LoggerUtil.logCallback("onReliableWriteCompleted", bluetoothGatt, i);
            RxBleGattCallback.this.d.i(bluetoothGatt, i);
            super.onReliableWriteCompleted(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            LoggerUtil.logCallback("onServicesDiscovered", bluetoothGatt, i);
            RxBleGattCallback.this.d.j(bluetoothGatt, i);
            super.onServicesDiscovered(bluetoothGatt, i);
            if (!RxBleGattCallback.this.f.a() || RxBleGattCallback.c(RxBleGattCallback.this.f, bluetoothGatt, i, BleGattOperationType.SERVICE_DISCOVERY)) {
                return;
            }
            RxBleGattCallback.this.f.f13427a.accept(new RxBleDeviceServices(bluetoothGatt.getServices()));
        }
    }

    /* loaded from: classes12.dex */
    public static class c<T> {

        /* renamed from: a  reason: collision with root package name */
        public final PublishRelay<T> f13427a = PublishRelay.create();
        public final PublishRelay<BleGattException> b = PublishRelay.create();

        public boolean a() {
            return this.f13427a.hasObservers() || this.b.hasObservers();
        }
    }

    @Inject
    public RxBleGattCallback(@Named("bluetooth_callbacks") Scheduler scheduler, BluetoothGattProvider bluetoothGattProvider, h hVar, l lVar) {
        this.f13425a = scheduler;
        this.b = bluetoothGattProvider;
        this.c = hVar;
        this.d = lVar;
    }

    public static boolean a(int i) {
        return i != 0;
    }

    public static RxBleConnection.RxBleConnectionState b(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return RxBleConnection.RxBleConnectionState.DISCONNECTED;
                }
                return RxBleConnection.RxBleConnectionState.DISCONNECTING;
            }
            return RxBleConnection.RxBleConnectionState.CONNECTED;
        }
        return RxBleConnection.RxBleConnectionState.CONNECTING;
    }

    public static boolean c(c<?> cVar, BluetoothGatt bluetoothGatt, int i, BleGattOperationType bleGattOperationType) {
        return a(i) && f(cVar, new BleGattException(bluetoothGatt, i, bleGattOperationType));
    }

    public static boolean d(c<?> cVar, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, BleGattOperationType bleGattOperationType) {
        return a(i) && f(cVar, new BleGattCharacteristicException(bluetoothGatt, bluetoothGattCharacteristic, i, bleGattOperationType));
    }

    public static boolean e(c<?> cVar, BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i, BleGattOperationType bleGattOperationType) {
        return a(i) && f(cVar, new BleGattDescriptorException(bluetoothGatt, bluetoothGattDescriptor, i, bleGattOperationType));
    }

    public static boolean f(c<?> cVar, BleGattException bleGattException) {
        cVar.b.accept(bleGattException);
        return true;
    }

    public final <T> Observable<T> g(c<T> cVar) {
        return Observable.merge(this.c.asErrorOnlyObservable(), cVar.f13427a, cVar.b.flatMap(this.o));
    }

    public BluetoothGattCallback getBluetoothGattCallback() {
        return this.p;
    }

    public Observable<ConnectionParameters> getConnectionParametersUpdates() {
        return g(this.n).delay(0L, TimeUnit.SECONDS, this.f13425a);
    }

    public Observable<CharacteristicChangedEvent> getOnCharacteristicChanged() {
        return Observable.merge(this.c.asErrorOnlyObservable(), this.i).delay(0L, TimeUnit.SECONDS, this.f13425a);
    }

    public Observable<ByteAssociation<UUID>> getOnCharacteristicRead() {
        return g(this.g).delay(0L, TimeUnit.SECONDS, this.f13425a);
    }

    public Observable<ByteAssociation<UUID>> getOnCharacteristicWrite() {
        return g(this.h).delay(0L, TimeUnit.SECONDS, this.f13425a);
    }

    public Observable<RxBleConnection.RxBleConnectionState> getOnConnectionStateChange() {
        return this.e.delay(0L, TimeUnit.SECONDS, this.f13425a);
    }

    public Observable<ByteAssociation<BluetoothGattDescriptor>> getOnDescriptorRead() {
        return g(this.j).delay(0L, TimeUnit.SECONDS, this.f13425a);
    }

    public Observable<ByteAssociation<BluetoothGattDescriptor>> getOnDescriptorWrite() {
        return g(this.k).delay(0L, TimeUnit.SECONDS, this.f13425a);
    }

    public Observable<Integer> getOnMtuChanged() {
        return g(this.m).delay(0L, TimeUnit.SECONDS, this.f13425a);
    }

    public Observable<Integer> getOnRssiRead() {
        return g(this.l).delay(0L, TimeUnit.SECONDS, this.f13425a);
    }

    public Observable<RxBleDeviceServices> getOnServicesDiscovered() {
        return g(this.f).delay(0L, TimeUnit.SECONDS, this.f13425a);
    }

    public <T> Observable<T> observeDisconnect() {
        return this.c.asErrorOnlyObservable();
    }

    public void setHiddenNativeCallback(HiddenBluetoothGattCallback hiddenBluetoothGattCallback) {
        this.d.m(hiddenBluetoothGattCallback);
    }

    public void setNativeCallback(BluetoothGattCallback bluetoothGattCallback) {
        this.d.l(bluetoothGattCallback);
    }
}
