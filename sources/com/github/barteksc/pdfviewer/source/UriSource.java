package com.github.barteksc.pdfviewer.source;

import android.content.Context;
import android.net.Uri;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import java.io.IOException;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes9.dex */
public class UriSource implements DocumentSource {

    /* renamed from: a  reason: collision with root package name */
    public Uri f7926a;

    public UriSource(Uri uri) {
        this.f7926a = uri;
    }

    @Override // com.github.barteksc.pdfviewer.source.DocumentSource
    public PdfDocument createDocument(Context context, PdfiumCore pdfiumCore, String str) throws IOException {
        return pdfiumCore.newDocument(context.getContentResolver().openFileDescriptor(this.f7926a, RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME), str);
    }
}
