package com.coveiot.leaderboard.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
/* loaded from: classes9.dex */
public class AddressFTUFragment extends Fragment {
    public Button h;
    public TextView i;
    public ConstraintLayout j;

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AddressFTUFragment.this.requireActivity().onBackPressed();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.FTU_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_RANK_BADGES_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SELECT_YOUR_LOCATION_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            AddressFTUFragment.this.onLearnAboutBadgeClick();
        }
    }

    /* loaded from: classes9.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AddressFTUFragment.this.onSkipClick();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_address_ftu, viewGroup, false);
        this.h = (Button) inflate.findViewById(R.id.learnAboutBadges);
        this.i = (TextView) inflate.findViewById(R.id.skip);
        ConstraintLayout constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.toolbar);
        this.j = constraintLayout;
        ((TextView) constraintLayout.findViewById(R.id.toolbar_title)).setVisibility(8);
        this.j.setOnClickListener(new a());
        this.h.setOnClickListener(new b());
        this.i.setOnClickListener(new c());
        return inflate;
    }

    public void onLearnAboutBadgeClick() {
        LeaderBoardDataUtiils.saveFirstTimeLeaderBoardShown(getActivity(), true);
        LeaderBoardNavigator.navigateToSelectAddressScreen(getActivity());
        getActivity().finish();
    }

    public void onSkipClick() {
        getActivity().finish();
    }
}
