package com.coveiot.android.leonardo.personalization;

import com.coveiot.android.bleabstract.models.DynamicSportsField;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public interface PersonalizedMessageHelper {

    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ ArrayList getOTAPersonalizedMessageRequest$default(PersonalizedMessageHelper personalizedMessageHelper, String str, int i, String str2, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 4) != 0) {
                    str2 = null;
                }
                return personalizedMessageHelper.getOTAPersonalizedMessageRequest(str, i, str2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getOTAPersonalizedMessageRequest");
        }
    }

    @NotNull
    ArrayList<DynamicSportsField> getOTAPersonalizedMessageRequest(@NotNull String str, int i, @Nullable String str2);
}
