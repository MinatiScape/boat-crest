package com.google.android.gms.gcm;

import android.os.Bundle;
import com.blankj.utilcode.constant.CacheConstants;
import com.google.android.gms.common.internal.ShowFirstParty;
@ShowFirstParty
/* loaded from: classes6.dex */
public final class zzl {
    public static final zzl zzaq = new zzl(0, 30, CacheConstants.HOUR);

    /* renamed from: a  reason: collision with root package name */
    public final int f8483a;
    public final int b = 30;
    public final int c = CacheConstants.HOUR;

    static {
        new zzl(1, 30, CacheConstants.HOUR);
    }

    public zzl(int i, int i2, int i3) {
        this.f8483a = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzl) {
            zzl zzlVar = (zzl) obj;
            return zzlVar.f8483a == this.f8483a && zzlVar.b == this.b && zzlVar.c == this.c;
        }
        return false;
    }

    public final int hashCode() {
        return (((((this.f8483a + 1) ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c;
    }

    public final String toString() {
        int i = this.f8483a;
        int i2 = this.b;
        int i3 = this.c;
        StringBuilder sb = new StringBuilder(74);
        sb.append("policy=");
        sb.append(i);
        sb.append(" initial_backoff=");
        sb.append(i2);
        sb.append(" maximum_backoff=");
        sb.append(i3);
        return sb.toString();
    }

    public final Bundle zzf(Bundle bundle) {
        bundle.putInt("retry_policy", this.f8483a);
        bundle.putInt("initial_backoff_seconds", this.b);
        bundle.putInt("maximum_backoff_seconds", this.c);
        return bundle;
    }

    public final int zzi() {
        return this.f8483a;
    }

    public final int zzj() {
        return this.b;
    }

    public final int zzk() {
        return this.c;
    }
}
