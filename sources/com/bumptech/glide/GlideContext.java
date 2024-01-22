package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.ImageView;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.util.GlideSuppliers;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class GlideContext extends ContextWrapper {
    @VisibleForTesting
    public static final TransitionOptions<?, ?> k = new GenericTransitionOptions();

    /* renamed from: a  reason: collision with root package name */
    public final ArrayPool f2305a;
    public final GlideSuppliers.GlideSupplier<Registry> b;
    public final ImageViewTargetFactory c;
    public final Glide.RequestOptionsFactory d;
    public final List<RequestListener<Object>> e;
    public final Map<Class<?>, TransitionOptions<?, ?>> f;
    public final Engine g;
    public final GlideExperiments h;
    public final int i;
    @Nullable
    @GuardedBy("this")
    public RequestOptions j;

    public GlideContext(@NonNull Context context, @NonNull ArrayPool arrayPool, @NonNull GlideSuppliers.GlideSupplier<Registry> glideSupplier, @NonNull ImageViewTargetFactory imageViewTargetFactory, @NonNull Glide.RequestOptionsFactory requestOptionsFactory, @NonNull Map<Class<?>, TransitionOptions<?, ?>> map, @NonNull List<RequestListener<Object>> list, @NonNull Engine engine, @NonNull GlideExperiments glideExperiments, int i) {
        super(context.getApplicationContext());
        this.f2305a = arrayPool;
        this.c = imageViewTargetFactory;
        this.d = requestOptionsFactory;
        this.e = list;
        this.f = map;
        this.g = engine;
        this.h = glideExperiments;
        this.i = i;
        this.b = GlideSuppliers.memorize(glideSupplier);
    }

    @NonNull
    public <X> ViewTarget<ImageView, X> buildImageViewTarget(@NonNull ImageView imageView, @NonNull Class<X> cls) {
        return this.c.buildTarget(imageView, cls);
    }

    @NonNull
    public ArrayPool getArrayPool() {
        return this.f2305a;
    }

    public List<RequestListener<Object>> getDefaultRequestListeners() {
        return this.e;
    }

    public synchronized RequestOptions getDefaultRequestOptions() {
        if (this.j == null) {
            this.j = this.d.build().lock();
        }
        return this.j;
    }

    @NonNull
    public <T> TransitionOptions<?, T> getDefaultTransitionOptions(@NonNull Class<T> cls) {
        TransitionOptions<?, T> transitionOptions = (TransitionOptions<?, T>) this.f.get(cls);
        if (transitionOptions == null) {
            for (Map.Entry<Class<?>, TransitionOptions<?, ?>> entry : this.f.entrySet()) {
                if (entry.getKey().isAssignableFrom(cls)) {
                    transitionOptions = (TransitionOptions<?, T>) entry.getValue();
                }
            }
        }
        return transitionOptions == null ? (TransitionOptions<?, T>) k : transitionOptions;
    }

    @NonNull
    public Engine getEngine() {
        return this.g;
    }

    public GlideExperiments getExperiments() {
        return this.h;
    }

    public int getLogLevel() {
        return this.i;
    }

    @NonNull
    public Registry getRegistry() {
        return this.b.get();
    }
}
