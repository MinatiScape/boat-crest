package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1OctetStringParser;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1SetParser;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.AuthenticatedDataParser;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.OriginatorInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.b;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class CMSAuthenticatedDataParser extends CMSContentInfoParser {

    /* renamed from: a  reason: collision with root package name */
    public RecipientInformationStore f14520a;
    public AuthenticatedDataParser b;
    public AlgorithmIdentifier c;
    public byte[] d;
    public AttributeTable e;
    public ASN1Set f;
    public AttributeTable g;
    public boolean h;
    public boolean i;
    public OriginatorInformation j;

    /* loaded from: classes12.dex */
    public class a implements org.bouncycastle.cms.a {
        public a() {
        }

        @Override // org.bouncycastle.cms.a
        public ASN1Set a() {
            try {
                return CMSAuthenticatedDataParser.this.c();
            } catch (IOException unused) {
                throw new IllegalStateException("can't parse authenticated attributes!");
            }
        }
    }

    public CMSAuthenticatedDataParser(InputStream inputStream) throws CMSException, IOException {
        this(inputStream, (DigestCalculatorProvider) null);
    }

    public CMSAuthenticatedDataParser(InputStream inputStream, DigestCalculatorProvider digestCalculatorProvider) throws CMSException, IOException {
        super(inputStream);
        this.h = true;
        AuthenticatedDataParser authenticatedDataParser = new AuthenticatedDataParser((ASN1SequenceParser) this._contentInfo.getContent(16));
        this.b = authenticatedDataParser;
        OriginatorInfo originatorInfo = authenticatedDataParser.getOriginatorInfo();
        if (originatorInfo != null) {
            this.j = new OriginatorInformation(originatorInfo);
        }
        ASN1Set aSN1Set = ASN1Set.getInstance(this.b.getRecipientInfos().toASN1Primitive());
        this.c = this.b.getMacAlgorithm();
        AlgorithmIdentifier digestAlgorithm = this.b.getDigestAlgorithm();
        if (digestAlgorithm == null) {
            this.f14520a = b.a(aSN1Set, this.c, new b.a(this.c, new c(((ASN1OctetStringParser) this.b.getEncapsulatedContentInfo().getContent(4)).getOctetStream())));
        } else if (digestCalculatorProvider == null) {
            throw new CMSException("a digest calculator provider is required if authenticated attributes are present");
        } else {
            try {
                this.f14520a = b.b(aSN1Set, this.c, new b.C0901b(digestCalculatorProvider.get(digestAlgorithm), new c(((ASN1OctetStringParser) this.b.getEncapsulatedContentInfo().getContent(4)).getOctetStream())), new a());
            } catch (OperatorCreationException e) {
                throw new CMSException("unable to create digest calculator: " + e.getMessage(), e);
            }
        }
    }

    public CMSAuthenticatedDataParser(byte[] bArr) throws CMSException, IOException {
        this(new ByteArrayInputStream(bArr));
    }

    public CMSAuthenticatedDataParser(byte[] bArr, DigestCalculatorProvider digestCalculatorProvider) throws CMSException, IOException {
        this(new ByteArrayInputStream(bArr), digestCalculatorProvider);
    }

    public final byte[] b(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public final ASN1Set c() throws IOException {
        if (this.e == null && this.h) {
            ASN1SetParser authAttrs = this.b.getAuthAttrs();
            if (authAttrs != null) {
                this.f = (ASN1Set) authAttrs.toASN1Primitive();
            }
            this.h = false;
        }
        return this.f;
    }

    public AttributeTable getAuthAttrs() throws IOException {
        ASN1Set c;
        if (this.e == null && this.h && (c = c()) != null) {
            this.e = new AttributeTable(c);
        }
        return this.e;
    }

    public byte[] getContentDigest() {
        AttributeTable attributeTable = this.e;
        if (attributeTable != null) {
            return ASN1OctetString.getInstance(attributeTable.get(CMSAttributes.messageDigest).getAttrValues().getObjectAt(0)).getOctets();
        }
        return null;
    }

    public byte[] getMac() throws IOException {
        if (this.d == null) {
            getAuthAttrs();
            this.d = this.b.getMac().getOctets();
        }
        return Arrays.clone(this.d);
    }

    public String getMacAlgOID() {
        return this.c.getAlgorithm().toString();
    }

    public byte[] getMacAlgParams() {
        try {
            return b(this.c.getParameters());
        } catch (Exception e) {
            throw new RuntimeException("exception getting encryption parameters " + e);
        }
    }

    public AlgorithmIdentifier getMacAlgorithm() {
        return this.c;
    }

    public OriginatorInformation getOriginatorInfo() {
        return this.j;
    }

    public RecipientInformationStore getRecipientInfos() {
        return this.f14520a;
    }

    public AttributeTable getUnauthAttrs() throws IOException {
        if (this.g == null && this.i) {
            ASN1SetParser unauthAttrs = this.b.getUnauthAttrs();
            this.i = false;
            if (unauthAttrs != null) {
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                while (true) {
                    ASN1Encodable readObject = unauthAttrs.readObject();
                    if (readObject == null) {
                        break;
                    }
                    aSN1EncodableVector.add(((ASN1SequenceParser) readObject).toASN1Primitive());
                }
                this.g = new AttributeTable(new DERSet(aSN1EncodableVector));
            }
        }
        return this.g;
    }
}
