package com.coveiot.android.crpsdk;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0014\u0010\u0015J*\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0004R\u0016\u0010\b\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\n\u0010\tR\u0016\u0010\u000b\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000b\u0010\tR\u0016\u0010\f\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\f\u0010\tR\u0016\u0010\r\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\r\u0010\tR\u0016\u0010\u000e\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000e\u0010\tR\u0016\u0010\u000f\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000f\u0010\tR\u0016\u0010\u0010\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0010\u0010\tR\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00118\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/coveiot/android/crpsdk/CRPBleRepeat;", "", "", "repeat", "Lkotlin/Function1;", "transfer", "", "toIndices", "SUNDAY", "I", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "EVERYDAY", "", "WEEKDAYS", "Ljava/util/List;", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class CRPBleRepeat {
    public static final int EVERYDAY = 127;
    public static final int FRIDAY = 32;
    public static final int MONDAY = 2;
    public static final int SATURDAY = 64;
    public static final int SUNDAY = 1;
    public static final int THURSDAY = 16;
    public static final int TUESDAY = 4;
    public static final int WEDNESDAY = 8;
    @NotNull
    public static final CRPBleRepeat INSTANCE = new CRPBleRepeat();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final List<Integer> f4109a = CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{1, 2, 4, 8, 16, 32, 64});

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\n"}, d2 = {"", "it", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes3.dex */
    public static final class a extends Lambda implements Function1<Integer, Integer> {

        /* renamed from: a  reason: collision with root package name */
        public static final a f4110a = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public Integer invoke(Integer num) {
            return Integer.valueOf(CRPBleRepeat.f4109a.indexOf(Integer.valueOf(num.intValue())));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Set toIndices$default(CRPBleRepeat cRPBleRepeat, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function1 = a.f4110a;
        }
        return cRPBleRepeat.toIndices(i, function1);
    }

    @NotNull
    public final Set<Integer> toIndices(int i, @NotNull Function1<? super Integer, Integer> transfer) {
        Intrinsics.checkNotNullParameter(transfer, "transfer");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Integer num : f4109a) {
            int intValue = num.intValue();
            if ((i & 127 & intValue) > 0) {
                linkedHashSet.add(transfer.invoke(Integer.valueOf(intValue)));
            }
        }
        return linkedHashSet;
    }
}
