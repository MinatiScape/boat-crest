package com.coveiot.android.dashboard2.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerMatrix;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.TodaysStepsData;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.model.ProcessedFitnessData;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.model.SmallHealthCardInfo;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.StepsDataModel;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.repository.profile.read.ProfileDBRead;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.bean.data.TodayTotalData;
import java.util.Calendar;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class StepsDataHelper {
    @NotNull
    public static final StepsDataHelper INSTANCE = new StepsDataHelper();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f4256a = "StepsDataHelper";

    @DebugMetadata(c = "com.coveiot.android.dashboard2.util.StepsDataHelper$saveLatestStepsDataToPreferenceFromWatch$1", f = "StepsDataHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Context $context;
        public final /* synthetic */ SuccessResultListener $listener;
        public final /* synthetic */ TodaysStepsData $todayStepsData;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(TodaysStepsData todaysStepsData, Context context, SuccessResultListener successResultListener, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$todayStepsData = todaysStepsData;
            this.$context = context;
            this.$listener = successResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$todayStepsData, this.$context, this.$listener, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$todayStepsData != null) {
                    StepsDataModel liveStepsData = UserDataManager.getInstance(this.$context).getLiveStepsData(Calendar.getInstance(), BleApiManager.getInstance(this.$context).getBleApi().getMacAddress());
                    StepsDataHelper stepsDataHelper = StepsDataHelper.INSTANCE;
                    Context context = this.$context;
                    int totalSteps = this.$todayStepsData.getTotalSteps();
                    int totalDistance = this.$todayStepsData.getTotalDistance();
                    this.$todayStepsData.getTotalCalories();
                    ProcessedFitnessData processedFitnessData = stepsDataHelper.getProcessedFitnessData(context, totalSteps, totalDistance, this.$todayStepsData.getTotalCalories());
                    liveStepsData.setSteps(processedFitnessData.getSteps());
                    liveStepsData.setDistance(processedFitnessData.getDistance());
                    liveStepsData.setCalories(Boxing.boxDouble(processedFitnessData.getCalorie()));
                    liveStepsData.setTimeStamp(System.currentTimeMillis());
                    UserDataManager.getInstance(this.$context).saveLiveStepsData(liveStepsData, Calendar.getInstance(), BleApiManager.getInstance(this.$context).getBleApi().getMacAddress());
                    SuccessResultListener successResultListener = this.$listener;
                    if (successResultListener != null) {
                        successResultListener.onSuccess();
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.util.StepsDataHelper$saveLiveStepsDataToPreferenceFromWatch$1", f = "StepsDataHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Context $context;
        public final /* synthetic */ SuccessResultListener $listener;
        public final /* synthetic */ LiveStepsData $liveStepsData;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(LiveStepsData liveStepsData, Context context, SuccessResultListener successResultListener, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$liveStepsData = liveStepsData;
            this.$context = context;
            this.$listener = successResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$liveStepsData, this.$context, this.$listener, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            TodayTotalData todayTotalData;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$liveStepsData != null) {
                    Calendar stepTimeStampCal = Calendar.getInstance();
                    stepTimeStampCal.setTimeInMillis(this.$liveStepsData.getTimeStamp());
                    Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
                    Intrinsics.checkNotNullExpressionValue(stepTimeStampCal, "stepTimeStampCal");
                    Calendar calendar = Calendar.getInstance();
                    Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                    if (companion.isSameDate(stepTimeStampCal, calendar)) {
                        StepsDataModel liveStepsData = UserDataManager.getInstance(this.$context).getLiveStepsData(Calendar.getInstance(), BleApiManager.getInstance(this.$context).getBleApi().getMacAddress());
                        if (liveStepsData.getSteps() <= this.$liveStepsData.getLiveSteps()) {
                            StepsDataHelper stepsDataHelper = StepsDataHelper.INSTANCE;
                            Context context = this.$context;
                            int liveSteps = this.$liveStepsData.getLiveSteps();
                            int meters = this.$liveStepsData.getMeters();
                            this.$liveStepsData.getCalories();
                            ProcessedFitnessData processedFitnessData = stepsDataHelper.getProcessedFitnessData(context, liveSteps, meters, this.$liveStepsData.getCalories());
                            liveStepsData.setSteps(processedFitnessData.getSteps());
                            liveStepsData.setDistance(processedFitnessData.getDistance());
                            liveStepsData.setCalories(Boxing.boxDouble(processedFitnessData.getCalorie()));
                            liveStepsData.setTimeStamp(System.currentTimeMillis());
                            if (DeviceUtils.Companion.isMatrixDevice(this.$context) && (todayTotalData = PreferenceManagerMatrix.getInstance(this.$context).getTodayTotalData()) != null) {
                                liveStepsData.setSteps(todayTotalData.getStep());
                                liveStepsData.setCalories(Boxing.boxDouble(todayTotalData.getCalorie() / 1000));
                                liveStepsData.setDistance(todayTotalData.getDistance());
                                liveStepsData.setTimeStamp(todayTotalData.getTimeStamp());
                            }
                            UserDataManager.getInstance(this.$context).saveLiveStepsData(liveStepsData, Calendar.getInstance(), BleApiManager.getInstance(this.$context).getBleApi().getMacAddress());
                        } else if (DeviceUtils.Companion.isMoyangDevice(this.$context)) {
                            liveStepsData.setSteps(this.$liveStepsData.getLiveSteps());
                            liveStepsData.setDistance(this.$liveStepsData.getMeters());
                            liveStepsData.setCalories(Boxing.boxDouble(this.$liveStepsData.getCalories()));
                            liveStepsData.setTimeStamp(System.currentTimeMillis());
                            UserDataManager.getInstance(this.$context).saveLiveStepsData(liveStepsData, Calendar.getInstance(), BleApiManager.getInstance(this.$context).getBleApi().getMacAddress());
                            BlePreferenceManager.savePreference(this.$context, CommonPreference.SHOULD_UPDATE_SETTINGS_BAND, Boxing.boxBoolean(true));
                        }
                        SuccessResultListener successResultListener = this.$listener;
                        if (successResultListener != null) {
                            successResultListener.onSuccess();
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static /* synthetic */ String getMotivationalInfoBasedOnProgress$default(StepsDataHelper stepsDataHelper, int i, Context context, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return stepsDataHelper.getMotivationalInfoBasedOnProgress(i, context);
    }

    public static /* synthetic */ ProcessedFitnessData getProcessedFitnessData$default(StepsDataHelper stepsDataHelper, Context context, int i, int i2, double d, int i3, Object obj) {
        int i4 = (i3 & 2) != 0 ? 0 : i;
        int i5 = (i3 & 4) != 0 ? 0 : i2;
        if ((i3 & 8) != 0) {
            d = 0.0d;
        }
        return stepsDataHelper.getProcessedFitnessData(context, i4, i5, d);
    }

    @NotNull
    public final SmallHealthCardInfo getCalorieSmallHealthCardInfo(@NotNull Context context, double d) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new SmallHealthCardInfo(context.getDrawable(R.drawable.ic_calories_home), String.valueOf(Dashboard2Utils.Companion.getCaloriesValue(Math.abs(d))), String.valueOf(context.getString(R.string.cal_camel_case)), context.getString(R.string.calories_dashboard), null, null, null, 0, null, null, false, 2032, null);
    }

    @NotNull
    public final Pair<Double, Integer> getCaloriesAndDistanceBySteps(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        String height = userDetails != null ? userDetails.getHeight() : null;
        Intrinsics.checkNotNull(height);
        int parseInt = Integer.parseInt(height);
        ProfileData userDetails2 = SessionManager.getInstance(context).getUserDetails();
        String weight = userDetails2 != null ? userDetails2.getWeight() : null;
        Intrinsics.checkNotNull(weight);
        int parseDouble = (int) Double.parseDouble(weight);
        int i2 = ProfileRepository.getInstance().getLatestProfileData(context).walkStrideLength;
        double calculateCalories = AppUtils.calculateCalories(i, parseInt, parseDouble, i2);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isCZDevice(context) || companion.isCADevice(context) || companion.isCYDevice(context) || companion.isPS1Device(context) || companion.isBESDevice(context)) {
            calculateCalories = Dashboard2Utils.Companion.calculateCaloriesForCZ(i, parseInt, parseDouble, i2);
        }
        return new Pair<>(Double.valueOf(calculateCalories), Integer.valueOf(AppUtils.calculateDistance(i, parseInt, i2)));
    }

    @NotNull
    public final Pair<Double, Double> getCaloriesAndDistanceForFitnessChallenge(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        String height = userDetails != null ? userDetails.getHeight() : null;
        Intrinsics.checkNotNull(height);
        int parseInt = Integer.parseInt(height);
        ProfileData userDetails2 = SessionManager.getInstance(context).getUserDetails();
        String weight = userDetails2 != null ? userDetails2.getWeight() : null;
        Intrinsics.checkNotNull(weight);
        int parseDouble = (int) Double.parseDouble(weight);
        int i2 = ProfileRepository.getInstance().getLatestProfileData(context).walkStrideLength;
        if (i2 <= 0) {
            i2 = AppUtils.caluclateWalkingStrideLenght(parseInt);
        }
        return new Pair<>(Double.valueOf((((parseDouble * i) / (160934 / (parseInt * 0.413f))) * 900.0f) / 1000), Double.valueOf(((i * i2) * 1.0f) / 100));
    }

    @Nullable
    public final DailyWalkData getConvertedDailyWalkData(@NotNull Context context, @NotNull StepsDataModel stepsDataModel) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(stepsDataModel, "stepsDataModel");
        LogHelper.d(f4256a, "getConvertedDailyWalkData: called");
        DailyWalkData dailyWalkData = new DailyWalkData();
        dailyWalkData.setmDate(AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(stepsDataModel.getTimeStamp())));
        dailyWalkData.setValue(stepsDataModel.getSteps());
        Double calories = stepsDataModel.getCalories();
        Intrinsics.checkNotNullExpressionValue(calories, "stepsDataModel.calories");
        dailyWalkData.setCalories(calories.doubleValue());
        dailyWalkData.setMeters(stepsDataModel.getDistance());
        dailyWalkData.setMacAddress(BleApiManager.getInstance(context).getBleApi().getMacAddress());
        if (ProfileDBRead.getInstance(context).getLatestProfileData() != null) {
            int i = ProfileDBRead.getInstance(context).getLatestProfileData().stepsTarget;
            dailyWalkData.setStepsTarget(ProfileDBRead.getInstance(context).getLatestProfileData().stepsTarget);
        }
        return dailyWalkData;
    }

    @NotNull
    public final SmallHealthCardInfo getDistanceSmallHealthCardInfo(@NotNull Context context, int i) {
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        Drawable drawable = context.getDrawable(R.drawable.ic_distance_home);
        String valueOf = String.valueOf(Dashboard2Utils.Companion.getDistanceValue(context, Math.abs(i)));
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            string = context.getString(R.string.mi_small);
        } else {
            string = context.getString(R.string.km_small);
        }
        return new SmallHealthCardInfo(drawable, valueOf, String.valueOf(string), context.getString(R.string.distance), null, null, null, 0, null, null, false, 2032, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004c  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getMotivationalInfoBasedOnProgress(int r8, @org.jetbrains.annotations.NotNull android.content.Context r9) {
        /*
            r7 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            int r0 = com.coveiot.android.dashboard2.R.string.great_start_keep_moving
            java.lang.String r1 = r9.getString(r0)
            java.lang.String r2 = "context.getString(R.stri….great_start_keep_moving)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.coveiot.covepreferences.UserDataManager r3 = com.coveiot.covepreferences.UserDataManager.getInstance(r9)
            com.coveiot.covepreferences.data.FitnessGoal r3 = r3.getFitnessGoalStepsData()
            if (r3 == 0) goto L1d
            java.lang.Integer r4 = r3.target
            goto L1e
        L1d:
            r4 = 0
        L1e:
            if (r4 == 0) goto L37
            java.lang.Integer r4 = r3.target
            java.lang.String r5 = "fitnessGoal.target"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            int r4 = r4.intValue()
            if (r4 <= 0) goto L37
            java.lang.Integer r3 = r3.target
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
            int r3 = r3.intValue()
            goto L39
        L37:
            r3 = 10000(0x2710, float:1.4013E-41)
        L39:
            r4 = 100
            int r8 = r8 * r4
            int r8 = r8 / r3
            if (r8 < r4) goto L4c
            int r8 = com.coveiot.android.dashboard2.R.string.awesom_you_did_it
            java.lang.String r1 = r9.getString(r8)
            java.lang.String r8 = "context.getString(R.string.awesom_you_did_it)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
            goto La4
        L4c:
            r3 = 76
            r5 = 1
            r6 = 0
            if (r3 > r8) goto L56
            if (r8 >= r4) goto L56
            r4 = r5
            goto L57
        L56:
            r4 = r6
        L57:
            if (r4 == 0) goto L65
            int r8 = com.coveiot.android.dashboard2.R.string.impresive_continue_moving
            java.lang.String r1 = r9.getString(r8)
            java.lang.String r8 = "context.getString(R.stri…mpresive_continue_moving)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
            goto La4
        L65:
            r4 = 51
            if (r4 > r8) goto L6d
            if (r8 >= r3) goto L6d
            r3 = r5
            goto L6e
        L6d:
            r3 = r6
        L6e:
            if (r3 == 0) goto L7c
            int r8 = com.coveiot.android.dashboard2.R.string.you_can_do_it_few_more
            java.lang.String r1 = r9.getString(r8)
            java.lang.String r8 = "context.getString(R.string.you_can_do_it_few_more)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
            goto La4
        L7c:
            r3 = 25
            if (r3 > r8) goto L84
            if (r8 >= r4) goto L84
            r3 = r5
            goto L85
        L84:
            r3 = r6
        L85:
            if (r3 == 0) goto L93
            int r8 = com.coveiot.android.dashboard2.R.string.keep_going_looks_like
            java.lang.String r1 = r9.getString(r8)
            java.lang.String r8 = "context.getString(R.string.keep_going_looks_like)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
            goto La4
        L93:
            if (r5 > r8) goto L9a
            r3 = 26
            if (r8 >= r3) goto L9a
            goto L9b
        L9a:
            r5 = r6
        L9b:
            if (r5 == 0) goto La4
            java.lang.String r1 = r9.getString(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
        La4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.util.StepsDataHelper.getMotivationalInfoBasedOnProgress(int, android.content.Context):java.lang.String");
    }

    @NotNull
    public final ProcessedFitnessData getProcessedFitnessData(@NotNull Context context, int i, int i2, double d) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProcessedFitnessData processedFitnessData = new ProcessedFitnessData(0, 0, 0.0d, 7, null);
        processedFitnessData.setSteps(i);
        BleApiManager bleApiManager = BleApiManager.getInstance(context);
        if ((bleApiManager != null ? bleApiManager.getDeviceType() : null) != DeviceType.v2) {
            BleApiManager bleApiManager2 = BleApiManager.getInstance(context);
            if ((bleApiManager2 != null ? bleApiManager2.getDeviceType() : null) != DeviceType.v7) {
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                if (!companion.isCZDevice(context) && !companion.isCADevice(context) && BleApiManager.getInstance(context).getDeviceType() != DeviceType.smartT) {
                    processedFitnessData.setDistance(i2);
                    processedFitnessData.setCalorie(d);
                    return processedFitnessData;
                }
            }
        }
        String height = SessionManager.getInstance(context).getUserDetails().getHeight();
        Intrinsics.checkNotNullExpressionValue(height, "getInstance(context)\n   …      .userDetails.height");
        int parseInt = Integer.parseInt(height);
        String weight = SessionManager.getInstance(context).getUserDetails().getWeight();
        Intrinsics.checkNotNullExpressionValue(weight, "getInstance(context)\n   …      .userDetails.weight");
        int parseDouble = (int) Double.parseDouble(weight);
        EntityProfile latestProfileData = ProfileRepository.getInstance().getLatestProfileData(context);
        Integer valueOf = latestProfileData != null ? Integer.valueOf(latestProfileData.walkStrideLength) : null;
        double calculateCalories = AppUtils.calculateCalories(processedFitnessData.getSteps(), parseInt, parseDouble, valueOf != null ? valueOf.intValue() : 0);
        DeviceUtils.Companion companion2 = DeviceUtils.Companion;
        if (companion2.isCZDevice(context) || companion2.isCADevice(context)) {
            calculateCalories = Dashboard2Utils.Companion.calculateCaloriesForCZ(i, parseInt, parseDouble, valueOf != null ? valueOf.intValue() : 0);
        }
        processedFitnessData.setDistance(AppUtils.calculateDistance(i, parseInt, valueOf != null ? valueOf.intValue() : 0));
        processedFitnessData.setCalorie(calculateCalories);
        return processedFitnessData;
    }

    @NotNull
    public final String getTAG() {
        return f4256a;
    }

    @Nullable
    public final DailyWalkData getWalkDataCurrentDate(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return WalkDBRead.getInstance(context).getDailyWalkDataWithDate(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"), BleApiManager.getInstance(context).getBleApi().getMacAddress());
    }

    @Nullable
    public final DailyWalkData getWalkDataPreviousDate(@NotNull Context context, @NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        return WalkDBRead.getInstance(context).getDailyWalkDataWithDate(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), BleApiManager.getInstance(context).getBleApi().getMacAddress());
    }

    public final void saveLatestStepsDataToPreferenceFromWatch(@NotNull Context context, @NotNull TodaysStepsData todayStepsData, @Nullable SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(todayStepsData, "todayStepsData");
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(todayStepsData, context, successResultListener, null), 2, null);
    }

    public final void saveLiveStepsDataToPreferenceFromWatch(@NotNull Context context, @NotNull LiveStepsData liveStepsData, @Nullable SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(liveStepsData, "liveStepsData");
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new b(liveStepsData, context, successResultListener, null), 2, null);
    }

    @Nullable
    public final DailyWalkData getConvertedDailyWalkData(@NotNull Context context, @NotNull TodayTotalData todayTotalData) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(todayTotalData, "todayTotalData");
        LogHelper.d(f4256a, "getConvertedDailyWalkData: called");
        DailyWalkData dailyWalkData = new DailyWalkData();
        dailyWalkData.setmDate(AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(todayTotalData.getTimeStamp())));
        dailyWalkData.setValue(todayTotalData.getStep());
        dailyWalkData.setCalories(todayTotalData.getCalorie() / 1000);
        dailyWalkData.setMeters(todayTotalData.getDistance());
        dailyWalkData.setMacAddress(BleApiManager.getInstance(context).getBleApi().getMacAddress());
        if (ProfileDBRead.getInstance(context).getLatestProfileData() != null) {
            int i = ProfileDBRead.getInstance(context).getLatestProfileData().stepsTarget;
            dailyWalkData.setStepsTarget(ProfileDBRead.getInstance(context).getLatestProfileData().stepsTarget);
        }
        return dailyWalkData;
    }
}
