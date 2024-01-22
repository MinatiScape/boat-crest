package com.mappls.sdk.plugin.directions;

import androidx.annotation.Keep;
import com.mappls.sdk.services.api.directions.models.LegStep;
@Keep
/* loaded from: classes11.dex */
public class DirectionsUtils {
    public static d textInstructions = new d("mappls-directions-en", "v5");
    public static d shortTextInstructions = new d("mappls-directions-en_short_instructions", "v5");

    private DirectionsUtils() {
    }

    public static Integer getManeuverId(LegStep legStep) {
        int i;
        String drivingSide;
        float f;
        String[] split = shortTextInstructions.c(legStep, true).split("\\$");
        String type = legStep.maneuver().type();
        if (type != null && (type.equalsIgnoreCase("roundabout") || type.equalsIgnoreCase("rotary"))) {
            if (legStep.maneuver().degree() != null) {
                f = legStep.maneuver().degree().floatValue();
                drivingSide = legStep.drivingSide();
            } else {
                drivingSide = legStep.drivingSide();
                f = 180.0f;
            }
            i = b.a(f, drivingSide);
        } else if (split.length > 1) {
            int parseInt = Integer.parseInt(split[1]);
            return (legStep.drivingSide() == null || legStep.drivingSide().equalsIgnoreCase("left")) ? Integer.valueOf(parseInt) : b.a(parseInt);
        } else {
            i = 0;
        }
        return Integer.valueOf(i);
    }

    public static String getShortInstruction(LegStep legStep) {
        return shortTextInstructions.c(legStep, true).split("\\$")[0];
    }

    public static String getTextInstructions(LegStep legStep) {
        return textInstructions.c(legStep, false);
    }
}
