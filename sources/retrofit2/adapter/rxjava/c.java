package retrofit2.adapter.rxjava;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
/* loaded from: classes13.dex */
public final class c<T> implements Observable.OnSubscribe<Response<T>> {
    public final Call<T> h;

    /* loaded from: classes13.dex */
    public class a implements Callback<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f15594a;

        public a(c cVar, b bVar) {
            this.f15594a = bVar;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<T> call, Throwable th) {
            Exceptions.throwIfFatal(th);
            this.f15594a.emitError(th);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<T> call, Response<T> response) {
            this.f15594a.emitResponse(response);
        }
    }

    public c(Call<T> call) {
        this.h = call;
    }

    @Override // rx.functions.Action1
    /* renamed from: a */
    public void call(Subscriber<? super Response<T>> subscriber) {
        Call<T> mo952clone = this.h.mo952clone();
        b bVar = new b(mo952clone, subscriber);
        subscriber.add(bVar);
        subscriber.setProducer(bVar);
        mo952clone.enqueue(new a(this, bVar));
    }
}
