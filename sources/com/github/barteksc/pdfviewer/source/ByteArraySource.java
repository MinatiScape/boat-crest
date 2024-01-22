package com.github.barteksc.pdfviewer.source;

import android.content.Context;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import java.io.IOException;
/* loaded from: classes9.dex */
public class ByteArraySource implements DocumentSource {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f7923a;

    public ByteArraySource(byte[] bArr) {
        this.f7923a = bArr;
    }

    @Override // com.github.barteksc.pdfviewer.source.DocumentSource
    public PdfDocument createDocument(Context context, PdfiumCore pdfiumCore, String str) throws IOException {
        return pdfiumCore.newDocument(this.f7923a, str);
    }
}
