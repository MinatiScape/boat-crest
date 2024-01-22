package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;
/* loaded from: classes13.dex */
public class PBESecretKeyFactory extends BaseSecretKeyFactory {
    public boolean h;
    public int i;
    public int j;
    public int k;
    public int l;

    public PBESecretKeyFactory(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, int i, int i2, int i3, int i4) {
        super(str, aSN1ObjectIdentifier);
        this.h = z;
        this.i = i;
        this.j = i2;
        this.k = i3;
        this.l = i4;
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory, javax.crypto.SecretKeyFactorySpi
    public SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof PBEKeySpec) {
            PBEKeySpec pBEKeySpec = (PBEKeySpec) keySpec;
            if (pBEKeySpec.getSalt() == null) {
                return new BCPBEKey(this.algName, this.algOid, this.i, this.j, this.k, this.l, pBEKeySpec, null);
            }
            return new BCPBEKey(this.algName, this.algOid, this.i, this.j, this.k, this.l, pBEKeySpec, this.h ? PBE.Util.makePBEParameters(pBEKeySpec, this.i, this.j, this.k, this.l) : PBE.Util.makePBEMacParameters(pBEKeySpec, this.i, this.j, this.k));
        }
        throw new InvalidKeySpecException("Invalid KeySpec");
    }
}
