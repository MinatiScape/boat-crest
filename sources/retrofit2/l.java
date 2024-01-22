package retrofit2;

import com.android.volley.toolbox.HttpClientStack;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import kotlin.coroutines.Continuation;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Tag;
import retrofit2.http.Url;
import retrofit2.i;
/* loaded from: classes13.dex */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    public final Method f15641a;
    public final HttpUrl b;
    public final String c;
    @Nullable
    public final String d;
    @Nullable
    public final Headers e;
    @Nullable
    public final MediaType f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final i<?>[] j;
    public final boolean k;

    /* loaded from: classes13.dex */
    public static final class a {
        public static final Pattern x = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
        public static final Pattern y = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");

        /* renamed from: a  reason: collision with root package name */
        public final Retrofit f15642a;
        public final Method b;
        public final Annotation[] c;
        public final Annotation[][] d;
        public final Type[] e;
        public boolean f;
        public boolean g;
        public boolean h;
        public boolean i;
        public boolean j;
        public boolean k;
        public boolean l;
        public boolean m;
        @Nullable
        public String n;
        public boolean o;
        public boolean p;
        public boolean q;
        @Nullable
        public String r;
        @Nullable
        public Headers s;
        @Nullable
        public MediaType t;
        @Nullable
        public Set<String> u;
        @Nullable
        public i<?>[] v;
        public boolean w;

        public a(Retrofit retrofit, Method method) {
            this.f15642a = retrofit;
            this.b = method;
            this.c = method.getAnnotations();
            this.e = method.getGenericParameterTypes();
            this.d = method.getParameterAnnotations();
        }

        public static Class<?> a(Class<?> cls) {
            return Boolean.TYPE == cls ? Boolean.class : Byte.TYPE == cls ? Byte.class : Character.TYPE == cls ? Character.class : Double.TYPE == cls ? Double.class : Float.TYPE == cls ? Float.class : Integer.TYPE == cls ? Integer.class : Long.TYPE == cls ? Long.class : Short.TYPE == cls ? Short.class : cls;
        }

        public static Set<String> h(String str) {
            Matcher matcher = x.matcher(str);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            while (matcher.find()) {
                linkedHashSet.add(matcher.group(1));
            }
            return linkedHashSet;
        }

        public l b() {
            for (Annotation annotation : this.c) {
                e(annotation);
            }
            if (this.n != null) {
                if (!this.o) {
                    if (!this.q) {
                        if (this.p) {
                            throw o.m(this.b, "FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                        }
                    } else {
                        throw o.m(this.b, "Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                    }
                }
                int length = this.d.length;
                this.v = new i[length];
                int i = length - 1;
                int i2 = 0;
                while (true) {
                    boolean z = true;
                    if (i2 >= length) {
                        break;
                    }
                    i<?>[] iVarArr = this.v;
                    Type type = this.e[i2];
                    Annotation[] annotationArr = this.d[i2];
                    if (i2 != i) {
                        z = false;
                    }
                    iVarArr[i2] = f(i2, type, annotationArr, z);
                    i2++;
                }
                if (this.r != null || this.m) {
                    boolean z2 = this.p;
                    if (!z2 && !this.q && !this.o && this.h) {
                        throw o.m(this.b, "Non-body HTTP method cannot contain @Body.", new Object[0]);
                    }
                    if (z2 && !this.f) {
                        throw o.m(this.b, "Form-encoded method must contain at least one @Field.", new Object[0]);
                    }
                    if (this.q && !this.g) {
                        throw o.m(this.b, "Multipart method must contain at least one @Part.", new Object[0]);
                    }
                    return new l(this);
                }
                throw o.m(this.b, "Missing either @%s URL or @Url parameter.", this.n);
            }
            throw o.m(this.b, "HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
        }

        public final Headers c(String[] strArr) {
            Headers.Builder builder = new Headers.Builder();
            for (String str : strArr) {
                int indexOf = str.indexOf(58);
                if (indexOf == -1 || indexOf == 0 || indexOf == str.length() - 1) {
                    throw o.m(this.b, "@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
                }
                String substring = str.substring(0, indexOf);
                String trim = str.substring(indexOf + 1).trim();
                if ("Content-Type".equalsIgnoreCase(substring)) {
                    try {
                        this.t = MediaType.get(trim);
                    } catch (IllegalArgumentException e) {
                        throw o.n(this.b, e, "Malformed content type: %s", trim);
                    }
                } else {
                    builder.add(substring, trim);
                }
            }
            return builder.build();
        }

        public final void d(String str, String str2, boolean z) {
            String str3 = this.n;
            if (str3 != null) {
                throw o.m(this.b, "Only one HTTP method is allowed. Found: %s and %s.", str3, str);
            }
            this.n = str;
            this.o = z;
            if (str2.isEmpty()) {
                return;
            }
            int indexOf = str2.indexOf(63);
            if (indexOf != -1 && indexOf < str2.length() - 1) {
                String substring = str2.substring(indexOf + 1);
                if (x.matcher(substring).find()) {
                    throw o.m(this.b, "URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", substring);
                }
            }
            this.r = str2;
            this.u = h(str2);
        }

        public final void e(Annotation annotation) {
            if (annotation instanceof DELETE) {
                d("DELETE", ((DELETE) annotation).value(), false);
            } else if (annotation instanceof GET) {
                d("GET", ((GET) annotation).value(), false);
            } else if (annotation instanceof HEAD) {
                d("HEAD", ((HEAD) annotation).value(), false);
            } else if (annotation instanceof PATCH) {
                d(HttpClientStack.HttpPatch.METHOD_NAME, ((PATCH) annotation).value(), true);
            } else if (annotation instanceof POST) {
                d("POST", ((POST) annotation).value(), true);
            } else if (annotation instanceof PUT) {
                d("PUT", ((PUT) annotation).value(), true);
            } else if (annotation instanceof OPTIONS) {
                d("OPTIONS", ((OPTIONS) annotation).value(), false);
            } else if (annotation instanceof HTTP) {
                HTTP http = (HTTP) annotation;
                d(http.method(), http.path(), http.hasBody());
            } else if (annotation instanceof retrofit2.http.Headers) {
                String[] value = ((retrofit2.http.Headers) annotation).value();
                if (value.length != 0) {
                    this.s = c(value);
                    return;
                }
                throw o.m(this.b, "@Headers annotation is empty.", new Object[0]);
            } else if (annotation instanceof Multipart) {
                if (!this.p) {
                    this.q = true;
                    return;
                }
                throw o.m(this.b, "Only one encoding annotation is allowed.", new Object[0]);
            } else if (annotation instanceof FormUrlEncoded) {
                if (!this.q) {
                    this.p = true;
                    return;
                }
                throw o.m(this.b, "Only one encoding annotation is allowed.", new Object[0]);
            }
        }

        @Nullable
        public final i<?> f(int i, Type type, @Nullable Annotation[] annotationArr, boolean z) {
            i<?> iVar;
            if (annotationArr != null) {
                iVar = null;
                for (Annotation annotation : annotationArr) {
                    i<?> g = g(i, type, annotationArr, annotation);
                    if (g != null) {
                        if (iVar != null) {
                            throw o.o(this.b, i, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                        }
                        iVar = g;
                    }
                }
            } else {
                iVar = null;
            }
            if (iVar == null) {
                if (z) {
                    try {
                        if (o.h(type) == Continuation.class) {
                            this.w = true;
                            return null;
                        }
                    } catch (NoClassDefFoundError unused) {
                    }
                }
                throw o.o(this.b, i, "No Retrofit annotation found.", new Object[0]);
            }
            return iVar;
        }

        @Nullable
        public final i<?> g(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (annotation instanceof Url) {
                j(i, type);
                if (!this.m) {
                    if (!this.i) {
                        if (!this.j) {
                            if (!this.k) {
                                if (!this.l) {
                                    if (this.r == null) {
                                        this.m = true;
                                        if (type != HttpUrl.class && type != String.class && type != URI.class && (!(type instanceof Class) || !"android.net.Uri".equals(((Class) type).getName()))) {
                                            throw o.o(this.b, i, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
                                        }
                                        return new i.p(this.b, i);
                                    }
                                    throw o.o(this.b, i, "@Url cannot be used with @%s URL", this.n);
                                }
                                throw o.o(this.b, i, "A @Url parameter must not come after a @QueryMap.", new Object[0]);
                            }
                            throw o.o(this.b, i, "A @Url parameter must not come after a @QueryName.", new Object[0]);
                        }
                        throw o.o(this.b, i, "A @Url parameter must not come after a @Query.", new Object[0]);
                    }
                    throw o.o(this.b, i, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                throw o.o(this.b, i, "Multiple @Url method annotations found.", new Object[0]);
            } else if (annotation instanceof Path) {
                j(i, type);
                if (!this.j) {
                    if (!this.k) {
                        if (!this.l) {
                            if (!this.m) {
                                if (this.r != null) {
                                    this.i = true;
                                    Path path = (Path) annotation;
                                    String value = path.value();
                                    i(i, value);
                                    return new i.k(this.b, i, value, this.f15642a.stringConverter(type, annotationArr), path.encoded());
                                }
                                throw o.o(this.b, i, "@Path can only be used with relative url on @%s", this.n);
                            }
                            throw o.o(this.b, i, "@Path parameters may not be used with @Url.", new Object[0]);
                        }
                        throw o.o(this.b, i, "A @Path parameter must not come after a @QueryMap.", new Object[0]);
                    }
                    throw o.o(this.b, i, "A @Path parameter must not come after a @QueryName.", new Object[0]);
                }
                throw o.o(this.b, i, "A @Path parameter must not come after a @Query.", new Object[0]);
            } else if (annotation instanceof Query) {
                j(i, type);
                Query query = (Query) annotation;
                String value2 = query.value();
                boolean encoded = query.encoded();
                Class<?> h = o.h(type);
                this.j = true;
                if (Iterable.class.isAssignableFrom(h)) {
                    if (type instanceof ParameterizedType) {
                        return new i.l(value2, this.f15642a.stringConverter(o.g(0, (ParameterizedType) type), annotationArr), encoded).c();
                    }
                    throw o.o(this.b, i, h.getSimpleName() + " must include generic type (e.g., " + h.getSimpleName() + "<String>)", new Object[0]);
                } else if (h.isArray()) {
                    return new i.l(value2, this.f15642a.stringConverter(a(h.getComponentType()), annotationArr), encoded).b();
                } else {
                    return new i.l(value2, this.f15642a.stringConverter(type, annotationArr), encoded);
                }
            } else if (annotation instanceof QueryName) {
                j(i, type);
                boolean encoded2 = ((QueryName) annotation).encoded();
                Class<?> h2 = o.h(type);
                this.k = true;
                if (Iterable.class.isAssignableFrom(h2)) {
                    if (type instanceof ParameterizedType) {
                        return new i.n(this.f15642a.stringConverter(o.g(0, (ParameterizedType) type), annotationArr), encoded2).c();
                    }
                    throw o.o(this.b, i, h2.getSimpleName() + " must include generic type (e.g., " + h2.getSimpleName() + "<String>)", new Object[0]);
                } else if (h2.isArray()) {
                    return new i.n(this.f15642a.stringConverter(a(h2.getComponentType()), annotationArr), encoded2).b();
                } else {
                    return new i.n(this.f15642a.stringConverter(type, annotationArr), encoded2);
                }
            } else if (annotation instanceof QueryMap) {
                j(i, type);
                Class<?> h3 = o.h(type);
                this.l = true;
                if (Map.class.isAssignableFrom(h3)) {
                    Type i2 = o.i(type, h3, Map.class);
                    if (i2 instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) i2;
                        Type g = o.g(0, parameterizedType);
                        if (String.class == g) {
                            return new i.m(this.b, i, this.f15642a.stringConverter(o.g(1, parameterizedType), annotationArr), ((QueryMap) annotation).encoded());
                        }
                        throw o.o(this.b, i, "@QueryMap keys must be of type String: " + g, new Object[0]);
                    }
                    throw o.o(this.b, i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw o.o(this.b, i, "@QueryMap parameter type must be Map.", new Object[0]);
            } else if (annotation instanceof Header) {
                j(i, type);
                String value3 = ((Header) annotation).value();
                Class<?> h4 = o.h(type);
                if (Iterable.class.isAssignableFrom(h4)) {
                    if (type instanceof ParameterizedType) {
                        return new i.f(value3, this.f15642a.stringConverter(o.g(0, (ParameterizedType) type), annotationArr)).c();
                    }
                    throw o.o(this.b, i, h4.getSimpleName() + " must include generic type (e.g., " + h4.getSimpleName() + "<String>)", new Object[0]);
                } else if (h4.isArray()) {
                    return new i.f(value3, this.f15642a.stringConverter(a(h4.getComponentType()), annotationArr)).b();
                } else {
                    return new i.f(value3, this.f15642a.stringConverter(type, annotationArr));
                }
            } else if (annotation instanceof HeaderMap) {
                if (type == Headers.class) {
                    return new i.h(this.b, i);
                }
                j(i, type);
                Class<?> h5 = o.h(type);
                if (Map.class.isAssignableFrom(h5)) {
                    Type i3 = o.i(type, h5, Map.class);
                    if (i3 instanceof ParameterizedType) {
                        ParameterizedType parameterizedType2 = (ParameterizedType) i3;
                        Type g2 = o.g(0, parameterizedType2);
                        if (String.class == g2) {
                            return new i.g(this.b, i, this.f15642a.stringConverter(o.g(1, parameterizedType2), annotationArr));
                        }
                        throw o.o(this.b, i, "@HeaderMap keys must be of type String: " + g2, new Object[0]);
                    }
                    throw o.o(this.b, i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw o.o(this.b, i, "@HeaderMap parameter type must be Map.", new Object[0]);
            } else if (annotation instanceof Field) {
                j(i, type);
                if (this.p) {
                    Field field = (Field) annotation;
                    String value4 = field.value();
                    boolean encoded3 = field.encoded();
                    this.f = true;
                    Class<?> h6 = o.h(type);
                    if (Iterable.class.isAssignableFrom(h6)) {
                        if (type instanceof ParameterizedType) {
                            return new i.d(value4, this.f15642a.stringConverter(o.g(0, (ParameterizedType) type), annotationArr), encoded3).c();
                        }
                        throw o.o(this.b, i, h6.getSimpleName() + " must include generic type (e.g., " + h6.getSimpleName() + "<String>)", new Object[0]);
                    } else if (h6.isArray()) {
                        return new i.d(value4, this.f15642a.stringConverter(a(h6.getComponentType()), annotationArr), encoded3).b();
                    } else {
                        return new i.d(value4, this.f15642a.stringConverter(type, annotationArr), encoded3);
                    }
                }
                throw o.o(this.b, i, "@Field parameters can only be used with form encoding.", new Object[0]);
            } else if (annotation instanceof FieldMap) {
                j(i, type);
                if (this.p) {
                    Class<?> h7 = o.h(type);
                    if (Map.class.isAssignableFrom(h7)) {
                        Type i4 = o.i(type, h7, Map.class);
                        if (i4 instanceof ParameterizedType) {
                            ParameterizedType parameterizedType3 = (ParameterizedType) i4;
                            Type g3 = o.g(0, parameterizedType3);
                            if (String.class == g3) {
                                Converter stringConverter = this.f15642a.stringConverter(o.g(1, parameterizedType3), annotationArr);
                                this.f = true;
                                return new i.e(this.b, i, stringConverter, ((FieldMap) annotation).encoded());
                            }
                            throw o.o(this.b, i, "@FieldMap keys must be of type String: " + g3, new Object[0]);
                        }
                        throw o.o(this.b, i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw o.o(this.b, i, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                throw o.o(this.b, i, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
            } else if (annotation instanceof Part) {
                j(i, type);
                if (this.q) {
                    Part part = (Part) annotation;
                    this.g = true;
                    String value5 = part.value();
                    Class<?> h8 = o.h(type);
                    if (value5.isEmpty()) {
                        if (Iterable.class.isAssignableFrom(h8)) {
                            if (type instanceof ParameterizedType) {
                                if (MultipartBody.Part.class.isAssignableFrom(o.h(o.g(0, (ParameterizedType) type)))) {
                                    return i.o.f15635a.c();
                                }
                                throw o.o(this.b, i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                            }
                            throw o.o(this.b, i, h8.getSimpleName() + " must include generic type (e.g., " + h8.getSimpleName() + "<String>)", new Object[0]);
                        } else if (h8.isArray()) {
                            if (MultipartBody.Part.class.isAssignableFrom(h8.getComponentType())) {
                                return i.o.f15635a.b();
                            }
                            throw o.o(this.b, i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        } else if (MultipartBody.Part.class.isAssignableFrom(h8)) {
                            return i.o.f15635a;
                        } else {
                            throw o.o(this.b, i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        }
                    }
                    Headers of = Headers.of(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + value5 + "\"", "Content-Transfer-Encoding", part.encoding());
                    if (Iterable.class.isAssignableFrom(h8)) {
                        if (type instanceof ParameterizedType) {
                            Type g4 = o.g(0, (ParameterizedType) type);
                            if (!MultipartBody.Part.class.isAssignableFrom(o.h(g4))) {
                                return new i.C0922i(this.b, i, of, this.f15642a.requestBodyConverter(g4, annotationArr, this.c)).c();
                            }
                            throw o.o(this.b, i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        }
                        throw o.o(this.b, i, h8.getSimpleName() + " must include generic type (e.g., " + h8.getSimpleName() + "<String>)", new Object[0]);
                    } else if (h8.isArray()) {
                        Class<?> a2 = a(h8.getComponentType());
                        if (!MultipartBody.Part.class.isAssignableFrom(a2)) {
                            return new i.C0922i(this.b, i, of, this.f15642a.requestBodyConverter(a2, annotationArr, this.c)).b();
                        }
                        throw o.o(this.b, i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    } else if (!MultipartBody.Part.class.isAssignableFrom(h8)) {
                        return new i.C0922i(this.b, i, of, this.f15642a.requestBodyConverter(type, annotationArr, this.c));
                    } else {
                        throw o.o(this.b, i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                }
                throw o.o(this.b, i, "@Part parameters can only be used with multipart encoding.", new Object[0]);
            } else if (annotation instanceof PartMap) {
                j(i, type);
                if (this.q) {
                    this.g = true;
                    Class<?> h9 = o.h(type);
                    if (Map.class.isAssignableFrom(h9)) {
                        Type i5 = o.i(type, h9, Map.class);
                        if (i5 instanceof ParameterizedType) {
                            ParameterizedType parameterizedType4 = (ParameterizedType) i5;
                            Type g5 = o.g(0, parameterizedType4);
                            if (String.class == g5) {
                                Type g6 = o.g(1, parameterizedType4);
                                if (!MultipartBody.Part.class.isAssignableFrom(o.h(g6))) {
                                    return new i.j(this.b, i, this.f15642a.requestBodyConverter(g6, annotationArr, this.c), ((PartMap) annotation).encoding());
                                }
                                throw o.o(this.b, i, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                            }
                            throw o.o(this.b, i, "@PartMap keys must be of type String: " + g5, new Object[0]);
                        }
                        throw o.o(this.b, i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw o.o(this.b, i, "@PartMap parameter type must be Map.", new Object[0]);
                }
                throw o.o(this.b, i, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
            } else if (annotation instanceof Body) {
                j(i, type);
                if (!this.p && !this.q) {
                    if (!this.h) {
                        try {
                            Converter requestBodyConverter = this.f15642a.requestBodyConverter(type, annotationArr, this.c);
                            this.h = true;
                            return new i.c(this.b, i, requestBodyConverter);
                        } catch (RuntimeException e) {
                            throw o.p(this.b, e, i, "Unable to create @Body converter for %s", type);
                        }
                    }
                    throw o.o(this.b, i, "Multiple @Body method annotations found.", new Object[0]);
                }
                throw o.o(this.b, i, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
            } else if (annotation instanceof Tag) {
                j(i, type);
                Class<?> h10 = o.h(type);
                for (int i6 = i - 1; i6 >= 0; i6--) {
                    i<?> iVar = this.v[i6];
                    if ((iVar instanceof i.q) && ((i.q) iVar).f15637a.equals(h10)) {
                        throw o.o(this.b, i, "@Tag type " + h10.getName() + " is duplicate of parameter #" + (i6 + 1) + " and would always overwrite its value.", new Object[0]);
                    }
                }
                return new i.q(h10);
            } else {
                return null;
            }
        }

        public final void i(int i, String str) {
            if (y.matcher(str).matches()) {
                if (!this.u.contains(str)) {
                    throw o.o(this.b, i, "URL \"%s\" does not contain \"{%s}\".", this.r, str);
                }
                return;
            }
            throw o.o(this.b, i, "@Path parameter name must match %s. Found: %s", x.pattern(), str);
        }

        public final void j(int i, Type type) {
            if (o.j(type)) {
                throw o.o(this.b, i, "Parameter type must not include a type variable or wildcard: %s", type);
            }
        }
    }

    public l(a aVar) {
        this.f15641a = aVar.b;
        this.b = aVar.f15642a.c;
        this.c = aVar.n;
        this.d = aVar.r;
        this.e = aVar.s;
        this.f = aVar.t;
        this.g = aVar.o;
        this.h = aVar.p;
        this.i = aVar.q;
        this.j = aVar.v;
        this.k = aVar.w;
    }

    public static l b(Retrofit retrofit, Method method) {
        return new a(retrofit, method).b();
    }

    public Request a(Object[] objArr) throws IOException {
        i<?>[] iVarArr = this.j;
        int length = objArr.length;
        if (length == iVarArr.length) {
            k kVar = new k(this.c, this.b, this.d, this.e, this.f, this.g, this.h, this.i);
            if (this.k) {
                length--;
            }
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                arrayList.add(objArr[i]);
                iVarArr[i].a(kVar, objArr[i]);
            }
            return kVar.k().tag(Invocation.class, new Invocation(this.f15641a, arrayList)).build();
        }
        throw new IllegalArgumentException("Argument count (" + length + ") doesn't match expected count (" + iVarArr.length + ")");
    }
}
