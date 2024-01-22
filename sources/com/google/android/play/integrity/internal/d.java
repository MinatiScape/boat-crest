package com.google.android.play.integrity.internal;
/* loaded from: classes10.dex */
public final class d extends e {

    /* renamed from: a  reason: collision with root package name */
    public final long f10469a;

    public d(int i, long j) {
        this.f10469a = j;
    }

    @Override // com.google.android.play.integrity.internal.e
    public final int a() {
        return 3;
    }

    @Override // com.google.android.play.integrity.internal.e
    public final long b() {
        return this.f10469a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            eVar.a();
            if (this.f10469a == eVar.b()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j = this.f10469a;
        return ((int) (j ^ (j >>> 32))) ^ (-724379968);
    }

    public final String toString() {
        long j = this.f10469a;
        return "EventRecord{eventType=3, eventTimestamp=" + j + "}";
    }
}
