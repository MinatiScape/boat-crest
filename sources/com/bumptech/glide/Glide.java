package com.bumptech.glide;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.BitmapPreFiller;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.HardwareConfigState;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.module.ManifestParser;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class Glide implements ComponentCallbacks2 {
    @GuardedBy("Glide.class")
    public static volatile Glide s;
    public static volatile boolean t;
    public final Engine h;
    public final BitmapPool i;
    public final MemoryCache j;
    public final GlideContext k;
    public final ArrayPool l;
    public final RequestManagerRetriever m;
    public final ConnectivityMonitorFactory n;
    public final RequestOptionsFactory p;
    @Nullable
    @GuardedBy("this")
    public BitmapPreFiller r;
    @GuardedBy("managers")
    public final List<RequestManager> o = new ArrayList();
    public MemoryCategory q = MemoryCategory.NORMAL;

    /* loaded from: classes.dex */
    public interface RequestOptionsFactory {
        @NonNull
        RequestOptions build();
    }

    public Glide(@NonNull Context context, @NonNull Engine engine, @NonNull MemoryCache memoryCache, @NonNull BitmapPool bitmapPool, @NonNull ArrayPool arrayPool, @NonNull RequestManagerRetriever requestManagerRetriever, @NonNull ConnectivityMonitorFactory connectivityMonitorFactory, int i, @NonNull RequestOptionsFactory requestOptionsFactory, @NonNull Map<Class<?>, TransitionOptions<?, ?>> map, @NonNull List<RequestListener<Object>> list, @NonNull List<GlideModule> list2, @Nullable AppGlideModule appGlideModule, @NonNull GlideExperiments glideExperiments) {
        this.h = engine;
        this.i = bitmapPool;
        this.l = arrayPool;
        this.j = memoryCache;
        this.m = requestManagerRetriever;
        this.n = connectivityMonitorFactory;
        this.p = requestOptionsFactory;
        this.k = new GlideContext(context, arrayPool, a.d(this, list2, appGlideModule), new ImageViewTargetFactory(), requestOptionsFactory, map, list, engine, glideExperiments, i);
    }

    @GuardedBy("Glide.class")
    public static void a(@NonNull Context context, @Nullable GeneratedAppGlideModule generatedAppGlideModule) {
        if (!t) {
            t = true;
            f(context, generatedAppGlideModule);
            t = false;
            return;
        }
        throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
    }

    @Nullable
    public static GeneratedAppGlideModule b(Context context) {
        try {
            return (GeneratedAppGlideModule) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(Context.class).newInstance(context.getApplicationContext());
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("Glide", 5)) {
                Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
            return null;
        } catch (IllegalAccessException e) {
            j(e);
            return null;
        } catch (InstantiationException e2) {
            j(e2);
            return null;
        } catch (NoSuchMethodException e3) {
            j(e3);
            return null;
        } catch (InvocationTargetException e4) {
            j(e4);
            return null;
        }
    }

    @NonNull
    public static RequestManagerRetriever e(@Nullable Context context) {
        Preconditions.checkNotNull(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return get(context).getRequestManagerRetriever();
    }

    @VisibleForTesting
    public static void enableHardwareBitmaps() {
        HardwareConfigState.getInstance().unblockHardwareBitmaps();
    }

    @GuardedBy("Glide.class")
    public static void f(@NonNull Context context, @Nullable GeneratedAppGlideModule generatedAppGlideModule) {
        g(context, new GlideBuilder(), generatedAppGlideModule);
    }

    @GuardedBy("Glide.class")
    public static void g(@NonNull Context context, @NonNull GlideBuilder glideBuilder, @Nullable GeneratedAppGlideModule generatedAppGlideModule) {
        Context applicationContext = context.getApplicationContext();
        List<GlideModule> emptyList = Collections.emptyList();
        if (generatedAppGlideModule == null || generatedAppGlideModule.isManifestParsingEnabled()) {
            emptyList = new ManifestParser(applicationContext).parse();
        }
        if (generatedAppGlideModule != null && !generatedAppGlideModule.a().isEmpty()) {
            Set<Class<?>> a2 = generatedAppGlideModule.a();
            Iterator<GlideModule> it = emptyList.iterator();
            while (it.hasNext()) {
                GlideModule next = it.next();
                if (a2.contains(next.getClass())) {
                    if (Log.isLoggable("Glide", 3)) {
                        Log.d("Glide", "AppGlideModule excludes manifest GlideModule: " + next);
                    }
                    it.remove();
                }
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            Iterator<GlideModule> it2 = emptyList.iterator();
            while (it2.hasNext()) {
                Log.d("Glide", "Discovered GlideModule from manifest: " + it2.next().getClass());
            }
        }
        glideBuilder.b(generatedAppGlideModule != null ? generatedAppGlideModule.b() : null);
        for (GlideModule glideModule : emptyList) {
            glideModule.applyOptions(applicationContext, glideBuilder);
        }
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.applyOptions(applicationContext, glideBuilder);
        }
        Glide a3 = glideBuilder.a(applicationContext, emptyList, generatedAppGlideModule);
        applicationContext.registerComponentCallbacks(a3);
        s = a3;
    }

    @NonNull
    public static Glide get(@NonNull Context context) {
        if (s == null) {
            GeneratedAppGlideModule b = b(context.getApplicationContext());
            synchronized (Glide.class) {
                if (s == null) {
                    a(context, b);
                }
            }
        }
        return s;
    }

    @Nullable
    public static File getPhotoCacheDir(@NonNull Context context) {
        return getPhotoCacheDir(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR);
    }

    @VisibleForTesting
    @Deprecated
    public static synchronized void init(Glide glide) {
        synchronized (Glide.class) {
            if (s != null) {
                tearDown();
            }
            s = glide;
        }
    }

    public static void j(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    @VisibleForTesting
    public static void tearDown() {
        synchronized (Glide.class) {
            if (s != null) {
                s.getContext().getApplicationContext().unregisterComponentCallbacks(s);
                s.h.shutdown();
            }
            s = null;
        }
    }

    @NonNull
    public static RequestManager with(@NonNull Context context) {
        return e(context).get(context);
    }

    public ConnectivityMonitorFactory c() {
        return this.n;
    }

    public void clearDiskCache() {
        Util.assertBackgroundThread();
        this.h.clearDiskCache();
    }

    public void clearMemory() {
        Util.assertMainThread();
        this.j.clearMemory();
        this.i.clearMemory();
        this.l.clearMemory();
    }

    @NonNull
    public GlideContext d() {
        return this.k;
    }

    @NonNull
    public ArrayPool getArrayPool() {
        return this.l;
    }

    @NonNull
    public BitmapPool getBitmapPool() {
        return this.i;
    }

    @NonNull
    public Context getContext() {
        return this.k.getBaseContext();
    }

    @NonNull
    public Registry getRegistry() {
        return this.k.getRegistry();
    }

    @NonNull
    public RequestManagerRetriever getRequestManagerRetriever() {
        return this.m;
    }

    public void h(RequestManager requestManager) {
        synchronized (this.o) {
            if (!this.o.contains(requestManager)) {
                this.o.add(requestManager);
            } else {
                throw new IllegalStateException("Cannot register already registered manager");
            }
        }
    }

    public boolean i(@NonNull Target<?> target) {
        synchronized (this.o) {
            for (RequestManager requestManager : this.o) {
                if (requestManager.e(target)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void k(RequestManager requestManager) {
        synchronized (this.o) {
            if (this.o.contains(requestManager)) {
                this.o.remove(requestManager);
            } else {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        clearMemory();
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        trimMemory(i);
    }

    public synchronized void preFillBitmapPool(@NonNull PreFillType.Builder... builderArr) {
        if (this.r == null) {
            this.r = new BitmapPreFiller(this.j, this.i, (DecodeFormat) this.p.build().getOptions().get(Downsampler.DECODE_FORMAT));
        }
        this.r.preFill(builderArr);
    }

    @NonNull
    public MemoryCategory setMemoryCategory(@NonNull MemoryCategory memoryCategory) {
        Util.assertMainThread();
        this.j.setSizeMultiplier(memoryCategory.getMultiplier());
        this.i.setSizeMultiplier(memoryCategory.getMultiplier());
        MemoryCategory memoryCategory2 = this.q;
        this.q = memoryCategory;
        return memoryCategory2;
    }

    public void trimMemory(int i) {
        Util.assertMainThread();
        synchronized (this.o) {
            for (RequestManager requestManager : this.o) {
                requestManager.onTrimMemory(i);
            }
        }
        this.j.trimMemory(i);
        this.i.trimMemory(i);
        this.l.trimMemory(i);
    }

    @Nullable
    public static File getPhotoCacheDir(@NonNull Context context, @NonNull String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File file = new File(cacheDir, str);
            if (file.isDirectory() || file.mkdirs()) {
                return file;
            }
            return null;
        }
        if (Log.isLoggable("Glide", 6)) {
            Log.e("Glide", "default disk cache dir is null");
        }
        return null;
    }

    @NonNull
    @Deprecated
    public static RequestManager with(@NonNull Activity activity) {
        return e(activity).get(activity);
    }

    @NonNull
    public static RequestManager with(@NonNull FragmentActivity fragmentActivity) {
        return e(fragmentActivity).get(fragmentActivity);
    }

    @NonNull
    public static RequestManager with(@NonNull Fragment fragment) {
        return e(fragment.getContext()).get(fragment);
    }

    @VisibleForTesting
    public static void init(@NonNull Context context, @NonNull GlideBuilder glideBuilder) {
        GeneratedAppGlideModule b = b(context);
        synchronized (Glide.class) {
            if (s != null) {
                tearDown();
            }
            g(context, glideBuilder, b);
        }
    }

    @NonNull
    @Deprecated
    public static RequestManager with(@NonNull android.app.Fragment fragment) {
        return e(fragment.getActivity()).get(fragment);
    }

    @NonNull
    public static RequestManager with(@NonNull View view) {
        return e(view.getContext()).get(view);
    }
}
