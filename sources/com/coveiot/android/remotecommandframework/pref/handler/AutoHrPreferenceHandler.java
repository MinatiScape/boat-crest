package com.coveiot.android.remotecommandframework.pref.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.handler.IAutoHrPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SAutoHrInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.covepreferences.UserDataManager;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class AutoHrPreferenceHandler implements IAutoHrPreferenceHandler {
    public final int a(String str) {
        if (str == null || str.length() != 8) {
            return 30;
        }
        int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(0));
        int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(1));
        Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(2));
        return (parseInt * 60) + parseInt2;
    }

    public final boolean b(int i) {
        return i == 5 || i == 10 || i == 15 || i == 30 || i == 60;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IAutoHrPreferenceHandler
    public boolean isAlreadySet(@NotNull Context context, @NotNull SAutoHrInfo sAutoHrInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sAutoHrInfo, "sAutoHrInfo");
        boolean isAutoHrEnabled = UserDataManager.getInstance(context).isAutoHrEnabled();
        int autoHrInterval = UserDataManager.getInstance(context).getAutoHrInterval();
        Boolean active = sAutoHrInfo.getActive();
        Intrinsics.checkNotNull(active);
        if (active.booleanValue() || isAutoHrEnabled) {
            Boolean active2 = sAutoHrInfo.getActive();
            Intrinsics.checkNotNull(active2);
            return active2.booleanValue() && isAutoHrEnabled && a(sAutoHrInfo.getInterval()) == autoHrInterval;
        }
        return true;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IAutoHrPreferenceHandler
    public boolean isValidInput(@NotNull Context context, @NotNull SAutoHrInfo sAutoHrInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sAutoHrInfo, "sAutoHrInfo");
        return b(a(sAutoHrInfo.getInterval()));
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IAutoHrPreferenceHandler
    public void update(@NotNull Context context, @NotNull SAutoHrInfo sAutoHrInfo, @NotNull SuccessListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sAutoHrInfo, "sAutoHrInfo");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (a(sAutoHrInfo.getInterval()) == 0) {
            UserDataManager.getInstance(context).saveAutoHrEnabled(false);
            UserDataManager.getInstance(context).saveAutoHrInterval(0);
        } else {
            UserDataManager.getInstance(context).saveAutoHrEnabled(Intrinsics.areEqual(sAutoHrInfo.getActive(), Boolean.TRUE));
            UserDataManager.getInstance(context).saveAutoHrInterval(a(sAutoHrInfo.getInterval()));
        }
        listener.onSuccess();
    }
}
