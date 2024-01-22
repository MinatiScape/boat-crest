package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractFitnessDashBoard;
import com.coveiot.android.leonardo.dashboard.health.model.DailyStepsInsightsModel;
import com.coveiot.android.leonardo.dashboard.health.model.WalkData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel;
import com.coveiot.android.leonardo.dashboard.model.InsightStepsHourModel;
import com.coveiot.android.leonardo.dashboard.model.InsightsStepsInitialModel;
import com.coveiot.android.leonardo.dashboard.model.StepsWeeklyMonthlyData;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covedb.deviceinfo.DeviceInfoRepository;
import com.coveiot.covedb.deviceinfo.EntityDeviceInfo;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.StepsDataModel;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.walk.WalkRepository;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentStepsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4766a;
    @NotNull
    public MutableLiveData<StepsWeeklyMonthlyData> b;
    @NotNull
    public MutableLiveData<InsightStepsHourModel> c;
    public ContractFitnessDashBoard contractFitnessDashBoard;
    @NotNull
    public MutableLiveData<InsightsStepsInitialModel> d;
    @NotNull
    public MutableLiveData<DailyWalkData> e;
    @NotNull
    public MutableLiveData<DailyWalkData> f;
    public boolean g;
    @NotNull
    public MutableLiveData<MyBadgesModel> h;
    @NotNull
    public MutableLiveData<List<DailyWalkData>> i;
    @NotNull
    public MutableLiveData<DailyStepsInsightsModel> j;
    @Nullable
    public String k;
    @Nullable
    public String l;
    public LifecycleOwner mLifecycleOwner;

    /* loaded from: classes3.dex */
    public static final class HourlyComparator implements Comparator<HourlyWalkData> {
        @Override // java.util.Comparator
        public int compare(@NotNull HourlyWalkData hourlyActivityLogs, @NotNull HourlyWalkData t1) {
            Intrinsics.checkNotNullParameter(hourlyActivityLogs, "hourlyActivityLogs");
            Intrinsics.checkNotNullParameter(t1, "t1");
            String startTime = hourlyActivityLogs.getStartTime();
            String startTime2 = t1.getStartTime();
            Intrinsics.checkNotNullExpressionValue(startTime2, "t1.startTime");
            return startTime.compareTo(startTime2);
        }
    }

    public FragmentStepsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4766a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.i = new MutableLiveData<>();
        this.j = new MutableLiveData<>();
    }

    public final void a(List<HourlyWalkData> list, int i, boolean z) {
        int size = list.get(0).getCodevalue().size();
        int i2 = 0;
        for (HourlyWalkData hourlyWalkData : list) {
            i2 += hourlyWalkData.getIntervelValue();
        }
        InsightStepsHourModel insightStepsHourModel = new InsightStepsHourModel(false, 0, 0);
        if (z) {
            insightStepsHourModel.setGoalAchieved(true);
            insightStepsHourModel.setTimeTakenInMinutes(b(size, list, i, true));
            this.c.postValue(insightStepsHourModel);
        } else if (i2 > i) {
            insightStepsHourModel.setGoalAchieved(true);
            insightStepsHourModel.setTimeTakenInMinutes(b(size, list, i, false));
            this.c.postValue(insightStepsHourModel);
        } else {
            insightStepsHourModel.setGoalAchieved(false);
            this.c.postValue(insightStepsHourModel);
        }
    }

    public final int b(int i, List<HourlyWalkData> list, int i2, boolean z) {
        int i3;
        int i4 = 60 / i;
        int i5 = 0;
        if (z) {
            ArrayList arrayList = new ArrayList();
            for (HourlyWalkData hourlyWalkData : list) {
                if (!arrayList.contains(hourlyWalkData.mDate)) {
                    arrayList.add(hourlyWalkData.mDate);
                }
            }
            int size = arrayList.size();
            i3 = 0;
            for (int i6 = 0; i6 < size; i6++) {
                Iterator<HourlyWalkData> it = list.iterator();
                int i7 = 0;
                int i8 = 0;
                while (true) {
                    if (it.hasNext()) {
                        HourlyWalkData next = it.next();
                        if (((String) arrayList.get(i6)).equals(next.mDate)) {
                            Iterator<Integer> it2 = next.getCodevalue().iterator();
                            while (it2.hasNext()) {
                                Integer codeValue = it2.next();
                                Intrinsics.checkNotNullExpressionValue(codeValue, "codeValue");
                                if (codeValue.intValue() > 0) {
                                    i7 += codeValue.intValue();
                                    i8++;
                                }
                            }
                            continue;
                        }
                        if (i7 >= i2) {
                            i3 += i8;
                            break;
                        }
                    }
                }
            }
        } else {
            int i9 = 0;
            int i10 = 0;
            for (HourlyWalkData hourlyWalkData2 : list) {
                Iterator<Integer> it3 = hourlyWalkData2.getCodevalue().iterator();
                while (it3.hasNext()) {
                    Integer codeValue2 = it3.next();
                    Intrinsics.checkNotNullExpressionValue(codeValue2, "codeValue");
                    if (codeValue2.intValue() > 0) {
                        if (i10 <= i2) {
                            i10 += codeValue2.intValue();
                            i9++;
                        }
                    }
                }
            }
            i3 = 0;
            i5 = i9;
        }
        return z ? i3 * i4 : i5 * i4;
    }

    public final boolean containsStartTime(@NotNull List<? extends HourlyWalkData> hourlyList, @NotNull String startTime) {
        Intrinsics.checkNotNullParameter(hourlyList, "hourlyList");
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        for (HourlyWalkData hourlyWalkData : hourlyList) {
            if (hourlyWalkData.getStartTime().equals(startTime)) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public final MutableLiveData<StepsWeeklyMonthlyData> getAvgStepsData() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f4766a;
    }

    @NotNull
    public final ContractFitnessDashBoard getContractFitnessDashBoard() {
        ContractFitnessDashBoard contractFitnessDashBoard = this.contractFitnessDashBoard;
        if (contractFitnessDashBoard != null) {
            return contractFitnessDashBoard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractFitnessDashBoard");
        return null;
    }

    @NotNull
    public final MutableLiveData<InsightsStepsInitialModel> getCurrentPreviousWeekOrMonthStepsData() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<DailyWalkData> getCurrentSingleDayStepData() {
        return this.e;
    }

    @NotNull
    public final LifecycleOwner getMLifecycleOwner() {
        LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
        if (lifecycleOwner != null) {
            return lifecycleOwner;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mLifecycleOwner");
        return null;
    }

    @NotNull
    public final MutableLiveData<MyBadgesModel> getMyBadges() {
        return this.h;
    }

    public final Calendar getPreviousDate(Calendar calendar, int i) {
        Object clone = calendar.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar2 = (Calendar) clone;
        calendar2.add(5, i);
        return calendar2;
    }

    @Nullable
    public final String getPreviousEndHour() {
        return this.l;
    }

    @NotNull
    public final MutableLiveData<DailyWalkData> getPreviousSingleDayStepData() {
        return this.f;
    }

    @Nullable
    public final String getPreviousStartHour() {
        return this.k;
    }

    @NotNull
    public final MutableLiveData<List<DailyWalkData>> getRangeDailyWalkData() {
        return this.i;
    }

    @NotNull
    public final MutableLiveData<DailyStepsInsightsModel> getStepsGoalInsights() {
        return this.j;
    }

    @NotNull
    public final MutableLiveData<InsightStepsHourModel> getTimeRequiredForStepGoalAchieved() {
        return this.c;
    }

    public final void getWeekAndMonthWiseHourlyData(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        WalkRepository.Companion.getInstance(this.f4766a).getHourlyDataBetweenDates(fromDate, toDate, BleApiManager.getInstance(this.f4766a).getBleApi().getMacAddress()).observe(getMLifecycleOwner(), new Observer<List<? extends HourlyWalkData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel$getWeekAndMonthWiseHourlyData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@NotNull List<? extends HourlyWalkData> mhourlyWalkDataList) {
                Intrinsics.checkNotNullParameter(mhourlyWalkDataList, "mhourlyWalkDataList");
                List asMutableList = TypeIntrinsics.asMutableList(mhourlyWalkDataList);
                if (!AppUtils.isEmpty(asMutableList)) {
                    FragmentStepsViewModel fragmentStepsViewModel = FragmentStepsViewModel.this;
                    Integer num = UserDataManager.getInstance(fragmentStepsViewModel.getContext()).getFitnessGoalStepsData().target;
                    Intrinsics.checkNotNullExpressionValue(num, "getInstance(context).fitnessGoalStepsData.target");
                    fragmentStepsViewModel.a(asMutableList, num.intValue(), true);
                    return;
                }
                InsightStepsHourModel insightStepsHourModel = new InsightStepsHourModel(false, 0, 0);
                insightStepsHourModel.setGoalAchieved(true);
                insightStepsHourModel.setTimeTakenInMinutes(0);
                FragmentStepsViewModel.this.getTimeRequiredForStepGoalAchieved().postValue(insightStepsHourModel);
            }
        });
    }

    public final boolean isWeekSelected() {
        return this.g;
    }

    public final void loadDailData(@NotNull final Calendar date) {
        LiveData<DailyWalkData> dailyDataWithoutFlowValidator;
        LiveData<DailyWalkData> dailyDataWithoutFlowValidator2;
        Intrinsics.checkNotNullParameter(date, "date");
        if (getContractFitnessDashBoard().isSyncInProgress()) {
            dailyDataWithoutFlowValidator = WalkRepository.Companion.getInstance(this.f4766a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4766a).getBleApi().getMacAddress());
        } else {
            dailyDataWithoutFlowValidator = WalkRepository.Companion.getInstance(this.f4766a).getDailyDataWithoutFlowValidator(date, BleApiManager.getInstance(this.f4766a).getBleApi().getMacAddress());
        }
        dailyDataWithoutFlowValidator.observe(getMLifecycleOwner(), new Observer<DailyWalkData>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel$loadDailData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable DailyWalkData dailyWalkData) {
                if (dailyWalkData != null) {
                    PayUtils payUtils = PayUtils.INSTANCE;
                    Calendar calendar = Calendar.getInstance();
                    Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                    if (payUtils.isSameDate(calendar, date)) {
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if (!companion.isSmaDevice(this.getContext()) && !companion.isMatrixDevice(this.getContext()) && !companion.isIDODevice(this.getContext()) && !companion.isTouchELXDevice(this.getContext()) && !companion.isEastApexDevice(this.getContext())) {
                            Context context = this.getContext();
                            Intrinsics.checkNotNull(context);
                            StepsDataModel liveStepsData = UserDataManager.getInstance(context).getLiveStepsData(Calendar.getInstance(), BleApiManager.getInstance(this.getContext()).getBleApi().getMacAddress());
                            dailyWalkData.setValue(liveStepsData.getSteps());
                            Double calories = liveStepsData.getCalories();
                            Intrinsics.checkNotNullExpressionValue(calories, "liveStepsData.calories");
                            dailyWalkData.setCalories(calories.doubleValue());
                            dailyWalkData.setMeters(liveStepsData.getDistance());
                        }
                    }
                    this.getContractFitnessDashBoard().updateDailyLevelData(dailyWalkData);
                    this.getCurrentSingleDayStepData().postValue(dailyWalkData);
                    return;
                }
                DailyWalkData dailyWalkData2 = new DailyWalkData();
                dailyWalkData2.setValue(0);
                dailyWalkData2.setCalories(0.0d);
                dailyWalkData2.setMeters(0);
                dailyWalkData2.mDate = AppUtils.formatDate(date.getTime(), "yyyy-MM-dd");
                int i = 10000;
                if (UserDataManager.getInstance(this.getContext()).getFitnessGoalStepsData() != null) {
                    Integer num = UserDataManager.getInstance(this.getContext()).getFitnessGoalStepsData().target;
                    Intrinsics.checkNotNullExpressionValue(num, "getInstance(context).fitnessGoalStepsData.target");
                    i = num.intValue();
                }
                dailyWalkData2.setStepsTarget(i);
                this.getContractFitnessDashBoard().updateDailyLevelData(dailyWalkData2);
                this.getCurrentSingleDayStepData().postValue(dailyWalkData2);
            }
        });
        final Calendar previousDate = getPreviousDate(date, -1);
        if (getContractFitnessDashBoard().isSyncInProgress()) {
            dailyDataWithoutFlowValidator2 = WalkRepository.Companion.getInstance(this.f4766a).getDailyDataWithoutFlowValidator(previousDate, BleApiManager.getInstance(this.f4766a).getBleApi().getMacAddress());
        } else {
            dailyDataWithoutFlowValidator2 = WalkRepository.Companion.getInstance(this.f4766a).getDailyDataWithoutFlowValidator(previousDate, BleApiManager.getInstance(this.f4766a).getBleApi().getMacAddress());
        }
        dailyDataWithoutFlowValidator2.observe(getMLifecycleOwner(), new Observer<DailyWalkData>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel$loadDailData$2
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable DailyWalkData dailyWalkData) {
                if (dailyWalkData != null) {
                    PayUtils payUtils = PayUtils.INSTANCE;
                    Calendar calendar = Calendar.getInstance();
                    Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                    if (payUtils.isSameDate(calendar, previousDate)) {
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if (!companion.isSmaDevice(this.getContext()) && !companion.isMatrixDevice(this.getContext()) && !companion.isIDODevice(this.getContext()) && !companion.isTouchELXDevice(this.getContext()) && !companion.isEastApexDevice(this.getContext())) {
                            Context context = this.getContext();
                            Intrinsics.checkNotNull(context);
                            StepsDataModel liveStepsData = UserDataManager.getInstance(context).getLiveStepsData(Calendar.getInstance(), BleApiManager.getInstance(this.getContext()).getBleApi().getMacAddress());
                            dailyWalkData.setValue(liveStepsData.getSteps());
                            Double calories = liveStepsData.getCalories();
                            Intrinsics.checkNotNullExpressionValue(calories, "liveStepsData.calories");
                            dailyWalkData.setCalories(calories.doubleValue());
                            dailyWalkData.setMeters(liveStepsData.getDistance());
                        }
                    }
                    this.getPreviousSingleDayStepData().postValue(dailyWalkData);
                    return;
                }
                DailyWalkData dailyWalkData2 = new DailyWalkData();
                dailyWalkData2.setValue(0);
                dailyWalkData2.setCalories(0.0d);
                dailyWalkData2.setMeters(0);
                this.getPreviousSingleDayStepData().postValue(dailyWalkData2);
            }
        });
    }

    public final void loadHourlyData(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        WalkRepository.Companion.getInstance(this.f4766a).getHourlyData(date, BleApiManager.getInstance(this.f4766a).getBleApi().getMacAddress()).observe(getMLifecycleOwner(), new Observer<List<? extends HourlyWalkData>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel$loadHourlyData$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@NotNull List<? extends HourlyWalkData> mhourlyWalkDataList) {
                int i;
                List emptyList;
                boolean z;
                ArrayList<String> arrayList;
                FitnessGoal fitnessGoalStepsData;
                FragmentStepsViewModel$loadHourlyData$1 fragmentStepsViewModel$loadHourlyData$1 = this;
                Intrinsics.checkNotNullParameter(mhourlyWalkDataList, "mhourlyWalkDataList");
                new ArrayList();
                List<? extends HourlyWalkData> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) mhourlyWalkDataList);
                if (!AppUtils.isEmpty(mutableList)) {
                    UserDataManager userDataManager = UserDataManager.getInstance(FragmentStepsViewModel.this.getContext());
                    if (((userDataManager == null || (fitnessGoalStepsData = userDataManager.getFitnessGoalStepsData()) == null) ? null : fitnessGoalStepsData.target) == null) {
                        FragmentStepsViewModel.this.a(mutableList, 10000, false);
                    } else {
                        FragmentStepsViewModel fragmentStepsViewModel = FragmentStepsViewModel.this;
                        Integer num = UserDataManager.getInstance(fragmentStepsViewModel.getContext()).getFitnessGoalStepsData().target;
                        Intrinsics.checkNotNullExpressionValue(num, "getInstance(context).fitnessGoalStepsData.target");
                        fragmentStepsViewModel.a(mutableList, num.intValue(), false);
                    }
                    ArrayList<BarEntry> arrayList2 = new ArrayList<>();
                    ArrayList<String> arrayList3 = new ArrayList<>();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= 24) {
                            break;
                        }
                        arrayList2.add(new BarEntry(i2, 0.0f));
                        arrayList3.add(PayUtils.getAmPmHourValue(String.valueOf(i2)));
                        i2++;
                    }
                    if (mutableList.size() < 24) {
                        String str = ((HourlyWalkData) mutableList.get(0)).mDate;
                        String macAddress = ((HourlyWalkData) mutableList.get(0)).getMacAddress();
                        int i3 = 0;
                        for (i = 24; i3 < i; i = 24) {
                            StringBuilder sb = new StringBuilder();
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Locale locale = Locale.ENGLISH;
                            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i3)}, 1));
                            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                            sb.append(format);
                            sb.append(":00:00");
                            if (FragmentStepsViewModel.this.containsStartTime(mutableList, sb.toString())) {
                                arrayList = arrayList3;
                            } else {
                                HourlyWalkData hourlyWalkData = new HourlyWalkData();
                                hourlyWalkData.setCalories(0);
                                hourlyWalkData.setDistance(0);
                                int i4 = i3 + 1;
                                if (i4 == i) {
                                    i4 = 0;
                                }
                                StringBuilder sb2 = new StringBuilder();
                                arrayList = arrayList3;
                                String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i3)}, 1));
                                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                                sb2.append(format2);
                                sb2.append(":00:00");
                                String sb3 = sb2.toString();
                                StringBuilder sb4 = new StringBuilder();
                                String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i4)}, 1));
                                Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                                sb4.append(format3);
                                sb4.append(":00:00");
                                String sb5 = sb4.toString();
                                hourlyWalkData.setStartTime(sb3);
                                hourlyWalkData.setEndTime(sb5);
                                hourlyWalkData.setIntervelValue(0);
                                hourlyWalkData.setCodevalue(JStyleUtils.getEmptyHourCodedValuesList());
                                hourlyWalkData.setMacAddress(macAddress);
                                hourlyWalkData.mDate = str;
                                mutableList.add(hourlyWalkData);
                            }
                            i3++;
                            fragmentStepsViewModel$loadHourlyData$1 = this;
                            arrayList3 = arrayList;
                        }
                    }
                    ArrayList<String> arrayList4 = arrayList3;
                    Collections.sort(mutableList, new FragmentStepsViewModel.HourlyComparator());
                    int size = mutableList.size();
                    for (int i5 = 0; i5 < size; i5++) {
                        HourlyWalkData hourlyWalkData2 = (HourlyWalkData) mutableList.get(i5);
                        String endTime = hourlyWalkData2.getEndTime();
                        Intrinsics.checkNotNullExpressionValue(endTime, "hourlyWalkData.endTime");
                        if (StringsKt__StringsKt.contains$default((CharSequence) endTime, (CharSequence) ":", false, 2, (Object) null)) {
                            try {
                                String startTime = hourlyWalkData2.getStartTime();
                                Intrinsics.checkNotNullExpressionValue(startTime, "hourlyWalkData.startTime");
                                List<String> split = new Regex(":").split(startTime, 0);
                                if (!split.isEmpty()) {
                                    ListIterator<String> listIterator = split.listIterator(split.size());
                                    while (listIterator.hasPrevious()) {
                                        if (listIterator.previous().length() == 0) {
                                            z = true;
                                            continue;
                                        } else {
                                            z = false;
                                            continue;
                                        }
                                        if (!z) {
                                            emptyList = CollectionsKt___CollectionsKt.take(split, listIterator.nextIndex() + 1);
                                            break;
                                        }
                                    }
                                }
                                emptyList = CollectionsKt__CollectionsKt.emptyList();
                                Integer.parseInt(((String[]) emptyList.toArray(new String[0]))[0]);
                                arrayList2.set(i5, new BarEntry(i5, hourlyWalkData2.getIntervelValue()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                FragmentStepsViewModel.this.getContractFitnessDashBoard().updateHourlyLevelData(null, null, false);
                            }
                        }
                    }
                    FragmentStepsViewModel.this.getContractFitnessDashBoard().updateHourlyLevelData(arrayList2, arrayList4, true);
                    return;
                }
                InsightStepsHourModel insightStepsHourModel = new InsightStepsHourModel(false, 0, 0);
                insightStepsHourModel.setGoalAchieved(true);
                insightStepsHourModel.setTimeTakenInMinutes(0);
                FragmentStepsViewModel.this.getTimeRequiredForStepGoalAchieved().postValue(insightStepsHourModel);
                FragmentStepsViewModel.this.getContractFitnessDashBoard().updateHourlyLevelData(null, null, false);
            }
        });
    }

    public final void loadHourlyDataForInsights(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        Ref.IntRef intRef = new Ref.IntRef();
        Ref.IntRef intRef2 = new Ref.IntRef();
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Ref.IntRef intRef3 = new Ref.IntRef();
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(this.f4766a).getDeviceInfoBy(BleApiManager.getInstance(this.f4766a).getBleApi().getMacAddress());
        PayUtils payUtils = PayUtils.INSTANCE;
        this.k = payUtils.getLastSyncCurrentHour(this.f4766a, deviceInfoBy);
        this.l = payUtils.getLastSyncPreviousHour(this.f4766a, deviceInfoBy);
        e.e(ViewModelKt.getViewModelScope(this), null, null, new FragmentStepsViewModel$loadHourlyDataForInsights$1(this, date, intRef3, intRef, intRef2, booleanRef, null), 3, null);
    }

    @NotNull
    public final String loadSelectedDAteRange(@NotNull WalkData walkData) {
        Intrinsics.checkNotNullParameter(walkData, "walkData");
        String type = walkData.getType();
        Intrinsics.checkNotNull(type);
        String str = "";
        if (m.equals(type, this.f4766a.getResources().getString(R.string.day), true)) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
            Calendar calendar = Calendar.getInstance();
            try {
                String format = simpleDateFormat2.format(simpleDateFormat.parse(walkData.getDwmValue()));
                Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format…parse(walkData.dwmValue))");
                str = format;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (m.equals(simpleDateFormat.format(calendar.getTime()), walkData.getDwmValue(), true)) {
                str = this.f4766a.getResources().getString(R.string.today);
                Intrinsics.checkNotNullExpressionValue(str, "context.resources.getString(R.string.today)");
            }
            calendar.add(5, -1);
            if (m.equals(simpleDateFormat.format(calendar.getTime()), walkData.getDwmValue(), true)) {
                String string = this.f4766a.getResources().getString(R.string.yesterday);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.yesterday)");
                return string;
            }
            return str;
        }
        return "";
    }

    public final void rangeStepsData(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        this.i.postValue(WalkDBRead.getInstance(this.f4766a).getDailyWalkDataBetweenDates(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), SessionManager.getInstance(this.f4766a).getConnectedDeviceMacAddress()));
    }

    public final void selectWeeklyView(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        StepsWeeklyMonthlyData stepsWeeklyMonthlyData;
        FragmentStepsViewModel fragmentStepsViewModel = this;
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        int findDateDifference = AppUtils.findDateDifference(fromDate.getTime(), toDate.getTime()) + 1;
        String formatDate = RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd");
        String formatDate2 = RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd");
        String formatDate3 = RepositoryUtils.formatDate(fragmentStepsViewModel.getPreviousDate(fromDate, fragmentStepsViewModel.g ? -6 : -30).getTime(), "yyyy-MM-dd");
        String formatDate4 = RepositoryUtils.formatDate(fragmentStepsViewModel.getPreviousDate(toDate, fragmentStepsViewModel.g ? -6 : -30).getTime(), "yyyy-MM-dd");
        List<DailyWalkData> weeklyListLiveData = WalkDBRead.getInstance(fragmentStepsViewModel.f4766a).getDailyWalkDataBetweenDates(formatDate, formatDate2, SessionManager.getInstance(fragmentStepsViewModel.f4766a).getConnectedDeviceMacAddress());
        List<DailyWalkData> dailyWalkDataBetweenDates = WalkDBRead.getInstance(fragmentStepsViewModel.f4766a).getDailyWalkDataBetweenDates(formatDate3, formatDate4, SessionManager.getInstance(fragmentStepsViewModel.f4766a).getConnectedDeviceMacAddress());
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd/MM");
        ArrayList<BarEntry> arrayList = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();
        int size = weeklyListLiveData.size();
        List<DailyWalkData> list = dailyWalkDataBetweenDates;
        int i = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        int i2 = 0;
        int i3 = 0;
        double d3 = 0.0d;
        int i4 = 0;
        while (i3 < size) {
            int value = i + weeklyListLiveData.get(i3).getValue();
            ArrayList<String> arrayList3 = arrayList2;
            int i5 = size;
            double meters = d3 + weeklyListLiveData.get(i3).getMeters();
            double parseDouble = d2 + Double.parseDouble(PayUtils.INSTANCE.getDistanceValueDouble(fragmentStepsViewModel.f4766a, weeklyListLiveData.get(i3).getMeters()));
            double calories = d + weeklyListLiveData.get(i3).getCalories();
            if (weeklyListLiveData.get(i3).getActiveTime() != null) {
                Integer activeTime = weeklyListLiveData.get(i3).getActiveTime();
                Intrinsics.checkNotNullExpressionValue(activeTime, "weeklyListLiveData[i].activeTime");
                i4 += activeTime.intValue();
            }
            DailyWalkData dailyWalkData = weeklyListLiveData.get(i3);
            BarEntry barEntry = new BarEntry(i3, dailyWalkData.getValue());
            if (dailyWalkData.getValue() > 0) {
                int value2 = dailyWalkData.getValue();
                Integer num = UserDataManager.getInstance(fragmentStepsViewModel.f4766a).getFitnessGoalStepsData().target;
                Intrinsics.checkNotNullExpressionValue(num, "getInstance(context).fitnessGoalStepsData.target");
                if (value2 >= num.intValue()) {
                    i2++;
                }
            }
            int value3 = dailyWalkData.getValue();
            String valueOf = String.valueOf(dailyWalkData.getCalories());
            String valueOf2 = String.valueOf(dailyWalkData.getMeters());
            String str = dailyWalkData.getmDate();
            Intrinsics.checkNotNullExpressionValue(str, "walkData.getmDate()");
            WalkData walkData = new WalkData(value3, valueOf, valueOf2, str);
            String string = fragmentStepsViewModel.f4766a.getResources().getString(R.string.Day);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.Day)");
            barEntry.setData(walkData.setType(string));
            arrayList.add(barEntry);
            try {
                arrayList2 = arrayList3;
                try {
                    arrayList2.add(simpleDateFormat2.format(simpleDateFormat.parse(dailyWalkData.getmDate())));
                } catch (ParseException e) {
                    e = e;
                    e.printStackTrace();
                    i3++;
                    i = value;
                    size = i5;
                    d3 = meters;
                    d = calories;
                    d2 = parseDouble;
                }
            } catch (ParseException e2) {
                e = e2;
                arrayList2 = arrayList3;
            }
            i3++;
            i = value;
            size = i5;
            d3 = meters;
            d = calories;
            d2 = parseDouble;
        }
        int size2 = list.size();
        double d4 = d2;
        int i6 = i2;
        int i7 = 0;
        int i8 = 0;
        double d5 = 0.0d;
        double d6 = 0.0d;
        while (i7 < size2) {
            int i9 = size2;
            List<DailyWalkData> list2 = list;
            int value4 = i8 + list2.get(i7).getValue();
            int i10 = i;
            double d7 = d;
            d6 += Double.parseDouble(PayUtils.INSTANCE.getDistanceValueDouble(fragmentStepsViewModel.f4766a, list2.get(i7).getMeters()));
            d5 += list2.get(i7).getCalories();
            if (list2.get(i7).getActiveTime() != null) {
                Integer activeTime2 = list2.get(i7).getActiveTime();
                Intrinsics.checkNotNullExpressionValue(activeTime2, "previousWeeklyListLiveData[i].activeTime");
                activeTime2.intValue();
            }
            i7++;
            fragmentStepsViewModel = this;
            i8 = value4;
            i = i10;
            d = d7;
            list = list2;
            size2 = i9;
        }
        int i11 = i;
        double d8 = d;
        List<DailyWalkData> list3 = list;
        ContractFitnessDashBoard contractFitnessDashBoard = getContractFitnessDashBoard();
        Intrinsics.checkNotNullExpressionValue(weeklyListLiveData, "weeklyListLiveData");
        contractFitnessDashBoard.onDataLoaded(arrayList, arrayList2, weeklyListLiveData);
        InsightsStepsInitialModel insightsStepsInitialModel = new InsightsStepsInitialModel(0.0d, 0.0d, 0, 0, 0, 0, 0, 0);
        if (weeklyListLiveData.size() == 0) {
            stepsWeeklyMonthlyData = new StepsWeeklyMonthlyData(0.0d, 0.0d, 0.0d, 0, 0);
            insightsStepsInitialModel.setDistanceCurrent(0.0d);
            insightsStepsInitialModel.setCaloriesCurrent(0);
            insightsStepsInitialModel.setStepsCurrent(0);
        } else {
            double d9 = findDateDifference;
            stepsWeeklyMonthlyData = new StepsWeeklyMonthlyData((d3 / 1000) / d9, (d3 / 1609) / d9, d8 / d9, i11 / findDateDifference, i4 / findDateDifference);
            insightsStepsInitialModel.setDistanceCurrent(d4);
            insightsStepsInitialModel.setCaloriesCurrent((int) d8);
            insightsStepsInitialModel.setStepsCurrent(i11);
        }
        this.b.postValue(stepsWeeklyMonthlyData);
        insightsStepsInitialModel.setTotalNoOfDays(i6);
        if (list3.size() == 0) {
            insightsStepsInitialModel.setDistancePrevious(0.0d);
            insightsStepsInitialModel.setCaloriesPrevious(0);
            insightsStepsInitialModel.setStepsPrevious(0);
        } else {
            insightsStepsInitialModel.setDistancePrevious(d6);
            insightsStepsInitialModel.setCaloriesPrevious((int) d5);
            insightsStepsInitialModel.setStepsPrevious(i8);
        }
        this.d.postValue(insightsStepsInitialModel);
    }

    public final void setAvgStepsData(@NotNull MutableLiveData<StepsWeeklyMonthlyData> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setContractFitnessDashBoard(@NotNull ContractFitnessDashBoard contractFitnessDashBoard) {
        Intrinsics.checkNotNullParameter(contractFitnessDashBoard, "<set-?>");
        this.contractFitnessDashBoard = contractFitnessDashBoard;
    }

    public final void setCurrentPreviousWeekOrMonthStepsData(@NotNull MutableLiveData<InsightsStepsInitialModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setCurrentSingleDayStepData(@NotNull MutableLiveData<DailyWalkData> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setIsWeekSelected(boolean z) {
        this.g = z;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }

    public final void setMyBadges(@NotNull MutableLiveData<MyBadgesModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }

    public final void setPreviousEndHour(@Nullable String str) {
        this.l = str;
    }

    public final void setPreviousSingleDayStepData(@NotNull MutableLiveData<DailyWalkData> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void setPreviousStartHour(@Nullable String str) {
        this.k = str;
    }

    public final void setRangeDailyWalkData(@NotNull MutableLiveData<List<DailyWalkData>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.i = mutableLiveData;
    }

    public final void setStepsGoalInsights(@NotNull MutableLiveData<DailyStepsInsightsModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.j = mutableLiveData;
    }

    public final void setTimeRequiredForStepGoalAchieved(@NotNull MutableLiveData<InsightStepsHourModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setWeekSelected(boolean z) {
        this.g = z;
    }

    /* renamed from: getMyBadges  reason: collision with other method in class */
    public final void m105getMyBadges() {
        if (AppUtils.isNetConnected(this.f4766a)) {
            CoveLeaderboardApi.getMyBadges(new CoveApiListener<MyBadgesModel, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel$getMyBadges$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    FragmentStepsViewModel.this.getMyBadges().postValue(null);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable MyBadgesModel myBadgesModel) {
                    if (myBadgesModel != null) {
                        LeaderBoardDataUtiils.saveMyBadges(FragmentStepsViewModel.this.getContext(), new Gson().toJson(myBadgesModel));
                        FragmentStepsViewModel.this.getMyBadges().postValue(myBadgesModel);
                    }
                }
            });
            return;
        }
        this.h.postValue((MyBadgesModel) new Gson().fromJson(LeaderBoardDataUtiils.getMyBadges(this.f4766a), (Class<Object>) MyBadgesModel.class));
    }
}
