package com.clevertap.android.sdk.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
/* loaded from: classes2.dex */
public class GifImageView extends AppCompatImageView implements Runnable {
    public boolean k;
    public OnAnimationStart l;
    public OnAnimationStop m;
    public Thread n;
    public OnFrameAvailable o;
    public long p;
    public com.clevertap.android.sdk.gif.a q;
    public final Handler r;
    public boolean s;
    public boolean t;
    public Bitmap u;
    public final Runnable v;
    public final Runnable w;

    /* loaded from: classes2.dex */
    public interface OnAnimationStart {
        void onAnimationStart();
    }

    /* loaded from: classes2.dex */
    public interface OnAnimationStop {
        void onAnimationStop();
    }

    /* loaded from: classes2.dex */
    public interface OnFrameAvailable {
        Bitmap onFrameAvailable(Bitmap bitmap);
    }

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GifImageView.this.u = null;
            GifImageView.this.q = null;
            GifImageView.this.n = null;
            GifImageView.this.t = false;
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (GifImageView.this.u == null || GifImageView.this.u.isRecycled()) {
                return;
            }
            GifImageView gifImageView = GifImageView.this;
            gifImageView.setImageBitmap(gifImageView.u);
            GifImageView.this.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

    public GifImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = null;
        this.m = null;
        this.o = null;
        this.p = -1L;
        this.r = new Handler(Looper.getMainLooper());
        this.v = new a();
        this.w = new b();
    }

    public void clear() {
        this.k = false;
        this.s = false;
        this.t = true;
        stopAnimation();
        this.r.post(this.v);
    }

    public final boolean f() {
        return (this.k || this.s) && this.q != null && this.n == null;
    }

    public final void g() {
        if (f()) {
            Thread thread = new Thread(this);
            this.n = thread;
            thread.start();
        }
    }

    public int getFrameCount() {
        return this.q.g();
    }

    public long getFramesDisplayDuration() {
        return this.p;
    }

    public int getGifHeight() {
        return this.q.i();
    }

    public int getGifWidth() {
        return this.q.m();
    }

    public OnAnimationStop getOnAnimationStop() {
        return this.m;
    }

    public OnFrameAvailable getOnFrameAvailable() {
        return this.o;
    }

    public void gotoFrame(int i) {
        if (this.q.e() == i || !this.q.w(i - 1) || this.k) {
            return;
        }
        this.s = true;
        g();
    }

    public boolean isAnimating() {
        return this.k;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clear();
    }

    public void resetAnimation() {
        this.q.r();
        gotoFrame(0);
    }

    @Override // java.lang.Runnable
    public void run() {
        long j;
        OnAnimationStart onAnimationStart = this.l;
        if (onAnimationStart != null) {
            onAnimationStart.onAnimationStart();
        }
        do {
            if (!this.k && !this.s) {
                break;
            }
            boolean a2 = this.q.a();
            try {
                long nanoTime = System.nanoTime();
                Bitmap l = this.q.l();
                this.u = l;
                OnFrameAvailable onFrameAvailable = this.o;
                if (onFrameAvailable != null) {
                    this.u = onFrameAvailable.onFrameAvailable(l);
                }
                j = (System.nanoTime() - nanoTime) / 1000000;
                try {
                    this.r.post(this.w);
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException unused) {
                }
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException unused2) {
                j = 0;
            }
            this.s = false;
            if (this.k && a2) {
                try {
                    int k = (int) (this.q.k() - j);
                    if (k > 0) {
                        long j2 = this.p;
                        if (j2 <= 0) {
                            j2 = k;
                        }
                        Thread.sleep(j2);
                    }
                } catch (InterruptedException unused3) {
                }
            } else {
                this.k = false;
                break;
            }
        } while (this.k);
        if (this.t) {
            this.r.post(this.v);
        }
        this.n = null;
        OnAnimationStop onAnimationStop = this.m;
        if (onAnimationStop != null) {
            onAnimationStop.onAnimationStop();
        }
    }

    public void setBytes(byte[] bArr) {
        com.clevertap.android.sdk.gif.a aVar = new com.clevertap.android.sdk.gif.a();
        this.q = aVar;
        try {
            aVar.n(bArr);
            if (this.k) {
                g();
            } else {
                gotoFrame(0);
            }
        } catch (Exception unused) {
            this.q = null;
        }
    }

    public void setFramesDisplayDuration(long j) {
        this.p = j;
    }

    public void setOnAnimationStart(OnAnimationStart onAnimationStart) {
        this.l = onAnimationStart;
    }

    public void setOnAnimationStop(OnAnimationStop onAnimationStop) {
        this.m = onAnimationStop;
    }

    public void setOnFrameAvailable(OnFrameAvailable onFrameAvailable) {
        this.o = onFrameAvailable;
    }

    public void startAnimation() {
        this.k = true;
        g();
    }

    public void stopAnimation() {
        this.k = false;
        Thread thread = this.n;
        if (thread != null) {
            thread.interrupt();
            this.n = null;
        }
    }

    public GifImageView(Context context) {
        super(context);
        this.l = null;
        this.m = null;
        this.o = null;
        this.p = -1L;
        this.r = new Handler(Looper.getMainLooper());
        this.v = new a();
        this.w = new b();
    }
}
