package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SaveFitnessProfileReq extends BaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public boolean k;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7526a;
        public int b;
        public int c;
        public int d;
        public int e;
        public boolean f;
        public int g = -1;

        public Builder(int i, int i2, int i3, int i4, boolean z) {
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = z;
        }

        public SaveFitnessProfileReq build() {
            SaveFitnessProfileReq saveFitnessProfileReq = new SaveFitnessProfileReq(this.f7526a);
            saveFitnessProfileReq.f = this.b;
            saveFitnessProfileReq.g = this.c;
            saveFitnessProfileReq.k = this.f;
            saveFitnessProfileReq.h = this.d;
            saveFitnessProfileReq.i = this.e;
            saveFitnessProfileReq.j = this.g;
            return saveFitnessProfileReq;
        }

        public int getAge() {
            return this.g;
        }

        public Builder setAge(int i) {
            this.g = i;
            return this;
        }

        public Builder setId(Object obj) {
            this.f7526a = obj;
            return this;
        }
    }

    public SaveFitnessProfileReq(Object obj) {
        super(obj);
        this.j = -1;
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.USER_HEIGHT_WEIGHT_STRIDE;
        byte[] bArr2 = {(byte) getHeight(), (byte) getWeight(), (byte) getStrideLength(), (byte) (!isMale()), (byte) getRunStrideLength()};
        if (this.j != -1) {
            bArr2 = new byte[]{(byte) getHeight(), (byte) getWeight(), (byte) getStrideLength(), (byte) (!isMale()), (byte) getRunStrideLength(), (byte) this.j};
        }
        int length = bArr.length + bArr2.length;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
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
        return CommandNames.SET_USER_FITNESS_INFO;
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

    public int getRunStrideLength() {
        int i = this.i;
        return i > 0 ? i : AppUtils.caluclateRunningStrideLenght(this.f);
    }

    public int getStrideLength() {
        int i = this.h;
        return i > 0 ? i : AppUtils.caluclateWalkingStrideLenght(this.f);
    }

    public int getWeight() {
        return this.g;
    }

    public boolean isMale() {
        return this.k;
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
