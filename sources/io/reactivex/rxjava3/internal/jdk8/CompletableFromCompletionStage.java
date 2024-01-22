package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.jdk8.FlowableFromCompletionStage;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
/* loaded from: classes12.dex */
public final class CompletableFromCompletionStage<T> extends Completable {
    public final CompletionStage<T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Disposable, BiConsumer<T, Throwable> {
        public final CompletableObserver h;
        public final FlowableFromCompletionStage.a<T> i;

        public a(CompletableObserver completableObserver, FlowableFromCompletionStage.a<T> aVar) {
            this.h = completableObserver;
            this.i = aVar;
        }

        @Override // java.util.function.BiConsumer
        /* renamed from: a */
        public void accept(T t, Throwable th) {
            if (th != null) {
                this.h.onError(th);
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

    public CompletableFromCompletionStage(CompletionStage<T> completionStage) {
        this.h = completionStage;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        FlowableFromCompletionStage.a aVar = new FlowableFromCompletionStage.a();
        a aVar2 = new a(completableObserver, aVar);
        aVar.lazySet(aVar2);
        completableObserver.onSubscribe(aVar2);
        this.h.whenComplete(aVar);
    }
}
