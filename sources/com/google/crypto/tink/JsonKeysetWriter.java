package com.google.crypto.tink;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.subtle.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public final class JsonKeysetWriter implements KeysetWriter {
    public static final Charset b = Charset.forName("UTF-8");

    /* renamed from: a  reason: collision with root package name */
    public final OutputStream f10817a;

    public JsonKeysetWriter(OutputStream outputStream) {
        this.f10817a = outputStream;
    }

    public static KeysetWriter withFile(File file) throws IOException {
        return new JsonKeysetWriter(new FileOutputStream(file));
    }

    public static KeysetWriter withOutputStream(OutputStream outputStream) {
        return new JsonKeysetWriter(outputStream);
    }

    public static KeysetWriter withPath(String str) throws IOException {
        return withFile(new File(str));
    }

    public final JSONObject a(EncryptedKeyset encryptedKeyset) throws JSONException {
        return new JSONObject().put("encryptedKeyset", Base64.encode(encryptedKeyset.getEncryptedKeyset().toByteArray())).put("keysetInfo", f(encryptedKeyset.getKeysetInfo()));
    }

    public final JSONObject b(KeyData keyData) throws JSONException {
        return new JSONObject().put("typeUrl", keyData.getTypeUrl()).put("value", Base64.encode(keyData.getValue().toByteArray())).put("keyMaterialType", keyData.getKeyMaterialType().name());
    }

    public final JSONObject c(Keyset.Key key) throws JSONException {
        return new JSONObject().put("keyData", b(key.getKeyData())).put(NotificationCompat.CATEGORY_STATUS, key.getStatus().name()).put("keyId", g(key.getKeyId())).put("outputPrefixType", key.getOutputPrefixType().name());
    }

    public final JSONObject d(Keyset keyset) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("primaryKeyId", g(keyset.getPrimaryKeyId()));
        JSONArray jSONArray = new JSONArray();
        for (Keyset.Key key : keyset.getKeyList()) {
            jSONArray.put(c(key));
        }
        jSONObject.put(Constants.KEY_KEY, jSONArray);
        return jSONObject;
    }

    public final JSONObject e(KeysetInfo.KeyInfo keyInfo) throws JSONException {
        return new JSONObject().put("typeUrl", keyInfo.getTypeUrl()).put(NotificationCompat.CATEGORY_STATUS, keyInfo.getStatus().name()).put("keyId", keyInfo.getKeyId()).put("outputPrefixType", keyInfo.getOutputPrefixType().name());
    }

    public final JSONObject f(KeysetInfo keysetInfo) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("primaryKeyId", g(keysetInfo.getPrimaryKeyId()));
        JSONArray jSONArray = new JSONArray();
        for (KeysetInfo.KeyInfo keyInfo : keysetInfo.getKeyInfoList()) {
            jSONArray.put(e(keyInfo));
        }
        jSONObject.put("keyInfo", jSONArray);
        return jSONObject;
    }

    public final long g(int i) {
        return i & 4294967295L;
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset) throws IOException {
        try {
            try {
                OutputStream outputStream = this.f10817a;
                String jSONObject = d(keyset).toString(4);
                Charset charset = b;
                outputStream.write(jSONObject.getBytes(charset));
                this.f10817a.write(System.lineSeparator().getBytes(charset));
            } catch (JSONException e) {
                throw new IOException(e);
            }
        } finally {
            this.f10817a.close();
        }
    }

    public static KeysetWriter withPath(Path path) throws IOException {
        return withFile(path.toFile());
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset encryptedKeyset) throws IOException {
        try {
            try {
                OutputStream outputStream = this.f10817a;
                String jSONObject = a(encryptedKeyset).toString(4);
                Charset charset = b;
                outputStream.write(jSONObject.getBytes(charset));
                this.f10817a.write(System.lineSeparator().getBytes(charset));
            } catch (JSONException e) {
                throw new IOException(e);
            }
        } finally {
            this.f10817a.close();
        }
    }
}
