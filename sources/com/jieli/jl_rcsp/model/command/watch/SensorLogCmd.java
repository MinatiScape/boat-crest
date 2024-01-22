package com.jieli.jl_rcsp.model.command.watch;

import com.jieli.jl_rcsp.model.base.BaseParameter;
import com.jieli.jl_rcsp.model.base.CommandWithParam;
import com.jieli.jl_rcsp.util.CHexConver;
/* loaded from: classes11.dex */
public class SensorLogCmd extends CommandWithParam<Param> {
    public SensorLogCmd(Param param) {
        super(163, SensorLogCmd.class.getSimpleName(), param);
    }

    /* loaded from: classes11.dex */
    public static class Param extends BaseParameter {
        public byte[] data;
        private int mask;
        public int type;

        public Param(byte[] bArr) {
            if (bArr == null || bArr.length <= 0) {
                return;
            }
            this.type = CHexConver.bytesToInt(bArr, 0, 4);
            int length = bArr.length - 4;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 4, bArr2, 0, length);
            this.data = bArr2;
        }

        @Override // com.jieli.jl_rcsp.model.base.BaseParameter, com.jieli.jl_rcsp.interfaces.cmd.IParamBase
        public byte[] getParamData() {
            return CHexConver.intToBigBytes(this.mask);
        }

        public Param(int i) {
            this.mask = i;
        }
    }
}
