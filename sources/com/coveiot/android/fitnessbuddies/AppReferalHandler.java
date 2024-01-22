package com.coveiot.android.fitnessbuddies;

import android.content.Context;
import android.content.Intent;
import com.coveiot.covepreferences.FitnessSocialManager;
import com.coveiot.covepreferences.data.AppReferalData;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.szabh.smable3.entity.BleNotification;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
/* loaded from: classes4.dex */
public class AppReferalHandler {

    /* renamed from: a  reason: collision with root package name */
    public Context f4412a;
    public String b;

    public AppReferalHandler(Context context) {
        this.f4412a = context;
    }

    public static URI appendUri(String str, String str2) throws URISyntaxException {
        URI uri = new URI(str);
        String query = uri.getQuery();
        if (query != null) {
            str2 = query + "&" + str2;
        }
        return new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), str2, uri.getFragment());
    }

    public void handleAppReferalFor(ArrayList<CoveContact> arrayList) {
        boolean isAppInstalled = AppUtils.isAppInstalled(this.f4412a, BleNotification.WHATS_APP);
        AppReferalData fitnessBuddiesAppReferalData = FitnessSocialManager.getInstance(this.f4412a).getFitnessBuddiesAppReferalData();
        if (fitnessBuddiesAppReferalData != null) {
            this.b = fitnessBuddiesAppReferalData.getInviteText();
            String inviteeLink = fitnessBuddiesAppReferalData.getInviteeLink();
            String str = null;
            try {
                if (isAppInstalled) {
                    str = appendUri(inviteeLink, "s=w").toString();
                } else {
                    str = appendUri(inviteeLink, "s=s").toString();
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            if (!AppUtils.isEmpty(str)) {
                this.b = this.b.replaceAll(inviteeLink, str);
            }
            String replaceAll = arrayList.size() > 1 ? "" : arrayList.get(0).getPhoneNumber().replaceAll(HexStringBuilder.DEFAULT_SEPARATOR, "");
            if (isAppInstalled) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", this.b);
                intent.addFlags(1073741824);
                intent.putExtra("jid", replaceAll.replace("+", "") + "@s.whatsapp.net");
                intent.setPackage(BleNotification.WHATS_APP);
                this.f4412a.startActivity(intent);
            }
        }
    }
}
