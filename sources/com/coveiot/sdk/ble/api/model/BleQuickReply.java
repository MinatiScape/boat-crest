package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes9.dex */
public class BleQuickReply implements Serializable {
    public boolean isQuickReplyEnabled;
    public int numberQuickReply;
    public List<QuickReply> quickReplyList;

    public BleQuickReply(boolean z, int i, List<QuickReply> list) {
        this.isQuickReplyEnabled = z;
        this.numberQuickReply = i;
        this.quickReplyList = list;
    }

    public int getNumberQuickReply() {
        return this.numberQuickReply;
    }

    public List<QuickReply> getQuickReplyList() {
        return this.quickReplyList;
    }

    public boolean isQuickReplyEnabled() {
        return this.isQuickReplyEnabled;
    }

    public void setNumberQuickReply(int i) {
        this.numberQuickReply = i;
    }

    public void setQuickReplyEnabled(boolean z) {
        this.isQuickReplyEnabled = z;
    }

    public void setQuickReplyList(List<QuickReply> list) {
        this.quickReplyList = list;
    }

    public String toString() {
        return "BleQuickReply{isQuickReplyEnabled=" + this.isQuickReplyEnabled + ", numberQuickReply=" + this.numberQuickReply + ", quickReplyList=" + this.quickReplyList + '}';
    }
}
