package retrofit2;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
/* loaded from: classes13.dex */
public abstract class i<T> {

    /* loaded from: classes13.dex */
    public class a extends i<Iterable<T>> {
        public a() {
        }

        @Override // retrofit2.i
        /* renamed from: d */
        public void a(retrofit2.k kVar, @Nullable Iterable<T> iterable) throws IOException {
            if (iterable == null) {
                return;
            }
            for (T t : iterable) {
                i.this.a(kVar, t);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class b extends i<Object> {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // retrofit2.i
        public void a(retrofit2.k kVar, @Nullable Object obj) throws IOException {
            if (obj == null) {
                return;
            }
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                i.this.a(kVar, Array.get(obj, i));
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T> extends i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Method f15623a;
        public final int b;
        public final Converter<T, RequestBody> c;

        public c(Method method, int i, Converter<T, RequestBody> converter) {
            this.f15623a = method;
            this.b = i;
            this.c = converter;
        }

        @Override // retrofit2.i
        public void a(retrofit2.k kVar, @Nullable T t) {
            if (t != null) {
                try {
                    kVar.l(this.c.convert(t));
                    return;
                } catch (IOException e) {
                    Method method = this.f15623a;
                    int i = this.b;
                    throw retrofit2.o.p(method, e, i, "Unable to convert " + t + " to RequestBody", new Object[0]);
                }
            }
            throw retrofit2.o.o(this.f15623a, this.b, "Body parameter value must not be null.", new Object[0]);
        }
    }

    /* loaded from: classes13.dex */
    public static final class d<T> extends i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final String f15624a;
        public final Converter<T, String> b;
        public final boolean c;

        public d(String str, Converter<T, String> converter, boolean z) {
            Objects.requireNonNull(str, "name == null");
            this.f15624a = str;
            this.b = converter;
            this.c = z;
        }

        @Override // retrofit2.i
        public void a(retrofit2.k kVar, @Nullable T t) throws IOException {
            String convert;
            if (t == null || (convert = this.b.convert(t)) == null) {
                return;
            }
            kVar.a(this.f15624a, convert, this.c);
        }
    }

    /* loaded from: classes13.dex */
    public static final class e<T> extends i<Map<String, T>> {

        /* renamed from: a  reason: collision with root package name */
        public final Method f15625a;
        public final int b;
        public final Converter<T, String> c;
        public final boolean d;

        public e(Method method, int i, Converter<T, String> converter, boolean z) {
            this.f15625a = method;
            this.b = i;
            this.c = converter;
            this.d = z;
        }

        @Override // retrofit2.i
        /* renamed from: d */
        public void a(retrofit2.k kVar, @Nullable Map<String, T> map) throws IOException {
            if (map != null) {
                for (Map.Entry<String, T> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (key != null) {
                        T value = entry.getValue();
                        if (value != null) {
                            String convert = this.c.convert(value);
                            if (convert != null) {
                                kVar.a(key, convert, this.d);
                            } else {
                                Method method = this.f15625a;
                                int i = this.b;
                                throw retrofit2.o.o(method, i, "Field map value '" + value + "' converted to null by " + this.c.getClass().getName() + " for key '" + key + "'.", new Object[0]);
                            }
                        } else {
                            Method method2 = this.f15625a;
                            int i2 = this.b;
                            throw retrofit2.o.o(method2, i2, "Field map contained null value for key '" + key + "'.", new Object[0]);
                        }
                    } else {
                        throw retrofit2.o.o(this.f15625a, this.b, "Field map contained null key.", new Object[0]);
                    }
                }
                return;
            }
            throw retrofit2.o.o(this.f15625a, this.b, "Field map was null.", new Object[0]);
        }
    }

    /* loaded from: classes13.dex */
    public static final class f<T> extends i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final String f15626a;
        public final Converter<T, String> b;

        public f(String str, Converter<T, String> converter) {
            Objects.requireNonNull(str, "name == null");
            this.f15626a = str;
            this.b = converter;
        }

        @Override // retrofit2.i
        public void a(retrofit2.k kVar, @Nullable T t) throws IOException {
            String convert;
            if (t == null || (convert = this.b.convert(t)) == null) {
                return;
            }
            kVar.b(this.f15626a, convert);
        }
    }

    /* loaded from: classes13.dex */
    public static final class g<T> extends i<Map<String, T>> {

        /* renamed from: a  reason: collision with root package name */
        public final Method f15627a;
        public final int b;
        public final Converter<T, String> c;

        public g(Method method, int i, Converter<T, String> converter) {
            this.f15627a = method;
            this.b = i;
            this.c = converter;
        }

        @Override // retrofit2.i
        /* renamed from: d */
        public void a(retrofit2.k kVar, @Nullable Map<String, T> map) throws IOException {
            if (map != null) {
                for (Map.Entry<String, T> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (key != null) {
                        T value = entry.getValue();
                        if (value != null) {
                            kVar.b(key, this.c.convert(value));
                        } else {
                            Method method = this.f15627a;
                            int i = this.b;
                            throw retrofit2.o.o(method, i, "Header map contained null value for key '" + key + "'.", new Object[0]);
                        }
                    } else {
                        throw retrofit2.o.o(this.f15627a, this.b, "Header map contained null key.", new Object[0]);
                    }
                }
                return;
            }
            throw retrofit2.o.o(this.f15627a, this.b, "Header map was null.", new Object[0]);
        }
    }

    /* loaded from: classes13.dex */
    public static final class h extends i<Headers> {

        /* renamed from: a  reason: collision with root package name */
        public final Method f15628a;
        public final int b;

        public h(Method method, int i) {
            this.f15628a = method;
            this.b = i;
        }

        @Override // retrofit2.i
        /* renamed from: d */
        public void a(retrofit2.k kVar, @Nullable Headers headers) {
            if (headers != null) {
                kVar.c(headers);
                return;
            }
            throw retrofit2.o.o(this.f15628a, this.b, "Headers parameter must not be null.", new Object[0]);
        }
    }

    /* renamed from: retrofit2.i$i  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static final class C0922i<T> extends i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Method f15629a;
        public final int b;
        public final Headers c;
        public final Converter<T, RequestBody> d;

        public C0922i(Method method, int i, Headers headers, Converter<T, RequestBody> converter) {
            this.f15629a = method;
            this.b = i;
            this.c = headers;
            this.d = converter;
        }

        @Override // retrofit2.i
        public void a(retrofit2.k kVar, @Nullable T t) {
            if (t == null) {
                return;
            }
            try {
                kVar.d(this.c, this.d.convert(t));
            } catch (IOException e) {
                Method method = this.f15629a;
                int i = this.b;
                throw retrofit2.o.o(method, i, "Unable to convert " + t + " to RequestBody", e);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class j<T> extends i<Map<String, T>> {

        /* renamed from: a  reason: collision with root package name */
        public final Method f15630a;
        public final int b;
        public final Converter<T, RequestBody> c;
        public final String d;

        public j(Method method, int i, Converter<T, RequestBody> converter, String str) {
            this.f15630a = method;
            this.b = i;
            this.c = converter;
            this.d = str;
        }

        @Override // retrofit2.i
        /* renamed from: d */
        public void a(retrofit2.k kVar, @Nullable Map<String, T> map) throws IOException {
            if (map != null) {
                for (Map.Entry<String, T> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (key != null) {
                        T value = entry.getValue();
                        if (value != null) {
                            kVar.d(Headers.of(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + key + "\"", "Content-Transfer-Encoding", this.d), this.c.convert(value));
                        } else {
                            Method method = this.f15630a;
                            int i = this.b;
                            throw retrofit2.o.o(method, i, "Part map contained null value for key '" + key + "'.", new Object[0]);
                        }
                    } else {
                        throw retrofit2.o.o(this.f15630a, this.b, "Part map contained null key.", new Object[0]);
                    }
                }
                return;
            }
            throw retrofit2.o.o(this.f15630a, this.b, "Part map was null.", new Object[0]);
        }
    }

    /* loaded from: classes13.dex */
    public static final class k<T> extends i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Method f15631a;
        public final int b;
        public final String c;
        public final Converter<T, String> d;
        public final boolean e;

        public k(Method method, int i, String str, Converter<T, String> converter, boolean z) {
            this.f15631a = method;
            this.b = i;
            Objects.requireNonNull(str, "name == null");
            this.c = str;
            this.d = converter;
            this.e = z;
        }

        @Override // retrofit2.i
        public void a(retrofit2.k kVar, @Nullable T t) throws IOException {
            if (t != null) {
                kVar.f(this.c, this.d.convert(t), this.e);
                return;
            }
            Method method = this.f15631a;
            int i = this.b;
            throw retrofit2.o.o(method, i, "Path parameter \"" + this.c + "\" value must not be null.", new Object[0]);
        }
    }

    /* loaded from: classes13.dex */
    public static final class l<T> extends i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final String f15632a;
        public final Converter<T, String> b;
        public final boolean c;

        public l(String str, Converter<T, String> converter, boolean z) {
            Objects.requireNonNull(str, "name == null");
            this.f15632a = str;
            this.b = converter;
            this.c = z;
        }

        @Override // retrofit2.i
        public void a(retrofit2.k kVar, @Nullable T t) throws IOException {
            String convert;
            if (t == null || (convert = this.b.convert(t)) == null) {
                return;
            }
            kVar.g(this.f15632a, convert, this.c);
        }
    }

    /* loaded from: classes13.dex */
    public static final class m<T> extends i<Map<String, T>> {

        /* renamed from: a  reason: collision with root package name */
        public final Method f15633a;
        public final int b;
        public final Converter<T, String> c;
        public final boolean d;

        public m(Method method, int i, Converter<T, String> converter, boolean z) {
            this.f15633a = method;
            this.b = i;
            this.c = converter;
            this.d = z;
        }

        @Override // retrofit2.i
        /* renamed from: d */
        public void a(retrofit2.k kVar, @Nullable Map<String, T> map) throws IOException {
            if (map != null) {
                for (Map.Entry<String, T> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (key != null) {
                        T value = entry.getValue();
                        if (value != null) {
                            String convert = this.c.convert(value);
                            if (convert != null) {
                                kVar.g(key, convert, this.d);
                            } else {
                                Method method = this.f15633a;
                                int i = this.b;
                                throw retrofit2.o.o(method, i, "Query map value '" + value + "' converted to null by " + this.c.getClass().getName() + " for key '" + key + "'.", new Object[0]);
                            }
                        } else {
                            Method method2 = this.f15633a;
                            int i2 = this.b;
                            throw retrofit2.o.o(method2, i2, "Query map contained null value for key '" + key + "'.", new Object[0]);
                        }
                    } else {
                        throw retrofit2.o.o(this.f15633a, this.b, "Query map contained null key.", new Object[0]);
                    }
                }
                return;
            }
            throw retrofit2.o.o(this.f15633a, this.b, "Query map was null", new Object[0]);
        }
    }

    /* loaded from: classes13.dex */
    public static final class n<T> extends i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Converter<T, String> f15634a;
        public final boolean b;

        public n(Converter<T, String> converter, boolean z) {
            this.f15634a = converter;
            this.b = z;
        }

        @Override // retrofit2.i
        public void a(retrofit2.k kVar, @Nullable T t) throws IOException {
            if (t == null) {
                return;
            }
            kVar.g(this.f15634a.convert(t), null, this.b);
        }
    }

    /* loaded from: classes13.dex */
    public static final class o extends i<MultipartBody.Part> {

        /* renamed from: a  reason: collision with root package name */
        public static final o f15635a = new o();

        @Override // retrofit2.i
        /* renamed from: d */
        public void a(retrofit2.k kVar, @Nullable MultipartBody.Part part) {
            if (part != null) {
                kVar.e(part);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class p extends i<Object> {

        /* renamed from: a  reason: collision with root package name */
        public final Method f15636a;
        public final int b;

        public p(Method method, int i) {
            this.f15636a = method;
            this.b = i;
        }

        @Override // retrofit2.i
        public void a(retrofit2.k kVar, @Nullable Object obj) {
            if (obj != null) {
                kVar.m(obj);
                return;
            }
            throw retrofit2.o.o(this.f15636a, this.b, "@Url parameter is null.", new Object[0]);
        }
    }

    /* loaded from: classes13.dex */
    public static final class q<T> extends i<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Class<T> f15637a;

        public q(Class<T> cls) {
            this.f15637a = cls;
        }

        @Override // retrofit2.i
        public void a(retrofit2.k kVar, @Nullable T t) {
            kVar.h(this.f15637a, t);
        }
    }

    public abstract void a(retrofit2.k kVar, @Nullable T t) throws IOException;

    public final i<Object> b() {
        return new b();
    }

    public final i<Iterable<T>> c() {
        return new a();
    }
}
