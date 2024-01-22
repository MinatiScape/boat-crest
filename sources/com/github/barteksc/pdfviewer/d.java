package com.github.barteksc.pdfviewer;

import android.graphics.PointF;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
import com.github.barteksc.pdfviewer.util.Constants;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.util.SizeF;
/* loaded from: classes9.dex */
public class d implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {
    public PDFView h;
    public a i;
    public GestureDetector j;
    public ScaleGestureDetector k;
    public boolean l = false;
    public boolean m = false;
    public boolean n = false;

    public d(PDFView pDFView, a aVar) {
        this.h = pDFView;
        this.i = aVar;
        this.j = new GestureDetector(pDFView.getContext(), this);
        this.k = new ScaleGestureDetector(pDFView.getContext(), this);
        pDFView.setOnTouchListener(this);
    }

    public final boolean a(float f, float f2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if (this.h.isSwipeVertical()) {
            if (abs2 > abs) {
                return true;
            }
        } else if (abs > abs2) {
            return true;
        }
        return false;
    }

    public final boolean b(float f, float f2) {
        int r;
        int m;
        PDFView pDFView = this.h;
        f fVar = pDFView.n;
        if (fVar == null) {
            return false;
        }
        float f3 = (-pDFView.getCurrentXOffset()) + f;
        float f4 = (-this.h.getCurrentYOffset()) + f2;
        int j = fVar.j(this.h.isSwipeVertical() ? f4 : f3, this.h.getZoom());
        SizeF q = fVar.q(j, this.h.getZoom());
        if (this.h.isSwipeVertical()) {
            m = (int) fVar.r(j, this.h.getZoom());
            r = (int) fVar.m(j, this.h.getZoom());
        } else {
            r = (int) fVar.r(j, this.h.getZoom());
            m = (int) fVar.m(j, this.h.getZoom());
        }
        int i = m;
        int i2 = r;
        for (PdfDocument.Link link : fVar.l(j)) {
            RectF s = fVar.s(j, i, i2, (int) q.getWidth(), (int) q.getHeight(), link.getBounds());
            s.sort();
            if (s.contains(f3, f4)) {
                this.h.y.callLinkHandler(new LinkTapEvent(f, f2, f3, f4, s, link));
                return true;
            }
        }
        return false;
    }

    public void c() {
        this.n = false;
    }

    public void d() {
        this.j.setIsLongpressEnabled(false);
    }

    public void e() {
        this.n = true;
    }

    public final void f() {
        ScrollHandle scrollHandle = this.h.getScrollHandle();
        if (scrollHandle == null || !scrollHandle.shown()) {
            return;
        }
        scrollHandle.hideDelayed();
    }

    public final void g(float f, float f2) {
        float f3;
        float f4;
        int currentXOffset = (int) this.h.getCurrentXOffset();
        int currentYOffset = (int) this.h.getCurrentYOffset();
        PDFView pDFView = this.h;
        f fVar = pDFView.n;
        float f5 = -fVar.m(pDFView.getCurrentPage(), this.h.getZoom());
        float k = f5 - fVar.k(this.h.getCurrentPage(), this.h.getZoom());
        float f6 = 0.0f;
        if (this.h.isSwipeVertical()) {
            f4 = -(this.h.toCurrentScale(fVar.h()) - this.h.getWidth());
            f3 = k + this.h.getHeight();
            f6 = f5;
            f5 = 0.0f;
        } else {
            float width = k + this.h.getWidth();
            f3 = -(this.h.toCurrentScale(fVar.f()) - this.h.getHeight());
            f4 = width;
        }
        this.i.g(currentXOffset, currentYOffset, (int) f, (int) f2, (int) f4, (int) f5, (int) f3, (int) f6);
    }

    public final void h(MotionEvent motionEvent) {
        this.h.loadPages();
        f();
        if (this.i.f()) {
            return;
        }
        this.h.performPageSnap();
    }

    public final void i(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float x;
        float x2;
        if (a(f, f2)) {
            int i = -1;
            if (!this.h.isSwipeVertical() ? f <= 0.0f : f2 <= 0.0f) {
                i = 1;
            }
            if (this.h.isSwipeVertical()) {
                x = motionEvent2.getY();
                x2 = motionEvent.getY();
            } else {
                x = motionEvent2.getX();
                x2 = motionEvent.getX();
            }
            float f3 = x - x2;
            int max = Math.max(0, Math.min(this.h.getPageCount() - 1, this.h.p(this.h.getCurrentXOffset() - (this.h.getZoom() * f3), this.h.getCurrentYOffset() - (f3 * this.h.getZoom())) + i));
            this.i.h(-this.h.A(max, this.h.q(max)));
        }
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.h.r()) {
            if (this.h.getZoom() < this.h.getMidZoom()) {
                this.h.zoomWithAnimation(motionEvent.getX(), motionEvent.getY(), this.h.getMidZoom());
                return true;
            } else if (this.h.getZoom() < this.h.getMaxZoom()) {
                this.h.zoomWithAnimation(motionEvent.getX(), motionEvent.getY(), this.h.getMaxZoom());
                return true;
            } else {
                this.h.resetZoomWithAnimation();
                return true;
            }
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        this.i.m();
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float f3;
        float currentScale;
        int height;
        if (this.h.isSwipeEnabled()) {
            if (this.h.isPageFlingEnabled()) {
                if (this.h.pageFillsScreen()) {
                    g(f, f2);
                } else {
                    i(motionEvent, motionEvent2, f, f2);
                }
                return true;
            }
            int currentXOffset = (int) this.h.getCurrentXOffset();
            int currentYOffset = (int) this.h.getCurrentYOffset();
            PDFView pDFView = this.h;
            f fVar = pDFView.n;
            if (pDFView.isSwipeVertical()) {
                f3 = -(this.h.toCurrentScale(fVar.h()) - this.h.getWidth());
                currentScale = fVar.e(this.h.getZoom());
                height = this.h.getHeight();
            } else {
                f3 = -(fVar.e(this.h.getZoom()) - this.h.getWidth());
                currentScale = this.h.toCurrentScale(fVar.f());
                height = this.h.getHeight();
            }
            this.i.g(currentXOffset, currentYOffset, (int) f, (int) f2, (int) f3, 0, (int) (-(currentScale - height)), 0);
            return true;
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        this.h.y.callOnLongPress(motionEvent);
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        float zoom = this.h.getZoom() * scaleFactor;
        float min = Math.min(Constants.Pinch.MINIMUM_ZOOM, this.h.getMinZoom());
        float min2 = Math.min(Constants.Pinch.MAXIMUM_ZOOM, this.h.getMaxZoom());
        if (zoom < min) {
            scaleFactor = min / this.h.getZoom();
        } else if (zoom > min2) {
            scaleFactor = min2 / this.h.getZoom();
        }
        this.h.zoomCenteredRelativeTo(scaleFactor, new PointF(scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY()));
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        this.m = true;
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        this.h.loadPages();
        f();
        this.m = false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.l = true;
        if (this.h.isZooming() || this.h.isSwipeEnabled()) {
            this.h.moveRelativeTo(-f, -f2);
        }
        if (!this.m || this.h.doRenderDuringScale()) {
            this.h.w();
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        ScrollHandle scrollHandle;
        boolean callOnTap = this.h.y.callOnTap(motionEvent);
        boolean b = b(motionEvent.getX(), motionEvent.getY());
        if (!callOnTap && !b && (scrollHandle = this.h.getScrollHandle()) != null && !this.h.documentFitsView()) {
            if (!scrollHandle.shown()) {
                scrollHandle.show();
            } else {
                scrollHandle.hide();
            }
        }
        this.h.performClick();
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.n) {
            boolean z = this.j.onTouchEvent(motionEvent) || this.k.onTouchEvent(motionEvent);
            if (motionEvent.getAction() == 1 && this.l) {
                this.l = false;
                h(motionEvent);
            }
            return z;
        }
        return false;
    }
}
