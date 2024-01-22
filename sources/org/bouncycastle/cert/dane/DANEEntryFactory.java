package org.bouncycastle.cert.dane;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes12.dex */
public class DANEEntryFactory {

    /* renamed from: a  reason: collision with root package name */
    public final DANEEntrySelectorFactory f14479a;

    public DANEEntryFactory(DigestCalculator digestCalculator) {
        this.f14479a = new DANEEntrySelectorFactory(digestCalculator);
    }

    public DANEEntry createEntry(String str, int i, X509CertificateHolder x509CertificateHolder) throws DANEException {
        if (i < 0 || i > 3) {
            throw new DANEException("unknown certificate usage: " + i);
        }
        return new DANEEntry(this.f14479a.createSelector(str).getDomainName(), new byte[]{(byte) i, 0, 0}, x509CertificateHolder);
    }

    public DANEEntry createEntry(String str, X509CertificateHolder x509CertificateHolder) throws DANEException {
        return createEntry(str, 3, x509CertificateHolder);
    }
}
