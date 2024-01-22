package com.coveiot.leaderboard.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.model.LeaderBoardNotificationModel;
import com.google.gson.Gson;
/* loaded from: classes9.dex */
public class OneBadgeNotificationFragment extends Fragment {
    public static String p = "data";
    public ImageView h;
    public TextView i;
    public TextView j;
    public TextView k;
    public ImageView l;
    public ImageView m;
    public String n;
    public OnFragmentInteractionListener o;

    /* loaded from: classes9.dex */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OneBadgeNotificationFragment.this.onMyBadgesClick();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OneBadgeNotificationFragment.this.onCloseClick();
        }
    }

    public static OneBadgeNotificationFragment newInstance(String str) {
        OneBadgeNotificationFragment oneBadgeNotificationFragment = new OneBadgeNotificationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(p, str);
        oneBadgeNotificationFragment.setArguments(bundle);
        return oneBadgeNotificationFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onButtonPressed(Uri uri) {
        OnFragmentInteractionListener onFragmentInteractionListener = this.o;
        if (onFragmentInteractionListener != null) {
            onFragmentInteractionListener.onFragmentInteraction(uri);
        }
    }

    public void onCloseClick() {
        getActivity().finish();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.n = getArguments().getString(p);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LeaderBoardNotificationModel leaderBoardNotificationModel;
        View inflate = layoutInflater.inflate(R.layout.multiple_fragment_notification, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.title);
        this.h = (ImageView) inflate.findViewById(R.id.badgeIv);
        this.i = (TextView) inflate.findViewById(R.id.badgeName);
        this.j = (TextView) inflate.findViewById(R.id.message);
        TextView textView2 = (TextView) inflate.findViewById(R.id.description);
        this.k = (TextView) inflate.findViewById(R.id.myBadges);
        this.l = (ImageView) inflate.findViewById(R.id.close);
        this.m = (ImageView) inflate.findViewById(R.id.iv_powered_cove);
        ThemesUtils.setPoweredByLogoIcon(requireContext(), this.m, true);
        String str = this.n;
        if (str != null && !str.isEmpty() && (leaderBoardNotificationModel = (LeaderBoardNotificationModel) new Gson().fromJson(this.n, (Class<Object>) LeaderBoardNotificationModel.class)) != null) {
            this.i.setVisibility(0);
            Glide.with(getActivity()).m30load(leaderBoardNotificationModel.getLevelImageUrl()).into(this.h);
            this.i.setText(leaderBoardNotificationModel.getBadgeName());
            this.j.setText(leaderBoardNotificationModel.getLevelDescription());
        }
        this.k.setOnClickListener(new a());
        this.l.setOnClickListener(new b());
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.o = null;
    }

    public void onMyBadgesClick() {
        LeaderBoardNavigator.navigateToMyBadgesScreen(getActivity());
        getActivity().finish();
    }
}
