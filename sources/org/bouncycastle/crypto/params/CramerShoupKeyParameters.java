package org.bouncycastle.crypto.params;
/* loaded from: classes13.dex */
public class CramerShoupKeyParameters extends AsymmetricKeyParameter {
    public CramerShoupParameters i;

    public CramerShoupKeyParameters(boolean z, CramerShoupParameters cramerShoupParameters) {
        super(z);
        this.i = cramerShoupParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupKeyParameters) {
            CramerShoupParameters cramerShoupParameters = this.i;
            CramerShoupParameters parameters = ((CramerShoupKeyParameters) obj).getParameters();
            return cramerShoupParameters == null ? parameters == null : cramerShoupParameters.equals(parameters);
        }
        return false;
    }

    public CramerShoupParameters getParameters() {
        return this.i;
    }

    public int hashCode() {
        int i = !isPrivate();
        CramerShoupParameters cramerShoupParameters = this.i;
        return cramerShoupParameters != null ? i ^ cramerShoupParameters.hashCode() : i;
    }
}
