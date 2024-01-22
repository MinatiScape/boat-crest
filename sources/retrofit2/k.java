package retrofit2;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
/* loaded from: classes13.dex */
public final class k {
    public static final char[] l = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final Pattern m = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");

    /* renamed from: a  reason: collision with root package name */
    public final String f15639a;
    public final HttpUrl b;
    @Nullable
    public String c;
    @Nullable
    public HttpUrl.Builder d;
    public final Request.Builder e = new Request.Builder();
    public final Headers.Builder f;
    @Nullable
    public MediaType g;
    public final boolean h;
    @Nullable
    public MultipartBody.Builder i;
    @Nullable
    public FormBody.Builder j;
    @Nullable
    public RequestBody k;

    /* loaded from: classes13.dex */
    public static class a extends RequestBody {

        /* renamed from: a  reason: collision with root package name */
        public final RequestBody f15640a;
        public final MediaType b;

        public a(RequestBody requestBody, MediaType mediaType) {
            this.f15640a = requestBody;
            this.b = mediaType;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() throws IOException {
            return this.f15640a.contentLength();
        }

        @Override // okhttp3.RequestBody
        public MediaType contentType() {
            return this.b;
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            this.f15640a.writeTo(bufferedSink);
        }
    }

    public k(String str, HttpUrl httpUrl, @Nullable String str2, @Nullable Headers headers, @Nullable MediaType mediaType, boolean z, boolean z2, boolean z3) {
        this.f15639a = str;
        this.b = httpUrl;
        this.c = str2;
        this.g = mediaType;
        this.h = z;
        if (headers != null) {
            this.f = headers.newBuilder();
        } else {
            this.f = new Headers.Builder();
        }
        if (z2) {
            this.j = new FormBody.Builder();
        } else if (z3) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            this.i = builder;
            builder.setType(MultipartBody.FORM);
        }
    }

    public static String i(String str, boolean z) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt >= 32 && codePointAt < 127 && " \"<>^`{}|\\?#".indexOf(codePointAt) == -1 && (z || (codePointAt != 47 && codePointAt != 37))) {
                i += Character.charCount(codePointAt);
            } else {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, 0, i);
                j(buffer, str, i, length, z);
                return buffer.readUtf8();
            }
        }
        return str;
    }

    public static void j(Buffer buffer, String str, int i, int i2, boolean z) {
        Buffer buffer2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!z || (codePointAt != 9 && codePointAt != 10 && codePointAt != 12 && codePointAt != 13)) {
                if (codePointAt >= 32 && codePointAt < 127 && " \"<>^`{}|\\?#".indexOf(codePointAt) == -1 && (z || (codePointAt != 47 && codePointAt != 37))) {
                    buffer.writeUtf8CodePoint(codePointAt);
                } else {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    buffer2.writeUtf8CodePoint(codePointAt);
                    while (!buffer2.exhausted()) {
                        int readByte = buffer2.readByte() & 255;
                        buffer.writeByte(37);
                        char[] cArr = l;
                        buffer.writeByte((int) cArr[(readByte >> 4) & 15]);
                        buffer.writeByte((int) cArr[readByte & 15]);
                    }
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    public void a(String str, String str2, boolean z) {
        if (z) {
            this.j.addEncoded(str, str2);
        } else {
            this.j.add(str, str2);
        }
    }

    public void b(String str, String str2) {
        if ("Content-Type".equalsIgnoreCase(str)) {
            try {
                this.g = MediaType.get(str2);
                return;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Malformed content type: " + str2, e);
            }
        }
        this.f.add(str, str2);
    }

    public void c(Headers headers) {
        this.f.addAll(headers);
    }

    public void d(Headers headers, RequestBody requestBody) {
        this.i.addPart(headers, requestBody);
    }

    public void e(MultipartBody.Part part) {
        this.i.addPart(part);
    }

    public void f(String str, String str2, boolean z) {
        if (this.c != null) {
            String i = i(str2, z);
            String str3 = this.c;
            String replace = str3.replace("{" + str + "}", i);
            if (!m.matcher(replace).matches()) {
                this.c = replace;
                return;
            }
            throw new IllegalArgumentException("@Path parameters shouldn't perform path traversal ('.' or '..'): " + str2);
        }
        throw new AssertionError();
    }

    public void g(String str, @Nullable String str2, boolean z) {
        String str3 = this.c;
        if (str3 != null) {
            HttpUrl.Builder newBuilder = this.b.newBuilder(str3);
            this.d = newBuilder;
            if (newBuilder != null) {
                this.c = null;
            } else {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.b + ", Relative: " + this.c);
            }
        }
        if (z) {
            this.d.addEncodedQueryParameter(str, str2);
        } else {
            this.d.addQueryParameter(str, str2);
        }
    }

    public <T> void h(Class<T> cls, @Nullable T t) {
        this.e.tag(cls, t);
    }

    public Request.Builder k() {
        HttpUrl resolve;
        HttpUrl.Builder builder = this.d;
        if (builder != null) {
            resolve = builder.build();
        } else {
            resolve = this.b.resolve(this.c);
            if (resolve == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.b + ", Relative: " + this.c);
            }
        }
        a aVar = this.k;
        if (aVar == null) {
            FormBody.Builder builder2 = this.j;
            if (builder2 != null) {
                aVar = builder2.build();
            } else {
                MultipartBody.Builder builder3 = this.i;
                if (builder3 != null) {
                    aVar = builder3.build();
                } else if (this.h) {
                    aVar = RequestBody.create((MediaType) null, new byte[0]);
                }
            }
        }
        MediaType mediaType = this.g;
        if (mediaType != null) {
            if (aVar != null) {
                aVar = new a(aVar, mediaType);
            } else {
                this.f.add("Content-Type", mediaType.toString());
            }
        }
        return this.e.url(resolve).headers(this.f.build()).method(this.f15639a, aVar);
    }

    public void l(RequestBody requestBody) {
        this.k = requestBody;
    }

    public void m(Object obj) {
        this.c = obj.toString();
    }
}
