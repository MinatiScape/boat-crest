package com.coveiot.repository.datasync;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.repository.datasync.ServerSyncUtils$serverUpload$2", f = "ServerSyncUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
public final class ServerSyncUtils$serverUpload$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $serial_no;
    public int label;
    public final /* synthetic */ ServerSyncUtils this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ServerSyncUtils$serverUpload$2(ServerSyncUtils serverSyncUtils, String str, Continuation<? super ServerSyncUtils$serverUpload$2> continuation) {
        super(2, continuation);
        this.this$0 = serverSyncUtils;
        this.$serial_no = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ServerSyncUtils$serverUpload$2(this.this$0, this.$serial_no, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ServerSyncUtils$serverUpload$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:256:0x07be  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x07d4  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x07e8  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x07fe  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0812  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0828  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 2206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.repository.datasync.ServerSyncUtils$serverUpload$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
