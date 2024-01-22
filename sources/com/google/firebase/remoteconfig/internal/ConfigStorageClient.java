package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
@AnyThread
/* loaded from: classes10.dex */
public class ConfigStorageClient {
    @GuardedBy("ConfigStorageClient.class")
    public static final Map<String, ConfigStorageClient> c = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public final Context f11499a;
    public final String b;

    public ConfigStorageClient(Context context, String str) {
        this.f11499a = context;
        this.b = str;
    }

    @VisibleForTesting
    public static synchronized void clearInstancesForTest() {
        synchronized (ConfigStorageClient.class) {
            c.clear();
        }
    }

    public static synchronized ConfigStorageClient getInstance(Context context, String str) {
        ConfigStorageClient configStorageClient;
        synchronized (ConfigStorageClient.class) {
            Map<String, ConfigStorageClient> map = c;
            if (!map.containsKey(str)) {
                map.put(str, new ConfigStorageClient(context, str));
            }
            configStorageClient = map.get(str);
        }
        return configStorageClient;
    }

    public String a() {
        return this.b;
    }

    public synchronized Void clear() {
        this.f11499a.deleteFile(this.b);
        return null;
    }

    @Nullable
    public synchronized ConfigContainer read() throws IOException {
        FileInputStream fileInputStream;
        Throwable th;
        try {
            fileInputStream = this.f11499a.openFileInput(this.b);
            try {
                int available = fileInputStream.available();
                byte[] bArr = new byte[available];
                fileInputStream.read(bArr, 0, available);
                ConfigContainer b = ConfigContainer.b(new JSONObject(new String(bArr, "UTF-8")));
                fileInputStream.close();
                return b;
            } catch (FileNotFoundException | JSONException unused) {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (FileNotFoundException | JSONException unused2) {
            fileInputStream = null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
        }
    }

    public synchronized Void write(ConfigContainer configContainer) throws IOException {
        FileOutputStream openFileOutput = this.f11499a.openFileOutput(this.b, 0);
        openFileOutput.write(configContainer.toString().getBytes("UTF-8"));
        openFileOutput.close();
        return null;
    }
}
