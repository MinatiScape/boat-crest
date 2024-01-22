package rx.internal.operators;

import java.util.Objects;
import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
/* loaded from: classes13.dex */
public final class SingleOperatorOnErrorResumeNext<T> implements Single.OnSubscribe<T> {
    public final Single<? extends T> h;
    public final Func1<Throwable, ? extends Single<? extends T>> i;

    /* loaded from: classes13.dex */
    public static class a implements Func1<Throwable, Single<? extends T>> {
        public final /* synthetic */ Single h;

        public a(Single single) {
            this.h = single;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Single<? extends T> call(Throwable th) {
            return this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class b extends SingleSubscriber<T> {
        public final /* synthetic */ SingleSubscriber i;

        public b(SingleSubscriber singleSubscriber) {
            this.i = singleSubscriber;
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            try {
                SingleOperatorOnErrorResumeNext.this.i.call(th).subscribe(this.i);
            } catch (Throwable th2) {
                Exceptions.throwOrReport(th2, this.i);
            }
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            this.i.onSuccess(t);
        }
    }

    public SingleOperatorOnErrorResumeNext(Single<? extends T> single, Func1<Throwable, ? extends Single<? extends T>> func1) {
        Objects.requireNonNull(single, "originalSingle must not be null");
        Objects.requireNonNull(func1, "resumeFunctionInCaseOfError must not be null");
        this.h = single;
        this.i = func1;
    }

    public static <T> SingleOperatorOnErrorResumeNext<T> withFunction(Single<? extends T> single, Func1<Throwable, ? extends Single<? extends T>> func1) {
        return new SingleOperatorOnErrorResumeNext<>(single, func1);
    }

    public static <T> SingleOperatorOnErrorResumeNext<T> withOther(Single<? extends T> single, Single<? extends T> single2) {
        Objects.requireNonNull(single2, "resumeSingleInCaseOfError must not be null");
        return new SingleOperatorOnErrorResumeNext<>(single, new a(single2));
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        b bVar = new b(singleSubscriber);
        singleSubscriber.add(bVar);
        this.h.subscribe(bVar);
    }
}
