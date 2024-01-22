package com.google.android.material.color;

import android.content.Context;
import android.content.res.loader.ResourcesLoader;
import android.content.res.loader.ResourcesProvider;
import android.os.ParcelFileDescriptor;
import android.system.Os;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.util.Map;
@RequiresApi(30)
/* loaded from: classes10.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10260a = "c";

    @Nullable
    public static ResourcesLoader a(@NonNull Context context, @NonNull Map<Integer, Integer> map) {
        FileDescriptor fileDescriptor;
        try {
            byte[] i = d.i(context, map);
            Log.i(f10260a, "Table created, length: " + i.length);
            if (i.length == 0) {
                return null;
            }
            try {
                fileDescriptor = Os.memfd_create("temp.arsc", 0);
            } catch (Throwable th) {
                th = th;
                fileDescriptor = null;
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileDescriptor);
                fileOutputStream.write(i);
                ParcelFileDescriptor dup = ParcelFileDescriptor.dup(fileDescriptor);
                try {
                    ResourcesLoader resourcesLoader = new ResourcesLoader();
                    resourcesLoader.addProvider(ResourcesProvider.loadFromTable(dup, null));
                    if (dup != null) {
                        dup.close();
                    }
                    fileOutputStream.close();
                    if (fileDescriptor != null) {
                        Os.close(fileDescriptor);
                    }
                    return resourcesLoader;
                } catch (Throwable th2) {
                    if (dup != null) {
                        try {
                            dup.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                th = th4;
                if (fileDescriptor != null) {
                    Os.close(fileDescriptor);
                }
                throw th;
            }
        } catch (Exception e) {
            Log.e(f10260a, "Failed to create the ColorResourcesTableCreator.", e);
            return null;
        }
    }
}
