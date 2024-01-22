package com.coveiot.android.activitymodes.workoutVideos.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagingData;
import androidx.paging.PagingDataTransforms;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.databinding.ActivityWorkoutVideosBinding;
import com.coveiot.android.activitymodes.feedback.AnswerModel;
import com.coveiot.android.activitymodes.feedback.FeedbackQuestionnarieModel;
import com.coveiot.android.activitymodes.feedback.OptionModel;
import com.coveiot.android.activitymodes.feedback.QuestionModel;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.activitymodes.workoutVideos.adapters.ActivityWorkoutVideosAdapter;
import com.coveiot.android.activitymodes.workoutVideos.adapters.ActivityWorkoutVideosCategoryAdapter;
import com.coveiot.android.activitymodes.workoutVideos.adapters.WorkoutVideosFeedbackAdapter;
import com.coveiot.android.activitymodes.workoutVideos.fragments.CultFitVideoFragment;
import com.coveiot.android.activitymodes.workoutVideos.models.WorkoutVideosBean;
import com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.workoutvideos.model.VideoType;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.logger.RingLogger;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WorkoutVideosActivity extends BaseActivity implements WorkoutVideosFeedbackAdapter.onItemClickListener, ActivityWorkoutVideosCategoryAdapter.ItemClickListener, ActivityWorkoutVideosAdapter.ItemClickListener {
    @Nullable
    public String B;
    public FeedbackQuestionnarieModel feedbackQuestionnaireModel;
    public ActivityWorkoutVideosBinding p;
    @Nullable
    public Job s;
    public ViewModelWorkoutVideos u;
    @Nullable
    public String v;
    @Nullable
    public Dialog w;
    public WorkoutVideosFeedbackAdapter workoutVideosFeedbackAdapter;
    @Nullable
    public RecyclerView x;
    @Nullable
    public String y;
    @Nullable
    public String z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String q = "WorkoutVideosActivity";
    @NotNull
    public final ActivityWorkoutVideosAdapter r = new ActivityWorkoutVideosAdapter(this);
    @NotNull
    public final ActivityWorkoutVideosCategoryAdapter t = new ActivityWorkoutVideosCategoryAdapter(this);
    public boolean A = true;

    @DebugMetadata(c = "com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity$pullWorkoutVideos$1", f = "WorkoutVideosActivity.kt", i = {}, l = {228, 236}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $category;
        public final /* synthetic */ CharSequence $searchVideo;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity$pullWorkoutVideos$1$1", f = "WorkoutVideosActivity.kt", i = {}, l = {229}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C0249a extends SuspendLambda implements Function2<PagingData<WorkoutVideosBean>, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object L$0;
            public int label;
            public final /* synthetic */ WorkoutVideosActivity this$0;

            @DebugMetadata(c = "com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity$pullWorkoutVideos$1$1$1", f = "WorkoutVideosActivity.kt", i = {}, l = {230}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes2.dex */
            public static final class C0250a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ PagingData<WorkoutVideosBean> $it;
                public int label;
                public final /* synthetic */ WorkoutVideosActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0250a(WorkoutVideosActivity workoutVideosActivity, PagingData<WorkoutVideosBean> pagingData, Continuation<? super C0250a> continuation) {
                    super(2, continuation);
                    this.this$0 = workoutVideosActivity;
                    this.$it = pagingData;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0250a(this.this$0, this.$it, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0250a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter = this.this$0.r;
                        if (activityWorkoutVideosAdapter != null) {
                            PagingData<WorkoutVideosBean> pagingData = this.$it;
                            this.label = 1;
                            if (activityWorkoutVideosAdapter.submitData(pagingData, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter2 = this.this$0.r;
                    if (activityWorkoutVideosAdapter2 != null) {
                        activityWorkoutVideosAdapter2.refresh();
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0249a(WorkoutVideosActivity workoutVideosActivity, Continuation<? super C0249a> continuation) {
                super(2, continuation);
                this.this$0 = workoutVideosActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                C0249a c0249a = new C0249a(this.this$0, continuation);
                c0249a.L$0 = obj;
                return c0249a;
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull PagingData<WorkoutVideosBean> pagingData, @Nullable Continuation<? super Unit> continuation) {
                return ((C0249a) create(pagingData, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    C0250a c0250a = new C0250a(this.this$0, (PagingData) this.L$0, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main, c0250a, this) == coroutine_suspended) {
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

        @DebugMetadata(c = "com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity$pullWorkoutVideos$1$2", f = "WorkoutVideosActivity.kt", i = {}, l = {237}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class b extends SuspendLambda implements Function2<PagingData<WorkoutVideosBean>, Continuation<? super Unit>, Object> {
            public final /* synthetic */ String $category;
            public final /* synthetic */ CharSequence $searchVideo;
            public /* synthetic */ Object L$0;
            public int label;
            public final /* synthetic */ WorkoutVideosActivity this$0;

            @DebugMetadata(c = "com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity$pullWorkoutVideos$1$2$1", f = "WorkoutVideosActivity.kt", i = {}, l = {RingLogger.EVT_UPDATE}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity$a$b$a  reason: collision with other inner class name */
            /* loaded from: classes2.dex */
            public static final class C0251a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ String $category;
                public final /* synthetic */ PagingData<WorkoutVideosBean> $it;
                public final /* synthetic */ CharSequence $searchVideo;
                public int label;
                public final /* synthetic */ WorkoutVideosActivity this$0;

                @DebugMetadata(c = "com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity$pullWorkoutVideos$1$2$1$searchedVideoList$1", f = "WorkoutVideosActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity$a$b$a$a  reason: collision with other inner class name */
                /* loaded from: classes2.dex */
                public static final class C0252a extends SuspendLambda implements Function2<WorkoutVideosBean, Continuation<? super Boolean>, Object> {
                    public final /* synthetic */ CharSequence $searchVideo;
                    public /* synthetic */ Object L$0;
                    public int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0252a(CharSequence charSequence, Continuation<? super C0252a> continuation) {
                        super(2, continuation);
                        this.$searchVideo = charSequence;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        C0252a c0252a = new C0252a(this.$searchVideo, continuation);
                        c0252a.L$0 = obj;
                        return c0252a;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull WorkoutVideosBean workoutVideosBean, @Nullable Continuation<? super Boolean> continuation) {
                        return ((C0252a) create(workoutVideosBean, continuation)).invokeSuspend(Unit.INSTANCE);
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
                            return Boxing.boxBoolean(StringsKt__StringsKt.contains$default((CharSequence) lowerCase, this.$searchVideo, false, 2, (Object) null));
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0251a(PagingData<WorkoutVideosBean> pagingData, String str, WorkoutVideosActivity workoutVideosActivity, CharSequence charSequence, Continuation<? super C0251a> continuation) {
                    super(2, continuation);
                    this.$it = pagingData;
                    this.$category = str;
                    this.this$0 = workoutVideosActivity;
                    this.$searchVideo = charSequence;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0251a(this.$it, this.$category, this.this$0, this.$searchVideo, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0251a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        PagingData filter = PagingDataTransforms.filter(this.$it, new C0252a(this.$searchVideo, null));
                        if (this.$category == null) {
                            this.this$0.I(true);
                        } else {
                            WorkoutVideosActivity workoutVideosActivity = this.this$0;
                            workoutVideosActivity.I(Intrinsics.areEqual(SessionManager.getInstance(workoutVideosActivity).getLastVideoCategory(), this.$category));
                        }
                        ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter = this.this$0.r;
                        if (activityWorkoutVideosAdapter != null) {
                            this.label = 1;
                            if (activityWorkoutVideosAdapter.submitData(filter, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter2 = this.this$0.r;
                    if (activityWorkoutVideosAdapter2 != null) {
                        activityWorkoutVideosAdapter2.refresh();
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(String str, WorkoutVideosActivity workoutVideosActivity, CharSequence charSequence, Continuation<? super b> continuation) {
                super(2, continuation);
                this.$category = str;
                this.this$0 = workoutVideosActivity;
                this.$searchVideo = charSequence;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                b bVar = new b(this.$category, this.this$0, this.$searchVideo, continuation);
                bVar.L$0 = obj;
                return bVar;
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull PagingData<WorkoutVideosBean> pagingData, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(pagingData, continuation)).invokeSuspend(Unit.INSTANCE);
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
                    C0251a c0251a = new C0251a(pagingData, this.$category, this.this$0, this.$searchVideo, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main, c0251a, this) == coroutine_suspended) {
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
        public a(String str, CharSequence charSequence, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$category = str;
            this.$searchVideo = charSequence;
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
                String workoutType = WorkoutVideosActivity.this.getWorkoutType();
                boolean z = false;
                if (workoutType != null && m.equals(workoutType, VideoType.SENSE_AI_COACH.getVideoType(), true)) {
                    z = true;
                }
                if (z) {
                    ViewModelWorkoutVideos viewModelWorkoutVideos = WorkoutVideosActivity.this.u;
                    if (viewModelWorkoutVideos == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
                        viewModelWorkoutVideos = null;
                    }
                    Flow<PagingData<WorkoutVideosBean>> pullSensAIVideos = viewModelWorkoutVideos.pullSensAIVideos(this.$category);
                    C0249a c0249a = new C0249a(WorkoutVideosActivity.this, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(pullSensAIVideos, c0249a, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    ViewModelWorkoutVideos viewModelWorkoutVideos2 = WorkoutVideosActivity.this.u;
                    if (viewModelWorkoutVideos2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
                        viewModelWorkoutVideos2 = null;
                    }
                    Flow<PagingData<WorkoutVideosBean>> pullWorkVideos = viewModelWorkoutVideos2.pullWorkVideos(this.$category);
                    b bVar = new b(this.$category, WorkoutVideosActivity.this, this.$searchVideo, null);
                    this.label = 2;
                    if (FlowKt.collectLatest(pullWorkVideos, bVar, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static final void A(WorkoutVideosActivity this$0, FeedbackQuestionnarieModel questionnarie) {
        QuestionModel questionModel;
        QuestionModel questionModel2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(questionnarie, "questionnarie");
        this$0.setFeedbackQuestionnaireModel(questionnarie);
        if (this$0.getFeedbackQuestionnaireModel().getQuestions() != null) {
            List<QuestionModel> questions = this$0.getFeedbackQuestionnaireModel().getQuestions();
            String str = null;
            List<OptionModel> options = (questions == null || (questionModel2 = questions.get(0)) == null) ? null : questionModel2.getOptions();
            Intrinsics.checkNotNull(options);
            this$0.setWorkoutVideosFeedbackAdapter(new WorkoutVideosFeedbackAdapter(this$0, options, this$0));
            this$0.getWorkoutVideosFeedbackAdapter().setOnFeedBackClickListener(this$0);
            RecyclerView recyclerView = this$0.x;
            if (recyclerView != null) {
                recyclerView.setAdapter(this$0.getWorkoutVideosFeedbackAdapter());
            }
            Dialog dialog = this$0.w;
            Intrinsics.checkNotNull(dialog);
            TextView textView = (TextView) dialog.findViewById(R.id.rate_session);
            List<QuestionModel> questions2 = this$0.getFeedbackQuestionnaireModel().getQuestions();
            if (questions2 != null && (questionModel = questions2.get(0)) != null) {
                str = questionModel.getText();
            }
            textView.setText(str);
        }
        this$0.dismissProgress();
    }

    public static final void B(WorkoutVideosActivity this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (bool != null && bool.booleanValue()) {
            this$0.showProgress();
        } else {
            this$0.dismissProgress();
        }
    }

    public static final void C(WorkoutVideosActivity this$0, List categoryList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityWorkoutVideosCategoryAdapter activityWorkoutVideosCategoryAdapter = this$0.t;
        Intrinsics.checkNotNullExpressionValue(categoryList, "categoryList");
        activityWorkoutVideosCategoryAdapter.setData(categoryList);
        this$0.dismissProgress();
        this$0.E(null, "");
    }

    public static final void D(WorkoutVideosActivity this$0, Boolean b) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(b, "b");
        if (b.booleanValue()) {
            Toast.makeText(this$0, this$0.getString(R.string.thankyou_for_your_feedback), 0).show();
        } else {
            Toast.makeText(this$0, this$0.getString(R.string.failed_to_save), 0).show();
        }
        this$0.dismissProgress();
        Dialog dialog = this$0.w;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static final void H(WorkoutVideosActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Dialog dialog = this$0.w;
        Intrinsics.checkNotNull(dialog);
        dialog.dismiss();
    }

    public static final void y(WorkoutVideosActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public final void E(String str, CharSequence charSequence) {
        Job e;
        if (AppUtils.isNetConnected(this)) {
            Job job = this.s;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            e = kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new a(str, charSequence, null), 2, null);
            this.s = e;
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 0).show();
    }

    public final void F() {
        if (SessionManager.getInstance(this).getLastVideoName() != null) {
            CultFitVideoFragment newInstance = CultFitVideoFragment.Companion.newInstance(true);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_cult_fit_last_video, newInstance).commit();
            return;
        }
        I(false);
    }

    public final void G(String str, String str2) {
        this.v = str2;
        Dialog dialog = new Dialog(this, R.style.DialogTheme);
        this.w = dialog;
        Intrinsics.checkNotNull(dialog);
        dialog.setContentView(R.layout.workout_videos_feedback);
        Dialog dialog2 = this.w;
        Intrinsics.checkNotNull(dialog2);
        dialog2.setCancelable(false);
        Dialog dialog3 = this.w;
        Intrinsics.checkNotNull(dialog3);
        dialog3.setCanceledOnTouchOutside(false);
        Dialog dialog4 = this.w;
        Intrinsics.checkNotNull(dialog4);
        ((ImageView) dialog4.findViewById(R.id.image_close_workout)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WorkoutVideosActivity.H(WorkoutVideosActivity.this, view);
            }
        });
        Dialog dialog5 = this.w;
        Intrinsics.checkNotNull(dialog5);
        ((TextView) dialog5.findViewById(R.id.video_title_text)).setText(str);
        Dialog dialog6 = this.w;
        Intrinsics.checkNotNull(dialog6);
        RecyclerView recyclerView = (RecyclerView) dialog6.findViewById(R.id.feedback_recycler_view);
        this.x = recyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
        }
        showProgress();
        ViewModelWorkoutVideos viewModelWorkoutVideos = this.u;
        if (viewModelWorkoutVideos == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
            viewModelWorkoutVideos = null;
        }
        viewModelWorkoutVideos.getFeedbackQuestionnaireList();
        Dialog dialog7 = this.w;
        Intrinsics.checkNotNull(dialog7);
        dialog7.show();
    }

    public final void I(boolean z) {
        ActivityWorkoutVideosBinding activityWorkoutVideosBinding = null;
        if (z) {
            ActivityWorkoutVideosBinding activityWorkoutVideosBinding2 = this.p;
            if (activityWorkoutVideosBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWorkoutVideosBinding = activityWorkoutVideosBinding2;
            }
            activityWorkoutVideosBinding.fragmentContainerCultFitLastVideo.setVisibility(0);
            return;
        }
        ActivityWorkoutVideosBinding activityWorkoutVideosBinding3 = this.p;
        if (activityWorkoutVideosBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWorkoutVideosBinding = activityWorkoutVideosBinding3;
        }
        activityWorkoutVideosBinding.fragmentContainerCultFitLastVideo.setVisibility(8);
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

    @Nullable
    public final Dialog getFeedBackDialog() {
        return this.w;
    }

    @NotNull
    public final FeedbackQuestionnarieModel getFeedbackQuestionnaireModel() {
        FeedbackQuestionnarieModel feedbackQuestionnarieModel = this.feedbackQuestionnaireModel;
        if (feedbackQuestionnarieModel != null) {
            return feedbackQuestionnarieModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("feedbackQuestionnaireModel");
        return null;
    }

    @Nullable
    public final RecyclerView getFeedbackRecyclerView() {
        return this.x;
    }

    @NotNull
    public final String getTAG() {
        return this.q;
    }

    @Nullable
    public final String getVideoId() {
        return this.v;
    }

    @Nullable
    public final String getWorkoutType() {
        return this.y;
    }

    @NotNull
    public final WorkoutVideosFeedbackAdapter getWorkoutVideosFeedbackAdapter() {
        WorkoutVideosFeedbackAdapter workoutVideosFeedbackAdapter = this.workoutVideosFeedbackAdapter;
        if (workoutVideosFeedbackAdapter != null) {
            return workoutVideosFeedbackAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("workoutVideosFeedbackAdapter");
        return null;
    }

    public final void initToolbar() {
        String str = this.y;
        boolean z = false;
        if (str != null && m.equals(str, VideoType.SENSE_AI_COACH.getVideoType(), true)) {
            z = true;
        }
        if (z) {
            ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.sens_ai_coach));
        } else {
            ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.workout_videos));
        }
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WorkoutVideosActivity.y(WorkoutVideosActivity.this, view);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 121 || intent == null) {
            return;
        }
        if (AppUtils.isNetConnected(this)) {
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            if (themesUtils.isGuestUser(this) || themesUtils.isPairDeviceLater(this)) {
                return;
            }
            String stringExtra = intent.getStringExtra(WorkoutConstants.VIDEO_NAME);
            Intrinsics.checkNotNull(stringExtra);
            String stringExtra2 = intent.getStringExtra(WorkoutConstants.VIDEO_ID);
            Intrinsics.checkNotNull(stringExtra2);
            G(stringExtra, stringExtra2);
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 0).show();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityWorkoutVideosBinding inflate = ActivityWorkoutVideosBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityWorkoutVideosBinding activityWorkoutVideosBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ViewModel viewModel = ViewModelProviders.of(this).get(ViewModelWorkoutVideos.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this).get(ViewModelWorkoutVideos::class.java)");
        this.u = (ViewModelWorkoutVideos) viewModel;
        Intent intent = getIntent();
        if ((intent != null ? intent.getStringExtra("WORKOUT_TYPE") : null) != null) {
            Intent intent2 = getIntent();
            this.y = intent2 != null ? intent2.getStringExtra("WORKOUT_TYPE") : null;
        }
        initToolbar();
        z();
        ActivityWorkoutVideosBinding activityWorkoutVideosBinding2 = this.p;
        if (activityWorkoutVideosBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWorkoutVideosBinding = activityWorkoutVideosBinding2;
        }
        activityWorkoutVideosBinding.etSearchVideo.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity$onCreate$1
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
                    WorkoutVideosActivity workoutVideosActivity = WorkoutVideosActivity.this;
                    str2 = workoutVideosActivity.z;
                    String lowerCase = searchVideo.toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    workoutVideosActivity.E(str2, lowerCase);
                    return;
                }
                if (searchVideo.length() == 0) {
                    WorkoutVideosActivity workoutVideosActivity2 = WorkoutVideosActivity.this;
                    str = workoutVideosActivity2.z;
                    workoutVideosActivity2.E(str, "");
                }
            }
        });
    }

    @Override // com.coveiot.android.activitymodes.workoutVideos.adapters.ActivityWorkoutVideosCategoryAdapter.ItemClickListener
    public void onItemClick(@Nullable String str) {
        x(str);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.A) {
            I(true);
        } else {
            I(Intrinsics.areEqual(SessionManager.getInstance(this).getLastVideoCategory(), this.B));
        }
        F();
    }

    @Override // com.coveiot.android.activitymodes.workoutVideos.adapters.ActivityWorkoutVideosAdapter.ItemClickListener
    public void onVideosItemClick(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable List<String> list) {
        if (AppUtils.isNetConnected(this)) {
            Intent intent = new Intent(this, YoutubePlayerNewActivity.class);
            intent.putExtra(WorkoutConstants.YOUTUBE_VIDEO_ID, str);
            intent.putExtra(WorkoutConstants.VIDEO_ID, str2);
            intent.putExtra(WorkoutConstants.VIDEO_NAME, str3);
            String str4 = this.z;
            if (str4 != null) {
                intent.putExtra(WorkoutConstants.VIDEO_CATEGORY, str4);
            } else {
                intent.putExtra(WorkoutConstants.VIDEO_CATEGORY, "ALL");
            }
            String str5 = this.y;
            if (str5 != null) {
                intent.putExtra(WorkoutConstants.WORKOUT_TYPE, str5);
            }
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
                String str6 = "";
                for (int i = 0; i < size; i++) {
                    String str7 = list.get(i);
                    this.B = str7;
                    SessionManager.getInstance(this).saveLastVideoCategoryPassed(this.B);
                    if (StringsKt__StringsKt.contains$default((CharSequence) str7, (CharSequence) "_", false, 2, (Object) null)) {
                        str7 = m.replace$default(str7, "_", HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null);
                    }
                    if (list.size() > 1) {
                        str6 = str6.length() > 0 ? str6 + ',' + str7 : str6 + str7;
                    } else {
                        str6 = str6 + str7;
                    }
                }
                String value4 = FirebaseEventParams.MetaData.CV_VIDEO_CATEGORY.getValue();
                String lowerCase3 = str6.toString().toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                hashMap.put(value4, lowerCase3);
                this.z = str6;
            }
            analyticsLog.setMapData(hashMap);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 0).show();
    }

    public final void setFeedBackDialog(@Nullable Dialog dialog) {
        this.w = dialog;
    }

    public final void setFeedbackQuestionnaireModel(@NotNull FeedbackQuestionnarieModel feedbackQuestionnarieModel) {
        Intrinsics.checkNotNullParameter(feedbackQuestionnarieModel, "<set-?>");
        this.feedbackQuestionnaireModel = feedbackQuestionnarieModel;
    }

    public final void setFeedbackRecyclerView(@Nullable RecyclerView recyclerView) {
        this.x = recyclerView;
    }

    public final void setVideoId(@Nullable String str) {
        this.v = str;
    }

    public final void setWorkoutType(@Nullable String str) {
        this.y = str;
    }

    public final void setWorkoutVideosFeedbackAdapter(@NotNull WorkoutVideosFeedbackAdapter workoutVideosFeedbackAdapter) {
        Intrinsics.checkNotNullParameter(workoutVideosFeedbackAdapter, "<set-?>");
        this.workoutVideosFeedbackAdapter = workoutVideosFeedbackAdapter;
    }

    public final void w() {
        boolean z = false;
        if (AppUtils.isNetConnected(this)) {
            showProgress();
            String str = this.y;
            if (str != null && m.equals(str, VideoType.SENSE_AI_COACH.getVideoType(), true)) {
                z = true;
            }
            ViewModelWorkoutVideos viewModelWorkoutVideos = null;
            if (z) {
                ViewModelWorkoutVideos viewModelWorkoutVideos2 = this.u;
                if (viewModelWorkoutVideos2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
                } else {
                    viewModelWorkoutVideos = viewModelWorkoutVideos2;
                }
                viewModelWorkoutVideos.getSensAICoachCategory();
                return;
            }
            ViewModelWorkoutVideos viewModelWorkoutVideos3 = this.u;
            if (viewModelWorkoutVideos3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
            } else {
                viewModelWorkoutVideos = viewModelWorkoutVideos3;
            }
            viewModelWorkoutVideos.getWorkoutVideoCategoryList();
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 0).show();
    }

    public final void x(String str) {
        this.A = str == null;
        this.z = str;
        E(str, "");
        ActivityWorkoutVideosBinding activityWorkoutVideosBinding = this.p;
        if (activityWorkoutVideosBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWorkoutVideosBinding = null;
        }
        activityWorkoutVideosBinding.etSearchVideo.setText("");
    }

    public final void z() {
        ActivityWorkoutVideosBinding activityWorkoutVideosBinding = this.p;
        ViewModelWorkoutVideos viewModelWorkoutVideos = null;
        if (activityWorkoutVideosBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWorkoutVideosBinding = null;
        }
        activityWorkoutVideosBinding.categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
        w();
        this.t.setItemClickListener(this);
        this.r.setItemClickListener(this);
        ActivityWorkoutVideosBinding activityWorkoutVideosBinding2 = this.p;
        if (activityWorkoutVideosBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWorkoutVideosBinding2 = null;
        }
        activityWorkoutVideosBinding2.categoryRecyclerView.setAdapter(this.t);
        ActivityWorkoutVideosBinding activityWorkoutVideosBinding3 = this.p;
        if (activityWorkoutVideosBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWorkoutVideosBinding3 = null;
        }
        activityWorkoutVideosBinding3.videosRecyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        ActivityWorkoutVideosAdapter activityWorkoutVideosAdapter = this.r;
        Intrinsics.checkNotNull(activityWorkoutVideosAdapter);
        activityWorkoutVideosAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        ActivityWorkoutVideosBinding activityWorkoutVideosBinding4 = this.p;
        if (activityWorkoutVideosBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWorkoutVideosBinding4 = null;
        }
        activityWorkoutVideosBinding4.videosRecyclerView.setAdapter(this.r);
        ViewModelWorkoutVideos viewModelWorkoutVideos2 = this.u;
        if (viewModelWorkoutVideos2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
            viewModelWorkoutVideos2 = null;
        }
        viewModelWorkoutVideos2.getGetQuestionarieLiveData().observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.workoutVideos.activities.c
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WorkoutVideosActivity.A(WorkoutVideosActivity.this, (FeedbackQuestionnarieModel) obj);
            }
        });
        ViewModelWorkoutVideos viewModelWorkoutVideos3 = this.u;
        if (viewModelWorkoutVideos3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
            viewModelWorkoutVideos3 = null;
        }
        viewModelWorkoutVideos3.getProgressLiveData().observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.workoutVideos.activities.d
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WorkoutVideosActivity.B(WorkoutVideosActivity.this, (Boolean) obj);
            }
        });
        ViewModelWorkoutVideos viewModelWorkoutVideos4 = this.u;
        if (viewModelWorkoutVideos4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
            viewModelWorkoutVideos4 = null;
        }
        viewModelWorkoutVideos4.getGetCategoriesLiveData().observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.workoutVideos.activities.f
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WorkoutVideosActivity.C(WorkoutVideosActivity.this, (List) obj);
            }
        });
        ViewModelWorkoutVideos viewModelWorkoutVideos5 = this.u;
        if (viewModelWorkoutVideos5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
        } else {
            viewModelWorkoutVideos = viewModelWorkoutVideos5;
        }
        viewModelWorkoutVideos.getPostQuestionarieLiveData().observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.workoutVideos.activities.e
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WorkoutVideosActivity.D(WorkoutVideosActivity.this, (Boolean) obj);
            }
        });
    }

    @Override // com.coveiot.android.activitymodes.workoutVideos.adapters.WorkoutVideosFeedbackAdapter.onItemClickListener
    public void onItemClick(int i, @NotNull String optionId) {
        QuestionModel questionModel;
        Intrinsics.checkNotNullParameter(optionId, "optionId");
        if (AppUtils.isNetConnected(this)) {
            ArrayList arrayList = new ArrayList();
            AnswerModel answerModel = new AnswerModel();
            List<QuestionModel> questions = getFeedbackQuestionnaireModel().getQuestions();
            ViewModelWorkoutVideos viewModelWorkoutVideos = null;
            answerModel.setQuestionId((questions == null || (questionModel = questions.get(0)) == null) ? null : questionModel.getQuestionId());
            if (!(optionId.length() == 0)) {
                answerModel.setAnswerIds(CollectionsKt___CollectionsKt.toList(CollectionsKt__CollectionsKt.mutableListOf(optionId)));
            }
            arrayList.add(answerModel);
            getFeedbackQuestionnaireModel().setAnswers(arrayList);
            ViewModelWorkoutVideos viewModelWorkoutVideos2 = this.u;
            if (viewModelWorkoutVideos2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
            } else {
                viewModelWorkoutVideos = viewModelWorkoutVideos2;
            }
            String str = this.v;
            Intrinsics.checkNotNull(str);
            FeedbackQuestionnarieModel feedbackQuestionnaireModel = getFeedbackQuestionnaireModel();
            Intrinsics.checkNotNull(feedbackQuestionnaireModel);
            viewModelWorkoutVideos.saveFeedbackAnswer(str, feedbackQuestionnaireModel);
            showProgress();
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 0).show();
    }
}
