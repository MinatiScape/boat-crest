package com.coveiot.android.fitnesschallenges.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnesschallenges.FitnessChallengeDetails;
import com.coveiot.android.fitnesschallenges.FitnessChallengesHome;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.adpter.FitnessChallengePagingAdapter;
import com.coveiot.android.fitnesschallenges.adpter.LoaderAdapter;
import com.coveiot.android.fitnesschallenges.databinding.FragmentJoinedBinding;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
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
public final class FragmentJoined extends BaseFragment implements FitnessChallengePagingAdapter.ChallengeClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentJoinedBinding m;
    public FitnessChallengePagingAdapter n;
    public FitnessChallengeListViewModel o;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentJoined newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            return new FragmentJoined();
        }
    }

    /* loaded from: classes2.dex */
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
            launchActivity.putExtra(FitnessChallengeConstants.CHALLENGE_TYPE, this.$challenge.getChallengeType());
        }
    }

    /* loaded from: classes2.dex */
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
            if (FragmentJoined.this.isAdded()) {
                FitnessChallengePagingAdapter fitnessChallengePagingAdapter = FragmentJoined.this.n;
                if (fitnessChallengePagingAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengePagingAdapter");
                    fitnessChallengePagingAdapter = null;
                }
                Lifecycle lifecycle = FragmentJoined.this.getLifecycle();
                Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
                Intrinsics.checkNotNullExpressionValue(pagingData, "pagingData");
                fitnessChallengePagingAdapter.submitData(lifecycle, pagingData);
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
        public final void invoke2(Boolean isEmpty) {
            if (FragmentJoined.this.isAdded()) {
                FragmentJoined.this.o().emptyChallengeView.tvNoChallengeVieTitle.setText(FragmentJoined.this.getString(R.string.no_joined_challenges));
                FragmentJoined.this.o().emptyChallengeView.tvNoChallengeViewInfo.setText(FragmentJoined.this.getString(R.string.join_challenge_and_achieve_goals));
                FragmentJoined.this.o().emptyChallengeView.btnCreateChallenge.setText(FragmentJoined.this.getString(R.string.join_challenge));
                FragmentJoined fragmentJoined = FragmentJoined.this;
                View root = fragmentJoined.o().emptyChallengeView.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "binding.emptyChallengeView.root");
                Intrinsics.checkNotNullExpressionValue(isEmpty, "isEmpty");
                fragmentJoined.visibleIf(root, isEmpty.booleanValue());
                FragmentJoined.this.dismissProgress();
            }
        }
    }

    /* loaded from: classes2.dex */
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
            if (FragmentJoined.this.isAdded()) {
                Intrinsics.checkNotNullExpressionValue(shouldShowProgress, "shouldShowProgress");
                if (shouldShowProgress.booleanValue()) {
                    FragmentJoined.this.showProgress(true);
                } else {
                    FragmentJoined.this.dismissProgress();
                }
            }
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentJoined newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
    }

    public static final void q(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void r(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void s(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void t(FragmentJoined this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.fitnesschallenges.FitnessChallengesHome");
        ((FitnessChallengesHome) activity).setViewpagerItem(0, 0);
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
        if (isAdded()) {
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
    }

    public final void initObservers() {
        FitnessChallengeListViewModel fitnessChallengeListViewModel = this.o;
        FitnessChallengeListViewModel fitnessChallengeListViewModel2 = null;
        if (fitnessChallengeListViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeListViewModel = null;
        }
        MutableLiveData<PagingData<BuddiesChallengeRes.Item>> myJoinedFitnessChallengeList = fitnessChallengeListViewModel.getMyJoinedFitnessChallengeList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final b bVar = new b();
        myJoinedFitnessChallengeList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.v0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentJoined.q(Function1.this, obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel3 = this.o;
        if (fitnessChallengeListViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeListViewModel3 = null;
        }
        MutableLiveData<Boolean> showEmptyLayout = fitnessChallengeListViewModel3.getShowEmptyLayout();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final c cVar = new c();
        showEmptyLayout.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.u0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentJoined.r(Function1.this, obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel4 = this.o;
        if (fitnessChallengeListViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fitnessChallengeListViewModel2 = fitnessChallengeListViewModel4;
        }
        MutableLiveData<Boolean> shouldShowProgress = fitnessChallengeListViewModel2.getShouldShowProgress();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        final d dVar = new d();
        shouldShowProgress.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.t0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentJoined.s(Function1.this, obj);
            }
        });
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.FitnessChallengePagingAdapter.ChallengeClickListener
    public void isDataLoaded(boolean z) {
        if (isAdded() && z) {
            dismissProgress();
        }
    }

    public final FragmentJoinedBinding o() {
        FragmentJoinedBinding fragmentJoinedBinding = this.m;
        if (fragmentJoinedBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentJoinedBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentJoinedBinding inflate = FragmentJoinedBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.m = inflate;
        View root = o().getRoot();
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
        p();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.o = new FitnessChallengeListViewModel(requireContext);
        o().infoDetails.tvInfoText.setText(getString(R.string.challenges_you_have_joined));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.n = new FitnessChallengePagingAdapter(requireContext2, true, this);
        o().rvChallenges.setLayoutManager(new LinearLayoutManager(requireActivity(), 1, false));
        o().rvChallenges.setHasFixedSize(true);
        RecyclerView recyclerView = o().rvChallenges;
        FitnessChallengePagingAdapter fitnessChallengePagingAdapter = this.n;
        if (fitnessChallengePagingAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengePagingAdapter");
            fitnessChallengePagingAdapter = null;
        }
        recyclerView.setAdapter(fitnessChallengePagingAdapter.withLoadStateFooter(new LoaderAdapter()));
        o().emptyChallengeView.btnCreateChallengeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.s0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentJoined.t(FragmentJoined.this, view2);
            }
        });
    }

    public final void p() {
        if (isAdded()) {
            if (AppUtils.isNetConnected(requireContext())) {
                showProgress(true);
                FitnessChallengeListViewModel fitnessChallengeListViewModel = this.o;
                if (fitnessChallengeListViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fitnessChallengeListViewModel = null;
                }
                fitnessChallengeListViewModel.getMyJoinedFitnessChallengesPagingData();
            } else {
                showNoInternetDialog();
                dismissProgress();
            }
        }
        initObservers();
    }
}
