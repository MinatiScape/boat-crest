package io.reactivex.rxjava3.subjects;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class a<T> extends Subject<T> implements AppendOnlyLinkedArrayList.NonThrowingPredicate<Object> {
    public final Subject<T> h;
    public boolean i;
    public AppendOnlyLinkedArrayList<Object> j;
    public volatile boolean k;

    public a(Subject<T> subject) {
        this.h = subject;
    }

    public void d() {
        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList;
        while (true) {
            synchronized (this) {
                appendOnlyLinkedArrayList = this.j;
                if (appendOnlyLinkedArrayList == null) {
                    this.i = false;
                    return;
                }
                this.j = null;
            }
            appendOnlyLinkedArrayList.forEachWhile(this);
        }
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @Nullable
    public Throwable getThrowable() {
        return this.h.getThrowable();
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    public boolean hasComplete() {
        return this.h.hasComplete();
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    public boolean hasObservers() {
        return this.h.hasObservers();
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    public boolean hasThrowable() {
        return this.h.hasThrowable();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        if (this.k) {
            return;
        }
        synchronized (this) {
            if (this.k) {
                return;
            }
            this.k = true;
            if (this.i) {
                AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.j;
                if (appendOnlyLinkedArrayList == null) {
                    appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                    this.j = appendOnlyLinkedArrayList;
                }
                appendOnlyLinkedArrayList.add(NotificationLite.complete());
                return;
            }
            this.i = true;
            this.h.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable th) {
        if (this.k) {
            RxJavaPlugins.onError(th);
            return;
        }
        synchronized (this) {
            boolean z = true;
            if (!this.k) {
                this.k = true;
                if (this.i) {
                    AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.j;
                    if (appendOnlyLinkedArrayList == null) {
                        appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                        this.j = appendOnlyLinkedArrayList;
                    }
                    appendOnlyLinkedArrayList.setFirst(NotificationLite.error(th));
                    return;
                }
                this.i = true;
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
    public void onNext(T t) {
        if (this.k) {
            return;
        }
        synchronized (this) {
            if (this.k) {
                return;
            }
            if (this.i) {
                AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.j;
                if (appendOnlyLinkedArrayList == null) {
                    appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                    this.j = appendOnlyLinkedArrayList;
                }
                appendOnlyLinkedArrayList.add(NotificationLite.next(t));
                return;
            }
            this.i = true;
            this.h.onNext(t);
            d();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onSubscribe(Disposable disposable) {
        boolean z = true;
        if (!this.k) {
            synchronized (this) {
                if (!this.k) {
                    if (this.i) {
                        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.j;
                        if (appendOnlyLinkedArrayList == null) {
                            appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                            this.j = appendOnlyLinkedArrayList;
                        }
                        appendOnlyLinkedArrayList.add(NotificationLite.disposable(disposable));
                        return;
                    }
                    this.i = true;
                    z = false;
                }
            }
        }
        if (z) {
            disposable.dispose();
            return;
        }
        this.h.onSubscribe(disposable);
        d();
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.h.subscribe(observer);
    }

    @Override // io.reactivex.rxjava3.internal.util.AppendOnlyLinkedArrayList.NonThrowingPredicate, io.reactivex.rxjava3.functions.Predicate
    public boolean test(Object obj) {
        return NotificationLite.acceptFull(obj, this.h);
    }
}
