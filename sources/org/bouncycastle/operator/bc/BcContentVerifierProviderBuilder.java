package org.bouncycastle.operator.bc;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes13.dex */
public abstract class BcContentVerifierProviderBuilder {
    public BcDigestProvider digestProvider = BcDefaultDigestProvider.INSTANCE;

    /* loaded from: classes13.dex */
    public class a implements ContentVerifierProvider {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ X509CertificateHolder f15231a;

        public a(X509CertificateHolder x509CertificateHolder) {
            this.f15231a = x509CertificateHolder;
        }

        @Override // org.bouncycastle.operator.ContentVerifierProvider
        public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
            try {
                return new c(BcContentVerifierProviderBuilder.this, algorithmIdentifier, BcContentVerifierProviderBuilder.this.b(algorithmIdentifier, BcContentVerifierProviderBuilder.this.extractKeyParameters(this.f15231a.getSubjectPublicKeyInfo())));
            } catch (IOException e) {
                throw new OperatorCreationException("exception on setup: " + e, e);
            }
        }

        @Override // org.bouncycastle.operator.ContentVerifierProvider
        public X509CertificateHolder getAssociatedCertificate() {
            return this.f15231a;
        }

        @Override // org.bouncycastle.operator.ContentVerifierProvider
        public boolean hasAssociatedCertificate() {
            return true;
        }
    }

    /* loaded from: classes13.dex */
    public class b implements ContentVerifierProvider {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AsymmetricKeyParameter f15232a;

        public b(AsymmetricKeyParameter asymmetricKeyParameter) {
            this.f15232a = asymmetricKeyParameter;
        }

        @Override // org.bouncycastle.operator.ContentVerifierProvider
        public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
            return new c(BcContentVerifierProviderBuilder.this, algorithmIdentifier, BcContentVerifierProviderBuilder.this.b(algorithmIdentifier, this.f15232a));
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
    public class c implements ContentVerifier {

        /* renamed from: a  reason: collision with root package name */
        public BcSignerOutputStream f15233a;
        public AlgorithmIdentifier b;

        public c(BcContentVerifierProviderBuilder bcContentVerifierProviderBuilder, AlgorithmIdentifier algorithmIdentifier, BcSignerOutputStream bcSignerOutputStream) {
            this.b = algorithmIdentifier;
            this.f15233a = bcSignerOutputStream;
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.b;
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public OutputStream getOutputStream() {
            BcSignerOutputStream bcSignerOutputStream = this.f15233a;
            if (bcSignerOutputStream != null) {
                return bcSignerOutputStream;
            }
            throw new IllegalStateException("verifier not initialised");
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public boolean verify(byte[] bArr) {
            return this.f15233a.b(bArr);
        }
    }

    public final BcSignerOutputStream b(AlgorithmIdentifier algorithmIdentifier, AsymmetricKeyParameter asymmetricKeyParameter) throws OperatorCreationException {
        Signer createSigner = createSigner(algorithmIdentifier);
        createSigner.init(false, asymmetricKeyParameter);
        return new BcSignerOutputStream(createSigner);
    }

    public ContentVerifierProvider build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException {
        return new a(x509CertificateHolder);
    }

    public ContentVerifierProvider build(AsymmetricKeyParameter asymmetricKeyParameter) throws OperatorCreationException {
        return new b(asymmetricKeyParameter);
    }

    public abstract Signer createSigner(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException;

    public abstract AsymmetricKeyParameter extractKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException;
}
