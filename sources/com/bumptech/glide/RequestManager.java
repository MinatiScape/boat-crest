package com.bumptech.glide;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.manager.TargetTracker;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class RequestManager implements ComponentCallbacks2, LifecycleListener {
    public static final RequestOptions q = RequestOptions.decodeTypeOf(Bitmap.class).lock();
    public static final RequestOptions r = RequestOptions.decodeTypeOf(GifDrawable.class).lock();
    public static final RequestOptions s = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA).priority(Priority.LOW).skipMemoryCache(true);
    public final Context context;
    public final Glide glide;
    public final Lifecycle h;
    @GuardedBy("this")
    public final RequestTracker i;
    @GuardedBy("this")
    public final RequestManagerTreeNode j;
    @GuardedBy("this")
    public final TargetTracker k;
    public final Runnable l;
    public final ConnectivityMonitor m;
    public final CopyOnWriteArrayList<RequestListener<Object>> n;
    @GuardedBy("this")
    public RequestOptions o;
    public boolean p;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RequestManager requestManager = RequestManager.this;
            requestManager.h.addListener(requestManager);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends CustomViewTarget<View, Object> {
        public b(@NonNull View view) {
            super(view);
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.CustomViewTarget
        public void onResourceCleared(@Nullable Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onResourceReady(@NonNull Object obj, @Nullable Transition<? super Object> transition) {
        }
    }

    /* loaded from: classes.dex */
    public class c implements ConnectivityMonitor.ConnectivityListener {
        @GuardedBy("RequestManager.this")

        /* renamed from: a  reason: collision with root package name */
        public final RequestTracker f2312a;

        public c(@NonNull RequestTracker requestTracker) {
            this.f2312a = requestTracker;
        }

        @Override // com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener
        public void onConnectivityChanged(boolean z) {
            if (z) {
                synchronized (RequestManager.this) {
                    this.f2312a.restartRequests();
                }
            }
        }
    }

    public RequestManager(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode requestManagerTreeNode, @NonNull Context context) {
        this(glide, lifecycle, requestManagerTreeNode, new RequestTracker(), glide.c(), context);
    }

    public List<RequestListener<Object>> a() {
        return this.n;
    }

    public RequestManager addDefaultRequestListener(RequestListener<Object> requestListener) {
        this.n.add(requestListener);
        return this;
    }

    @NonNull
    public synchronized RequestManager applyDefaultRequestOptions(@NonNull RequestOptions requestOptions) {
        g(requestOptions);
        return this;
    }

    @NonNull
    @CheckResult
    public <ResourceType> RequestBuilder<ResourceType> as(@NonNull Class<ResourceType> cls) {
        return new RequestBuilder<>(this.glide, this, cls, this.context);
    }

    @NonNull
    @CheckResult
    public RequestBuilder<Bitmap> asBitmap() {
        return as(Bitmap.class).apply((BaseRequestOptions<?>) q);
    }

    @NonNull
    @CheckResult
    public RequestBuilder<Drawable> asDrawable() {
        return as(Drawable.class);
    }

    @NonNull
    @CheckResult
    public RequestBuilder<File> asFile() {
        return as(File.class).apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true));
    }

    @NonNull
    @CheckResult
    public RequestBuilder<GifDrawable> asGif() {
        return as(GifDrawable.class).apply((BaseRequestOptions<?>) r);
    }

    public synchronized RequestOptions b() {
        return this.o;
    }

    @NonNull
    public <T> TransitionOptions<?, T> c(Class<T> cls) {
        return this.glide.d().getDefaultTransitionOptions(cls);
    }

    public void clear(@NonNull View view) {
        clear(new b(view));
    }

    public synchronized void d(@NonNull Target<?> target, @NonNull Request request) {
        this.k.track(target);
        this.i.runRequest(request);
    }

    @NonNull
    @CheckResult
    public RequestBuilder<File> download(@Nullable Object obj) {
        return downloadOnly().m20load(obj);
    }

    @NonNull
    @CheckResult
    public RequestBuilder<File> downloadOnly() {
        return as(File.class).apply((BaseRequestOptions<?>) s);
    }

    public synchronized boolean e(@NonNull Target<?> target) {
        Request request = target.getRequest();
        if (request == null) {
            return true;
        }
        if (this.i.clearAndRemove(request)) {
            this.k.untrack(target);
            target.setRequest(null);
            return true;
        }
        return false;
    }

    public final void f(@NonNull Target<?> target) {
        boolean e = e(target);
        Request request = target.getRequest();
        if (e || this.glide.i(target) || request == null) {
            return;
        }
        target.setRequest(null);
        request.clear();
    }

    public final synchronized void g(@NonNull RequestOptions requestOptions) {
        this.o = this.o.apply(requestOptions);
    }

    public synchronized boolean isPaused() {
        return this.i.isPaused();
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public synchronized void onDestroy() {
        this.k.onDestroy();
        for (Target<?> target : this.k.getAll()) {
            clear(target);
        }
        this.k.clear();
        this.i.clearRequests();
        this.h.removeListener(this);
        this.h.removeListener(this.m);
        Util.removeCallbacksOnUiThread(this.l);
        this.glide.k(this);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public synchronized void onStart() {
        resumeRequests();
        this.k.onStart();
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public synchronized void onStop() {
        pauseRequests();
        this.k.onStop();
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        if (i == 60 && this.p) {
            pauseAllRequestsRecursive();
        }
    }

    public synchronized void pauseAllRequests() {
        this.i.pauseAllRequests();
    }

    public synchronized void pauseAllRequestsRecursive() {
        pauseAllRequests();
        for (RequestManager requestManager : this.j.getDescendants()) {
            requestManager.pauseAllRequests();
        }
    }

    public synchronized void pauseRequests() {
        this.i.pauseRequests();
    }

    public synchronized void pauseRequestsRecursive() {
        pauseRequests();
        for (RequestManager requestManager : this.j.getDescendants()) {
            requestManager.pauseRequests();
        }
    }

    public synchronized void resumeRequests() {
        this.i.resumeRequests();
    }

    public synchronized void resumeRequestsRecursive() {
        Util.assertMainThread();
        resumeRequests();
        for (RequestManager requestManager : this.j.getDescendants()) {
            requestManager.resumeRequests();
        }
    }

    @NonNull
    public synchronized RequestManager setDefaultRequestOptions(@NonNull RequestOptions requestOptions) {
        setRequestOptions(requestOptions);
        return this;
    }

    public void setPauseAllRequestsOnTrimMemoryModerate(boolean z) {
        this.p = z;
    }

    public synchronized void setRequestOptions(@NonNull RequestOptions requestOptions) {
        this.o = requestOptions.mo14clone().autoClone();
    }

    public synchronized String toString() {
        return super.toString() + "{tracker=" + this.i + ", treeNode=" + this.j + "}";
    }

    public void clear(@Nullable Target<?> target) {
        if (target == null) {
            return;
        }
        f(target);
    }

    public RequestManager(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, RequestTracker requestTracker, ConnectivityMonitorFactory connectivityMonitorFactory, Context context) {
        this.k = new TargetTracker();
        a aVar = new a();
        this.l = aVar;
        this.glide = glide;
        this.h = lifecycle;
        this.j = requestManagerTreeNode;
        this.i = requestTracker;
        this.context = context;
        ConnectivityMonitor build = connectivityMonitorFactory.build(context.getApplicationContext(), new c(requestTracker));
        this.m = build;
        if (Util.isOnBackgroundThread()) {
            Util.postOnUiThread(aVar);
        } else {
            lifecycle.addListener(this);
        }
        lifecycle.addListener(build);
        this.n = new CopyOnWriteArrayList<>(glide.d().getDefaultRequestListeners());
        setRequestOptions(glide.d().getDefaultRequestOptions());
        glide.h(this);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> m24load(@Nullable Bitmap bitmap) {
        return asDrawable().m15load(bitmap);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> m25load(@Nullable Drawable drawable) {
        return asDrawable().m16load(drawable);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> m30load(@Nullable String str) {
        return asDrawable().m21load(str);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> m26load(@Nullable Uri uri) {
        return asDrawable().m17load(uri);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> m27load(@Nullable File file) {
        return asDrawable().m18load(file);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> m28load(@Nullable @DrawableRes @RawRes Integer num) {
        return asDrawable().m19load(num);
    }

    @CheckResult
    @Deprecated
    /* renamed from: load */
    public RequestBuilder<Drawable> m31load(@Nullable URL url) {
        return asDrawable().m22load(url);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> m32load(@Nullable byte[] bArr) {
        return asDrawable().m23load(bArr);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<Drawable> m29load(@Nullable Object obj) {
        return asDrawable().m20load(obj);
    }
}
