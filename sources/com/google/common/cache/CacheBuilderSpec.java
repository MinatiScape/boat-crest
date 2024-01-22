package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.a;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class CacheBuilderSpec {
    public static final Splitter o = Splitter.on(',').trimResults();
    public static final Splitter p = Splitter.on('=').trimResults();
    public static final ImmutableMap<String, m> q;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: a  reason: collision with root package name */
    public Integer f10538a;
    @VisibleForTesting
    @NullableDecl
    public Long b;
    @VisibleForTesting
    @NullableDecl
    public Long c;
    @VisibleForTesting
    @NullableDecl
    public Integer d;
    @VisibleForTesting
    @NullableDecl
    public a.t e;
    @VisibleForTesting
    @NullableDecl
    public a.t f;
    @VisibleForTesting
    @NullableDecl
    public Boolean g;
    @VisibleForTesting
    public long h;
    @VisibleForTesting
    @NullableDecl
    public TimeUnit i;
    @VisibleForTesting
    public long j;
    @VisibleForTesting
    @NullableDecl
    public TimeUnit k;
    @VisibleForTesting
    public long l;
    @VisibleForTesting
    @NullableDecl
    public TimeUnit m;
    public final String n;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10539a;

        static {
            int[] iArr = new int[a.t.values().length];
            f10539a = iArr;
            try {
                iArr[a.t.WEAK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10539a[a.t.SOFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class b extends d {
        @Override // com.google.common.cache.CacheBuilderSpec.d
        public void b(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.k == null, "expireAfterAccess already set");
            cacheBuilderSpec.j = j;
            cacheBuilderSpec.k = timeUnit;
        }
    }

    /* loaded from: classes10.dex */
    public static class c extends f {
        @Override // com.google.common.cache.CacheBuilderSpec.f
        public void b(CacheBuilderSpec cacheBuilderSpec, int i) {
            Integer num = cacheBuilderSpec.d;
            Preconditions.checkArgument(num == null, "concurrency level was already set to ", num);
            cacheBuilderSpec.d = Integer.valueOf(i);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class d implements m {
        @Override // com.google.common.cache.CacheBuilderSpec.m
        public void a(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            TimeUnit timeUnit;
            Preconditions.checkArgument((str2 == null || str2.isEmpty()) ? false : true, "value of key %s omitted", str);
            try {
                char charAt = str2.charAt(str2.length() - 1);
                if (charAt == 'd') {
                    timeUnit = TimeUnit.DAYS;
                } else if (charAt == 'h') {
                    timeUnit = TimeUnit.HOURS;
                } else if (charAt == 'm') {
                    timeUnit = TimeUnit.MINUTES;
                } else if (charAt == 's') {
                    timeUnit = TimeUnit.SECONDS;
                } else {
                    throw new IllegalArgumentException(CacheBuilderSpec.c("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", str, str2));
                }
                b(cacheBuilderSpec, Long.parseLong(str2.substring(0, str2.length() - 1)), timeUnit);
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(CacheBuilderSpec.c("key %s value set to %s, must be integer", str, str2));
            }
        }

        public abstract void b(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit);
    }

    /* loaded from: classes10.dex */
    public static class e extends f {
        @Override // com.google.common.cache.CacheBuilderSpec.f
        public void b(CacheBuilderSpec cacheBuilderSpec, int i) {
            Integer num = cacheBuilderSpec.f10538a;
            Preconditions.checkArgument(num == null, "initial capacity was already set to ", num);
            cacheBuilderSpec.f10538a = Integer.valueOf(i);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class f implements m {
        @Override // com.google.common.cache.CacheBuilderSpec.m
        public void a(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument((str2 == null || str2.isEmpty()) ? false : true, "value of key %s omitted", str);
            try {
                b(cacheBuilderSpec, Integer.parseInt(str2));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.c("key %s value set to %s, must be integer", str, str2), e);
            }
        }

        public abstract void b(CacheBuilderSpec cacheBuilderSpec, int i);
    }

    /* loaded from: classes10.dex */
    public static class g implements m {

        /* renamed from: a  reason: collision with root package name */
        public final a.t f10540a;

        public g(a.t tVar) {
            this.f10540a = tVar;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.m
        public void a(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2) {
            Preconditions.checkArgument(str2 == null, "key %s does not take values", str);
            a.t tVar = cacheBuilderSpec.e;
            Preconditions.checkArgument(tVar == null, "%s was already set to %s", str, tVar);
            cacheBuilderSpec.e = this.f10540a;
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class h implements m {
        @Override // com.google.common.cache.CacheBuilderSpec.m
        public void a(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument((str2 == null || str2.isEmpty()) ? false : true, "value of key %s omitted", str);
            try {
                b(cacheBuilderSpec, Long.parseLong(str2));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.c("key %s value set to %s, must be integer", str, str2), e);
            }
        }

        public abstract void b(CacheBuilderSpec cacheBuilderSpec, long j);
    }

    /* loaded from: classes10.dex */
    public static class i extends h {
        @Override // com.google.common.cache.CacheBuilderSpec.h
        public void b(CacheBuilderSpec cacheBuilderSpec, long j) {
            Long l = cacheBuilderSpec.b;
            Preconditions.checkArgument(l == null, "maximum size was already set to ", l);
            Long l2 = cacheBuilderSpec.c;
            Preconditions.checkArgument(l2 == null, "maximum weight was already set to ", l2);
            cacheBuilderSpec.b = Long.valueOf(j);
        }
    }

    /* loaded from: classes10.dex */
    public static class j extends h {
        @Override // com.google.common.cache.CacheBuilderSpec.h
        public void b(CacheBuilderSpec cacheBuilderSpec, long j) {
            Long l = cacheBuilderSpec.c;
            Preconditions.checkArgument(l == null, "maximum weight was already set to ", l);
            Long l2 = cacheBuilderSpec.b;
            Preconditions.checkArgument(l2 == null, "maximum size was already set to ", l2);
            cacheBuilderSpec.c = Long.valueOf(j);
        }
    }

    /* loaded from: classes10.dex */
    public static class k implements m {
        @Override // com.google.common.cache.CacheBuilderSpec.m
        public void a(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2) {
            Preconditions.checkArgument(str2 == null, "recordStats does not take values");
            Preconditions.checkArgument(cacheBuilderSpec.g == null, "recordStats already set");
            cacheBuilderSpec.g = Boolean.TRUE;
        }
    }

    /* loaded from: classes10.dex */
    public static class l extends d {
        @Override // com.google.common.cache.CacheBuilderSpec.d
        public void b(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.m == null, "refreshAfterWrite already set");
            cacheBuilderSpec.l = j;
            cacheBuilderSpec.m = timeUnit;
        }
    }

    /* loaded from: classes10.dex */
    public interface m {
        void a(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2);
    }

    /* loaded from: classes10.dex */
    public static class n implements m {

        /* renamed from: a  reason: collision with root package name */
        public final a.t f10541a;

        public n(a.t tVar) {
            this.f10541a = tVar;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.m
        public void a(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2) {
            Preconditions.checkArgument(str2 == null, "key %s does not take values", str);
            a.t tVar = cacheBuilderSpec.f;
            Preconditions.checkArgument(tVar == null, "%s was already set to %s", str, tVar);
            cacheBuilderSpec.f = this.f10541a;
        }
    }

    /* loaded from: classes10.dex */
    public static class o extends d {
        @Override // com.google.common.cache.CacheBuilderSpec.d
        public void b(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.i == null, "expireAfterWrite already set");
            cacheBuilderSpec.h = j;
            cacheBuilderSpec.i = timeUnit;
        }
    }

    static {
        ImmutableMap.Builder put = ImmutableMap.builder().put("initialCapacity", new e()).put("maximumSize", new i()).put("maximumWeight", new j()).put("concurrencyLevel", new c());
        a.t tVar = a.t.WEAK;
        q = put.put("weakKeys", new g(tVar)).put("softValues", new n(a.t.SOFT)).put("weakValues", new n(tVar)).put("recordStats", new k()).put("expireAfterAccess", new b()).put("expireAfterWrite", new o()).put("refreshAfterWrite", new l()).put("refreshInterval", new l()).build();
    }

    public CacheBuilderSpec(String str) {
        this.n = str;
    }

    @NullableDecl
    public static Long b(long j2, @NullableDecl TimeUnit timeUnit) {
        if (timeUnit == null) {
            return null;
        }
        return Long.valueOf(timeUnit.toNanos(j2));
    }

    public static String c(String str, Object... objArr) {
        return String.format(Locale.ROOT, str, objArr);
    }

    public static CacheBuilderSpec disableCaching() {
        return parse("maximumSize=0");
    }

    public static CacheBuilderSpec parse(String str) {
        CacheBuilderSpec cacheBuilderSpec = new CacheBuilderSpec(str);
        if (!str.isEmpty()) {
            for (String str2 : o.split(str)) {
                ImmutableList copyOf = ImmutableList.copyOf(p.split(str2));
                Preconditions.checkArgument(!copyOf.isEmpty(), "blank key-value pair");
                Preconditions.checkArgument(copyOf.size() <= 2, "key-value pair %s with more than one equals sign", str2);
                String str3 = (String) copyOf.get(0);
                m mVar = q.get(str3);
                Preconditions.checkArgument(mVar != null, "unknown key %s", str3);
                mVar.a(cacheBuilderSpec, str3, copyOf.size() == 1 ? null : (String) copyOf.get(1));
            }
        }
        return cacheBuilderSpec;
    }

    public CacheBuilder<Object, Object> d() {
        CacheBuilder<Object, Object> newBuilder = CacheBuilder.newBuilder();
        Integer num = this.f10538a;
        if (num != null) {
            newBuilder.initialCapacity(num.intValue());
        }
        Long l2 = this.b;
        if (l2 != null) {
            newBuilder.maximumSize(l2.longValue());
        }
        Long l3 = this.c;
        if (l3 != null) {
            newBuilder.maximumWeight(l3.longValue());
        }
        Integer num2 = this.d;
        if (num2 != null) {
            newBuilder.concurrencyLevel(num2.intValue());
        }
        a.t tVar = this.e;
        if (tVar != null) {
            if (a.f10539a[tVar.ordinal()] == 1) {
                newBuilder.weakKeys();
            } else {
                throw new AssertionError();
            }
        }
        a.t tVar2 = this.f;
        if (tVar2 != null) {
            int i2 = a.f10539a[tVar2.ordinal()];
            if (i2 == 1) {
                newBuilder.weakValues();
            } else if (i2 == 2) {
                newBuilder.softValues();
            } else {
                throw new AssertionError();
            }
        }
        Boolean bool = this.g;
        if (bool != null && bool.booleanValue()) {
            newBuilder.recordStats();
        }
        TimeUnit timeUnit = this.i;
        if (timeUnit != null) {
            newBuilder.expireAfterWrite(this.h, timeUnit);
        }
        TimeUnit timeUnit2 = this.k;
        if (timeUnit2 != null) {
            newBuilder.expireAfterAccess(this.j, timeUnit2);
        }
        TimeUnit timeUnit3 = this.m;
        if (timeUnit3 != null) {
            newBuilder.refreshAfterWrite(this.l, timeUnit3);
        }
        return newBuilder;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CacheBuilderSpec) {
            CacheBuilderSpec cacheBuilderSpec = (CacheBuilderSpec) obj;
            return Objects.equal(this.f10538a, cacheBuilderSpec.f10538a) && Objects.equal(this.b, cacheBuilderSpec.b) && Objects.equal(this.c, cacheBuilderSpec.c) && Objects.equal(this.d, cacheBuilderSpec.d) && Objects.equal(this.e, cacheBuilderSpec.e) && Objects.equal(this.f, cacheBuilderSpec.f) && Objects.equal(this.g, cacheBuilderSpec.g) && Objects.equal(b(this.h, this.i), b(cacheBuilderSpec.h, cacheBuilderSpec.i)) && Objects.equal(b(this.j, this.k), b(cacheBuilderSpec.j, cacheBuilderSpec.k)) && Objects.equal(b(this.l, this.m), b(cacheBuilderSpec.l, cacheBuilderSpec.m));
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.f10538a, this.b, this.c, this.d, this.e, this.f, this.g, b(this.h, this.i), b(this.j, this.k), b(this.l, this.m));
    }

    public String toParsableString() {
        return this.n;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(toParsableString()).toString();
    }
}
