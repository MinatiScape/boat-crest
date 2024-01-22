package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.dashboard2.util.StepsDataHelper;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.fitnesschallenges.utils.ExtensionsKt;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnesschallenge.CoveFitnessChallengeApi;
import com.coveiot.coveaccess.fitnesschallenge.model.FitnessChallengeStatsReq;
import com.coveiot.coveaccess.fitnesschallenge.model.GetChallengeStartNEndDateRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FitnessChallengeSyncViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4272a;
    @NotNull
    public ArrayList<FitnessChallengeStatsReq.ChallengeStat> b;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.FitnessChallengeSyncViewModel$getLastSyncDateForFitnessChallenge$1", f = "FitnessChallengeSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, String str2, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$endDate = str;
            this.$startDate = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$endDate, this.$startDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ArrayList arrayList;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                String fitnessChallengeLastSyncDate = FitnessChallengeSessionManager.getInstance(FitnessChallengeSyncViewModel.this.getContext()).getFitnessChallengeLastSyncDate();
                Date time = Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(time);
                if (time.after(simpleDateFormat.parse(this.$endDate))) {
                    format = this.$endDate;
                }
                new ArrayList();
                if (fitnessChallengeLastSyncDate == null) {
                    ThemesUtils themesUtils = ThemesUtils.INSTANCE;
                    String str = this.$startDate;
                    String formattedDate = themesUtils.formattedDate(format, "yyyy-MM-dd");
                    Intrinsics.checkNotNull(formattedDate);
                    List<Date> dates = themesUtils.getDates(str, formattedDate);
                    Intrinsics.checkNotNull(dates, "null cannot be cast to non-null type java.util.ArrayList<java.util.Date>");
                    arrayList = (ArrayList) dates;
                } else {
                    ThemesUtils themesUtils2 = ThemesUtils.INSTANCE;
                    String fitnessChallengeLastSyncDate2 = FitnessChallengeSessionManager.getInstance(FitnessChallengeSyncViewModel.this.getContext()).getFitnessChallengeLastSyncDate();
                    Intrinsics.checkNotNullExpressionValue(fitnessChallengeLastSyncDate2, "getInstance(context).fitnessChallengeLastSyncDate");
                    String formattedDate2 = themesUtils2.formattedDate(format, "yyyy-MM-dd");
                    Intrinsics.checkNotNull(formattedDate2);
                    List<Date> dates2 = themesUtils2.getDates(fitnessChallengeLastSyncDate2, formattedDate2);
                    Intrinsics.checkNotNull(dates2, "null cannot be cast to non-null type java.util.ArrayList<java.util.Date>");
                    arrayList = (ArrayList) dates2;
                }
                FitnessChallengeSyncViewModel.this.setChallengeStatsList(new ArrayList<>());
                if (!arrayList.isEmpty()) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime((Date) it.next());
                        StepsDataHelper stepsDataHelper = StepsDataHelper.INSTANCE;
                        Context context = FitnessChallengeSyncViewModel.this.getContext();
                        Intrinsics.checkNotNullExpressionValue(cal, "cal");
                        DailyWalkData walkDataPreviousDate = stepsDataHelper.getWalkDataPreviousDate(context, cal);
                        if (walkDataPreviousDate != null) {
                            walkDataPreviousDate.getValue();
                            FitnessChallengeSyncViewModel.this.b(ExtensionsKt.toFormattedDateStr(cal, "yyyy-MM-dd"), walkDataPreviousDate.getValue(), walkDataPreviousDate.getCalories(), walkDataPreviousDate.getMeters());
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.FitnessChallengeSyncViewModel$postFitnessChallengeData$1", f = "FitnessChallengeSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $date;
        public final /* synthetic */ double $watchCalories;
        public final /* synthetic */ int $watchDistance;
        public final /* synthetic */ int $watchSteps;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, int i, int i2, double d, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$date = str;
            this.$watchSteps = i;
            this.$watchDistance = i2;
            this.$watchCalories = d;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$date, this.$watchSteps, this.$watchDistance, this.$watchCalories, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:49:0x00e6, code lost:
            if ((r17.$watchCalories == 0.0d) != false) goto L56;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r18) {
            /*
                Method dump skipped, instructions count: 355
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.viewmodel.FitnessChallengeSyncViewModel.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public FitnessChallengeSyncViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4272a = context;
        this.b = new ArrayList<>();
    }

    public final void a(String str, String str2) {
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new a(str2, str, null), 2, null);
    }

    public final void b(String str, int i, double d, int i2) {
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new b(str, i, i2, d, null), 2, null);
    }

    public final void c(List<? extends FitnessChallengeStatsReq.ChallengeStat> list) {
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new FitnessChallengeSyncViewModel$postFitnessChallengeStats$1(this, list, null), 2, null);
    }

    @NotNull
    public final ArrayList<FitnessChallengeStatsReq.ChallengeStat> getChallengeStatsList() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f4272a;
    }

    public final void getFitnessChallengeActiveDateRange(@NotNull final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (CoveUtils.INSTANCE.isNetConnected(context)) {
            CoveFitnessChallengeApi.getFitnessChallengeDates(new CoveApiListener<GetChallengeStartNEndDateRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.viewmodel.FitnessChallengeSyncViewModel$getFitnessChallengeActiveDateRange$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.d("fitnessChallenge", "onError_challengeActiveDateRange:" + new Gson().toJson(coveApiErrorModel));
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull GetChallengeStartNEndDateRes challengeActiveDateRange) {
                    Intrinsics.checkNotNullParameter(challengeActiveDateRange, "challengeActiveDateRange");
                    LogHelper.d("fitnessChallenge", "challengeActiveDateRange:" + new Gson().toJson(challengeActiveDateRange));
                    FitnessChallengeSessionManager.getInstance(context).saveFitnessChallengeActiveDateRange(challengeActiveDateRange);
                    GetChallengeStartNEndDateRes.ActiveDateRange activeDateRange = challengeActiveDateRange.getActiveDateRange();
                    if ((activeDateRange != null ? activeDateRange.getStartDate() : null) != null) {
                        GetChallengeStartNEndDateRes.ActiveDateRange activeDateRange2 = challengeActiveDateRange.getActiveDateRange();
                        if ((activeDateRange2 != null ? activeDateRange2.getEndDate() : null) != null) {
                            FitnessChallengeSyncViewModel fitnessChallengeSyncViewModel = this;
                            String startDate = challengeActiveDateRange.getActiveDateRange().getStartDate();
                            Intrinsics.checkNotNullExpressionValue(startDate, "challengeActiveDateRange.activeDateRange.startDate");
                            String endDate = challengeActiveDateRange.getActiveDateRange().getEndDate();
                            Intrinsics.checkNotNullExpressionValue(endDate, "challengeActiveDateRange.activeDateRange.endDate");
                            fitnessChallengeSyncViewModel.a(startDate, endDate);
                        }
                    }
                }
            });
        } else {
            LogHelper.d("fitnessChallenge", "No Internet");
        }
    }

    public final void setChallengeStatsList(@NotNull ArrayList<FitnessChallengeStatsReq.ChallengeStat> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.b = arrayList;
    }
}
