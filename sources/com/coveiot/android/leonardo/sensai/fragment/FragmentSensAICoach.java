package com.coveiot.android.leonardo.sensai.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagingData;
import androidx.paging.PagingDataTransforms;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.activitymodes.workoutVideos.activities.YoutubePlayerNewActivity;
import com.coveiot.android.activitymodes.workoutVideos.adapters.ActivityWorkoutVideosAdapter;
import com.coveiot.android.activitymodes.workoutVideos.adapters.ActivityWorkoutVideosCategoryAdapter;
import com.coveiot.android.activitymodes.workoutVideos.fragments.CultFitVideoFragment;
import com.coveiot.android.activitymodes.workoutVideos.models.WorkoutVideosBean;
import com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentSensAiCoachBinding;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.coveaccess.workoutvideos.model.VideoType;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentSensAICoach extends BaseFragment implements ActivityWorkoutVideosCategoryAdapter.ItemClickListener, ActivityWorkoutVideosAdapter.ItemClickListener {
    public ActivityWorkoutVideosAdapter n;
    @Nullable
    public Job o;
    public ActivityWorkoutVideosCategoryAdapter p;
    public ViewModelWorkoutVideos q;
    @Nullable
    public String r;
    @Nullable
    public String s;
    @Nullable
    public FragmentSensAiCoachBinding u;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentSensAICoach";
    public boolean t = true;

    @DebugMetadata(c = "com.coveiot.android.leonardo.sensai.fragment.FragmentSensAICoach$pullWorkoutVideos$1", f = "FragmentSensAICoach.kt", i = {}, l = {188}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $category;
        public final /* synthetic */ String $searchVideo;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.leonardo.sensai.fragment.FragmentSensAICoach$pullWorkoutVideos$1$1", f = "FragmentSensAICoach.kt", i = {}, l = {CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.leonardo.sensai.fragment.FragmentSensAICoach$a$a  reason: collision with other inner class name */
        /* loaded from: classes5.dex */
        public static final class C0281a extends SuspendLambda implements Function2<PagingData<WorkoutVideosBean>, Continuation<? super Unit>, Object> {
            public final /* synthetic */ String $category;
            public final /* synthetic */ String $searchVideo;
            public /* synthetic */ Object L$0;
            public int label;
            public final /* synthetic */ FragmentSensAICoach this$0;

            @DebugMetadata(c = "com.coveiot.android.leonardo.sensai.fragment.FragmentSensAICoach$pullWorkoutVideos$1$1$1", f = "FragmentSensAICoach.kt", i = {}, l = {200}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.leonardo.sensai.fragment.FragmentSensAICoach$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes5.dex */
            public static final class C0282a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ String $category;
                public final /* synthetic */ PagingData<WorkoutVideosBean> $it;
                public final /* synthetic */ String $searchVideo;
                public int label;
                public final /* synthetic */ FragmentSensAICoach this$0;

                @DebugMetadata(c = "com.coveiot.android.leonardo.sensai.fragment.FragmentSensAICoach$pullWorkoutVideos$1$1$1$searchedVideoList$1", f = "FragmentSensAICoach.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.leonardo.sensai.fragment.FragmentSensAICoach$a$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes5.dex */
                public static final class C0283a extends SuspendLambda implements Function2<WorkoutVideosBean, Continuation<? super Boolean>, Object> {
                    public final /* synthetic */ String $searchVideo;
                    public /* synthetic */ Object L$0;
                    public int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0283a(String str, Continuation<? super C0283a> continuation) {
                        super(2, continuation);
                        this.$searchVideo = str;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        C0283a c0283a = new C0283a(this.$searchVideo, continuation);
                        c0283a.L$0 = obj;
                        return c0283a;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull WorkoutVideosBean workoutVideosBean, @Nullable Continuation<? super Boolean> continuation) {
                        return ((C0283a) create(workoutVideosBean, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            String title = ((WorkoutVideosBean) this.L$0).getTitle();
                            Intrinsics.checkNotNull(title);
                            String lowerCase = title.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                            return Boxing.boxBoolean(StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) this.$searchVideo, false, 2, (Object) null));
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0282a(PagingData<WorkoutVideosBean> pagingData, String str, FragmentSensAICoach fragmentSensAICoach, String str2, Continuation<? super C0282a> continuation) {
                    super(2, continuation);
                    this.$it = pagingData;
                    this.$category = str;
                    this.this$0 = fragmentSensAICoach;
                    this.$searchVideo = str2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0282a(this.$it, this.$category, this.this$0, this.$searchVideo, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0282a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter = null;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        PagingData filter = PagingDataTransforms.filter(this.$it, new C0283a(this.$searchVideo, null));
                        if (this.$category == null) {
                            this.this$0.u(true);
                        } else {
                            FragmentSensAICoach fragmentSensAICoach = this.this$0;
                            fragmentSensAICoach.u(Intrinsics.areEqual(SessionManager.getInstance(fragmentSensAICoach.requireContext()).getSensAIVideoCategoryPassed(), this.$category));
                        }
                        ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter2 = this.this$0.n;
                        if (activityWorkoutVideosAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activityWorkoutVideosAdapter");
                            activityWorkoutVideosAdapter2 = null;
                        }
                        this.label = 1;
                        if (activityWorkoutVideosAdapter2.submitData(filter, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter3 = this.this$0.n;
                    if (activityWorkoutVideosAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activityWorkoutVideosAdapter");
                    } else {
                        activityWorkoutVideosAdapter = activityWorkoutVideosAdapter3;
                    }
                    activityWorkoutVideosAdapter.refresh();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0281a(String str, FragmentSensAICoach fragmentSensAICoach, String str2, Continuation<? super C0281a> continuation) {
                super(2, continuation);
                this.$category = str;
                this.this$0 = fragmentSensAICoach;
                this.$searchVideo = str2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                C0281a c0281a = new C0281a(this.$category, this.this$0, this.$searchVideo, continuation);
                c0281a.L$0 = obj;
                return c0281a;
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull PagingData<WorkoutVideosBean> pagingData, @Nullable Continuation<? super Unit> continuation) {
                return ((C0281a) create(pagingData, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PagingData pagingData = (PagingData) this.L$0;
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    C0282a c0282a = new C0282a(pagingData, this.$category, this.this$0, this.$searchVideo, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main, c0282a, this) == coroutine_suspended) {
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
        public a(String str, String str2, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$category = str;
            this.$searchVideo = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$category, this.$searchVideo, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ViewModelWorkoutVideos viewModelWorkoutVideos = FragmentSensAICoach.this.q;
                if (viewModelWorkoutVideos == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
                    viewModelWorkoutVideos = null;
                }
                Flow<PagingData<WorkoutVideosBean>> pullSensAIVideos = viewModelWorkoutVideos.pullSensAIVideos(this.$category);
                C0281a c0281a = new C0281a(this.$category, FragmentSensAICoach.this, this.$searchVideo, null);
                this.label = 1;
                if (FlowKt.collectLatest(pullSensAIVideos, c0281a, this) == coroutine_suspended) {
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

    public static final void q(FragmentSensAICoach this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (bool != null && bool.booleanValue()) {
            BaseFragment.showProgress$default(this$0, false, 1, null);
        } else {
            this$0.dismissProgress();
        }
    }

    public static final void r(FragmentSensAICoach this$0, List categoryList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityWorkoutVideosCategoryAdapter activityWorkoutVideosCategoryAdapter = this$0.p;
        if (activityWorkoutVideosCategoryAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityWorkoutVideosCategoryAdapter");
            activityWorkoutVideosCategoryAdapter = null;
        }
        Intrinsics.checkNotNullExpressionValue(categoryList, "categoryList");
        activityWorkoutVideosCategoryAdapter.setData(categoryList);
        this$0.dismissProgress();
        this$0.s(null, "");
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

    @NotNull
    public final String getTAG() {
        return this.m;
    }

    public final void m() {
        if (AppUtils.isNetConnected(requireActivity())) {
            ViewModelWorkoutVideos viewModelWorkoutVideos = null;
            BaseFragment.showProgress$default(this, false, 1, null);
            ViewModelWorkoutVideos viewModelWorkoutVideos2 = this.q;
            if (viewModelWorkoutVideos2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
            } else {
                viewModelWorkoutVideos = viewModelWorkoutVideos2;
            }
            viewModelWorkoutVideos.getSensAICoachCategory();
            return;
        }
        Toast.makeText(requireActivity(), getString(R.string.no_internet_connection), 0).show();
    }

    public final FragmentSensAiCoachBinding n() {
        FragmentSensAiCoachBinding fragmentSensAiCoachBinding = this.u;
        Intrinsics.checkNotNull(fragmentSensAiCoachBinding);
        return fragmentSensAiCoachBinding;
    }

    public final void o(String str) {
        this.t = str == null;
        this.r = str;
        s(str, "");
        n().etSearchVideo.setText("");
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.u = FragmentSensAiCoachBinding.inflate(inflater, viewGroup, false);
        return n().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.activitymodes.workoutVideos.adapters.ActivityWorkoutVideosCategoryAdapter.ItemClickListener
    public void onItemClick(@Nullable String str) {
        o(str);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.t) {
            u(true);
        } else {
            u(Intrinsics.areEqual(SessionManager.getInstance(requireContext()).getSensAIVideoCategoryPassed(), this.s));
        }
        t();
    }

    @Override // com.coveiot.android.activitymodes.workoutVideos.adapters.ActivityWorkoutVideosAdapter.ItemClickListener
    public void onVideosItemClick(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable List<String> list) {
        if (AppUtils.isNetConnected(requireActivity())) {
            Intent intent = new Intent(requireActivity(), YoutubePlayerNewActivity.class);
            intent.putExtra(WorkoutConstants.YOUTUBE_VIDEO_ID, str);
            intent.putExtra(WorkoutConstants.VIDEO_ID, str2);
            intent.putExtra(WorkoutConstants.VIDEO_NAME, str3);
            intent.putExtra(WorkoutConstants.WORKOUT_TYPE, VideoType.SENSE_AI_COACH.getVideoType());
            startActivityForResult(intent, 121);
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.CV_VIDEO_TAP.getValue());
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
            analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.WORKOUT_VIDEOS.getValue());
            HashMap<String, String> hashMap = new HashMap<>();
            String value = FirebaseEventParams.MetaData.CV_VIDEO_NAME.getValue();
            String lowerCase = String.valueOf(str3).toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            hashMap.put(value, lowerCase);
            String value2 = FirebaseEventParams.MetaData.CV_VIDEO_DURATION.getValue();
            WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
            Intrinsics.checkNotNull(num);
            hashMap.put(value2, workoutUtils.getFormattedDuration(num.intValue()));
            String value3 = FirebaseEventParams.MetaData.CV_VIDEO_ID.getValue();
            String lowerCase2 = String.valueOf(str2).toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
            hashMap.put(value3, lowerCase2);
            if (list != null) {
                int size = list.size();
                String str4 = "";
                for (int i = 0; i < size; i++) {
                    String str5 = list.get(i);
                    this.s = str5;
                    SessionManager.getInstance(requireContext()).saveLastSensAIVideoCategoryPassed(str5);
                    if (StringsKt__StringsKt.contains$default((CharSequence) str5, (CharSequence) "_", false, 2, (Object) null)) {
                        str5 = kotlin.text.m.replace$default(str5, "_", HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null);
                    }
                    if (list.size() > 1) {
                        str4 = str4.length() > 0 ? str4 + ',' + str5 : str4 + str5;
                    } else {
                        str4 = str4 + str5;
                    }
                }
                String value4 = FirebaseEventParams.MetaData.CV_VIDEO_CATEGORY.getValue();
                String lowerCase3 = str4.toString().toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                hashMap.put(value4, lowerCase3);
            }
            analyticsLog.setMapData(hashMap);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            return;
        }
        Toast.makeText(requireContext(), getString(R.string.no_internet_connection), 0).show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.q = (ViewModelWorkoutVideos) ViewModelProviders.of(requireActivity()).get(ViewModelWorkoutVideos.class);
        p();
        n().etSearchVideo.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.sensai.fragment.FragmentSensAICoach$onViewCreated$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@NotNull Editable s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@NotNull CharSequence s, int i, int i2, int i3) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@NotNull CharSequence searchVideo, int i, int i2, int i3) {
                String str;
                String str2;
                Intrinsics.checkNotNullParameter(searchVideo, "searchVideo");
                if (searchVideo.length() > 2) {
                    FragmentSensAICoach fragmentSensAICoach = FragmentSensAICoach.this;
                    str2 = fragmentSensAICoach.r;
                    String lowerCase = searchVideo.toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    fragmentSensAICoach.s(str2, lowerCase);
                    return;
                }
                if (searchVideo.length() == 0) {
                    FragmentSensAICoach fragmentSensAICoach2 = FragmentSensAICoach.this;
                    str = fragmentSensAICoach2.r;
                    fragmentSensAICoach2.s(str, "");
                }
            }
        });
    }

    public final void p() {
        n().categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
        m();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.n = new ActivityWorkoutVideosAdapter(requireContext);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        ActivityWorkoutVideosCategoryAdapter activityWorkoutVideosCategoryAdapter = new ActivityWorkoutVideosCategoryAdapter(requireContext2);
        this.p = activityWorkoutVideosCategoryAdapter;
        activityWorkoutVideosCategoryAdapter.setItemClickListener(this);
        ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter = this.n;
        ViewModelWorkoutVideos viewModelWorkoutVideos = null;
        if (activityWorkoutVideosAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityWorkoutVideosAdapter");
            activityWorkoutVideosAdapter = null;
        }
        activityWorkoutVideosAdapter.setItemClickListener(this);
        RecyclerView recyclerView = n().categoryRecyclerView;
        ActivityWorkoutVideosCategoryAdapter activityWorkoutVideosCategoryAdapter2 = this.p;
        if (activityWorkoutVideosCategoryAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityWorkoutVideosCategoryAdapter");
            activityWorkoutVideosCategoryAdapter2 = null;
        }
        recyclerView.setAdapter(activityWorkoutVideosCategoryAdapter2);
        n().videosRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), 1, false));
        ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter2 = this.n;
        if (activityWorkoutVideosAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityWorkoutVideosAdapter");
            activityWorkoutVideosAdapter2 = null;
        }
        activityWorkoutVideosAdapter2.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        RecyclerView recyclerView2 = n().videosRecyclerView;
        ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter3 = this.n;
        if (activityWorkoutVideosAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityWorkoutVideosAdapter");
            activityWorkoutVideosAdapter3 = null;
        }
        recyclerView2.setAdapter(activityWorkoutVideosAdapter3);
        ViewModelWorkoutVideos viewModelWorkoutVideos2 = this.q;
        if (viewModelWorkoutVideos2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
            viewModelWorkoutVideos2 = null;
        }
        viewModelWorkoutVideos2.getProgressLiveData().observe(requireActivity(), new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.u
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentSensAICoach.q(FragmentSensAICoach.this, (Boolean) obj);
            }
        });
        ViewModelWorkoutVideos viewModelWorkoutVideos3 = this.q;
        if (viewModelWorkoutVideos3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
        } else {
            viewModelWorkoutVideos = viewModelWorkoutVideos3;
        }
        viewModelWorkoutVideos.getGetCategoriesLiveData().observe(requireActivity(), new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.v
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentSensAICoach.r(FragmentSensAICoach.this, (List) obj);
            }
        });
    }

    public final void s(String str, String str2) {
        Job e;
        if (AppUtils.isNetConnected(requireActivity())) {
            Job job = this.o;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            e = kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new a(str, str2, null), 2, null);
            this.o = e;
            return;
        }
        Toast.makeText(requireContext(), getString(R.string.no_internet_connection), 0).show();
    }

    public final void t() {
        if (SessionManager.getInstance(requireContext()).getLastSensAIVideoName() != null) {
            CultFitVideoFragment newInstance = CultFitVideoFragment.Companion.newInstance(false);
            FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_cult_fit_last_video, newInstance).commit();
            return;
        }
        u(false);
    }

    public final void u(boolean z) {
        if (z) {
            n().fragmentContainerCultFitLastVideo.setVisibility(0);
        } else {
            n().fragmentContainerCultFitLastVideo.setVisibility(8);
        }
    }
}
