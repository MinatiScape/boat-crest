package org.bouncycastle.crypto.params;

import java.util.Objects;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class ECDHUPrivateParameters implements CipherParameters {
    public ECPrivateKeyParameters h;
    public ECPrivateKeyParameters i;
    public ECPublicKeyParameters j;

    public ECDHUPrivateParameters(ECPrivateKeyParameters eCPrivateKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters2) {
        this(eCPrivateKeyParameters, eCPrivateKeyParameters2, null);
    }

    public ECDHUPrivateParameters(ECPrivateKeyParameters eCPrivateKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters2, ECPublicKeyParameters eCPublicKeyParameters) {
        Objects.requireNonNull(eCPrivateKeyParameters, "staticPrivateKey cannot be null");
        Objects.requireNonNull(eCPrivateKeyParameters2, "ephemeralPrivateKey cannot be null");
        ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
        if (!parameters.equals(eCPrivateKeyParameters2.getParameters())) {
            throw new IllegalArgumentException("static and ephemeral private keys have different domain parameters");
        }
        if (eCPublicKeyParameters == null) {
            eCPublicKeyParameters = new ECPublicKeyParameters(parameters.getG().multiply(eCPrivateKeyParameters2.getD()), parameters);
        } else if (!parameters.equals(eCPublicKeyParameters.getParameters())) {
            throw new IllegalArgumentException("ephemeral public key has different domain parameters");
        }
        this.h = eCPrivateKeyParameters;
        this.i = eCPrivateKeyParameters2;
        this.j = eCPublicKeyParameters;
    }

    public ECPrivateKeyParameters getEphemeralPrivateKey() {
        return this.i;
    }

    public ECPublicKeyParameters getEphemeralPublicKey() {
        return this.j;
    }

    public ECPrivateKeyParameters getStaticPrivateKey() {
        return this.h;
    }
}
