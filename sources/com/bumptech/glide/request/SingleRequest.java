package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.annotation.DrawableRes;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.GlideTrace;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public final class SingleRequest<R> implements Request, SizeReadyCallback, ResourceCallback {
    public static final boolean E = Log.isLoggable("GlideRequest", 2);
    @GuardedBy("requestLock")
    public int A;
    @GuardedBy("requestLock")
    public int B;
    @GuardedBy("requestLock")
    public boolean C;
    @Nullable
    public RuntimeException D;

    /* renamed from: a  reason: collision with root package name */
    public int f2526a;
    @Nullable
    public final String b;
    public final StateVerifier c;
    public final Object d;
    @Nullable
    public final RequestListener<R> e;
    public final RequestCoordinator f;
    public final Context g;
    public final GlideContext h;
    @Nullable
    public final Object i;
    public final Class<R> j;
    public final BaseRequestOptions<?> k;
    public final int l;
    public final int m;
    public final Priority n;
    public final Target<R> o;
    @Nullable
    public final List<RequestListener<R>> p;
    public final TransitionFactory<? super R> q;
    public final Executor r;
    @GuardedBy("requestLock")
    public Resource<R> s;
    @GuardedBy("requestLock")
    public Engine.LoadStatus t;
    @GuardedBy("requestLock")
    public long u;
    public volatile Engine v;
    @GuardedBy("requestLock")
    public a w;
    @Nullable
    @GuardedBy("requestLock")
    public Drawable x;
    @Nullable
    @GuardedBy("requestLock")
    public Drawable y;
    @Nullable
    @GuardedBy("requestLock")
    public Drawable z;

    /* loaded from: classes2.dex */
    public enum a {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    public SingleRequest(Context context, GlideContext glideContext, @NonNull Object obj, @Nullable Object obj2, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i, int i2, Priority priority, Target<R> target, @Nullable RequestListener<R> requestListener, @Nullable List<RequestListener<R>> list, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> transitionFactory, Executor executor) {
        this.b = E ? String.valueOf(super.hashCode()) : null;
        this.c = StateVerifier.newInstance();
        this.d = obj;
        this.g = context;
        this.h = glideContext;
        this.i = obj2;
        this.j = cls;
        this.k = baseRequestOptions;
        this.l = i;
        this.m = i2;
        this.n = priority;
        this.o = target;
        this.e = requestListener;
        this.p = list;
        this.f = requestCoordinator;
        this.v = engine;
        this.q = transitionFactory;
        this.r = executor;
        this.w = a.PENDING;
        if (this.D == null && glideContext.getExperiments().isEnabled(GlideBuilder.LogRequestOrigins.class)) {
            this.D = new RuntimeException("Glide request origin trace");
        }
    }

    public static int m(int i, float f) {
        return i == Integer.MIN_VALUE ? i : Math.round(f * i);
    }

    public static <R> SingleRequest<R> obtain(Context context, GlideContext glideContext, Object obj, Object obj2, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i, int i2, Priority priority, Target<R> target, RequestListener<R> requestListener, @Nullable List<RequestListener<R>> list, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> transitionFactory, Executor executor) {
        return new SingleRequest<>(context, glideContext, obj, obj2, cls, baseRequestOptions, i, i2, priority, target, requestListener, list, requestCoordinator, engine, transitionFactory, executor);
    }

    @GuardedBy("requestLock")
    public final void a() {
        if (this.C) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    @GuardedBy("requestLock")
    public final boolean b() {
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || requestCoordinator.canNotifyCleared(this);
    }

    @Override // com.bumptech.glide.request.Request
    public void begin() {
        synchronized (this.d) {
            a();
            this.c.throwIfRecycled();
            this.u = LogTime.getLogTime();
            Object obj = this.i;
            if (obj == null) {
                if (Util.isValidDimensions(this.l, this.m)) {
                    this.A = this.l;
                    this.B = this.m;
                }
                p(new GlideException("Received null model"), h() == null ? 5 : 3);
                return;
            }
            a aVar = this.w;
            a aVar2 = a.RUNNING;
            if (aVar != aVar2) {
                if (aVar == a.COMPLETE) {
                    onResourceReady(this.s, DataSource.MEMORY_CACHE, false);
                    return;
                }
                f(obj);
                this.f2526a = GlideTrace.beginSectionAsync("GlideRequest");
                a aVar3 = a.WAITING_FOR_SIZE;
                this.w = aVar3;
                if (Util.isValidDimensions(this.l, this.m)) {
                    onSizeReady(this.l, this.m);
                } else {
                    this.o.getSize(this);
                }
                a aVar4 = this.w;
                if ((aVar4 == aVar2 || aVar4 == aVar3) && c()) {
                    this.o.onLoadStarted(i());
                }
                if (E) {
                    l("finished run method in " + LogTime.getElapsedMillis(this.u));
                }
                return;
            }
            throw new IllegalArgumentException("Cannot restart a running request");
        }
    }

    @GuardedBy("requestLock")
    public final boolean c() {
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || requestCoordinator.canNotifyStatusChanged(this);
    }

    @Override // com.bumptech.glide.request.Request
    public void clear() {
        synchronized (this.d) {
            a();
            this.c.throwIfRecycled();
            a aVar = this.w;
            a aVar2 = a.CLEARED;
            if (aVar == aVar2) {
                return;
            }
            e();
            Resource<R> resource = this.s;
            if (resource != null) {
                this.s = null;
            } else {
                resource = null;
            }
            if (b()) {
                this.o.onLoadCleared(i());
            }
            GlideTrace.endSectionAsync("GlideRequest", this.f2526a);
            this.w = aVar2;
            if (resource != null) {
                this.v.release(resource);
            }
        }
    }

    @GuardedBy("requestLock")
    public final boolean d() {
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || requestCoordinator.canSetImage(this);
    }

    @GuardedBy("requestLock")
    public final void e() {
        a();
        this.c.throwIfRecycled();
        this.o.removeCallback(this);
        Engine.LoadStatus loadStatus = this.t;
        if (loadStatus != null) {
            loadStatus.cancel();
            this.t = null;
        }
    }

    public final void f(Object obj) {
        List<RequestListener<R>> list = this.p;
        if (list == null) {
            return;
        }
        for (RequestListener<R> requestListener : list) {
            if (requestListener instanceof ExperimentalRequestListener) {
                ((ExperimentalRequestListener) requestListener).onRequestStarted(obj);
            }
        }
    }

    @GuardedBy("requestLock")
    public final Drawable g() {
        if (this.x == null) {
            Drawable errorPlaceholder = this.k.getErrorPlaceholder();
            this.x = errorPlaceholder;
            if (errorPlaceholder == null && this.k.getErrorId() > 0) {
                this.x = k(this.k.getErrorId());
            }
        }
        return this.x;
    }

    @Override // com.bumptech.glide.request.ResourceCallback
    public Object getLock() {
        this.c.throwIfRecycled();
        return this.d;
    }

    @GuardedBy("requestLock")
    public final Drawable h() {
        if (this.z == null) {
            Drawable fallbackDrawable = this.k.getFallbackDrawable();
            this.z = fallbackDrawable;
            if (fallbackDrawable == null && this.k.getFallbackId() > 0) {
                this.z = k(this.k.getFallbackId());
            }
        }
        return this.z;
    }

    @GuardedBy("requestLock")
    public final Drawable i() {
        if (this.y == null) {
            Drawable placeholderDrawable = this.k.getPlaceholderDrawable();
            this.y = placeholderDrawable;
            if (placeholderDrawable == null && this.k.getPlaceholderId() > 0) {
                this.y = k(this.k.getPlaceholderId());
            }
        }
        return this.y;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isAnyResourceSet() {
        boolean z;
        synchronized (this.d) {
            z = this.w == a.COMPLETE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isCleared() {
        boolean z;
        synchronized (this.d) {
            z = this.w == a.CLEARED;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isComplete() {
        boolean z;
        synchronized (this.d) {
            z = this.w == a.COMPLETE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isEquivalentTo(Request request) {
        int i;
        int i2;
        Object obj;
        Class<R> cls;
        BaseRequestOptions<?> baseRequestOptions;
        Priority priority;
        int size;
        int i3;
        int i4;
        Object obj2;
        Class<R> cls2;
        BaseRequestOptions<?> baseRequestOptions2;
        Priority priority2;
        int size2;
        if (request instanceof SingleRequest) {
            synchronized (this.d) {
                i = this.l;
                i2 = this.m;
                obj = this.i;
                cls = this.j;
                baseRequestOptions = this.k;
                priority = this.n;
                List<RequestListener<R>> list = this.p;
                size = list != null ? list.size() : 0;
            }
            SingleRequest singleRequest = (SingleRequest) request;
            synchronized (singleRequest.d) {
                i3 = singleRequest.l;
                i4 = singleRequest.m;
                obj2 = singleRequest.i;
                cls2 = singleRequest.j;
                baseRequestOptions2 = singleRequest.k;
                priority2 = singleRequest.n;
                List<RequestListener<R>> list2 = singleRequest.p;
                size2 = list2 != null ? list2.size() : 0;
            }
            return i == i3 && i2 == i4 && Util.bothModelsNullEquivalentOrEquals(obj, obj2) && cls.equals(cls2) && baseRequestOptions.equals(baseRequestOptions2) && priority == priority2 && size == size2;
        }
        return false;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isRunning() {
        boolean z;
        synchronized (this.d) {
            a aVar = this.w;
            z = aVar == a.RUNNING || aVar == a.WAITING_FOR_SIZE;
        }
        return z;
    }

    @GuardedBy("requestLock")
    public final boolean j() {
        RequestCoordinator requestCoordinator = this.f;
        return requestCoordinator == null || !requestCoordinator.getRoot().isAnyResourceSet();
    }

    @GuardedBy("requestLock")
    public final Drawable k(@DrawableRes int i) {
        return DrawableDecoderCompat.getDrawable(this.h, i, this.k.getTheme() != null ? this.k.getTheme() : this.g.getTheme());
    }

    public final void l(String str) {
        Log.v("GlideRequest", str + " this: " + this.b);
    }

    @GuardedBy("requestLock")
    public final void n() {
        RequestCoordinator requestCoordinator = this.f;
        if (requestCoordinator != null) {
            requestCoordinator.onRequestFailed(this);
        }
    }

    @GuardedBy("requestLock")
    public final void o() {
        RequestCoordinator requestCoordinator = this.f;
        if (requestCoordinator != null) {
            requestCoordinator.onRequestSuccess(this);
        }
    }

    @Override // com.bumptech.glide.request.ResourceCallback
    public void onLoadFailed(GlideException glideException) {
        p(glideException, 5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.request.ResourceCallback
    public void onResourceReady(Resource<?> resource, DataSource dataSource, boolean z) {
        this.c.throwIfRecycled();
        Resource<?> resource2 = null;
        try {
            synchronized (this.d) {
                try {
                    this.t = null;
                    if (resource == null) {
                        onLoadFailed(new GlideException("Expected to receive a Resource<R> with an object of " + this.j + " inside, but instead got null."));
                        return;
                    }
                    Object obj = resource.get();
                    try {
                        if (obj != null && this.j.isAssignableFrom(obj.getClass())) {
                            if (!d()) {
                                this.s = null;
                                this.w = a.COMPLETE;
                                GlideTrace.endSectionAsync("GlideRequest", this.f2526a);
                                this.v.release(resource);
                                return;
                            }
                            q(resource, obj, dataSource, z);
                            return;
                        }
                        this.s = null;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Expected to receive an object of ");
                        sb.append(this.j);
                        sb.append(" but instead got ");
                        sb.append(obj != null ? obj.getClass() : "");
                        sb.append("{");
                        sb.append(obj);
                        sb.append("} inside Resource{");
                        sb.append(resource);
                        sb.append("}.");
                        sb.append(obj != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
                        onLoadFailed(new GlideException(sb.toString()));
                        this.v.release(resource);
                    } catch (Throwable th) {
                        resource2 = resource;
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            if (resource2 != null) {
                this.v.release(resource2);
            }
            throw th3;
        }
    }

    @Override // com.bumptech.glide.request.target.SizeReadyCallback
    public void onSizeReady(int i, int i2) {
        Object obj;
        this.c.throwIfRecycled();
        Object obj2 = this.d;
        synchronized (obj2) {
            try {
                try {
                    boolean z = E;
                    if (z) {
                        l("Got onSizeReady in " + LogTime.getElapsedMillis(this.u));
                    }
                    if (this.w == a.WAITING_FOR_SIZE) {
                        a aVar = a.RUNNING;
                        this.w = aVar;
                        float sizeMultiplier = this.k.getSizeMultiplier();
                        this.A = m(i, sizeMultiplier);
                        this.B = m(i2, sizeMultiplier);
                        if (z) {
                            l("finished setup for calling load in " + LogTime.getElapsedMillis(this.u));
                        }
                        obj = obj2;
                        try {
                            this.t = this.v.load(this.h, this.i, this.k.getSignature(), this.A, this.B, this.k.getResourceClass(), this.j, this.n, this.k.getDiskCacheStrategy(), this.k.getTransformations(), this.k.isTransformationRequired(), this.k.a(), this.k.getOptions(), this.k.isMemoryCacheable(), this.k.getUseUnlimitedSourceGeneratorsPool(), this.k.getUseAnimationPool(), this.k.getOnlyRetrieveFromCache(), this, this.r);
                            if (this.w != aVar) {
                                this.t = null;
                            }
                            if (z) {
                                l("finished onSizeReady in " + LogTime.getElapsedMillis(this.u));
                            }
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                obj = obj2;
            }
        }
    }

    public final void p(GlideException glideException, int i) {
        boolean z;
        this.c.throwIfRecycled();
        synchronized (this.d) {
            glideException.setOrigin(this.D);
            int logLevel = this.h.getLogLevel();
            if (logLevel <= i) {
                Log.w("Glide", "Load failed for [" + this.i + "] with dimensions [" + this.A + "x" + this.B + "]", glideException);
                if (logLevel <= 4) {
                    glideException.logRootCauses("Glide");
                }
            }
            this.t = null;
            this.w = a.FAILED;
            n();
            boolean z2 = true;
            this.C = true;
            List<RequestListener<R>> list = this.p;
            if (list != null) {
                z = false;
                for (RequestListener<R> requestListener : list) {
                    z |= requestListener.onLoadFailed(glideException, this.i, this.o, j());
                }
            } else {
                z = false;
            }
            RequestListener<R> requestListener2 = this.e;
            if (requestListener2 == null || !requestListener2.onLoadFailed(glideException, this.i, this.o, j())) {
                z2 = false;
            }
            if (!(z | z2)) {
                r();
            }
            this.C = false;
            GlideTrace.endSectionAsync("GlideRequest", this.f2526a);
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void pause() {
        synchronized (this.d) {
            if (isRunning()) {
                clear();
            }
        }
    }

    @GuardedBy("requestLock")
    public final void q(Resource<R> resource, R r, DataSource dataSource, boolean z) {
        boolean z2;
        boolean j = j();
        this.w = a.COMPLETE;
        this.s = resource;
        if (this.h.getLogLevel() <= 3) {
            Log.d("Glide", "Finished loading " + r.getClass().getSimpleName() + " from " + dataSource + " for " + this.i + " with size [" + this.A + "x" + this.B + "] in " + LogTime.getElapsedMillis(this.u) + " ms");
        }
        o();
        boolean z3 = true;
        this.C = true;
        try {
            List<RequestListener<R>> list = this.p;
            if (list != null) {
                z2 = false;
                for (RequestListener<R> requestListener : list) {
                    z2 |= requestListener.onResourceReady(r, this.i, this.o, dataSource, j);
                }
            } else {
                z2 = false;
            }
            RequestListener<R> requestListener2 = this.e;
            if (requestListener2 == null || !requestListener2.onResourceReady(r, this.i, this.o, dataSource, j)) {
                z3 = false;
            }
            if (!(z3 | z2)) {
                this.o.onResourceReady(r, this.q.build(dataSource, j));
            }
            this.C = false;
            GlideTrace.endSectionAsync("GlideRequest", this.f2526a);
        } catch (Throwable th) {
            this.C = false;
            throw th;
        }
    }

    @GuardedBy("requestLock")
    public final void r() {
        if (c()) {
            Drawable h = this.i == null ? h() : null;
            if (h == null) {
                h = g();
            }
            if (h == null) {
                h = i();
            }
            this.o.onLoadFailed(h);
        }
    }

    public String toString() {
        Object obj;
        Class<R> cls;
        synchronized (this.d) {
            obj = this.i;
            cls = this.j;
        }
        return super.toString() + "[model=" + obj + ", transcodeClass=" + cls + "]";
    }
}
