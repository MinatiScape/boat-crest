package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.security.PrivateKey;
import javax.crypto.Mac;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyTransRecipientId;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.jcajce.io.MacOutputStream;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.jcajce.JceGenericKey;
/* loaded from: classes12.dex */
public class JceKTSKeyTransAuthenticatedRecipient extends JceKTSKeyTransRecipient {

    /* loaded from: classes12.dex */
    public class a implements MacCalculator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f14588a;
        public final /* synthetic */ Key b;
        public final /* synthetic */ Mac c;

        public a(JceKTSKeyTransAuthenticatedRecipient jceKTSKeyTransAuthenticatedRecipient, AlgorithmIdentifier algorithmIdentifier, Key key, Mac mac) {
            this.f14588a = algorithmIdentifier;
            this.b = key;
            this.c = mac;
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f14588a;
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public GenericKey getKey() {
            return new JceGenericKey(this.f14588a, this.b);
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

    public JceKTSKeyTransAuthenticatedRecipient(PrivateKey privateKey, KeyTransRecipientId keyTransRecipientId) throws IOException {
        super(privateKey, JceKTSKeyTransRecipient.getPartyVInfoFromRID(keyTransRecipientId));
    }

    @Override // org.bouncycastle.cms.KeyTransRecipient
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CMSException {
        Key extractSecretKey = extractSecretKey(algorithmIdentifier, algorithmIdentifier2, bArr);
        return new RecipientOperator(new a(this, algorithmIdentifier2, extractSecretKey, this.contentHelper.e(extractSecretKey, algorithmIdentifier2)));
    }
}
