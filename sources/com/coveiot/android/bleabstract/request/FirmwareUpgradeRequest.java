package com.coveiot.android.bleabstract.request;

import java.io.File;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class FirmwareUpgradeRequest extends BleBaseRequest {
    public String currentVersion;
    public File file;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public File f3490a;
        @NotNull
        public String b;

        public Builder(@NotNull File file, @NotNull String currentVersion) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(currentVersion, "currentVersion");
            this.f3490a = file;
            this.b = currentVersion;
        }

        @NotNull
        public final FirmwareUpgradeRequest build() {
            FirmwareUpgradeRequest firmwareUpgradeRequest = new FirmwareUpgradeRequest();
            firmwareUpgradeRequest.setFile(this.f3490a);
            firmwareUpgradeRequest.setCurrentVersion(this.b);
            return firmwareUpgradeRequest;
        }

        @NotNull
        public final String getCurrentVersion() {
            return this.b;
        }

        @NotNull
        public final File getFile() {
            return this.f3490a;
        }

        public final void setCurrentVersion(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.b = str;
        }

        public final void setFile(@NotNull File file) {
            Intrinsics.checkNotNullParameter(file, "<set-?>");
            this.f3490a = file;
        }
    }

    @NotNull
    public final String getCurrentVersion() {
        String str = this.currentVersion;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("currentVersion");
        return null;
    }

    @NotNull
    public final File getFile() {
        File file = this.file;
        if (file != null) {
            return file;
        }
        Intrinsics.throwUninitializedPropertyAccessException("file");
        return null;
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
