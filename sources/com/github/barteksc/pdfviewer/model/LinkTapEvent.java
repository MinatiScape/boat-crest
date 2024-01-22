package com.github.barteksc.pdfviewer.model;

import android.graphics.RectF;
import com.shockwave.pdfium.PdfDocument;
/* loaded from: classes9.dex */
public class LinkTapEvent {

    /* renamed from: a  reason: collision with root package name */
    public float f7920a;
    public float b;
    public float c;
    public float d;
    public RectF e;
    public PdfDocument.Link f;

    public LinkTapEvent(float f, float f2, float f3, float f4, RectF rectF, PdfDocument.Link link) {
        this.f7920a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = rectF;
        this.f = link;
    }

    public float getDocumentX() {
        return this.c;
    }

    public float getDocumentY() {
        return this.d;
    }

    public PdfDocument.Link getLink() {
        return this.f;
    }

    public RectF getMappedLinkRect() {
        return this.e;
    }

    public float getOriginalX() {
        return this.f7920a;
    }

    public float getOriginalY() {
        return this.b;
    }
}
