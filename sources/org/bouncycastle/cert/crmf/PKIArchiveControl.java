package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.asn1.crmf.CRMFObjectIdentifiers;
import org.bouncycastle.asn1.crmf.EncryptedKey;
import org.bouncycastle.asn1.crmf.PKIArchiveOptions;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSException;
/* loaded from: classes12.dex */
public class PKIArchiveControl implements Control {
    public static final int archiveRemGenPrivKey = 2;
    public static final ASN1ObjectIdentifier b = CRMFObjectIdentifiers.id_regCtrl_pkiArchiveOptions;
    public static final int encryptedPrivKey = 0;
    public static final int keyGenParameters = 1;

    /* renamed from: a  reason: collision with root package name */
    public final PKIArchiveOptions f14461a;

    public PKIArchiveControl(PKIArchiveOptions pKIArchiveOptions) {
        this.f14461a = pKIArchiveOptions;
    }

    public int getArchiveType() {
        return this.f14461a.getType();
    }

    public CMSEnvelopedData getEnvelopedData() throws CRMFException {
        try {
            return new CMSEnvelopedData(new ContentInfo(CMSObjectIdentifiers.envelopedData, EnvelopedData.getInstance(EncryptedKey.getInstance(this.f14461a.getValue()).getValue())));
        } catch (CMSException e) {
            throw new CRMFException("CMS parsing error: " + e.getMessage(), e.getCause());
        } catch (Exception e2) {
            throw new CRMFException("CRMF parsing error: " + e2.getMessage(), e2);
        }
    }

    @Override // org.bouncycastle.cert.crmf.Control
    public ASN1ObjectIdentifier getType() {
        return b;
    }

    @Override // org.bouncycastle.cert.crmf.Control
    public ASN1Encodable getValue() {
        return this.f14461a;
    }

    public boolean isEnvelopedData() {
        return !EncryptedKey.getInstance(this.f14461a.getValue()).isEncryptedValue();
    }
}
