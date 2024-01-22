package com.jieli.jl_rcsp.model.base;

import com.jieli.jl_rcsp.util.CHexConver;
/* loaded from: classes11.dex */
public class BasePacket {
    public static final int FALG_HAVE_RESPONSE = 1;
    public static final int FALG_NO_RESPONSE = 0;
    public static final int TYPE_COMMAND = 1;
    public static final int TYPE_RESPONSE = 0;
    private int hasResponse;
    private int opCode;
    private byte[] paramData;
    private int paramLen;
    private int status;
    private int type;
    private int unused;
    private int opCodeSn = -1;
    private int xmOpCode = -1;

    public int getHasResponse() {
        return this.hasResponse;
    }

    public int getOpCode() {
        return this.opCode;
    }

    public int getOpCodeSn() {
        return this.opCodeSn;
    }

    public byte[] getParamData() {
        return this.paramData;
    }

    public int getParamLen() {
        return this.paramLen;
    }

    public int getStatus() {
        return this.status;
    }

    public int getType() {
        return this.type;
    }

    public int getUnused() {
        return this.unused;
    }

    public int getXmOpCode() {
        return this.xmOpCode;
    }

    public BasePacket setHasResponse(int i) {
        this.hasResponse = i;
        return this;
    }

    public BasePacket setOpCode(int i) {
        this.opCode = i;
        return this;
    }

    public BasePacket setOpCodeSn(int i) {
        this.opCodeSn = i;
        return this;
    }

    public BasePacket setParamData(byte[] bArr) {
        this.paramData = bArr;
        return this;
    }

    public BasePacket setParamLen(int i) {
        this.paramLen = i;
        return this;
    }

    public BasePacket setStatus(int i) {
        this.status = i;
        return this;
    }

    public BasePacket setType(int i) {
        this.type = i;
        return this;
    }

    public BasePacket setXmOpCode(int i) {
        this.xmOpCode = i;
        return this;
    }

    public String toString() {
        return "BasePacket{type=" + this.type + ", hasResponse=" + this.hasResponse + ", unused=" + this.unused + ", opCode=" + this.opCode + ", paramLen=" + this.paramLen + ", status=" + this.status + ", opCodeSn=" + this.opCodeSn + ", xmOpCode=" + this.xmOpCode + ", paramData=" + CHexConver.byte2HexStr(this.paramData) + '}';
    }
}
