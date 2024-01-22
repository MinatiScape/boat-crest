package org.bouncycastle.tsp;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.tsp.MessageImprint;
import org.bouncycastle.asn1.tsp.TimeStampReq;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
/* loaded from: classes13.dex */
public class TimeStampRequestGenerator {

    /* renamed from: a  reason: collision with root package name */
    public ASN1ObjectIdentifier f15382a;
    public ASN1Boolean b;
    public ExtensionsGenerator c = new ExtensionsGenerator();

    public void addExtension(String str, boolean z, ASN1Encodable aSN1Encodable) throws IOException {
        addExtension(str, z, aSN1Encodable.toASN1Primitive().getEncoded());
    }

    public void addExtension(String str, boolean z, byte[] bArr) {
        this.c.addExtension(new ASN1ObjectIdentifier(str), z, bArr);
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, ASN1Encodable aSN1Encodable) throws TSPIOException {
        TSPUtil.a(this.c, aSN1ObjectIdentifier, z, aSN1Encodable);
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, byte[] bArr) {
        this.c.addExtension(aSN1ObjectIdentifier, z, bArr);
    }

    public TimeStampRequest generate(String str, byte[] bArr) {
        return generate(str, bArr, (BigInteger) null);
    }

    public TimeStampRequest generate(String str, byte[] bArr, BigInteger bigInteger) {
        if (str != null) {
            MessageImprint messageImprint = new MessageImprint(new AlgorithmIdentifier(new ASN1ObjectIdentifier(str), DERNull.INSTANCE), bArr);
            Extensions generate = this.c.isEmpty() ? null : this.c.generate();
            ASN1ObjectIdentifier aSN1ObjectIdentifier = this.f15382a;
            return bigInteger != null ? new TimeStampRequest(new TimeStampReq(messageImprint, aSN1ObjectIdentifier, new ASN1Integer(bigInteger), this.b, generate)) : new TimeStampRequest(new TimeStampReq(messageImprint, aSN1ObjectIdentifier, null, this.b, generate));
        }
        throw new IllegalArgumentException("No digest algorithm specified");
    }

    public TimeStampRequest generate(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        return generate(aSN1ObjectIdentifier.getId(), bArr);
    }

    public TimeStampRequest generate(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr, BigInteger bigInteger) {
        return generate(aSN1ObjectIdentifier.getId(), bArr, bigInteger);
    }

    public void setCertReq(boolean z) {
        this.b = ASN1Boolean.getInstance(z);
    }

    public void setReqPolicy(String str) {
        this.f15382a = new ASN1ObjectIdentifier(str);
    }

    public void setReqPolicy(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.f15382a = aSN1ObjectIdentifier;
    }
}
