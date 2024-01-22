package com.coveiot.android.femalewellness.utils;

import androidx.room.TypeConverter;
import com.clevertap.android.sdk.Constants;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.f;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class Converter {
    @TypeConverter
    @NotNull
    public final List<String> fromString(@NotNull String stringListString) {
        Intrinsics.checkNotNullParameter(stringListString, "stringListString");
        List<String> split$default = StringsKt__StringsKt.split$default((CharSequence) stringListString, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(split$default, 10));
        for (String str : split$default) {
            arrayList.add(str);
        }
        return arrayList;
    }

    @TypeConverter
    @NotNull
    public final String toString(@NotNull List<String> stringList) {
        Intrinsics.checkNotNullParameter(stringList, "stringList");
        return CollectionsKt___CollectionsKt.joinToString$default(stringList, Constants.SEPARATOR_COMMA, null, null, 0, null, null, 62, null);
    }
}
