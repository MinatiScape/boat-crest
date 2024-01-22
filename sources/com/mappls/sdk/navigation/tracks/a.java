package com.mappls.sdk.navigation.tracks;

import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationService;
import com.mappls.sdk.navigation.d;
import com.mappls.sdk.navigation.iface.ISaveTrackListener;
import com.mappls.sdk.navigation.s;
import com.mappls.sdk.navigation.t;
import com.mappls.sdk.navigation.u;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public final class a extends s {
    public t c;
    public NavigationApplication d;

    /* renamed from: com.mappls.sdk.navigation.tracks.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0647a extends u.b<Void, Object, Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f12964a;
        public final /* synthetic */ ISaveTrackListener b;

        public C0647a(String str, ISaveTrackListener iSaveTrackListener) {
            this.f12964a = str;
            this.b = iSaveTrackListener;
        }

        @Override // com.mappls.sdk.navigation.u.b
        public final Object a(Void[] voidArr) {
            try {
                SavingTrackHelper j = a.this.d.j();
                String str = this.f12964a;
                if (str == null) {
                    str = "";
                }
                j.saveDataToGpx(str, a.this.d.c().a());
                j.close();
                a.this.d.getNotificationHelper().showNotifications();
                return null;
            } catch (Throwable th) {
                a.this.d.getNotificationHelper().showNotifications();
                throw th;
            }
        }

        @Override // com.mappls.sdk.navigation.u.b
        public final void a(Object obj) {
            a.this.getClass();
            a.this.getClass();
            ISaveTrackListener iSaveTrackListener = this.b;
            if (iSaveTrackListener != null) {
                iSaveTrackListener.onSuccess();
            }
        }

        @Override // com.mappls.sdk.navigation.u.b
        public final void a() {
            a.this.getClass();
            a.this.getClass();
        }
    }

    public a(NavigationApplication navigationApplication) {
        this.d = navigationApplication;
        ArrayList b = d.b();
        d.a((d[]) b.toArray(new d[b.size()]));
        this.c = navigationApplication.k();
    }

    public final void a(String str, ISaveTrackListener iSaveTrackListener) {
        this.d.n().getClass();
        u.a(new C0647a(str, iSaveTrackListener), null);
    }

    public final void b(NavigationApplication navigationApplication) {
        Integer num = this.c.v.get();
        Boolean bool = this.c.w.get();
        if (bool.booleanValue() || navigationApplication == null) {
            this.d.j().startNewSegment();
            this.c.v.set(num);
            this.c.u.set(Boolean.TRUE);
            this.c.w.set(bool);
            int intValue = this.c.v.get().intValue();
            NavigationApplication navigationApplication2 = this.d;
            int i = NavigationService.p;
            if (intValue < 30000) {
                intValue = 0;
            }
            navigationApplication2.a(2, intValue);
        }
    }

    @Override // com.mappls.sdk.navigation.s
    public final void c() {
    }

    @Override // com.mappls.sdk.navigation.s
    public final void d() {
    }

    public final void e() {
        this.c.u.set(Boolean.FALSE);
        if (this.d.f() != null) {
            NavigationService f = this.d.f();
            NavigationApplication navigationApplication = this.d;
            int i = NavigationService.p;
            f.a(navigationApplication, 2);
        }
    }
}
