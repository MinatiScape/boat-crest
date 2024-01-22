package com.coveiot.android.leonardo.performance.custommessage;

import com.coveiot.android.bleabstract.models.DynamicSportFieldBBText;
import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.sdk.ble.api.model.AlignmentEnum;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.AppUtils;
import com.jieli.jl_rcsp.constant.Command;
import java.util.ArrayList;
import java.util.Date;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class CustomMessageHelperCY implements CustomMessageHelper {
    @NotNull
    public static final CustomMessageHelperCY INSTANCE = new CustomMessageHelperCY();

    @Override // com.coveiot.android.leonardo.performance.custommessage.CustomMessageHelper
    @NotNull
    public ArrayList<DynamicSportsField> getEnergyScoreCustomMessageRequest(int i, @NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 46, 0, 362, 200, i2));
        BleUtils.convertHexTo565("#ffffff");
        int convertHexTo565 = BleUtils.convertHexTo565("#FE9805");
        int convertHexTo5652 = BleUtils.convertHexTo565("#979ad1");
        arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 205, Command.CMD_RECEIVE_SPEECH_CANCEL, 32, 32, 32, 0, String.valueOf(i)));
        arrayList.add(new DynamicSportFieldText(2, convertHexTo5652, 120, 160, 32, 32, 24, 0, "Energy Meter"));
        arrayList.add(new DynamicSportFieldBBText(4, convertHexTo5652, 57, 175, 0, 0, 0, 0, message, 28, 360, 200, AlignmentEnum.BOTTOM.getValue()));
        return arrayList;
    }

    @Override // com.coveiot.android.leonardo.performance.custommessage.CustomMessageHelper
    @NotNull
    public ArrayList<DynamicSportsField> getGenericCustomMessageRequest(@NotNull String message, int i, @Nullable String str) {
        Intrinsics.checkNotNullParameter(message, "message");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        int convertHexTo5652 = BleUtils.convertHexTo565("#ffad02");
        arrayList.add(new DynamicSportFieldImage(1, -1, 46, 0, 362, 200, i));
        if (str != null) {
            if (str.length() > 6) {
                arrayList.add(new DynamicSportFieldText(3, convertHexTo5652, 138, 227, 32, 32, 32, 0, str));
            } else {
                arrayList.add(new DynamicSportFieldText(3, convertHexTo5652, 178, 227, 32, 32, 32, 0, str));
            }
        }
        arrayList.add(new DynamicSportFieldBBText(2, convertHexTo565, 57, 185, 0, 0, 0, 0, message, 28, 360, 200, AlignmentEnum.BOTTOM.getValue()));
        return arrayList;
    }

    @Override // com.coveiot.android.leonardo.performance.custommessage.CustomMessageHelper
    @NotNull
    public ArrayList<DynamicSportsField> getStressCustomMessageRequest(@NotNull Pair<Integer, Long> stressValuePair, @NotNull String message, int i) {
        Intrinsics.checkNotNullParameter(stressValuePair, "stressValuePair");
        Intrinsics.checkNotNullParameter(message, "message");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 46, 0, 362, 200, i));
        BleUtils.convertHexTo565("#ffffff");
        int convertHexTo565 = BleUtils.convertHexTo565("#faa3a6");
        int convertHexTo5652 = BleUtils.convertHexTo565("#979ad1");
        arrayList.add(new DynamicSportFieldText(2, convertHexTo565, 320, 163, 32, 32, 32, 0, String.valueOf(stressValuePair.getFirst())));
        arrayList.add(new DynamicSportFieldText(3, convertHexTo5652, 101, 163, 32, 32, 24, 0, "Max Stress -"));
        StringBuilder sb = new StringBuilder();
        Long second = stressValuePair.getSecond();
        Intrinsics.checkNotNull(second);
        sb.append(AppUtils.formatDate(new Date(second.longValue()), "hh:mm a"));
        sb.append(" today");
        arrayList.add(new DynamicSportFieldText(4, convertHexTo5652, 101, 220, 32, 32, 32, 0, sb.toString()));
        arrayList.add(new DynamicSportFieldBBText(5, convertHexTo5652, 77, 163, 0, 0, 24, 0, message, 28, 308, 200, AlignmentEnum.BOTTOM.getValue()));
        return arrayList;
    }
}
