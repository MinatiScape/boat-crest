package com.jieli.jl_rcsp.model.command.watch;

import com.jieli.jl_rcsp.model.base.BaseParameter;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.model.base.CommonResponse;
import com.jieli.jl_rcsp.util.CHexConver;
import java.nio.ByteBuffer;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class SportsInfoStatusSyncCmd extends CommandBase<Param, Response> {
    public static final byte OP_APP_STOP_SPORTS = 2;
    public static final byte OP_FIRMWARE_STOP_SPORTS = 3;
    public static final byte OP_PAUSE_SPORTS = 4;
    public static final byte OP_READ_REAL_DATA = 6;
    public static final byte OP_READ_SPORTS_INFO = 0;
    public static final byte OP_RESUME_SPORTS = 5;
    public static final byte OP_SET_REAL_DATA_READ_INTERVAL = 7;
    public static final byte OP_START_SPORTS = 1;
    public static final byte SPORTS_STATUS_RUNNING = 1;
    public static final byte SPORTS_STATUS_STOP = 0;
    public static final byte SPORTS_TYPE_INDOOR = 2;
    public static final byte SPORTS_TYPE_OUTDOOR = 1;

    /* loaded from: classes11.dex */
    public static class AppStopSportsParam extends Param {
        public AppStopSportsParam() {
            super((byte) 2, new byte[0]);
        }
    }

    /* loaded from: classes11.dex */
    public static class AppStopSportsResponse extends Response {
        public short fileId;
        public int fileSize;
        public int restoreTime;
        public int stopTime;
        public byte[] strengthIntervalTimer;

        public AppStopSportsResponse(byte[] bArr) {
            this.stopTime = CHexConver.bytesToInt(bArr, 0, 4);
            this.restoreTime = CHexConver.bytesToInt(bArr, 4, 4);
            this.fileId = CHexConver.bytesToShort(bArr[8], bArr[9]);
            this.fileSize = CHexConver.bytesToInt(bArr[10], bArr[11]);
            this.strengthIntervalTimer = ByteBuffer.allocate(bArr.length - 8).put(bArr, 12, bArr.length - 12).array();
        }

        @Override // com.jieli.jl_rcsp.model.base.CommonResponse
        public String toString() {
            return "AppStopExerciseResponse{stopTime=" + this.stopTime + ", restoreTime=" + this.restoreTime + ", strengthIntervalTimer=" + Arrays.toString(this.strengthIntervalTimer) + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class FirmwareStopSportsParam extends Param {
        public short fileId;
        public int fileSize;
        public int restoreTime;
        public int stopTime;
        public byte[] strengthIntervalTimer;

        public FirmwareStopSportsParam(byte[] bArr) {
            super((byte) 3, bArr);
            this.stopTime = CHexConver.bytesToInt(bArr, 0, 4);
            this.restoreTime = CHexConver.bytesToInt(bArr, 4, 4);
            this.fileId = CHexConver.bytesToShort(bArr[8], bArr[9]);
            this.fileSize = CHexConver.bytesToInt(bArr[10], bArr[11]);
            this.strengthIntervalTimer = ByteBuffer.allocate(bArr.length - 8).put(bArr, 12, bArr.length - 12).array();
        }
    }

    /* loaded from: classes11.dex */
    public static class Param extends BaseParameter {
        private final byte[] data;
        private final byte op;

        public Param(byte b, byte[] bArr) {
            this.op = b;
            this.data = bArr == null ? new byte[0] : bArr;
        }

        public byte[] getData() {
            return this.data;
        }

        public byte getOp() {
            return this.op;
        }

        @Override // com.jieli.jl_rcsp.model.base.BaseParameter, com.jieli.jl_rcsp.interfaces.cmd.IParamBase
        public byte[] getParamData() {
            return ByteBuffer.allocate(this.data.length + 1).put(this.op).put(this.data).array();
        }
    }

    /* loaded from: classes11.dex */
    public static class PauseSportsParam extends Param {
        public PauseSportsParam() {
            super((byte) 4, new byte[0]);
        }
    }

    /* loaded from: classes11.dex */
    public static class ReadRealDataParam extends Param {
        public ReadRealDataParam() {
            super((byte) 6, new byte[0]);
        }
    }

    /* loaded from: classes11.dex */
    public static class ReadRealDataResponse extends Response {
        public float distance;
        public int duration;
        public int exerciseStatus;
        public int heartRate;
        public int kcal;
        public float pace;
        public float speed;
        public int step;
        public int stepFreq;
        public int stride;
        public byte version;

        public ReadRealDataResponse(byte[] bArr) {
            this.version = bArr[0];
            this.step = CHexConver.bytesToInt(bArr, 1, 4);
            this.distance = (CHexConver.bytesToInt(bArr, 5, 2) & 65535) * 0.01f;
            this.duration = CHexConver.bytesToInt(bArr, 7, 4);
            float bytesToInt = (CHexConver.bytesToInt(bArr, 11, 2) & 255) * 0.01f;
            this.speed = bytesToInt;
            this.pace = bytesToInt != 0.0f ? (1.0f / bytesToInt) * 3600.0f : 0.0f;
            this.kcal = CHexConver.bytesToInt(bArr, 13, 2) & 255;
            this.stepFreq = CHexConver.bytesToInt(bArr, 15, 2) & 255;
            this.stride = CHexConver.bytesToInt(bArr, 17, 2) & 255;
            this.exerciseStatus = bArr[19];
            this.heartRate = bArr[20] & 255;
        }

        @Override // com.jieli.jl_rcsp.model.base.CommonResponse
        public String toString() {
            return "UpdateRealDataParam{version=" + ((int) this.version) + ", step=" + this.step + ", distance=" + this.distance + ", duration=" + this.duration + ", speed=" + this.speed + ", pace=" + this.pace + ", kcal=" + this.kcal + ", stepFreq=" + this.stepFreq + ", stride=" + this.stride + ", exerciseStatus=" + this.exerciseStatus + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class ReadSportsInfoParam extends Param {
        public ReadSportsInfoParam() {
            super((byte) 0, null);
        }
    }

    /* loaded from: classes11.dex */
    public static class ReadSportsInfoResponse extends Response {
        public boolean hasGps;
        public byte heartRateMode;
        public int id;
        public int readReadDataInterval;
        public byte status;
        public byte type;

        public ReadSportsInfoResponse(byte[] bArr) {
            this.type = bArr[0];
            this.status = bArr[1];
            this.id = CHexConver.bytesToInt(bArr, 2, 4);
            this.hasGps = bArr[6] == 1;
            this.heartRateMode = bArr[7];
            this.readReadDataInterval = CHexConver.bytesToInt(bArr, 8, 2);
        }

        @Override // com.jieli.jl_rcsp.model.base.CommonResponse
        public String toString() {
            return "ReadExerciseInfoResponse{mode=" + ((int) this.type) + ", status=" + ((int) this.status) + ", id=" + this.id + ", hasGps=" + this.hasGps + ", heartRateMode=" + ((int) this.heartRateMode) + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Response extends CommonResponse {
    }

    /* loaded from: classes11.dex */
    public static class ResumeSportsParam extends Param {
        public ResumeSportsParam() {
            super((byte) 5, new byte[0]);
        }
    }

    /* loaded from: classes11.dex */
    public static class SetReadRealDataIntervalParam extends Param {
        public SetReadRealDataIntervalParam(short s) {
            super((byte) 7, CHexConver.shortToBigBytes(s));
        }
    }

    /* loaded from: classes11.dex */
    public static class StartSportsParam extends Param {
        public byte type;

        public StartSportsParam(byte b) {
            super((byte) 1, new byte[]{b});
            this.type = b;
        }
    }

    public SportsInfoStatusSyncCmd(Param param) {
        this(2, param);
    }

    public SportsInfoStatusSyncCmd(int i, Param param) {
        super(166, SportsInfoStatusSyncCmd.class.getSimpleName(), i);
        setParam(param);
    }
}
