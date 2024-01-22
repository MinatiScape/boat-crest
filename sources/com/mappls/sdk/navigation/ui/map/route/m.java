package com.mappls.sdk.navigation.ui.map.route;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import timber.log.Timber;
/* loaded from: classes11.dex */
public class m implements Runnable {
    public final /* synthetic */ ReportDetails h;
    public final /* synthetic */ String i;
    public final /* synthetic */ a j;

    public m(j jVar, ReportDetails reportDetails, String str, a aVar) {
        this.h = reportDetails;
        this.i = str;
        this.j = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        URL url;
        try {
            url = new URL(this.h.getReportIcon("24px"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            com.mappls.sdk.navigation.ui.d.a().a(this.i, decodeStream);
            a aVar = this.j;
            l lVar = (l) aVar;
            lVar.f12986a.b.getStyle(new k(lVar, this.i, decodeStream));
        } catch (IOException e2) {
            e2.printStackTrace();
            ((l) this.j).getClass();
            Timber.e("There is some error in loading image", new Object[0]);
        }
    }
}
