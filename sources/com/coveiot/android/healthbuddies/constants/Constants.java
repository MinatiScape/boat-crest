package com.coveiot.android.healthbuddies.constants;

import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public enum Constants {
    INTENT_EXTRA_URL(WorkoutConstants.INTENT_EXTRA_URL),
    GUARDIAN_CONSENT_EULA_URL("https://static.coveiot.com/borrelly/grd-consent-eula.html"),
    GUARDIAN_CONSENT_PRIVACY_POLICY_URL("https://static.coveiot.com/borrelly/grd-consent-privacy-policy.html"),
    INTENT_EXTRA_TITLE(WorkoutConstants.INTENT_EXTRA_TITLE),
    EXTRA_GUARDIAN_TAB_POSITION("guardian_tab_pos"),
    RELATION_TYPE("relation_type"),
    TOOLBAR_TITLE("toolbar_title"),
    FROM_DOCTOR("from_doctor");
    
    @NotNull
    private String value;

    Constants(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }
}
