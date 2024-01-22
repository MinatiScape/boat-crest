package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class CurrentSportModeReq extends BaseRequest {
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7483a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public Object f;
        public boolean g;

        public CurrentSportModeReq build() {
            CurrentSportModeReq currentSportModeReq = new CurrentSportModeReq(this.f);
            currentSportModeReq.i = this.c;
            currentSportModeReq.f = this.f7483a;
            currentSportModeReq.g = this.d;
            currentSportModeReq.h = this.b;
            currentSportModeReq.j = this.e;
            currentSportModeReq.k = this.g;
            return currentSportModeReq;
        }

        public Builder setIsIndoor(boolean z) {
            this.g = z;
            return this;
        }

        public Builder setModes(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
            this.f7483a = z;
            this.d = z2;
            this.b = z3;
            this.c = z4;
            this.e = z5;
            return this;
        }
    }

    public CurrentSportModeReq(Object obj) {
        super(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        int i;
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {1, -117, 6, 0};
        if (isRunning()) {
            i = 2;
        } else if (isSwimming()) {
            i = 4;
        } else if (isCycling()) {
            i = 3;
        } else if (isWalking()) {
            i = 1;
        } else {
            i = isTaichi() ? 5 : 0;
        }
        byte[] bArr2 = {(byte) i, !this.k ? 1 : 0};
        byte[] bArr3 = new byte[6];
        System.arraycopy(bArr, 0, bArr3, 0, 4);
        System.arraycopy(bArr2, 0, bArr3, 4, 2);
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
        return CommandNames.CURRENT_SPORT_MODE;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public boolean isCycling() {
        return this.h;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    public boolean isRunning() {
        return this.f;
    }

    public boolean isSwimming() {
        return this.g;
    }

    public boolean isTaichi() {
        return this.j;
    }

    public boolean isWalking() {
        return this.i;
    }
}
