package org.bouncycastle.operator.bc;

import java.io.OutputStream;
import java.security.SecureRandom;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.RuntimeOperatorException;
/* loaded from: classes13.dex */
public abstract class BcContentSignerBuilder {

    /* renamed from: a  reason: collision with root package name */
    public SecureRandom f15229a;
    public AlgorithmIdentifier b;
    public AlgorithmIdentifier c;
    public BcDigestProvider digestProvider = BcDefaultDigestProvider.INSTANCE;

    /* loaded from: classes13.dex */
    public class a implements ContentSigner {

        /* renamed from: a  reason: collision with root package name */
        public BcSignerOutputStream f15230a;
        public final /* synthetic */ Signer b;

        public a(Signer signer) {
            this.b = signer;
            this.f15230a = new BcSignerOutputStream(signer);
        }

        @Override // org.bouncycastle.operator.ContentSigner
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return BcContentSignerBuilder.this.b;
        }

        @Override // org.bouncycastle.operator.ContentSigner
        public OutputStream getOutputStream() {
            return this.f15230a;
        }

        @Override // org.bouncycastle.operator.ContentSigner
        public byte[] getSignature() {
            try {
                return this.f15230a.a();
            } catch (CryptoException e) {
                throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
            }
        }
    }

    public BcContentSignerBuilder(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        this.b = algorithmIdentifier;
        this.c = algorithmIdentifier2;
    }

    public ContentSigner build(AsymmetricKeyParameter asymmetricKeyParameter) throws OperatorCreationException {
        Signer createSigner = createSigner(this.b, this.c);
        SecureRandom secureRandom = this.f15229a;
        if (secureRandom != null) {
            createSigner.init(true, new ParametersWithRandom(asymmetricKeyParameter, secureRandom));
        } else {
            createSigner.init(true, asymmetricKeyParameter);
        }
        return new a(createSigner);
    }

    public abstract Signer createSigner(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) throws OperatorCreationException;

    public BcContentSignerBuilder setSecureRandom(SecureRandom secureRandom) {
        this.f15229a = secureRandom;
        return this;
    }
}
