package retrofit2.adapter.rxjava;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.plugins.RxJavaPlugins;
/* loaded from: classes13.dex */
public final class e<T> implements Observable.OnSubscribe<Result<T>> {
    public final Observable.OnSubscribe<Response<T>> h;

    /* loaded from: classes13.dex */
    public static class a<R> extends Subscriber<Response<R>> {
        public final Subscriber<? super Result<R>> l;

        public a(Subscriber<? super Result<R>> subscriber) {
            super(subscriber);
            this.l = subscriber;
        }

        @Override // rx.Observer
        /* renamed from: b */
        public void onNext(Response<R> response) {
            this.l.onNext(Result.response(response));
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            try {
                this.l.onNext(Result.error(th));
                this.l.onCompleted();
            } catch (Throwable th2) {
                try {
                    this.l.onError(th2);
                } catch (OnCompletedFailedException e) {
                    e = e;
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
                } catch (OnErrorFailedException e2) {
                    e = e2;
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
                } catch (OnErrorNotImplementedException e3) {
                    e = e3;
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
                } catch (Throwable th3) {
                    Exceptions.throwIfFatal(th3);
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(th2, th3));
                }
            }
        }
    }

    public e(Observable.OnSubscribe<Response<T>> onSubscribe) {
        this.h = onSubscribe;
    }

    @Override // rx.functions.Action1
    /* renamed from: a */
    public void call(Subscriber<? super Result<T>> subscriber) {
        this.h.call(new a(subscriber));
    }
}
