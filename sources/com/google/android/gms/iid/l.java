package com.google.android.gms.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;
/* loaded from: classes6.dex */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    public final KeyPair f8489a;
    public final long b;

    @VisibleForTesting
    public l(KeyPair keyPair, long j) {
        this.f8489a = keyPair;
        this.b = j;
    }

    public final long a() {
        return this.b;
    }

    public final KeyPair b() {
        return this.f8489a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof l) {
            l lVar = (l) obj;
            return this.b == lVar.b && this.f8489a.getPublic().equals(lVar.f8489a.getPublic()) && this.f8489a.getPrivate().equals(lVar.f8489a.getPrivate());
        }
        return false;
    }

    public final String f() {
        return Base64.encodeToString(this.f8489a.getPublic().getEncoded(), 11);
    }

    public final String g() {
        return Base64.encodeToString(this.f8489a.getPrivate().getEncoded(), 11);
    }

    public final int hashCode() {
        return Objects.hashCode(this.f8489a.getPublic(), this.f8489a.getPrivate(), Long.valueOf(this.b));
    }
}
