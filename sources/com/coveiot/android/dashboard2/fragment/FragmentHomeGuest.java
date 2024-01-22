package com.coveiot.android.dashboard2.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity;
import com.coveiot.android.activitymodes.workoutVideos.fragments.CultFitVideoFragment;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.ViewModelFactory;
import com.coveiot.android.dashboard2.adapter.BestOffersAdapter;
import com.coveiot.android.dashboard2.databinding.FragmentHomeGuestBinding;
import com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface;
import com.coveiot.android.dashboard2.model.BestOffers;
import com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel;
import com.coveiot.android.dashboard2.viewmodel.PromotionsViewModel;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeAdapter;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel;
import com.coveiot.android.respiratoryrate.utils.ViewUtilsKt;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FragmentHomeGuest extends Fragment implements FitnessChallengeAdapter.ChallengeClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentHomeGuestBinding h;
    public PromotionsViewModel i;
    public FitnessChallengeAdapter j;
    public FragmentHomeViewModel k;
    public FitnessChallengeListViewModel l;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentHomeGuest newInstance() {
            FragmentHomeGuest fragmentHomeGuest = new FragmentHomeGuest();
            fragmentHomeGuest.setArguments(new Bundle());
            return fragmentHomeGuest;
        }
    }

    public static final void A(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_PROFILEICON_TAPPED.getValue(), null);
        this$0.z();
    }

    public static final void B(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireContext(), WorkoutVideosActivity.class));
    }

    public static final void C(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_CREATECHALLENGE_TAPPED.getValue(), null);
        this$0.Q();
    }

    public static final void D(View view) {
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_CHALLENGEBNR_TAPPED.getValue(), null);
    }

    public static final void E(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.Q();
    }

    public static final void F(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_ACTIVITIES_TAPPED.getValue(), null);
        this$0.Q();
    }

    public static final void G(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_CREWSETUP_TAPPED.getValue(), null);
        this$0.Q();
    }

    public static final void H(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_SPORTSCORES_TAPPED.getValue(), null);
        this$0.Q();
    }

    public static final void I(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.Q();
    }

    public static final void J(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_FITNESSPLAN_TAPPED.getValue(), null);
        this$0.Q();
    }

    public static final void K(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_CHALLENGEVIEWMORE_TAPPED.getValue(), null);
        this$0.Q();
    }

    public static final void L(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.Q();
    }

    public static final void O(FragmentHomeGuest this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            View root = this$0.u().noChallengeView.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.noChallengeView.root");
            ViewUtilsKt.visible(root);
        }
    }

    public static final void P(FragmentHomeGuest this$0, BuddiesChallengeRes buddiesChallengeRes) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.isAdded() || buddiesChallengeRes == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(buddiesChallengeRes.getItems().get(0));
        if (buddiesChallengeRes.getItems().size() > 1 && buddiesChallengeRes.getItems().get(1) != null) {
            arrayList.add(buddiesChallengeRes.getItems().get(1));
        }
        if (buddiesChallengeRes.getItems().size() > 1) {
            LinearLayout linearLayout = this$0.u().challengeLinearLayoutDots;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.challengeLinearLayoutDots");
            ViewUtilsKt.visible(linearLayout);
        } else {
            LinearLayout linearLayout2 = this$0.u().challengeLinearLayoutDots;
            Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.challengeLinearLayoutDots");
            ViewUtilsKt.gone(linearLayout2);
        }
        this$0.s(buddiesChallengeRes.getItems().size(), 0);
        View root = this$0.u().noChallengeView.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.noChallengeView.root");
        ViewUtilsKt.gone(root);
        FitnessChallengeAdapter fitnessChallengeAdapter = this$0.j;
        if (fitnessChallengeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeAdapter");
            fitnessChallengeAdapter = null;
        }
        fitnessChallengeAdapter.setChallengesList(arrayList);
    }

    public static final void R(BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        guestOrPairDevicePopup.dismiss();
    }

    public static final void S(FragmentHomeGuest this$0, BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (themesUtils.isGuestUser(requireContext)) {
            if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
                FragmentActivity activity = this$0.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                ((FragmentHomeCallBackInterface) activity).navigateToLogin();
            }
        } else if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity2 = this$0.getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity2).navigateToPairDevice();
        }
        guestOrPairDevicePopup.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final FragmentHomeGuest newInstance() {
        return Companion.newInstance();
    }

    public static final void w(FragmentHomeGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_CULTBANNER_TAPPED.getValue(), null);
        this$0.startActivity(new Intent(this$0.requireContext(), WorkoutVideosActivity.class));
    }

    public static final void y(FragmentHomeGuest this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.u().setShowFitnessChallenge(bool);
    }

    public final void M() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.j = new FitnessChallengeAdapter(requireContext, this);
        RecyclerView recyclerView = u().rvFitnessChallenge;
        FitnessChallengeAdapter fitnessChallengeAdapter = this.j;
        if (fitnessChallengeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeAdapter");
            fitnessChallengeAdapter = null;
        }
        recyclerView.setAdapter(fitnessChallengeAdapter);
        u().rvFitnessChallenge.setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
        u().rvFitnessChallenge.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHomeGuest$setChallengesRecyclerview$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView2, int i) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrollStateChanged(recyclerView2, i);
                RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
                Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                FragmentHomeGuest.this.s(2, ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition());
            }
        });
    }

    public final void N() {
        FitnessChallengeListViewModel fitnessChallengeListViewModel = this.l;
        FitnessChallengeListViewModel fitnessChallengeListViewModel2 = null;
        if (fitnessChallengeListViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
            fitnessChallengeListViewModel = null;
        }
        fitnessChallengeListViewModel.getShowEmptyLayout().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.dashboard2.fragment.m1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentHomeGuest.O(FragmentHomeGuest.this, (Boolean) obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel3 = this.l;
        if (fitnessChallengeListViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
        } else {
            fitnessChallengeListViewModel2 = fitnessChallengeListViewModel3;
        }
        fitnessChallengeListViewModel2.getGetBuddiesChallengeListLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.dashboard2.fragment.l1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentHomeGuest.P(FragmentHomeGuest.this, (BuddiesChallengeRes) obj);
            }
        });
    }

    public final void Q() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String string = getString(themesUtils.isGuestUser(requireContext2) ? R.string.login : R.string.pair_device);
        Intrinsics.checkNotNullExpressionValue(string, "if(requireContext().isGu…ing(R.string.pair_device)");
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        String string2 = getString(themesUtils.isGuestUser(requireContext3) ? R.string.please_login_to_use_this_feature : R.string.please_pair_with_your_bluetooth_device_to_use_this_feature);
        Intrinsics.checkNotNullExpressionValue(string2, "if(requireContext().isGu…vice_to_use_this_feature)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.j1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHomeGuest.R(BottomSheetDialogTwoButtons.this, view);
            }
        });
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        String string4 = getString(themesUtils.isGuestUser(requireContext4) ? R.string.login_now : R.string.pair_now);
        Intrinsics.checkNotNullExpressionValue(string4, "if(requireContext().isGu…String(R.string.pair_now)");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.i1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHomeGuest.S(FragmentHomeGuest.this, bottomSheetDialogTwoButtons, view);
            }
        });
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeAdapter.ChallengeClickListener
    public void challengeClick(@NotNull BuddiesChallengeRes.Item challenge, boolean z) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        if (isAdded()) {
            Q();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.h = FragmentHomeGuestBinding.inflate(inflater, viewGroup, false);
        View root = u().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        v(u());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.k = (FragmentHomeViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity)).get(FragmentHomeViewModel.class);
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        this.i = (PromotionsViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity2)).get(PromotionsViewModel.class);
        FragmentHomeViewModel fragmentHomeViewModel = this.k;
        if (fragmentHomeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            fragmentHomeViewModel = null;
        }
        fragmentHomeViewModel.getFitnessChallengeFirebaseConfig();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.l = new FitnessChallengeListViewModel(requireContext);
        FragmentHomeGuestBinding u = u();
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        if (themesUtils.isPairDeviceLater(requireContext2)) {
            String name = SessionManager.getInstance(getActivity()).getUserDetails().getName();
            Intrinsics.checkNotNullExpressionValue(name, "getInstance(activity).userDetails.name");
            if (name.length() > 0) {
                u.tvGuestUserName.setText(SessionManager.getInstance(getActivity()).getUserDetails().getName());
            } else {
                u.tvGuestUserName.setText(requireContext().getString(R.string.boathead));
            }
        }
        u.activities700plus.tvActionButton.setText(getString(R.string.get_started));
        ImageView imageView = u.toolbar.imgWeatherDash;
        Intrinsics.checkNotNullExpressionValue(imageView, "toolbar.imgWeatherDash");
        ViewUtilsKt.gone(imageView);
        ConstraintLayout constraintLayout = u.toolbar.clboAtCoins;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "toolbar.clboAtCoins");
        ViewUtilsKt.gone(constraintLayout);
        u.toolbar.imgVProfilePic.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.r1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.A(FragmentHomeGuest.this, view2);
            }
        });
        u.viewMoreCultFit.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.v1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.B(FragmentHomeGuest.this, view2);
            }
        });
        u.deviceNotPaired.clCompleteProfile.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.w1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.E(FragmentHomeGuest.this, view2);
            }
        });
        u.activities700plus.cvMain.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.f1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.F(FragmentHomeGuest.this, view2);
            }
        });
        u.clWellnessCrew.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.g1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.G(FragmentHomeGuest.this, view2);
            }
        });
        u.clMatches.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.o1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.H(FragmentHomeGuest.this, view2);
            }
        });
        u.clTapPay.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.t1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.I(FragmentHomeGuest.this, view2);
            }
        });
        u.clBuildFitnessPlan.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.u1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.J(FragmentHomeGuest.this, view2);
            }
        });
        u.tvFitnessChallengeViewMore.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.s1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.K(FragmentHomeGuest.this, view2);
            }
        });
        u.challengeBannerSectionLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.q1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.L(FragmentHomeGuest.this, view2);
            }
        });
        u.viewFitnessChallengeDashboardHeader.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.h1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.C(FragmentHomeGuest.this, view2);
            }
        });
        u.noChallengeView.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.k1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentHomeGuest.D(view2);
            }
        });
        t();
        M();
        x();
        N();
    }

    public final void s(int i, int i2) {
        LinearLayout linearLayout = u().challengeLinearLayoutDots;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.challengeLinearLayoutDots");
        linearLayout.removeAllViews();
        for (int i3 = 0; i3 < i; i3++) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, getResources().getDimensionPixelSize(R.dimen.margin_8dp), 0);
            imageView.setLayoutParams(layoutParams);
            if (i3 == i2) {
                imageView.setImageResource(R.drawable.viewpager_selected_indicator);
            } else {
                imageView.setImageResource(R.drawable.viewpager_unselected_indicator);
            }
            linearLayout.addView(imageView);
        }
    }

    public final void t() {
        PromotionsViewModel promotionsViewModel = this.i;
        if (promotionsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPromotionsViewModel");
            promotionsViewModel = null;
        }
        promotionsViewModel.getBestOffersFromServer(new PromotionsViewModel.BestOffersListeners() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHomeGuest$getBestOffersData$1
            @Override // com.coveiot.android.dashboard2.viewmodel.PromotionsViewModel.BestOffersListeners
            public void onFailure(@Nullable String str) {
            }

            @Override // com.coveiot.android.dashboard2.viewmodel.PromotionsViewModel.BestOffersListeners
            public void onOffersLoaded(@Nullable List<BestOffers> list) {
                FragmentHomeGuestBinding u;
                FragmentHomeGuestBinding u2;
                FragmentHomeGuestBinding u3;
                FragmentHomeGuestBinding u4;
                FragmentHomeGuestBinding u5;
                FragmentHomeGuestBinding u6;
                if (list != null) {
                    if (FragmentHomeGuest.this.isAdded()) {
                        u2 = FragmentHomeGuest.this.u();
                        u2.setBestOffers(Integer.valueOf(list.size()));
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FragmentHomeGuest.this.requireContext());
                        linearLayoutManager.setOrientation(0);
                        linearLayoutManager.setReverseLayout(false);
                        u3 = FragmentHomeGuest.this.u();
                        u3.tvBestOffersCardContainer.rcvBestOffers.setLayoutManager(linearLayoutManager);
                        u4 = FragmentHomeGuest.this.u();
                        u4.tvBestOffersCardContainer.rcvBestOffers.setHasFixedSize(false);
                        u5 = FragmentHomeGuest.this.u();
                        u5.tvBestOffersCardContainer.rcvBestOffers.setAdapter(new BestOffersAdapter(list));
                        u6 = FragmentHomeGuest.this.u();
                        u6.tvBestOffersCardContainer.rcvBestOffers.scrollToPosition(0);
                        return;
                    }
                    return;
                }
                u = FragmentHomeGuest.this.u();
                u.setBestOffers(0);
            }
        });
    }

    public final FragmentHomeGuestBinding u() {
        FragmentHomeGuestBinding fragmentHomeGuestBinding = this.h;
        Intrinsics.checkNotNull(fragmentHomeGuestBinding);
        return fragmentHomeGuestBinding;
    }

    public final void v(FragmentHomeGuestBinding fragmentHomeGuestBinding) {
        if (AppUtils.isNetConnected(requireActivity())) {
            fragmentHomeGuestBinding.cultFitLayout.setVisibility(0);
            if (SessionManager.getInstance(requireContext()).getLastVideoId() == null) {
                fragmentHomeGuestBinding.setShowCultFitFTU(Boolean.TRUE);
                fragmentHomeGuestBinding.cultFitLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.p1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentHomeGuest.w(FragmentHomeGuest.this, view);
                    }
                });
                return;
            }
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_cult_fit, CultFitVideoFragment.Companion.newInstance(true)).commit();
            return;
        }
        fragmentHomeGuestBinding.cultFitLayout.setVisibility(8);
    }

    public final void x() {
        if (isAdded()) {
            if (AppUtils.isNetConnected(requireContext())) {
                FragmentHomeViewModel fragmentHomeViewModel = this.k;
                FitnessChallengeListViewModel fitnessChallengeListViewModel = null;
                if (fragmentHomeViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    fragmentHomeViewModel = null;
                }
                fragmentHomeViewModel.getFitnessChallengeVisibility().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.dashboard2.fragment.n1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentHomeGuest.y(FragmentHomeGuest.this, (Boolean) obj);
                    }
                });
                FitnessChallengeListViewModel fitnessChallengeListViewModel2 = this.l;
                if (fitnessChallengeListViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
                } else {
                    fitnessChallengeListViewModel = fitnessChallengeListViewModel2;
                }
                fitnessChallengeListViewModel.getDashboardFirstTwoChallenges();
            } else {
                FitnessChallengeRemoteConfiguration fitnessChallengeRemoteConfig = FitnessChallengeSessionManager.getInstance(getContext()).getFitnessChallengeRemoteConfig();
                if (fitnessChallengeRemoteConfig != null && fitnessChallengeRemoteConfig.getFitness_challenges() != null) {
                    u().setShowFitnessChallenge(fitnessChallengeRemoteConfig.getFitness_challenges().getVisibility());
                }
            }
            u().viewFitnessChallengeDashboardHeader.setTitle(getString(R.string.create_challenge));
        }
    }

    public final void z() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToProfileActivity();
        }
    }
}
