package com.coveiot.android.fitnesschallenges.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.coveiot.android.fitnesschallenges.ActivityRemoveParticipants;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.adpter.LoaderAdapter;
import com.coveiot.android.fitnesschallenges.adpter.ViewAllParticipantsDetailsPagingAdapter;
import com.coveiot.android.fitnesschallenges.databinding.FragmentParticipantsListBinding;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.RemoveParticipantsReq;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentOtherParticipants extends BaseFragment implements ViewAllParticipantsDetailsPagingAdapter.ChallengeClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public FragmentParticipantsListBinding m;
    public FitnessChallengeListViewModel n;
    @Nullable
    public ViewAllParticipantsDetailsPagingAdapter o;
    @Nullable
    public BuddiesChallengeDetail p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public boolean q = true;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentOtherParticipants newInstance(@NotNull BuddiesChallengeDetail buddiesChallengeDetail, boolean z) {
            Intrinsics.checkNotNullParameter(buddiesChallengeDetail, "buddiesChallengeDetail");
            FragmentOtherParticipants fragmentOtherParticipants = new FragmentOtherParticipants();
            fragmentOtherParticipants.setBuddiesChallengeDetail(buddiesChallengeDetail);
            fragmentOtherParticipants.setViewAllParticipants(z);
            return fragmentOtherParticipants;
        }
    }

    /* loaded from: classes2.dex */
    public static final class a extends Lambda implements Function1<PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails> pagingData) {
            invoke2(pagingData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails> pagingData) {
            ViewAllParticipantsDetailsPagingAdapter viewAllParticipantsDetailsPagingAdapter;
            if (!FragmentOtherParticipants.this.isAdded() || (viewAllParticipantsDetailsPagingAdapter = FragmentOtherParticipants.this.o) == null) {
                return;
            }
            Lifecycle lifecycle = FragmentOtherParticipants.this.getLifecycle();
            Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
            Intrinsics.checkNotNullExpressionValue(pagingData, "pagingData");
            viewAllParticipantsDetailsPagingAdapter.submitData(lifecycle, pagingData);
        }
    }

    /* loaded from: classes2.dex */
    public static final class b extends Lambda implements Function1<Boolean, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean isEmpty) {
            if (FragmentOtherParticipants.this.isAdded()) {
                FragmentOtherParticipants fragmentOtherParticipants = FragmentOtherParticipants.this;
                View root = fragmentOtherParticipants.n().emptyChallengeView.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "binding.emptyChallengeView.root");
                Intrinsics.checkNotNullExpressionValue(isEmpty, "isEmpty");
                fragmentOtherParticipants.visibleIf(root, isEmpty.booleanValue());
                FragmentOtherParticipants.this.n().emptyChallengeView.tvNoChallengeVieTitle.setText(FragmentOtherParticipants.this.getString(R.string.no_other_participants));
                FragmentOtherParticipants fragmentOtherParticipants2 = FragmentOtherParticipants.this;
                TextView textView = fragmentOtherParticipants2.n().emptyChallengeView.tvNoChallengeViewInfo;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.emptyChallengeView.tvNoChallengeViewInfo");
                fragmentOtherParticipants2.inVisible(textView);
                FragmentOtherParticipants fragmentOtherParticipants3 = FragmentOtherParticipants.this;
                ConstraintLayout constraintLayout = fragmentOtherParticipants3.n().emptyChallengeView.btnCreateChallengeLayout;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.emptyChallengeVi….btnCreateChallengeLayout");
                fragmentOtherParticipants3.gone(constraintLayout);
                FragmentOtherParticipants.this.dismissProgress();
            }
        }
    }

    /* loaded from: classes2.dex */
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
        public final void invoke2(Boolean shouldShowProgress) {
            if (FragmentOtherParticipants.this.isAdded()) {
                Intrinsics.checkNotNullExpressionValue(shouldShowProgress, "shouldShowProgress");
                if (shouldShowProgress.booleanValue()) {
                    FragmentOtherParticipants.this.showProgress(true);
                } else {
                    FragmentOtherParticipants.this.dismissProgress();
                }
            }
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentOtherParticipants newInstance(@NotNull BuddiesChallengeDetail buddiesChallengeDetail, boolean z) {
        return Companion.newInstance(buddiesChallengeDetail, z);
    }

    public static final void o(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void p(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void q(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
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

    @Nullable
    public final BuddiesChallengeDetail getBuddiesChallengeDetail() {
        return this.p;
    }

    public final void initObservers() {
        FitnessChallengeListViewModel fitnessChallengeListViewModel = this.n;
        FitnessChallengeListViewModel fitnessChallengeListViewModel2 = null;
        if (fitnessChallengeListViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeListViewModel = null;
        }
        MutableLiveData<PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>> leaderboardRankingFitnessChallengeList = fitnessChallengeListViewModel.getLeaderboardRankingFitnessChallengeList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final a aVar = new a();
        leaderboardRankingFitnessChallengeList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.b1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentOtherParticipants.o(Function1.this, obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel3 = this.n;
        if (fitnessChallengeListViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeListViewModel3 = null;
        }
        MutableLiveData<Boolean> showEmptyLayoutParticipants = fitnessChallengeListViewModel3.getShowEmptyLayoutParticipants();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final b bVar = new b();
        showEmptyLayoutParticipants.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.a1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentOtherParticipants.p(Function1.this, obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel4 = this.n;
        if (fitnessChallengeListViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fitnessChallengeListViewModel2 = fitnessChallengeListViewModel4;
        }
        MutableLiveData<Boolean> shouldShowProgress = fitnessChallengeListViewModel2.getShouldShowProgress();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        final c cVar = new c();
        shouldShowProgress.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.c1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentOtherParticipants.q(Function1.this, obj);
            }
        });
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.ViewAllParticipantsDetailsPagingAdapter.ChallengeClickListener
    public void isDataLoaded(boolean z) {
        if (isAdded()) {
            View root = n().emptyChallengeView.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.emptyChallengeView.root");
            goneIF(root, z);
            if (z) {
                dismissProgress();
            }
        }
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.ViewAllParticipantsDetailsPagingAdapter.ChallengeClickListener
    public void isItemSelected(@NotNull GetAllParticipantsFitnessChallengeRes.ParticipantsDetails item, boolean z) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (this.q) {
            return;
        }
        if (z) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.fitnesschallenges.ActivityRemoveParticipants");
            ((ActivityRemoveParticipants) activity).getRemoveParticipantsList().add(new RemoveParticipantsReq.Participants(item.getName(), item.getUserId()));
        } else {
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.fitnesschallenges.ActivityRemoveParticipants");
            ((ActivityRemoveParticipants) activity2).getRemoveParticipantsList().remove(new RemoveParticipantsReq.Participants(item.getName(), item.getUserId()));
        }
        FragmentActivity activity3 = getActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.coveiot.android.fitnesschallenges.ActivityRemoveParticipants");
        ((ActivityRemoveParticipants) activity3).enableRemoveParticipantButton();
    }

    public final boolean isViewAllParticipants() {
        return this.q;
    }

    public final FragmentParticipantsListBinding n() {
        FragmentParticipantsListBinding fragmentParticipantsListBinding = this.m;
        if (fragmentParticipantsListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentParticipantsListBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentParticipantsListBinding inflate = FragmentParticipantsListBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.m = inflate;
        View root = n().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (isAdded()) {
            if (AppUtils.isNetConnected(requireContext())) {
                if (this.p != null) {
                    showProgress(false);
                    FitnessChallengeListViewModel fitnessChallengeListViewModel = this.n;
                    if (fitnessChallengeListViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fitnessChallengeListViewModel = null;
                    }
                    BuddiesChallengeDetail buddiesChallengeDetail = this.p;
                    Object challengeId = buddiesChallengeDetail != null ? buddiesChallengeDetail.getChallengeId() : null;
                    Intrinsics.checkNotNull(challengeId);
                    BuddiesChallengeDetail buddiesChallengeDetail2 = this.p;
                    String challengeType = buddiesChallengeDetail2 != null ? buddiesChallengeDetail2.getChallengeType() : null;
                    Intrinsics.checkNotNull(challengeType);
                    fitnessChallengeListViewModel.getFitnessChallengesLeaderboardRankingPagingData(challengeId, challengeType, FitnessChallengeConstants.OTHER_PARTICIPANTS);
                }
            } else {
                showNoInternetDialog();
                dismissProgress();
            }
        }
        initObservers();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.n = new FitnessChallengeListViewModel(requireContext);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.o = new ViewAllParticipantsDetailsPagingAdapter(requireContext2, this.q, this);
        n().rvParticipantList.setLayoutManager(new LinearLayoutManager(requireActivity(), 1, false));
        n().rvParticipantList.setHasFixedSize(true);
        RecyclerView recyclerView = n().rvParticipantList;
        ViewAllParticipantsDetailsPagingAdapter viewAllParticipantsDetailsPagingAdapter = this.o;
        Intrinsics.checkNotNull(viewAllParticipantsDetailsPagingAdapter);
        recyclerView.setAdapter(viewAllParticipantsDetailsPagingAdapter.withLoadStateFooter(new LoaderAdapter()));
    }

    public final void setBuddiesChallengeDetail(@Nullable BuddiesChallengeDetail buddiesChallengeDetail) {
        this.p = buddiesChallengeDetail;
    }

    public final void setViewAllParticipants(boolean z) {
        this.q = z;
    }
}
