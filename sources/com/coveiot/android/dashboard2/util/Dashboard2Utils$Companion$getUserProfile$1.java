package com.coveiot.android.dashboard2.util;

import android.content.Context;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetUserDetails;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.WeeklyReportPrefData;
import java.util.HashMap;
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
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.dashboard2.util.Dashboard2Utils$Companion$getUserProfile$1", f = "Dashboard2Utils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class Dashboard2Utils$Companion$getUserProfile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Dashboard2Utils$Companion$getUserProfile$1(Context context, Continuation<? super Dashboard2Utils$Companion$getUserProfile$1> continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new Dashboard2Utils$Companion$getUserProfile$1(this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((Dashboard2Utils$Companion$getUserProfile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            final Context context = this.$context;
            CoveUserAppSettings.getUserDetails(new CoveApiListener<SGetUserDetails, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.util.Dashboard2Utils$Companion$getUserProfile$1.1

                @DebugMetadata(c = "com.coveiot.android.dashboard2.util.Dashboard2Utils$Companion$getUserProfile$1$1$onSuccess$1", f = "Dashboard2Utils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.dashboard2.util.Dashboard2Utils$Companion$getUserProfile$1$1$a */
                /* loaded from: classes4.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ Context $context;
                    public final /* synthetic */ SGetUserDetails $userDetails;
                    public int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(SGetUserDetails sGetUserDetails, Context context, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$userDetails = sGetUserDetails;
                        this.$context = context;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.$userDetails, this.$context, continuation);
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
                            if (this.$userDetails.getData() != null && this.$userDetails.getData().getAxTrackerId() != null && SessionManager.getInstance(this.$context).getAxTrackerId() == null) {
                                SessionManager.getInstance(this.$context).setAxTrackerId(this.$userDetails.getData().getAxTrackerId());
                                HashMap hashMap = new HashMap();
                                hashMap.put("Identity", this.$userDetails.getData().getAxTrackerId());
                                CoveAnalyticsManager.Companion.getInstance().setOnUserLogin(hashMap);
                            }
                            ProfileData userDetails = SessionManager.getInstance(this.$context).getUserDetails();
                            if (userDetails != null) {
                                userDetails.setEmail(this.$userDetails.getData().getEmailId());
                                SessionManager.getInstance(this.$context).createLoginSession(userDetails);
                                WeeklyReportPrefData weeklyReportData = UserDataManager.getInstance(this.$context).getWeeklyReportData();
                                if (weeklyReportData == null) {
                                    weeklyReportData = WeeklyReportPrefData.getInstance();
                                }
                                weeklyReportData.setEmailVerified(this.$userDetails.getData().getEmailVerified());
                                UserDataManager.getInstance(this.$context).saveWeeklyReport(weeklyReportData);
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull SGetUserDetails userDetails) {
                    Intrinsics.checkNotNullParameter(userDetails, "userDetails");
                    e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(userDetails, context, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
