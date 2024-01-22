package com.coveiot.android.leonardo.personalization;

import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldTextV2;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.sdk.ble.utils.BleUtils;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class PersonalizedMessageHelperPS1 implements PersonalizedMessageHelper {
    @NotNull
    public static final PersonalizedMessageHelperPS1 INSTANCE = new PersonalizedMessageHelperPS1();

    @Override // com.coveiot.android.leonardo.personalization.PersonalizedMessageHelper
    @NotNull
    public ArrayList<DynamicSportsField> getOTAPersonalizedMessageRequest(@NotNull String message, int i, @Nullable String str) {
        Intrinsics.checkNotNullParameter(message, "message");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        BleUtils.convertHexTo565("#FF3038");
        BleUtils.convertHexTo565("#1F1F20");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, i));
        if (str != null) {
            arrayList.add(new DynamicSportFieldTextV2(2, convertHexTo565, 15, 133, 39, 400, 28, 0, str, 3));
        }
        arrayList.add(new DynamicSportFieldTextV2(2, convertHexTo565, 15, 187, 103, 426, 24, 0, message, 1));
        return arrayList;
    }
}
