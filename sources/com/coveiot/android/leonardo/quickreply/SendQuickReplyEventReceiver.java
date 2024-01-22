package com.coveiot.android.leonardo.quickreply;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.leonardo.quickreply.model.QuickReplyModel;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.GetAllUserAppSettingsRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.QuickReplyListModel;
import com.coveiot.sdk.ble.api.response.SendQuickReplyRes;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.permissions.PermissionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SendQuickReplyEventReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public int f5341a;
    @NotNull
    public String b = "";
    @Nullable
    public String c;
    public Context context;

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final void a(final int i, final String str) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        CoveUserAppSettings.getAllUserAppSettings(new CoveApiListener<GetAllUserAppSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.quickreply.SendQuickReplyEventReceiver$getQuickReplyList$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetAllUserAppSettingsRes getAllUserAppSettingsRes) {
                String str2;
                if (getAllUserAppSettingsRes == null || getAllUserAppSettingsRes.getCallQuickReplies() == null || getAllUserAppSettingsRes.getCallQuickReplies().getMessages() == null) {
                    return;
                }
                List<QuickReplyModel> list = objectRef.element;
                Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.leonardo.quickreply.model.QuickReplyModel>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.leonardo.quickreply.model.QuickReplyModel> }");
                ArrayList arrayList = (ArrayList) list;
                for (String item : getAllUserAppSettingsRes.getCallQuickReplies().getMessages()) {
                    Intrinsics.checkNotNullExpressionValue(item, "item");
                    ((ArrayList) objectRef.element).add(new QuickReplyModel(item));
                }
                this.b = objectRef.element.get(i).getQuickReplyMessage();
                String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this.getContext(), new String[]{"android.permission.SEND_SMS"});
                Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…                        )");
                if (checkPermissionsHasGranted.length == 0) {
                    SmsManager smsManager = SmsManager.getDefault();
                    Intrinsics.checkNotNullExpressionValue(smsManager, "getDefault()");
                    String str3 = str;
                    str2 = this.b;
                    smsManager.sendTextMessage(str3, null, str2, null, null);
                    UserDataManager.getInstance(this.getContext()).saveLastIncomingNumberToQuickReply(null);
                }
            }
        });
    }

    @NotNull
    public final Context getContext() {
        Context context = this.context;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("context");
        return null;
    }

    public final int getIndex() {
        return this.f5341a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        setContext(context);
        if (intent.hasExtra(Constants.SEND_QUICK_REPLY_BROADCAST_INTENT_EXTRA)) {
            Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            Serializable serializable = extras.getSerializable(Constants.SEND_QUICK_REPLY_BROADCAST_INTENT_EXTRA);
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.SendQuickReplyRes");
            SendQuickReplyRes sendQuickReplyRes = (SendQuickReplyRes) serializable;
            LogsHelper.d("sendQuickReplyRes", sendQuickReplyRes.toString());
            sendQuickReplyRes.getIndex();
            this.f5341a = sendQuickReplyRes.getIndex();
            String lastIncomingNumberToQuickReply = UserDataManager.getInstance(context).getLastIncomingNumberToQuickReply();
            this.c = lastIncomingNumberToQuickReply;
            if (lastIncomingNumberToQuickReply == null || lastIncomingNumberToQuickReply.length() == 0) {
                return;
            }
            QuickReplyListModel quickReplyListFromPref = UserDataManager.getInstance(context).getQuickReplyListFromPref();
            if (quickReplyListFromPref != null) {
                String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(context, new String[]{"android.permission.SEND_SMS"});
                Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…                        )");
                if (checkPermissionsHasGranted.length == 0) {
                    this.b = quickReplyListFromPref.getQuickReplyMessage().get(this.f5341a);
                    SmsManager smsManager = SmsManager.getDefault();
                    Intrinsics.checkNotNullExpressionValue(smsManager, "getDefault()");
                    smsManager.sendTextMessage(this.c, null, this.b, null, null);
                    UserDataManager.getInstance(context).saveLastIncomingNumberToQuickReply(null);
                    return;
                }
                return;
            }
            int i = this.f5341a;
            String str = this.c;
            Intrinsics.checkNotNull(str);
            a(i, str);
        }
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    public final void setIndex(int i) {
        this.f5341a = i;
    }
}
