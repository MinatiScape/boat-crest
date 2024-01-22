package org.bouncycastle.jcajce.provider.util;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.util.Integers;
/* loaded from: classes13.dex */
public class SecretKeyUtil {

    /* renamed from: a  reason: collision with root package name */
    public static Map f15068a;

    static {
        HashMap hashMap = new HashMap();
        f15068a = hashMap;
        hashMap.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), Integers.valueOf(192));
        f15068a.put(NISTObjectIdentifiers.id_aes128_CBC, Integers.valueOf(128));
        f15068a.put(NISTObjectIdentifiers.id_aes192_CBC, Integers.valueOf(192));
        f15068a.put(NISTObjectIdentifiers.id_aes256_CBC, Integers.valueOf(256));
        f15068a.put(NTTObjectIdentifiers.id_camellia128_cbc, Integers.valueOf(128));
        f15068a.put(NTTObjectIdentifiers.id_camellia192_cbc, Integers.valueOf(192));
        f15068a.put(NTTObjectIdentifiers.id_camellia256_cbc, Integers.valueOf(256));
    }

    public static int getKeySize(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Integer num = (Integer) f15068a.get(aSN1ObjectIdentifier);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }
}
