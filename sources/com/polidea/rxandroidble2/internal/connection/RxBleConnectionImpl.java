package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.DeadObjectException;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ConnectionParameters;
import com.polidea.rxandroidble2.NotificationSetupMode;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.RxBleCustomOperation;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.internal.Priority;
import com.polidea.rxandroidble2.internal.QueueOperation;
import com.polidea.rxandroidble2.internal.operations.OperationsProvider;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble2.internal.util.ByteAssociation;
import com.polidea.rxandroidble2.internal.util.QueueReleasingEmitterWrapper;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@ConnectionScope
/* loaded from: classes12.dex */
public class RxBleConnectionImpl implements RxBleConnection {

    /* renamed from: a  reason: collision with root package name */
    public final ConnectionOperationQueue f13423a;
    public final RxBleGattCallback b;
    public final BluetoothGatt c;
    public final OperationsProvider d;
    public final Provider<RxBleConnection.LongWriteOperationBuilder> e;
    public final Scheduler f;
    public final x g;
    public final w h;
    public final com.polidea.rxandroidble2.internal.connection.j i;
    public final com.polidea.rxandroidble2.internal.connection.f j;
    public final IllegalOperationChecker k;

    /* loaded from: classes12.dex */
    public class a implements Function<RxBleDeviceServices, SingleSource<BluetoothGattDescriptor>> {
        public final /* synthetic */ UUID h;
        public final /* synthetic */ UUID i;
        public final /* synthetic */ UUID j;

        public a(RxBleConnectionImpl rxBleConnectionImpl, UUID uuid, UUID uuid2, UUID uuid3) {
            this.h = uuid;
            this.i = uuid2;
            this.j = uuid3;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<BluetoothGattDescriptor> apply(RxBleDeviceServices rxBleDeviceServices) {
            return rxBleDeviceServices.getDescriptor(this.h, this.i, this.j);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes12.dex */
    public class b<T> extends QueueOperation<T> {
        public final /* synthetic */ RxBleCustomOperation h;
        public final /* synthetic */ Priority i;

        /* loaded from: classes12.dex */
        public class a implements Action {
            public a() {
            }

            @Override // io.reactivex.functions.Action
            public void run() {
                RxBleConnectionImpl.this.b.setNativeCallback(null);
                RxBleConnectionImpl.this.b.setHiddenNativeCallback(null);
            }
        }

        public b(RxBleCustomOperation rxBleCustomOperation, Priority priority) {
            this.h = rxBleCustomOperation;
            this.i = priority;
        }

        public final Action a() {
            return new a();
        }

        @Override // com.polidea.rxandroidble2.internal.QueueOperation, com.polidea.rxandroidble2.internal.operations.Operation
        public Priority definedPriority() {
            return this.i;
        }

        @Override // com.polidea.rxandroidble2.internal.QueueOperation
        public void protectedRun(ObservableEmitter<T> observableEmitter, QueueReleaseInterface queueReleaseInterface) throws Throwable {
            try {
                RxBleCustomOperation rxBleCustomOperation = this.h;
                RxBleConnectionImpl rxBleConnectionImpl = RxBleConnectionImpl.this;
                Observable<T> asObservable = rxBleCustomOperation.asObservable(rxBleConnectionImpl.c, rxBleConnectionImpl.b, rxBleConnectionImpl.f);
                if (asObservable != null) {
                    asObservable.doOnTerminate(a()).subscribe(new QueueReleasingEmitterWrapper(observableEmitter, queueReleaseInterface));
                    return;
                }
                queueReleaseInterface.release();
                throw new IllegalArgumentException("The custom operation asObservable method must return a non-null observable");
            } catch (Throwable th) {
                queueReleaseInterface.release();
                throw th;
            }
        }

        @Override // com.polidea.rxandroidble2.internal.QueueOperation
        public BleException provideException(DeadObjectException deadObjectException) {
            return new BleDisconnectedException(deadObjectException, RxBleConnectionImpl.this.c.getDevice().getAddress(), -1);
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Function<RxBleDeviceServices, Single<? extends BluetoothGattCharacteristic>> {
        public final /* synthetic */ UUID h;

        public c(RxBleConnectionImpl rxBleConnectionImpl, UUID uuid) {
            this.h = uuid;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Single<? extends BluetoothGattCharacteristic> apply(RxBleDeviceServices rxBleDeviceServices) {
            return rxBleDeviceServices.getCharacteristic(this.h);
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Function<BluetoothGattCharacteristic, ObservableSource<? extends Observable<byte[]>>> {
        public final /* synthetic */ NotificationSetupMode h;

        public d(NotificationSetupMode notificationSetupMode) {
            this.h = notificationSetupMode;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Observable<? extends Observable<byte[]>> apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            return RxBleConnectionImpl.this.setupNotification(bluetoothGattCharacteristic, this.h);
        }
    }

    /* loaded from: classes12.dex */
    public class e implements Function<BluetoothGattCharacteristic, ObservableSource<? extends Observable<byte[]>>> {
        public final /* synthetic */ NotificationSetupMode h;

        public e(NotificationSetupMode notificationSetupMode) {
            this.h = notificationSetupMode;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Observable<? extends Observable<byte[]>> apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            return RxBleConnectionImpl.this.setupIndication(bluetoothGattCharacteristic, this.h);
        }
    }

    /* loaded from: classes12.dex */
    public class f implements Function<BluetoothGattCharacteristic, SingleSource<? extends byte[]>> {
        public f() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<? extends byte[]> apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            return RxBleConnectionImpl.this.readCharacteristic(bluetoothGattCharacteristic);
        }
    }

    /* loaded from: classes12.dex */
    public class g implements Function<BluetoothGattCharacteristic, SingleSource<? extends byte[]>> {
        public final /* synthetic */ byte[] h;

        public g(byte[] bArr) {
            this.h = bArr;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<? extends byte[]> apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            return RxBleConnectionImpl.this.writeCharacteristic(bluetoothGattCharacteristic, this.h);
        }
    }

    /* loaded from: classes12.dex */
    public class h implements Function<BluetoothGattDescriptor, SingleSource<byte[]>> {
        public h() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<byte[]> apply(BluetoothGattDescriptor bluetoothGattDescriptor) {
            return RxBleConnectionImpl.this.readDescriptor(bluetoothGattDescriptor);
        }
    }

    /* loaded from: classes12.dex */
    public class i implements Function<RxBleDeviceServices, SingleSource<BluetoothGattDescriptor>> {
        public final /* synthetic */ UUID h;
        public final /* synthetic */ UUID i;
        public final /* synthetic */ UUID j;

        public i(RxBleConnectionImpl rxBleConnectionImpl, UUID uuid, UUID uuid2, UUID uuid3) {
            this.h = uuid;
            this.i = uuid2;
            this.j = uuid3;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public SingleSource<BluetoothGattDescriptor> apply(RxBleDeviceServices rxBleDeviceServices) {
            return rxBleDeviceServices.getDescriptor(this.h, this.i, this.j);
        }
    }

    /* loaded from: classes12.dex */
    public class j implements Function<ByteAssociation<BluetoothGattDescriptor>, byte[]> {
        public j(RxBleConnectionImpl rxBleConnectionImpl) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public byte[] apply(ByteAssociation<BluetoothGattDescriptor> byteAssociation) {
            return byteAssociation.second;
        }
    }

    /* loaded from: classes12.dex */
    public class k implements Function<BluetoothGattDescriptor, CompletableSource> {
        public final /* synthetic */ byte[] h;

        public k(byte[] bArr) {
            this.h = bArr;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public CompletableSource apply(BluetoothGattDescriptor bluetoothGattDescriptor) {
            return RxBleConnectionImpl.this.writeDescriptor(bluetoothGattDescriptor, this.h);
        }
    }

    @Inject
    public RxBleConnectionImpl(ConnectionOperationQueue connectionOperationQueue, RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, x xVar, w wVar, com.polidea.rxandroidble2.internal.connection.j jVar, com.polidea.rxandroidble2.internal.connection.f fVar, OperationsProvider operationsProvider, Provider<RxBleConnection.LongWriteOperationBuilder> provider, @Named("bluetooth_interaction") Scheduler scheduler, IllegalOperationChecker illegalOperationChecker) {
        this.f13423a = connectionOperationQueue;
        this.b = rxBleGattCallback;
        this.c = bluetoothGatt;
        this.g = xVar;
        this.h = wVar;
        this.i = jVar;
        this.j = fVar;
        this.d = operationsProvider;
        this.e = provider;
        this.f = scheduler;
        this.k = illegalOperationChecker;
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public RxBleConnection.LongWriteOperationBuilder createNewLongWriteBuilder() {
        return this.e.get();
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Single<RxBleDeviceServices> discoverServices() {
        return this.g.a(20L, TimeUnit.SECONDS);
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    @Deprecated
    public Single<BluetoothGattCharacteristic> getCharacteristic(@NonNull UUID uuid) {
        return discoverServices().flatMap(new c(this, uuid));
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public int getMtu() {
        return this.i.getMtu();
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Observable<ConnectionParameters> observeConnectionParametersUpdates() {
        return this.b.getConnectionParametersUpdates();
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public <T> Observable<T> queue(@NonNull RxBleCustomOperation<T> rxBleCustomOperation) {
        return queue(rxBleCustomOperation, Priority.NORMAL);
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Single<byte[]> readCharacteristic(@NonNull UUID uuid) {
        return getCharacteristic(uuid).flatMap(new f());
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Single<byte[]> readDescriptor(@NonNull UUID uuid, @NonNull UUID uuid2, @NonNull UUID uuid3) {
        return discoverServices().flatMap(new i(this, uuid, uuid2, uuid3)).flatMap(new h());
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Single<Integer> readRssi() {
        return this.f13423a.queue(this.d.provideRssiReadOperation()).firstOrError();
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    @RequiresApi(21)
    public Completable requestConnectionPriority(int i2, long j2, @NonNull TimeUnit timeUnit) {
        if (i2 == 2 || i2 == 0 || i2 == 1) {
            if (j2 <= 0) {
                return Completable.error(new IllegalArgumentException("Delay must be bigger than 0"));
            }
            return this.f13423a.queue(this.d.provideConnectionPriorityChangeOperation(i2, j2, timeUnit)).ignoreElements();
        }
        return Completable.error(new IllegalArgumentException("Connection priority must have valid value from BluetoothGatt (received " + i2 + ")"));
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    @RequiresApi(21)
    public Single<Integer> requestMtu(int i2) {
        return this.f13423a.queue(this.d.provideMtuChangeOperation(i2)).firstOrError();
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Observable<Observable<byte[]>> setupIndication(@NonNull UUID uuid) {
        return setupIndication(uuid, NotificationSetupMode.DEFAULT);
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Observable<Observable<byte[]>> setupNotification(@NonNull UUID uuid) {
        return setupNotification(uuid, NotificationSetupMode.DEFAULT);
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Single<byte[]> writeCharacteristic(@NonNull UUID uuid, @NonNull byte[] bArr) {
        return getCharacteristic(uuid).flatMap(new g(bArr));
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Completable writeDescriptor(@NonNull UUID uuid, @NonNull UUID uuid2, @NonNull UUID uuid3, @NonNull byte[] bArr) {
        return discoverServices().flatMap(new a(this, uuid, uuid2, uuid3)).flatMapCompletable(new k(bArr));
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Single<RxBleDeviceServices> discoverServices(long j2, @NonNull TimeUnit timeUnit) {
        return this.g.a(j2, timeUnit);
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public <T> Observable<T> queue(@NonNull RxBleCustomOperation<T> rxBleCustomOperation, @NonNull Priority priority) {
        return this.f13423a.queue(new b(rxBleCustomOperation, priority));
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Observable<Observable<byte[]>> setupIndication(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return setupIndication(bluetoothGattCharacteristic, NotificationSetupMode.DEFAULT);
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Observable<Observable<byte[]>> setupNotification(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return setupNotification(bluetoothGattCharacteristic, NotificationSetupMode.DEFAULT);
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Single<byte[]> readCharacteristic(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return this.k.checkAnyPropertyMatches(bluetoothGattCharacteristic, 2).andThen(this.f13423a.queue(this.d.provideReadCharacteristic(bluetoothGattCharacteristic))).firstOrError();
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Observable<Observable<byte[]>> setupIndication(@NonNull UUID uuid, @NonNull NotificationSetupMode notificationSetupMode) {
        return getCharacteristic(uuid).flatMapObservable(new e(notificationSetupMode));
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Observable<Observable<byte[]>> setupNotification(@NonNull UUID uuid, @NonNull NotificationSetupMode notificationSetupMode) {
        return getCharacteristic(uuid).flatMapObservable(new d(notificationSetupMode));
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Single<byte[]> writeCharacteristic(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr) {
        return this.k.checkAnyPropertyMatches(bluetoothGattCharacteristic, 76).andThen(this.f13423a.queue(this.d.provideWriteCharacteristic(bluetoothGattCharacteristic, bArr))).firstOrError();
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Single<byte[]> readDescriptor(@NonNull BluetoothGattDescriptor bluetoothGattDescriptor) {
        return this.f13423a.queue(this.d.provideReadDescriptor(bluetoothGattDescriptor)).firstOrError().map(new j(this));
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Completable writeDescriptor(@NonNull BluetoothGattDescriptor bluetoothGattDescriptor, @NonNull byte[] bArr) {
        return this.j.a(bluetoothGattDescriptor, bArr);
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Observable<Observable<byte[]>> setupIndication(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull NotificationSetupMode notificationSetupMode) {
        return this.k.checkAnyPropertyMatches(bluetoothGattCharacteristic, 32).andThen(this.h.x(bluetoothGattCharacteristic, notificationSetupMode, true));
    }

    @Override // com.polidea.rxandroidble2.RxBleConnection
    public Observable<Observable<byte[]>> setupNotification(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull NotificationSetupMode notificationSetupMode) {
        return this.k.checkAnyPropertyMatches(bluetoothGattCharacteristic, 16).andThen(this.h.x(bluetoothGattCharacteristic, notificationSetupMode, false));
    }
}
