package com.jieli.bluetooth_connect.bean.ble;

import com.jieli.bluetooth_connect.util.CHexConverter;
import java.io.Serializable;
/* loaded from: classes11.dex */
public class BleScanMessage implements Serializable {
    private int action;
    private int chargingBinMode;
    private int chargingBinQuantity;
    private int chargingBinStatus;
    private int connectWay;
    private String edrAddr;
    private int edrStatus;
    private String flagContent;
    private byte[] hash;
    private boolean isDeviceCharging;
    private boolean isLeftCharging;
    private boolean isOTA;
    private boolean isRightCharging;
    private int leftDeviceQuantity;
    private int mainDevFlag;
    private byte[] otaADVReserve;
    private int otaADVVersion;
    private String otaBleAddress;
    private int pairedFlag;
    private byte[] phoneVirtualAddress;
    private int pid;
    private byte[] rawData;
    private int rightDeviceQuantity;
    private int rssi;
    private int seq;
    private boolean showDialog;
    private int twsFlag;
    private int uid;
    private int version;
    private int vid;
    private int deviceType = -1;
    private boolean isEnableConnect = true;

    private int formatQuantity(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > 100) {
            return 100;
        }
        return i;
    }

    public boolean baseEquals(BleScanMessage bleScanMessage) {
        return bleScanMessage != null && bleScanMessage.getEdrAddr() != null && bleScanMessage.getEdrAddr().equals(this.edrAddr) && bleScanMessage.getVid() == this.vid && bleScanMessage.getUid() == this.uid && bleScanMessage.getPid() == this.pid;
    }

    public int getAction() {
        return this.action;
    }

    public int getChargingBinMode() {
        return this.chargingBinMode;
    }

    public int getChargingBinQuantity() {
        return this.chargingBinQuantity;
    }

    public int getChargingBinStatus() {
        return this.chargingBinStatus;
    }

    public int getConnectWay() {
        return this.connectWay;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public String getEdrAddr() {
        return this.edrAddr;
    }

    public int getEdrStatus() {
        return this.edrStatus;
    }

    public String getFlagContent() {
        return this.flagContent;
    }

    public byte[] getHash() {
        return this.hash;
    }

    public int getLeftDeviceQuantity() {
        return this.leftDeviceQuantity;
    }

    public int getMainDevFlag() {
        return this.mainDevFlag;
    }

    public byte[] getOtaADVReserve() {
        return this.otaADVReserve;
    }

    public int getOtaADVVersion() {
        return this.otaADVVersion;
    }

    public String getOtaBleAddress() {
        return this.otaBleAddress;
    }

    public int getPairedFlag() {
        return this.pairedFlag;
    }

    public byte[] getPhoneVirtualAddress() {
        return this.phoneVirtualAddress;
    }

    public int getPid() {
        return this.pid;
    }

    public byte[] getRawData() {
        return this.rawData;
    }

    public int getRightDeviceQuantity() {
        return this.rightDeviceQuantity;
    }

    public int getRssi() {
        return this.rssi;
    }

    public int getSeq() {
        return this.seq;
    }

    public int getTwsFlag() {
        return this.twsFlag;
    }

    public int getUid() {
        return this.uid;
    }

    public int getVersion() {
        return this.version;
    }

    public int getVid() {
        return this.vid;
    }

    public boolean isDeviceCharging() {
        return this.isDeviceCharging;
    }

    public boolean isEnableConnect() {
        return this.isEnableConnect;
    }

    public boolean isLeftCharging() {
        return this.isLeftCharging;
    }

    public boolean isOTA() {
        return this.isOTA;
    }

    public boolean isRightCharging() {
        return this.isRightCharging;
    }

    public boolean isShowDialog() {
        return this.showDialog;
    }

    public BleScanMessage setAction(int i) {
        this.action = i;
        return this;
    }

    public BleScanMessage setChargingBinMode(int i) {
        this.chargingBinMode = i;
        return this;
    }

    public BleScanMessage setChargingBinQuantity(int i) {
        this.chargingBinQuantity = formatQuantity(i);
        return this;
    }

    public BleScanMessage setChargingBinStatus(int i) {
        this.chargingBinStatus = i;
        return this;
    }

    public BleScanMessage setConnectWay(int i) {
        this.connectWay = i;
        return this;
    }

    public BleScanMessage setDeviceCharging(boolean z) {
        this.isDeviceCharging = z;
        return this;
    }

    public BleScanMessage setDeviceType(int i) {
        this.deviceType = i;
        return this;
    }

    public BleScanMessage setEdrAddr(String str) {
        this.edrAddr = str;
        return this;
    }

    public BleScanMessage setEdrStatus(int i) {
        this.edrStatus = i;
        return this;
    }

    public BleScanMessage setEnableConnect(boolean z) {
        this.isEnableConnect = z;
        return this;
    }

    public BleScanMessage setFlagContent(String str) {
        this.flagContent = str;
        return this;
    }

    public BleScanMessage setHash(byte[] bArr) {
        this.hash = bArr;
        return this;
    }

    public BleScanMessage setLeftCharging(boolean z) {
        this.isLeftCharging = z;
        return this;
    }

    public BleScanMessage setLeftDeviceQuantity(int i) {
        this.leftDeviceQuantity = formatQuantity(i);
        return this;
    }

    public BleScanMessage setMainDevFlag(int i) {
        this.mainDevFlag = i;
        return this;
    }

    public BleScanMessage setOTA(boolean z) {
        this.isOTA = z;
        return this;
    }

    public BleScanMessage setOtaADVReserve(byte[] bArr) {
        this.otaADVReserve = bArr;
        return this;
    }

    public BleScanMessage setOtaADVVersion(int i) {
        this.otaADVVersion = i;
        return this;
    }

    public BleScanMessage setOtaBleAddress(String str) {
        this.otaBleAddress = str;
        return this;
    }

    public BleScanMessage setPairedFlag(int i) {
        this.pairedFlag = i;
        return this;
    }

    public BleScanMessage setPhoneVirtualAddress(byte[] bArr) {
        this.phoneVirtualAddress = bArr;
        return this;
    }

    public BleScanMessage setPid(int i) {
        this.pid = i;
        return this;
    }

    public BleScanMessage setRawData(byte[] bArr) {
        this.rawData = bArr;
        return this;
    }

    public BleScanMessage setRightCharging(boolean z) {
        this.isRightCharging = z;
        return this;
    }

    public BleScanMessage setRightDeviceQuantity(int i) {
        this.rightDeviceQuantity = formatQuantity(i);
        return this;
    }

    public BleScanMessage setRssi(int i) {
        this.rssi = i;
        return this;
    }

    public BleScanMessage setSeq(int i) {
        this.seq = i;
        return this;
    }

    public BleScanMessage setShowDialog(boolean z) {
        this.showDialog = z;
        return this;
    }

    public BleScanMessage setTwsFlag(int i) {
        this.twsFlag = i;
        return this;
    }

    public BleScanMessage setUid(int i) {
        this.uid = i;
        return this;
    }

    public BleScanMessage setVersion(int i) {
        this.version = i;
        return this;
    }

    public BleScanMessage setVid(int i) {
        this.vid = i;
        return this;
    }

    public String toString() {
        return "BleScanMessage{flagContent='" + this.flagContent + "', pairedFlag=" + this.pairedFlag + ", pid=" + this.pid + ", vid=" + this.vid + ", uid=" + this.uid + ", deviceType=" + this.deviceType + ", version=" + this.version + ", showDialog=" + this.showDialog + ", edrAddr='" + this.edrAddr + "', edrStatus=" + this.edrStatus + ", leftDeviceQuantity=" + this.leftDeviceQuantity + ", isLeftCharging=" + this.isLeftCharging + ", rightDeviceQuantity=" + this.rightDeviceQuantity + ", isRightCharging=" + this.isRightCharging + ", chargingBinQuantity=" + this.chargingBinQuantity + ", isDeviceCharging=" + this.isDeviceCharging + ", twsFlag=" + this.twsFlag + ", chargingBinStatus=" + this.chargingBinStatus + ", mainDevFlag=" + this.mainDevFlag + ", action=" + this.action + ", seq=" + this.seq + ", chargingBinMode=" + this.chargingBinMode + ", rssi=" + this.rssi + ", isEnableConnect=" + this.isEnableConnect + ", connectWay=" + this.connectWay + ", isOTA=" + this.isOTA + ", otaADVVersion=" + this.otaADVVersion + ", otaBleAddress='" + this.otaBleAddress + "', otaADVReserve=" + CHexConverter.byte2HexStr(this.otaADVReserve) + '}';
    }
}
