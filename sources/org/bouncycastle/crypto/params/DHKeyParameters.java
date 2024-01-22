package org.bouncycastle.crypto.params;
/* loaded from: classes13.dex */
public class DHKeyParameters extends AsymmetricKeyParameter {
    public DHParameters i;

    public DHKeyParameters(boolean z, DHParameters dHParameters) {
        super(z);
        this.i = dHParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DHKeyParameters) {
            DHParameters dHParameters = this.i;
            DHParameters parameters = ((DHKeyParameters) obj).getParameters();
            return dHParameters == null ? parameters == null : dHParameters.equals(parameters);
        }
        return false;
    }

    public DHParameters getParameters() {
        return this.i;
    }

    public int hashCode() {
        int i = !isPrivate();
        DHParameters dHParameters = this.i;
        return dHParameters != null ? i ^ dHParameters.hashCode() : i;
    }
}
