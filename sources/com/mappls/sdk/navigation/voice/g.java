package com.mappls.sdk.navigation.voice;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.telephony.TelephonyManager;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.routing.h;
import com.szabh.smable3.entity.Languages;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import timber.log.Timber;
/* loaded from: classes11.dex */
public final class g extends a {
    public static TextToSpeech j = null;
    public static String k = "";
    public static int l;
    public Context e;
    public HashMap<String, String> f;
    public h g;
    public float h;
    public boolean i;

    public g(Context context, h hVar, String str) {
        super((NavigationApplication) context.getApplicationContext(), str);
        this.f = new HashMap<>();
        this.h = 1.0f;
        this.i = false;
        this.g = hVar;
        if (com.mappls.sdk.navigation.util.a.a(Languages.DEFAULT_LANGUAGE)) {
            throw new d(context.getString(R.string.mappls_voice_data_corrupted));
        }
        NavigationApplication navigationApplication = (NavigationApplication) context.getApplicationContext();
        if (navigationApplication.a()) {
            this.h = ((Float) navigationApplication.k().i.get()).floatValue();
        }
        if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean("bluetooth_phone_call_switch_preference", false) && i()) {
            ((NavigationApplication) context.getApplicationContext()).k().h0.a(com.mappls.sdk.navigation.d.i, (Integer) 0);
        } else {
            ((NavigationApplication) context.getApplicationContext()).k().h0.a(com.mappls.sdk.navigation.d.i, (Integer) 3);
        }
        d(navigationApplication, context);
        this.f.put("streamType", ((Integer) navigationApplication.k().h0.a(com.mappls.sdk.navigation.d.i)).toString());
    }

    public static void e(g gVar) {
        gVar.getClass();
        l = 0;
        gVar.i = false;
        TextToSpeech textToSpeech = j;
        if (textToSpeech != null) {
            textToSpeech.shutdown();
            j = null;
        }
        gVar.a();
        gVar.e = null;
        k = "";
    }

    public static boolean i() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled() && defaultAdapter.getProfileConnectionState(1) == 2;
    }

    public static /* synthetic */ int o() {
        int i = l - 1;
        l = i;
        return i;
    }

    public final synchronized void a(b bVar) {
        Context context = this.e;
        if (context != null && ((Boolean) ((NavigationApplication) context.getApplicationContext()).k().m0.get()).booleanValue()) {
            a(bVar, false);
        }
    }

    public final void d(NavigationApplication navigationApplication, Context context) {
        if (this.e != navigationApplication) {
            l = 0;
            this.i = false;
            TextToSpeech textToSpeech = j;
            if (textToSpeech != null) {
                textToSpeech.shutdown();
                j = null;
            }
            a();
            this.e = null;
            k = "";
        }
        if (j == null) {
            this.e = navigationApplication;
            k = "";
            l = 0;
            TextToSpeech textToSpeech2 = new TextToSpeech(navigationApplication, new e(this, new Locale(Languages.DEFAULT_LANGUAGE, "IN"), context, this.h));
            j = textToSpeech2;
            textToSpeech2.setOnUtteranceCompletedListener(new f(this));
        }
    }

    public final void h() {
        NavigationApplication navigationApplication = this.f13046a;
        if (navigationApplication != null && navigationApplication.k() != null) {
            this.f13046a.k().E0.a(this);
        }
        a();
        this.f13046a = null;
        l = 0;
        this.i = false;
        TextToSpeech textToSpeech = j;
        if (textToSpeech != null) {
            textToSpeech.shutdown();
            j = null;
        }
        a();
        this.e = null;
        k = "";
    }

    public final void j() {
        l = 0;
        TextToSpeech textToSpeech = j;
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
        a();
    }

    public final void a(b bVar, boolean z) {
        Context context = this.e;
        if (context == null || ((NavigationApplication) context.getApplicationContext()) == null) {
            return;
        }
        if (!MapplsNavigationHelper.getInstance().isPlayDuringPhoneCallEnabled()) {
            TelephonyManager telephonyManager = (TelephonyManager) this.e.getSystemService("phone");
            if (Build.VERSION.SDK_INT < 31) {
                if (telephonyManager != null && telephonyManager.getCallState() != 0) {
                    return;
                }
            } else if (ContextCompat.checkSelfPermission(this.e, "android.permission.READ_PHONE_STATE") == 0 && telephonyManager != null && telephonyManager.getCallStateForSubscription() != 0) {
                return;
            }
        }
        try {
            this.g.getClass();
        } catch (Exception e) {
            Timber.d(e);
        }
        if (h.e()) {
            return;
        }
        if (((Boolean) ((NavigationApplication) this.e.getApplicationContext()).k().l0.get()).booleanValue()) {
            return;
        }
        ArrayList a2 = bVar.a();
        StringBuilder sb = new StringBuilder();
        Iterator it = a2.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append(' ');
        }
        String sb2 = sb.toString();
        if (MapplsNavigationHelper.getInstance().getVoiceCommandListener() != null) {
            MapplsNavigationHelper.getInstance().getVoiceCommandListener().speak(sb2);
        }
        Intent intent = new Intent("com.getpebble.action.SEND_NOTIFICATION");
        HashMap hashMap = new HashMap();
        hashMap.put("title", "Voice");
        hashMap.put("body", sb2);
        String jSONArray = new JSONArray().put(new JSONObject((Map<?, ?>) hashMap)).toString();
        intent.putExtra("messageType", "PEBBLE_ALERT");
        intent.putExtra(NotificationCompat.MessagingStyle.Message.KEY_SENDER, "OsmAnd");
        intent.putExtra("notificationData", jSONArray);
        NavigationApplication navigationApplication = this.f13046a;
        if (navigationApplication != null) {
            navigationApplication.sendBroadcast(intent);
            NavigationLogger.i("Send message to pebble " + sb2, new Object[0]);
        }
        boolean z2 = ((Boolean) ((NavigationApplication) this.e.getApplicationContext()).k().m0.get()).booleanValue() || z;
        if (j == null || !z2 || !this.i) {
            if (this.f13046a != null) {
                this.g.getClass();
                return;
            }
            return;
        }
        int i = l;
        l = i + 1;
        if (i == 0) {
            b();
            if (((Integer) this.f13046a.k().h0.a(com.mappls.sdk.navigation.d.i)).intValue() == 0) {
                l++;
                if (Build.VERSION.SDK_INT < 21) {
                    HashMap<String, String> hashMap2 = this.f;
                    StringBuilder a3 = com.mappls.sdk.navigation.h.a("");
                    a3.append(System.currentTimeMillis());
                    hashMap2.put("utteranceId", a3.toString());
                    j.playSilence(((Integer) this.f13046a.k().j0.get()).intValue(), 1, this.f);
                } else {
                    StringBuilder a4 = com.mappls.sdk.navigation.h.a("");
                    a4.append(System.currentTimeMillis());
                    j.playSilentUtterance(((Integer) this.f13046a.k().j0.get()).intValue(), 1, a4.toString());
                }
            }
        }
        NavigationLogger.d("ttsRequests = %d", Integer.valueOf(l));
        HashMap<String, String> hashMap3 = this.f;
        StringBuilder a5 = com.mappls.sdk.navigation.h.a("");
        a5.append(System.currentTimeMillis());
        hashMap3.put("utteranceId", a5.toString());
        NavigationLogger.d("TTS speak result = %d", Integer.valueOf(j.speak(sb.toString(), 1, this.f)));
    }

    public final void a(int i) {
        this.b = i;
        HashMap<String, String> hashMap = this.f;
        hashMap.put("streamType", i + "");
    }
}
