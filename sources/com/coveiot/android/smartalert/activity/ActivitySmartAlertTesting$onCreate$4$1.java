package com.coveiot.android.smartalert.activity;

import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.android.bleabstract.request.SetNonSmartAlertApplicationContentRequest;
import com.coveiot.android.bleabstract.request.SetSmartAlertApplicationContentRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.smartalert.SmartAlertHandler;
import com.coveiot.android.smartalert.databinding.ActivitySmartAlertTestingBinding;
import com.coveiot.android.smartalert.model.DisplayPosition;
import com.coveiot.android.smartalert.model.ParsingOutput;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$4$1", f = "ActivitySmartAlertTesting.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class ActivitySmartAlertTesting$onCreate$4$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ ActivitySmartAlertTesting this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivitySmartAlertTesting$onCreate$4$1(ActivitySmartAlertTesting activitySmartAlertTesting, Continuation<? super ActivitySmartAlertTesting$onCreate$4$1> continuation) {
        super(2, continuation);
        this.this$0 = activitySmartAlertTesting;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivitySmartAlertTesting$onCreate$4$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivitySmartAlertTesting$onCreate$4$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding;
        ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding2;
        ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding3;
        ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding4;
        int i;
        ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding5;
        ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding6;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ActivitySmartAlertTesting activitySmartAlertTesting = this.this$0;
            SmartAlertHandler smartAlertHandler = new SmartAlertHandler(activitySmartAlertTesting, activitySmartAlertTesting.getAppPackageName());
            ActivitySmartAlertTesting activitySmartAlertTesting2 = this.this$0;
            activitySmartAlertTestingBinding = activitySmartAlertTesting2.p;
            ActivitySmartAlertTestingBinding activitySmartAlertTestingBinding7 = null;
            if (activitySmartAlertTestingBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySmartAlertTestingBinding = null;
            }
            String obj2 = activitySmartAlertTestingBinding.title.getText().toString();
            activitySmartAlertTestingBinding2 = this.this$0.p;
            if (activitySmartAlertTestingBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySmartAlertTestingBinding2 = null;
            }
            ParsingOutput parsedMessage = smartAlertHandler.getParsedMessage(activitySmartAlertTesting2, obj2, activitySmartAlertTestingBinding2.content.getText().toString());
            if (BleApiManager.getInstance(this.this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED && parsedMessage != null) {
                boolean z = false;
                if (parsedMessage.isParsed()) {
                    List<DynamicSportsField> dynamicSportFields = parsedMessage.getDynamicSportFields();
                    if (!(dynamicSportFields == null || dynamicSportFields.isEmpty())) {
                        i = Intrinsics.areEqual(this.this$0.getAppPackageName(), "com.ubercab") ? 1 : 2;
                        int ordinal = DisplayPosition.LOCK_SCREEN.ordinal();
                        List<DynamicSportsField> dynamicSportFields2 = parsedMessage.getDynamicSportFields();
                        Intrinsics.checkNotNull(dynamicSportFields2);
                        SetSmartAlertApplicationContentRequest setSmartAlertApplicationContentRequest = new SetSmartAlertApplicationContentRequest(i, ordinal, dynamicSportFields2);
                        BleApi bleApi = BleApiManager.getInstance(this.this$0).getBleApi();
                        final ActivitySmartAlertTesting activitySmartAlertTesting3 = this.this$0;
                        bleApi.setUserSettings(setSmartAlertApplicationContentRequest, new SettingsResultListener() { // from class: com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$4$1.1

                            @DebugMetadata(c = "com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$4$1$1$onSettingsError$1", f = "ActivitySmartAlertTesting.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$4$1$1$a */
                            /* loaded from: classes6.dex */
                            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                public int label;
                                public final /* synthetic */ ActivitySmartAlertTesting this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public a(ActivitySmartAlertTesting activitySmartAlertTesting, Continuation<? super a> continuation) {
                                    super(2, continuation);
                                    this.this$0 = activitySmartAlertTesting;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                @NotNull
                                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                    return new a(this.this$0, continuation);
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
                                        Toast.makeText(this.this$0, "Failed", 0).show();
                                        return Unit.INSTANCE;
                                    }
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                            }

                            @DebugMetadata(c = "com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$4$1$1$onSettingsResponse$1", f = "ActivitySmartAlertTesting.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$4$1$1$b */
                            /* loaded from: classes6.dex */
                            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                public int label;
                                public final /* synthetic */ ActivitySmartAlertTesting this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public b(ActivitySmartAlertTesting activitySmartAlertTesting, Continuation<? super b> continuation) {
                                    super(2, continuation);
                                    this.this$0 = activitySmartAlertTesting;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                @NotNull
                                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                    return new b(this.this$0, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                @Nullable
                                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                                    return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                @Nullable
                                public final Object invokeSuspend(@NotNull Object obj) {
                                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                                    if (this.label == 0) {
                                        ResultKt.throwOnFailure(obj);
                                        Toast.makeText(this.this$0, "Success", 0).show();
                                        return Unit.INSTANCE;
                                    }
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                if (ActivitySmartAlertTesting.this.isFinishing()) {
                                    return;
                                }
                                ActivitySmartAlertTesting.this.dismissProgress();
                                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivitySmartAlertTesting.this), Dispatchers.getMain(), null, new a(ActivitySmartAlertTesting.this, null), 2, null);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                if (ActivitySmartAlertTesting.this.isFinishing()) {
                                    return;
                                }
                                ActivitySmartAlertTesting.this.dismissProgress();
                                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivitySmartAlertTesting.this), Dispatchers.getMain(), null, new b(ActivitySmartAlertTesting.this, null), 2, null);
                            }
                        });
                    }
                }
                activitySmartAlertTestingBinding3 = this.this$0.p;
                if (activitySmartAlertTestingBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySmartAlertTestingBinding3 = null;
                }
                String obj3 = activitySmartAlertTestingBinding3.title.getText().toString();
                if (!(obj3 == null || obj3.length() == 0)) {
                    activitySmartAlertTestingBinding4 = this.this$0.p;
                    if (activitySmartAlertTestingBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySmartAlertTestingBinding4 = null;
                    }
                    String obj4 = activitySmartAlertTestingBinding4.content.getText().toString();
                    if (obj4 == null || obj4.length() == 0) {
                        z = true;
                    }
                    if (!z) {
                        i = Intrinsics.areEqual(this.this$0.getAppPackageName(), "com.ubercab") ? 1 : 2;
                        int ordinal2 = DisplayPosition.LOCK_SCREEN.ordinal();
                        activitySmartAlertTestingBinding5 = this.this$0.p;
                        if (activitySmartAlertTestingBinding5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySmartAlertTestingBinding5 = null;
                        }
                        String obj5 = activitySmartAlertTestingBinding5.title.getText().toString();
                        activitySmartAlertTestingBinding6 = this.this$0.p;
                        if (activitySmartAlertTestingBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activitySmartAlertTestingBinding7 = activitySmartAlertTestingBinding6;
                        }
                        SetNonSmartAlertApplicationContentRequest setNonSmartAlertApplicationContentRequest = new SetNonSmartAlertApplicationContentRequest(i, ordinal2, obj5, activitySmartAlertTestingBinding7.content.getText().toString());
                        BleApi bleApi2 = BleApiManager.getInstance(this.this$0).getBleApi();
                        final ActivitySmartAlertTesting activitySmartAlertTesting4 = this.this$0;
                        bleApi2.setUserSettings(setNonSmartAlertApplicationContentRequest, new SettingsResultListener() { // from class: com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$4$1.2

                            @DebugMetadata(c = "com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$4$1$2$onSettingsError$1", f = "ActivitySmartAlertTesting.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$4$1$2$a */
                            /* loaded from: classes6.dex */
                            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                public int label;
                                public final /* synthetic */ ActivitySmartAlertTesting this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public a(ActivitySmartAlertTesting activitySmartAlertTesting, Continuation<? super a> continuation) {
                                    super(2, continuation);
                                    this.this$0 = activitySmartAlertTesting;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                @NotNull
                                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                    return new a(this.this$0, continuation);
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
                                        Toast.makeText(this.this$0, "Failed", 0).show();
                                        return Unit.INSTANCE;
                                    }
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                            }

                            @DebugMetadata(c = "com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$4$1$2$onSettingsResponse$1", f = "ActivitySmartAlertTesting.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: com.coveiot.android.smartalert.activity.ActivitySmartAlertTesting$onCreate$4$1$2$b */
                            /* loaded from: classes6.dex */
                            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                public int label;
                                public final /* synthetic */ ActivitySmartAlertTesting this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public b(ActivitySmartAlertTesting activitySmartAlertTesting, Continuation<? super b> continuation) {
                                    super(2, continuation);
                                    this.this$0 = activitySmartAlertTesting;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                @NotNull
                                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                    return new b(this.this$0, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                @Nullable
                                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                                    return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                @Nullable
                                public final Object invokeSuspend(@NotNull Object obj) {
                                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                                    if (this.label == 0) {
                                        ResultKt.throwOnFailure(obj);
                                        Toast.makeText(this.this$0, "Success", 0).show();
                                        return Unit.INSTANCE;
                                    }
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                if (ActivitySmartAlertTesting.this.isFinishing()) {
                                    return;
                                }
                                ActivitySmartAlertTesting.this.dismissProgress();
                                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivitySmartAlertTesting.this), Dispatchers.getMain(), null, new a(ActivitySmartAlertTesting.this, null), 2, null);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                if (ActivitySmartAlertTesting.this.isFinishing()) {
                                    return;
                                }
                                ActivitySmartAlertTesting.this.dismissProgress();
                                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivitySmartAlertTesting.this), Dispatchers.getMain(), null, new b(ActivitySmartAlertTesting.this, null), 2, null);
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
