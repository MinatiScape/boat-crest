package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class FirmwareAndBt3Version {
    public static final int EFFECTIVE_FLAG = 1;
    public static final int INVALID_FLAG = 0;
    public int BT_flag;
    public int BT_match_version1;
    public int BT_match_version2;
    public int BT_match_version3;
    public int BT_version1;
    public int BT_version2;
    public int BT_version3;
    public int firmware_version1;
    public int firmware_version2;
    public int firmware_version3;

    public String toString() {
        return "FirmwareAndBt3Version{firmware_version1=" + this.firmware_version1 + ", firmware_version2=" + this.firmware_version2 + ", firmware_version3=" + this.firmware_version3 + ", BT_flag=" + this.BT_flag + ", BT_version1=" + this.BT_version1 + ", BT_version2=" + this.BT_version2 + ", BT_version3=" + this.BT_version3 + ", BT_match_version1=" + this.BT_match_version1 + ", BT_match_version2=" + this.BT_match_version2 + ", BT_match_version3=" + this.BT_match_version3 + '}';
    }
}
