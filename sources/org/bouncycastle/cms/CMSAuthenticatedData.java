package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.AuthenticatedData;
import org.bouncycastle.asn1.cms.CMSAlgorithmProtection;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.b;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Encodable;
/* loaded from: classes12.dex */
public class CMSAuthenticatedData implements Encodable {
    public RecipientInformationStore h;
    public ContentInfo i;
    public AlgorithmIdentifier j;
    public ASN1Set k;
    public ASN1Set l;
    public byte[] m;
    public OriginatorInformation n;

    /* loaded from: classes12.dex */
    public class a implements org.bouncycastle.cms.a {
        public a() {
        }

        @Override // org.bouncycastle.cms.a
        public ASN1Set a() {
            return CMSAuthenticatedData.this.k;
        }
    }

    public CMSAuthenticatedData(InputStream inputStream) throws CMSException {
        this(g.o(inputStream));
    }

    public CMSAuthenticatedData(InputStream inputStream, DigestCalculatorProvider digestCalculatorProvider) throws CMSException {
        this(g.o(inputStream), digestCalculatorProvider);
    }

    public CMSAuthenticatedData(ContentInfo contentInfo) throws CMSException {
        this(contentInfo, (DigestCalculatorProvider) null);
    }

    public CMSAuthenticatedData(ContentInfo contentInfo, DigestCalculatorProvider digestCalculatorProvider) throws CMSException {
        this.i = contentInfo;
        AuthenticatedData authenticatedData = AuthenticatedData.getInstance(contentInfo.getContent());
        if (authenticatedData.getOriginatorInfo() != null) {
            this.n = new OriginatorInformation(authenticatedData.getOriginatorInfo());
        }
        ASN1Set recipientInfos = authenticatedData.getRecipientInfos();
        this.j = authenticatedData.getMacAlgorithm();
        this.k = authenticatedData.getAuthAttrs();
        this.m = authenticatedData.getMac().getOctets();
        this.l = authenticatedData.getUnauthAttrs();
        CMSProcessableByteArray cMSProcessableByteArray = new CMSProcessableByteArray(ASN1OctetString.getInstance(authenticatedData.getEncapsulatedContentInfo().getContent()).getOctets());
        ASN1Set aSN1Set = this.k;
        if (aSN1Set == null) {
            this.h = b.a(recipientInfos, this.j, new b.a(this.j, cMSProcessableByteArray));
        } else if (digestCalculatorProvider == null) {
            throw new CMSException("a digest calculator provider is required if authenticated attributes are present");
        } else {
            ASN1EncodableVector all = new AttributeTable(aSN1Set).getAll(CMSAttributes.cmsAlgorithmProtect);
            if (all.size() > 1) {
                throw new CMSException("Only one instance of a cmsAlgorithmProtect attribute can be present");
            }
            if (all.size() > 0) {
                Attribute attribute = Attribute.getInstance(all.get(0));
                if (attribute.getAttrValues().size() != 1) {
                    throw new CMSException("A cmsAlgorithmProtect attribute MUST contain exactly one value");
                }
                CMSAlgorithmProtection cMSAlgorithmProtection = CMSAlgorithmProtection.getInstance(attribute.getAttributeValues()[0]);
                if (!g.m(cMSAlgorithmProtection.getDigestAlgorithm(), authenticatedData.getDigestAlgorithm())) {
                    throw new CMSException("CMS Algorithm Identifier Protection check failed for digestAlgorithm");
                }
                if (!g.m(cMSAlgorithmProtection.getMacAlgorithm(), this.j)) {
                    throw new CMSException("CMS Algorithm Identifier Protection check failed for macAlgorithm");
                }
            }
            try {
                this.h = b.b(recipientInfos, this.j, new b.C0901b(digestCalculatorProvider.get(authenticatedData.getDigestAlgorithm()), cMSProcessableByteArray), new a());
            } catch (OperatorCreationException e) {
                throw new CMSException("unable to create digest calculator: " + e.getMessage(), e);
            }
        }
    }

    public CMSAuthenticatedData(byte[] bArr) throws CMSException {
        this(g.q(bArr));
    }

    public CMSAuthenticatedData(byte[] bArr, DigestCalculatorProvider digestCalculatorProvider) throws CMSException {
        this(g.q(bArr), digestCalculatorProvider);
    }

    public final byte[] b(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public AttributeTable getAuthAttrs() {
        ASN1Set aSN1Set = this.k;
        if (aSN1Set == null) {
            return null;
        }
        return new AttributeTable(aSN1Set);
    }

    public byte[] getContentDigest() {
        if (this.k != null) {
            return ASN1OctetString.getInstance(getAuthAttrs().get(CMSAttributes.messageDigest).getAttrValues().getObjectAt(0)).getOctets();
        }
        return null;
    }

    public ContentInfo getContentInfo() {
        return this.i;
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.i.getEncoded();
    }

    public byte[] getMac() {
        return Arrays.clone(this.m);
    }

    public String getMacAlgOID() {
        return this.j.getAlgorithm().getId();
    }

    public byte[] getMacAlgParams() {
        try {
            return b(this.j.getParameters());
        } catch (Exception e) {
            throw new RuntimeException("exception getting encryption parameters " + e);
        }
    }

    public AlgorithmIdentifier getMacAlgorithm() {
        return this.j;
    }

    public OriginatorInformation getOriginatorInfo() {
        return this.n;
    }

    public RecipientInformationStore getRecipientInfos() {
        return this.h;
    }

    public AttributeTable getUnauthAttrs() {
        ASN1Set aSN1Set = this.l;
        if (aSN1Set == null) {
            return null;
        }
        return new AttributeTable(aSN1Set);
    }

    public ContentInfo toASN1Structure() {
        return this.i;
    }
}
