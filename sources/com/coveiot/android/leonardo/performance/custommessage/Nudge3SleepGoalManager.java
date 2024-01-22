package com.coveiot.android.leonardo.performance.custommessage;

import android.content.Context;
import com.coveiot.android.boat.R;
import com.coveiot.android.khperformancecalculator.KHPerformanceManager;
import com.coveiot.android.khperformancecalculator.SleepTarget;
import com.coveiot.android.khperformancecalculator.model.KHPSleepData;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.performance.Constants;
import com.coveiot.android.leonardo.performance.PerformanceDataFormatter;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class Nudge3SleepGoalManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "Nudge3SleepGoalManager";

    /* loaded from: classes5.dex */
    public static final class Companion {

        @DebugMetadata(c = "com.coveiot.android.leonardo.performance.custommessage.Nudge3SleepGoalManager$Companion$calculateSleepTargetStatus$1", f = "Nudge3SleepGoalManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                String string;
                String str;
                KHPSleepData kHPSleepData;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(4, -1);
                    calendar.set(7, 2);
                    calendar.set(11, 0);
                    calendar.set(12, 0);
                    calendar.set(13, 0);
                    calendar.set(14, 0);
                    ArrayList<Calendar> arrayList = new ArrayList();
                    Object clone = calendar.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                    arrayList.add((Calendar) clone);
                    for (int i = 0; i < 6; i++) {
                        calendar.add(5, 1);
                        Object clone2 = calendar.clone();
                        Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
                        arrayList.add((Calendar) clone2);
                    }
                    String formatDate = AppUtils.formatDate(new Date(((Calendar) CollectionsKt___CollectionsKt.first((List<? extends Object>) arrayList)).getTimeInMillis()), "yyyy-MM-dd");
                    Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(Date(lastWeek…eInMillis), \"yyyy-MM-dd\")");
                    String formatDate2 = AppUtils.formatDate(new Date(((Calendar) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList)).getTimeInMillis()), "yyyy-MM-dd");
                    Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(Date(lastWeek…eInMillis), \"yyyy-MM-dd\")");
                    LogHelper.d(Nudge3SleepGoalManager.TAG, "startDate->" + formatDate + ", endData->" + formatDate2);
                    ArrayList arrayList2 = new ArrayList();
                    for (Calendar calendar2 : arrayList) {
                        String connectedDeviceMacAddress = SessionManager.getInstance(this.$context).getConnectedDeviceMacAddress();
                        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
                        List<SleepDataModelForLastNight> lastNignthSleepDataWithOutLiveData = SleepRepository.Companion.getInstance(this.$context).getLastNignthSleepDataWithOutLiveData(calendar2, connectedDeviceMacAddress);
                        if (lastNignthSleepDataWithOutLiveData != null && (!lastNignthSleepDataWithOutLiveData.isEmpty()) && (kHPSleepData = PerformanceDataFormatter.Companion.getKHPSleepData(calendar2, lastNignthSleepDataWithOutLiveData, this.$context)) != null) {
                            Boxing.boxBoolean(arrayList2.add(kHPSleepData));
                        }
                    }
                    if (!arrayList2.isEmpty()) {
                        if (KHPerformanceManager.Companion.getInstance(this.$context).getWeeklySleepTargetStatus(3, arrayList2) == SleepTarget.ACHIEVED) {
                            string = this.$context.getResources().getString(R.string.sleep_target_achieved_msg);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…leep_target_achieved_msg)");
                            LogHelper.d(Nudge3SleepGoalManager.TAG, "Sleep target achieved!!");
                            str = "Great going!";
                        } else {
                            string = this.$context.getResources().getString(R.string.sleep_target_not_achieved_msg);
                            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…_target_not_achieved_msg)");
                            LogHelper.d(Nudge3SleepGoalManager.TAG, "Sleep target not achieved!!");
                            str = "Shoot!";
                        }
                        NudgeCommandManager.Companion.sendGenericMessageToBle(this.$context, string, Constants.SLEEP_IMAGE_ID, str);
                        NotificationManager.getInstance().notifySleepInsight(this.$context, Constants.SLEEP_INSIGHT_NOTIFICATION_ID, string);
                    } else {
                        LogHelper.d(Nudge3SleepGoalManager.TAG, "no sleep data for week " + calendar.get(3) + ", " + calendar.get(1));
                    }
                    Map<String, Long> notificationRecordMap = KHPerformancePreferenceManager.getInstance(this.$context).getPerformanceNotificationSentRecords();
                    Intrinsics.checkNotNullExpressionValue(notificationRecordMap, "notificationRecordMap");
                    notificationRecordMap.put(Constants.LAST_SENT_INFO_NUDGE_3, Boxing.boxLong(System.currentTimeMillis()));
                    KHPerformancePreferenceManager.getInstance(this.$context).savePerformanceNotificationSentRecords(notificationRecordMap);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
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

        public final void calculateNudge3AndSendToBLE(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Long l = KHPerformancePreferenceManager.getInstance(context).getPerformanceNotificationSentRecords().get(Constants.LAST_SENT_INFO_NUDGE_3);
            if (l == null || !PerformanceDataFormatter.Companion.isTimeInCurrentWeek(l.longValue())) {
                a(context);
            }
        }
    }
}
