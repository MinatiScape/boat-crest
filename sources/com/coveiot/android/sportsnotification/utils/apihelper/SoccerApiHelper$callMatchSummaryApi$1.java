package com.coveiot.android.sportsnotification.utils.apihelper;

import com.coveiot.android.sportsnotification.AccessTokenPreference;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.utils.SoccerData;
import com.coveiot.android.sportsnotification.utils.SoccerSummary;
import com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiErrorModel;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiListener;
import com.coveiot.android.sportsnotificationsdk.soccer.models.common.MatchInfo;
import com.coveiot.android.sportsnotificationsdk.soccer.models.common.Periods;
import com.coveiot.android.sportsnotificationsdk.soccer.models.common.Result;
import com.coveiot.android.sportsnotificationsdk.soccer.models.common.Team;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchstats.SMatchStatsResponse;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchstats.Statistics;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.jstyle.blesdk1860.constant.BleConst;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
@DebugMetadata(c = "com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$callMatchSummaryApi$1", f = "SoccerApiHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class SoccerApiHelper$callMatchSummaryApi$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ SoccerApiHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SoccerApiHelper$callMatchSummaryApi$1(SoccerApiHelper soccerApiHelper, Continuation<? super SoccerApiHelper$callMatchSummaryApi$1> continuation) {
        super(2, continuation);
        this.this$0 = soccerApiHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SoccerApiHelper$callMatchSummaryApi$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SoccerApiHelper$callMatchSummaryApi$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (AppUtils.isNetConnected(this.this$0.getContext())) {
                SportsPreference.Companion companion = SportsPreference.Companion;
                companion.setOfflineStatus(this.this$0.getContext(), false);
                if (companion.getSelectedSoccerMatch(this.this$0.getContext()) != null) {
                    MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(this.this$0.getContext());
                    Intrinsics.checkNotNull(selectedSoccerMatch);
                    Integer matchId = selectedSoccerMatch.getMatchId();
                    if (matchId != null && matchId.intValue() != 0) {
                        SportsNotificationApiManager.Companion companion2 = SportsNotificationApiManager.Companion;
                        int intValue = matchId.intValue();
                        String accessToken = new AccessTokenPreference(this.this$0.getContext()).getAccessToken();
                        final SoccerApiHelper soccerApiHelper = this.this$0;
                        companion2.getSoccerMatchStats(intValue, accessToken, new SportsApiListener<Response<SMatchStatsResponse>, SportsApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$callMatchSummaryApi$1.1
                            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
                            public void onError(@NotNull SportsApiErrorModel obj2) {
                                int i;
                                int i2;
                                Intrinsics.checkNotNullParameter(obj2, "obj");
                                if (obj2.getResponse() != null) {
                                    LogHelper.e(SoccerApiHelper.this.getTAG(), obj2.getResponse());
                                }
                                if (obj2.getCode() != 401) {
                                    i = SoccerApiHelper.this.c;
                                    if (i >= 3) {
                                        SoccerApiHelper.this.c("match_stats", obj2.getResponse());
                                        return;
                                    }
                                    SoccerApiHelper soccerApiHelper2 = SoccerApiHelper.this;
                                    i2 = soccerApiHelper2.c;
                                    soccerApiHelper2.c = i2 + 1;
                                    SoccerApiHelper.this.a();
                                    return;
                                }
                                SoccerApiHelper.this.getAccessToken();
                            }

                            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
                            public void onSuccess(@NotNull Response<SMatchStatsResponse> obj2) {
                                SMatchStatsResponse body;
                                String str;
                                int i;
                                int parseInt;
                                int parseInt2;
                                int intValue2;
                                int intValue3;
                                int i2;
                                Intrinsics.checkNotNullParameter(obj2, "obj");
                                if (obj2.body() == null || (body = obj2.body()) == null) {
                                    return;
                                }
                                SoccerData soccerData = new SoccerData();
                                int i3 = 0;
                                MatchInfo matchInfo = body.getResponse().getItems().getMatchInfo().get(0);
                                Integer[] numArr = {0, 0};
                                Integer[] numArr2 = {0, 0};
                                Integer[] numArr3 = {0, 0};
                                Integer[] numArr4 = {0, 0};
                                Integer[] numArr5 = {0, 0};
                                for (Statistics statistics : body.getResponse().getItems().getStatistics()) {
                                    if (statistics.getName().equals("Shots on target")) {
                                        numArr = new Integer[]{Integer.valueOf((int) statistics.getHome().doubleValue()), Integer.valueOf((int) statistics.getAway().doubleValue())};
                                    }
                                    if (statistics.getName().equals("Ball possession")) {
                                        numArr2 = new Integer[]{Integer.valueOf((int) statistics.getHome().doubleValue()), Integer.valueOf((int) statistics.getAway().doubleValue())};
                                    }
                                    if (statistics.getName().equals("Yellow cards")) {
                                        numArr3 = new Integer[]{Integer.valueOf((int) statistics.getHome().doubleValue()), Integer.valueOf((int) statistics.getAway().doubleValue())};
                                    }
                                    if (statistics.getName().equals("Red cards")) {
                                        numArr4 = new Integer[]{Integer.valueOf((int) statistics.getHome().doubleValue()), Integer.valueOf((int) statistics.getAway().doubleValue())};
                                    }
                                    if (statistics.getName().equals("Fouls")) {
                                        numArr5 = new Integer[]{Integer.valueOf((int) statistics.getHome().doubleValue()), Integer.valueOf((int) statistics.getAway().doubleValue())};
                                    }
                                }
                                String cname = matchInfo.getCompetition().getCname();
                                Intrinsics.checkNotNullExpressionValue(cname, "matchInfo.competition.cname");
                                soccerData.setLeageName(cname);
                                Team home = matchInfo.getTeams().getHome();
                                Intrinsics.checkNotNull(home);
                                String abbr = home.getAbbr();
                                Intrinsics.checkNotNull(abbr);
                                Team away = matchInfo.getTeams().getAway();
                                Intrinsics.checkNotNull(away);
                                String abbr2 = away.getAbbr();
                                Intrinsics.checkNotNull(abbr2);
                                if (abbr.length() > 3) {
                                    abbr = abbr.substring(0, 3);
                                    Intrinsics.checkNotNullExpressionValue(abbr, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                if (abbr2.length() > 3) {
                                    abbr2 = abbr2.substring(0, 3);
                                    Intrinsics.checkNotNullExpressionValue(abbr2, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                soccerData.setTeamAShortName(abbr);
                                soccerData.setTeamBShortName(abbr2);
                                Result result = matchInfo.getResult();
                                String str2 = BleConst.GetDeviceTime;
                                if (result != null) {
                                    str2 = result.getHome();
                                    Intrinsics.checkNotNullExpressionValue(str2, "result.home");
                                    str = result.getAway();
                                    Intrinsics.checkNotNullExpressionValue(str, "result.away");
                                } else {
                                    str = BleConst.GetDeviceTime;
                                }
                                if (matchInfo.getPeriods() == null || !matchInfo.getPeriods().isJsonObject()) {
                                    i = 0;
                                } else {
                                    try {
                                        Object fromJson = new Gson().fromJson(matchInfo.getPeriods(), (Class<Object>) Periods.class);
                                        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(\n       …                        )");
                                        Periods periods = (Periods) fromJson;
                                        try {
                                            if (periods.getPs() != null && periods.getP1() != null && periods.getP2() != null && periods.getEt() != null) {
                                                String home2 = result.getHome();
                                                Intrinsics.checkNotNullExpressionValue(home2, "result.home");
                                                int parseInt3 = Integer.parseInt(home2);
                                                if (periods.getP1().getHome() == null) {
                                                    i2 = 0;
                                                } else {
                                                    int intValue4 = periods.getP1().getHome().intValue();
                                                    if (periods.getP2().getHome() == null) {
                                                        intValue3 = 0;
                                                    } else {
                                                        int intValue5 = periods.getP2().getHome().intValue();
                                                        Integer home3 = periods.getEt().getHome() == null ? 0 : periods.getEt().getHome();
                                                        Intrinsics.checkNotNullExpressionValue(home3, "if (periods.et.home == n…l) 0 else periods.et.home");
                                                        intValue3 = intValue5 + home3.intValue();
                                                    }
                                                    i2 = intValue4 + intValue3;
                                                }
                                                parseInt = parseInt3 - i2;
                                                String away2 = result.getAway();
                                                Intrinsics.checkNotNullExpressionValue(away2, "result.away");
                                                parseInt2 = Integer.parseInt(away2);
                                                if (periods.getP1().getAway() == null) {
                                                    i = parseInt2 - i3;
                                                    i3 = parseInt;
                                                    str2 = String.valueOf(Integer.parseInt(str2) - i3);
                                                    str = String.valueOf(Integer.parseInt(str) - i);
                                                } else {
                                                    intValue2 = periods.getP1().getAway().intValue();
                                                    if (periods.getP2().getAway() != null) {
                                                        int intValue6 = periods.getP2().getAway().intValue();
                                                        Integer home4 = periods.getEt().getAway() == null ? 0 : periods.getEt().getHome();
                                                        Intrinsics.checkNotNullExpressionValue(home4, "if (periods.et.away == n…l) 0 else periods.et.home");
                                                        i3 = intValue6 + home4.intValue();
                                                    }
                                                    i3 += intValue2;
                                                    i = parseInt2 - i3;
                                                    i3 = parseInt;
                                                    str2 = String.valueOf(Integer.parseInt(str2) - i3);
                                                    str = String.valueOf(Integer.parseInt(str) - i);
                                                }
                                            } else if (periods.getPs() != null && periods.getP1() != null && periods.getP2() != null) {
                                                String home5 = result.getHome();
                                                Intrinsics.checkNotNullExpressionValue(home5, "result.home");
                                                parseInt = Integer.parseInt(home5) - (periods.getP1().getHome() == null ? 0 : periods.getP1().getHome().intValue() + (periods.getP2().getHome() == null ? 0 : periods.getP2().getHome().intValue()));
                                                String away3 = result.getAway();
                                                Intrinsics.checkNotNullExpressionValue(away3, "result.away");
                                                parseInt2 = Integer.parseInt(away3);
                                                if (periods.getP1().getAway() == null) {
                                                    i = parseInt2 - i3;
                                                    i3 = parseInt;
                                                    str2 = String.valueOf(Integer.parseInt(str2) - i3);
                                                    str = String.valueOf(Integer.parseInt(str) - i);
                                                } else {
                                                    intValue2 = periods.getP1().getAway().intValue();
                                                    if (periods.getP2().getAway() != null) {
                                                        i3 = periods.getP2().getAway().intValue();
                                                    }
                                                    i3 += intValue2;
                                                    i = parseInt2 - i3;
                                                    i3 = parseInt;
                                                    str2 = String.valueOf(Integer.parseInt(str2) - i3);
                                                    str = String.valueOf(Integer.parseInt(str) - i);
                                                }
                                            } else {
                                                i = 0;
                                                try {
                                                    str2 = String.valueOf(Integer.parseInt(str2) - i3);
                                                    str = String.valueOf(Integer.parseInt(str) - i);
                                                } catch (Exception e) {
                                                    e = e;
                                                    e.printStackTrace();
                                                    soccerData.setTeamAScore(str2);
                                                    soccerData.setTeamBScore(str);
                                                    soccerData.setTeamAPenaltyScore(String.valueOf(i3));
                                                    soccerData.setTeamBPenaltyScore(String.valueOf(i));
                                                    soccerData.setSoccerSummary(new SoccerSummary(numArr, numArr2, numArr3, numArr4, numArr5));
                                                    SoccerApiHelper.this.g(soccerData);
                                                }
                                            }
                                        } catch (Exception e2) {
                                            e = e2;
                                            i = 0;
                                            i3 = "result.home";
                                        }
                                    } catch (Exception e3) {
                                        e = e3;
                                        i = 0;
                                    }
                                }
                                soccerData.setTeamAScore(str2);
                                soccerData.setTeamBScore(str);
                                soccerData.setTeamAPenaltyScore(String.valueOf(i3));
                                soccerData.setTeamBPenaltyScore(String.valueOf(i));
                                soccerData.setSoccerSummary(new SoccerSummary(numArr, numArr2, numArr3, numArr4, numArr5));
                                SoccerApiHelper.this.g(soccerData);
                            }
                        });
                    } else {
                        this.this$0.sendNoMatchSelectedToWatch();
                        this.this$0.l();
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
