package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class LiveStepsControlReq extends BaseRequest {
    public boolean f;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7502a;

        public Builder(boolean z) {
            this.f7502a = false;
            this.f7502a = z;
        }

        public LiveStepsControlReq build() {
            return new LiveStepsControlReq(this.f7502a);
        }
    }

    public LiveStepsControlReq(boolean z) {
        super(Boolean.valueOf(z));
        this.f = z;
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {1, -109, 5, 0};
        byte[] bArr2 = new byte[1];
        bArr2[0] = (byte) (isEnabled() ? 1 : 2);
        byte[] bArr3 = new byte[5];
        System.arraycopy(bArr, 0, bArr3, 0, 4);
        System.arraycopy(bArr2, 0, bArr3, 4, 1);
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
        return CommandNames.LIVE_STEPS_CONTROL;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public boolean isEnabled() {
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
