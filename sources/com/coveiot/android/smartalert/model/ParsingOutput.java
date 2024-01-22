package com.coveiot.android.smartalert.model;

import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class ParsingOutput {
    @Nullable
    private String content;
    @NotNull
    private List<DynamicSportsField> dynamicSportFields;
    private boolean isParsed;
    @Nullable
    private String rawMessage;
    @Nullable
    private String title;

    public ParsingOutput() {
        this(false, null, null, null, null, 31, null);
    }

    public ParsingOutput(boolean z, @Nullable String str, @Nullable String str2, @Nullable String str3, @NotNull List<DynamicSportsField> dynamicSportFields) {
        Intrinsics.checkNotNullParameter(dynamicSportFields, "dynamicSportFields");
        this.isParsed = z;
        this.rawMessage = str;
        this.title = str2;
        this.content = str3;
        this.dynamicSportFields = dynamicSportFields;
    }

    public static /* synthetic */ ParsingOutput copy$default(ParsingOutput parsingOutput, boolean z, String str, String str2, String str3, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            z = parsingOutput.isParsed;
        }
        if ((i & 2) != 0) {
            str = parsingOutput.rawMessage;
        }
        String str4 = str;
        if ((i & 4) != 0) {
            str2 = parsingOutput.title;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = parsingOutput.content;
        }
        String str6 = str3;
        List<DynamicSportsField> list2 = list;
        if ((i & 16) != 0) {
            list2 = parsingOutput.dynamicSportFields;
        }
        return parsingOutput.copy(z, str4, str5, str6, list2);
    }

    public final boolean component1() {
        return this.isParsed;
    }

    @Nullable
    public final String component2() {
        return this.rawMessage;
    }

    @Nullable
    public final String component3() {
        return this.title;
    }

    @Nullable
    public final String component4() {
        return this.content;
    }

    @NotNull
    public final List<DynamicSportsField> component5() {
        return this.dynamicSportFields;
    }

    @NotNull
    public final ParsingOutput copy(boolean z, @Nullable String str, @Nullable String str2, @Nullable String str3, @NotNull List<DynamicSportsField> dynamicSportFields) {
        Intrinsics.checkNotNullParameter(dynamicSportFields, "dynamicSportFields");
        return new ParsingOutput(z, str, str2, str3, dynamicSportFields);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ParsingOutput) {
            ParsingOutput parsingOutput = (ParsingOutput) obj;
            return this.isParsed == parsingOutput.isParsed && Intrinsics.areEqual(this.rawMessage, parsingOutput.rawMessage) && Intrinsics.areEqual(this.title, parsingOutput.title) && Intrinsics.areEqual(this.content, parsingOutput.content) && Intrinsics.areEqual(this.dynamicSportFields, parsingOutput.dynamicSportFields);
        }
        return false;
    }

    @Nullable
    public final String getContent() {
        return this.content;
    }

    @NotNull
    public final List<DynamicSportsField> getDynamicSportFields() {
        return this.dynamicSportFields;
    }

    @Nullable
    public final String getRawMessage() {
        return this.rawMessage;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    public int hashCode() {
        boolean z = this.isParsed;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        String str = this.rawMessage;
        int hashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.title;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.content;
        return ((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.dynamicSportFields.hashCode();
    }

    public final boolean isParsed() {
        return this.isParsed;
    }

    public final void setContent(@Nullable String str) {
        this.content = str;
    }

    public final void setDynamicSportFields(@NotNull List<DynamicSportsField> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.dynamicSportFields = list;
    }

    public final void setParsed(boolean z) {
        this.isParsed = z;
    }

    public final void setRawMessage(@Nullable String str) {
        this.rawMessage = str;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    @NotNull
    public String toString() {
        return "ParsingOutput(isParsed=" + this.isParsed + ", rawMessage=" + this.rawMessage + ", title=" + this.title + ", content=" + this.content + ", dynamicSportFields=" + this.dynamicSportFields + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ ParsingOutput(boolean z, String str, String str2, String str3, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) == 0 ? str3 : null, (i & 16) != 0 ? new ArrayList() : list);
    }
}
