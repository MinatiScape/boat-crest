package com.coveiot.android.bleabstract.services;

import com.coveiot.android.bleabstract.formatter.SMATemperatureFormatter;
import com.coveiot.khsmadb.temperature.KhTemperatureRepository;
import com.szabh.smable3.entity.BleTemperature;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onReadTemperature$1", f = "SmaBaseBleService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class SmaBaseBleService$bleHandleCallback$2$1$onReadTemperature$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List<BleTemperature> f3923a;
    public final /* synthetic */ SmaBaseBleService b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaBaseBleService$bleHandleCallback$2$1$onReadTemperature$1(List<BleTemperature> list, SmaBaseBleService smaBaseBleService, Continuation<? super SmaBaseBleService$bleHandleCallback$2$1$onReadTemperature$1> continuation) {
        super(2, continuation);
        this.f3923a = list;
        this.b = smaBaseBleService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SmaBaseBleService$bleHandleCallback$2$1$onReadTemperature$1(this.f3923a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SmaBaseBleService$bleHandleCallback$2$1$onReadTemperature$1(this.f3923a, this.b, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        try {
            if (!this.f3923a.isEmpty()) {
                KhTemperatureRepository.Companion.getInstance(this.b).insertTemperatureData(SMATemperatureFormatter.Companion.getInstance(this.b).getKhBleTemperature(this.b.getMacAddress(), this.f3923a));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }
}
