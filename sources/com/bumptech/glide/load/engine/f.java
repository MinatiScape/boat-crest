package com.bumptech.glide.load.engine;

import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.UnitTransformation;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public final class f<Transcode> {

    /* renamed from: a  reason: collision with root package name */
    public final List<ModelLoader.LoadData<?>> f2382a = new ArrayList();
    public final List<Key> b = new ArrayList();
    public GlideContext c;
    public Object d;
    public int e;
    public int f;
    public Class<?> g;
    public g.e h;
    public Options i;
    public Map<Class<?>, Transformation<?>> j;
    public Class<Transcode> k;
    public boolean l;
    public boolean m;
    public Key n;
    public Priority o;
    public DiskCacheStrategy p;
    public boolean q;
    public boolean r;

    public void a() {
        this.c = null;
        this.d = null;
        this.n = null;
        this.g = null;
        this.k = null;
        this.i = null;
        this.o = null;
        this.j = null;
        this.p = null;
        this.f2382a.clear();
        this.l = false;
        this.b.clear();
        this.m = false;
    }

    public ArrayPool b() {
        return this.c.getArrayPool();
    }

    public List<Key> c() {
        if (!this.m) {
            this.m = true;
            this.b.clear();
            List<ModelLoader.LoadData<?>> g = g();
            int size = g.size();
            for (int i = 0; i < size; i++) {
                ModelLoader.LoadData<?> loadData = g.get(i);
                if (!this.b.contains(loadData.sourceKey)) {
                    this.b.add(loadData.sourceKey);
                }
                for (int i2 = 0; i2 < loadData.alternateKeys.size(); i2++) {
                    if (!this.b.contains(loadData.alternateKeys.get(i2))) {
                        this.b.add(loadData.alternateKeys.get(i2));
                    }
                }
            }
        }
        return this.b;
    }

    public DiskCache d() {
        return this.h.a();
    }

    public DiskCacheStrategy e() {
        return this.p;
    }

    public int f() {
        return this.f;
    }

    public List<ModelLoader.LoadData<?>> g() {
        if (!this.l) {
            this.l = true;
            this.f2382a.clear();
            List modelLoaders = this.c.getRegistry().getModelLoaders(this.d);
            int size = modelLoaders.size();
            for (int i = 0; i < size; i++) {
                ModelLoader.LoadData<?> buildLoadData = ((ModelLoader) modelLoaders.get(i)).buildLoadData(this.d, this.e, this.f, this.i);
                if (buildLoadData != null) {
                    this.f2382a.add(buildLoadData);
                }
            }
        }
        return this.f2382a;
    }

    public <Data> LoadPath<Data, ?, Transcode> h(Class<Data> cls) {
        return this.c.getRegistry().getLoadPath(cls, this.g, this.k);
    }

    public Class<?> i() {
        return this.d.getClass();
    }

    public List<ModelLoader<File, ?>> j(File file) throws Registry.NoModelLoaderAvailableException {
        return this.c.getRegistry().getModelLoaders(file);
    }

    public Options k() {
        return this.i;
    }

    public Priority l() {
        return this.o;
    }

    public List<Class<?>> m() {
        return this.c.getRegistry().getRegisteredResourceClasses(this.d.getClass(), this.g, this.k);
    }

    public <Z> ResourceEncoder<Z> n(Resource<Z> resource) {
        return this.c.getRegistry().getResultEncoder(resource);
    }

    public <T> DataRewinder<T> o(T t) {
        return this.c.getRegistry().getRewinder(t);
    }

    public Key p() {
        return this.n;
    }

    public <X> Encoder<X> q(X x) throws Registry.NoSourceEncoderAvailableException {
        return this.c.getRegistry().getSourceEncoder(x);
    }

    public Class<?> r() {
        return (Class<Transcode>) this.k;
    }

    public <Z> Transformation<Z> s(Class<Z> cls) {
        Transformation<Z> transformation = (Transformation<Z>) this.j.get(cls);
        if (transformation == null) {
            Iterator<Map.Entry<Class<?>, Transformation<?>>> it = this.j.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Class<?>, Transformation<?>> next = it.next();
                if (next.getKey().isAssignableFrom(cls)) {
                    transformation = (Transformation<Z>) next.getValue();
                    break;
                }
            }
        }
        if (transformation == null) {
            if (this.j.isEmpty() && this.q) {
                throw new IllegalArgumentException("Missing transformation for " + cls + ". If you wish to ignore unknown resource types, use the optional transformation methods.");
            }
            return UnitTransformation.get();
        }
        return transformation;
    }

    public int t() {
        return this.e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean u(Class<?> cls) {
        return h(cls) != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <R> void v(GlideContext glideContext, Object obj, Key key, int i, int i2, DiskCacheStrategy diskCacheStrategy, Class<?> cls, Class<R> cls2, Priority priority, Options options, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, g.e eVar) {
        this.c = glideContext;
        this.d = obj;
        this.n = key;
        this.e = i;
        this.f = i2;
        this.p = diskCacheStrategy;
        this.g = cls;
        this.h = eVar;
        this.k = cls2;
        this.o = priority;
        this.i = options;
        this.j = map;
        this.q = z;
        this.r = z2;
    }

    public boolean w(Resource<?> resource) {
        return this.c.getRegistry().isResourceEncoderAvailable(resource);
    }

    public boolean x() {
        return this.r;
    }

    public boolean y(Key key) {
        List<ModelLoader.LoadData<?>> g = g();
        int size = g.size();
        for (int i = 0; i < size; i++) {
            if (g.get(i).sourceKey.equals(key)) {
                return true;
            }
        }
        return false;
    }
}
