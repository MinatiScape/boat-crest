package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Request;
import okio.Timeout;
import retrofit2.CallAdapter;
import retrofit2.c;
/* loaded from: classes13.dex */
public final class c extends CallAdapter.Factory {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Executor f15601a;

    /* loaded from: classes13.dex */
    public class a implements CallAdapter<Object, Call<?>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Type f15602a;
        public final /* synthetic */ Executor b;

        public a(c cVar, Type type, Executor executor) {
            this.f15602a = type;
            this.b = executor;
        }

        @Override // retrofit2.CallAdapter
        /* renamed from: a */
        public Call<Object> adapt(Call<Object> call) {
            Executor executor = this.b;
            return executor == null ? call : new b(executor, call);
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return this.f15602a;
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> implements Call<T> {
        public final Executor h;
        public final Call<T> i;

        /* loaded from: classes13.dex */
        public class a implements Callback<T> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Callback f15603a;

            public a(Callback callback) {
                this.f15603a = callback;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void c(Callback callback, Throwable th) {
                callback.onFailure(b.this, th);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void d(Callback callback, Response response) {
                if (b.this.i.isCanceled()) {
                    callback.onFailure(b.this, new IOException("Canceled"));
                } else {
                    callback.onResponse(b.this, response);
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<T> call, final Throwable th) {
                Executor executor = b.this.h;
                final Callback callback = this.f15603a;
                executor.execute(new Runnable() { // from class: retrofit2.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        c.b.a.this.c(callback, th);
                    }
                });
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<T> call, final Response<T> response) {
                Executor executor = b.this.h;
                final Callback callback = this.f15603a;
                executor.execute(new Runnable() { // from class: retrofit2.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        c.b.a.this.d(callback, response);
                    }
                });
            }
        }

        public b(Executor executor, Call<T> call) {
            this.h = executor;
            this.i = call;
        }

        @Override // retrofit2.Call
        public void cancel() {
            this.i.cancel();
        }

        @Override // retrofit2.Call
        public void enqueue(Callback<T> callback) {
            Objects.requireNonNull(callback, "callback == null");
            this.i.enqueue(new a(callback));
        }

        @Override // retrofit2.Call
        public Response<T> execute() throws IOException {
            return this.i.execute();
        }

        @Override // retrofit2.Call
        public boolean isCanceled() {
            return this.i.isCanceled();
        }

        @Override // retrofit2.Call
        public boolean isExecuted() {
            return this.i.isExecuted();
        }

        @Override // retrofit2.Call
        public Request request() {
            return this.i.request();
        }

        @Override // retrofit2.Call
        public Timeout timeout() {
            return this.i.timeout();
        }

        @Override // retrofit2.Call
        public Call<T> clone() {
            return new b(this.h, this.i.mo952clone());
        }
    }

    public c(@Nullable Executor executor) {
        this.f15601a = executor;
    }

    @Override // retrofit2.CallAdapter.Factory
    @Nullable
    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (CallAdapter.Factory.getRawType(type) != Call.class) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return new a(this, o.g(0, (ParameterizedType) type), o.l(annotationArr, SkipCallbackExecutor.class) ? null : this.f15601a);
        }
        throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
    }
}
