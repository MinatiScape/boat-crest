package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.annotations.NonNull;
@FunctionalInterface
/* loaded from: classes12.dex */
public interface CompletableOperator {
    @NonNull
    CompletableObserver apply(@NonNull CompletableObserver completableObserver) throws Throwable;
}
