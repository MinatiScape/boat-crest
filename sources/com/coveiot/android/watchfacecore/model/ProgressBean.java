package com.coveiot.android.watchfacecore.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ProgressBean {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f6111a;
    public int b;
    public boolean c;

    public ProgressBean(@Nullable String str, int i, boolean z) {
        this.f6111a = str;
        this.b = i;
        this.c = z;
    }

    public static /* synthetic */ ProgressBean copy$default(ProgressBean progressBean, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = progressBean.f6111a;
        }
        if ((i2 & 2) != 0) {
            i = progressBean.b;
        }
        if ((i2 & 4) != 0) {
            z = progressBean.c;
        }
        return progressBean.copy(str, i, z);
    }

    @Nullable
    public final String component1() {
        return this.f6111a;
    }

    public final int component2() {
        return this.b;
    }

    public final boolean component3() {
        return this.c;
    }

    @NotNull
    public final ProgressBean copy(@Nullable String str, int i, boolean z) {
        return new ProgressBean(str, i, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ProgressBean) {
            ProgressBean progressBean = (ProgressBean) obj;
            return Intrinsics.areEqual(this.f6111a, progressBean.f6111a) && this.b == progressBean.b && this.c == progressBean.c;
        }
        return false;
    }

    public final int getProgress() {
        return this.b;
    }

    @Nullable
    public final String getTitle() {
        return this.f6111a;
    }

    public final boolean getVisible() {
        return this.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.f6111a;
        int hashCode = (((str == null ? 0 : str.hashCode()) * 31) + Integer.hashCode(this.b)) * 31;
        boolean z = this.c;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public final void setProgress(int i) {
        this.b = i;
    }

    public final void setTitle(@Nullable String str) {
        this.f6111a = str;
    }

    public final void setVisible(boolean z) {
        this.c = z;
    }

    @NotNull
    public String toString() {
        return "ProgressBean(title=" + this.f6111a + ", progress=" + this.b + ", visible=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
