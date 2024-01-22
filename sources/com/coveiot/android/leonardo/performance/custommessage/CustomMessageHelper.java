package com.coveiot.android.leonardo.performance.custommessage;

import com.coveiot.android.bleabstract.models.DynamicSportsField;
import java.util.ArrayList;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public interface CustomMessageHelper {

    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ ArrayList getGenericCustomMessageRequest$default(CustomMessageHelper customMessageHelper, String str, int i, String str2, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 4) != 0) {
                    str2 = null;
                }
                return customMessageHelper.getGenericCustomMessageRequest(str, i, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getGenericCustomMessageRequest");
        }
    }

    @NotNull
    ArrayList<DynamicSportsField> getEnergyScoreCustomMessageRequest(int i, @NotNull String str, int i2);

    @NotNull
    ArrayList<DynamicSportsField> getGenericCustomMessageRequest(@NotNull String str, int i, @Nullable String str2);

    @NotNull
    ArrayList<DynamicSportsField> getStressCustomMessageRequest(@NotNull Pair<Integer, Long> pair, @NotNull String str, int i);
}
