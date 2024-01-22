package com.coveiot.android.leonardo.performance.custommessage;

import android.content.Context;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.boat.R;
import com.coveiot.android.khperformancecalculator.KHPerformanceManager;
import com.coveiot.android.khperformancecalculator.model.KHPActivityData;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.performance.Constants;
import com.coveiot.android.leonardo.performance.PerformanceDataFormatter;
import com.coveiot.utils.utility.AppUtils;
import java.util.Date;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class Nudge1NoActivityManager {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {

        @DebugMetadata(c = "com.coveiot.android.leonardo.performance.custommessage.Nudge1NoActivityManager$Companion$calculateBasedOnLastActivity$1", f = "Nudge1NoActivityManager.kt", i = {}, l = {38}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Context $context;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Context context, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v43, types: [T, java.lang.String] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object latestSession;
                String sb;
                T t;
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    latestSession = WorkoutSessionRepository.Companion.getInstance(this.$context).getLatestSession(this);
                    if (latestSession == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                    latestSession = obj;
                }
                EntityWorkoutSession entityWorkoutSession = (EntityWorkoutSession) latestSession;
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                if ((entityWorkoutSession != null ? entityWorkoutSession.getActivity_type() : null) == null) {
                    Map<String, Long> notificationRecordMap = KHPerformancePreferenceManager.getInstance(this.$context).getPerformanceNotificationSentRecords();
                    if (notificationRecordMap.get(Constants.LAST_SENT_INFO_NUDGE_1) == null) {
                        Intrinsics.checkNotNullExpressionValue(notificationRecordMap, "notificationRecordMap");
                        notificationRecordMap.put(Constants.LAST_SENT_INFO_NUDGE_1, Boxing.boxLong(System.currentTimeMillis()));
                        KHPerformancePreferenceManager.getInstance(this.$context).savePerformanceNotificationSentRecords(notificationRecordMap);
                    } else {
                        objectRef2.element = "Uh oh !";
                        String string = this.$context.getResources().getString(R.string.you_have_not_done_activity_in_x_days);
                        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…_done_activity_in_x_days)");
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("3 ");
                        String string2 = this.$context.getResources().getString(R.string.day_s);
                        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.day_s)");
                        String lowerCase = string2.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                        sb2.append(lowerCase);
                        objectRef.element = m.replace$default(string, "$", sb2.toString(), false, 4, (Object) null);
                    }
                } else {
                    KHPActivityData kHPActivityData = PerformanceDataFormatter.Companion.getKHPActivityData(entityWorkoutSession, this.$context);
                    if (kHPActivityData != null) {
                        Context context = this.$context;
                        int daysDifferenceFromActivity = KHPerformanceManager.Companion.getInstance(context).getDaysDifferenceFromActivity(kHPActivityData);
                        if (daysDifferenceFromActivity >= 3) {
                            objectRef2.element = "Uh oh !";
                            if (daysDifferenceFromActivity == 3) {
                                String string3 = context.getResources().getString(R.string.you_have_not_done_activity_in_x_days);
                                Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…_done_activity_in_x_days)");
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(daysDifferenceFromActivity);
                                sb3.append(' ');
                                String string4 = context.getResources().getString(R.string.day_s);
                                Intrinsics.checkNotNullExpressionValue(string4, "context.resources.getString(R.string.day_s)");
                                String lowerCase2 = string4.toLowerCase();
                                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                                sb3.append(lowerCase2);
                                t = m.replace$default(string3, "$", sb3.toString(), false, 4, (Object) null);
                            } else {
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append(daysDifferenceFromActivity);
                                sb4.append(' ');
                                String string5 = context.getResources().getString(R.string.day_s);
                                Intrinsics.checkNotNullExpressionValue(string5, "context.resources.getString(R.string.day_s)");
                                String lowerCase3 = string5.toLowerCase();
                                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                                sb4.append(lowerCase3);
                                String sb5 = sb4.toString();
                                boolean z = false;
                                if (8 <= daysDifferenceFromActivity && daysDifferenceFromActivity < 366) {
                                    z = true;
                                }
                                if (z) {
                                    int i2 = daysDifferenceFromActivity / 7;
                                    if (i2 > 1) {
                                        StringBuilder sb6 = new StringBuilder();
                                        sb6.append(i2);
                                        sb6.append(' ');
                                        String string6 = context.getResources().getString(R.string.week_s);
                                        Intrinsics.checkNotNullExpressionValue(string6, "context.resources.getString(R.string.week_s)");
                                        String lowerCase4 = string6.toLowerCase();
                                        Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                                        sb6.append(lowerCase4);
                                        sb = sb6.toString();
                                    } else {
                                        StringBuilder sb7 = new StringBuilder();
                                        sb7.append(i2);
                                        sb7.append(' ');
                                        String string7 = context.getResources().getString(R.string.week);
                                        Intrinsics.checkNotNullExpressionValue(string7, "context.resources.getString(R.string.week)");
                                        String lowerCase5 = string7.toLowerCase();
                                        Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase()");
                                        sb7.append(lowerCase5);
                                        sb = sb7.toString();
                                    }
                                } else {
                                    if (daysDifferenceFromActivity > 365) {
                                        int i3 = daysDifferenceFromActivity / 365;
                                        if (i3 > 1) {
                                            StringBuilder sb8 = new StringBuilder();
                                            sb8.append(i3);
                                            sb8.append(' ');
                                            String string8 = context.getResources().getString(R.string.year);
                                            Intrinsics.checkNotNullExpressionValue(string8, "context.resources.getString(R.string.year)");
                                            String lowerCase6 = string8.toLowerCase();
                                            Intrinsics.checkNotNullExpressionValue(lowerCase6, "this as java.lang.String).toLowerCase()");
                                            sb8.append(lowerCase6);
                                            sb = sb8.toString();
                                        } else {
                                            StringBuilder sb9 = new StringBuilder();
                                            sb9.append(i3);
                                            sb9.append(' ');
                                            String string9 = context.getResources().getString(R.string.year_s);
                                            Intrinsics.checkNotNullExpressionValue(string9, "context.resources.getString(R.string.year_s)");
                                            String lowerCase7 = string9.toLowerCase();
                                            Intrinsics.checkNotNullExpressionValue(lowerCase7, "this as java.lang.String).toLowerCase()");
                                            sb9.append(lowerCase7);
                                            sb = sb9.toString();
                                        }
                                    }
                                    String string10 = context.getResources().getString(R.string.you_have_not_done_activity_in_x_days);
                                    Intrinsics.checkNotNullExpressionValue(string10, "context.resources.getStr…_done_activity_in_x_days)");
                                    t = m.replace$default(string10, "$", String.valueOf(sb5), false, 4, (Object) null);
                                }
                                sb5 = sb;
                                String string102 = context.getResources().getString(R.string.you_have_not_done_activity_in_x_days);
                                Intrinsics.checkNotNullExpressionValue(string102, "context.resources.getStr…_done_activity_in_x_days)");
                                t = m.replace$default(string102, "$", String.valueOf(sb5), false, 4, (Object) null);
                            }
                            objectRef.element = t;
                        }
                    }
                }
                String str = (String) objectRef.element;
                if (str != null) {
                    Context context2 = this.$context;
                    NudgeCommandManager.Companion.sendGenericMessageToBle(context2, str, Constants.ACTIVITY_IMAGE_ID, (String) objectRef2.element);
                    NotificationManager.getInstance().notifyActivityInsight(context2, Constants.ACTIVITY_INSIGHT_NOTIFICATION_ID, str);
                    Map<String, Long> notificationRecordMap2 = KHPerformancePreferenceManager.getInstance(context2).getPerformanceNotificationSentRecords();
                    Intrinsics.checkNotNullExpressionValue(notificationRecordMap2, "notificationRecordMap");
                    notificationRecordMap2.put(Constants.LAST_SENT_INFO_NUDGE_1, Boxing.boxLong(System.currentTimeMillis()));
                    KHPerformancePreferenceManager.getInstance(context2).savePerformanceNotificationSentRecords(notificationRecordMap2);
                }
                return Unit.INSTANCE;
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(Context context) {
            e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(context, null), 2, null);
        }

        public final void calculateNudge1AndSendToBLE(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Map<String, Long> performanceNotificationSentRecords = KHPerformancePreferenceManager.getInstance(context).getPerformanceNotificationSentRecords();
            if (performanceNotificationSentRecords.get(Constants.LAST_SENT_INFO_NUDGE_1) != null) {
                Long l = performanceNotificationSentRecords.get(Constants.LAST_SENT_INFO_NUDGE_1);
                Intrinsics.checkNotNull(l);
                if (AppUtils.findDateDifference(new Date(l.longValue()), new Date()) < 3) {
                    return;
                }
            }
            a(context);
        }
    }
}
