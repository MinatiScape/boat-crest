package com.coveiot.android.weathersdk.response;

import androidx.core.app.NotificationCompat;
import com.google.android.material.color.c;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u000e¢\u0006\u0004\b\u0014\u0010\u0015R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR\u0019\u0010\u0013\u001a\u00020\u000e8\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/coveiot/android/weathersdk/response/WeatherErrorModel;", "", "", "a", "Ljava/lang/String;", "getStatus", "()Ljava/lang/String;", "setStatus", "(Ljava/lang/String;)V", NotificationCompat.CATEGORY_STATUS, "b", "getResponse", "setResponse", "response", "", c.f10260a, "I", "getCode", "()I", "code", "<init>", "(I)V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public class WeatherErrorModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f6211a;
    @Nullable
    public String b;
    public final int c;

    public WeatherErrorModel(int i) {
        this.c = i;
    }

    public final int getCode() {
        return this.c;
    }

    @Nullable
    public final String getResponse() {
        return this.b;
    }

    @Nullable
    public final String getStatus() {
        return this.f6211a;
    }

    public final void setResponse(@Nullable String str) {
        this.b = str;
    }

    public final void setStatus(@Nullable String str) {
        this.f6211a = str;
    }
}
