package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.model.AppAutoActivityDetectionModel;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetAutoActivityDetectionSettingsReq extends HybridCommandRequest {
    public AppAutoActivityDetectionModel h;

    public SetAutoActivityDetectionSettingsReq(Object obj, short s, AppAutoActivityDetectionModel appAutoActivityDetectionModel) {
        super(obj, s);
        this.h = null;
        this.h = appAutoActivityDetectionModel;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        if (this.h != null) {
            List<Byte> arrayList2 = new ArrayList<>();
            byte[] bArr = {0, 0, 0, 0};
            if (this.h.getActivities() != null && this.h.getActivities().length > 0) {
                int i = 0;
                int i2 = 0;
                char c = 0;
                while (i < this.h.getActivities().length) {
                    char c2 = c;
                    if (i == 8) {
                        i2 = 0;
                        c2 = 1;
                    }
                    char c3 = c2;
                    if (i == 16) {
                        i2 = 0;
                        c3 = 2;
                    }
                    char c4 = c3;
                    if (i == 24) {
                        i2 = 0;
                        c4 = 3;
                    }
                    if (this.h.getActivities()[i] == 1) {
                        if (i2 == 0) {
                            bArr[c4] = (byte) (bArr[c4] + 1);
                        } else if (i2 == 1) {
                            bArr[c4] = (byte) (bArr[c4] + 2);
                        } else if (i2 == 2) {
                            bArr[c4] = (byte) (bArr[c4] + 4);
                        } else if (i2 == 3) {
                            bArr[c4] = (byte) (bArr[c4] + 8);
                        } else if (i2 == 4) {
                            bArr[c4] = (byte) (bArr[c4] + 16);
                        } else if (i2 == 5) {
                            bArr[c4] = (byte) (bArr[c4] + 32);
                        } else if (i2 == 6) {
                            bArr[c4] = (byte) (bArr[c4] + 64);
                        } else if (i2 == 7) {
                            bArr[c4] = (byte) (bArr[c4] + 128);
                        }
                    }
                    i2++;
                    i++;
                    c = c4;
                }
            }
            byte b = this.h.getSundayEnabled().booleanValue() ? (byte) 1 : (byte) 0;
            if (this.h.getMondayEnabled().booleanValue()) {
                b = (byte) (b + 2);
            }
            if (this.h.getTuesdayEnabled().booleanValue()) {
                b = (byte) (b + 4);
            }
            if (this.h.getWednesdayEnabled().booleanValue()) {
                b = (byte) (b + 8);
            }
            if (this.h.getThursdayEnabled().booleanValue()) {
                b = (byte) (b + 16);
            }
            if (this.h.getFridayEnabled().booleanValue()) {
                b = (byte) (b + 32);
            }
            if (this.h.getSaturdayEnabled().booleanValue()) {
                b = (byte) (b + 64);
            }
            byte byteValue = this.h.getCountDownValue().byteValue();
            boolean booleanValue = this.h.getVibrationEnabled().booleanValue();
            ArrayList arrayList3 = new ArrayList();
            if (this.h.getPeriods() != null && this.h.getPeriods().size() > 0) {
                arrayList3.add(Byte.valueOf((byte) this.h.getPeriods().size()));
                for (int i3 = 0; i3 < this.h.getPeriods().size(); i3++) {
                    AppAutoActivityDetectionModel.Period period = this.h.getPeriods().get(i3);
                    ByteBuffer allocate = ByteBuffer.allocate(4);
                    ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                    byte[] array = allocate.order(byteOrder).putInt(period.getStartTime().intValue()).array();
                    byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(period.getEndTime().intValue()).array();
                    arrayList3.add(Byte.valueOf(array[0]));
                    arrayList3.add(Byte.valueOf(array[1]));
                    arrayList3.add(Byte.valueOf(array2[0]));
                    arrayList3.add(Byte.valueOf(array2[1]));
                }
            } else {
                arrayList3.add((byte) 0);
            }
            for (int i4 = 0; i4 < 4; i4++) {
                arrayList2.add(Byte.valueOf(bArr[i4]));
            }
            arrayList2.add(Byte.valueOf(b));
            arrayList2.add(Byte.valueOf(byteValue));
            arrayList2.add(Byte.valueOf(booleanValue ? (byte) 1 : (byte) 0));
            for (int i5 = 0; i5 < arrayList3.size(); i5++) {
                arrayList2.add(arrayList3.get(i5));
            }
            setPayload(arrayList2);
            List<Byte> payload = super.getPayload();
            ArrayList arrayList4 = new ArrayList();
            arrayList4.add((byte) 1);
            arrayList4.add((byte) -85);
            byte[] array3 = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(payload.size() + 4).array();
            arrayList4.add(Byte.valueOf(array3[0]));
            arrayList4.add(Byte.valueOf(array3[1]));
            arrayList4.addAll(payload);
            byte[] bArr2 = new byte[arrayList4.size()];
            for (int i6 = 0; i6 < arrayList4.size(); i6++) {
                bArr2[i6] = ((Byte) arrayList4.get(i6)).byteValue();
            }
            CommandBytes commandBytes = new CommandBytes();
            commandBytes.setCommandData(bArr2);
            arrayList.add(commandBytes);
        }
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_AUTO_ACTIVITY_DETECTION_SETTINGS;
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
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }
}
