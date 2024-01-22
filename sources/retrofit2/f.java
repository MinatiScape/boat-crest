package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import kotlin.coroutines.Continuation;
import okhttp3.Call;
import okhttp3.ResponseBody;
import retrofit2.o;
/* loaded from: classes13.dex */
public abstract class f<ResponseT, ReturnT> extends m<ReturnT> {

    /* renamed from: a  reason: collision with root package name */
    public final l f15617a;
    public final Call.Factory b;
    public final Converter<ResponseBody, ResponseT> c;

    /* loaded from: classes13.dex */
    public static final class a<ResponseT, ReturnT> extends f<ResponseT, ReturnT> {
        public final CallAdapter<ResponseT, ReturnT> d;

        public a(l lVar, Call.Factory factory, Converter<ResponseBody, ResponseT> converter, CallAdapter<ResponseT, ReturnT> callAdapter) {
            super(lVar, factory, converter);
            this.d = callAdapter;
        }

        @Override // retrofit2.f
        public ReturnT c(Call<ResponseT> call, Object[] objArr) {
            return this.d.adapt(call);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<ResponseT> extends f<ResponseT, Object> {
        public final CallAdapter<ResponseT, Call<ResponseT>> d;
        public final boolean e;

        public b(l lVar, Call.Factory factory, Converter<ResponseBody, ResponseT> converter, CallAdapter<ResponseT, Call<ResponseT>> callAdapter, boolean z) {
            super(lVar, factory, converter);
            this.d = callAdapter;
            this.e = z;
        }

        @Override // retrofit2.f
        public Object c(Call<ResponseT> call, Object[] objArr) {
            Call<ResponseT> adapt = this.d.adapt(call);
            Continuation continuation = (Continuation) objArr[objArr.length - 1];
            try {
                if (this.e) {
                    return KotlinExtensions.awaitNullable(adapt, continuation);
                }
                return KotlinExtensions.await(adapt, continuation);
            } catch (Exception e) {
                return KotlinExtensions.suspendAndThrow(e, continuation);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<ResponseT> extends f<ResponseT, Object> {
        public final CallAdapter<ResponseT, Call<ResponseT>> d;

        public c(l lVar, Call.Factory factory, Converter<ResponseBody, ResponseT> converter, CallAdapter<ResponseT, Call<ResponseT>> callAdapter) {
            super(lVar, factory, converter);
            this.d = callAdapter;
        }

        @Override // retrofit2.f
        public Object c(Call<ResponseT> call, Object[] objArr) {
            Call<ResponseT> adapt = this.d.adapt(call);
            Continuation continuation = (Continuation) objArr[objArr.length - 1];
            try {
                return KotlinExtensions.awaitResponse(adapt, continuation);
            } catch (Exception e) {
                return KotlinExtensions.suspendAndThrow(e, continuation);
            }
        }
    }

    public f(l lVar, Call.Factory factory, Converter<ResponseBody, ResponseT> converter) {
        this.f15617a = lVar;
        this.b = factory;
        this.c = converter;
    }

    public static <ResponseT, ReturnT> CallAdapter<ResponseT, ReturnT> d(Retrofit retrofit, Method method, Type type, Annotation[] annotationArr) {
        try {
            return (CallAdapter<ResponseT, ReturnT>) retrofit.callAdapter(type, annotationArr);
        } catch (RuntimeException e) {
            throw o.n(method, e, "Unable to create call adapter for %s", type);
        }
    }

    public static <ResponseT> Converter<ResponseBody, ResponseT> e(Retrofit retrofit, Method method, Type type) {
        try {
            return retrofit.responseBodyConverter(type, method.getAnnotations());
        } catch (RuntimeException e) {
            throw o.n(method, e, "Unable to create converter for %s", type);
        }
    }

    public static <ResponseT, ReturnT> f<ResponseT, ReturnT> f(Retrofit retrofit, Method method, l lVar) {
        Type genericReturnType;
        boolean z;
        boolean z2 = lVar.k;
        Annotation[] annotations = method.getAnnotations();
        if (z2) {
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            Type f = o.f(0, (ParameterizedType) genericParameterTypes[genericParameterTypes.length - 1]);
            if (o.h(f) == Response.class && (f instanceof ParameterizedType)) {
                f = o.g(0, (ParameterizedType) f);
                z = true;
            } else {
                z = false;
            }
            genericReturnType = new o.b(null, Call.class, f);
            annotations = n.a(annotations);
        } else {
            genericReturnType = method.getGenericReturnType();
            z = false;
        }
        CallAdapter d = d(retrofit, method, genericReturnType, annotations);
        Type responseType = d.responseType();
        if (responseType != okhttp3.Response.class) {
            if (responseType != Response.class) {
                if (lVar.c.equals("HEAD") && !Void.class.equals(responseType)) {
                    throw o.m(method, "HEAD method must use Void as response type.", new Object[0]);
                }
                Converter e = e(retrofit, method, responseType);
                Call.Factory factory = retrofit.b;
                if (z2) {
                    if (z) {
                        return new c(lVar, factory, e, d);
                    }
                    return new b(lVar, factory, e, d, false);
                }
                return new a(lVar, factory, e, d);
            }
            throw o.m(method, "Response must include generic type (e.g., Response<String>)", new Object[0]);
        }
        throw o.m(method, "'" + o.h(responseType).getName() + "' is not a valid response body type. Did you mean ResponseBody?", new Object[0]);
    }

    @Override // retrofit2.m
    @Nullable
    public final ReturnT a(Object[] objArr) {
        return c(new g(this.f15617a, objArr, this.b, this.c), objArr);
    }

    @Nullable
    public abstract ReturnT c(Call<ResponseT> call, Object[] objArr);
}
