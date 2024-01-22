package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.PPGTypes;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class SetAutomaticPPGConfigRequest extends BleBaseRequest {
    public PPGTypes f = PPGTypes.PPG;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public int j = 0;

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.SET_AUTOMATIC_PPG_CONFIG;
    }

    public int getDuration() {
        return this.j;
    }

    public int getEndTime() {
        return this.h;
    }

    public int getInterval() {
        return this.i;
    }

    public int getStartTime() {
        return this.g;
    }

    public PPGTypes getmPPGType() {
        return this.f;
    }

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public PPGTypes f3519a;
        public int b;
        public int c;
        public int d;
        public int e;

        public Builder(int i, int i2, int i3, int i4) {
            this.f3519a = PPGTypes.PPG;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }

        public SetAutomaticPPGConfigRequest build() {
            SetAutomaticPPGConfigRequest setAutomaticPPGConfigRequest = new SetAutomaticPPGConfigRequest();
            setAutomaticPPGConfigRequest.i = this.d;
            setAutomaticPPGConfigRequest.j = this.e;
            setAutomaticPPGConfigRequest.f = this.f3519a;
            setAutomaticPPGConfigRequest.g = this.b;
            setAutomaticPPGConfigRequest.h = this.c;
            return setAutomaticPPGConfigRequest;
        }

        public Builder(PPGTypes pPGTypes, int i, int i2, int i3, int i4) {
            this.f3519a = PPGTypes.PPG;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f3519a = pPGTypes;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }
}
