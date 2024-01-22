package io.reactivex;

import io.reactivex.annotations.NonNull;
/* loaded from: classes12.dex */
public interface MaybeTransformer<Upstream, Downstream> {
    @NonNull
    MaybeSource<Downstream> apply(@NonNull Maybe<Upstream> maybe);
}