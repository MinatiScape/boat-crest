package com.jieli.jl_rcsp.model.command.watch;

import com.jieli.jl_rcsp.model.base.BaseParameter;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.base.CommonResponse;
import com.jieli.jl_rcsp.util.CHexConver;
/* loaded from: classes11.dex */
public class RequestHealthDataCmd extends CommandBase<Param, Response> {

    /* loaded from: classes11.dex */
    public static class Param extends BaseParameter {
        public final int mask;
        public final byte[] subMask;
        public final byte version;

        public Param(int i, byte b, byte... bArr) {
            checkMaskAndSubMaskRelation(i, bArr);
            this.mask = i;
            this.version = b;
            this.subMask = bArr;
        }

        private void checkMaskAndSubMaskRelation(int i, byte[] bArr) {
            int i2 = 0;
            for (int i3 = 0; i3 < 32; i3++) {
                if (CHexConver.getBitByPosition(i, i3) == 1) {
                    i2++;
                }
            }
            if (i2 != 0) {
                if (bArr.length != i2) {
                    throw new IllegalArgumentException("mask set count not equal to subMask length");
                }
                return;
            }
            throw new IllegalArgumentException("mask must not be 0x00");
        }

        @Override // com.jieli.jl_rcsp.model.base.BaseParameter, com.jieli.jl_rcsp.interfaces.cmd.IParamBase
        public byte[] getParamData() {
            byte[] bArr = new byte[this.subMask.length + 5];
            byte[] intToBigBytes = CHexConver.intToBigBytes(this.mask);
            System.arraycopy(intToBigBytes, 0, bArr, 0, intToBigBytes.length);
            bArr[4] = this.version;
            byte[] bArr2 = this.subMask;
            System.arraycopy(bArr2, 0, bArr, 5, bArr2.length);
            return bArr;
        }
    }

    /* loaded from: classes11.dex */
    public static class Response extends CommonResponse {
        public final byte[] data;
        public final byte flag;
        public final byte ret;

        public Response(byte b, byte b2, byte[] bArr) {
            this.ret = b;
            this.flag = b2;
            this.data = bArr;
        }

        public byte[] getData() {
            return this.data;
        }

        public byte getRet() {
            return this.ret;
        }
    }

    public RequestHealthDataCmd(Param param) {
        this(2, param);
    }

    public RequestHealthDataCmd(int i, Param param) {
        super(160, RequestHealthDataCmd.class.getSimpleName(), i);
        setParam(param);
    }
}
