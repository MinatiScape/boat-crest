package com.polidea.rxandroidble2.utils;

import com.polidea.rxandroidble2.RxBleConnection;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Action;
import java.util.concurrent.atomic.AtomicReference;
@Deprecated
/* loaded from: classes12.dex */
public class ConnectionSharingAdapter implements ObservableTransformer<RxBleConnection, RxBleConnection> {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReference<Observable<RxBleConnection>> f13529a = new AtomicReference<>();

    /* loaded from: classes12.dex */
    public class a implements Action {
        public a() {
        }

        @Override // io.reactivex.functions.Action
        public void run() {
            ConnectionSharingAdapter.this.f13529a.set(null);
        }
    }

    @Override // io.reactivex.ObservableTransformer
    public ObservableSource<RxBleConnection> apply(Observable<RxBleConnection> observable) {
        synchronized (this.f13529a) {
            Observable<RxBleConnection> observable2 = this.f13529a.get();
            if (observable2 != null) {
                return observable2;
            }
            Observable<RxBleConnection> refCount = observable.doFinally(new a()).replay(1).refCount();
            this.f13529a.set(refCount);
            return refCount;
        }
    }
}
