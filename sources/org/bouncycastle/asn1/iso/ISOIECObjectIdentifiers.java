package org.bouncycastle.asn1.iso;

import com.jstyle.blesdk1860.constant.BleConst;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes12.dex */
public interface ISOIECObjectIdentifiers {
    public static final ASN1ObjectIdentifier hash_algorithms;
    public static final ASN1ObjectIdentifier id_ac_generic_hybrid;
    public static final ASN1ObjectIdentifier id_kem_rsa;
    public static final ASN1ObjectIdentifier is18033_2;
    public static final ASN1ObjectIdentifier iso_encryption_algorithms;
    public static final ASN1ObjectIdentifier ripemd128;
    public static final ASN1ObjectIdentifier ripemd160;
    public static final ASN1ObjectIdentifier whirlpool;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.0.10118");
        iso_encryption_algorithms = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch("3.0");
        hash_algorithms = branch;
        ripemd160 = branch.branch(BleConst.CMD_Set_WorkOutReminder);
        ripemd128 = branch.branch(BleConst.CMD_Get_WorkOutReminder);
        whirlpool = branch.branch("55");
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = new ASN1ObjectIdentifier("1.0.18033.2");
        is18033_2 = aSN1ObjectIdentifier2;
        id_ac_generic_hybrid = aSN1ObjectIdentifier2.branch("1.2");
        id_kem_rsa = aSN1ObjectIdentifier2.branch("2.4");
    }
}
