package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes12.dex */
public class CMSConfig {
    public static void setSigningDigestAlgorithmMapping(String str, String str2) {
        f.f14565a.h(new ASN1ObjectIdentifier(str), str2);
    }

    public static void setSigningEncryptionAlgorithmMapping(String str, String str2) {
        f.f14565a.i(new ASN1ObjectIdentifier(str), str2);
    }
}
