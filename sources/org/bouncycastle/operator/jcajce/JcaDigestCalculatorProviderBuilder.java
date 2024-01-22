package org.bouncycastle.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Provider;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes13.dex */
public class JcaDigestCalculatorProviderBuilder {

    /* renamed from: a  reason: collision with root package name */
    public org.bouncycastle.operator.jcajce.a f15246a = new org.bouncycastle.operator.jcajce.a(new DefaultJcaJceHelper());

    /* loaded from: classes13.dex */
    public class a implements DigestCalculatorProvider {

        /* renamed from: org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0909a implements DigestCalculator {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ AlgorithmIdentifier f15248a;
            public final /* synthetic */ b b;

            public C0909a(a aVar, AlgorithmIdentifier algorithmIdentifier, b bVar) {
                this.f15248a = algorithmIdentifier;
                this.b = bVar;
            }

            @Override // org.bouncycastle.operator.DigestCalculator
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return this.f15248a;
            }

            @Override // org.bouncycastle.operator.DigestCalculator
            public byte[] getDigest() {
                return this.b.a();
            }

            @Override // org.bouncycastle.operator.DigestCalculator
            public OutputStream getOutputStream() {
                return this.b;
            }
        }

        public a() {
        }

        @Override // org.bouncycastle.operator.DigestCalculatorProvider
        public DigestCalculator get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
            try {
                return new C0909a(this, algorithmIdentifier, new b(JcaDigestCalculatorProviderBuilder.this, JcaDigestCalculatorProviderBuilder.this.f15246a.e(algorithmIdentifier)));
            } catch (GeneralSecurityException e) {
                throw new OperatorCreationException("exception on setup: " + e, e);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class b extends OutputStream {
        public MessageDigest h;

        public b(JcaDigestCalculatorProviderBuilder jcaDigestCalculatorProviderBuilder, MessageDigest messageDigest) {
            this.h = messageDigest;
        }

        public byte[] a() {
            return this.h.digest();
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.h.update((byte) i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.h.update(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.h.update(bArr, i, i2);
        }
    }

    public DigestCalculatorProvider build() throws OperatorCreationException {
        return new a();
    }

    public JcaDigestCalculatorProviderBuilder setProvider(String str) {
        this.f15246a = new org.bouncycastle.operator.jcajce.a(new NamedJcaJceHelper(str));
        return this;
    }

    public JcaDigestCalculatorProviderBuilder setProvider(Provider provider) {
        this.f15246a = new org.bouncycastle.operator.jcajce.a(new ProviderJcaJceHelper(provider));
        return this;
    }
}
