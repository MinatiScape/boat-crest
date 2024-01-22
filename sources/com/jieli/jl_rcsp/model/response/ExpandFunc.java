package com.jieli.jl_rcsp.model.response;

import com.jieli.jl_rcsp.util.CHexConver;
/* loaded from: classes11.dex */
public class ExpandFunc {
    private boolean banEq;
    private boolean isGameMode;
    private boolean isSupportMD5;
    private boolean isSupportSearchDevice;
    private byte[] rawData;
    private boolean supportAdaptiveANC;
    private boolean supportAnc;
    private boolean supportDevConfigure;
    private boolean supportExternalFlashTransfer;
    private boolean supportHearingAssist;
    private boolean supportReadErrorMSg;
    private boolean supportSoundCard;

    public byte[] getRawData() {
        return this.rawData;
    }

    public boolean isBanEq() {
        return this.banEq;
    }

    public boolean isGameMode() {
        return this.isGameMode;
    }

    public boolean isSupportAdaptiveANC() {
        return this.supportAdaptiveANC;
    }

    public boolean isSupportAnc() {
        return this.supportAnc;
    }

    public boolean isSupportDevConfigure() {
        return this.supportDevConfigure;
    }

    public boolean isSupportExternalFlashTransfer() {
        return this.supportExternalFlashTransfer;
    }

    public boolean isSupportHearingAssist() {
        return this.supportHearingAssist;
    }

    public boolean isSupportMD5() {
        return this.isSupportMD5;
    }

    public boolean isSupportReadErrorMSg() {
        return this.supportReadErrorMSg;
    }

    public boolean isSupportSearchDevice() {
        return this.isSupportSearchDevice;
    }

    public boolean isSupportSoundCard() {
        return this.supportSoundCard;
    }

    public void parseData(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length < 1) {
                return;
            }
            setRawData(bArr);
            byte b = bArr[0];
            setSupportMD5((b & 1) == 1);
            setGameMode(((b >> 1) & 1) == 1);
            setSupportSearchDevice(((b >> 2) & 1) == 1);
            setSupportSoundCard(((b >> 3) & 1) == 1);
            setBanEq(((b >> 4) & 1) == 1);
            setSupportExternalFlashTransfer(((b >> 5) & 1) == 1);
            setSupportAnc(((b >> 6) & 1) == 1);
            setSupportReadErrorMSg(((b >> 7) & 1) == 1);
            if (bArr.length >= 2) {
                byte b2 = bArr[1];
                setSupportHearingAssist((b2 & 1) == 1);
                setSupportAdaptiveANC(((b2 >> 1) & 1) == 1);
                setSupportDevConfigure(((b2 >> 2) & 1) == 1);
            }
        }
    }

    public void setBanEq(boolean z) {
        this.banEq = z;
    }

    public void setGameMode(boolean z) {
        this.isGameMode = z;
    }

    public void setRawData(byte[] bArr) {
        this.rawData = bArr;
    }

    public void setSupportAdaptiveANC(boolean z) {
        this.supportAdaptiveANC = z;
    }

    public void setSupportAnc(boolean z) {
        this.supportAnc = z;
    }

    public void setSupportDevConfigure(boolean z) {
        this.supportDevConfigure = z;
    }

    public void setSupportExternalFlashTransfer(boolean z) {
        this.supportExternalFlashTransfer = z;
    }

    public void setSupportHearingAssist(boolean z) {
        this.supportHearingAssist = z;
    }

    public void setSupportMD5(boolean z) {
        this.isSupportMD5 = z;
    }

    public void setSupportReadErrorMSg(boolean z) {
        this.supportReadErrorMSg = z;
    }

    public void setSupportSearchDevice(boolean z) {
        this.isSupportSearchDevice = z;
    }

    public void setSupportSoundCard(boolean z) {
        this.supportSoundCard = z;
    }

    public String toString() {
        return "ExpandFunc{rawData=" + CHexConver.byte2HexStr(this.rawData) + ", isSupportMD5=" + this.isSupportMD5 + ", isGameMode=" + this.isGameMode + ", isSupportSearchDevice=" + this.isSupportSearchDevice + ", supportSoundCard=" + this.supportSoundCard + ", banEq=" + this.banEq + ", supportExternalFlashTransfer=" + this.supportExternalFlashTransfer + ", supportAnc=" + this.supportAnc + ", supportReadErrorMSg=" + this.supportReadErrorMSg + ", supportHearingAssist=" + this.supportHearingAssist + ", supportAdaptiveANC=" + this.supportAdaptiveANC + ", supportDevConfigure=" + this.supportDevConfigure + '}';
    }
}
