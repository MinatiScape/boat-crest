package com.google.android.gms.fido.u2f.api.common;

import androidx.annotation.NonNull;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes6.dex */
public class Error {
    @NonNull
    @VisibleForTesting
    public static final String JSON_ERROR_CODE = "errorCode";
    @NonNull
    @VisibleForTesting
    public static final String JSON_ERROR_MESSAGE = "errorMessage";

    /* renamed from: a  reason: collision with root package name */
    public final ErrorCode f8417a;
    public final String b;

    public Error(@NonNull ErrorCode errorCode) {
        this.f8417a = errorCode;
        this.b = null;
    }

    public Error(@NonNull ErrorCode errorCode, @NonNull String str) {
        this.f8417a = errorCode;
        this.b = str;
    }

    @NonNull
    public ErrorCode getErrorCode() {
        return this.f8417a;
    }

    @NonNull
    public String getErrorMessage() {
        return this.b;
    }

    @NonNull
    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", this.f8417a.getCode());
            String str = this.b;
            if (str != null) {
                jSONObject.put("errorMessage", str);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public String toString() {
        return this.b == null ? String.format(Locale.ENGLISH, "{errorCode: %d}", Integer.valueOf(this.f8417a.getCode())) : String.format(Locale.ENGLISH, "{errorCode: %d, errorMessage: %s}", Integer.valueOf(this.f8417a.getCode()), this.b);
    }
}
