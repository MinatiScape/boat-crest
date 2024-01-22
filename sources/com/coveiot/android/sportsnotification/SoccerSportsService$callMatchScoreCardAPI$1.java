package com.coveiot.android.sportsnotification;

import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiErrorModel;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiListener;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchinfo.SMatchInfoResponse;
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
@DebugMetadata(c = "com.coveiot.android.sportsnotification.SoccerSportsService$callMatchScoreCardAPI$1", f = "SoccerSportsService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class SoccerSportsService$callMatchScoreCardAPI$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ SoccerSportsService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SoccerSportsService$callMatchScoreCardAPI$1(SoccerSportsService soccerSportsService, Continuation<? super SoccerSportsService$callMatchScoreCardAPI$1> continuation) {
        super(2, continuation);
        this.this$0 = soccerSportsService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SoccerSportsService$callMatchScoreCardAPI$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SoccerSportsService$callMatchScoreCardAPI$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                if (companion.getSelectedSoccerMatch(this.this$0) == null) {
                    this.this$0.q();
                } else {
                    MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(this.this$0);
                    Intrinsics.checkNotNull(selectedSoccerMatch);
                    final Integer matchId = selectedSoccerMatch.getMatchId();
                    if (matchId == null || matchId.intValue() == 0) {
                        this.this$0.q();
                    } else {
                        SportsNotificationApiManager.Companion companion2 = SportsNotificationApiManager.Companion;
                        int intValue = matchId.intValue();
                        String accessToken = new AccessTokenPreference(this.this$0).getAccessToken();
                        final SoccerSportsService soccerSportsService = this.this$0;
                        companion2.getSoccerMatchInfo(intValue, accessToken, new SportsApiListener<Response<SMatchInfoResponse>, SportsApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$callMatchScoreCardAPI$1.1
                            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
                            public void onError(@NotNull SportsApiErrorModel obj2) {
                                Intrinsics.checkNotNullParameter(obj2, "obj");
                                LogHelper.d(SoccerSportsService.this.k, "callMatchScoreCardAPI onError");
                                if (obj2.getCode() == 401) {
                                    SoccerSportsService.this.getAccessToken();
                                } else if (SoccerSportsService.this.getRetryCount() >= 3) {
                                    SoccerSportsService.this.j("match_info", obj2.getResponse());
                                } else {
                                    SoccerSportsService soccerSportsService2 = SoccerSportsService.this;
                                    soccerSportsService2.setRetryCount(soccerSportsService2.getRetryCount() + 1);
                                    SoccerSportsService.this.c();
                                }
                            }

                            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
                            public void onSuccess(@NotNull Response<SMatchInfoResponse> obj2) {
                                Intrinsics.checkNotNullParameter(obj2, "obj");
                                SoccerSportsService.this.setRetryCount(0);
                                SoccerSportsService.this.l(obj2, matchId.intValue());
                            }
                        });
                    }
                }
            } else if (!SportsPreference.Companion.isOfflineStatusSent(this.this$0)) {
                this.this$0.r();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
