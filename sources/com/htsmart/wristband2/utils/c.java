package com.htsmart.wristband2.utils;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
/* loaded from: classes11.dex */
public class c implements Function<Flowable<? extends Throwable>, Publisher<?>> {
    public final int h;
    public final int i;
    public final Predicate<? super Throwable> j;
    public int k;

    /* loaded from: classes11.dex */
    public class a implements Function<Throwable, Publisher<?>> {
        public a() {
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Publisher<?> apply(Throwable th) throws Exception {
            return ((c.this.j == null || c.this.j.test(th)) && c.b(c.this) <= c.this.h) ? Flowable.timer(c.this.i, TimeUnit.MILLISECONDS) : Flowable.error(th);
        }
    }

    public c(int i, int i2) {
        this(i, i2, null);
    }

    public c(int i, int i2, Predicate<? super Throwable> predicate) {
        this.h = i;
        this.i = i2;
        this.j = predicate;
    }

    public static /* synthetic */ int b(c cVar) {
        int i = cVar.k + 1;
        cVar.k = i;
        return i;
    }

    @Override // io.reactivex.functions.Function
    /* renamed from: a */
    public Publisher<?> apply(Flowable<? extends Throwable> flowable) throws Exception {
        return flowable.flatMap(new a());
    }
}
