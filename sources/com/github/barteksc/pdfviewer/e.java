package com.github.barteksc.pdfviewer;

import android.graphics.RectF;
import com.github.barteksc.pdfviewer.util.Constants;
import com.github.barteksc.pdfviewer.util.MathUtils;
import com.github.barteksc.pdfviewer.util.Util;
import com.shockwave.pdfium.util.SizeF;
import java.util.List;
/* loaded from: classes9.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public PDFView f7911a;
    public int b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public final RectF i = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
    public final int j;

    /* loaded from: classes9.dex */
    public class b {

        /* renamed from: a  reason: collision with root package name */
        public int f7912a;
        public int b;

        public b(e eVar) {
        }

        public String toString() {
            return "GridSize{rows=" + this.f7912a + ", cols=" + this.b + '}';
        }
    }

    /* loaded from: classes9.dex */
    public class c {

        /* renamed from: a  reason: collision with root package name */
        public int f7913a;
        public int b;

        public c(e eVar) {
        }

        public String toString() {
            return "Holder{row=" + this.f7913a + ", col=" + this.b + '}';
        }
    }

    /* loaded from: classes9.dex */
    public class d {

        /* renamed from: a  reason: collision with root package name */
        public int f7914a = 0;
        public b b;
        public c c;
        public c d;

        public d(e eVar) {
            this.b = new b();
            this.c = new c();
            this.d = new c();
        }

        public String toString() {
            return "RenderRange{page=" + this.f7914a + ", gridSize=" + this.b + ", leftTop=" + this.c + ", rightBottom=" + this.d + '}';
        }
    }

    public e(PDFView pDFView) {
        this.f7911a = pDFView;
        this.j = Util.getDP(pDFView.getContext(), Constants.PRELOAD_OFFSET);
    }

    public final void a(b bVar) {
        float f = 1.0f / bVar.b;
        this.e = f;
        float f2 = 1.0f / bVar.f7912a;
        this.f = f2;
        float f3 = Constants.PART_SIZE;
        this.g = f3 / f;
        this.h = f3 / f2;
    }

    public final void b(b bVar, int i) {
        SizeF n = this.f7911a.n.n(i);
        float height = (Constants.PART_SIZE * (1.0f / n.getHeight())) / this.f7911a.getZoom();
        float width = (Constants.PART_SIZE * (1.0f / n.getWidth())) / this.f7911a.getZoom();
        bVar.f7912a = MathUtils.ceil(1.0f / height);
        bVar.b = MathUtils.ceil(1.0f / width);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0190  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.github.barteksc.pdfviewer.e.d> c(float r20, float r21, float r22, float r23) {
        /*
            Method dump skipped, instructions count: 513
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.barteksc.pdfviewer.e.c(float, float, float, float):java.util.List");
    }

    public final boolean d(int i, int i2, int i3, float f, float f2) {
        float f3 = i3 * f;
        float f4 = i2 * f2;
        float f5 = this.g;
        float f6 = this.h;
        float f7 = f3 + f > 1.0f ? 1.0f - f3 : f;
        float f8 = f4 + f2 > 1.0f ? 1.0f - f4 : f2;
        float f9 = f5 * f7;
        float f10 = f6 * f8;
        RectF rectF = new RectF(f3, f4, f7 + f3, f8 + f4);
        if (f9 <= 0.0f || f10 <= 0.0f) {
            return false;
        }
        if (!this.f7911a.k.k(i, rectF, this.b)) {
            PDFView pDFView = this.f7911a;
            pDFView.w.b(i, f9, f10, rectF, false, this.b, pDFView.isBestQuality(), this.f7911a.isAnnotationRendering());
        }
        this.b++;
        return true;
    }

    public final int e(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = 0;
        while (i2 <= i3) {
            for (int i8 = i4; i8 <= i5; i8++) {
                if (d(i, i2, i8, this.e, this.f)) {
                    i7++;
                }
                if (i7 >= i6) {
                    return i7;
                }
            }
            i2++;
        }
        return i7;
    }

    public void f() {
        this.b = 1;
        this.c = -MathUtils.max(this.f7911a.getCurrentXOffset(), 0.0f);
        this.d = -MathUtils.max(this.f7911a.getCurrentYOffset(), 0.0f);
        h();
    }

    public final void g(int i) {
        SizeF n = this.f7911a.n.n(i);
        float width = n.getWidth() * Constants.THUMBNAIL_RATIO;
        float height = n.getHeight() * Constants.THUMBNAIL_RATIO;
        if (this.f7911a.k.d(i, this.i)) {
            return;
        }
        PDFView pDFView = this.f7911a;
        pDFView.w.b(i, width, height, this.i, true, 0, pDFView.isBestQuality(), this.f7911a.isAnnotationRendering());
    }

    public final void h() {
        float f = this.j;
        float f2 = this.c;
        float f3 = this.d;
        List<d> c2 = c((-f2) + f, (-f3) + f, ((-f2) - this.f7911a.getWidth()) - f, ((-f3) - this.f7911a.getHeight()) - f);
        for (d dVar : c2) {
            g(dVar.f7914a);
        }
        int i = 0;
        for (d dVar2 : c2) {
            a(dVar2.b);
            int i2 = dVar2.f7914a;
            c cVar = dVar2.c;
            int i3 = cVar.f7913a;
            c cVar2 = dVar2.d;
            i += e(i2, i3, cVar2.f7913a, cVar.b, cVar2.b, Constants.Cache.CACHE_SIZE - i);
            if (i >= Constants.Cache.CACHE_SIZE) {
                return;
            }
        }
    }
}
