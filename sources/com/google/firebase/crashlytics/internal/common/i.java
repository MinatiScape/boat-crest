package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.io.File;
import java.io.IOException;
/* loaded from: classes10.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    public final String f11157a;
    public final FileStore b;

    public i(String str, FileStore fileStore) {
        this.f11157a = str;
        this.b = fileStore;
    }

    public boolean a() {
        try {
            return b().createNewFile();
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            logger.e("Error creating marker: " + this.f11157a, e);
            return false;
        }
    }

    public final File b() {
        return new File(this.b.getFilesDir(), this.f11157a);
    }

    public boolean c() {
        return b().exists();
    }

    public boolean d() {
        return b().delete();
    }
}
