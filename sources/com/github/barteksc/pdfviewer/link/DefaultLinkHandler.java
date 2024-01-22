package com.github.barteksc.pdfviewer.link;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;
/* loaded from: classes9.dex */
public class DefaultLinkHandler implements LinkHandler {
    public static final String b = "DefaultLinkHandler";

    /* renamed from: a  reason: collision with root package name */
    public PDFView f7918a;

    public DefaultLinkHandler(PDFView pDFView) {
        this.f7918a = pDFView;
    }

    public final void a(int i) {
        this.f7918a.jumpTo(i);
    }

    public final void b(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        Context context = this.f7918a.getContext();
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            return;
        }
        String str2 = b;
        Log.w(str2, "No activity found for URI: " + str);
    }

    @Override // com.github.barteksc.pdfviewer.link.LinkHandler
    public void handleLinkEvent(LinkTapEvent linkTapEvent) {
        String uri = linkTapEvent.getLink().getUri();
        Integer destPageIdx = linkTapEvent.getLink().getDestPageIdx();
        if (uri != null && !uri.isEmpty()) {
            b(uri);
        } else if (destPageIdx != null) {
            a(destPageIdx.intValue());
        }
    }
}
