package com.coveiot.android.leonardo.model;

import com.coveiot.android.leonardo.more.models.TestingStatus;
import com.coveiot.android.leonardo.more.models.TroubleshootTestType;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TroubleshootingTestModel implements Serializable {
    @Nullable
    private String failureMessage;
    private final int serialNumber;
    @NotNull
    private final String testName;
    @NotNull
    private final TroubleshootTestType testType;
    @NotNull
    private TestingStatus testingStatus;

    public TroubleshootingTestModel(int i, @NotNull TroubleshootTestType testType, @NotNull String testName, @NotNull TestingStatus testingStatus, @Nullable String str) {
        Intrinsics.checkNotNullParameter(testType, "testType");
        Intrinsics.checkNotNullParameter(testName, "testName");
        Intrinsics.checkNotNullParameter(testingStatus, "testingStatus");
        this.serialNumber = i;
        this.testType = testType;
        this.testName = testName;
        this.testingStatus = testingStatus;
        this.failureMessage = str;
    }

    public static /* synthetic */ TroubleshootingTestModel copy$default(TroubleshootingTestModel troubleshootingTestModel, int i, TroubleshootTestType troubleshootTestType, String str, TestingStatus testingStatus, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = troubleshootingTestModel.serialNumber;
        }
        if ((i2 & 2) != 0) {
            troubleshootTestType = troubleshootingTestModel.testType;
        }
        TroubleshootTestType troubleshootTestType2 = troubleshootTestType;
        if ((i2 & 4) != 0) {
            str = troubleshootingTestModel.testName;
        }
        String str3 = str;
        if ((i2 & 8) != 0) {
            testingStatus = troubleshootingTestModel.testingStatus;
        }
        TestingStatus testingStatus2 = testingStatus;
        if ((i2 & 16) != 0) {
            str2 = troubleshootingTestModel.failureMessage;
        }
        return troubleshootingTestModel.copy(i, troubleshootTestType2, str3, testingStatus2, str2);
    }

    public final int component1() {
        return this.serialNumber;
    }

    @NotNull
    public final TroubleshootTestType component2() {
        return this.testType;
    }

    @NotNull
    public final String component3() {
        return this.testName;
    }

    @NotNull
    public final TestingStatus component4() {
        return this.testingStatus;
    }

    @Nullable
    public final String component5() {
        return this.failureMessage;
    }

    @NotNull
    public final TroubleshootingTestModel copy(int i, @NotNull TroubleshootTestType testType, @NotNull String testName, @NotNull TestingStatus testingStatus, @Nullable String str) {
        Intrinsics.checkNotNullParameter(testType, "testType");
        Intrinsics.checkNotNullParameter(testName, "testName");
        Intrinsics.checkNotNullParameter(testingStatus, "testingStatus");
        return new TroubleshootingTestModel(i, testType, testName, testingStatus, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TroubleshootingTestModel) {
            TroubleshootingTestModel troubleshootingTestModel = (TroubleshootingTestModel) obj;
            return this.serialNumber == troubleshootingTestModel.serialNumber && this.testType == troubleshootingTestModel.testType && Intrinsics.areEqual(this.testName, troubleshootingTestModel.testName) && this.testingStatus == troubleshootingTestModel.testingStatus && Intrinsics.areEqual(this.failureMessage, troubleshootingTestModel.failureMessage);
        }
        return false;
    }

    @Nullable
    public final String getFailureMessage() {
        return this.failureMessage;
    }

    public final int getSerialNumber() {
        return this.serialNumber;
    }

    @NotNull
    public final String getTestName() {
        return this.testName;
    }

    @NotNull
    public final TroubleshootTestType getTestType() {
        return this.testType;
    }

    @NotNull
    public final TestingStatus getTestingStatus() {
        return this.testingStatus;
    }

    public int hashCode() {
        int hashCode = ((((((Integer.hashCode(this.serialNumber) * 31) + this.testType.hashCode()) * 31) + this.testName.hashCode()) * 31) + this.testingStatus.hashCode()) * 31;
        String str = this.failureMessage;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final void setFailureMessage(@Nullable String str) {
        this.failureMessage = str;
    }

    public final void setTestingStatus(@NotNull TestingStatus testingStatus) {
        Intrinsics.checkNotNullParameter(testingStatus, "<set-?>");
        this.testingStatus = testingStatus;
    }

    @NotNull
    public String toString() {
        return "TroubleshootingTestModel(serialNumber=" + this.serialNumber + ", testType=" + this.testType + ", testName=" + this.testName + ", testingStatus=" + this.testingStatus + ", failureMessage=" + this.failureMessage + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ TroubleshootingTestModel(int i, TroubleshootTestType troubleshootTestType, String str, TestingStatus testingStatus, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, troubleshootTestType, str, (i2 & 8) != 0 ? TestingStatus.NOT_STARTED : testingStatus, (i2 & 16) != 0 ? null : str2);
    }
}
