package org.bouncycastle.cms.jcajce;

import java.io.InputStream;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.jcajce.io.CipherInputStream;
import org.bouncycastle.operator.InputDecryptor;
/* loaded from: classes12.dex */
public class JceKeyTransEnvelopedRecipient extends JceKeyTransRecipient {

    /* loaded from: classes12.dex */
    public class a implements InputDecryptor {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f14595a;
        public final /* synthetic */ Cipher b;

        public a(JceKeyTransEnvelopedRecipient jceKeyTransEnvelopedRecipient, AlgorithmIdentifier algorithmIdentifier, Cipher cipher) {
            this.f14595a = algorithmIdentifier;
            this.b = cipher;
        }

        @Override // org.bouncycastle.operator.InputDecryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f14595a;
        }

        @Override // org.bouncycastle.operator.InputDecryptor
        public InputStream getInputStream(InputStream inputStream) {
            return new CipherInputStream(inputStream, this.b);
        }
    }

    public JceKeyTransEnvelopedRecipient(PrivateKey privateKey) {
        super(privateKey);
    }

    @Override // org.bouncycastle.cms.KeyTransRecipient
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CMSException {
        return new RecipientOperator(new a(this, algorithmIdentifier2, this.contentHelper.createContentCipher(extractSecretKey(algorithmIdentifier, algorithmIdentifier2, bArr), algorithmIdentifier2)));
    }
}
