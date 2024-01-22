package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeam;", "", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardHome;", "a", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardHome;", "getHome", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardHome;", "setHome", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardHome;)V", "home", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardAway;", "b", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardAway;", "getAway", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardAway;", "setAway", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardAway;)V", "away", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardTeam {
    @SerializedName("home")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private ScorecardHome f5971a;
    @SerializedName("away")
    @Expose
    @Nullable
    private ScorecardAway b;

    @Nullable
    public final ScorecardAway getAway() {
        return this.b;
    }

    @Nullable
    public final ScorecardHome getHome() {
        return this.f5971a;
    }

    public final void setAway(@Nullable ScorecardAway scorecardAway) {
        this.b = scorecardAway;
    }

    public final void setHome(@Nullable ScorecardHome scorecardHome) {
        this.f5971a = scorecardHome;
    }
}
