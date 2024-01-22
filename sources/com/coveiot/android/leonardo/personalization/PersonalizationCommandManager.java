package com.coveiot.android.leonardo.personalization;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.PersonalizedMessageRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class PersonalizationCommandManager {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void sendOTAMessageToBle$default(Companion companion, Context context, String str, int i, String str2, int i2, Object obj) {
            if ((i2 & 8) != 0) {
                str2 = null;
            }
            companion.sendOTAMessageToBle(context, str, i, str2);
        }

        public final void sendOTAMessageToBle(@NotNull Context context, @NotNull String message, int i, @Nullable String str) {
            DeviceSupportedFeatures deviceSupportedFeatures;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(message, "message");
            BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
            Boolean bool = null;
            if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
                if (bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null) {
                    bool = Boolean.valueOf(deviceSupportedFeatures.isPersonalizedMessageSupported());
                }
                Intrinsics.checkNotNull(bool);
                if (bool.booleanValue()) {
                    BleApiManager.getInstance(context).getBleApi().getData(new PersonalizedMessageRequest((short) 0, (short) 2000, (short) 10000, (short) 2, (short) 1, PersonalizedMessageHelperFactory.Companion.getPersonalizedMessageHelper(context).getOTAPersonalizedMessageRequest(message, i, str)), new DataResultListener() { // from class: com.coveiot.android.leonardo.personalization.PersonalizationCommandManager$Companion$sendOTAMessageToBle$1
                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onDataError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onDataResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onProgressUpdate(@NotNull ProgressData progress) {
                            Intrinsics.checkNotNullParameter(progress, "progress");
                        }
                    });
                }
            }
        }
    }
}
