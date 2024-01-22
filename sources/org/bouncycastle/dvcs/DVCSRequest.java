package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.dvcs.DVCSObjectIdentifiers;
import org.bouncycastle.asn1.dvcs.ServiceType;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cms.CMSSignedData;
/* loaded from: classes13.dex */
public class DVCSRequest extends DVCSMessage {
    public org.bouncycastle.asn1.dvcs.DVCSRequest b;
    public DVCSRequestInfo c;
    public DVCSRequestData d;

    public DVCSRequest(ContentInfo contentInfo) throws DVCSConstructionException {
        super(contentInfo);
        DVCSRequestData cCPDRequestData;
        if (!DVCSObjectIdentifiers.id_ct_DVCSRequestData.equals(contentInfo.getContentType())) {
            throw new DVCSConstructionException("ContentInfo not a DVCS Request");
        }
        try {
            this.b = contentInfo.getContent().toASN1Primitive() instanceof ASN1Sequence ? org.bouncycastle.asn1.dvcs.DVCSRequest.getInstance(contentInfo.getContent()) : org.bouncycastle.asn1.dvcs.DVCSRequest.getInstance(ASN1OctetString.getInstance(contentInfo.getContent()).getOctets());
            DVCSRequestInfo dVCSRequestInfo = new DVCSRequestInfo(this.b.getRequestInformation());
            this.c = dVCSRequestInfo;
            int serviceType = dVCSRequestInfo.getServiceType();
            if (serviceType == ServiceType.CPD.getValue().intValue()) {
                cCPDRequestData = new CPDRequestData(this.b.getData());
            } else if (serviceType == ServiceType.VSD.getValue().intValue()) {
                cCPDRequestData = new VSDRequestData(this.b.getData());
            } else if (serviceType == ServiceType.VPKC.getValue().intValue()) {
                cCPDRequestData = new VPKCRequestData(this.b.getData());
            } else if (serviceType != ServiceType.CCPD.getValue().intValue()) {
                throw new DVCSConstructionException("Unknown service type: " + serviceType);
            } else {
                cCPDRequestData = new CCPDRequestData(this.b.getData());
            }
            this.d = cCPDRequestData;
        } catch (Exception e) {
            throw new DVCSConstructionException("Unable to parse content: " + e.getMessage(), e);
        }
    }

    public DVCSRequest(CMSSignedData cMSSignedData) throws DVCSConstructionException {
        this(SignedData.getInstance(cMSSignedData.toASN1Structure().getContent()).getEncapContentInfo());
    }

    @Override // org.bouncycastle.dvcs.DVCSMessage
    public ASN1Encodable getContent() {
        return this.b;
    }

    public DVCSRequestData getData() {
        return this.d;
    }

    public DVCSRequestInfo getRequestInfo() {
        return this.c;
    }

    public GeneralName getTransactionIdentifier() {
        return this.b.getTransactionIdentifier();
    }
}
