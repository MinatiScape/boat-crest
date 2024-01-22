package com.google.crypto.tink;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public final class JsonKeysetReader implements KeysetReader {
    public static final Charset e = Charset.forName("UTF-8");

    /* renamed from: a  reason: collision with root package name */
    public final InputStream f10816a;
    public final JSONObject b;
    public final boolean c;
    public boolean d;

    public JsonKeysetReader(InputStream inputStream, boolean z) {
        this.d = false;
        this.f10816a = inputStream;
        this.c = z;
        this.b = null;
    }

    public static KeyData.KeyMaterialType b(String str) throws JSONException {
        if (str.equals("SYMMETRIC")) {
            return KeyData.KeyMaterialType.SYMMETRIC;
        }
        if (str.equals("ASYMMETRIC_PRIVATE")) {
            return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
        }
        if (str.equals("ASYMMETRIC_PUBLIC")) {
            return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
        }
        if (str.equals("REMOTE")) {
            return KeyData.KeyMaterialType.REMOTE;
        }
        throw new JSONException("unknown key material type: " + str);
    }

    public static OutputPrefixType c(String str) throws JSONException {
        if (str.equals("TINK")) {
            return OutputPrefixType.TINK;
        }
        if (str.equals("RAW")) {
            return OutputPrefixType.RAW;
        }
        if (str.equals("LEGACY")) {
            return OutputPrefixType.LEGACY;
        }
        if (str.equals("CRUNCHY")) {
            return OutputPrefixType.CRUNCHY;
        }
        throw new JSONException("unknown output prefix type: " + str);
    }

    public static KeyStatusType d(String str) throws JSONException {
        if (str.equals("ENABLED")) {
            return KeyStatusType.ENABLED;
        }
        if (str.equals("DISABLED")) {
            return KeyStatusType.DISABLED;
        }
        throw new JSONException("unknown status: " + str);
    }

    public static KeysetInfo.KeyInfo g(JSONObject jSONObject) throws JSONException {
        return KeysetInfo.KeyInfo.newBuilder().setStatus(d(jSONObject.getString(NotificationCompat.CATEGORY_STATUS))).setKeyId(jSONObject.getInt("keyId")).setOutputPrefixType(c(jSONObject.getString("outputPrefixType"))).setTypeUrl(jSONObject.getString("typeUrl")).build();
    }

    public static KeysetInfo i(JSONObject jSONObject) throws JSONException {
        KeysetInfo.Builder newBuilder = KeysetInfo.newBuilder();
        if (jSONObject.has("primaryKeyId")) {
            newBuilder.setPrimaryKeyId(jSONObject.getInt("primaryKeyId"));
        }
        if (jSONObject.has("keyInfo")) {
            JSONArray jSONArray = jSONObject.getJSONArray("keyInfo");
            for (int i = 0; i < jSONArray.length(); i++) {
                newBuilder.addKeyInfo(g(jSONArray.getJSONObject(i)));
            }
        }
        return newBuilder.build();
    }

    public static void j(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("encryptedKeyset")) {
            throw new JSONException("invalid encrypted keyset");
        }
    }

    public static void k(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("keyData") || !jSONObject.has(NotificationCompat.CATEGORY_STATUS) || !jSONObject.has("keyId") || !jSONObject.has("outputPrefixType")) {
            throw new JSONException("invalid key");
        }
    }

    public static void l(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("typeUrl") || !jSONObject.has("value") || !jSONObject.has("keyMaterialType")) {
            throw new JSONException("invalid keyData");
        }
    }

    public static void m(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has(Constants.KEY_KEY) || jSONObject.getJSONArray(Constants.KEY_KEY).length() == 0) {
            throw new JSONException("invalid keyset");
        }
    }

    public static JsonKeysetReader withBytes(byte[] bArr) {
        return new JsonKeysetReader(new ByteArrayInputStream(bArr), true);
    }

    public static JsonKeysetReader withFile(File file) throws IOException {
        return new JsonKeysetReader(new FileInputStream(file), true);
    }

    public static KeysetReader withInputStream(InputStream inputStream) throws IOException {
        return new JsonKeysetReader(inputStream, false);
    }

    public static JsonKeysetReader withJsonObject(JSONObject jSONObject) {
        return new JsonKeysetReader(jSONObject);
    }

    public static JsonKeysetReader withPath(String str) throws IOException {
        return withFile(new File(str));
    }

    public static JsonKeysetReader withString(String str) {
        return new JsonKeysetReader(new ByteArrayInputStream(str.getBytes(e)), true);
    }

    public final EncryptedKeyset a(JSONObject jSONObject) throws JSONException {
        byte[] decode;
        j(jSONObject);
        if (this.d) {
            decode = Base64.urlSafeDecode(jSONObject.getString("encryptedKeyset"));
        } else {
            decode = Base64.decode(jSONObject.getString("encryptedKeyset"));
        }
        return EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(decode)).setKeysetInfo(i(jSONObject.getJSONObject("keysetInfo"))).build();
    }

    public final KeyData e(JSONObject jSONObject) throws JSONException {
        byte[] decode;
        l(jSONObject);
        if (this.d) {
            decode = Base64.urlSafeDecode(jSONObject.getString("value"));
        } else {
            decode = Base64.decode(jSONObject.getString("value"));
        }
        return KeyData.newBuilder().setTypeUrl(jSONObject.getString("typeUrl")).setValue(ByteString.copyFrom(decode)).setKeyMaterialType(b(jSONObject.getString("keyMaterialType"))).build();
    }

    public final Keyset.Key f(JSONObject jSONObject) throws JSONException {
        k(jSONObject);
        return Keyset.Key.newBuilder().setStatus(d(jSONObject.getString(NotificationCompat.CATEGORY_STATUS))).setKeyId(jSONObject.getInt("keyId")).setOutputPrefixType(c(jSONObject.getString("outputPrefixType"))).setKeyData(e(jSONObject.getJSONObject("keyData"))).build();
    }

    public final Keyset h(JSONObject jSONObject) throws JSONException {
        m(jSONObject);
        Keyset.Builder newBuilder = Keyset.newBuilder();
        if (jSONObject.has("primaryKeyId")) {
            newBuilder.setPrimaryKeyId(jSONObject.getInt("primaryKeyId"));
        }
        JSONArray jSONArray = jSONObject.getJSONArray(Constants.KEY_KEY);
        for (int i = 0; i < jSONArray.length(); i++) {
            newBuilder.addKey(f(jSONArray.getJSONObject(i)));
        }
        return newBuilder.build();
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        try {
            try {
                JSONObject jSONObject = this.b;
                if (jSONObject != null) {
                    return h(jSONObject);
                }
                Keyset h = h(new JSONObject(new String(a.c(this.f10816a), e)));
                InputStream inputStream = this.f10816a;
                if (inputStream != null && this.c) {
                    inputStream.close();
                }
                return h;
            } catch (JSONException e2) {
                throw new IOException(e2);
            }
        } finally {
            InputStream inputStream2 = this.f10816a;
            if (inputStream2 != null && this.c) {
                inputStream2.close();
            }
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        try {
            try {
                JSONObject jSONObject = this.b;
                if (jSONObject != null) {
                    return a(jSONObject);
                }
                EncryptedKeyset a2 = a(new JSONObject(new String(a.c(this.f10816a), e)));
                InputStream inputStream = this.f10816a;
                if (inputStream != null && this.c) {
                    inputStream.close();
                }
                return a2;
            } catch (JSONException e2) {
                throw new IOException(e2);
            }
        } finally {
            InputStream inputStream2 = this.f10816a;
            if (inputStream2 != null && this.c) {
                inputStream2.close();
            }
        }
    }

    public JsonKeysetReader withUrlSafeBase64() {
        this.d = true;
        return this;
    }

    public static JsonKeysetReader withPath(Path path) throws IOException {
        return withFile(path.toFile());
    }

    public JsonKeysetReader(JSONObject jSONObject) {
        this.d = false;
        this.b = jSONObject;
        this.f10816a = null;
        this.c = false;
    }
}
