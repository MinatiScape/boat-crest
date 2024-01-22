package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetMusicVolumePercentageChangedRequest extends BleBaseRequest {
    public int f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3536a;

        public Builder(int i) {
            this.f3536a = i;
        }

        public SetMusicVolumePercentageChangedRequest build() {
            SetMusicVolumePercentageChangedRequest setMusicVolumePercentageChangedRequest = new SetMusicVolumePercentageChangedRequest();
            setMusicVolumePercentageChangedRequest.f = this.f3536a;
            return setMusicVolumePercentageChangedRequest;
        }
    }

    public int getVolumePercentage() {
        return this.f;
    }
}
