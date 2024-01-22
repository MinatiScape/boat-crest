package com.github.barteksc.pdfviewer;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.SparseBooleanArray;
import com.github.barteksc.pdfviewer.exception.PageRenderingException;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.github.barteksc.pdfviewer.util.PageSizeCalculator;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import com.shockwave.pdfium.util.Size;
import com.shockwave.pdfium.util.SizeF;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class f {
    public static final Object t = new Object();

    /* renamed from: a  reason: collision with root package name */
    public PdfDocument f7915a;
    public PdfiumCore b;
    public boolean k;
    public int l;
    public boolean m;
    public final FitPolicy q;
    public final boolean r;
    public int[] s;
    public int c = 0;
    public List<Size> d = new ArrayList();
    public List<SizeF> e = new ArrayList();
    public SparseBooleanArray f = new SparseBooleanArray();
    public Size g = new Size(0, 0);
    public Size h = new Size(0, 0);
    public SizeF i = new SizeF(0.0f, 0.0f);
    public SizeF j = new SizeF(0.0f, 0.0f);
    public List<Float> n = new ArrayList();
    public List<Float> o = new ArrayList();
    public float p = 0.0f;

    public f(PdfiumCore pdfiumCore, PdfDocument pdfDocument, FitPolicy fitPolicy, Size size, int[] iArr, boolean z, int i, boolean z2, boolean z3) {
        this.b = pdfiumCore;
        this.f7915a = pdfDocument;
        this.q = fitPolicy;
        this.s = iArr;
        this.k = z;
        this.l = i;
        this.m = z2;
        this.r = z3;
        A(size);
    }

    public final void A(Size size) {
        int[] iArr = this.s;
        if (iArr != null) {
            this.c = iArr.length;
        } else {
            this.c = this.b.getPageCount(this.f7915a);
        }
        for (int i = 0; i < this.c; i++) {
            Size pageSize = this.b.getPageSize(this.f7915a, c(i));
            if (pageSize.getWidth() > this.g.getWidth()) {
                this.g = pageSize;
            }
            if (pageSize.getHeight() > this.h.getHeight()) {
                this.h = pageSize;
            }
            this.d.add(pageSize);
        }
        y(size);
    }

    public int a(int i) {
        int p;
        if (i <= 0) {
            return 0;
        }
        int[] iArr = this.s;
        if (iArr != null) {
            if (i >= iArr.length) {
                p = iArr.length;
                return p - 1;
            }
            return i;
        }
        if (i >= p()) {
            p = p();
            return p - 1;
        }
        return i;
    }

    public void b() {
        PdfDocument pdfDocument;
        PdfiumCore pdfiumCore = this.b;
        if (pdfiumCore != null && (pdfDocument = this.f7915a) != null) {
            pdfiumCore.closeDocument(pdfDocument);
        }
        this.f7915a = null;
        this.s = null;
    }

    public int c(int i) {
        int i2;
        int[] iArr = this.s;
        if (iArr == null) {
            i2 = i;
        } else if (i < 0 || i >= iArr.length) {
            return -1;
        } else {
            i2 = iArr[i];
        }
        if (i2 < 0 || i >= p()) {
            return -1;
        }
        return i2;
    }

    public List<PdfDocument.Bookmark> d() {
        PdfDocument pdfDocument = this.f7915a;
        if (pdfDocument == null) {
            return new ArrayList();
        }
        return this.b.getTableOfContents(pdfDocument);
    }

    public float e(float f) {
        return this.p * f;
    }

    public float f() {
        return g().getHeight();
    }

    public SizeF g() {
        return this.k ? this.j : this.i;
    }

    public float h() {
        return g().getWidth();
    }

    public PdfDocument.Meta i() {
        PdfDocument pdfDocument = this.f7915a;
        if (pdfDocument == null) {
            return null;
        }
        return this.b.getDocumentMeta(pdfDocument);
    }

    public int j(float f, float f2) {
        int i = 0;
        for (int i2 = 0; i2 < p() && (this.n.get(i2).floatValue() * f2) - (o(i2, f2) / 2.0f) < f; i2++) {
            i++;
        }
        int i3 = i - 1;
        if (i3 >= 0) {
            return i3;
        }
        return 0;
    }

    public float k(int i, float f) {
        SizeF n = n(i);
        return (this.k ? n.getHeight() : n.getWidth()) * f;
    }

    public List<PdfDocument.Link> l(int i) {
        return this.b.getPageLinks(this.f7915a, c(i));
    }

    public float m(int i, float f) {
        if (c(i) < 0) {
            return 0.0f;
        }
        return this.n.get(i).floatValue() * f;
    }

    public SizeF n(int i) {
        if (c(i) < 0) {
            return new SizeF(0.0f, 0.0f);
        }
        return this.e.get(i);
    }

    public float o(int i, float f) {
        return (this.m ? this.o.get(i).floatValue() : this.l) * f;
    }

    public int p() {
        return this.c;
    }

    public SizeF q(int i, float f) {
        SizeF n = n(i);
        return new SizeF(n.getWidth() * f, n.getHeight() * f);
    }

    public float r(int i, float f) {
        float f2;
        float height;
        SizeF n = n(i);
        if (this.k) {
            f2 = h();
            height = n.getWidth();
        } else {
            f2 = f();
            height = n.getHeight();
        }
        return (f * (f2 - height)) / 2.0f;
    }

    public RectF s(int i, int i2, int i3, int i4, int i5, RectF rectF) {
        return this.b.mapRectToDevice(this.f7915a, c(i), i2, i3, i4, i5, 0, rectF);
    }

    public boolean t(int i) throws PageRenderingException {
        int c = c(i);
        if (c < 0) {
            return false;
        }
        synchronized (t) {
            if (this.f.indexOfKey(c) < 0) {
                try {
                    this.b.openPage(this.f7915a, c);
                    this.f.put(c, true);
                    return true;
                } catch (Exception e) {
                    this.f.put(c, false);
                    throw new PageRenderingException(i, e);
                }
            }
            return false;
        }
    }

    public boolean u(int i) {
        return !this.f.get(c(i), false);
    }

    public final void v(Size size) {
        float width;
        float width2;
        this.o.clear();
        for (int i = 0; i < p(); i++) {
            SizeF sizeF = this.e.get(i);
            if (this.k) {
                width = size.getHeight();
                width2 = sizeF.getHeight();
            } else {
                width = size.getWidth();
                width2 = sizeF.getWidth();
            }
            float max = Math.max(0.0f, width - width2);
            if (i < p() - 1) {
                max += this.l;
            }
            this.o.add(Float.valueOf(max));
        }
    }

    public final void w() {
        float f;
        float f2 = 0.0f;
        for (int i = 0; i < p(); i++) {
            SizeF sizeF = this.e.get(i);
            f2 += this.k ? sizeF.getHeight() : sizeF.getWidth();
            if (this.m) {
                f = this.o.get(i).floatValue();
            } else if (i < p() - 1) {
                f = this.l;
            }
            f2 += f;
        }
        this.p = f2;
    }

    public final void x() {
        float f;
        this.n.clear();
        float f2 = 0.0f;
        for (int i = 0; i < p(); i++) {
            SizeF sizeF = this.e.get(i);
            float height = this.k ? sizeF.getHeight() : sizeF.getWidth();
            if (this.m) {
                f2 += this.o.get(i).floatValue() / 2.0f;
                if (i == 0) {
                    f2 -= this.l / 2.0f;
                } else if (i == p() - 1) {
                    f2 += this.l / 2.0f;
                }
                this.n.add(Float.valueOf(f2));
                f = this.o.get(i).floatValue() / 2.0f;
            } else {
                this.n.add(Float.valueOf(f2));
                f = this.l;
            }
            f2 += height + f;
        }
    }

    public void y(Size size) {
        this.e.clear();
        PageSizeCalculator pageSizeCalculator = new PageSizeCalculator(this.q, this.g, this.h, size, this.r);
        this.j = pageSizeCalculator.getOptimalMaxWidthPageSize();
        this.i = pageSizeCalculator.getOptimalMaxHeightPageSize();
        for (Size size2 : this.d) {
            this.e.add(pageSizeCalculator.calculate(size2));
        }
        if (this.m) {
            v(size);
        }
        w();
        x();
    }

    public void z(Bitmap bitmap, int i, Rect rect, boolean z) {
        this.b.renderPageBitmap(this.f7915a, bitmap, c(i), rect.left, rect.top, rect.width(), rect.height(), z);
    }
}
