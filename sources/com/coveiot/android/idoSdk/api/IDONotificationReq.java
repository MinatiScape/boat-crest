package com.coveiot.android.idoSdk.api;

import com.coveiot.android.idoSdk.IDOBleCommandName;
import com.google.android.material.color.c;
import com.ido.ble.protocol.model.IncomingCallInfo;
import com.ido.ble.protocol.model.NewMessageInfo;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0015\u0010\u0016J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016R\u001b\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/coveiot/android/idoSdk/api/IDONotificationReq;", "Lcom/coveiot/android/idoSdk/api/IDOBaseReq;", "", "isMultiPacket", "isPriority", "", "getTimeOut", "Lcom/coveiot/android/idoSdk/IDOBleCommandName;", "getCommandName", "Lcom/ido/ble/protocol/model/NewMessageInfo;", c.f10260a, "Lcom/ido/ble/protocol/model/NewMessageInfo;", "getMessageInfo", "()Lcom/ido/ble/protocol/model/NewMessageInfo;", "messageInfo", "Lcom/ido/ble/protocol/model/IncomingCallInfo;", "d", "Lcom/ido/ble/protocol/model/IncomingCallInfo;", "getIncomingCallInfo", "()Lcom/ido/ble/protocol/model/IncomingCallInfo;", "incomingCallInfo", "<init>", "(Lcom/ido/ble/protocol/model/NewMessageInfo;Lcom/ido/ble/protocol/model/IncomingCallInfo;)V", "idoSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class IDONotificationReq extends IDOBaseReq {
    @Nullable
    public final NewMessageInfo c;
    @Nullable
    public final IncomingCallInfo d;

    public IDONotificationReq(@Nullable NewMessageInfo newMessageInfo, @Nullable IncomingCallInfo incomingCallInfo) {
        this.c = newMessageInfo;
        this.d = incomingCallInfo;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    @NotNull
    public IDOBleCommandName getCommandName() {
        return IDOBleCommandName.SET_SOCIAL_NOTIFICATION;
    }

    @Nullable
    public final IncomingCallInfo getIncomingCallInfo() {
        return this.d;
    }

    @Nullable
    public final NewMessageInfo getMessageInfo() {
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
