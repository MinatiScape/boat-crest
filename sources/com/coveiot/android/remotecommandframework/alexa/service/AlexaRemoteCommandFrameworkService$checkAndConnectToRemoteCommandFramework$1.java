package com.coveiot.android.remotecommandframework.alexa.service;

import com.coveiot.android.remotecommandframework.listener.OnSuccessFailureListener;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.utility.LogHelper;
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
@DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$checkAndConnectToRemoteCommandFramework$1", f = "AlexaRemoteCommandFrameworkService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class AlexaRemoteCommandFrameworkService$checkAndConnectToRemoteCommandFramework$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ AlexaRemoteCommandFrameworkService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlexaRemoteCommandFrameworkService$checkAndConnectToRemoteCommandFramework$1(AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService, Continuation<? super AlexaRemoteCommandFrameworkService$checkAndConnectToRemoteCommandFramework$1> continuation) {
        super(2, continuation);
        this.this$0 = alexaRemoteCommandFrameworkService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new AlexaRemoteCommandFrameworkService$checkAndConnectToRemoteCommandFramework$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((AlexaRemoteCommandFrameworkService$checkAndConnectToRemoteCommandFramework$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        DeviceModelBean deviceModelBean;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (SessionManager.getInstance(this.this$0).isDevicePaired() && (deviceModelBean = SessionManager.getInstance(this.this$0).getDeviceModelBean()) != null && deviceModelBean.getRemoteFrameworkSupported() != null) {
                Boolean remoteFrameworkSupported = deviceModelBean.getRemoteFrameworkSupported();
                Intrinsics.checkNotNullExpressionValue(remoteFrameworkSupported, "deviceModelBean.remoteFrameworkSupported");
                if (remoteFrameworkSupported.booleanValue()) {
                    if (!this.this$0.isConnected()) {
                        final AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService = this.this$0;
                        alexaRemoteCommandFrameworkService.connect(new OnSuccessFailureListener() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$checkAndConnectToRemoteCommandFramework$1.1
                            @Override // com.coveiot.android.remotecommandframework.listener.OnSuccessFailureListener
                            public void onFailure(@Nullable String str) {
                                String str2;
                                str2 = AlexaRemoteCommandFrameworkService.this.h;
                                LogHelper.d(str2, "Remote Command Framework failed to connected.");
                            }

                            @Override // com.coveiot.android.remotecommandframework.listener.OnSuccessFailureListener
                            public void onSuccess() {
                                String str;
                                str = AlexaRemoteCommandFrameworkService.this.h;
                                LogHelper.d(str, "Remote Command Framework successfully connected.");
                                final AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService2 = AlexaRemoteCommandFrameworkService.this;
                                alexaRemoteCommandFrameworkService2.p(new OnSuccessFailureListener() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$checkAndConnectToRemoteCommandFramework$1$1$onSuccess$1
                                    @Override // com.coveiot.android.remotecommandframework.listener.OnSuccessFailureListener
                                    public void onFailure(@Nullable String str2) {
                                        String str3;
                                        str3 = AlexaRemoteCommandFrameworkService.this.h;
                                        LogHelper.d(str3, "Updated Account Linking Status to Failed.");
                                    }

                                    @Override // com.coveiot.android.remotecommandframework.listener.OnSuccessFailureListener
                                    public void onSuccess() {
                                        String str2;
                                        str2 = AlexaRemoteCommandFrameworkService.this.h;
                                        LogHelper.d(str2, "Updated Account Linking Status to Success.");
                                    }
                                });
                            }
                        });
                    } else {
                        final AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService2 = this.this$0;
                        alexaRemoteCommandFrameworkService2.p(new OnSuccessFailureListener() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$checkAndConnectToRemoteCommandFramework$1.2
                            @Override // com.coveiot.android.remotecommandframework.listener.OnSuccessFailureListener
                            public void onFailure(@Nullable String str) {
                                String str2;
                                str2 = AlexaRemoteCommandFrameworkService.this.h;
                                LogHelper.d(str2, "Updated Account Linking Status to Failed.");
                            }

                            @Override // com.coveiot.android.remotecommandframework.listener.OnSuccessFailureListener
                            public void onSuccess() {
                                String str;
                                str = AlexaRemoteCommandFrameworkService.this.h;
                                LogHelper.d(str, "Updated Account Linking Status to Success.");
                                AlexaRemoteCommandFrameworkService.this.subscribe();
                            }
                        });
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
