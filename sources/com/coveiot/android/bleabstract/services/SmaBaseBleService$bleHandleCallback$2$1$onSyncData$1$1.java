package com.coveiot.android.bleabstract.services;

import com.coveiot.android.bleabstract.formatter.SMAFormatter;
import com.coveiot.android.bleabstract.formatter.SMAWorkout2Formatter;
import com.coveiot.android.bleabstract.formatter.SMAWorkoutFormatter;
import com.coveiot.android.smasdk.SmaResponseListener;
import com.coveiot.android.smasdk.api.SmaBaseReq;
import com.coveiot.android.smasdk.api.SmaBaseRes;
import com.coveiot.khsmadb.bp.KhBleBloodPressure;
import com.coveiot.khsmadb.bp.KhBloodPressureRepository;
import com.coveiot.khsmadb.spo2.KhBleSpO2;
import com.coveiot.khsmadb.spo2.KhSpO2Repository;
import com.coveiot.khsmadb.workout.KhWorkoutRepository;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.szabh.smable3.BleKey;
import java.util.List;
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
@DebugMetadata(c = "com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1", f = "SmaBaseBleService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3926a;
    public final /* synthetic */ BleKey b;
    public final /* synthetic */ SmaBaseBleService c;

    @DebugMetadata(c = "com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1$4", f = "SmaBaseBleService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SmaBaseBleService f3927a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(SmaBaseBleService smaBaseBleService, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.f3927a = smaBaseBleService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass4(this.f3927a, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass4(this.f3927a, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            if (this.f3927a.getKhCurrentCommand() != null) {
                SmaBaseRes smaBaseRes = new SmaBaseRes();
                SmaBaseReq khCurrentCommand = this.f3927a.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                smaBaseRes.setBaseReq(khCurrentCommand);
                List<KhBleBloodPressure> allBloodPressureData = KhBloodPressureRepository.Companion.getInstance(this.f3927a).getAllBloodPressureData(this.f3927a.getMacAddress(), false);
                if (allBloodPressureData != null) {
                    smaBaseRes.setObj(SMAFormatter.Companion.getInstance(this.f3927a).convertBPData(allBloodPressureData));
                }
                SmaBaseReq khCurrentCommand2 = this.f3927a.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                SmaResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(smaBaseRes);
            } else {
                LogsHelper.d(this.f3927a.getTAG(), "khCurrent command is null");
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1$5", f = "SmaBaseBleService.kt", i = {0}, l = {977}, m = "invokeSuspend", n = {"baseRes"}, s = {"L$0"})
    /* renamed from: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1$5  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public Object f3928a;
        public int b;
        public final /* synthetic */ SmaBaseBleService c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(SmaBaseBleService smaBaseBleService, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.c = smaBaseBleService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass5(this.c, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass5(this.c, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            SmaBaseRes smaBaseRes;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.b;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SmaBaseRes smaBaseRes2 = new SmaBaseRes();
                this.f3928a = smaBaseRes2;
                this.b = 1;
                if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                smaBaseRes = smaBaseRes2;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                smaBaseRes = (SmaBaseRes) this.f3928a;
                ResultKt.throwOnFailure(obj);
            }
            SmaBaseReq khCurrentCommand = this.c.getKhCurrentCommand();
            Intrinsics.checkNotNull(khCurrentCommand);
            smaBaseRes.setBaseReq(khCurrentCommand);
            smaBaseRes.setObj(SMAWorkoutFormatter.Companion.getInstance(this.c).getSportModeHistoryData(this.c.getMacAddress(), KhWorkoutRepository.Companion.getInstance(this.c).getUnMarkedWorkoutList(this.c.getMacAddress())));
            SmaBaseReq khCurrentCommand2 = this.c.getKhCurrentCommand();
            Intrinsics.checkNotNull(khCurrentCommand2);
            SmaResponseListener responseListener = khCurrentCommand2.getResponseListener();
            Intrinsics.checkNotNull(responseListener);
            responseListener.onResponse(smaBaseRes);
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1$6", f = "SmaBaseBleService.kt", i = {0}, l = {996}, m = "invokeSuspend", n = {"baseRes"}, s = {"L$0"})
    /* renamed from: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1$6  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public Object f3929a;
        public int b;
        public final /* synthetic */ SmaBaseBleService c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(SmaBaseBleService smaBaseBleService, Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
            this.c = smaBaseBleService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass6(this.c, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass6(this.c, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            SmaBaseRes smaBaseRes;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.b;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SmaBaseRes smaBaseRes2 = new SmaBaseRes();
                this.f3929a = smaBaseRes2;
                this.b = 1;
                if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                smaBaseRes = smaBaseRes2;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                smaBaseRes = (SmaBaseRes) this.f3929a;
                ResultKt.throwOnFailure(obj);
            }
            SmaBaseReq khCurrentCommand = this.c.getKhCurrentCommand();
            Intrinsics.checkNotNull(khCurrentCommand);
            smaBaseRes.setBaseReq(khCurrentCommand);
            smaBaseRes.setObj(SMAWorkout2Formatter.Companion.getInstance(this.c).getSportModeHistoryData(this.c.getMacAddress(), KhWorkoutRepository.Companion.getInstance(this.c).getUnMarkedWorkoutList(this.c.getMacAddress())));
            SmaBaseReq khCurrentCommand2 = this.c.getKhCurrentCommand();
            Intrinsics.checkNotNull(khCurrentCommand2);
            SmaResponseListener responseListener = khCurrentCommand2.getResponseListener();
            Intrinsics.checkNotNull(responseListener);
            responseListener.onResponse(smaBaseRes);
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1$7", f = "SmaBaseBleService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1$7  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SmaBaseBleService f3930a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(SmaBaseBleService smaBaseBleService, Continuation<? super AnonymousClass7> continuation) {
            super(2, continuation);
            this.f3930a = smaBaseBleService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass7(this.f3930a, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass7(this.f3930a, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            if (this.f3930a.getKhCurrentCommand() != null) {
                SmaBaseRes smaBaseRes = new SmaBaseRes();
                SmaBaseReq khCurrentCommand = this.f3930a.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                smaBaseRes.setBaseReq(khCurrentCommand);
                List<KhBleSpO2> allSpO2Data = KhSpO2Repository.Companion.getInstance(this.f3930a).getAllSpO2Data(this.f3930a.getMacAddress(), false);
                if (allSpO2Data != null) {
                    smaBaseRes.setObj(SMAFormatter.Companion.getInstance(this.f3930a).convertSpO2Data(allSpO2Data));
                }
                SmaBaseReq khCurrentCommand2 = this.f3930a.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                SmaResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(smaBaseRes);
            } else {
                LogsHelper.d(this.f3930a.getTAG(), "khCurrent command is null");
            }
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BleKey.values().length];
            try {
                iArr[BleKey.HEART_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BleKey.TEMPERATURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BleKey.PRESSURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[BleKey.SLEEP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[BleKey.ACTIVITY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[BleKey.BLOOD_PRESSURE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[BleKey.LOCATION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[BleKey.WORKOUT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[BleKey.WORKOUT2.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[BleKey.BLOOD_OXYGEN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[BleKey.NONE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1(int i, BleKey bleKey, SmaBaseBleService smaBaseBleService, Continuation<? super SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1> continuation) {
        super(2, continuation);
        this.f3926a = i;
        this.b = bleKey;
        this.c = smaBaseBleService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1(this.f3926a, this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1(this.f3926a, this.b, this.c, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0331, code lost:
        r13.setObj(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x03cf, code lost:
        r13.setObj(com.coveiot.android.bleabstract.formatter.SMAFormatter.Companion.getInstance(r12.c).convertStressData(r0, 0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x043f, code lost:
        r13.setObj(com.coveiot.android.bleabstract.formatter.SMAFormatter.Companion.getInstance(r12.c).convertTemperatureData(r0, 0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x04ae, code lost:
        r13.setObj(com.coveiot.android.bleabstract.formatter.SMAFormatter.Companion.getInstance(r12.c).convertHrData(r0, 0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0156, code lost:
        r0.setObj(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01d0, code lost:
        r13.setObj(r0.element);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x024c, code lost:
        r13.setObj(r0.element);
     */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02c0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0292 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r13v23, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r13v32, types: [java.util.List, T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) {
        /*
            Method dump skipped, instructions count: 1278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
