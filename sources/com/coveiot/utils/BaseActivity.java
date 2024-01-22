package com.coveiot.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
/* loaded from: classes9.dex */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String SESSION_EXPIRY_BRODCAST_INTENT_ACTION = "session_expiry_brodcast";
    public static int i;
    public SessionExpiryBroadCastReciever h = new SessionExpiryBroadCastReciever();

    /* loaded from: classes9.dex */
    public class SessionExpiryBroadCastReciever extends BroadcastReceiver {
        public SessionExpiryBroadCastReciever() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BaseActivity.this.d();
        }
    }

    /* loaded from: classes9.dex */
    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            BaseActivity.this.getSharedPreferences("USERDATA_MANAGER", BaseActivity.i).edit().clear().commit();
            BaseActivity.this.getSharedPreferences("FHSCore", BaseActivity.i).edit().clear().commit();
            BaseActivity.this.getSharedPreferences("HELP_SCREEN", BaseActivity.i).edit().clear().commit();
        }
    }

    public final void d() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.session_expired));
        builder.setMessage(getString(R.string.session_expired_msg));
        builder.setPositiveButton(getString(R.string.ok), new a());
        builder.show();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public abstract void onSessionExpired();

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        registerReceiver(this.h, new IntentFilter("session_expiry_brodcast"));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        unregisterReceiver(this.h);
    }
}
