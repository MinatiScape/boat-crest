package org.bouncycastle.operator.bc;

import java.io.IOException;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.signers.DSADigestSigner;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes13.dex */
public class BcECContentVerifierProviderBuilder extends BcContentVerifierProviderBuilder {

    /* renamed from: a  reason: collision with root package name */
    public DigestAlgorithmIdentifierFinder f15238a;

    public BcECContentVerifierProviderBuilder(DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        this.f15238a = digestAlgorithmIdentifierFinder;
    }

    @Override // org.bouncycastle.operator.bc.BcContentVerifierProviderBuilder
    public Signer createSigner(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
        return new DSADigestSigner(new ECDSASigner(), this.digestProvider.get(this.f15238a.find(algorithmIdentifier)));
    }

    @Override // org.bouncycastle.operator.bc.BcContentVerifierProviderBuilder
    public AsymmetricKeyParameter extractKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        return PublicKeyFactory.createKey(subjectPublicKeyInfo);
    }
}