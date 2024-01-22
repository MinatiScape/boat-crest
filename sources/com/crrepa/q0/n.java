package com.crrepa.q0;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
/* loaded from: classes9.dex */
public final class n {
    public static final com.crrepa.n0.x<String> A;
    public static final com.crrepa.n0.x<BigDecimal> B;
    public static final com.crrepa.n0.x<BigInteger> C;
    public static final com.crrepa.n0.y D;
    public static final com.crrepa.n0.x<StringBuilder> E;
    public static final com.crrepa.n0.y F;
    public static final com.crrepa.n0.x<StringBuffer> G;
    public static final com.crrepa.n0.y H;
    public static final com.crrepa.n0.x<URL> I;
    public static final com.crrepa.n0.y J;
    public static final com.crrepa.n0.x<URI> K;
    public static final com.crrepa.n0.y L;
    public static final com.crrepa.n0.x<InetAddress> M;
    public static final com.crrepa.n0.y N;
    public static final com.crrepa.n0.x<UUID> O;
    public static final com.crrepa.n0.y P;
    public static final com.crrepa.n0.x<Currency> Q;
    public static final com.crrepa.n0.y R;
    public static final com.crrepa.n0.y S;
    public static final com.crrepa.n0.x<Calendar> T;
    public static final com.crrepa.n0.y U;
    public static final com.crrepa.n0.x<Locale> V;
    public static final com.crrepa.n0.y W;
    public static final com.crrepa.n0.x<com.crrepa.n0.l> X;
    public static final com.crrepa.n0.y Y;
    public static final com.crrepa.n0.y Z;

    /* renamed from: a  reason: collision with root package name */
    public static final com.crrepa.n0.x<Class> f7829a;
    public static final com.crrepa.n0.y b;
    public static final com.crrepa.n0.x<BitSet> c;
    public static final com.crrepa.n0.y d;
    public static final com.crrepa.n0.x<Boolean> e;
    public static final com.crrepa.n0.x<Boolean> f;
    public static final com.crrepa.n0.y g;
    public static final com.crrepa.n0.x<Number> h;
    public static final com.crrepa.n0.y i;
    public static final com.crrepa.n0.x<Number> j;
    public static final com.crrepa.n0.y k;
    public static final com.crrepa.n0.x<Number> l;
    public static final com.crrepa.n0.y m;
    public static final com.crrepa.n0.x<AtomicInteger> n;
    public static final com.crrepa.n0.y o;
    public static final com.crrepa.n0.x<AtomicBoolean> p;
    public static final com.crrepa.n0.y q;
    public static final com.crrepa.n0.x<AtomicIntegerArray> r;
    public static final com.crrepa.n0.y s;
    public static final com.crrepa.n0.x<Number> t;
    public static final com.crrepa.n0.x<Number> u;
    public static final com.crrepa.n0.x<Number> v;
    public static final com.crrepa.n0.x<Number> w;
    public static final com.crrepa.n0.y x;
    public static final com.crrepa.n0.x<Character> y;
    public static final com.crrepa.n0.y z;

    /* loaded from: classes9.dex */
    public static class a implements com.crrepa.n0.y {
        public final /* synthetic */ Class h;
        public final /* synthetic */ Class i;
        public final /* synthetic */ com.crrepa.n0.x j;

        public a(Class cls, Class cls2, com.crrepa.n0.x xVar) {
            this.h = cls;
            this.i = cls2;
            this.j = xVar;
        }

        @Override // com.crrepa.n0.y
        public <T> com.crrepa.n0.x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            Class<? super T> a2 = aVar.a();
            if (a2 == this.h || a2 == this.i) {
                return this.j;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.h.getName() + "+" + this.i.getName() + ",adapter=" + this.j + "]";
        }
    }

    /* loaded from: classes9.dex */
    public static class a0 extends com.crrepa.n0.x<UUID> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, UUID uuid) throws IOException {
            dVar.e(uuid == null ? null : uuid.toString());
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public UUID a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return UUID.fromString(aVar.r());
        }
    }

    /* loaded from: classes9.dex */
    public static class b extends com.crrepa.n0.x<AtomicIntegerArray> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            dVar.c();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                dVar.a(atomicIntegerArray.get(i));
            }
            dVar.e();
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public AtomicIntegerArray a(com.crrepa.t0.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.a();
            while (aVar.i()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.n()));
                } catch (NumberFormatException e) {
                    throw new com.crrepa.n0.v(e);
                }
            }
            aVar.f();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }
    }

    /* loaded from: classes9.dex */
    public static class b0 extends com.crrepa.n0.x<Currency> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Currency currency) throws IOException {
            dVar.e(currency.getCurrencyCode());
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Currency a(com.crrepa.t0.a aVar) throws IOException {
            return Currency.getInstance(aVar.r());
        }
    }

    /* loaded from: classes9.dex */
    public static class c implements com.crrepa.n0.y {
        public final /* synthetic */ Class h;
        public final /* synthetic */ com.crrepa.n0.x i;

        /* JADX INFO: Add missing generic type declarations: [T1] */
        /* loaded from: classes9.dex */
        public class a<T1> extends com.crrepa.n0.x<T1> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Class f7830a;

            public a(Class cls) {
                this.f7830a = cls;
            }

            @Override // com.crrepa.n0.x
            public T1 a(com.crrepa.t0.a aVar) throws IOException {
                T1 t1 = (T1) c.this.i.a(aVar);
                if (t1 == null || this.f7830a.isInstance(t1)) {
                    return t1;
                }
                throw new com.crrepa.n0.v("Expected a " + this.f7830a.getName() + " but was " + t1.getClass().getName());
            }

            @Override // com.crrepa.n0.x
            public void a(com.crrepa.t0.d dVar, T1 t1) throws IOException {
                c.this.i.a(dVar, (com.crrepa.t0.d) t1);
            }
        }

        public c(Class cls, com.crrepa.n0.x xVar) {
            this.h = cls;
            this.i = xVar;
        }

        @Override // com.crrepa.n0.y
        public <T2> com.crrepa.n0.x<T2> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T2> aVar) {
            Class<? super T2> a2 = aVar.a();
            if (this.h.isAssignableFrom(a2)) {
                return new a(a2);
            }
            return null;
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.h.getName() + ",adapter=" + this.i + "]";
        }
    }

    /* loaded from: classes9.dex */
    public static class c0 implements com.crrepa.n0.y {

        /* loaded from: classes9.dex */
        public class a extends com.crrepa.n0.x<Timestamp> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ com.crrepa.n0.x f7831a;

            public a(c0 c0Var, com.crrepa.n0.x xVar) {
                this.f7831a = xVar;
            }

            @Override // com.crrepa.n0.x
            /* renamed from: c */
            public void a(com.crrepa.t0.d dVar, Timestamp timestamp) throws IOException {
                this.f7831a.a(dVar, (com.crrepa.t0.d) timestamp);
            }

            @Override // com.crrepa.n0.x
            /* renamed from: d */
            public Timestamp a(com.crrepa.t0.a aVar) throws IOException {
                Date date = (Date) this.f7831a.a(aVar);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }
        }

        @Override // com.crrepa.n0.y
        public <T> com.crrepa.n0.x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            if (aVar.a() != Timestamp.class) {
                return null;
            }
            return new a(this, fVar.a((Class) Date.class));
        }
    }

    /* loaded from: classes9.dex */
    public static class d extends com.crrepa.n0.x<Number> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Number number) throws IOException {
            dVar.a(number);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Number a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            try {
                return Long.valueOf(aVar.o());
            } catch (NumberFormatException e) {
                throw new com.crrepa.n0.v(e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class d0 extends com.crrepa.n0.x<Calendar> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                dVar.k();
                return;
            }
            dVar.d();
            dVar.b("year");
            dVar.a(calendar.get(1));
            dVar.b("month");
            dVar.a(calendar.get(2));
            dVar.b("dayOfMonth");
            dVar.a(calendar.get(5));
            dVar.b("hourOfDay");
            dVar.a(calendar.get(11));
            dVar.b("minute");
            dVar.a(calendar.get(12));
            dVar.b("second");
            dVar.a(calendar.get(13));
            dVar.f();
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Calendar a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            aVar.b();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (aVar.t() != com.crrepa.t0.c.END_OBJECT) {
                String p = aVar.p();
                int n = aVar.n();
                if ("year".equals(p)) {
                    i = n;
                } else if ("month".equals(p)) {
                    i2 = n;
                } else if ("dayOfMonth".equals(p)) {
                    i3 = n;
                } else if ("hourOfDay".equals(p)) {
                    i4 = n;
                } else if ("minute".equals(p)) {
                    i5 = n;
                } else if ("second".equals(p)) {
                    i6 = n;
                }
            }
            aVar.g();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class e {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7832a;

        static {
            int[] iArr = new int[com.crrepa.t0.c.values().length];
            f7832a = iArr;
            try {
                iArr[com.crrepa.t0.c.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7832a[com.crrepa.t0.c.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7832a[com.crrepa.t0.c.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7832a[com.crrepa.t0.c.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7832a[com.crrepa.t0.c.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7832a[com.crrepa.t0.c.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7832a[com.crrepa.t0.c.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7832a[com.crrepa.t0.c.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f7832a[com.crrepa.t0.c.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f7832a[com.crrepa.t0.c.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class e0 extends com.crrepa.n0.x<Locale> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Locale locale) throws IOException {
            dVar.e(locale == null ? null : locale.toString());
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Locale a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.r(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (nextToken2 == null && nextToken3 == null) ? new Locale(nextToken) : nextToken3 == null ? new Locale(nextToken, nextToken2) : new Locale(nextToken, nextToken2, nextToken3);
        }
    }

    /* loaded from: classes9.dex */
    public static class f extends com.crrepa.n0.x<Number> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Number number) throws IOException {
            dVar.a(number);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Number a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return Float.valueOf((float) aVar.m());
        }
    }

    /* loaded from: classes9.dex */
    public static class f0 extends com.crrepa.n0.x<com.crrepa.n0.l> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, com.crrepa.n0.l lVar) throws IOException {
            if (lVar == null || lVar.t()) {
                dVar.k();
            } else if (lVar.v()) {
                com.crrepa.n0.r n = lVar.n();
                if (n.y()) {
                    dVar.a(n.p());
                } else if (n.x()) {
                    dVar.d(n.d());
                } else {
                    dVar.e(n.r());
                }
            } else if (lVar.s()) {
                dVar.c();
                Iterator<com.crrepa.n0.l> it = lVar.k().iterator();
                while (it.hasNext()) {
                    a(dVar, it.next());
                }
                dVar.e();
            } else if (!lVar.u()) {
                throw new IllegalArgumentException("Couldn't write " + lVar.getClass());
            } else {
                dVar.d();
                for (Map.Entry<String, com.crrepa.n0.l> entry : lVar.m().x()) {
                    dVar.b(entry.getKey());
                    a(dVar, entry.getValue());
                }
                dVar.f();
            }
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public com.crrepa.n0.l a(com.crrepa.t0.a aVar) throws IOException {
            switch (e.f7832a[aVar.t().ordinal()]) {
                case 1:
                    return new com.crrepa.n0.r((Number) new com.crrepa.p0.f(aVar.r()));
                case 2:
                    return new com.crrepa.n0.r(Boolean.valueOf(aVar.l()));
                case 3:
                    return new com.crrepa.n0.r(aVar.r());
                case 4:
                    aVar.q();
                    return com.crrepa.n0.n.f7780a;
                case 5:
                    com.crrepa.n0.i iVar = new com.crrepa.n0.i();
                    aVar.a();
                    while (aVar.i()) {
                        iVar.a(a(aVar));
                    }
                    aVar.f();
                    return iVar;
                case 6:
                    com.crrepa.n0.o oVar = new com.crrepa.n0.o();
                    aVar.b();
                    while (aVar.i()) {
                        oVar.a(aVar.p(), a(aVar));
                    }
                    aVar.g();
                    return oVar;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class g extends com.crrepa.n0.x<Boolean> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Boolean bool) throws IOException {
            dVar.a(bool);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Boolean a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() != com.crrepa.t0.c.NULL) {
                return aVar.t() == com.crrepa.t0.c.STRING ? Boolean.valueOf(Boolean.parseBoolean(aVar.r())) : Boolean.valueOf(aVar.l());
            }
            aVar.q();
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public static class g0 extends com.crrepa.n0.x<BitSet> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                dVar.k();
                return;
            }
            dVar.c();
            for (int i = 0; i < bitSet.length(); i++) {
                dVar.a(bitSet.get(i) ? 1L : 0L);
            }
            dVar.e();
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0038, code lost:
            if (java.lang.Integer.parseInt(r1) != 0) goto L20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0072, code lost:
            if (r8.n() != 0) goto L20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0075, code lost:
            r5 = false;
         */
        @Override // com.crrepa.n0.x
        /* renamed from: d */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.util.BitSet a(com.crrepa.t0.a r8) throws java.io.IOException {
            /*
                r7 = this;
                com.crrepa.t0.c r0 = r8.t()
                com.crrepa.t0.c r1 = com.crrepa.t0.c.NULL
                if (r0 != r1) goto Ld
                r8.q()
                r8 = 0
                return r8
            Ld:
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r8.a()
                com.crrepa.t0.c r1 = r8.t()
                r2 = 0
                r3 = r2
            L1b:
                com.crrepa.t0.c r4 = com.crrepa.t0.c.END_ARRAY
                if (r1 == r4) goto L82
                int[] r4 = com.crrepa.q0.n.e.f7832a
                int r5 = r1.ordinal()
                r4 = r4[r5]
                r5 = 1
                if (r4 == r5) goto L6e
                r6 = 2
                if (r4 == r6) goto L69
                r6 = 3
                if (r4 != r6) goto L52
                java.lang.String r1 = r8.r()
                int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L3b
                if (r1 == 0) goto L75
                goto L76
            L3b:
                com.crrepa.n0.v r8 = new com.crrepa.n0.v
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Error: Expecting: bitset number value (1, 0), Found: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r8.<init>(r0)
                throw r8
            L52:
                com.crrepa.n0.v r8 = new com.crrepa.n0.v
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Invalid bitset value type: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r8.<init>(r0)
                throw r8
            L69:
                boolean r5 = r8.l()
                goto L76
            L6e:
                int r1 = r8.n()
                if (r1 == 0) goto L75
                goto L76
            L75:
                r5 = r2
            L76:
                if (r5 == 0) goto L7b
                r0.set(r3)
            L7b:
                int r3 = r3 + 1
                com.crrepa.t0.c r1 = r8.t()
                goto L1b
            L82:
                r8.f()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.crrepa.q0.n.g0.a(com.crrepa.t0.a):java.util.BitSet");
        }
    }

    /* loaded from: classes9.dex */
    public static class h extends com.crrepa.n0.x<Number> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Number number) throws IOException {
            dVar.a(number);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Number a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return Double.valueOf(aVar.m());
        }
    }

    /* loaded from: classes9.dex */
    public static class h0 implements com.crrepa.n0.y {
        @Override // com.crrepa.n0.y
        public <T> com.crrepa.n0.x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            Class a2 = aVar.a();
            if (!Enum.class.isAssignableFrom(a2) || a2 == Enum.class) {
                return null;
            }
            if (!a2.isEnum()) {
                a2 = (Class<? super Object>) a2.getSuperclass();
            }
            return new u(a2);
        }
    }

    /* loaded from: classes9.dex */
    public static class i extends com.crrepa.n0.x<Boolean> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Boolean bool) throws IOException {
            dVar.e(bool == null ? "null" : bool.toString());
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Boolean a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return Boolean.valueOf(aVar.r());
        }
    }

    /* loaded from: classes9.dex */
    public static class i0 implements com.crrepa.n0.y {
        public final /* synthetic */ com.crrepa.s0.a h;
        public final /* synthetic */ com.crrepa.n0.x i;

        public i0(com.crrepa.s0.a aVar, com.crrepa.n0.x xVar) {
            this.h = aVar;
            this.i = xVar;
        }

        @Override // com.crrepa.n0.y
        public <T> com.crrepa.n0.x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            if (aVar.equals(this.h)) {
                return this.i;
            }
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public static class j extends com.crrepa.n0.x<Number> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Number number) throws IOException {
            dVar.a(number);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Number a(com.crrepa.t0.a aVar) throws IOException {
            com.crrepa.t0.c t = aVar.t();
            int i = e.f7832a[t.ordinal()];
            if (i != 1) {
                if (i == 4) {
                    aVar.q();
                    return null;
                }
                throw new com.crrepa.n0.v("Expecting number, got: " + t);
            }
            return new com.crrepa.p0.f(aVar.r());
        }
    }

    /* loaded from: classes9.dex */
    public static class j0 implements com.crrepa.n0.y {
        public final /* synthetic */ Class h;
        public final /* synthetic */ com.crrepa.n0.x i;

        public j0(Class cls, com.crrepa.n0.x xVar) {
            this.h = cls;
            this.i = xVar;
        }

        @Override // com.crrepa.n0.y
        public <T> com.crrepa.n0.x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            if (aVar.a() == this.h) {
                return this.i;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.h.getName() + ",adapter=" + this.i + "]";
        }
    }

    /* loaded from: classes9.dex */
    public static class k extends com.crrepa.n0.x<Number> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Number number) throws IOException {
            dVar.a(number);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Number a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.n());
            } catch (NumberFormatException e) {
                throw new com.crrepa.n0.v(e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class k0 implements com.crrepa.n0.y {
        public final /* synthetic */ Class h;
        public final /* synthetic */ Class i;
        public final /* synthetic */ com.crrepa.n0.x j;

        public k0(Class cls, Class cls2, com.crrepa.n0.x xVar) {
            this.h = cls;
            this.i = cls2;
            this.j = xVar;
        }

        @Override // com.crrepa.n0.y
        public <T> com.crrepa.n0.x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            Class<? super T> a2 = aVar.a();
            if (a2 == this.h || a2 == this.i) {
                return this.j;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.i.getName() + "+" + this.h.getName() + ",adapter=" + this.j + "]";
        }
    }

    /* loaded from: classes9.dex */
    public static class l extends com.crrepa.n0.x<Character> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Character ch) throws IOException {
            dVar.e(ch == null ? null : String.valueOf(ch));
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Character a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            String r = aVar.r();
            if (r.length() == 1) {
                return Character.valueOf(r.charAt(0));
            }
            throw new com.crrepa.n0.v("Expecting character, got: " + r);
        }
    }

    /* loaded from: classes9.dex */
    public static class m extends com.crrepa.n0.x<Number> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Number number) throws IOException {
            dVar.a(number);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Number a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.n());
            } catch (NumberFormatException e) {
                throw new com.crrepa.n0.v(e);
            }
        }
    }

    /* renamed from: com.crrepa.q0.n$n  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0355n extends com.crrepa.n0.x<String> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, String str) throws IOException {
            dVar.e(str);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public String a(com.crrepa.t0.a aVar) throws IOException {
            com.crrepa.t0.c t = aVar.t();
            if (t != com.crrepa.t0.c.NULL) {
                return t == com.crrepa.t0.c.BOOLEAN ? Boolean.toString(aVar.l()) : aVar.r();
            }
            aVar.q();
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public static class o extends com.crrepa.n0.x<Number> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Number number) throws IOException {
            dVar.a(number);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Number a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            try {
                return Integer.valueOf(aVar.n());
            } catch (NumberFormatException e) {
                throw new com.crrepa.n0.v(e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class p extends com.crrepa.n0.x<BigDecimal> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, BigDecimal bigDecimal) throws IOException {
            dVar.a(bigDecimal);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public BigDecimal a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            try {
                return new BigDecimal(aVar.r());
            } catch (NumberFormatException e) {
                throw new com.crrepa.n0.v(e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class q extends com.crrepa.n0.x<AtomicInteger> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, AtomicInteger atomicInteger) throws IOException {
            dVar.a(atomicInteger.get());
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public AtomicInteger a(com.crrepa.t0.a aVar) throws IOException {
            try {
                return new AtomicInteger(aVar.n());
            } catch (NumberFormatException e) {
                throw new com.crrepa.n0.v(e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class r extends com.crrepa.n0.x<BigInteger> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, BigInteger bigInteger) throws IOException {
            dVar.a(bigInteger);
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public BigInteger a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            try {
                return new BigInteger(aVar.r());
            } catch (NumberFormatException e) {
                throw new com.crrepa.n0.v(e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class s extends com.crrepa.n0.x<AtomicBoolean> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, AtomicBoolean atomicBoolean) throws IOException {
            dVar.d(atomicBoolean.get());
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public AtomicBoolean a(com.crrepa.t0.a aVar) throws IOException {
            return new AtomicBoolean(aVar.l());
        }
    }

    /* loaded from: classes9.dex */
    public static class t extends com.crrepa.n0.x<StringBuilder> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, StringBuilder sb) throws IOException {
            dVar.e(sb == null ? null : sb.toString());
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public StringBuilder a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return new StringBuilder(aVar.r());
        }
    }

    /* loaded from: classes9.dex */
    public static final class u<T extends Enum<T>> extends com.crrepa.n0.x<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Map<String, T> f7833a = new HashMap();
        public final Map<T, String> b = new HashMap();

        public u(Class<T> cls) {
            T[] enumConstants;
            try {
                for (T t : cls.getEnumConstants()) {
                    String name = t.name();
                    com.crrepa.o0.c cVar = (com.crrepa.o0.c) cls.getField(name).getAnnotation(com.crrepa.o0.c.class);
                    if (cVar != null) {
                        name = cVar.value();
                        for (String str : cVar.alternate()) {
                            this.f7833a.put(str, t);
                        }
                    }
                    this.f7833a.put(name, t);
                    this.b.put(t, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, T t) throws IOException {
            dVar.e(t == null ? null : this.b.get(t));
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public T a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return this.f7833a.get(aVar.r());
        }
    }

    /* loaded from: classes9.dex */
    public static class v extends com.crrepa.n0.x<Class> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Class cls) throws IOException {
            if (cls == null) {
                dVar.k();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public Class a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }

    /* loaded from: classes9.dex */
    public static class w extends com.crrepa.n0.x<StringBuffer> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, StringBuffer stringBuffer) throws IOException {
            dVar.e(stringBuffer == null ? null : stringBuffer.toString());
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public StringBuffer a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return new StringBuffer(aVar.r());
        }
    }

    /* loaded from: classes9.dex */
    public static class x extends com.crrepa.n0.x<URL> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, URL url) throws IOException {
            dVar.e(url == null ? null : url.toExternalForm());
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public URL a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            String r = aVar.r();
            if ("null".equals(r)) {
                return null;
            }
            return new URL(r);
        }
    }

    /* loaded from: classes9.dex */
    public static class y extends com.crrepa.n0.x<URI> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, URI uri) throws IOException {
            dVar.e(uri == null ? null : uri.toASCIIString());
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public URI a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            try {
                String r = aVar.r();
                if ("null".equals(r)) {
                    return null;
                }
                return new URI(r);
            } catch (URISyntaxException e) {
                throw new com.crrepa.n0.m(e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class z extends com.crrepa.n0.x<InetAddress> {
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, InetAddress inetAddress) throws IOException {
            dVar.e(inetAddress == null ? null : inetAddress.getHostAddress());
        }

        @Override // com.crrepa.n0.x
        /* renamed from: d */
        public InetAddress a(com.crrepa.t0.a aVar) throws IOException {
            if (aVar.t() == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            return InetAddress.getByName(aVar.r());
        }
    }

    static {
        v vVar = new v();
        f7829a = vVar;
        b = a(Class.class, vVar);
        g0 g0Var = new g0();
        c = g0Var;
        d = a(BitSet.class, g0Var);
        g gVar = new g();
        e = gVar;
        f = new i();
        g = a(Boolean.TYPE, Boolean.class, gVar);
        k kVar = new k();
        h = kVar;
        i = a(Byte.TYPE, Byte.class, kVar);
        m mVar = new m();
        j = mVar;
        k = a(Short.TYPE, Short.class, mVar);
        o oVar = new o();
        l = oVar;
        m = a(Integer.TYPE, Integer.class, oVar);
        com.crrepa.n0.x<AtomicInteger> a2 = new q().a();
        n = a2;
        o = a(AtomicInteger.class, a2);
        com.crrepa.n0.x<AtomicBoolean> a3 = new s().a();
        p = a3;
        q = a(AtomicBoolean.class, a3);
        com.crrepa.n0.x<AtomicIntegerArray> a4 = new b().a();
        r = a4;
        s = a(AtomicIntegerArray.class, a4);
        t = new d();
        u = new f();
        v = new h();
        j jVar = new j();
        w = jVar;
        x = a(Number.class, jVar);
        l lVar = new l();
        y = lVar;
        z = a(Character.TYPE, Character.class, lVar);
        C0355n c0355n = new C0355n();
        A = c0355n;
        B = new p();
        C = new r();
        D = a(String.class, c0355n);
        t tVar = new t();
        E = tVar;
        F = a(StringBuilder.class, tVar);
        w wVar = new w();
        G = wVar;
        H = a(StringBuffer.class, wVar);
        x xVar = new x();
        I = xVar;
        J = a(URL.class, xVar);
        y yVar = new y();
        K = yVar;
        L = a(URI.class, yVar);
        z zVar = new z();
        M = zVar;
        N = b(InetAddress.class, zVar);
        a0 a0Var = new a0();
        O = a0Var;
        P = a(UUID.class, a0Var);
        com.crrepa.n0.x<Currency> a5 = new b0().a();
        Q = a5;
        R = a(Currency.class, a5);
        S = new c0();
        d0 d0Var = new d0();
        T = d0Var;
        U = b(Calendar.class, GregorianCalendar.class, d0Var);
        e0 e0Var = new e0();
        V = e0Var;
        W = a(Locale.class, e0Var);
        f0 f0Var = new f0();
        X = f0Var;
        Y = b(com.crrepa.n0.l.class, f0Var);
        Z = new h0();
    }

    public n() {
        throw new UnsupportedOperationException();
    }

    public static <TT> com.crrepa.n0.y a(com.crrepa.s0.a<TT> aVar, com.crrepa.n0.x<TT> xVar) {
        return new i0(aVar, xVar);
    }

    public static <TT> com.crrepa.n0.y a(Class<TT> cls, com.crrepa.n0.x<TT> xVar) {
        return new j0(cls, xVar);
    }

    public static <TT> com.crrepa.n0.y a(Class<TT> cls, Class<TT> cls2, com.crrepa.n0.x<? super TT> xVar) {
        return new k0(cls, cls2, xVar);
    }

    public static <T1> com.crrepa.n0.y b(Class<T1> cls, com.crrepa.n0.x<T1> xVar) {
        return new c(cls, xVar);
    }

    public static <TT> com.crrepa.n0.y b(Class<TT> cls, Class<? extends TT> cls2, com.crrepa.n0.x<? super TT> xVar) {
        return new a(cls, cls2, xVar);
    }
}
