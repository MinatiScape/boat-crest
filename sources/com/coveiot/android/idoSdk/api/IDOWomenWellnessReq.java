package com.coveiot.android.idoSdk.api;

import com.coveiot.android.idoSdk.IDOBleCommandName;
import com.google.android.material.color.c;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0015\u0010\u0016J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016R\u0019\u0010\u000e\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0014\u001a\u00020\u000f8\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/coveiot/android/idoSdk/api/IDOWomenWellnessReq;", "Lcom/coveiot/android/idoSdk/api/IDOBaseReq;", "", "isMultiPacket", "isPriority", "", "getTimeOut", "Lcom/coveiot/android/idoSdk/IDOBleCommandName;", "getCommandName", "Lcom/ido/ble/protocol/model/Menstrual;", c.f10260a, "Lcom/ido/ble/protocol/model/Menstrual;", "getMenstrual", "()Lcom/ido/ble/protocol/model/Menstrual;", "menstrual", "Lcom/ido/ble/protocol/model/MenstrualRemind;", "d", "Lcom/ido/ble/protocol/model/MenstrualRemind;", "getMenstrualRemind", "()Lcom/ido/ble/protocol/model/MenstrualRemind;", "menstrualRemind", "<init>", "(Lcom/ido/ble/protocol/model/Menstrual;Lcom/ido/ble/protocol/model/MenstrualRemind;)V", "idoSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class IDOWomenWellnessReq extends IDOBaseReq {
    @NotNull
    public final Menstrual c;
    @NotNull
    public final MenstrualRemind d;

    public IDOWomenWellnessReq(@NotNull Menstrual menstrual, @NotNull MenstrualRemind menstrualRemind) {
        Intrinsics.checkNotNullParameter(menstrual, "menstrual");
        Intrinsics.checkNotNullParameter(menstrualRemind, "menstrualRemind");
        this.c = menstrual;
        this.d = menstrualRemind;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    @NotNull
    public IDOBleCommandName getCommandName() {
        return IDOBleCommandName.SET_WOMEN_WELLNESS;
    }

    @NotNull
    public final Menstrual getMenstrual() {
        return this.c;
    }

    @NotNull
    public final MenstrualRemind getMenstrualRemind() {
        return this.d;
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
