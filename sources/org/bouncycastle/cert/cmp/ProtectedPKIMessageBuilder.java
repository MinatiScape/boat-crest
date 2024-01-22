package org.bouncycastle.cert.cmp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmp.CMPCertificate;
import org.bouncycastle.asn1.cmp.InfoTypeAndValue;
import org.bouncycastle.asn1.cmp.PKIBody;
import org.bouncycastle.asn1.cmp.PKIFreeText;
import org.bouncycastle.asn1.cmp.PKIHeader;
import org.bouncycastle.asn1.cmp.PKIHeaderBuilder;
import org.bouncycastle.asn1.cmp.PKIMessage;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.MacCalculator;
/* loaded from: classes12.dex */
public class ProtectedPKIMessageBuilder {

    /* renamed from: a  reason: collision with root package name */
    public PKIHeaderBuilder f14454a;
    public PKIBody b;
    public List c;
    public List d;

    public ProtectedPKIMessageBuilder(int i, GeneralName generalName, GeneralName generalName2) {
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.f14454a = new PKIHeaderBuilder(i, generalName, generalName2);
    }

    public ProtectedPKIMessageBuilder(GeneralName generalName, GeneralName generalName2) {
        this(2, generalName, generalName2);
    }

    public final byte[] a(MacCalculator macCalculator, PKIHeader pKIHeader, PKIBody pKIBody) throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(pKIHeader);
        aSN1EncodableVector.add(pKIBody);
        OutputStream outputStream = macCalculator.getOutputStream();
        outputStream.write(new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER));
        outputStream.close();
        return macCalculator.getMac();
    }

    public ProtectedPKIMessageBuilder addCMPCertificate(X509CertificateHolder x509CertificateHolder) {
        this.d.add(x509CertificateHolder);
        return this;
    }

    public ProtectedPKIMessageBuilder addGeneralInfo(InfoTypeAndValue infoTypeAndValue) {
        this.c.add(infoTypeAndValue);
        return this;
    }

    public final byte[] b(ContentSigner contentSigner, PKIHeader pKIHeader, PKIBody pKIBody) throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(pKIHeader);
        aSN1EncodableVector.add(pKIBody);
        OutputStream outputStream = contentSigner.getOutputStream();
        outputStream.write(new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER));
        outputStream.close();
        return contentSigner.getSignature();
    }

    public ProtectedPKIMessage build(ContentSigner contentSigner) throws CMPException {
        c(contentSigner.getAlgorithmIdentifier());
        PKIHeader build = this.f14454a.build();
        try {
            return d(build, new DERBitString(b(contentSigner, build, this.b)));
        } catch (IOException e) {
            throw new CMPException("unable to encode signature input: " + e.getMessage(), e);
        }
    }

    public ProtectedPKIMessage build(MacCalculator macCalculator) throws CMPException {
        c(macCalculator.getAlgorithmIdentifier());
        PKIHeader build = this.f14454a.build();
        try {
            return d(build, new DERBitString(a(macCalculator, build, this.b)));
        } catch (IOException e) {
            throw new CMPException("unable to encode MAC input: " + e.getMessage(), e);
        }
    }

    public final void c(AlgorithmIdentifier algorithmIdentifier) {
        this.f14454a.setProtectionAlg(algorithmIdentifier);
        if (this.c.isEmpty()) {
            return;
        }
        this.f14454a.setGeneralInfo((InfoTypeAndValue[]) this.c.toArray(new InfoTypeAndValue[this.c.size()]));
    }

    public final ProtectedPKIMessage d(PKIHeader pKIHeader, DERBitString dERBitString) {
        if (this.d.isEmpty()) {
            return new ProtectedPKIMessage(new PKIMessage(pKIHeader, this.b, dERBitString));
        }
        int size = this.d.size();
        CMPCertificate[] cMPCertificateArr = new CMPCertificate[size];
        for (int i = 0; i != size; i++) {
            cMPCertificateArr[i] = new CMPCertificate(((X509CertificateHolder) this.d.get(i)).toASN1Structure());
        }
        return new ProtectedPKIMessage(new PKIMessage(pKIHeader, this.b, dERBitString, cMPCertificateArr));
    }

    public ProtectedPKIMessageBuilder setBody(PKIBody pKIBody) {
        this.b = pKIBody;
        return this;
    }

    public ProtectedPKIMessageBuilder setFreeText(PKIFreeText pKIFreeText) {
        this.f14454a.setFreeText(pKIFreeText);
        return this;
    }

    public ProtectedPKIMessageBuilder setMessageTime(Date date) {
        this.f14454a.setMessageTime(new ASN1GeneralizedTime(date));
        return this;
    }

    public ProtectedPKIMessageBuilder setRecipKID(byte[] bArr) {
        this.f14454a.setRecipKID(bArr);
        return this;
    }

    public ProtectedPKIMessageBuilder setRecipNonce(byte[] bArr) {
        this.f14454a.setRecipNonce(bArr);
        return this;
    }

    public ProtectedPKIMessageBuilder setSenderKID(byte[] bArr) {
        this.f14454a.setSenderKID(bArr);
        return this;
    }

    public ProtectedPKIMessageBuilder setSenderNonce(byte[] bArr) {
        this.f14454a.setSenderNonce(bArr);
        return this;
    }

    public ProtectedPKIMessageBuilder setTransactionID(byte[] bArr) {
        this.f14454a.setTransactionID(bArr);
        return this;
    }
}
