package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import androidx.annotation.NonNull;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.NotificationSetupMode;
import com.polidea.rxandroidble2.exceptions.BleCannotSetCharacteristicNotificationException;
import com.polidea.rxandroidble2.exceptions.BleConflictingNotificationAlreadySetException;
import com.polidea.rxandroidble2.internal.util.ActiveCharacteristicNotification;
import com.polidea.rxandroidble2.internal.util.CharacteristicChangedEvent;
import com.polidea.rxandroidble2.internal.util.CharacteristicNotificationId;
import com.polidea.rxandroidble2.internal.util.ObservableUtil;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.subjects.PublishSubject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
@ConnectionScope
/* loaded from: classes12.dex */
public class w {
    public static final UUID h = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f13438a;
    public final byte[] b;
    public final byte[] c;
    public final BluetoothGatt d;
    public final RxBleGattCallback e;
    public final f f;
    public final Map<CharacteristicNotificationId, ActiveCharacteristicNotification> g = new HashMap();

    /* loaded from: classes12.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f13439a;

        static {
            int[] iArr = new int[NotificationSetupMode.values().length];
            f13439a = iArr;
            try {
                iArr[NotificationSetupMode.COMPAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13439a[NotificationSetupMode.QUICK_SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13439a[NotificationSetupMode.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Inject
    public w(@Named("enable-notification-value") byte[] bArr, @Named("enable-indication-value") byte[] bArr2, @Named("disable-notification-value") byte[] bArr3, BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, f fVar) {
        this.f13438a = bArr;
        this.b = bArr2;
        this.c = bArr3;
        this.d = bluetoothGatt;
        this.e = rxBleGattCallback;
        this.f = fVar;
    }

    public static /* synthetic */ boolean k(CharacteristicNotificationId characteristicNotificationId, CharacteristicChangedEvent characteristicChangedEvent) throws Exception {
        return characteristicChangedEvent.equals(characteristicNotificationId);
    }

    public static /* synthetic */ void m(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) throws Exception {
        if (!bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z)) {
            throw new BleCannotSetCharacteristicNotificationException(bluetoothGattCharacteristic, 1, null);
        }
    }

    public static /* synthetic */ Observable n(Completable completable, Observable observable) throws Exception {
        return observable.mergeWith(completable.onErrorComplete());
    }

    public static /* synthetic */ ObservableSource o(NotificationSetupMode notificationSetupMode, BluetoothGattCharacteristic bluetoothGattCharacteristic, f fVar, byte[] bArr, Observable observable) {
        int i = a.f13439a[notificationSetupMode.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return z(bluetoothGattCharacteristic, fVar, bArr).andThen(observable);
            }
            final Completable ignoreElements = z(bluetoothGattCharacteristic, fVar, bArr).toObservable().publish().autoConnect(2).ignoreElements();
            return observable.mergeWith(ignoreElements).map(new Function() { // from class: com.polidea.rxandroidble2.internal.connection.r
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    Observable n;
                    n = w.n(Completable.this, (Observable) obj);
                    return n;
                }
            });
        }
        return observable;
    }

    public static /* synthetic */ Observable p(PublishSubject publishSubject, Observable observable) throws Exception {
        return Observable.amb(Arrays.asList(publishSubject.cast(byte[].class), observable.takeUntil(publishSubject)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(PublishSubject publishSubject, CharacteristicNotificationId characteristicNotificationId, BluetoothGattCharacteristic bluetoothGattCharacteristic, NotificationSetupMode notificationSetupMode) throws Exception {
        publishSubject.onComplete();
        synchronized (this.g) {
            this.g.remove(characteristicNotificationId);
        }
        v(this.d, bluetoothGattCharacteristic, false).compose(y(this.f, bluetoothGattCharacteristic, this.c, notificationSetupMode)).subscribe(Functions.EMPTY_ACTION, Functions.emptyConsumer());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ObservableSource r(final BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, final NotificationSetupMode notificationSetupMode) throws Exception {
        synchronized (this.g) {
            final CharacteristicNotificationId characteristicNotificationId = new CharacteristicNotificationId(bluetoothGattCharacteristic.getUuid(), Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()));
            ActiveCharacteristicNotification activeCharacteristicNotification = this.g.get(characteristicNotificationId);
            boolean z2 = true;
            if (activeCharacteristicNotification != null) {
                if (activeCharacteristicNotification.isIndication == z) {
                    return activeCharacteristicNotification.notificationObservable;
                }
                UUID uuid = bluetoothGattCharacteristic.getUuid();
                if (z) {
                    z2 = false;
                }
                return Observable.error(new BleConflictingNotificationAlreadySetException(uuid, z2));
            }
            byte[] bArr = z ? this.b : this.f13438a;
            final PublishSubject create = PublishSubject.create();
            Observable refCount = v(this.d, bluetoothGattCharacteristic, true).andThen(ObservableUtil.justOnNext(u(this.e, characteristicNotificationId))).compose(w(this.f, bluetoothGattCharacteristic, bArr, notificationSetupMode)).map(new Function() { // from class: com.polidea.rxandroidble2.internal.connection.s
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    Observable p;
                    p = w.p(PublishSubject.this, (Observable) obj);
                    return p;
                }
            }).doFinally(new Action() { // from class: com.polidea.rxandroidble2.internal.connection.p
                @Override // io.reactivex.functions.Action
                public final void run() {
                    w.this.q(create, characteristicNotificationId, bluetoothGattCharacteristic, notificationSetupMode);
                }
            }).mergeWith(this.e.observeDisconnect()).replay(1).refCount();
            this.g.put(characteristicNotificationId, new ActiveCharacteristicNotification(refCount, z));
            return refCount;
        }
    }

    public static /* synthetic */ CompletableSource s(NotificationSetupMode notificationSetupMode, BluetoothGattCharacteristic bluetoothGattCharacteristic, f fVar, byte[] bArr, Completable completable) {
        return notificationSetupMode == NotificationSetupMode.COMPAT ? completable : completable.andThen(z(bluetoothGattCharacteristic, fVar, bArr));
    }

    public static /* synthetic */ CompletableSource t(BluetoothGattCharacteristic bluetoothGattCharacteristic, Throwable th) throws Exception {
        return Completable.error(new BleCannotSetCharacteristicNotificationException(bluetoothGattCharacteristic, 3, th));
    }

    @NonNull
    public static Observable<byte[]> u(RxBleGattCallback rxBleGattCallback, final CharacteristicNotificationId characteristicNotificationId) {
        return rxBleGattCallback.getOnCharacteristicChanged().filter(new Predicate() { // from class: com.polidea.rxandroidble2.internal.connection.u
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Object obj) {
                boolean k;
                k = w.k(CharacteristicNotificationId.this, (CharacteristicChangedEvent) obj);
                return k;
            }
        }).map(new Function() { // from class: com.polidea.rxandroidble2.internal.connection.t
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                byte[] bArr;
                bArr = ((CharacteristicChangedEvent) obj).data;
                return bArr;
            }
        });
    }

    @NonNull
    public static Completable v(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final boolean z) {
        return Completable.fromAction(new Action() { // from class: com.polidea.rxandroidble2.internal.connection.o
            @Override // io.reactivex.functions.Action
            public final void run() {
                w.m(bluetoothGatt, bluetoothGattCharacteristic, z);
            }
        });
    }

    @NonNull
    public static ObservableTransformer<Observable<byte[]>, Observable<byte[]>> w(final f fVar, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final byte[] bArr, final NotificationSetupMode notificationSetupMode) {
        return new ObservableTransformer() { // from class: com.polidea.rxandroidble2.internal.connection.n
            @Override // io.reactivex.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                ObservableSource o;
                o = w.o(NotificationSetupMode.this, bluetoothGattCharacteristic, fVar, bArr, observable);
                return o;
            }
        };
    }

    @NonNull
    public static CompletableTransformer y(final f fVar, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final byte[] bArr, final NotificationSetupMode notificationSetupMode) {
        return new CompletableTransformer() { // from class: com.polidea.rxandroidble2.internal.connection.m
            @Override // io.reactivex.CompletableTransformer
            public final CompletableSource apply(Completable completable) {
                CompletableSource s;
                s = w.s(NotificationSetupMode.this, bluetoothGattCharacteristic, fVar, bArr, completable);
                return s;
            }
        };
    }

    @NonNull
    public static Completable z(final BluetoothGattCharacteristic bluetoothGattCharacteristic, f fVar, byte[] bArr) {
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(h);
        if (descriptor == null) {
            return Completable.error(new BleCannotSetCharacteristicNotificationException(bluetoothGattCharacteristic, 2, null));
        }
        return fVar.a(descriptor, bArr).onErrorResumeNext(new Function() { // from class: com.polidea.rxandroidble2.internal.connection.q
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                CompletableSource t;
                t = w.t(bluetoothGattCharacteristic, (Throwable) obj);
                return t;
            }
        });
    }

    public Observable<Observable<byte[]>> x(@NonNull final BluetoothGattCharacteristic bluetoothGattCharacteristic, final NotificationSetupMode notificationSetupMode, final boolean z) {
        return Observable.defer(new Callable() { // from class: com.polidea.rxandroidble2.internal.connection.v
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ObservableSource r;
                r = w.this.r(bluetoothGattCharacteristic, z, notificationSetupMode);
                return r;
            }
        });
    }
}
