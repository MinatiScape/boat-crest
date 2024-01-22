package org.bouncycastle.cms.jcajce;

import java.security.PrivateKey;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceKTSKeyUnwrapper;
/* loaded from: classes12.dex */
public interface c extends JcaJceHelper {
    JceKTSKeyUnwrapper a(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey, byte[] bArr, byte[] bArr2);

    JceAsymmetricKeyUnwrapper b(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey);

    SymmetricKeyUnwrapper c(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey);
}
