package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes10.dex */
public class PreviewActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(@RecentlyNonNull Bundle bundle) {
        try {
            super.onCreate(bundle);
            zzbg zzbgVar = zzdh.zzb;
            zzbgVar.zzb("Preview activity");
            Uri data = getIntent().getData();
            if (data == null) {
                zzdh.zza("data is null in PreviewActivity.onCreate");
                return;
            }
            if (!TagManager.getInstance(this).zzd(data)) {
                String valueOf = String.valueOf(data);
                StringBuilder sb = new StringBuilder(valueOf.length() + 73);
                sb.append("Cannot preview the app with the uri: ");
                sb.append(valueOf);
                sb.append(". Launching current version instead.");
                String sb2 = sb.toString();
                zzdh.zzc(sb2);
                AlertDialog create = new AlertDialog.Builder(this).create();
                create.setTitle("Preview failure");
                create.setMessage(sb2);
                create.setButton(-1, "Continue", new zzdz(this));
                create.show();
            }
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (launchIntentForPackage != null) {
                String valueOf2 = String.valueOf(getPackageName());
                zzbgVar.zzb(valueOf2.length() != 0 ? "Invoke the launch activity for package name: ".concat(valueOf2) : new String("Invoke the launch activity for package name: "));
                startActivity(launchIntentForPackage);
                return;
            }
            String valueOf3 = String.valueOf(getPackageName());
            zzbgVar.zzb(valueOf3.length() != 0 ? "No launch activity found for package name: ".concat(valueOf3) : new String("No launch activity found for package name: "));
        } catch (Exception e) {
            String valueOf4 = String.valueOf(e.getMessage());
            zzdh.zza(valueOf4.length() != 0 ? "Calling preview threw an exception: ".concat(valueOf4) : new String("Calling preview threw an exception: "));
        }
    }
}
