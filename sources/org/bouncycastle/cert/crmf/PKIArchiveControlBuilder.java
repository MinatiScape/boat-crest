package org.bouncycastle.cert.crmf;

import java.io.IOException;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.asn1.crmf.CRMFObjectIdentifiers;
import org.bouncycastle.asn1.crmf.EncKeyWithID;
import org.bouncycastle.asn1.crmf.EncryptedKey;
import org.bouncycastle.asn1.crmf.PKIArchiveOptions;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cms.CMSEnvelopedDataGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.RecipientInfoGenerator;
import org.bouncycastle.operator.OutputEncryptor;
/* loaded from: classes12.dex */
public class PKIArchiveControlBuilder {

    /* renamed from: a  reason: collision with root package name */
    public CMSEnvelopedDataGenerator f14462a;
    public CMSProcessableByteArray b;

    public PKIArchiveControlBuilder(PrivateKeyInfo privateKeyInfo, GeneralName generalName) {
        try {
            this.b = new CMSProcessableByteArray(CRMFObjectIdentifiers.id_ct_encKeyWithID, new EncKeyWithID(privateKeyInfo, generalName).getEncoded());
            this.f14462a = new CMSEnvelopedDataGenerator();
        } catch (IOException unused) {
            throw new IllegalStateException("unable to encode key and general name info");
        }
    }

    public PKIArchiveControlBuilder addRecipientGenerator(RecipientInfoGenerator recipientInfoGenerator) {
        this.f14462a.addRecipientInfoGenerator(recipientInfoGenerator);
        return this;
    }

    public PKIArchiveControl build(OutputEncryptor outputEncryptor) throws CMSException {
        return new PKIArchiveControl(new PKIArchiveOptions(new EncryptedKey(EnvelopedData.getInstance(this.f14462a.generate(this.b, outputEncryptor).toASN1Structure().getContent()))));
    }
}
