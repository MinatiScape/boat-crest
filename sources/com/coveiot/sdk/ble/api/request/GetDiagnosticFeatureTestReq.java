package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.model.BleWatchDiagnosticFeature;
import com.coveiot.sdk.ble.api.model.BleWatchDiagnosticFeatureType;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class GetDiagnosticFeatureTestReq extends BaseRequest {
    public BleWatchDiagnosticFeature f;

    public GetDiagnosticFeatureTestReq(Object obj, BleWatchDiagnosticFeature bleWatchDiagnosticFeature) {
        super(obj);
        this.f = bleWatchDiagnosticFeature;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        byte[] bArr;
        ArrayList arrayList = new ArrayList();
        BleWatchDiagnosticFeature bleWatchDiagnosticFeature = this.f;
        if (bleWatchDiagnosticFeature != null && bleWatchDiagnosticFeature.getmFeatureType() != null) {
            if (this.f.getmFeatureType() == BleWatchDiagnosticFeatureType.CHARGING_TEST) {
                bArr = new byte[]{6, 8, 7, 0, 65, (byte) this.f.getmFeatureType().getId(), (byte) this.f.getTimeout()};
            } else if (this.f.getmFeatureType() == BleWatchDiagnosticFeatureType.DISPLAY_TEST) {
                byte[] array = ByteBuffer.allocate(2).putShort((short) this.f.getColor()).array();
                byte[] bArr2 = {6, 8, 8, 0, 65, (byte) this.f.getmFeatureType().getId(), 0, 0};
                bArr2[6] = array[1];
                bArr2[7] = array[0];
                bArr = bArr2;
            } else if (this.f.getmFeatureType() == BleWatchDiagnosticFeatureType.VIBRATION_TEST) {
                bArr = new byte[]{6, 8, 7, 0, 65, (byte) this.f.getmFeatureType().getId(), (byte) this.f.getVibrationCount()};
            } else if (this.f.getmFeatureType() == BleWatchDiagnosticFeatureType.BUTTON_TEST) {
                bArr = new byte[]{6, 8, 8, 0, 65, (byte) this.f.getmFeatureType().getId(), (byte) this.f.getButtonPosition(), (byte) this.f.getTimeout()};
            } else if (this.f.getmFeatureType() == BleWatchDiagnosticFeatureType.TOUCHSCREEN_TEST) {
                bArr = new byte[]{6, 8, 7, 0, 65, (byte) this.f.getmFeatureType().getId(), (byte) this.f.getTimeout()};
            } else {
                bArr = this.f.getmFeatureType() == BleWatchDiagnosticFeatureType.SENSOR_TEST ? new byte[]{6, 8, 8, 0, 65, (byte) this.f.getmFeatureType().getId(), (byte) this.f.getSensorType(), (byte) this.f.getTimeout()} : new byte[]{6, 8, 8, 0, 65, (byte) BleWatchDiagnosticFeatureType.RESERVED.getId()};
            }
            CommandBytes commandBytes = new CommandBytes();
            commandBytes.setCommandData(bArr);
            arrayList.add(commandBytes);
        }
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.GET_DIAGNOSTIC_FEATURE_TEST;
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
        return false;
    }
}
