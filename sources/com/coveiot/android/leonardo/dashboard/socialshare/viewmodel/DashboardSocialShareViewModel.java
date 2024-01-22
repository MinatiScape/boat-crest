package com.coveiot.android.leonardo.dashboard.socialshare.viewmodel;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.IndoorOutdoor;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.socialshare.model.BadgeShareData;
import com.coveiot.android.leonardo.dashboard.socialshare.model.FitnessShareData;
import com.coveiot.android.leonardo.dashboard.socialshare.model.ShareData;
import com.coveiot.android.leonardo.dashboard.socialshare.model.WorkoutShareData;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.LastNightSleepData;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.StepsDataModel;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.heartrate.HeartRateRepository;
import com.coveiot.repository.hrv.datasource.HRVRepository;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.repository.stress.StressRepository;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class DashboardSocialShareViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4810a;
    @NotNull
    public final MutableLiveData<List<ShareData>> b;
    public LifecycleOwner lifecycleOwner;

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.socialshare.viewmodel.DashboardSocialShareViewModel$fetchMyBadges$2", f = "DashboardSocialShareViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ List<ShareData> $listShareData;
        public int label;
        public final /* synthetic */ DashboardSocialShareViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(List<ShareData> list, DashboardSocialShareViewModel dashboardSocialShareViewModel, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$listShareData = list;
            this.this$0 = dashboardSocialShareViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$listShareData, this.this$0, continuation);
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
                this.$listShareData.add(this.this$0.l());
                this.this$0.getLiveDataSocialShare().postValue(this.$listShareData);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.socialshare.viewmodel.DashboardSocialShareViewModel$fetchSocialShareData$1", f = "DashboardSocialShareViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ArrayList arrayList = new ArrayList();
                arrayList.add(DashboardSocialShareViewModel.this.p());
                if (BleApiManager.getInstance(DashboardSocialShareViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isHeartRateSupported()) {
                    arrayList.add(DashboardSocialShareViewModel.this.i());
                }
                if (BleApiManager.getInstance(DashboardSocialShareViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isManualHRVMeasurementSupported() || BleApiManager.getInstance(DashboardSocialShareViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
                    arrayList.add(DashboardSocialShareViewModel.this.j());
                }
                if (BleApiManager.getInstance(DashboardSocialShareViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isManualSpo2SupportedOnBand() || BleApiManager.getInstance(DashboardSocialShareViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                    arrayList.add(DashboardSocialShareViewModel.this.m());
                }
                if (BleApiManager.getInstance(DashboardSocialShareViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isSleepSupported()) {
                    arrayList.add(DashboardSocialShareViewModel.this.o());
                }
                if (BleApiManager.getInstance(DashboardSocialShareViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isManualStressMeasurementSupported() || BleApiManager.getInstance(DashboardSocialShareViewModel.this.getContext()).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
                    arrayList.add(DashboardSocialShareViewModel.this.q());
                }
                if (UserDataManager.getInstance(DashboardSocialShareViewModel.this.getContext()).isEnableSleepEnergyScoreFeature(DashboardSocialShareViewModel.this.getContext())) {
                    arrayList.add(DashboardSocialShareViewModel.this.h());
                }
                DashboardSocialShareViewModel.this.c(arrayList);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public DashboardSocialShareViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4810a = context;
        this.b = new MutableLiveData<>();
    }

    public final void c(final List<ShareData> list) {
        if (!AppUtils.isNetConnected(this.f4810a)) {
            e.e(LifecycleOwnerKt.getLifecycleScope(getLifecycleOwner()), Dispatchers.getIO(), null, new a(list, this, null), 2, null);
        } else {
            CoveLeaderboardApi.getMyBadges(new CoveApiListener<MyBadgesModel, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.socialshare.viewmodel.DashboardSocialShareViewModel$fetchMyBadges$1

                @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.socialshare.viewmodel.DashboardSocialShareViewModel$fetchMyBadges$1$onError$1", f = "DashboardSocialShareViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes4.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ List<ShareData> $listShareData;
                    public int label;
                    public final /* synthetic */ DashboardSocialShareViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(List<ShareData> list, DashboardSocialShareViewModel dashboardSocialShareViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$listShareData = list;
                        this.this$0 = dashboardSocialShareViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.$listShareData, this.this$0, continuation);
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
                            this.$listShareData.add(this.this$0.l());
                            this.this$0.getLiveDataSocialShare().postValue(this.$listShareData);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.socialshare.viewmodel.DashboardSocialShareViewModel$fetchMyBadges$1$onSuccess$1", f = "DashboardSocialShareViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes4.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ List<ShareData> $listShareData;
                    public final /* synthetic */ MyBadgesModel $myBadgesModel;
                    public int label;
                    public final /* synthetic */ DashboardSocialShareViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(DashboardSocialShareViewModel dashboardSocialShareViewModel, MyBadgesModel myBadgesModel, List<ShareData> list, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = dashboardSocialShareViewModel;
                        this.$myBadgesModel = myBadgesModel;
                        this.$listShareData = list;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, this.$myBadgesModel, this.$listShareData, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        ShareData k;
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            LeaderBoardDataUtiils.saveMyBadges(this.this$0.getContext(), new Gson().toJson(this.$myBadgesModel));
                            MyBadgesModel myBadgesModel = this.$myBadgesModel;
                            if ((myBadgesModel != null ? myBadgesModel.getData() : null) != null) {
                                MyBadgesModel myBadgesModel2 = this.$myBadgesModel;
                                MyBadgesModel.DataBean data = myBadgesModel2 != null ? myBadgesModel2.getData() : null;
                                Intrinsics.checkNotNull(data);
                                List<MyBadgesModel.DataBean.BadgesBean> badges = data.getBadges();
                                if (!(badges == null || badges.isEmpty())) {
                                    DashboardSocialShareViewModel dashboardSocialShareViewModel = this.this$0;
                                    MyBadgesModel myBadgesModel3 = this.$myBadgesModel;
                                    Intrinsics.checkNotNull(myBadgesModel3);
                                    MyBadgesModel.DataBean.BadgesBean badgesBean = myBadgesModel3.getData().getBadges().get(0);
                                    Intrinsics.checkNotNullExpressionValue(badgesBean, "myBadgesModel!!.data.badges[0]");
                                    k = dashboardSocialShareViewModel.k(badgesBean);
                                    if (k != null) {
                                        this.$listShareData.add(k);
                                    }
                                    this.$listShareData.add(this.this$0.l());
                                    this.this$0.getLiveDataSocialShare().postValue(this.$listShareData);
                                    return Unit.INSTANCE;
                                }
                            }
                            BadgeShareData badgeShareData = new BadgeShareData();
                            badgeShareData.setShareType(ShareData.ShareType.BADGES);
                            badgeShareData.setTitle(this.this$0.getContext().getString(R.string.last_badge_earned));
                            this.$listShareData.add(badgeShareData);
                            this.$listShareData.add(this.this$0.l());
                            this.this$0.getLiveDataSocialShare().postValue(this.$listShareData);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    e.e(LifecycleOwnerKt.getLifecycleScope(DashboardSocialShareViewModel.this.getLifecycleOwner()), Dispatchers.getIO(), null, new a(list, DashboardSocialShareViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull MyBadgesModel myBadgesModel) {
                    Intrinsics.checkNotNullParameter(myBadgesModel, "myBadgesModel");
                    e.e(LifecycleOwnerKt.getLifecycleScope(DashboardSocialShareViewModel.this.getLifecycleOwner()), Dispatchers.getIO(), null, new b(DashboardSocialShareViewModel.this, myBadgesModel, list, null), 2, null);
                }
            });
        }
    }

    public final Pair<Double, Integer> d(int i) {
        ProfileData userDetails = SessionManager.getInstance(this.f4810a).getUserDetails();
        String height = userDetails != null ? userDetails.getHeight() : null;
        Intrinsics.checkNotNull(height);
        int parseInt = Integer.parseInt(height);
        ProfileData userDetails2 = SessionManager.getInstance(this.f4810a).getUserDetails();
        String weight = userDetails2 != null ? userDetails2.getWeight() : null;
        Intrinsics.checkNotNull(weight);
        int parseDouble = (int) Double.parseDouble(weight);
        int i2 = ProfileRepository.getInstance().getLatestProfileData(this.f4810a).walkStrideLength;
        double calculateCalories = AppUtils.calculateCalories(i, parseInt, parseDouble, i2);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isCZDevice(this.f4810a) || companion.isCADevice(this.f4810a) || companion.isCYDevice(this.f4810a) || companion.isPS1Device(this.f4810a) || companion.isBESDevice(this.f4810a)) {
            calculateCalories = PayUtils.INSTANCE.calculateCaloriesForCZ(i, parseInt, parseDouble, i2);
        }
        return new Pair<>(Double.valueOf(calculateCalories), Integer.valueOf(AppUtils.calculateDistance(i, parseInt, i2)));
    }

    public final void fetchSocialShareData() {
        e.e(LifecycleOwnerKt.getLifecycleScope(getLifecycleOwner()), Dispatchers.getIO(), null, new b(null), 2, null);
    }

    @NotNull
    public final Context getContext() {
        return this.f4810a;
    }

    @NotNull
    public final LifecycleOwner getLifecycleOwner() {
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        if (lifecycleOwner != null) {
            return lifecycleOwner;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lifecycleOwner");
        return null;
    }

    @NotNull
    public final MutableLiveData<List<ShareData>> getLiveDataSocialShare() {
        return this.b;
    }

    public final ShareData h() {
        Integer sleepScore;
        ArrayList<EnergyData> arrayList;
        EnergyData energyData;
        ShareData shareData = new ShareData();
        shareData.setTitle(this.f4810a.getString(R.string.energy_meter));
        shareData.setShareType(ShareData.ShareType.ENERGY);
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        DailyWalkData r = r(calendar);
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        SleepScoreData n = n(calendar2);
        if ((r != null && r.getValue() > 0) || (n != null && ((sleepScore = n.getSleepScore()) == null || sleepScore.intValue() != -1))) {
            String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
            Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …-MM-dd\"\n                )");
            EnergyScoreDbData energyScoreData = EnergyScoreRepository.Companion.getInstance(this.f4810a).getEnergyScoreData(formatDate, BleApiManager.getInstance(this.f4810a).getBleApi().getMacAddress());
            if (energyScoreData != null && (arrayList = energyScoreData.data) != null && (energyData = arrayList.get(0)) != null) {
                shareData.setValue(String.valueOf(energyData.getCurrentEnergyLevel()));
            }
        }
        return shareData;
    }

    public final ShareData i() {
        ShareData shareData = new ShareData();
        shareData.setTitle(this.f4810a.getString(R.string.heart_rate));
        shareData.setShareType(ShareData.ShareType.HR);
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        EntityDailyHeartRateData dailyHeartRateData = HeartRateRepository.Companion.getInstance(this.f4810a).getDailyHeartRateData(calendar, BleApiManager.getInstance(this.f4810a).getBleApi().getMacAddress());
        if (dailyHeartRateData != null) {
            dailyHeartRateData.getMinHeartRate();
            if (dailyHeartRateData.getMinHeartRate() > 0) {
                shareData.setMin(Integer.valueOf(dailyHeartRateData.getMinHeartRate()).toString());
            } else {
                shareData.setMin(AppConstants.TWO_DASH_STRING.getValue());
            }
            dailyHeartRateData.getMaxHeartRate();
            if (dailyHeartRateData.getMaxHeartRate() > 0) {
                shareData.setMax(Integer.valueOf(dailyHeartRateData.getMaxHeartRate()).toString());
            } else {
                shareData.setMax(AppConstants.TWO_DASH_STRING.getValue());
            }
        }
        return shareData;
    }

    public final ShareData j() {
        ShareData shareData = new ShareData();
        shareData.setTitle(this.f4810a.getString(R.string.hrv_caps));
        shareData.setShareType(ShareData.ShareType.HRV);
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        DailyHRV dailyHrvData = HRVRepository.Companion.getInstance(this.f4810a).getDailyHrvData(calendar, BleApiManager.getInstance(this.f4810a).getBleApi().getMacAddress());
        if (dailyHrvData != null) {
            double d = dailyHrvData.hrv_low;
            if (d > 0.0d) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%.02f", Arrays.copyOf(new Object[]{Double.valueOf(PayUtils.INSTANCE.calculationFormulaHRV(d))}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                shareData.setMin(format);
            } else {
                shareData.setMin(AppConstants.TWO_DASH_STRING.getValue());
            }
            double d2 = dailyHrvData.hrv_high;
            if (d2 > 0.0d) {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String format2 = String.format("%.02f", Arrays.copyOf(new Object[]{Double.valueOf(PayUtils.INSTANCE.calculationFormulaHRV(d2))}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                shareData.setMax(format2);
            } else {
                shareData.setMax(AppConstants.TWO_DASH_STRING.getValue());
            }
            double d3 = dailyHrvData.hrv_avg;
            if (d3 > 0.0d) {
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String format3 = String.format("%.02f", Arrays.copyOf(new Object[]{Double.valueOf(PayUtils.INSTANCE.calculationFormulaHRV(d3))}, 1));
                Intrinsics.checkNotNullExpressionValue(format3, "format(format, *args)");
                shareData.setValue(format3);
            } else {
                shareData.setValue(AppConstants.TWO_DASH_STRING.getValue());
            }
        }
        return shareData;
    }

    public final ShareData k(MyBadgesModel.DataBean.BadgesBean badgesBean) {
        BadgeShareData badgeShareData = new BadgeShareData();
        badgeShareData.setShareType(ShareData.ShareType.BADGES);
        badgeShareData.setBadgeName(badgesBean.getBadgeName());
        badgeShareData.setTitle(this.f4810a.getString(R.string.last_badge_earned));
        badgeShareData.setBadgeDescription(badgesBean.getBadgeDescription());
        badgeShareData.setDynamicImageUrl(badgesBean.getBadgeImageUrl());
        List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels = badgesBean.getBadgeLevels();
        if (!(badgeLevels == null || badgeLevels.isEmpty())) {
            MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean = null;
            for (MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean2 : badgesBean.getBadgeLevels()) {
                String obtainedDate = badgeLevelsBean2.getObtainedDate();
                if (!(obtainedDate == null || obtainedDate.length() == 0)) {
                    if (badgeLevelsBean != null) {
                        String obtainedDate2 = badgeLevelsBean.getObtainedDate();
                        String obtainedDate3 = badgeLevelsBean2.getObtainedDate();
                        Intrinsics.checkNotNullExpressionValue(obtainedDate3, "levelBean.obtainedDate");
                        if (obtainedDate2.compareTo(obtainedDate3) < 0) {
                        }
                    }
                    badgeLevelsBean = badgeLevelsBean2;
                }
            }
            if (badgeLevelsBean != null) {
                badgeShareData.setBadgeName(badgesBean.getBadgeName() + Soundex.SILENT_MARKER + badgeLevelsBean.getLevelName());
                badgeShareData.setBadgeDescription(badgeLevelsBean.getLevelDescription());
                badgeShareData.setDynamicImageUrl(badgeLevelsBean.getLevelImageUrl());
            }
        }
        return badgeShareData;
    }

    public final ShareData l() {
        String str;
        WorkoutShareData workoutShareData = new WorkoutShareData();
        workoutShareData.setShareType(ShareData.ShareType.WORKOUT);
        String date = AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(date, "date");
        EntityWorkoutSession lastSessionDataByDate = WorkoutSessionRepository.Companion.getInstance(this.f4810a).getLastSessionDataByDate(date, BleApiManager.getInstance(this.f4810a).getBleApi().getMacAddress());
        if (lastSessionDataByDate != null) {
            WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
            workoutShareData.setDuration(workoutUtils.getFormattedDuration(lastSessionDataByDate.getSession_duration()));
            ActivityMode activityMode = workoutUtils.getActivityMode(lastSessionDataByDate.getActivity_type());
            workoutShareData.setTitle(workoutUtils.getActivityModeNames(activityMode, this.f4810a, lastSessionDataByDate));
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if ((companion.isSmaDevice(this.f4810a) || companion.isMatrixDevice(this.f4810a)) && m.equals$default(lastSessionDataByDate.getActivity_type(), ActivityMode.CYCLE.name(), false, 2, null) && m.equals(lastSessionDataByDate.getIndoor_outdoor(), IndoorOutdoor.INDOOR.name(), true)) {
                workoutShareData.setTitle(this.f4810a.getString(R.string.spinning_title_case));
            }
            Context context = this.f4810a;
            if (lastSessionDataByDate.getIndoor_outdoor() != null) {
                str = lastSessionDataByDate.getIndoor_outdoor();
                Intrinsics.checkNotNull(str);
            } else {
                str = null;
            }
            WorkoutImageBean imageUiBean = workoutUtils.getImageUiBean(context, activityMode, str, lastSessionDataByDate.getActivityId(), lastSessionDataByDate.getCategoryId());
            workoutShareData.setDynamicImage(imageUiBean != null ? imageUiBean.getImage() : null);
            lastSessionDataByDate.getMin_hr();
            if (lastSessionDataByDate.getMin_hr() > 0) {
                workoutShareData.setMin(String.valueOf(lastSessionDataByDate.getMin_hr()));
            }
            lastSessionDataByDate.getMax_hr();
            if (lastSessionDataByDate.getMax_hr() > 0) {
                workoutShareData.setMax(String.valueOf(lastSessionDataByDate.getMax_hr()));
            }
            workoutShareData.setCalories((int) lastSessionDataByDate.getTotal_calories());
        }
        return workoutShareData;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.coveiot.android.leonardo.dashboard.socialshare.model.ShareData m() {
        /*
            r4 = this;
            com.coveiot.android.leonardo.dashboard.socialshare.model.ShareData r0 = new com.coveiot.android.leonardo.dashboard.socialshare.model.ShareData
            r0.<init>()
            android.content.Context r1 = r4.f4810a
            r2 = 2131954956(0x7f130d0c, float:1.9546426E38)
            java.lang.String r1 = r1.getString(r2)
            r0.setTitle(r1)
            com.coveiot.android.leonardo.dashboard.socialshare.model.ShareData$ShareType r1 = com.coveiot.android.leonardo.dashboard.socialshare.model.ShareData.ShareType.SPO2
            r0.setShareType(r1)
            com.coveiot.repository.periodicspo2.PeriodicSpo2Repository$Companion r1 = com.coveiot.repository.periodicspo2.PeriodicSpo2Repository.Companion
            android.content.Context r2 = r4.f4810a
            java.lang.Object r1 = r1.getInstance(r2)
            com.coveiot.repository.periodicspo2.PeriodicSpo2Repository r1 = (com.coveiot.repository.periodicspo2.PeriodicSpo2Repository) r1
            java.util.Calendar r2 = java.util.Calendar.getInstance()
            java.lang.String r3 = "getInstance()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            android.content.Context r3 = r4.f4810a
            com.coveiot.android.bleabstract.api.BleApiManager r3 = com.coveiot.android.bleabstract.api.BleApiManager.getInstance(r3)
            com.coveiot.android.bleabstract.api.BleApi r3 = r3.getBleApi()
            java.lang.String r3 = r3.getMacAddress()
            com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2 r1 = r1.getDailySpo2Data(r2, r3)
            r2 = 0
            if (r1 == 0) goto L45
            int r3 = r1.spo2_low
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L46
        L45:
            r3 = r2
        L46:
            if (r3 == 0) goto L6d
            if (r1 == 0) goto L51
            int r3 = r1.spo2_low
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L52
        L51:
            r3 = r2
        L52:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            int r3 = r3.intValue()
            if (r3 <= 0) goto L6d
            if (r1 == 0) goto L68
            int r3 = r1.spo2_low
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.String r3 = r3.toString()
            goto L69
        L68:
            r3 = r2
        L69:
            r0.setMin(r3)
            goto L76
        L6d:
            com.coveiot.android.leonardo.utils.AppConstants r3 = com.coveiot.android.leonardo.utils.AppConstants.TWO_DASH_STRING
            java.lang.String r3 = r3.getValue()
            r0.setMin(r3)
        L76:
            if (r1 == 0) goto L7f
            int r3 = r1.spo2_high
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L80
        L7f:
            r3 = r2
        L80:
            if (r3 == 0) goto La5
            if (r1 == 0) goto L8b
            int r3 = r1.spo2_high
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L8c
        L8b:
            r3 = r2
        L8c:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            int r3 = r3.intValue()
            if (r3 <= 0) goto La5
            if (r1 == 0) goto La1
            int r1 = r1.spo2_high
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = r1.toString()
        La1:
            r0.setMax(r2)
            goto Lae
        La5:
            com.coveiot.android.leonardo.utils.AppConstants r1 = com.coveiot.android.leonardo.utils.AppConstants.TWO_DASH_STRING
            java.lang.String r1 = r1.getValue()
            r0.setMax(r1)
        Lae:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.socialshare.viewmodel.DashboardSocialShareViewModel.m():com.coveiot.android.leonardo.dashboard.socialshare.model.ShareData");
    }

    public final SleepScoreData n(Calendar calendar) {
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …-MM-dd\"\n                )");
        return SleepScoreRepository.Companion.getInstance(this.f4810a).getSleepScoreData(formatDate, BleApiManager.getInstance(this.f4810a).getBleApi().getMacAddress());
    }

    public final ShareData o() {
        Integer sleepScore;
        Integer sleepScore2;
        ShareData shareData = new ShareData();
        shareData.setShareType(ShareData.ShareType.SLEEP);
        shareData.setTitle(this.f4810a.getString(R.string.sleep));
        if (UserDataManager.getInstance(this.f4810a).isEnableSleepEnergyScoreFeature(this.f4810a)) {
            shareData.setTitle(this.f4810a.getString(R.string.sleep_score));
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
            SleepScoreData n = n(calendar);
            if (n != null) {
                Integer sleepScore3 = n.getSleepScore();
                shareData.setComputedValue(sleepScore3 != null ? sleepScore3.toString() : null);
            } else {
                shareData.setComputedValue(BleConst.GetDeviceTime);
            }
        } else {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isIDODevice(this.f4810a)) {
                shareData.setTitle(this.f4810a.getString(R.string.sleep_score));
                PayUtils payUtils = PayUtils.INSTANCE;
                Context context = this.f4810a;
                String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(Calendar.getI…nce().time, \"yyyy-MM-dd\")");
                SleepScoreData sleepScoreFromDbWithDate = payUtils.getSleepScoreFromDbWithDate(context, formatDate);
                if (sleepScoreFromDbWithDate != null && (sleepScore2 = sleepScoreFromDbWithDate.getSleepScore()) != null) {
                    r2 = sleepScore2.toString();
                }
                shareData.setComputedValue(r2);
            } else if (companion.isTouchELXDevice(this.f4810a)) {
                shareData.setTitle(this.f4810a.getString(R.string.sleep_score));
                PayUtils payUtils2 = PayUtils.INSTANCE;
                Context context2 = this.f4810a;
                String formatDate2 = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(Calendar.getI…nce().time, \"yyyy-MM-dd\")");
                SleepScoreData sleepScoreFromDbWithDate2 = payUtils2.getSleepScoreFromDbWithDate(context2, formatDate2);
                if (sleepScoreFromDbWithDate2 != null && (sleepScore = sleepScoreFromDbWithDate2.getSleepScore()) != null) {
                    r2 = sleepScore.toString();
                }
                shareData.setComputedValue(r2);
            }
        }
        LastNightSleepData lastNightSleepData = UserDataManager.getInstance(this.f4810a).getLastNightSleepData(BleApiManager.getInstance(this.f4810a).getBleApi().getMacAddress());
        if (lastNightSleepData != null) {
            shareData.setDuration(String.valueOf(Integer.valueOf(lastNightSleepData.getDuration())));
        }
        return shareData;
    }

    public final ShareData p() {
        Integer num;
        FitnessShareData fitnessShareData = new FitnessShareData();
        fitnessShareData.setShareType(ShareData.ShareType.STEPS);
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this.f4810a).getFitnessGoalStepsData();
        fitnessShareData.setTarget((fitnessGoalStepsData == null || (num = fitnessGoalStepsData.target) == null) ? null : String.valueOf(num));
        fitnessShareData.setSteps(new ArrayList<>());
        ArrayList<String> steps = fitnessShareData.getSteps();
        Intrinsics.checkNotNull(steps);
        steps.add(null);
        ArrayList<String> steps2 = fitnessShareData.getSteps();
        Intrinsics.checkNotNull(steps2);
        steps2.add(null);
        Calendar cal = Calendar.getInstance();
        cal.add(6, -1);
        Intrinsics.checkNotNullExpressionValue(cal, "cal");
        DailyWalkData r = r(cal);
        ArrayList<String> steps3 = fitnessShareData.getSteps();
        Intrinsics.checkNotNull(steps3);
        steps3.set(1, r != null ? Integer.valueOf(r.getValue()).toString() : null);
        fitnessShareData.setYesterdayTarget(r != null ? Integer.valueOf(r.getStepsTarget()).toString() : null);
        Boolean isUnitInMile = UserDataManager.getInstance(this.f4810a).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isUnitInMile, "isUnitInMile");
        if (isUnitInMile.booleanValue()) {
            fitnessShareData.setDistanceUnit(this.f4810a.getString(R.string.mi_dot));
        } else {
            fitnessShareData.setDistanceUnit(this.f4810a.getString(R.string.km));
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isIDODevice(this.f4810a) && !companion.isTouchELXDevice(this.f4810a) && !companion.isEastApexDevice(this.f4810a)) {
            StepsDataModel liveStepsData = UserDataManager.getInstance(this.f4810a).getLiveStepsData(Calendar.getInstance(), BleApiManager.getInstance(this.f4810a).getBleApi().getMacAddress());
            if (liveStepsData != null) {
                if (liveStepsData.getSteps() != 0 && (Intrinsics.areEqual(liveStepsData.getCalories(), 0.0d) || liveStepsData.getDistance() == 0)) {
                    Pair<Double, Integer> d = d(liveStepsData.getSteps());
                    double doubleValue = d.component1().doubleValue();
                    int intValue = d.component2().intValue();
                    liveStepsData.setCalories(Double.valueOf(doubleValue));
                    liveStepsData.setDistance(intValue);
                }
                int steps4 = liveStepsData.getSteps();
                ArrayList<String> steps5 = fitnessShareData.getSteps();
                Intrinsics.checkNotNull(steps5);
                steps5.set(0, String.valueOf(steps4));
                Double calories = liveStepsData.getCalories();
                if (calories != null) {
                    Intrinsics.checkNotNullExpressionValue(calories, "calories");
                    fitnessShareData.setCalories(PayUtils.INSTANCE.getCaloriesValue(calories.doubleValue()));
                }
                fitnessShareData.setDistance(PayUtils.INSTANCE.getDistanceValue(this.f4810a, liveStepsData.getDistance()));
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
            DailyWalkData r2 = r(calendar);
            if (r2 != null) {
                int value = r2.getValue();
                ArrayList<String> steps6 = fitnessShareData.getSteps();
                Intrinsics.checkNotNull(steps6);
                steps6.set(0, String.valueOf(value));
                double calories2 = r2.getCalories();
                PayUtils payUtils = PayUtils.INSTANCE;
                fitnessShareData.setCalories(payUtils.getCaloriesValue(calories2));
                fitnessShareData.setDistance(payUtils.getDistanceValue(this.f4810a, r2.getMeters()));
            }
        }
        return fitnessShareData;
    }

    public final ShareData q() {
        ShareData shareData = new ShareData();
        shareData.setTitle(this.f4810a.getString(R.string.stress));
        shareData.setShareType(ShareData.ShareType.STRESS);
        if (BleApiManager.getInstance(this.f4810a).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
            DailyStress dailyStressData = StressRepository.Companion.getInstance(this.f4810a).getDailyStressData(calendar, BleApiManager.getInstance(this.f4810a).getBleApi().getMacAddress());
            if (dailyStressData != null) {
                shareData.setValue(String.valueOf((int) dailyStressData.stress_avg));
                shareData.setLevel(PayUtils.getStressRange((int) dailyStressData.stress_avg, this.f4810a));
            }
        } else if (BleApiManager.getInstance(this.f4810a).getBleApi().getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
            ManualDataRepository.Companion.getInstance(this.f4810a).getLastMeasuredStress(BleApiManager.getInstance(this.f4810a).getBleApi().getMacAddress(), null);
        }
        return shareData;
    }

    public final DailyWalkData r(Calendar calendar) {
        return WalkDBRead.getInstance(this.f4810a).getDailyWalkDataWithDate(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), BleApiManager.getInstance(this.f4810a).getBleApi().getMacAddress());
    }

    public final void setLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.lifecycleOwner = lifecycleOwner;
    }
}
