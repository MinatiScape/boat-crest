package com.abupdate.mqtt_libs.mqttv3.b;

import java.io.File;
import java.io.FilenameFilter;
/* loaded from: classes.dex */
public class d implements FilenameFilter {

    /* renamed from: a  reason: collision with root package name */
    public final String f1964a;

    public d(String str) {
        this.f1964a = str;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return str.endsWith(this.f1964a);
    }
}
