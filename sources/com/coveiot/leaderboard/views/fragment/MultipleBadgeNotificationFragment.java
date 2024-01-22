package com.coveiot.leaderboard.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public class MultipleBadgeNotificationFragment extends Fragment {
    public TextView h;
    public ImageView i;
    public ImageView j;

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MultipleBadgeNotificationFragment.this.onMyBadgesClick();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MultipleBadgeNotificationFragment.this.onCloseClick();
        }
    }

    public void onCloseClick() {
        getActivity().finish();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.multiple_fragment_notification, viewGroup, false);
        this.h = (TextView) inflate.findViewById(R.id.myBadges);
        this.i = (ImageView) inflate.findViewById(R.id.close);
        this.j = (ImageView) inflate.findViewById(R.id.iv_powered_cove);
        ThemesUtils.setPoweredByLogoIcon(requireContext(), this.j, true);
        this.h.setOnClickListener(new a());
        this.i.setOnClickListener(new b());
        return inflate;
    }

    public void onMyBadgesClick() {
        LeaderBoardNavigator.navigateToMyBadgesScreen(getActivity());
        getActivity().finish();
    }
}
