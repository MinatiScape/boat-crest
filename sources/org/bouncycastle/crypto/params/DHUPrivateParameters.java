package org.bouncycastle.crypto.params;

import java.util.Objects;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class DHUPrivateParameters implements CipherParameters {
    public DHPrivateKeyParameters h;
    public DHPrivateKeyParameters i;
    public DHPublicKeyParameters j;

    public DHUPrivateParameters(DHPrivateKeyParameters dHPrivateKeyParameters, DHPrivateKeyParameters dHPrivateKeyParameters2) {
        this(dHPrivateKeyParameters, dHPrivateKeyParameters2, null);
    }

    public DHUPrivateParameters(DHPrivateKeyParameters dHPrivateKeyParameters, DHPrivateKeyParameters dHPrivateKeyParameters2, DHPublicKeyParameters dHPublicKeyParameters) {
        Objects.requireNonNull(dHPrivateKeyParameters, "staticPrivateKey cannot be null");
        Objects.requireNonNull(dHPrivateKeyParameters2, "ephemeralPrivateKey cannot be null");
        DHParameters parameters = dHPrivateKeyParameters.getParameters();
        if (!parameters.equals(dHPrivateKeyParameters2.getParameters())) {
            throw new IllegalArgumentException("static and ephemeral private keys have different domain parameters");
        }
        if (dHPublicKeyParameters == null) {
            dHPublicKeyParameters = new DHPublicKeyParameters(parameters.getG().modPow(dHPrivateKeyParameters2.getX(), parameters.getP()), parameters);
        } else if (!parameters.equals(dHPublicKeyParameters.getParameters())) {
            throw new IllegalArgumentException("ephemeral public key has different domain parameters");
        }
        this.h = dHPrivateKeyParameters;
        this.i = dHPrivateKeyParameters2;
        this.j = dHPublicKeyParameters;
    }

    public DHPrivateKeyParameters getEphemeralPrivateKey() {
        return this.i;
    }

    public DHPublicKeyParameters getEphemeralPublicKey() {
        return this.j;
    }

    public DHPrivateKeyParameters getStaticPrivateKey() {
        return this.h;
    }
}
