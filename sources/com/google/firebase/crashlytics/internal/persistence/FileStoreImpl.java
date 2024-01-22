package com.google.firebase.crashlytics.internal.persistence;

import android.content.Context;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.File;
/* loaded from: classes10.dex */
public class FileStoreImpl implements FileStore {
    public static final String FILES_PATH = ".com.google.firebase.crashlytics";

    /* renamed from: a  reason: collision with root package name */
    public final Context f11247a;

    public FileStoreImpl(Context context) {
        this.f11247a = context;
    }

    public File a(File file) {
        if (file != null) {
            if (file.exists() || file.mkdirs()) {
                return file;
            }
            Logger.getLogger().w("Couldn't create file");
            return null;
        }
        Logger.getLogger().w("Null File");
        return null;
    }

    @Override // com.google.firebase.crashlytics.internal.persistence.FileStore
    public File getFilesDir() {
        return a(new File(this.f11247a.getFilesDir(), FILES_PATH));
    }

    @Override // com.google.firebase.crashlytics.internal.persistence.FileStore
    public String getFilesDirPath() {
        return new File(this.f11247a.getFilesDir(), FILES_PATH).getPath();
    }
}
