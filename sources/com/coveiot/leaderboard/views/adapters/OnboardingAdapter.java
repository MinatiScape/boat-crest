package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.coveiot.leaderboard.views.fragment.LeaderBoardFTUFragment;
import com.coveiot.leaderboard.views.fragment.LeaderBoardFTUFragment2;
/* loaded from: classes9.dex */
public class OnboardingAdapter extends FragmentPagerAdapter {
    public OnboardingAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 2;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        if (i == 0) {
            return new LeaderBoardFTUFragment();
        }
        if (i == 1) {
            return LeaderBoardFTUFragment2.newInstance(null, null);
        }
        return null;
    }
}
