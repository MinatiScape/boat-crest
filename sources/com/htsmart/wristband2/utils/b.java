package com.htsmart.wristband2.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class b implements Function<Observable<? extends Throwable>, Observable<?>> {
    public final int h;
    public final int i;
    public final Predicate<? super Throwable> j;
    public int k;

    /* loaded from: classes11.dex */
    public class a implements Function<Throwable, ObservableSource<?>> {
        public a() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<?> apply(Throwable th) throws Exception {
            return ((b.this.j == null || b.this.j.test(th)) && b.c(b.this) <= b.this.h) ? Observable.timer(b.this.i, TimeUnit.MILLISECONDS) : Observable.error(th);
        }
    }

    public b(int i, int i2) {
        this(i, i2, null);
    }

    public b(int i, int i2, Predicate<? super Throwable> predicate) {
        this.h = i;
        this.i = i2;
        this.j = predicate;
    }

    public static /* synthetic */ int c(b bVar) {
        int i = bVar.k + 1;
        bVar.k = i;
        return i;
    }

    @Override // io.reactivex.functions.Function
    /* renamed from: a */
    public Observable<?> apply(Observable<? extends Throwable> observable) {
        return observable.flatMap(new a());
    }
}
