package org.bouncycastle.cms.bc;

import java.io.InputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.operator.InputDecryptor;
/* loaded from: classes12.dex */
public class BcPasswordEnvelopedRecipient extends BcPasswordRecipient {

    /* loaded from: classes12.dex */
    public class a implements InputDecryptor {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f14559a;
        public final /* synthetic */ Object b;

        public a(BcPasswordEnvelopedRecipient bcPasswordEnvelopedRecipient, AlgorithmIdentifier algorithmIdentifier, Object obj) {
            this.f14559a = algorithmIdentifier;
            this.b = obj;
        }

        @Override // org.bouncycastle.operator.InputDecryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f14559a;
        }

        @Override // org.bouncycastle.operator.InputDecryptor
        public InputStream getInputStream(InputStream inputStream) {
            return this.b instanceof BufferedBlockCipher ? new CipherInputStream(inputStream, (BufferedBlockCipher) this.b) : new CipherInputStream(inputStream, (StreamCipher) this.b);
        }
    }

    public BcPasswordEnvelopedRecipient(char[] cArr) {
        super(cArr);
    }

    @Override // org.bouncycastle.cms.PasswordRecipient
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr, byte[] bArr2) throws CMSException {
        return new RecipientOperator(new a(this, algorithmIdentifier2, b.c(false, extractSecretKey(algorithmIdentifier, algorithmIdentifier2, bArr, bArr2), algorithmIdentifier2)));
    }
}
