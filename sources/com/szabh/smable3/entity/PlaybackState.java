package com.szabh.smable3.entity;
/* loaded from: classes12.dex */
public enum PlaybackState {
    PAUSED((byte) 0),
    PLAYING((byte) 1),
    REWINDING((byte) 2),
    FAST_FORWARDING((byte) 3),
    UNKNOWN((byte) -1);
    
    private final byte mState;

    PlaybackState(byte b) {
        this.mState = b;
    }

    public final byte getMState() {
        return this.mState;
    }
}
