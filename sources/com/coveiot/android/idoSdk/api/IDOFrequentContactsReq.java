package com.coveiot.android.idoSdk.api;

import com.coveiot.android.idoSdk.IDOBleCommandName;
import com.coveiot.android.idoSdk.IDOConstants;
import com.google.android.material.color.c;
import com.ido.ble.protocol.model.FrequentContactsV3;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016R)\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b8\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/coveiot/android/idoSdk/api/IDOFrequentContactsReq;", "Lcom/coveiot/android/idoSdk/api/IDOBaseReq;", "", "isMultiPacket", "isPriority", "", "getTimeOut", "Lcom/coveiot/android/idoSdk/IDOBleCommandName;", "getCommandName", "Ljava/util/ArrayList;", "Lcom/ido/ble/protocol/model/FrequentContactsV3;", "Lkotlin/collections/ArrayList;", c.f10260a, "Ljava/util/ArrayList;", "getFrequentContactsList", "()Ljava/util/ArrayList;", "frequentContactsList", "<init>", "(Ljava/util/ArrayList;)V", "idoSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class IDOFrequentContactsReq extends IDOBaseReq {
    @NotNull
    public final ArrayList<FrequentContactsV3> c;

    public IDOFrequentContactsReq(@NotNull ArrayList<FrequentContactsV3> frequentContactsList) {
        Intrinsics.checkNotNullParameter(frequentContactsList, "frequentContactsList");
        this.c = frequentContactsList;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    @NotNull
    public IDOBleCommandName getCommandName() {
        return IDOBleCommandName.SET_FREQUENT_CONTACT_LIST;
    }

    @NotNull
    public final ArrayList<FrequentContactsV3> getFrequentContactsList() {
        return this.c;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    public int getTimeOut() {
        return IDOConstants.MULTI_PACKET_CMD_MS_TIMER;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    public boolean isPriority() {
        return false;
    }
}
