package com.google.firebase.installations.local;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;
/* loaded from: classes10.dex */
public class IidStore {
    public static final String[] c = {Marker.ANY_MARKER, FirebaseMessaging.INSTANCE_ID_SCOPE, "GCM", ""};
    @GuardedBy("iidPrefs")

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f11315a;
    public final String b;

    public IidStore(@NonNull FirebaseApp firebaseApp) {
        this.f11315a = firebaseApp.getApplicationContext().getSharedPreferences("com.google.android.gms.appid", 0);
        this.b = b(firebaseApp);
    }

    public static String b(FirebaseApp firebaseApp) {
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = firebaseApp.getOptions().getApplicationId();
        if (applicationId.startsWith("1:") || applicationId.startsWith("2:")) {
            String[] split = applicationId.split(":");
            if (split.length != 4) {
                return null;
            }
            String str = split[1];
            if (str.isEmpty()) {
                return null;
            }
            return str;
        }
        return applicationId;
    }

    @Nullable
    public static String c(@NonNull PublicKey publicKey) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(publicKey.getEncoded());
            digest[0] = (byte) (((digest[0] & 15) + 112) & 255);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("ContentValues", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    public final String a(@NonNull String str, @NonNull String str2) {
        return "|T|" + str + "|" + str2;
    }

    public final String d(String str) {
        try {
            return new JSONObject(str).getString(MapplsLMSDbAdapter.KEY_TOKEN);
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    public final PublicKey e(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 8)));
        } catch (IllegalArgumentException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            Log.w("ContentValues", "Invalid key stored " + e);
            return null;
        }
    }

    @Nullable
    public final String f() {
        String string;
        synchronized (this.f11315a) {
            string = this.f11315a.getString("|S|id", null);
        }
        return string;
    }

    @Nullable
    public final String g() {
        synchronized (this.f11315a) {
            String string = this.f11315a.getString("|S||P|", null);
            if (string == null) {
                return null;
            }
            PublicKey e = e(string);
            if (e == null) {
                return null;
            }
            return c(e);
        }
    }

    @Nullable
    public String readIid() {
        synchronized (this.f11315a) {
            String f = f();
            if (f != null) {
                return f;
            }
            return g();
        }
    }

    @Nullable
    public String readToken() {
        synchronized (this.f11315a) {
            for (String str : c) {
                String string = this.f11315a.getString(a(this.b, str), null);
                if (string != null && !string.isEmpty()) {
                    if (string.startsWith("{")) {
                        string = d(string);
                    }
                    return string;
                }
            }
            return null;
        }
    }

    @VisibleForTesting
    public IidStore(@NonNull SharedPreferences sharedPreferences, @Nullable String str) {
        this.f11315a = sharedPreferences;
        this.b = str;
    }
}
