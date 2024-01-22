package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SaveBpCalibrationReq extends BaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7525a;
        public int b;
        public int c;
        public int d;
        public int e;

        public Builder(int i, int i2, int i3, int i4) {
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }

        public SaveBpCalibrationReq build() {
            SaveBpCalibrationReq saveBpCalibrationReq = new SaveBpCalibrationReq(this.f7525a);
            saveBpCalibrationReq.f = this.b;
            saveBpCalibrationReq.g = this.c;
            saveBpCalibrationReq.h = this.d;
            saveBpCalibrationReq.i = this.e;
            return saveBpCalibrationReq;
        }
    }

    public SaveBpCalibrationReq(Object obj) {
        super(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_BP_CALIBRATIOIN;
        byte[] bArr2 = {(byte) getDbpCalculatingSign(), (byte) getDbp(), (byte) getSbpCalculatingSign(), (byte) getSbp()};
        byte[] bArr3 = new byte[bArr.length + 4];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, 4);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr3);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_BP_CALIBRATIOIN;
    }

    public int getDbp() {
        return this.i;
    }

    public int getDbpCalculatingSign() {
        return this.h;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getSbp() {
        return this.g;
    }

    public int getSbpCalculatingSign() {
        return this.f;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    public void setDbp(int i) {
        this.i = i;
    }

    public void setDbpCalculatingSign(int i) {
        this.h = i;
    }

    public void setSbb(int i) {
        this.g = i;
    }

    public void setSbpCalculatingSign(int i) {
        this.f = i;
    }
}
