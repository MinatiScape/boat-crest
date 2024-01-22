package com.jstyle.blesdk1860.model;
/* loaded from: classes11.dex */
public class Notifier extends SendData {
    public static final int Data_Facebook = 3;
    public static final int Data_Sms = 2;
    public static final int Data_Stop_Tel = 255;
    public static final int Data_Tel = 0;
    public static final int Data_Telegra = 4;
    public static final int Data_Twitter = 7;
    public static final int Data_Vk = 8;
    public static final int Data_WeChat = 1;
    public static final int Data_WhatApp = 9;

    /* renamed from: a  reason: collision with root package name */
    public int f12530a;
    public String b;

    public String getInfo() {
        return this.b;
    }

    public int getType() {
        return this.f12530a;
    }

    public void setInfo(String str) {
        this.b = str;
    }

    public void setType(int i) {
        this.f12530a = i;
    }

    public String toString() {
        return "Notifier{type=" + this.f12530a + ", info='" + this.b + "'}";
    }
}
