package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OctetStringParser;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1SetParser;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.EncryptedContentInfoParser;
import org.bouncycastle.asn1.cms.EnvelopedDataParser;
import org.bouncycastle.asn1.cms.OriginatorInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.b;
/* loaded from: classes12.dex */
public class CMSEnvelopedDataParser extends CMSContentInfoParser {

    /* renamed from: a  reason: collision with root package name */
    public RecipientInformationStore f14524a;
    public EnvelopedDataParser b;
    public AlgorithmIdentifier c;
    public AttributeTable d;
    public boolean e;
    public OriginatorInformation f;

    public CMSEnvelopedDataParser(InputStream inputStream) throws CMSException, IOException {
        super(inputStream);
        this.e = true;
        EnvelopedDataParser envelopedDataParser = new EnvelopedDataParser((ASN1SequenceParser) this._contentInfo.getContent(16));
        this.b = envelopedDataParser;
        OriginatorInfo originatorInfo = envelopedDataParser.getOriginatorInfo();
        if (originatorInfo != null) {
            this.f = new OriginatorInformation(originatorInfo);
        }
        ASN1Set aSN1Set = ASN1Set.getInstance(this.b.getRecipientInfos().toASN1Primitive());
        EncryptedContentInfoParser encryptedContentInfo = this.b.getEncryptedContentInfo();
        this.c = encryptedContentInfo.getContentEncryptionAlgorithm();
        this.f14524a = b.a(aSN1Set, this.c, new b.c(this.c, new c(((ASN1OctetStringParser) encryptedContentInfo.getEncryptedContent(4)).getOctetStream())));
    }

    public CMSEnvelopedDataParser(byte[] bArr) throws CMSException, IOException {
        this(new ByteArrayInputStream(bArr));
    }

    public final byte[] a(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public AlgorithmIdentifier getContentEncryptionAlgorithm() {
        return this.c;
    }

    public String getEncryptionAlgOID() {
        return this.c.getAlgorithm().toString();
    }

    public byte[] getEncryptionAlgParams() {
        try {
            return a(this.c.getParameters());
        } catch (Exception e) {
            throw new RuntimeException("exception getting encryption parameters " + e);
        }
    }

    public OriginatorInformation getOriginatorInfo() {
        return this.f;
    }

    public RecipientInformationStore getRecipientInfos() {
        return this.f14524a;
    }

    public AttributeTable getUnprotectedAttributes() throws IOException {
        if (this.d == null && this.e) {
            ASN1SetParser unprotectedAttrs = this.b.getUnprotectedAttrs();
            this.e = false;
            if (unprotectedAttrs != null) {
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                while (true) {
                    ASN1Encodable readObject = unprotectedAttrs.readObject();
                    if (readObject == null) {
                        break;
                    }
                    aSN1EncodableVector.add(((ASN1SequenceParser) readObject).toASN1Primitive());
                }
                this.d = new AttributeTable(new DERSet(aSN1EncodableVector));
            }
        }
        return this.d;
    }
}
