package com.coveiot.android.sportsnotification;

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
import com.coveiot.android.sportsnotification.SportsService;
import com.coveiot.android.sportsnotification.SportsService$sendSportDataToTheWatch$1;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.sportsnotification.SportsService$sendSportDataToTheWatch$1", f = "SportsService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class SportsService$sendSportDataToTheWatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ArrayList<DynamicSportsField> $dynamicSportsFieldList;
    public final /* synthetic */ SportsService.SportsSettingsResultListener $listener;
    public int label;
    public final /* synthetic */ SportsService this$0;

    /* renamed from: com.coveiot.android.sportsnotification.SportsService$sendSportDataToTheWatch$1$1  reason: invalid class name */
    /* loaded from: classes7.dex */
    public static final class AnonymousClass1 implements SettingsResultListener {
        public final /* synthetic */ SportsService h;
        public final /* synthetic */ SportsNotificationRequest i;
        public final /* synthetic */ SportsService.SportsSettingsResultListener j;
        public final /* synthetic */ ArrayList<DynamicSportsField> k;

        public AnonymousClass1(SportsService sportsService, SportsNotificationRequest sportsNotificationRequest, SportsService.SportsSettingsResultListener sportsSettingsResultListener, ArrayList<DynamicSportsField> arrayList) {
            this.h = sportsService;
            this.i = sportsNotificationRequest;
            this.j = sportsSettingsResultListener;
            this.k = arrayList;
        }

        public static final void c(SportsService this$0, SportsNotificationRequest sportsNotificationRequest, SportsService.SportsSettingsResultListener listener, ArrayList dynamicSportsFieldList) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(sportsNotificationRequest, "$sportsNotificationRequest");
            Intrinsics.checkNotNullParameter(listener, "$listener");
            Intrinsics.checkNotNullParameter(dynamicSportsFieldList, "$dynamicSportsFieldList");
            this$0.B(sportsNotificationRequest, listener, dynamicSportsFieldList);
        }

        public static final void d(SportsService this$0, SportsNotificationRequest sportsNotificationRequest, SportsService.SportsSettingsResultListener listener, ArrayList dynamicSportsFieldList) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(sportsNotificationRequest, "$sportsNotificationRequest");
            Intrinsics.checkNotNullParameter(listener, "$listener");
            Intrinsics.checkNotNullParameter(dynamicSportsFieldList, "$dynamicSportsFieldList");
            this$0.B(sportsNotificationRequest, listener, dynamicSportsFieldList);
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsError(@NotNull BleBaseError error) {
            Handler handler;
            Intrinsics.checkNotNullParameter(error, "error");
            handler = this.h.m;
            if (handler != null) {
                final SportsService sportsService = this.h;
                final SportsNotificationRequest sportsNotificationRequest = this.i;
                final SportsService.SportsSettingsResultListener sportsSettingsResultListener = this.j;
                final ArrayList<DynamicSportsField> arrayList = this.k;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.o
                    @Override // java.lang.Runnable
                    public final void run() {
                        SportsService$sendSportDataToTheWatch$1.AnonymousClass1.c(SportsService.this, sportsNotificationRequest, sportsSettingsResultListener, arrayList);
                    }
                }, 500L);
            }
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsResponse(@NotNull BleBaseResponse response) {
            Handler handler;
            Intrinsics.checkNotNullParameter(response, "response");
            handler = this.h.m;
            if (handler != null) {
                final SportsService sportsService = this.h;
                final SportsNotificationRequest sportsNotificationRequest = this.i;
                final SportsService.SportsSettingsResultListener sportsSettingsResultListener = this.j;
                final ArrayList<DynamicSportsField> arrayList = this.k;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.n
                    @Override // java.lang.Runnable
                    public final void run() {
                        SportsService$sendSportDataToTheWatch$1.AnonymousClass1.d(SportsService.this, sportsNotificationRequest, sportsSettingsResultListener, arrayList);
                    }
                }, 500L);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SportsService$sendSportDataToTheWatch$1(SportsService sportsService, ArrayList<DynamicSportsField> arrayList, SportsService.SportsSettingsResultListener sportsSettingsResultListener, Continuation<? super SportsService$sendSportDataToTheWatch$1> continuation) {
        super(2, continuation);
        this.this$0 = sportsService;
        this.$dynamicSportsFieldList = arrayList;
        this.$listener = sportsSettingsResultListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SportsService$sendSportDataToTheWatch$1(this.this$0, this.$dynamicSportsFieldList, this.$listener, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SportsService$sendSportDataToTheWatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (BleApiManager.getInstance(this.this$0).getBleApi() != null && BleApiManager.getInstance(this.this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                SportsNotificationRequest sportsNotificationRequest = new SportsNotificationRequest(this.$dynamicSportsFieldList);
                MotorVibrationRequest build = new MotorVibrationRequest.Builder().setVibrationModelList(kotlin.collections.e.listOf(new VibrationModel(1, 80, 16))).build();
                if (!SportsPreference.Companion.isVibrationEnabled(this.this$0)) {
                    this.this$0.B(sportsNotificationRequest, this.$listener, this.$dynamicSportsFieldList);
                } else {
                    BleApiManager.getInstance(this.this$0).getBleApi().setUserSettings(build, new AnonymousClass1(this.this$0, sportsNotificationRequest, this.$listener, this.$dynamicSportsFieldList));
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
