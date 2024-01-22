package com.coveiot.leaderboard;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.databinding.ActivityAchievementsHomeBindingImpl;
import com.coveiot.leaderboard.databinding.ActivityAddressBindingImpl;
import com.coveiot.leaderboard.databinding.ActivityLevelBadgeScreenBindingImpl;
import com.coveiot.leaderboard.databinding.ActivityRankFilterBindingImpl;
import com.coveiot.leaderboard.databinding.ActivityRankShareBindingImpl;
import com.coveiot.leaderboard.databinding.ActivityStepsBadgeScreenBindingImpl;
import com.coveiot.leaderboard.databinding.AddressFragmentBindingImpl;
import com.coveiot.leaderboard.databinding.AllBadgesFragmentBindingImpl;
import com.coveiot.leaderboard.databinding.CurrentLocationDetailsBindingImpl;
import com.coveiot.leaderboard.databinding.FiltedAppliedItemBindingImpl;
import com.coveiot.leaderboard.databinding.FragmentBadgeCardShareBindingImpl;
import com.coveiot.leaderboard.databinding.FragmentBadgesBindingImpl;
import com.coveiot.leaderboard.databinding.FragmentCurrentLocationMapBindingImpl;
import com.coveiot.leaderboard.databinding.FragmentLeaderboardHomeBindingImpl;
import com.coveiot.leaderboard.databinding.FragmentRankShareBindingImpl;
import com.coveiot.leaderboard.databinding.ItemBadgeBindingImpl;
import com.coveiot.leaderboard.databinding.LeaderHomeMyBadgesItemBindingImpl;
import com.coveiot.leaderboard.databinding.RankFilterItemBindingImpl;
import com.coveiot.leaderboard.databinding.ShareCardItemBindingImpl;
import com.coveiot.leaderboard.databinding.TopRankersListItemBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes9.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYACHIEVEMENTSHOME = 1;
    private static final int LAYOUT_ACTIVITYADDRESS = 2;
    private static final int LAYOUT_ACTIVITYLEVELBADGESCREEN = 3;
    private static final int LAYOUT_ACTIVITYRANKFILTER = 4;
    private static final int LAYOUT_ACTIVITYRANKSHARE = 5;
    private static final int LAYOUT_ACTIVITYSTEPSBADGESCREEN = 6;
    private static final int LAYOUT_ADDRESSFRAGMENT = 7;
    private static final int LAYOUT_ALLBADGESFRAGMENT = 8;
    private static final int LAYOUT_CURRENTLOCATIONDETAILS = 9;
    private static final int LAYOUT_FILTEDAPPLIEDITEM = 10;
    private static final int LAYOUT_FRAGMENTBADGECARDSHARE = 11;
    private static final int LAYOUT_FRAGMENTBADGES = 12;
    private static final int LAYOUT_FRAGMENTCURRENTLOCATIONMAP = 13;
    private static final int LAYOUT_FRAGMENTLEADERBOARDHOME = 14;
    private static final int LAYOUT_FRAGMENTRANKSHARE = 15;
    private static final int LAYOUT_ITEMBADGE = 16;
    private static final int LAYOUT_LEADERHOMEMYBADGESITEM = 17;
    private static final int LAYOUT_RANKFILTERITEM = 18;
    private static final int LAYOUT_SHARECARDITEM = 19;
    private static final int LAYOUT_TOPRANKERSLISTITEM = 20;

    /* loaded from: classes9.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f7204a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(25);
            f7204a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "bindingDataModel1");
            sparseArray.put(2, "challengeItem");
            sparseArray.put(3, "challengeJoined");
            sparseArray.put(4, "daysLeft");
            sparseArray.put(5, "firstCardType");
            sparseArray.put(6, "fitnessType");
            sparseArray.put(7, "healthInfo");
            sparseArray.put(8, "isDataAvailable");
            sparseArray.put(9, "isFirstCardDataAvailable");
            sparseArray.put(10, "participantData");
            sparseArray.put(11, "participantItem");
            sparseArray.put(12, "progress");
            sparseArray.put(13, "showAlexaConnect");
            sparseArray.put(14, "showFitnessPlan");
            sparseArray.put(15, "showSOSSettings");
            sparseArray.put(16, "showSportScores");
            sparseArray.put(17, "showTapAndPay");
            sparseArray.put(18, "showWellnessCrew");
            sparseArray.put(19, "stepsDataModel");
            sparseArray.put(20, "stepsGoal");
            sparseArray.put(21, "tipsData");
            sparseArray.put(22, "title");
            sparseArray.put(23, "totalParticipantsCount");
            sparseArray.put(24, "watchName");
        }
    }

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f7205a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(20);
            f7205a = hashMap;
            hashMap.put("layout/activity_achievements_home_0", Integer.valueOf(R.layout.activity_achievements_home));
            hashMap.put("layout/activity_address_0", Integer.valueOf(R.layout.activity_address));
            hashMap.put("layout/activity_level_badge_screen_0", Integer.valueOf(R.layout.activity_level_badge_screen));
            hashMap.put("layout/activity_rank_filter_0", Integer.valueOf(R.layout.activity_rank_filter));
            hashMap.put("layout/activity_rank_share_0", Integer.valueOf(R.layout.activity_rank_share));
            hashMap.put("layout/activity_steps_badge_screen_0", Integer.valueOf(R.layout.activity_steps_badge_screen));
            hashMap.put("layout/address_fragment_0", Integer.valueOf(R.layout.address_fragment));
            hashMap.put("layout/all_badges_fragment_0", Integer.valueOf(R.layout.all_badges_fragment));
            hashMap.put("layout/current_location_details_0", Integer.valueOf(R.layout.current_location_details));
            hashMap.put("layout/filted_applied_item_0", Integer.valueOf(R.layout.filted_applied_item));
            hashMap.put("layout/fragment_badge_card_share_0", Integer.valueOf(R.layout.fragment_badge_card_share));
            hashMap.put("layout/fragment_badges_0", Integer.valueOf(R.layout.fragment_badges));
            hashMap.put("layout/fragment_current_location_map_0", Integer.valueOf(R.layout.fragment_current_location_map));
            hashMap.put("layout/fragment_leaderboard_home_0", Integer.valueOf(R.layout.fragment_leaderboard_home));
            hashMap.put("layout/fragment_rank_share_0", Integer.valueOf(R.layout.fragment_rank_share));
            hashMap.put("layout/item_badge_0", Integer.valueOf(R.layout.item_badge));
            hashMap.put("layout/leader_home_my_badges_item_0", Integer.valueOf(R.layout.leader_home_my_badges_item));
            hashMap.put("layout/rank_filter_item_0", Integer.valueOf(R.layout.rank_filter_item));
            hashMap.put("layout/share_card_item_0", Integer.valueOf(R.layout.share_card_item));
            hashMap.put("layout/top_rankers_list_item_0", Integer.valueOf(R.layout.top_rankers_list_item));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(20);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_achievements_home, 1);
        sparseIntArray.put(R.layout.activity_address, 2);
        sparseIntArray.put(R.layout.activity_level_badge_screen, 3);
        sparseIntArray.put(R.layout.activity_rank_filter, 4);
        sparseIntArray.put(R.layout.activity_rank_share, 5);
        sparseIntArray.put(R.layout.activity_steps_badge_screen, 6);
        sparseIntArray.put(R.layout.address_fragment, 7);
        sparseIntArray.put(R.layout.all_badges_fragment, 8);
        sparseIntArray.put(R.layout.current_location_details, 9);
        sparseIntArray.put(R.layout.filted_applied_item, 10);
        sparseIntArray.put(R.layout.fragment_badge_card_share, 11);
        sparseIntArray.put(R.layout.fragment_badges, 12);
        sparseIntArray.put(R.layout.fragment_current_location_map, 13);
        sparseIntArray.put(R.layout.fragment_leaderboard_home, 14);
        sparseIntArray.put(R.layout.fragment_rank_share, 15);
        sparseIntArray.put(R.layout.item_badge, 16);
        sparseIntArray.put(R.layout.leader_home_my_badges_item, 17);
        sparseIntArray.put(R.layout.rank_filter_item, 18);
        sparseIntArray.put(R.layout.share_card_item, 19);
        sparseIntArray.put(R.layout.top_rankers_list_item, 20);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.fitnessbuddies.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.fitnesschallenges.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f7204a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/activity_achievements_home_0".equals(tag)) {
                            return new ActivityAchievementsHomeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_achievements_home is invalid. Received: " + tag);
                    case 2:
                        if ("layout/activity_address_0".equals(tag)) {
                            return new ActivityAddressBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_address is invalid. Received: " + tag);
                    case 3:
                        if ("layout/activity_level_badge_screen_0".equals(tag)) {
                            return new ActivityLevelBadgeScreenBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_level_badge_screen is invalid. Received: " + tag);
                    case 4:
                        if ("layout/activity_rank_filter_0".equals(tag)) {
                            return new ActivityRankFilterBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_rank_filter is invalid. Received: " + tag);
                    case 5:
                        if ("layout/activity_rank_share_0".equals(tag)) {
                            return new ActivityRankShareBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_rank_share is invalid. Received: " + tag);
                    case 6:
                        if ("layout/activity_steps_badge_screen_0".equals(tag)) {
                            return new ActivityStepsBadgeScreenBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_steps_badge_screen is invalid. Received: " + tag);
                    case 7:
                        if ("layout/address_fragment_0".equals(tag)) {
                            return new AddressFragmentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for address_fragment is invalid. Received: " + tag);
                    case 8:
                        if ("layout/all_badges_fragment_0".equals(tag)) {
                            return new AllBadgesFragmentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for all_badges_fragment is invalid. Received: " + tag);
                    case 9:
                        if ("layout/current_location_details_0".equals(tag)) {
                            return new CurrentLocationDetailsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for current_location_details is invalid. Received: " + tag);
                    case 10:
                        if ("layout/filted_applied_item_0".equals(tag)) {
                            return new FiltedAppliedItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for filted_applied_item is invalid. Received: " + tag);
                    case 11:
                        if ("layout/fragment_badge_card_share_0".equals(tag)) {
                            return new FragmentBadgeCardShareBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_badge_card_share is invalid. Received: " + tag);
                    case 12:
                        if ("layout/fragment_badges_0".equals(tag)) {
                            return new FragmentBadgesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_badges is invalid. Received: " + tag);
                    case 13:
                        if ("layout/fragment_current_location_map_0".equals(tag)) {
                            return new FragmentCurrentLocationMapBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_current_location_map is invalid. Received: " + tag);
                    case 14:
                        if ("layout/fragment_leaderboard_home_0".equals(tag)) {
                            return new FragmentLeaderboardHomeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_leaderboard_home is invalid. Received: " + tag);
                    case 15:
                        if ("layout/fragment_rank_share_0".equals(tag)) {
                            return new FragmentRankShareBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_rank_share is invalid. Received: " + tag);
                    case 16:
                        if ("layout/item_badge_0".equals(tag)) {
                            return new ItemBadgeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for item_badge is invalid. Received: " + tag);
                    case 17:
                        if ("layout/leader_home_my_badges_item_0".equals(tag)) {
                            return new LeaderHomeMyBadgesItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for leader_home_my_badges_item is invalid. Received: " + tag);
                    case 18:
                        if ("layout/rank_filter_item_0".equals(tag)) {
                            return new RankFilterItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for rank_filter_item is invalid. Received: " + tag);
                    case 19:
                        if ("layout/share_card_item_0".equals(tag)) {
                            return new ShareCardItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for share_card_item is invalid. Received: " + tag);
                    case 20:
                        if ("layout/top_rankers_list_item_0".equals(tag)) {
                            return new TopRankersListItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for top_rankers_list_item is invalid. Received: " + tag);
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
        if (str == null || (num = b.f7205a.get(str)) == null) {
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
