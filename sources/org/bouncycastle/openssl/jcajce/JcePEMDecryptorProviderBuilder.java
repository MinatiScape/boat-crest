package org.bouncycastle.openssl.jcajce;

import java.security.Provider;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openssl.PEMDecryptor;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PasswordException;
/* loaded from: classes13.dex */
public class JcePEMDecryptorProviderBuilder {

    /* renamed from: a  reason: collision with root package name */
    public JcaJceHelper f15213a = new DefaultJcaJceHelper();

    /* loaded from: classes13.dex */
    public class a implements PEMDecryptorProvider {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ char[] f15214a;

        /* renamed from: org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0908a implements PEMDecryptor {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ String f15215a;

            public C0908a(String str) {
                this.f15215a = str;
            }

            @Override // org.bouncycastle.openssl.PEMDecryptor
            public byte[] decrypt(byte[] bArr, byte[] bArr2) throws PEMException {
                a aVar = a.this;
                if (aVar.f15214a != null) {
                    return org.bouncycastle.openssl.jcajce.a.a(false, JcePEMDecryptorProviderBuilder.this.f15213a, bArr, a.this.f15214a, this.f15215a, bArr2);
                }
                throw new PasswordException("Password is null, but a password is required");
            }
        }

        public a(char[] cArr) {
            this.f15214a = cArr;
        }

        @Override // org.bouncycastle.openssl.PEMDecryptorProvider
        public PEMDecryptor get(String str) {
            return new C0908a(str);
        }
    }

    public PEMDecryptorProvider build(char[] cArr) {
        return new a(cArr);
    }

    public JcePEMDecryptorProviderBuilder setProvider(String str) {
        this.f15213a = new NamedJcaJceHelper(str);
        return this;
    }

    public JcePEMDecryptorProviderBuilder setProvider(Provider provider) {
        this.f15213a = new ProviderJcaJceHelper(provider);
        return this;
    }
}
