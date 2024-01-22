package org.bouncycastle.crypto.params;

import java.util.Objects;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public class SM2KeyExchangePrivateParameters implements CipherParameters {
    public final boolean h;
    public final ECPrivateKeyParameters i;
    public final ECPoint j;
    public final ECPrivateKeyParameters k;
    public final ECPoint l;

    public SM2KeyExchangePrivateParameters(boolean z, ECPrivateKeyParameters eCPrivateKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters2) {
        Objects.requireNonNull(eCPrivateKeyParameters, "staticPrivateKey cannot be null");
        Objects.requireNonNull(eCPrivateKeyParameters2, "ephemeralPrivateKey cannot be null");
        ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
        if (!parameters.equals(eCPrivateKeyParameters2.getParameters())) {
            throw new IllegalArgumentException("Static and ephemeral private keys have different domain parameters");
        }
        this.h = z;
        this.i = eCPrivateKeyParameters;
        this.j = parameters.getG().multiply(eCPrivateKeyParameters.getD()).normalize();
        this.k = eCPrivateKeyParameters2;
        this.l = parameters.getG().multiply(eCPrivateKeyParameters2.getD()).normalize();
    }

    public ECPrivateKeyParameters getEphemeralPrivateKey() {
        return this.k;
    }

    public ECPoint getEphemeralPublicPoint() {
        return this.l;
    }

    public ECPrivateKeyParameters getStaticPrivateKey() {
        return this.i;
    }

    public ECPoint getStaticPublicPoint() {
        return this.j;
    }

    public boolean isInitiator() {
        return this.h;
    }
}
