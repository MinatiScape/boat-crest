package com.coveiot.android.fitnesschallenges;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.databinding.ActivityCreateChallengeBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessChallengeCardShareBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessChallengesHomeBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessDetailsBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.ActivityViewAllParticipantsDetailsBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.ChallengeFirstRankLayoutBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.CoustomizedChallengeImageItemBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.CreateChallengeBannerBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.EmptyChallengeViewLayoutBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FitnessChallengeItemBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FitnessChallengeParticipantsBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FitnessDetailsEditDialogBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentBoatCrestChallengesBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentBuddiesChallengesBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentChallengesLeaderboardBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentCompletedBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentCreateChallengeBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentFCLeaderboardBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentJoinChallengeBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentJoinedBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentMyChallengeBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentMyCreatedBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentParticipantsListBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.FragmentShareChallengeBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.ListItemFitnessChallengeLeaderboardLayoutBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.ListItemLoadingLayoutBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.NoChallengesBannerBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.ParticipantItemBindingImpl;
import com.coveiot.android.fitnesschallenges.databinding.ShareInfoLayoutBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes2.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYCREATECHALLENGE = 1;
    private static final int LAYOUT_ACTIVITYFITNESSCHALLENGECARDSHARE = 2;
    private static final int LAYOUT_ACTIVITYFITNESSCHALLENGESHOME = 3;
    private static final int LAYOUT_ACTIVITYFITNESSDETAILS = 4;
    private static final int LAYOUT_ACTIVITYVIEWALLPARTICIPANTSDETAILS = 5;
    private static final int LAYOUT_CHALLENGEFIRSTRANKLAYOUT = 6;
    private static final int LAYOUT_COUSTOMIZEDCHALLENGEIMAGEITEM = 7;
    private static final int LAYOUT_CREATECHALLENGEBANNER = 8;
    private static final int LAYOUT_EMPTYCHALLENGEVIEWLAYOUT = 9;
    private static final int LAYOUT_FITNESSCHALLENGEITEM = 10;
    private static final int LAYOUT_FITNESSCHALLENGEPARTICIPANTS = 11;
    private static final int LAYOUT_FITNESSDETAILSEDITDIALOG = 12;
    private static final int LAYOUT_FRAGMENTBOATCRESTCHALLENGES = 13;
    private static final int LAYOUT_FRAGMENTBUDDIESCHALLENGES = 14;
    private static final int LAYOUT_FRAGMENTCHALLENGESLEADERBOARD = 15;
    private static final int LAYOUT_FRAGMENTCOMPLETED = 16;
    private static final int LAYOUT_FRAGMENTCREATECHALLENGE = 17;
    private static final int LAYOUT_FRAGMENTFCLEADERBOARD = 18;
    private static final int LAYOUT_FRAGMENTJOINCHALLENGE = 19;
    private static final int LAYOUT_FRAGMENTJOINED = 20;
    private static final int LAYOUT_FRAGMENTMYCHALLENGE = 21;
    private static final int LAYOUT_FRAGMENTMYCREATED = 22;
    private static final int LAYOUT_FRAGMENTPARTICIPANTSLIST = 23;
    private static final int LAYOUT_FRAGMENTSHARECHALLENGE = 24;
    private static final int LAYOUT_LISTITEMFITNESSCHALLENGELEADERBOARDLAYOUT = 25;
    private static final int LAYOUT_LISTITEMLOADINGLAYOUT = 26;
    private static final int LAYOUT_NOCHALLENGESBANNER = 27;
    private static final int LAYOUT_PARTICIPANTITEM = 28;
    private static final int LAYOUT_SHAREINFOLAYOUT = 29;

    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f4488a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(25);
            f4488a = sparseArray;
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

    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f4489a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(29);
            f4489a = hashMap;
            hashMap.put("layout/activity_create_challenge_0", Integer.valueOf(R.layout.activity_create_challenge));
            hashMap.put("layout/activity_fitness_challenge_card_share_0", Integer.valueOf(R.layout.activity_fitness_challenge_card_share));
            hashMap.put("layout/activity_fitness_challenges_home_0", Integer.valueOf(R.layout.activity_fitness_challenges_home));
            hashMap.put("layout/activity_fitness_details_0", Integer.valueOf(R.layout.activity_fitness_details));
            hashMap.put("layout/activity_view_all_participants_details_0", Integer.valueOf(R.layout.activity_view_all_participants_details));
            hashMap.put("layout/challenge_first_rank_layout_0", Integer.valueOf(R.layout.challenge_first_rank_layout));
            hashMap.put("layout/coustomized_challenge_image_item_0", Integer.valueOf(R.layout.coustomized_challenge_image_item));
            hashMap.put("layout/create_challenge_banner_0", Integer.valueOf(R.layout.create_challenge_banner));
            hashMap.put("layout/empty_challenge_view_layout_0", Integer.valueOf(R.layout.empty_challenge_view_layout));
            hashMap.put("layout/fitness_challenge_item_0", Integer.valueOf(R.layout.fitness_challenge_item));
            hashMap.put("layout/fitness_challenge_participants_0", Integer.valueOf(R.layout.fitness_challenge_participants));
            hashMap.put("layout/fitness_details_edit_dialog_0", Integer.valueOf(R.layout.fitness_details_edit_dialog));
            hashMap.put("layout/fragment_boat_crest_challenges_0", Integer.valueOf(R.layout.fragment_boat_crest_challenges));
            hashMap.put("layout/fragment_buddies_challenges_0", Integer.valueOf(R.layout.fragment_buddies_challenges));
            hashMap.put("layout/fragment_challenges_leaderboard_0", Integer.valueOf(R.layout.fragment_challenges_leaderboard));
            hashMap.put("layout/fragment_completed_0", Integer.valueOf(R.layout.fragment_completed));
            hashMap.put("layout/fragment_create_challenge_0", Integer.valueOf(R.layout.fragment_create_challenge));
            hashMap.put("layout/fragment_f_c_leaderboard_0", Integer.valueOf(R.layout.fragment_f_c_leaderboard));
            hashMap.put("layout/fragment_join_challenge_0", Integer.valueOf(R.layout.fragment_join_challenge));
            hashMap.put("layout/fragment_joined_0", Integer.valueOf(R.layout.fragment_joined));
            hashMap.put("layout/fragment_my_challenge_0", Integer.valueOf(R.layout.fragment_my_challenge));
            hashMap.put("layout/fragment_my_created_0", Integer.valueOf(R.layout.fragment_my_created));
            hashMap.put("layout/fragment_participants_list_0", Integer.valueOf(R.layout.fragment_participants_list));
            hashMap.put("layout/fragment_share_challenge_0", Integer.valueOf(R.layout.fragment_share_challenge));
            hashMap.put("layout/list_item_fitness_challenge_leaderboard_layout_0", Integer.valueOf(R.layout.list_item_fitness_challenge_leaderboard_layout));
            hashMap.put("layout/list_item_loading_layout_0", Integer.valueOf(R.layout.list_item_loading_layout));
            hashMap.put("layout/no_challenges_banner_0", Integer.valueOf(R.layout.no_challenges_banner));
            hashMap.put("layout/participant_item_0", Integer.valueOf(R.layout.participant_item));
            hashMap.put("layout/share_info_layout_0", Integer.valueOf(R.layout.share_info_layout));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(29);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_create_challenge, 1);
        sparseIntArray.put(R.layout.activity_fitness_challenge_card_share, 2);
        sparseIntArray.put(R.layout.activity_fitness_challenges_home, 3);
        sparseIntArray.put(R.layout.activity_fitness_details, 4);
        sparseIntArray.put(R.layout.activity_view_all_participants_details, 5);
        sparseIntArray.put(R.layout.challenge_first_rank_layout, 6);
        sparseIntArray.put(R.layout.coustomized_challenge_image_item, 7);
        sparseIntArray.put(R.layout.create_challenge_banner, 8);
        sparseIntArray.put(R.layout.empty_challenge_view_layout, 9);
        sparseIntArray.put(R.layout.fitness_challenge_item, 10);
        sparseIntArray.put(R.layout.fitness_challenge_participants, 11);
        sparseIntArray.put(R.layout.fitness_details_edit_dialog, 12);
        sparseIntArray.put(R.layout.fragment_boat_crest_challenges, 13);
        sparseIntArray.put(R.layout.fragment_buddies_challenges, 14);
        sparseIntArray.put(R.layout.fragment_challenges_leaderboard, 15);
        sparseIntArray.put(R.layout.fragment_completed, 16);
        sparseIntArray.put(R.layout.fragment_create_challenge, 17);
        sparseIntArray.put(R.layout.fragment_f_c_leaderboard, 18);
        sparseIntArray.put(R.layout.fragment_join_challenge, 19);
        sparseIntArray.put(R.layout.fragment_joined, 20);
        sparseIntArray.put(R.layout.fragment_my_challenge, 21);
        sparseIntArray.put(R.layout.fragment_my_created, 22);
        sparseIntArray.put(R.layout.fragment_participants_list, 23);
        sparseIntArray.put(R.layout.fragment_share_challenge, 24);
        sparseIntArray.put(R.layout.list_item_fitness_challenge_leaderboard_layout, 25);
        sparseIntArray.put(R.layout.list_item_loading_layout, 26);
        sparseIntArray.put(R.layout.no_challenges_banner, 27);
        sparseIntArray.put(R.layout.participant_item, 28);
        sparseIntArray.put(R.layout.share_info_layout, 29);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.fitnessbuddies.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f4488a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/activity_create_challenge_0".equals(tag)) {
                            return new ActivityCreateChallengeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_create_challenge is invalid. Received: " + tag);
                    case 2:
                        if ("layout/activity_fitness_challenge_card_share_0".equals(tag)) {
                            return new ActivityFitnessChallengeCardShareBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_fitness_challenge_card_share is invalid. Received: " + tag);
                    case 3:
                        if ("layout/activity_fitness_challenges_home_0".equals(tag)) {
                            return new ActivityFitnessChallengesHomeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_fitness_challenges_home is invalid. Received: " + tag);
                    case 4:
                        if ("layout/activity_fitness_details_0".equals(tag)) {
                            return new ActivityFitnessDetailsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_fitness_details is invalid. Received: " + tag);
                    case 5:
                        if ("layout/activity_view_all_participants_details_0".equals(tag)) {
                            return new ActivityViewAllParticipantsDetailsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_view_all_participants_details is invalid. Received: " + tag);
                    case 6:
                        if ("layout/challenge_first_rank_layout_0".equals(tag)) {
                            return new ChallengeFirstRankLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for challenge_first_rank_layout is invalid. Received: " + tag);
                    case 7:
                        if ("layout/coustomized_challenge_image_item_0".equals(tag)) {
                            return new CoustomizedChallengeImageItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for coustomized_challenge_image_item is invalid. Received: " + tag);
                    case 8:
                        if ("layout/create_challenge_banner_0".equals(tag)) {
                            return new CreateChallengeBannerBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for create_challenge_banner is invalid. Received: " + tag);
                    case 9:
                        if ("layout/empty_challenge_view_layout_0".equals(tag)) {
                            return new EmptyChallengeViewLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for empty_challenge_view_layout is invalid. Received: " + tag);
                    case 10:
                        if ("layout/fitness_challenge_item_0".equals(tag)) {
                            return new FitnessChallengeItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fitness_challenge_item is invalid. Received: " + tag);
                    case 11:
                        if ("layout/fitness_challenge_participants_0".equals(tag)) {
                            return new FitnessChallengeParticipantsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fitness_challenge_participants is invalid. Received: " + tag);
                    case 12:
                        if ("layout/fitness_details_edit_dialog_0".equals(tag)) {
                            return new FitnessDetailsEditDialogBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fitness_details_edit_dialog is invalid. Received: " + tag);
                    case 13:
                        if ("layout/fragment_boat_crest_challenges_0".equals(tag)) {
                            return new FragmentBoatCrestChallengesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_boat_crest_challenges is invalid. Received: " + tag);
                    case 14:
                        if ("layout/fragment_buddies_challenges_0".equals(tag)) {
                            return new FragmentBuddiesChallengesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_buddies_challenges is invalid. Received: " + tag);
                    case 15:
                        if ("layout/fragment_challenges_leaderboard_0".equals(tag)) {
                            return new FragmentChallengesLeaderboardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_challenges_leaderboard is invalid. Received: " + tag);
                    case 16:
                        if ("layout/fragment_completed_0".equals(tag)) {
                            return new FragmentCompletedBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_completed is invalid. Received: " + tag);
                    case 17:
                        if ("layout/fragment_create_challenge_0".equals(tag)) {
                            return new FragmentCreateChallengeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_create_challenge is invalid. Received: " + tag);
                    case 18:
                        if ("layout/fragment_f_c_leaderboard_0".equals(tag)) {
                            return new FragmentFCLeaderboardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_f_c_leaderboard is invalid. Received: " + tag);
                    case 19:
                        if ("layout/fragment_join_challenge_0".equals(tag)) {
                            return new FragmentJoinChallengeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_join_challenge is invalid. Received: " + tag);
                    case 20:
                        if ("layout/fragment_joined_0".equals(tag)) {
                            return new FragmentJoinedBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_joined is invalid. Received: " + tag);
                    case 21:
                        if ("layout/fragment_my_challenge_0".equals(tag)) {
                            return new FragmentMyChallengeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_my_challenge is invalid. Received: " + tag);
                    case 22:
                        if ("layout/fragment_my_created_0".equals(tag)) {
                            return new FragmentMyCreatedBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_my_created is invalid. Received: " + tag);
                    case 23:
                        if ("layout/fragment_participants_list_0".equals(tag)) {
                            return new FragmentParticipantsListBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_participants_list is invalid. Received: " + tag);
                    case 24:
                        if ("layout/fragment_share_challenge_0".equals(tag)) {
                            return new FragmentShareChallengeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_share_challenge is invalid. Received: " + tag);
                    case 25:
                        if ("layout/list_item_fitness_challenge_leaderboard_layout_0".equals(tag)) {
                            return new ListItemFitnessChallengeLeaderboardLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for list_item_fitness_challenge_leaderboard_layout is invalid. Received: " + tag);
                    case 26:
                        if ("layout/list_item_loading_layout_0".equals(tag)) {
                            return new ListItemLoadingLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for list_item_loading_layout is invalid. Received: " + tag);
                    case 27:
                        if ("layout/no_challenges_banner_0".equals(tag)) {
                            return new NoChallengesBannerBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for no_challenges_banner is invalid. Received: " + tag);
                    case 28:
                        if ("layout/participant_item_0".equals(tag)) {
                            return new ParticipantItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for participant_item is invalid. Received: " + tag);
                    case 29:
                        if ("layout/share_info_layout_0".equals(tag)) {
                            return new ShareInfoLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for share_info_layout is invalid. Received: " + tag);
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
        if (str == null || (num = b.f4489a.get(str)) == null) {
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
