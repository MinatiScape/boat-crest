package com.abupdate.mqtt_libs.mqttv3.b;

import java.io.File;
import java.io.FileFilter;
/* loaded from: classes.dex */
public class c implements FileFilter {

    /* renamed from: a  reason: collision with root package name */
    public final String f1963a;

    public c(String str) {
        this.f1963a = str;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return file.getName().endsWith(this.f1963a);
    }
}
