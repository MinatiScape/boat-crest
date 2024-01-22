package com.polidea.rxandroidble2.internal.connection;

import com.polidea.rxandroidble2.RxBleConnection;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
/* loaded from: classes12.dex */
public class ImmediateSerializedBatchAckStrategy implements RxBleConnection.WriteOperationAckStrategy {
    @Override // io.reactivex.ObservableTransformer
    /* renamed from: apply */
    public ObservableSource<Boolean> apply2(Observable<Boolean> observable) {
        return observable;
    }
}
