package com.coveiot.coveaccess.userappsetting;

import java.util.List;
/* loaded from: classes8.dex */
public class SaveQuickReplySettingsReq {

    /* renamed from: a  reason: collision with root package name */
    public boolean f6853a;
    public List<String> b;

    public List<String> getQuickReplyMessages() {
        return this.b;
    }

    public boolean isActive() {
        return this.f6853a;
    }

    public void setActive(boolean z) {
        this.f6853a = z;
    }

    public void setQuickReplyMessages(List<String> list) {
        this.b = list;
    }
}
