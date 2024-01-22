package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FilenameFilter;
/* loaded from: classes13.dex */
public class PersistanceFileNameFilter implements FilenameFilter {

    /* renamed from: a  reason: collision with root package name */
    public final String f15466a;

    public PersistanceFileNameFilter(String str) {
        this.f15466a = str;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return str.endsWith(this.f15466a);
    }
}
