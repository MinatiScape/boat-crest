package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class BandDisplaySettingsReq extends BaseRequest {
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7473a;
        public boolean b = true;
        public boolean c = true;
        public boolean d = true;
        public boolean e = true;
        public boolean f = true;
        public boolean g = true;
        public boolean h = true;
        public boolean i = true;

        public BandDisplaySettingsReq build() {
            BandDisplaySettingsReq bandDisplaySettingsReq = new BandDisplaySettingsReq(this.f7473a);
            bandDisplaySettingsReq.g = this.c;
            bandDisplaySettingsReq.h = this.d;
            bandDisplaySettingsReq.l = this.h;
            bandDisplaySettingsReq.f = this.b;
            bandDisplaySettingsReq.i = this.e;
            bandDisplaySettingsReq.j = this.f;
            bandDisplaySettingsReq.m = this.i;
            bandDisplaySettingsReq.k = this.g;
            return bandDisplaySettingsReq;
        }

        public Builder setId(Object obj) {
            this.f7473a = obj;
            return this;
        }

        public Builder shouldShowActiviteTime(boolean z) {
            this.f = z;
            return this;
        }

        public Builder shouldShowActivityProgress(boolean z) {
            this.g = z;
            return this;
        }

        public Builder shouldShowAlarm(boolean z) {
            this.i = z;
            return this;
        }

        public Builder shouldShowCaloriesCount(boolean z) {
            this.d = z;
            return this;
        }

        public Builder shouldShowDateAndTime(boolean z) {
            this.b = z;
            return this;
        }

        public Builder shouldShowDistance(boolean z) {
            this.e = z;
            return this;
        }

        public Builder shouldShowEmotion(boolean z) {
            this.h = z;
            return this;
        }

        public Builder shouldShowStepCount(boolean z) {
            this.c = z;
            return this;
        }
    }

    public BandDisplaySettingsReq(Object obj) {
        super(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        byte[] bArr = BleUUID.SET_BAND_SETTINGS;
        ArrayList arrayList = new ArrayList();
        byte b = isShowDateAndTime() ? (byte) 3 : (byte) 1;
        if (isShowStepCount()) {
            b = (byte) (b + 4);
        }
        if (isShowCaloriesCount()) {
            b = (byte) (b + 8);
        }
        if (isShowDistance()) {
            b = (byte) (b + 16);
        }
        if (isShowActiveTime()) {
            b = (byte) (b + 32);
        }
        if (isShowActivityProgress()) {
            b = (byte) (b + 64);
        }
        if (isShowEmotion()) {
            b = (byte) (b + 128);
        }
        byte[] bArr2 = {b, (byte) (((byte) (((byte) ((isShowAlarm() ? (byte) (-15) : (byte) -16) + 2)) + 4)) + 8), -1, -1};
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
        return CommandNames.SET_BAND_SETTINGS;
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

    public boolean isShowActiveTime() {
        return this.j;
    }

    public boolean isShowActivityProgress() {
        return this.k;
    }

    public boolean isShowAlarm() {
        return this.m;
    }

    public boolean isShowCaloriesCount() {
        return this.h;
    }

    public boolean isShowDateAndTime() {
        return this.f;
    }

    public boolean isShowDistance() {
        return this.i;
    }

    public boolean isShowEmotion() {
        return this.l;
    }

    public boolean isShowStepCount() {
        return this.g;
    }
}
