package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import androidx.annotation.NonNull;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.internal.operations.OperationsProvider;
import com.polidea.rxandroidble2.internal.operations.TimeoutConfiguration;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
@ConnectionScope
/* loaded from: classes12.dex */
public class x {

    /* renamed from: a  reason: collision with root package name */
    public final ConnectionOperationQueue f13440a;
    public final BluetoothGatt b;
    public final OperationsProvider c;
    public Single<RxBleDeviceServices> d;
    public final Subject<TimeoutConfiguration> e = BehaviorSubject.create().toSerialized();
    public boolean f = false;

    /* loaded from: classes12.dex */
    public class a implements Consumer<Disposable> {
        public final /* synthetic */ long h;
        public final /* synthetic */ TimeUnit i;

        public a(long j, TimeUnit timeUnit) {
            this.h = j;
            this.i = timeUnit;
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Disposable disposable) {
            x.this.e.onNext(new TimeoutConfiguration(this.h, this.i, Schedulers.computation()));
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Action {
        public b() {
        }

        @Override // io.reactivex.functions.Action
        public void run() {
            x.this.f = true;
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Action {
        public c() {
        }

        @Override // io.reactivex.functions.Action
        public void run() {
            x.this.d();
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Function<List<BluetoothGattService>, RxBleDeviceServices> {
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public RxBleDeviceServices apply(List<BluetoothGattService> list) {
            return new RxBleDeviceServices(list);
        }
    }

    /* loaded from: classes12.dex */
    public class e implements Predicate<List<BluetoothGattService>> {
        public e(x xVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(List<BluetoothGattService> list) {
            return list.size() > 0;
        }
    }

    /* loaded from: classes12.dex */
    public class f implements Callable<List<BluetoothGattService>> {
        public f() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<BluetoothGattService> call() {
            return x.this.b.getServices();
        }
    }

    /* loaded from: classes12.dex */
    public class g implements Function<TimeoutConfiguration, Single<RxBleDeviceServices>> {
        public g() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Single<RxBleDeviceServices> apply(TimeoutConfiguration timeoutConfiguration) {
            return x.this.f13440a.queue(x.this.c.provideServiceDiscoveryOperation(timeoutConfiguration.timeout, timeoutConfiguration.timeoutTimeUnit)).firstOrError();
        }
    }

    @Inject
    public x(ConnectionOperationQueue connectionOperationQueue, BluetoothGatt bluetoothGatt, OperationsProvider operationsProvider) {
        this.f13440a = connectionOperationQueue;
        this.b = bluetoothGatt;
        this.c = operationsProvider;
        d();
    }

    @NonNull
    public static Function<List<BluetoothGattService>, RxBleDeviceServices> f() {
        return new d();
    }

    public Single<RxBleDeviceServices> a(long j, TimeUnit timeUnit) {
        if (this.f) {
            return this.d;
        }
        return this.d.doOnSubscribe(new a(j, timeUnit));
    }

    public final Maybe<List<BluetoothGattService>> b() {
        return Single.fromCallable(new f()).filter(new e(this));
    }

    @NonNull
    public final Single<TimeoutConfiguration> c() {
        return this.e.firstOrError();
    }

    public void d() {
        this.f = false;
        this.d = b().map(f()).switchIfEmpty(c().flatMap(e())).doOnSuccess(Functions.actionConsumer(new b())).doOnError(Functions.actionConsumer(new c())).cache();
    }

    @NonNull
    public final Function<TimeoutConfiguration, Single<RxBleDeviceServices>> e() {
        return new g();
    }
}
