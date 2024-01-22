package com.coveiot.android.fitnessbuddies;

import android.content.Context;
import android.content.Intent;
import com.coveiot.android.fitnessbuddies.activities.FitnessBuddiesDashBoardActivity;
import com.coveiot.android.fitnessbuddies.constants.FitnessBuddiesTabs;
import com.coveiot.android.fitnessbuddies.constants.FitnessConstants;
/* loaded from: classes4.dex */
public class FitnessBuddiesNavigator {
    public static void navigateToFitnessBuddiesMainActivity(Context context) {
        context.startActivity(new Intent(context, FitnessBuddiesDashBoardActivity.class));
    }

    public static void navigateToFitnessBuddiesMainActivity(Context context, FitnessBuddiesTabs fitnessBuddiesTabs) {
        Intent intent = new Intent(context, FitnessBuddiesDashBoardActivity.class);
        int i = 0;
        if (fitnessBuddiesTabs != FitnessBuddiesTabs.BUDDIES_GOAL) {
            if (fitnessBuddiesTabs == FitnessBuddiesTabs.MESSAGES) {
                i = 1;
            } else if (fitnessBuddiesTabs == FitnessBuddiesTabs.MANAGE_BUDDIES) {
                i = 2;
            }
        }
        intent.putExtra(FitnessConstants.EXTRA_FITNESS_TAB_POSITION, i);
        context.startActivity(intent);
    }
}
