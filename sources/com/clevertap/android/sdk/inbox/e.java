package com.clevertap.android.sdk.inbox;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import androidx.viewpager.widget.ViewPager;
import com.clevertap.android.sdk.Constants;
import java.util.HashMap;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class e implements View.OnClickListener {
    public JSONObject h;
    public final String i;
    public final CTInboxListViewFragment j;
    public final CTInboxMessage k;
    public final int l;
    public ViewPager m;
    public final int n;

    public e(int i, CTInboxMessage cTInboxMessage, String str, JSONObject jSONObject, CTInboxListViewFragment cTInboxListViewFragment, boolean z, int i2) {
        this.l = i;
        this.k = cTInboxMessage;
        this.i = str;
        this.j = cTInboxListViewFragment;
        this.h = jSONObject;
        this.n = i2;
    }

    public final void a(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
        ClipData newPlainText = ClipData.newPlainText(this.i, this.k.getInboxMessageContents().get(0).getLinkCopyText(this.h));
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(newPlainText);
            Toast.makeText(context, "Text Copied to Clipboard", 0).show();
        }
    }

    public final HashMap<String, String> b(CTInboxMessage cTInboxMessage) {
        if (cTInboxMessage == null || cTInboxMessage.getInboxMessageContents() == null || cTInboxMessage.getInboxMessageContents().get(0) == null || !Constants.KEY_KV.equalsIgnoreCase(cTInboxMessage.getInboxMessageContents().get(0).getLinktype(this.h))) {
            return null;
        }
        return cTInboxMessage.getInboxMessageContents().get(0).getLinkKeyValue(this.h);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewPager viewPager = this.m;
        if (viewPager != null) {
            CTInboxListViewFragment cTInboxListViewFragment = this.j;
            if (cTInboxListViewFragment != null) {
                cTInboxListViewFragment.h(this.l, viewPager.getCurrentItem());
            }
        } else if (this.i != null && this.h != null) {
            if (this.j != null) {
                if (this.k.getInboxMessageContents().get(0).getLinktype(this.h).equalsIgnoreCase(Constants.COPY_TYPE) && this.j.getActivity() != null) {
                    a(this.j.getActivity());
                }
                this.j.g(this.l, 0, this.i, this.h, b(this.k), this.n);
            }
        } else {
            CTInboxListViewFragment cTInboxListViewFragment2 = this.j;
            if (cTInboxListViewFragment2 != null) {
                cTInboxListViewFragment2.g(this.l, 0, null, null, null, this.n);
            }
        }
    }

    public e(int i, CTInboxMessage cTInboxMessage, String str, CTInboxListViewFragment cTInboxListViewFragment, ViewPager viewPager, boolean z, int i2) {
        this.l = i;
        this.k = cTInboxMessage;
        this.i = str;
        this.j = cTInboxListViewFragment;
        this.m = viewPager;
        this.n = i2;
    }
}
