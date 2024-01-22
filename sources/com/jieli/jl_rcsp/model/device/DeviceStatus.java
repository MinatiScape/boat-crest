package com.jieli.jl_rcsp.model.device;

import com.jieli.jl_rcsp.model.command.GetDevConfigureCmd;
import com.jieli.jl_rcsp.model.response.ADVInfoResponse;
import com.jieli.jl_rcsp.model.response.ExternalFlashMsgResponse;
import com.jieli.jl_rcsp.model.response.GetLowLatencySettingsResponse;
import com.jieli.jl_rcsp.model.response.TargetInfoResponse;
/* loaded from: classes11.dex */
public class DeviceStatus {
    private GetDevConfigureCmd.Response deviceConfigure;
    private boolean isAuthDevice;
    private boolean isEnableLatencyMode;
    private boolean isEnterLowPowerMode;
    private boolean isMandatoryUpgrade;
    private boolean isTwsConnected;
    private ADVInfoResponse mADVInfo;
    private String mDevMD5;
    private DeviceInfo mDeviceInfo;
    private ExternalFlashMsgResponse mExtFlashMSg;
    private GetLowLatencySettingsResponse mLatencySettings;
    private VoiceData mVoiceData;
    private int maxCommunicationMtu = 530;
    private int maxReceiveMtu = 530;
    private int status;

    public ADVInfoResponse getADVInfo() {
        return this.mADVInfo;
    }

    public String getDevMD5() {
        return this.mDevMD5;
    }

    public GetDevConfigureCmd.Response getDeviceConfigure() {
        return this.deviceConfigure;
    }

    public DeviceInfo getDeviceInfo() {
        return this.mDeviceInfo;
    }

    public ExternalFlashMsgResponse getExtFlashMSg() {
        return this.mExtFlashMSg;
    }

    public GetLowLatencySettingsResponse getLatencySettings() {
        return this.mLatencySettings;
    }

    public int getMaxCommunicationMtu() {
        return this.maxCommunicationMtu;
    }

    public int getMaxReceiveMtu() {
        return this.maxReceiveMtu;
    }

    public int getStatus() {
        return this.status;
    }

    public VoiceData getVoiceData() {
        return this.mVoiceData;
    }

    public boolean isAuthDevice() {
        return this.isAuthDevice;
    }

    public boolean isEnableLatencyMode() {
        return this.isEnableLatencyMode;
    }

    public boolean isEnterLowPowerMode() {
        return this.isEnterLowPowerMode;
    }

    public boolean isMandatoryUpgrade() {
        return this.isMandatoryUpgrade;
    }

    public boolean isTwsConnected() {
        return this.isTwsConnected;
    }

    public DeviceStatus setADVInfo(ADVInfoResponse aDVInfoResponse) {
        this.mADVInfo = aDVInfoResponse;
        setTwsConnected(aDVInfoResponse != null && aDVInfoResponse.getLeftDeviceQuantity() > 0 && aDVInfoResponse.getRightDeviceQuantity() > 0);
        return this;
    }

    public DeviceStatus setAuthDevice(boolean z) {
        this.isAuthDevice = z;
        return this;
    }

    public DeviceStatus setDevMD5(String str) {
        this.mDevMD5 = str;
        return this;
    }

    public DeviceStatus setDeviceConfigure(GetDevConfigureCmd.Response response) {
        this.deviceConfigure = response;
        return this;
    }

    public DeviceStatus setDeviceInfo(TargetInfoResponse targetInfoResponse) {
        if (targetInfoResponse instanceof DeviceInfo) {
            this.mDeviceInfo = (DeviceInfo) targetInfoResponse;
        } else {
            this.mDeviceInfo = DeviceInfo.convertFromTargetInfo(targetInfoResponse);
        }
        return this;
    }

    public DeviceStatus setEnableLatencyMode(boolean z) {
        this.isEnableLatencyMode = z;
        return this;
    }

    public DeviceStatus setEnterLowPowerMode(boolean z) {
        this.isEnterLowPowerMode = z;
        return this;
    }

    public DeviceStatus setExtFlashMSg(ExternalFlashMsgResponse externalFlashMsgResponse) {
        this.mExtFlashMSg = externalFlashMsgResponse;
        return this;
    }

    public DeviceStatus setLatencySettings(GetLowLatencySettingsResponse getLowLatencySettingsResponse) {
        this.mLatencySettings = getLowLatencySettingsResponse;
        return this;
    }

    public DeviceStatus setMandatoryUpgrade(boolean z) {
        this.isMandatoryUpgrade = z;
        return this;
    }

    public DeviceStatus setMaxCommunicationMtu(int i) {
        this.maxCommunicationMtu = i;
        return this;
    }

    public DeviceStatus setMaxReceiveMtu(int i) {
        this.maxReceiveMtu = i;
        return this;
    }

    public DeviceStatus setStatus(int i) {
        this.status = i;
        return this;
    }

    public DeviceStatus setTwsConnected(boolean z) {
        this.isTwsConnected = z;
        return this;
    }

    public DeviceStatus setVoiceData(VoiceData voiceData) {
        this.mVoiceData = voiceData;
        return this;
    }

    public String toString() {
        return "DeviceStatus{status=" + this.status + ", isAuthDevice=" + this.isAuthDevice + ", isEnterLowPowerMode=" + this.isEnterLowPowerMode + ", isMandatoryUpgrade=" + this.isMandatoryUpgrade + ", isEnableLatencyMode=" + this.isEnableLatencyMode + ", mDeviceInfo=" + this.mDeviceInfo + ", mADVInfo=" + this.mADVInfo + ", mDevMD5='" + this.mDevMD5 + "', mLatencySettings=" + this.mLatencySettings + ", maxCommunicationMtu=" + this.maxCommunicationMtu + ", maxReceiveMtu=" + this.maxReceiveMtu + ", mVoiceData=" + this.mVoiceData + ", isTwsConnected=" + this.isTwsConnected + ", mExtFlashMSg=" + this.mExtFlashMSg + ", deviceConfigure=" + this.deviceConfigure + '}';
    }
}
