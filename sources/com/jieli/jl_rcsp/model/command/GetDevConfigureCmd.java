package com.jieli.jl_rcsp.model.command;

import com.jieli.jl_rcsp.constant.Command;
import com.jieli.jl_rcsp.model.base.CommandWithResponse;
import com.jieli.jl_rcsp.model.base.CommonResponse;
import com.jieli.jl_rcsp.util.CHexConver;
/* loaded from: classes11.dex */
public class GetDevConfigureCmd extends CommandWithResponse<Response> {

    /* loaded from: classes11.dex */
    public static class Response extends CommonResponse {
        private byte[] data;
        private int productFlag;
        private int version;

        public byte[] getData() {
            return this.data;
        }

        public int getProductFlag() {
            return this.productFlag;
        }

        public int getVersion() {
            return this.version;
        }

        public void parseData(byte[] bArr) {
            if (bArr == null || bArr.length < 2) {
                return;
            }
            setRawData(bArr);
            setProductFlag(CHexConver.byteToInt(bArr[0]));
            setVersion(CHexConver.byteToInt(bArr[1]));
            int length = bArr.length - 2;
            if (length > 0) {
                byte[] bArr2 = new byte[length];
                System.arraycopy(bArr, 2, bArr2, 0, length);
                setData(bArr2);
            }
        }

        public void setData(byte[] bArr) {
            this.data = bArr;
        }

        public void setProductFlag(int i) {
            this.productFlag = i;
        }

        public void setVersion(int i) {
            this.version = i;
        }
    }

    public GetDevConfigureCmd() {
        super(Command.CMD_GET_DEVICE_CONFIG_INFO, GetDevConfigureCmd.class.getSimpleName());
    }
}
