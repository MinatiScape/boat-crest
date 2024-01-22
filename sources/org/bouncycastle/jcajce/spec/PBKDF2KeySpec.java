package org.bouncycastle.jcajce.spec;

import javax.crypto.spec.PBEKeySpec;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes13.dex */
public class PBKDF2KeySpec extends PBEKeySpec {
    public static final AlgorithmIdentifier i = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA1, DERNull.INSTANCE);
    public AlgorithmIdentifier h;

    public PBKDF2KeySpec(char[] cArr, byte[] bArr, int i2, int i3, AlgorithmIdentifier algorithmIdentifier) {
        super(cArr, bArr, i2, i3);
        this.h = algorithmIdentifier;
    }

    public AlgorithmIdentifier getPrf() {
        return this.h;
    }

    public boolean isDefaultPrf() {
        return i.equals(this.h);
    }
}
