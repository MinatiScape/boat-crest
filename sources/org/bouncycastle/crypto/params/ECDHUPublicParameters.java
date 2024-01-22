package org.bouncycastle.crypto.params;

import java.util.Objects;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class ECDHUPublicParameters implements CipherParameters {
    public ECPublicKeyParameters h;
    public ECPublicKeyParameters i;

    public ECDHUPublicParameters(ECPublicKeyParameters eCPublicKeyParameters, ECPublicKeyParameters eCPublicKeyParameters2) {
        Objects.requireNonNull(eCPublicKeyParameters, "staticPublicKey cannot be null");
        Objects.requireNonNull(eCPublicKeyParameters2, "ephemeralPublicKey cannot be null");
        if (!eCPublicKeyParameters.getParameters().equals(eCPublicKeyParameters2.getParameters())) {
            throw new IllegalArgumentException("static and ephemeral public keys have different domain parameters");
        }
        this.h = eCPublicKeyParameters;
        this.i = eCPublicKeyParameters2;
    }

    public ECPublicKeyParameters getEphemeralPublicKey() {
        return this.i;
    }

    public ECPublicKeyParameters getStaticPublicKey() {
        return this.h;
    }
}
