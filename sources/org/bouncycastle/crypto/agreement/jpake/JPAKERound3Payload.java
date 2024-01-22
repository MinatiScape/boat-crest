package org.bouncycastle.crypto.agreement.jpake;

import java.math.BigInteger;
/* loaded from: classes6.dex */
public class JPAKERound3Payload {

    /* renamed from: a  reason: collision with root package name */
    public final String f14623a;
    public final BigInteger b;

    public JPAKERound3Payload(String str, BigInteger bigInteger) {
        this.f14623a = str;
        this.b = bigInteger;
    }

    public BigInteger getMacTag() {
        return this.b;
    }

    public String getParticipantId() {
        return this.f14623a;
    }
}
