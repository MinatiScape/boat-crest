package com.coveiot.android.sportsnotificationsdk.network;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000e\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\t\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/network/SportsApiErrorModel;", "Ljava/io/Serializable;", "", "code", "I", "getCode", "()I", "", NotificationCompat.CATEGORY_STATUS, "Ljava/lang/String;", "getStatus", "()Ljava/lang/String;", "setStatus", "(Ljava/lang/String;)V", "response", "getResponse", "setResponse", "<init>", "(I)V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public class SportsApiErrorModel implements Serializable {
    private final int code;
    @SerializedName("response")
    @Nullable
    private String response;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Nullable
    private String status;

    public SportsApiErrorModel(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    @Nullable
    public final String getResponse() {
        return this.response;
    }

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    public final void setResponse(@Nullable String str) {
        this.response = str;
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }
}
