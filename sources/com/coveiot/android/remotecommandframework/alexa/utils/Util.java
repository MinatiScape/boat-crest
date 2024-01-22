package com.coveiot.android.remotecommandframework.alexa.utils;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.remotecommandframework.alexa.response.model.BadRequest;
import com.coveiot.android.remotecommandframework.alexa.response.model.CommandParsingFailed;
import com.coveiot.android.remotecommandframework.alexa.response.model.DeviceNotConnected;
import com.coveiot.android.remotecommandframework.alexa.response.model.DuplicateRequest;
import com.coveiot.android.remotecommandframework.alexa.response.model.RequestAck;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.google.gson.Gson;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class Util {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final String getJsonFromObject(@NotNull Object obj) {
            String json;
            Intrinsics.checkNotNullParameter(obj, "obj");
            try {
                if (obj instanceof RequestAck) {
                    json = new Gson().toJson(obj, RequestAck.class);
                } else if (obj instanceof BadRequest) {
                    json = new Gson().toJson(obj, BadRequest.class);
                } else if (obj instanceof CommandParsingFailed) {
                    json = new Gson().toJson(obj, CommandParsingFailed.class);
                } else if (obj instanceof DeviceNotConnected) {
                    json = new Gson().toJson(obj, DeviceNotConnected.class);
                } else {
                    json = obj instanceof DuplicateRequest ? new Gson().toJson(obj, DuplicateRequest.class) : new Gson().toJson(obj, Object.class);
                }
                return json;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public final boolean isDeviceConnected(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return BleApiManager.getInstance(context).getBleApi() != null && BleApiManager.getInstance(context).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED;
        }

        public final boolean isSyncInProgress() {
            return SyncManager.getInstance().isSyncInProgress();
        }
    }
}
