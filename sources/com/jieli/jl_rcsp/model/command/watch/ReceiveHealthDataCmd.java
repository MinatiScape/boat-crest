package com.jieli.jl_rcsp.model.command.watch;

import com.jieli.jl_rcsp.model.base.BaseParameter;
import com.jieli.jl_rcsp.model.base.CommandWithParam;
/* loaded from: classes11.dex */
public class ReceiveHealthDataCmd extends CommandWithParam<Param> {

    /* loaded from: classes11.dex */
    public static class Param extends BaseParameter {
        public byte[] data;
        public byte packageCount;
        public byte packageId;
        public byte version;

        public Param(byte[] bArr) {
            if (bArr == null || bArr.length <= 0) {
                return;
            }
            this.version = bArr[0];
            this.packageCount = bArr[1];
            this.packageId = bArr[2];
            int length = bArr.length - 3;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 3, bArr2, 0, length);
            this.data = bArr2;
        }
    }

    public ReceiveHealthDataCmd(Param param) {
        super(162, ReceiveHealthDataCmd.class.getSimpleName(), param);
    }
}
