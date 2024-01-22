package com.github.barteksc.pdfviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.github.barteksc.pdfviewer.exception.PageRenderingException;
import com.github.barteksc.pdfviewer.link.DefaultLinkHandler;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.listener.Callbacks;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnLongPressListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.github.barteksc.pdfviewer.model.PagePart;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
import com.github.barteksc.pdfviewer.source.AssetSource;
import com.github.barteksc.pdfviewer.source.ByteArraySource;
import com.github.barteksc.pdfviewer.source.DocumentSource;
import com.github.barteksc.pdfviewer.source.FileSource;
import com.github.barteksc.pdfviewer.source.InputStreamSource;
import com.github.barteksc.pdfviewer.source.UriSource;
import com.github.barteksc.pdfviewer.util.Constants;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.github.barteksc.pdfviewer.util.MathUtils;
import com.github.barteksc.pdfviewer.util.SnapEdge;
import com.github.barteksc.pdfviewer.util.Util;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import com.shockwave.pdfium.util.Size;
import com.shockwave.pdfium.util.SizeF;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
public class PDFView extends RelativeLayout {
    public static final float DEFAULT_MAX_SCALE = 3.0f;
    public static final float DEFAULT_MID_SCALE = 1.75f;
    public static final float DEFAULT_MIN_SCALE = 1.0f;
    public static final String a0 = PDFView.class.getSimpleName();
    public Paint A;
    public FitPolicy B;
    public boolean C;
    public int D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public PdfiumCore J;
    public ScrollHandle K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public boolean P;
    public PaintFlagsDrawFilter Q;
    public int R;
    public boolean S;
    public boolean T;
    public List<Integer> U;
    public boolean V;
    public Configurator W;
    public float h;
    public float i;
    public float j;
    public com.github.barteksc.pdfviewer.b k;
    public com.github.barteksc.pdfviewer.a l;
    public d m;
    public f n;
    public int o;
    public float p;
    public float q;
    public float r;
    public boolean s;
    public c t;
    public com.github.barteksc.pdfviewer.c u;
    public HandlerThread v;
    public g w;
    public e x;
    public Callbacks y;
    public Paint z;

    /* loaded from: classes9.dex */
    public class Configurator {
        public boolean A;
        public boolean B;

        /* renamed from: a  reason: collision with root package name */
        public final DocumentSource f7907a;
        public int[] b;
        public boolean c;
        public boolean d;
        public OnDrawListener e;
        public OnDrawListener f;
        public OnLoadCompleteListener g;
        public OnErrorListener h;
        public OnPageChangeListener i;
        public OnPageScrollListener j;
        public OnRenderListener k;
        public OnTapListener l;
        public OnLongPressListener m;
        public OnPageErrorListener n;
        public LinkHandler o;
        public int p;
        public boolean q;
        public boolean r;
        public String s;
        public ScrollHandle t;
        public boolean u;
        public int v;
        public boolean w;
        public FitPolicy x;
        public boolean y;
        public boolean z;

        public Configurator autoSpacing(boolean z) {
            this.w = z;
            return this;
        }

        public Configurator defaultPage(int i) {
            this.p = i;
            return this;
        }

        public Configurator disableLongpress() {
            PDFView.this.m.d();
            return this;
        }

        public Configurator enableAnnotationRendering(boolean z) {
            this.r = z;
            return this;
        }

        public Configurator enableAntialiasing(boolean z) {
            this.u = z;
            return this;
        }

        public Configurator enableDoubletap(boolean z) {
            this.d = z;
            return this;
        }

        public Configurator enableSwipe(boolean z) {
            this.c = z;
            return this;
        }

        public Configurator fitEachPage(boolean z) {
            this.y = z;
            return this;
        }

        public Configurator linkHandler(LinkHandler linkHandler) {
            this.o = linkHandler;
            return this;
        }

        public void load() {
            if (!PDFView.this.V) {
                PDFView.this.W = this;
                return;
            }
            PDFView.this.recycle();
            PDFView.this.y.setOnLoadComplete(this.g);
            PDFView.this.y.setOnError(this.h);
            PDFView.this.y.setOnDraw(this.e);
            PDFView.this.y.setOnDrawAll(this.f);
            PDFView.this.y.setOnPageChange(this.i);
            PDFView.this.y.setOnPageScroll(this.j);
            PDFView.this.y.setOnRender(this.k);
            PDFView.this.y.setOnTap(this.l);
            PDFView.this.y.setOnLongPress(this.m);
            PDFView.this.y.setOnPageError(this.n);
            PDFView.this.y.setLinkHandler(this.o);
            PDFView.this.setSwipeEnabled(this.c);
            PDFView.this.setNightMode(this.B);
            PDFView.this.o(this.d);
            PDFView.this.setDefaultPage(this.p);
            PDFView.this.setSwipeVertical(!this.q);
            PDFView.this.enableAnnotationRendering(this.r);
            PDFView.this.setScrollHandle(this.t);
            PDFView.this.enableAntialiasing(this.u);
            PDFView.this.setSpacing(this.v);
            PDFView.this.setAutoSpacing(this.w);
            PDFView.this.setPageFitPolicy(this.x);
            PDFView.this.setFitEachPage(this.y);
            PDFView.this.setPageSnap(this.A);
            PDFView.this.setPageFling(this.z);
            int[] iArr = this.b;
            if (iArr != null) {
                PDFView.this.t(this.f7907a, this.s, iArr);
            } else {
                PDFView.this.s(this.f7907a, this.s);
            }
        }

        public Configurator nightMode(boolean z) {
            this.B = z;
            return this;
        }

        public Configurator onDraw(OnDrawListener onDrawListener) {
            this.e = onDrawListener;
            return this;
        }

        public Configurator onDrawAll(OnDrawListener onDrawListener) {
            this.f = onDrawListener;
            return this;
        }

        public Configurator onError(OnErrorListener onErrorListener) {
            this.h = onErrorListener;
            return this;
        }

        public Configurator onLoad(OnLoadCompleteListener onLoadCompleteListener) {
            this.g = onLoadCompleteListener;
            return this;
        }

        public Configurator onLongPress(OnLongPressListener onLongPressListener) {
            this.m = onLongPressListener;
            return this;
        }

        public Configurator onPageChange(OnPageChangeListener onPageChangeListener) {
            this.i = onPageChangeListener;
            return this;
        }

        public Configurator onPageError(OnPageErrorListener onPageErrorListener) {
            this.n = onPageErrorListener;
            return this;
        }

        public Configurator onPageScroll(OnPageScrollListener onPageScrollListener) {
            this.j = onPageScrollListener;
            return this;
        }

        public Configurator onRender(OnRenderListener onRenderListener) {
            this.k = onRenderListener;
            return this;
        }

        public Configurator onTap(OnTapListener onTapListener) {
            this.l = onTapListener;
            return this;
        }

        public Configurator pageFitPolicy(FitPolicy fitPolicy) {
            this.x = fitPolicy;
            return this;
        }

        public Configurator pageFling(boolean z) {
            this.z = z;
            return this;
        }

        public Configurator pageSnap(boolean z) {
            this.A = z;
            return this;
        }

        public Configurator pages(int... iArr) {
            this.b = iArr;
            return this;
        }

        public Configurator password(String str) {
            this.s = str;
            return this;
        }

        public Configurator scrollHandle(ScrollHandle scrollHandle) {
            this.t = scrollHandle;
            return this;
        }

        public Configurator spacing(int i) {
            this.v = i;
            return this;
        }

        public Configurator swipeHorizontal(boolean z) {
            this.q = z;
            return this;
        }

        public Configurator(DocumentSource documentSource) {
            this.b = null;
            this.c = true;
            this.d = true;
            this.o = new DefaultLinkHandler(PDFView.this);
            this.p = 0;
            this.q = false;
            this.r = false;
            this.s = null;
            this.t = null;
            this.u = true;
            this.v = 0;
            this.w = false;
            this.x = FitPolicy.WIDTH;
            this.y = false;
            this.z = false;
            this.A = false;
            this.B = false;
            this.f7907a = documentSource;
        }
    }

    /* loaded from: classes9.dex */
    public enum b {
        NONE,
        START,
        END
    }

    /* loaded from: classes9.dex */
    public enum c {
        DEFAULT,
        LOADED,
        SHOWN,
        ERROR
    }

    public PDFView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 1.0f;
        this.i = 1.75f;
        this.j = 3.0f;
        b bVar = b.NONE;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 1.0f;
        this.s = true;
        this.t = c.DEFAULT;
        this.y = new Callbacks();
        this.B = FitPolicy.WIDTH;
        this.C = false;
        this.D = 0;
        this.E = true;
        this.F = true;
        this.G = true;
        this.H = false;
        this.I = true;
        this.L = false;
        this.M = false;
        this.N = false;
        this.O = false;
        this.P = true;
        this.Q = new PaintFlagsDrawFilter(0, 3);
        this.R = 0;
        this.S = false;
        this.T = true;
        this.U = new ArrayList(10);
        this.V = false;
        this.v = new HandlerThread("PDF renderer");
        if (isInEditMode()) {
            return;
        }
        this.k = new com.github.barteksc.pdfviewer.b();
        com.github.barteksc.pdfviewer.a aVar = new com.github.barteksc.pdfviewer.a(this);
        this.l = aVar;
        this.m = new d(this, aVar);
        this.x = new e(this);
        this.z = new Paint();
        Paint paint = new Paint();
        this.A = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.J = new PdfiumCore(context);
        setWillNotDraw(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAutoSpacing(boolean z) {
        this.S = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDefaultPage(int i) {
        this.D = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFitEachPage(boolean z) {
        this.C = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPageFitPolicy(FitPolicy fitPolicy) {
        this.B = fitPolicy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScrollHandle(ScrollHandle scrollHandle) {
        this.K = scrollHandle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSpacing(int i) {
        this.R = Util.getDP(getContext(), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSwipeVertical(boolean z) {
        this.E = z;
    }

    public float A(int i, SnapEdge snapEdge) {
        float f;
        float m = this.n.m(i, this.r);
        float height = this.E ? getHeight() : getWidth();
        float k = this.n.k(i, this.r);
        if (snapEdge == SnapEdge.CENTER) {
            f = m - (height / 2.0f);
            k /= 2.0f;
        } else if (snapEdge != SnapEdge.END) {
            return m;
        } else {
            f = m - height;
        }
        return f + k;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        f fVar = this.n;
        if (fVar == null) {
            return true;
        }
        if (this.E) {
            if (i >= 0 || this.p >= 0.0f) {
                return i > 0 && this.p + toCurrentScale(fVar.h()) > ((float) getWidth());
            }
            return true;
        } else if (i >= 0 || this.p >= 0.0f) {
            return i > 0 && this.p + fVar.e(this.r) > ((float) getWidth());
        } else {
            return true;
        }
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i) {
        f fVar = this.n;
        if (fVar == null) {
            return true;
        }
        if (this.E) {
            if (i >= 0 || this.q >= 0.0f) {
                return i > 0 && this.q + fVar.e(this.r) > ((float) getHeight());
            }
            return true;
        } else if (i >= 0 || this.q >= 0.0f) {
            return i > 0 && this.q + toCurrentScale(fVar.f()) > ((float) getHeight());
        } else {
            return true;
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (isInEditMode()) {
            return;
        }
        this.l.d();
    }

    public boolean doRenderDuringScale() {
        return this.O;
    }

    public boolean documentFitsView() {
        float e = this.n.e(1.0f);
        return this.E ? e < ((float) getHeight()) : e < ((float) getWidth());
    }

    public void enableAnnotationRendering(boolean z) {
        this.N = z;
    }

    public void enableAntialiasing(boolean z) {
        this.P = z;
    }

    public void enableRenderDuringScale(boolean z) {
        this.O = z;
    }

    public void fitToWidth(int i) {
        if (this.t != c.SHOWN) {
            Log.e(a0, "Cannot fit, document not rendered yet");
            return;
        }
        zoomTo(getWidth() / this.n.n(i).getWidth());
        jumpTo(i);
    }

    public Configurator fromAsset(String str) {
        return new Configurator(new AssetSource(str));
    }

    public Configurator fromBytes(byte[] bArr) {
        return new Configurator(new ByteArraySource(bArr));
    }

    public Configurator fromFile(File file) {
        return new Configurator(new FileSource(file));
    }

    public Configurator fromSource(DocumentSource documentSource) {
        return new Configurator(documentSource);
    }

    public Configurator fromStream(InputStream inputStream) {
        return new Configurator(new InputStreamSource(inputStream));
    }

    public Configurator fromUri(Uri uri) {
        return new Configurator(new UriSource(uri));
    }

    public int getCurrentPage() {
        return this.o;
    }

    public float getCurrentXOffset() {
        return this.p;
    }

    public float getCurrentYOffset() {
        return this.q;
    }

    public PdfDocument.Meta getDocumentMeta() {
        f fVar = this.n;
        if (fVar == null) {
            return null;
        }
        return fVar.i();
    }

    public List<PdfDocument.Link> getLinks(int i) {
        f fVar = this.n;
        if (fVar == null) {
            return Collections.emptyList();
        }
        return fVar.l(i);
    }

    public float getMaxZoom() {
        return this.j;
    }

    public float getMidZoom() {
        return this.i;
    }

    public float getMinZoom() {
        return this.h;
    }

    public int getPageAtPositionOffset(float f) {
        f fVar = this.n;
        return fVar.j(fVar.e(this.r) * f, this.r);
    }

    public int getPageCount() {
        f fVar = this.n;
        if (fVar == null) {
            return 0;
        }
        return fVar.p();
    }

    public FitPolicy getPageFitPolicy() {
        return this.B;
    }

    public SizeF getPageSize(int i) {
        f fVar = this.n;
        if (fVar == null) {
            return new SizeF(0.0f, 0.0f);
        }
        return fVar.n(i);
    }

    public float getPositionOffset() {
        float f;
        float e;
        int width;
        if (this.E) {
            f = -this.q;
            e = this.n.e(this.r);
            width = getHeight();
        } else {
            f = -this.p;
            e = this.n.e(this.r);
            width = getWidth();
        }
        return MathUtils.limit(f / (e - width), 0.0f, 1.0f);
    }

    public ScrollHandle getScrollHandle() {
        return this.K;
    }

    public int getSpacingPx() {
        return this.R;
    }

    public List<PdfDocument.Bookmark> getTableOfContents() {
        f fVar = this.n;
        if (fVar == null) {
            return Collections.emptyList();
        }
        return fVar.d();
    }

    public float getZoom() {
        return this.r;
    }

    public boolean isAnnotationRendering() {
        return this.N;
    }

    public boolean isAntialiasing() {
        return this.P;
    }

    public boolean isAutoSpacingEnabled() {
        return this.S;
    }

    public boolean isBestQuality() {
        return this.M;
    }

    public boolean isFitEachPage() {
        return this.C;
    }

    public boolean isPageFlingEnabled() {
        return this.T;
    }

    public boolean isPageSnap() {
        return this.I;
    }

    public boolean isRecycled() {
        return this.s;
    }

    public boolean isSwipeEnabled() {
        return this.F;
    }

    public boolean isSwipeVertical() {
        return this.E;
    }

    public boolean isZooming() {
        return this.r != this.h;
    }

    public void jumpTo(int i, boolean z) {
        f fVar = this.n;
        if (fVar == null) {
            return;
        }
        int a2 = fVar.a(i);
        float f = a2 == 0 ? 0.0f : -this.n.m(a2, this.r);
        if (this.E) {
            if (z) {
                this.l.j(this.q, f);
            } else {
                moveTo(this.p, f);
            }
        } else if (z) {
            this.l.i(this.p, f);
        } else {
            moveTo(f, this.q);
        }
        z(a2);
    }

    public void loadPages() {
        g gVar;
        if (this.n == null || (gVar = this.w) == null) {
            return;
        }
        gVar.removeMessages(1);
        this.k.i();
        this.x.f();
        y();
    }

    public final void m(Canvas canvas, PagePart pagePart) {
        float m;
        float currentScale;
        RectF pageRelativeBounds = pagePart.getPageRelativeBounds();
        Bitmap renderedBitmap = pagePart.getRenderedBitmap();
        if (renderedBitmap.isRecycled()) {
            return;
        }
        SizeF n = this.n.n(pagePart.getPage());
        if (this.E) {
            currentScale = this.n.m(pagePart.getPage(), this.r);
            m = toCurrentScale(this.n.h() - n.getWidth()) / 2.0f;
        } else {
            m = this.n.m(pagePart.getPage(), this.r);
            currentScale = toCurrentScale(this.n.f() - n.getHeight()) / 2.0f;
        }
        canvas.translate(m, currentScale);
        Rect rect = new Rect(0, 0, renderedBitmap.getWidth(), renderedBitmap.getHeight());
        float currentScale2 = toCurrentScale(pageRelativeBounds.left * n.getWidth());
        float currentScale3 = toCurrentScale(pageRelativeBounds.top * n.getHeight());
        RectF rectF = new RectF((int) currentScale2, (int) currentScale3, (int) (currentScale2 + toCurrentScale(pageRelativeBounds.width() * n.getWidth())), (int) (currentScale3 + toCurrentScale(pageRelativeBounds.height() * n.getHeight())));
        float f = this.p + m;
        float f2 = this.q + currentScale;
        if (rectF.left + f < getWidth() && f + rectF.right > 0.0f && rectF.top + f2 < getHeight() && f2 + rectF.bottom > 0.0f) {
            canvas.drawBitmap(renderedBitmap, rect, rectF, this.z);
            if (Constants.DEBUG_MODE) {
                this.A.setColor(pagePart.getPage() % 2 == 0 ? SupportMenu.CATEGORY_MASK : -16776961);
                canvas.drawRect(rectF, this.A);
            }
            canvas.translate(-m, -currentScale);
            return;
        }
        canvas.translate(-m, -currentScale);
    }

    public void moveRelativeTo(float f, float f2) {
        moveTo(this.p + f, this.q + f2);
    }

    public void moveTo(float f, float f2) {
        moveTo(f, f2, true);
    }

    public final void n(Canvas canvas, int i, OnDrawListener onDrawListener) {
        float f;
        if (onDrawListener != null) {
            float f2 = 0.0f;
            if (this.E) {
                f = this.n.m(i, this.r);
            } else {
                f2 = this.n.m(i, this.r);
                f = 0.0f;
            }
            canvas.translate(f2, f);
            SizeF n = this.n.n(i);
            onDrawListener.onLayerDrawn(canvas, toCurrentScale(n.getWidth()), toCurrentScale(n.getHeight()), i);
            canvas.translate(-f2, -f);
        }
    }

    public void o(boolean z) {
        this.G = z;
    }

    public void onBitmapRendered(PagePart pagePart) {
        if (this.t == c.LOADED) {
            this.t = c.SHOWN;
            this.y.callOnRender(this.n.p());
        }
        if (pagePart.isThumbnail()) {
            this.k.c(pagePart);
        } else {
            this.k.b(pagePart);
        }
        y();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        recycle();
        HandlerThread handlerThread = this.v;
        if (handlerThread != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                handlerThread.quitSafely();
            } else {
                handlerThread.quit();
            }
            this.v = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (isInEditMode()) {
            return;
        }
        if (this.P) {
            canvas.setDrawFilter(this.Q);
        }
        Drawable background = getBackground();
        if (background == null) {
            canvas.drawColor(this.H ? ViewCompat.MEASURED_STATE_MASK : -1);
        } else {
            background.draw(canvas);
        }
        if (!this.s && this.t == c.SHOWN) {
            float f = this.p;
            float f2 = this.q;
            canvas.translate(f, f2);
            for (PagePart pagePart : this.k.g()) {
                m(canvas, pagePart);
            }
            for (PagePart pagePart2 : this.k.f()) {
                m(canvas, pagePart2);
                if (this.y.getOnDrawAll() != null && !this.U.contains(Integer.valueOf(pagePart2.getPage()))) {
                    this.U.add(Integer.valueOf(pagePart2.getPage()));
                }
            }
            for (Integer num : this.U) {
                n(canvas, num.intValue(), this.y.getOnDrawAll());
            }
            this.U.clear();
            n(canvas, this.o, this.y.getOnDraw());
            canvas.translate(-f, -f2);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        float e;
        float f;
        this.V = true;
        Configurator configurator = this.W;
        if (configurator != null) {
            configurator.load();
        }
        if (isInEditMode() || this.t != c.SHOWN) {
            return;
        }
        float f2 = (-this.p) + (i3 * 0.5f);
        float f3 = (-this.q) + (i4 * 0.5f);
        if (this.E) {
            e = f2 / this.n.h();
            f = this.n.e(this.r);
        } else {
            e = f2 / this.n.e(this.r);
            f = this.n.f();
        }
        float f4 = f3 / f;
        this.l.l();
        this.n.y(new Size(i, i2));
        if (this.E) {
            this.p = ((-e) * this.n.h()) + (i * 0.5f);
            this.q = ((-f4) * this.n.e(this.r)) + (i2 * 0.5f);
        } else {
            this.p = ((-e) * this.n.e(this.r)) + (i * 0.5f);
            this.q = ((-f4) * this.n.f()) + (i2 * 0.5f);
        }
        moveTo(this.p, this.q);
        w();
    }

    public int p(float f, float f2) {
        boolean z = this.E;
        if (z) {
            f = f2;
        }
        float height = z ? getHeight() : getWidth();
        if (f > -1.0f) {
            return 0;
        }
        if (f < (-this.n.e(this.r)) + height + 1.0f) {
            return this.n.p() - 1;
        }
        return this.n.j(-(f - (height / 2.0f)), this.r);
    }

    public boolean pageFillsScreen() {
        float f = -this.n.m(this.o, this.r);
        float k = f - this.n.k(this.o, this.r);
        if (isSwipeVertical()) {
            float f2 = this.q;
            return f > f2 && k < f2 - ((float) getHeight());
        }
        float f3 = this.p;
        return f > f3 && k < f3 - ((float) getWidth());
    }

    public void performPageSnap() {
        f fVar;
        int p;
        SnapEdge q;
        if (!this.I || (fVar = this.n) == null || fVar.p() == 0 || (q = q((p = p(this.p, this.q)))) == SnapEdge.NONE) {
            return;
        }
        float A = A(p, q);
        if (this.E) {
            this.l.j(this.q, -A);
        } else {
            this.l.i(this.p, -A);
        }
    }

    public SnapEdge q(int i) {
        if (this.I && i >= 0) {
            float f = this.E ? this.q : this.p;
            float f2 = -this.n.m(i, this.r);
            int height = this.E ? getHeight() : getWidth();
            float k = this.n.k(i, this.r);
            float f3 = height;
            if (f3 >= k) {
                return SnapEdge.CENTER;
            }
            if (f >= f2) {
                return SnapEdge.START;
            }
            if (f2 - k > f - f3) {
                return SnapEdge.END;
            }
            return SnapEdge.NONE;
        }
        return SnapEdge.NONE;
    }

    public boolean r() {
        return this.G;
    }

    public void recycle() {
        this.W = null;
        this.l.l();
        this.m.c();
        g gVar = this.w;
        if (gVar != null) {
            gVar.f();
            this.w.removeMessages(1);
        }
        com.github.barteksc.pdfviewer.c cVar = this.u;
        if (cVar != null) {
            cVar.cancel(true);
        }
        this.k.j();
        ScrollHandle scrollHandle = this.K;
        if (scrollHandle != null && this.L) {
            scrollHandle.destroyLayout();
        }
        f fVar = this.n;
        if (fVar != null) {
            fVar.b();
            this.n = null;
        }
        this.w = null;
        this.K = null;
        this.L = false;
        this.q = 0.0f;
        this.p = 0.0f;
        this.r = 1.0f;
        this.s = true;
        this.y = new Callbacks();
        this.t = c.DEFAULT;
    }

    public void resetZoom() {
        zoomTo(this.h);
    }

    public void resetZoomWithAnimation() {
        zoomWithAnimation(this.h);
    }

    public final void s(DocumentSource documentSource, String str) {
        t(documentSource, str, null);
    }

    public void setMaxZoom(float f) {
        this.j = f;
    }

    public void setMidZoom(float f) {
        this.i = f;
    }

    public void setMinZoom(float f) {
        this.h = f;
    }

    public void setNightMode(boolean z) {
        this.H = z;
        if (z) {
            this.z.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f})));
            return;
        }
        this.z.setColorFilter(null);
    }

    public void setPageFling(boolean z) {
        this.T = z;
    }

    public void setPageSnap(boolean z) {
        this.I = z;
    }

    public void setPositionOffset(float f, boolean z) {
        if (this.E) {
            moveTo(this.p, ((-this.n.e(this.r)) + getHeight()) * f, z);
        } else {
            moveTo(((-this.n.e(this.r)) + getWidth()) * f, this.q, z);
        }
        w();
    }

    public void setSwipeEnabled(boolean z) {
        this.F = z;
    }

    public void stopFling() {
        this.l.m();
    }

    public final void t(DocumentSource documentSource, String str, int[] iArr) {
        if (this.s) {
            this.s = false;
            com.github.barteksc.pdfviewer.c cVar = new com.github.barteksc.pdfviewer.c(documentSource, str, iArr, this, this.J);
            this.u = cVar;
            cVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        throw new IllegalStateException("Don't call load on a PDF View without recycling it first.");
    }

    public float toCurrentScale(float f) {
        return f * this.r;
    }

    public float toRealScale(float f) {
        return f / this.r;
    }

    public void u(f fVar) {
        this.t = c.LOADED;
        this.n = fVar;
        if (!this.v.isAlive()) {
            this.v.start();
        }
        g gVar = new g(this.v.getLooper(), this);
        this.w = gVar;
        gVar.e();
        ScrollHandle scrollHandle = this.K;
        if (scrollHandle != null) {
            scrollHandle.setupLayout(this);
            this.L = true;
        }
        this.m.e();
        this.y.callOnLoadComplete(fVar.p());
        jumpTo(this.D, false);
    }

    public void useBestQuality(boolean z) {
        this.M = z;
    }

    public void v(Throwable th) {
        this.t = c.ERROR;
        OnErrorListener onError = this.y.getOnError();
        recycle();
        invalidate();
        if (onError != null) {
            onError.onError(th);
        } else {
            Log.e("PDFView", "load pdf error", th);
        }
    }

    public void w() {
        float f;
        int width;
        if (this.n.p() == 0) {
            return;
        }
        if (this.E) {
            f = this.q;
            width = getHeight();
        } else {
            f = this.p;
            width = getWidth();
        }
        int j = this.n.j(-(f - (width / 2.0f)), this.r);
        if (j >= 0 && j <= this.n.p() - 1 && j != getCurrentPage()) {
            z(j);
        } else {
            loadPages();
        }
    }

    public void x(PageRenderingException pageRenderingException) {
        if (this.y.callOnPageError(pageRenderingException.getPage(), pageRenderingException.getCause())) {
            return;
        }
        String str = a0;
        Log.e(str, "Cannot open page " + pageRenderingException.getPage(), pageRenderingException.getCause());
    }

    public void y() {
        invalidate();
    }

    public void z(int i) {
        if (this.s) {
            return;
        }
        this.o = this.n.a(i);
        loadPages();
        if (this.K != null && !documentFitsView()) {
            this.K.setPageNum(this.o + 1);
        }
        this.y.callOnPageChange(this.o, this.n.p());
    }

    public void zoomCenteredRelativeTo(float f, PointF pointF) {
        zoomCenteredTo(this.r * f, pointF);
    }

    public void zoomCenteredTo(float f, PointF pointF) {
        float f2 = f / this.r;
        zoomTo(f);
        float f3 = pointF.x;
        float f4 = pointF.y;
        moveTo((this.p * f2) + (f3 - (f3 * f2)), (this.q * f2) + (f4 - (f2 * f4)));
    }

    public void zoomTo(float f) {
        this.r = f;
    }

    public void zoomWithAnimation(float f, float f2, float f3) {
        this.l.k(f, f2, this.r, f3);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void moveTo(float r6, float r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.barteksc.pdfviewer.PDFView.moveTo(float, float, boolean):void");
    }

    public void zoomWithAnimation(float f) {
        this.l.k(getWidth() / 2, getHeight() / 2, this.r, f);
    }

    public void setPositionOffset(float f) {
        setPositionOffset(f, true);
    }

    public void jumpTo(int i) {
        jumpTo(i, false);
    }
}
