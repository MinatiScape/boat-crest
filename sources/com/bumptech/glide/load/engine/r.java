package com.bumptech.glide.load.engine;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.LogTime;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public class r implements DataFetcherGenerator, DataFetcherGenerator.FetcherReadyCallback {
    public final f<?> h;
    public final DataFetcherGenerator.FetcherReadyCallback i;
    public volatile int j;
    public volatile c k;
    public volatile Object l;
    public volatile ModelLoader.LoadData<?> m;
    public volatile d n;

    /* loaded from: classes2.dex */
    public class a implements DataFetcher.DataCallback<Object> {
        public final /* synthetic */ ModelLoader.LoadData h;

        public a(ModelLoader.LoadData loadData) {
            this.h = loadData;
        }

        @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
        public void onDataReady(@Nullable Object obj) {
            if (r.this.d(this.h)) {
                r.this.e(this.h, obj);
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
        public void onLoadFailed(@NonNull Exception exc) {
            if (r.this.d(this.h)) {
                r.this.f(this.h, exc);
            }
        }
    }

    public r(f<?> fVar, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.h = fVar;
        this.i = fetcherReadyCallback;
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public boolean a() {
        if (this.l != null) {
            Object obj = this.l;
            this.l = null;
            try {
                if (!b(obj)) {
                    return true;
                }
            } catch (IOException e) {
                if (Log.isLoggable("SourceGenerator", 3)) {
                    Log.d("SourceGenerator", "Failed to properly rewind or write data to cache", e);
                }
            }
        }
        if (this.k == null || !this.k.a()) {
            this.k = null;
            this.m = null;
            boolean z = false;
            while (!z && c()) {
                List<ModelLoader.LoadData<?>> g = this.h.g();
                int i = this.j;
                this.j = i + 1;
                this.m = g.get(i);
                if (this.m != null && (this.h.e().isDataCacheable(this.m.fetcher.getDataSource()) || this.h.u(this.m.fetcher.getDataClass()))) {
                    g(this.m);
                    z = true;
                }
            }
            return z;
        }
        return true;
    }

    public final boolean b(Object obj) throws IOException {
        long logTime = LogTime.getLogTime();
        boolean z = true;
        try {
            DataRewinder<T> o = this.h.o(obj);
            Object rewindAndGet = o.rewindAndGet();
            Encoder<X> q = this.h.q(rewindAndGet);
            e eVar = new e(q, rewindAndGet, this.h.k());
            d dVar = new d(this.m.sourceKey, this.h.p());
            DiskCache d = this.h.d();
            d.put(dVar, eVar);
            if (Log.isLoggable("SourceGenerator", 2)) {
                Log.v("SourceGenerator", "Finished encoding source to cache, key: " + dVar + ", data: " + obj + ", encoder: " + q + ", duration: " + LogTime.getElapsedMillis(logTime));
            }
            if (d.get(dVar) != null) {
                this.n = dVar;
                this.k = new c(Collections.singletonList(this.m.sourceKey), this.h, this);
                this.m.fetcher.cleanup();
                return true;
            }
            if (Log.isLoggable("SourceGenerator", 3)) {
                Log.d("SourceGenerator", "Attempt to write: " + this.n + ", data: " + obj + " to the disk cache failed, maybe the disk cache is disabled? Trying to decode the data directly...");
            }
            try {
                this.i.onDataFetcherReady(this.m.sourceKey, o.rewindAndGet(), this.m.fetcher, this.m.fetcher.getDataSource(), this.m.sourceKey);
                return false;
            } catch (Throwable th) {
                th = th;
                if (!z) {
                    this.m.fetcher.cleanup();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            z = false;
        }
    }

    public final boolean c() {
        return this.j < this.h.g().size();
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public void cancel() {
        ModelLoader.LoadData<?> loadData = this.m;
        if (loadData != null) {
            loadData.fetcher.cancel();
        }
    }

    public boolean d(ModelLoader.LoadData<?> loadData) {
        ModelLoader.LoadData<?> loadData2 = this.m;
        return loadData2 != null && loadData2 == loadData;
    }

    public void e(ModelLoader.LoadData<?> loadData, Object obj) {
        DiskCacheStrategy e = this.h.e();
        if (obj != null && e.isDataCacheable(loadData.fetcher.getDataSource())) {
            this.l = obj;
            this.i.reschedule();
            return;
        }
        DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback = this.i;
        Key key = loadData.sourceKey;
        DataFetcher<?> dataFetcher = loadData.fetcher;
        fetcherReadyCallback.onDataFetcherReady(key, obj, dataFetcher, dataFetcher.getDataSource(), this.n);
    }

    public void f(ModelLoader.LoadData<?> loadData, @NonNull Exception exc) {
        DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback = this.i;
        d dVar = this.n;
        DataFetcher<?> dataFetcher = loadData.fetcher;
        fetcherReadyCallback.onDataFetcherFailed(dVar, exc, dataFetcher, dataFetcher.getDataSource());
    }

    public final void g(ModelLoader.LoadData<?> loadData) {
        this.m.fetcher.loadData(this.h.l(), new a(loadData));
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        this.i.onDataFetcherFailed(key, exc, dataFetcher, this.m.fetcher.getDataSource());
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherReady(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.i.onDataFetcherReady(key, obj, dataFetcher, this.m.fetcher.getDataSource(), key);
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void reschedule() {
        throw new UnsupportedOperationException();
    }
}
