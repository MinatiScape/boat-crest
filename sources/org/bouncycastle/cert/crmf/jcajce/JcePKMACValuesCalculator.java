package org.bouncycastle.cert.crmf.jcajce;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Provider;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.PKMACValuesCalculator;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
/* loaded from: classes12.dex */
public class JcePKMACValuesCalculator implements PKMACValuesCalculator {

    /* renamed from: a  reason: collision with root package name */
    public MessageDigest f14474a;
    public Mac b;
    public a c = new a(new DefaultJcaJceHelper());

    @Override // org.bouncycastle.cert.crmf.PKMACValuesCalculator
    public byte[] calculateDigest(byte[] bArr) {
        return this.f14474a.digest(bArr);
    }

    @Override // org.bouncycastle.cert.crmf.PKMACValuesCalculator
    public byte[] calculateMac(byte[] bArr, byte[] bArr2) throws CRMFException {
        try {
            this.b.init(new SecretKeySpec(bArr, this.b.getAlgorithm()));
            return this.b.doFinal(bArr2);
        } catch (GeneralSecurityException e) {
            throw new CRMFException("failure in setup: " + e.getMessage(), e);
        }
    }

    public JcePKMACValuesCalculator setProvider(String str) {
        this.c = new a(new NamedJcaJceHelper(str));
        return this;
    }

    public JcePKMACValuesCalculator setProvider(Provider provider) {
        this.c = new a(new ProviderJcaJceHelper(provider));
        return this;
    }

    @Override // org.bouncycastle.cert.crmf.PKMACValuesCalculator
    public void setup(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) throws CRMFException {
        this.f14474a = this.c.e(algorithmIdentifier.getAlgorithm());
        this.b = this.c.h(algorithmIdentifier2.getAlgorithm());
    }
}
