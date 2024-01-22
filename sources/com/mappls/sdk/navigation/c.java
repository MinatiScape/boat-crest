package com.mappls.sdk.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.gpx.GpxSelectionHelper;
import com.mappls.sdk.navigation.t;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes11.dex */
public final class c implements g {

    /* renamed from: a  reason: collision with root package name */
    public NavigationApplication f12884a;
    public long c;
    public boolean b = false;
    public ArrayList d = new ArrayList();
    public ArrayList e = new ArrayList();
    public boolean f = false;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a(d dVar) {
        }

        @Override // java.lang.Runnable
        public final void run() {
            Iterator it = c.this.e.iterator();
            while (it.hasNext()) {
                ((InterfaceC0639c) it.next()).b();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                c.g(c.this);
            } finally {
                c.this.f = false;
            }
        }
    }

    /* renamed from: com.mappls.sdk.navigation.c$c  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public interface InterfaceC0639c {
        void a();

        void b();
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* loaded from: classes11.dex */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        public static final d f12885a = new d("TASK_CHANGED", 0);
        public static final d b = new d("SAVE_GPX_TRACKS", 1);
        public static final d c = new d("LOAD_GPX_TRACKS", 2);

        private d(String str, int i) {
        }
    }

    public c(NavigationApplication navigationApplication) {
        this.f12884a = navigationApplication;
    }

    public static void g(c cVar) {
        cVar.getClass();
        boolean z = true;
        try {
            try {
                cVar.c = System.currentTimeMillis();
                cVar.a(d.c);
                cVar.f();
                cVar.a(d.b);
                cVar.f12884a.l.post(new e(cVar));
                ArrayList arrayList = cVar.d;
                if (arrayList == null || arrayList.isEmpty()) {
                    return;
                }
            } catch (RuntimeException e) {
                NavigationLogger.d(e);
                cVar.d.add(e.getMessage());
                cVar.f12884a.l.post(new e(cVar));
                ArrayList arrayList2 = cVar.d;
                if (arrayList2 == null || arrayList2.isEmpty()) {
                    return;
                }
            }
            NavigationApplication navigationApplication = cVar.f12884a;
            Iterator it = cVar.d.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (z) {
                    z = false;
                }
            }
            navigationApplication.q();
        } catch (Throwable th) {
            cVar.f12884a.l.post(new e(cVar));
            ArrayList arrayList3 = cVar.d;
            if (arrayList3 != null && !arrayList3.isEmpty()) {
                NavigationApplication navigationApplication2 = cVar.f12884a;
                Iterator it2 = cVar.d.iterator();
                while (it2.hasNext()) {
                    String str2 = (String) it2.next();
                    if (z) {
                        z = false;
                    }
                }
                navigationApplication2.q();
            }
            throw th;
        }
    }

    public final void a(d dVar) {
        if (dVar != d.f12885a) {
            long currentTimeMillis = System.currentTimeMillis();
            NavigationLogger.d("Initialized " + dVar + " in " + (currentTimeMillis - this.c) + " ms", new Object[0]);
            this.c = currentTimeMillis;
        }
        this.f12884a.l.post(new a(dVar));
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void b() {
        if (this.b) {
            return;
        }
        this.b = true;
    }

    public final synchronized void c(Context context, String str) {
        new Thread(new com.mappls.sdk.navigation.b(this, str, context, null)).start();
    }

    public final void e() {
        t k = this.f12884a.k();
        if (!((Boolean) k.o0.get()).booleanValue()) {
            t.InterfaceC0646t<com.mappls.sdk.navigation.d> interfaceC0646t = k.E0;
            com.mappls.sdk.navigation.d dVar = com.mappls.sdk.navigation.d.i;
            t.g gVar = (t.g) interfaceC0646t;
            gVar.getClass();
            gVar.set(dVar);
        }
        NavigationApplication navigationApplication = this.f12884a;
        com.mappls.sdk.navigation.routing.d dVar2 = new com.mappls.sdk.navigation.routing.d(navigationApplication);
        System.currentTimeMillis();
        navigationApplication.q = dVar2;
        NavigationApplication navigationApplication2 = this.f12884a;
        com.mappls.sdk.navigation.session.a aVar = new com.mappls.sdk.navigation.session.a(navigationApplication2);
        System.currentTimeMillis();
        navigationApplication2.r = aVar;
        NavigationApplication navigationApplication3 = this.f12884a;
        com.mappls.sdk.navigation.util.b bVar = new com.mappls.sdk.navigation.util.b(navigationApplication3);
        System.currentTimeMillis();
        navigationApplication3.p = bVar;
        NavigationApplication navigationApplication4 = this.f12884a;
        NavigationLocationProvider navigationLocationProvider = new NavigationLocationProvider(navigationApplication4);
        System.currentTimeMillis();
        navigationApplication4.o = navigationLocationProvider;
        NavigationApplication navigationApplication5 = this.f12884a;
        SavingTrackHelper savingTrackHelper = new SavingTrackHelper(this.f12884a);
        System.currentTimeMillis();
        navigationApplication5.m = savingTrackHelper;
        NavigationApplication navigationApplication6 = this.f12884a;
        NavigationApplication navigationApplication7 = this.f12884a;
        new GpxSelectionHelper(navigationApplication7, navigationApplication7.m);
        System.currentTimeMillis();
        navigationApplication6.getClass();
        NavigationApplication navigationApplication8 = this.f12884a;
        NotificationHelper notificationHelper = new NotificationHelper(navigationApplication8);
        System.currentTimeMillis();
        navigationApplication8.t = notificationHelper;
        NavigationApplication navigationApplication9 = this.f12884a;
        com.mappls.sdk.navigation.helpers.c cVar = new com.mappls.sdk.navigation.helpers.c(navigationApplication9);
        System.currentTimeMillis();
        navigationApplication9.w = cVar;
        NavigationApplication navigationApplication10 = this.f12884a;
        x xVar = new x(navigationApplication10);
        System.currentTimeMillis();
        navigationApplication10.u = xVar;
        NavigationApplication navigationApplication11 = this.f12884a;
        com.mappls.sdk.navigation.refresh.h hVar = new com.mappls.sdk.navigation.refresh.h(navigationApplication11);
        System.currentTimeMillis();
        navigationApplication11.v = hVar;
        NavigationApplication navigationApplication12 = this.f12884a;
        navigationApplication12.a(navigationApplication12);
    }

    public final void f() {
        if (this.f12884a.m.hasDataToSave()) {
            if (System.currentTimeMillis() - this.f12884a.m.getLastTrackPointTime() >= 1800000) {
                this.f12884a.getString(R.string.mappls_saving_gpx_tracks);
                a(d.f12885a);
                try {
                    ArrayList arrayList = this.d;
                    NavigationApplication navigationApplication = this.f12884a;
                    arrayList.addAll(navigationApplication.m.saveDataToGpx(navigationApplication.k.f12912a.d()));
                } catch (RuntimeException e) {
                    NavigationLogger.d(e);
                    this.d.add(e.getMessage());
                }
            } else {
                this.f12884a.m.loadGpxFromDatabase();
            }
        }
        if (!this.f12884a.k().u.get().booleanValue() || s.a() == null) {
            return;
        }
        int intValue = this.f12884a.k().v.get().intValue();
        NavigationApplication navigationApplication2 = this.f12884a;
        int i = NavigationService.p;
        if (intValue < 30000) {
            intValue = 0;
        }
        navigationApplication2.a(2, intValue);
    }

    public final synchronized void h() {
        if (this.f) {
            return;
        }
        this.f = true;
        new Thread(new b(), "Initializing app").start();
    }
}
