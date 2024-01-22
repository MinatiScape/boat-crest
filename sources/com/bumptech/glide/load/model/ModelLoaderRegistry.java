package com.bumptech.glide.load.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pools;
import com.bumptech.glide.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class ModelLoaderRegistry {

    /* renamed from: a  reason: collision with root package name */
    public final MultiModelLoaderFactory f2412a;
    public final a b;

    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final Map<Class<?>, C0219a<?>> f2413a = new HashMap();

        /* renamed from: com.bumptech.glide.load.model.ModelLoaderRegistry$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static class C0219a<Model> {

            /* renamed from: a  reason: collision with root package name */
            public final List<ModelLoader<Model, ?>> f2414a;

            public C0219a(List<ModelLoader<Model, ?>> list) {
                this.f2414a = list;
            }
        }

        public void a() {
            this.f2413a.clear();
        }

        @Nullable
        public <Model> List<ModelLoader<Model, ?>> b(Class<Model> cls) {
            C0219a<?> c0219a = this.f2413a.get(cls);
            if (c0219a == null) {
                return null;
            }
            return (List<ModelLoader<Model, ?>>) c0219a.f2414a;
        }

        public <Model> void c(Class<Model> cls, List<ModelLoader<Model, ?>> list) {
            if (this.f2413a.put(cls, new C0219a<>(list)) == null) {
                return;
            }
            throw new IllegalStateException("Already cached loaders for model: " + cls);
        }
    }

    public ModelLoaderRegistry(@NonNull Pools.Pool<List<Throwable>> pool) {
        this(new MultiModelLoaderFactory(pool));
    }

    @NonNull
    public static <A> Class<A> a(@NonNull A a2) {
        return (Class<A>) a2.getClass();
    }

    public synchronized <Model, Data> void append(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        this.f2412a.b(cls, cls2, modelLoaderFactory);
        this.b.a();
    }

    @NonNull
    public final synchronized <A> List<ModelLoader<A, ?>> b(@NonNull Class<A> cls) {
        List<ModelLoader<A, ?>> b;
        b = this.b.b(cls);
        if (b == null) {
            b = Collections.unmodifiableList(this.f2412a.d(cls));
            this.b.c(cls, b);
        }
        return b;
    }

    public synchronized <Model, Data> ModelLoader<Model, Data> build(@NonNull Class<Model> cls, @NonNull Class<Data> cls2) {
        return this.f2412a.build(cls, cls2);
    }

    public final <Model, Data> void c(@NonNull List<ModelLoaderFactory<? extends Model, ? extends Data>> list) {
        for (ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory : list) {
            modelLoaderFactory.teardown();
        }
    }

    @NonNull
    public synchronized List<Class<?>> getDataClasses(@NonNull Class<?> cls) {
        return this.f2412a.f(cls);
    }

    @NonNull
    public <A> List<ModelLoader<A, ?>> getModelLoaders(@NonNull A a2) {
        List<ModelLoader<A, ?>> b = b(a(a2));
        if (!b.isEmpty()) {
            int size = b.size();
            List<ModelLoader<A, ?>> emptyList = Collections.emptyList();
            boolean z = true;
            for (int i = 0; i < size; i++) {
                ModelLoader<A, ?> modelLoader = b.get(i);
                if (modelLoader.handles(a2)) {
                    if (z) {
                        emptyList = new ArrayList<>(size - i);
                        z = false;
                    }
                    emptyList.add(modelLoader);
                }
            }
            if (emptyList.isEmpty()) {
                throw new Registry.NoModelLoaderAvailableException(a2, b);
            }
            return emptyList;
        }
        throw new Registry.NoModelLoaderAvailableException(a2);
    }

    public synchronized <Model, Data> void prepend(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        this.f2412a.h(cls, cls2, modelLoaderFactory);
        this.b.a();
    }

    public synchronized <Model, Data> void remove(@NonNull Class<Model> cls, @NonNull Class<Data> cls2) {
        c(this.f2412a.i(cls, cls2));
        this.b.a();
    }

    public synchronized <Model, Data> void replace(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        c(this.f2412a.j(cls, cls2, modelLoaderFactory));
        this.b.a();
    }

    public ModelLoaderRegistry(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
        this.b = new a();
        this.f2412a = multiModelLoaderFactory;
    }
}
