package com.jakewharton.rxrelay2;

import io.reactivex.functions.Predicate;
/* loaded from: classes11.dex */
public class AppendOnlyLinkedArrayList<T> {

    /* renamed from: a  reason: collision with root package name */
    public final int f12328a;
    public final Object[] b;
    public Object[] c;
    public int d;

    /* loaded from: classes11.dex */
    public interface NonThrowingPredicate<T> extends Predicate<T> {
        @Override // io.reactivex.functions.Predicate
        boolean test(T t);
    }

    public AppendOnlyLinkedArrayList(int i) {
        this.f12328a = i;
        Object[] objArr = new Object[i + 1];
        this.b = objArr;
        this.c = objArr;
    }

    public void a(Relay<? super T> relay) {
        int i = this.f12328a;
        for (Object[] objArr = this.b; objArr != null; objArr = objArr[i]) {
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = objArr[i2];
                if (obj == null) {
                    break;
                }
                relay.accept(obj);
            }
        }
    }

    public void b(T t) {
        int i = this.f12328a;
        int i2 = this.d;
        if (i2 == i) {
            Object[] objArr = new Object[i + 1];
            this.c[i] = objArr;
            this.c = objArr;
            i2 = 0;
        }
        this.c[i2] = t;
        this.d = i2 + 1;
    }

    public void c(NonThrowingPredicate<? super T> nonThrowingPredicate) {
        int i;
        int i2 = this.f12328a;
        for (Object[] objArr = this.b; objArr != null; objArr = objArr[i2]) {
            while (i < i2) {
                Object obj = objArr[i];
                i = (obj == null || nonThrowingPredicate.test(obj)) ? 0 : i + 1;
            }
        }
    }
}
