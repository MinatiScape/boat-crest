package com.jieli.jl_rcsp.model.command.file_op;

import com.jieli.jl_rcsp.model.base.BaseParameter;
import com.jieli.jl_rcsp.model.base.CommandWithParamAndResponse;
import com.jieli.jl_rcsp.model.base.CommonResponse;
import com.jieli.jl_rcsp.util.CHexConver;
import java.nio.ByteBuffer;
/* loaded from: classes11.dex */
public class DeviceExtendParamCmd extends CommandWithParamAndResponse<Param, Response> {
    public static final byte OP_DELETE_FILE = 1;
    public static final byte OP_FILE_TRANSFER = 0;
    public static final byte OP_READ_FILE = 2;

    /* loaded from: classes11.dex */
    public static class DeleteFileParam extends DevParam {
        public DeleteFileParam(int i) {
            super((byte) 1, i);
        }
    }

    /* loaded from: classes11.dex */
    public static class DevParam extends Param {
        public int dev;

        public DevParam(byte b, int i) {
            super(b);
            this.dev = i;
        }

        @Override // com.jieli.jl_rcsp.model.command.file_op.DeviceExtendParamCmd.Param
        public byte[] toData() {
            return ByteBuffer.allocate(5).put(super.toData()).put(CHexConver.intToBigBytes(this.dev)).array();
        }
    }

    /* loaded from: classes11.dex */
    public static class FileTransferParam extends DevParam {
        public boolean hasCrc16;

        public FileTransferParam(int i, boolean z) {
            super((byte) 0, i);
            this.hasCrc16 = z;
        }

        @Override // com.jieli.jl_rcsp.model.command.file_op.DeviceExtendParamCmd.DevParam, com.jieli.jl_rcsp.model.command.file_op.DeviceExtendParamCmd.Param
        public byte[] toData() {
            return ByteBuffer.allocate(6).put(super.toData()).put(this.hasCrc16 ? (byte) 1 : (byte) 0).array();
        }
    }

    /* loaded from: classes11.dex */
    public static class FileTransferResponse extends Response {
        public boolean hasCrc16;

        public FileTransferResponse(boolean z) {
            super((byte) 0);
            this.hasCrc16 = z;
        }
    }

    /* loaded from: classes11.dex */
    public static class Param extends BaseParameter {
        public byte op;

        public Param(byte b) {
            this.op = b;
        }

        @Override // com.jieli.jl_rcsp.model.base.BaseParameter, com.jieli.jl_rcsp.interfaces.cmd.IParamBase
        public byte[] getParamData() {
            return toData();
        }

        public byte[] toData() {
            return ByteBuffer.allocate(1).put(this.op).array();
        }
    }

    /* loaded from: classes11.dex */
    public static class ReadFileParam extends DevParam {
        public ReadFileParam(int i) {
            super((byte) 2, i);
        }
    }

    /* loaded from: classes11.dex */
    public static class Response extends CommonResponse {
        public byte op;

        public Response(byte b) {
            this.op = b;
        }
    }

    public DeviceExtendParamCmd(Param param) {
        super(39, DeviceExtendParamCmd.class.getSimpleName(), param);
    }
}
