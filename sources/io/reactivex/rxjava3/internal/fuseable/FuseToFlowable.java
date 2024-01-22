package io.reactivex.rxjava3.internal.fuseable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
/* loaded from: classes12.dex */
public interface FuseToFlowable<T> {
    @NonNull
    Flowable<T> fuseToFlowable();
}
