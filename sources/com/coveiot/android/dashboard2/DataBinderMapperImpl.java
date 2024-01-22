package com.coveiot.android.dashboard2;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.dashboard2.databinding.FragmentFitnessGuestBindingImpl;
import com.coveiot.android.dashboard2.databinding.FragmentHomeBindingImpl;
import com.coveiot.android.dashboard2.databinding.FragmentHomeGuestBindingImpl;
import com.coveiot.android.dashboard2.databinding.ItemEditHomeDashboardVitalsBindingImpl;
import com.coveiot.android.dashboard2.databinding.ItemTopFeaturesBindingImpl;
import com.coveiot.android.dashboard2.databinding.SetupWatchLayoutBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes4.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_FRAGMENTFITNESSGUEST = 1;
    private static final int LAYOUT_FRAGMENTHOME = 2;
    private static final int LAYOUT_FRAGMENTHOMEGUEST = 3;
    private static final int LAYOUT_ITEMEDITHOMEDASHBOARDVITALS = 4;
    private static final int LAYOUT_ITEMTOPFEATURES = 5;
    private static final int LAYOUT_SETUPWATCHLAYOUT = 6;

    /* loaded from: classes4.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f4193a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(97);
            f4193a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "activitiesCompleted");
            sparseArray.put(2, "arrival");
            sparseArray.put(3, "bestOffers");
            sparseArray.put(4, "bindingDataModel1");
            sparseArray.put(5, "bleConnectionState");
            sparseArray.put(6, "bt3ConnectionState");
            sparseArray.put(7, "calorieData");
            sparseArray.put(8, "challengeItem");
            sparseArray.put(9, "challengeJoined");
            sparseArray.put(10, "cloudCategoryData");
            sparseArray.put(11, "completedCalories");
            sparseArray.put(12, "completedDistance");
            sparseArray.put(13, "data");
            sparseArray.put(14, "dayCount");
            sparseArray.put(15, "dayProgress");
            sparseArray.put(16, "dayProgressMax");
            sparseArray.put(17, "daysLeft");
            sparseArray.put(18, "destinationRouteLocation");
            sparseArray.put(19, "distance");
            sparseArray.put(20, "distanceCovered");
            sparseArray.put(21, "distanceData");
            sparseArray.put(22, "doMoreWithYourWatchItemCount");
            sparseArray.put(23, "featureData");
            sparseArray.put(24, "firstCardType");
            sparseArray.put(25, "fitnessType");
            sparseArray.put(26, "futureDayInfo");
            sparseArray.put(27, "goalCategory");
            sparseArray.put(28, "healthInfo");
            sparseArray.put(29, "isDataAvailable");
            sparseArray.put(30, "isFirstCardDataAvailable");
            sparseArray.put(31, "isFitnessPlanOngoing");
            sparseArray.put(32, "isFutureDay");
            sparseArray.put(33, "isHistoryPlan");
            sparseArray.put(34, "isInEditMode");
            sparseArray.put(35, "isMetricUnitData");
            sparseArray.put(36, "isPlanCompleted");
            sparseArray.put(37, "isPlanStartsTomorrow");
            sparseArray.put(38, "isProgressFull");
            sparseArray.put(39, "isRestDay");
            sparseArray.put(40, "isSetupYourWatchSettingsCompleted");
            sparseArray.put(41, "isUserProfileCompleted");
            sparseArray.put(42, "isWeatherEnableData");
            sparseArray.put(43, "onClickGetRoute");
            sparseArray.put(44, "onClickSearchCategory");
            sparseArray.put(45, "onHandleBack");
            sparseArray.put(46, "onNextClick");
            sparseArray.put(47, "onPreviousClick");
            sparseArray.put(48, "onRouteReportClick");
            sparseArray.put(49, "onStartClick");
            sparseArray.put(50, "onclickHandleBack");
            sparseArray.put(51, "participantData");
            sparseArray.put(52, "participantItem");
            sparseArray.put(53, "planBg");
            sparseArray.put(54, "planHeader");
            sparseArray.put(55, "planHistoryData");
            sparseArray.put(56, "planTitle");
            sparseArray.put(57, "progress");
            sparseArray.put(58, "progressText");
            sparseArray.put(59, "retryButtonClick");
            sparseArray.put(60, "routeTime");
            sparseArray.put(61, "selectedVitalsCount");
            sparseArray.put(62, "setupYourWatchItemCount");
            sparseArray.put(63, "setupYourWatchItemSelectedPosition");
            sparseArray.put(64, "show1kFtu");
            sparseArray.put(65, "showActivityHistory");
            sparseArray.put(66, "showAlexaConnect");
            sparseArray.put(67, "showBoatCoins");
            sparseArray.put(68, "showCultFitFTU");
            sparseArray.put(69, "showDynamicTab");
            sparseArray.put(70, "showFitnessChallenge");
            sparseArray.put(71, "showFitnessPlan");
            sparseArray.put(72, "showMyBuddies");
            sparseArray.put(73, "showMyBuddiesList");
            sparseArray.put(74, "showPersonalizedWatchFace");
            sparseArray.put(75, "showSOSSettings");
            sparseArray.put(76, "showSportScores");
            sparseArray.put(77, "showTapAndPay");
            sparseArray.put(78, "showWatchSettingsBigLayout");
            sparseArray.put(79, "showWatchfaceBigLayout");
            sparseArray.put(80, "showWatchfaceStudioBigLayout");
            sparseArray.put(81, "showWatchfaceStudioBigLayoutTop");
            sparseArray.put(82, "showWellnessCrew");
            sparseArray.put(83, "sleepData");
            sparseArray.put(84, "sourceRouteLocation");
            sparseArray.put(85, "startButtonClick");
            sparseArray.put(86, "stepsDataModel");
            sparseArray.put(87, "stepsGoal");
            sparseArray.put(88, "syncingStateData");
            sparseArray.put(89, "tipsData");
            sparseArray.put(90, "title");
            sparseArray.put(91, "totalActivities");
            sparseArray.put(92, "totalActivitiesAndDistance");
            sparseArray.put(93, "totalParticipantsCount");
            sparseArray.put(94, "watchName");
            sparseArray.put(95, "wayPoints");
            sparseArray.put(96, "weekCount");
        }
    }

    /* loaded from: classes4.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f4194a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(6);
            f4194a = hashMap;
            hashMap.put("layout/fragment_fitness_guest_0", Integer.valueOf(R.layout.fragment_fitness_guest));
            hashMap.put("layout/fragment_home_0", Integer.valueOf(R.layout.fragment_home));
            hashMap.put("layout/fragment_home_guest_0", Integer.valueOf(R.layout.fragment_home_guest));
            hashMap.put("layout/item_edit_home_dashboard_vitals_0", Integer.valueOf(R.layout.item_edit_home_dashboard_vitals));
            hashMap.put("layout/item_top_features_0", Integer.valueOf(R.layout.item_top_features));
            hashMap.put("layout/setup_watch_layout_0", Integer.valueOf(R.layout.setup_watch_layout));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(6);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.fragment_fitness_guest, 1);
        sparseIntArray.put(R.layout.fragment_home, 2);
        sparseIntArray.put(R.layout.fragment_home_guest, 3);
        sparseIntArray.put(R.layout.item_edit_home_dashboard_vitals, 4);
        sparseIntArray.put(R.layout.item_top_features, 5);
        sparseIntArray.put(R.layout.setup_watch_layout, 6);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(14);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.activitymodes.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.customreminders.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.fitnessbuddies.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.fitnesschallenges.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.navigation.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.respiratoryrate.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.sleepenergyscore.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.smartalert.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.sos.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.sportsnotification.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.watchfaceui.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.weather.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f4193a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/fragment_fitness_guest_0".equals(tag)) {
                            return new FragmentFitnessGuestBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_fitness_guest is invalid. Received: " + tag);
                    case 2:
                        if ("layout/fragment_home_0".equals(tag)) {
                            return new FragmentHomeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
                    case 3:
                        if ("layout/fragment_home_guest_0".equals(tag)) {
                            return new FragmentHomeGuestBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_home_guest is invalid. Received: " + tag);
                    case 4:
                        if ("layout/item_edit_home_dashboard_vitals_0".equals(tag)) {
                            return new ItemEditHomeDashboardVitalsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for item_edit_home_dashboard_vitals is invalid. Received: " + tag);
                    case 5:
                        if ("layout/item_top_features_0".equals(tag)) {
                            return new ItemTopFeaturesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for item_top_features is invalid. Received: " + tag);
                    case 6:
                        if ("layout/setup_watch_layout_0".equals(tag)) {
                            return new SetupWatchLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for setup_watch_layout is invalid. Received: " + tag);
                    default:
                        return null;
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f4194a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
