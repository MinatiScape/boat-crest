package org.bouncycastle.asn1.nsri;

import com.jstyle.blesdk1860.constant.BleConst;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes12.dex */
public interface NSRIObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_algorithm;
    public static final ASN1ObjectIdentifier id_aria128_cbc;
    public static final ASN1ObjectIdentifier id_aria128_ccm;
    public static final ASN1ObjectIdentifier id_aria128_cfb;
    public static final ASN1ObjectIdentifier id_aria128_cmac;
    public static final ASN1ObjectIdentifier id_aria128_ctr;
    public static final ASN1ObjectIdentifier id_aria128_ecb;
    public static final ASN1ObjectIdentifier id_aria128_gcm;
    public static final ASN1ObjectIdentifier id_aria128_kw;
    public static final ASN1ObjectIdentifier id_aria128_kwp;
    public static final ASN1ObjectIdentifier id_aria128_ocb2;
    public static final ASN1ObjectIdentifier id_aria128_ofb;
    public static final ASN1ObjectIdentifier id_aria192_cbc;
    public static final ASN1ObjectIdentifier id_aria192_ccm;
    public static final ASN1ObjectIdentifier id_aria192_cfb;
    public static final ASN1ObjectIdentifier id_aria192_cmac;
    public static final ASN1ObjectIdentifier id_aria192_ctr;
    public static final ASN1ObjectIdentifier id_aria192_ecb;
    public static final ASN1ObjectIdentifier id_aria192_gcm;
    public static final ASN1ObjectIdentifier id_aria192_kw;
    public static final ASN1ObjectIdentifier id_aria192_kwp;
    public static final ASN1ObjectIdentifier id_aria192_ocb2;
    public static final ASN1ObjectIdentifier id_aria192_ofb;
    public static final ASN1ObjectIdentifier id_aria256_cbc;
    public static final ASN1ObjectIdentifier id_aria256_ccm;
    public static final ASN1ObjectIdentifier id_aria256_cfb;
    public static final ASN1ObjectIdentifier id_aria256_cmac;
    public static final ASN1ObjectIdentifier id_aria256_ctr;
    public static final ASN1ObjectIdentifier id_aria256_ecb;
    public static final ASN1ObjectIdentifier id_aria256_gcm;
    public static final ASN1ObjectIdentifier id_aria256_kw;
    public static final ASN1ObjectIdentifier id_aria256_kwp;
    public static final ASN1ObjectIdentifier id_aria256_ocb2;
    public static final ASN1ObjectIdentifier id_aria256_ofb;
    public static final ASN1ObjectIdentifier id_pad;
    public static final ASN1ObjectIdentifier id_pad_1;
    public static final ASN1ObjectIdentifier id_pad_null;
    public static final ASN1ObjectIdentifier id_sea;
    public static final ASN1ObjectIdentifier nsri;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.2.410.200046");
        nsri = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch("1");
        id_algorithm = branch;
        ASN1ObjectIdentifier branch2 = branch.branch("1");
        id_sea = branch2;
        id_pad = branch.branch("2");
        id_pad_null = branch.branch(BleConst.GetDeviceTime);
        id_pad_1 = branch.branch("1");
        id_aria128_ecb = branch2.branch("1");
        id_aria128_cbc = branch2.branch("2");
        id_aria128_cfb = branch2.branch("3");
        id_aria128_ofb = branch2.branch(BleConst.GetDeviceInfo);
        id_aria128_ctr = branch2.branch(BleConst.SetDeviceInfo);
        id_aria192_ecb = branch2.branch(BleConst.CMD_Set_Mac);
        id_aria192_cbc = branch2.branch(BleConst.GetStepGoal);
        id_aria192_cfb = branch2.branch(BleConst.SetStepGoal);
        id_aria192_ofb = branch2.branch(BleConst.GetDeviceBatteryLevel);
        id_aria192_ctr = branch2.branch(BleConst.GetDeviceMacAddress);
        id_aria256_ecb = branch2.branch(BleConst.GetDeviceVersion);
        id_aria256_cbc = branch2.branch(BleConst.CMD_Reset);
        id_aria256_cfb = branch2.branch(BleConst.CMD_MCUReset);
        id_aria256_ofb = branch2.branch(BleConst.SetMotorVibrationWithTimes);
        id_aria256_ctr = branch2.branch(BleConst.GetDeviceName);
        id_aria128_cmac = branch2.branch(BleConst.GetSedentaryReminder);
        id_aria192_cmac = branch2.branch(BleConst.SetSedentaryReminder);
        id_aria256_cmac = branch2.branch(BleConst.RealTimeStep);
        id_aria128_ocb2 = branch2.branch(BleConst.DeviceSendDataToAPP);
        id_aria192_ocb2 = branch2.branch("32");
        id_aria256_ocb2 = branch2.branch("33");
        id_aria128_gcm = branch2.branch("34");
        id_aria192_gcm = branch2.branch(BleConst.BackHomeView);
        id_aria256_gcm = branch2.branch(BleConst.GetTempHistoryData);
        id_aria128_ccm = branch2.branch(BleConst.ECGDATA);
        id_aria192_ccm = branch2.branch(BleConst.EcgppGstatus);
        id_aria256_ccm = branch2.branch(BleConst.EcgppG);
        id_aria128_kw = branch2.branch(BleConst.GPSControlCommand);
        id_aria192_kw = branch2.branch(BleConst.Gps);
        id_aria256_kw = branch2.branch("42");
        id_aria128_kwp = branch2.branch(BleConst.CMD_Set_TemperatureCorrection);
        id_aria192_kwp = branch2.branch(BleConst.Weather);
        id_aria256_kwp = branch2.branch(BleConst.Braceletdial);
    }
}