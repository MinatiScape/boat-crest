package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import retrofit2.CallAdapter;
@IgnoreJRERequirement
/* loaded from: classes13.dex */
public final class b extends CallAdapter.Factory {

    /* renamed from: a  reason: collision with root package name */
    public static final CallAdapter.Factory f15596a = new b();

    @IgnoreJRERequirement
    /* loaded from: classes13.dex */
    public static final class a<R> implements CallAdapter<R, CompletableFuture<R>> {

        /* renamed from: a  reason: collision with root package name */
        public final Type f15597a;

        @IgnoreJRERequirement
        /* renamed from: retrofit2.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0920a implements Callback<R> {

            /* renamed from: a  reason: collision with root package name */
            public final CompletableFuture<R> f15598a;

            public C0920a(a aVar, CompletableFuture<R> completableFuture) {
                this.f15598a = completableFuture;
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<R> call, Throwable th) {
                this.f15598a.completeExceptionally(th);
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<R> call, Response<R> response) {
                if (response.isSuccessful()) {
                    this.f15598a.complete(response.body());
                } else {
                    this.f15598a.completeExceptionally(new HttpException(response));
                }
            }
        }

        public a(Type type) {
            this.f15597a = type;
        }

        @Override // retrofit2.CallAdapter
        /* renamed from: a */
        public CompletableFuture<R> adapt(Call<R> call) {
            C0921b c0921b = new C0921b(call);
            call.enqueue(new C0920a(this, c0921b));
            return c0921b;
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return this.f15597a;
        }
    }

    @IgnoreJRERequirement
    /* renamed from: retrofit2.b$b  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static final class C0921b<T> extends CompletableFuture<T> {
        public final Call<?> h;

        public C0921b(Call<?> call) {
            this.h = call;
        }

        @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.Future
        public boolean cancel(boolean z) {
            if (z) {
                this.h.cancel();
            }
            return super.cancel(z);
        }
    }

    @IgnoreJRERequirement
    /* loaded from: classes13.dex */
    public static final class c<R> implements CallAdapter<R, CompletableFuture<Response<R>>> {

        /* renamed from: a  reason: collision with root package name */
        public final Type f15599a;

        @IgnoreJRERequirement
        /* loaded from: classes13.dex */
        public class a implements Callback<R> {

            /* renamed from: a  reason: collision with root package name */
            public final CompletableFuture<Response<R>> f15600a;

            public a(c cVar, CompletableFuture<Response<R>> completableFuture) {
                this.f15600a = completableFuture;
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<R> call, Throwable th) {
                this.f15600a.completeExceptionally(th);
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<R> call, Response<R> response) {
                this.f15600a.complete(response);
            }
        }

        public c(Type type) {
            this.f15599a = type;
        }

        @Override // retrofit2.CallAdapter
        /* renamed from: a */
        public CompletableFuture<Response<R>> adapt(Call<R> call) {
            C0921b c0921b = new C0921b(call);
            call.enqueue(new a(this, c0921b));
            return c0921b;
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return this.f15599a;
        }
    }

    @Override // retrofit2.CallAdapter.Factory
    @Nullable
    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (CallAdapter.Factory.getRawType(type) != CompletableFuture.class) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            Type parameterUpperBound = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) type);
            if (CallAdapter.Factory.getRawType(parameterUpperBound) != Response.class) {
                return new a(parameterUpperBound);
            }
            if (parameterUpperBound instanceof ParameterizedType) {
                return new c(CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound));
            }
            throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
        }
        throw new IllegalStateException("CompletableFuture return type must be parameterized as CompletableFuture<Foo> or CompletableFuture<? extends Foo>");
    }
}
