package com.szabh.smable3.entity;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class BleRepeat {
    public static final int EVERYDAY = 127;
    public static final int FRIDAY = 16;
    public static final int MONDAY = 1;
    public static final int ONCE = 0;
    public static final int SATURDAY = 32;
    public static final int SUNDAY = 64;
    public static final int THURSDAY = 8;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 4;
    public static final int WEEKEND = 96;
    public static final int WORKDAY = 31;
    @NotNull
    public static final BleRepeat INSTANCE = new BleRepeat();
    @NotNull
    private static final List<Integer> WEEKDAYS = CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{1, 2, 4, 8, 16, 32, 64});

    private BleRepeat() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ int indicesToRepeat$default(BleRepeat bleRepeat, Set set, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: com.szabh.smable3.entity.BleRepeat$indicesToRepeat$1
                @NotNull
                public final Integer invoke(int i2) {
                    List list;
                    list = BleRepeat.WEEKDAYS;
                    return (Integer) list.get(i2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return bleRepeat.indicesToRepeat(set, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Set toIndices$default(BleRepeat bleRepeat, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: com.szabh.smable3.entity.BleRepeat$toIndices$1
                @NotNull
                public final Integer invoke(int i3) {
                    List list;
                    list = BleRepeat.WEEKDAYS;
                    return Integer.valueOf(list.indexOf(Integer.valueOf(i3)));
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return bleRepeat.toIndices(i, function1);
    }

    public final int indicesToRepeat(@NotNull Set<Integer> indices, @NotNull Function1<? super Integer, Integer> transfer) {
        Intrinsics.checkNotNullParameter(indices, "indices");
        Intrinsics.checkNotNullParameter(transfer, "transfer");
        int i = 0;
        for (Integer num : indices) {
            i |= transfer.invoke(Integer.valueOf(num.intValue())).intValue();
        }
        return i;
    }

    @NotNull
    public final Set<Integer> toIndices(int i, @NotNull Function1<? super Integer, Integer> transfer) {
        Intrinsics.checkNotNullParameter(transfer, "transfer");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Integer num : WEEKDAYS) {
            int intValue = num.intValue();
            if ((i & 127 & intValue) > 0) {
                linkedHashSet.add(transfer.invoke(Integer.valueOf(intValue)));
            }
        }
        return linkedHashSet;
    }

    @NotNull
    public final String toWeekdayText(int i, @NotNull Function1<? super Integer, String> transfer) {
        Intrinsics.checkNotNullParameter(transfer, "transfer");
        StringBuilder sb = new StringBuilder();
        for (Integer num : WEEKDAYS) {
            int intValue = num.intValue();
            if ((i & 127 & intValue) > 0) {
                sb.append(transfer.invoke(Integer.valueOf(intValue)));
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
        return sb2;
    }
}
