package com.mappls.sdk.navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.ConnectionResult;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.apis.c;
import com.mappls.sdk.navigation.routing.c;
import com.mappls.sdk.navigation.util.MapplsNavigationMode;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;
/* loaded from: classes11.dex */
public final class t {
    public final j A;
    public SharedPreferences A0;
    public final j B;
    public SharedPreferences B0;
    public final j C;
    public Object C0;
    public final j D;
    public com.mappls.sdk.navigation.d D0;
    public final j E;
    public final InterfaceC0646t<com.mappls.sdk.navigation.d> E0;
    public final j F;
    public boolean F0;
    public final j G;
    public p G0;
    public final j H;
    public final j I;
    public final j J;
    public final j K;
    public final j L;
    public final j M;
    public final j N;
    public final j O;
    public final j P;
    public final j Q;
    public final j R;
    public final j S;
    public final j T;
    public final j U;
    public final j V;
    public final j W;
    public final j X;
    public final j Y;
    public final j Z;

    /* renamed from: a */
    public final j f12956a;
    public final j<Integer> a0;
    public final j b;
    public final j<Float> b0;
    public final j c;
    public final j<Float> c0;
    public final j d;
    public final j<Float> d0;
    public final j e;
    public final j e0;
    public final j f;
    public final j<Integer> f0;
    public final j g;
    public final j<Integer> g0;
    public final j h;
    public final j h0;
    public final j i;
    public final j i0;
    public final j j;
    public final j j0;
    public final j k;
    public final j<Boolean> k0;
    public final j l;
    public final j l0;
    public final j m;
    public final j m0;
    public final j n;
    public final j n0;
    public final j o;
    public final j o0;
    public final j<k> p;
    public final j p0;
    public final j<MapplsNavigationMode> q;
    public final j q0;
    public final j r;
    public final j r0;
    public final j<Boolean> s;
    public final NavigationApplication s0;
    public final j<Boolean> t;
    public final j t0;
    public final j<Boolean> u;
    public final j u0;
    public final j<Integer> v;
    public com.mappls.sdk.navigation.apis.c v0;
    public final j<Boolean> w;
    public final InterfaceC0646t<com.mappls.sdk.navigation.d> w0;
    public final j<Boolean> x;
    public final j x0;
    public final j y;
    public final j y0;
    public final j<Boolean> z;
    public final j z0;

    /* loaded from: classes11.dex */
    public class a extends o {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a() {
            super(r4, "audio_stream", 3, 0);
            t.this = r4;
        }

        @Override // com.mappls.sdk.navigation.t.o, com.mappls.sdk.navigation.t.j
        /* renamed from: e */
        public final boolean b(Object obj, Integer num) {
            j jVar;
            int i;
            int i2;
            boolean b = super.b(obj, num);
            if (b) {
                com.mappls.sdk.navigation.voice.g gVar = t.this.s0.s;
                if (gVar != null) {
                    gVar.a(get().intValue());
                }
                com.mappls.sdk.navigation.d dVar = t.this.D0;
                if (num.intValue() == 3) {
                    jVar = t.this.i0;
                    i = 12;
                } else if (num.intValue() == 5) {
                    jVar = t.this.i0;
                    i2 = 5;
                    jVar.a(dVar, i2);
                } else if (num.intValue() == 0) {
                    jVar = t.this.i0;
                    i = 2;
                }
                i2 = Integer.valueOf(i);
                jVar.a(dVar, i2);
            }
            return b;
        }
    }

    /* loaded from: classes11.dex */
    public class b extends m<v> {
        public final /* synthetic */ t l;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public b(com.mappls.sdk.navigation.t r7, com.mappls.sdk.navigation.t.v[] r8) {
            /*
                r6 = this;
                com.mappls.sdk.navigation.t$v r3 = com.mappls.sdk.navigation.t.v.KILOMETERS_PER_HOUR
                r6.l = r7
                java.lang.String r2 = "speed_system"
                r5 = 0
                r0 = r6
                r1 = r7
                r4 = r8
                r0.<init>(r1, r2, r3, r4, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.t.b.<init>(com.mappls.sdk.navigation.t, com.mappls.sdk.navigation.t$v[]):void");
        }

        @Override // com.mappls.sdk.navigation.t.j
        public final Object b(com.mappls.sdk.navigation.d dVar) {
            s sVar = (s) this.l.y0.get();
            if (dVar.a(com.mappls.sdk.navigation.d.k)) {
                if (sVar == s.KILOMETERS_AND_METERS) {
                    return v.MINUTES_PER_KILOMETER;
                }
            } else if (dVar.a(com.mappls.sdk.navigation.d.l) || sVar == s.NAUTICAL_MILES) {
                return v.NAUTICALMILES_PER_HOUR;
            } else {
                if (sVar == s.KILOMETERS_AND_METERS) {
                    return v.KILOMETERS_PER_HOUR;
                }
            }
            return v.MILES_PER_HOUR;
        }
    }

    /* loaded from: classes11.dex */
    public class c extends w {
        public c(t tVar) {
            super(tVar, "voice_provider", "en-tts", 0);
        }
    }

    /* loaded from: classes11.dex */
    public class d extends j<com.mappls.sdk.navigation.d> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(com.mappls.sdk.navigation.d dVar) {
            super("default_application_mode_string", dVar);
            t.this = r2;
            c();
        }

        @Override // com.mappls.sdk.navigation.t.j
        public final com.mappls.sdk.navigation.d a(Object obj, com.mappls.sdk.navigation.d dVar) {
            com.mappls.sdk.navigation.d dVar2 = dVar;
            com.mappls.sdk.navigation.apis.c cVar = t.this.v0;
            String b = b();
            String g = dVar2.g();
            ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
            return com.mappls.sdk.navigation.d.a(((SharedPreferences) obj).getString(b, g), dVar2);
        }

        @Override // com.mappls.sdk.navigation.t.j
        public final boolean b(Object obj, com.mappls.sdk.navigation.d dVar) {
            return ((com.mappls.sdk.navigation.apis.d) t.this.v0).a(obj).putString(b(), dVar.g()).commit();
        }
    }

    /* loaded from: classes11.dex */
    public class e extends m<l> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(l lVar, l[] lVarArr) {
            super(r7, "default_driving_region", lVar, lVarArr, 0);
            t.this = r7;
        }

        @Override // com.mappls.sdk.navigation.t.m
        /* renamed from: f */
        public final boolean e(Object obj, l lVar) {
            if (lVar != null) {
                t.this.y0.set(lVar.b);
            }
            return super.b(obj, lVar);
        }
    }

    /* loaded from: classes11.dex */
    public class f extends m<s> {
        public f(t tVar, s[] sVarArr) {
            super(tVar, "metric_system", s.KILOMETERS_AND_METERS, sVarArr, 0);
        }
    }

    /* loaded from: classes11.dex */
    public class g extends u<com.mappls.sdk.navigation.d> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g() {
            super(0);
            t.this = r1;
        }

        @Override // com.mappls.sdk.navigation.t.InterfaceC0646t
        /* renamed from: c */
        public final boolean set(com.mappls.sdk.navigation.d dVar) {
            com.mappls.sdk.navigation.d dVar2 = t.this.D0;
            boolean commit = ((com.mappls.sdk.navigation.apis.d) t.this.v0).a(t.this.A0).putString("navigation_mode", dVar.g()).commit();
            if (commit) {
                t.this.D0 = dVar;
                t tVar = t.this;
                tVar.C0 = tVar.a(tVar.D0);
                a((g) dVar2);
            }
            return commit;
        }

        @Override // com.mappls.sdk.navigation.t.InterfaceC0646t
        public final Object get() {
            return t.this.D0;
        }
    }

    /* loaded from: classes11.dex */
    public class h extends i {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(String str, boolean z) {
            super(r2, str, z, 0);
            t.this = r2;
        }

        public /* synthetic */ h(t tVar, String str, boolean z, int i) {
            this(str, z);
        }

        @Override // com.mappls.sdk.navigation.t.i, com.mappls.sdk.navigation.t.j
        public final Boolean a(Object obj, Boolean bool) {
            return super.a(obj, bool);
        }

        @Override // com.mappls.sdk.navigation.t.i
        public final Boolean e(Object obj, Boolean bool) {
            return super.a(obj, bool);
        }

        @Override // com.mappls.sdk.navigation.t.i, com.mappls.sdk.navigation.t.j
        /* renamed from: f */
        public final boolean b(Object obj, Boolean bool) {
            return t.this.s0.a() && super.b(obj, bool);
        }
    }

    /* loaded from: classes11.dex */
    public class i extends j<Boolean> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i(String str, boolean z) {
            super(str, Boolean.valueOf(z));
            t.this = r1;
        }

        public /* synthetic */ i(t tVar, String str, boolean z, int i) {
            this(str, z);
        }

        @Override // com.mappls.sdk.navigation.t.j
        /* renamed from: e */
        public Boolean a(Object obj, Boolean bool) {
            com.mappls.sdk.navigation.apis.c cVar = t.this.v0;
            String b = b();
            boolean booleanValue = bool.booleanValue();
            ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
            return Boolean.valueOf(((SharedPreferences) obj).getBoolean(b, booleanValue));
        }

        @Override // com.mappls.sdk.navigation.t.j
        /* renamed from: f */
        public boolean b(Object obj, Boolean bool) {
            return ((com.mappls.sdk.navigation.apis.d) t.this.v0).a(obj).putBoolean(b(), bool.booleanValue()).commit();
        }
    }

    /* loaded from: classes11.dex */
    public abstract class j<T> extends u<T> {
        public final String b;
        public boolean c;
        public T d;
        public Object e;
        public boolean f;
        public LinkedHashMap g;
        public T h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j(String str, T t) {
            super(0);
            t.this = r1;
            this.b = str;
            this.h = t;
        }

        public final j<T> a() {
            this.f = true;
            return this;
        }

        public final T a(com.mappls.sdk.navigation.d dVar) {
            if (this.c) {
                return get();
            }
            return a((Object) t.this.a(dVar), (SharedPreferences) b(dVar));
        }

        public abstract T a(Object obj, T t);

        public final void a(com.mappls.sdk.navigation.d dVar, T t) {
            if (this.g == null) {
                this.g = new LinkedHashMap();
            }
            this.g.put(dVar, t);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final boolean a(com.mappls.sdk.navigation.d dVar, Integer num) {
            if (this.c) {
                return set(num);
            }
            boolean b = b(t.this.a(dVar), num);
            super.a((j<T>) num);
            return b;
        }

        public T b(com.mappls.sdk.navigation.d dVar) {
            if (this.c) {
                return this.h;
            }
            LinkedHashMap linkedHashMap = this.g;
            if (linkedHashMap == null || !linkedHashMap.containsKey(dVar)) {
                com.mappls.sdk.navigation.d f = dVar.f();
                if (f != null) {
                    return b(f);
                }
                com.mappls.sdk.navigation.apis.c cVar = t.this.v0;
                SharedPreferences sharedPreferences = t.this.B0;
                String str = this.b;
                ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
                return sharedPreferences.contains(str) ? a((Object) t.this.B0, (SharedPreferences) this.h) : this.h;
            }
            return (T) this.g.get(dVar);
        }

        public final String b() {
            return this.b;
        }

        public abstract boolean b(Object obj, T t);

        public final j<T> c() {
            this.c = true;
            return this;
        }

        public final j<T> d() {
            this.c = false;
            return this;
        }

        @Override // com.mappls.sdk.navigation.t.InterfaceC0646t
        public final T get() {
            if (this.f && this.d != null) {
                if (this.e == (this.c ? t.this.A0 : t.this.C0)) {
                    return this.d;
                }
            }
            Object obj = this.c ? t.this.A0 : t.this.C0;
            this.e = obj;
            T a2 = a(obj, b(t.this.D0));
            this.d = a2;
            return a2;
        }

        @Override // com.mappls.sdk.navigation.t.InterfaceC0646t
        public final boolean set(T t) {
            Object obj = this.c ? t.this.A0 : t.this.C0;
            if (b(obj, t)) {
                this.d = t;
                this.e = obj;
                super.a((j<T>) t);
                return true;
            }
            return false;
        }
    }

    /* loaded from: classes11.dex */
    public enum k {
        AUTO,
        DAY,
        NIGHT,
        SENSOR
    }

    /* loaded from: classes11.dex */
    public static final class l {
        public static final l c;
        private static final /* synthetic */ l[] d;
        public final s b = s.KILOMETERS_AND_METERS;

        /* renamed from: a */
        public final boolean f12958a = true;

        static {
            l lVar = new l();
            c = lVar;
            d = new l[]{lVar};
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private l() {
            super("EUROPE_ASIA", 0);
            s sVar = s.KILOMETERS_AND_METERS;
            this.b = sVar;
            this.f12958a = true;
        }

        public static l[] a() {
            return (l[]) d.clone();
        }
    }

    /* loaded from: classes11.dex */
    public class m<E extends Enum<E>> extends j<E> {
        public final E[] j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public m(String str, E e, E[] eArr) {
            super(str, e);
            t.this = r1;
            this.j = eArr;
        }

        public /* synthetic */ m(t tVar, String str, Enum r3, Enum[] enumArr, int i) {
            this(str, r3, enumArr);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.mappls.sdk.navigation.t.j
        public final Object a(Object obj, Object obj2) {
            Enum r5 = (Enum) obj2;
            try {
                com.mappls.sdk.navigation.apis.c cVar = t.this.v0;
                String b = b();
                ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
                int i = ((SharedPreferences) obj).getInt(b, -1);
                if (i >= 0) {
                    E[] eArr = this.j;
                    return i < eArr.length ? eArr[i] : r5;
                }
                return r5;
            } catch (ClassCastException e) {
                NavigationLogger.d(e);
                b(obj, r5);
                return r5;
            }
        }

        @Override // com.mappls.sdk.navigation.t.j
        /* renamed from: e */
        public boolean b(Object obj, E e) {
            return ((com.mappls.sdk.navigation.apis.d) t.this.v0).a(obj).a(e.ordinal(), b()).commit();
        }
    }

    /* loaded from: classes11.dex */
    public class n extends j<Float> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public n(String str, float f) {
            super(str, Float.valueOf(f));
            t.this = r1;
        }

        public /* synthetic */ n(t tVar, String str, float f, int i) {
            this(str, f);
        }

        @Override // com.mappls.sdk.navigation.t.j
        public final Float a(Object obj, Float f) {
            com.mappls.sdk.navigation.apis.c cVar = t.this.v0;
            String b = b();
            float floatValue = f.floatValue();
            ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
            return Float.valueOf(((SharedPreferences) obj).getFloat(b, floatValue));
        }

        @Override // com.mappls.sdk.navigation.t.j
        public final boolean b(Object obj, Float f) {
            return ((com.mappls.sdk.navigation.apis.d) t.this.v0).a(obj).putFloat(b(), f.floatValue()).commit();
        }
    }

    /* loaded from: classes11.dex */
    public class o extends j<Integer> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public o(String str, int i) {
            super(str, Integer.valueOf(i));
            t.this = r1;
        }

        public /* synthetic */ o(t tVar, String str, int i, int i2) {
            this(str, i);
        }

        @Override // com.mappls.sdk.navigation.t.j
        public final Integer a(Object obj, Integer num) {
            com.mappls.sdk.navigation.apis.c cVar = t.this.v0;
            String b = b();
            int intValue = num.intValue();
            ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
            return Integer.valueOf(((SharedPreferences) obj).getInt(b, intValue));
        }

        @Override // com.mappls.sdk.navigation.t.j
        /* renamed from: e */
        public boolean b(Object obj, Integer num) {
            return ((com.mappls.sdk.navigation.apis.d) t.this.v0).a(obj).a(num.intValue(), b()).commit();
        }
    }

    /* loaded from: classes11.dex */
    public class p extends r {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p() {
            super();
            t.this = r1;
            this.f12959a = "intermediate_points";
            this.b = "intermediate_points_description";
        }

        public final boolean c(ArrayList arrayList, ArrayList arrayList2) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                if (i > 0) {
                    sb.append(";");
                }
                sb.append((String) arrayList.get(i));
            }
            StringBuilder sb2 = new StringBuilder();
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                if (i2 > 0) {
                    sb2.append("--");
                }
                sb2.append(arrayList2.get(i2) == null ? "" : (String) arrayList2.get(i2));
            }
            boolean commit = ((com.mappls.sdk.navigation.apis.d) t.this.v0).a(t.this.A0).putString(this.f12959a, sb.toString()).putString(this.b, sb2.toString()).commit();
            t.this.a();
            return commit;
        }
    }

    /* loaded from: classes11.dex */
    public class q extends j<Long> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public q(String str, long j) {
            super(str, Long.valueOf(j));
            t.this = r1;
        }

        public /* synthetic */ q(t tVar, String str, long j, int i) {
            this(str, j);
        }

        @Override // com.mappls.sdk.navigation.t.j
        public final Long a(Object obj, Long l) {
            com.mappls.sdk.navigation.apis.c cVar = t.this.v0;
            String b = b();
            long longValue = l.longValue();
            ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
            return Long.valueOf(((SharedPreferences) obj).getLong(b, longValue));
        }

        @Override // com.mappls.sdk.navigation.t.j
        public final boolean b(Object obj, Long l) {
            return ((com.mappls.sdk.navigation.apis.d) t.this.v0).a(obj).putLong(b(), l.longValue()).commit();
        }
    }

    /* loaded from: classes11.dex */
    public abstract class r {

        /* renamed from: a */
        public String f12959a;
        public String b;

        public r() {
            t.this = r1;
        }

        public final ArrayList a() {
            ArrayList arrayList = new ArrayList();
            com.mappls.sdk.navigation.apis.c cVar = t.this.v0;
            SharedPreferences sharedPreferences = t.this.A0;
            String str = this.f12959a;
            ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
            String string = sharedPreferences.getString(str, "");
            if (string.trim().length() > 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(string, ";");
                while (stringTokenizer.hasMoreTokens()) {
                    arrayList.add(stringTokenizer.nextToken());
                }
            }
            return arrayList;
        }

        public final ArrayList b(int i) {
            ArrayList arrayList = new ArrayList();
            com.mappls.sdk.navigation.apis.c cVar = t.this.v0;
            SharedPreferences sharedPreferences = t.this.A0;
            String str = this.b;
            ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
            String string = sharedPreferences.getString(str, "");
            if (string.trim().length() > 0) {
                arrayList.addAll(Arrays.asList(string.split("--")));
            }
            while (arrayList.size() > i) {
                arrayList.remove(arrayList.size() - 1);
            }
            while (arrayList.size() < i) {
                arrayList.add("");
            }
            return arrayList;
        }
    }

    /* loaded from: classes11.dex */
    public enum s {
        KILOMETERS_AND_METERS,
        MILES_AND_FEET,
        MILES_AND_METERS,
        MILES_AND_YARDS,
        NAUTICAL_MILES
    }

    /* renamed from: com.mappls.sdk.navigation.t$t */
    /* loaded from: classes11.dex */
    public interface InterfaceC0646t<T> {
        void a(com.mappls.sdk.navigation.voice.g gVar);

        void a(com.mappls.sdk.navigation.w<T> wVar);

        T get();

        boolean set(T t);
    }

    /* loaded from: classes11.dex */
    public abstract class u<T> implements InterfaceC0646t<T> {

        /* renamed from: a */
        public LinkedList f12961a;

        public u() {
            this.f12961a = null;
        }

        public /* synthetic */ u(int i) {
            this();
        }

        @Override // com.mappls.sdk.navigation.t.InterfaceC0646t
        public synchronized void a(com.mappls.sdk.navigation.voice.g gVar) {
            LinkedList linkedList = this.f12961a;
            if (linkedList != null) {
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    if (((com.mappls.sdk.navigation.w) ((WeakReference) it.next()).get()) == gVar) {
                        it.remove();
                    }
                }
            }
        }

        @Override // com.mappls.sdk.navigation.t.InterfaceC0646t
        public synchronized void a(com.mappls.sdk.navigation.w<T> wVar) {
            if (this.f12961a == null) {
                this.f12961a = new LinkedList();
            }
            if (!this.f12961a.contains(new WeakReference(wVar))) {
                this.f12961a.add(new WeakReference(wVar));
            }
        }

        public synchronized void a(T t) {
            LinkedList linkedList = this.f12961a;
            if (linkedList != null) {
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    com.mappls.sdk.navigation.w wVar = (com.mappls.sdk.navigation.w) ((WeakReference) it.next()).get();
                    if (wVar == null) {
                        it.remove();
                    } else {
                        wVar.a(t);
                    }
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public enum v {
        KILOMETERS_PER_HOUR(R.string.mappls_km_h),
        MILES_PER_HOUR(R.string.mappls_mile_per_hour),
        METERS_PER_SECOND(R.string.mappls_m_s),
        MINUTES_PER_MILE(R.string.mappls_min_mile),
        MINUTES_PER_KILOMETER(R.string.mappls_min_km),
        NAUTICALMILES_PER_HOUR(R.string.mappls_nm_h);
        

        /* renamed from: a */
        private final int f12962a;

        v(int i) {
            this.f12962a = i;
        }

        public final String a(Context context) {
            return context.getString(this.f12962a);
        }
    }

    /* loaded from: classes11.dex */
    public class w extends j<String> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public w(String str, String str2) {
            super(str, str2);
            t.this = r1;
        }

        public /* synthetic */ w(t tVar, String str, String str2, int i) {
            this(str, str2);
        }

        @Override // com.mappls.sdk.navigation.t.j
        public final String a(Object obj, String str) {
            com.mappls.sdk.navigation.apis.c cVar = t.this.v0;
            String b = b();
            ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
            return ((SharedPreferences) obj).getString(b, str);
        }

        @Override // com.mappls.sdk.navigation.t.j
        public final boolean b(Object obj, String str) {
            String str2 = str;
            c.a a2 = ((com.mappls.sdk.navigation.apis.d) t.this.v0).a(obj);
            String b = b();
            if (str2 != null) {
                str2 = str2.trim();
            }
            return a2.putString(b, str2).commit();
        }
    }

    public t(NavigationApplication navigationApplication, com.mappls.sdk.navigation.apis.d dVar) {
        new w(this, "available_navigation_modes", "car,bicycle,pedestrian", 0).c().a();
        this.f12956a = new w(this, "route_options", null, 0).c();
        this.b = new o(this, "refresh_interval", 60000, 0).c();
        this.c = new o(this, "event_refresh_interval", 300000, 0).c();
        new o(this, "event_refresh_interval", 600000, 0).c();
        this.d = new o(this, "safety_event_visual_before", 200, 0).c();
        this.e = new o(this, "safety_event_audio_before", 200, 0).c();
        this.f = new i(this, "enable_eta_vs_ata_logging", false, 0).c();
        this.g = new o(this, "show_junction_view_before", 200, 0).c();
        this.h = new m(this, "accessibility_mode", com.mappls.sdk.navigation.apis.a.DEFAULT, com.mappls.sdk.navigation.apis.a.values(), 0).c().a();
        Float valueOf = Float.valueOf(1.0f);
        this.i = new n(this, "speech_rate", 1.0f, 0).c();
        this.j = new n(this, "arrival_distance_factor", 1.0f, 0).d();
        Float valueOf2 = Float.valueOf(5.0f);
        new n(this, "speed_limit_exceed", 5.0f, 0).d();
        this.k = new h(this, "disable_offroute_recalc", false, 0).c();
        this.l = new h(this, "disable_wrong_direction_recalc", true, 0).c();
        this.m = new i(this, "use_magnetic_field_sensor_compass", false, 0).c().a();
        this.n = new i(this, "use_kalman_filter_compass", false, 0).c().a();
        j<Float> a2 = new n(this, "text_scale", 1.0f, 0).d().a();
        j<Float> a3 = new n(this, "map_density_n", 1.0f, 0).d().a();
        this.o = new w(this, "preferred_locale", "", 0).c();
        k kVar = k.AUTO;
        m mVar = new m(this, "daynight_mode", kVar, k.values(), 0);
        this.p = mVar;
        this.q = new m(this, "navigation_mode", MapplsNavigationMode.ONLINE, MapplsNavigationMode.values(), 0);
        this.r = new m(this, "router_service", c.a.OSRM, c.a.values(), 0).d();
        j<Boolean> a4 = new i(this, "snap_to_road", true, 0).d().a();
        this.s = a4;
        this.t = new i(this, "interrupt_music", false, 0).c();
        this.u = new i(this, "save_global_track_to_gpx", false, 0).c().a();
        this.v = new o(this, "save_global_track_interval", 5000, 0).c().a();
        this.w = new i(this, "save_global_track_remember", true, 0).c().a();
        j<Boolean> a5 = new i(this, "save_track_to_gpx", true, 0).d().a();
        this.x = a5;
        new i(this, "disable_recording_once_app_killed", false, 0).c();
        this.y = new i(this, "fast_route_mode", true, 0).d();
        j<Boolean> a6 = new i(this, "show_traffic_warnings", false, 0).d().a();
        this.z = a6;
        j<Boolean> a7 = new i(this, "show_pedestrian", false, 0).d().a();
        this.A = new i(this, "show_cameras", true, 0).d().a();
        j<Boolean> a8 = new i(this, "show_lanes", false, 0).d().a();
        this.B = new i(this, "show_gpx_wpt", true, 0).c().a();
        this.C = new i(this, "show_nearby_favorites", false, 0).d().a();
        this.D = new i(this, "show_nearby_poi_along_route", false, 0).d().a();
        this.E = new q(this, "along_the_route_buffer", 200L, 0).d().a();
        this.F = new w(this, "show_nearby_poi_categories", "FODCNV;FINATM;TRNCGS;TRNECS;TRNSPS;CGSBMW;TRNPMP;POLOFC", 0).d().a();
        this.G = new i(this, "enable_junction", false, 0).d().a();
        this.H = new i(this, "enable_events", false, 0).d().a();
        this.I = new i(this, "speak_events", true, 0).d().a();
        this.J = new i(this, "speak_events_safety", true, 0).d().a();
        this.K = new i(this, "show_events_safety", true, 0).d().a();
        this.L = new i(this, "speak_events_traffic", true, 0).d().a();
        this.M = new i(this, "show_events_traffic", true, 0).d().a();
        this.N = new i(this, "speak_events_road_conditions", true, 0).d().a();
        this.O = new i(this, "show_events_road_conditions", true, 0).d().a();
        this.P = new i(this, "enable_traffic_data", false, 0).d().a();
        this.Q = new i(this, "speak_street_names", true, 0).d().a();
        this.R = new i(this, "speak_traffic_warnings", true, 0).d().a();
        this.S = new i(this, "speak_pedestrian", true, 0).d().a();
        this.T = new i(this, "speak_speed_limit", true, 0).d().a();
        this.U = new i(this, "speak_cameras", true, 0).d().a();
        this.V = new i(this, "announce_wpt", true, 0).c().a();
        this.W = new i(this, "announce_nearby_favorites", false, 0).d().a();
        this.X = new i(this, "announce_nearby_poi", false, 0).d().a();
        this.Y = new i(this, "announce_junction", true, 0).d().a();
        this.Z = new i(this, "announce_navigation_events", true, 0).d().a();
        new i(this, "gpx_routing_calculate_navigation_route", true, 0).c().a();
        new w(this, "report_master_list_category", "", 0).d().a();
        new q(this, "report_master_list_category_data_last_time_downloaded", 0L, 0).d().a();
        j<Integer> d2 = new o(this, "save_track_interval", 5000, 0).d();
        this.a0 = d2;
        j<Float> d3 = new n(this, "save_track_precision", 0.0f, 0).d();
        this.b0 = d3;
        j<Float> d4 = new n(this, "save_track_min_speed", 0.0f, 0).d();
        this.c0 = d4;
        j<Float> d5 = new n(this, "save_track_min_distance", 0.0f, 0).d();
        this.d0 = d5;
        new i(this, "show_trip_recording_notification", true, 0).c();
        this.e0 = new w(this, "selected_gpx", "", 0).c();
        j<Integer> d6 = new o(this, "auto_follow_route", 0, 0).d();
        j<Integer> d7 = new o(this, "keep_informing", 0, 0).d();
        this.f0 = d7;
        j<Integer> d8 = new o(this, "wake_on_voice_int", 0, 0).d();
        this.g0 = d8;
        this.h0 = new o(this, "audio_stream", 3, 0).c();
        this.i0 = new o(this, "audio_usage", 12, 0).d();
        new a().d();
        this.j0 = new o(this, "bt_sco_delay", ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED, 0).c().a();
        new i(this, "use_map_markers", false, 0).c().a();
        this.k0 = new i(this, "use_intermediate_points_navigation", true, 0).c().a();
        this.l0 = new i(this, "voice_mute", false, 0).c();
        this.m0 = new i(this, "navigation_prompt", true, 0).c();
        this.n0 = new o(this, "coordinates_format", 0, 0).c();
        this.o0 = new i(this, "follow_to_route", false, 0).c();
        this.p0 = new w(this, "follow_gpx", null, 0).c();
        this.q0 = new q(this, "agps_data_downloaded", 0L, 0).c();
        this.r0 = new b(this, v.values()).d();
        this.t0 = new c(this).c();
        this.u0 = new w(this, "enabled_plugins", "", 0).c();
        new LinkedHashMap();
        this.w0 = new d(com.mappls.sdk.navigation.d.h);
        this.x0 = new e(l.c, l.a()).c().a();
        this.y0 = new f(this, s.values()).c();
        this.z0 = new o(this, "better_route_suggestion", 1, 0).c();
        this.E0 = new g();
        this.F0 = true;
        this.G0 = new p();
        com.mappls.sdk.navigation.d dVar2 = com.mappls.sdk.navigation.d.i;
        a2.a(dVar2, (com.mappls.sdk.navigation.d) Float.valueOf(1.25f));
        a3.a(dVar2, (com.mappls.sdk.navigation.d) Float.valueOf(1.5f));
        mVar.d().a();
        mVar.a(dVar2, (com.mappls.sdk.navigation.d) kVar);
        com.mappls.sdk.navigation.d dVar3 = com.mappls.sdk.navigation.d.j;
        mVar.a(dVar3, (com.mappls.sdk.navigation.d) kVar);
        com.mappls.sdk.navigation.d dVar4 = com.mappls.sdk.navigation.d.k;
        mVar.a(dVar4, (com.mappls.sdk.navigation.d) k.DAY);
        Boolean bool = Boolean.TRUE;
        a4.a(dVar2, (com.mappls.sdk.navigation.d) bool);
        a4.a(dVar3, (com.mappls.sdk.navigation.d) bool);
        Boolean bool2 = Boolean.FALSE;
        a5.a(dVar2, (com.mappls.sdk.navigation.d) bool2);
        a5.a(dVar3, (com.mappls.sdk.navigation.d) bool2);
        a5.a(dVar4, (com.mappls.sdk.navigation.d) bool2);
        a6.a(dVar2, (com.mappls.sdk.navigation.d) bool);
        a7.a(dVar2, (com.mappls.sdk.navigation.d) bool);
        a8.a(dVar2, (com.mappls.sdk.navigation.d) bool);
        a8.a(dVar3, (com.mappls.sdk.navigation.d) bool);
        d2.a(dVar2, (com.mappls.sdk.navigation.d) 3000);
        d2.a(dVar3, (com.mappls.sdk.navigation.d) Integer.valueOf((int) NavigationConstants.UI_HANDLER_SEARCH));
        d2.a(dVar4, (com.mappls.sdk.navigation.d) 10000);
        d3.a(dVar2, (com.mappls.sdk.navigation.d) valueOf);
        d3.a(dVar3, (com.mappls.sdk.navigation.d) valueOf);
        d3.a(dVar4, (com.mappls.sdk.navigation.d) valueOf);
        d4.a(dVar2, (com.mappls.sdk.navigation.d) valueOf);
        d4.a(dVar3, (com.mappls.sdk.navigation.d) valueOf);
        d4.a(dVar4, (com.mappls.sdk.navigation.d) Float.valueOf(0.0f));
        d5.a(dVar2, (com.mappls.sdk.navigation.d) valueOf2);
        d5.a(dVar3, (com.mappls.sdk.navigation.d) valueOf2);
        d5.a(dVar4, (com.mappls.sdk.navigation.d) valueOf2);
        d6.a(dVar2, (com.mappls.sdk.navigation.d) 15);
        d6.a(dVar3, (com.mappls.sdk.navigation.d) 15);
        d6.a(dVar4, (com.mappls.sdk.navigation.d) 0);
        d7.a(dVar2, (com.mappls.sdk.navigation.d) 0);
        d7.a(dVar3, (com.mappls.sdk.navigation.d) 0);
        d7.a(dVar4, (com.mappls.sdk.navigation.d) 0);
        d8.a(dVar2, (com.mappls.sdk.navigation.d) 0);
        d8.a(dVar3, (com.mappls.sdk.navigation.d) 0);
        d8.a(dVar4, (com.mappls.sdk.navigation.d) 0);
        this.s0 = navigationApplication;
        this.v0 = dVar;
        p();
    }

    public static boolean a(File file) {
        try {
            file.mkdirs();
            File createTempFile = File.createTempFile("navigation_", ".tmp", file);
            boolean exists = createTempFile.exists();
            createTempFile.delete();
            return exists;
        } catch (IOException e2) {
            NavigationLogger.d(e2);
            return false;
        }
    }

    public final void a() {
        c.a a2 = ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0);
        com.mappls.sdk.navigation.apis.c cVar = this.v0;
        SharedPreferences sharedPreferences = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
        c.a putFloat = a2.putFloat("start_point_lat_backup", sharedPreferences.getFloat("start_point_lat", 0.0f));
        com.mappls.sdk.navigation.apis.c cVar2 = this.v0;
        SharedPreferences sharedPreferences2 = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar2).getClass();
        c.a putFloat2 = putFloat.putFloat("start_point_lon_backup", sharedPreferences2.getFloat("start_point_lon", 0.0f));
        com.mappls.sdk.navigation.apis.c cVar3 = this.v0;
        SharedPreferences sharedPreferences3 = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar3).getClass();
        putFloat2.putString("start_point_description_backup", sharedPreferences3.getString("start_point_description", "")).commit();
        c.a a3 = ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0);
        com.mappls.sdk.navigation.apis.c cVar4 = this.v0;
        SharedPreferences sharedPreferences4 = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar4).getClass();
        c.a putString = a3.putString("point_navigate_mappls_pin_backup", sharedPreferences4.getString("point_navigate_mappls_pin", null));
        com.mappls.sdk.navigation.apis.c cVar5 = this.v0;
        SharedPreferences sharedPreferences5 = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar5).getClass();
        putString.putString("point_navigate_description_backup", sharedPreferences5.getString("point_navigate_description", "")).commit();
        c.a a4 = ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0);
        com.mappls.sdk.navigation.apis.c cVar6 = this.v0;
        SharedPreferences sharedPreferences6 = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar6).getClass();
        c.a putString2 = a4.putString("intermediate_points_backup", sharedPreferences6.getString("intermediate_points", ""));
        com.mappls.sdk.navigation.apis.c cVar7 = this.v0;
        SharedPreferences sharedPreferences7 = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar7).getClass();
        putString2.putString("intermediate_points_description_backup", sharedPreferences7.getString("intermediate_points_description", "")).commit();
    }

    public final void a(double d2, double d3, com.mappls.sdk.navigation.data.a aVar) {
        c.a a2 = ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0);
        a2.putString("point_navigate_mappls_pin", d3 + Constants.SEPARATOR_COMMA + d2).commit();
        ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0).putString("point_navigate_description", com.mappls.sdk.navigation.data.a.a(aVar)).commit();
        a();
    }

    public final void a(String str, com.mappls.sdk.navigation.data.a aVar) {
        ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0).putString("point_navigate_mappls_pin", str).commit();
        ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0).putString("point_navigate_description", com.mappls.sdk.navigation.data.a.a(aVar)).commit();
        a();
    }

    public final ArrayList b(int i2) {
        return this.G0.b(i2);
    }

    public final void b() {
        ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0).remove("intermediate_points").remove("intermediate_points_description").commit();
    }

    public final void b(double d2, double d3, com.mappls.sdk.navigation.data.a aVar, int i2) {
        p pVar = this.G0;
        ArrayList a2 = pVar.a();
        ArrayList b2 = pVar.b(a2.size());
        if (i2 < a2.size()) {
            a2.set(i2, d3 + Constants.SEPARATOR_COMMA + d2);
            b2.set(i2, com.mappls.sdk.navigation.data.a.a(aVar));
            pVar.c(a2, b2);
        }
    }

    public final void c() {
        ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0).remove("point_navigate_mappls_pin").remove("point_navigate_description").commit();
    }

    public final void d() {
        ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0).remove("start_point_lat").remove("start_point_lon").remove("start_point_description").commit();
    }

    public final com.mappls.sdk.navigation.d e() {
        return t.this.D0;
    }

    public final NavigationApplication f() {
        return this.s0;
    }

    public final LinkedHashSet g() {
        StringTokenizer stringTokenizer = new StringTokenizer((String) this.u0.get(), Constants.SEPARATOR_COMMA);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (!nextToken.startsWith("-")) {
                linkedHashSet.add(nextToken);
            }
        }
        return linkedHashSet;
    }

    public final File h() {
        File file;
        File file2;
        if (Build.VERSION.SDK_INT < 19) {
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            com.mappls.sdk.navigation.apis.c cVar = this.v0;
            SharedPreferences sharedPreferences = this.A0;
            ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
            return new File(new File(sharedPreferences.getString("external_storage_dir", absolutePath)), "map/");
        }
        com.mappls.sdk.navigation.apis.c cVar2 = this.v0;
        SharedPreferences sharedPreferences2 = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar2).getClass();
        int i2 = sharedPreferences2.getInt("external_storage_dir_type_newer_version", -1);
        com.mappls.sdk.navigation.apis.c cVar3 = this.v0;
        SharedPreferences sharedPreferences3 = this.A0;
        String absolutePath2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        com.mappls.sdk.navigation.apis.c cVar4 = this.v0;
        SharedPreferences sharedPreferences4 = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar4).getClass();
        String absolutePath3 = new File(new File(sharedPreferences4.getString("external_storage_dir", absolutePath2)), "map/").getAbsolutePath();
        ((com.mappls.sdk.navigation.apis.d) cVar3).getClass();
        File file3 = new File(sharedPreferences3.getString("external_storage_dir_newer_version", absolutePath3));
        if (i2 != -1 || a(file3)) {
            return file3;
        }
        File[] externalFilesDirs = this.s0.getExternalFilesDirs(null);
        if (externalFilesDirs == null || externalFilesDirs.length <= 0 || (file2 = externalFilesDirs[0]) == null) {
            File[] obbDirs = this.s0.getObbDirs();
            return (obbDirs == null || obbDirs.length <= 0 || (file = obbDirs[0]) == null) ? j() : file;
        }
        return file2;
    }

    public final ArrayList i() {
        return this.G0.a();
    }

    public final File j() {
        File noBackupFilesDir;
        return (Build.VERSION.SDK_INT < 21 || (noBackupFilesDir = this.s0.getNoBackupFilesDir()) == null) ? this.s0.getFilesDir() : noBackupFilesDir;
    }

    public final com.mappls.sdk.navigation.data.a k() {
        com.mappls.sdk.navigation.apis.c cVar = this.v0;
        SharedPreferences sharedPreferences = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
        return com.mappls.sdk.navigation.data.a.a(sharedPreferences.getString("point_navigate_description", ""), l());
    }

    public final String l() {
        com.mappls.sdk.navigation.apis.c cVar = this.v0;
        SharedPreferences sharedPreferences = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
        return sharedPreferences.getString("point_navigate_mappls_pin", null);
    }

    public final LatLng m() {
        float a2 = ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0, "start_point_lat");
        float a3 = ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0, "start_point_lon");
        if (a2 == 0.0f && a3 == 0.0f) {
            return null;
        }
        return new LatLng(a2, a3);
    }

    public final com.mappls.sdk.navigation.data.a n() {
        com.mappls.sdk.navigation.apis.c cVar = this.v0;
        SharedPreferences sharedPreferences = this.A0;
        ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
        return com.mappls.sdk.navigation.data.a.a(m(), sharedPreferences.getString("start_point_description", ""));
    }

    public final void o() {
        if (Build.VERSION.SDK_INT < 19) {
            ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0).putString("external_storage_dir", j().getAbsolutePath()).commit();
            return;
        }
        File file = null;
        File[] externalFilesDirs = this.s0.getExternalFilesDirs(null);
        File file2 = (externalFilesDirs == null || externalFilesDirs.length <= 0) ? null : externalFilesDirs[0];
        if (file2 == null || !a(file2)) {
            ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0).a(2, "external_storage_dir_type_newer_version").putString("external_storage_dir_newer_version", j().getAbsolutePath()).commit();
            return;
        }
        File[] externalFilesDirs2 = this.s0.getExternalFilesDirs(null);
        if (externalFilesDirs2 != null && externalFilesDirs2.length > 0) {
            file = externalFilesDirs2[0];
        }
        ((com.mappls.sdk.navigation.apis.d) this.v0).a(this.A0).a(1, "external_storage_dir_type_newer_version").putString("external_storage_dir_newer_version", file.getAbsolutePath()).commit();
    }

    public final void p() {
        this.A0 = ((com.mappls.sdk.navigation.apis.d) this.v0).a("com.mmi.maps.settings");
        com.mappls.sdk.navigation.d dVar = com.mappls.sdk.navigation.d.h;
        this.B0 = a(dVar);
        com.mappls.sdk.navigation.apis.c cVar = this.v0;
        SharedPreferences sharedPreferences = this.A0;
        ((g) this.E0).getClass();
        String g2 = dVar.g();
        ((com.mappls.sdk.navigation.apis.d) cVar).getClass();
        com.mappls.sdk.navigation.d a2 = com.mappls.sdk.navigation.d.a(sharedPreferences.getString("navigation_mode", g2), dVar);
        this.D0 = a2;
        this.C0 = a(a2);
    }

    public final void a(int i2) {
        p pVar = this.G0;
        ArrayList a2 = pVar.a();
        ArrayList b2 = pVar.b(a2.size());
        if (i2 < a2.size()) {
            a2.remove(i2);
            b2.remove(i2);
            pVar.c(a2, b2);
        }
    }

    public final SharedPreferences a(com.mappls.sdk.navigation.d dVar) {
        String sb;
        com.mappls.sdk.navigation.apis.c cVar = this.v0;
        if (dVar == null) {
            sb = "com.mmi.maps.settings";
        } else {
            StringBuilder a2 = com.mappls.sdk.navigation.h.a("com.mmi.maps.settings.");
            a2.append(dVar.g().toLowerCase());
            sb = a2.toString();
        }
        return ((com.mappls.sdk.navigation.apis.d) cVar).a(sb);
    }

    public final void a(String str, com.mappls.sdk.navigation.data.a aVar, int i2) {
        p pVar = this.G0;
        ArrayList a2 = pVar.a();
        ArrayList b2 = pVar.b(a2.size());
        a2.add(i2, str);
        b2.add(i2, com.mappls.sdk.navigation.data.a.a(aVar));
        pVar.c(a2, b2);
    }

    public final void a(double d2, double d3, com.mappls.sdk.navigation.data.a aVar, int i2) {
        p pVar = this.G0;
        ArrayList a2 = pVar.a();
        ArrayList b2 = pVar.b(a2.size());
        a2.add(i2, d3 + Constants.SEPARATOR_COMMA + d2);
        b2.add(i2, com.mappls.sdk.navigation.data.a.a(aVar));
        pVar.c(a2, b2);
    }

    public final boolean a(boolean z) {
        NetworkInfo.State state;
        long currentTimeMillis = System.currentTimeMillis() - 0;
        if (currentTimeMillis < 0 || currentTimeMillis > 15000 || z) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.s0.getSystemService("connectivity")).getActiveNetworkInfo();
            this.F0 = (activeNetworkInfo == null || (state = activeNetworkInfo.getState()) == NetworkInfo.State.DISCONNECTED || state == NetworkInfo.State.DISCONNECTING) ? false : true;
        }
        return this.F0;
    }
}
