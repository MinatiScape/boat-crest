package com.bumptech.glide.load.engine;

import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.GlideTrace;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class g<R> implements DataFetcherGenerator.FetcherReadyCallback, Runnable, Comparable<g<?>>, FactoryPools.Poolable {
    public long A;
    public boolean B;
    public Object C;
    public Thread D;
    public Key E;
    public Key F;
    public Object G;
    public DataSource H;
    public DataFetcher<?> I;
    public volatile DataFetcherGenerator J;
    public volatile boolean K;
    public volatile boolean L;
    public boolean M;
    public final e k;
    public final Pools.Pool<g<?>> l;
    public GlideContext o;
    public Key p;
    public Priority q;
    public j r;
    public int s;
    public int t;
    public DiskCacheStrategy u;
    public Options v;
    public b<R> w;
    public int x;
    public h y;
    public EnumC0217g z;
    public final com.bumptech.glide.load.engine.f<R> h = new com.bumptech.glide.load.engine.f<>();
    public final List<Throwable> i = new ArrayList();
    public final StateVerifier j = StateVerifier.newInstance();
    public final d<?> m = new d<>();
    public final f n = new f();

    /* loaded from: classes2.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2383a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[EncodeStrategy.values().length];
            c = iArr;
            try {
                iArr[EncodeStrategy.SOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[EncodeStrategy.TRANSFORMED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[h.values().length];
            b = iArr2;
            try {
                iArr2[h.RESOURCE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[h.DATA_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[h.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[h.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[h.INITIALIZE.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[EnumC0217g.values().length];
            f2383a = iArr3;
            try {
                iArr3[EnumC0217g.INITIALIZE.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2383a[EnumC0217g.SWITCH_TO_SOURCE_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2383a[EnumC0217g.DECODE_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface b<R> {
        void a(g<?> gVar);

        void onLoadFailed(GlideException glideException);

        void onResourceReady(Resource<R> resource, DataSource dataSource, boolean z);
    }

    /* loaded from: classes2.dex */
    public final class c<Z> implements DecodePath.a<Z> {

        /* renamed from: a  reason: collision with root package name */
        public final DataSource f2384a;

        public c(DataSource dataSource) {
            this.f2384a = dataSource;
        }

        @Override // com.bumptech.glide.load.engine.DecodePath.a
        @NonNull
        public Resource<Z> a(@NonNull Resource<Z> resource) {
            return g.this.r(this.f2384a, resource);
        }
    }

    /* loaded from: classes2.dex */
    public static class d<Z> {

        /* renamed from: a  reason: collision with root package name */
        public Key f2385a;
        public ResourceEncoder<Z> b;
        public n<Z> c;

        public void a() {
            this.f2385a = null;
            this.b = null;
            this.c = null;
        }

        public void b(e eVar, Options options) {
            GlideTrace.beginSection("DecodeJob.encode");
            try {
                eVar.a().put(this.f2385a, new com.bumptech.glide.load.engine.e(this.b, this.c, options));
            } finally {
                this.c.d();
                GlideTrace.endSection();
            }
        }

        public boolean c() {
            return this.c != null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public <X> void d(Key key, ResourceEncoder<X> resourceEncoder, n<X> nVar) {
            this.f2385a = key;
            this.b = resourceEncoder;
            this.c = nVar;
        }
    }

    /* loaded from: classes2.dex */
    public interface e {
        DiskCache a();
    }

    /* loaded from: classes2.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        public boolean f2386a;
        public boolean b;
        public boolean c;

        public final boolean a(boolean z) {
            return (this.c || z || this.b) && this.f2386a;
        }

        public synchronized boolean b() {
            this.b = true;
            return a(false);
        }

        public synchronized boolean c() {
            this.c = true;
            return a(false);
        }

        public synchronized boolean d(boolean z) {
            this.f2386a = true;
            return a(z);
        }

        public synchronized void e() {
            this.b = false;
            this.f2386a = false;
            this.c = false;
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.g$g  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public enum EnumC0217g {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    /* loaded from: classes2.dex */
    public enum h {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    public g(e eVar, Pools.Pool<g<?>> pool) {
        this.k = eVar;
        this.l = pool;
    }

    public void a() {
        this.L = true;
        DataFetcherGenerator dataFetcherGenerator = this.J;
        if (dataFetcherGenerator != null) {
            dataFetcherGenerator.cancel();
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: b */
    public int compareTo(@NonNull g<?> gVar) {
        int i = i() - gVar.i();
        return i == 0 ? this.x - gVar.x : i;
    }

    public final <Data> Resource<R> c(DataFetcher<?> dataFetcher, Data data, DataSource dataSource) throws GlideException {
        if (data == null) {
            return null;
        }
        try {
            long logTime = LogTime.getLogTime();
            Resource<R> d2 = d(data, dataSource);
            if (Log.isLoggable("DecodeJob", 2)) {
                k("Decoded result " + d2, logTime);
            }
            return d2;
        } finally {
            dataFetcher.cleanup();
        }
    }

    public final <Data> Resource<R> d(Data data, DataSource dataSource) throws GlideException {
        return w(data, dataSource, (LoadPath<Data, ?, R>) this.h.h(data.getClass()));
    }

    public final void e() {
        if (Log.isLoggable("DecodeJob", 2)) {
            long j = this.A;
            l("Retrieved data", j, "data: " + this.G + ", cache key: " + this.E + ", fetcher: " + this.I);
        }
        Resource<R> resource = null;
        try {
            resource = c(this.I, this.G, this.H);
        } catch (GlideException e2) {
            e2.setLoggingDetails(this.F, this.H);
            this.i.add(e2);
        }
        if (resource != null) {
            n(resource, this.H, this.M);
        } else {
            v();
        }
    }

    public final DataFetcherGenerator f() {
        int i = a.b[this.y.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return null;
                    }
                    throw new IllegalStateException("Unrecognized stage: " + this.y);
                }
                return new r(this.h, this);
            }
            return new com.bumptech.glide.load.engine.c(this.h, this);
        }
        return new o(this.h, this);
    }

    public final h g(h hVar) {
        int i = a.b[hVar.ordinal()];
        if (i == 1) {
            if (this.u.decodeCachedData()) {
                return h.DATA_CACHE;
            }
            return g(h.DATA_CACHE);
        } else if (i == 2) {
            return this.B ? h.FINISHED : h.SOURCE;
        } else if (i == 3 || i == 4) {
            return h.FINISHED;
        } else {
            if (i == 5) {
                if (this.u.decodeCachedResource()) {
                    return h.RESOURCE_CACHE;
                }
                return g(h.RESOURCE_CACHE);
            }
            throw new IllegalArgumentException("Unrecognized stage: " + hVar);
        }
    }

    @Override // com.bumptech.glide.util.pool.FactoryPools.Poolable
    @NonNull
    public StateVerifier getVerifier() {
        return this.j;
    }

    @NonNull
    public final Options h(DataSource dataSource) {
        Options options = this.v;
        if (Build.VERSION.SDK_INT < 26) {
            return options;
        }
        boolean z = dataSource == DataSource.RESOURCE_DISK_CACHE || this.h.x();
        Option<Boolean> option = Downsampler.ALLOW_HARDWARE_CONFIG;
        Boolean bool = (Boolean) options.get(option);
        if (bool == null || (bool.booleanValue() && !z)) {
            Options options2 = new Options();
            options2.putAll(this.v);
            options2.set(option, Boolean.valueOf(z));
            return options2;
        }
        return options;
    }

    public final int i() {
        return this.q.ordinal();
    }

    public g<R> j(GlideContext glideContext, Object obj, j jVar, Key key, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, boolean z3, Options options, b<R> bVar, int i3) {
        this.h.v(glideContext, obj, key, i, i2, diskCacheStrategy, cls, cls2, priority, options, map, z, z2, this.k);
        this.o = glideContext;
        this.p = key;
        this.q = priority;
        this.r = jVar;
        this.s = i;
        this.t = i2;
        this.u = diskCacheStrategy;
        this.B = z3;
        this.v = options;
        this.w = bVar;
        this.x = i3;
        this.z = EnumC0217g.INITIALIZE;
        this.C = obj;
        return this;
    }

    public final void k(String str, long j) {
        l(str, j, null);
    }

    public final void l(String str, long j, String str2) {
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" in ");
        sb.append(LogTime.getElapsedMillis(j));
        sb.append(", load key: ");
        sb.append(this.r);
        if (str2 != null) {
            str3 = ", " + str2;
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(", thread: ");
        sb.append(Thread.currentThread().getName());
        Log.v("DecodeJob", sb.toString());
    }

    public final void m(Resource<R> resource, DataSource dataSource, boolean z) {
        y();
        this.w.onResourceReady(resource, dataSource, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void n(Resource<R> resource, DataSource dataSource, boolean z) {
        GlideTrace.beginSection("DecodeJob.notifyEncodeAndRelease");
        try {
            if (resource instanceof Initializable) {
                ((Initializable) resource).initialize();
            }
            n nVar = 0;
            if (this.m.c()) {
                resource = n.b(resource);
                nVar = resource;
            }
            m(resource, dataSource, z);
            this.y = h.ENCODE;
            if (this.m.c()) {
                this.m.b(this.k, this.v);
            }
            if (nVar != 0) {
                nVar.d();
            }
            p();
        } finally {
            GlideTrace.endSection();
        }
    }

    public final void o() {
        y();
        this.w.onLoadFailed(new GlideException("Failed to load resource", new ArrayList(this.i)));
        q();
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        dataFetcher.cleanup();
        GlideException glideException = new GlideException("Fetching data failed", exc);
        glideException.setLoggingDetails(key, dataSource, dataFetcher.getDataClass());
        this.i.add(glideException);
        if (Thread.currentThread() != this.D) {
            u(EnumC0217g.SWITCH_TO_SOURCE_SERVICE);
        } else {
            v();
        }
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherReady(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.E = key;
        this.G = obj;
        this.I = dataFetcher;
        this.H = dataSource;
        this.F = key2;
        this.M = key != this.h.c().get(0);
        if (Thread.currentThread() != this.D) {
            u(EnumC0217g.DECODE_DATA);
            return;
        }
        GlideTrace.beginSection("DecodeJob.decodeFromRetrievedData");
        try {
            e();
        } finally {
            GlideTrace.endSection();
        }
    }

    public final void p() {
        if (this.n.b()) {
            t();
        }
    }

    public final void q() {
        if (this.n.c()) {
            t();
        }
    }

    @NonNull
    public <Z> Resource<Z> r(DataSource dataSource, @NonNull Resource<Z> resource) {
        Resource<Z> resource2;
        Transformation<Z> transformation;
        EncodeStrategy encodeStrategy;
        Key dVar;
        Class<?> cls = resource.get().getClass();
        ResourceEncoder<Z> resourceEncoder = null;
        if (dataSource != DataSource.RESOURCE_DISK_CACHE) {
            Transformation<Z> s = this.h.s(cls);
            transformation = s;
            resource2 = s.transform(this.o, resource, this.s, this.t);
        } else {
            resource2 = resource;
            transformation = null;
        }
        if (!resource.equals(resource2)) {
            resource.recycle();
        }
        if (this.h.w(resource2)) {
            resourceEncoder = this.h.n(resource2);
            encodeStrategy = resourceEncoder.getEncodeStrategy(this.v);
        } else {
            encodeStrategy = EncodeStrategy.NONE;
        }
        ResourceEncoder resourceEncoder2 = resourceEncoder;
        if (this.u.isResourceCacheable(!this.h.y(this.E), dataSource, encodeStrategy)) {
            if (resourceEncoder2 != null) {
                int i = a.c[encodeStrategy.ordinal()];
                if (i == 1) {
                    dVar = new com.bumptech.glide.load.engine.d(this.E, this.p);
                } else if (i == 2) {
                    dVar = new p(this.h.b(), this.E, this.p, this.s, this.t, transformation, cls, this.v);
                } else {
                    throw new IllegalArgumentException("Unknown strategy: " + encodeStrategy);
                }
                n b2 = n.b(resource2);
                this.m.d(dVar, resourceEncoder2, b2);
                return b2;
            }
            throw new Registry.NoResultEncoderAvailableException(resource2.get().getClass());
        }
        return resource2;
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void reschedule() {
        u(EnumC0217g.SWITCH_TO_SOURCE_SERVICE);
    }

    @Override // java.lang.Runnable
    public void run() {
        GlideTrace.beginSectionFormat("DecodeJob#run(reason=%s, model=%s)", this.z, this.C);
        DataFetcher<?> dataFetcher = this.I;
        try {
            try {
                if (this.L) {
                    o();
                    if (dataFetcher != null) {
                        dataFetcher.cleanup();
                    }
                    GlideTrace.endSection();
                    return;
                }
                x();
                if (dataFetcher != null) {
                    dataFetcher.cleanup();
                }
                GlideTrace.endSection();
            } catch (com.bumptech.glide.load.engine.b e2) {
                throw e2;
            }
        }
    }

    public void s(boolean z) {
        if (this.n.d(z)) {
            t();
        }
    }

    public final void t() {
        this.n.e();
        this.m.a();
        this.h.a();
        this.K = false;
        this.o = null;
        this.p = null;
        this.v = null;
        this.q = null;
        this.r = null;
        this.w = null;
        this.y = null;
        this.J = null;
        this.D = null;
        this.E = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.A = 0L;
        this.L = false;
        this.C = null;
        this.i.clear();
        this.l.release(this);
    }

    public final void u(EnumC0217g enumC0217g) {
        this.z = enumC0217g;
        this.w.a(this);
    }

    public final void v() {
        this.D = Thread.currentThread();
        this.A = LogTime.getLogTime();
        boolean z = false;
        while (!this.L && this.J != null && !(z = this.J.a())) {
            this.y = g(this.y);
            this.J = f();
            if (this.y == h.SOURCE) {
                u(EnumC0217g.SWITCH_TO_SOURCE_SERVICE);
                return;
            }
        }
        if ((this.y == h.FINISHED || this.L) && !z) {
            o();
        }
    }

    public final <Data, ResourceType> Resource<R> w(Data data, DataSource dataSource, LoadPath<Data, ResourceType, R> loadPath) throws GlideException {
        Options h2 = h(dataSource);
        DataRewinder<Data> rewinder = this.o.getRegistry().getRewinder(data);
        try {
            return loadPath.load(rewinder, h2, this.s, this.t, new c(dataSource));
        } finally {
            rewinder.cleanup();
        }
    }

    public final void x() {
        int i = a.f2383a[this.z.ordinal()];
        if (i == 1) {
            this.y = g(h.INITIALIZE);
            this.J = f();
            v();
        } else if (i == 2) {
            v();
        } else if (i == 3) {
            e();
        } else {
            throw new IllegalStateException("Unrecognized run reason: " + this.z);
        }
    }

    public final void y() {
        Throwable th;
        this.j.throwIfRecycled();
        if (this.K) {
            if (this.i.isEmpty()) {
                th = null;
            } else {
                List<Throwable> list = this.i;
                th = list.get(list.size() - 1);
            }
            throw new IllegalStateException("Already notified", th);
        }
        this.K = true;
    }

    public boolean z() {
        h g = g(h.INITIALIZE);
        return g == h.RESOURCE_CACHE || g == h.DATA_CACHE;
    }
}
