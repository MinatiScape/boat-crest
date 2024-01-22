package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class ActivityPauseResumetReq extends BaseRequest {
    public boolean f;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7472a;
        public boolean b;

        public static Builder aSetWalkTargetReq() {
            return new Builder();
        }

        public ActivityPauseResumetReq build() {
            ActivityPauseResumetReq activityPauseResumetReq = new ActivityPauseResumetReq(this.f7472a);
            activityPauseResumetReq.f = this.b;
            return activityPauseResumetReq;
        }

        public Builder pauseSession(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setId(Object obj) {
            this.f7472a = obj;
            return this;
        }
    }

    public ActivityPauseResumetReq(Object obj) {
        super(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.PAUSE_ACTIVITY_SESSION;
        byte[] bArr2 = {(byte) getPauseFlag()};
        byte[] bArr3 = new byte[bArr.length + 1];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, 1);
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
        return CommandNames.ACTIVITY_PAUSE;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getPauseFlag() {
        return this.f ? 1 : 2;
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
