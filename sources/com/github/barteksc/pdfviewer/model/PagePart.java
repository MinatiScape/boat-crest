package com.github.barteksc.pdfviewer.model;

import android.graphics.Bitmap;
import android.graphics.RectF;
/* loaded from: classes9.dex */
public class PagePart {

    /* renamed from: a  reason: collision with root package name */
    public int f7921a;
    public Bitmap b;
    public RectF c;
    public boolean d;
    public int e;

    public PagePart(int i, Bitmap bitmap, RectF rectF, boolean z, int i2) {
        this.f7921a = i;
        this.b = bitmap;
        this.c = rectF;
        this.d = z;
        this.e = i2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof PagePart) {
            PagePart pagePart = (PagePart) obj;
            return pagePart.getPage() == this.f7921a && pagePart.getPageRelativeBounds().left == this.c.left && pagePart.getPageRelativeBounds().right == this.c.right && pagePart.getPageRelativeBounds().top == this.c.top && pagePart.getPageRelativeBounds().bottom == this.c.bottom;
        }
        return false;
    }

    public int getCacheOrder() {
        return this.e;
    }

    public int getPage() {
        return this.f7921a;
    }

    public RectF getPageRelativeBounds() {
        return this.c;
    }

    public Bitmap getRenderedBitmap() {
        return this.b;
    }

    public boolean isThumbnail() {
        return this.d;
    }

    public void setCacheOrder(int i) {
        this.e = i;
    }
}
