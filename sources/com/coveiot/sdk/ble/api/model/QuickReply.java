package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class QuickReply {
    public int length;
    public String quickReply;

    public QuickReply(int i, String str) {
        this.length = i;
        this.quickReply = str;
    }

    public int getLength() {
        return this.length;
    }

    public String getQuickReply() {
        return this.quickReply;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public void setQuickReply(String str) {
        this.quickReply = str;
    }

    public String toString() {
        return "QuickReply{length=" + this.length + ", quickReply='" + this.quickReply + "'}";
    }
}
