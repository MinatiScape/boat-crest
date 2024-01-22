package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class VoiceRecognizeState {
    public static final int STATE_RECOGNIZED = 0;
    public static final int STATE_TIMEOUT = 2;
    public static final int STATE_UN_RECOGNIZE = 1;
    public int phone_state;

    public String toString() {
        return "VoiceRecognizeState{phone_state=" + this.phone_state + '}';
    }
}
