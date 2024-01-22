package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetWalkTargetReq extends BaseRequest {
    public int f;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7535a;
        public int b = 10000;

        public static Builder aSetWalkTargetReq() {
            return new Builder();
        }

        public SetWalkTargetReq build() {
            SetWalkTargetReq setWalkTargetReq = new SetWalkTargetReq(this.f7535a);
            setWalkTargetReq.f = this.b;
            return setWalkTargetReq;
        }

        public Builder setId(Object obj) {
            this.f7535a = obj;
            return this;
        }

        public Builder setTarget(int i) {
            this.b = i;
            return this;
        }
    }

    public SetWalkTargetReq(Object obj) {
        super(obj);
        this.f = 10000;
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_DAILY_WALK_TARGET;
        int target = getTarget();
        if (target == 0) {
            target = 10000;
        }
        byte[] bArr2 = {(byte) target, (byte) (target >> 8), (byte) (target >> 16), (byte) (target >> 24)};
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
        return CommandNames.SET_DAILY_WALK_TARGET;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getTarget() {
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
}
