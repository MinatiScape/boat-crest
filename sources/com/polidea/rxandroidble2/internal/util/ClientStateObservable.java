package com.polidea.rxandroidble2.internal.util;

import androidx.annotation.NonNull;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.RxBleAdapterStateObservable;
import com.polidea.rxandroidble2.RxBleClient;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class ClientStateObservable extends Observable<RxBleClient.State> {
    public final RxBleAdapterWrapper h;
    public final Observable<RxBleAdapterStateObservable.BleAdapterState> i;
    public final Observable<Boolean> j;
    public final LocationServicesStatus k;
    public final Scheduler l;

    /* loaded from: classes12.dex */
    public class a implements Function<Long, Boolean> {
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Boolean apply(Long l) {
            return Boolean.valueOf(l.longValue() == 0);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Predicate<Long> {
        public final /* synthetic */ LocationServicesStatus h;

        public b(LocationServicesStatus locationServicesStatus) {
            this.h = locationServicesStatus;
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(Long l) {
            return !this.h.isLocationPermissionOk();
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Function<RxBleAdapterStateObservable.BleAdapterState, Observable<RxBleClient.State>> {
        public final /* synthetic */ Observable h;

        /* loaded from: classes12.dex */
        public class a implements Function<Boolean, RxBleClient.State> {
            public a(c cVar) {
            }

            @Override // io.reactivex.functions.Function
            /* renamed from: a */
            public RxBleClient.State apply(Boolean bool) {
                return bool.booleanValue() ? RxBleClient.State.READY : RxBleClient.State.LOCATION_SERVICES_NOT_ENABLED;
            }
        }

        public c(Observable observable) {
            this.h = observable;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Observable<RxBleClient.State> apply(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
            if (bleAdapterState != RxBleAdapterStateObservable.BleAdapterState.STATE_ON) {
                return Observable.just(RxBleClient.State.BLUETOOTH_NOT_ENABLED);
            }
            return this.h.map(new a(this));
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Function<Boolean, Observable<RxBleClient.State>> {
        public d() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Observable<RxBleClient.State> apply(Boolean bool) {
            ClientStateObservable clientStateObservable = ClientStateObservable.this;
            Observable<RxBleClient.State> distinctUntilChanged = ClientStateObservable.d(clientStateObservable.h, clientStateObservable.i, clientStateObservable.j).distinctUntilChanged();
            return bool.booleanValue() ? distinctUntilChanged.skip(1L) : distinctUntilChanged;
        }
    }

    @Inject
    public ClientStateObservable(RxBleAdapterWrapper rxBleAdapterWrapper, Observable<RxBleAdapterStateObservable.BleAdapterState> observable, @Named("location-ok-boolean-observable") Observable<Boolean> observable2, LocationServicesStatus locationServicesStatus, @Named("timeout") Scheduler scheduler) {
        this.h = rxBleAdapterWrapper;
        this.i = observable;
        this.j = observable2;
        this.k = locationServicesStatus;
        this.l = scheduler;
    }

    @NonNull
    public static Observable<RxBleClient.State> d(RxBleAdapterWrapper rxBleAdapterWrapper, Observable<RxBleAdapterStateObservable.BleAdapterState> observable, Observable<Boolean> observable2) {
        RxBleAdapterStateObservable.BleAdapterState bleAdapterState;
        if (rxBleAdapterWrapper.isBluetoothEnabled()) {
            bleAdapterState = RxBleAdapterStateObservable.BleAdapterState.STATE_ON;
        } else {
            bleAdapterState = RxBleAdapterStateObservable.BleAdapterState.STATE_OFF;
        }
        return observable.startWith((Observable<RxBleAdapterStateObservable.BleAdapterState>) bleAdapterState).switchMap(new c(observable2));
    }

    @NonNull
    public static Single<Boolean> e(LocationServicesStatus locationServicesStatus, Scheduler scheduler) {
        return Observable.interval(0L, 1L, TimeUnit.SECONDS, scheduler).takeWhile(new b(locationServicesStatus)).count().map(new a());
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super RxBleClient.State> observer) {
        if (!this.h.hasBluetoothAdapter()) {
            observer.onSubscribe(Disposables.empty());
            observer.onComplete();
            return;
        }
        e(this.k, this.l).flatMapObservable(new d()).subscribe(observer);
    }
}
