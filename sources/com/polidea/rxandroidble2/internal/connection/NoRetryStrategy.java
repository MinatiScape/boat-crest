package com.polidea.rxandroidble2.internal.connection;

import com.polidea.rxandroidble2.RxBleConnection;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
/* loaded from: classes12.dex */
public class NoRetryStrategy implements RxBleConnection.WriteOperationRetryStrategy {

    /* loaded from: classes12.dex */
    public class a implements Function<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure, Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure>> {
        public a(NoRetryStrategy noRetryStrategy) {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> apply(RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure longWriteFailure) {
            return Observable.error(longWriteFailure.getCause());
        }
    }

    @Override // io.reactivex.ObservableTransformer
    /* renamed from: apply */
    public ObservableSource<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> apply2(Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> observable) {
        return observable.flatMap(new a(this));
    }
}
