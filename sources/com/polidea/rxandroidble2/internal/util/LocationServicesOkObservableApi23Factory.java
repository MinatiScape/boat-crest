package com.polidea.rxandroidble2.internal.util;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import bleshadow.javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;
import io.reactivex.schedulers.Schedulers;
@TargetApi(19)
/* loaded from: classes12.dex */
public class LocationServicesOkObservableApi23Factory {

    /* renamed from: a  reason: collision with root package name */
    public final Context f13512a;
    public final LocationServicesStatus b;

    /* loaded from: classes12.dex */
    public class a implements ObservableOnSubscribe<Boolean> {

        /* renamed from: com.polidea.rxandroidble2.internal.util.LocationServicesOkObservableApi23Factory$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public class C0715a extends BroadcastReceiver {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ ObservableEmitter f13514a;

            public C0715a(ObservableEmitter observableEmitter) {
                this.f13514a = observableEmitter;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                this.f13514a.onNext(Boolean.valueOf(LocationServicesOkObservableApi23Factory.this.b.isLocationProviderOk()));
            }
        }

        /* loaded from: classes12.dex */
        public class b implements Cancellable {
            public final /* synthetic */ BroadcastReceiver h;

            public b(BroadcastReceiver broadcastReceiver) {
                this.h = broadcastReceiver;
            }

            @Override // io.reactivex.functions.Cancellable
            public void cancel() {
                LocationServicesOkObservableApi23Factory.this.f13512a.unregisterReceiver(this.h);
            }
        }

        public a() {
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public void subscribe(ObservableEmitter<Boolean> observableEmitter) {
            boolean isLocationProviderOk = LocationServicesOkObservableApi23Factory.this.b.isLocationProviderOk();
            C0715a c0715a = new C0715a(observableEmitter);
            observableEmitter.onNext(Boolean.valueOf(isLocationProviderOk));
            LocationServicesOkObservableApi23Factory.this.f13512a.registerReceiver(c0715a, new IntentFilter("android.location.MODE_CHANGED"));
            observableEmitter.setCancellable(new b(c0715a));
        }
    }

    @Inject
    public LocationServicesOkObservableApi23Factory(Context context, LocationServicesStatus locationServicesStatus) {
        this.f13512a = context;
        this.b = locationServicesStatus;
    }

    public Observable<Boolean> get() {
        return Observable.create(new a()).distinctUntilChanged().subscribeOn(Schedulers.trampoline()).unsubscribeOn(Schedulers.trampoline());
    }
}
