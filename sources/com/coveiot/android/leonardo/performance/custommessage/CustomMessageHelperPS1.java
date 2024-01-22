package com.coveiot.android.leonardo.performance.custommessage;

import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
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
public final class CustomMessageHelperPS1 implements CustomMessageHelper {
    @NotNull
    public static final CustomMessageHelperPS1 INSTANCE = new CustomMessageHelperPS1();

    @Override // com.coveiot.android.leonardo.performance.custommessage.CustomMessageHelper
    @NotNull
    public ArrayList<DynamicSportsField> getEnergyScoreCustomMessageRequest(int i, @NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, i2));
        BleUtils.convertHexTo565("#ffffff");
        int convertHexTo565 = BleUtils.convertHexTo565("#FE9805");
        int convertHexTo5652 = BleUtils.convertHexTo565("#979ad1");
        arrayList.add(new DynamicSportFieldText(2, convertHexTo5652, 118, 136, 32, 32, 28, 0, "Energy Meter"));
        arrayList.add(new DynamicSportFieldText(3, convertHexTo565, Command.CMD_RECEIVE_SPEECH_CANCEL, 191, 32, 32, 28, 0, String.valueOf(i)));
        arrayList.add(new DynamicSportFieldText(4, convertHexTo5652, 50, 240, 32, 32, 24, 0, message));
        return arrayList;
    }

    @Override // com.coveiot.android.leonardo.performance.custommessage.CustomMessageHelper
    @NotNull
    public ArrayList<DynamicSportsField> getGenericCustomMessageRequest(@NotNull String message, int i, @Nullable String str) {
        Intrinsics.checkNotNullParameter(message, "message");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        int convertHexTo5652 = BleUtils.convertHexTo565("#ffad02");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, i));
        if (str != null) {
            if (str.length() > 6) {
                arrayList.add(new DynamicSportFieldText(3, convertHexTo5652, 128, 136, 32, 32, 32, 0, str));
            } else {
                arrayList.add(new DynamicSportFieldText(3, convertHexTo5652, 178, 136, 32, 32, 32, 0, str));
            }
        }
        arrayList.add(new DynamicSportFieldText(2, convertHexTo565, 15, 191, 32, 32, 24, 0, message));
        return arrayList;
    }

    @Override // com.coveiot.android.leonardo.performance.custommessage.CustomMessageHelper
    @NotNull
    public ArrayList<DynamicSportsField> getStressCustomMessageRequest(@NotNull Pair<Integer, Long> stressValuePair, @NotNull String message, int i) {
        Intrinsics.checkNotNullParameter(stressValuePair, "stressValuePair");
        Intrinsics.checkNotNullParameter(message, "message");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, i));
        int convertHexTo565 = BleUtils.convertHexTo565("#b3b3b3");
        int convertHexTo5652 = BleUtils.convertHexTo565("#CCCCCC");
        int convertHexTo5653 = BleUtils.convertHexTo565("#FE9805");
        arrayList.add(new DynamicSportFieldText(3, convertHexTo5653, 46, 136, 32, 32, 28, 0, "Max Stress -  " + stressValuePair.getFirst()));
        StringBuilder sb = new StringBuilder();
        sb.append("at ");
        Long second = stressValuePair.getSecond();
        Intrinsics.checkNotNull(second);
        sb.append(AppUtils.formatDate(new Date(second.longValue()), "hh:mm a"));
        sb.append(" today");
        arrayList.add(new DynamicSportFieldText(4, convertHexTo5652, 46, 191, 32, 32, 24, 0, sb.toString()));
        arrayList.add(new DynamicSportFieldText(5, convertHexTo565, 53, Command.CMD_PHONE_NUMBER_PLAY_MODE, 32, 32, 24, 0, message));
        return arrayList;
    }
}
