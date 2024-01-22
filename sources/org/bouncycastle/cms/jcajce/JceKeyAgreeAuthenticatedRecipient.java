package org.bouncycastle.cms.jcajce;

import java.io.OutputStream;
import java.security.Key;
import java.security.PrivateKey;
import javax.crypto.Mac;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.jcajce.io.MacOutputStream;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.jcajce.JceGenericKey;
/* loaded from: classes12.dex */
public class JceKeyAgreeAuthenticatedRecipient extends JceKeyAgreeRecipient {

    /* loaded from: classes12.dex */
    public class a implements MacCalculator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f14591a;
        public final /* synthetic */ Key b;
        public final /* synthetic */ Mac c;

        public a(JceKeyAgreeAuthenticatedRecipient jceKeyAgreeAuthenticatedRecipient, AlgorithmIdentifier algorithmIdentifier, Key key, Mac mac) {
            this.f14591a = algorithmIdentifier;
            this.b = key;
            this.c = mac;
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f14591a;
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public GenericKey getKey() {
            return new JceGenericKey(this.f14591a, this.b);
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public byte[] getMac() {
            return this.c.doFinal();
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public OutputStream getOutputStream() {
            return new MacOutputStream(this.c);
        }
    }

    public JceKeyAgreeAuthenticatedRecipient(PrivateKey privateKey) {
        super(privateKey);
    }

    @Override // org.bouncycastle.cms.KeyAgreeRecipient
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1OctetString aSN1OctetString, byte[] bArr) throws CMSException {
        Key extractSecretKey = extractSecretKey(algorithmIdentifier, algorithmIdentifier2, subjectPublicKeyInfo, aSN1OctetString, bArr);
        return new RecipientOperator(new a(this, algorithmIdentifier2, extractSecretKey, this.contentHelper.e(extractSecretKey, algorithmIdentifier2)));
    }
}
