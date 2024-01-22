package com.jieli.jl_rcsp.model.command.watch;

import com.jieli.jl_rcsp.model.base.BaseParameter;
import com.jieli.jl_rcsp.model.base.CommandWithParamAndResponse;
import com.jieli.jl_rcsp.model.base.CommonResponse;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.RcspUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes11.dex */
public class PushInfoDataToDeviceCmd extends CommandWithParamAndResponse<Param, Response> {

    /* loaded from: classes11.dex */
    public static abstract class BaseInfo {
        private final byte type;
        private final byte version;

        public BaseInfo(byte b, byte b2) {
            this.type = b;
            this.version = b2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] toParamData() {
            byte[] beanToData = beanToData();
            byte[] bArr = new byte[beanToData.length + 2];
            bArr[0] = this.type;
            bArr[1] = this.version;
            System.arraycopy(beanToData, 0, bArr, 2, beanToData.length);
            return bArr;
        }

        public abstract byte[] beanToData();

        public byte getType() {
            return this.type;
        }

        public byte getVersion() {
            return this.version;
        }
    }

    /* loaded from: classes11.dex */
    public static class MessageData extends BaseInfo {
        private final byte[] data;
        private final int seq;

        public MessageData(int i, byte[] bArr) {
            super((byte) 2, (byte) 0);
            this.seq = i;
            this.data = bArr;
        }

        @Override // com.jieli.jl_rcsp.model.command.watch.PushInfoDataToDeviceCmd.BaseInfo
        public byte[] beanToData() {
            byte[] bArr = this.data;
            int length = bArr == null ? 0 : bArr.length;
            byte[] bArr2 = new byte[length + 1];
            bArr2[0] = (byte) this.seq;
            if (length > 0) {
                System.arraycopy(bArr, 0, bArr2, 1, length);
            }
            return bArr2;
        }

        public byte[] getData() {
            return this.data;
        }

        public int getSeq() {
            return this.seq;
        }
    }

    /* loaded from: classes11.dex */
    public static class Param<T extends BaseInfo> extends BaseParameter {
        private final BaseInfo baseInfo;

        public Param(BaseInfo baseInfo) {
            this.baseInfo = baseInfo;
        }

        @Override // com.jieli.jl_rcsp.model.base.BaseParameter, com.jieli.jl_rcsp.interfaces.cmd.IParamBase
        public byte[] getParamData() {
            BaseInfo baseInfo = this.baseInfo;
            return baseInfo == null ? new byte[0] : baseInfo.toParamData();
        }
    }

    /* loaded from: classes11.dex */
    public static class RemoveMsg extends BaseInfo {
        private final byte[] appPackageName;
        private final byte[] timestamp;

        public RemoveMsg(String str, long j) {
            super((byte) 3, (byte) 0);
            this.appPackageName = RcspUtil.getData(str, 31);
            this.timestamp = CHexConver.intToBigBytes(RcspUtil.time2Int(j));
        }

        @Override // com.jieli.jl_rcsp.model.command.watch.PushInfoDataToDeviceCmd.BaseInfo
        public byte[] beanToData() {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(RcspUtil.packLTVPacket2(0, this.timestamp));
                byteArrayOutputStream.write(RcspUtil.packLTVPacket2(1, this.appPackageName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return byteArrayOutputStream.toByteArray();
        }

        public byte[] getAppPackageName() {
            return this.appPackageName;
        }

        public byte[] getTimestamp() {
            return this.timestamp;
        }
    }

    /* loaded from: classes11.dex */
    public static class Response extends CommonResponse {
        public final int reason;
        public final byte type;

        public Response(byte b, int i) {
            this.reason = i;
            this.type = b;
        }

        public int getReason() {
            return this.reason;
        }

        public byte getType() {
            return this.type;
        }
    }

    /* loaded from: classes11.dex */
    public static class Weather extends BaseInfo {
        private final String city;
        private final byte humidity;
        private final String province;
        private final byte temperature;
        private final long time;
        private final byte weather;
        private final byte windDirection;
        private final byte windPower;

        public Weather(String str, String str2, byte b, byte b2, byte b3, byte b4, byte b5, long j) {
            super((byte) 1, (byte) 0);
            this.province = str;
            this.city = str2;
            this.weather = b;
            this.temperature = b2;
            this.humidity = b3;
            this.windDirection = b4;
            this.windPower = b5;
            this.time = j;
        }

        @Override // com.jieli.jl_rcsp.model.command.watch.PushInfoDataToDeviceCmd.BaseInfo
        public byte[] beanToData() {
            byte[] bytes = this.city.getBytes();
            byte[] bytes2 = this.province.getBytes();
            ByteBuffer allocate = ByteBuffer.allocate(bytes.length + bytes2.length + 20);
            allocate.put((byte) bytes2.length);
            allocate.put(bytes2);
            allocate.put((byte) bytes.length);
            allocate.put(bytes);
            allocate.put(this.weather);
            allocate.put(this.temperature);
            allocate.put(this.humidity);
            allocate.put(this.windDirection);
            allocate.put(this.windPower);
            allocate.put(CHexConver.intToBigBytes(RcspUtil.time2Int(this.time)));
            byte[] bArr = new byte[allocate.position()];
            allocate.flip();
            allocate.get(bArr);
            return bArr;
        }

        public String getCity() {
            return this.city;
        }

        public byte getHumidity() {
            return this.humidity;
        }

        public String getProvince() {
            return this.province;
        }

        public byte getTemperature() {
            return this.temperature;
        }

        public long getTime() {
            return this.time;
        }

        public byte getWeather() {
            return this.weather;
        }

        public byte getWindDirection() {
            return this.windDirection;
        }

        public byte getWindPower() {
            return this.windPower;
        }

        public String toString() {
            return "Weather{province=" + this.province + ", city=" + this.city + ", weather=" + ((int) this.weather) + ", temperature=" + ((int) this.temperature) + ", humidity=" + ((int) this.humidity) + ", windDirection=" + ((int) this.windDirection) + ", windPower=" + ((int) this.windPower) + ", time=" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(this.time)) + '}';
        }
    }

    public PushInfoDataToDeviceCmd(Param param) {
        super(161, PushInfoDataToDeviceCmd.class.getSimpleName(), param);
    }
}
