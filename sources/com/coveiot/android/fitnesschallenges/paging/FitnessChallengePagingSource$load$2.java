package com.coveiot.android.fitnesschallenges.paging;

import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnesschallenge.CoveFitnessChallengeApi;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource$load$2", f = "FitnessChallengePagingSource.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class FitnessChallengePagingSource$load$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ int $position;
    public final /* synthetic */ CompletableDeferred<BuddiesChallengeRes> $response;
    public int label;
    public final /* synthetic */ FitnessChallengePagingSource this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessChallengePagingSource$load$2(FitnessChallengePagingSource fitnessChallengePagingSource, int i, CompletableDeferred<BuddiesChallengeRes> completableDeferred, Continuation<? super FitnessChallengePagingSource$load$2> continuation) {
        super(2, continuation);
        this.this$0 = fitnessChallengePagingSource;
        this.$position = i;
        this.$response = completableDeferred;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FitnessChallengePagingSource$load$2(this.this$0, this.$position, this.$response, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FitnessChallengePagingSource$load$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        int i;
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            i = this.this$0.d;
            if (i == 0) {
                this.this$0.getListener().shouldShowProgressBar(true);
                String type = this.this$0.getType();
                int i2 = this.$position;
                final CompletableDeferred<BuddiesChallengeRes> completableDeferred = this.$response;
                final FitnessChallengePagingSource fitnessChallengePagingSource = this.this$0;
                CoveFitnessChallengeApi.getChallengesByType(type, i2, 10, new CoveApiListener<CommonResponseGeneric<BuddiesChallengeRes>, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource$load$2.1
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        fitnessChallengePagingSource.getListener().shouldShowProgressBar(false);
                        fitnessChallengePagingSource.getListener().shouldShowEmptyLayout(true);
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@Nullable CommonResponseGeneric<BuddiesChallengeRes> commonResponseGeneric) {
                        BuddiesChallengeRes data;
                        Integer totalItems;
                        if ((commonResponseGeneric != null ? commonResponseGeneric.getData() : null) != null) {
                            List<BuddiesChallengeRes.Item> items = commonResponseGeneric.getData().getItems();
                            if (!(items == null || items.isEmpty())) {
                                CompletableDeferred<BuddiesChallengeRes> completableDeferred2 = completableDeferred;
                                BuddiesChallengeRes data2 = commonResponseGeneric.getData();
                                Intrinsics.checkNotNullExpressionValue(data2, "p0.data");
                                completableDeferred2.complete(data2);
                                fitnessChallengePagingSource.getListener().shouldShowProgressBar(false);
                                fitnessChallengePagingSource.getListener().shouldShowEmptyLayout(false);
                                return;
                            }
                        }
                        if ((commonResponseGeneric == null || (data = commonResponseGeneric.getData()) == null || (totalItems = data.getTotalItems()) == null || totalItems.intValue() != 0) ? false : true) {
                            fitnessChallengePagingSource.getListener().shouldShowProgressBar(false);
                            fitnessChallengePagingSource.getListener().shouldShowEmptyLayout(true);
                            return;
                        }
                        fitnessChallengePagingSource.getListener().shouldShowProgressBar(false);
                        fitnessChallengePagingSource.getListener().shouldShowEmptyLayout(false);
                    }
                });
            } else if (i == 1) {
                this.this$0.getListener().shouldShowProgressBar(true);
                String type2 = this.this$0.getType();
                int i3 = this.$position;
                final CompletableDeferred<BuddiesChallengeRes> completableDeferred2 = this.$response;
                final FitnessChallengePagingSource fitnessChallengePagingSource2 = this.this$0;
                CoveFitnessChallengeApi.getMyFitnessChallenges(type2, i3, 10, new CoveApiListener<BuddiesChallengeRes, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource$load$2.2
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        fitnessChallengePagingSource2.getListener().shouldShowProgressBar(false);
                        fitnessChallengePagingSource2.getListener().shouldShowEmptyLayout(true);
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@Nullable BuddiesChallengeRes buddiesChallengeRes) {
                        Integer totalItems;
                        if (buddiesChallengeRes != null) {
                            List<BuddiesChallengeRes.Item> items = buddiesChallengeRes.getItems();
                            if (!(items == null || items.isEmpty())) {
                                completableDeferred2.complete(buddiesChallengeRes);
                                fitnessChallengePagingSource2.getListener().shouldShowProgressBar(false);
                                fitnessChallengePagingSource2.getListener().shouldShowEmptyLayout(false);
                                return;
                            }
                        }
                        if ((buddiesChallengeRes == null || (totalItems = buddiesChallengeRes.getTotalItems()) == null || totalItems.intValue() != 0) ? false : true) {
                            fitnessChallengePagingSource2.getListener().shouldShowProgressBar(false);
                            fitnessChallengePagingSource2.getListener().shouldShowEmptyLayout(true);
                            return;
                        }
                        fitnessChallengePagingSource2.getListener().shouldShowProgressBar(false);
                        fitnessChallengePagingSource2.getListener().shouldShowEmptyLayout(false);
                    }
                });
            } else if (i == 2) {
                this.this$0.getListener().shouldShowProgressBar(true);
                int i4 = this.$position;
                final CompletableDeferred<BuddiesChallengeRes> completableDeferred3 = this.$response;
                final FitnessChallengePagingSource fitnessChallengePagingSource3 = this.this$0;
                CoveFitnessChallengeApi.getMyAchievementDetail(i4, 10, new CoveApiListener<BuddiesChallengeRes, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource$load$2.3
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        fitnessChallengePagingSource3.getListener().shouldShowProgressBar(false);
                        fitnessChallengePagingSource3.getListener().shouldShowEmptyLayout(true);
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@Nullable BuddiesChallengeRes buddiesChallengeRes) {
                        Integer totalItems;
                        if (buddiesChallengeRes != null) {
                            List<BuddiesChallengeRes.Item> items = buddiesChallengeRes.getItems();
                            if (!(items == null || items.isEmpty())) {
                                completableDeferred3.complete(buddiesChallengeRes);
                                fitnessChallengePagingSource3.getListener().shouldShowProgressBar(false);
                                fitnessChallengePagingSource3.getListener().shouldShowEmptyLayout(false);
                                return;
                            }
                        }
                        if ((buddiesChallengeRes == null || (totalItems = buddiesChallengeRes.getTotalItems()) == null || totalItems.intValue() != 0) ? false : true) {
                            fitnessChallengePagingSource3.getListener().shouldShowProgressBar(false);
                            fitnessChallengePagingSource3.getListener().shouldShowEmptyLayout(true);
                            return;
                        }
                        fitnessChallengePagingSource3.getListener().shouldShowProgressBar(false);
                        fitnessChallengePagingSource3.getListener().shouldShowEmptyLayout(false);
                    }
                });
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
