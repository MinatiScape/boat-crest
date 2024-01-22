package org.bouncycastle.asn1.x509;

import com.jstyle.blesdk1860.constant.BleConst;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
/* loaded from: classes12.dex */
public class KeyPurposeId extends ASN1Object {
    public static final KeyPurposeId anyExtendedKeyUsage;
    public static final ASN1ObjectIdentifier i;
    public static final KeyPurposeId id_kp_OCSPSigning;
    public static final KeyPurposeId id_kp_capwapAC;
    public static final KeyPurposeId id_kp_capwapWTP;
    public static final KeyPurposeId id_kp_clientAuth;
    public static final KeyPurposeId id_kp_codeSigning;
    public static final KeyPurposeId id_kp_dvcs;
    public static final KeyPurposeId id_kp_eapOverLAN;
    public static final KeyPurposeId id_kp_eapOverPPP;
    public static final KeyPurposeId id_kp_emailProtection;
    public static final KeyPurposeId id_kp_ipsecEndSystem;
    public static final KeyPurposeId id_kp_ipsecIKE;
    public static final KeyPurposeId id_kp_ipsecTunnel;
    public static final KeyPurposeId id_kp_ipsecUser;
    public static final KeyPurposeId id_kp_macAddress;
    public static final KeyPurposeId id_kp_msSGC;
    public static final KeyPurposeId id_kp_nsSGC;
    public static final KeyPurposeId id_kp_sbgpCertAAServerAuth;
    public static final KeyPurposeId id_kp_scvpClient;
    public static final KeyPurposeId id_kp_scvpServer;
    public static final KeyPurposeId id_kp_scvp_responder;
    public static final KeyPurposeId id_kp_serverAuth;
    public static final KeyPurposeId id_kp_smartcardlogon;
    public static final KeyPurposeId id_kp_timeStamping;
    public ASN1ObjectIdentifier h;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.3");
        i = aSN1ObjectIdentifier;
        anyExtendedKeyUsage = new KeyPurposeId(Extension.extendedKeyUsage.branch(BleConst.GetDeviceTime));
        id_kp_serverAuth = new KeyPurposeId(aSN1ObjectIdentifier.branch("1"));
        id_kp_clientAuth = new KeyPurposeId(aSN1ObjectIdentifier.branch("2"));
        id_kp_codeSigning = new KeyPurposeId(aSN1ObjectIdentifier.branch("3"));
        id_kp_emailProtection = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.GetDeviceInfo));
        id_kp_ipsecEndSystem = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.SetDeviceInfo));
        id_kp_ipsecTunnel = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.CMD_Set_Mac));
        id_kp_ipsecUser = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.GetStepGoal));
        id_kp_timeStamping = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.SetStepGoal));
        id_kp_OCSPSigning = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.GetDeviceBatteryLevel));
        id_kp_dvcs = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.GetDeviceMacAddress));
        id_kp_sbgpCertAAServerAuth = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.GetDeviceVersion));
        id_kp_scvp_responder = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.CMD_Reset));
        id_kp_eapOverPPP = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.CMD_MCUReset));
        id_kp_eapOverLAN = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.SetMotorVibrationWithTimes));
        id_kp_scvpServer = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.GetDeviceName));
        id_kp_scvpClient = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.SetDeviceName));
        id_kp_ipsecIKE = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.GetAutomaticHRMonitoring));
        id_kp_capwapAC = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.SetAutomaticHRMonitoring));
        id_kp_capwapWTP = new KeyPurposeId(aSN1ObjectIdentifier.branch(BleConst.GetAlarmClock));
        id_kp_smartcardlogon = new KeyPurposeId(new ASN1ObjectIdentifier("1.3.6.1.4.1.311.20.2.2"));
        id_kp_macAddress = new KeyPurposeId(new ASN1ObjectIdentifier("1.3.6.1.1.1.1.22"));
        id_kp_msSGC = new KeyPurposeId(new ASN1ObjectIdentifier("1.3.6.1.4.1.311.10.3.3"));
        id_kp_nsSGC = new KeyPurposeId(new ASN1ObjectIdentifier("2.16.840.1.113730.4.1"));
    }

    public KeyPurposeId(String str) {
        this(new ASN1ObjectIdentifier(str));
    }

    public KeyPurposeId(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.h = aSN1ObjectIdentifier;
    }

    public static KeyPurposeId getInstance(Object obj) {
        if (obj instanceof KeyPurposeId) {
            return (KeyPurposeId) obj;
        }
        if (obj != null) {
            return new KeyPurposeId(ASN1ObjectIdentifier.getInstance(obj));
        }
        return null;
    }

    public String getId() {
        return this.h.getId();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }

    public ASN1ObjectIdentifier toOID() {
        return this.h;
    }

    public String toString() {
        return this.h.toString();
    }
}
