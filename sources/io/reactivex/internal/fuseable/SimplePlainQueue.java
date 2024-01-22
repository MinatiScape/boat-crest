package io.reactivex.internal.fuseable;

import io.reactivex.annotations.Nullable;
/* loaded from: classes12.dex */
public interface SimplePlainQueue<T> extends SimpleQueue<T> {
    @Override // io.reactivex.internal.fuseable.SimpleQueue
    @Nullable
    T poll();
}
