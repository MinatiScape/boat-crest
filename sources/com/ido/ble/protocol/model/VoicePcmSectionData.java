package com.ido.ble.protocol.model;

import java.util.Arrays;
/* loaded from: classes11.dex */
public class VoicePcmSectionData {
    public int pcm_data_len;
    public byte[] pcm_voice_data;

    public String toString() {
        return "VoicePcmSectionData{pcm_data_len=" + this.pcm_data_len + ", pcm_voice_data=" + Arrays.toString(this.pcm_voice_data) + '}';
    }
}
