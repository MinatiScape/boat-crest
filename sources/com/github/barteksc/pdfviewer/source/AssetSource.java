package com.github.barteksc.pdfviewer.source;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.github.barteksc.pdfviewer.util.FileUtils;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import java.io.IOException;
/* loaded from: classes9.dex */
public class AssetSource implements DocumentSource {

    /* renamed from: a  reason: collision with root package name */
    public final String f7922a;

    public AssetSource(String str) {
        this.f7922a = str;
    }

    @Override // com.github.barteksc.pdfviewer.source.DocumentSource
    public PdfDocument createDocument(Context context, PdfiumCore pdfiumCore, String str) throws IOException {
        return pdfiumCore.newDocument(ParcelFileDescriptor.open(FileUtils.fileFromAsset(context, this.f7922a), 268435456), str);
    }
}
