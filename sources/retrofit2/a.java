package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import kotlin.Unit;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.http.Streaming;
/* loaded from: classes13.dex */
public final class a extends Converter.Factory {

    /* renamed from: a  reason: collision with root package name */
    public boolean f15585a = true;

    /* renamed from: retrofit2.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static final class C0918a implements Converter<ResponseBody, ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        public static final C0918a f15586a = new C0918a();

        @Override // retrofit2.Converter
        /* renamed from: a */
        public ResponseBody convert(ResponseBody responseBody) throws IOException {
            try {
                return o.a(responseBody);
            } finally {
                responseBody.close();
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class b implements Converter<RequestBody, RequestBody> {

        /* renamed from: a  reason: collision with root package name */
        public static final b f15587a = new b();

        @Override // retrofit2.Converter
        /* renamed from: a */
        public RequestBody convert(RequestBody requestBody) {
            return requestBody;
        }
    }

    /* loaded from: classes13.dex */
    public static final class c implements Converter<ResponseBody, ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        public static final c f15588a = new c();

        @Override // retrofit2.Converter
        /* renamed from: a */
        public ResponseBody convert(ResponseBody responseBody) {
            return responseBody;
        }
    }

    /* loaded from: classes13.dex */
    public static final class d implements Converter<Object, String> {

        /* renamed from: a  reason: collision with root package name */
        public static final d f15589a = new d();

        @Override // retrofit2.Converter
        /* renamed from: a */
        public String convert(Object obj) {
            return obj.toString();
        }
    }

    /* loaded from: classes13.dex */
    public static final class e implements Converter<ResponseBody, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final e f15590a = new e();

        @Override // retrofit2.Converter
        /* renamed from: a */
        public Unit convert(ResponseBody responseBody) {
            responseBody.close();
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes13.dex */
    public static final class f implements Converter<ResponseBody, Void> {

        /* renamed from: a  reason: collision with root package name */
        public static final f f15591a = new f();

        @Override // retrofit2.Converter
        /* renamed from: a */
        public Void convert(ResponseBody responseBody) {
            responseBody.close();
            return null;
        }
    }

    @Override // retrofit2.Converter.Factory
    @Nullable
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        if (RequestBody.class.isAssignableFrom(o.h(type))) {
            return b.f15587a;
        }
        return null;
    }

    @Override // retrofit2.Converter.Factory
    @Nullable
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (type == ResponseBody.class) {
            if (o.l(annotationArr, Streaming.class)) {
                return c.f15588a;
            }
            return C0918a.f15586a;
        } else if (type == Void.class) {
            return f.f15591a;
        } else {
            if (this.f15585a && type == Unit.class) {
                try {
                    return e.f15590a;
                } catch (NoClassDefFoundError unused) {
                    this.f15585a = false;
                    return null;
                }
            }
            return null;
        }
    }
}
