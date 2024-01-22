package com.coveiot.android.leonardo.phonelocator;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.IOException;
import kotlinx.coroutines.DebugKt;
/* loaded from: classes5.dex */
public class PhoneLocator {
    public static final String s = "PhoneLocator";
    public static PhoneLocator t;
    public Context b;
    public NotificationManager c;
    public int d;
    public MediaPlayer e;
    public AudioManager f;
    public boolean g;
    public Camera h;
    public Camera.Parameters i;
    public boolean j;
    public CameraManager k;
    public Ringtone l;
    public Uri m;
    public int n;
    public int o;
    public Handler p;
    public boolean q;

    /* renamed from: a  reason: collision with root package name */
    public final Handler f5336a = new Handler();
    public Runnable r = new a();

    /* loaded from: classes5.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PhoneLocator.this.stopPlayingAudio();
        }
    }

    /* loaded from: classes5.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PhoneLocator.this.g) {
                if (PhoneLocator.this.j) {
                    PhoneLocator.this.k();
                } else {
                    PhoneLocator.this.l();
                }
                PhoneLocator.this.f5336a.postDelayed(this, 200L);
                return;
            }
            PhoneLocator.this.n();
            PhoneLocator.this.f5336a.removeCallbacks(this);
        }
    }

    public PhoneLocator(Context context) {
        this.b = context;
        this.k = (CameraManager) context.getSystemService("camera");
    }

    public static PhoneLocator getInstance(Context context) {
        if (t == null) {
            t = new PhoneLocator(context);
        }
        return t;
    }

    public final String f(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return Character.toUpperCase(charAt) + str.substring(1);
    }

    public final void g(int i) {
        NotificationManager notificationManager;
        if (Build.VERSION.SDK_INT < 23 || (notificationManager = this.c) == null || !notificationManager.isNotificationPolicyAccessGranted()) {
            return;
        }
        this.c.setInterruptionFilter(i);
    }

    public String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.toLowerCase().startsWith(str.toLowerCase())) {
            return f(str2);
        }
        return f(str) + HexStringBuilder.DEFAULT_SEPARATOR + str2;
    }

    public final void h() {
        try {
            this.f5336a.postDelayed(new b(), 200L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void i() {
        if (this.e == null) {
            this.e = new MediaPlayer();
        }
        AudioManager audioManager = (AudioManager) this.b.getSystemService("audio");
        this.f = audioManager;
        this.o = audioManager.getStreamVolume(4);
        this.f.setStreamVolume(4, this.f.getStreamMaxVolume(5), 8);
        this.e.setAudioStreamType(4);
        try {
            this.e.setDataSource(this.b, Settings.System.DEFAULT_RINGTONE_URI);
            this.e.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.e.isPlaying()) {
            return;
        }
        this.e.start();
        this.g = true;
        m();
    }

    public final void j() {
        Ringtone ringtone = this.l;
        if (ringtone == null) {
            this.m = RingtoneManager.getDefaultUri(1);
            this.l = RingtoneManager.getRingtone(this.b.getApplicationContext(), this.m);
            AudioManager audioManager = (AudioManager) this.b.getSystemService("audio");
            this.f = audioManager;
            int ringerMode = audioManager.getRingerMode();
            this.n = ringerMode;
            if (ringerMode == 0) {
                this.q = true;
            }
            String str = s;
            LogsHelper.d(str, "playThroughRingtoneManager() 1: Ringer Mode === " + this.n);
            this.o = this.f.getStreamVolume(2);
            int streamMaxVolume = this.f.getStreamMaxVolume(5);
            this.f.setRingerMode(2);
            this.f.setStreamVolume(2, streamMaxVolume, 8);
            this.l.setStreamType(2);
            if (Build.VERSION.SDK_INT >= 21) {
                this.l.setAudioAttributes(new AudioAttributes.Builder().setUsage(6).setContentType(2).build());
            }
            if (this.l.isPlaying()) {
                return;
            }
            this.l.play();
            this.g = true;
            m();
        } else if (ringtone.isPlaying()) {
        } else {
            AudioManager audioManager2 = (AudioManager) this.b.getSystemService("audio");
            this.f = audioManager2;
            int ringerMode2 = audioManager2.getRingerMode();
            this.n = ringerMode2;
            if (ringerMode2 == 0) {
                this.q = true;
            }
            String str2 = s;
            LogsHelper.d(str2, "playThroughRingtoneManager() 2: Ringer Mode === " + this.n);
            this.o = this.f.getStreamVolume(2);
            int streamMaxVolume2 = this.f.getStreamMaxVolume(5);
            this.f.setRingerMode(2);
            this.f.setStreamVolume(2, streamMaxVolume2, 8);
            this.l.play();
            this.g = true;
            m();
        }
    }

    public final void k() {
        Camera.Parameters parameters;
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    CameraManager cameraManager = this.k;
                    if (cameraManager != null) {
                        this.k.setTorchMode(cameraManager.getCameraIdList()[0], false);
                        this.j = false;
                        return;
                    }
                    return;
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (this.h == null || this.i == null) {
                Camera open = Camera.open();
                this.h = open;
                this.i = open.getParameters();
            }
            if (this.h == null || (parameters = this.i) == null) {
                return;
            }
            parameters.setFlashMode(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
            this.h.setParameters(this.i);
            this.h.stopPreview();
            this.j = false;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void l() {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    CameraManager cameraManager = this.k;
                    if (cameraManager != null) {
                        this.k.setTorchMode(cameraManager.getCameraIdList()[0], true);
                        this.j = true;
                        return;
                    }
                    return;
                } catch (CameraAccessException unused) {
                    return;
                }
            }
            if (this.h == null || this.i == null) {
                Camera open = Camera.open();
                this.h = open;
                this.i = open.getParameters();
            }
            Camera camera = this.h;
            if (camera == null || this.i == null) {
                return;
            }
            Camera.Parameters parameters = camera.getParameters();
            this.i = parameters;
            parameters.setFlashMode("torch");
            this.h.setParameters(this.i);
            this.h.startPreview();
            this.j = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void m() {
        try {
            if (this.b.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera.flash")) {
                try {
                    h();
                } catch (Exception unused) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void managePhoneFinderRingtone() {
        if (this.g) {
            return;
        }
        if (!AppUtils.isServiceRunning(PlayerService.class.getName(), this.b.getApplicationContext())) {
            Intent intent = new Intent(this.b, PlayerService.class);
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    this.b.startForegroundService(intent);
                } else {
                    this.b.startService(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
                BleApiUtils.checkExceptionAndShowNotification(e, this.b);
            }
        }
        String deviceName = getDeviceName();
        NotificationManager notificationManager = (NotificationManager) this.b.getSystemService("notification");
        this.c = notificationManager;
        if (Build.VERSION.SDK_INT >= 23) {
            int currentInterruptionFilter = notificationManager.getCurrentInterruptionFilter();
            this.d = currentInterruptionFilter;
            if (currentInterruptionFilter != 1) {
                String str = s;
                LogsHelper.d(str, "managePhoneFinderRingtone() 1: Interruption Filter === " + this.d);
                g(1);
            } else {
                String str2 = s;
                LogsHelper.d(str2, "managePhoneFinderRingtone() 2: Interruption Filter === " + this.d);
            }
        }
        try {
            if (deviceName.contains("OPPO")) {
                i();
            } else {
                j();
            }
            Handler handler = new Handler();
            this.p = handler;
            handler.postDelayed(this.r, 20000L);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void n() {
        try {
            this.f5336a.removeCallbacksAndMessages(null);
            k();
            Camera camera = this.h;
            if (camera != null) {
                camera.release();
                this.h = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void o() {
        try {
            try {
                MediaPlayer mediaPlayer = this.e;
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    this.e.release();
                    this.e = null;
                    AudioManager audioManager = (AudioManager) this.b.getSystemService("audio");
                    this.f = audioManager;
                    audioManager.setStreamVolume(4, this.o, 8);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            this.g = false;
            n();
            k();
        }
    }

    public final void p() {
        try {
            try {
                if (this.l != null) {
                    String str = s;
                    LogsHelper.d(str, "stopRingtone(): Ringer Mode === " + this.n);
                    this.l.stop();
                    AudioManager audioManager = (AudioManager) this.b.getSystemService("audio");
                    this.f = audioManager;
                    if (this.q) {
                        audioManager.setRingerMode(1);
                    } else {
                        audioManager.setRingerMode(this.n);
                    }
                    this.f.setStreamVolume(2, this.o, 8);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            this.g = false;
            n();
            k();
        }
    }

    public void stopPlayingAudio() {
        com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().cancleNotification(this.b, Integer.MAX_VALUE);
        this.b.stopService(new Intent(this.b, PlayerService.class));
        if (getDeviceName().contains("OPPO")) {
            o();
        } else {
            p();
        }
        if (this.d != 1) {
            String str = s;
            LogsHelper.d(str, "stopPlayingAudio() 1: Interruption Filter === " + this.d);
            g(this.d);
        } else {
            String str2 = s;
            LogsHelper.d(str2, "stopPlayingAudio() 2: Interruption Filter === " + this.d);
        }
        Handler handler = this.p;
        if (handler != null) {
            handler.removeCallbacks(this.r);
        }
    }
}
