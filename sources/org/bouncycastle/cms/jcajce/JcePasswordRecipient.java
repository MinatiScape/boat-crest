package org.bouncycastle.cms.jcajce;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.PasswordRecipient;
/* loaded from: classes12.dex */
public abstract class JcePasswordRecipient implements PasswordRecipient {
    public char[] b;

    /* renamed from: a  reason: collision with root package name */
    public int f14599a = 1;
    public EnvelopedDataHelper helper = new EnvelopedDataHelper(new b());

    public JcePasswordRecipient(char[] cArr) {
        this.b = cArr;
    }

    @Override // org.bouncycastle.cms.PasswordRecipient
    public byte[] calculateDerivedKey(int i, AlgorithmIdentifier algorithmIdentifier, int i2) throws CMSException {
        return this.helper.a(i, this.b, algorithmIdentifier, i2);
    }

    public Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr, byte[] bArr2) throws CMSException {
        Cipher i = this.helper.i(algorithmIdentifier.getAlgorithm());
        try {
            i.init(4, new SecretKeySpec(bArr, i.getAlgorithm()), new IvParameterSpec(ASN1OctetString.getInstance(algorithmIdentifier.getParameters()).getOctets()));
            return i.unwrap(bArr2, algorithmIdentifier2.getAlgorithm().getId(), 3);
        } catch (GeneralSecurityException e) {
            throw new CMSException("cannot process content encryption key: " + e.getMessage(), e);
        }
    }

    @Override // org.bouncycastle.cms.PasswordRecipient
    public char[] getPassword() {
        return this.b;
    }

    @Override // org.bouncycastle.cms.PasswordRecipient
    public int getPasswordConversionScheme() {
        return this.f14599a;
    }

    public JcePasswordRecipient setPasswordConversionScheme(int i) {
        this.f14599a = i;
        return this;
    }

    public JcePasswordRecipient setProvider(String str) {
        this.helper = new EnvelopedDataHelper(new e(str));
        return this;
    }

    public JcePasswordRecipient setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new f(provider));
        return this;
    }
}
