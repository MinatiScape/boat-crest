package org.bouncycastle.openssl.bc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.generators.OpenSSLPBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.util.Integers;
/* loaded from: classes13.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f15205a;
    public static final Set b;
    public static final Set c;

    static {
        HashMap hashMap = new HashMap();
        f15205a = hashMap;
        HashSet hashSet = new HashSet();
        b = hashSet;
        HashSet hashSet2 = new HashSet();
        c = hashSet2;
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC);
        hashSet2.add(PKCSObjectIdentifiers.id_PBES2);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.des_EDE3_CBC;
        hashSet2.add(aSN1ObjectIdentifier);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_aes128_CBC;
        hashSet2.add(aSN1ObjectIdentifier2);
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = NISTObjectIdentifiers.id_aes192_CBC;
        hashSet2.add(aSN1ObjectIdentifier3);
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = NISTObjectIdentifiers.id_aes256_CBC;
        hashSet2.add(aSN1ObjectIdentifier4);
        hashMap.put(aSN1ObjectIdentifier.getId(), Integers.valueOf(192));
        hashMap.put(aSN1ObjectIdentifier2.getId(), Integers.valueOf(128));
        hashMap.put(aSN1ObjectIdentifier3.getId(), Integers.valueOf(192));
        hashMap.put(aSN1ObjectIdentifier4.getId(), Integers.valueOf(256));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4.getId(), Integers.valueOf(128));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4, Integers.valueOf(40));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC, Integers.valueOf(128));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, Integers.valueOf(192));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC, Integers.valueOf(128));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC, Integers.valueOf(40));
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0130 A[Catch: Exception -> 0x016a, TRY_ENTER, TryCatch #0 {Exception -> 0x016a, blocks: (B:63:0x0130, B:67:0x013e, B:69:0x014a, B:72:0x0164, B:68:0x0142, B:64:0x0136), top: B:81:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0136 A[Catch: Exception -> 0x016a, TryCatch #0 {Exception -> 0x016a, blocks: (B:63:0x0130, B:67:0x013e, B:69:0x014a, B:72:0x0164, B:68:0x0142, B:64:0x0136), top: B:81:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x013e A[Catch: Exception -> 0x016a, TryCatch #0 {Exception -> 0x016a, blocks: (B:63:0x0130, B:67:0x013e, B:69:0x014a, B:72:0x0164, B:68:0x0142, B:64:0x0136), top: B:81:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0142 A[Catch: Exception -> 0x016a, TryCatch #0 {Exception -> 0x016a, blocks: (B:63:0x0130, B:67:0x013e, B:69:0x014a, B:72:0x0164, B:68:0x0142, B:64:0x0136), top: B:81:0x012e }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0163 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0164 A[Catch: Exception -> 0x016a, TRY_LEAVE, TryCatch #0 {Exception -> 0x016a, blocks: (B:63:0x0130, B:67:0x013e, B:69:0x014a, B:72:0x0164, B:68:0x0142, B:64:0x0136), top: B:81:0x012e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(boolean r16, byte[] r17, char[] r18, java.lang.String r19, byte[] r20) throws org.bouncycastle.openssl.PEMException {
        /*
            Method dump skipped, instructions count: 417
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.openssl.bc.a.a(boolean, byte[], char[], java.lang.String, byte[]):byte[]");
    }

    public static KeyParameter b(char[] cArr, int i, byte[] bArr) throws PEMException {
        return c(cArr, i, bArr, false);
    }

    public static KeyParameter c(char[] cArr, int i, byte[] bArr, boolean z) throws PEMException {
        OpenSSLPBEParametersGenerator openSSLPBEParametersGenerator = new OpenSSLPBEParametersGenerator();
        openSSLPBEParametersGenerator.init(PBEParametersGenerator.PKCS5PasswordToBytes(cArr), bArr, 1);
        KeyParameter keyParameter = (KeyParameter) openSSLPBEParametersGenerator.generateDerivedParameters(i * 8);
        if (z && keyParameter.getKey().length == 24) {
            byte[] key = keyParameter.getKey();
            System.arraycopy(key, 0, key, 16, 8);
            return new KeyParameter(key);
        }
        return keyParameter;
    }
}
