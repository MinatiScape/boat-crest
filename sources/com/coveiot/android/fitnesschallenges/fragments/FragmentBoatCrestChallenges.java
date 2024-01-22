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
import com.coveiot.android.fitnesschallenges.databinding.FragmentBoatCrestChallengesBinding;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeDetailsViewModel;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.JoinChallengeReq;
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
public final class FragmentBoatCrestChallenges extends BaseFragment implements FitnessChallengePagingAdapter.ChallengeClickListener, SuccessResultListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentBoatCrestChallengesBinding m;
    public FitnessChallengePagingAdapter n;
    public FitnessChallengeListViewModel o;
    public FitnessChallengeDetailsViewModel p;
    @Nullable
    public String q;
    @Nullable
    public String r;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentBoatCrestChallenges newInstance() {
            return new FragmentBoatCrestChallenges();
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
            launchActivity.putExtra(FitnessChallengeConstants.CHALLENGE_TYPE, this.$challenge.getChallengeType());
            launchActivity.putExtra(FitnessChallengeConstants.CHALLENGE_ID, this.$challenge.getChallengeId().toString());
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
            if (FragmentBoatCrestChallenges.this.isAdded()) {
                FitnessChallengePagingAdapter fitnessChallengePagingAdapter = FragmentBoatCrestChallenges.this.n;
                if (fitnessChallengePagingAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengePagingAdapter");
                    fitnessChallengePagingAdapter = null;
                }
                Lifecycle lifecycle = FragmentBoatCrestChallenges.this.getLifecycle();
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
            if (FragmentBoatCrestChallenges.this.isAdded()) {
                FragmentBoatCrestChallenges fragmentBoatCrestChallenges = FragmentBoatCrestChallenges.this;
                View root = fragmentBoatCrestChallenges.o().emptyChallengeView.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "binding.emptyChallengeView.root");
                Intrinsics.checkNotNullExpressionValue(isEmpty, "isEmpty");
                fragmentBoatCrestChallenges.visibleIf(root, isEmpty.booleanValue());
                FragmentBoatCrestChallenges.this.dismissProgress();
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
            if (FragmentBoatCrestChallenges.this.isAdded()) {
                Intrinsics.checkNotNullExpressionValue(shouldShowProgress, "shouldShowProgress");
                if (shouldShowProgress.booleanValue()) {
                    FragmentBoatCrestChallenges.this.showProgress(true);
                } else {
                    FragmentBoatCrestChallenges.this.dismissProgress();
                }
            }
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentBoatCrestChallenges newInstance() {
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

    public static final void t(FragmentBoatCrestChallenges this$0, View view) {
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
        this.q = challenge.getChallengeId().toString();
        this.r = challenge.getType();
        FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = null;
        if (z) {
            showProgress(true);
            FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel2 = this.p;
            if (fitnessChallengeDetailsViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("detailsViewModel");
            } else {
                fitnessChallengeDetailsViewModel = fitnessChallengeDetailsViewModel2;
            }
            fitnessChallengeDetailsViewModel.joinFitnessChallenge(new JoinChallengeReq(challenge.getChallengeId(), challenge.getType()));
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
        FitnessChallengeListViewModel fitnessChallengeListViewModel = this.o;
        FitnessChallengeListViewModel fitnessChallengeListViewModel2 = null;
        if (fitnessChallengeListViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeListViewModel = null;
        }
        MutableLiveData<PagingData<BuddiesChallengeRes.Item>> fitnessChallengeList = fitnessChallengeListViewModel.getFitnessChallengeList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final b bVar = new b();
        fitnessChallengeList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.b
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentBoatCrestChallenges.q(Function1.this, obj);
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
        showEmptyLayout.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.d
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentBoatCrestChallenges.r(Function1.this, obj);
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
        shouldShowProgress.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.c
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentBoatCrestChallenges.s(Function1.this, obj);
            }
        });
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.FitnessChallengePagingAdapter.ChallengeClickListener
    public void isDataLoaded(boolean z) {
        if (isAdded() && z) {
            dismissProgress();
        }
    }

    public final FragmentBoatCrestChallengesBinding o() {
        FragmentBoatCrestChallengesBinding fragmentBoatCrestChallengesBinding = this.m;
        if (fragmentBoatCrestChallengesBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentBoatCrestChallengesBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentBoatCrestChallengesBinding inflate = FragmentBoatCrestChallengesBinding.inflate(inflater, viewGroup, false);
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

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        if (str != null) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.fitnesschallenges.FitnessChallengesHome");
            ((FitnessChallengesHome) activity).showCommonMessageDialogInHome(false, str, null, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        p();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        String str;
        dismissProgress();
        String str2 = this.q;
        if (str2 == null || (str = this.r) == null) {
            return;
        }
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.fitnesschallenges.FitnessChallengesHome");
        String string = requireContext().getString(R.string.woah_challenge_joined);
        Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…ng.woah_challenge_joined)");
        ((FitnessChallengesHome) activity).showCommonMessageDialogInHome(true, string, str2, str);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.o = new FitnessChallengeListViewModel(requireContext);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = new FitnessChallengeDetailsViewModel(requireContext2);
        this.p = fitnessChallengeDetailsViewModel;
        fitnessChallengeDetailsViewModel.setMListener(this);
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        this.n = new FitnessChallengePagingAdapter(requireContext3, false, this);
        o().rvChallenges.setLayoutManager(new LinearLayoutManager(requireActivity(), 1, false));
        o().rvChallenges.setHasFixedSize(true);
        RecyclerView recyclerView = o().rvChallenges;
        FitnessChallengePagingAdapter fitnessChallengePagingAdapter = this.n;
        if (fitnessChallengePagingAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengePagingAdapter");
            fitnessChallengePagingAdapter = null;
        }
        recyclerView.setAdapter(fitnessChallengePagingAdapter.withLoadStateFooter(new LoaderAdapter()));
        o().emptyChallengeView.btnCreateChallengeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentBoatCrestChallenges.t(FragmentBoatCrestChallenges.this, view2);
            }
        });
    }

    public final void p() {
        if (isAdded()) {
            if (AppUtils.isNetConnected(requireContext())) {
                FitnessChallengeListViewModel fitnessChallengeListViewModel = this.o;
                if (fitnessChallengeListViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fitnessChallengeListViewModel = null;
                }
                fitnessChallengeListViewModel.getGlobalFitnessChallengesPagingData();
                showProgress(false);
            } else {
                showNoInternetDialog();
                dismissProgress();
            }
        }
        initObservers();
    }
}
