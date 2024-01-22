package io.shipbook.shipbooksdk.Networking;

import com.google.android.material.color.c;
import io.shipbook.shipbooksdk.Models.ErrorResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0013\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u001a¢\u0006\u0004\b\u001b\u0010\u001cR\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\r\u001a\u0004\u0018\u00010\b8\u0006@\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0013\u001a\u00020\u000e8\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0019\u001a\u00020\u00148\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001d"}, d2 = {"Lio/shipbook/shipbooksdk/Networking/ResponseData;", "", "Lorg/json/JSONObject;", "a", "Lorg/json/JSONObject;", "getData", "()Lorg/json/JSONObject;", "data", "Lio/shipbook/shipbooksdk/Models/ErrorResponse;", "b", "Lio/shipbook/shipbooksdk/Models/ErrorResponse;", "getError", "()Lio/shipbook/shipbooksdk/Models/ErrorResponse;", "error", "", c.f10260a, "Z", "getOk", "()Z", "ok", "", "d", "I", "getStatusCode", "()I", "statusCode", "", "<init>", "(ZILjava/lang/String;)V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ResponseData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final JSONObject f14042a;
    @Nullable
    public final ErrorResponse b;
    public final boolean c;
    public final int d;

    public ResponseData(boolean z, int i, @NotNull String data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.c = z;
        this.d = i;
        ErrorResponse errorResponse = null;
        JSONObject jSONObject = m.isBlank(data) ^ true ? new JSONObject(data) : null;
        this.f14042a = jSONObject;
        if (jSONObject != null && !z) {
            errorResponse = ErrorResponse.Companion.create(jSONObject);
        }
        this.b = errorResponse;
    }

    @Nullable
    public final JSONObject getData() {
        return this.f14042a;
    }

    @Nullable
    public final ErrorResponse getError() {
        return this.b;
    }

    public final boolean getOk() {
        return this.c;
    }

    public final int getStatusCode() {
        return this.d;
    }

    public /* synthetic */ ResponseData(boolean z, int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i2 & 2) != 0 ? -1 : i, (i2 & 4) != 0 ? "" : str);
    }
}
