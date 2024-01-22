package com.coveiot.android.androidappkillmanager.model;

import java.util.ArrayList;
/* loaded from: classes2.dex */
public class AutostartIntent {
    private String action;
    private String component;
    private ArrayList<String> extras;

    public String getAction() {
        return this.action;
    }

    public String getComponent() {
        return this.component;
    }

    public ArrayList<String> getExtras() {
        return this.extras;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setComponent(String str) {
        this.component = str;
    }

    public void setExtras(ArrayList<String> arrayList) {
        this.extras = arrayList;
    }
}
