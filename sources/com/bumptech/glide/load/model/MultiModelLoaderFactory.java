package com.bumptech.glide.load.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pools;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* loaded from: classes2.dex */
public class MultiModelLoaderFactory {
    public static final c e = new c();
    public static final ModelLoader<Object, Object> f = new a();

    /* renamed from: a  reason: collision with root package name */
    public final List<b<?, ?>> f2415a;
    public final c b;
    public final Set<b<?, ?>> c;
    public final Pools.Pool<List<Throwable>> d;

    /* loaded from: classes2.dex */
    public static class a implements ModelLoader<Object, Object> {
        @Override // com.bumptech.glide.load.model.ModelLoader
        @Nullable
        public ModelLoader.LoadData<Object> buildLoadData(@NonNull Object obj, int i, int i2, @NonNull Options options) {
            return null;
        }

        @Override // com.bumptech.glide.load.model.ModelLoader
        public boolean handles(@NonNull Object obj) {
            return false;
        }
    }

    /* loaded from: classes2.dex */
    public static class b<Model, Data> {

        /* renamed from: a  reason: collision with root package name */
        public final Class<Model> f2416a;
        public final Class<Data> b;
        public final ModelLoaderFactory<? extends Model, ? extends Data> c;

        public b(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
            this.f2416a = cls;
            this.b = cls2;
            this.c = modelLoaderFactory;
        }

        public boolean a(@NonNull Class<?> cls) {
            return this.f2416a.isAssignableFrom(cls);
        }

        public boolean b(@NonNull Class<?> cls, @NonNull Class<?> cls2) {
            return a(cls) && this.b.isAssignableFrom(cls2);
        }
    }

    /* loaded from: classes2.dex */
    public static class c {
        @NonNull
        public <Model, Data> com.bumptech.glide.load.model.a<Model, Data> a(@NonNull List<ModelLoader<Model, Data>> list, @NonNull Pools.Pool<List<Throwable>> pool) {
            return new com.bumptech.glide.load.model.a<>(list, pool);
        }
    }

    public MultiModelLoaderFactory(@NonNull Pools.Pool<List<Throwable>> pool) {
        this(pool, e);
    }

    @NonNull
    public static <Model, Data> ModelLoader<Model, Data> e() {
        return (ModelLoader<Model, Data>) f;
    }

    public final <Model, Data> void a(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory, boolean z) {
        b<?, ?> bVar = new b<>(cls, cls2, modelLoaderFactory);
        List<b<?, ?>> list = this.f2415a;
        list.add(z ? list.size() : 0, bVar);
    }

    public synchronized <Model, Data> void b(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        a(cls, cls2, modelLoaderFactory, true);
    }

    @NonNull
    public synchronized <Model, Data> ModelLoader<Model, Data> build(@NonNull Class<Model> cls, @NonNull Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (b<?, ?> bVar : this.f2415a) {
                if (this.c.contains(bVar)) {
                    z = true;
                } else if (bVar.b(cls, cls2)) {
                    this.c.add(bVar);
                    arrayList.add(c(bVar));
                    this.c.remove(bVar);
                }
            }
            if (arrayList.size() > 1) {
                return this.b.a(arrayList, this.d);
            } else if (arrayList.size() == 1) {
                return (ModelLoader) arrayList.get(0);
            } else if (z) {
                return e();
            } else {
                throw new Registry.NoModelLoaderAvailableException((Class<?>) cls, (Class<?>) cls2);
            }
        } catch (Throwable th) {
            this.c.clear();
            throw th;
        }
    }

    @NonNull
    public final <Model, Data> ModelLoader<Model, Data> c(@NonNull b<?, ?> bVar) {
        return (ModelLoader) Preconditions.checkNotNull(bVar.c.build(this));
    }

    @NonNull
    public synchronized <Model> List<ModelLoader<Model, ?>> d(@NonNull Class<Model> cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (b<?, ?> bVar : this.f2415a) {
                if (!this.c.contains(bVar) && bVar.a(cls)) {
                    this.c.add(bVar);
                    arrayList.add(c(bVar));
                    this.c.remove(bVar);
                }
            }
        } catch (Throwable th) {
            this.c.clear();
            throw th;
        }
        return arrayList;
    }

    @NonNull
    public synchronized List<Class<?>> f(@NonNull Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (b<?, ?> bVar : this.f2415a) {
            if (!arrayList.contains(bVar.b) && bVar.a(cls)) {
                arrayList.add(bVar.b);
            }
        }
        return arrayList;
    }

    @NonNull
    public final <Model, Data> ModelLoaderFactory<Model, Data> g(@NonNull b<?, ?> bVar) {
        return (ModelLoaderFactory<Model, Data>) bVar.c;
    }

    public synchronized <Model, Data> void h(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        a(cls, cls2, modelLoaderFactory, false);
    }

    @NonNull
    public synchronized <Model, Data> List<ModelLoaderFactory<? extends Model, ? extends Data>> i(@NonNull Class<Model> cls, @NonNull Class<Data> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<b<?, ?>> it = this.f2415a.iterator();
        while (it.hasNext()) {
            b<?, ?> next = it.next();
            if (next.b(cls, cls2)) {
                it.remove();
                arrayList.add(g(next));
            }
        }
        return arrayList;
    }

    @NonNull
    public synchronized <Model, Data> List<ModelLoaderFactory<? extends Model, ? extends Data>> j(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        List<ModelLoaderFactory<? extends Model, ? extends Data>> i;
        i = i(cls, cls2);
        b(cls, cls2, modelLoaderFactory);
        return i;
    }

    @VisibleForTesting
    public MultiModelLoaderFactory(@NonNull Pools.Pool<List<Throwable>> pool, @NonNull c cVar) {
        this.f2415a = new ArrayList();
        this.c = new HashSet();
        this.d = pool;
        this.b = cVar;
    }
}
