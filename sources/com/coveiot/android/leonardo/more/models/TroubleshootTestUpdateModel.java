package com.coveiot.android.leonardo.more.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class TroubleshootTestUpdateModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final TroubleshootTestType f5137a;
    @NotNull
    public TestingStatus b;
    @Nullable
    public String c;

    public TroubleshootTestUpdateModel(@NotNull TroubleshootTestType testType, @NotNull TestingStatus testingStatus, @Nullable String str) {
        Intrinsics.checkNotNullParameter(testType, "testType");
        Intrinsics.checkNotNullParameter(testingStatus, "testingStatus");
        this.f5137a = testType;
        this.b = testingStatus;
        this.c = str;
    }

    public static /* synthetic */ TroubleshootTestUpdateModel copy$default(TroubleshootTestUpdateModel troubleshootTestUpdateModel, TroubleshootTestType troubleshootTestType, TestingStatus testingStatus, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            troubleshootTestType = troubleshootTestUpdateModel.f5137a;
        }
        if ((i & 2) != 0) {
            testingStatus = troubleshootTestUpdateModel.b;
        }
        if ((i & 4) != 0) {
            str = troubleshootTestUpdateModel.c;
        }
        return troubleshootTestUpdateModel.copy(troubleshootTestType, testingStatus, str);
    }

    @NotNull
    public final TroubleshootTestType component1() {
        return this.f5137a;
    }

    @NotNull
    public final TestingStatus component2() {
        return this.b;
    }

    @Nullable
    public final String component3() {
        return this.c;
    }

    @NotNull
    public final TroubleshootTestUpdateModel copy(@NotNull TroubleshootTestType testType, @NotNull TestingStatus testingStatus, @Nullable String str) {
        Intrinsics.checkNotNullParameter(testType, "testType");
        Intrinsics.checkNotNullParameter(testingStatus, "testingStatus");
        return new TroubleshootTestUpdateModel(testType, testingStatus, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TroubleshootTestUpdateModel) {
            TroubleshootTestUpdateModel troubleshootTestUpdateModel = (TroubleshootTestUpdateModel) obj;
            return this.f5137a == troubleshootTestUpdateModel.f5137a && this.b == troubleshootTestUpdateModel.b && Intrinsics.areEqual(this.c, troubleshootTestUpdateModel.c);
        }
        return false;
    }

    @Nullable
    public final String getExtra() {
        return this.c;
    }

    @NotNull
    public final TroubleshootTestType getTestType() {
        return this.f5137a;
    }

    @NotNull
    public final TestingStatus getTestingStatus() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = ((this.f5137a.hashCode() * 31) + this.b.hashCode()) * 31;
        String str = this.c;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final void setExtra(@Nullable String str) {
        this.c = str;
    }

    public final void setTestingStatus(@NotNull TestingStatus testingStatus) {
        Intrinsics.checkNotNullParameter(testingStatus, "<set-?>");
        this.b = testingStatus;
    }

    @NotNull
    public String toString() {
        return "TroubleshootTestUpdateModel(testType=" + this.f5137a + ", testingStatus=" + this.b + ", extra=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ TroubleshootTestUpdateModel(TroubleshootTestType troubleshootTestType, TestingStatus testingStatus, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(troubleshootTestType, testingStatus, (i & 4) != 0 ? null : str);
    }
}
