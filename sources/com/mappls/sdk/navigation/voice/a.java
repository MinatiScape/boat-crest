package com.mappls.sdk.navigation.voice;

import android.media.AudioManager;
import android.os.Build;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.h;
import com.mappls.sdk.navigation.t;
import com.mappls.sdk.navigation.w;
import com.szabh.smable3.entity.Languages;
import java.util.Locale;
/* loaded from: classes11.dex */
public abstract class a implements c, w<com.mappls.sdk.navigation.d> {
    public static boolean c = false;
    public static com.mappls.sdk.navigation.apis.b d;

    /* renamed from: a  reason: collision with root package name */
    public NavigationApplication f13046a;
    public int b;

    public a(NavigationApplication navigationApplication, String str) {
        new Locale(Languages.DEFAULT_LANGUAGE, "IN");
        this.f13046a = navigationApplication;
        long currentTimeMillis = System.currentTimeMillis();
        this.f13046a = navigationApplication;
        StringBuilder a2 = h.a("Initializing prolog system : ");
        a2.append(System.currentTimeMillis() - currentTimeMillis);
        NavigationLogger.i(a2.toString(), new Object[0]);
        this.b = ((Integer) navigationApplication.k().h0.get()).intValue();
        b(navigationApplication.k(), str);
    }

    public final synchronized void a() {
        com.mappls.sdk.navigation.apis.b bVar;
        NavigationLogger.d("abandonAudioFocus", new Object[0]);
        NavigationApplication navigationApplication = this.f13046a;
        if ((navigationApplication != null && ((Integer) navigationApplication.k().h0.a(this.f13046a.k().E0.get())).intValue() == 0) || c) {
            c(false);
        }
        NavigationApplication navigationApplication2 = this.f13046a;
        if (navigationApplication2 != null && (bVar = d) != null) {
            bVar.a(navigationApplication2, navigationApplication2.k().E0.get());
        }
        d = null;
    }

    @Override // com.mappls.sdk.navigation.w
    public final /* bridge */ /* synthetic */ void a(com.mappls.sdk.navigation.d dVar) {
    }

    public final synchronized void b() {
        com.mappls.sdk.navigation.apis.b bVar;
        NavigationApplication navigationApplication;
        NavigationLogger.d("requestAudioFocus", new Object[0]);
        if (Build.VERSION.SDK_INT >= 8) {
            try {
                bVar = new com.mappls.sdk.navigation.apis.b();
            } catch (Exception e) {
                NavigationLogger.d(e);
                bVar = null;
            }
            d = bVar;
        }
        com.mappls.sdk.navigation.apis.b bVar2 = d;
        if (bVar2 != null && (navigationApplication = this.f13046a) != null && bVar2.a(navigationApplication, navigationApplication.k().E0.get(), this.b) && ((Integer) this.f13046a.k().h0.a(this.f13046a.k().E0.get())).intValue() == 0) {
            c(true);
        }
    }

    public final void b(t tVar, String str) {
        try {
            tVar.e();
            tVar.E0.a(this);
        } catch (Exception e) {
            NavigationLogger.e(e, "Loading voice config exception %s", str);
        }
    }

    public final synchronized void c(boolean z) {
        if (!z) {
            AudioManager audioManager = (AudioManager) this.f13046a.getSystemService("audio");
            if (audioManager == null) {
                return;
            }
            audioManager.setBluetoothScoOn(false);
            audioManager.stopBluetoothSco();
            audioManager.setMode(0);
            c = false;
            return;
        }
        try {
            AudioManager audioManager2 = (AudioManager) this.f13046a.getSystemService("audio");
            if (audioManager2 != null && audioManager2.isBluetoothScoAvailableOffCall()) {
                audioManager2.setMode(0);
                audioManager2.startBluetoothSco();
                audioManager2.setBluetoothScoOn(true);
                audioManager2.setMode(3);
                c = true;
            }
        } catch (Exception e) {
            NavigationLogger.d(e);
            c = false;
            e.getMessage();
        }
    }
}
