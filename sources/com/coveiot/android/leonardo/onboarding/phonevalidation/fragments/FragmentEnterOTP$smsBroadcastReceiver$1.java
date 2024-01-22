package com.coveiot.android.leonardo.onboarding.phonevalidation.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterOTP;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentEnterOTP$smsBroadcastReceiver$1 extends BroadcastReceiver {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public FragmentEnterOTP.SmsBroadcastReceiverListener f5284a;

    @Nullable
    public final FragmentEnterOTP.SmsBroadcastReceiverListener getSmsBroadcastReceiverListener() {
        return this.f5284a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @NotNull Intent intent) {
        Bundle extras;
        Status status;
        FragmentEnterOTP.SmsBroadcastReceiverListener smsBroadcastReceiverListener;
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (!Intrinsics.areEqual(SmsRetriever.SMS_RETRIEVED_ACTION, intent.getAction()) || (extras = intent.getExtras()) == null || (status = (Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")) == null) {
            return;
        }
        if (status.getStatusCode() == 0) {
            String str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
            FragmentEnterOTP.SmsBroadcastReceiverListener smsBroadcastReceiverListener2 = this.f5284a;
            if (smsBroadcastReceiverListener2 != null) {
                smsBroadcastReceiverListener2.onSuccess(str);
            }
        } else if (status.getStatusCode() != 15 || (smsBroadcastReceiverListener = this.f5284a) == null) {
        } else {
            smsBroadcastReceiverListener.onFailure();
        }
    }

    public final void setSmsBroadcastReceiverListener(@Nullable FragmentEnterOTP.SmsBroadcastReceiverListener smsBroadcastReceiverListener) {
        this.f5284a = smsBroadcastReceiverListener;
    }
}
