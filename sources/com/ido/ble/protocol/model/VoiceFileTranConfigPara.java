package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class VoiceFileTranConfigPara {
    public int file_len;
    public int opus_init_enum;
    public int prn;
    public int sbc_init_enum;
    public int voice_type;

    public String toString() {
        return "TranStartPara{prn=" + this.prn + ", voice_type=" + this.voice_type + ", sbc_init_enum=" + this.sbc_init_enum + ", opus_init_enum=" + this.opus_init_enum + ", file_len=" + this.file_len + '}';
    }
}
