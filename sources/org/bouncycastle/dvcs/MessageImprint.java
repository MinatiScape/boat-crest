package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.x509.DigestInfo;
/* loaded from: classes13.dex */
public class MessageImprint {

    /* renamed from: a  reason: collision with root package name */
    public final DigestInfo f14887a;

    public MessageImprint(DigestInfo digestInfo) {
        this.f14887a = digestInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MessageImprint) {
            return this.f14887a.equals(((MessageImprint) obj).f14887a);
        }
        return false;
    }

    public int hashCode() {
        return this.f14887a.hashCode();
    }

    public DigestInfo toASN1Structure() {
        return this.f14887a;
    }
}
