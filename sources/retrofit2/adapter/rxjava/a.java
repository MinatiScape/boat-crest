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
public final class a<T> implements Observable.OnSubscribe<T> {
    public final Observable.OnSubscribe<Response<T>> h;

    /* renamed from: retrofit2.adapter.rxjava.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0919a<R> extends Subscriber<Response<R>> {
        public final Subscriber<? super R> l;
        public boolean m;

        public C0919a(Subscriber<? super R> subscriber) {
            super(subscriber);
            this.l = subscriber;
        }

        @Override // rx.Observer
        /* renamed from: b */
        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                this.l.onNext(response.body());
                return;
            }
            this.m = true;
            HttpException httpException = new HttpException(response);
            try {
                this.l.onError(httpException);
            } catch (OnCompletedFailedException e) {
                e = e;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (OnErrorFailedException e2) {
                e = e2;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (OnErrorNotImplementedException e3) {
                e = e3;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(httpException, th));
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.m) {
                return;
            }
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (!this.m) {
                this.l.onError(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a Retrofit bug with the full stacktrace.");
            assertionError.initCause(th);
            RxJavaPlugins.getInstance().getErrorHandler().handleError(assertionError);
        }
    }

    public a(Observable.OnSubscribe<Response<T>> onSubscribe) {
        this.h = onSubscribe;
    }

    @Override // rx.functions.Action1
    /* renamed from: a */
    public void call(Subscriber<? super T> subscriber) {
        this.h.call(new C0919a(subscriber));
    }
}
