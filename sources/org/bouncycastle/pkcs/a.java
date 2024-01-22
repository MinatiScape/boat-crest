package org.bouncycastle.pkcs;

import java.io.OutputStream;
import org.bouncycastle.asn1.pkcs.MacData;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.operator.MacCalculator;
/* loaded from: classes13.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public PKCS12MacCalculatorBuilder f15262a;

    public a(PKCS12MacCalculatorBuilder pKCS12MacCalculatorBuilder) {
        this.f15262a = pKCS12MacCalculatorBuilder;
    }

    public MacData a(char[] cArr, byte[] bArr) throws PKCSException {
        try {
            MacCalculator build = this.f15262a.build(cArr);
            OutputStream outputStream = build.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
            AlgorithmIdentifier algorithmIdentifier = build.getAlgorithmIdentifier();
            DigestInfo digestInfo = new DigestInfo(this.f15262a.getDigestAlgorithmIdentifier(), build.getMac());
            PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
            return new MacData(digestInfo, pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
        } catch (Exception e) {
            throw new PKCSException("unable to process data: " + e.getMessage(), e);
        }
    }
}
