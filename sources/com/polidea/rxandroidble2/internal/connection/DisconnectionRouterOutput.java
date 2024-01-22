package com.polidea.rxandroidble2.internal.connection;

import com.polidea.rxandroidble2.exceptions.BleException;
import io.reactivex.Observable;
/* loaded from: classes12.dex */
public interface DisconnectionRouterOutput {
    <T> Observable<T> asErrorOnlyObservable();

    Observable<BleException> asValueOnlyObservable();
}
