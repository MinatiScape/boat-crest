package rx.internal.operators;

import java.util.Arrays;
import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class SingleOnSubscribeUsing<T, Resource> implements Single.OnSubscribe<T> {
    public final Func0<Resource> h;
    public final Func1<? super Resource, ? extends Single<? extends T>> i;
    public final Action1<? super Resource> j;
    public final boolean k;

    /* loaded from: classes13.dex */
    public class a extends SingleSubscriber<T> {
        public final /* synthetic */ Object i;
        public final /* synthetic */ SingleSubscriber j;

        public a(Object obj, SingleSubscriber singleSubscriber) {
            this.i = obj;
            this.j = singleSubscriber;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            SingleOnSubscribeUsing.this.a(this.j, this.i, th);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            SingleOnSubscribeUsing singleOnSubscribeUsing = SingleOnSubscribeUsing.this;
            if (singleOnSubscribeUsing.k) {
                try {
                    singleOnSubscribeUsing.j.call((Object) this.i);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.j.onError(th);
                    return;
                }
            }
            this.j.onSuccess(t);
            SingleOnSubscribeUsing singleOnSubscribeUsing2 = SingleOnSubscribeUsing.this;
            if (singleOnSubscribeUsing2.k) {
                return;
            }
            try {
                singleOnSubscribeUsing2.j.call((Object) this.i);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                RxJavaHooks.onError(th2);
            }
        }
    }

    public SingleOnSubscribeUsing(Func0<Resource> func0, Func1<? super Resource, ? extends Single<? extends T>> func1, Action1<? super Resource> action1, boolean z) {
        this.h = func0;
        this.i = func1;
        this.j = action1;
        this.k = z;
    }

    public void a(SingleSubscriber<? super T> singleSubscriber, Resource resource, Throwable th) {
        Exceptions.throwIfFatal(th);
        if (this.k) {
            try {
                this.j.call(resource);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(Arrays.asList(th, th2));
            }
        }
        singleSubscriber.onError(th);
        if (this.k) {
            return;
        }
        try {
            this.j.call(resource);
        } catch (Throwable th3) {
            Exceptions.throwIfFatal(th3);
            RxJavaHooks.onError(th3);
        }
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        try {
            Resource call = this.h.call();
            try {
                Single<? extends T> call2 = this.i.call(call);
                if (call2 == null) {
                    a(singleSubscriber, call, new NullPointerException("The single"));
                    return;
                }
                a aVar = new a(call, singleSubscriber);
                singleSubscriber.add(aVar);
                call2.subscribe(aVar);
            } catch (Throwable th) {
                a(singleSubscriber, call, th);
            }
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            singleSubscriber.onError(th2);
        }
    }
}
