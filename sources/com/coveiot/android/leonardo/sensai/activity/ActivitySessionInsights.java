package com.coveiot.android.leonardo.sensai.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivitySessionInsightsBinding;
import com.coveiot.android.boat.databinding.ComparePopupBinding;
import com.coveiot.android.leonardo.sensai.adapter.SessionInsightsListAdapter;
import com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDataViewModel;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.repository.RepositoryUtils;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySessionInsights extends BaseActivity implements SessionInsightsListAdapter.OnItemClickListener {
    public ActivitySessionInsightsBinding p;
    public SessionInsightsListAdapter q;
    @Nullable
    public ArrayList<String> r;
    public SensAISummaryDataViewModel s;
    public int v;
    public int w;
    public int x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<? extends SensAIActivitySummary> t = new ArrayList();
    public final int u = 301;
    @NotNull
    public final Handler y = new Handler();

    @DebugMetadata(c = "com.coveiot.android.leonardo.sensai.activity.ActivitySessionInsights$onCreate$1", f = "ActivitySessionInsights.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                String lastSensAIServerSync = PreferenceManager.getLastSensAIServerSync(ActivitySessionInsights.this);
                if (!(lastSensAIServerSync == null || lastSensAIServerSync.length() == 0) && RepositoryUtils.findDateDifference(PreferenceManager.getLastSensAIServerSync(ActivitySessionInsights.this)) <= 180) {
                    i = RepositoryUtils.findDateDifference(PreferenceManager.getLastSensAIServerSync(ActivitySessionInsights.this));
                }
                Calendar fromData = Calendar.getInstance();
                fromData.add(6, -i);
                Calendar toData = Calendar.getInstance();
                toData.add(6, 0);
                SensAISummaryDataViewModel sensAISummaryDataViewModel = ActivitySessionInsights.this.s;
                if (sensAISummaryDataViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryData");
                    sensAISummaryDataViewModel = null;
                }
                Intrinsics.checkNotNullExpressionValue(fromData, "fromData");
                Intrinsics.checkNotNullExpressionValue(toData, "toData");
                String connectedDeviceMacAddress = new com.coveiot.android.activitymodes.preference.PreferenceManager(ActivitySessionInsights.this).getConnectedDeviceMacAddress();
                Intrinsics.checkNotNull(connectedDeviceMacAddress);
                sensAISummaryDataViewModel.getSessionsListFromServer(fromData, toData, connectedDeviceMacAddress);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(ActivitySessionInsights this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.w();
    }

    public static final void C(ActivitySessionInsights this$0, List summaryList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(summaryList, "summaryList");
        this$0.t = summaryList;
        ActivitySessionInsightsBinding activitySessionInsightsBinding = null;
        SessionInsightsListAdapter sessionInsightsListAdapter = null;
        if (!summaryList.isEmpty()) {
            ActivitySessionInsightsBinding activitySessionInsightsBinding2 = this$0.p;
            if (activitySessionInsightsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding2 = null;
            }
            activitySessionInsightsBinding2.clSession.setVisibility(0);
            ActivitySessionInsightsBinding activitySessionInsightsBinding3 = this$0.p;
            if (activitySessionInsightsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding3 = null;
            }
            activitySessionInsightsBinding3.rvSessionInsightsList.setVisibility(0);
            ActivitySessionInsightsBinding activitySessionInsightsBinding4 = this$0.p;
            if (activitySessionInsightsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding4 = null;
            }
            activitySessionInsightsBinding4.btnCompare.setVisibility(0);
            ActivitySessionInsightsBinding activitySessionInsightsBinding5 = this$0.p;
            if (activitySessionInsightsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding5 = null;
            }
            activitySessionInsightsBinding5.tvNoSessions.setVisibility(8);
            SessionInsightsListAdapter sessionInsightsListAdapter2 = this$0.q;
            if (sessionInsightsListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
            } else {
                sessionInsightsListAdapter = sessionInsightsListAdapter2;
            }
            List<? extends SensAIActivitySummary> list = this$0.t;
            Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.MutableList<com.coveiot.covedb.sensai.entity.SensAIActivitySummary>");
            sessionInsightsListAdapter.setData(TypeIntrinsics.asMutableList(list));
            return;
        }
        ActivitySessionInsightsBinding activitySessionInsightsBinding6 = this$0.p;
        if (activitySessionInsightsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsBinding6 = null;
        }
        activitySessionInsightsBinding6.rvSessionInsightsList.setVisibility(8);
        ActivitySessionInsightsBinding activitySessionInsightsBinding7 = this$0.p;
        if (activitySessionInsightsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsBinding7 = null;
        }
        activitySessionInsightsBinding7.btnCompare.setVisibility(8);
        ActivitySessionInsightsBinding activitySessionInsightsBinding8 = this$0.p;
        if (activitySessionInsightsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySessionInsightsBinding = activitySessionInsightsBinding8;
        }
        activitySessionInsightsBinding.tvNoSessions.setVisibility(0);
    }

    public static final void E(Ref.ObjectRef dialog) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((Dialog) dialog.element).dismiss();
    }

    public static final void x(ActivitySessionInsights this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void y(ActivitySessionInsights this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, ActivitySensAIFilter.class);
        ArrayList<String> arrayList = this$0.r;
        if (arrayList != null) {
            intent.putExtra("extra_selected_filter_options", arrayList);
        }
        this$0.startActivityForResult(intent, this$0.u);
    }

    public static final void z(ActivitySessionInsights this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ArrayList<String> arrayList = this$0.r;
        if (arrayList != null) {
            if (arrayList != null) {
                arrayList.clear();
            }
            ActivitySessionInsightsBinding activitySessionInsightsBinding = this$0.p;
            ActivitySessionInsightsBinding activitySessionInsightsBinding2 = null;
            if (activitySessionInsightsBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding = null;
            }
            activitySessionInsightsBinding.tvFiltersApplied.setText("0 Filters Applied");
            ActivitySessionInsightsBinding activitySessionInsightsBinding3 = this$0.p;
            if (activitySessionInsightsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySessionInsightsBinding2 = activitySessionInsightsBinding3;
            }
            activitySessionInsightsBinding2.tvFiltersApplied.setTextColor(this$0.getColor(R.color.secondary_text_color));
            this$0.B();
        }
    }

    public final void B() {
        String connectedDeviceMacAddress = new com.coveiot.android.activitymodes.preference.PreferenceManager(this).getConnectedDeviceMacAddress();
        if (connectedDeviceMacAddress != null) {
            SensAISummaryDataViewModel sensAISummaryDataViewModel = this.s;
            if (sensAISummaryDataViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryData");
                sensAISummaryDataViewModel = null;
            }
            sensAISummaryDataViewModel.getSensAIActivitySummaryDataFromDB(connectedDeviceMacAddress, this.r, 1).observe(this, new Observer() { // from class: com.coveiot.android.leonardo.sensai.activity.c0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ActivitySessionInsights.C(ActivitySessionInsights.this, (List) obj);
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, android.app.Dialog] */
    public final void D(String str) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new Dialog(this, R.style.GenericDialog);
        ComparePopupBinding inflate = ComparePopupBinding.inflate(LayoutInflater.from(this));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f…ActivitySessionInsights))");
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
        this.y.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.sensai.activity.d0
            @Override // java.lang.Runnable
            public final void run() {
                ActivitySessionInsights.E(Ref.ObjectRef.this);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final int getBattingCompareCount() {
        return this.w;
    }

    public final int getBowlingCompareCount() {
        return this.x;
    }

    public final int getCompareCount() {
        return this.v;
    }

    @NotNull
    public final List<SensAIActivitySummary> getEntityActivitySummaryList() {
        return this.t;
    }

    public final int getFILTERS_REQUEST_CODE() {
        return this.u;
    }

    @Nullable
    public final ArrayList<String> getSelectedFilters() {
        return this.r;
    }

    public final void initToolbar() {
        ActivitySessionInsightsBinding activitySessionInsightsBinding = this.p;
        ActivitySessionInsightsBinding activitySessionInsightsBinding2 = null;
        if (activitySessionInsightsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsBinding = null;
        }
        TextView textView = (TextView) activitySessionInsightsBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivitySessionInsightsBinding activitySessionInsightsBinding3 = this.p;
        if (activitySessionInsightsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySessionInsightsBinding2 = activitySessionInsightsBinding3;
        }
        textView.setText(getString(R.string.session_insights));
        ((TextView) activitySessionInsightsBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySessionInsights.x(ActivitySessionInsights.this, view);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.u && i2 == -1) {
            ActivitySessionInsightsBinding activitySessionInsightsBinding = null;
            if (intent != null) {
                if (intent.hasExtra("extra_selected_filter_options")) {
                    Bundle extras = intent.getExtras();
                    if ((extras != null ? extras.getSerializable("extra_selected_filter_options") : null) != null) {
                        Bundle extras2 = intent.getExtras();
                        Serializable serializable = extras2 != null ? extras2.getSerializable("extra_selected_filter_options") : null;
                        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>");
                        ArrayList<String> arrayList = (ArrayList) serializable;
                        this.r = arrayList;
                        if (arrayList != null && arrayList.size() == 1) {
                            ActivitySessionInsightsBinding activitySessionInsightsBinding2 = this.p;
                            if (activitySessionInsightsBinding2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activitySessionInsightsBinding2 = null;
                            }
                            TextView textView = activitySessionInsightsBinding2.tvFiltersApplied;
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Locale locale = Locale.ENGLISH;
                            String string = getString(R.string.filter_applied);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.filter_applied)");
                            Object[] objArr = new Object[1];
                            ArrayList<String> arrayList2 = this.r;
                            objArr[0] = arrayList2 != null ? Integer.valueOf(arrayList2.size()) : null;
                            String format = String.format(locale, string, Arrays.copyOf(objArr, 1));
                            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                            textView.setText(format);
                        } else {
                            ActivitySessionInsightsBinding activitySessionInsightsBinding3 = this.p;
                            if (activitySessionInsightsBinding3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activitySessionInsightsBinding3 = null;
                            }
                            TextView textView2 = activitySessionInsightsBinding3.tvFiltersApplied;
                            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                            Locale locale2 = Locale.ENGLISH;
                            String string2 = getString(R.string.filters_applied);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.filters_applied)");
                            Object[] objArr2 = new Object[1];
                            ArrayList<String> arrayList3 = this.r;
                            objArr2[0] = arrayList3 != null ? Integer.valueOf(arrayList3.size()) : null;
                            String format2 = String.format(locale2, string2, Arrays.copyOf(objArr2, 1));
                            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                            textView2.setText(format2);
                        }
                        ArrayList<String> arrayList4 = this.r;
                        Integer valueOf = arrayList4 != null ? Integer.valueOf(arrayList4.size()) : null;
                        Intrinsics.checkNotNull(valueOf);
                        if (valueOf.intValue() > 0) {
                            ActivitySessionInsightsBinding activitySessionInsightsBinding4 = this.p;
                            if (activitySessionInsightsBinding4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activitySessionInsightsBinding = activitySessionInsightsBinding4;
                            }
                            activitySessionInsightsBinding.tvFiltersApplied.setTextColor(getColor(R.color.text_color_primary));
                        } else {
                            ActivitySessionInsightsBinding activitySessionInsightsBinding5 = this.p;
                            if (activitySessionInsightsBinding5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activitySessionInsightsBinding = activitySessionInsightsBinding5;
                            }
                            activitySessionInsightsBinding.tvFiltersApplied.setTextColor(getColor(R.color.secondary_text_color));
                        }
                        B();
                        return;
                    }
                }
                ActivitySessionInsightsBinding activitySessionInsightsBinding6 = this.p;
                if (activitySessionInsightsBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsBinding6 = null;
                }
                TextView textView3 = activitySessionInsightsBinding6.tvFiltersApplied;
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                Locale locale3 = Locale.ENGLISH;
                String string3 = getString(R.string.filters_applied);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.filters_applied)");
                String format3 = String.format(locale3, string3, Arrays.copyOf(new Object[]{BleConst.GetDeviceTime}, 1));
                Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                textView3.setText(format3);
                ActivitySessionInsightsBinding activitySessionInsightsBinding7 = this.p;
                if (activitySessionInsightsBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activitySessionInsightsBinding = activitySessionInsightsBinding7;
                }
                activitySessionInsightsBinding.tvFiltersApplied.setTextColor(getColor(R.color.secondary_text_color));
                return;
            }
            ActivitySessionInsightsBinding activitySessionInsightsBinding8 = this.p;
            if (activitySessionInsightsBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding8 = null;
            }
            activitySessionInsightsBinding8.tvFiltersApplied.setText("0 Filters Applied");
            ActivitySessionInsightsBinding activitySessionInsightsBinding9 = this.p;
            if (activitySessionInsightsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySessionInsightsBinding = activitySessionInsightsBinding9;
            }
            activitySessionInsightsBinding.tvFiltersApplied.setTextColor(getColor(R.color.secondary_text_color));
        }
    }

    @Override // com.coveiot.android.leonardo.sensai.adapter.SessionInsightsListAdapter.OnItemClickListener
    public void onBattingAddToCompareClicked(@Nullable SensAIActivitySummary sensAIActivitySummary, boolean z) {
        ActivitySessionInsightsBinding activitySessionInsightsBinding = null;
        SessionInsightsListAdapter sessionInsightsListAdapter = null;
        SessionInsightsListAdapter sessionInsightsListAdapter2 = null;
        if (z) {
            if (this.x != 0) {
                ActivitySessionInsightsBinding activitySessionInsightsBinding2 = this.p;
                if (activitySessionInsightsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsBinding2 = null;
                }
                if (activitySessionInsightsBinding2.rvSessionInsightsList.isComputingLayout()) {
                    return;
                }
                if (sensAIActivitySummary != null) {
                    sensAIActivitySummary.setAddToCompare(false);
                }
                SessionInsightsListAdapter sessionInsightsListAdapter3 = this.q;
                if (sessionInsightsListAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
                } else {
                    sessionInsightsListAdapter = sessionInsightsListAdapter3;
                }
                sessionInsightsListAdapter.notifyDataSetChanged();
                String string = getString(R.string.compare_batting_bowling_error);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.compare_batting_bowling_error)");
                D(string);
                return;
            } else if (this.v == 2) {
                ActivitySessionInsightsBinding activitySessionInsightsBinding3 = this.p;
                if (activitySessionInsightsBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsBinding3 = null;
                }
                if (activitySessionInsightsBinding3.rvSessionInsightsList.isComputingLayout()) {
                    return;
                }
                if (sensAIActivitySummary != null) {
                    sensAIActivitySummary.setAddToCompare(false);
                }
                SessionInsightsListAdapter sessionInsightsListAdapter4 = this.q;
                if (sessionInsightsListAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
                } else {
                    sessionInsightsListAdapter2 = sessionInsightsListAdapter4;
                }
                sessionInsightsListAdapter2.notifyDataSetChanged();
                String string2 = getString(R.string.compare_max_error);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.compare_max_error)");
                D(string2);
                return;
            }
        }
        if (z) {
            this.v++;
            this.w++;
        } else {
            this.v--;
            this.w--;
        }
        ActivitySessionInsightsBinding activitySessionInsightsBinding4 = this.p;
        if (activitySessionInsightsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySessionInsightsBinding = activitySessionInsightsBinding4;
        }
        activitySessionInsightsBinding.btnCompare.setEnabled(this.v == 2);
    }

    @Override // com.coveiot.android.leonardo.sensai.adapter.SessionInsightsListAdapter.OnItemClickListener
    public void onBowlingAddToCompareClicked(@Nullable SensAIActivitySummary sensAIActivitySummary, boolean z) {
        ActivitySessionInsightsBinding activitySessionInsightsBinding = null;
        SessionInsightsListAdapter sessionInsightsListAdapter = null;
        SessionInsightsListAdapter sessionInsightsListAdapter2 = null;
        if (z) {
            if (this.w != 0) {
                ActivitySessionInsightsBinding activitySessionInsightsBinding2 = this.p;
                if (activitySessionInsightsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsBinding2 = null;
                }
                if (activitySessionInsightsBinding2.rvSessionInsightsList.isComputingLayout()) {
                    return;
                }
                if (sensAIActivitySummary != null) {
                    sensAIActivitySummary.setAddToCompare(false);
                }
                SessionInsightsListAdapter sessionInsightsListAdapter3 = this.q;
                if (sessionInsightsListAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
                } else {
                    sessionInsightsListAdapter = sessionInsightsListAdapter3;
                }
                sessionInsightsListAdapter.notifyDataSetChanged();
                String string = getString(R.string.compare_batting_bowling_error);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.compare_batting_bowling_error)");
                D(string);
                return;
            } else if (this.v == 2) {
                ActivitySessionInsightsBinding activitySessionInsightsBinding3 = this.p;
                if (activitySessionInsightsBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsBinding3 = null;
                }
                if (activitySessionInsightsBinding3.rvSessionInsightsList.isComputingLayout()) {
                    return;
                }
                if (sensAIActivitySummary != null) {
                    sensAIActivitySummary.setAddToCompare(false);
                }
                SessionInsightsListAdapter sessionInsightsListAdapter4 = this.q;
                if (sessionInsightsListAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
                } else {
                    sessionInsightsListAdapter2 = sessionInsightsListAdapter4;
                }
                sessionInsightsListAdapter2.notifyDataSetChanged();
                String string2 = getString(R.string.compare_max_error);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.compare_max_error)");
                D(string2);
                return;
            }
        }
        if (z) {
            this.v++;
            this.x++;
        } else {
            this.v--;
            this.x--;
        }
        ActivitySessionInsightsBinding activitySessionInsightsBinding4 = this.p;
        if (activitySessionInsightsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySessionInsightsBinding = activitySessionInsightsBinding4;
        }
        activitySessionInsightsBinding.btnCompare.setEnabled(this.v == 2);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivitySessionInsightsBinding inflate = ActivitySessionInsightsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivitySessionInsightsBinding activitySessionInsightsBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
        this.s = (SensAISummaryDataViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(SensAISummaryDataViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ActivitySessionInsightsBinding activitySessionInsightsBinding2 = this.p;
        if (activitySessionInsightsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsBinding2 = null;
        }
        activitySessionInsightsBinding2.rvSessionInsightsList.setLayoutManager(linearLayoutManager);
        this.q = new SessionInsightsListAdapter(this, this);
        ActivitySessionInsightsBinding activitySessionInsightsBinding3 = this.p;
        if (activitySessionInsightsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsBinding3 = null;
        }
        RecyclerView recyclerView = activitySessionInsightsBinding3.rvSessionInsightsList;
        SessionInsightsListAdapter sessionInsightsListAdapter = this.q;
        if (sessionInsightsListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
            sessionInsightsListAdapter = null;
        }
        recyclerView.setAdapter(sessionInsightsListAdapter);
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
        B();
        List<? extends SensAIActivitySummary> list = this.t;
        if (list == null || list.isEmpty()) {
            ActivitySessionInsightsBinding activitySessionInsightsBinding4 = this.p;
            if (activitySessionInsightsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding4 = null;
            }
            activitySessionInsightsBinding4.clSession.setVisibility(8);
            ActivitySessionInsightsBinding activitySessionInsightsBinding5 = this.p;
            if (activitySessionInsightsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding5 = null;
            }
            activitySessionInsightsBinding5.btnCompare.setVisibility(8);
            ActivitySessionInsightsBinding activitySessionInsightsBinding6 = this.p;
            if (activitySessionInsightsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding6 = null;
            }
            activitySessionInsightsBinding6.tvNoSessions.setVisibility(0);
        } else {
            ActivitySessionInsightsBinding activitySessionInsightsBinding7 = this.p;
            if (activitySessionInsightsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding7 = null;
            }
            activitySessionInsightsBinding7.clSession.setVisibility(0);
            ActivitySessionInsightsBinding activitySessionInsightsBinding8 = this.p;
            if (activitySessionInsightsBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding8 = null;
            }
            activitySessionInsightsBinding8.btnCompare.setVisibility(0);
            ActivitySessionInsightsBinding activitySessionInsightsBinding9 = this.p;
            if (activitySessionInsightsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsBinding9 = null;
            }
            activitySessionInsightsBinding9.tvNoSessions.setVisibility(8);
        }
        ActivitySessionInsightsBinding activitySessionInsightsBinding10 = this.p;
        if (activitySessionInsightsBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsBinding10 = null;
        }
        activitySessionInsightsBinding10.btnFilter.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySessionInsights.y(ActivitySessionInsights.this, view);
            }
        });
        ActivitySessionInsightsBinding activitySessionInsightsBinding11 = this.p;
        if (activitySessionInsightsBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsBinding11 = null;
        }
        activitySessionInsightsBinding11.btnReset.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySessionInsights.z(ActivitySessionInsights.this, view);
            }
        });
        ActivitySessionInsightsBinding activitySessionInsightsBinding12 = this.p;
        if (activitySessionInsightsBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySessionInsightsBinding = activitySessionInsightsBinding12;
        }
        activitySessionInsightsBinding.btnCompare.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySessionInsights.A(ActivitySessionInsights.this, view);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.sensai.adapter.SessionInsightsListAdapter.OnItemClickListener
    public void onItemClicked(@Nullable String str, int i) {
        if (str != null) {
            AppNavigator.Companion.navigateToSensAISessionInsightsDetailsScreen(this, str, i);
        }
    }

    public final void setBattingCompareCount(int i) {
        this.w = i;
    }

    public final void setBowlingCompareCount(int i) {
        this.x = i;
    }

    public final void setCompareCount(int i) {
        this.v = i;
    }

    public final void setEntityActivitySummaryList(@NotNull List<? extends SensAIActivitySummary> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.t = list;
    }

    public final void setSelectedFilters(@Nullable ArrayList<String> arrayList) {
        this.r = arrayList;
    }

    public final void w() {
        ActivitySessionInsightsBinding activitySessionInsightsBinding = this.p;
        if (activitySessionInsightsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsBinding = null;
        }
        activitySessionInsightsBinding.btnCompare.setEnabled(false);
        this.v = 0;
        this.w = 0;
        this.x = 0;
        for (SensAIActivitySummary sensAIActivitySummary : this.t) {
            if (sensAIActivitySummary.isAddToCompare()) {
                sensAIActivitySummary.setAddToCompare(false);
                SessionInsightsListAdapter sessionInsightsListAdapter = this.q;
                if (sessionInsightsListAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sessionInsightsListAdapter");
                    sessionInsightsListAdapter = null;
                }
                sessionInsightsListAdapter.notifyDataSetChanged();
            }
        }
    }
}
