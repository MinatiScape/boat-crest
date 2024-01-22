package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetAmbientSoundLevelControlRequest extends BleBaseRequest {
    public boolean f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3518a;

        public Builder(boolean z) {
            this.f3518a = z;
        }

        @NotNull
        public final SetAmbientSoundLevelControlRequest build() {
            SetAmbientSoundLevelControlRequest setAmbientSoundLevelControlRequest = new SetAmbientSoundLevelControlRequest();
            setAmbientSoundLevelControlRequest.setEnabled(this.f3518a);
            return setAmbientSoundLevelControlRequest;
        }

        public final boolean isEnabled() {
            return this.f3518a;
        }

        public final void setEnabled(boolean z) {
            this.f3518a = z;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.SET_AMBIENT_SOUND_CONTROL;
    }

    public final boolean isEnabled() {
        return this.f;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public void setBleCommand(@Nullable BleCommand bleCommand) {
        super.setBleCommand(bleCommand);
    }

    public final void setEnabled(boolean z) {
        this.f = z;
    }
}
