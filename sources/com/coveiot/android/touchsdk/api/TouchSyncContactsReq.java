package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGContacts;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchSyncContactsReq extends TouchELXBaseReq {
    @NotNull
    public final List<TGContacts> e;

    /* JADX WARN: Multi-variable type inference failed */
    public TouchSyncContactsReq(@NotNull List<? extends TGContacts> contactsList) {
        Intrinsics.checkNotNullParameter(contactsList, "contactsList");
        this.e = contactsList;
    }

    @NotNull
    public final List<TGContacts> getContactsList() {
        return this.e;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return true;
    }
}
