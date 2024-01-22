package com.coveiot.android.fitnessbuddies;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.utils.LanguageHelper;
/* loaded from: classes4.dex */
public class FitnessBuddiesBaseActivity extends BaseActivity {
    public static final String SESSION_EXPIRY_BRODCAST_INTENT_ACTION = "session_expiry_brodcast";
    public static int r;
    public SessionExpiryBroadCastReciever p = new SessionExpiryBroadCastReciever();
    public AlertDialog q;

    /* loaded from: classes4.dex */
    public class SessionExpiryBroadCastReciever extends BroadcastReceiver {
        public SessionExpiryBroadCastReciever() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            FitnessBuddiesBaseActivity.this.r();
        }
    }

    /* loaded from: classes4.dex */
    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            FitnessBuddiesBaseActivity.this.getSharedPreferences("USERDATA_MANAGER", FitnessBuddiesBaseActivity.r).edit().clear().commit();
            FitnessBuddiesBaseActivity.this.getSharedPreferences("FHSCore", FitnessBuddiesBaseActivity.r).edit().clear().commit();
            FitnessBuddiesBaseActivity.this.getSharedPreferences("HELP_SCREEN", FitnessBuddiesBaseActivity.r).edit().clear().commit();
            FitnessBuddiesBaseActivity.this.deleteDatabase(CoveAppDatabase.covedatabase);
            Intent intent = new Intent("com.coveiot.android.fhscore.Splash");
            intent.setFlags(32768);
            FitnessBuddiesBaseActivity.this.startActivity(intent);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageHelper.onAttach(context));
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(com.coveiot.utils.R.string.session_expired));
        builder.setMessage(getString(com.coveiot.utils.R.string.session_expired_msg));
        builder.setPositiveButton(getString(com.coveiot.utils.R.string.ok), new a());
        this.q = builder.create();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        registerReceiver(this.p, new IntentFilter("session_expiry_brodcast"));
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        unregisterReceiver(this.p);
    }

    public final void r() {
        if (this.q.isShowing()) {
            return;
        }
        this.q.show();
    }
}
