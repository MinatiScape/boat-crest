package com.bumptech.glide.provider;

import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.load.engine.LoadPath;
import com.bumptech.glide.load.resource.transcode.UnitTranscoder;
import com.bumptech.glide.util.MultiClassKey;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
public class LoadPathCache {
    public static final LoadPath<?, ?, ?> c = new LoadPath<>(Object.class, Object.class, Object.class, Collections.singletonList(new DecodePath(Object.class, Object.class, Object.class, Collections.emptyList(), new UnitTranscoder(), null)), null);

    /* renamed from: a  reason: collision with root package name */
    public final ArrayMap<MultiClassKey, LoadPath<?, ?, ?>> f2519a = new ArrayMap<>();
    public final AtomicReference<MultiClassKey> b = new AtomicReference<>();

    public final MultiClassKey a(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        MultiClassKey andSet = this.b.getAndSet(null);
        if (andSet == null) {
            andSet = new MultiClassKey();
        }
        andSet.set(cls, cls2, cls3);
        return andSet;
    }

    @Nullable
    public <Data, TResource, Transcode> LoadPath<Data, TResource, Transcode> get(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        LoadPath<Data, TResource, Transcode> loadPath;
        MultiClassKey a2 = a(cls, cls2, cls3);
        synchronized (this.f2519a) {
            loadPath = (LoadPath<Data, TResource, Transcode>) this.f2519a.get(a2);
        }
        this.b.set(a2);
        return loadPath;
    }

    public boolean isEmptyLoadPath(@Nullable LoadPath<?, ?, ?> loadPath) {
        return c.equals(loadPath);
    }

    public void put(Class<?> cls, Class<?> cls2, Class<?> cls3, @Nullable LoadPath<?, ?, ?> loadPath) {
        synchronized (this.f2519a) {
            ArrayMap<MultiClassKey, LoadPath<?, ?, ?>> arrayMap = this.f2519a;
            MultiClassKey multiClassKey = new MultiClassKey(cls, cls2, cls3);
            if (loadPath == null) {
                loadPath = c;
            }
            arrayMap.put(multiClassKey, loadPath);
        }
    }
}
