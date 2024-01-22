package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.crypto.engines.GOST28147Engine;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class GOST28147ParameterSpec implements AlgorithmParameterSpec {
    public static Map c;

    /* renamed from: a  reason: collision with root package name */
    public byte[] f15071a;
    public byte[] b;

    static {
        HashMap hashMap = new HashMap();
        c = hashMap;
        hashMap.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet, "E-A");
        c.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_B_ParamSet, "E-B");
        c.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_C_ParamSet, "E-C");
        c.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_D_ParamSet, "E-D");
    }

    public GOST28147ParameterSpec(String str) {
        this.f15071a = null;
        this.b = null;
        this.b = GOST28147Engine.getSBox(str);
    }

    public GOST28147ParameterSpec(String str, byte[] bArr) {
        this(str);
        byte[] bArr2 = new byte[bArr.length];
        this.f15071a = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    public GOST28147ParameterSpec(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        this(a(aSN1ObjectIdentifier));
        this.f15071a = Arrays.clone(bArr);
    }

    public GOST28147ParameterSpec(byte[] bArr) {
        this.f15071a = null;
        this.b = null;
        byte[] bArr2 = new byte[bArr.length];
        this.b = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    public GOST28147ParameterSpec(byte[] bArr, byte[] bArr2) {
        this(bArr);
        byte[] bArr3 = new byte[bArr2.length];
        this.f15071a = bArr3;
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
    }

    public static String a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) c.get(aSN1ObjectIdentifier);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("unknown OID: " + aSN1ObjectIdentifier);
    }

    public byte[] getIV() {
        return Arrays.clone(this.f15071a);
    }

    public byte[] getSBox() {
        return Arrays.clone(this.b);
    }

    public byte[] getSbox() {
        return Arrays.clone(this.b);
    }
}
