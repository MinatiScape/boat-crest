package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class NoisePara {
    public static final int MODE_OFF = 85;
    public static final int MODE_ON = 170;
    public int high_noise_value;
    public int mode = 85;
    public int start_hour = 0;
    public int start_minute = 0;
    public int end_hour = 23;
    public int end_minute = 59;
    public int high_noise_on_off = 85;

    public String toString() {
        return "NoisePara{mode=" + this.mode + ", start_hour=" + this.start_hour + ", start_minute=" + this.start_minute + ", end_hour=" + this.end_hour + ", end_minute=" + this.end_minute + ", high_noise_on_off=" + this.high_noise_on_off + ", high_noise_value=" + this.high_noise_value + '}';
    }
}
