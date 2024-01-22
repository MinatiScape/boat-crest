package retrofit2.adapter.rxjava;

import java.util.concurrent.atomic.AtomicInteger;
import retrofit2.Call;
import retrofit2.Response;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.plugins.RxJavaPlugins;
/* loaded from: classes13.dex */
public final class b<T> extends AtomicInteger implements Subscription, Producer {
    private static final int STATE_HAS_RESPONSE = 2;
    private static final int STATE_REQUESTED = 1;
    private static final int STATE_TERMINATED = 3;
    private static final int STATE_WAITING = 0;
    private final Call<T> call;
    private volatile Response<T> response;
    private final Subscriber<? super Response<T>> subscriber;
    private volatile boolean unsubscribed;

    public b(Call<T> call, Subscriber<? super Response<T>> subscriber) {
        super(0);
        this.call = call;
        this.subscriber = subscriber;
    }

    private void deliverResponse(Response<T> response) {
        try {
            if (!isUnsubscribed()) {
                this.subscriber.onNext(response);
            }
            try {
                if (isUnsubscribed()) {
                    return;
                }
                this.subscriber.onCompleted();
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
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }
        } catch (OnCompletedFailedException e4) {
            e = e4;
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        } catch (OnErrorFailedException e5) {
            e = e5;
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        } catch (OnErrorNotImplementedException e6) {
            e = e6;
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            try {
                this.subscriber.onError(th2);
            } catch (OnCompletedFailedException e7) {
                e = e7;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (OnErrorFailedException e8) {
                e = e8;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (OnErrorNotImplementedException e9) {
                e = e9;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(th2, th3));
            }
        }
    }

    public void emitError(Throwable th) {
        set(3);
        if (isUnsubscribed()) {
            return;
        }
        try {
            this.subscriber.onError(th);
        } catch (OnCompletedFailedException e) {
            e = e;
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        } catch (OnErrorFailedException e2) {
            e = e2;
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        } catch (OnErrorNotImplementedException e3) {
            e = e3;
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(th, th2));
        }
    }

    public void emitResponse(Response<T> response) {
        while (true) {
            int i = get();
            if (i == 0) {
                this.response = response;
                if (compareAndSet(0, 2)) {
                    return;
                }
            } else if (i != 1) {
                if (i != 2 && i != 3) {
                    throw new IllegalStateException("Unknown state: " + i);
                }
                throw new AssertionError();
            } else if (compareAndSet(1, 3)) {
                deliverResponse(response);
                return;
            }
        }
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    @Override // rx.Producer
    public void request(long j) {
        if (j == 0) {
            return;
        }
        while (true) {
            int i = get();
            if (i != 0) {
                if (i == 1) {
                    return;
                }
                if (i != 2) {
                    if (i == 3) {
                        return;
                    }
                    throw new IllegalStateException("Unknown state: " + i);
                } else if (compareAndSet(2, 3)) {
                    deliverResponse(this.response);
                    return;
                }
            } else if (compareAndSet(0, 1)) {
                return;
            }
        }
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.unsubscribed = true;
        this.call.cancel();
    }
}
