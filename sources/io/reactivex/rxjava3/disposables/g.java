package io.reactivex.rxjava3.disposables;

import io.reactivex.rxjava3.annotations.NonNull;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class g extends e<Subscription> {
    private static final long serialVersionUID = -707001650852963139L;

    public g(Subscription subscription) {
        super(subscription);
    }

    @Override // io.reactivex.rxjava3.disposables.e
    public void onDisposed(@NonNull Subscription subscription) {
        subscription.cancel();
    }
}
