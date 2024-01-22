package com.github.barteksc.pdfviewer.source;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import java.io.File;
import java.io.IOException;
/* loaded from: classes9.dex */
public class FileSource implements DocumentSource {

    /* renamed from: a  reason: collision with root package name */
    public File f7924a;

    public FileSource(File file) {
        this.f7924a = file;
    }

    @Override // com.github.barteksc.pdfviewer.source.DocumentSource
    public PdfDocument createDocument(Context context, PdfiumCore pdfiumCore, String str) throws IOException {
        return pdfiumCore.newDocument(ParcelFileDescriptor.open(this.f7924a, 268435456), str);
    }
}
