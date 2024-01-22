package com.coveiot.android.sportsnotification.utils.apihelper;

import android.os.Handler;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.android.bleabstract.models.VibrationModel;
import com.coveiot.android.bleabstract.request.MotorVibrationRequest;
import com.coveiot.android.bleabstract.request.SportsNotificationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.sportsnotification.SoccerSportsServiceNew;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$sendSportDataToTheWatch$1;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.e;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$sendSportDataToTheWatch$1", f = "SoccerApiHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class SoccerApiHelper$sendSportDataToTheWatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ArrayList<DynamicSportsField> $dynamicSportsFieldList;
    public final /* synthetic */ SoccerSportsServiceNew.SportsSettingsResultListener $listener;
    public int label;
    public final /* synthetic */ SoccerApiHelper this$0;

    /* renamed from: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$sendSportDataToTheWatch$1$1  reason: invalid class name */
    /* loaded from: classes7.dex */
    public static final class AnonymousClass1 implements SettingsResultListener {
        public final /* synthetic */ SoccerApiHelper h;
        public final /* synthetic */ SportsNotificationRequest i;
        public final /* synthetic */ SoccerSportsServiceNew.SportsSettingsResultListener j;
        public final /* synthetic */ ArrayList<DynamicSportsField> k;

        public AnonymousClass1(SoccerApiHelper soccerApiHelper, SportsNotificationRequest sportsNotificationRequest, SoccerSportsServiceNew.SportsSettingsResultListener sportsSettingsResultListener, ArrayList<DynamicSportsField> arrayList) {
            this.h = soccerApiHelper;
            this.i = sportsNotificationRequest;
            this.j = sportsSettingsResultListener;
            this.k = arrayList;
        }

        public static final void c(SoccerApiHelper this$0, SportsNotificationRequest sportsNotificationRequest, SoccerSportsServiceNew.SportsSettingsResultListener listener, ArrayList dynamicSportsFieldList) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(sportsNotificationRequest, "$sportsNotificationRequest");
            Intrinsics.checkNotNullParameter(listener, "$listener");
            Intrinsics.checkNotNullParameter(dynamicSportsFieldList, "$dynamicSportsFieldList");
            this$0.i(sportsNotificationRequest, listener, dynamicSportsFieldList);
        }

        public static final void d(SoccerApiHelper this$0, SportsNotificationRequest sportsNotificationRequest, SoccerSportsServiceNew.SportsSettingsResultListener listener, ArrayList dynamicSportsFieldList) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(sportsNotificationRequest, "$sportsNotificationRequest");
            Intrinsics.checkNotNullParameter(listener, "$listener");
            Intrinsics.checkNotNullParameter(dynamicSportsFieldList, "$dynamicSportsFieldList");
            this$0.i(sportsNotificationRequest, listener, dynamicSportsFieldList);
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsError(@NotNull BleBaseError error) {
            Handler handler;
            Intrinsics.checkNotNullParameter(error, "error");
            handler = this.h.g;
            if (handler != null) {
                final SoccerApiHelper soccerApiHelper = this.h;
                final SportsNotificationRequest sportsNotificationRequest = this.i;
                final SoccerSportsServiceNew.SportsSettingsResultListener sportsSettingsResultListener = this.j;
                final ArrayList<DynamicSportsField> arrayList = this.k;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        SoccerApiHelper$sendSportDataToTheWatch$1.AnonymousClass1.c(SoccerApiHelper.this, sportsNotificationRequest, sportsSettingsResultListener, arrayList);
                    }
                }, 500L);
            }
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsResponse(@NotNull BleBaseResponse response) {
            Handler handler;
            Intrinsics.checkNotNullParameter(response, "response");
            handler = this.h.g;
            if (handler != null) {
                final SoccerApiHelper soccerApiHelper = this.h;
                final SportsNotificationRequest sportsNotificationRequest = this.i;
                final SoccerSportsServiceNew.SportsSettingsResultListener sportsSettingsResultListener = this.j;
                final ArrayList<DynamicSportsField> arrayList = this.k;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        SoccerApiHelper$sendSportDataToTheWatch$1.AnonymousClass1.d(SoccerApiHelper.this, sportsNotificationRequest, sportsSettingsResultListener, arrayList);
                    }
                }, 500L);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SoccerApiHelper$sendSportDataToTheWatch$1(SoccerApiHelper soccerApiHelper, ArrayList<DynamicSportsField> arrayList, SoccerSportsServiceNew.SportsSettingsResultListener sportsSettingsResultListener, Continuation<? super SoccerApiHelper$sendSportDataToTheWatch$1> continuation) {
        super(2, continuation);
        this.this$0 = soccerApiHelper;
        this.$dynamicSportsFieldList = arrayList;
        this.$listener = sportsSettingsResultListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SoccerApiHelper$sendSportDataToTheWatch$1(this.this$0, this.$dynamicSportsFieldList, this.$listener, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SoccerApiHelper$sendSportDataToTheWatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        long j;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (BleApiManager.getInstance(this.this$0.getContext()).getBleApi() != null && BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                SportsNotificationRequest sportsNotificationRequest = new SportsNotificationRequest(this.$dynamicSportsFieldList);
                MotorVibrationRequest build = new MotorVibrationRequest.Builder().setVibrationModelList(e.listOf(new VibrationModel(1, 80, 16))).build();
                if (!SportsPreference.Companion.isVibrationEnabled(this.this$0.getContext())) {
                    this.this$0.i(sportsNotificationRequest, this.$listener, this.$dynamicSportsFieldList);
                } else {
                    long currentTimeMillis = System.currentTimeMillis();
                    j = this.this$0.b;
                    if (currentTimeMillis - j < 15000) {
                        this.this$0.i(sportsNotificationRequest, this.$listener, this.$dynamicSportsFieldList);
                    } else {
                        this.this$0.b = System.currentTimeMillis();
                        BleApiManager.getInstance(this.this$0.getContext()).getBleApi().setUserSettings(build, new AnonymousClass1(this.this$0, sportsNotificationRequest, this.$listener, this.$dynamicSportsFieldList));
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
