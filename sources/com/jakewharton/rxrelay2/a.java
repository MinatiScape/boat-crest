package com.jakewharton.rxrelay2;

import io.reactivex.Observer;
/* loaded from: classes11.dex */
public final class a<T> extends Relay<T> {
    public final Relay<T> h;
    public boolean i;
    public AppendOnlyLinkedArrayList<T> j;

    public a(Relay<T> relay) {
        this.h = relay;
    }

    @Override // com.jakewharton.rxrelay2.Relay, io.reactivex.functions.Consumer
    public void accept(T t) {
        synchronized (this) {
            if (this.i) {
                AppendOnlyLinkedArrayList<T> appendOnlyLinkedArrayList = this.j;
                if (appendOnlyLinkedArrayList == null) {
                    appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                    this.j = appendOnlyLinkedArrayList;
                }
                appendOnlyLinkedArrayList.b(t);
                return;
            }
            this.i = true;
            this.h.accept(t);
            d();
        }
    }

    public final void d() {
        AppendOnlyLinkedArrayList<T> appendOnlyLinkedArrayList;
        while (true) {
            synchronized (this) {
                appendOnlyLinkedArrayList = this.j;
                if (appendOnlyLinkedArrayList == null) {
                    this.i = false;
                    return;
                }
                this.j = null;
            }
            appendOnlyLinkedArrayList.a(this.h);
        }
    }

    @Override // com.jakewharton.rxrelay2.Relay
    public boolean hasObservers() {
        return this.h.hasObservers();
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.h.subscribe(observer);
    }
}
