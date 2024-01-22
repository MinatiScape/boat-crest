package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SaveFitnessProfileReqOld extends BaseRequest {
    public int f;
    public int g;
    public int h;
    public boolean i;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7527a;
        public int b;
        public int c;
        public int d;
        public boolean e;

        public Builder(int i, int i2, int i3, boolean z) {
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = z;
        }

        public SaveFitnessProfileReqOld build() {
            SaveFitnessProfileReqOld saveFitnessProfileReqOld = new SaveFitnessProfileReqOld(this.f7527a);
            saveFitnessProfileReqOld.f = this.b;
            saveFitnessProfileReqOld.g = this.c;
            saveFitnessProfileReqOld.i = this.e;
            saveFitnessProfileReqOld.h = this.d;
            return saveFitnessProfileReqOld;
        }

        public Builder setId(Object obj) {
            this.f7527a = obj;
            return this;
        }
    }

    public SaveFitnessProfileReqOld(Object obj) {
        super(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.USER_HEIGHT_WEIGHT_STRIDE;
        byte[] bArr2 = {(byte) getHeight(), (byte) getWeight(), (byte) getStrideLength(), (byte) (!isMale())};
        int length = bArr.length + 4;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, 4);
        bArr3[2] = (byte) length;
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
        return CommandNames.SET_USER_FITNESS_INFO_OLD;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getHeight() {
        return this.f;
    }

    public final int getStrideLength() {
        int i = this.h;
        return i > 0 ? i : AppUtils.caluclateWalkingStrideLenght(this.f);
    }

    public int getWeight() {
        return this.g;
    }

    public boolean isMale() {
        return this.i;
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
