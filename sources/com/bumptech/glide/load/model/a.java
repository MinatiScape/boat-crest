package com.bumptech.glide.load.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pools;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes2.dex */
public class a<Model, Data> implements ModelLoader<Model, Data> {

    /* renamed from: a  reason: collision with root package name */
    public final List<ModelLoader<Model, Data>> f2431a;
    public final Pools.Pool<List<Throwable>> b;

    /* renamed from: com.bumptech.glide.load.model.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0220a<Data> implements DataFetcher<Data>, DataFetcher.DataCallback<Data> {
        public final List<DataFetcher<Data>> h;
        public final Pools.Pool<List<Throwable>> i;
        public int j;
        public Priority k;
        public DataFetcher.DataCallback<? super Data> l;
        @Nullable
        public List<Throwable> m;
        public boolean n;

        public C0220a(@NonNull List<DataFetcher<Data>> list, @NonNull Pools.Pool<List<Throwable>> pool) {
            this.i = pool;
            Preconditions.checkNotEmpty(list);
            this.h = list;
            this.j = 0;
        }

        public final void a() {
            if (this.n) {
                return;
            }
            if (this.j < this.h.size() - 1) {
                this.j++;
                loadData(this.k, this.l);
                return;
            }
            Preconditions.checkNotNull(this.m);
            this.l.onLoadFailed(new GlideException("Fetch failed", new ArrayList(this.m)));
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cancel() {
            this.n = true;
            for (DataFetcher<Data> dataFetcher : this.h) {
                dataFetcher.cancel();
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cleanup() {
            List<Throwable> list = this.m;
            if (list != null) {
                this.i.release(list);
            }
            this.m = null;
            for (DataFetcher<Data> dataFetcher : this.h) {
                dataFetcher.cleanup();
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        @NonNull
        public Class<Data> getDataClass() {
            return this.h.get(0).getDataClass();
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        @NonNull
        public DataSource getDataSource() {
            return this.h.get(0).getDataSource();
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super Data> dataCallback) {
            this.k = priority;
            this.l = dataCallback;
            this.m = this.i.acquire();
            this.h.get(this.j).loadData(priority, this);
            if (this.n) {
                cancel();
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
        public void onDataReady(@Nullable Data data) {
            if (data != null) {
                this.l.onDataReady(data);
            } else {
                a();
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
        public void onLoadFailed(@NonNull Exception exc) {
            ((List) Preconditions.checkNotNull(this.m)).add(exc);
            a();
        }
    }

    public a(@NonNull List<ModelLoader<Model, Data>> list, @NonNull Pools.Pool<List<Throwable>> pool) {
        this.f2431a = list;
        this.b = pool;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Data> buildLoadData(@NonNull Model model, int i, int i2, @NonNull Options options) {
        ModelLoader.LoadData<Data> buildLoadData;
        int size = this.f2431a.size();
        ArrayList arrayList = new ArrayList(size);
        Key key = null;
        for (int i3 = 0; i3 < size; i3++) {
            ModelLoader<Model, Data> modelLoader = this.f2431a.get(i3);
            if (modelLoader.handles(model) && (buildLoadData = modelLoader.buildLoadData(model, i, i2, options)) != null) {
                key = buildLoadData.sourceKey;
                arrayList.add(buildLoadData.fetcher);
            }
        }
        if (arrayList.isEmpty() || key == null) {
            return null;
        }
        return new ModelLoader.LoadData<>(key, new C0220a(arrayList, this.b));
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public boolean handles(@NonNull Model model) {
        for (ModelLoader<Model, Data> modelLoader : this.f2431a) {
            if (modelLoader.handles(model)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "MultiModelLoader{modelLoaders=" + Arrays.toString(this.f2431a.toArray()) + '}';
    }
}
