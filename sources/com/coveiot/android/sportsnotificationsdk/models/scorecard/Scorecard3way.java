package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\bB\u0010CR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0019\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010!\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010)\u001a\u0004\u0018\u00010\"8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R$\u00101\u001a\u0004\u0018\u00010*8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R$\u00109\u001a\u0004\u0018\u0001028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R$\u0010A\u001a\u0004\u0018\u00010:8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@¨\u0006D"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/Scorecard3way;", "", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardBookmaker;", "a", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardBookmaker;", "getBookmaker", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardBookmaker;", "setBookmaker", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardBookmaker;)V", "bookmaker", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardHome1;", "b", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardHome1;", "getHome", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardHome1;", "setHome", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardHome1;)V", "home", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardDraw;", c.f10260a, "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardDraw;", "getDraw", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardDraw;", "setDraw", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardDraw;)V", "draw", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecaredAway1;", "d", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecaredAway1;", "getAway", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecaredAway1;", "setAway", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecaredAway1;)V", "away", "", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Ljava/lang/String;", "getType", "()Ljava/lang/String;", "setType", "(Ljava/lang/String;)V", "type", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScoreCardLayhome;", "f", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScoreCardLayhome;", "getLayhome", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScoreCardLayhome;", "setLayhome", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScoreCardLayhome;)V", "layhome", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardLaydraw;", "g", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardLaydraw;", "getLaydraw", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardLaydraw;", "setLaydraw", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardLaydraw;)V", "laydraw", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardLayaway;", "h", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardLayaway;", "getLayaway", "()Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardLayaway;", "setLayaway", "(Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/ScorecardLayaway;)V", "layaway", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class Scorecard3way {
    @SerializedName("bookmaker")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private ScorecardBookmaker f5951a;
    @SerializedName("home")
    @Expose
    @Nullable
    private ScorecardHome1 b;
    @SerializedName("draw")
    @Expose
    @Nullable
    private ScorecardDraw c;
    @SerializedName("away")
    @Expose
    @Nullable
    private ScorecaredAway1 d;
    @SerializedName("type")
    @Expose
    @Nullable
    private String e;
    @SerializedName("layhome")
    @Expose
    @Nullable
    private ScoreCardLayhome f;
    @SerializedName("laydraw")
    @Expose
    @Nullable
    private ScorecardLaydraw g;
    @SerializedName("layaway")
    @Expose
    @Nullable
    private ScorecardLayaway h;

    @Nullable
    public final ScorecaredAway1 getAway() {
        return this.d;
    }

    @Nullable
    public final ScorecardBookmaker getBookmaker() {
        return this.f5951a;
    }

    @Nullable
    public final ScorecardDraw getDraw() {
        return this.c;
    }

    @Nullable
    public final ScorecardHome1 getHome() {
        return this.b;
    }

    @Nullable
    public final ScorecardLayaway getLayaway() {
        return this.h;
    }

    @Nullable
    public final ScorecardLaydraw getLaydraw() {
        return this.g;
    }

    @Nullable
    public final ScoreCardLayhome getLayhome() {
        return this.f;
    }

    @Nullable
    public final String getType() {
        return this.e;
    }

    public final void setAway(@Nullable ScorecaredAway1 scorecaredAway1) {
        this.d = scorecaredAway1;
    }

    public final void setBookmaker(@Nullable ScorecardBookmaker scorecardBookmaker) {
        this.f5951a = scorecardBookmaker;
    }

    public final void setDraw(@Nullable ScorecardDraw scorecardDraw) {
        this.c = scorecardDraw;
    }

    public final void setHome(@Nullable ScorecardHome1 scorecardHome1) {
        this.b = scorecardHome1;
    }

    public final void setLayaway(@Nullable ScorecardLayaway scorecardLayaway) {
        this.h = scorecardLayaway;
    }

    public final void setLaydraw(@Nullable ScorecardLaydraw scorecardLaydraw) {
        this.g = scorecardLaydraw;
    }

    public final void setLayhome(@Nullable ScoreCardLayhome scoreCardLayhome) {
        this.f = scoreCardLayhome;
    }

    public final void setType(@Nullable String str) {
        this.e = str;
    }
}
