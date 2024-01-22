package com.coveiot.android.fitnessbuddies.activities;

import android.content.Context;
import android.os.Bundle;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.constants.FitnessConstants;
import com.coveiot.android.fitnessbuddies.fragments.FitnessBuddiesHomeFragment;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.utils.LanguageHelper;
/* loaded from: classes4.dex */
public class FitnessBuddiesDashBoardActivity extends BaseActivity {
    public int p = 0;

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageHelper.onAttach(context));
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getExtras() != null && getIntent().hasExtra(FitnessConstants.EXTRA_FITNESS_TAB_POSITION)) {
            this.p = getIntent().getIntExtra(FitnessConstants.EXTRA_FITNESS_TAB_POSITION, 0);
        }
        setContentView(R.layout.activity_fitness_buddies_dash_board);
        FitnessBuddiesHomeFragment fitnessBuddiesHomeFragment = new FitnessBuddiesHomeFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("tab_pos", this.p);
        fitnessBuddiesHomeFragment.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fitnessBuddiesHomeFragment).commitAllowingStateLoss();
    }
}
