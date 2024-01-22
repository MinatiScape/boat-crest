package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class VoiceLoginState {
    public static final int STATE_HAS_LOGIN = 0;
    public static final int STATE_NOT_LOGIN = 1;
    public static final int STATE_NOT_NETWORK = 2;
    public int log_state;

    public String toString() {
        return "VoiceLoginState{log_state=" + this.log_state + '}';
    }
}
