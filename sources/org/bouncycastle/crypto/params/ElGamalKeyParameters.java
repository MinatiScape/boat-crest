package org.bouncycastle.crypto.params;
/* loaded from: classes13.dex */
public class ElGamalKeyParameters extends AsymmetricKeyParameter {
    public ElGamalParameters i;

    public ElGamalKeyParameters(boolean z, ElGamalParameters elGamalParameters) {
        super(z);
        this.i = elGamalParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ElGamalKeyParameters) {
            ElGamalParameters elGamalParameters = this.i;
            ElGamalParameters parameters = ((ElGamalKeyParameters) obj).getParameters();
            return elGamalParameters == null ? parameters == null : elGamalParameters.equals(parameters);
        }
        return false;
    }

    public ElGamalParameters getParameters() {
        return this.i;
    }

    public int hashCode() {
        ElGamalParameters elGamalParameters = this.i;
        if (elGamalParameters != null) {
            return elGamalParameters.hashCode();
        }
        return 0;
    }
}
