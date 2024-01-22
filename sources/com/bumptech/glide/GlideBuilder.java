package com.bumptech.glide;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideExperiments;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class GlideBuilder {
    public Engine c;
    public BitmapPool d;
    public ArrayPool e;
    public MemoryCache f;
    public GlideExecutor g;
    public GlideExecutor h;
    public DiskCache.Factory i;
    public MemorySizeCalculator j;
    public ConnectivityMonitorFactory k;
    @Nullable
    public RequestManagerRetriever.RequestManagerFactory n;
    public GlideExecutor o;
    public boolean p;
    @Nullable
    public List<RequestListener<Object>> q;

    /* renamed from: a  reason: collision with root package name */
    public final Map<Class<?>, TransitionOptions<?, ?>> f2303a = new ArrayMap();
    public final GlideExperiments.a b = new GlideExperiments.a();
    public int l = 4;
    public Glide.RequestOptionsFactory m = new a(this);

    /* loaded from: classes.dex */
    public static final class LogRequestOrigins implements GlideExperiments.b {
    }

    /* loaded from: classes.dex */
    public static final class UseLifecycleInsteadOfInjectingFragments implements GlideExperiments.b {
    }

    /* loaded from: classes.dex */
    public static final class WaitForFramesAfterTrimMemory implements GlideExperiments.b {
    }

    /* loaded from: classes.dex */
    public class a implements Glide.RequestOptionsFactory {
        public a(GlideBuilder glideBuilder) {
        }

        @Override // com.bumptech.glide.Glide.RequestOptionsFactory
        @NonNull
        public RequestOptions build() {
            return new RequestOptions();
        }
    }

    /* loaded from: classes.dex */
    public class b implements Glide.RequestOptionsFactory {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ RequestOptions f2304a;

        public b(GlideBuilder glideBuilder, RequestOptions requestOptions) {
            this.f2304a = requestOptions;
        }

        @Override // com.bumptech.glide.Glide.RequestOptionsFactory
        @NonNull
        public RequestOptions build() {
            RequestOptions requestOptions = this.f2304a;
            return requestOptions != null ? requestOptions : new RequestOptions();
        }
    }

    /* loaded from: classes.dex */
    public static final class c implements GlideExperiments.b {
    }

    @NonNull
    public Glide a(@NonNull Context context, List<GlideModule> list, AppGlideModule appGlideModule) {
        if (this.g == null) {
            this.g = GlideExecutor.newSourceExecutor();
        }
        if (this.h == null) {
            this.h = GlideExecutor.newDiskCacheExecutor();
        }
        if (this.o == null) {
            this.o = GlideExecutor.newAnimationExecutor();
        }
        if (this.j == null) {
            this.j = new MemorySizeCalculator.Builder(context).build();
        }
        if (this.k == null) {
            this.k = new DefaultConnectivityMonitorFactory();
        }
        if (this.d == null) {
            int bitmapPoolSize = this.j.getBitmapPoolSize();
            if (bitmapPoolSize > 0) {
                this.d = new LruBitmapPool(bitmapPoolSize);
            } else {
                this.d = new BitmapPoolAdapter();
            }
        }
        if (this.e == null) {
            this.e = new LruArrayPool(this.j.getArrayPoolSizeInBytes());
        }
        if (this.f == null) {
            this.f = new LruResourceCache(this.j.getMemoryCacheSize());
        }
        if (this.i == null) {
            this.i = new InternalCacheDiskCacheFactory(context);
        }
        if (this.c == null) {
            this.c = new Engine(this.f, this.i, this.h, this.g, GlideExecutor.newUnlimitedSourceExecutor(), this.o, this.p);
        }
        List<RequestListener<Object>> list2 = this.q;
        if (list2 == null) {
            this.q = Collections.emptyList();
        } else {
            this.q = Collections.unmodifiableList(list2);
        }
        GlideExperiments c2 = this.b.c();
        return new Glide(context, this.c, this.f, this.d, this.e, new RequestManagerRetriever(this.n, c2), this.k, this.l, this.m, this.f2303a, this.q, list, appGlideModule, c2);
    }

    @NonNull
    public GlideBuilder addGlobalRequestListener(@NonNull RequestListener<Object> requestListener) {
        if (this.q == null) {
            this.q = new ArrayList();
        }
        this.q.add(requestListener);
        return this;
    }

    public void b(@Nullable RequestManagerRetriever.RequestManagerFactory requestManagerFactory) {
        this.n = requestManagerFactory;
    }

    @NonNull
    public GlideBuilder setAnimationExecutor(@Nullable GlideExecutor glideExecutor) {
        this.o = glideExecutor;
        return this;
    }

    @NonNull
    public GlideBuilder setArrayPool(@Nullable ArrayPool arrayPool) {
        this.e = arrayPool;
        return this;
    }

    @NonNull
    public GlideBuilder setBitmapPool(@Nullable BitmapPool bitmapPool) {
        this.d = bitmapPool;
        return this;
    }

    @NonNull
    public GlideBuilder setConnectivityMonitorFactory(@Nullable ConnectivityMonitorFactory connectivityMonitorFactory) {
        this.k = connectivityMonitorFactory;
        return this;
    }

    @NonNull
    public GlideBuilder setDefaultRequestOptions(@Nullable RequestOptions requestOptions) {
        return setDefaultRequestOptions(new b(this, requestOptions));
    }

    @NonNull
    public <T> GlideBuilder setDefaultTransitionOptions(@NonNull Class<T> cls, @Nullable TransitionOptions<?, T> transitionOptions) {
        this.f2303a.put(cls, transitionOptions);
        return this;
    }

    @NonNull
    public GlideBuilder setDiskCache(@Nullable DiskCache.Factory factory) {
        this.i = factory;
        return this;
    }

    @NonNull
    public GlideBuilder setDiskCacheExecutor(@Nullable GlideExecutor glideExecutor) {
        this.h = glideExecutor;
        return this;
    }

    public GlideBuilder setImageDecoderEnabledForBitmaps(boolean z) {
        this.b.d(new c(), z && Build.VERSION.SDK_INT >= 29);
        return this;
    }

    @NonNull
    public GlideBuilder setIsActiveResourceRetentionAllowed(boolean z) {
        this.p = z;
        return this;
    }

    @NonNull
    public GlideBuilder setLogLevel(int i) {
        if (i >= 2 && i <= 6) {
            this.l = i;
            return this;
        }
        throw new IllegalArgumentException("Log level must be one of Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, or Log.ERROR");
    }

    public GlideBuilder setLogRequestOrigins(boolean z) {
        this.b.d(new LogRequestOrigins(), z);
        return this;
    }

    @NonNull
    public GlideBuilder setMemoryCache(@Nullable MemoryCache memoryCache) {
        this.f = memoryCache;
        return this;
    }

    @NonNull
    public GlideBuilder setMemorySizeCalculator(@NonNull MemorySizeCalculator.Builder builder) {
        return setMemorySizeCalculator(builder.build());
    }

    @Deprecated
    public GlideBuilder setResizeExecutor(@Nullable GlideExecutor glideExecutor) {
        return setSourceExecutor(glideExecutor);
    }

    @NonNull
    public GlideBuilder setSourceExecutor(@Nullable GlideExecutor glideExecutor) {
        this.g = glideExecutor;
        return this;
    }

    public GlideBuilder useLifecycleInsteadOfInjectingFragments(boolean z) {
        this.b.d(new UseLifecycleInsteadOfInjectingFragments(), z);
        return this;
    }

    @NonNull
    public GlideBuilder setDefaultRequestOptions(@NonNull Glide.RequestOptionsFactory requestOptionsFactory) {
        this.m = (Glide.RequestOptionsFactory) Preconditions.checkNotNull(requestOptionsFactory);
        return this;
    }

    @NonNull
    public GlideBuilder setMemorySizeCalculator(@Nullable MemorySizeCalculator memorySizeCalculator) {
        this.j = memorySizeCalculator;
        return this;
    }
}
