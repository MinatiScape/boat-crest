package com.github.barteksc.pdfviewer;

import android.os.AsyncTask;
import com.github.barteksc.pdfviewer.source.DocumentSource;
import com.shockwave.pdfium.PdfiumCore;
import com.shockwave.pdfium.util.Size;
import java.lang.ref.WeakReference;
/* loaded from: classes9.dex */
public class c extends AsyncTask<Void, Void, Throwable> {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7910a = false;
    public WeakReference<PDFView> b;
    public PdfiumCore c;
    public String d;
    public DocumentSource e;
    public int[] f;
    public f g;

    public c(DocumentSource documentSource, String str, int[] iArr, PDFView pDFView, PdfiumCore pdfiumCore) {
        this.e = documentSource;
        this.f = iArr;
        this.b = new WeakReference<>(pDFView);
        this.d = str;
        this.c = pdfiumCore;
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public Throwable doInBackground(Void... voidArr) {
        try {
            PDFView pDFView = this.b.get();
            if (pDFView != null) {
                this.g = new f(this.c, this.e.createDocument(pDFView.getContext(), this.c, this.d), pDFView.getPageFitPolicy(), b(pDFView), this.f, pDFView.isSwipeVertical(), pDFView.getSpacingPx(), pDFView.isAutoSpacingEnabled(), pDFView.isFitEachPage());
                return null;
            }
            return new NullPointerException("pdfView == null");
        } catch (Throwable th) {
            return th;
        }
    }

    public final Size b(PDFView pDFView) {
        return new Size(pDFView.getWidth(), pDFView.getHeight());
    }

    @Override // android.os.AsyncTask
    /* renamed from: c */
    public void onPostExecute(Throwable th) {
        PDFView pDFView = this.b.get();
        if (pDFView != null) {
            if (th != null) {
                pDFView.v(th);
            } else if (this.f7910a) {
            } else {
                pDFView.u(this.g);
            }
        }
    }

    @Override // android.os.AsyncTask
    public void onCancelled() {
        this.f7910a = true;
    }
}
