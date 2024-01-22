package com.coveiot.android.watchfaceui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.activities.ActivityWatchFace;
import com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter;
import com.coveiot.android.watchfaceui.databinding.FragmentWatchFaceDefaultNew2Binding;
import com.coveiot.android.watchfaceui.listener.OnClickListener;
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.android.watchfaceui.utils.ViewModelFactory;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceDefaultViewModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.utility.AppUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentWatchFaceDefaultNew extends BaseFragment implements WatchFacePagingAdapter.OnWatchFaceItemClickListener, OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentWatchFaceDefaultNew2Binding m;
    public WatchFaceDefaultViewModel n;
    public ActivityWatchFaceViewModel o;
    @Nullable
    public Job p;
    @Nullable
    public Integer q;
    @Nullable
    public WatchFacePagingAdapter r;
    public boolean s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public int[] t = {R.drawable.beat_call_plus_gt_default_1, R.drawable.beat_call_plus_gt_default_2, R.drawable.beat_call_plus_gt_default_3, R.drawable.beat_call_plus_gt_default_4};
    @NotNull
    public int[] u = {R.drawable.wave_style_call_tce_default_1, R.drawable.wave_style_call_tce_default_2, R.drawable.wave_style_call_tce_default_3, R.drawable.wave_style_call_tce_default_4};

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentWatchFaceDefaultNew newInstance() {
            return new FragmentWatchFaceDefaultNew();
        }
    }

    /* loaded from: classes8.dex */
    public static final class a extends Lambda implements Function1<Boolean, Unit> {
        public final /* synthetic */ Object $image;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Object obj) {
            super(1);
            this.$image = obj;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean item) {
            Intrinsics.checkNotNullExpressionValue(item, "item");
            if (!item.booleanValue() || FragmentWatchFaceDefaultNew.this.isVisible()) {
                return;
            }
            WatchFacePagingAdapter adapter = FragmentWatchFaceDefaultNew.this.getAdapter();
            Intrinsics.checkNotNull(adapter);
            adapter.setMSelectedPosition(-1);
            FragmentWatchFaceDefaultNew.this.setMAppliedWatchface(-1);
            WatchFacePagingAdapter adapter2 = FragmentWatchFaceDefaultNew.this.getAdapter();
            Intrinsics.checkNotNull(adapter2);
            adapter2.notifyDataSetChanged();
            Object obj = this.$image;
            if (obj != null) {
                Glide.with(((ImageView) obj).getContext()).clear((View) this.$image);
            }
        }
    }

    /* loaded from: classes8.dex */
    public static final class b extends Lambda implements Function1<Boolean, Unit> {
        public final /* synthetic */ Object $image;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Object obj) {
            super(1);
            this.$image = obj;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean item) {
            Intrinsics.checkNotNullExpressionValue(item, "item");
            if (!item.booleanValue() || FragmentWatchFaceDefaultNew.this.isVisible()) {
                return;
            }
            WatchFacePagingAdapter adapter = FragmentWatchFaceDefaultNew.this.getAdapter();
            Intrinsics.checkNotNull(adapter);
            adapter.setMSelectedPosition(-1);
            FragmentWatchFaceDefaultNew.this.setMAppliedWatchface(-1);
            WatchFacePagingAdapter adapter2 = FragmentWatchFaceDefaultNew.this.getAdapter();
            Intrinsics.checkNotNull(adapter2);
            adapter2.notifyDataSetChanged();
            Object obj = this.$image;
            if (obj != null) {
                Glide.with(((ImageView) obj).getContext()).clear((View) this.$image);
            }
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceDefaultNew$pullWatchFace$1", f = "FragmentWatchFaceDefaultNew.kt", i = {}, l = {154}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceDefaultNew$pullWatchFace$1$1", f = "FragmentWatchFaceDefaultNew.kt", i = {}, l = {155}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes8.dex */
        public static final class a extends SuspendLambda implements Function2<PagingData<WatchFaceBean>, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object L$0;
            public int label;
            public final /* synthetic */ FragmentWatchFaceDefaultNew this$0;

            @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceDefaultNew$pullWatchFace$1$1$1", f = "FragmentWatchFaceDefaultNew.kt", i = {}, l = {156}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceDefaultNew$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes8.dex */
            public static final class C0327a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ PagingData<WatchFaceBean> $it;
                public int label;
                public final /* synthetic */ FragmentWatchFaceDefaultNew this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0327a(FragmentWatchFaceDefaultNew fragmentWatchFaceDefaultNew, PagingData<WatchFaceBean> pagingData, Continuation<? super C0327a> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentWatchFaceDefaultNew;
                    this.$it = pagingData;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0327a(this.this$0, this.$it, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0327a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        WatchFacePagingAdapter adapter = this.this$0.getAdapter();
                        if (adapter != null) {
                            PagingData<WatchFaceBean> pagingData = this.$it;
                            this.label = 1;
                            if (adapter.submitData(pagingData, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentWatchFaceDefaultNew fragmentWatchFaceDefaultNew, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentWatchFaceDefaultNew;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                a aVar = new a(this.this$0, continuation);
                aVar.L$0 = obj;
                return aVar;
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull PagingData<WatchFaceBean> pagingData, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(pagingData, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    C0327a c0327a = new C0327a(this.this$0, (PagingData) this.L$0, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main, c0327a, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WatchFaceDefaultViewModel watchFaceDefaultViewModel = FragmentWatchFaceDefaultNew.this.n;
                if (watchFaceDefaultViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
                    watchFaceDefaultViewModel = null;
                }
                Flow<PagingData<WatchFaceBean>> pullWatchFaces = watchFaceDefaultViewModel.pullWatchFaces();
                if (pullWatchFaces != null) {
                    a aVar = new a(FragmentWatchFaceDefaultNew.this, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(pullWatchFaces, aVar, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentWatchFaceDefaultNew newInstance() {
        return Companion.newInstance();
    }

    public static final void p(FragmentWatchFaceDefaultNew this$0, Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WatchFacePagingAdapter watchFacePagingAdapter = this$0.r;
        if (watchFacePagingAdapter != null) {
            Intrinsics.checkNotNull(watchFacePagingAdapter);
            Integer mSelectedPosition = watchFacePagingAdapter.getMSelectedPosition();
            if (mSelectedPosition == null || mSelectedPosition.intValue() != -1 || this$0.s) {
                return;
            }
            WatchFacePagingAdapter watchFacePagingAdapter2 = this$0.r;
            Intrinsics.checkNotNull(watchFacePagingAdapter2);
            ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.o;
            ActivityWatchFaceViewModel activityWatchFaceViewModel2 = null;
            if (activityWatchFaceViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                activityWatchFaceViewModel = null;
            }
            watchFacePagingAdapter2.setMSelectedPosition(Integer.valueOf(activityWatchFaceViewModel.getDisplayedWatchFacePositionFromWatch()));
            ActivityWatchFaceViewModel activityWatchFaceViewModel3 = this$0.o;
            if (activityWatchFaceViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            } else {
                activityWatchFaceViewModel2 = activityWatchFaceViewModel3;
            }
            this$0.q = Integer.valueOf(activityWatchFaceViewModel2.getDisplayedWatchFacePositionFromWatch());
            WatchFacePagingAdapter watchFacePagingAdapter3 = this$0.r;
            Intrinsics.checkNotNull(watchFacePagingAdapter3);
            watchFacePagingAdapter3.notifyDataSetChanged();
        }
    }

    public static final void q(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void r(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void s(FragmentWatchFaceDefaultNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = (TextView) this$0.requireActivity().findViewById(R.id.tvToolTip);
        if (textView.getVisibility() == 0) {
            textView.setVisibility(8);
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

    @Nullable
    public final WatchFacePagingAdapter getAdapter() {
        return this.r;
    }

    @NotNull
    public final int[] getBeatCallPlusGujaratTitansImageArray() {
        return this.t;
    }

    @Nullable
    public final Integer getMAppliedWatchface() {
        return this.q;
    }

    @NotNull
    public final int[] getWaveStyleCallTCEImageArray() {
        return this.u;
    }

    public final void initObserver() {
        WatchFaceDefaultViewModel watchFaceDefaultViewModel = this.n;
        if (watchFaceDefaultViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
            watchFaceDefaultViewModel = null;
        }
        watchFaceDefaultViewModel.getWatchFacePositionLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.watchfaceui.fragments.f2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentWatchFaceDefaultNew.p(FragmentWatchFaceDefaultNew.this, (Integer) obj);
            }
        });
    }

    public final boolean isBackgroundWatchFace() {
        return this.s;
    }

    public final FragmentWatchFaceDefaultNew2Binding o() {
        FragmentWatchFaceDefaultNew2Binding fragmentWatchFaceDefaultNew2Binding = this.m;
        Intrinsics.checkNotNull(fragmentWatchFaceDefaultNew2Binding);
        return fragmentWatchFaceDefaultNew2Binding;
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onAppliedClicked() {
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentWatchFaceDefaultNew2Binding.inflate(inflater, viewGroup, false);
        return o().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter.OnWatchFaceItemClickListener
    public void onItemClick(@NotNull WatchFaceBean watchFaceBean) {
        View findViewById;
        Intrinsics.checkNotNullParameter(watchFaceBean, "watchFaceBean");
        ((TextView) requireActivity().findViewById(R.id.tvToolTip)).setVisibility(8);
        if (SessionManager.getInstance(requireContext()).getRoundedImage().equals(0)) {
            findViewById = requireActivity().findViewById(R.id.selectedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…>(R.id.selectedWatchFace)");
        } else {
            findViewById = requireActivity().findViewById(R.id.selectedRoundedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…selectedRoundedWatchFace)");
        }
        TextView textView = (TextView) requireActivity().findViewById(R.id.applyToWatch);
        Utils utils = Utils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (utils.isWaveBeatCallGujaratTitansFirmware(requireContext)) {
            Integer facePosition = watchFaceBean.getFacePosition();
            Intrinsics.checkNotNull(facePosition);
            setGujaratTitanWatchface(facePosition.intValue(), (ImageView) findViewById);
        } else {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (utils.isWaveStyleCallTCEFirmware(requireContext2)) {
                Integer facePosition2 = watchFaceBean.getFacePosition();
                Intrinsics.checkNotNull(facePosition2);
                setTCEWatchface(facePosition2.intValue(), (ImageView) findViewById);
            } else {
                Glide.with(requireActivity()).m30load(watchFaceBean.getPreviewImageUrl()).into((ImageView) findViewById);
            }
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.o;
        WatchFaceDefaultViewModel watchFaceDefaultViewModel = null;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.setWatchFacePushType(0);
        WatchFaceDefaultViewModel watchFaceDefaultViewModel2 = this.n;
        if (watchFaceDefaultViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
            watchFaceDefaultViewModel2 = null;
        }
        watchFaceDefaultViewModel2.setDefaultWatchFacePosition(watchFaceBean.getFacePosition());
        WatchFaceDefaultViewModel watchFaceDefaultViewModel3 = this.n;
        if (watchFaceDefaultViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
            watchFaceDefaultViewModel3 = null;
        }
        watchFaceDefaultViewModel3.setWatchFaceBean(watchFaceBean);
        String uid = watchFaceBean.getUid();
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        WatchFaceBean lastSelectedWatchFace = companion.getInstance(requireContext3).getLastSelectedWatchFace();
        if (Intrinsics.areEqual(uid, lastSelectedWatchFace != null ? lastSelectedWatchFace.getUid() : null)) {
            WatchFaceDefaultViewModel watchFaceDefaultViewModel4 = this.n;
            if (watchFaceDefaultViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
            } else {
                watchFaceDefaultViewModel = watchFaceDefaultViewModel4;
            }
            watchFaceDefaultViewModel.showSaveBtn(false);
            textView.setEnabled(false);
            return;
        }
        WatchFaceDefaultViewModel watchFaceDefaultViewModel5 = this.n;
        if (watchFaceDefaultViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
        } else {
            watchFaceDefaultViewModel = watchFaceDefaultViewModel5;
        }
        watchFaceDefaultViewModel.showSaveBtn(true);
        textView.setEnabled(true);
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onSaveClicked() {
        String modelNumber;
        WatchFaceBean mSelectedWatchFace;
        WatchFaceBean mSelectedWatchFace2;
        if (this.o == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.o;
        WatchFaceDefaultViewModel watchFaceDefaultViewModel = null;
        r2 = null;
        String str = null;
        WatchFaceDefaultViewModel watchFaceDefaultViewModel2 = null;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        if (activityWatchFaceViewModel.getWatchFacePushType() == 0) {
            if (this.n == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
            }
            WatchFaceDefaultViewModel watchFaceDefaultViewModel3 = this.n;
            if (watchFaceDefaultViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
                watchFaceDefaultViewModel3 = null;
            }
            if (watchFaceDefaultViewModel3.getDefaultWatchFacePosition() != null) {
                WatchFaceDefaultViewModel watchFaceDefaultViewModel4 = this.n;
                if (watchFaceDefaultViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
                    watchFaceDefaultViewModel4 = null;
                }
                Integer defaultWatchFacePosition = watchFaceDefaultViewModel4.getDefaultWatchFacePosition();
                if (defaultWatchFacePosition == null || defaultWatchFacePosition.intValue() != -1) {
                    if (requireActivity() instanceof ActivityWatchFace) {
                        FragmentActivity requireActivity = requireActivity();
                        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.watchfaceui.activities.ActivityWatchFace");
                        ((ActivityWatchFace) requireActivity).showProgress();
                    }
                    WatchFaceDefaultViewModel watchFaceDefaultViewModel5 = this.n;
                    if (watchFaceDefaultViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
                        watchFaceDefaultViewModel5 = null;
                    }
                    watchFaceDefaultViewModel5.changeDefaultWatchFace();
                    WatchFacePagingAdapter watchFacePagingAdapter = this.r;
                    this.q = watchFacePagingAdapter != null ? watchFacePagingAdapter.getMSelectedPosition() : null;
                    AnalyticsLog analyticsLog = new AnalyticsLog();
                    analyticsLog.setEventName(FirebaseEventParams.EventName.WATCHFASE_SAVE.getValue());
                    DeviceModelBean deviceModelBean = SessionManager.getInstance(getContext()).getDeviceModelBean();
                    if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
                        DeviceModelBean deviceModelBean2 = SessionManager.getInstance(getContext()).getDeviceModelBean();
                        modelNumber = deviceModelBean2 != null ? deviceModelBean2.getName() : null;
                    } else {
                        Utils utils = Utils.INSTANCE;
                        FragmentActivity requireActivity2 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                        modelNumber = utils.getModelNumber(requireActivity2);
                    }
                    analyticsLog.setCVPrevScreenName(modelNumber + "_features");
                    analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.APPLY_WATCHFACE.getValue());
                    HashMap<String, String> hashMap = new HashMap<>();
                    String value = FirebaseEventParams.MetaData.CV_WATCHFACE_ID.getValue();
                    WatchFacePagingAdapter watchFacePagingAdapter2 = this.r;
                    hashMap.put(value, String.valueOf((watchFacePagingAdapter2 == null || (mSelectedWatchFace2 = watchFacePagingAdapter2.getMSelectedWatchFace()) == null) ? null : mSelectedWatchFace2.getFaceId()));
                    hashMap.put(FirebaseEventParams.MetaData.CV_POSITION.getValue(), String.valueOf(this.q));
                    String value2 = FirebaseEventParams.MetaData.CV_WATCHFACE_CATEGORY.getValue();
                    WatchFacePagingAdapter watchFacePagingAdapter3 = this.r;
                    if (watchFacePagingAdapter3 != null && (mSelectedWatchFace = watchFacePagingAdapter3.getMSelectedWatchFace()) != null) {
                        str = mSelectedWatchFace.getFaceType();
                    }
                    String lowerCase = String.valueOf(str).toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                    hashMap.put(value2, lowerCase);
                    analyticsLog.setMapData(hashMap);
                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    hashMap2.put(CleverTapConstants.CustomEventProperties.WATCH_FACE_TYPE.getValue(), CleverTapConstants.CustomEventValues.DEFAULT.getValue());
                    hashMap2.put(CleverTapConstants.CustomEventProperties.UPDATE_LOCATION.getValue(), CleverTapConstants.CustomEventValues.WATCH_FACES_PAGE.getValue());
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    hashMap2.putAll(companion.getDeviceId(requireContext));
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    hashMap2.putAll(companion.getWatchDetails(requireContext2));
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.WF_UPDATED.getValue(), hashMap2);
                    return;
                }
            }
            Toast.makeText(requireActivity(), "Please choose your watch face", 1).show();
            WatchFaceDefaultViewModel watchFaceDefaultViewModel6 = this.n;
            if (watchFaceDefaultViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
            } else {
                watchFaceDefaultViewModel2 = watchFaceDefaultViewModel6;
            }
            watchFaceDefaultViewModel2.onFail();
            return;
        }
        Toast.makeText(requireActivity(), "Please choose your watch face", 1).show();
        WatchFaceDefaultViewModel watchFaceDefaultViewModel7 = this.n;
        if (watchFaceDefaultViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
        } else {
            watchFaceDefaultViewModel = watchFaceDefaultViewModel7;
        }
        watchFaceDefaultViewModel.onFail();
    }

    @Override // com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter.OnWatchFaceItemClickListener
    public void onSelectionFound(@NotNull WatchFaceBean watchFaceBean) {
        View findViewById;
        Intrinsics.checkNotNullParameter(watchFaceBean, "watchFaceBean");
        if (SessionManager.getInstance(requireContext()).getRoundedImage().equals(0)) {
            findViewById = requireActivity().findViewById(R.id.selectedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…>(R.id.selectedWatchFace)");
        } else {
            findViewById = requireActivity().findViewById(R.id.selectedRoundedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…selectedRoundedWatchFace)");
        }
        Utils utils = Utils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (utils.isWaveBeatCallGujaratTitansFirmware(requireContext)) {
            Integer facePosition = watchFaceBean.getFacePosition();
            Intrinsics.checkNotNull(facePosition);
            setGujaratTitanWatchface(facePosition.intValue(), (ImageView) findViewById);
            return;
        }
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        if (utils.isWaveStyleCallTCEFirmware(requireContext2)) {
            Integer facePosition2 = watchFaceBean.getFacePosition();
            Intrinsics.checkNotNull(facePosition2);
            setTCEWatchface(facePosition2.intValue(), (ImageView) findViewById);
            return;
        }
        Glide.with(requireActivity()).m30load(watchFaceBean.getPreviewImageUrl()).into((ImageView) findViewById);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        View findViewById;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        ViewModel viewModel = ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(WatchFaceDefaultViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            requireA…ultViewModel::class.java)");
        this.n = (WatchFaceDefaultViewModel) viewModel;
        FragmentActivity requireActivity3 = requireActivity();
        FragmentActivity requireActivity4 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
        ViewModel viewModel2 = ViewModelProviders.of(requireActivity3, new ViewModelFactory(requireActivity4)).get(ActivityWatchFaceViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(\n            requireA…aceViewModel::class.java)");
        this.o = (ActivityWatchFaceViewModel) viewModel2;
        FragmentActivity requireActivity5 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity()");
        ActivityWatchFaceViewModel activityWatchFaceViewModel = null;
        WatchFacePagingAdapter watchFacePagingAdapter = new WatchFacePagingAdapter(requireActivity5, 0, null);
        this.r = watchFacePagingAdapter;
        Intrinsics.checkNotNull(watchFacePagingAdapter);
        watchFacePagingAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        WatchFacePagingAdapter watchFacePagingAdapter2 = this.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter2);
        watchFacePagingAdapter2.setOnWatchFaceClickListener(this, null);
        o().watchFaceRecycler.setLayoutManager(new GridLayoutManager(requireActivity(), 3));
        o().watchFaceRecycler.setAdapter(this.r);
        WatchFacePagingAdapter watchFacePagingAdapter3 = this.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter3);
        ActivityWatchFaceViewModel activityWatchFaceViewModel2 = this.o;
        if (activityWatchFaceViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel2 = null;
        }
        watchFacePagingAdapter3.setMSelectedPosition(Integer.valueOf(activityWatchFaceViewModel2.getDisplayedWatchFacePositionFromWatch()));
        initObserver();
        t();
        if (SessionManager.getInstance(requireContext()).getRoundedImage().equals(0)) {
            findViewById = requireActivity().findViewById(R.id.selectedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…>(R.id.selectedWatchFace)");
        } else {
            findViewById = requireActivity().findViewById(R.id.selectedRoundedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…selectedRoundedWatchFace)");
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel3 = this.o;
        if (activityWatchFaceViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel3 = null;
        }
        LiveData<Boolean> isCloudWatchFaceSelected = activityWatchFaceViewModel3.getIsCloudWatchFaceSelected();
        if (isCloudWatchFaceSelected != null) {
            FragmentActivity requireActivity6 = requireActivity();
            final a aVar = new a(findViewById);
            isCloudWatchFaceSelected.observe(requireActivity6, new Observer() { // from class: com.coveiot.android.watchfaceui.fragments.g2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentWatchFaceDefaultNew.q(Function1.this, obj);
                }
            });
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel4 = this.o;
        if (activityWatchFaceViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
        } else {
            activityWatchFaceViewModel = activityWatchFaceViewModel4;
        }
        LiveData<Boolean> isDiyWatchFaceSelected = activityWatchFaceViewModel.getIsDiyWatchFaceSelected();
        if (isDiyWatchFaceSelected != null) {
            FragmentActivity requireActivity7 = requireActivity();
            final b bVar = new b(findViewById);
            isDiyWatchFaceSelected.observe(requireActivity7, new Observer() { // from class: com.coveiot.android.watchfaceui.fragments.h2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentWatchFaceDefaultNew.r(Function1.this, obj);
                }
            });
        }
        o().rootLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.e2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWatchFaceDefaultNew.s(FragmentWatchFaceDefaultNew.this, view2);
            }
        });
    }

    public final void setAdapter(@Nullable WatchFacePagingAdapter watchFacePagingAdapter) {
        this.r = watchFacePagingAdapter;
    }

    public final void setBackgroundWatchFace(boolean z) {
        this.s = z;
    }

    public final void setBeatCallPlusGujaratTitansImageArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.t = iArr;
    }

    public final void setGujaratTitanWatchface(int i, @NotNull ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (i == 0) {
            imageView.setImageResource(this.t[0]);
        } else if (i == 1) {
            imageView.setImageResource(this.t[1]);
        } else if (i == 2) {
            imageView.setImageResource(this.t[2]);
        } else if (i != 3) {
        } else {
            imageView.setImageResource(this.t[3]);
        }
    }

    public final void setMAppliedWatchface(@Nullable Integer num) {
        this.q = num;
    }

    public final void setTCEWatchface(int i, @NotNull ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (i == 0) {
            imageView.setImageResource(this.u[0]);
        } else if (i == 1) {
            imageView.setImageResource(this.u[1]);
        } else if (i == 2) {
            imageView.setImageResource(this.u[2]);
        } else if (i != 3) {
        } else {
            imageView.setImageResource(this.u[3]);
        }
    }

    public final void setWaveStyleCallTCEImageArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.u = iArr;
    }

    public final void t() {
        Job e;
        if (AppUtils.isNetConnected(requireActivity())) {
            Job job = this.p;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            e = kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new c(null), 2, null);
            this.p = e;
            return;
        }
        Toast.makeText(requireActivity(), getString(R.string.no_internet_connection), 0).show();
    }
}
