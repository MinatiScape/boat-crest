package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.pool.GlideTrace;
import java.io.File;
import java.util.List;
/* loaded from: classes2.dex */
public class o implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    public final DataFetcherGenerator.FetcherReadyCallback h;
    public final f<?> i;
    public int j;
    public int k = -1;
    public Key l;
    public List<ModelLoader<File, ?>> m;
    public int n;
    public volatile ModelLoader.LoadData<?> o;
    public File p;
    public p q;

    public o(f<?> fVar, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.i = fVar;
        this.h = fetcherReadyCallback;
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public boolean a() {
        GlideTrace.beginSection("ResourceCacheGenerator.startNext");
        try {
            List<Key> c = this.i.c();
            boolean z = false;
            if (c.isEmpty()) {
                return false;
            }
            List<Class<?>> m = this.i.m();
            if (m.isEmpty()) {
                if (File.class.equals(this.i.r())) {
                    return false;
                }
                throw new IllegalStateException("Failed to find any load path from " + this.i.i() + " to " + this.i.r());
            }
            while (true) {
                if (this.m != null && b()) {
                    this.o = null;
                    while (!z && b()) {
                        List<ModelLoader<File, ?>> list = this.m;
                        int i = this.n;
                        this.n = i + 1;
                        this.o = list.get(i).buildLoadData(this.p, this.i.t(), this.i.f(), this.i.k());
                        if (this.o != null && this.i.u(this.o.fetcher.getDataClass())) {
                            this.o.fetcher.loadData(this.i.l(), this);
                            z = true;
                        }
                    }
                    return z;
                }
                int i2 = this.k + 1;
                this.k = i2;
                if (i2 >= m.size()) {
                    int i3 = this.j + 1;
                    this.j = i3;
                    if (i3 >= c.size()) {
                        return false;
                    }
                    this.k = 0;
                }
                Key key = c.get(this.j);
                Class<?> cls = m.get(this.k);
                this.q = new p(this.i.b(), key, this.i.p(), this.i.t(), this.i.f(), this.i.s(cls), cls, this.i.k());
                File file = this.i.d().get(this.q);
                this.p = file;
                if (file != null) {
                    this.l = key;
                    this.m = this.i.j(file);
                    this.n = 0;
                }
            }
        } finally {
            GlideTrace.endSection();
        }
    }

    public final boolean b() {
        return this.n < this.m.size();
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public void cancel() {
        ModelLoader.LoadData<?> loadData = this.o;
        if (loadData != null) {
            loadData.fetcher.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onDataReady(Object obj) {
        this.h.onDataFetcherReady(this.l, obj, this.o.fetcher, DataSource.RESOURCE_DISK_CACHE, this.q);
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(@NonNull Exception exc) {
        this.h.onDataFetcherFailed(this.q, exc, this.o.fetcher, DataSource.RESOURCE_DISK_CACHE);
    }
}
