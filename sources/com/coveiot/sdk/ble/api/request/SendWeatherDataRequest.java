package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.api.model.SendWeatherModel;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SendWeatherDataRequest extends BaseRequest {
    public String f;
    public List<SendWeatherModel> g;

    public SendWeatherDataRequest(Object obj, String str, List<SendWeatherModel> list) {
        super(obj);
        this.f = str;
        this.g = list;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        CommandBytes commandBytes = new CommandBytes();
        try {
            ArrayList arrayList2 = new ArrayList();
            byte[] bytes = this.f.getBytes(StandardCharsets.UTF_16LE);
            arrayList2.add(Byte.valueOf((byte) this.g.size()));
            arrayList2.add(Byte.valueOf((byte) bytes.length));
            for (byte b : bytes) {
                arrayList2.add(Byte.valueOf(b));
            }
            for (int i = 0; i <= this.g.size() - 1; i++) {
                ByteBuffer allocate = ByteBuffer.allocate(8);
                ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                byte[] array = allocate.order(byteOrder).putLong(this.g.get(i).getTime().longValue()).array();
                byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.g.get(i).getIconId()).array();
                byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.g.get(i).getSunRise()).array();
                byte[] array4 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.g.get(i).getSunSet()).array();
                byte[] array5 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.g.get(i).getWindSpeed()).array();
                byte[] array6 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.g.get(i).getPressure()).array();
                byte[] bytes2 = this.g.get(i).getWeatherText().getBytes(StandardCharsets.UTF_16LE);
                arrayList2.add(Byte.valueOf(array[0]));
                arrayList2.add(Byte.valueOf(array[1]));
                arrayList2.add(Byte.valueOf(array[2]));
                arrayList2.add(Byte.valueOf(array[3]));
                arrayList2.add(Byte.valueOf(array2[0]));
                arrayList2.add(Byte.valueOf(array2[1]));
                arrayList2.add(Byte.valueOf((byte) this.g.get(i).getTemp()));
                arrayList2.add(Byte.valueOf((byte) this.g.get(i).getTempMin()));
                arrayList2.add(Byte.valueOf((byte) this.g.get(i).getTempMax()));
                arrayList2.add(Byte.valueOf(array3[0]));
                arrayList2.add(Byte.valueOf(array3[1]));
                arrayList2.add(Byte.valueOf(array4[0]));
                arrayList2.add(Byte.valueOf(array4[1]));
                arrayList2.add(Byte.valueOf((byte) this.g.get(i).getHumidity()));
                arrayList2.add(Byte.valueOf(array5[0]));
                arrayList2.add(Byte.valueOf(array5[1]));
                arrayList2.add(Byte.valueOf(array6[0]));
                arrayList2.add(Byte.valueOf(array6[1]));
                arrayList2.add(Byte.valueOf((byte) bytes2.length));
                for (byte b2 : bytes2) {
                    arrayList2.add(Byte.valueOf(b2));
                }
            }
            int size = arrayList2.size();
            byte[] bArr = new byte[size];
            for (int i2 = 0; i2 < size; i2++) {
                bArr[i2] = ((Byte) arrayList2.get(i2)).byteValue();
            }
            if (size <= 178) {
                int i3 = size + 4;
                byte[] array7 = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i3).array();
                byte[] bArr2 = new byte[i3];
                System.arraycopy(new byte[]{2, -109, array7[0], array7[1]}, 0, bArr2, 0, 4);
                System.arraycopy(bArr, 0, bArr2, 4, size);
                commandBytes.setCommandData(bArr2);
                arrayList.add(commandBytes);
                return arrayList;
            }
            ArrayList<CommandBytes> generateRequest = MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -109, bArr, new byte[0], false, false);
            this.f7474a = new ExpectedDataSize(generateRequest.size());
            return generateRequest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SEND_WEATHER_DATA;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldSendAllPacketsAtOnce() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldWaitForRes() {
        return true;
    }
}
