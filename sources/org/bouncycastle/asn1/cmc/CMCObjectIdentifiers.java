package org.bouncycastle.asn1.cmc;

import com.jstyle.blesdk1860.constant.BleConst;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes12.dex */
public interface CMCObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_cct;
    public static final ASN1ObjectIdentifier id_cct_PKIData;
    public static final ASN1ObjectIdentifier id_cct_PKIResponse;
    public static final ASN1ObjectIdentifier id_cmc;
    public static final ASN1ObjectIdentifier id_cmc_addExtensions;
    public static final ASN1ObjectIdentifier id_cmc_authData;
    public static final ASN1ObjectIdentifier id_cmc_batchRequests;
    public static final ASN1ObjectIdentifier id_cmc_batchResponses;
    public static final ASN1ObjectIdentifier id_cmc_confirmCertAcceptance;
    public static final ASN1ObjectIdentifier id_cmc_controlProcessed;
    public static final ASN1ObjectIdentifier id_cmc_dataReturn;
    public static final ASN1ObjectIdentifier id_cmc_decryptedPOP;
    public static final ASN1ObjectIdentifier id_cmc_encryptedPOP;
    public static final ASN1ObjectIdentifier id_cmc_getCRL;
    public static final ASN1ObjectIdentifier id_cmc_getCert;
    public static final ASN1ObjectIdentifier id_cmc_identification;
    public static final ASN1ObjectIdentifier id_cmc_identityProof;
    public static final ASN1ObjectIdentifier id_cmc_identityProofV2;
    public static final ASN1ObjectIdentifier id_cmc_lraPOPWitness;
    public static final ASN1ObjectIdentifier id_cmc_modCertTemplate;
    public static final ASN1ObjectIdentifier id_cmc_popLinkRandom;
    public static final ASN1ObjectIdentifier id_cmc_popLinkWitness;
    public static final ASN1ObjectIdentifier id_cmc_popLinkWitnessV2;
    public static final ASN1ObjectIdentifier id_cmc_publishCert;
    public static final ASN1ObjectIdentifier id_cmc_queryPending;
    public static final ASN1ObjectIdentifier id_cmc_recipientNonce;
    public static final ASN1ObjectIdentifier id_cmc_regInfo;
    public static final ASN1ObjectIdentifier id_cmc_responseInfo;
    public static final ASN1ObjectIdentifier id_cmc_revokeRequest;
    public static final ASN1ObjectIdentifier id_cmc_senderNonce;
    public static final ASN1ObjectIdentifier id_cmc_statusInfo;
    public static final ASN1ObjectIdentifier id_cmc_statusInfoV2;
    public static final ASN1ObjectIdentifier id_cmc_transactionId;
    public static final ASN1ObjectIdentifier id_cmc_trustedAnchors;
    public static final ASN1ObjectIdentifier id_pkix;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.5.5.7");
        id_pkix = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch(BleConst.GetStepGoal);
        id_cmc = branch;
        ASN1ObjectIdentifier branch2 = aSN1ObjectIdentifier.branch(BleConst.CMD_Reset);
        id_cct = branch2;
        id_cmc_identityProof = branch.branch("3");
        id_cmc_dataReturn = branch.branch(BleConst.GetDeviceInfo);
        id_cmc_regInfo = branch.branch(BleConst.SetAutomaticHRMonitoring);
        id_cmc_responseInfo = branch.branch(BleConst.GetAlarmClock);
        id_cmc_queryPending = branch.branch(BleConst.GetSedentaryReminder);
        id_cmc_popLinkRandom = branch.branch(BleConst.SetSedentaryReminder);
        id_cmc_popLinkWitness = branch.branch(BleConst.RealTimeStep);
        id_cmc_identification = branch.branch("2");
        id_cmc_transactionId = branch.branch(BleConst.SetDeviceInfo);
        id_cmc_senderNonce = branch.branch(BleConst.CMD_Set_Mac);
        id_cmc_recipientNonce = branch.branch(BleConst.GetStepGoal);
        id_cct_PKIData = branch2.branch("2");
        id_cct_PKIResponse = branch2.branch("3");
        id_cmc_statusInfo = branch.branch("1");
        id_cmc_addExtensions = branch.branch(BleConst.SetStepGoal);
        id_cmc_encryptedPOP = branch.branch(BleConst.GetDeviceBatteryLevel);
        id_cmc_decryptedPOP = branch.branch(BleConst.GetDeviceMacAddress);
        id_cmc_lraPOPWitness = branch.branch(BleConst.GetDeviceVersion);
        id_cmc_getCert = branch.branch(BleConst.GetDeviceName);
        id_cmc_getCRL = branch.branch(BleConst.SetDeviceName);
        id_cmc_revokeRequest = branch.branch(BleConst.GetAutomaticHRMonitoring);
        id_cmc_confirmCertAcceptance = branch.branch(BleConst.GetTotalActivityData);
        id_cmc_statusInfoV2 = branch.branch(BleConst.GetDetailActivityData);
        id_cmc_trustedAnchors = branch.branch(BleConst.GetDetailSleepData);
        id_cmc_authData = branch.branch(BleConst.GetDynamicHR);
        id_cmc_batchRequests = branch.branch(BleConst.GetStaticHR);
        id_cmc_batchResponses = branch.branch(BleConst.GetActivityModeData);
        id_cmc_publishCert = branch.branch(BleConst.EnterActivityMode);
        id_cmc_modCertTemplate = branch.branch(BleConst.DeviceSendDataToAPP);
        id_cmc_controlProcessed = branch.branch("32");
        id_cmc_identityProofV2 = branch.branch("34");
        id_cmc_popLinkWitnessV2 = branch.branch("33");
    }
}
