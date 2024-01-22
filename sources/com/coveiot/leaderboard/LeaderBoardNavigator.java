package com.coveiot.leaderboard;

import android.app.Activity;
import android.content.Intent;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.leaderboard.rankshare.activities.ActivityRankShare;
import com.coveiot.leaderboard.utils.LeaderboardConstants;
import com.coveiot.leaderboard.utils.ShareData;
import com.coveiot.leaderboard.views.activities.ActivityAchievementsHome;
import com.coveiot.leaderboard.views.activities.ActivityAddress;
import com.coveiot.leaderboard.views.activities.ActivityLevelBadgeScreen;
import com.coveiot.leaderboard.views.activities.ActivityRanksFilter;
import com.coveiot.leaderboard.views.activities.ActivityStepsBadgeScreen;
import com.coveiot.leaderboard.views.activities.AllBadgesActivity;
import com.coveiot.leaderboard.views.activities.LeaderBoardActivity;
import com.coveiot.leaderboard.views.activities.LeaderBoardOnboardingActivity;
import com.coveiot.leaderboard.views.activities.RankingActivity;
import com.coveiot.leaderboard.views.activities.RankingHistoryActivity;
import com.coveiot.leaderboard.views.activities.SelectAddressActivity;
/* loaded from: classes9.dex */
public class LeaderBoardNavigator {
    public static String INTENT_EXTRA_BADGES = "INTENT_EXTRA_BADGES";

    public static void navigateToAchievementsHomeScreen(Activity activity, int i) {
        Intent intent = new Intent(activity, ActivityAchievementsHome.class);
        intent.putExtra(INTENT_EXTRA_BADGES, i);
        activity.startActivity(intent);
    }

    public static void navigateToAddressScreen(Activity activity) {
        activity.startActivity(new Intent(activity, ActivityAddress.class));
    }

    public static void navigateToBadgesScreen(Activity activity) {
        activity.startActivity(new Intent(activity, AllBadgesActivity.class));
    }

    public static void navigateToLeaderBoardOnBoardScreen(Activity activity) {
        activity.startActivity(new Intent(activity, LeaderBoardOnboardingActivity.class));
    }

    public static void navigateToLeaderBoardScreen(Activity activity) {
        activity.startActivity(new Intent(activity, LeaderBoardActivity.class));
    }

    public static void navigateToLevelBadge(Activity activity, MyBadgesModel.DataBean.BadgesBean badgesBean) {
        Intent intent = new Intent(activity, ActivityLevelBadgeScreen.class);
        intent.putExtra(LeaderboardConstants.LEVEL_BADGE, badgesBean);
        activity.startActivity(intent);
    }

    public static void navigateToMyBadgesScreen(Activity activity) {
        activity.startActivity(new Intent(activity, ActivityAchievementsHome.class));
    }

    public static void navigateToRankShareScreen(Activity activity, ShareData shareData, String str) {
        Intent intent = new Intent(activity, ActivityRankShare.class);
        intent.putExtra(LeaderboardConstants.SHARE_SCREEN_TYPE, str);
        intent.putExtra("share_data", shareData);
        activity.startActivity(intent);
    }

    public static void navigateToRankingHistoryScreen(Activity activity) {
        activity.startActivity(new Intent(activity, RankingHistoryActivity.class));
    }

    public static void navigateToRankingScreen(Activity activity) {
        activity.startActivity(new Intent(activity, RankingActivity.class));
    }

    public static void navigateToRanksFilterScreen(Activity activity) {
        activity.startActivity(new Intent(activity, ActivityRanksFilter.class));
    }

    public static void navigateToSelectAddressScreen(Activity activity) {
        activity.startActivity(new Intent(activity, SelectAddressActivity.class));
    }

    public static void navigateToStepsBadge(Activity activity, MyBadgesModel.DataBean.BadgesBean badgesBean) {
        Intent intent = new Intent(activity, ActivityStepsBadgeScreen.class);
        intent.putExtra(LeaderboardConstants.STEPS_BADGE, badgesBean);
        activity.startActivity(intent);
    }
}
