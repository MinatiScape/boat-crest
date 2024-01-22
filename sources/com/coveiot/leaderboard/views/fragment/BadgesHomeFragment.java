package com.coveiot.leaderboard.views.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CircularArcProgressBar;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.coveaccess.leaderboard.model.MyRankModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.callbacks.IMyBadgeItemClick;
import com.coveiot.leaderboard.eventbus.CloveEventBusManager;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.leaderboard.utils.LeaderboardUtils;
import com.coveiot.leaderboard.utils.ShareData;
import com.coveiot.leaderboard.views.adapters.HomeMyBadgesAdapter;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import com.squareup.otto.Subscribe;
import java.util.Locale;
/* loaded from: classes9.dex */
public class BadgesHomeFragment extends BaseFragment implements IMyBadgeItemClick {
    public ConstraintLayout A;
    public ImageView B;
    public RecyclerView.LayoutManager C;
    public HomeMyBadgesAdapter D;
    public ShareData E;
    public ImageView F;
    public LinearLayout G;
    public LinearLayout H;
    public CardView I;
    public CardView J;
    public TextView K;
    public RecyclerView m;
    public LinearLayout n;
    public ImageView o;
    public ImageView p;
    public FrameLayout q;
    public CircularArcProgressBar r;
    public TextView s;
    public TextView t;
    public TextView u;
    public TextView v;
    public TextView w;
    public ConstraintLayout x;
    public ConstraintLayout y;
    public ConstraintLayout z;

    /* loaded from: classes9.dex */
    public class a implements CoveApiListener<MyRankModel, CoveApiErrorModel> {
        public a() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(MyRankModel myRankModel) {
            if (BadgesHomeFragment.this.getActivity() != null && !BadgesHomeFragment.this.getActivity().isFinishing() && BadgesHomeFragment.this.isAdded() && myRankModel != null && myRankModel.getData() != null && myRankModel.getData().getRank() != null) {
                LeaderBoardDataUtiils.saveMyRank(BadgesHomeFragment.this.getActivity(), new Gson().toJson(myRankModel));
                BadgesHomeFragment.this.q.setVisibility(0);
                BadgesHomeFragment.this.I.setVisibility(0);
                BadgesHomeFragment.this.n.setVisibility(8);
                if (myRankModel.getData().getRank().getRank() >= 1000) {
                    TextView textView = BadgesHomeFragment.this.s;
                    textView.setText("" + String.format(Locale.ENGLISH, "%.1f", Double.valueOf(myRankModel.getData().getRank().getRank() / 1000.0d)) + BadgesHomeFragment.this.getContext().getResources().getString(R.string.k));
                } else {
                    TextView textView2 = BadgesHomeFragment.this.s;
                    textView2.setText("" + myRankModel.getData().getRank().getRank());
                }
                TextView textView3 = BadgesHomeFragment.this.u;
                textView3.setText(HexStringBuilder.DEFAULT_SEPARATOR + myRankModel.getData().getBestRank().getRank());
                TextView textView4 = BadgesHomeFragment.this.t;
                textView4.setText("" + myRankModel.getData().getRank().getSteps());
                BadgesHomeFragment badgesHomeFragment = BadgesHomeFragment.this;
                badgesHomeFragment.r.setProgress(badgesHomeFragment.r(myRankModel.getData().getRank().getRank(), myRankModel.getData().getRank().getTotalUsers()));
                int q = BadgesHomeFragment.this.q(myRankModel);
                if (q >= 0) {
                    BadgesHomeFragment.this.v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_small_red_arrow_down, 0, 0, 0);
                    BadgesHomeFragment.this.w.setText(String.valueOf(q));
                    return;
                }
                BadgesHomeFragment.this.v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_small_green_arrow_up, 0, 0, 0);
                BadgesHomeFragment.this.w.setText(String.valueOf(q * (-1)));
                return;
            }
            BadgesHomeFragment.this.q.setVisibility(8);
            BadgesHomeFragment.this.I.setVisibility(8);
            BadgesHomeFragment.this.n.setVisibility(0);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BadgesHomeFragment.this.getActivity().onBackPressed();
        }
    }

    /* loaded from: classes9.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.BOTTOM_MENU_RANK_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_RANK_BADGES_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.LOCATION_ICON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            BadgesHomeFragment.this.t();
        }
    }

    /* loaded from: classes9.dex */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BadgesHomeFragment.this.onNewRankWithinLocalityClick();
        }
    }

    /* loaded from: classes9.dex */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.BOTTOM_MENU_RANK_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_ALL_BADGES_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.VIEW_BADGES.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            BadgesHomeFragment.this.viewAllClick();
        }
    }

    /* loaded from: classes9.dex */
    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BadgesHomeFragment.this.bestRankLayoutClick();
        }
    }

    /* loaded from: classes9.dex */
    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.BOTTOM_MENU_RANK_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_ALL_BADGES_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.VIEW_BADGES.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            BadgesHomeFragment.this.viewAllClick();
        }
    }

    /* loaded from: classes9.dex */
    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.BOTTOM_MENU_RANK_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_ALL_BADGES_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.VIEW_BADGES.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            BadgesHomeFragment.this.viewAllClick();
        }
    }

    /* loaded from: classes9.dex */
    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BadgesHomeFragment.this.share();
        }
    }

    /* loaded from: classes9.dex */
    public class j implements View.OnClickListener {
        public final /* synthetic */ Dialog h;

        public j(BadgesHomeFragment badgesHomeFragment, Dialog dialog) {
            this.h = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.h.dismiss();
        }
    }

    public static BadgesHomeFragment newInstance() {
        return new BadgesHomeFragment();
    }

    public void OnBestRankClick() {
        LeaderBoardNavigator.navigateToRankingHistoryScreen(getActivity());
    }

    public void OnRankWithinLocalityClick() {
        LeaderBoardNavigator.navigateToRankingScreen(getActivity());
    }

    public void bestRankLayoutClick() {
        u((MyRankModel) new Gson().fromJson(LeaderBoardDataUtiils.getMyRank(getActivity()), (Class<Object>) MyRankModel.class));
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_home_badges, viewGroup, false);
        s(inflate);
        p();
        this.v = (TextView) inflate.findViewById(R.id.progressStatusTv);
        ImageView imageView = (ImageView) this.A.findViewById(R.id.share_iv);
        this.F = imageView;
        imageView.setVisibility(0);
        this.F.setImageResource(R.drawable.ic_location);
        this.F.setColorFilter(ContextCompat.getColor(getActivity(), R.color.main_text_color));
        this.A.setOnClickListener(new b());
        if (LeaderBoardDataUtiils.getMyRank(getActivity()) != null && !LeaderBoardDataUtiils.getMyRank(getActivity()).isEmpty()) {
            MyRankModel myRankModel = (MyRankModel) new Gson().fromJson(LeaderBoardDataUtiils.getMyRank(getActivity()), (Class<Object>) MyRankModel.class);
            this.q.setVisibility(0);
            this.I.setVisibility(0);
            this.n.setVisibility(8);
            if (myRankModel.getData() != null && myRankModel.getData().getRank() != null) {
                if (myRankModel.getData().getRank().getRank() >= 1000) {
                    TextView textView = this.s;
                    textView.setText("" + String.format(Locale.ENGLISH, "%.1f", Double.valueOf(myRankModel.getData().getRank().getRank() / 1000.0d)) + getString(R.string.k));
                } else {
                    TextView textView2 = this.s;
                    textView2.setText("" + myRankModel.getData().getRank().getRank());
                }
                TextView textView3 = this.u;
                textView3.setText(HexStringBuilder.DEFAULT_SEPARATOR + myRankModel.getData().getBestRank().getRank());
                TextView textView4 = this.t;
                textView4.setText("" + myRankModel.getData().getRank().getSteps());
                this.r.setProgress(r(myRankModel.getData().getRank().getRank(), myRankModel.getData().getRank().getTotalUsers()));
                int q = q(myRankModel);
                if (q == 0) {
                    this.x.setVisibility(4);
                } else if (q > 0) {
                    this.x.setVisibility(0);
                    this.v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_small_red_arrow_down, 0, 0, 0);
                    this.w.setText(String.valueOf(q));
                } else {
                    this.x.setVisibility(0);
                    this.v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_small_green_arrow_up, 0, 0, 0);
                    this.w.setText(String.valueOf(q * (-1)));
                }
            } else {
                this.q.setVisibility(8);
                this.I.setVisibility(8);
                this.n.setVisibility(0);
            }
        } else {
            this.q.setVisibility(8);
            this.I.setVisibility(8);
            this.n.setVisibility(0);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        this.C = gridLayoutManager;
        this.m.setLayoutManager(gridLayoutManager);
        this.m.setHasFixedSize(true);
        MyBadgesModel myBadgesModel = (MyBadgesModel) new Gson().fromJson(LeaderBoardDataUtiils.getMyBadges(getActivity()), (Class<Object>) MyBadgesModel.class);
        if (myBadgesModel != null && myBadgesModel.getData() != null && myBadgesModel.getData().getBadges().size() > 0) {
            HomeMyBadgesAdapter homeMyBadgesAdapter = new HomeMyBadgesAdapter(getActivity(), this);
            this.D = homeMyBadgesAdapter;
            homeMyBadgesAdapter.setData(myBadgesModel.getData().getBadges());
            this.m.setAdapter(this.D);
            this.G.setVisibility(8);
            this.H.setVisibility(0);
            this.J.setVisibility(0);
        } else {
            this.G.setVisibility(0);
            this.J.setVisibility(8);
            this.H.setVisibility(8);
        }
        this.p.setVisibility(4);
        this.F.setOnClickListener(new c());
        return inflate;
    }

    @Override // com.coveiot.leaderboard.callbacks.IMyBadgeItemClick
    public void onMyBadgeItemClick(MyBadgesModel.DataBean.BadgesBean badgesBean) {
        if (badgesBean.getBadgeLevels().size() == 4) {
            LeaderBoardNavigator.navigateToStepsBadge(getActivity(), badgesBean);
        } else {
            LeaderBoardNavigator.navigateToLevelBadge(getActivity(), badgesBean);
        }
    }

    public void onNewRankWithinLocalityClick() {
        LeaderBoardNavigator.navigateToRankingScreen(getActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        CloveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        CloveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.BOTTOM_MENU_RANK_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    public final void p() {
        CoveLeaderboardApi.getMyRank(GeoCodingCriteria.POD_CITY, new a());
    }

    public final int q(MyRankModel myRankModel) {
        if (myRankModel.getData().getPreviousRank() != null) {
            return myRankModel.getData().getRank().getRank() - myRankModel.getData().getPreviousRank().getRank();
        }
        return 0;
    }

    public final int r(int i2, int i3) {
        int i4 = ((int) ((1.0d - (i2 / i3)) * 100.0d)) + 1;
        if (i4 > 100) {
            return 100;
        }
        return i4;
    }

    public final void s(View view) {
        this.m = (RecyclerView) view.findViewById(R.id.myBadges);
        this.n = (LinearLayout) view.findViewById(R.id.noRankingLayout);
        this.G = (LinearLayout) view.findViewById(R.id.no_badges_layout);
        this.H = (LinearLayout) view.findViewById(R.id.myBadges_layout);
        this.J = (CardView) view.findViewById(R.id.cv_view_all_layout);
        this.I = (CardView) view.findViewById(R.id.cv_best_rank);
        this.B = (ImageView) view.findViewById(R.id.iv_all_badges);
        this.K = (TextView) view.findViewById(R.id.tv_view_all_badges);
        TextView textView = (TextView) view.findViewById(R.id.view_last_activity);
        this.o = (ImageView) view.findViewById(R.id.levelIv);
        TextView textView2 = (TextView) view.findViewById(R.id.rank);
        TextView textView3 = (TextView) view.findViewById(R.id.location);
        this.p = (ImageView) view.findViewById(R.id.ic_image_share);
        this.q = (FrameLayout) view.findViewById(R.id.rankingContentLayout);
        this.r = (CircularArcProgressBar) view.findViewById(R.id.circularArcProgressBar);
        this.s = (TextView) view.findViewById(R.id.newRank);
        this.t = (TextView) view.findViewById(R.id.steps);
        this.u = (TextView) view.findViewById(R.id.newBestRank);
        this.w = (TextView) view.findViewById(R.id.progressStatusTv);
        this.y = (ConstraintLayout) view.findViewById(R.id.viewAllLayout);
        this.z = (ConstraintLayout) view.findViewById(R.id.my_best_rank_layout);
        this.x = (ConstraintLayout) view.findViewById(R.id.clBottom);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.toolbar);
        this.A = constraintLayout;
        ((TextView) constraintLayout.findViewById(R.id.toolbar_title)).setText(R.string.rank_badges);
        this.q.setOnClickListener(new d());
        this.y.setOnClickListener(new e());
        this.z.setOnClickListener(new f());
        this.B.setOnClickListener(new g());
        this.K.setOnClickListener(new h());
        this.p.setOnClickListener(new i());
    }

    public final void share() {
        this.E = new ShareData();
        this.E.setRankLevel(this.s.getText().toString());
        this.E.setSteps(this.t.getText().toString());
        this.E.setRank(this.u.getText().toString());
        MyRankModel myRankModel = (MyRankModel) new Gson().fromJson(LeaderBoardDataUtiils.getMyRank(getActivity()), (Class<Object>) MyRankModel.class);
        myRankModel.getData().getRank().getRankDate();
        this.E.setRankDate(LeaderboardUtils.formattedDate(myRankModel.getData().getBestRank().getRankDate(), "dd MMM yyyy"));
        this.E.setUserName(SessionManager.getInstance(getActivity()).getUserDetails().getName());
        this.E.setProgress(this.r.getProgress());
        this.E.setProgressStatus(q(myRankModel));
        LeaderBoardNavigator.navigateToRankShareScreen(getActivity(), this.E, getResources().getString(R.string.rank));
    }

    @Subscribe
    public void synBadges(MyBadgesModel myBadgesModel) {
        HomeMyBadgesAdapter homeMyBadgesAdapter = this.D;
        if (homeMyBadgesAdapter != null) {
            homeMyBadgesAdapter.setData(myBadgesModel.getData().getBadges());
            this.m.setAdapter(this.D);
        }
    }

    public final void t() {
        LeaderBoardNavigator.navigateToSelectAddressScreen(getActivity());
    }

    public final void u(MyRankModel myRankModel) {
        if (myRankModel != null) {
            Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
            dialog.requestWindowFeature(1);
            dialog.setContentView(R.layout.best_rank_info_dialog);
            dialog.setCanceledOnTouchOutside(false);
            ((TextView) dialog.findViewById(R.id.myRank)).setText(String.valueOf(myRankModel.getData().getBestRank().getRank()));
            ((TextView) dialog.findViewById(R.id.totalUsers)).setText(" / " + String.valueOf(myRankModel.getData().getBestRank().getTotalUsers()));
            ((TextView) dialog.findViewById(R.id.steps)).setText(String.valueOf(myRankModel.getData().getBestRank().getSteps() + getString(R.string.steps_with_space)));
            ((TextView) dialog.findViewById(R.id.date)).setText(getString(R.string.on) + getString(R.string.space) + LeaderboardUtils.formattedDate(myRankModel.getData().getBestRank().getRankDate(), "dd MMMM yyyy"));
            ((ImageView) dialog.findViewById(R.id.close)).setOnClickListener(new j(this, dialog));
            dialog.show();
        }
    }

    public void viewAllClick() {
        LeaderBoardNavigator.navigateToBadgesScreen(getActivity());
    }

    public void viewAllLayoutClick() {
        LeaderBoardNavigator.navigateToBadgesScreen(getActivity());
    }

    public void viewBadgesClick() {
        LeaderBoardNavigator.navigateToBadgesScreen(getActivity());
    }
}
