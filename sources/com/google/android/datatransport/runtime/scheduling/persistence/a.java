package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.c;
/* loaded from: classes6.dex */
public final class a extends c {
    public final long b;
    public final int c;
    public final int d;
    public final long e;
    public final int f;

    /* loaded from: classes6.dex */
    public static final class b extends c.a {

        /* renamed from: a  reason: collision with root package name */
        public Long f8137a;
        public Integer b;
        public Integer c;
        public Long d;
        public Integer e;

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.c.a
        public c a() {
            String str = "";
            if (this.f8137a == null) {
                str = " maxStorageSizeInBytes";
            }
            if (this.b == null) {
                str = str + " loadBatchSize";
            }
            if (this.c == null) {
                str = str + " criticalSectionEnterTimeoutMs";
            }
            if (this.d == null) {
                str = str + " eventCleanUpAge";
            }
            if (this.e == null) {
                str = str + " maxBlobByteSizePerRow";
            }
            if (str.isEmpty()) {
                return new a(this.f8137a.longValue(), this.b.intValue(), this.c.intValue(), this.d.longValue(), this.e.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.c.a
        public c.a b(int i) {
            this.c = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.c.a
        public c.a c(long j) {
            this.d = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.c.a
        public c.a d(int i) {
            this.b = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.c.a
        public c.a e(int i) {
            this.e = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.c.a
        public c.a f(long j) {
            this.f8137a = Long.valueOf(j);
            return this;
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.c
    public int b() {
        return this.d;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.c
    public long c() {
        return this.e;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.c
    public int d() {
        return this.c;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.c
    public int e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            return this.b == cVar.f() && this.c == cVar.d() && this.d == cVar.b() && this.e == cVar.c() && this.f == cVar.e();
        }
        return false;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.c
    public long f() {
        return this.b;
    }

    public int hashCode() {
        long j = this.b;
        long j2 = this.e;
        return ((((((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.c) * 1000003) ^ this.d) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.f;
    }

    public String toString() {
        return "EventStoreConfig{maxStorageSizeInBytes=" + this.b + ", loadBatchSize=" + this.c + ", criticalSectionEnterTimeoutMs=" + this.d + ", eventCleanUpAge=" + this.e + ", maxBlobByteSizePerRow=" + this.f + "}";
    }

    public a(long j, int i, int i2, long j2, int i3) {
        this.b = j;
        this.c = i;
        this.d = i2;
        this.e = j2;
        this.f = i3;
    }
}
