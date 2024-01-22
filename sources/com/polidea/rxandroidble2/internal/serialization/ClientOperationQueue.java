package com.polidea.rxandroidble2.internal.serialization;

import com.polidea.rxandroidble2.internal.operations.Operation;
import io.reactivex.Observable;
/* loaded from: classes12.dex */
public interface ClientOperationQueue {
    <T> Observable<T> queue(Operation<T> operation);
}
