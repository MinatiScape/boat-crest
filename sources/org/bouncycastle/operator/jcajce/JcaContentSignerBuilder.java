package org.bouncycastle.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OperatorStreamException;
import org.bouncycastle.operator.RuntimeOperatorException;
/* loaded from: classes13.dex */
public class JcaContentSignerBuilder {

    /* renamed from: a  reason: collision with root package name */
    public org.bouncycastle.operator.jcajce.a f15240a = new org.bouncycastle.operator.jcajce.a(new DefaultJcaJceHelper());
    public SecureRandom b;
    public AlgorithmIdentifier c;

    /* loaded from: classes13.dex */
    public class a implements ContentSigner {

        /* renamed from: a  reason: collision with root package name */
        public b f15241a;
        public final /* synthetic */ Signature b;
        public final /* synthetic */ AlgorithmIdentifier c;

        public a(Signature signature, AlgorithmIdentifier algorithmIdentifier) {
            this.b = signature;
            this.c = algorithmIdentifier;
            this.f15241a = new b(JcaContentSignerBuilder.this, signature);
        }

        @Override // org.bouncycastle.operator.ContentSigner
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.c;
        }

        @Override // org.bouncycastle.operator.ContentSigner
        public OutputStream getOutputStream() {
            return this.f15241a;
        }

        @Override // org.bouncycastle.operator.ContentSigner
        public byte[] getSignature() {
            try {
                return this.f15241a.a();
            } catch (SignatureException e) {
                throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class b extends OutputStream {
        public Signature h;

        public b(JcaContentSignerBuilder jcaContentSignerBuilder, Signature signature) {
            this.h = signature;
        }

        public byte[] a() throws SignatureException {
            return this.h.sign();
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            try {
                this.h.update((byte) i);
            } catch (SignatureException e) {
                throw new OperatorStreamException("exception in content signer: " + e.getMessage(), e);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            try {
                this.h.update(bArr);
            } catch (SignatureException e) {
                throw new OperatorStreamException("exception in content signer: " + e.getMessage(), e);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                this.h.update(bArr, i, i2);
            } catch (SignatureException e) {
                throw new OperatorStreamException("exception in content signer: " + e.getMessage(), e);
            }
        }
    }

    public JcaContentSignerBuilder(String str) {
        this.c = new DefaultSignatureAlgorithmIdentifierFinder().find(str);
    }

    public ContentSigner build(PrivateKey privateKey) throws OperatorCreationException {
        try {
            Signature g = this.f15240a.g(this.c);
            AlgorithmIdentifier algorithmIdentifier = this.c;
            SecureRandom secureRandom = this.b;
            if (secureRandom != null) {
                g.initSign(privateKey, secureRandom);
            } else {
                g.initSign(privateKey);
            }
            return new a(g, algorithmIdentifier);
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException("cannot create signer: " + e.getMessage(), e);
        }
    }

    public JcaContentSignerBuilder setProvider(String str) {
        this.f15240a = new org.bouncycastle.operator.jcajce.a(new NamedJcaJceHelper(str));
        return this;
    }

    public JcaContentSignerBuilder setProvider(Provider provider) {
        this.f15240a = new org.bouncycastle.operator.jcajce.a(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcaContentSignerBuilder setSecureRandom(SecureRandom secureRandom) {
        this.b = secureRandom;
        return this;
    }
}
