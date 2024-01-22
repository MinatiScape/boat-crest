package com.coveiot.android.leonardo.performance.custommessage;

import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Date;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class CustomMessageHelperOPP5 implements CustomMessageHelper {
    @NotNull
    public static final CustomMessageHelperOPP5 INSTANCE = new CustomMessageHelperOPP5();

    @Override // com.coveiot.android.leonardo.performance.custommessage.CustomMessageHelper
    @NotNull
    public ArrayList<DynamicSportsField> getEnergyScoreCustomMessageRequest(int i, @NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, i2));
        BleUtils.convertHexTo565("#ffffff");
        int convertHexTo565 = BleUtils.convertHexTo565("#FE9805");
        int convertHexTo5652 = BleUtils.convertHexTo565("#979ad1");
        arrayList.add(new DynamicSportFieldText(2, convertHexTo5652, 59, 124, 32, 32, 22, 0, "Energy Meter"));
        arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 109, 155, 32, 32, 32, 0, String.valueOf(i)));
        arrayList.add(new DynamicSportFieldText(4, convertHexTo5652, 15, 195, 32, 32, 22, 0, message));
        return arrayList;
    }

    @Override // com.coveiot.android.leonardo.performance.custommessage.CustomMessageHelper
    @NotNull
    public ArrayList<DynamicSportsField> getGenericCustomMessageRequest(@NotNull String message, int i, @Nullable String str) {
        Intrinsics.checkNotNullParameter(message, "message");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, i));
        arrayList.add(new DynamicSportFieldText(2, convertHexTo565, 15, 122, 32, 32, 22, 0, message));
        return arrayList;
    }

    @Override // com.coveiot.android.leonardo.performance.custommessage.CustomMessageHelper
    @NotNull
    public ArrayList<DynamicSportsField> getStressCustomMessageRequest(@NotNull Pair<Integer, Long> stressValuePair, @NotNull String message, int i) {
        Intrinsics.checkNotNullParameter(stressValuePair, "stressValuePair");
        Intrinsics.checkNotNullParameter(message, "message");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, i));
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        int convertHexTo5652 = BleUtils.convertHexTo565("#FE9805");
        int convertHexTo5653 = BleUtils.convertHexTo565("#979ad1");
        arrayList.add(new DynamicSportFieldText(2, convertHexTo5652, 250, 163, 32, 32, 32, 0, String.valueOf(stressValuePair.getFirst())));
        arrayList.add(new DynamicSportFieldText(3, convertHexTo5653, 46, 163, 32, 32, 24, 0, "Max Stress -"));
        StringBuilder sb = new StringBuilder();
        sb.append("at ");
        Long second = stressValuePair.getSecond();
        Intrinsics.checkNotNull(second);
        sb.append(AppUtils.formatDate(new Date(second.longValue()), "hh:mm a"));
        sb.append(" today");
        arrayList.add(new DynamicSportFieldText(4, convertHexTo5653, 46, 220, 32, 32, 32, 0, sb.toString()));
        arrayList.add(new DynamicSportFieldText(5, convertHexTo565, 15, 308, 32, 32, 24, 0, message));
        return arrayList;
    }
}
