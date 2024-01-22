package org.bouncycastle.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OperatorStreamException;
import org.bouncycastle.operator.RawContentVerifier;
import org.bouncycastle.operator.RuntimeOperatorException;
/* loaded from: classes13.dex */
public class JcaContentVerifierProviderBuilder {

    /* renamed from: a  reason: collision with root package name */
    public org.bouncycastle.operator.jcajce.a f15242a = new org.bouncycastle.operator.jcajce.a(new DefaultJcaJceHelper());

    /* loaded from: classes13.dex */
    public class a implements ContentVerifierProvider {

        /* renamed from: a  reason: collision with root package name */
        public e f15243a;
        public final /* synthetic */ X509CertificateHolder b;
        public final /* synthetic */ X509Certificate c;

        public a(X509CertificateHolder x509CertificateHolder, X509Certificate x509Certificate) {
            this.b = x509CertificateHolder;
            this.c = x509Certificate;
        }

        @Override // org.bouncycastle.operator.ContentVerifierProvider
        public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
            try {
                Signature g = JcaContentVerifierProviderBuilder.this.f15242a.g(algorithmIdentifier);
                g.initVerify(this.c.getPublicKey());
                this.f15243a = new e(JcaContentVerifierProviderBuilder.this, g);
                Signature d = JcaContentVerifierProviderBuilder.this.d(algorithmIdentifier, this.c.getPublicKey());
                return d != null ? new c(JcaContentVerifierProviderBuilder.this, algorithmIdentifier, this.f15243a, d) : new d(JcaContentVerifierProviderBuilder.this, algorithmIdentifier, this.f15243a);
            } catch (GeneralSecurityException e) {
                throw new OperatorCreationException("exception on setup: " + e, e);
            }
        }

        @Override // org.bouncycastle.operator.ContentVerifierProvider
        public X509CertificateHolder getAssociatedCertificate() {
            return this.b;
        }

        @Override // org.bouncycastle.operator.ContentVerifierProvider
        public boolean hasAssociatedCertificate() {
            return true;
        }
    }

    /* loaded from: classes13.dex */
    public class b implements ContentVerifierProvider {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ PublicKey f15244a;

        public b(PublicKey publicKey) {
            this.f15244a = publicKey;
        }

        @Override // org.bouncycastle.operator.ContentVerifierProvider
        public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
            e e = JcaContentVerifierProviderBuilder.this.e(algorithmIdentifier, this.f15244a);
            Signature d = JcaContentVerifierProviderBuilder.this.d(algorithmIdentifier, this.f15244a);
            return d != null ? new c(JcaContentVerifierProviderBuilder.this, algorithmIdentifier, e, d) : new d(JcaContentVerifierProviderBuilder.this, algorithmIdentifier, e);
        }

        @Override // org.bouncycastle.operator.ContentVerifierProvider
        public X509CertificateHolder getAssociatedCertificate() {
            return null;
        }

        @Override // org.bouncycastle.operator.ContentVerifierProvider
        public boolean hasAssociatedCertificate() {
            return false;
        }
    }

    /* loaded from: classes13.dex */
    public class c extends d implements RawContentVerifier {
        public Signature c;

        public c(JcaContentVerifierProviderBuilder jcaContentVerifierProviderBuilder, AlgorithmIdentifier algorithmIdentifier, e eVar, Signature signature) {
            super(jcaContentVerifierProviderBuilder, algorithmIdentifier, eVar);
            this.c = signature;
        }

        @Override // org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder.d, org.bouncycastle.operator.ContentVerifier
        public boolean verify(byte[] bArr) {
            try {
                return super.verify(bArr);
            } finally {
                try {
                    this.c.verify(bArr);
                } catch (Exception unused) {
                }
            }
        }

        @Override // org.bouncycastle.operator.RawContentVerifier
        public boolean verify(byte[] bArr, byte[] bArr2) {
            try {
                try {
                    this.c.update(bArr);
                    boolean verify = this.c.verify(bArr2);
                    try {
                        this.b.a(bArr2);
                    } catch (Exception unused) {
                    }
                    return verify;
                } catch (Throwable th) {
                    try {
                        this.b.a(bArr2);
                    } catch (Exception unused2) {
                    }
                    throw th;
                }
            } catch (SignatureException e) {
                throw new RuntimeOperatorException("exception obtaining raw signature: " + e.getMessage(), e);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class d implements ContentVerifier {

        /* renamed from: a  reason: collision with root package name */
        public AlgorithmIdentifier f15245a;
        public e b;

        public d(JcaContentVerifierProviderBuilder jcaContentVerifierProviderBuilder, AlgorithmIdentifier algorithmIdentifier, e eVar) {
            this.f15245a = algorithmIdentifier;
            this.b = eVar;
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f15245a;
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public OutputStream getOutputStream() {
            e eVar = this.b;
            if (eVar != null) {
                return eVar;
            }
            throw new IllegalStateException("verifier not initialised");
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public boolean verify(byte[] bArr) {
            try {
                return this.b.a(bArr);
            } catch (SignatureException e) {
                throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class e extends OutputStream {
        public Signature h;

        public e(JcaContentVerifierProviderBuilder jcaContentVerifierProviderBuilder, Signature signature) {
            this.h = signature;
        }

        public boolean a(byte[] bArr) throws SignatureException {
            return this.h.verify(bArr);
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

    public ContentVerifierProvider build(PublicKey publicKey) throws OperatorCreationException {
        return new b(publicKey);
    }

    public ContentVerifierProvider build(X509Certificate x509Certificate) throws OperatorCreationException {
        try {
            return new a(new JcaX509CertificateHolder(x509Certificate), x509Certificate);
        } catch (CertificateEncodingException e2) {
            throw new OperatorCreationException("cannot process certificate: " + e2.getMessage(), e2);
        }
    }

    public ContentVerifierProvider build(SubjectPublicKeyInfo subjectPublicKeyInfo) throws OperatorCreationException {
        return build(this.f15242a.b(subjectPublicKeyInfo));
    }

    public ContentVerifierProvider build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
        return build(this.f15242a.a(x509CertificateHolder));
    }

    public final Signature d(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        try {
            Signature f = this.f15242a.f(algorithmIdentifier);
            if (f != null) {
                f.initVerify(publicKey);
                return f;
            }
            return f;
        } catch (Exception unused) {
            return null;
        }
    }

    public final e e(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) throws OperatorCreationException {
        try {
            Signature g = this.f15242a.g(algorithmIdentifier);
            g.initVerify(publicKey);
            return new e(this, g);
        } catch (GeneralSecurityException e2) {
            throw new OperatorCreationException("exception on setup: " + e2, e2);
        }
    }

    public JcaContentVerifierProviderBuilder setProvider(String str) {
        this.f15242a = new org.bouncycastle.operator.jcajce.a(new NamedJcaJceHelper(str));
        return this;
    }

    public JcaContentVerifierProviderBuilder setProvider(Provider provider) {
        this.f15242a = new org.bouncycastle.operator.jcajce.a(new ProviderJcaJceHelper(provider));
        return this;
    }
}
