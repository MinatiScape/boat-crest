package com.coveiot.android.leonardo.model;

import com.coveiot.android.leonardo.more.models.TestingStatus;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DiagnosticTestModel {

    /* renamed from: a  reason: collision with root package name */
    public final int f4852a;
    @NotNull
    public final String b;
    @NotNull
    public TestingStatus c;
    public boolean d;
    public int e;
    @NotNull
    public List<Integer> f;

    public DiagnosticTestModel(int i, @NotNull String testName, @NotNull TestingStatus testStatus, boolean z, int i2, @NotNull List<Integer> childTestCodes) {
        Intrinsics.checkNotNullParameter(testName, "testName");
        Intrinsics.checkNotNullParameter(testStatus, "testStatus");
        Intrinsics.checkNotNullParameter(childTestCodes, "childTestCodes");
        this.f4852a = i;
        this.b = testName;
        this.c = testStatus;
        this.d = z;
        this.e = i2;
        this.f = childTestCodes;
    }

    public static /* synthetic */ DiagnosticTestModel copy$default(DiagnosticTestModel diagnosticTestModel, int i, String str, TestingStatus testingStatus, boolean z, int i2, List list, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = diagnosticTestModel.f4852a;
        }
        if ((i3 & 2) != 0) {
            str = diagnosticTestModel.b;
        }
        String str2 = str;
        if ((i3 & 4) != 0) {
            testingStatus = diagnosticTestModel.c;
        }
        TestingStatus testingStatus2 = testingStatus;
        if ((i3 & 8) != 0) {
            z = diagnosticTestModel.d;
        }
        boolean z2 = z;
        if ((i3 & 16) != 0) {
            i2 = diagnosticTestModel.e;
        }
        int i4 = i2;
        List<Integer> list2 = list;
        if ((i3 & 32) != 0) {
            list2 = diagnosticTestModel.f;
        }
        return diagnosticTestModel.copy(i, str2, testingStatus2, z2, i4, list2);
    }

    public final int component1() {
        return this.f4852a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final TestingStatus component3() {
        return this.c;
    }

    public final boolean component4() {
        return this.d;
    }

    public final int component5() {
        return this.e;
    }

    @NotNull
    public final List<Integer> component6() {
        return this.f;
    }

    @NotNull
    public final DiagnosticTestModel copy(int i, @NotNull String testName, @NotNull TestingStatus testStatus, boolean z, int i2, @NotNull List<Integer> childTestCodes) {
        Intrinsics.checkNotNullParameter(testName, "testName");
        Intrinsics.checkNotNullParameter(testStatus, "testStatus");
        Intrinsics.checkNotNullParameter(childTestCodes, "childTestCodes");
        return new DiagnosticTestModel(i, testName, testStatus, z, i2, childTestCodes);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DiagnosticTestModel) {
            DiagnosticTestModel diagnosticTestModel = (DiagnosticTestModel) obj;
            return this.f4852a == diagnosticTestModel.f4852a && Intrinsics.areEqual(this.b, diagnosticTestModel.b) && this.c == diagnosticTestModel.c && this.d == diagnosticTestModel.d && this.e == diagnosticTestModel.e && Intrinsics.areEqual(this.f, diagnosticTestModel.f);
        }
        return false;
    }

    @NotNull
    public final List<Integer> getChildTestCodes() {
        return this.f;
    }

    public final int getExtensionTestCode() {
        return this.e;
    }

    public final boolean getShow() {
        return this.d;
    }

    public final int getTestCode() {
        return this.f4852a;
    }

    @NotNull
    public final String getTestName() {
        return this.b;
    }

    @NotNull
    public final TestingStatus getTestStatus() {
        return this.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((Integer.hashCode(this.f4852a) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31;
        boolean z = this.d;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((((hashCode + i) * 31) + Integer.hashCode(this.e)) * 31) + this.f.hashCode();
    }

    public final void setChildTestCodes(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f = list;
    }

    public final void setExtensionTestCode(int i) {
        this.e = i;
    }

    public final void setShow(boolean z) {
        this.d = z;
    }

    public final void setTestStatus(@NotNull TestingStatus testingStatus) {
        Intrinsics.checkNotNullParameter(testingStatus, "<set-?>");
        this.c = testingStatus;
    }

    @NotNull
    public String toString() {
        return "DiagnosticTestModel(testCode=" + this.f4852a + ", testName=" + this.b + ", testStatus=" + this.c + ", show=" + this.d + ", extensionTestCode=" + this.e + ", childTestCodes=" + this.f + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ DiagnosticTestModel(int i, String str, TestingStatus testingStatus, boolean z, int i2, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, (i3 & 4) != 0 ? TestingStatus.NOT_STARTED : testingStatus, (i3 & 8) != 0 ? false : z, (i3 & 16) != 0 ? -1 : i2, (i3 & 32) != 0 ? new ArrayList() : list);
    }
}
