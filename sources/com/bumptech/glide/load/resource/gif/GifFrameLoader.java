package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class GifFrameLoader {

    /* renamed from: a  reason: collision with root package name */
    public final GifDecoder f2494a;
    public final Handler b;
    public final List<FrameCallback> c;
    public final RequestManager d;
    public final BitmapPool e;
    public boolean f;
    public boolean g;
    public boolean h;
    public RequestBuilder<Bitmap> i;
    public a j;
    public boolean k;
    public a l;
    public Bitmap m;
    public Transformation<Bitmap> n;
    public a o;
    @Nullable
    public c p;
    public int q;
    public int r;
    public int s;

    /* loaded from: classes2.dex */
    public interface FrameCallback {
        void onFrameReady();
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class a extends CustomTarget<Bitmap> {
        public final Handler k;
        public final int l;
        public final long m;
        public Bitmap n;

        public a(Handler handler, int i, long j) {
            this.k = handler;
            this.l = i;
            this.m = j;
        }

        public Bitmap a() {
            return this.n;
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(@Nullable Drawable drawable) {
            this.n = null;
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
        }

        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
            this.n = bitmap;
            this.k.sendMessageAtTime(this.k.obtainMessage(1, this), this.m);
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Handler.Callback {
        public b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                GifFrameLoader.this.o((a) message.obj);
                return true;
            } else if (i == 2) {
                GifFrameLoader.this.d.clear((a) message.obj);
                return false;
            } else {
                return false;
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public interface c {
        void onFrameReady();
    }

    public GifFrameLoader(Glide glide, GifDecoder gifDecoder, int i, int i2, Transformation<Bitmap> transformation, Bitmap bitmap) {
        this(glide.getBitmapPool(), Glide.with(glide.getContext()), gifDecoder, null, k(Glide.with(glide.getContext()), i, i2), transformation, bitmap);
    }

    public static Key g() {
        return new ObjectKey(Double.valueOf(Math.random()));
    }

    public static RequestBuilder<Bitmap> k(RequestManager requestManager, int i, int i2) {
        return requestManager.asBitmap().apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).useAnimationPool(true).skipMemoryCache(true).override(i, i2));
    }

    public void a() {
        this.c.clear();
        p();
        t();
        a aVar = this.j;
        if (aVar != null) {
            this.d.clear(aVar);
            this.j = null;
        }
        a aVar2 = this.l;
        if (aVar2 != null) {
            this.d.clear(aVar2);
            this.l = null;
        }
        a aVar3 = this.o;
        if (aVar3 != null) {
            this.d.clear(aVar3);
            this.o = null;
        }
        this.f2494a.clear();
        this.k = true;
    }

    public ByteBuffer b() {
        return this.f2494a.getData().asReadOnlyBuffer();
    }

    public Bitmap c() {
        a aVar = this.j;
        return aVar != null ? aVar.a() : this.m;
    }

    public int d() {
        a aVar = this.j;
        if (aVar != null) {
            return aVar.l;
        }
        return -1;
    }

    public Bitmap e() {
        return this.m;
    }

    public int f() {
        return this.f2494a.getFrameCount();
    }

    public Transformation<Bitmap> h() {
        return this.n;
    }

    public int i() {
        return this.s;
    }

    public int j() {
        return this.f2494a.getTotalIterationCount();
    }

    public int l() {
        return this.f2494a.getByteSize() + this.q;
    }

    public int m() {
        return this.r;
    }

    public final void n() {
        if (!this.f || this.g) {
            return;
        }
        if (this.h) {
            Preconditions.checkArgument(this.o == null, "Pending target must be null when starting from the first frame");
            this.f2494a.resetFrameIndex();
            this.h = false;
        }
        a aVar = this.o;
        if (aVar != null) {
            this.o = null;
            o(aVar);
            return;
        }
        this.g = true;
        long uptimeMillis = SystemClock.uptimeMillis() + this.f2494a.getNextDelay();
        this.f2494a.advance();
        this.l = new a(this.b, this.f2494a.getCurrentFrameIndex(), uptimeMillis);
        this.i.apply((BaseRequestOptions<?>) RequestOptions.signatureOf(g())).m20load((Object) this.f2494a).into((RequestBuilder<Bitmap>) this.l);
    }

    @VisibleForTesting
    public void o(a aVar) {
        c cVar = this.p;
        if (cVar != null) {
            cVar.onFrameReady();
        }
        this.g = false;
        if (this.k) {
            this.b.obtainMessage(2, aVar).sendToTarget();
        } else if (!this.f) {
            if (this.h) {
                this.b.obtainMessage(2, aVar).sendToTarget();
            } else {
                this.o = aVar;
            }
        } else {
            if (aVar.a() != null) {
                p();
                a aVar2 = this.j;
                this.j = aVar;
                for (int size = this.c.size() - 1; size >= 0; size--) {
                    this.c.get(size).onFrameReady();
                }
                if (aVar2 != null) {
                    this.b.obtainMessage(2, aVar2).sendToTarget();
                }
            }
            n();
        }
    }

    public final void p() {
        Bitmap bitmap = this.m;
        if (bitmap != null) {
            this.e.put(bitmap);
            this.m = null;
        }
    }

    public void q(Transformation<Bitmap> transformation, Bitmap bitmap) {
        this.n = (Transformation) Preconditions.checkNotNull(transformation);
        this.m = (Bitmap) Preconditions.checkNotNull(bitmap);
        this.i = this.i.apply((BaseRequestOptions<?>) new RequestOptions().transform(transformation));
        this.q = Util.getBitmapByteSize(bitmap);
        this.r = bitmap.getWidth();
        this.s = bitmap.getHeight();
    }

    public void r() {
        Preconditions.checkArgument(!this.f, "Can't restart a running animation");
        this.h = true;
        a aVar = this.o;
        if (aVar != null) {
            this.d.clear(aVar);
            this.o = null;
        }
    }

    public final void s() {
        if (this.f) {
            return;
        }
        this.f = true;
        this.k = false;
        n();
    }

    public final void t() {
        this.f = false;
    }

    public void u(FrameCallback frameCallback) {
        if (!this.k) {
            if (!this.c.contains(frameCallback)) {
                boolean isEmpty = this.c.isEmpty();
                this.c.add(frameCallback);
                if (isEmpty) {
                    s();
                    return;
                }
                return;
            }
            throw new IllegalStateException("Cannot subscribe twice in a row");
        }
        throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
    }

    public void v(FrameCallback frameCallback) {
        this.c.remove(frameCallback);
        if (this.c.isEmpty()) {
            t();
        }
    }

    public GifFrameLoader(BitmapPool bitmapPool, RequestManager requestManager, GifDecoder gifDecoder, Handler handler, RequestBuilder<Bitmap> requestBuilder, Transformation<Bitmap> transformation, Bitmap bitmap) {
        this.c = new ArrayList();
        this.d = requestManager;
        handler = handler == null ? new Handler(Looper.getMainLooper(), new b()) : handler;
        this.e = bitmapPool;
        this.b = handler;
        this.i = requestBuilder;
        this.f2494a = gifDecoder;
        q(transformation, bitmap);
    }
}
