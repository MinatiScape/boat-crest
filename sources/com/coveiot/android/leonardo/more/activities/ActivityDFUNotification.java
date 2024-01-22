package com.coveiot.android.leonardo.more.activities;

import android.content.Intent;
import android.os.Bundle;
import com.coveiot.android.theme.BaseActivity;
/* loaded from: classes5.dex */
public class ActivityDFUNotification extends BaseActivity {
    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isTaskRoot()) {
            Intent intent = new Intent(this, ActivityFirmwareUpdate.class);
            intent.putExtras(getIntent().getExtras());
            startActivities(new Intent[]{intent});
        }
        finish();
    }
}
