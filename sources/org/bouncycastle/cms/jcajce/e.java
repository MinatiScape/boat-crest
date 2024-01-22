package org.bouncycastle.cms.jcajce;

import java.security.PrivateKey;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceKTSKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceSymmetricKeyUnwrapper;
/* loaded from: classes12.dex */
public class e extends NamedJcaJceHelper implements c {
    public e(String str) {
        super(str);
    }

    @Override // org.bouncycastle.cms.jcajce.c
    public JceKTSKeyUnwrapper a(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey, byte[] bArr, byte[] bArr2) {
        return new JceKTSKeyUnwrapper(algorithmIdentifier, privateKey, bArr, bArr2).setProvider(this.providerName);
    }

    @Override // org.bouncycastle.cms.jcajce.c
    public JceAsymmetricKeyUnwrapper b(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        return new JceAsymmetricKeyUnwrapper(algorithmIdentifier, privateKey).setProvider(this.providerName);
    }

    @Override // org.bouncycastle.cms.jcajce.c
    public SymmetricKeyUnwrapper c(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey) {
        return new JceSymmetricKeyUnwrapper(algorithmIdentifier, secretKey).setProvider(this.providerName);
    }
}
