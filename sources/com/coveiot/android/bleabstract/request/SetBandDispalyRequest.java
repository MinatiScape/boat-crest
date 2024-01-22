package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
/* loaded from: classes2.dex */
public class SetBandDispalyRequest extends BleBaseRequest {
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f3520a;
        public boolean b = true;
        public boolean c = true;
        public boolean d = true;
        public boolean e = true;
        public boolean f = true;
        public boolean g = true;
        public boolean h = true;
        public boolean i = true;

        public SetBandDispalyRequest build() {
            SetBandDispalyRequest setBandDispalyRequest = new SetBandDispalyRequest();
            setBandDispalyRequest.g = this.c;
            setBandDispalyRequest.h = this.d;
            setBandDispalyRequest.l = this.h;
            setBandDispalyRequest.f = this.b;
            setBandDispalyRequest.i = this.e;
            setBandDispalyRequest.j = this.f;
            setBandDispalyRequest.m = this.i;
            setBandDispalyRequest.k = this.g;
            return setBandDispalyRequest;
        }

        public Builder setId(Object obj) {
            this.f3520a = obj;
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

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.SET_BAND_DISPLAY;
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
