package com.mappls.sdk.navigation.routing;

import android.media.SoundPool;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.ConnectionResult;
import com.jieli.jl_rcsp.constant.Command;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationConstants;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.helpers.c;
import com.mappls.sdk.navigation.o;
import com.mappls.sdk.navigation.routing.a;
import com.mappls.sdk.navigation.t;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.codec.language.Soundex;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes11.dex */
public final class h {
    public static com.mappls.sdk.navigation.voice.c n = null;
    public static boolean o = false;
    public static int p = 0;
    public static float q = 0.0f;
    public static long r = 0;
    public static long s = 0;
    public static long t = 0;
    public static long u = 0;
    public static boolean v = false;
    public static long w;
    public static NavigationStep x;

    /* renamed from: a  reason: collision with root package name */
    public final d f12950a;
    public final t b;
    public ConcurrentHashMap<b, Integer> l;
    public float c = 12.0f;
    public float d = 5.0f;
    public int e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public int j = 0;
    public int k = 0;
    public double m = 0.0d;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            for (b bVar : h.this.l.keySet()) {
                bVar.a();
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a();
    }

    public h(d dVar, t tVar) {
        this.f12950a = dVar;
        this.b = tVar;
        o = !((Boolean) tVar.m0.get()).booleanValue();
        this.l = new ConcurrentHashMap<>();
    }

    public static void a() {
        c();
        if (v) {
            v = false;
        }
    }

    public static void a(com.mappls.sdk.navigation.voice.c cVar) {
        n = cVar;
    }

    public static void a(boolean z) {
        o = z;
    }

    public static String b(com.mappls.sdk.navigation.router.c cVar) {
        StringBuilder a2 = com.mappls.sdk.navigation.h.a("voice router= ");
        a2.append(cVar.c());
        NavigationLogger.d(a2.toString(), new Object[0]);
        if (cVar.c() == 0) {
            return "left";
        }
        if (1 == cVar.c()) {
            return "left_sh";
        }
        if (2 == cVar.c()) {
            return "left_sl";
        }
        if (3 == cVar.c()) {
            return "right";
        }
        if (4 == cVar.c()) {
            return "right_sh";
        }
        if (5 == cVar.c()) {
            return "right_sl";
        }
        if (9 == cVar.c()) {
            return "left_keep";
        }
        if (10 == cVar.c()) {
            return "right_keep";
        }
        if (40 == cVar.c()) {
            return "border_crossing";
        }
        if (36 == cVar.c()) {
            return "take_ferry";
        }
        if (37 == cVar.c()) {
            return "leave_ferry";
        }
        if (21 == cVar.c()) {
            return "continue_straight_at";
        }
        if (20 == cVar.c()) {
            return "keep_right_at";
        }
        if (19 == cVar.c()) {
            return "keep_left_at";
        }
        if (11 == cVar.c()) {
            return "take_left_at";
        }
        if (12 == cVar.c()) {
            return "take_right_at";
        }
        if (14 == cVar.c()) {
            return "take_right_at_end";
        }
        if (13 == cVar.c()) {
            return "take_left_at_end";
        }
        if (15 == cVar.c()) {
            return "at_fork_left";
        }
        if (16 == cVar.c()) {
            return "at_fork_right";
        }
        if (19 == cVar.c()) {
            return "take_left_at_intersection";
        }
        if (20 == cVar.c()) {
            return "take_right_at_intersection";
        }
        if (34 == cVar.c()) {
            return "highway_exit_left_driving_road";
        }
        if (35 == cVar.c()) {
            return "highway_enter_left_driving_road";
        }
        return null;
    }

    public static com.mappls.sdk.navigation.voice.b c() {
        if (n == null) {
            return null;
        }
        w = System.currentTimeMillis();
        com.mappls.sdk.navigation.voice.a aVar = (com.mappls.sdk.navigation.voice.a) n;
        aVar.getClass();
        return new com.mappls.sdk.navigation.voice.b(aVar);
    }

    public static String d(String str) {
        if (str != null) {
            String replace = str.replace(Soundex.SILENT_MARKER, ' ').replace(':', ' ').replace(";", ", ").replace(MqttTopic.TOPIC_LEVEL_SEPARATOR, ", ");
            if (n != null) {
                replace = replace.replace("ß", "ss");
            }
            return n != null ? replace.replace("SR", "S R") : replace;
        }
        return str;
    }

    public static void d() {
        com.mappls.sdk.navigation.voice.c cVar = n;
        if (cVar != null) {
            ((com.mappls.sdk.navigation.voice.g) cVar).j();
        }
    }

    public static boolean e() {
        return o;
    }

    public static void h() {
        com.mappls.sdk.navigation.voice.c cVar = n;
        if (cVar != null) {
            ((com.mappls.sdk.navigation.voice.g) cVar).h();
        }
    }

    public static boolean h(int i) {
        return p <= i;
    }

    public final String a(NavigationStep navigationStep) {
        return (navigationStep == null || !((Boolean) this.f12950a.m().Q.get()).booleanValue()) ? "" : navigationStep.getStreetName();
    }

    public final void a(double d) {
        long currentTimeMillis = System.currentTimeMillis();
        long j = u;
        if (j == 0 || currentTimeMillis - t > j) {
            com.mappls.sdk.navigation.voice.b c = c();
            if (c != null) {
                g();
                c.a(d).d();
                v = true;
            }
            long j2 = u;
            u = j2 == 0 ? Constants.ONE_MIN_IN_MILLIS : (long) (j2 * 2.5d);
            t = currentTimeMillis;
        }
    }

    public final void a(NavLocation navLocation, List<c.a> list) {
        com.mappls.sdk.navigation.voice.b c = c();
        if (c == null) {
            return;
        }
        g();
        double[] dArr = new double[1];
        i();
        c.a(dArr[0], (String) null, (NavigationStep) null).b(a(navLocation, list, dArr)).d();
    }

    public final void a(com.mappls.sdk.navigation.routing.a aVar, float f) {
        com.mappls.sdk.navigation.voice.b c;
        StringBuilder sb;
        com.mappls.sdk.navigation.voice.b c2;
        a.EnumC0643a b2 = aVar.b();
        if (b2 == a.EnumC0643a.c) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = s;
            if (j == 0) {
                if (currentTimeMillis - r > 120000) {
                    s = currentTimeMillis;
                    return;
                }
                return;
            } else if (currentTimeMillis - j > 20000) {
                s = 0L;
                return;
            } else if (!((Boolean) this.f12950a.m().T.get()).booleanValue() || currentTimeMillis - s <= 10000 || (c2 = c()) == null) {
                return;
            } else {
                g();
                r = currentTimeMillis;
                s = 0L;
                c2.f().d();
                return;
            }
        }
        if (b2 == a.EnumC0643a.b) {
            if (!((Boolean) this.f12950a.m().U.get()).booleanValue() || (c = c()) == null) {
                return;
            }
            g();
            sb = new StringBuilder();
        } else if (b2 != a.EnumC0643a.e) {
            if (((Boolean) this.f12950a.m().R.get()).booleanValue()) {
                com.mappls.sdk.navigation.voice.b c3 = c();
                if (c3 != null) {
                    g();
                    c3.h(b2 + "").d();
                }
                a.EnumC0643a enumC0643a = a.EnumC0643a.d;
                return;
            }
            return;
        } else if (!((Boolean) this.f12950a.m().S.get()).booleanValue() || (c = c()) == null) {
            return;
        } else {
            g();
            sb = new StringBuilder();
        }
        sb.append(b2);
        sb.append("");
        c.h(sb.toString()).d();
    }

    public final void a(String str) {
        com.mappls.sdk.navigation.voice.b c = c();
        if (c == null) {
            return;
        }
        g();
        c.i(str).e();
    }

    public final void a(List<c.a> list) {
        com.mappls.sdk.navigation.voice.b c = c();
        if (c == null) {
            return;
        }
        g();
        i();
        c.e(a(null, list, null)).d();
    }

    public final boolean a(float f, double d, double d2, float f2) {
        if (f2 <= 0.0f) {
            f2 = this.c;
        }
        if (f <= 0.0f) {
            f = this.c;
        }
        if (this.b.f().l() == 0 && !com.mappls.sdk.navigation.voice.a.c) {
            this.m = (f * ((Integer) this.b.j0.get()).intValue()) / 1000.0d;
        }
        double d3 = this.m;
        return d < d2 + d3 || (d - d3) / ((double) f) < d2 / ((double) f2);
    }

    public final void b() {
        com.mappls.sdk.navigation.voice.b c = c();
        if (c != null) {
            g();
            c.d(null).d();
        }
    }

    public final void b(NavLocation navLocation, List<c.a> list) {
        com.mappls.sdk.navigation.voice.b c = c();
        if (c == null) {
            return;
        }
        g();
        double[] dArr = new double[1];
        c.a(dArr[0], (String) null, (NavigationStep) null).c(a(navLocation, list, dArr)).d();
    }

    public final void b(String str) {
        com.mappls.sdk.navigation.voice.b c = c();
        if (c == null) {
            return;
        }
        g();
        c.i(str).e();
    }

    public final void b(List<c.a> list) {
        com.mappls.sdk.navigation.voice.b c = c();
        if (c == null) {
            return;
        }
        g();
        c.g(a(null, list, null)).d();
    }

    public final void c(NavLocation navLocation, List<c.a> list) {
        if (c() == null) {
            return;
        }
        g();
        i();
        a(navLocation, list, new double[1]);
    }

    public final void c(String str) {
        com.mappls.sdk.navigation.voice.b c = c();
        if (c != null) {
            g();
            c.f(d(str)).d();
        }
    }

    public final void c(List<c.a> list) {
        if (c() == null) {
            return;
        }
        g();
        i();
        a(null, list, null);
    }

    public final void d(int i, String str, NavigationStep navigationStep) {
        com.mappls.sdk.navigation.voice.b c = c();
        if (c != null) {
            g();
            c.a(i, str, navigationStep).d();
        }
    }

    public final void e(NavigationStep navigationStep, int i) {
        com.mappls.sdk.navigation.voice.b c;
        com.mappls.sdk.navigation.voice.b c2 = c();
        if (c2 != null) {
            String b2 = b(navigationStep.getTurnType());
            if (b2 != null) {
                g();
                c = c2.a(b2, i, a(navigationStep), navigationStep);
            } else if (navigationStep.getTurnType().e()) {
                g();
                c = c2.a(i, navigationStep.getTurnType().a(), a(navigationStep), navigationStep);
            } else if (navigationStep.getTurnType().c() != 6 && navigationStep.getTurnType().c() != 41) {
                return;
            } else {
                g();
                c = c2.c(i, a(navigationStep), navigationStep);
            }
            c.d();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(com.mappls.sdk.navigation.routing.NavigationStep r11, int r12, com.mappls.sdk.navigation.routing.NavigationStep r13) {
        /*
            r10 = this;
            com.mappls.sdk.navigation.voice.b r6 = c()
            if (r6 == 0) goto Le8
            com.mappls.sdk.navigation.router.c r0 = r11.getTurnType()
            java.lang.String r1 = b(r0)
            r7 = 41
            r8 = 6
            r9 = 1
            if (r1 == 0) goto L1f
            double r2 = (double) r12
            java.lang.String r4 = r10.a(r11)
            r0 = r6
            r5 = r11
            r0.b(r1, r2, r4, r5)
            goto L62
        L1f:
            com.mappls.sdk.navigation.router.c r0 = r11.getTurnType()
            boolean r0 = r0.e()
            if (r0 == 0) goto L43
            double r1 = (double) r12
            com.mappls.sdk.navigation.router.c r0 = r11.getTurnType()
            r0.b()
            com.mappls.sdk.navigation.router.c r0 = r11.getTurnType()
            int r3 = r0.a()
            java.lang.String r4 = r10.a(r11)
            r0 = r6
            r5 = r11
            r0.b(r1, r3, r4, r5)
            goto L62
        L43:
            com.mappls.sdk.navigation.router.c r0 = r11.getTurnType()
            int r0 = r0.c()
            if (r0 == r8) goto L5a
            com.mappls.sdk.navigation.router.c r0 = r11.getTurnType()
            int r0 = r0.c()
            if (r0 != r7) goto L58
            goto L5a
        L58:
            r0 = 0
            goto L63
        L5a:
            double r0 = (double) r12
            java.lang.String r2 = r10.a(r11)
            r6.b(r0, r2, r11)
        L62:
            r0 = r9
        L63:
            if (r13 == 0) goto Ldf
            com.mappls.sdk.navigation.router.c r0 = r13.getTurnType()
            int r1 = r0.c()
            r2 = 7
            if (r1 == r2) goto L82
            com.mappls.sdk.navigation.router.c r1 = r11.getTurnType()
            int r1 = r1.c()
            if (r1 != r2) goto L82
            double r1 = (double) r12
            java.lang.String r12 = r10.a(r11)
            r6.a(r1, r12, r11)
        L82:
            int r12 = r0.c()
            if (r12 == 0) goto Ld3
            int r12 = r0.c()
            if (r12 == r9) goto Ld3
            int r12 = r0.c()
            r1 = 2
            if (r12 == r1) goto Ld3
            int r12 = r0.c()
            if (r12 == r8) goto Ld3
            int r12 = r0.c()
            r1 = 9
            if (r12 != r1) goto La4
            goto Ld3
        La4:
            int r12 = r0.c()
            r1 = 3
            if (r12 == r1) goto Lc7
            int r12 = r0.c()
            r1 = 4
            if (r12 == r1) goto Lc7
            int r12 = r0.c()
            r1 = 5
            if (r12 == r1) goto Lc7
            int r12 = r0.c()
            if (r12 == r7) goto Lc7
            int r12 = r0.c()
            r0 = 10
            if (r12 != r0) goto Le0
        Lc7:
            com.mappls.sdk.navigation.voice.b r12 = r6.g()
            java.lang.String r11 = r10.a(r11)
            r12.b(r11, r13)
            goto Le0
        Ld3:
            com.mappls.sdk.navigation.voice.b r12 = r6.g()
            java.lang.String r11 = r10.a(r11)
            r12.a(r11, r13)
            goto Le0
        Ldf:
            r9 = r0
        Le0:
            if (r9 == 0) goto Le8
            r10.g()
            r6.d()
        Le8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.routing.h.f(com.mappls.sdk.navigation.routing.NavigationStep, int, com.mappls.sdk.navigation.routing.NavigationStep):void");
    }

    public final void g() {
        if (this.b.g0.get().intValue() > 0) {
            this.f12950a.b().a(new a());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(com.mappls.sdk.navigation.routing.NavigationStep r8, com.mappls.sdk.navigation.routing.NavigationRoute.a r9) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.routing.h.g(com.mappls.sdk.navigation.routing.NavigationStep, com.mappls.sdk.navigation.routing.NavigationRoute$a):void");
    }

    public final void i() {
        if (o) {
            return;
        }
        boolean z = false;
        SoundPool soundPool = new SoundPool(5, ((Integer) this.b.h0.get()).intValue(), 0);
        int i = -1;
        try {
            i = soundPool.load(this.b.f().getAssets().openFd("sounds/ding.ogg"), 1);
            z = true;
        } catch (IOException e) {
            NavigationLogger.d(e);
        }
        int i2 = i;
        if (z) {
            soundPool.play(i2, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public final void j() {
        if (this.f12950a.a().a(com.mappls.sdk.navigation.d.i)) {
            this.e = 3500;
            this.f = NavigationConstants.UI_HANDLER_MAP_CONTROLS;
            this.g = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            this.h = 1200;
            this.i = 300;
            this.j = Command.CMD_RECEIVE_SPEECH_CANCEL;
            this.k = 30;
            this.d = 7.0f;
            this.c = 13.0f;
        } else if (this.f12950a.a().a(com.mappls.sdk.navigation.d.j)) {
            this.e = 500;
            this.f = 1300;
            this.g = 200;
            this.h = 120;
            this.i = 80;
            this.j = 60;
            this.k = 30;
            this.c = 5.0f;
            this.d = 5.0f;
        } else if (this.f12950a.a().a(com.mappls.sdk.navigation.d.k)) {
            this.e = 500;
            this.f = 600;
            this.g = 200;
            this.h = 250;
            this.i = 50;
            this.j = 30;
            this.k = 15;
            this.c = 2.0f;
            this.d = 2.0f;
        } else {
            float d = this.f12950a.a().d();
            this.c = d;
            this.d = d / 2.0f;
            this.e = (int) (270.0f * d);
            this.f = ((int) (230.0f * d)) * 2;
            this.g = (int) (115.0f * d);
            this.h = (int) (92.0f * d);
            this.i = (int) (23.0f * d);
            this.j = (int) (16.0f * d);
            this.k = (int) (d * 7.0f);
        }
    }

    public final void k() {
        com.mappls.sdk.navigation.voice.b c = c();
        if (c != null) {
            g();
            c.g().d();
        }
    }

    public final void a(NavLocation navLocation) {
        synchronized (this.f12950a) {
            boolean z = true;
            if (p != -1) {
                a(navLocation, true);
            } else {
                com.mappls.sdk.navigation.voice.b c = c();
                if (c != null) {
                    g();
                    c.c().d();
                } else {
                    z = false;
                }
                if (z) {
                    q = 0.0f;
                }
            }
        }
    }

    public final String a(NavLocation navLocation, List<c.a> list, double[] dArr) {
        String str = "";
        for (c.a aVar : list) {
            if (str.length() != 0) {
                str = o.a(str, ", ");
            } else if (navLocation != null && dArr != null) {
                dArr[0] = aVar.a() + com.mappls.sdk.navigation.util.d.a(navLocation.getLatitude(), navLocation.getLongitude(), aVar.b().getLatitude(), aVar.b().getLongitude());
            }
            StringBuilder a2 = com.mappls.sdk.navigation.h.a(str);
            a2.append(com.mappls.sdk.navigation.data.a.a(aVar.b(), this.f12950a.b()));
            str = a2.toString();
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.mappls.sdk.navigation.NavLocation r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 488
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.routing.h.a(com.mappls.sdk.navigation.NavLocation, boolean):void");
    }
}
