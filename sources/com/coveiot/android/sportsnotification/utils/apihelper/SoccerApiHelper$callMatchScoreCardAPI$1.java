package com.coveiot.android.sportsnotification.utils.apihelper;

import com.coveiot.android.sportsnotification.AccessTokenPreference;
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
@DebugMetadata(c = "com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$callMatchScoreCardAPI$1", f = "SoccerApiHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class SoccerApiHelper$callMatchScoreCardAPI$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ SoccerApiHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SoccerApiHelper$callMatchScoreCardAPI$1(SoccerApiHelper soccerApiHelper, Continuation<? super SoccerApiHelper$callMatchScoreCardAPI$1> continuation) {
        super(2, continuation);
        this.this$0 = soccerApiHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SoccerApiHelper$callMatchScoreCardAPI$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SoccerApiHelper$callMatchScoreCardAPI$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                    final Integer matchId = selectedSoccerMatch.getMatchId();
                    if (matchId != null && matchId.intValue() != 0) {
                        SportsNotificationApiManager.Companion companion2 = SportsNotificationApiManager.Companion;
                        int intValue = matchId.intValue();
                        String accessToken = new AccessTokenPreference(this.this$0.getContext()).getAccessToken();
                        final SoccerApiHelper soccerApiHelper = this.this$0;
                        companion2.getSoccerMatchInfo(intValue, accessToken, new SportsApiListener<Response<SMatchInfoResponse>, SportsApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$callMatchScoreCardAPI$1.1
                            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
                            public void onError(@NotNull SportsApiErrorModel obj2) {
                                int i;
                                int i2;
                                Intrinsics.checkNotNullParameter(obj2, "obj");
                                LogHelper.d(SoccerApiHelper.this.getTAG(), "callMatchScoreCardAPI onError");
                                if (obj2.getCode() != 401) {
                                    i = SoccerApiHelper.this.c;
                                    if (i >= 3) {
                                        SoccerApiHelper.this.c("match_info", obj2.getResponse());
                                        return;
                                    }
                                    SoccerApiHelper soccerApiHelper2 = SoccerApiHelper.this;
                                    i2 = soccerApiHelper2.c;
                                    soccerApiHelper2.c = i2 + 1;
                                    SoccerApiHelper.this.callMatchScoreCardAPI();
                                    return;
                                }
                                SoccerApiHelper.this.getAccessToken();
                            }

                            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
                            public void onSuccess(@NotNull Response<SMatchInfoResponse> obj2) {
                                Intrinsics.checkNotNullParameter(obj2, "obj");
                                SoccerApiHelper.this.d(obj2, matchId.intValue());
                            }
                        });
                    } else {
                        this.this$0.sendNoMatchSelectedToWatch();
                    }
                } else {
                    this.this$0.sendNoMatchSelectedToWatch();
                }
            } else if (!SportsPreference.Companion.isOfflineStatusSent(this.this$0.getContext())) {
                this.this$0.h();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
