package com.coveiot.android.sportsnotification;

import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.utils.SoccerData;
import com.coveiot.android.sportsnotification.utils.SoccerSummary;
import com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiErrorModel;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiListener;
import com.coveiot.android.sportsnotificationsdk.soccer.models.common.MatchInfo;
import com.coveiot.android.sportsnotificationsdk.soccer.models.common.Team;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchstats.SMatchStatsResponse;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchstats.Statistics;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
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
@DebugMetadata(c = "com.coveiot.android.sportsnotification.SoccerSportsService$callMatchSummaryApi$1", f = "SoccerSportsService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class SoccerSportsService$callMatchSummaryApi$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ SoccerSportsService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SoccerSportsService$callMatchSummaryApi$1(SoccerSportsService soccerSportsService, Continuation<? super SoccerSportsService$callMatchSummaryApi$1> continuation) {
        super(2, continuation);
        this.this$0 = soccerSportsService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SoccerSportsService$callMatchSummaryApi$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SoccerSportsService$callMatchSummaryApi$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (AppUtils.isNetConnected(this.this$0)) {
                SportsPreference.Companion companion = SportsPreference.Companion;
                companion.setOfflineStatus(this.this$0, false);
                if (companion.getSelectedSoccerMatch(this.this$0) != null) {
                    MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(this.this$0);
                    Intrinsics.checkNotNull(selectedSoccerMatch);
                    Integer matchId = selectedSoccerMatch.getMatchId();
                    if (matchId == null || matchId.intValue() == 0) {
                        this.this$0.q();
                        this.this$0.x();
                    } else {
                        SportsNotificationApiManager.Companion companion2 = SportsNotificationApiManager.Companion;
                        int intValue = matchId.intValue();
                        String accessToken = new AccessTokenPreference(this.this$0).getAccessToken();
                        final SoccerSportsService soccerSportsService = this.this$0;
                        companion2.getSoccerMatchStats(intValue, accessToken, new SportsApiListener<Response<SMatchStatsResponse>, SportsApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$callMatchSummaryApi$1.1
                            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
                            public void onError(@NotNull SportsApiErrorModel obj2) {
                                Intrinsics.checkNotNullParameter(obj2, "obj");
                                LogHelper.e(SoccerSportsService.this.k, obj2.getResponse());
                                if (obj2.getCode() == 401) {
                                    SoccerSportsService.this.getAccessToken();
                                } else if (SoccerSportsService.this.getRetryCount() >= 3) {
                                    SoccerSportsService.this.j("match_stats", obj2.getResponse());
                                } else {
                                    SoccerSportsService soccerSportsService2 = SoccerSportsService.this;
                                    soccerSportsService2.setRetryCount(soccerSportsService2.getRetryCount() + 1);
                                    SoccerSportsService.this.d();
                                }
                            }

                            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
                            public void onSuccess(@NotNull Response<SMatchStatsResponse> obj2) {
                                SMatchStatsResponse body;
                                Intrinsics.checkNotNullParameter(obj2, "obj");
                                SoccerSportsService.this.setRetryCount(0);
                                if (obj2.body() == null || (body = obj2.body()) == null) {
                                    return;
                                }
                                SoccerData soccerData = new SoccerData();
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
                                String home2 = matchInfo.getResult().getHome();
                                Intrinsics.checkNotNullExpressionValue(home2, "matchInfo.result.home");
                                soccerData.setTeamAScore(home2);
                                String away2 = matchInfo.getResult().getAway();
                                Intrinsics.checkNotNullExpressionValue(away2, "matchInfo.result.away");
                                soccerData.setTeamBScore(away2);
                                soccerData.setSoccerSummary(new SoccerSummary(numArr, numArr2, numArr3, numArr4, numArr5));
                                SoccerSportsService.this.p(soccerData);
                            }
                        });
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
