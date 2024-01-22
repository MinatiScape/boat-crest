package com.github.barteksc.pdfviewer.scroll;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.R;
import com.github.barteksc.pdfviewer.util.Util;
/* loaded from: classes9.dex */
public class DefaultScrollHandle extends RelativeLayout implements ScrollHandle {
    public Context context;
    public float h;
    public boolean i;
    public PDFView j;
    public float k;
    public Handler l;
    public Runnable m;
    public TextView textView;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DefaultScrollHandle.this.hide();
        }
    }

    public DefaultScrollHandle(Context context) {
        this(context, false);
    }

    private void setPosition(float f) {
        int width;
        if (Float.isInfinite(f) || Float.isNaN(f)) {
            return;
        }
        if (this.j.isSwipeVertical()) {
            width = this.j.getHeight();
        } else {
            width = this.j.getWidth();
        }
        float f2 = width;
        float f3 = f - this.h;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        } else if (f3 > f2 - Util.getDP(this.context, 40)) {
            f3 = f2 - Util.getDP(this.context, 40);
        }
        if (this.j.isSwipeVertical()) {
            setY(f3);
        } else {
            setX(f3);
        }
        a();
        invalidate();
    }

    public final void a() {
        float x;
        float width;
        int width2;
        if (this.j.isSwipeVertical()) {
            x = getY();
            width = getHeight();
            width2 = this.j.getHeight();
        } else {
            x = getX();
            width = getWidth();
            width2 = this.j.getWidth();
        }
        this.h = ((x + this.h) / width2) * width;
    }

    public final boolean b() {
        PDFView pDFView = this.j;
        return (pDFView == null || pDFView.getPageCount() <= 0 || this.j.documentFitsView()) ? false : true;
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void destroyLayout() {
        this.j.removeView(this);
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void hide() {
        setVisibility(4);
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void hideDelayed() {
        this.l.postDelayed(this.m, 1000L);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007d  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.b()
            if (r0 != 0) goto Lb
            boolean r5 = super.onTouchEvent(r5)
            return r5
        Lb:
            int r0 = r5.getAction()
            r1 = 1
            if (r0 == 0) goto L2e
            if (r0 == r1) goto L25
            r2 = 2
            if (r0 == r2) goto L59
            r2 = 3
            if (r0 == r2) goto L25
            r2 = 5
            if (r0 == r2) goto L2e
            r2 = 6
            if (r0 == r2) goto L25
            boolean r5 = super.onTouchEvent(r5)
            return r5
        L25:
            r4.hideDelayed()
            com.github.barteksc.pdfviewer.PDFView r5 = r4.j
            r5.performPageSnap()
            return r1
        L2e:
            com.github.barteksc.pdfviewer.PDFView r0 = r4.j
            r0.stopFling()
            android.os.Handler r0 = r4.l
            java.lang.Runnable r2 = r4.m
            r0.removeCallbacks(r2)
            com.github.barteksc.pdfviewer.PDFView r0 = r4.j
            boolean r0 = r0.isSwipeVertical()
            if (r0 == 0) goto L4e
            float r0 = r5.getRawY()
            float r2 = r4.getY()
            float r0 = r0 - r2
            r4.k = r0
            goto L59
        L4e:
            float r0 = r5.getRawX()
            float r2 = r4.getX()
            float r0 = r0 - r2
            r4.k = r0
        L59:
            com.github.barteksc.pdfviewer.PDFView r0 = r4.j
            boolean r0 = r0.isSwipeVertical()
            r2 = 0
            if (r0 == 0) goto L7d
            float r5 = r5.getRawY()
            float r0 = r4.k
            float r5 = r5 - r0
            float r0 = r4.h
            float r5 = r5 + r0
            r4.setPosition(r5)
            com.github.barteksc.pdfviewer.PDFView r5 = r4.j
            float r0 = r4.h
            int r3 = r4.getHeight()
            float r3 = (float) r3
            float r0 = r0 / r3
            r5.setPositionOffset(r0, r2)
            goto L97
        L7d:
            float r5 = r5.getRawX()
            float r0 = r4.k
            float r5 = r5 - r0
            float r0 = r4.h
            float r5 = r5 + r0
            r4.setPosition(r5)
            com.github.barteksc.pdfviewer.PDFView r5 = r4.j
            float r0 = r4.h
            int r3 = r4.getWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            r5.setPositionOffset(r0, r2)
        L97:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void setPageNum(int i) {
        String valueOf = String.valueOf(i);
        if (this.textView.getText().equals(valueOf)) {
            return;
        }
        this.textView.setText(valueOf);
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void setScroll(float f) {
        if (!shown()) {
            show();
        } else {
            this.l.removeCallbacks(this.m);
        }
        PDFView pDFView = this.j;
        if (pDFView != null) {
            setPosition((pDFView.isSwipeVertical() ? this.j.getHeight() : this.j.getWidth()) * f);
        }
    }

    public void setTextColor(int i) {
        this.textView.setTextColor(i);
    }

    public void setTextSize(int i) {
        this.textView.setTextSize(1, i);
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void setupLayout(PDFView pDFView) {
        int i;
        Drawable drawable;
        int i2 = 65;
        int i3 = 40;
        if (pDFView.isSwipeVertical()) {
            if (this.i) {
                i = 9;
                drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_left);
            } else {
                i = 11;
                drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_right);
            }
        } else {
            if (this.i) {
                i = 10;
                drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_top);
            } else {
                i = 12;
                drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_bottom);
            }
            i3 = 65;
            i2 = 40;
        }
        if (Build.VERSION.SDK_INT < 16) {
            setBackgroundDrawable(drawable);
        } else {
            setBackground(drawable);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Util.getDP(this.context, i2), Util.getDP(this.context, i3));
        layoutParams.setMargins(0, 0, 0, 0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        addView(this.textView, layoutParams2);
        layoutParams.addRule(i);
        pDFView.addView(this, layoutParams);
        this.j = pDFView;
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void show() {
        setVisibility(0);
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public boolean shown() {
        return getVisibility() == 0;
    }

    public DefaultScrollHandle(Context context, boolean z) {
        super(context);
        this.h = 0.0f;
        this.l = new Handler();
        this.m = new a();
        this.context = context;
        this.i = z;
        this.textView = new TextView(context);
        setVisibility(4);
        setTextColor(ViewCompat.MEASURED_STATE_MASK);
        setTextSize(16);
    }
}
