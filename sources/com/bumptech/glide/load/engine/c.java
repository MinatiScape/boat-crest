package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;
/* loaded from: classes2.dex */
public class c implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    public final List<Key> h;
    public final f<?> i;
    public final DataFetcherGenerator.FetcherReadyCallback j;
    public int k;
    public Key l;
    public List<ModelLoader<File, ?>> m;
    public int n;
    public volatile ModelLoader.LoadData<?> o;
    public File p;

    public c(f<?> fVar, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this(fVar.c(), fVar, fetcherReadyCallback);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001b, code lost:
        if (b() == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        r0 = r7.m;
        r3 = r7.n;
        r7.n = r3 + 1;
        r7.o = r0.get(r3).buildLoadData(r7.p, r7.i.t(), r7.i.f(), r7.i.k());
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0047, code lost:
        if (r7.o == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0057, code lost:
        if (r7.i.u(r7.o.fetcher.getDataClass()) == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0059, code lost:
        r7.o.fetcher.loadData(r7.i.l(), r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0066, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x006b, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0012, code lost:
        r7.o = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0015, code lost:
        if (r1 != false) goto L30;
     */
    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a() {
        /*
            r7 = this;
            java.lang.String r0 = "DataCacheGenerator.startNext"
            com.bumptech.glide.util.pool.GlideTrace.beginSection(r0)
        L5:
            java.util.List<com.bumptech.glide.load.model.ModelLoader<java.io.File, ?>> r0 = r7.m     // Catch: java.lang.Throwable -> Lae
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L6c
            boolean r0 = r7.b()     // Catch: java.lang.Throwable -> Lae
            if (r0 != 0) goto L12
            goto L6c
        L12:
            r0 = 0
            r7.o = r0     // Catch: java.lang.Throwable -> Lae
        L15:
            if (r1 != 0) goto L68
            boolean r0 = r7.b()     // Catch: java.lang.Throwable -> Lae
            if (r0 == 0) goto L68
            java.util.List<com.bumptech.glide.load.model.ModelLoader<java.io.File, ?>> r0 = r7.m     // Catch: java.lang.Throwable -> Lae
            int r3 = r7.n     // Catch: java.lang.Throwable -> Lae
            int r4 = r3 + 1
            r7.n = r4     // Catch: java.lang.Throwable -> Lae
            java.lang.Object r0 = r0.get(r3)     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.model.ModelLoader r0 = (com.bumptech.glide.load.model.ModelLoader) r0     // Catch: java.lang.Throwable -> Lae
            java.io.File r3 = r7.p     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.engine.f<?> r4 = r7.i     // Catch: java.lang.Throwable -> Lae
            int r4 = r4.t()     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.engine.f<?> r5 = r7.i     // Catch: java.lang.Throwable -> Lae
            int r5 = r5.f()     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.engine.f<?> r6 = r7.i     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.Options r6 = r6.k()     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.model.ModelLoader$LoadData r0 = r0.buildLoadData(r3, r4, r5, r6)     // Catch: java.lang.Throwable -> Lae
            r7.o = r0     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r0 = r7.o     // Catch: java.lang.Throwable -> Lae
            if (r0 == 0) goto L15
            com.bumptech.glide.load.engine.f<?> r0 = r7.i     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r3 = r7.o     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.data.DataFetcher<Data> r3 = r3.fetcher     // Catch: java.lang.Throwable -> Lae
            java.lang.Class r3 = r3.getDataClass()     // Catch: java.lang.Throwable -> Lae
            boolean r0 = r0.u(r3)     // Catch: java.lang.Throwable -> Lae
            if (r0 == 0) goto L15
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r0 = r7.o     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.data.DataFetcher<Data> r0 = r0.fetcher     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.engine.f<?> r1 = r7.i     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.Priority r1 = r1.l()     // Catch: java.lang.Throwable -> Lae
            r0.loadData(r1, r7)     // Catch: java.lang.Throwable -> Lae
            r1 = r2
            goto L15
        L68:
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r1
        L6c:
            int r0 = r7.k     // Catch: java.lang.Throwable -> Lae
            int r0 = r0 + r2
            r7.k = r0     // Catch: java.lang.Throwable -> Lae
            java.util.List<com.bumptech.glide.load.Key> r2 = r7.h     // Catch: java.lang.Throwable -> Lae
            int r2 = r2.size()     // Catch: java.lang.Throwable -> Lae
            if (r0 < r2) goto L7d
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r1
        L7d:
            java.util.List<com.bumptech.glide.load.Key> r0 = r7.h     // Catch: java.lang.Throwable -> Lae
            int r2 = r7.k     // Catch: java.lang.Throwable -> Lae
            java.lang.Object r0 = r0.get(r2)     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.Key r0 = (com.bumptech.glide.load.Key) r0     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.engine.d r2 = new com.bumptech.glide.load.engine.d     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.engine.f<?> r3 = r7.i     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.Key r3 = r3.p()     // Catch: java.lang.Throwable -> Lae
            r2.<init>(r0, r3)     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.engine.f<?> r3 = r7.i     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.engine.cache.DiskCache r3 = r3.d()     // Catch: java.lang.Throwable -> Lae
            java.io.File r2 = r3.get(r2)     // Catch: java.lang.Throwable -> Lae
            r7.p = r2     // Catch: java.lang.Throwable -> Lae
            if (r2 == 0) goto L5
            r7.l = r0     // Catch: java.lang.Throwable -> Lae
            com.bumptech.glide.load.engine.f<?> r0 = r7.i     // Catch: java.lang.Throwable -> Lae
            java.util.List r0 = r0.j(r2)     // Catch: java.lang.Throwable -> Lae
            r7.m = r0     // Catch: java.lang.Throwable -> Lae
            r7.n = r1     // Catch: java.lang.Throwable -> Lae
            goto L5
        Lae:
            r0 = move-exception
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.c.a():boolean");
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
        this.j.onDataFetcherReady(this.l, obj, this.o.fetcher, DataSource.DATA_DISK_CACHE, this.l);
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(@NonNull Exception exc) {
        this.j.onDataFetcherFailed(this.l, exc, this.o.fetcher, DataSource.DATA_DISK_CACHE);
    }

    public c(List<Key> list, f<?> fVar, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.k = -1;
        this.h = list;
        this.i = fVar;
        this.j = fetcherReadyCallback;
    }
}
