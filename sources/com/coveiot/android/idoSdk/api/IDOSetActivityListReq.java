package com.coveiot.android.idoSdk.api;

import com.coveiot.android.idoSdk.IDOBleCommandName;
import com.google.android.material.color.c;
import com.ido.ble.protocol.model.SportModeSortV3;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000fj\b\u0012\u0004\u0012\u00020\u0005`\u0010\u0012\u0006\u0010\u001a\u001a\u00020\u0005¢\u0006\u0004\b\u001b\u0010\u001cJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016R\u0019\u0010\u000e\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR)\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000fj\b\u0012\u0004\u0012\u00020\u0005`\u00108\u0006@\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u001a\u001a\u00020\u00058\u0006@\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lcom/coveiot/android/idoSdk/api/IDOSetActivityListReq;", "Lcom/coveiot/android/idoSdk/api/IDOBaseReq;", "", "isMultiPacket", "isPriority", "", "getTimeOut", "Lcom/coveiot/android/idoSdk/IDOBleCommandName;", "getCommandName", "Lcom/ido/ble/protocol/model/SportModeSortV3;", c.f10260a, "Lcom/ido/ble/protocol/model/SportModeSortV3;", "getSportModeSortV3", "()Lcom/ido/ble/protocol/model/SportModeSortV3;", "sportModeSortV3", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "d", "Ljava/util/ArrayList;", "getSport100TypeList", "()Ljava/util/ArrayList;", "sport100TypeList", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "I", "getShowListSize", "()I", "showListSize", "<init>", "(Lcom/ido/ble/protocol/model/SportModeSortV3;Ljava/util/ArrayList;I)V", "idoSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class IDOSetActivityListReq extends IDOBaseReq {
    @NotNull
    public final SportModeSortV3 c;
    @NotNull
    public final ArrayList<Integer> d;
    public final int e;

    public IDOSetActivityListReq(@NotNull SportModeSortV3 sportModeSortV3, @NotNull ArrayList<Integer> sport100TypeList, int i) {
        Intrinsics.checkNotNullParameter(sportModeSortV3, "sportModeSortV3");
        Intrinsics.checkNotNullParameter(sport100TypeList, "sport100TypeList");
        this.c = sportModeSortV3;
        this.d = sport100TypeList;
        this.e = i;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    @NotNull
    public IDOBleCommandName getCommandName() {
        return IDOBleCommandName.SET_ACTIVITY_LIST;
    }

    public final int getShowListSize() {
        return this.e;
    }

    @NotNull
    public final ArrayList<Integer> getSport100TypeList() {
        return this.d;
    }

    @NotNull
    public final SportModeSortV3 getSportModeSortV3() {
        return this.c;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    public int getTimeOut() {
        return 30000;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    public boolean isPriority() {
        return true;
    }
}
