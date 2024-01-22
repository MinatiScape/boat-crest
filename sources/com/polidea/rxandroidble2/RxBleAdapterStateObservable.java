package com.polidea.rxandroidble2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.RxBleLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.functions.Cancellable;
import io.reactivex.schedulers.Schedulers;
/* loaded from: classes9.dex */
public class RxBleAdapterStateObservable extends Observable<BleAdapterState> {
    @NonNull
    public final Observable<BleAdapterState> h;

    /* loaded from: classes9.dex */
    public static class BleAdapterState {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f13371a;
        public final String b;
        public static final BleAdapterState STATE_ON = new BleAdapterState(true, "STATE_ON");
        public static final BleAdapterState STATE_OFF = new BleAdapterState(false, "STATE_OFF");
        public static final BleAdapterState STATE_TURNING_ON = new BleAdapterState(false, "STATE_TURNING_ON");
        public static final BleAdapterState STATE_TURNING_OFF = new BleAdapterState(false, "STATE_TURNING_OFF");

        public BleAdapterState(boolean z, String str) {
            this.f13371a = z;
            this.b = str;
        }

        public boolean isUsable() {
            return this.f13371a;
        }

        @NonNull
        public String toString() {
            return this.b;
        }
    }

    /* loaded from: classes9.dex */
    public class a implements ObservableOnSubscribe<BleAdapterState> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f13372a;

        /* renamed from: com.polidea.rxandroidble2.RxBleAdapterStateObservable$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public class C0710a extends BroadcastReceiver {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ ObservableEmitter f13373a;

            public C0710a(a aVar, ObservableEmitter observableEmitter) {
                this.f13373a = observableEmitter;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                BleAdapterState d = RxBleAdapterStateObservable.d(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1));
                RxBleLog.i("Adapter state changed: %s", d);
                this.f13373a.onNext(d);
            }
        }

        /* loaded from: classes9.dex */
        public class b implements Cancellable {
            public final /* synthetic */ BroadcastReceiver h;

            public b(BroadcastReceiver broadcastReceiver) {
                this.h = broadcastReceiver;
            }

            @Override // io.reactivex.functions.Cancellable
            public void cancel() {
                a.this.f13372a.unregisterReceiver(this.h);
            }
        }

        public a(RxBleAdapterStateObservable rxBleAdapterStateObservable, Context context) {
            this.f13372a = context;
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public void subscribe(ObservableEmitter<BleAdapterState> observableEmitter) {
            C0710a c0710a = new C0710a(this, observableEmitter);
            this.f13372a.registerReceiver(c0710a, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            observableEmitter.setCancellable(new b(c0710a));
        }
    }

    @Inject
    public RxBleAdapterStateObservable(@NonNull Context context) {
        this.h = Observable.create(new a(this, context)).subscribeOn(Schedulers.trampoline()).unsubscribeOn(Schedulers.trampoline()).share();
    }

    public static BleAdapterState d(int i) {
        switch (i) {
            case 11:
                return BleAdapterState.STATE_TURNING_ON;
            case 12:
                return BleAdapterState.STATE_ON;
            case 13:
                return BleAdapterState.STATE_TURNING_OFF;
            default:
                return BleAdapterState.STATE_OFF;
        }
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super BleAdapterState> observer) {
        this.h.subscribe(observer);
    }
}
