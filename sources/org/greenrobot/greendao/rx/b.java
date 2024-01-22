package org.greenrobot.greendao.rx;

import java.util.concurrent.Callable;
import org.greenrobot.greendao.annotation.apihint.Internal;
import rx.Observable;
import rx.functions.Func0;
@Internal
/* loaded from: classes13.dex */
public class b {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public class a<T> implements Func0<Observable<T>> {
        public final /* synthetic */ Callable h;

        public a(Callable callable) {
            this.h = callable;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        /* renamed from: a */
        public Observable<T> call() {
            try {
                return Observable.just(this.h.call());
            } catch (Exception e) {
                return Observable.error(e);
            }
        }
    }

    @Internal
    public static <T> Observable<T> a(Callable<T> callable) {
        return Observable.defer(new a(callable));
    }
}
