package com.coveiot.android.leonardo.model;

import com.coveiot.android.leonardo.more.models.TroubleshootTestCategory;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TroubleshootingModel implements Serializable {
    @NotNull
    private final String name;
    @NotNull
    private final TroubleshootTestCategory troubleshootTestCategory;

    public TroubleshootingModel(@NotNull String name, @NotNull TroubleshootTestCategory troubleshootTestCategory) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(troubleshootTestCategory, "troubleshootTestCategory");
        this.name = name;
        this.troubleshootTestCategory = troubleshootTestCategory;
    }

    public static /* synthetic */ TroubleshootingModel copy$default(TroubleshootingModel troubleshootingModel, String str, TroubleshootTestCategory troubleshootTestCategory, int i, Object obj) {
        if ((i & 1) != 0) {
            str = troubleshootingModel.name;
        }
        if ((i & 2) != 0) {
            troubleshootTestCategory = troubleshootingModel.troubleshootTestCategory;
        }
        return troubleshootingModel.copy(str, troubleshootTestCategory);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final TroubleshootTestCategory component2() {
        return this.troubleshootTestCategory;
    }

    @NotNull
    public final TroubleshootingModel copy(@NotNull String name, @NotNull TroubleshootTestCategory troubleshootTestCategory) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(troubleshootTestCategory, "troubleshootTestCategory");
        return new TroubleshootingModel(name, troubleshootTestCategory);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TroubleshootingModel) {
            TroubleshootingModel troubleshootingModel = (TroubleshootingModel) obj;
            return Intrinsics.areEqual(this.name, troubleshootingModel.name) && this.troubleshootTestCategory == troubleshootingModel.troubleshootTestCategory;
        }
        return false;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final TroubleshootTestCategory getTroubleshootTestCategory() {
        return this.troubleshootTestCategory;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.troubleshootTestCategory.hashCode();
    }

    @NotNull
    public String toString() {
        return "TroubleshootingModel(name=" + this.name + ", troubleshootTestCategory=" + this.troubleshootTestCategory + HexStringBuilder.COMMENT_END_CHAR;
    }
}
