package com.polidea.rxandroidble2.internal.connection;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.exceptions.BleGattException;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import io.reactivex.Observable;
import io.reactivex.disposables.SerialDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
@ConnectionScope
/* loaded from: classes12.dex */
public class k implements ConnectionSubscriptionWatcher, j, Consumer<Integer> {
    public Integer h;
    public final Observable<Integer> i;
    public final SerialDisposable j = new SerialDisposable();

    /* loaded from: classes12.dex */
    public class a implements Predicate<Throwable> {
        public a(k kVar) {
        }

        @Override // io.reactivex.functions.Predicate
        /* renamed from: a */
        public boolean test(Throwable th) {
            return (th instanceof BleGattException) && ((BleGattException) th).getBleGattOperationType() == BleGattOperationType.ON_MTU_CHANGED;
        }
    }

    @Inject
    public k(RxBleGattCallback rxBleGattCallback, @Named("GATT_MTU_MINIMUM") int i) {
        this.i = rxBleGattCallback.getOnMtuChanged().retry(new a(this));
        this.h = Integer.valueOf(i);
    }

    @Override // io.reactivex.functions.Consumer
    /* renamed from: a */
    public void accept(Integer num) {
        this.h = num;
    }

    @Override // com.polidea.rxandroidble2.internal.connection.j
    public int getMtu() {
        return this.h.intValue();
    }

    @Override // com.polidea.rxandroidble2.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionSubscribed() {
        this.j.set(this.i.subscribe(this, Functions.emptyConsumer()));
    }

    @Override // com.polidea.rxandroidble2.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionUnsubscribed() {
        this.j.dispose();
    }
}
