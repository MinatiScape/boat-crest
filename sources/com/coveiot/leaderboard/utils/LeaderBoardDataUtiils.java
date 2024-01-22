package com.coveiot.leaderboard.utils;

import android.content.Context;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.coveaccess.leaderboard.model.MyRankModel;
import com.coveiot.leaderboard.preference.CloveCommonPreference;
import com.coveiot.leaderboard.preference.PreferenceManager;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class LeaderBoardDataUtiils {
    public static String getAddressJson(Context context) {
        return (String) PreferenceManager.getPreference(context, CloveCommonPreference.ADDRESS_JSON, "");
    }

    public static String getAllBadges(Context context) {
        return (String) PreferenceManager.getPreference(context, CloveCommonPreference.LEADER_BOARD_ALL_BADGES, "");
    }

    public static String getBadgeLevels(Context context) {
        return (String) PreferenceManager.getPreference(context, CloveCommonPreference.LEADER_BADGES_LEVEL, "");
    }

    public static String getDailyBadges(Context context) {
        return (String) PreferenceManager.getPreference(context, CloveCommonPreference.LEADER_BOARD_DAILY_BADGES, "");
    }

    public static String getMyBadges(Context context) {
        return (String) PreferenceManager.getPreference(context, CloveCommonPreference.LEADER_BOARD_MY_BADGES, "");
    }

    public static List<MyBadgesModel.DataBean.BadgesBean> getMyLastNBadges(Context context, int i) {
        ArrayList arrayList = new ArrayList();
        MyBadgesModel myBadgesModel = (MyBadgesModel) new Gson().fromJson(getMyBadges(context), (Class<Object>) MyBadgesModel.class);
        if (myBadgesModel != null && myBadgesModel.getData() != null && myBadgesModel.getData().getBadges() != null) {
            for (int i2 = 0; i2 < myBadgesModel.getData().getBadges().size(); i2++) {
                arrayList.add(myBadgesModel.getData().getBadges().get(i2));
                if (arrayList.size() == i) {
                    break;
                }
            }
        }
        return arrayList;
    }

    public static String getMyRank(Context context) {
        return (String) PreferenceManager.getPreference(context, CloveCommonPreference.MY_RANK, "");
    }

    public static String getSpecialBadges(Context context) {
        return (String) PreferenceManager.getPreference(context, CloveCommonPreference.LEADER_BOARD_SPECIAL_BADGES, "");
    }

    public static boolean isFirstTimeLeaderBoardShown(Context context) {
        return ((Boolean) PreferenceManager.getPreference(context, CloveCommonPreference.IS_FIRST_TIME_SHOWN_LEADER_BOARD, Boolean.FALSE)).booleanValue();
    }

    public static boolean isFragmentAddress(Context context) {
        return ((Boolean) PreferenceManager.getPreference(context, CloveCommonPreference.IS_FRAGMENT_ADDRESS, Boolean.FALSE)).booleanValue();
    }

    public static boolean isLocationOnBoardingDone(Context context) {
        return ((Boolean) PreferenceManager.getPreference(context, CloveCommonPreference.IS_LOCATION_ONBOARDING_DONE, Boolean.FALSE)).booleanValue();
    }

    public static boolean isRankEmpty(Context context) {
        String myRank = getMyRank(context);
        if (AppUtils.isEmpty(myRank)) {
            return true;
        }
        MyRankModel myRankModel = (MyRankModel) new Gson().fromJson(myRank, (Class<Object>) MyRankModel.class);
        return myRankModel.getData() == null || myRankModel.getData().getRank() == null;
    }

    public static void saveAddressJson(Context context, String str) {
        PreferenceManager.savePreference(context, CloveCommonPreference.ADDRESS_JSON, str);
    }

    public static void saveAllBadges(Context context, String str) {
        PreferenceManager.savePreference(context, CloveCommonPreference.LEADER_BOARD_ALL_BADGES, str);
    }

    public static void saveBadgeLevels(Context context, String str) {
        PreferenceManager.savePreference(context, CloveCommonPreference.LEADER_BADGES_LEVEL, str);
    }

    public static void saveDailyBadges(Context context, String str) {
        PreferenceManager.savePreference(context, CloveCommonPreference.LEADER_BOARD_DAILY_BADGES, str);
    }

    public static void saveFirstTimeLeaderBoardShown(Context context, boolean z) {
        PreferenceManager.savePreference(context, CloveCommonPreference.IS_FIRST_TIME_SHOWN_LEADER_BOARD, Boolean.valueOf(z));
    }

    public static void saveIsFragmentAddress(Context context, boolean z) {
        PreferenceManager.savePreference(context, CloveCommonPreference.IS_FRAGMENT_ADDRESS, Boolean.valueOf(z));
    }

    public static void saveLocationOnboarding(Context context, boolean z) {
        PreferenceManager.savePreference(context, CloveCommonPreference.IS_LOCATION_ONBOARDING_DONE, Boolean.valueOf(z));
    }

    public static void saveMyBadges(Context context, String str) {
        PreferenceManager.savePreference(context, CloveCommonPreference.LEADER_BOARD_MY_BADGES, str);
    }

    public static void saveMyRank(Context context, String str) {
        PreferenceManager.savePreference(context, CloveCommonPreference.MY_RANK, str);
    }

    public static void saveSpecialBadges(Context context, String str) {
        PreferenceManager.savePreference(context, CloveCommonPreference.LEADER_BOARD_SPECIAL_BADGES, str);
    }
}
