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
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfacecore.utils.Constants;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.adapter.WatchFaceCategoryAdapter;
import com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter;
import com.coveiot.android.watchfaceui.databinding.FragmentWatchFaceCloudNewBinding;
import com.coveiot.android.watchfaceui.listener.OnClickListener;
import com.coveiot.android.watchfaceui.model.Categories;
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.android.watchfaceui.utils.ViewModelFactory;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceCloudViewModel;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import com.jieli.watchtesttool.tool.upgrade.OTAManager;
import com.touchgui.sdk.TGEventListener;
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
public final class FragmentWatchFaceCloudNew extends BaseFragment implements WatchFacePagingAdapter.OnWatchFaceItemClickListener, OnClickListener, WatchFaceCategoryAdapter.CategoryClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentWatchFaceCloudNewBinding n;
    public WatchFaceCloudViewModel o;
    public ActivityWatchFaceViewModel p;
    @Nullable
    public WatchFaceBean q;
    @Nullable
    public Job r;
    @Nullable
    public WatchFacePagingAdapter s;
    @Nullable
    public WatchFaceBean t;
    public int u;
    public WatchFaceCategoryAdapter w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public String m = FragmentWatchFaceCloudNew.class.getSimpleName();
    @NotNull
    public final ArrayList<Categories> v = new ArrayList<>();

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentWatchFaceCloudNew newInstance() {
            return new FragmentWatchFaceCloudNew();
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
        public final void invoke2(Boolean bool) {
            WatchFaceCloudViewModel watchFaceCloudViewModel = null;
            if (!bool.booleanValue()) {
                FragmentWatchFaceCloudNew.this.q = null;
                if (((ImageView) FragmentWatchFaceCloudNew.this._$_findCachedViewById(R.id.selected_watch_face)) != null) {
                    Glide.with(((ImageView) this.$image).getContext()).clear((View) this.$image);
                }
                WatchFacePagingAdapter adapter = FragmentWatchFaceCloudNew.this.getAdapter();
                Intrinsics.checkNotNull(adapter);
                adapter.setMSelectedWatchFace(null);
                WatchFacePagingAdapter adapter2 = FragmentWatchFaceCloudNew.this.getAdapter();
                Intrinsics.checkNotNull(adapter2);
                adapter2.notifyDataSetChanged();
                return;
            }
            FragmentWatchFaceCloudNew fragmentWatchFaceCloudNew = FragmentWatchFaceCloudNew.this;
            WatchFaceCloudViewModel watchFaceCloudViewModel2 = fragmentWatchFaceCloudNew.o;
            if (watchFaceCloudViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            } else {
                watchFaceCloudViewModel = watchFaceCloudViewModel2;
            }
            fragmentWatchFaceCloudNew.q = watchFaceCloudViewModel.getUserSelectedWatchFace();
            WatchFaceBean watchFaceBean = FragmentWatchFaceCloudNew.this.q;
            if (watchFaceBean != null) {
                FragmentWatchFaceCloudNew fragmentWatchFaceCloudNew2 = FragmentWatchFaceCloudNew.this;
                Object obj = this.$image;
                String previewImageUrl = watchFaceBean.getPreviewImageUrl();
                if (previewImageUrl != null) {
                    Glide.with(fragmentWatchFaceCloudNew2.requireActivity()).m30load(previewImageUrl).into((ImageView) obj);
                    WatchFacePagingAdapter adapter3 = fragmentWatchFaceCloudNew2.getAdapter();
                    Intrinsics.checkNotNull(adapter3);
                    adapter3.setMSelectedWatchFace(watchFaceBean);
                    WatchFacePagingAdapter adapter4 = fragmentWatchFaceCloudNew2.getAdapter();
                    Intrinsics.checkNotNull(adapter4);
                    adapter4.notifyDataSetChanged();
                }
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
            if (!item.booleanValue() || FragmentWatchFaceCloudNew.this.isVisible()) {
                return;
            }
            FragmentWatchFaceCloudNew.this.q = null;
            if (((ImageView) FragmentWatchFaceCloudNew.this._$_findCachedViewById(R.id.selected_watch_face)) != null) {
                Glide.with(((ImageView) this.$image).getContext()).clear((View) this.$image);
            }
            WatchFacePagingAdapter adapter = FragmentWatchFaceCloudNew.this.getAdapter();
            Intrinsics.checkNotNull(adapter);
            adapter.setMSelectedWatchFace(null);
            WatchFacePagingAdapter adapter2 = FragmentWatchFaceCloudNew.this.getAdapter();
            Intrinsics.checkNotNull(adapter2);
            adapter2.notifyDataSetChanged();
        }
    }

    /* loaded from: classes8.dex */
    public static final class c extends Lambda implements Function1<List<Categories>, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<Categories> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<Categories> list) {
            WatchFaceCategoryAdapter watchFaceCategoryAdapter = null;
            if (list != null && list.size() > 0) {
                FragmentWatchFaceCloudNew.this.t(null);
                FragmentWatchFaceCloudNew fragmentWatchFaceCloudNew = FragmentWatchFaceCloudNew.this;
                RecyclerView recyclerView = fragmentWatchFaceCloudNew.o().catogoriesRecycler;
                Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.catogoriesRecycler");
                fragmentWatchFaceCloudNew.visible(recyclerView);
                Categories categories = new Categories(null, null, false, 7, null);
                categories.setTitle("All (" + FragmentWatchFaceCloudNew.this.getTotalItems() + HexStringBuilder.COMMENT_END_CHAR);
                categories.setCategoryId("ALL");
                categories.setNewCategory(false);
                FragmentWatchFaceCloudNew.this.getCategoryList().add(categories);
                FragmentWatchFaceCloudNew.this.getCategoryList().addAll(list);
                WatchFaceCategoryAdapter watchFaceCategoryAdapter2 = FragmentWatchFaceCloudNew.this.w;
                if (watchFaceCategoryAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("watchFaceCategoryAdapter");
                    watchFaceCategoryAdapter2 = null;
                }
                watchFaceCategoryAdapter2.setSelectedCategory(0);
                WatchFaceCategoryAdapter watchFaceCategoryAdapter3 = FragmentWatchFaceCloudNew.this.w;
                if (watchFaceCategoryAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("watchFaceCategoryAdapter");
                } else {
                    watchFaceCategoryAdapter = watchFaceCategoryAdapter3;
                }
                watchFaceCategoryAdapter.setCategoryList(FragmentWatchFaceCloudNew.this.getCategoryList());
                return;
            }
            FragmentWatchFaceCloudNew fragmentWatchFaceCloudNew2 = FragmentWatchFaceCloudNew.this;
            RecyclerView recyclerView2 = fragmentWatchFaceCloudNew2.o().catogoriesRecycler;
            Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.catogoriesRecycler");
            fragmentWatchFaceCloudNew2.gone(recyclerView2);
            FragmentWatchFaceCloudNew.this.t(null);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceCloudNew$pullWatchFace$1", f = "FragmentWatchFaceCloudNew.kt", i = {}, l = {TGEventListener.APP_MENU_STYLE_UPDATED}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $categoryId;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceCloudNew$pullWatchFace$1$1", f = "FragmentWatchFaceCloudNew.kt", i = {}, l = {TGEventListener.REQUEST_UPDATE_STOCK}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes8.dex */
        public static final class a extends SuspendLambda implements Function2<PagingData<WatchFaceBean>, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object L$0;
            public int label;
            public final /* synthetic */ FragmentWatchFaceCloudNew this$0;

            @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceCloudNew$pullWatchFace$1$1$1", f = "FragmentWatchFaceCloudNew.kt", i = {}, l = {296}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceCloudNew$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes8.dex */
            public static final class C0326a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ PagingData<WatchFaceBean> $it;
                public int label;
                public final /* synthetic */ FragmentWatchFaceCloudNew this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0326a(FragmentWatchFaceCloudNew fragmentWatchFaceCloudNew, PagingData<WatchFaceBean> pagingData, Continuation<? super C0326a> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentWatchFaceCloudNew;
                    this.$it = pagingData;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0326a(this.this$0, this.$it, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0326a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            public a(FragmentWatchFaceCloudNew fragmentWatchFaceCloudNew, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentWatchFaceCloudNew;
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
                    C0326a c0326a = new C0326a(this.this$0, (PagingData) this.L$0, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main, c0326a, this) == coroutine_suspended) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(String str, Continuation<? super d> continuation) {
            super(2, continuation);
            this.$categoryId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(this.$categoryId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WatchFaceCloudViewModel watchFaceCloudViewModel = FragmentWatchFaceCloudNew.this.o;
                if (watchFaceCloudViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
                    watchFaceCloudViewModel = null;
                }
                Flow<PagingData<WatchFaceBean>> pullWatchFaces = watchFaceCloudViewModel.pullWatchFaces(this.$categoryId);
                a aVar = new a(FragmentWatchFaceCloudNew.this, null);
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

    @JvmStatic
    @NotNull
    public static final FragmentWatchFaceCloudNew newInstance() {
        return Companion.newInstance();
    }

    public static final void p(FragmentWatchFaceCloudNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = (TextView) this$0.requireActivity().findViewById(R.id.tvToolTip);
        if (textView.getVisibility() == 0) {
            textView.setVisibility(8);
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

    public static final void s(Function1 tmp0, Object obj) {
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

    @Override // com.coveiot.android.watchfaceui.adapter.WatchFaceCategoryAdapter.CategoryClickListener
    public void categoryClick(@NotNull Categories categories, int i) {
        Intrinsics.checkNotNullParameter(categories, "categories");
        WatchFaceCategoryAdapter watchFaceCategoryAdapter = this.w;
        if (watchFaceCategoryAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("watchFaceCategoryAdapter");
            watchFaceCategoryAdapter = null;
        }
        watchFaceCategoryAdapter.setSelectedCategory(i);
        if (kotlin.text.m.equals(categories.getTitle(), "All", false)) {
            t(null);
        } else {
            t(categories.getCategoryId());
        }
    }

    @Nullable
    public final WatchFacePagingAdapter getAdapter() {
        return this.s;
    }

    @NotNull
    public final ArrayList<Categories> getCategoryList() {
        return this.v;
    }

    @Nullable
    public final WatchFaceBean getPrefWatchFaceBean() {
        return this.t;
    }

    public final String getTAG() {
        return this.m;
    }

    public final int getTotalItems() {
        return this.u;
    }

    public final void initData() {
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        WatchFaceBean selectedCloudWatchFace = companion.getInstance(requireActivity).getSelectedCloudWatchFace();
        this.t = selectedCloudWatchFace;
        if (selectedCloudWatchFace != null) {
            WatchFaceCloudViewModel watchFaceCloudViewModel = this.o;
            WatchFaceCloudViewModel watchFaceCloudViewModel2 = null;
            if (watchFaceCloudViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
                watchFaceCloudViewModel = null;
            }
            if (watchFaceCloudViewModel.getUserSelectedWatchFace() == null) {
                WatchFaceCloudViewModel watchFaceCloudViewModel3 = this.o;
                if (watchFaceCloudViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
                } else {
                    watchFaceCloudViewModel2 = watchFaceCloudViewModel3;
                }
                WatchFaceBean watchFaceBean = this.t;
                Intrinsics.checkNotNull(watchFaceBean);
                String uid = watchFaceBean.getUid();
                WatchFaceBean watchFaceBean2 = this.t;
                Intrinsics.checkNotNull(watchFaceBean2);
                String faceId = watchFaceBean2.getFaceId();
                WatchFaceBean watchFaceBean3 = this.t;
                Intrinsics.checkNotNull(watchFaceBean3);
                String faceType = watchFaceBean3.getFaceType();
                WatchFaceBean watchFaceBean4 = this.t;
                Intrinsics.checkNotNull(watchFaceBean4);
                String title = watchFaceBean4.getTitle();
                WatchFaceBean watchFaceBean5 = this.t;
                Intrinsics.checkNotNull(watchFaceBean5);
                String downloadUrl = watchFaceBean5.getDownloadUrl();
                WatchFaceBean watchFaceBean6 = this.t;
                Intrinsics.checkNotNull(watchFaceBean6);
                String fileType = watchFaceBean6.getFileType();
                WatchFaceBean watchFaceBean7 = this.t;
                Intrinsics.checkNotNull(watchFaceBean7);
                String fileMd5Hash = watchFaceBean7.getFileMd5Hash();
                WatchFaceBean watchFaceBean8 = this.t;
                Intrinsics.checkNotNull(watchFaceBean8);
                String previewImageUrl = watchFaceBean8.getPreviewImageUrl();
                WatchFaceBean watchFaceBean9 = this.t;
                Intrinsics.checkNotNull(watchFaceBean9);
                List<String> tags = watchFaceBean9.getTags();
                WatchFaceBean watchFaceBean10 = this.t;
                Intrinsics.checkNotNull(watchFaceBean10);
                Integer faceResId = watchFaceBean10.getFaceResId();
                WatchFaceBean watchFaceBean11 = this.t;
                Intrinsics.checkNotNull(watchFaceBean11);
                watchFaceCloudViewModel2.setUserSelectedWatchFace(new WatchFaceBean(uid, faceId, faceType, title, downloadUrl, fileType, fileMd5Hash, previewImageUrl, tags, null, faceResId, watchFaceBean11.getFacePosition(), null, false, null, 29184, null));
            }
        }
    }

    public final FragmentWatchFaceCloudNewBinding o() {
        FragmentWatchFaceCloudNewBinding fragmentWatchFaceCloudNewBinding = this.n;
        Intrinsics.checkNotNull(fragmentWatchFaceCloudNewBinding);
        return fragmentWatchFaceCloudNewBinding;
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onAppliedClicked() {
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.n = FragmentWatchFaceCloudNewBinding.inflate(inflater, viewGroup, false);
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
        if (SessionManager.getInstance(requireContext()).getRoundedImage().equals(0)) {
            findViewById = requireActivity().findViewById(R.id.selectedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…>(R.id.selectedWatchFace)");
        } else {
            findViewById = requireActivity().findViewById(R.id.selectedRoundedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…selectedRoundedWatchFace)");
        }
        TextView textView = (TextView) requireActivity().findViewById(R.id.applyToWatch);
        WatchFaceCloudViewModel watchFaceCloudViewModel = this.o;
        WatchFaceCloudViewModel watchFaceCloudViewModel2 = null;
        if (watchFaceCloudViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel = null;
        }
        watchFaceCloudViewModel.setUserSelectedWatchFace(watchFaceBean);
        this.q = watchFaceBean;
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.p;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.setWatchFacePushType(1);
        Glide.with(requireActivity()).m30load(watchFaceBean.getPreviewImageUrl()).into((ImageView) findViewById);
        String uid = watchFaceBean.getUid();
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        WatchFaceBean lastSelectedWatchFace = companion.getInstance(requireContext).getLastSelectedWatchFace();
        if (Intrinsics.areEqual(uid, lastSelectedWatchFace != null ? lastSelectedWatchFace.getUid() : null)) {
            WatchFaceCloudViewModel watchFaceCloudViewModel3 = this.o;
            if (watchFaceCloudViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            } else {
                watchFaceCloudViewModel2 = watchFaceCloudViewModel3;
            }
            watchFaceCloudViewModel2.showSaveBtn(false);
            textView.setEnabled(false);
        } else {
            WatchFaceCloudViewModel watchFaceCloudViewModel4 = this.o;
            if (watchFaceCloudViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            } else {
                watchFaceCloudViewModel2 = watchFaceCloudViewModel4;
            }
            watchFaceCloudViewModel2.showSaveBtn(true);
            textView.setEnabled(true);
        }
        ((TextView) requireActivity().findViewById(R.id.tvToolTip)).setVisibility(8);
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onSaveClicked() {
        if (this.p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.p;
        WatchFaceCloudViewModel watchFaceCloudViewModel = null;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        if (activityWatchFaceViewModel.getWatchFacePushType() == 1) {
            if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                BleApiManager.getInstance(requireActivity()).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceCloudNew$onSaveClicked$1
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
                            Context requireContext = FragmentWatchFaceCloudNew.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            if (utils.isRuggedDevice(requireContext)) {
                                intValue = utils.getBatteryPercentageForMatrix(intValue);
                            }
                            LogHelper.i("FragmentWatchFaceCloud", "batteryLevel -- " + intValue);
                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                            Context requireContext2 = FragmentWatchFaceCloudNew.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                            if (companion.isCurrentFirmwareHasIssueWithBatteryPercentage(requireContext2) && intValue <= 0) {
                                FragmentWatchFaceCloudNew.this.saveCloudWatchface();
                                return;
                            }
                            Context requireContext3 = FragmentWatchFaceCloudNew.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                            if (intValue >= utils.getMinBatteryPerForWatchFaceUpload(requireContext3)) {
                                FragmentWatchFaceCloudNew.this.saveCloudWatchface();
                                return;
                            }
                            FragmentActivity requireActivity = FragmentWatchFaceCloudNew.this.requireActivity();
                            FragmentWatchFaceCloudNew fragmentWatchFaceCloudNew = FragmentWatchFaceCloudNew.this;
                            int i = R.string.make_sure_battery;
                            StringBuilder sb = new StringBuilder();
                            Context requireContext4 = FragmentWatchFaceCloudNew.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                            sb.append(utils.getMinBatteryPerForWatchFaceUpload(requireContext4));
                            sb.append(" %");
                            Toast.makeText(requireActivity, fragmentWatchFaceCloudNew.getString(i, sb.toString()), 1).show();
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
        Toast.makeText(requireActivity(), "Please choose your watch face", 1).show();
        WatchFaceCloudViewModel watchFaceCloudViewModel2 = this.o;
        if (watchFaceCloudViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
        } else {
            watchFaceCloudViewModel = watchFaceCloudViewModel2;
        }
        watchFaceCloudViewModel.onFail();
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
        Glide.with(requireActivity()).m30load(watchFaceBean.getPreviewImageUrl()).into((ImageView) findViewById);
        WatchFaceBean watchFaceBean2 = this.t;
        if (watchFaceBean2 != null) {
            Intrinsics.checkNotNull(watchFaceBean2);
            if (watchFaceBean2.getPreviewImageUrl() == null) {
                WatchFaceBean watchFaceBean3 = this.t;
                Intrinsics.checkNotNull(watchFaceBean3);
                if (Intrinsics.areEqual(watchFaceBean3.getUid(), watchFaceBean.getUid())) {
                    WatchFaceBean watchFaceBean4 = this.t;
                    Intrinsics.checkNotNull(watchFaceBean4);
                    watchFaceBean4.setPreviewImageUrl(watchFaceBean.getPreviewImageUrl());
                    WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    companion.getInstance(requireActivity).saveSelectedCloudWatchFace(this.t);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        View findViewById;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        o().watchFaceRecycler.setHasFixedSize(true);
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        ViewModel viewModel = ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(WatchFaceCloudViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            requireA…oudViewModel::class.java)");
        this.o = (WatchFaceCloudViewModel) viewModel;
        FragmentActivity requireActivity3 = requireActivity();
        FragmentActivity requireActivity4 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
        ViewModel viewModel2 = ViewModelProviders.of(requireActivity3, new ViewModelFactory(requireActivity4)).get(ActivityWatchFaceViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(\n            requireA…aceViewModel::class.java)");
        this.p = (ActivityWatchFaceViewModel) viewModel2;
        WatchFaceCloudViewModel watchFaceCloudViewModel = null;
        if (AppUtils.isNetConnected(getContext())) {
            showProgress(false);
            WatchFaceCloudViewModel watchFaceCloudViewModel2 = this.o;
            if (watchFaceCloudViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
                watchFaceCloudViewModel2 = null;
            }
            FragmentActivity requireActivity5 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity()");
            watchFaceCloudViewModel2.getCloudWatchFaceLists(requireActivity5, new SuccessResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceCloudNew$onViewCreated$1

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceCloudNew$onViewCreated$1$onError$1", f = "FragmentWatchFaceCloudNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes8.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ FragmentWatchFaceCloudNew this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(FragmentWatchFaceCloudNew fragmentWatchFaceCloudNew, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = fragmentWatchFaceCloudNew;
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

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceCloudNew$onViewCreated$1$onSuccess$1", f = "FragmentWatchFaceCloudNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes8.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ FragmentWatchFaceCloudNew this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(FragmentWatchFaceCloudNew fragmentWatchFaceCloudNew, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = fragmentWatchFaceCloudNew;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, continuation);
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
                                FragmentWatchFaceCloudNew fragmentWatchFaceCloudNew = this.this$0;
                                Integer cloudWatchFaceItems = SessionManager.getInstance(fragmentWatchFaceCloudNew.requireContext()).getCloudWatchFaceItems();
                                Intrinsics.checkNotNullExpressionValue(cloudWatchFaceItems, "getInstance(requireContext()).cloudWatchFaceItems");
                                fragmentWatchFaceCloudNew.setTotalItems(cloudWatchFaceItems.intValue());
                                WatchFaceCloudViewModel watchFaceCloudViewModel = this.this$0.o;
                                if (watchFaceCloudViewModel == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
                                    watchFaceCloudViewModel = null;
                                }
                                watchFaceCloudViewModel.watchFaceCategory();
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.theme.SuccessResultListener
                public void onError(@Nullable String str) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentWatchFaceCloudNew.this), Dispatchers.getMain(), null, new a(FragmentWatchFaceCloudNew.this, null), 2, null);
                }

                @Override // com.coveiot.android.theme.SuccessResultListener
                public void onSuccess() {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentWatchFaceCloudNew.this), Dispatchers.getMain(), null, new b(FragmentWatchFaceCloudNew.this, null), 2, null);
                }
            });
        } else {
            dismissProgress();
            Toast.makeText(getContext(), getString(R.string.no_internet_connection), 0).show();
        }
        if (SessionManager.getInstance(requireContext()).getRoundedImage().equals(0)) {
            findViewById = requireActivity().findViewById(R.id.selectedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…>(R.id.selectedWatchFace)");
        } else {
            findViewById = requireActivity().findViewById(R.id.selectedRoundedWatchFace);
            Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findVi…selectedRoundedWatchFace)");
        }
        FragmentActivity requireActivity6 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity6, "requireActivity()");
        WatchFacePagingAdapter watchFacePagingAdapter = new WatchFacePagingAdapter(requireActivity6, 1, null);
        this.s = watchFacePagingAdapter;
        Intrinsics.checkNotNull(watchFacePagingAdapter);
        watchFacePagingAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        WatchFacePagingAdapter watchFacePagingAdapter2 = this.s;
        Intrinsics.checkNotNull(watchFacePagingAdapter2);
        watchFacePagingAdapter2.setOnWatchFaceClickListener(this, null);
        int i = R.id.watchFace_recycler;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new GridLayoutManager(requireActivity(), 3));
        ((RecyclerView) _$_findCachedViewById(i)).setAdapter(this.s);
        o().rootLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.a2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWatchFaceCloudNew.p(FragmentWatchFaceCloudNew.this, view2);
            }
        });
        initData();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.w = new WatchFaceCategoryAdapter(requireContext, this);
        o().catogoriesRecycler.setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
        o().catogoriesRecycler.setItemAnimator(null);
        RecyclerView recyclerView = o().catogoriesRecycler;
        WatchFaceCategoryAdapter watchFaceCategoryAdapter = this.w;
        if (watchFaceCategoryAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("watchFaceCategoryAdapter");
            watchFaceCategoryAdapter = null;
        }
        recyclerView.setAdapter(watchFaceCategoryAdapter);
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.p;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        LiveData<Boolean> isCloudWatchFaceSelected = activityWatchFaceViewModel.getIsCloudWatchFaceSelected();
        if (isCloudWatchFaceSelected != null) {
            FragmentActivity requireActivity7 = requireActivity();
            final a aVar = new a(findViewById);
            isCloudWatchFaceSelected.observe(requireActivity7, new Observer() { // from class: com.coveiot.android.watchfaceui.fragments.d2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentWatchFaceCloudNew.q(Function1.this, obj);
                }
            });
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel2 = this.p;
        if (activityWatchFaceViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel2 = null;
        }
        LiveData<Boolean> isDiyWatchFaceSelected = activityWatchFaceViewModel2.getIsDiyWatchFaceSelected();
        if (isDiyWatchFaceSelected != null) {
            FragmentActivity requireActivity8 = requireActivity();
            final b bVar = new b(findViewById);
            isDiyWatchFaceSelected.observe(requireActivity8, new Observer() { // from class: com.coveiot.android.watchfaceui.fragments.b2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentWatchFaceCloudNew.r(Function1.this, obj);
                }
            });
        }
        WatchFaceBean watchFaceBean = this.q;
        if (watchFaceBean != null) {
            Intrinsics.checkNotNull(watchFaceBean);
            if (watchFaceBean.getPreviewImageUrl() != null) {
                RequestManager with = Glide.with(requireActivity());
                WatchFaceBean watchFaceBean2 = this.q;
                Intrinsics.checkNotNull(watchFaceBean2);
                with.m30load(watchFaceBean2.getPreviewImageUrl()).into((ImageView) findViewById);
            }
            WatchFacePagingAdapter watchFacePagingAdapter3 = this.s;
            Intrinsics.checkNotNull(watchFacePagingAdapter3);
            watchFacePagingAdapter3.setMSelectedWatchFace(this.q);
        }
        WatchFaceCloudViewModel watchFaceCloudViewModel3 = this.o;
        if (watchFaceCloudViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
        } else {
            watchFaceCloudViewModel = watchFaceCloudViewModel3;
        }
        MutableLiveData<List<Categories>> categoryTitle = watchFaceCloudViewModel.getCategoryTitle();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final c cVar = new c();
        categoryTitle.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.watchfaceui.fragments.c2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentWatchFaceCloudNew.s(Function1.this, obj);
            }
        });
    }

    public final void saveCloudWatchface() {
        String modelNumber;
        if (this.p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.p;
        WatchFaceCloudViewModel watchFaceCloudViewModel = null;
        WatchFaceCloudViewModel watchFaceCloudViewModel2 = null;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        if (activityWatchFaceViewModel.getWatchFacePushType() == 1) {
            if (this.o == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            }
            WatchFaceCloudViewModel watchFaceCloudViewModel3 = this.o;
            if (watchFaceCloudViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
                watchFaceCloudViewModel3 = null;
            }
            if (watchFaceCloudViewModel3.getUserSelectedWatchFace() != null) {
                WatchFaceCloudViewModel watchFaceCloudViewModel4 = this.o;
                if (watchFaceCloudViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
                    watchFaceCloudViewModel4 = null;
                }
                WatchFaceBean userSelectedWatchFace = watchFaceCloudViewModel4.getUserSelectedWatchFace();
                Intrinsics.checkNotNull(userSelectedWatchFace);
                if (userSelectedWatchFace.getDownloadUrl() != null) {
                    Utils utils = Utils.INSTANCE;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    String str = utils.isMatrixDevice(requireContext) ? Constants.WATCH_FACE_FILE_NAME_MATRIX : "watchface";
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    if (companion.isPS1Device(requireContext2)) {
                        WatchFaceCloudViewModel watchFaceCloudViewModel5 = this.o;
                        if (watchFaceCloudViewModel5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
                            watchFaceCloudViewModel5 = null;
                        }
                        WatchFaceBean userSelectedWatchFace2 = watchFaceCloudViewModel5.getUserSelectedWatchFace();
                        Intrinsics.checkNotNull(userSelectedWatchFace2);
                        String downloadUrl = userSelectedWatchFace2.getDownloadUrl();
                        Intrinsics.checkNotNull(downloadUrl);
                        if (kotlin.text.m.endsWith$default(downloadUrl, ".bin", false, 2, null)) {
                            str = str + ".bin";
                        } else {
                            WatchFaceCloudViewModel watchFaceCloudViewModel6 = this.o;
                            if (watchFaceCloudViewModel6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
                                watchFaceCloudViewModel6 = null;
                            }
                            WatchFaceBean userSelectedWatchFace3 = watchFaceCloudViewModel6.getUserSelectedWatchFace();
                            Intrinsics.checkNotNull(userSelectedWatchFace3);
                            String downloadUrl2 = userSelectedWatchFace3.getDownloadUrl();
                            Intrinsics.checkNotNull(downloadUrl2);
                            if (kotlin.text.m.endsWith$default(downloadUrl2, OTAManager.OTA_ZIP_SUFFIX, false, 2, null)) {
                                str = str + OTAManager.OTA_ZIP_SUFFIX;
                            }
                        }
                    }
                    WatchFaceCloudViewModel watchFaceCloudViewModel7 = this.o;
                    if (watchFaceCloudViewModel7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
                        watchFaceCloudViewModel7 = null;
                    }
                    WatchFaceCloudViewModel watchFaceCloudViewModel8 = this.o;
                    if (watchFaceCloudViewModel8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
                        watchFaceCloudViewModel8 = null;
                    }
                    WatchFaceBean userSelectedWatchFace4 = watchFaceCloudViewModel8.getUserSelectedWatchFace();
                    Intrinsics.checkNotNull(userSelectedWatchFace4);
                    String downloadUrl3 = userSelectedWatchFace4.getDownloadUrl();
                    Intrinsics.checkNotNull(downloadUrl3);
                    watchFaceCloudViewModel7.downloadWatchFaceFromServerSendToWatch(str, downloadUrl3, this.q);
                    AnalyticsLog analyticsLog = new AnalyticsLog();
                    analyticsLog.setEventName(FirebaseEventParams.EventName.WATCHFASE_SAVE.getValue());
                    DeviceModelBean deviceModelBean = SessionManager.getInstance(getContext()).getDeviceModelBean();
                    if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
                        DeviceModelBean deviceModelBean2 = SessionManager.getInstance(getContext()).getDeviceModelBean();
                        modelNumber = deviceModelBean2 != null ? deviceModelBean2.getName() : null;
                    } else {
                        FragmentActivity requireActivity = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        modelNumber = utils.getModelNumber(requireActivity);
                    }
                    analyticsLog.setCVPrevScreenName(modelNumber + "_features");
                    analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.APPLY_WATCHFACE.getValue());
                    HashMap<String, String> hashMap = new HashMap<>();
                    String value = FirebaseEventParams.MetaData.CV_WATCHFACE_ID.getValue();
                    WatchFaceBean watchFaceBean = this.q;
                    hashMap.put(value, String.valueOf(watchFaceBean != null ? watchFaceBean.getFaceId() : null));
                    String value2 = FirebaseEventParams.MetaData.CV_WATCHFACE_FILE_NAME.getValue();
                    WatchFaceBean watchFaceBean2 = this.q;
                    String downloadUrl4 = watchFaceBean2 != null ? watchFaceBean2.getDownloadUrl() : null;
                    Intrinsics.checkNotNull(downloadUrl4);
                    hashMap.put(value2, URI.create(downloadUrl4).getPath());
                    String value3 = FirebaseEventParams.MetaData.CV_WATCHFACE_CATEGORY.getValue();
                    WatchFaceBean watchFaceBean3 = this.q;
                    String lowerCase = String.valueOf(watchFaceBean3 != null ? watchFaceBean3.getFaceType() : null).toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                    hashMap.put(value3, lowerCase);
                    analyticsLog.setMapData(hashMap);
                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    hashMap2.put(CleverTapConstants.CustomEventProperties.WATCH_FACE_TYPE.getValue(), CleverTapConstants.CustomEventValues.CLOUD.getValue());
                    hashMap2.put(CleverTapConstants.CustomEventProperties.UPDATE_LOCATION.getValue(), CleverTapConstants.CustomEventValues.WATCH_FACES_PAGE.getValue());
                    Context requireContext3 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    hashMap2.putAll(companion.getDeviceId(requireContext3));
                    Context requireContext4 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                    hashMap2.putAll(companion.getWatchDetails(requireContext4));
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.WF_UPDATED.getValue(), hashMap2);
                    return;
                }
            }
            Toast.makeText(requireActivity(), "Please choose your watch face", 1).show();
            WatchFaceCloudViewModel watchFaceCloudViewModel9 = this.o;
            if (watchFaceCloudViewModel9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            } else {
                watchFaceCloudViewModel2 = watchFaceCloudViewModel9;
            }
            watchFaceCloudViewModel2.onFail();
            return;
        }
        WatchFaceCloudViewModel watchFaceCloudViewModel10 = this.o;
        if (watchFaceCloudViewModel10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
        } else {
            watchFaceCloudViewModel = watchFaceCloudViewModel10;
        }
        watchFaceCloudViewModel.onFail();
    }

    public final void setAdapter(@Nullable WatchFacePagingAdapter watchFacePagingAdapter) {
        this.s = watchFacePagingAdapter;
    }

    public final void setPrefWatchFaceBean(@Nullable WatchFaceBean watchFaceBean) {
        this.t = watchFaceBean;
    }

    public final void setTAG(String str) {
        this.m = str;
    }

    public final void setTotalItems(int i) {
        this.u = i;
    }

    public final void t(String str) {
        Job e;
        if (AppUtils.isNetConnected(requireActivity())) {
            if (SessionManager.getInstance(getContext()).getBleDeviceInfo() == null || ((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(getContext()).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision() == null) {
                return;
            }
            Job job = this.r;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            e = kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new d(str, null), 2, null);
            this.r = e;
            return;
        }
        Toast.makeText(requireActivity(), getString(R.string.no_internet_connection), 0).show();
    }
}
