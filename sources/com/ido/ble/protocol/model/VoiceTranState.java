package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class VoiceTranState {
    public static final int STATE_IDLE = 0;
    public static final int STATE_REQUEST_APP_LOGIN_STATUS = 5;
    public static final int STATE_START = 1;
    public static final int STATE_STOP = 2;
    public static final int STATE_TIMEOUT = 3;
    public int state;

    public String toString() {
        return "VoiceTranState{state=" + this.state + '}';
    }
}
