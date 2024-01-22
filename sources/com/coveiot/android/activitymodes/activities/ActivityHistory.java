package com.coveiot.android.activitymodes.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.adapters.ActivityFeedbackAdapter;
import com.coveiot.android.activitymodes.adapters.ActivityHistoryAdapter;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.feedback.AnswerModel;
import com.coveiot.android.activitymodes.feedback.FeedbackModel;
import com.coveiot.android.activitymodes.feedback.FeedbackQuestionnarieModel;
import com.coveiot.android.activitymodes.feedback.QuestionModel;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutHistory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityHistory extends BaseActivity implements ActivityHistoryAdapter.AutoDetectionFeedbackInterface, ActivityFeedbackAdapter.AnswerFeedbackInterface {
    public ViewModelWorkoutHistory p;
    @Nullable
    public View q;
    @Nullable
    public AlertDialog r;
    @Nullable
    public FeedbackQuestionnarieModel s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final ActivityHistoryAdapter t = new ActivityHistoryAdapter(this, WorkoutUiBeanProvider.ScreenType.ACTIVITY_HISTORY);
    @NotNull
    public List<EntityWorkoutSession> u = new ArrayList();

    public static final void u(ActivityHistory this$0, List sessions) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(sessions, "sessions");
        this$0.u = sessions;
        if (!sessions.isEmpty()) {
            this$0.t.setData(sessions);
        }
    }

    public static final void v(ActivityHistory this$0, FeedbackQuestionnarieModel feedbackQuestionnarieModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.s = feedbackQuestionnarieModel;
    }

    public static final void w(ActivityHistory this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void y(final ActivityHistory this$0, final EntityWorkoutSession entityWorkoutSession, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(entityWorkoutSession, "$entityWorkoutSession");
        if (AppUtils.isNetConnected(this$0)) {
            FeedbackQuestionnarieModel feedbackQuestionnarieModel = this$0.s;
            Intrinsics.checkNotNull(feedbackQuestionnarieModel);
            List<AnswerModel> answers = feedbackQuestionnarieModel.getAnswers();
            if (answers == null || answers.isEmpty()) {
                Toast.makeText(this$0, this$0.getString(R.string.please_select_answer), 0).show();
                return;
            }
            this$0.showProgress();
            ViewModelWorkoutHistory viewModelWorkoutHistory = this$0.p;
            ViewModelWorkoutHistory viewModelWorkoutHistory2 = null;
            if (viewModelWorkoutHistory == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutHistory");
                viewModelWorkoutHistory = null;
            }
            viewModelWorkoutHistory.getPostQuestionarieLiveData().observe(this$0, new Observer<Boolean>() { // from class: com.coveiot.android.activitymodes.activities.ActivityHistory$showAutoActivityDetectionWarningDialog$2$1

                @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityHistory$showAutoActivityDetectionWarningDialog$2$1$onChanged$1", f = "ActivityHistory.kt", i = {}, l = {164}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes2.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
                    public int label;
                    public final /* synthetic */ ActivityHistory this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(ActivityHistory activityHistory, EntityWorkoutSession entityWorkoutSession, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = activityHistory;
                        this.$entityWorkoutSession = entityWorkoutSession;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.this$0, this.$entityWorkoutSession, continuation);
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
                            EntityWorkoutSession entityWorkoutSession = this.$entityWorkoutSession;
                            this.label = 1;
                            if (WorkoutSessionRepository.Companion.getInstance(this.this$0).updateSession(entityWorkoutSession, this) == coroutine_suspended) {
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

                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable Boolean bool) {
                    ViewModelWorkoutHistory viewModelWorkoutHistory3;
                    AlertDialog alertDialog;
                    FeedbackQuestionnarieModel feedbackQuestionnarieModel2;
                    FeedbackQuestionnarieModel feedbackQuestionnarieModel3;
                    ViewModelWorkoutHistory viewModelWorkoutHistory4 = null;
                    if (bool != null) {
                        if (bool.booleanValue()) {
                            ActivityHistory activityHistory = ActivityHistory.this;
                            Toast.makeText(activityHistory, activityHistory.getString(R.string.save_successfully), 0).show();
                            FeedbackModel feedbackModel = new FeedbackModel();
                            feedbackQuestionnarieModel2 = ActivityHistory.this.s;
                            Intrinsics.checkNotNull(feedbackQuestionnarieModel2);
                            feedbackModel.setQuestionnaireId(feedbackQuestionnarieModel2.getQuestionnaireId());
                            feedbackQuestionnarieModel3 = ActivityHistory.this.s;
                            Intrinsics.checkNotNull(feedbackQuestionnarieModel3);
                            feedbackModel.setQuestionsAndAnswers(feedbackQuestionnarieModel3.getAnswers());
                            entityWorkoutSession.setFeedback(new Gson().toJson(feedbackModel));
                            ActivityHistory.this.getActivityHistoryAdapter().setData(ActivityHistory.this.getEntityWorkoutSessions());
                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(ActivityHistory.this, entityWorkoutSession, null), 2, null);
                        } else {
                            ActivityHistory activityHistory2 = ActivityHistory.this;
                            Toast.makeText(activityHistory2, activityHistory2.getString(R.string.failed_to_save), 0).show();
                        }
                    }
                    viewModelWorkoutHistory3 = ActivityHistory.this.p;
                    if (viewModelWorkoutHistory3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutHistory");
                    } else {
                        viewModelWorkoutHistory4 = viewModelWorkoutHistory3;
                    }
                    viewModelWorkoutHistory4.getPostQuestionarieLiveData().removeObserver(this);
                    ActivityHistory.this.dismissProgress();
                    alertDialog = ActivityHistory.this.r;
                    if (alertDialog != null) {
                        alertDialog.dismiss();
                    }
                }
            });
            ViewModelWorkoutHistory viewModelWorkoutHistory3 = this$0.p;
            if (viewModelWorkoutHistory3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutHistory");
            } else {
                viewModelWorkoutHistory2 = viewModelWorkoutHistory3;
            }
            FeedbackQuestionnarieModel feedbackQuestionnarieModel2 = this$0.s;
            Intrinsics.checkNotNull(feedbackQuestionnarieModel2);
            viewModelWorkoutHistory2.saveFeedbackAnswer(entityWorkoutSession, feedbackQuestionnarieModel2);
            return;
        }
        Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
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

    @NotNull
    public final ActivityHistoryAdapter getActivityHistoryAdapter() {
        return this.t;
    }

    @NotNull
    public final List<EntityWorkoutSession> getEntityWorkoutSessions() {
        return this.u;
    }

    public final void init() {
        int i = R.id.activityHistoryRecyclerView;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(this));
        this.t.setAutoDetectFeedbackListener(this);
        ((RecyclerView) _$_findCachedViewById(i)).setAdapter(this.t);
        ViewModel viewModel = ViewModelProviders.of(this).get(ViewModelWorkoutHistory.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this).get(ViewModelWorkoutHistory::class.java)");
        ViewModelWorkoutHistory viewModelWorkoutHistory = (ViewModelWorkoutHistory) viewModel;
        this.p = viewModelWorkoutHistory;
        ViewModelWorkoutHistory viewModelWorkoutHistory2 = null;
        if (viewModelWorkoutHistory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutHistory");
            viewModelWorkoutHistory = null;
        }
        String connectedDeviceMacAddress = new PreferenceManager(this).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNull(connectedDeviceMacAddress);
        viewModelWorkoutHistory.getAllWorkoutSessionsFromDB(connectedDeviceMacAddress).observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.activities.d1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityHistory.u(ActivityHistory.this, (List) obj);
            }
        });
        ViewModelWorkoutHistory viewModelWorkoutHistory3 = this.p;
        if (viewModelWorkoutHistory3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutHistory");
            viewModelWorkoutHistory3 = null;
        }
        viewModelWorkoutHistory3.getGetQuestionarieLiveData().observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.activities.c1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityHistory.v(ActivityHistory.this, (FeedbackQuestionnarieModel) obj);
            }
        });
        if (AppUtils.isNetConnected(this)) {
            ViewModelWorkoutHistory viewModelWorkoutHistory4 = this.p;
            if (viewModelWorkoutHistory4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutHistory");
            } else {
                viewModelWorkoutHistory2 = viewModelWorkoutHistory4;
            }
            viewModelWorkoutHistory2.getFeedbackQuestionnarieList();
        } else {
            Toast.makeText(this, getString(R.string.no_internet_connection), 0).show();
        }
        int i2 = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i2).findViewById(R.id.toolbar_title)).setText(getString(R.string.activity_history));
        _$_findCachedViewById(i2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.a1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityHistory.w(ActivityHistory.this, view);
            }
        });
    }

    @Override // com.coveiot.android.activitymodes.adapters.ActivityFeedbackAdapter.AnswerFeedbackInterface
    public void onAnswer(@NotNull AnswerModel answerModel) {
        Intrinsics.checkNotNullParameter(answerModel, "answerModel");
        FeedbackQuestionnarieModel feedbackQuestionnarieModel = this.s;
        if (feedbackQuestionnarieModel != null) {
            if (feedbackQuestionnarieModel.getAnswers() == null) {
                feedbackQuestionnarieModel.setAnswers(new ArrayList());
            }
            List<AnswerModel> answers = feedbackQuestionnarieModel.getAnswers();
            Intrinsics.checkNotNull(answers);
            Iterator<AnswerModel> it = answers.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AnswerModel next = it.next();
                if (Intrinsics.areEqual(next.getQuestionId(), answerModel.getQuestionId())) {
                    List<AnswerModel> answers2 = feedbackQuestionnarieModel.getAnswers();
                    Intrinsics.checkNotNull(answers2);
                    answers2.remove(next);
                    break;
                }
            }
            List<AnswerModel> answers3 = feedbackQuestionnarieModel.getAnswers();
            Intrinsics.checkNotNull(answers3);
            answers3.add(answerModel);
            View view = this.q;
            Button button = view != null ? (Button) view.findViewById(R.id.btn_submit) : null;
            if (button == null) {
                return;
            }
            button.setEnabled(true);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_history);
        init();
    }

    @Override // com.coveiot.android.activitymodes.adapters.ActivityHistoryAdapter.AutoDetectionFeedbackInterface
    public void onFeedbackClicked(@NotNull EntityWorkoutSession entityWorkoutSession) {
        Intrinsics.checkNotNullParameter(entityWorkoutSession, "entityWorkoutSession");
        if (AppUtils.isNetConnected(this)) {
            FeedbackQuestionnarieModel feedbackQuestionnarieModel = this.s;
            Unit unit = null;
            if (feedbackQuestionnarieModel != null) {
                List<QuestionModel> questions = feedbackQuestionnarieModel.getQuestions();
                if (questions != null) {
                    if (!questions.isEmpty()) {
                        x(entityWorkoutSession);
                    } else {
                        Toast.makeText(this, getString(R.string.no_questionnarie_found), 0).show();
                    }
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    Toast.makeText(this, getString(R.string.no_questionnarie_found), 0).show();
                }
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                Toast.makeText(this, getString(R.string.no_questionnarie_found), 0).show();
                return;
            }
            return;
        }
        Toast.makeText(this, getString(R.string.please_enable_internet), 0).show();
    }

    public final void setEntityWorkoutSessions(@NotNull List<EntityWorkoutSession> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.u = list;
    }

    public final void x(final EntityWorkoutSession entityWorkoutSession) {
        AlertDialog alertDialog = this.r;
        if (alertDialog != null) {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
            this.r = null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "this.layoutInflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_auto_activity_detection_feedback, (ViewGroup) null);
        this.q = inflate;
        builder.setView(inflate);
        View view = this.q;
        Intrinsics.checkNotNull(view);
        int i = R.id.rv_feedback;
        ((RecyclerView) view.findViewById(i)).setLayoutManager(new LinearLayoutManager(this));
        ActivityFeedbackAdapter activityFeedbackAdapter = new ActivityFeedbackAdapter(this);
        activityFeedbackAdapter.setAnswerFeedbackListener(this);
        FeedbackQuestionnarieModel feedbackQuestionnarieModel = this.s;
        Intrinsics.checkNotNull(feedbackQuestionnarieModel);
        List<QuestionModel> questions = feedbackQuestionnarieModel.getQuestions();
        Intrinsics.checkNotNull(questions);
        activityFeedbackAdapter.setData(questions);
        View view2 = this.q;
        Intrinsics.checkNotNull(view2);
        ((RecyclerView) view2.findViewById(i)).setAdapter(activityFeedbackAdapter);
        View view3 = this.q;
        Intrinsics.checkNotNull(view3);
        ((Button) view3.findViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.b1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view4) {
                ActivityHistory.y(ActivityHistory.this, entityWorkoutSession, view4);
            }
        });
        AlertDialog create = builder.create();
        this.r = create;
        Intrinsics.checkNotNull(create);
        create.setCancelable(true);
        AlertDialog alertDialog2 = this.r;
        Intrinsics.checkNotNull(alertDialog2);
        alertDialog2.setCanceledOnTouchOutside(true);
        AlertDialog alertDialog3 = this.r;
        Intrinsics.checkNotNull(alertDialog3);
        alertDialog3.show();
    }
}
