package com.touchgui.sdk.bean;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class TGQuickReply {
    private String content;
    private int msgIndex;
    private int msgType;
    @Nullable
    private String replyTo;

    @NonNull
    public String getContent() {
        return TextUtils.isEmpty(this.content) ? "" : this.content;
    }

    public int getMsgIndex() {
        return this.msgIndex;
    }

    public int getMsgType() {
        return this.msgType;
    }

    @Nullable
    public String getReplyTo() {
        return this.replyTo;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setMsgIndex(int i) {
        this.msgIndex = i;
    }

    public void setMsgType(int i) {
        this.msgType = i;
    }

    public void setReplyTo(@Nullable String str) {
        this.replyTo = str;
    }

    public String toString() {
        return "TGQuickReply{msgType=" + this.msgType + ", msgIndex=" + this.msgIndex + ", content='" + this.content + "', replyTo='" + this.replyTo + "'}";
    }
}
