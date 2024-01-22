package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.NotificationUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public class MessengerUtils {

    /* renamed from: a  reason: collision with root package name */
    public static ConcurrentHashMap<String, MessageCallback> f2265a = new ConcurrentHashMap<>();
    public static Map<String, a> b = new HashMap();
    public static a c;

    /* loaded from: classes.dex */
    public interface MessageCallback {
        void messageCall(Bundle bundle);
    }

    /* loaded from: classes.dex */
    public static class ServerService extends Service {
        public final ConcurrentHashMap<Integer, Messenger> h = new ConcurrentHashMap<>();
        @SuppressLint({"HandlerLeak"})
        public final Handler i;
        public final Messenger j;

        /* loaded from: classes.dex */
        public class a extends Handler {
            public a() {
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    ServerService.this.h.put(Integer.valueOf(message.arg1), message.replyTo);
                } else if (i == 1) {
                    ServerService.this.h.remove(Integer.valueOf(message.arg1));
                } else if (i == 2) {
                    ServerService.this.e(message);
                    ServerService.this.d(message);
                } else {
                    super.handleMessage(message);
                }
            }
        }

        public ServerService() {
            a aVar = new a();
            this.i = aVar;
            this.j = new Messenger(aVar);
        }

        public final void d(Message message) {
            String string;
            MessageCallback messageCallback;
            Bundle data = message.getData();
            if (data == null || (string = data.getString("MESSENGER_UTILS")) == null || (messageCallback = (MessageCallback) MessengerUtils.f2265a.get(string)) == null) {
                return;
            }
            messageCallback.messageCall(data);
        }

        public final void e(Message message) {
            for (Messenger messenger : this.h.values()) {
                if (messenger != null) {
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override // android.app.Service
        @Nullable
        public IBinder onBind(Intent intent) {
            return this.j.getBinder();
        }

        @Override // android.app.Service
        public int onStartCommand(Intent intent, int i, int i2) {
            Bundle extras;
            if (Build.VERSION.SDK_INT >= 26) {
                startForeground(1, b.b0(NotificationUtils.ChannelConfig.DEFAULT_CHANNEL_CONFIG, null));
            }
            if (intent != null && (extras = intent.getExtras()) != null) {
                Message obtain = Message.obtain(this.i, 2);
                obtain.replyTo = this.j;
                obtain.setData(extras);
                e(obtain);
                d(obtain);
            }
            return 2;
        }
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public String f2267a;
        public Messenger b;
        public LinkedList<Bundle> c = new LinkedList<>();
        @SuppressLint({"HandlerLeak"})
        public Handler d = new HandlerC0206a(this);
        public Messenger e = new Messenger(this.d);
        public ServiceConnection f = new b();

        /* renamed from: com.blankj.utilcode.util.MessengerUtils$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class HandlerC0206a extends Handler {
            public HandlerC0206a(a aVar) {
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                MessageCallback messageCallback;
                Bundle data = message.getData();
                data.setClassLoader(MessengerUtils.class.getClassLoader());
                String string = data.getString("MESSENGER_UTILS");
                if (string == null || (messageCallback = (MessageCallback) MessengerUtils.f2265a.get(string)) == null) {
                    return;
                }
                messageCallback.messageCall(data);
            }
        }

        /* loaded from: classes.dex */
        public class b implements ServiceConnection {
            public b() {
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d("MessengerUtils", "client service connected " + componentName);
                a.this.b = new Messenger(iBinder);
                Message obtain = Message.obtain(a.this.d, 0, com.blankj.utilcode.util.b.N().hashCode(), 0);
                obtain.getData().setClassLoader(MessengerUtils.class.getClassLoader());
                a aVar = a.this;
                obtain.replyTo = aVar.e;
                try {
                    aVar.b.send(obtain);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                a.this.d();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Log.w("MessengerUtils", "client service disconnected:" + componentName);
                a aVar = a.this;
                aVar.b = null;
                if (aVar.b()) {
                    return;
                }
                Log.e("MessengerUtils", "client service rebind failed: " + componentName);
            }
        }

        public a(String str) {
            this.f2267a = str;
        }

        public boolean b() {
            if (TextUtils.isEmpty(this.f2267a)) {
                return Utils.getApp().bindService(new Intent(Utils.getApp(), ServerService.class), this.f, 1);
            } else if (com.blankj.utilcode.util.b.r0(this.f2267a)) {
                if (com.blankj.utilcode.util.b.s0(this.f2267a)) {
                    Intent intent = new Intent(this.f2267a + ".messenger");
                    intent.setPackage(this.f2267a);
                    return Utils.getApp().bindService(intent, this.f, 1);
                }
                Log.e("MessengerUtils", "bind: the app is not running -> " + this.f2267a);
                return false;
            } else {
                Log.e("MessengerUtils", "bind: the app is not installed -> " + this.f2267a);
                return false;
            }
        }

        public final boolean c(Bundle bundle) {
            Message obtain = Message.obtain(this.d, 2);
            bundle.setClassLoader(MessengerUtils.class.getClassLoader());
            obtain.setData(bundle);
            obtain.replyTo = this.e;
            try {
                this.b.send(obtain);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }

        public final void d() {
            if (this.c.isEmpty()) {
                return;
            }
            for (int size = this.c.size() - 1; size >= 0; size--) {
                if (c(this.c.get(size))) {
                    this.c.remove(size);
                }
            }
        }

        public void e(Bundle bundle) {
            if (this.b == null) {
                this.c.addFirst(bundle);
                Log.i("MessengerUtils", "save the bundle " + bundle);
                return;
            }
            d();
            if (c(bundle)) {
                return;
            }
            this.c.addFirst(bundle);
        }

        public void f() {
            Message obtain = Message.obtain(this.d, 1, com.blankj.utilcode.util.b.N().hashCode(), 0);
            obtain.replyTo = this.e;
            try {
                this.b.send(obtain);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                Utils.getApp().unbindService(this.f);
            } catch (Exception unused) {
            }
        }
    }

    public static void b(Intent intent) {
        try {
            intent.setFlags(32);
            if (Build.VERSION.SDK_INT >= 26) {
                Utils.getApp().startForegroundService(intent);
            } else {
                Utils.getApp().startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void post(@NonNull String str, @NonNull Bundle bundle) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(bundle, "Argument 'data' of type Bundle (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        bundle.putString("MESSENGER_UTILS", str);
        a aVar = c;
        if (aVar != null) {
            aVar.e(bundle);
        } else {
            Intent intent = new Intent(Utils.getApp(), ServerService.class);
            intent.putExtras(bundle);
            b(intent);
        }
        for (a aVar2 : b.values()) {
            aVar2.e(bundle);
        }
    }

    public static void register() {
        if (b.y0()) {
            if (b.B0(ServerService.class.getName())) {
                Log.i("MessengerUtils", "Server service is running.");
            } else {
                b(new Intent(Utils.getApp(), ServerService.class));
            }
        } else if (c == null) {
            a aVar = new a(null);
            if (aVar.b()) {
                c = aVar;
            } else {
                Log.e("MessengerUtils", "Bind service failed.");
            }
        } else {
            Log.i("MessengerUtils", "The client have been bind.");
        }
    }

    public static void subscribe(@NonNull String str, @NonNull MessageCallback messageCallback) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(messageCallback, "Argument 'callback' of type MessageCallback (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        f2265a.put(str, messageCallback);
    }

    public static void unregister() {
        if (b.y0()) {
            if (!b.B0(ServerService.class.getName())) {
                Log.i("MessengerUtils", "Server service isn't running.");
                return;
            } else {
                Utils.getApp().stopService(new Intent(Utils.getApp(), ServerService.class));
            }
        }
        a aVar = c;
        if (aVar != null) {
            aVar.f();
        }
    }

    public static void unsubscribe(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        f2265a.remove(str);
    }

    public static void unregister(String str) {
        if (!b.containsKey(str)) {
            Log.i("MessengerUtils", "unregister: client didn't register: " + str);
            return;
        }
        a aVar = b.get(str);
        b.remove(str);
        if (aVar != null) {
            aVar.f();
        }
    }

    public static void register(String str) {
        if (b.containsKey(str)) {
            Log.i("MessengerUtils", "register: client registered: " + str);
            return;
        }
        a aVar = new a(str);
        if (aVar.b()) {
            b.put(str, aVar);
            return;
        }
        Log.e("MessengerUtils", "register: client bind failed: " + str);
    }
}
