package org.bouncycastle.openssl.jcajce;

import java.security.Provider;
import java.security.SecureRandom;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openssl.PEMEncryptor;
import org.bouncycastle.openssl.PEMException;
/* loaded from: classes13.dex */
public class JcePEMEncryptorBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final String f15216a;
    public JcaJceHelper b = new DefaultJcaJceHelper();
    public SecureRandom c;

    /* loaded from: classes13.dex */
    public class a implements PEMEncryptor {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ byte[] f15217a;
        public final /* synthetic */ char[] b;

        public a(byte[] bArr, char[] cArr) {
            this.f15217a = bArr;
            this.b = cArr;
        }

        @Override // org.bouncycastle.openssl.PEMEncryptor
        public byte[] encrypt(byte[] bArr) throws PEMException {
            return org.bouncycastle.openssl.jcajce.a.a(true, JcePEMEncryptorBuilder.this.b, bArr, this.b, JcePEMEncryptorBuilder.this.f15216a, this.f15217a);
        }

        @Override // org.bouncycastle.openssl.PEMEncryptor
        public String getAlgorithm() {
            return JcePEMEncryptorBuilder.this.f15216a;
        }

        @Override // org.bouncycastle.openssl.PEMEncryptor
        public byte[] getIV() {
            return this.f15217a;
        }
    }

    public JcePEMEncryptorBuilder(String str) {
        this.f15216a = str;
    }

    public PEMEncryptor build(char[] cArr) {
        if (this.c == null) {
            this.c = new SecureRandom();
        }
        byte[] bArr = new byte[this.f15216a.startsWith("AES-") ? 16 : 8];
        this.c.nextBytes(bArr);
        return new a(bArr, cArr);
    }

    public JcePEMEncryptorBuilder setProvider(String str) {
        this.b = new NamedJcaJceHelper(str);
        return this;
    }

    public JcePEMEncryptorBuilder setProvider(Provider provider) {
        this.b = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePEMEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.c = secureRandom;
        return this;
    }
}
