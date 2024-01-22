package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class VoiceToText {
    public int flag_is_continue;
    public String text_content;
    public String title;
    private int version = 1;

    public String toString() {
        return "VoiceToText{version=" + this.version + ", title='" + this.title + "', text_content='" + this.text_content + "', flag_is_continue=" + this.flag_is_continue + '}';
    }
}
