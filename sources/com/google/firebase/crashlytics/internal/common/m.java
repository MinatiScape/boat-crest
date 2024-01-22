package com.google.firebase.crashlytics.internal.common;

import androidx.annotation.NonNull;
import com.coveiot.android.tappy.utils.Constants;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jose4j.jwk.JsonWebKeySet;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class m {
    public static final Charset b = Charset.forName("UTF-8");

    /* renamed from: a  reason: collision with root package name */
    public final File f11161a;

    /* loaded from: classes10.dex */
    public class a extends JSONObject {
        public final /* synthetic */ UserMetadata b;

        public a(UserMetadata userMetadata) throws JSONException {
            this.b = userMetadata;
            put(Constants.END_USER_GLOBAL_ID, userMetadata.getUserId());
        }
    }

    public m(File file) {
        this.f11161a = file;
    }

    public static Map<String, String> d(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, i(jSONObject, next));
        }
        return hashMap;
    }

    public static String e(Map<String, String> map) throws JSONException {
        return new JSONObject((Map<?, ?>) map).toString();
    }

    public static String h(UserMetadata userMetadata) throws JSONException {
        return new a(userMetadata).toString();
    }

    public static String i(JSONObject jSONObject, String str) {
        if (jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.optString(str, null);
    }

    @NonNull
    public File a(String str) {
        File file = this.f11161a;
        return new File(file, str + "internal-keys.meta");
    }

    @NonNull
    public File b(String str) {
        File file = this.f11161a;
        return new File(file, str + JsonWebKeySet.JWK_SET_MEMBER_NAME + ".meta");
    }

    @NonNull
    public File c(String str) {
        File file = this.f11161a;
        return new File(file, str + "user.meta");
    }

    public Map<String, String> f(String str) {
        return g(str, false);
    }

    public Map<String, String> g(String str, boolean z) {
        FileInputStream fileInputStream;
        File a2 = z ? a(str) : b(str);
        if (!a2.exists()) {
            return Collections.emptyMap();
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(a2);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            Map<String, String> d = d(CommonUtils.streamToString(fileInputStream));
            CommonUtils.closeOrLog(fileInputStream, "Failed to close user metadata file.");
            return d;
        } catch (Exception e2) {
            e = e2;
            fileInputStream2 = fileInputStream;
            Logger.getLogger().e("Error deserializing user metadata.", e);
            CommonUtils.closeOrLog(fileInputStream2, "Failed to close user metadata file.");
            return Collections.emptyMap();
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            CommonUtils.closeOrLog(fileInputStream2, "Failed to close user metadata file.");
            throw th;
        }
    }

    public void j(String str, Map<String, String> map, boolean z) {
        String e;
        BufferedWriter bufferedWriter;
        File a2 = z ? a(str) : b(str);
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                e = e(map);
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(a2), b));
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            bufferedWriter.write(e);
            bufferedWriter.flush();
            CommonUtils.closeOrLog(bufferedWriter, "Failed to close key/value metadata file.");
        } catch (Exception e3) {
            e = e3;
            bufferedWriter2 = bufferedWriter;
            Logger.getLogger().e("Error serializing key/value metadata.", e);
            CommonUtils.closeOrLog(bufferedWriter2, "Failed to close key/value metadata file.");
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
            CommonUtils.closeOrLog(bufferedWriter2, "Failed to close key/value metadata file.");
            throw th;
        }
    }

    public void k(String str, UserMetadata userMetadata) {
        String h;
        BufferedWriter bufferedWriter;
        File c = c(str);
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                h = h(userMetadata);
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(c), b));
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedWriter.write(h);
            bufferedWriter.flush();
            CommonUtils.closeOrLog(bufferedWriter, "Failed to close user metadata file.");
        } catch (Exception e2) {
            e = e2;
            bufferedWriter2 = bufferedWriter;
            Logger.getLogger().e("Error serializing user metadata.", e);
            CommonUtils.closeOrLog(bufferedWriter2, "Failed to close user metadata file.");
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
            CommonUtils.closeOrLog(bufferedWriter2, "Failed to close user metadata file.");
            throw th;
        }
    }
}
