package com.coveiot.android.sportsnotification;

import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager;
import com.coveiot.android.sportsnotificationsdk.models.scorecard.GetScorecardRes;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiErrorModel;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiListener;
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
@DebugMetadata(c = "com.coveiot.android.sportsnotification.SportsService$callMatchScoreCardAPI$1", f = "SportsService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class SportsService$callMatchScoreCardAPI$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ SportsService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SportsService$callMatchScoreCardAPI$1(SportsService sportsService, Continuation<? super SportsService$callMatchScoreCardAPI$1> continuation) {
        super(2, continuation);
        this.this$0 = sportsService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SportsService$callMatchScoreCardAPI$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SportsService$callMatchScoreCardAPI$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                if (companion.getSelectedMatch(this.this$0) == null) {
                    this.this$0.z();
                } else {
                    MatchListModel selectedMatch = companion.getSelectedMatch(this.this$0);
                    Intrinsics.checkNotNull(selectedMatch);
                    final Integer matchId = selectedMatch.getMatchId();
                    if (matchId == null || matchId.intValue() == 0) {
                        this.this$0.z();
                    } else {
                        SportsNotificationApiManager.Companion companion2 = SportsNotificationApiManager.Companion;
                        int intValue = matchId.intValue();
                        String accessToken = new AccessTokenPreference(this.this$0).getAccessToken();
                        final SportsService sportsService = this.this$0;
                        companion2.getMatchScoreCard(intValue, accessToken, new SportsApiListener<Response<GetScorecardRes>, SportsApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.SportsService$callMatchScoreCardAPI$1.1
                            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
                            public void onError(@NotNull SportsApiErrorModel obj2) {
                                String str;
                                Intrinsics.checkNotNullParameter(obj2, "obj");
                                str = SportsService.this.j;
                                LogHelper.d(str, "callMatchScoreCardAPI onError");
                                if (obj2.getCode() == 401) {
                                    SportsService.this.getAccessToken();
                                }
                            }

                            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
                            public void onSuccess(@NotNull Response<GetScorecardRes> obj2) {
                                Intrinsics.checkNotNullParameter(obj2, "obj");
                                SportsService.this.r(obj2, matchId.intValue());
                            }
                        });
                    }
                }
            } else if (!SportsPreference.Companion.isOfflineStatusSent(this.this$0)) {
                this.this$0.A();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
