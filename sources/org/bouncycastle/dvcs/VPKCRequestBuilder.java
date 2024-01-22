package org.bouncycastle.dvcs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bouncycastle.asn1.dvcs.CertEtcToken;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformationBuilder;
import org.bouncycastle.asn1.dvcs.DVCSTime;
import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.asn1.dvcs.ServiceType;
import org.bouncycastle.asn1.dvcs.TargetEtcChain;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509CertificateHolder;
/* loaded from: classes13.dex */
public class VPKCRequestBuilder extends DVCSRequestBuilder {
    public List b;

    public VPKCRequestBuilder() {
        super(new DVCSRequestInformationBuilder(ServiceType.VPKC));
        this.b = new ArrayList();
    }

    public void addTargetChain(Extension extension) {
        this.b.add(new TargetEtcChain(new CertEtcToken(extension)));
    }

    public void addTargetChain(X509CertificateHolder x509CertificateHolder) {
        this.b.add(new TargetEtcChain(new CertEtcToken(0, x509CertificateHolder.toASN1Structure())));
    }

    public void addTargetChain(TargetChain targetChain) {
        this.b.add(targetChain.toASN1Structure());
    }

    public DVCSRequest build() throws DVCSException {
        List list = this.b;
        return createDVCRequest(new Data((TargetEtcChain[]) list.toArray(new TargetEtcChain[list.size()])));
    }

    public void setRequestTime(Date date) {
        this.requestInformationBuilder.setRequestTime(new DVCSTime(date));
    }
}
