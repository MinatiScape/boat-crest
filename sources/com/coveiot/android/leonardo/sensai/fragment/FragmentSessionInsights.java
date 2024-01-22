package com.coveiot.android.leonardo.sensai.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ComparePopupBinding;
import com.coveiot.android.boat.databinding.FragmentSessionInsightsBinding;
import com.coveiot.android.leonardo.sensai.adapter.SensAIFilterListAdapter;
import com.coveiot.android.leonardo.sensai.adapter.SensAISortByListAdapter;
import com.coveiot.android.leonardo.sensai.adapter.SessionInsightsListAdapter;
import com.coveiot.android.leonardo.sensai.model.SensAICompareData;
import com.coveiot.android.leonardo.sensai.model.SensAIFilterModel;
import com.coveiot.android.leonardo.sensai.model.SensAISortByModel;
import com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDataViewModel;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.repository.RepositoryUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentSessionInsights extends BaseFragment implements SessionInsightsListAdapter.OnItemClickListener, SensAIFilterListAdapter.OnItemClickListener, SensAISortByListAdapter.OnItemClickListener {
    @Nullable
    public FragmentSessionInsightsBinding m;
    public SessionInsightsListAdapter o;
    public SensAISummaryDataViewModel p;
    public SensAIFilterListAdapter t;
    public SensAISortByListAdapter v;
    @Nullable
    public ArrayList<String> w;
    public int x;
    public int y;
    public int z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public boolean n = true;
    @NotNull
    public List<? extends SensAIActivitySummary> q = new ArrayList();
    @NotNull
    public ArrayList<SensAIFilterModel> r = new ArrayList<>();
    @NotNull
    public ArrayList<SensAIFilterModel> s = new ArrayList<>();
    @NotNull
    public ArrayList<SensAISortByModel> u = new ArrayList<>();
    @NotNull
    public final Handler A = new Handler();
    public int B = 1;

    @DebugMetadata(c = "com.coveiot.android.leonardo.sensai.fragment.FragmentSessionInsights$onViewCreated$3", f = "FragmentSessionInsights.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                int i = 180;
                String lastSensAIServerSync = PreferenceManager.getLastSensAIServerSync(FragmentSessionInsights.this.requireActivity());
                if (!(lastSensAIServerSync == null || lastSensAIServerSync.length() == 0) && RepositoryUtils.findDateDifference(PreferenceManager.getLastSensAIServerSync(FragmentSessionInsights.this.requireActivity())) <= 180) {
                    i = RepositoryUtils.findDateDifference(PreferenceManager.getLastSensAIServerSync(FragmentSessionInsights.this.requireActivity()));
                }
                Calendar fromData = Calendar.getInstance();
                fromData.add(6, -i);
                Calendar toData = Calendar.getInstance();
                toData.add(6, 0);
                SensAISummaryDataViewModel sensAISummaryDataViewModel = FragmentSessionInsights.this.p;
                if (sensAISummaryDataViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryData");
                    sensAISummaryDataViewModel = null;
                }
                Intrinsics.checkNotNullExpressionValue(fromData, "fromData");
                Intrinsics.checkNotNullExpressionValue(toData, "toData");
                FragmentActivity requireActivity = FragmentSessionInsights.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                String connectedDeviceMacAddress = new com.coveiot.android.activitymodes.preference.PreferenceManager(requireActivity).getConnectedDeviceMacAddress();
                Intrinsics.checkNotNull(connectedDeviceMacAddress);
                sensAISummaryDataViewModel.getSessionsListFromServer(fromData, toData, connectedDeviceMacAddress);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void B(Ref.ObjectRef dialog) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((Dialog) dialog.element).dismiss();
    }

    public static final void t(FragmentSessionInsights this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View root = this$0.s().sortByDialog.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.sortByDialog.root");
        this$0.visible(root);
    }

    public static final void u(FragmentSessionInsights this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View root = this$0.s().sortByDialog.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.sortByDialog.root");
        this$0.gone(root);
    }

    public static final void v(FragmentSessionInsights this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.w = null;
        this$0.n = true;
        this$0.x = 0;
        this$0.y = 0;
        this$0.z = 0;
        this$0.s().tvBatting.setTextColor(this$0.requireContext().getColor(R.color.main_text_color));
        this$0.s().tvBowling.setTextColor(this$0.requireContext().getColor(R.color.color_b3b3b3));
        this$0.s().tvBatting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sens_ai_selected_batting, 0, 0, 0);
        this$0.s().tvBowling.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sens_ai_unselected_bowling, 0, 0, 0);
        this$0.y();
    }

    public static final void w(FragmentSessionInsights this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.w = null;
        this$0.n = false;
        this$0.x = 0;
        this$0.y = 0;
        this$0.z = 0;
        this$0.s().tvBatting.setTextColor(this$0.requireContext().getColor(R.color.color_b3b3b3));
        this$0.s().tvBowling.setTextColor(this$0.requireContext().getColor(R.color.main_text_color));
        this$0.s().tvBatting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sens_ai_unselected_batting, 0, 0, 0);
        this$0.s().tvBowling.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sens_ai_selected_bowling, 0, 0, 0);
        this$0.y();
    }

    public static final void x(FragmentSessionInsights this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r();
    }

    public static final void z(FragmentSessionInsights this$0, List summaryList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(summaryList, "summaryList");
        this$0.q = summaryList;
        if (!summaryList.isEmpty()) {
            this$0.s().clSession.setVisibility(0);
            this$0.s().rvSessionInsightsList.setVisibility(0);
            this$0.s().btnCompare.setVisibility(0);
            this$0.s().tvNoSessions.setVisibility(8);
            this$0.s().tvSortBy.setEnabled(true);
            this$0.s().rvFilterList.setEnabled(true);
            SessionInsightsListAdapter sessionInsightsListAdapter = this$0.o;
            if (sessionInsightsListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
                sessionInsightsListAdapter = null;
            }
            List<? extends SensAIActivitySummary> list = this$0.q;
            Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.MutableList<com.coveiot.covedb.sensai.entity.SensAIActivitySummary>");
            sessionInsightsListAdapter.setData(TypeIntrinsics.asMutableList(list));
            return;
        }
        this$0.s().rvSessionInsightsList.setVisibility(8);
        this$0.s().btnCompare.setVisibility(8);
        this$0.s().tvNoSessions.setVisibility(0);
        this$0.s().tvSortBy.setEnabled(false);
        this$0.s().rvFilterList.setEnabled(false);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, android.app.Dialog] */
    public final void A(String str) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new Dialog(requireActivity(), R.style.GenericDialog);
        ComparePopupBinding inflate = ComparePopupBinding.inflate(LayoutInflater.from(requireActivity()));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(requireActivity()))");
        ((Dialog) objectRef.element).setContentView(inflate.getRoot());
        ((Dialog) objectRef.element).setCancelable(true);
        ((Dialog) objectRef.element).setCanceledOnTouchOutside(true);
        Window window = ((Dialog) objectRef.element).getWindow();
        Intrinsics.checkNotNull(window);
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Intrinsics.checkNotNullExpressionValue(attributes, "window.attributes");
            attributes.width = -1;
            attributes.height = -2;
            attributes.gravity = 80;
            window.setAttributes(attributes);
        }
        inflate.tvCompare.setText(str);
        ((Dialog) objectRef.element).show();
        this.A.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.sensai.fragment.o0
            @Override // java.lang.Runnable
            public final void run() {
                FragmentSessionInsights.B(Ref.ObjectRef.this);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
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

    public final int getBattingCompareCount() {
        return this.y;
    }

    @NotNull
    public final ArrayList<SensAIFilterModel> getBattingFilterList() {
        return this.r;
    }

    public final int getBowlingCompareCount() {
        return this.z;
    }

    @NotNull
    public final ArrayList<SensAIFilterModel> getBowlingFilterList() {
        return this.s;
    }

    public final int getCompareCount() {
        return this.x;
    }

    @NotNull
    public final List<SensAIActivitySummary> getEntityActivitySummaryList() {
        return this.q;
    }

    @Nullable
    public final ArrayList<String> getSelectedFilters() {
        return this.w;
    }

    @NotNull
    public final ArrayList<SensAISortByModel> getSortByList() {
        return this.u;
    }

    @Override // com.coveiot.android.leonardo.sensai.adapter.SessionInsightsListAdapter.OnItemClickListener
    public void onBattingAddToCompareClicked(@Nullable SensAIActivitySummary sensAIActivitySummary, boolean z) {
        if (z) {
            SessionInsightsListAdapter sessionInsightsListAdapter = null;
            if (this.z != 0) {
                if (s().rvSessionInsightsList.isComputingLayout()) {
                    return;
                }
                if (sensAIActivitySummary != null) {
                    sensAIActivitySummary.setAddToCompare(false);
                }
                SessionInsightsListAdapter sessionInsightsListAdapter2 = this.o;
                if (sessionInsightsListAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
                } else {
                    sessionInsightsListAdapter = sessionInsightsListAdapter2;
                }
                sessionInsightsListAdapter.notifyDataSetChanged();
                String string = getString(R.string.compare_batting_bowling_error);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.compare_batting_bowling_error)");
                A(string);
                return;
            } else if (this.x == 2) {
                if (s().rvSessionInsightsList.isComputingLayout()) {
                    return;
                }
                if (sensAIActivitySummary != null) {
                    sensAIActivitySummary.setAddToCompare(false);
                }
                SessionInsightsListAdapter sessionInsightsListAdapter3 = this.o;
                if (sessionInsightsListAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
                } else {
                    sessionInsightsListAdapter = sessionInsightsListAdapter3;
                }
                sessionInsightsListAdapter.notifyDataSetChanged();
                String string2 = getString(R.string.compare_max_error);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.compare_max_error)");
                A(string2);
                return;
            }
        }
        if (z) {
            this.x++;
            this.y++;
        } else {
            this.x--;
            this.y--;
        }
        s().btnCompare.setEnabled(this.x == 2);
    }

    @Override // com.coveiot.android.leonardo.sensai.adapter.SessionInsightsListAdapter.OnItemClickListener
    public void onBowlingAddToCompareClicked(@Nullable SensAIActivitySummary sensAIActivitySummary, boolean z) {
        if (z) {
            SessionInsightsListAdapter sessionInsightsListAdapter = null;
            if (this.y != 0) {
                if (s().rvSessionInsightsList.isComputingLayout()) {
                    return;
                }
                if (sensAIActivitySummary != null) {
                    sensAIActivitySummary.setAddToCompare(false);
                }
                SessionInsightsListAdapter sessionInsightsListAdapter2 = this.o;
                if (sessionInsightsListAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
                } else {
                    sessionInsightsListAdapter = sessionInsightsListAdapter2;
                }
                sessionInsightsListAdapter.notifyDataSetChanged();
                String string = getString(R.string.compare_batting_bowling_error);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.compare_batting_bowling_error)");
                A(string);
                return;
            } else if (this.x == 2) {
                if (s().rvSessionInsightsList.isComputingLayout()) {
                    return;
                }
                if (sensAIActivitySummary != null) {
                    sensAIActivitySummary.setAddToCompare(false);
                }
                SessionInsightsListAdapter sessionInsightsListAdapter3 = this.o;
                if (sessionInsightsListAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
                } else {
                    sessionInsightsListAdapter = sessionInsightsListAdapter3;
                }
                sessionInsightsListAdapter.notifyDataSetChanged();
                String string2 = getString(R.string.compare_max_error);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.compare_max_error)");
                A(string2);
                return;
            }
        }
        if (z) {
            this.x++;
            this.z++;
        } else {
            this.x--;
            this.z--;
        }
        s().btnCompare.setEnabled(this.x == 2);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentSessionInsightsBinding.inflate(inflater, viewGroup, false);
        return s().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.sensai.adapter.SessionInsightsListAdapter.OnItemClickListener
    public void onItemClicked(@Nullable String str, int i) {
        if (str != null) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            companion.navigateToSensAISessionInsightsDetailsScreen(requireActivity, str, i);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.p = (SensAISummaryDataViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity)).get(SensAISummaryDataViewModel.class);
        this.r.add(new SensAIFilterModel(requireContext().getString(R.string.right_hand), false));
        this.r.add(new SensAIFilterModel(requireContext().getString(R.string.left_hand), false));
        this.s.add(new SensAIFilterModel(requireContext().getString(R.string.right_hand), false));
        this.s.add(new SensAIFilterModel(requireContext().getString(R.string.left_hand), false));
        this.s.add(new SensAIFilterModel(requireContext().getString(R.string.spin), false));
        this.s.add(new SensAIFilterModel(requireContext().getString(R.string.medium_pace), false));
        this.s.add(new SensAIFilterModel(requireContext().getString(R.string.fast), false));
        s().rvSessionInsightsList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        this.o = new SessionInsightsListAdapter(requireActivity2, this);
        RecyclerView recyclerView = s().rvSessionInsightsList;
        SessionInsightsListAdapter sessionInsightsListAdapter = this.o;
        if (sessionInsightsListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
            sessionInsightsListAdapter = null;
        }
        recyclerView.setAdapter(sessionInsightsListAdapter);
        s().rvFilterList.setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
        this.t = new SensAIFilterListAdapter(requireActivity3, this);
        RecyclerView recyclerView2 = s().rvFilterList;
        SensAIFilterListAdapter sensAIFilterListAdapter = this.t;
        if (sensAIFilterListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterListAdapter");
            sensAIFilterListAdapter = null;
        }
        recyclerView2.setAdapter(sensAIFilterListAdapter);
        s().sortByDialog.rvSortByList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        FragmentActivity requireActivity4 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
        this.v = new SensAISortByListAdapter(requireActivity4, this);
        RecyclerView recyclerView3 = s().sortByDialog.rvSortByList;
        SensAISortByListAdapter sensAISortByListAdapter = this.v;
        if (sensAISortByListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortByListAdapter");
            sensAISortByListAdapter = null;
        }
        recyclerView3.setAdapter(sensAISortByListAdapter);
        this.u.add(new SensAISortByModel(requireContext().getString(R.string.recent_date), true));
        this.u.add(new SensAISortByModel(requireContext().getString(R.string.longest_session), false));
        this.u.add(new SensAISortByModel(requireContext().getString(R.string.goals_achieved), false));
        SensAISortByListAdapter sensAISortByListAdapter2 = this.v;
        if (sensAISortByListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortByListAdapter");
            sensAISortByListAdapter2 = null;
        }
        sensAISortByListAdapter2.setData(this.u);
        s().tvSortBy.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.m0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSessionInsights.t(FragmentSessionInsights.this, view2);
            }
        });
        s().sortByDialog.tvSortBy.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.k0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSessionInsights.u(FragmentSessionInsights.this, view2);
            }
        });
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
        y();
        List<? extends SensAIActivitySummary> list = this.q;
        if (list == null || list.isEmpty()) {
            s().clSession.setVisibility(8);
            s().btnCompare.setVisibility(8);
            s().tvNoSessions.setVisibility(0);
            s().tvSortBy.setEnabled(false);
            s().rvFilterList.setEnabled(false);
        } else {
            s().clSession.setVisibility(0);
            s().btnCompare.setVisibility(0);
            s().tvNoSessions.setVisibility(8);
            s().tvSortBy.setEnabled(true);
            s().rvFilterList.setEnabled(true);
        }
        s().tvBatting.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSessionInsights.v(FragmentSessionInsights.this, view2);
            }
        });
        s().tvBowling.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSessionInsights.w(FragmentSessionInsights.this, view2);
            }
        });
        s().btnCompare.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSessionInsights.x(FragmentSessionInsights.this, view2);
            }
        });
    }

    public final void r() {
        AppNavigator.Companion companion = AppNavigator.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        SensAISummaryDataViewModel sensAISummaryDataViewModel = this.p;
        if (sensAISummaryDataViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryData");
            sensAISummaryDataViewModel = null;
        }
        ArrayList<SensAICompareData> compareDataList = sensAISummaryDataViewModel.getCompareDataList(this.q);
        Intrinsics.checkNotNull(compareDataList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.leonardo.sensai.model.SensAICompareData>");
        SensAISummaryDataViewModel sensAISummaryDataViewModel2 = this.p;
        if (sensAISummaryDataViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryData");
            sensAISummaryDataViewModel2 = null;
        }
        companion.navigateToSensAICompareScreen(requireActivity, compareDataList, sensAISummaryDataViewModel2.getCompareTitleList(this.q));
        s().btnCompare.setEnabled(false);
        this.x = 0;
        this.y = 0;
        this.z = 0;
        for (SensAIActivitySummary sensAIActivitySummary : this.q) {
            if (sensAIActivitySummary.isAddToCompare()) {
                sensAIActivitySummary.setAddToCompare(false);
                SessionInsightsListAdapter sessionInsightsListAdapter = this.o;
                if (sessionInsightsListAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
                    sessionInsightsListAdapter = null;
                }
                sessionInsightsListAdapter.notifyDataSetChanged();
            }
        }
    }

    public final FragmentSessionInsightsBinding s() {
        FragmentSessionInsightsBinding fragmentSessionInsightsBinding = this.m;
        Intrinsics.checkNotNull(fragmentSessionInsightsBinding);
        return fragmentSessionInsightsBinding;
    }

    public final void setBattingCompareCount(int i) {
        this.y = i;
    }

    public final void setBattingFilterList(@NotNull ArrayList<SensAIFilterModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.r = arrayList;
    }

    public final void setBowlingCompareCount(int i) {
        this.z = i;
    }

    public final void setBowlingFilterList(@NotNull ArrayList<SensAIFilterModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.s = arrayList;
    }

    public final void setCompareCount(int i) {
        this.x = i;
    }

    public final void setEntityActivitySummaryList(@NotNull List<? extends SensAIActivitySummary> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.q = list;
    }

    public final void setSelectedFilters(@Nullable ArrayList<String> arrayList) {
        this.w = arrayList;
    }

    public final void setSortByList(@NotNull ArrayList<SensAISortByModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.u = arrayList;
    }

    public final void y() {
        ArrayList<String> arrayList;
        ArrayList<String> arrayList2;
        if (this.w == null) {
            this.w = new ArrayList<>();
        }
        SensAISummaryDataViewModel sensAISummaryDataViewModel = null;
        boolean z = true;
        if (this.n) {
            ArrayList<String> arrayList3 = this.w;
            if (arrayList3 != null) {
                arrayList3.add("Batting");
            }
            ArrayList<String> arrayList4 = this.w;
            if (arrayList4 == null || !arrayList4.contains("Bowling")) {
                z = false;
            }
            if (z && (arrayList2 = this.w) != null) {
                arrayList2.remove("Bowling");
            }
            SensAIFilterListAdapter sensAIFilterListAdapter = this.t;
            if (sensAIFilterListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("filterListAdapter");
                sensAIFilterListAdapter = null;
            }
            sensAIFilterListAdapter.setData(this.r);
        } else {
            ArrayList<String> arrayList5 = this.w;
            if (arrayList5 != null) {
                arrayList5.add("Bowling");
            }
            ArrayList<String> arrayList6 = this.w;
            if (arrayList6 == null || !arrayList6.contains("Batting")) {
                z = false;
            }
            if (z && (arrayList = this.w) != null) {
                arrayList.remove("Batting");
            }
            SensAIFilterListAdapter sensAIFilterListAdapter2 = this.t;
            if (sensAIFilterListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("filterListAdapter");
                sensAIFilterListAdapter2 = null;
            }
            sensAIFilterListAdapter2.setData(this.s);
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String connectedDeviceMacAddress = new com.coveiot.android.activitymodes.preference.PreferenceManager(requireActivity).getConnectedDeviceMacAddress();
        if (connectedDeviceMacAddress != null) {
            SensAISummaryDataViewModel sensAISummaryDataViewModel2 = this.p;
            if (sensAISummaryDataViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryData");
            } else {
                sensAISummaryDataViewModel = sensAISummaryDataViewModel2;
            }
            sensAISummaryDataViewModel.getSensAIActivitySummaryDataFromDB(connectedDeviceMacAddress, this.w, this.B).observe(requireActivity(), new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.n0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentSessionInsights.z(FragmentSessionInsights.this, (List) obj);
                }
            });
        }
    }

    @Override // com.coveiot.android.leonardo.sensai.adapter.SensAIFilterListAdapter.OnItemClickListener
    public void onItemClicked(@NotNull SensAIFilterModel filterItem) {
        Intrinsics.checkNotNullParameter(filterItem, "filterItem");
        if (filterItem.isSelected()) {
            ArrayList<String> arrayList = this.w;
            if (arrayList != null) {
                arrayList.add(filterItem.getName());
            }
            y();
            return;
        }
        ArrayList<String> arrayList2 = this.w;
        if (arrayList2 != null) {
            arrayList2.remove(filterItem.getName());
        }
        y();
    }

    @Override // com.coveiot.android.leonardo.sensai.adapter.SensAISortByListAdapter.OnItemClickListener
    public void onItemClicked(@NotNull SensAISortByModel sortByModel) {
        Intrinsics.checkNotNullParameter(sortByModel, "sortByModel");
        if (sortByModel.isSelected() && sortByModel.getName().equals(requireContext().getString(R.string.recent_date))) {
            this.B = 1;
        } else if (sortByModel.isSelected() && sortByModel.getName().equals(requireContext().getString(R.string.longest_session))) {
            this.B = 2;
        } else if (sortByModel.isSelected() && sortByModel.getName().equals(requireContext().getString(R.string.goals_achieved))) {
            this.B = 3;
        }
        y();
        View root = s().sortByDialog.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.sortByDialog.root");
        gone(root);
    }
}
