package com.github.barteksc.pdfviewer;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.github.barteksc.pdfviewer.exception.PageRenderingException;
import com.github.barteksc.pdfviewer.model.PagePart;
/* loaded from: classes9.dex */
public class g extends Handler {
    public static final String f = g.class.getName();

    /* renamed from: a  reason: collision with root package name */
    public PDFView f7916a;
    public RectF b;
    public Rect c;
    public Matrix d;
    public boolean e;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ PagePart h;

        public a(PagePart pagePart) {
            this.h = pagePart;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.f7916a.onBitmapRendered(this.h);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public final /* synthetic */ PageRenderingException h;

        public b(PageRenderingException pageRenderingException) {
            this.h = pageRenderingException;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.f7916a.x(this.h);
        }
    }

    /* loaded from: classes9.dex */
    public class c {

        /* renamed from: a  reason: collision with root package name */
        public float f7917a;
        public float b;
        public RectF c;
        public int d;
        public boolean e;
        public int f;
        public boolean g;
        public boolean h;

        public c(g gVar, float f, float f2, RectF rectF, int i, boolean z, int i2, boolean z2, boolean z3) {
            this.d = i;
            this.f7917a = f;
            this.b = f2;
            this.c = rectF;
            this.e = z;
            this.f = i2;
            this.g = z2;
            this.h = z3;
        }
    }

    public g(Looper looper, PDFView pDFView) {
        super(looper);
        this.b = new RectF();
        this.c = new Rect();
        this.d = new Matrix();
        this.e = false;
        this.f7916a = pDFView;
    }

    public void b(int i, float f2, float f3, RectF rectF, boolean z, int i2, boolean z2, boolean z3) {
        sendMessage(obtainMessage(1, new c(this, f2, f3, rectF, i, z, i2, z2, z3)));
    }

    public final void c(int i, int i2, RectF rectF) {
        this.d.reset();
        float f2 = i;
        float f3 = i2;
        this.d.postTranslate((-rectF.left) * f2, (-rectF.top) * f3);
        this.d.postScale(1.0f / rectF.width(), 1.0f / rectF.height());
        this.b.set(0.0f, 0.0f, f2, f3);
        this.d.mapRect(this.b);
        this.b.round(this.c);
    }

    public final PagePart d(c cVar) throws PageRenderingException {
        f fVar = this.f7916a.n;
        fVar.t(cVar.d);
        int round = Math.round(cVar.f7917a);
        int round2 = Math.round(cVar.b);
        if (round != 0 && round2 != 0 && !fVar.u(cVar.d)) {
            try {
                Bitmap createBitmap = Bitmap.createBitmap(round, round2, cVar.g ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
                c(round, round2, cVar.c);
                fVar.z(createBitmap, cVar.d, this.c, cVar.h);
                return new PagePart(cVar.d, createBitmap, cVar.c, cVar.e, cVar.f);
            } catch (IllegalArgumentException e) {
                Log.e(f, "Cannot create bitmap", e);
            }
        }
        return null;
    }

    public void e() {
        this.e = true;
    }

    public void f() {
        this.e = false;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            PagePart d = d((c) message.obj);
            if (d != null) {
                if (this.e) {
                    this.f7916a.post(new a(d));
                } else {
                    d.getRenderedBitmap().recycle();
                }
            }
        } catch (PageRenderingException e) {
            this.f7916a.post(new b(e));
        }
    }
}
