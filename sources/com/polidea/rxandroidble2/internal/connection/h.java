package com.polidea.rxandroidble2.internal.connection;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.polidea.rxandroidble2.RxBleAdapterStateObservable;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.exceptions.BleGattException;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
@ConnectionScope
/* loaded from: classes12.dex */
public class h implements DisconnectionRouterOutput {

    /* renamed from: a  reason: collision with root package name */
    public final BehaviorRelay<BleException> f13433a;
    public final Observable<BleException> b;
    public final Observable<Object> c;

    /* loaded from: classes12.dex */
    public class a implements Consumer<Throwable> {
        public a(h hVar) {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) {
            RxBleLog.e(th, "Failed to monitor adapter state.", new Object[0]);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Consumer<BleException> {
        public b(h hVar) {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(BleException bleException) {
            RxBleLog.v("An exception received, indicating that the adapter has became unusable.", new Object[0]);
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Function<Boolean, BleException> {
        public final /* synthetic */ String h;

        public c(h hVar, String str) {
            this.h = str;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public BleException apply(Boolean bool) {
            return BleDisconnectedException.adapterDisabled(this.h);
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Action {
        public final /* synthetic */ Disposable h;

        public d(h hVar, Disposable disposable) {
            this.h = disposable;
        }

        @Override // io.reactivex.functions.Action
        public void run() {
            this.h.dispose();
        }
    }

    /* loaded from: classes12.dex */
    public class e implements Function<BleException, ObservableSource<?>> {
        public e(h hVar) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<?> apply(BleException bleException) {
            return Observable.error(bleException);
        }
    }

    /* loaded from: classes12.dex */
    public class f implements Predicate<Boolean> {
        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(Boolean bool) {
            return !bool.booleanValue();
        }
    }

    /* loaded from: classes12.dex */
    public class g implements Function<RxBleAdapterStateObservable.BleAdapterState, Boolean> {
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Boolean apply(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
            return Boolean.valueOf(bleAdapterState.isUsable());
        }
    }

    @Inject
    public h(@Named("mac-address") String str, RxBleAdapterWrapper rxBleAdapterWrapper, Observable<RxBleAdapterStateObservable.BleAdapterState> observable) {
        BehaviorRelay<BleException> create = BehaviorRelay.create();
        this.f13433a = create;
        Observable<BleException> autoConnect = create.firstElement().toObservable().doOnTerminate(new d(this, a(rxBleAdapterWrapper, observable).map(new c(this, str)).doOnNext(new b(this)).subscribe(create, new a(this)))).replay().autoConnect(0);
        this.b = autoConnect;
        this.c = autoConnect.flatMap(new e(this));
    }

    public static Observable<Boolean> a(RxBleAdapterWrapper rxBleAdapterWrapper, Observable<RxBleAdapterStateObservable.BleAdapterState> observable) {
        return observable.map(new g()).startWith((Observable<R>) Boolean.valueOf(rxBleAdapterWrapper.isBluetoothEnabled())).filter(new f());
    }

    @Override // com.polidea.rxandroidble2.internal.connection.DisconnectionRouterOutput
    public <T> Observable<T> asErrorOnlyObservable() {
        return (Observable<T>) this.c;
    }

    @Override // com.polidea.rxandroidble2.internal.connection.DisconnectionRouterOutput
    public Observable<BleException> asValueOnlyObservable() {
        return this.b;
    }

    public void b(BleDisconnectedException bleDisconnectedException) {
        this.f13433a.accept(bleDisconnectedException);
    }

    public void c(BleGattException bleGattException) {
        this.f13433a.accept(bleGattException);
    }
}
