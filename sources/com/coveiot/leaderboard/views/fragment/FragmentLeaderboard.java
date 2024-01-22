package com.coveiot.leaderboard.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.fitnessbuddies.activities.FitnessBuddiesDashBoardActivity;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor;
import com.coveiot.android.fitnessbuddies.fragments.viewmodels.NotificationsViewModel;
import com.coveiot.android.fitnesschallenges.ActivityCreateChallenge;
import com.coveiot.android.fitnesschallenges.FitnessChallengeDetails;
import com.coveiot.android.fitnesschallenges.adpter.FitnessChallengePagingAdapter;
import com.coveiot.android.fitnesschallenges.adpter.LoaderAdapter;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetThemeDialogOneButtonTitleMessage;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import com.coveiot.coveaccess.fitnessbuddies.model.common.BuddiesGoal;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.GetChallengeStartNEndDateRes;
import com.coveiot.coveaccess.leaderboard.model.FilterType;
import com.coveiot.coveaccess.leaderboard.model.RankHistoryModel;
import com.coveiot.coveaccess.leaderboard.model.RankType;
import com.coveiot.coveaccess.leaderboard.model.TopRankModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.databinding.FragmentLeaderboardHomeBinding;
import com.coveiot.leaderboard.views.BuddiesRankDetailsDialog;
import com.coveiot.leaderboard.views.activities.ActivityRanksFilter;
import com.coveiot.leaderboard.views.adapters.RanksFilterAppliedAdapter;
import com.coveiot.leaderboard.views.adapters.TopRankersListAdapter;
import com.coveiot.leaderboard.views.viewmodel.LeaderboardViewModel;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class FragmentLeaderboard extends BaseFragment implements SuccessResultListener, RanksFilterAppliedAdapter.OnItemClickListener, NotificationsContarctor, FitnessChallengePagingAdapter.ChallengeClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public NotificationsViewModel B;
    public FitnessChallengePagingAdapter C;
    public FitnessChallengeListViewModel D;
    @Nullable
    public FilterType E;
    @Nullable
    public RankType F;
    public boolean H;
    @Nullable
    public BottomSheetThemeDialogOneButtonTitleMessage I;
    public FragmentLeaderboardHomeBinding m;
    public LeaderboardViewModel n;
    public final int p;
    public TopRankersListAdapter u;
    public boolean v;
    public boolean w;
    @Nullable
    public BuddiesRankDetailsDialog x;
    @Nullable
    public TopRankModel.DataBean.TopRanksBean y;
    public RanksFilterAppliedAdapter z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int o = 2;
    public final int q = 1;
    public final int r = 2;
    @NotNull
    public List<TopRankModel.DataBean.TopRanksBean> s = new ArrayList();
    @NotNull
    public List<RankHistoryModel.DataBean.RanksBean> t = new ArrayList();
    @NotNull
    public List<String> A = new ArrayList();
    public final int G = 501;

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentLeaderboard newInstance() {
            return new FragmentLeaderboard();
        }
    }

    /* loaded from: classes9.dex */
    public static final class a extends Lambda implements Function1<Intent, Unit> {
        public final /* synthetic */ BuddiesChallengeRes.Item $challenge;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(BuddiesChallengeRes.Item item) {
            super(1);
            this.$challenge = item;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.putExtra(FitnessChallengeConstants.CHALLENGE_ID, this.$challenge.getChallengeId().toString());
            launchActivity.putExtra(FitnessChallengeConstants.CHALLENGE_TYPE, this.$challenge.getType());
            launchActivity.putExtra(FitnessChallengeConstants.ISFROMACHIEVEMENTS, true);
        }
    }

    /* loaded from: classes9.dex */
    public static final class b extends Lambda implements Function1<PagingData<BuddiesChallengeRes.Item>, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PagingData<BuddiesChallengeRes.Item> pagingData) {
            invoke2(pagingData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(PagingData<BuddiesChallengeRes.Item> pagingData) {
            FitnessChallengePagingAdapter fitnessChallengePagingAdapter = FragmentLeaderboard.this.C;
            if (fitnessChallengePagingAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengePagingAdapter");
                fitnessChallengePagingAdapter = null;
            }
            Lifecycle lifecycle = FragmentLeaderboard.this.getLifecycle();
            Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
            Intrinsics.checkNotNullExpressionValue(pagingData, "pagingData");
            fitnessChallengePagingAdapter.submitData(lifecycle, pagingData);
        }
    }

    /* loaded from: classes9.dex */
    public static final class c extends Lambda implements Function1<Boolean, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean isEmpty) {
            FragmentLeaderboard fragmentLeaderboard = FragmentLeaderboard.this;
            View root = fragmentLeaderboard.D().emptyChallengeView.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.emptyChallengeView.root");
            Intrinsics.checkNotNullExpressionValue(isEmpty, "isEmpty");
            fragmentLeaderboard.visibleIf(root, isEmpty.booleanValue());
            FragmentLeaderboard.this.dismissProgress();
        }
    }

    /* loaded from: classes9.dex */
    public static final class d extends Lambda implements Function1<Boolean, Unit> {
        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean shouldShowProgress) {
            Intrinsics.checkNotNullExpressionValue(shouldShowProgress, "shouldShowProgress");
            if (shouldShowProgress.booleanValue()) {
                FragmentLeaderboard.this.showProgress(true);
            } else {
                FragmentLeaderboard.this.dismissProgress();
            }
        }
    }

    /* loaded from: classes9.dex */
    public static final class e extends Lambda implements Function1<Intent, Unit> {
        public static final e INSTANCE = new e();

        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
        }
    }

    public static final void A(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void B(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void C(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void H(FragmentLeaderboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = this$0.p;
        this$0.U();
    }

    public static final void I(FragmentLeaderboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = this$0.r;
        this$0.U();
    }

    public static final void J(FragmentLeaderboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = this$0.q;
        this$0.U();
    }

    public static final void K(FragmentLeaderboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GetChallengeStartNEndDateRes fitnessChallengeActiveDateRange = FitnessChallengeSessionManager.getInstance(this$0.requireContext()).getFitnessChallengeActiveDateRange();
        if (fitnessChallengeActiveDateRange != null && fitnessChallengeActiveDateRange.getActiveCreatedBuddiesChallenges() != null) {
            Integer activeCreatedBuddiesChallenges = fitnessChallengeActiveDateRange.getActiveCreatedBuddiesChallenges();
            Intrinsics.checkNotNullExpressionValue(activeCreatedBuddiesChallenges, "getChallengeActiveDates.…eCreatedBuddiesChallenges");
            int intValue = activeCreatedBuddiesChallenges.intValue();
            Integer maxAllowedBuddiesChallenges = fitnessChallengeActiveDateRange.getMaxAllowedBuddiesChallenges();
            Intrinsics.checkNotNullExpressionValue(maxAllowedBuddiesChallenges, "getChallengeActiveDates.…xAllowedBuddiesChallenges");
            if (intValue >= maxAllowedBuddiesChallenges.intValue()) {
                this$0.createChallengeError();
                return;
            }
        }
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        e eVar = e.INSTANCE;
        Intent intent = new Intent(requireContext, ActivityCreateChallenge.class);
        eVar.invoke((e) intent);
        requireContext.startActivity(intent, null);
    }

    public static final void L(FragmentLeaderboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0.requireActivity(), ActivityRanksFilter.class);
        RankType rankType = this$0.F;
        if (rankType != null || this$0.E != null) {
            intent.putExtra(ActivityRanksFilter.RANK_TYPE_SELECTED_FILTER, rankType);
            intent.putExtra(ActivityRanksFilter.FILTER_TYPE_SELECTED_FILTER, this$0.E);
        }
        this$0.startActivityForResult(intent, this$0.G);
    }

    public static final void M(FragmentLeaderboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TopRankModel.DataBean.TopRanksBean topRanksBean = this$0.y;
        if (topRanksBean != null) {
            this$0.G(topRanksBean);
        }
    }

    public static final void N(FragmentLeaderboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.startActivity(new Intent(this$0.requireContext(), FitnessBuddiesDashBoardActivity.class));
        }
    }

    public static final void O(FragmentLeaderboard this$0, TopRankModel topRankModel) {
        String num;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (topRankModel.getData().getTopRanks() != null && topRankModel.getData().getTopRanks().size() > 0) {
            if (this$0.v) {
                ConstraintLayout constraintLayout = this$0.D().clToppersRank;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clToppersRank");
                this$0.gone(constraintLayout);
            } else {
                ConstraintLayout constraintLayout2 = this$0.D().clToppersRank;
                Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clToppersRank");
                this$0.visible(constraintLayout2);
            }
            LinearLayout linearLayout = this$0.D().llMyRank;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.llMyRank");
            this$0.visible(linearLayout);
            ConstraintLayout constraintLayout3 = this$0.D().clRankList;
            Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clRankList");
            this$0.visible(constraintLayout3);
            ConstraintLayout constraintLayout4 = this$0.D().clEmptyBuddies;
            Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.clEmptyBuddies");
            this$0.gone(constraintLayout4);
            List<TopRankModel.DataBean.TopRanksBean> topRanks = topRankModel.getData().getTopRanks();
            Intrinsics.checkNotNullExpressionValue(topRanks, "it.data.topRanks");
            this$0.s = topRanks;
            String str = "--";
            if (topRanks.size() >= 3 && this$0.s.get(2) != null) {
                TextView textView = this$0.D().tvRankThreeName;
                TopRankModel.DataBean.TopRanksBean topRanksBean = this$0.s.get(2);
                textView.setText((topRanksBean == null || (r7 = topRanksBean.getUserName()) == null) ? "--" : "--");
                TextView textView2 = this$0.D().tvRankThreeSteps;
                TopRankModel.DataBean.TopRanksBean topRanksBean2 = this$0.s.get(2);
                textView2.setText((topRanksBean2 == null || (r7 = Integer.valueOf(topRanksBean2.getSteps()).toString()) == null) ? "--" : "--");
                RequestManager with = Glide.with(this$0.requireContext());
                TopRankModel.DataBean.TopRanksBean topRanksBean3 = this$0.s.get(2);
                with.m30load(topRanksBean3 != null ? topRanksBean3.getDpUrl() : null).apply((BaseRequestOptions<?>) new RequestOptions().circleCrop().placeholder(R.drawable.default_avatar)).into(this$0.D().rankThreeIv);
                TopRankModel.DataBean.TopRanksBean topRanksBean4 = this$0.s.get(2);
                if ((topRanksBean4 != null ? topRanksBean4.getRank() : null) != null) {
                    TextView textView3 = this$0.D().tvRankThree;
                    Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvRankThree");
                    this$0.visible(textView3);
                    ImageView imageView = this$0.D().rankThreeValue;
                    Intrinsics.checkNotNullExpressionValue(imageView, "binding.rankThreeValue");
                    this$0.visible(imageView);
                    TextView textView4 = this$0.D().tvRankThree;
                    TopRankModel.DataBean.TopRanksBean topRanksBean5 = this$0.s.get(2);
                    textView4.setText(String.valueOf(topRanksBean5 != null ? topRanksBean5.getRank() : null));
                } else {
                    TextView textView5 = this$0.D().tvRankThree;
                    Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvRankThree");
                    this$0.gone(textView5);
                    ImageView imageView2 = this$0.D().rankThreeValue;
                    Intrinsics.checkNotNullExpressionValue(imageView2, "binding.rankThreeValue");
                    this$0.gone(imageView2);
                }
            } else {
                TextView textView6 = this$0.D().tvRankThree;
                Intrinsics.checkNotNullExpressionValue(textView6, "binding.tvRankThree");
                this$0.gone(textView6);
                ImageView imageView3 = this$0.D().rankThreeValue;
                Intrinsics.checkNotNullExpressionValue(imageView3, "binding.rankThreeValue");
                this$0.gone(imageView3);
                this$0.D().tvRankThreeName.setText("--");
                this$0.D().tvRankThreeSteps.setText("--");
                this$0.D().rankThreeIv.setImageDrawable(this$0.requireContext().getDrawable(R.drawable.circular_shape_grey));
            }
            if (this$0.s.size() >= 2 && this$0.s.get(1) != null) {
                TextView textView7 = this$0.D().tvRankTwoName;
                TopRankModel.DataBean.TopRanksBean topRanksBean6 = this$0.s.get(1);
                textView7.setText((topRanksBean6 == null || (r4 = topRanksBean6.getUserName()) == null) ? "--" : "--");
                TextView textView8 = this$0.D().tvRankTwoSteps;
                TopRankModel.DataBean.TopRanksBean topRanksBean7 = this$0.s.get(1);
                textView8.setText((topRanksBean7 == null || (r4 = Integer.valueOf(topRanksBean7.getSteps()).toString()) == null) ? "--" : "--");
                RequestManager with2 = Glide.with(this$0.requireContext());
                TopRankModel.DataBean.TopRanksBean topRanksBean8 = this$0.s.get(1);
                with2.m30load(topRanksBean8 != null ? topRanksBean8.getDpUrl() : null).apply((BaseRequestOptions<?>) new RequestOptions().circleCrop().placeholder(R.drawable.default_avatar)).into(this$0.D().rankTwoIv);
                TopRankModel.DataBean.TopRanksBean topRanksBean9 = this$0.s.get(1);
                if ((topRanksBean9 != null ? topRanksBean9.getRank() : null) != null) {
                    TextView textView9 = this$0.D().tvRankTwo;
                    Intrinsics.checkNotNullExpressionValue(textView9, "binding.tvRankTwo");
                    this$0.visible(textView9);
                    ImageView imageView4 = this$0.D().rankTwoValue;
                    Intrinsics.checkNotNullExpressionValue(imageView4, "binding.rankTwoValue");
                    this$0.visible(imageView4);
                    TextView textView10 = this$0.D().tvRankTwo;
                    TopRankModel.DataBean.TopRanksBean topRanksBean10 = this$0.s.get(1);
                    textView10.setText(String.valueOf(topRanksBean10 != null ? topRanksBean10.getRank() : null));
                } else {
                    TextView textView11 = this$0.D().tvRankTwo;
                    Intrinsics.checkNotNullExpressionValue(textView11, "binding.tvRankTwo");
                    this$0.gone(textView11);
                    ImageView imageView5 = this$0.D().rankTwoValue;
                    Intrinsics.checkNotNullExpressionValue(imageView5, "binding.rankTwoValue");
                    this$0.gone(imageView5);
                }
            } else {
                TextView textView12 = this$0.D().tvRankTwo;
                Intrinsics.checkNotNullExpressionValue(textView12, "binding.tvRankTwo");
                this$0.gone(textView12);
                ImageView imageView6 = this$0.D().rankTwoValue;
                Intrinsics.checkNotNullExpressionValue(imageView6, "binding.rankTwoValue");
                this$0.gone(imageView6);
                this$0.D().tvRankTwoName.setText("--");
                this$0.D().tvRankTwoSteps.setText("--");
                this$0.D().rankTwoIv.setImageDrawable(this$0.requireContext().getDrawable(R.drawable.circular_shape_grey));
            }
            if (this$0.s.size() >= 1 && this$0.s.get(0) != null) {
                TextView textView13 = this$0.D().tvRankOneName;
                TopRankModel.DataBean.TopRanksBean topRanksBean11 = this$0.s.get(0);
                textView13.setText((topRanksBean11 == null || (r8 = topRanksBean11.getUserName()) == null) ? "--" : "--");
                TextView textView14 = this$0.D().tvRankOneSteps;
                TopRankModel.DataBean.TopRanksBean topRanksBean12 = this$0.s.get(0);
                if (topRanksBean12 != null && (num = Integer.valueOf(topRanksBean12.getSteps()).toString()) != null) {
                    str = num;
                }
                textView14.setText(str);
                RequestManager with3 = Glide.with(this$0.requireContext());
                TopRankModel.DataBean.TopRanksBean topRanksBean13 = this$0.s.get(0);
                with3.m30load(topRanksBean13 != null ? topRanksBean13.getDpUrl() : null).apply((BaseRequestOptions<?>) new RequestOptions().circleCrop().placeholder(R.drawable.default_avatar)).into(this$0.D().rankOneIv);
                TopRankModel.DataBean.TopRanksBean topRanksBean14 = this$0.s.get(0);
                if ((topRanksBean14 != null ? topRanksBean14.getRank() : null) != null) {
                    TextView textView15 = this$0.D().tvRankOne;
                    Intrinsics.checkNotNullExpressionValue(textView15, "binding.tvRankOne");
                    this$0.visible(textView15);
                    ImageView imageView7 = this$0.D().rankOneValue;
                    Intrinsics.checkNotNullExpressionValue(imageView7, "binding.rankOneValue");
                    this$0.visible(imageView7);
                    TextView textView16 = this$0.D().tvRankOne;
                    TopRankModel.DataBean.TopRanksBean topRanksBean15 = this$0.s.get(0);
                    textView16.setText(String.valueOf(topRanksBean15 != null ? topRanksBean15.getRank() : null));
                } else {
                    TextView textView17 = this$0.D().tvRankOne;
                    Intrinsics.checkNotNullExpressionValue(textView17, "binding.tvRankOne");
                    this$0.gone(textView17);
                    ImageView imageView8 = this$0.D().rankOneValue;
                    Intrinsics.checkNotNullExpressionValue(imageView8, "binding.rankOneValue");
                    this$0.gone(imageView8);
                }
            } else {
                TextView textView18 = this$0.D().tvRankOne;
                Intrinsics.checkNotNullExpressionValue(textView18, "binding.tvRankOne");
                this$0.gone(textView18);
                ImageView imageView9 = this$0.D().rankOneValue;
                Intrinsics.checkNotNullExpressionValue(imageView9, "binding.rankOneValue");
                this$0.gone(imageView9);
                this$0.D().tvRankOneName.setText("--");
                this$0.D().tvRankOneSteps.setText("--");
                this$0.D().rankOneIv.setImageDrawable(this$0.requireContext().getDrawable(R.drawable.circular_shape_grey));
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this$0.s);
            if (!this$0.v) {
                if (arrayList.size() >= 1) {
                    arrayList.remove(0);
                }
                if (arrayList.size() >= 1) {
                    arrayList.remove(0);
                }
                if (arrayList.size() >= 1) {
                    arrayList.remove(0);
                }
            }
            this$0.D().rvRankList.setLayoutManager(new LinearLayoutManager(this$0.requireContext()));
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            this$0.u = new TopRankersListAdapter(requireContext, arrayList);
            RecyclerView recyclerView = this$0.D().rvRankList;
            TopRankersListAdapter topRankersListAdapter = this$0.u;
            if (topRankersListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rankListAdapter");
                topRankersListAdapter = null;
            }
            recyclerView.setAdapter(topRankersListAdapter);
            if (this$0.s.size() <= 3 && !this$0.v) {
                ConstraintLayout constraintLayout5 = this$0.D().clRankList;
                Intrinsics.checkNotNullExpressionValue(constraintLayout5, "binding.clRankList");
                this$0.visible(constraintLayout5);
                View view = this$0.D().view;
                Intrinsics.checkNotNullExpressionValue(view, "binding.view");
                this$0.gone(view);
                LinearLayout linearLayout2 = this$0.D().llList;
                Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.llList");
                this$0.gone(linearLayout2);
                RecyclerView recyclerView2 = this$0.D().rvRankList;
                Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.rvRankList");
                this$0.gone(recyclerView2);
            } else {
                LinearLayout linearLayout3 = this$0.D().llList;
                Intrinsics.checkNotNullExpressionValue(linearLayout3, "binding.llList");
                this$0.visible(linearLayout3);
                RecyclerView recyclerView3 = this$0.D().rvRankList;
                Intrinsics.checkNotNullExpressionValue(recyclerView3, "binding.rvRankList");
                this$0.visible(recyclerView3);
                View view2 = this$0.D().view;
                Intrinsics.checkNotNullExpressionValue(view2, "binding.view");
                this$0.visible(view2);
            }
            Iterator<TopRankModel.DataBean.TopRanksBean> it = this$0.s.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                TopRankModel.DataBean.TopRanksBean next = it.next();
                if (next.getUserId() == SessionManager.getInstance(this$0.getContext()).getUserDetails().getUserId()) {
                    this$0.y = next;
                    break;
                } else if (this$0.o == this$0.p) {
                    this$0.y = null;
                }
            }
            this$0.x();
        } else {
            this$0.F();
        }
        this$0.dismissProgress();
    }

    public static final void P(FragmentLeaderboard this$0, RankHistoryModel rankHistoryModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (rankHistoryModel.getData().getRanks() != null) {
            List<RankHistoryModel.DataBean.RanksBean> ranks = rankHistoryModel.getData().getRanks();
            Intrinsics.checkNotNullExpressionValue(ranks, "it.data.ranks");
            this$0.t = ranks;
            if (this$0.o == 1) {
                RankHistoryModel.DataBean.RanksBean ranksBean = ranks.get(0);
                LeaderboardViewModel leaderboardViewModel = null;
                if ((ranksBean != null ? Integer.valueOf(ranksBean.getId()) : null) != null) {
                    TopRankModel.DataBean.TopRanksBean topRanksBean = new TopRankModel.DataBean.TopRanksBean();
                    this$0.y = topRanksBean;
                    topRanksBean.setRank(Integer.valueOf(this$0.t.get(0).getRank()));
                    TopRankModel.DataBean.TopRanksBean topRanksBean2 = this$0.y;
                    if (topRanksBean2 != null) {
                        topRanksBean2.setSteps(this$0.t.get(0).getSteps());
                    }
                    TopRankModel.DataBean.TopRanksBean topRanksBean3 = this$0.y;
                    if (topRanksBean3 != null) {
                        topRanksBean3.setDpUrl(this$0.t.get(0).getDpUrl());
                    }
                    TopRankModel.DataBean.TopRanksBean topRanksBean4 = this$0.y;
                    if (topRanksBean4 != null) {
                        topRanksBean4.setPreviousRank(this$0.t.get(0).getPreviousRank());
                    }
                    TopRankModel.DataBean.TopRanksBean topRanksBean5 = this$0.y;
                    if (topRanksBean5 != null) {
                        topRanksBean5.setUserId(this$0.t.get(0).getUserId());
                    }
                    TopRankModel.DataBean.TopRanksBean topRanksBean6 = this$0.y;
                    if (topRanksBean6 != null) {
                        topRanksBean6.setUserName(this$0.t.get(0).getUserName().toString());
                    }
                    TopRankModel.DataBean.TopRanksBean topRanksBean7 = this$0.y;
                    if (topRanksBean7 != null) {
                        topRanksBean7.setRankDate(this$0.t.get(0).getRankDate());
                    }
                    LeaderboardViewModel leaderboardViewModel2 = this$0.n;
                    if (leaderboardViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        leaderboardViewModel = leaderboardViewModel2;
                    }
                    leaderboardViewModel.getGlobalRankListData(String.valueOf(this$0.t.get(0).getId()));
                }
            }
        }
    }

    public static final void y(FragmentLeaderboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage = this$0.I;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage);
        bottomSheetThemeDialogOneButtonTitleMessage.dismiss();
    }

    public final FragmentLeaderboardHomeBinding D() {
        FragmentLeaderboardHomeBinding fragmentLeaderboardHomeBinding = this.m;
        if (fragmentLeaderboardHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentLeaderboardHomeBinding;
    }

    public final void E() {
        if (AppUtils.isNetConnected(requireContext())) {
            showProgress(true);
            Q();
            FitnessChallengeListViewModel fitnessChallengeListViewModel = this.D;
            if (fitnessChallengeListViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
                fitnessChallengeListViewModel = null;
            }
            fitnessChallengeListViewModel.getFitnessChallengesPagingData();
            z();
            return;
        }
        showNoInternetDialog();
        dismissProgress();
    }

    public final void F() {
        ConstraintLayout constraintLayout = D().clToppersRank;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clToppersRank");
        gone(constraintLayout);
        LinearLayout linearLayout = D().llMyRank;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.llMyRank");
        gone(linearLayout);
        ConstraintLayout constraintLayout2 = D().clRankList;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clRankList");
        gone(constraintLayout2);
        ConstraintLayout constraintLayout3 = D().clEmptyBuddies;
        Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clEmptyBuddies");
        visible(constraintLayout3);
        int i = this.o;
        if (i == this.p && !this.v) {
            if (this.w) {
                D().tvEmptyRankList.setText(requireContext().getString(R.string.you_can_see_where_you_stand));
                Button button = D().btnAddBuddies;
                Intrinsics.checkNotNullExpressionValue(button, "binding.btnAddBuddies");
                gone(button);
            } else {
                D().tvEmptyRankList.setText(requireContext().getString(R.string.empty_buddies_msg));
                Button button2 = D().btnAddBuddies;
                Intrinsics.checkNotNullExpressionValue(button2, "binding.btnAddBuddies");
                visible(button2);
            }
        } else if (i == this.q && !this.v) {
            D().tvEmptyRankList.setText(requireContext().getString(R.string.you_can_see_where_you_stand));
            Button button3 = D().btnAddBuddies;
            Intrinsics.checkNotNullExpressionValue(button3, "binding.btnAddBuddies");
            gone(button3);
        } else if (this.v) {
            LinearLayout linearLayout2 = D().llMyRank;
            Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.llMyRank");
            visible(linearLayout2);
            ConstraintLayout constraintLayout4 = D().clRankList;
            Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.clRankList");
            visible(constraintLayout4);
            RecyclerView recyclerView = D().rvRankList;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvRankList");
            gone(recyclerView);
            ConstraintLayout constraintLayout5 = D().clEmptyBuddies;
            Intrinsics.checkNotNullExpressionValue(constraintLayout5, "binding.clEmptyBuddies");
            gone(constraintLayout5);
        }
        dismissProgress();
    }

    public final void G(TopRankModel.DataBean.TopRanksBean topRanksBean) {
        BuddiesRankDetailsDialog buddiesRankDetailsDialog;
        BuddiesRankDetailsDialog buddiesRankDetailsDialog2 = this.x;
        if (buddiesRankDetailsDialog2 != null) {
            boolean z = true;
            if (buddiesRankDetailsDialog2 == null || !buddiesRankDetailsDialog2.isShowing()) {
                z = false;
            }
            if (z && (buddiesRankDetailsDialog = this.x) != null) {
                buddiesRankDetailsDialog.dismiss();
            }
            this.x = null;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        BuddiesRankDetailsDialog buddiesRankDetailsDialog3 = new BuddiesRankDetailsDialog(requireActivity, topRanksBean);
        this.x = buddiesRankDetailsDialog3;
        buddiesRankDetailsDialog3.setCancelableOutside(false);
        BuddiesRankDetailsDialog buddiesRankDetailsDialog4 = this.x;
        if (buddiesRankDetailsDialog4 != null) {
            buddiesRankDetailsDialog4.show();
        }
    }

    public final void Q() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.C = new FitnessChallengePagingAdapter(requireContext, true, this);
        D().rvChallenges.setLayoutManager(new LinearLayoutManager(requireActivity(), 1, false));
        D().rvChallenges.setHasFixedSize(true);
        RecyclerView recyclerView = D().rvChallenges;
        FitnessChallengePagingAdapter fitnessChallengePagingAdapter = this.C;
        if (fitnessChallengePagingAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengePagingAdapter");
            fitnessChallengePagingAdapter = null;
        }
        recyclerView.setAdapter(fitnessChallengePagingAdapter.withLoadStateFooter(new LoaderAdapter()));
    }

    public final void R(boolean z) {
        if (z) {
            ConstraintLayout constraintLayout = D().clRankList;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clRankList");
            gone(constraintLayout);
            LinearLayout linearLayout = D().llMyRank;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.llMyRank");
            gone(linearLayout);
            ConstraintLayout constraintLayout2 = D().clToppersRank;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clToppersRank");
            gone(constraintLayout2);
            RecyclerView recyclerView = D().rvChallenges;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvChallenges");
            gone(recyclerView);
            View root = D().emptyChallengeView.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.emptyChallengeView.root");
            gone(root);
            return;
        }
        LinearLayout linearLayout2 = D().llMyRank;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.llMyRank");
        gone(linearLayout2);
        ConstraintLayout constraintLayout3 = D().clRankList;
        Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clRankList");
        gone(constraintLayout3);
        ConstraintLayout constraintLayout4 = D().clToppersRank;
        Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.clToppersRank");
        gone(constraintLayout4);
        ConstraintLayout constraintLayout5 = D().clEmptyBuddies;
        Intrinsics.checkNotNullExpressionValue(constraintLayout5, "binding.clEmptyBuddies");
        gone(constraintLayout5);
        View root2 = D().emptyChallengeView.getRoot();
        Intrinsics.checkNotNullExpressionValue(root2, "binding.emptyChallengeView.root");
        gone(root2);
        RecyclerView recyclerView2 = D().rvChallenges;
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.rvChallenges");
        visible(recyclerView2);
    }

    public final void S(TextView textView, TextView textView2, TextView textView3) {
        textView.setBackgroundResource(R.drawable.tab_selected_bg);
        textView.setTextColor(requireContext().getColor(R.color.main_text_color));
        textView.setTextAppearance(R.style.bold);
        T(textView2);
        T(textView3);
    }

    public final void T(TextView textView) {
        textView.setBackground(null);
        textView.setTextColor(requireContext().getColor(R.color.secondary_text_color));
        textView.setTextAppearance(R.style.regular);
    }

    public final void U() {
        showProgress(true);
        FragmentLeaderboardHomeBinding D = D();
        R(this.o < 2);
        int i = this.o;
        LeaderboardViewModel leaderboardViewModel = null;
        NotificationsViewModel notificationsViewModel = null;
        if (i == this.p) {
            NotificationsViewModel notificationsViewModel2 = this.B;
            if (notificationsViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBuddiesNotificationViewModel");
            } else {
                notificationsViewModel = notificationsViewModel2;
            }
            notificationsViewModel.loadBuddiesGoalInformation();
            TextView tabBuddies = D.tabBuddies;
            Intrinsics.checkNotNullExpressionValue(tabBuddies, "tabBuddies");
            TextView tabChallenge = D.tabChallenge;
            Intrinsics.checkNotNullExpressionValue(tabChallenge, "tabChallenge");
            TextView tabGlobal = D.tabGlobal;
            Intrinsics.checkNotNullExpressionValue(tabGlobal, "tabGlobal");
            S(tabBuddies, tabChallenge, tabGlobal);
        } else if (i == this.q) {
            LeaderboardViewModel leaderboardViewModel2 = this.n;
            if (leaderboardViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                leaderboardViewModel = leaderboardViewModel2;
            }
            RankType rankType = this.F;
            if (rankType == null) {
                rankType = RankType.DAY;
            }
            FilterType filterType = this.E;
            if (filterType == null) {
                filterType = FilterType.CITY;
            }
            leaderboardViewModel.getDailyRankingHistory(rankType, filterType);
            TextView tabGlobal2 = D.tabGlobal;
            Intrinsics.checkNotNullExpressionValue(tabGlobal2, "tabGlobal");
            TextView tabChallenge2 = D.tabChallenge;
            Intrinsics.checkNotNullExpressionValue(tabChallenge2, "tabChallenge");
            TextView tabBuddies2 = D.tabBuddies;
            Intrinsics.checkNotNullExpressionValue(tabBuddies2, "tabBuddies");
            S(tabGlobal2, tabChallenge2, tabBuddies2);
        } else if (i == this.r) {
            E();
            TextView tabChallenge3 = D.tabChallenge;
            Intrinsics.checkNotNullExpressionValue(tabChallenge3, "tabChallenge");
            TextView tabGlobal3 = D.tabGlobal;
            Intrinsics.checkNotNullExpressionValue(tabGlobal3, "tabGlobal");
            TextView tabBuddies3 = D.tabBuddies;
            Intrinsics.checkNotNullExpressionValue(tabBuddies3, "tabBuddies");
            S(tabChallenge3, tabGlobal3, tabBuddies3);
        } else {
            TextView tabChallenge4 = D.tabChallenge;
            Intrinsics.checkNotNullExpressionValue(tabChallenge4, "tabChallenge");
            TextView tabGlobal4 = D.tabGlobal;
            Intrinsics.checkNotNullExpressionValue(tabGlobal4, "tabGlobal");
            TextView tabBuddies4 = D.tabBuddies;
            Intrinsics.checkNotNullExpressionValue(tabBuddies4, "tabBuddies");
            S(tabChallenge4, tabGlobal4, tabBuddies4);
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.FitnessChallengePagingAdapter.ChallengeClickListener
    public void challengeClick(@NotNull BuddiesChallengeRes.Item challenge, boolean z) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        if (!AppUtils.isNetConnected(requireContext())) {
            showNoInternetDialog();
            return;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        a aVar = new a(challenge);
        Intent intent = new Intent(requireContext, FitnessChallengeDetails.class);
        aVar.invoke((a) intent);
        requireContext.startActivity(intent, null);
    }

    public final void createChallengeError() {
        if (this.I == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(com.coveiot.android.fitnesschallenges.R.string.maximum_challenges_created);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…ximum_challenges_created)");
            String string2 = getString(com.coveiot.android.fitnesschallenges.R.string.please_note_that_a_maximum_of_two_challenges_can_be_created_simultaneously);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…e_created_simultaneously)");
            BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage = new BottomSheetThemeDialogOneButtonTitleMessage(requireContext, string, string2);
            this.I = bottomSheetThemeDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage);
            String string3 = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetThemeDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.fragment.p
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentLeaderboard.y(FragmentLeaderboard.this, view);
                }
            });
        }
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage2 = this.I;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage2);
        if (bottomSheetThemeDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage3 = this.I;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage3);
        bottomSheetThemeDialogOneButtonTitleMessage3.show();
    }

    public final int getFILTERS_REQUEST_CODE() {
        return this.G;
    }

    @NotNull
    public final List<RankHistoryModel.DataBean.RanksBean> getFilteredRankList() {
        return this.t;
    }

    @NotNull
    public final List<TopRankModel.DataBean.TopRanksBean> getToppersRankList() {
        return this.s;
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.FitnessChallengePagingAdapter.ChallengeClickListener
    public void isDataLoaded(boolean z) {
        if (z) {
            dismissProgress();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.G && i2 == -1 && intent != null) {
            Bundle extras = intent.getExtras();
            LeaderboardViewModel leaderboardViewModel = null;
            if ((extras != null ? extras.getString(ActivityRanksFilter.RANK_TYPE_SELECTED_FILTER) : null) == null) {
                Bundle extras2 = intent.getExtras();
                if ((extras2 != null ? extras2.getString(ActivityRanksFilter.FILTER_TYPE_SELECTED_FILTER) : null) == null) {
                    this.A = new ArrayList();
                    RanksFilterAppliedAdapter ranksFilterAppliedAdapter = this.z;
                    if (ranksFilterAppliedAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("ranksFilterAppliedAdapter");
                        ranksFilterAppliedAdapter = null;
                    }
                    ranksFilterAppliedAdapter.setData(this.A);
                    showProgress(true);
                    if (this.o == 0) {
                        LeaderboardViewModel leaderboardViewModel2 = this.n;
                        if (leaderboardViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        } else {
                            leaderboardViewModel = leaderboardViewModel2;
                        }
                        leaderboardViewModel.getBuddiesRankListData(this.F, this.E);
                        return;
                    }
                    LeaderboardViewModel leaderboardViewModel3 = this.n;
                    if (leaderboardViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        leaderboardViewModel = leaderboardViewModel3;
                    }
                    leaderboardViewModel.getDailyRankingHistory(RankType.DAY, FilterType.CITY);
                    return;
                }
            }
            this.v = true;
            int i3 = R.string.rank_daily;
            String string = getString(i3);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.rank_daily)");
            int i4 = R.string.rank_locality;
            String string2 = getString(i4);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.rank_locality)");
            Bundle extras3 = intent.getExtras();
            String string3 = extras3 != null ? extras3.getString(ActivityRanksFilter.RANK_TYPE_SELECTED_FILTER) : null;
            boolean z = false;
            if (!(string3 == null || string3.length() == 0)) {
                Bundle extras4 = intent.getExtras();
                string = extras4 != null ? extras4.getString(ActivityRanksFilter.RANK_TYPE_SELECTED_FILTER) : null;
                Intrinsics.checkNotNull(string);
            }
            Bundle extras5 = intent.getExtras();
            String string4 = extras5 != null ? extras5.getString(ActivityRanksFilter.FILTER_TYPE_SELECTED_FILTER) : null;
            if (string4 == null || string4.length() == 0) {
                z = true;
            }
            if (!z) {
                Bundle extras6 = intent.getExtras();
                string2 = extras6 != null ? extras6.getString(ActivityRanksFilter.FILTER_TYPE_SELECTED_FILTER) : null;
                Intrinsics.checkNotNull(string2);
            }
            if (kotlin.text.m.equals(string, getString(i3), true)) {
                this.F = RankType.DAY;
            } else if (kotlin.text.m.equals(string, getString(R.string.rank_weekly), true)) {
                this.F = RankType.WEEK;
            } else if (kotlin.text.m.equals(string, getString(R.string.rank_monthly), true)) {
                this.F = RankType.MONTH;
            }
            if (kotlin.text.m.equals(string2, getString(i4), true)) {
                this.E = FilterType.LOCALITY;
            } else if (kotlin.text.m.equals(string2, getString(R.string.rank_state), true)) {
                this.E = FilterType.STATE;
            } else if (kotlin.text.m.equals(string2, getString(R.string.rank_city), true)) {
                this.E = FilterType.CITY;
            } else if (kotlin.text.m.equals(string2, getString(R.string.rank_country), true)) {
                this.E = FilterType.COUNTRY;
            }
            ConstraintLayout constraintLayout = D().clToppersRank;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clToppersRank");
            gone(constraintLayout);
            ConstraintLayout constraintLayout2 = D().clFilter;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clFilter");
            visible(constraintLayout2);
            ArrayList arrayList = new ArrayList();
            this.A = arrayList;
            arrayList.add(string);
            this.A.add(string2);
            RanksFilterAppliedAdapter ranksFilterAppliedAdapter2 = this.z;
            if (ranksFilterAppliedAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ranksFilterAppliedAdapter");
                ranksFilterAppliedAdapter2 = null;
            }
            ranksFilterAppliedAdapter2.setData(this.A);
            showProgress(true);
            if (this.o == 0) {
                LeaderboardViewModel leaderboardViewModel4 = this.n;
                if (leaderboardViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    leaderboardViewModel = leaderboardViewModel4;
                }
                RankType rankType = this.F;
                Intrinsics.checkNotNull(rankType);
                FilterType filterType = this.E;
                Intrinsics.checkNotNull(filterType);
                leaderboardViewModel.getBuddiesRankListData(rankType, filterType);
                return;
            }
            LeaderboardViewModel leaderboardViewModel5 = this.n;
            if (leaderboardViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                leaderboardViewModel = leaderboardViewModel5;
            }
            RankType rankType2 = this.F;
            Intrinsics.checkNotNull(rankType2);
            FilterType filterType2 = this.E;
            Intrinsics.checkNotNull(filterType2);
            leaderboardViewModel.getDailyRankingHistory(rankType2, filterType2);
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentLeaderboardHomeBinding inflate = FragmentLeaderboardHomeBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater,container,false)");
        this.m = inflate;
        return D().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        F();
    }

    @Override // com.coveiot.leaderboard.views.adapters.RanksFilterAppliedAdapter.OnItemClickListener
    public void onItemClicked(@NotNull String filteredItem) {
        Intrinsics.checkNotNullParameter(filteredItem, "filteredItem");
        this.A.remove(filteredItem);
        RanksFilterAppliedAdapter ranksFilterAppliedAdapter = this.z;
        LeaderboardViewModel leaderboardViewModel = null;
        if (ranksFilterAppliedAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ranksFilterAppliedAdapter");
            ranksFilterAppliedAdapter = null;
        }
        ranksFilterAppliedAdapter.setData(this.A);
        if (this.A.size() == 1) {
            if (kotlin.text.m.equals(filteredItem, getString(R.string.rank_daily), true) || kotlin.text.m.equals(filteredItem, getString(R.string.rank_weekly), true) || kotlin.text.m.equals(filteredItem, getString(R.string.rank_monthly), true)) {
                this.F = null;
                showProgress(true);
                if (this.o == 0) {
                    LeaderboardViewModel leaderboardViewModel2 = this.n;
                    if (leaderboardViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        leaderboardViewModel2 = null;
                    }
                    leaderboardViewModel2.getBuddiesRankListData(RankType.DAY, this.E);
                } else {
                    LeaderboardViewModel leaderboardViewModel3 = this.n;
                    if (leaderboardViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        leaderboardViewModel3 = null;
                    }
                    RankType rankType = RankType.DAY;
                    FilterType filterType = this.E;
                    Intrinsics.checkNotNull(filterType);
                    leaderboardViewModel3.getDailyRankingHistory(rankType, filterType);
                }
            }
            if (kotlin.text.m.equals(filteredItem, getString(R.string.rank_locality), true) || kotlin.text.m.equals(filteredItem, getString(R.string.rank_city), true) || kotlin.text.m.equals(filteredItem, getString(R.string.rank_state), true) || kotlin.text.m.equals(filteredItem, getString(R.string.rank_country), true)) {
                this.E = null;
                showProgress(true);
                if (this.o == 0) {
                    LeaderboardViewModel leaderboardViewModel4 = this.n;
                    if (leaderboardViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        leaderboardViewModel4 = null;
                    }
                    leaderboardViewModel4.getBuddiesRankListData(this.F, FilterType.LOCALITY);
                } else {
                    LeaderboardViewModel leaderboardViewModel5 = this.n;
                    if (leaderboardViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        leaderboardViewModel5 = null;
                    }
                    RankType rankType2 = this.F;
                    Intrinsics.checkNotNull(rankType2);
                    leaderboardViewModel5.getDailyRankingHistory(rankType2, FilterType.LOCALITY);
                }
            }
        }
        if (this.A.size() == 0) {
            this.v = false;
            showProgress(true);
            this.F = null;
            this.E = null;
            if (this.o == 0) {
                LeaderboardViewModel leaderboardViewModel6 = this.n;
                if (leaderboardViewModel6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    leaderboardViewModel = leaderboardViewModel6;
                }
                leaderboardViewModel.getBuddiesRankListData(this.F, this.E);
                return;
            }
            LeaderboardViewModel leaderboardViewModel7 = this.n;
            if (leaderboardViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                leaderboardViewModel = leaderboardViewModel7;
            }
            leaderboardViewModel.getDailyRankingHistory(RankType.DAY, FilterType.CITY);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        U();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        dismissProgress();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x00b5, code lost:
        if (r6.booleanValue() != false) goto L13;
     */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r6, @org.jetbrains.annotations.Nullable android.os.Bundle r7) {
        /*
            Method dump skipped, instructions count: 416
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.leaderboard.views.fragment.FragmentLeaderboard.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    public final void setFilteredRankList(@NotNull List<RankHistoryModel.DataBean.RanksBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.t = list;
    }

    public final void setToppersRankList(@NotNull List<TopRankModel.DataBean.TopRanksBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.s = list;
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showBuddyContents(@NotNull List<GetBuddyItems> buddiesGoals) {
        Intrinsics.checkNotNullParameter(buddiesGoals, "buddiesGoals");
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showContents(@NotNull List<? extends BuddiesGoal> buddiesGoals) {
        Intrinsics.checkNotNullParameter(buddiesGoals, "buddiesGoals");
        if ((!buddiesGoals.isEmpty()) && this.o == 0) {
            showProgress(true);
            this.w = true;
            LeaderboardViewModel leaderboardViewModel = this.n;
            if (leaderboardViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                leaderboardViewModel = null;
            }
            leaderboardViewModel.getBuddiesRankListData(this.F, this.E);
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showEmptyLayout() {
        this.w = false;
        ConstraintLayout constraintLayout = D().clToppersRank;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clToppersRank");
        gone(constraintLayout);
        LinearLayout linearLayout = D().llMyRank;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.llMyRank");
        gone(linearLayout);
        ConstraintLayout constraintLayout2 = D().clRankList;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clRankList");
        gone(constraintLayout2);
        ConstraintLayout constraintLayout3 = D().clEmptyBuddies;
        Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clEmptyBuddies");
        visible(constraintLayout3);
        D().tvEmptyRankList.setText(requireContext().getString(R.string.empty_buddies_msg));
        Button button = D().btnAddBuddies;
        Intrinsics.checkNotNullExpressionValue(button, "binding.btnAddBuddies");
        visible(button);
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showProgress() {
    }

    public final void x() {
        String num;
        Integer rank;
        TextView textView = D().myRank;
        TopRankModel.DataBean.TopRanksBean topRanksBean = this.y;
        String str = "--";
        textView.setText((topRanksBean == null || (rank = topRanksBean.getRank()) == null) ? "--" : String.valueOf(rank));
        GlideUtils.loadScaledImage(requireContext(), SessionManager.getInstance(requireContext()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.leaderboard.views.fragment.FragmentLeaderboard$buddiesMyRankDetails$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Bitmap circleBitmap;
                Intrinsics.checkNotNullParameter(resource, "resource");
                if (FragmentLeaderboard.this.D().ivRankerProfile == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                FragmentLeaderboard.this.D().ivRankerProfile.setImageBitmap(circleBitmap);
            }
        });
        D().myName.setText(SessionManager.getInstance(requireContext()).getUserDetails().getName());
        TextView textView2 = D().mySteps;
        TopRankModel.DataBean.TopRanksBean topRanksBean2 = this.y;
        if (topRanksBean2 != null && (num = Integer.valueOf(topRanksBean2.getSteps()).toString()) != null) {
            str = num;
        }
        textView2.setText(str);
    }

    public final void z() {
        FitnessChallengeListViewModel fitnessChallengeListViewModel = this.D;
        FitnessChallengeListViewModel fitnessChallengeListViewModel2 = null;
        if (fitnessChallengeListViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
            fitnessChallengeListViewModel = null;
        }
        MutableLiveData<PagingData<BuddiesChallengeRes.Item>> fitnessChallengeList = fitnessChallengeListViewModel.getFitnessChallengeList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final b bVar = new b();
        fitnessChallengeList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.leaderboard.views.fragment.i
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentLeaderboard.A(Function1.this, obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel3 = this.D;
        if (fitnessChallengeListViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
            fitnessChallengeListViewModel3 = null;
        }
        MutableLiveData<Boolean> showEmptyLayout = fitnessChallengeListViewModel3.getShowEmptyLayout();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final c cVar = new c();
        showEmptyLayout.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.leaderboard.views.fragment.j
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentLeaderboard.B(Function1.this, obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel4 = this.D;
        if (fitnessChallengeListViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
        } else {
            fitnessChallengeListViewModel2 = fitnessChallengeListViewModel4;
        }
        MutableLiveData<Boolean> shouldShowProgress = fitnessChallengeListViewModel2.getShouldShowProgress();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        final d dVar = new d();
        shouldShowProgress.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.leaderboard.views.fragment.h
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentLeaderboard.C(Function1.this, obj);
            }
        });
    }
}
