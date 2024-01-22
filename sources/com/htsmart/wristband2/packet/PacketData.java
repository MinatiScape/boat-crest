package com.htsmart.wristband2.packet;

import com.clevertap.android.sdk.Constants;
import com.htsmart.wristband2.utils.BytesUtil;
/* loaded from: classes11.dex */
public class PacketData {

    /* renamed from: a  reason: collision with root package name */
    public byte f12025a;
    public byte b;
    public byte[] c;

    public PacketData() {
    }

    public PacketData(byte b, byte b2) {
        this.f12025a = b;
        this.b = b2;
    }

    public PacketData(byte b, byte b2, byte[] bArr) {
        this.f12025a = b;
        this.b = b2;
        this.c = bArr;
    }

    public byte getCmdId() {
        return this.f12025a;
    }

    public byte[] getKeyData() {
        return this.c;
    }

    public byte getKeyId() {
        return this.b;
    }

    public void setCmdId(byte b) {
        this.f12025a = b;
    }

    public void setKeyData(byte[] bArr) {
        this.c = bArr;
    }

    public void setKeyId(byte b) {
        this.b = b;
    }

    public String toString() {
        return "[" + ((int) this.f12025a) + Constants.SEPARATOR_COMMA + ((int) this.b) + "]:" + BytesUtil.bytes2HexStr(this.c);
    }
}
