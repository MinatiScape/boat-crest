package org.bouncycastle.asn1.dvcs;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.ess.ESSCertID;
import org.bouncycastle.asn1.ocsp.CertID;
import org.bouncycastle.asn1.ocsp.CertStatus;
import org.bouncycastle.asn1.ocsp.OCSPResponse;
import org.bouncycastle.asn1.smime.SMIMECapabilities;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.Extension;
/* loaded from: classes12.dex */
public class CertEtcToken extends ASN1Object implements ASN1Choice {
    public static final int TAG_ASSERTION = 3;
    public static final int TAG_CAPABILITIES = 8;
    public static final int TAG_CERTIFICATE = 0;
    public static final int TAG_CRL = 4;
    public static final int TAG_ESSCERTID = 1;
    public static final int TAG_OCSPCERTID = 6;
    public static final int TAG_OCSPCERTSTATUS = 5;
    public static final int TAG_OCSPRESPONSE = 7;
    public static final int TAG_PKISTATUS = 2;
    public static final boolean[] k = {false, true, false, true, false, true, false, false, true};
    public int h;
    public ASN1Encodable i;
    public Extension j;

    public CertEtcToken(int i, ASN1Encodable aSN1Encodable) {
        this.h = i;
        this.i = aSN1Encodable;
    }

    public CertEtcToken(ASN1TaggedObject aSN1TaggedObject) {
        ASN1Encodable certificate;
        int tagNo = aSN1TaggedObject.getTagNo();
        this.h = tagNo;
        switch (tagNo) {
            case 0:
                certificate = Certificate.getInstance(aSN1TaggedObject, false);
                break;
            case 1:
                certificate = ESSCertID.getInstance(aSN1TaggedObject.getObject());
                break;
            case 2:
                certificate = PKIStatusInfo.getInstance(aSN1TaggedObject, false);
                break;
            case 3:
                certificate = ContentInfo.getInstance(aSN1TaggedObject.getObject());
                break;
            case 4:
                certificate = CertificateList.getInstance(aSN1TaggedObject, false);
                break;
            case 5:
                certificate = CertStatus.getInstance(aSN1TaggedObject.getObject());
                break;
            case 6:
                certificate = CertID.getInstance(aSN1TaggedObject, false);
                break;
            case 7:
                certificate = OCSPResponse.getInstance(aSN1TaggedObject, false);
                break;
            case 8:
                certificate = SMIMECapabilities.getInstance(aSN1TaggedObject.getObject());
                break;
            default:
                throw new IllegalArgumentException("Unknown tag: " + this.h);
        }
        this.i = certificate;
    }

    public CertEtcToken(Extension extension) {
        this.h = -1;
        this.j = extension;
    }

    public static CertEtcToken[] arrayFromSequence(ASN1Sequence aSN1Sequence) {
        int size = aSN1Sequence.size();
        CertEtcToken[] certEtcTokenArr = new CertEtcToken[size];
        for (int i = 0; i != size; i++) {
            certEtcTokenArr[i] = getInstance(aSN1Sequence.getObjectAt(i));
        }
        return certEtcTokenArr;
    }

    public static CertEtcToken getInstance(Object obj) {
        if (obj instanceof CertEtcToken) {
            return (CertEtcToken) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new CertEtcToken((ASN1TaggedObject) obj);
        }
        if (obj != null) {
            return new CertEtcToken(Extension.getInstance(obj));
        }
        return null;
    }

    public Extension getExtension() {
        return this.j;
    }

    public int getTagNo() {
        return this.h;
    }

    public ASN1Encodable getValue() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        Extension extension = this.j;
        if (extension == null) {
            boolean[] zArr = k;
            int i = this.h;
            return new DERTaggedObject(zArr[i], i, this.i);
        }
        return extension.toASN1Primitive();
    }

    public String toString() {
        return "CertEtcToken {\n" + this.i + "}\n";
    }
}
