package com.coveiot.android.dynamictab.sports.model;

import java.io.Serializable;
/* loaded from: classes4.dex */
public class WebViewDataCMMModel implements Serializable {
    public String actionText;
    public String actionUrl;
    public String message;
    public String title;
    public String webview;

    public String getActionText() {
        return this.actionText;
    }

    public String getActionUrl() {
        return this.actionUrl;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTitle() {
        return this.title;
    }

    public String getWebview() {
        return this.webview;
    }

    public void setActionText(String str) {
        this.actionText = str;
    }

    public void setActionUrl(String str) {
        this.actionUrl = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setWebview(String str) {
        this.webview = str;
    }
}
