package com.mappls.sdk.navigation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import com.mappls.sdk.navigation.apis.NavigationLogger;
/* loaded from: classes11.dex */
public final class b implements Runnable {
    public final /* synthetic */ String h;
    public final /* synthetic */ Context i;
    public final /* synthetic */ ProgressDialog j;
    public final /* synthetic */ Runnable k = null;
    public final /* synthetic */ c l;

    public b(c cVar, String str, Context context, ProgressDialog progressDialog) {
        this.l = cVar;
        this.h = str;
        this.i = context;
        this.j = progressDialog;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NavigationApplication navigationApplication;
        NavigationApplication navigationApplication2;
        NavigationApplication navigationApplication3;
        NavigationApplication navigationApplication4;
        NavigationApplication navigationApplication5;
        NavigationApplication navigationApplication6;
        Context context;
        NavigationApplication navigationApplication7;
        try {
            navigationApplication2 = this.l.f12884a;
            if (navigationApplication2.s != null) {
                navigationApplication7 = this.l.f12884a;
                navigationApplication7.s.h();
            }
            navigationApplication3 = this.l.f12884a;
            String str = this.h;
            navigationApplication4 = this.l.f12884a;
            navigationApplication3.s = str != null ? new com.mappls.sdk.navigation.voice.g(this.i, navigationApplication4.q.o(), str) : null;
            navigationApplication5 = this.l.f12884a;
            com.mappls.sdk.navigation.routing.h o = navigationApplication5.q.o();
            navigationApplication6 = this.l.f12884a;
            com.mappls.sdk.navigation.voice.g gVar = navigationApplication6.s;
            o.getClass();
            com.mappls.sdk.navigation.routing.h.a(gVar);
            ProgressDialog progressDialog = this.j;
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            Runnable runnable = this.k;
            if (runnable == null || (context = this.i) == null || !(context instanceof Activity)) {
                return;
            }
            ((Activity) context).runOnUiThread(runnable);
        } catch (com.mappls.sdk.navigation.voice.d e) {
            ProgressDialog progressDialog2 = this.j;
            if (progressDialog2 != null) {
                progressDialog2.dismiss();
            }
            navigationApplication = this.l.f12884a;
            navigationApplication.q();
            NavigationLogger.d(e);
        }
    }
}
