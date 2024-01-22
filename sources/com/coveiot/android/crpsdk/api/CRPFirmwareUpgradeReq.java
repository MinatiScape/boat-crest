package com.coveiot.android.crpsdk.api;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\u00020\u00058\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/coveiot/android/crpsdk/api/CRPFirmwareUpgradeReq;", "Lcom/coveiot/android/crpsdk/api/CRPBaseReq;", "", "isMultiPacket", "isPriority", "", "getCommandType", "Ljava/io/File;", "file", "Ljava/io/File;", "getFile", "()Ljava/io/File;", "setFile", "(Ljava/io/File;)V", "currentVersion", "Ljava/lang/String;", "getCurrentVersion", "()Ljava/lang/String;", "setCurrentVersion", "(Ljava/lang/String;)V", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class CRPFirmwareUpgradeReq extends CRPBaseReq {
    public String currentVersion;
    public File file;

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    @NotNull
    public String getCommandType() {
        String simpleName = CRPFirmwareUpgradeReq.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        return simpleName;
    }

    @NotNull
    public final String getCurrentVersion() {
        String str = this.currentVersion;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("currentVersion");
        throw null;
    }

    @NotNull
    public final File getFile() {
        File file = this.file;
        if (file != null) {
            return file;
        }
        Intrinsics.throwUninitializedPropertyAccessException("file");
        throw null;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.crpsdk.api.CRPBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setCurrentVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.currentVersion = str;
    }

    public final void setFile(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<set-?>");
        this.file = file;
    }
}
