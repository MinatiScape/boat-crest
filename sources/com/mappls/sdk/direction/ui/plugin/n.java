package com.mappls.sdk.direction.ui.plugin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.mappls.sdk.direction.ui.plugin.j;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/* loaded from: classes11.dex */
public final class n implements Runnable {
    public final /* synthetic */ String h;
    public final /* synthetic */ j.c i;

    public n(String str, j.c cVar) {
        this.h = str;
        this.i = cVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(this.h);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        try {
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.i.a(bitmap);
    }
}
