package com.coveiot.android.eastapexsdk.api;

import com.apex.bluetooth.model.EABleContact;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016R\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u00020\r8\u0006@\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/coveiot/android/eastapexsdk/api/EastApexSyncContactsReq;", "Lcom/coveiot/android/eastapexsdk/api/EastApexBaseReq;", "", "isMultiPacket", "isPriority", "", "getCommandType", "", "Lcom/apex/bluetooth/model/EABleContact;", "contactsList", "Ljava/util/List;", "getContactsList", "()Ljava/util/List;", "", "flag", "I", "getFlag", "()I", "<init>", "(Ljava/util/List;I)V", "eastapexSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes4.dex */
public final class EastApexSyncContactsReq extends EastApexBaseReq {
    @NotNull
    public final List<EABleContact> c;
    public final int d;

    /* JADX WARN: Multi-variable type inference failed */
    public EastApexSyncContactsReq(@NotNull List<? extends EABleContact> contactsList, int i) {
        Intrinsics.checkNotNullParameter(contactsList, "contactsList");
        this.c = contactsList;
        this.d = i;
    }

    @Override // com.coveiot.android.eastapexsdk.api.EastApexBaseReq
    @NotNull
    public String getCommandType() {
        String simpleName = EastApexSyncContactsReq.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        return simpleName;
    }

    @NotNull
    public final List<EABleContact> getContactsList() {
        return this.c;
    }

    public final int getFlag() {
        return this.d;
    }

    @Override // com.coveiot.android.eastapexsdk.api.EastApexBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.eastapexsdk.api.EastApexBaseReq
    public boolean isPriority() {
        return true;
    }
}
