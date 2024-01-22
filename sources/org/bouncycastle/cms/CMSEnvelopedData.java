package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.EncryptedContentInfo;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.b;
import org.bouncycastle.util.Encodable;
/* loaded from: classes12.dex */
public class CMSEnvelopedData implements Encodable {
    public RecipientInformationStore h;
    public ContentInfo i;
    public AlgorithmIdentifier j;
    public ASN1Set k;
    public OriginatorInformation l;

    public CMSEnvelopedData(InputStream inputStream) throws CMSException {
        this(g.o(inputStream));
    }

    public CMSEnvelopedData(ContentInfo contentInfo) throws CMSException {
        this.i = contentInfo;
        try {
            EnvelopedData envelopedData = EnvelopedData.getInstance(contentInfo.getContent());
            if (envelopedData.getOriginatorInfo() != null) {
                this.l = new OriginatorInformation(envelopedData.getOriginatorInfo());
            }
            ASN1Set recipientInfos = envelopedData.getRecipientInfos();
            EncryptedContentInfo encryptedContentInfo = envelopedData.getEncryptedContentInfo();
            this.j = encryptedContentInfo.getContentEncryptionAlgorithm();
            this.h = b.a(recipientInfos, this.j, new b.c(this.j, new CMSProcessableByteArray(encryptedContentInfo.getEncryptedContent().getOctets())));
            this.k = envelopedData.getUnprotectedAttrs();
        } catch (ClassCastException e) {
            throw new CMSException("Malformed content.", e);
        } catch (IllegalArgumentException e2) {
            throw new CMSException("Malformed content.", e2);
        }
    }

    public CMSEnvelopedData(byte[] bArr) throws CMSException {
        this(g.q(bArr));
    }

    public final byte[] a(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public AlgorithmIdentifier getContentEncryptionAlgorithm() {
        return this.j;
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.i.getEncoded();
    }

    public String getEncryptionAlgOID() {
        return this.j.getAlgorithm().getId();
    }

    public byte[] getEncryptionAlgParams() {
        try {
            return a(this.j.getParameters());
        } catch (Exception e) {
            throw new RuntimeException("exception getting encryption parameters " + e);
        }
    }

    public OriginatorInformation getOriginatorInfo() {
        return this.l;
    }

    public RecipientInformationStore getRecipientInfos() {
        return this.h;
    }

    public AttributeTable getUnprotectedAttributes() {
        ASN1Set aSN1Set = this.k;
        if (aSN1Set == null) {
            return null;
        }
        return new AttributeTable(aSN1Set);
    }

    public ContentInfo toASN1Structure() {
        return this.i;
    }
}
