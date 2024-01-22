package org.bouncycastle.cms.jcajce;

import java.io.InputStream;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.jcajce.io.CipherInputStream;
import org.bouncycastle.operator.InputDecryptor;
/* loaded from: classes12.dex */
public class JcePasswordEnvelopedRecipient extends JcePasswordRecipient {

    /* loaded from: classes12.dex */
    public class a implements InputDecryptor {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f14598a;
        public final /* synthetic */ Cipher b;

        public a(JcePasswordEnvelopedRecipient jcePasswordEnvelopedRecipient, AlgorithmIdentifier algorithmIdentifier, Cipher cipher) {
            this.f14598a = algorithmIdentifier;
            this.b = cipher;
        }

        @Override // org.bouncycastle.operator.InputDecryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f14598a;
        }

        @Override // org.bouncycastle.operator.InputDecryptor
        public InputStream getInputStream(InputStream inputStream) {
            return new CipherInputStream(inputStream, this.b);
        }
    }

    public JcePasswordEnvelopedRecipient(char[] cArr) {
        super(cArr);
    }

    @Override // org.bouncycastle.cms.PasswordRecipient
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr, byte[] bArr2) throws CMSException {
        return new RecipientOperator(new a(this, algorithmIdentifier2, this.helper.createContentCipher(extractSecretKey(algorithmIdentifier, algorithmIdentifier2, bArr, bArr2), algorithmIdentifier2)));
    }
}
