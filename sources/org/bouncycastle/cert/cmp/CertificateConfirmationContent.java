package org.bouncycastle.cert.cmp;

import org.bouncycastle.asn1.cmp.CertConfirmContent;
import org.bouncycastle.asn1.cmp.CertStatus;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
/* loaded from: classes12.dex */
public class CertificateConfirmationContent {

    /* renamed from: a  reason: collision with root package name */
    public DigestAlgorithmIdentifierFinder f14449a;
    public CertConfirmContent b;

    public CertificateConfirmationContent(CertConfirmContent certConfirmContent) {
        this(certConfirmContent, new DefaultDigestAlgorithmIdentifierFinder());
    }

    public CertificateConfirmationContent(CertConfirmContent certConfirmContent, DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        this.f14449a = digestAlgorithmIdentifierFinder;
        this.b = certConfirmContent;
    }

    public CertificateStatus[] getStatusMessages() {
        CertStatus[] certStatusArray = this.b.toCertStatusArray();
        int length = certStatusArray.length;
        CertificateStatus[] certificateStatusArr = new CertificateStatus[length];
        for (int i = 0; i != length; i++) {
            certificateStatusArr[i] = new CertificateStatus(this.f14449a, certStatusArray[i]);
        }
        return certificateStatusArr;
    }

    public CertConfirmContent toASN1Structure() {
        return this.b;
    }
}
