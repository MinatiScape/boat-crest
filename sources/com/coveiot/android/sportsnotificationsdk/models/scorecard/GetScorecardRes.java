package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/GetScorecardRes;", "", "", "a", "Ljava/lang/String;", "getStatus", "()Ljava/lang/String;", "setStatus", "(Ljava/lang/String;)V", NotificationCompat.CATEGORY_STATUS, "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardResponse;", "b", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardResponse;", "getResponse", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardResponse;", "setResponse", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardResponse;)V", "response", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class GetScorecardRes {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5949a;
    @SerializedName("response")
    @Expose
    @Nullable
    private ScorecardResponse b;

    @Nullable
    public final ScorecardResponse getResponse() {
        return this.b;
    }

    @Nullable
    public final String getStatus() {
        return this.f5949a;
    }

    public final void setResponse(@Nullable ScorecardResponse scorecardResponse) {
        this.b = scorecardResponse;
    }

    public final void setStatus(@Nullable String str) {
        this.f5949a = str;
    }
}
