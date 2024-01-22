package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002J\u0016\u0010\u0007\u001a\u00020\u00062\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002R$\u0010\u000f\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardPreMatchOdds;", "", "", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/Scorecard3way;", "get3way", "_3way", "", "set3way", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeam;", "a", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeam;", "getTeam", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeam;", "setTeam", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardTeam;)V", "team", "b", "Ljava/util/List;", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class ScorecardPreMatchOdds {
    @SerializedName("team")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private ScorecardTeam f5969a;
    @SerializedName("3way")
    @Expose
    @Nullable
    private List<Scorecard3way> b;

    @Nullable
    public final List<Scorecard3way> get3way() {
        return this.b;
    }

    @Nullable
    public final ScorecardTeam getTeam() {
        return this.f5969a;
    }

    public final void set3way(@Nullable List<Scorecard3way> list) {
        this.b = list;
    }

    public final void setTeam(@Nullable ScorecardTeam scorecardTeam) {
        this.f5969a = scorecardTeam;
    }
}
