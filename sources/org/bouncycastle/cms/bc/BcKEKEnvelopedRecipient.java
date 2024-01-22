package org.bouncycastle.cms.bc;

import java.io.InputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.bc.BcSymmetricKeyUnwrapper;
/* loaded from: classes12.dex */
public class BcKEKEnvelopedRecipient extends BcKEKRecipient {

    /* loaded from: classes12.dex */
    public class a implements InputDecryptor {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f14556a;
        public final /* synthetic */ Object b;

        public a(BcKEKEnvelopedRecipient bcKEKEnvelopedRecipient, AlgorithmIdentifier algorithmIdentifier, Object obj) {
            this.f14556a = algorithmIdentifier;
            this.b = obj;
        }

        @Override // org.bouncycastle.operator.InputDecryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f14556a;
        }

        @Override // org.bouncycastle.operator.InputDecryptor
        public InputStream getInputStream(InputStream inputStream) {
            return this.b instanceof BufferedBlockCipher ? new CipherInputStream(inputStream, (BufferedBlockCipher) this.b) : new CipherInputStream(inputStream, (StreamCipher) this.b);
        }
    }

    public BcKEKEnvelopedRecipient(BcSymmetricKeyUnwrapper bcSymmetricKeyUnwrapper) {
        super(bcSymmetricKeyUnwrapper);
    }

    @Override // org.bouncycastle.cms.KEKRecipient
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CMSException {
        return new RecipientOperator(new a(this, algorithmIdentifier2, b.c(false, (KeyParameter) extractSecretKey(algorithmIdentifier, algorithmIdentifier2, bArr), algorithmIdentifier2)));
    }
}
