package com.coveiot.android.watchfaceui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.CombinedLoadStates;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleAndMessageWatchFace;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfacecore.utils.Constants;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter;
import com.coveiot.android.watchfaceui.databinding.FragmentMyDesignsBinding;
import com.coveiot.android.watchfaceui.listener.OnClickListener;
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
import com.coveiot.android.watchfaceui.utils.AppNavigator;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.android.watchfaceui.utils.ViewModelFactory;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
import kotlin.jvm.internal.Ref;
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
public final class FragmentMyDesigns extends BaseFragment implements WatchFacePagingAdapter.OnWatchFaceItemClickListener, OnClickListener, WatchFacePagingAdapter.OnCheckBoxListener, WatchFacePagingAdapter.OnInitiateLongPress {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public boolean A;
    public ActivityWatchFaceViewModel n;
    public WatchFaceDiyViewModel o;
    @Nullable
    public WatchFaceBean p;
    @Nullable
    public Job q;
    @Nullable
    public WatchFacePagingAdapter r;
    @Nullable
    public WatchFaceBean s;
    @Nullable
    public FragmentMyDesignsBinding t;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle u;
    @Nullable
    public BottomSheetDialogImageTitleAndMessageWatchFace w;
    public boolean x;
    public boolean y;
    public boolean z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentMyDesigns";
    @NotNull
    public ArrayList<WatchFaceBean> v = new ArrayList<>();

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentMyDesigns newInstance() {
            return new FragmentMyDesigns();
        }
    }

    /* loaded from: classes8.dex */
    public static final class a extends Lambda implements Function1<CombinedLoadStates, Unit> {
        public static final a INSTANCE = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CombinedLoadStates combinedLoadStates) {
            invoke2(combinedLoadStates);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull CombinedLoadStates it) {
            Intrinsics.checkNotNullParameter(it, "it");
        }
    }

    /* loaded from: classes8.dex */
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
        public final void invoke2(Boolean item) {
            Intrinsics.checkNotNullExpressionValue(item, "item");
            if (!item.booleanValue() || FragmentMyDesigns.this.isVisible()) {
                return;
            }
            FragmentMyDesigns.this.p = null;
            WatchFacePagingAdapter adapter = FragmentMyDesigns.this.getAdapter();
            Intrinsics.checkNotNull(adapter);
            adapter.setMSelectedWatchFace(null);
            WatchFacePagingAdapter adapter2 = FragmentMyDesigns.this.getAdapter();
            Intrinsics.checkNotNull(adapter2);
            adapter2.notifyDataSetChanged();
        }
    }

    /* loaded from: classes8.dex */
    public static final class c extends Lambda implements Function1<CombinedLoadStates, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CombinedLoadStates combinedLoadStates) {
            invoke2(combinedLoadStates);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull CombinedLoadStates it) {
            Intrinsics.checkNotNullParameter(it, "it");
            if (FragmentMyDesigns.this.isAdded()) {
                if (FragmentMyDesigns.this.getAdapter() != null) {
                    WatchFacePagingAdapter adapter = FragmentMyDesigns.this.getAdapter();
                    Intrinsics.checkNotNull(adapter);
                    if (adapter.getItemCount() > 0) {
                        if (!SessionManager.getInstance(FragmentMyDesigns.this.requireContext()).isDiyWatchFaceFTUShown()) {
                            WatchFacePagingAdapter adapter2 = FragmentMyDesigns.this.getAdapter();
                            Intrinsics.checkNotNull(adapter2);
                            adapter2.setAlpha(true);
                            FragmentMyDesigns fragmentMyDesigns = FragmentMyDesigns.this;
                            TextView textView = fragmentMyDesigns.z().tvToolTipFragment;
                            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvToolTipFragment");
                            fragmentMyDesigns.visible(textView);
                            FragmentMyDesigns fragmentMyDesigns2 = FragmentMyDesigns.this;
                            ConstraintLayout constraintLayout = fragmentMyDesigns2.z().infoLayout;
                            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.infoLayout");
                            fragmentMyDesigns2.gone(constraintLayout);
                            FragmentMyDesigns fragmentMyDesigns3 = FragmentMyDesigns.this;
                            View findViewById = fragmentMyDesigns3.requireActivity().findViewById(R.id.toolTipInfo);
                            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…geView>(R.id.toolTipInfo)");
                            fragmentMyDesigns3.gone(findViewById);
                        } else {
                            FragmentMyDesigns fragmentMyDesigns4 = FragmentMyDesigns.this;
                            TextView textView2 = fragmentMyDesigns4.z().tvToolTipFragment;
                            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvToolTipFragment");
                            fragmentMyDesigns4.gone(textView2);
                            FragmentMyDesigns fragmentMyDesigns5 = FragmentMyDesigns.this;
                            ConstraintLayout constraintLayout2 = fragmentMyDesigns5.z().infoLayout;
                            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.infoLayout");
                            fragmentMyDesigns5.visible(constraintLayout2);
                            WatchFacePagingAdapter adapter3 = FragmentMyDesigns.this.getAdapter();
                            Intrinsics.checkNotNull(adapter3);
                            adapter3.setAlpha(false);
                        }
                        FragmentMyDesigns.this.z().watchFaceRecycler.setVisibility(0);
                        FragmentMyDesigns.this.z().noWatchFace.setVisibility(8);
                        FragmentMyDesigns fragmentMyDesigns6 = FragmentMyDesigns.this;
                        ImageView imageView = fragmentMyDesigns6.z().toolTipInfo;
                        Intrinsics.checkNotNullExpressionValue(imageView, "binding.toolTipInfo");
                        fragmentMyDesigns6.visible(imageView);
                        return;
                    }
                }
                FragmentMyDesigns fragmentMyDesigns7 = FragmentMyDesigns.this;
                ConstraintLayout constraintLayout3 = fragmentMyDesigns7.z().infoLayout;
                Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.infoLayout");
                fragmentMyDesigns7.gone(constraintLayout3);
                FragmentMyDesigns fragmentMyDesigns8 = FragmentMyDesigns.this;
                ImageView imageView2 = fragmentMyDesigns8.z().toolTipInfo;
                Intrinsics.checkNotNullExpressionValue(imageView2, "binding.toolTipInfo");
                fragmentMyDesigns8.gone(imageView2);
                FragmentMyDesigns fragmentMyDesigns9 = FragmentMyDesigns.this;
                TextView textView3 = fragmentMyDesigns9.z().tvToolTipFragment;
                Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvToolTipFragment");
                fragmentMyDesigns9.gone(textView3);
                WatchFacePagingAdapter adapter4 = FragmentMyDesigns.this.getAdapter();
                Intrinsics.checkNotNull(adapter4);
                adapter4.setAlpha(false);
                FragmentMyDesigns.this.z().watchFaceRecycler.setVisibility(8);
                FragmentMyDesigns.this.z().noWatchFace.setVisibility(0);
            }
        }
    }

    /* loaded from: classes8.dex */
    public static final class d extends Lambda implements Function1<Boolean, Unit> {
        public static final d INSTANCE = new d();

        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean bool) {
        }
    }

    /* loaded from: classes8.dex */
    public static final class e extends Lambda implements Function1<Boolean, Unit> {
        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean it) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (it.booleanValue()) {
                FragmentMyDesigns.this.L();
                FragmentMyDesigns.this.z().close.performClick();
                FragmentMyDesigns.this.dismissProgress();
            }
        }
    }

    /* loaded from: classes8.dex */
    public static final class f extends Lambda implements Function1<Boolean, Unit> {
        public f() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean bool) {
            WatchFaceDiyViewModel watchFaceDiyViewModel = null;
            if (!bool.booleanValue()) {
                FragmentMyDesigns.this.p = null;
                WatchFacePagingAdapter adapter = FragmentMyDesigns.this.getAdapter();
                Intrinsics.checkNotNull(adapter);
                adapter.setMSelectedWatchFace(null);
                WatchFacePagingAdapter adapter2 = FragmentMyDesigns.this.getAdapter();
                Intrinsics.checkNotNull(adapter2);
                adapter2.notifyDataSetChanged();
                return;
            }
            FragmentMyDesigns fragmentMyDesigns = FragmentMyDesigns.this;
            WatchFaceDiyViewModel watchFaceDiyViewModel2 = fragmentMyDesigns.o;
            if (watchFaceDiyViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            } else {
                watchFaceDiyViewModel = watchFaceDiyViewModel2;
            }
            fragmentMyDesigns.p = watchFaceDiyViewModel.getUserSelectedWatchFace();
            WatchFaceBean watchFaceBean = FragmentMyDesigns.this.p;
            if (watchFaceBean != null) {
                FragmentMyDesigns fragmentMyDesigns2 = FragmentMyDesigns.this;
                if (watchFaceBean.getPreviewImageUrl() != null) {
                    WatchFacePagingAdapter adapter3 = fragmentMyDesigns2.getAdapter();
                    Intrinsics.checkNotNull(adapter3);
                    adapter3.setMSelectedWatchFace(watchFaceBean);
                    WatchFacePagingAdapter adapter4 = fragmentMyDesigns2.getAdapter();
                    Intrinsics.checkNotNull(adapter4);
                    adapter4.notifyDataSetChanged();
                }
            }
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentMyDesigns$pullWatchFace$1", f = "FragmentMyDesigns.kt", i = {}, l = {509}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentMyDesigns$pullWatchFace$1$1", f = "FragmentMyDesigns.kt", i = {}, l = {510}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes8.dex */
        public static final class a extends SuspendLambda implements Function2<PagingData<WatchFaceBean>, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object L$0;
            public int label;
            public final /* synthetic */ FragmentMyDesigns this$0;

            @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentMyDesigns$pullWatchFace$1$1$1", f = "FragmentMyDesigns.kt", i = {}, l = {511}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.watchfaceui.fragments.FragmentMyDesigns$g$a$a  reason: collision with other inner class name */
            /* loaded from: classes8.dex */
            public static final class C0325a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ PagingData<WatchFaceBean> $it;
                public int label;
                public final /* synthetic */ FragmentMyDesigns this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0325a(FragmentMyDesigns fragmentMyDesigns, PagingData<WatchFaceBean> pagingData, Continuation<? super C0325a> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentMyDesigns;
                    this.$it = pagingData;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0325a(this.this$0, this.$it, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0325a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            public a(FragmentMyDesigns fragmentMyDesigns, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentMyDesigns;
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
                    C0325a c0325a = new C0325a(this.this$0, (PagingData) this.L$0, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main, c0325a, this) == coroutine_suspended) {
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

        public g(Continuation<? super g> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WatchFaceDiyViewModel watchFaceDiyViewModel = FragmentMyDesigns.this.o;
                if (watchFaceDiyViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                    watchFaceDiyViewModel = null;
                }
                Flow<PagingData<WatchFaceBean>> pullWatchFaces = watchFaceDiyViewModel.pullWatchFaces();
                a aVar = new a(FragmentMyDesigns.this, null);
                this.label = 1;
                if (FlowKt.collectLatest(pullWatchFaces, aVar, this) == coroutine_suspended) {
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

    public static final void A(FragmentMyDesigns this$0, WatchFaceBean watchFaceBean, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(watchFaceBean, "$watchFaceBean");
        if (AppUtils.isNetConnected(this$0.getContext())) {
            if (this$0.A) {
                AppNavigator.Companion companion = AppNavigator.Companion;
                FragmentActivity requireActivity = this$0.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                String string = StringUtils.getString(R.string.create_watchface);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.create_watchface)");
                String editUrl = watchFaceBean.getEditUrl();
                Intrinsics.checkNotNull(editUrl);
                companion.navigateToWebViewer(requireActivity, string, editUrl, false);
                return;
            } else if (this$0.y) {
                return;
            } else {
                String uid = watchFaceBean.getUid();
                WatchFacePreferenceManager.Companion companion2 = WatchFacePreferenceManager.Companion;
                Context requireContext = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                WatchFaceBean lastSelectedWatchFace = companion2.getInstance(requireContext).getLastSelectedWatchFace();
                if (Intrinsics.areEqual(uid, lastSelectedWatchFace != null ? lastSelectedWatchFace.getUid() : null)) {
                    Toast.makeText(this$0.getContext(), this$0.getString(R.string.cannot_edit_applied_watchface), 1).show();
                    return;
                } else {
                    Toast.makeText(this$0.getContext(), this$0.getString(R.string.cannot_edit_watchface), 1).show();
                    return;
                }
            }
        }
        Toast.makeText(this$0.getContext(), this$0.getString(R.string.no_internet_connection), 0).show();
    }

    public static final void B(FragmentMyDesigns this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = (TextView) this$0.requireActivity().findViewById(R.id.tvToolTip);
        if (textView.getVisibility() == 0) {
            textView.setVisibility(8);
        }
    }

    public static final void C(FragmentMyDesigns this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView tooltip = (TextView) this$0.requireActivity().findViewById(R.id.tvToolTip);
        if (tooltip.getVisibility() == 0) {
            Intrinsics.checkNotNullExpressionValue(tooltip, "tooltip");
            this$0.gone(tooltip);
            return;
        }
        Intrinsics.checkNotNullExpressionValue(tooltip, "tooltip");
        this$0.visible(tooltip);
    }

    public static final void D(FragmentMyDesigns this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WatchFacePagingAdapter watchFacePagingAdapter = this$0.r;
        if (watchFacePagingAdapter != null) {
            watchFacePagingAdapter.clearChangesWatchfaceListByOnItemClick();
        }
        this$0.v.clear();
        ((TextView) this$0._$_findCachedViewById(R.id.tvDelete)).setText("Delete(0)");
        WatchFacePagingAdapter watchFacePagingAdapter2 = this$0.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter2);
        watchFacePagingAdapter2.clearCheckBox(true);
        WatchFacePagingAdapter watchFacePagingAdapter3 = this$0.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter3);
        watchFacePagingAdapter3.showCheckBox(false);
        ConstraintLayout constraintLayout = this$0.z().deleteLayout;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.deleteLayout");
        this$0.gone(constraintLayout);
        View findViewById = this$0.requireActivity().findViewById(R.id.toolTipInfo);
        Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…geView>(R.id.toolTipInfo)");
        this$0.visible(findViewById);
        this$0.y = false;
        WatchFacePagingAdapter watchFacePagingAdapter4 = this$0.r;
        if (watchFacePagingAdapter4 != null) {
            watchFacePagingAdapter4.setLongPressed(false);
        }
        WatchFacePagingAdapter watchFacePagingAdapter5 = this$0.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter5);
        watchFacePagingAdapter5.notifyDataSetChanged();
        this$0.x = false;
        ConstraintLayout constraintLayout2 = this$0.z().infoLayout;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.infoLayout");
        this$0.visible(constraintLayout2);
        this$0.z().tvSelectAll.setText(this$0.getString(R.string.select_all));
        WatchFaceBean watchFaceBean = this$0.p;
        WatchFaceDiyViewModel watchFaceDiyViewModel = null;
        String uid = watchFaceBean != null ? watchFaceBean.getUid() : null;
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        WatchFaceBean lastSelectedWatchFace = companion.getInstance(requireContext).getLastSelectedWatchFace();
        if (Intrinsics.areEqual(uid, lastSelectedWatchFace != null ? lastSelectedWatchFace.getUid() : null)) {
            return;
        }
        WatchFacePagingAdapter watchFacePagingAdapter6 = this$0.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter6);
        watchFacePagingAdapter6.setMSelectedWatchFace(this$0.p);
        WatchFacePagingAdapter watchFacePagingAdapter7 = this$0.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter7);
        watchFacePagingAdapter7.notifyDataSetChanged();
        WatchFaceBean watchFaceBean2 = this$0.p;
        if ((watchFaceBean2 != null ? watchFaceBean2.getEditUrl() : null) != null) {
            this$0.editEnable();
            WatchFaceDiyViewModel watchFaceDiyViewModel2 = this$0.o;
            if (watchFaceDiyViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            } else {
                watchFaceDiyViewModel = watchFaceDiyViewModel2;
            }
            watchFaceDiyViewModel.showSaveBtn(true);
            return;
        }
        this$0.editDisable();
        WatchFaceDiyViewModel watchFaceDiyViewModel3 = this$0.o;
        if (watchFaceDiyViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
        } else {
            watchFaceDiyViewModel = watchFaceDiyViewModel3;
        }
        watchFaceDiyViewModel.showSaveBtn(false);
    }

    public static final void E(FragmentMyDesigns this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WatchFacePagingAdapter watchFacePagingAdapter = this$0.r;
        if (watchFacePagingAdapter != null) {
            watchFacePagingAdapter.clearChangesWatchfaceListByOnItemClick();
        }
        CharSequence text = this$0.z().tvSelectAll.getText();
        int i = R.string.select_all;
        if (Intrinsics.areEqual(text, this$0.getString(i))) {
            RecyclerView recyclerView = this$0.z().watchFaceRecycler;
            if (recyclerView != null) {
                WatchFacePagingAdapter watchFacePagingAdapter2 = this$0.r;
                Intrinsics.checkNotNull(watchFacePagingAdapter2);
                recyclerView.smoothScrollToPosition(watchFacePagingAdapter2.getItemCount());
            }
            this$0.x = true;
            this$0.v.clear();
            WatchFacePagingAdapter watchFacePagingAdapter3 = this$0.r;
            Intrinsics.checkNotNull(watchFacePagingAdapter3);
            watchFacePagingAdapter3.clearCheckBox(false);
            WatchFacePagingAdapter watchFacePagingAdapter4 = this$0.r;
            Intrinsics.checkNotNull(watchFacePagingAdapter4);
            watchFacePagingAdapter4.showCheckBox(true);
            this$0.z().tvSelectAll.setText(this$0.getString(R.string.deselect_all));
            return;
        }
        RecyclerView recyclerView2 = this$0.z().watchFaceRecycler;
        if (recyclerView2 != null) {
            recyclerView2.smoothScrollToPosition(0);
        }
        this$0.x = false;
        this$0.v.clear();
        WatchFacePagingAdapter watchFacePagingAdapter5 = this$0.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter5);
        watchFacePagingAdapter5.clearCheckBox(true);
        WatchFacePagingAdapter watchFacePagingAdapter6 = this$0.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter6);
        watchFacePagingAdapter6.showCheckBox(true);
        this$0.z().tvDelete.setText("Delete(0)");
        this$0.z().tvSelectAll.setText(this$0.getString(i));
    }

    public static final void F(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.String] */
    public static final void G(final FragmentMyDesigns this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SessionManager.getInstance(this$0.requireContext()).setDiyWatchFaceFTUShown(true);
        this$0.toolTioVisibilty();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = SessionManager.getInstance(this$0.requireContext()).getWatchFaceDiyUrl();
        if (AppUtils.isNetConnected(this$0.requireContext())) {
            final SessionManager sessionManager = SessionManager.getInstance(this$0.requireContext());
            Integer watchFaceMaxAllowed = sessionManager.getWatchFaceMaxAllowed();
            if (watchFaceMaxAllowed != null && watchFaceMaxAllowed.intValue() == -1) {
                if (objectRef.element != 0) {
                    AppNavigator.Companion companion = AppNavigator.Companion;
                    FragmentActivity requireActivity = this$0.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    String string = StringUtils.getString(R.string.create_watchface);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.create_watchface)");
                    T url = objectRef.element;
                    Intrinsics.checkNotNullExpressionValue(url, "url");
                    companion.navigateToWebViewer(requireActivity, string, (String) url, false);
                }
                HashMap<String, Object> hashMap = new HashMap<>();
                DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                Context requireContext = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                hashMap.putAll(companion2.getDeviceId(requireContext));
                Context requireContext2 = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                hashMap.putAll(companion2.getWatchDetails(requireContext2));
                companion2.logAnalyticsEvent(CleverTapConstants.EventName.WF_WFS_CTA_TAPPED.getValue(), hashMap);
                return;
            }
            this$0.showProgress(false);
            WatchFaceDiyViewModel watchFaceDiyViewModel = this$0.o;
            if (watchFaceDiyViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                watchFaceDiyViewModel = null;
            }
            FragmentActivity requireActivity2 = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            watchFaceDiyViewModel.getDiyWatchFaceLists(requireActivity2, new SuccessResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentMyDesigns$onViewCreated$6$1

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentMyDesigns$onViewCreated$6$1$onError$1", f = "FragmentMyDesigns.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes8.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ FragmentMyDesigns this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(FragmentMyDesigns fragmentMyDesigns, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = fragmentMyDesigns;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.this$0, continuation);
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
                            if (this.this$0.isAdded()) {
                                this.this$0.dismissProgress();
                                Toast.makeText(this.this$0.requireContext(), this.this$0.getString(R.string.something_went_wrong), 0).show();
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentMyDesigns$onViewCreated$6$1$onSuccess$1", f = "FragmentMyDesigns.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes8.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ SessionManager $sessionManager;
                    public final /* synthetic */ Ref.ObjectRef<String> $url;
                    public int label;
                    public final /* synthetic */ FragmentMyDesigns this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(FragmentMyDesigns fragmentMyDesigns, SessionManager sessionManager, Ref.ObjectRef<String> objectRef, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = fragmentMyDesigns;
                        this.$sessionManager = sessionManager;
                        this.$url = objectRef;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, this.$sessionManager, this.$url, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            if (this.this$0.isAdded()) {
                                this.this$0.dismissProgress();
                                Integer diyWatchFaceListItems = SessionManager.getInstance(this.this$0.requireContext()).getDiyWatchFaceListItems();
                                Intrinsics.checkNotNullExpressionValue(diyWatchFaceListItems, "getInstance(requireConte…()).diyWatchFaceListItems");
                                int intValue = diyWatchFaceListItems.intValue();
                                Integer watchFaceMaxAllowed = this.$sessionManager.getWatchFaceMaxAllowed();
                                Intrinsics.checkNotNullExpressionValue(watchFaceMaxAllowed, "sessionManager.watchFaceMaxAllowed");
                                if (intValue < watchFaceMaxAllowed.intValue()) {
                                    if (this.$url.element != null) {
                                        AppNavigator.Companion companion = AppNavigator.Companion;
                                        FragmentActivity requireActivity = this.this$0.requireActivity();
                                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                                        String string = StringUtils.getString(R.string.create_watchface);
                                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.create_watchface)");
                                        String url = this.$url.element;
                                        Intrinsics.checkNotNullExpressionValue(url, "url");
                                        companion.navigateToWebViewer(requireActivity, string, url, false);
                                    }
                                    HashMap<String, Object> hashMap = new HashMap<>();
                                    DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                                    Context requireContext = this.this$0.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                    hashMap.putAll(companion2.getDeviceId(requireContext));
                                    Context requireContext2 = this.this$0.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                    hashMap.putAll(companion2.getWatchDetails(requireContext2));
                                    companion2.logAnalyticsEvent(CleverTapConstants.EventName.WF_WFS_CTA_TAPPED.getValue(), hashMap);
                                } else {
                                    FragmentMyDesigns fragmentMyDesigns = this.this$0;
                                    String string2 = fragmentMyDesigns.getString(R.string.max_exceeded);
                                    Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ui.R.string.max_exceeded)");
                                    String string3 = this.this$0.getString(R.string.max_info);
                                    Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.an…faceui.R.string.max_info)");
                                    fragmentMyDesigns.showBottomSheetDialog(string2, string3, true);
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.theme.SuccessResultListener
                public void onError(@Nullable String str) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentMyDesigns.this), Dispatchers.getMain(), null, new a(FragmentMyDesigns.this, null), 2, null);
                }

                @Override // com.coveiot.android.theme.SuccessResultListener
                public void onSuccess() {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentMyDesigns.this), Dispatchers.getMain(), null, new b(FragmentMyDesigns.this, sessionManager, objectRef, null), 2, null);
                }
            });
            return;
        }
        this$0.showInternetOffDialog();
    }

    public static final void H(FragmentMyDesigns this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.v.size() >= 1) {
            WatchFacePagingAdapter watchFacePagingAdapter = this$0.r;
            Intrinsics.checkNotNull(watchFacePagingAdapter);
            if (watchFacePagingAdapter.getItemCount() == this$0.v.size()) {
                String string = this$0.getString(R.string.delete_all_watch_faces);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.delete_all_watch_faces)");
                String string2 = this$0.getString(R.string.are_you_sure_all);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.are_you_sure_all)");
                this$0.showBottomSheetDialog(string, string2, false);
                return;
            }
            String string3 = this$0.getString(R.string.delete_watch_faces);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.delete_watch_faces)");
            String string4 = this$0.getString(R.string.are_you_sure);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.are_you_sure)");
            this$0.showBottomSheetDialog(string3, string4, false);
        }
    }

    public static final void I(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void J(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void K(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void N(FragmentMyDesigns this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WatchFaceDiyViewModel watchFaceDiyViewModel = null;
        BaseFragment.showProgress$default(this$0, false, 1, null);
        if (this$0.z().tvToolTipFragment.getVisibility() == 0) {
            TextView textView = this$0.z().tvToolTipFragment;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvToolTipFragment");
            this$0.gone(textView);
        }
        WatchFaceDiyViewModel watchFaceDiyViewModel2 = this$0.o;
        if (watchFaceDiyViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
        } else {
            watchFaceDiyViewModel = watchFaceDiyViewModel2;
        }
        watchFaceDiyViewModel.deleteWatchFaces(this$0.v);
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        bottomSheetDialogImageTitleAndMessageWatchFace.dismiss();
    }

    public static final void O(FragmentMyDesigns this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        bottomSheetDialogImageTitleAndMessageWatchFace.dismiss();
    }

    public static final void P(FragmentMyDesigns this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        bottomSheetDialogImageTitleAndMessageWatchFace.dismiss();
    }

    public static final void Q(FragmentMyDesigns this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.u;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentMyDesigns newInstance() {
        return Companion.newInstance();
    }

    public final void L() {
        Job e2;
        if (AppUtils.isNetConnected(requireActivity())) {
            if (SessionManager.getInstance(getContext()).getBleDeviceInfo() == null || ((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(getContext()).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision() == null) {
                return;
            }
            Job job = this.q;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            e2 = kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new g(null), 2, null);
            this.q = e2;
            return;
        }
        Toast.makeText(requireActivity(), getString(R.string.no_internet_connection), 0).show();
    }

    public final void M() {
        String modelNumber;
        String str = (BleApiManager.getInstance(requireContext()) == null || BleApiManager.getInstance(requireContext()).getDeviceType() != DeviceType.matrix) ? "watchface" : Constants.WATCH_FACE_FILE_NAME_MATRIX;
        WatchFaceDiyViewModel watchFaceDiyViewModel = this.o;
        if (watchFaceDiyViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel = null;
        }
        WatchFaceDiyViewModel watchFaceDiyViewModel2 = this.o;
        if (watchFaceDiyViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel2 = null;
        }
        WatchFaceBean userSelectedWatchFace = watchFaceDiyViewModel2.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace);
        String downloadUrl = userSelectedWatchFace.getDownloadUrl();
        Intrinsics.checkNotNull(downloadUrl);
        watchFaceDiyViewModel.downloadWatchFaceFromServerSendToWatch(str, downloadUrl, this.p);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.WATCHFASE_SAVE.getValue());
        DeviceModelBean deviceModelBean = SessionManager.getInstance(getContext()).getDeviceModelBean();
        if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
            DeviceModelBean deviceModelBean2 = SessionManager.getInstance(getContext()).getDeviceModelBean();
            modelNumber = deviceModelBean2 != null ? deviceModelBean2.getName() : null;
        } else {
            Utils utils = Utils.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            modelNumber = utils.getModelNumber(requireActivity);
        }
        analyticsLog.setCVPrevScreenName(modelNumber + "_features");
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.APPLY_WATCHFACE.getValue());
        HashMap<String, String> hashMap = new HashMap<>();
        String value = FirebaseEventParams.MetaData.CV_WATCHFACE_ID.getValue();
        WatchFaceBean watchFaceBean = this.p;
        hashMap.put(value, String.valueOf(watchFaceBean != null ? watchFaceBean.getFaceId() : null));
        String value2 = FirebaseEventParams.MetaData.CV_WATCHFACE_FILE_NAME.getValue();
        WatchFaceBean watchFaceBean2 = this.p;
        hashMap.put(value2, URI.create((watchFaceBean2 == null || (r4 = watchFaceBean2.getDownloadUrl()) == null) ? "" : "").getPath());
        String value3 = FirebaseEventParams.MetaData.CV_WATCHFACE_CATEGORY.getValue();
        WatchFaceBean watchFaceBean3 = this.p;
        String lowerCase = String.valueOf(watchFaceBean3 != null ? watchFaceBean3.getFaceType() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        hashMap.put(value3, lowerCase);
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put(CleverTapConstants.CustomEventProperties.WATCH_FACE_TYPE.getValue(), CleverTapConstants.CustomEventValues.CUSTOM.getValue());
        hashMap2.put(CleverTapConstants.CustomEventProperties.UPDATE_LOCATION.getValue(), CleverTapConstants.CustomEventValues.WATCH_FACES_PAGE.getValue());
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap2.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap2.putAll(companion.getWatchDetails(requireContext2));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.WF_UPDATED.getValue(), hashMap2);
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

    public final void editDisable() {
        TextView textView = (TextView) requireActivity().findViewById(R.id.editBtn);
        textView.setTextColor(getResources().getColor(R.color.color_666666));
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.reminder_edit_icon_grey, 0, 0, 0);
        this.A = false;
    }

    public final void editEnable() {
        TextView textView = (TextView) requireActivity().findViewById(R.id.editBtn);
        textView.setTextColor(getResources().getColor(R.color.main_text_color));
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.reminder_edit_icon, 0, 0, 0);
        this.A = true;
    }

    @Nullable
    public final WatchFacePagingAdapter getAdapter() {
        return this.r;
    }

    @Nullable
    public final BottomSheetDialogImageTitleAndMessageWatchFace getBottomSheetDialogImageTitleAndMessageWatchFace() {
        return this.w;
    }

    @Nullable
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitle1() {
        return this.u;
    }

    @NotNull
    public final ArrayList<WatchFaceBean> getListOfFaceId() {
        return this.v;
    }

    @Nullable
    public final WatchFaceBean getPrefWatchFaceBean() {
        return this.s;
    }

    @NotNull
    public final String getTAG() {
        return this.m;
    }

    public final void initData() {
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        WatchFaceBean selectedDiyWatchFace = companion.getInstance(requireActivity).getSelectedDiyWatchFace();
        this.s = selectedDiyWatchFace;
        if (selectedDiyWatchFace != null) {
            WatchFaceDiyViewModel watchFaceDiyViewModel = this.o;
            WatchFaceDiyViewModel watchFaceDiyViewModel2 = null;
            if (watchFaceDiyViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                watchFaceDiyViewModel = null;
            }
            if (watchFaceDiyViewModel.getUserSelectedWatchFace() == null) {
                WatchFaceDiyViewModel watchFaceDiyViewModel3 = this.o;
                if (watchFaceDiyViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                } else {
                    watchFaceDiyViewModel2 = watchFaceDiyViewModel3;
                }
                WatchFaceBean watchFaceBean = this.s;
                Intrinsics.checkNotNull(watchFaceBean);
                String uid = watchFaceBean.getUid();
                WatchFaceBean watchFaceBean2 = this.s;
                Intrinsics.checkNotNull(watchFaceBean2);
                String faceId = watchFaceBean2.getFaceId();
                WatchFaceBean watchFaceBean3 = this.s;
                Intrinsics.checkNotNull(watchFaceBean3);
                String faceType = watchFaceBean3.getFaceType();
                WatchFaceBean watchFaceBean4 = this.s;
                Intrinsics.checkNotNull(watchFaceBean4);
                String title = watchFaceBean4.getTitle();
                WatchFaceBean watchFaceBean5 = this.s;
                Intrinsics.checkNotNull(watchFaceBean5);
                String downloadUrl = watchFaceBean5.getDownloadUrl();
                WatchFaceBean watchFaceBean6 = this.s;
                Intrinsics.checkNotNull(watchFaceBean6);
                String fileType = watchFaceBean6.getFileType();
                WatchFaceBean watchFaceBean7 = this.s;
                Intrinsics.checkNotNull(watchFaceBean7);
                String fileMd5Hash = watchFaceBean7.getFileMd5Hash();
                WatchFaceBean watchFaceBean8 = this.s;
                Intrinsics.checkNotNull(watchFaceBean8);
                String previewImageUrl = watchFaceBean8.getPreviewImageUrl();
                WatchFaceBean watchFaceBean9 = this.s;
                Intrinsics.checkNotNull(watchFaceBean9);
                List<String> tags = watchFaceBean9.getTags();
                WatchFaceBean watchFaceBean10 = this.s;
                Intrinsics.checkNotNull(watchFaceBean10);
                Integer faceResId = watchFaceBean10.getFaceResId();
                WatchFaceBean watchFaceBean11 = this.s;
                Intrinsics.checkNotNull(watchFaceBean11);
                watchFaceDiyViewModel2.setUserSelectedWatchFace(new WatchFaceBean(uid, faceId, faceType, title, downloadUrl, fileType, fileMd5Hash, previewImageUrl, tags, null, faceResId, watchFaceBean11.getFacePosition(), null, false, null, 29184, null));
            }
        }
    }

    public final boolean isEditEnabled() {
        return this.A;
    }

    public final boolean isItemClickSelected() {
        return this.z;
    }

    public final boolean isLongPressed() {
        return this.y;
    }

    public final boolean isSelectAll() {
        return this.x;
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onAppliedClicked() {
        this.A = false;
        if (isAdded()) {
            WatchFacePagingAdapter watchFacePagingAdapter = this.r;
            Intrinsics.checkNotNull(watchFacePagingAdapter);
            watchFacePagingAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter.OnCheckBoxListener
    public void onChecked(@NotNull WatchFaceBean watchFaceBean, boolean z) {
        Intrinsics.checkNotNullParameter(watchFaceBean, "watchFaceBean");
        if (z) {
            if (!this.v.contains(watchFaceBean)) {
                this.v.add(watchFaceBean);
            }
        } else {
            this.v.remove(watchFaceBean);
        }
        TextView textView = z().tvDelete;
        textView.setText("Delete (" + this.v.size() + HexStringBuilder.COMMENT_END_CHAR);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.t = FragmentMyDesignsBinding.inflate(inflater, viewGroup, false);
        return z().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        WatchFacePagingAdapter watchFacePagingAdapter = this.r;
        if (watchFacePagingAdapter != null) {
            watchFacePagingAdapter.removeLoadStateListener(a.INSTANCE);
        }
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter.OnWatchFaceItemClickListener
    public void onItemClick(@NotNull final WatchFaceBean watchFaceBean) {
        View findViewById;
        Intrinsics.checkNotNullParameter(watchFaceBean, "watchFaceBean");
        toolTioVisibilty();
        TextView textView = (TextView) requireActivity().findViewById(R.id.editBtn);
        SessionManager sessionManager = SessionManager.getInstance(requireContext());
        if (sessionManager.getRoundedImage().equals(0)) {
            findViewById = requireActivity().findViewById(R.id.selectedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…>(R.id.selectedWatchFace)");
        } else {
            findViewById = requireActivity().findViewById(R.id.selectedRoundedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…selectedRoundedWatchFace)");
        }
        TextView textView2 = (TextView) requireActivity().findViewById(R.id.applyToWatch);
        WatchFaceDiyViewModel watchFaceDiyViewModel = this.o;
        if (watchFaceDiyViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel = null;
        }
        watchFaceDiyViewModel.setUserSelectedWatchFace(watchFaceBean);
        this.p = watchFaceBean;
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.n;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.setWatchFacePushType(2);
        Glide.with(requireActivity()).m30load(watchFaceBean.getPreviewImageUrl()).into((ImageView) findViewById);
        String uid = watchFaceBean.getUid();
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        WatchFaceBean lastSelectedWatchFace = companion.getInstance(requireContext).getLastSelectedWatchFace();
        if (Intrinsics.areEqual(uid, lastSelectedWatchFace != null ? lastSelectedWatchFace.getUid() : null)) {
            WatchFaceDiyViewModel watchFaceDiyViewModel2 = this.o;
            if (watchFaceDiyViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                watchFaceDiyViewModel2 = null;
            }
            watchFaceDiyViewModel2.showSaveBtn(false);
            WatchFaceDiyViewModel watchFaceDiyViewModel3 = this.o;
            if (watchFaceDiyViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                watchFaceDiyViewModel3 = null;
            }
            watchFaceDiyViewModel3.isEditEnable(false);
            editDisable();
            textView2.setEnabled(false);
        } else {
            WatchFaceDiyViewModel watchFaceDiyViewModel4 = this.o;
            if (watchFaceDiyViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                watchFaceDiyViewModel4 = null;
            }
            watchFaceDiyViewModel4.showSaveBtn(true);
            WatchFaceDiyViewModel watchFaceDiyViewModel5 = this.o;
            if (watchFaceDiyViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                watchFaceDiyViewModel5 = null;
            }
            watchFaceDiyViewModel5.isEditEnable(true);
            editEnable();
            textView2.setEnabled(true);
        }
        sessionManager.setDiyWatchFaceFTUShown(true);
        TextView textView3 = z().tvToolTipFragment;
        Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvToolTipFragment");
        gone(textView3);
        ConstraintLayout constraintLayout = z().infoLayout;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.infoLayout");
        visible(constraintLayout);
        WatchFacePagingAdapter watchFacePagingAdapter = this.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter);
        watchFacePagingAdapter.setAlpha(false);
        if (z().deleteLayout.getVisibility() == 0) {
            ImageView imageView = z().toolTipInfo;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.toolTipInfo");
            gone(imageView);
        } else {
            ImageView imageView2 = z().toolTipInfo;
            Intrinsics.checkNotNullExpressionValue(imageView2, "binding.toolTipInfo");
            visible(imageView2);
        }
        if (watchFaceBean.getEditUrl() != null) {
            String uid2 = watchFaceBean.getUid();
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            WatchFaceBean lastSelectedWatchFace2 = companion.getInstance(requireContext2).getLastSelectedWatchFace();
            if (!Intrinsics.areEqual(uid2, lastSelectedWatchFace2 != null ? lastSelectedWatchFace2.getUid() : null)) {
                editEnable();
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.b
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentMyDesigns.A(FragmentMyDesigns.this, watchFaceBean, view);
                    }
                });
            }
        }
        editDisable();
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentMyDesigns.A(FragmentMyDesigns.this, watchFaceBean, view);
            }
        });
    }

    @Override // com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter.OnInitiateLongPress
    public void onLongPressed(@NotNull WatchFaceBean watchFaceBean) {
        Intrinsics.checkNotNullParameter(watchFaceBean, "watchFaceBean");
        SessionManager.getInstance(requireContext()).setDiyWatchFaceFTUShown(true);
        toolTioVisibilty();
        this.y = true;
        this.v.clear();
        z().tvDelete.setText("Delete(0)");
        View findViewById = requireActivity().findViewById(R.id.toolTipInfo);
        Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…geView>(R.id.toolTipInfo)");
        gone(findViewById);
        ConstraintLayout constraintLayout = z().deleteLayout;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.deleteLayout");
        visible(constraintLayout);
        TextView textView = z().tvToolTipFragment;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvToolTipFragment");
        gone(textView);
        ConstraintLayout constraintLayout2 = z().infoLayout;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.infoLayout");
        gone(constraintLayout2);
        WatchFacePagingAdapter watchFacePagingAdapter = this.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter);
        watchFacePagingAdapter.setAlpha(false);
        WatchFacePagingAdapter watchFacePagingAdapter2 = this.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter2);
        watchFacePagingAdapter2.showCheckBox(true);
        WatchFaceDiyViewModel watchFaceDiyViewModel = this.o;
        WatchFaceDiyViewModel watchFaceDiyViewModel2 = null;
        if (watchFaceDiyViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel = null;
        }
        watchFaceDiyViewModel.showSaveBtn(false);
        WatchFaceDiyViewModel watchFaceDiyViewModel3 = this.o;
        if (watchFaceDiyViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
        } else {
            watchFaceDiyViewModel2 = watchFaceDiyViewModel3;
        }
        watchFaceDiyViewModel2.isEditEnable(false);
        editDisable();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        z().close.performClick();
        ConstraintLayout constraintLayout = (ConstraintLayout) requireActivity().findViewById(R.id.watchFaceStudio);
        if (constraintLayout != null) {
            constraintLayout.setVisibility(0);
        }
        L();
        WatchFaceDiyViewModel watchFaceDiyViewModel = this.o;
        if (watchFaceDiyViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel = null;
        }
        watchFaceDiyViewModel.showSaveBtn(false);
        editDisable();
        this.p = null;
        WatchFacePagingAdapter watchFacePagingAdapter = this.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter);
        watchFacePagingAdapter.setMSelectedWatchFace(null);
        WatchFacePagingAdapter watchFacePagingAdapter2 = this.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter2);
        watchFacePagingAdapter2.notifyDataSetChanged();
        super.onResume();
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onSaveClicked() {
        if (this.n == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.n;
        WatchFaceDiyViewModel watchFaceDiyViewModel = null;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        if (activityWatchFaceViewModel.getWatchFacePushType() == 2) {
            if (this.o == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            }
            WatchFaceDiyViewModel watchFaceDiyViewModel2 = this.o;
            if (watchFaceDiyViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                watchFaceDiyViewModel2 = null;
            }
            if (watchFaceDiyViewModel2.getUserSelectedWatchFace() != null) {
                WatchFaceDiyViewModel watchFaceDiyViewModel3 = this.o;
                if (watchFaceDiyViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
                    watchFaceDiyViewModel3 = null;
                }
                WatchFaceBean userSelectedWatchFace = watchFaceDiyViewModel3.getUserSelectedWatchFace();
                Intrinsics.checkNotNull(userSelectedWatchFace);
                if (userSelectedWatchFace.getDownloadUrl() != null) {
                    if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        BleApiManager.getInstance(requireActivity()).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentMyDesigns$onSaveClicked$1
                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onDataError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                LogHelper.i("FragmentWatchFaceCloud", "BatteryLevelRequest -- onDataError ");
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onDataResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                if (response.getResponseData() instanceof BatteryLevelResponse) {
                                    Object responseData = response.getResponseData();
                                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                                    Integer batteryLevel = ((BatteryLevelResponse) responseData).getBatteryLevel();
                                    Intrinsics.checkNotNull(batteryLevel);
                                    int intValue = batteryLevel.intValue();
                                    Utils utils = Utils.INSTANCE;
                                    Context requireContext = FragmentMyDesigns.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                    if (utils.isRuggedDevice(requireContext)) {
                                        intValue = utils.getBatteryPercentageForMatrix(intValue);
                                    }
                                    LogHelper.i("FragmentWatchFaceCloud", "batteryLevel -- " + intValue);
                                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                                    Context requireContext2 = FragmentMyDesigns.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                    if (companion.isCurrentFirmwareHasIssueWithBatteryPercentage(requireContext2) && intValue <= 0) {
                                        FragmentMyDesigns.this.M();
                                        return;
                                    }
                                    Context requireContext3 = FragmentMyDesigns.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                                    if (intValue >= utils.getMinBatteryPerForWatchFaceUpload(requireContext3)) {
                                        FragmentMyDesigns.this.M();
                                        return;
                                    }
                                    FragmentActivity requireActivity = FragmentMyDesigns.this.requireActivity();
                                    FragmentMyDesigns fragmentMyDesigns = FragmentMyDesigns.this;
                                    int i = R.string.make_sure_battery;
                                    StringBuilder sb = new StringBuilder();
                                    Context requireContext4 = FragmentMyDesigns.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                                    sb.append(utils.getMinBatteryPerForWatchFaceUpload(requireContext4));
                                    sb.append(" %");
                                    Toast.makeText(requireActivity, fragmentMyDesigns.getString(i, sb.toString()), 1).show();
                                }
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onProgressUpdate(@NotNull ProgressData progress) {
                                Intrinsics.checkNotNullParameter(progress, "progress");
                            }
                        });
                        return;
                    } else {
                        Toast.makeText(requireActivity(), getString(R.string.band_not_connected), 1).show();
                        return;
                    }
                }
            }
            Toast.makeText(requireActivity(), "Please choose your watch face", 1).show();
            WatchFaceDiyViewModel watchFaceDiyViewModel4 = this.o;
            if (watchFaceDiyViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            } else {
                watchFaceDiyViewModel = watchFaceDiyViewModel4;
            }
            watchFaceDiyViewModel.onFail();
            return;
        }
        Toast.makeText(requireActivity(), "Please choose your watch face", 1).show();
        WatchFaceDiyViewModel watchFaceDiyViewModel5 = this.o;
        if (watchFaceDiyViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
        } else {
            watchFaceDiyViewModel = watchFaceDiyViewModel5;
        }
        watchFaceDiyViewModel.onFail();
    }

    @Override // com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter.OnWatchFaceItemClickListener
    public void onSelectionFound(@NotNull WatchFaceBean watchFaceBean) {
        Intrinsics.checkNotNullParameter(watchFaceBean, "watchFaceBean");
        WatchFaceBean watchFaceBean2 = this.s;
        if (watchFaceBean2 != null) {
            Intrinsics.checkNotNull(watchFaceBean2);
            if (watchFaceBean2.getPreviewImageUrl() == null) {
                WatchFaceBean watchFaceBean3 = this.s;
                Intrinsics.checkNotNull(watchFaceBean3);
                if (Intrinsics.areEqual(watchFaceBean3.getUid(), watchFaceBean.getUid())) {
                    WatchFaceBean watchFaceBean4 = this.s;
                    Intrinsics.checkNotNull(watchFaceBean4);
                    watchFaceBean4.setPreviewImageUrl(watchFaceBean.getPreviewImageUrl());
                    WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    companion.getInstance(requireActivity).saveSelectedDiyWatchFace(this.s);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        z().watchFaceRecycler.setHasFixedSize(true);
        z().rootLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentMyDesigns.B(FragmentMyDesigns.this, view2);
            }
        });
        z().watchFaceStudio.setVisibility(0);
        ConstraintLayout constraintLayout = z().deleteLayout;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.deleteLayout");
        gone(constraintLayout);
        z().toolTipInfo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentMyDesigns.C(FragmentMyDesigns.this, view2);
            }
        });
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        ViewModel viewModel = ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(WatchFaceDiyViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            requireA…DiyViewModel::class.java)");
        this.o = (WatchFaceDiyViewModel) viewModel;
        FragmentActivity requireActivity3 = requireActivity();
        FragmentActivity requireActivity4 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
        ViewModel viewModel2 = ViewModelProviders.of(requireActivity3, new ViewModelFactory(requireActivity4)).get(ActivityWatchFaceViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(\n            requireA…aceViewModel::class.java)");
        this.n = (ActivityWatchFaceViewModel) viewModel2;
        FragmentActivity requireActivity5 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity()");
        WatchFacePagingAdapter watchFacePagingAdapter = new WatchFacePagingAdapter(requireActivity5, 2, this);
        this.r = watchFacePagingAdapter;
        Intrinsics.checkNotNull(watchFacePagingAdapter);
        watchFacePagingAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        WatchFacePagingAdapter watchFacePagingAdapter2 = this.r;
        Intrinsics.checkNotNull(watchFacePagingAdapter2);
        watchFacePagingAdapter2.setOnWatchFaceClickListener(this, this);
        z().watchFaceRecycler.setLayoutManager(new GridLayoutManager(requireActivity(), 3));
        z().watchFaceRecycler.setAdapter(this.r);
        z().close.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentMyDesigns.D(FragmentMyDesigns.this, view2);
            }
        });
        z().tvSelectAll.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentMyDesigns.E(FragmentMyDesigns.this, view2);
            }
        });
        WatchFaceDiyViewModel watchFaceDiyViewModel = this.o;
        ActivityWatchFaceViewModel activityWatchFaceViewModel = null;
        if (watchFaceDiyViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel = null;
        }
        MutableLiveData<Boolean> saveEnableDisable = watchFaceDiyViewModel.getSaveEnableDisable();
        FragmentActivity requireActivity6 = requireActivity();
        final d dVar = d.INSTANCE;
        saveEnableDisable.observe(requireActivity6, new Observer() { // from class: com.coveiot.android.watchfaceui.fragments.c
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentMyDesigns.F(Function1.this, obj);
            }
        });
        z().watchFaceStudio.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentMyDesigns.G(FragmentMyDesigns.this, view2);
            }
        });
        z().tvDelete.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentMyDesigns.H(FragmentMyDesigns.this, view2);
            }
        });
        WatchFaceDiyViewModel watchFaceDiyViewModel2 = this.o;
        if (watchFaceDiyViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel2 = null;
        }
        MutableLiveData<Boolean> isDeleteWatchfaceSuccess = watchFaceDiyViewModel2.isDeleteWatchfaceSuccess();
        FragmentActivity requireActivity7 = requireActivity();
        final e eVar = new e();
        isDeleteWatchfaceSuccess.observe(requireActivity7, new Observer() { // from class: com.coveiot.android.watchfaceui.fragments.f
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentMyDesigns.I(Function1.this, obj);
            }
        });
        initData();
        ActivityWatchFaceViewModel activityWatchFaceViewModel2 = this.n;
        if (activityWatchFaceViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel2 = null;
        }
        LiveData<Boolean> isDiyWatchFaceSelected = activityWatchFaceViewModel2.getIsDiyWatchFaceSelected();
        if (isDiyWatchFaceSelected != null) {
            FragmentActivity requireActivity8 = requireActivity();
            final f fVar = new f();
            isDiyWatchFaceSelected.observe(requireActivity8, new Observer() { // from class: com.coveiot.android.watchfaceui.fragments.e
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentMyDesigns.J(Function1.this, obj);
                }
            });
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel3 = this.n;
        if (activityWatchFaceViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
        } else {
            activityWatchFaceViewModel = activityWatchFaceViewModel3;
        }
        LiveData<Boolean> isCloudWatchFaceSelected = activityWatchFaceViewModel.getIsCloudWatchFaceSelected();
        if (isCloudWatchFaceSelected != null) {
            FragmentActivity requireActivity9 = requireActivity();
            final b bVar = new b();
            isCloudWatchFaceSelected.observe(requireActivity9, new Observer() { // from class: com.coveiot.android.watchfaceui.fragments.d
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentMyDesigns.K(Function1.this, obj);
                }
            });
        }
        if (SessionManager.getInstance(requireContext()).isDiyWatchFaceFTUShown()) {
            WatchFacePagingAdapter watchFacePagingAdapter3 = this.r;
            Intrinsics.checkNotNull(watchFacePagingAdapter3);
            watchFacePagingAdapter3.setAlpha(false);
            TextView textView = z().tvToolTipFragment;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvToolTipFragment");
            gone(textView);
        }
        WatchFacePagingAdapter watchFacePagingAdapter4 = this.r;
        if (watchFacePagingAdapter4 != null) {
            watchFacePagingAdapter4.addLoadStateListener(new c());
        }
    }

    public final void setAdapter(@Nullable WatchFacePagingAdapter watchFacePagingAdapter) {
        this.r = watchFacePagingAdapter;
    }

    public final void setBottomSheetDialogImageTitleAndMessageWatchFace(@Nullable BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace) {
        this.w = bottomSheetDialogImageTitleAndMessageWatchFace;
    }

    public final void setBottomSheetDialogOneButtonOneTitle1(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.u = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setEditEnabled(boolean z) {
        this.A = z;
    }

    public final void setItemClickSelected(boolean z) {
        this.z = z;
    }

    public final void setListOfFaceId(@NotNull ArrayList<WatchFaceBean> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.v = arrayList;
    }

    public final void setLongPressed(boolean z) {
        this.y = z;
    }

    public final void setPrefWatchFaceBean(@Nullable WatchFaceBean watchFaceBean) {
        this.s = watchFaceBean;
    }

    public final void setSelectAll(boolean z) {
        this.x = z;
    }

    public final void showBottomSheetDialog(@NotNull String title, @NotNull String message, boolean z) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = new BottomSheetDialogImageTitleAndMessageWatchFace(requireContext, title, message);
        this.w = bottomSheetDialogImageTitleAndMessageWatchFace;
        if (!z) {
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
            String string = getString(R.string.delete);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.delete)");
            bottomSheetDialogImageTitleAndMessageWatchFace.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.m
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentMyDesigns.N(FragmentMyDesigns.this, view);
                }
            });
            BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace2 = this.w;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace2);
            String string2 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.cancel)");
            bottomSheetDialogImageTitleAndMessageWatchFace2.setNegativeButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentMyDesigns.O(FragmentMyDesigns.this, view);
                }
            });
            BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace3 = this.w;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace3);
            bottomSheetDialogImageTitleAndMessageWatchFace3.show();
            BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace4 = this.w;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace4);
            bottomSheetDialogImageTitleAndMessageWatchFace4.setCancelable(false);
            return;
        }
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        String string3 = getString(R.string.done);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.done)");
        bottomSheetDialogImageTitleAndMessageWatchFace.setDoneButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentMyDesigns.P(FragmentMyDesigns.this, view);
            }
        });
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace5 = this.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace5);
        bottomSheetDialogImageTitleAndMessageWatchFace5.show();
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace6 = this.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace6);
        bottomSheetDialogImageTitleAndMessageWatchFace6.setCancelable(false);
    }

    public final void showInternetOffDialog() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(com.coveiot.android.theme.R.string.please_check_network);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…ing.please_check_network)");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
        this.u = bottomSheetDialogOneButtonOneTitle;
        String string2 = getString(com.coveiot.android.theme.R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            c…eme.R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentMyDesigns.Q(FragmentMyDesigns.this, view);
            }
        });
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.u;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            bottomSheetDialogOneButtonOneTitle2.show();
        }
    }

    public final void toolTioVisibilty() {
        ((TextView) requireActivity().findViewById(R.id.tvToolTip)).setVisibility(8);
    }

    public final FragmentMyDesignsBinding z() {
        FragmentMyDesignsBinding fragmentMyDesignsBinding = this.t;
        Intrinsics.checkNotNull(fragmentMyDesignsBinding);
        return fragmentMyDesignsBinding;
    }
}
