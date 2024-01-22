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
import com.coveiot.android.fitnesschallenges.databinding.FragmentMyCreatedBinding;
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
public final class FragmentMyCreated extends BaseFragment implements FitnessChallengePagingAdapter.ChallengeClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentMyCreatedBinding m;
    public FitnessChallengeListViewModel n;
    public FitnessChallengePagingAdapter o;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentMyCreated newInstance() {
            return new FragmentMyCreated();
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
            if (FragmentMyCreated.this.isAdded()) {
                FitnessChallengePagingAdapter fitnessChallengePagingAdapter = FragmentMyCreated.this.o;
                if (fitnessChallengePagingAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengePagingAdapter");
                    fitnessChallengePagingAdapter = null;
                }
                Lifecycle lifecycle = FragmentMyCreated.this.getLifecycle();
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
            if (FragmentMyCreated.this.isAdded()) {
                FragmentMyCreated.this.o().emptyChallengeView.tvNoChallengeVieTitle.setText(FragmentMyCreated.this.getString(R.string.no_created_challenges));
                FragmentMyCreated.this.o().emptyChallengeView.tvNoChallengeViewInfo.setText(FragmentMyCreated.this.getString(R.string.create_challenge_invite_buddies_and_achieve_goals));
                FragmentMyCreated.this.o().emptyChallengeView.btnCreateChallenge.setText(FragmentMyCreated.this.getString(R.string.create_challenge));
                FragmentMyCreated fragmentMyCreated = FragmentMyCreated.this;
                View root = fragmentMyCreated.o().emptyChallengeView.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "binding.emptyChallengeView.root");
                Intrinsics.checkNotNullExpressionValue(isEmpty, "isEmpty");
                fragmentMyCreated.visibleIf(root, isEmpty.booleanValue());
                FragmentMyCreated.this.dismissProgress();
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
            if (FragmentMyCreated.this.isAdded()) {
                Intrinsics.checkNotNullExpressionValue(shouldShowProgress, "shouldShowProgress");
                if (shouldShowProgress.booleanValue()) {
                    FragmentMyCreated.this.showProgress(true);
                } else {
                    FragmentMyCreated.this.dismissProgress();
                }
            }
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentMyCreated newInstance() {
        return Companion.newInstance();
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

    public static final void t(FragmentMyCreated this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.fitnesschallenges.FitnessChallengesHome");
        ((FitnessChallengesHome) activity).loadCreateChallenge();
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

    public final void initObservers() {
        FitnessChallengeListViewModel fitnessChallengeListViewModel = this.n;
        FitnessChallengeListViewModel fitnessChallengeListViewModel2 = null;
        if (fitnessChallengeListViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeListViewModel = null;
        }
        MutableLiveData<PagingData<BuddiesChallengeRes.Item>> myCreatedFitnessChallengeList = fitnessChallengeListViewModel.getMyCreatedFitnessChallengeList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final b bVar = new b();
        myCreatedFitnessChallengeList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.y0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentMyCreated.q(Function1.this, obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel3 = this.n;
        if (fitnessChallengeListViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeListViewModel3 = null;
        }
        MutableLiveData<Boolean> showEmptyLayout = fitnessChallengeListViewModel3.getShowEmptyLayout();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final c cVar = new c();
        showEmptyLayout.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.z0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentMyCreated.r(Function1.this, obj);
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
        final d dVar = new d();
        shouldShowProgress.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.x0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentMyCreated.s(Function1.this, obj);
            }
        });
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.FitnessChallengePagingAdapter.ChallengeClickListener
    public void isDataLoaded(boolean z) {
        View root = o().emptyChallengeView.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.emptyChallengeView.root");
        goneIF(root, z);
        if (z) {
            dismissProgress();
        }
    }

    public final FragmentMyCreatedBinding o() {
        FragmentMyCreatedBinding fragmentMyCreatedBinding = this.m;
        if (fragmentMyCreatedBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentMyCreatedBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentMyCreatedBinding inflate = FragmentMyCreatedBinding.inflate(inflater, viewGroup, false);
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
        this.n = new FitnessChallengeListViewModel(requireContext);
        o().infoDetails.tvInfoText.setText(getString(R.string.challenges_you_have_created));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.o = new FitnessChallengePagingAdapter(requireContext2, true, this);
        o().rvChallenges.setLayoutManager(new LinearLayoutManager(requireActivity(), 1, false));
        o().rvChallenges.setHasFixedSize(true);
        RecyclerView recyclerView = o().rvChallenges;
        FitnessChallengePagingAdapter fitnessChallengePagingAdapter = this.o;
        if (fitnessChallengePagingAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengePagingAdapter");
            fitnessChallengePagingAdapter = null;
        }
        recyclerView.setAdapter(fitnessChallengePagingAdapter.withLoadStateFooter(new LoaderAdapter()));
        o().emptyChallengeView.btnCreateChallengeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.w0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentMyCreated.t(FragmentMyCreated.this, view2);
            }
        });
    }

    public final void p() {
        if (isAdded()) {
            if (AppUtils.isNetConnected(requireContext())) {
                showProgress(false);
                FitnessChallengeListViewModel fitnessChallengeListViewModel = this.n;
                if (fitnessChallengeListViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fitnessChallengeListViewModel = null;
                }
                fitnessChallengeListViewModel.getMyCreatedFitnessChallengesPagingData();
            } else {
                showNoInternetDialog();
                dismissProgress();
            }
        }
        initObservers();
    }
}
