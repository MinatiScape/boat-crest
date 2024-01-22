package com.coveiot.android.tappy.adapter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.IOException;
/* loaded from: classes7.dex */
public final class RegisteredCardAdapterKt {
    public static final Bitmap a(String str) {
        try {
            ParcelFileDescriptor open = ParcelFileDescriptor.open(new File(str), 268435456);
            PdfRenderer pdfRenderer = new PdfRenderer(open);
            PdfRenderer.Page openPage = pdfRenderer.openPage(0);
            Bitmap createBitmap = Bitmap.createBitmap(openPage.getWidth(), openPage.getHeight(), Bitmap.Config.ARGB_8888);
            new Canvas(createBitmap).drawColor(-1);
            openPage.render(createBitmap, null, null, 1);
            openPage.close();
            pdfRenderer.close();
            open.close();
            return createBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
