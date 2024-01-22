package com.coveiot.android.idoSdk.api;

import com.coveiot.android.idoSdk.IDOBleCommandName;
import com.google.android.material.color.c;
import com.ido.ble.protocol.model.WallpaperFileCreateConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u0013\u001a\u00020\u0005¢\u0006\u0004\b\u0014\u0010\u0015J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016R\u0019\u0010\u000e\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0013\u001a\u00020\u00058\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/coveiot/android/idoSdk/api/IDODIYWatchFaceReq;", "Lcom/coveiot/android/idoSdk/api/IDOBaseReq;", "", "isMultiPacket", "isPriority", "", "getTimeOut", "Lcom/coveiot/android/idoSdk/IDOBleCommandName;", "getCommandName", "Lcom/ido/ble/protocol/model/WallpaperFileCreateConfig;", c.f10260a, "Lcom/ido/ble/protocol/model/WallpaperFileCreateConfig;", "getWallPaperConFig", "()Lcom/ido/ble/protocol/model/WallpaperFileCreateConfig;", "wallPaperConFig", "d", "I", "getTextColor", "()I", "textColor", "<init>", "(Lcom/ido/ble/protocol/model/WallpaperFileCreateConfig;I)V", "idoSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class IDODIYWatchFaceReq extends IDOBaseReq {
    @NotNull
    public final WallpaperFileCreateConfig c;
    public final int d;

    public IDODIYWatchFaceReq(@NotNull WallpaperFileCreateConfig wallPaperConFig, int i) {
        Intrinsics.checkNotNullParameter(wallPaperConFig, "wallPaperConFig");
        this.c = wallPaperConFig;
        this.d = i;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    @NotNull
    public IDOBleCommandName getCommandName() {
        return IDOBleCommandName.CHANGE_WATCH_FACE_BG;
    }

    public final int getTextColor() {
        return this.d;
    }

    @Override // com.coveiot.android.idoSdk.api.IDOBaseReq
    public int getTimeOut() {
        return 180000;
    }

    @NotNull
    public final WallpaperFileCreateConfig getWallPaperConFig() {
        return this.c;
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
