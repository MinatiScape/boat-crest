package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.b;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes12.dex */
public abstract class RecipientInformation {

    /* renamed from: a  reason: collision with root package name */
    public a f14544a;
    public byte[] b;
    public RecipientOperator c;
    public AlgorithmIdentifier keyEncAlg;
    public AlgorithmIdentifier messageAlgorithm;
    public RecipientId rid;
    public e secureReadable;

    public RecipientInformation(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, e eVar, a aVar) {
        this.keyEncAlg = algorithmIdentifier;
        this.messageAlgorithm = algorithmIdentifier2;
        this.secureReadable = eVar;
        this.f14544a = aVar;
    }

    public final byte[] a(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public byte[] getContent(Recipient recipient) throws CMSException {
        try {
            return g.r(getContentStream(recipient).getContentStream());
        } catch (IOException e) {
            throw new CMSException("unable to parse internal stream: " + e.getMessage(), e);
        }
    }

    public byte[] getContentDigest() {
        e eVar = this.secureReadable;
        if (eVar instanceof b.C0901b) {
            return ((b.C0901b) eVar).b();
        }
        return null;
    }

    public CMSTypedStream getContentStream(Recipient recipient) throws CMSException, IOException {
        RecipientOperator recipientOperator = getRecipientOperator(recipient);
        this.c = recipientOperator;
        return this.f14544a != null ? new CMSTypedStream(this.secureReadable.getInputStream()) : new CMSTypedStream(recipientOperator.getInputStream(this.secureReadable.getInputStream()));
    }

    public String getKeyEncryptionAlgOID() {
        return this.keyEncAlg.getAlgorithm().getId();
    }

    public byte[] getKeyEncryptionAlgParams() {
        try {
            return a(this.keyEncAlg.getParameters());
        } catch (Exception e) {
            throw new RuntimeException("exception getting encryption parameters " + e);
        }
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.keyEncAlg;
    }

    public byte[] getMac() {
        if (this.b == null && this.c.isMacBased()) {
            if (this.f14544a != null) {
                try {
                    Streams.drain(this.c.getInputStream(new ByteArrayInputStream(this.f14544a.a().getEncoded(ASN1Encoding.DER))));
                } catch (IOException e) {
                    throw new IllegalStateException("unable to drain input: " + e.getMessage());
                }
            }
            this.b = this.c.getMac();
        }
        return this.b;
    }

    public RecipientId getRID() {
        return this.rid;
    }

    public abstract RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException, IOException;
}
