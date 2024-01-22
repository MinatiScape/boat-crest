package com.coveiot.android.eastapexsdk.api;

import com.apex.bluetooth.model.EABleSocialContact;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u000e\u0010\rJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/coveiot/android/eastapexsdk/api/EastApexSetCallStatusReq;", "Lcom/coveiot/android/eastapexsdk/api/EastApexBaseReq;", "", "isMultiPacket", "isPriority", "", "getCommandType", "Lcom/apex/bluetooth/model/EABleSocialContact;", "eaBleSocialContact", "Lcom/apex/bluetooth/model/EABleSocialContact;", "getEaBleSocialContact", "()Lcom/apex/bluetooth/model/EABleSocialContact;", "setEaBleSocialContact", "(Lcom/apex/bluetooth/model/EABleSocialContact;)V", "<init>", "eastapexSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes4.dex */
public final class EastApexSetCallStatusReq extends EastApexBaseReq {
    @NotNull
    public EABleSocialContact c;

    public EastApexSetCallStatusReq(@NotNull EABleSocialContact eaBleSocialContact) {
        Intrinsics.checkNotNullParameter(eaBleSocialContact, "eaBleSocialContact");
        this.c = eaBleSocialContact;
    }

    @Override // com.coveiot.android.eastapexsdk.api.EastApexBaseReq
    @NotNull
    public String getCommandType() {
        String simpleName = EastApexSetCallStatusReq.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        return simpleName;
    }

    @NotNull
    public final EABleSocialContact getEaBleSocialContact() {
        return this.c;
    }

    @Override // com.coveiot.android.eastapexsdk.api.EastApexBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.eastapexsdk.api.EastApexBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setEaBleSocialContact(@NotNull EABleSocialContact eABleSocialContact) {
        Intrinsics.checkNotNullParameter(eABleSocialContact, "<set-?>");
        this.c = eABleSocialContact;
    }
}
