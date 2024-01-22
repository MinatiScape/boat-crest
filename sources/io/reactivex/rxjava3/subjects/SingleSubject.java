package io.reactivex.rxjava3.subjects;

import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class SingleSubject<T> extends Single<T> implements SingleObserver<T> {
    public static final a[] l = new a[0];
    public static final a[] m = new a[0];
    public T j;
    public Throwable k;
    public final AtomicBoolean i = new AtomicBoolean();
    public final AtomicReference<a<T>[]> h = new AtomicReference<>(l);

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicReference<SingleSubject<T>> implements Disposable {
        private static final long serialVersionUID = -7650903191002190468L;
        public final SingleObserver<? super T> downstream;

        public a(SingleObserver<? super T> singleObserver, SingleSubject<T> singleSubject) {
            this.downstream = singleObserver;
            lazySet(singleSubject);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            SingleSubject<T> andSet = getAndSet(null);
            if (andSet != null) {
                andSet.d(this);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return get() == null;
        }
    }

    @CheckReturnValue
    @NonNull
    public static <T> SingleSubject<T> create() {
        return new SingleSubject<>();
    }

    public boolean c(@NonNull a<T> aVar) {
        a<T>[] aVarArr;
        a<T>[] aVarArr2;
        do {
            aVarArr = this.h.get();
            if (aVarArr == m) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.h.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void d(@NonNull a<T> aVar) {
        a<T>[] aVarArr;
        a[] aVarArr2;
        do {
            aVarArr = this.h.get();
            int length = aVarArr.length;
            if (length == 0) {
                return;
            }
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (aVarArr[i2] == aVar) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                aVarArr2 = l;
            } else {
                a[] aVarArr3 = new a[length - 1];
                System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                aVarArr2 = aVarArr3;
            }
        } while (!this.h.compareAndSet(aVarArr, aVarArr2));
    }

    @Nullable
    public Throwable getThrowable() {
        if (this.h.get() == m) {
            return this.k;
        }
        return null;
    }

    @Nullable
    public T getValue() {
        if (this.h.get() == m) {
            return this.j;
        }
        return null;
    }

    public boolean hasObservers() {
        return this.h.get().length != 0;
    }

    public boolean hasThrowable() {
        return this.h.get() == m && this.k != null;
    }

    public boolean hasValue() {
        return this.h.get() == m && this.j != null;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
    public void onError(@NonNull Throwable th) {
        ExceptionHelper.nullCheck(th, "onError called with a null Throwable.");
        if (this.i.compareAndSet(false, true)) {
            this.k = th;
            for (a<T> aVar : this.h.getAndSet(m)) {
                aVar.downstream.onError(th);
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
    public void onSubscribe(@NonNull Disposable disposable) {
        if (this.h.get() == m) {
            disposable.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public void onSuccess(@NonNull T t) {
        ExceptionHelper.nullCheck(t, "onSuccess called with a null value.");
        if (this.i.compareAndSet(false, true)) {
            this.j = t;
            for (a<T> aVar : this.h.getAndSet(m)) {
                aVar.downstream.onSuccess(t);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(@NonNull SingleObserver<? super T> singleObserver) {
        a<T> aVar = new a<>(singleObserver, this);
        singleObserver.onSubscribe(aVar);
        if (c(aVar)) {
            if (aVar.isDisposed()) {
                d(aVar);
                return;
            }
            return;
        }
        Throwable th = this.k;
        if (th != null) {
            singleObserver.onError(th);
        } else {
            singleObserver.onSuccess((T) this.j);
        }
    }
}
