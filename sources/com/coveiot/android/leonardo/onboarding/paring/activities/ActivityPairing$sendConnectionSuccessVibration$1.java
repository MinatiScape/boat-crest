package com.coveiot.android.leonardo.onboarding.paring.activities;

import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectionSuccessVibration$1", f = "ActivityPairing.kt", i = {}, l = {2696}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class ActivityPairing$sendConnectionSuccessVibration$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ ActivityPairing this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityPairing$sendConnectionSuccessVibration$1(ActivityPairing activityPairing, Continuation<? super ActivityPairing$sendConnectionSuccessVibration$1> continuation) {
        super(2, continuation);
        this.this$0 = activityPairing;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityPairing$sendConnectionSuccessVibration$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityPairing$sendConnectionSuccessVibration$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        DeviceInfoRequest build = new DeviceInfoRequest.Builder().setIsFwVersion(true).build();
        BleApi bleApi = BleApiManager.getInstance(this.this$0).getBleApi();
        final ActivityPairing activityPairing = this.this$0;
        bleApi.getData(build, new DataResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectionSuccessVibration$1.1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            /* JADX WARN: Removed duplicated region for block: B:24:0x005e A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onDataResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.response.BleBaseResponse r15) {
                /*
                    r14 = this;
                    java.lang.String r0 = "response"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
                    java.lang.Object r15 = r15.getResponseData()
                    java.lang.String r0 = "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse"
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r15, r0)
                    com.coveiot.android.bleabstract.response.DeviceInfoResponse r15 = (com.coveiot.android.bleabstract.response.DeviceInfoResponse) r15
                    com.coveiot.android.bleabstract.response.DeviceInfoData r15 = r15.getDeviceInfo()
                    if (r15 == 0) goto L83
                    java.lang.String r0 = r15.getFwVersion()
                    boolean r0 = com.coveiot.utils.utility.AppUtils.isEmpty(r0)
                    if (r0 != 0) goto L83
                    java.lang.String r15 = r15.getFwVersion()
                    if (r15 == 0) goto L5b
                    java.util.Locale r0 = java.util.Locale.ENGLISH
                    java.lang.String r1 = "ENGLISH"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                    java.lang.String r2 = r15.toLowerCase(r0)
                    java.lang.String r15 = "this as java.lang.String).toLowerCase(locale)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r15)
                    if (r2 == 0) goto L5b
                    r5 = 0
                    r6 = 4
                    r7 = 0
                    java.lang.String r3 = "v"
                    java.lang.String r4 = ""
                    java.lang.String r8 = kotlin.text.m.replace$default(r2, r3, r4, r5, r6, r7)
                    if (r8 == 0) goto L5b
                    r11 = 0
                    r12 = 4
                    r13 = 0
                    java.lang.String r9 = "."
                    java.lang.String r10 = ""
                    java.lang.String r15 = kotlin.text.m.replace$default(r8, r9, r10, r11, r12, r13)
                    if (r15 == 0) goto L5b
                    int r15 = java.lang.Integer.parseInt(r15)
                    java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
                    goto L5c
                L5b:
                    r15 = 0
                L5c:
                    if (r15 == 0) goto L83
                    int r15 = r15.intValue()     // Catch: java.lang.Exception -> L7f
                    r0 = 472(0x1d8, float:6.61E-43)
                    if (r15 < r0) goto L83
                    com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing r15 = com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing.this     // Catch: java.lang.Exception -> L7f
                    com.coveiot.android.bleabstract.api.BleApiManager r15 = com.coveiot.android.bleabstract.api.BleApiManager.getInstance(r15)     // Catch: java.lang.Exception -> L7f
                    com.coveiot.android.bleabstract.api.BleApi r15 = r15.getBleApi()     // Catch: java.lang.Exception -> L7f
                    com.coveiot.android.bleabstract.request.PairingFlowRequest r0 = new com.coveiot.android.bleabstract.request.PairingFlowRequest     // Catch: java.lang.Exception -> L7f
                    r1 = 1
                    r0.<init>(r1)     // Catch: java.lang.Exception -> L7f
                    com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectionSuccessVibration$1$1$onDataResponse$1 r1 = new com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectionSuccessVibration$1$1$onDataResponse$1     // Catch: java.lang.Exception -> L7f
                    r1.<init>()     // Catch: java.lang.Exception -> L7f
                    r15.setUserSettings(r0, r1)     // Catch: java.lang.Exception -> L7f
                    goto L83
                L7f:
                    r15 = move-exception
                    r15.printStackTrace()
                L83:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectionSuccessVibration$1.AnonymousClass1.onDataResponse(com.coveiot.android.bleabstract.response.BleBaseResponse):void");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
        return Unit.INSTANCE;
    }
}
