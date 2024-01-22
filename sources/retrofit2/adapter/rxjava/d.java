package retrofit2.adapter.rxjava;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
/* loaded from: classes13.dex */
public final class d<T> implements Observable.OnSubscribe<Response<T>> {
    public final Call<T> h;

    public d(Call<T> call) {
        this.h = call;
    }

    @Override // rx.functions.Action1
    /* renamed from: a */
    public void call(Subscriber<? super Response<T>> subscriber) {
        Call<T> mo952clone = this.h.mo952clone();
        b bVar = new b(mo952clone, subscriber);
        subscriber.add(bVar);
        subscriber.setProducer(bVar);
        try {
            bVar.emitResponse(mo952clone.execute());
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            bVar.emitError(th);
        }
    }
}
