package com.ido.ble.protocol.model;

import java.util.Arrays;
/* loaded from: classes11.dex */
public class VoiceOpusSectionData {
    public int encode_data_len;
    public byte[] opus_voice_data;

    public String toString() {
        return "VoiceSectionData{encode_data_len=" + this.encode_data_len + ", opus_voice_data=" + Arrays.toString(this.opus_voice_data) + '}';
    }
}
