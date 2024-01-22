package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.response.MusicControlState;
/* loaded from: classes2.dex */
public class SetMusicPlaybackStateChangedRequest extends BleBaseRequest {
    public MusicControlState f;

    /* loaded from: classes2.dex */
    public static final class Builder {
        public MusicControlState musicControlState;

        public SetMusicPlaybackStateChangedRequest build() {
            SetMusicPlaybackStateChangedRequest setMusicPlaybackStateChangedRequest = new SetMusicPlaybackStateChangedRequest();
            setMusicPlaybackStateChangedRequest.f = this.musicControlState;
            return setMusicPlaybackStateChangedRequest;
        }

        public Builder setMusicControlState(MusicControlState musicControlState) {
            this.musicControlState = musicControlState;
            return this;
        }
    }

    public MusicControlState getMusicControlState() {
        return this.f;
    }
}
