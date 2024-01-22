package org.bouncycastle.asn1.teletrust;

import com.jstyle.blesdk1860.constant.BleConst;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes12.dex */
public interface TeleTrusTObjectIdentifiers {
    public static final ASN1ObjectIdentifier brainpoolP160r1;
    public static final ASN1ObjectIdentifier brainpoolP160t1;
    public static final ASN1ObjectIdentifier brainpoolP192r1;
    public static final ASN1ObjectIdentifier brainpoolP192t1;
    public static final ASN1ObjectIdentifier brainpoolP224r1;
    public static final ASN1ObjectIdentifier brainpoolP224t1;
    public static final ASN1ObjectIdentifier brainpoolP256r1;
    public static final ASN1ObjectIdentifier brainpoolP256t1;
    public static final ASN1ObjectIdentifier brainpoolP320r1;
    public static final ASN1ObjectIdentifier brainpoolP320t1;
    public static final ASN1ObjectIdentifier brainpoolP384r1;
    public static final ASN1ObjectIdentifier brainpoolP384t1;
    public static final ASN1ObjectIdentifier brainpoolP512r1;
    public static final ASN1ObjectIdentifier brainpoolP512t1;
    public static final ASN1ObjectIdentifier ecSign;
    public static final ASN1ObjectIdentifier ecSignWithRipemd160;
    public static final ASN1ObjectIdentifier ecSignWithSha1;
    public static final ASN1ObjectIdentifier ecc_brainpool;
    public static final ASN1ObjectIdentifier ellipticCurve;
    public static final ASN1ObjectIdentifier ripemd128;
    public static final ASN1ObjectIdentifier ripemd160;
    public static final ASN1ObjectIdentifier ripemd256;
    public static final ASN1ObjectIdentifier rsaSignatureWithripemd128;
    public static final ASN1ObjectIdentifier rsaSignatureWithripemd160;
    public static final ASN1ObjectIdentifier rsaSignatureWithripemd256;
    public static final ASN1ObjectIdentifier teleTrusTAlgorithm;
    public static final ASN1ObjectIdentifier teleTrusTRSAsignatureAlgorithm;
    public static final ASN1ObjectIdentifier versionOne;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.36.3");
        teleTrusTAlgorithm = aSN1ObjectIdentifier;
        ripemd160 = aSN1ObjectIdentifier.branch("2.1");
        ripemd128 = aSN1ObjectIdentifier.branch("2.2");
        ripemd256 = aSN1ObjectIdentifier.branch("2.3");
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch("3.1");
        teleTrusTRSAsignatureAlgorithm = branch;
        rsaSignatureWithripemd160 = branch.branch("2");
        rsaSignatureWithripemd128 = branch.branch("3");
        rsaSignatureWithripemd256 = branch.branch(BleConst.GetDeviceInfo);
        ASN1ObjectIdentifier branch2 = aSN1ObjectIdentifier.branch("3.2");
        ecSign = branch2;
        ecSignWithSha1 = branch2.branch("1");
        ecSignWithRipemd160 = branch2.branch("2");
        ASN1ObjectIdentifier branch3 = aSN1ObjectIdentifier.branch("3.2.8");
        ecc_brainpool = branch3;
        ASN1ObjectIdentifier branch4 = branch3.branch("1");
        ellipticCurve = branch4;
        ASN1ObjectIdentifier branch5 = branch4.branch("1");
        versionOne = branch5;
        brainpoolP160r1 = branch5.branch("1");
        brainpoolP160t1 = branch5.branch("2");
        brainpoolP192r1 = branch5.branch("3");
        brainpoolP192t1 = branch5.branch(BleConst.GetDeviceInfo);
        brainpoolP224r1 = branch5.branch(BleConst.SetDeviceInfo);
        brainpoolP224t1 = branch5.branch(BleConst.CMD_Set_Mac);
        brainpoolP256r1 = branch5.branch(BleConst.GetStepGoal);
        brainpoolP256t1 = branch5.branch(BleConst.SetStepGoal);
        brainpoolP320r1 = branch5.branch(BleConst.GetDeviceBatteryLevel);
        brainpoolP320t1 = branch5.branch(BleConst.GetDeviceMacAddress);
        brainpoolP384r1 = branch5.branch(BleConst.GetDeviceVersion);
        brainpoolP384t1 = branch5.branch(BleConst.CMD_Reset);
        brainpoolP512r1 = branch5.branch(BleConst.CMD_MCUReset);
        brainpoolP512t1 = branch5.branch(BleConst.SetMotorVibrationWithTimes);
    }
}
