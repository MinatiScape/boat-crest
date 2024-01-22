package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScoreCardLayhome;", "", "", "a", "Ljava/lang/String;", "getOdds", "()Ljava/lang/String;", "setOdds", "(Ljava/lang/String;)V", "odds", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScoreCardLayhome {
    @SerializedName("odds")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5950a;

    @Nullable
    public final String getOdds() {
        return this.f5950a;
    }

    public final void setOdds(@Nullable String str) {
        this.f5950a = str;
    }
}
