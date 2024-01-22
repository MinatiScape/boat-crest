package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.cms.OriginatorInfo;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Store;
/* loaded from: classes12.dex */
public class OriginatorInformation {

    /* renamed from: a  reason: collision with root package name */
    public OriginatorInfo f14540a;

    public OriginatorInformation(OriginatorInfo originatorInfo) {
        this.f14540a = originatorInfo;
    }

    public Store getCRLs() {
        ASN1Set cRLs = this.f14540a.getCRLs();
        if (cRLs != null) {
            ArrayList arrayList = new ArrayList(cRLs.size());
            Enumeration objects = cRLs.getObjects();
            while (objects.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) objects.nextElement()).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Sequence) {
                    arrayList.add(new X509CRLHolder(CertificateList.getInstance(aSN1Primitive)));
                }
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    public Store getCertificates() {
        ASN1Set certificates = this.f14540a.getCertificates();
        if (certificates != null) {
            ArrayList arrayList = new ArrayList(certificates.size());
            Enumeration objects = certificates.getObjects();
            while (objects.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) objects.nextElement()).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Sequence) {
                    arrayList.add(new X509CertificateHolder(Certificate.getInstance(aSN1Primitive)));
                }
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    public OriginatorInfo toASN1Structure() {
        return this.f14540a;
    }
}
