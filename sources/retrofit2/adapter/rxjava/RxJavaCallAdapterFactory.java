package retrofit2.adapter.rxjava;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import javax.annotation.Nullable;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Completable;
import rx.Observable;
import rx.Scheduler;
import rx.Single;
/* loaded from: classes13.dex */
public final class RxJavaCallAdapterFactory extends CallAdapter.Factory {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Scheduler f15593a;
    public final boolean b;

    public RxJavaCallAdapterFactory(@Nullable Scheduler scheduler, boolean z) {
        this.f15593a = scheduler;
        this.b = z;
    }

    public static RxJavaCallAdapterFactory create() {
        return new RxJavaCallAdapterFactory(null, false);
    }

    public static RxJavaCallAdapterFactory createAsync() {
        return new RxJavaCallAdapterFactory(null, true);
    }

    public static RxJavaCallAdapterFactory createWithScheduler(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler == null");
        return new RxJavaCallAdapterFactory(scheduler, false);
    }

    @Override // retrofit2.CallAdapter.Factory
    @Nullable
    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        Type type2;
        boolean z;
        boolean z2;
        Class<?> rawType = CallAdapter.Factory.getRawType(type);
        boolean z3 = rawType == Single.class;
        boolean z4 = rawType == Completable.class;
        if (rawType == Observable.class || z3 || z4) {
            if (z4) {
                return new f(Void.class, this.f15593a, this.b, false, true, false, true);
            }
            if (!(type instanceof ParameterizedType)) {
                String str = z3 ? "Single" : "Observable";
                throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
            }
            Type parameterUpperBound = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) type);
            Class<?> rawType2 = CallAdapter.Factory.getRawType(parameterUpperBound);
            if (rawType2 == Response.class) {
                if (parameterUpperBound instanceof ParameterizedType) {
                    type2 = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound);
                    z2 = false;
                    z = false;
                } else {
                    throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
                }
            } else if (rawType2 != Result.class) {
                type2 = parameterUpperBound;
                z = true;
                z2 = false;
            } else if (parameterUpperBound instanceof ParameterizedType) {
                type2 = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound);
                z2 = true;
                z = false;
            } else {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            return new f(type2, this.f15593a, this.b, z2, z, z3, false);
        }
        return null;
    }
}
