package com.mappls.sdk.navigation.textinstructions;

import android.text.TextUtils;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.plugin.directions.DirectionsUtils;
import com.mappls.sdk.services.api.directions.models.LegStep;
/* loaded from: classes11.dex */
public class TextInstructionHelper {

    /* renamed from: a  reason: collision with root package name */
    public static TextInstructionHelper f12963a;

    public static TextInstructionHelper getInstance() {
        if (f12963a == null) {
            f12963a = new TextInstructionHelper();
        }
        return f12963a;
    }

    public String getInstruction(LegStep legStep) {
        return (TextUtils.isEmpty(legStep.maneuver().instruction()) || !MapplsNavigationHelper.getInstance().isEnableInstructionsFromAPI()) ? DirectionsUtils.getTextInstructions(legStep) : legStep.maneuver().instruction();
    }

    public String getInstruction(LegStep legStep, boolean z) {
        return (TextUtils.isEmpty(legStep.maneuver().instruction()) || !MapplsNavigationHelper.getInstance().isEnableInstructionsFromAPI()) ? DirectionsUtils.getTextInstructions(legStep) : legStep.maneuver().instruction();
    }

    public Integer getManeuverId(LegStep legStep) {
        return (legStep.maneuver().maneuverId() == null || !MapplsNavigationHelper.getInstance().isEnableInstructionsFromAPI()) ? DirectionsUtils.getManeuverId(legStep) : legStep.maneuver().maneuverId();
    }

    public String getShortInstruction(LegStep legStep) {
        return (TextUtils.isEmpty(legStep.maneuver().shortInstruction()) || !MapplsNavigationHelper.getInstance().isEnableInstructionsFromAPI()) ? DirectionsUtils.getShortInstruction(legStep) : legStep.maneuver().shortInstruction();
    }
}
