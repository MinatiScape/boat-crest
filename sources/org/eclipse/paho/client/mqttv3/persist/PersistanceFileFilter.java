package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FileFilter;
/* loaded from: classes13.dex */
public class PersistanceFileFilter implements FileFilter {

    /* renamed from: a  reason: collision with root package name */
    public final String f15465a;

    public PersistanceFileFilter(String str) {
        this.f15465a = str;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return file.getName().endsWith(this.f15465a);
    }
}
