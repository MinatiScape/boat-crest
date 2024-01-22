package io.reactivex.disposables;

import io.reactivex.annotations.NonNull;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class e extends c<Subscription> {
    private static final long serialVersionUID = -707001650852963139L;

    public e(Subscription subscription) {
        super(subscription);
    }

    @Override // io.reactivex.disposables.c
    public void onDisposed(@NonNull Subscription subscription) {
        subscription.cancel();
    }
}
