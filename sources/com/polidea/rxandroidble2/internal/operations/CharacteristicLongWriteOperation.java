package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.DeadObjectException;
import androidx.annotation.NonNull;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.exceptions.BleGattCallbackTimeoutException;
import com.polidea.rxandroidble2.exceptions.BleGattCannotStartException;
import com.polidea.rxandroidble2.exceptions.BleGattCharacteristicException;
import com.polidea.rxandroidble2.exceptions.BleGattException;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.QueueOperation;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.connection.PayloadSizeLimitProvider;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble2.internal.util.ByteAssociation;
import com.polidea.rxandroidble2.internal.util.DisposableUtil;
import com.polidea.rxandroidble2.internal.util.QueueReleasingEmitterWrapper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import java.nio.ByteBuffer;
import java.util.UUID;
/* loaded from: classes12.dex */
public class CharacteristicLongWriteOperation extends QueueOperation<byte[]> {
    public final BluetoothGatt h;
    public final RxBleGattCallback i;
    public final Scheduler j;
    public final TimeoutConfiguration k;
    public final BluetoothGattCharacteristic l;
    public final PayloadSizeLimitProvider m;
    public final RxBleConnection.WriteOperationAckStrategy n;
    public final RxBleConnection.WriteOperationRetryStrategy o;
    public final byte[] p;
    public byte[] q;

    /* loaded from: classes12.dex */
    public class a implements g {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ByteBuffer f13445a;
        public final /* synthetic */ int b;

        public a(CharacteristicLongWriteOperation characteristicLongWriteOperation, ByteBuffer byteBuffer, int i) {
            this.f13445a = byteBuffer;
            this.b = i;
        }

        @Override // com.polidea.rxandroidble2.internal.operations.CharacteristicLongWriteOperation.g
        public int get() {
            return ((int) Math.ceil(this.f13445a.position() / this.b)) - 1;
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Observer<ByteAssociation<UUID>> {
        public final /* synthetic */ QueueReleasingEmitterWrapper h;

        public b(QueueReleasingEmitterWrapper queueReleasingEmitterWrapper) {
            this.h = queueReleasingEmitterWrapper;
        }

        @Override // io.reactivex.Observer
        /* renamed from: a */
        public void onNext(ByteAssociation<UUID> byteAssociation) {
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.h.onNext(CharacteristicLongWriteOperation.this.p);
            this.h.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
        }
    }

    /* loaded from: classes12.dex */
    public class c implements ObservableOnSubscribe<ByteAssociation<UUID>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Observable f13446a;
        public final /* synthetic */ ByteBuffer b;
        public final /* synthetic */ int c;
        public final /* synthetic */ g d;

        public c(Observable observable, ByteBuffer byteBuffer, int i, g gVar) {
            this.f13446a = observable;
            this.b = byteBuffer;
            this.c = i;
            this.d = gVar;
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public void subscribe(ObservableEmitter<ByteAssociation<UUID>> observableEmitter) {
            observableEmitter.setDisposable((DisposableObserver) this.f13446a.subscribeWith(DisposableUtil.disposableObserverFromEmitter(observableEmitter)));
            try {
                CharacteristicLongWriteOperation.this.e(CharacteristicLongWriteOperation.this.c(this.b, this.c), this.d);
            } catch (Throwable th) {
                observableEmitter.onError(th);
            }
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Predicate<ByteAssociation<UUID>> {
        public final /* synthetic */ BluetoothGattCharacteristic h;

        public d(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            this.h = bluetoothGattCharacteristic;
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(ByteAssociation<UUID> byteAssociation) {
            return byteAssociation.first.equals(this.h.getUuid());
        }
    }

    /* loaded from: classes12.dex */
    public class e implements Function<Observable<?>, ObservableSource<?>> {
        public final /* synthetic */ QueueReleasingEmitterWrapper h;
        public final /* synthetic */ ByteBuffer i;
        public final /* synthetic */ RxBleConnection.WriteOperationAckStrategy j;

        /* loaded from: classes12.dex */
        public class a implements Predicate<Boolean> {
            public a(e eVar) {
            }

            @Override // io.reactivex.functions.Predicate
            /* renamed from: a */
            public boolean test(Boolean bool) {
                return bool.booleanValue();
            }
        }

        /* loaded from: classes12.dex */
        public class b implements Function<Object, Boolean> {
            public final /* synthetic */ ByteBuffer h;

            public b(e eVar, ByteBuffer byteBuffer) {
                this.h = byteBuffer;
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public Boolean apply(Object obj) {
                return Boolean.valueOf(this.h.hasRemaining());
            }
        }

        /* loaded from: classes12.dex */
        public class c implements Predicate<Object> {
            public final /* synthetic */ QueueReleasingEmitterWrapper h;

            public c(e eVar, QueueReleasingEmitterWrapper queueReleasingEmitterWrapper) {
                this.h = queueReleasingEmitterWrapper;
            }

            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                return !this.h.isWrappedEmitterUnsubscribed();
            }
        }

        public e(QueueReleasingEmitterWrapper queueReleasingEmitterWrapper, ByteBuffer byteBuffer, RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy) {
            this.h = queueReleasingEmitterWrapper;
            this.i = byteBuffer;
            this.j = writeOperationAckStrategy;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<?> apply(Observable<?> observable) {
            return observable.takeWhile(c(this.h)).map(b(this.i)).compose(this.j).takeWhile(new a(this));
        }

        @NonNull
        public final Function<Object, Boolean> b(ByteBuffer byteBuffer) {
            return new b(this, byteBuffer);
        }

        @NonNull
        public final Predicate<Object> c(QueueReleasingEmitterWrapper<byte[]> queueReleasingEmitterWrapper) {
            return new c(this, queueReleasingEmitterWrapper);
        }
    }

    /* loaded from: classes12.dex */
    public class f implements Function<Observable<Throwable>, ObservableSource<?>> {
        public final /* synthetic */ RxBleConnection.WriteOperationRetryStrategy h;
        public final /* synthetic */ g i;
        public final /* synthetic */ int j;
        public final /* synthetic */ ByteBuffer k;

        /* loaded from: classes12.dex */
        public class a implements Function<Throwable, Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure>> {
            public a() {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> apply(Throwable th) {
                if (!(th instanceof BleGattCharacteristicException) && !(th instanceof BleGattCannotStartException)) {
                    return Observable.error(th);
                }
                return Observable.just(new RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure(f.this.i.get(), (BleGattException) th));
            }
        }

        /* loaded from: classes12.dex */
        public class b implements Consumer<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> {
            public b() {
            }

            @Override // io.reactivex.functions.Consumer
            /* renamed from: a */
            public void accept(RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure longWriteFailure) {
                int batchIndex = longWriteFailure.getBatchIndex();
                f fVar = f.this;
                fVar.k.position(batchIndex * fVar.j);
            }
        }

        public f(RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy, g gVar, int i, ByteBuffer byteBuffer) {
            this.h = writeOperationRetryStrategy;
            this.i = gVar;
            this.j = i;
            this.k = byteBuffer;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<?> apply(Observable<Throwable> observable) {
            return observable.flatMap(c()).doOnNext(b()).compose(this.h);
        }

        @NonNull
        public final Consumer<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> b() {
            return new b();
        }

        @NonNull
        public final Function<Throwable, Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure>> c() {
            return new a();
        }
    }

    /* loaded from: classes12.dex */
    public interface g {
        int get();
    }

    public CharacteristicLongWriteOperation(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, @Named("bluetooth_interaction") Scheduler scheduler, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, BluetoothGattCharacteristic bluetoothGattCharacteristic, PayloadSizeLimitProvider payloadSizeLimitProvider, RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy, RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy, byte[] bArr) {
        this.h = bluetoothGatt;
        this.i = rxBleGattCallback;
        this.j = scheduler;
        this.k = timeoutConfiguration;
        this.l = bluetoothGattCharacteristic;
        this.m = payloadSizeLimitProvider;
        this.n = writeOperationAckStrategy;
        this.o = writeOperationRetryStrategy;
        this.p = bArr;
    }

    public static Function<Observable<?>, ObservableSource<?>> a(RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy, ByteBuffer byteBuffer, QueueReleasingEmitterWrapper<byte[]> queueReleasingEmitterWrapper) {
        return new e(queueReleasingEmitterWrapper, byteBuffer, writeOperationAckStrategy);
    }

    public static Function<Observable<Throwable>, ObservableSource<?>> b(RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy, ByteBuffer byteBuffer, int i, g gVar) {
        return new f(writeOperationRetryStrategy, gVar, i, byteBuffer);
    }

    public static Predicate<ByteAssociation<UUID>> f(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new d(bluetoothGattCharacteristic);
    }

    public byte[] c(ByteBuffer byteBuffer, int i) {
        int min = Math.min(byteBuffer.remaining(), i);
        byte[] bArr = this.q;
        if (bArr == null || bArr.length != min) {
            this.q = new byte[min];
        }
        byteBuffer.get(this.q);
        return this.q;
    }

    @NonNull
    public final Observable<ByteAssociation<UUID>> d(int i, ByteBuffer byteBuffer, g gVar) {
        return Observable.create(new c(this.i.getOnCharacteristicWrite(), byteBuffer, i, gVar));
    }

    public void e(byte[] bArr, g gVar) {
        if (RxBleLog.isAtLeast(3)) {
            RxBleLog.d("Writing batch #%04d: %s", Integer.valueOf(gVar.get()), LoggerUtil.bytesToHex(bArr));
        }
        this.l.setValue(bArr);
        if (!this.h.writeCharacteristic(this.l)) {
            throw new BleGattCannotStartException(this.h, BleGattOperationType.CHARACTERISTIC_LONG_WRITE);
        }
    }

    @Override // com.polidea.rxandroidble2.internal.QueueOperation
    public void protectedRun(ObservableEmitter<byte[]> observableEmitter, QueueReleaseInterface queueReleaseInterface) {
        int payloadSizeLimit = this.m.getPayloadSizeLimit();
        if (payloadSizeLimit > 0) {
            Observable error = Observable.error(new BleGattCallbackTimeoutException(this.h, BleGattOperationType.CHARACTERISTIC_LONG_WRITE));
            ByteBuffer wrap = ByteBuffer.wrap(this.p);
            QueueReleasingEmitterWrapper queueReleasingEmitterWrapper = new QueueReleasingEmitterWrapper(observableEmitter, queueReleaseInterface);
            a aVar = new a(this, wrap, payloadSizeLimit);
            Observable<ByteAssociation<UUID>> take = d(payloadSizeLimit, wrap, aVar).subscribeOn(this.j).filter(f(this.l)).take(1L);
            TimeoutConfiguration timeoutConfiguration = this.k;
            take.timeout(timeoutConfiguration.timeout, timeoutConfiguration.timeoutTimeUnit, timeoutConfiguration.timeoutScheduler, error).repeatWhen(a(this.n, wrap, queueReleasingEmitterWrapper)).retryWhen(b(this.o, wrap, payloadSizeLimit, aVar)).subscribe(new b(queueReleasingEmitterWrapper));
            return;
        }
        throw new IllegalArgumentException("batchSizeProvider value must be greater than zero (now: " + payloadSizeLimit + ")");
    }

    @Override // com.polidea.rxandroidble2.internal.QueueOperation
    public BleException provideException(DeadObjectException deadObjectException) {
        return new BleDisconnectedException(deadObjectException, this.h.getDevice().getAddress(), -1);
    }

    public String toString() {
        return "CharacteristicLongWriteOperation{" + LoggerUtil.commonMacMessage(this.h) + ", characteristic=" + LoggerUtil.wrap(this.l, false) + ", maxBatchSize=" + this.m.getPayloadSizeLimit() + '}';
    }
}
