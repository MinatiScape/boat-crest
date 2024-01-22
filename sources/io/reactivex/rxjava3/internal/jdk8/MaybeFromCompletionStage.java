package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.jdk8.FlowableFromCompletionStage;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
/* loaded from: classes12.dex */
public final class MaybeFromCompletionStage<T> extends Maybe<T> {
    public final CompletionStage<T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Disposable, BiConsumer<T, Throwable> {
        public final MaybeObserver<? super T> h;
        public final FlowableFromCompletionStage.a<T> i;

        public a(MaybeObserver<? super T> maybeObserver, FlowableFromCompletionStage.a<T> aVar) {
            this.h = maybeObserver;
            this.i = aVar;
        }

        @Override // java.util.function.BiConsumer
        /* renamed from: a */
        public void accept(T t, Throwable th) {
            if (th != null) {
                this.h.onError(th);
            } else if (t != null) {
                this.h.onSuccess(t);
            } else {
                this.h.onComplete();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.i.set(null);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.i.get() == null;
        }
    }

    public MaybeFromCompletionStage(CompletionStage<T> completionStage) {
        this.h = completionStage;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        FlowableFromCompletionStage.a aVar = new FlowableFromCompletionStage.a();
        a aVar2 = new a(maybeObserver, aVar);
        aVar.lazySet(aVar2);
        maybeObserver.onSubscribe(aVar2);
        this.h.whenComplete(aVar);
    }
}
