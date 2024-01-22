package com.coveiot.android.crpsdk.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016R\"\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/coveiot/android/crpsdk/api/CRPSetSongTitleReq;", "Lcom/coveiot/android/crpsdk/api/CRPBaseReq;", "", "isMultiPacket", "isPriority", "", "getCommandType", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class CRPSetSongTitleReq extends CRPBaseReq {
    @NotNull
    public String c = "";

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    @NotNull
    public String getCommandType() {
        String simpleName = CRPSetSongTitleReq.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        return simpleName;
    }

    @NotNull
    public final String getTitle() {
        return this.c;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.c = str;
    }
}
