package retrofit2;

import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;
/* loaded from: classes13.dex */
public final class g<T> implements Call<T> {
    public final l h;
    public final Object[] i;
    public final Call.Factory j;
    public final Converter<ResponseBody, T> k;
    public volatile boolean l;
    @GuardedBy("this")
    @Nullable
    public okhttp3.Call m;
    @GuardedBy("this")
    @Nullable
    public Throwable n;
    @GuardedBy("this")
    public boolean o;

    /* loaded from: classes13.dex */
    public class a implements okhttp3.Callback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Callback f15618a;

        public a(Callback callback) {
            this.f15618a = callback;
        }

        public final void a(Throwable th) {
            try {
                this.f15618a.onFailure(g.this, th);
            } catch (Throwable th2) {
                o.s(th2);
                th2.printStackTrace();
            }
        }

        @Override // okhttp3.Callback
        public void onFailure(okhttp3.Call call, IOException iOException) {
            a(iOException);
        }

        @Override // okhttp3.Callback
        public void onResponse(okhttp3.Call call, okhttp3.Response response) {
            try {
                try {
                    this.f15618a.onResponse(g.this, g.this.d(response));
                } catch (Throwable th) {
                    o.s(th);
                    th.printStackTrace();
                }
            } catch (Throwable th2) {
                o.s(th2);
                a(th2);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class b extends ResponseBody {
        public final ResponseBody i;
        public final BufferedSource j;
        @Nullable
        public IOException k;

        /* loaded from: classes13.dex */
        public class a extends ForwardingSource {
            public a(Source source) {
                super(source);
            }

            @Override // okio.ForwardingSource, okio.Source
            public long read(Buffer buffer, long j) throws IOException {
                try {
                    return super.read(buffer, j);
                } catch (IOException e) {
                    b.this.k = e;
                    throw e;
                }
            }
        }

        public b(ResponseBody responseBody) {
            this.i = responseBody;
            this.j = Okio.buffer(new a(responseBody.source()));
        }

        public void b() throws IOException {
            IOException iOException = this.k;
            if (iOException != null) {
                throw iOException;
            }
        }

        @Override // okhttp3.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.i.close();
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.i.contentLength();
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.i.contentType();
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            return this.j;
        }
    }

    /* loaded from: classes13.dex */
    public static final class c extends ResponseBody {
        @Nullable
        public final MediaType i;
        public final long j;

        public c(@Nullable MediaType mediaType, long j) {
            this.i = mediaType;
            this.j = j;
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.j;
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.i;
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    public g(l lVar, Object[] objArr, Call.Factory factory, Converter<ResponseBody, T> converter) {
        this.h = lVar;
        this.i = objArr;
        this.j = factory;
        this.k = converter;
    }

    @Override // retrofit2.Call
    /* renamed from: a */
    public g<T> mo952clone() {
        return new g<>(this.h, this.i, this.j, this.k);
    }

    public final okhttp3.Call b() throws IOException {
        okhttp3.Call newCall = this.j.newCall(this.h.a(this.i));
        Objects.requireNonNull(newCall, "Call.Factory returned null.");
        return newCall;
    }

    @GuardedBy("this")
    public final okhttp3.Call c() throws IOException {
        okhttp3.Call call = this.m;
        if (call != null) {
            return call;
        }
        Throwable th = this.n;
        if (th != null) {
            if (!(th instanceof IOException)) {
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                throw ((Error) th);
            }
            throw ((IOException) th);
        }
        try {
            okhttp3.Call b2 = b();
            this.m = b2;
            return b2;
        } catch (IOException | Error | RuntimeException e) {
            o.s(e);
            this.n = e;
            throw e;
        }
    }

    @Override // retrofit2.Call
    public void cancel() {
        okhttp3.Call call;
        this.l = true;
        synchronized (this) {
            call = this.m;
        }
        if (call != null) {
            call.cancel();
        }
    }

    public Response<T> d(okhttp3.Response response) throws IOException {
        ResponseBody body = response.body();
        okhttp3.Response build = response.newBuilder().body(new c(body.contentType(), body.contentLength())).build();
        int code = build.code();
        if (code < 200 || code >= 300) {
            try {
                return Response.error(o.a(body), build);
            } finally {
                body.close();
            }
        } else if (code != 204 && code != 205) {
            b bVar = new b(body);
            try {
                return Response.success(this.k.convert(bVar), build);
            } catch (RuntimeException e) {
                bVar.b();
                throw e;
            }
        } else {
            body.close();
            return Response.success((Object) null, build);
        }
    }

    @Override // retrofit2.Call
    public void enqueue(Callback<T> callback) {
        okhttp3.Call call;
        Throwable th;
        Objects.requireNonNull(callback, "callback == null");
        synchronized (this) {
            if (!this.o) {
                this.o = true;
                call = this.m;
                th = this.n;
                if (call == null && th == null) {
                    okhttp3.Call b2 = b();
                    this.m = b2;
                    call = b2;
                }
            } else {
                throw new IllegalStateException("Already executed.");
            }
        }
        if (th != null) {
            callback.onFailure(this, th);
            return;
        }
        if (this.l) {
            call.cancel();
        }
        call.enqueue(new a(callback));
    }

    @Override // retrofit2.Call
    public Response<T> execute() throws IOException {
        okhttp3.Call c2;
        synchronized (this) {
            if (!this.o) {
                this.o = true;
                c2 = c();
            } else {
                throw new IllegalStateException("Already executed.");
            }
        }
        if (this.l) {
            c2.cancel();
        }
        return d(c2.execute());
    }

    @Override // retrofit2.Call
    public boolean isCanceled() {
        boolean z = true;
        if (this.l) {
            return true;
        }
        synchronized (this) {
            okhttp3.Call call = this.m;
            if (call == null || !call.isCanceled()) {
                z = false;
            }
        }
        return z;
    }

    @Override // retrofit2.Call
    public synchronized boolean isExecuted() {
        return this.o;
    }

    @Override // retrofit2.Call
    public synchronized Request request() {
        try {
        } catch (IOException e) {
            throw new RuntimeException("Unable to create request.", e);
        }
        return c().request();
    }

    @Override // retrofit2.Call
    public synchronized Timeout timeout() {
        try {
        } catch (IOException e) {
            throw new RuntimeException("Unable to create call.", e);
        }
        return c().timeout();
    }
}
