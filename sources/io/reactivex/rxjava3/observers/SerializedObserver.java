package io.reactivex.rxjava3.observers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class SerializedObserver<T> implements Observer<T>, Disposable {
    public final Observer<? super T> h;
    public final boolean i;
    public Disposable j;
    public boolean k;
    public AppendOnlyLinkedArrayList<Object> l;
    public volatile boolean m;

    public SerializedObserver(@NonNull Observer<? super T> observer) {
        this(observer, false);
    }

    public void a() {
        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList;
        do {
            synchronized (this) {
                appendOnlyLinkedArrayList = this.l;
                if (appendOnlyLinkedArrayList == null) {
                    this.k = false;
                    return;
                }
                this.l = null;
            }
        } while (!appendOnlyLinkedArrayList.accept((Observer<? super T>) this.h));
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.m = true;
        this.j.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.j.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        if (this.m) {
            return;
        }
        synchronized (this) {
            if (this.m) {
                return;
            }
            if (this.k) {
                AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.l;
                if (appendOnlyLinkedArrayList == null) {
                    appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                    this.l = appendOnlyLinkedArrayList;
                }
                appendOnlyLinkedArrayList.add(NotificationLite.complete());
                return;
            }
            this.m = true;
            this.k = true;
            this.h.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(@NonNull Throwable th) {
        if (this.m) {
            RxJavaPlugins.onError(th);
            return;
        }
        synchronized (this) {
            boolean z = true;
            if (!this.m) {
                if (this.k) {
                    this.m = true;
                    AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.l;
                    if (appendOnlyLinkedArrayList == null) {
                        appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                        this.l = appendOnlyLinkedArrayList;
                    }
                    Object error = NotificationLite.error(th);
                    if (this.i) {
                        appendOnlyLinkedArrayList.add(error);
                    } else {
                        appendOnlyLinkedArrayList.setFirst(error);
                    }
                    return;
                }
                this.m = true;
                this.k = true;
                z = false;
            }
            if (z) {
                RxJavaPlugins.onError(th);
            } else {
                this.h.onError(th);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(@NonNull T t) {
        if (this.m) {
            return;
        }
        if (t == null) {
            this.j.dispose();
            onError(ExceptionHelper.createNullPointerException("onNext called with a null value."));
            return;
        }
        synchronized (this) {
            if (this.m) {
                return;
            }
            if (this.k) {
                AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.l;
                if (appendOnlyLinkedArrayList == null) {
                    appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                    this.l = appendOnlyLinkedArrayList;
                }
                appendOnlyLinkedArrayList.add(NotificationLite.next(t));
                return;
            }
            this.k = true;
            this.h.onNext(t);
            a();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onSubscribe(@NonNull Disposable disposable) {
        if (DisposableHelper.validate(this.j, disposable)) {
            this.j = disposable;
            this.h.onSubscribe(this);
        }
    }

    public SerializedObserver(@NonNull Observer<? super T> observer, boolean z) {
        this.h = observer;
        this.i = z;
    }
}
