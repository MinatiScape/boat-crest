package org.bouncycastle.cert.cmp;

import java.math.BigInteger;
import org.bouncycastle.asn1.cmp.RevDetails;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes12.dex */
public class RevocationDetails {

    /* renamed from: a  reason: collision with root package name */
    public RevDetails f14455a;

    public RevocationDetails(RevDetails revDetails) {
        this.f14455a = revDetails;
    }

    public X500Name getIssuer() {
        return this.f14455a.getCertDetails().getIssuer();
    }

    public BigInteger getSerialNumber() {
        return this.f14455a.getCertDetails().getSerialNumber().getValue();
    }

    public X500Name getSubject() {
        return this.f14455a.getCertDetails().getSubject();
    }

    public RevDetails toASN1Structure() {
        return this.f14455a;
    }
}
