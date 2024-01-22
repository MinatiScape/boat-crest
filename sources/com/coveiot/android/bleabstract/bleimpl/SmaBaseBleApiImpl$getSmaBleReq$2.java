package com.coveiot.android.bleabstract.bleimpl;

import androidx.core.content.ContextCompat;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.smasdk.api.SmaBaseReq;
import com.coveiot.android.smasdk.api.SmaUploadContactReq;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$getSmaBleReq$2", f = "SmaBaseBleApiImpl.kt", i = {0}, l = {1242}, m = "invokeSuspend", n = {"bytes"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class SmaBaseBleApiImpl$getSmaBleReq$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public Object f3216a;
    public int b;
    public final /* synthetic */ SmaBaseBleApiImpl c;
    public final /* synthetic */ Ref.ObjectRef<SmaBaseReq> d;
    public final /* synthetic */ BleBaseRequest e;

    @DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$getSmaBleReq$2$1", f = "SmaBaseBleApiImpl.kt", i = {}, l = {1243}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$getSmaBleReq$2$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public Object f3217a;
        public int b;
        public final /* synthetic */ Ref.ObjectRef<byte[]> c;
        public final /* synthetic */ SmaBaseBleApiImpl d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Ref.ObjectRef<byte[]> objectRef, SmaBaseBleApiImpl smaBaseBleApiImpl, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.c = objectRef;
            this.d = smaBaseBleApiImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(this.c, this.d, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.c, this.d, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Ref.ObjectRef<byte[]> objectRef;
            T t;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.b;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<byte[]> objectRef2 = this.c;
                SmaBaseBleApiImpl smaBaseBleApiImpl = this.d;
                this.f3217a = objectRef2;
                this.b = 1;
                Object contactBytes = smaBaseBleApiImpl.getContactBytes(this);
                if (contactBytes == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                t = contactBytes;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                objectRef = (Ref.ObjectRef) this.f3217a;
                ResultKt.throwOnFailure(obj);
                t = obj;
            }
            objectRef.element = t;
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaBaseBleApiImpl$getSmaBleReq$2(SmaBaseBleApiImpl smaBaseBleApiImpl, Ref.ObjectRef<SmaBaseReq> objectRef, BleBaseRequest bleBaseRequest, Continuation<? super SmaBaseBleApiImpl$getSmaBleReq$2> continuation) {
        super(2, continuation);
        this.c = smaBaseBleApiImpl;
        this.d = objectRef;
        this.e = bleBaseRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SmaBaseBleApiImpl$getSmaBleReq$2(this.c, this.d, this.e, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SmaBaseBleApiImpl$getSmaBleReq$2(this.c, this.d, this.e, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [com.coveiot.android.smasdk.api.SmaUploadContactReq, T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Ref.ObjectRef objectRef;
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.b;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            if (ContextCompat.checkSelfPermission(this.c.getContext(), "android.permission.READ_CONTACTS") == 0) {
                CoroutineDispatcher io2 = Dispatchers.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(objectRef2, this.c, null);
                this.f3216a = objectRef2;
                this.b = 1;
                if (BuildersKt.withContext(io2, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            objectRef = objectRef2;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            objectRef = (Ref.ObjectRef) this.f3216a;
            ResultKt.throwOnFailure(obj);
        }
        T t = objectRef.element;
        if (t != 0) {
            Intrinsics.checkNotNull(t);
            if (!(((byte[]) t).length == 0)) {
                this.d.element = new SmaUploadContactReq();
                SmaBaseReq smaBaseReq = this.d.element;
                Intrinsics.checkNotNull(smaBaseReq, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaUploadContactReq");
                ((SmaUploadContactReq) smaBaseReq).setReqId(this.e.getRequId());
                SmaBaseReq smaBaseReq2 = this.d.element;
                Intrinsics.checkNotNull(smaBaseReq2, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaUploadContactReq");
                ((SmaUploadContactReq) smaBaseReq2).setKey(BleKey.CONTACT);
                SmaBaseReq smaBaseReq3 = this.d.element;
                Intrinsics.checkNotNull(smaBaseReq3, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaUploadContactReq");
                ((SmaUploadContactReq) smaBaseReq3).setContactBytes((byte[]) objectRef.element);
                SmaBaseReq smaBaseReq4 = this.d.element;
                Intrinsics.checkNotNull(smaBaseReq4, "null cannot be cast to non-null type com.coveiot.android.smasdk.api.SmaUploadContactReq");
                ((SmaUploadContactReq) smaBaseReq4).setKeyFlag(BleKeyFlag.UPDATE);
            }
        }
        return Unit.INSTANCE;
    }
}
