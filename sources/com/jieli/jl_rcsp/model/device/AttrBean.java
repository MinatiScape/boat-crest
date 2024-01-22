package com.jieli.jl_rcsp.model.device;

import com.jieli.jl_rcsp.util.CHexConver;
/* loaded from: classes11.dex */
public class AttrBean {
    private byte[] attrData;
    private byte type;

    public byte[] getAttrData() {
        return this.attrData;
    }

    public byte[] getData() {
        byte[] bArr = this.attrData;
        if (bArr == null || bArr.length == 0) {
            return new byte[]{1, this.type};
        }
        int length = bArr.length + 2;
        byte[] bArr2 = new byte[length];
        bArr2[0] = (byte) (length - 1);
        bArr2[1] = this.type;
        System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
        return bArr2;
    }

    public byte getType() {
        return this.type;
    }

    public AttrBean setAttrData(byte[] bArr) {
        this.attrData = bArr;
        return this;
    }

    public AttrBean setType(byte b) {
        this.type = b;
        return this;
    }

    public String toString() {
        return "AttrBean{type=" + ((int) this.type) + ", attrData=" + CHexConver.byte2HexStr(this.attrData) + '}';
    }
}
