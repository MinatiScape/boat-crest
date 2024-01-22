package org.bouncycastle.asn1.isismtt;

import com.jstyle.blesdk1860.constant.BleConst;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes12.dex */
public interface ISISMTTObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_isismtt;
    public static final ASN1ObjectIdentifier id_isismtt_at;
    public static final ASN1ObjectIdentifier id_isismtt_at_PKReference;
    public static final ASN1ObjectIdentifier id_isismtt_at_additionalInformation;
    public static final ASN1ObjectIdentifier id_isismtt_at_admission;
    public static final ASN1ObjectIdentifier id_isismtt_at_certHash;
    public static final ASN1ObjectIdentifier id_isismtt_at_certInDirSince;
    public static final ASN1ObjectIdentifier id_isismtt_at_dateOfCertGen;
    public static final ASN1ObjectIdentifier id_isismtt_at_declarationOfMajority;
    public static final ASN1ObjectIdentifier id_isismtt_at_iCCSN;
    public static final ASN1ObjectIdentifier id_isismtt_at_liabilityLimitationFlag;
    public static final ASN1ObjectIdentifier id_isismtt_at_monetaryLimit;
    public static final ASN1ObjectIdentifier id_isismtt_at_nameAtBirth;
    public static final ASN1ObjectIdentifier id_isismtt_at_namingAuthorities;
    public static final ASN1ObjectIdentifier id_isismtt_at_procuration;
    public static final ASN1ObjectIdentifier id_isismtt_at_requestedCertificate;
    public static final ASN1ObjectIdentifier id_isismtt_at_restriction;
    public static final ASN1ObjectIdentifier id_isismtt_at_retrieveIfAllowed;
    public static final ASN1ObjectIdentifier id_isismtt_cp;
    public static final ASN1ObjectIdentifier id_isismtt_cp_accredited;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.36.8");
        id_isismtt = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch("1");
        id_isismtt_cp = branch;
        id_isismtt_cp_accredited = branch.branch("1");
        ASN1ObjectIdentifier branch2 = aSN1ObjectIdentifier.branch("3");
        id_isismtt_at = branch2;
        id_isismtt_at_dateOfCertGen = branch2.branch("1");
        id_isismtt_at_procuration = branch2.branch("2");
        id_isismtt_at_admission = branch2.branch("3");
        id_isismtt_at_monetaryLimit = branch2.branch(BleConst.GetDeviceInfo);
        id_isismtt_at_declarationOfMajority = branch2.branch(BleConst.SetDeviceInfo);
        id_isismtt_at_iCCSN = branch2.branch(BleConst.CMD_Set_Mac);
        id_isismtt_at_PKReference = branch2.branch(BleConst.GetStepGoal);
        id_isismtt_at_restriction = branch2.branch(BleConst.SetStepGoal);
        id_isismtt_at_retrieveIfAllowed = branch2.branch(BleConst.GetDeviceBatteryLevel);
        id_isismtt_at_requestedCertificate = branch2.branch(BleConst.GetDeviceMacAddress);
        id_isismtt_at_namingAuthorities = branch2.branch(BleConst.GetDeviceVersion);
        id_isismtt_at_certInDirSince = branch2.branch(BleConst.CMD_Reset);
        id_isismtt_at_certHash = branch2.branch(BleConst.CMD_MCUReset);
        id_isismtt_at_nameAtBirth = branch2.branch(BleConst.SetMotorVibrationWithTimes);
        id_isismtt_at_additionalInformation = branch2.branch(BleConst.GetDeviceName);
        id_isismtt_at_liabilityLimitationFlag = new ASN1ObjectIdentifier("0.2.262.1.10.12.0");
    }
}
