package com.coveiot.android.bleabstract.utils.jstyleUtils;

import android.content.Context;
import com.coveiot.android.jstyle1860Sdk.model.JstyleGpsData;
import com.coveiot.khjstyledb.gps.KHJstyleGPSRepository;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.utils.jstyleUtils.JStyle1860ProcessData$insertSessionGPSData$1", f = "JStyle1860ProcessData.kt", i = {}, l = {584}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class JStyle1860ProcessData$insertSessionGPSData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public int f4058a;
    public final /* synthetic */ ArrayList<JstyleGpsData> b;
    public final /* synthetic */ String c;
    public final /* synthetic */ Context d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JStyle1860ProcessData$insertSessionGPSData$1(ArrayList<JstyleGpsData> arrayList, String str, Context context, Continuation<? super JStyle1860ProcessData$insertSessionGPSData$1> continuation) {
        super(2, continuation);
        this.b = arrayList;
        this.c = str;
        this.d = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new JStyle1860ProcessData$insertSessionGPSData$1(this.b, this.c, this.d, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new JStyle1860ProcessData$insertSessionGPSData$1(this.b, this.c, this.d, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = a.getCOROUTINE_SUSPENDED();
        int i = this.f4058a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayList access$getEntityGPSSessionData = JStyle1860ProcessData.access$getEntityGPSSessionData(JStyle1860ProcessData.INSTANCE, this.b, this.c);
            this.f4058a = 1;
            if (KHJstyleGPSRepository.Companion.getInstance(this.d).insertSessionGPSData(access$getEntityGPSSessionData, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
