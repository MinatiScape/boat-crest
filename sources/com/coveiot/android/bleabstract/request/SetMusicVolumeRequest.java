package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetMusicVolumeRequest extends BleBaseRequest {
    public int f;
    public int g;

    /* loaded from: classes2.dex */
    public static final class Builder {
        public int maxVolume;
        public int volume;

        public SetMusicVolumeRequest build() {
            SetMusicVolumeRequest setMusicVolumeRequest = new SetMusicVolumeRequest();
            setMusicVolumeRequest.f = this.volume;
            setMusicVolumeRequest.g = this.maxVolume;
            return setMusicVolumeRequest;
        }

        public Builder setMaxVolume(int i) {
            this.maxVolume = i;
            return this;
        }

        public Builder setVolume(int i) {
            this.volume = i;
            return this;
        }
    }

    public int getMaxVolume() {
        return this.g;
    }

    public int getVolume() {
        return this.f;
    }
}
