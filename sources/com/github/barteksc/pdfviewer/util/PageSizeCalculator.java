package com.github.barteksc.pdfviewer.util;

import com.shockwave.pdfium.util.Size;
import com.shockwave.pdfium.util.SizeF;
/* loaded from: classes9.dex */
public class PageSizeCalculator {

    /* renamed from: a  reason: collision with root package name */
    public FitPolicy f7927a;
    public final Size b;
    public final Size c;
    public final Size d;
    public SizeF e;
    public SizeF f;
    public float g;
    public float h;
    public boolean i;

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7928a;

        static {
            int[] iArr = new int[FitPolicy.values().length];
            f7928a = iArr;
            try {
                iArr[FitPolicy.HEIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7928a[FitPolicy.BOTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public PageSizeCalculator(FitPolicy fitPolicy, Size size, Size size2, Size size3, boolean z) {
        this.f7927a = fitPolicy;
        this.b = size;
        this.c = size2;
        this.d = size3;
        this.i = z;
        a();
    }

    public final void a() {
        int i = a.f7928a[this.f7927a.ordinal()];
        if (i == 1) {
            SizeF c = c(this.c, this.d.getHeight());
            this.f = c;
            this.h = c.getHeight() / this.c.getHeight();
            Size size = this.b;
            this.e = c(size, size.getHeight() * this.h);
        } else if (i != 2) {
            SizeF d = d(this.b, this.d.getWidth());
            this.e = d;
            this.g = d.getWidth() / this.b.getWidth();
            Size size2 = this.c;
            this.f = d(size2, size2.getWidth() * this.g);
        } else {
            float width = b(this.b, this.d.getWidth(), this.d.getHeight()).getWidth() / this.b.getWidth();
            Size size3 = this.c;
            SizeF b = b(size3, size3.getWidth() * width, this.d.getHeight());
            this.f = b;
            this.h = b.getHeight() / this.c.getHeight();
            SizeF b2 = b(this.b, this.d.getWidth(), this.b.getHeight() * this.h);
            this.e = b2;
            this.g = b2.getWidth() / this.b.getWidth();
        }
    }

    public final SizeF b(Size size, float f, float f2) {
        float width = size.getWidth() / size.getHeight();
        float floor = (float) Math.floor(f / width);
        if (floor > f2) {
            f = (float) Math.floor(width * f2);
        } else {
            f2 = floor;
        }
        return new SizeF(f, f2);
    }

    public final SizeF c(Size size, float f) {
        return new SizeF((float) Math.floor(f / (size.getHeight() / size.getWidth())), f);
    }

    public SizeF calculate(Size size) {
        if (size.getWidth() > 0 && size.getHeight() > 0) {
            float width = this.i ? this.d.getWidth() : size.getWidth() * this.g;
            float height = this.i ? this.d.getHeight() : size.getHeight() * this.h;
            int i = a.f7928a[this.f7927a.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    return d(size, width);
                }
                return b(size, width, height);
            }
            return c(size, height);
        }
        return new SizeF(0.0f, 0.0f);
    }

    public final SizeF d(Size size, float f) {
        return new SizeF(f, (float) Math.floor(f / (size.getWidth() / size.getHeight())));
    }

    public SizeF getOptimalMaxHeightPageSize() {
        return this.f;
    }

    public SizeF getOptimalMaxWidthPageSize() {
        return this.e;
    }
}
