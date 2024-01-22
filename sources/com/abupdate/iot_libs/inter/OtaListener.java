package com.abupdate.iot_libs.inter;

import com.abupdate.iot_libs.a.b;
import com.abupdate.iot_libs.engine.f;
import com.abupdate.iot_libs.inter.MessageListener;
import com.abupdate.iot_libs.service.OtaService;
import com.abupdate.mqtt_libs.connect.MqttManager;
import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.IMqttToken;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.trace.Trace;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class OtaListener implements MessageListener.ConnectionLostListener, IMqttActionListener {
    public static OtaListener g;
    public Action e;

    /* renamed from: a  reason: collision with root package name */
    public Object f1903a = new Object();
    public List<Action> b = new ArrayList();
    public Action[] c = {Action.SUB_LOGIN, Action.SUB_LOGOUT, Action.SUB_NOTIFY};
    public int d = 3;
    public Map<Enum, List<IListener>> f = new HashMap();

    /* loaded from: classes.dex */
    public enum Action {
        CONNECT,
        DISCONNECT,
        PUB_REPORT_DEVICEINFO,
        PUB_LOGIN,
        PUB_LOGOUT,
        SUB_LOGIN,
        SUB_LOGOUT,
        SUB_NOTIFY,
        SUB_REPORT_DEVICEINFO
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ List h;

        /* renamed from: com.abupdate.iot_libs.inter.OtaListener$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0193a implements ILoginCallback {
            public C0193a() {
            }

            @Override // com.abupdate.iot_libs.inter.ILoginCallback
            public void onLoginFail(int i) {
                OtaService.selfDisconnect();
            }

            @Override // com.abupdate.iot_libs.inter.ILoginCallback
            public void onLoginSuccess() {
                List<IListener> list = a.this.h;
                if (list != null) {
                    for (IListener iListener : list) {
                        ((IStatusListener) iListener).onConnected();
                    }
                }
            }

            @Override // com.abupdate.iot_libs.inter.ILoginCallback
            public void onLoginTimeout() {
                OtaService.selfDisconnect();
            }
        }

        public a(List list) {
            this.h = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            OtaListener.this.i();
            if (OtaListener.this.b.size() == OtaListener.this.c.length) {
                com.abupdate.iot_libs.a.b.a().b(new C0193a());
            } else {
                OtaService.selfDisconnect();
            }
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f1905a;

        static {
            int[] iArr = new int[Action.values().length];
            f1905a = iArr;
            try {
                iArr[Action.CONNECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1905a[Action.DISCONNECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1905a[Action.PUB_REPORT_DEVICEINFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1905a[Action.PUB_LOGIN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1905a[Action.PUB_LOGOUT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1905a[Action.SUB_LOGIN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1905a[Action.SUB_LOGOUT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1905a[Action.SUB_NOTIFY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1905a[Action.SUB_REPORT_DEVICEINFO.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public static OtaListener getInstance() {
        if (g == null) {
            synchronized (OtaListener.class) {
                if (g == null) {
                    g = new OtaListener();
                }
            }
        }
        return g;
    }

    public void addListener(Action action, IListener iListener) {
        if (!this.f.containsKey(action)) {
            this.f.put(action, new ArrayList());
        }
        this.f.get(action).add(iListener);
    }

    public final void b(Action action) {
        List<Action> list = this.b;
        if (list == null || list.contains(action)) {
            return;
        }
        this.b.add(action);
    }

    public final void c() {
        com.abupdate.iot_libs.a.b.a().a(b.a.Connected);
        e(g(Action.CONNECT));
    }

    public void connect(Throwable th) {
        com.abupdate.iot_libs.a.b.a().a(b.a.Disconnected);
        List<IListener> g2 = g(Action.CONNECT);
        if (g2 != null) {
            Iterator<IListener> it = g2.iterator();
            while (it.hasNext()) {
                ((IStatusListener) it.next()).onError(f(th));
            }
        }
        if (th != null) {
            th.printStackTrace();
            Trace.e("OtaListener", "connect() " + th.toString());
        }
        if (MqttManager.getInstance().isConneect() || com.abupdate.iot_libs.a.b.a().b() == b.a.Connecting || 5 == f(th) || 4 == f(th)) {
            return;
        }
        MqttManager.getInstance().keepConnect(1800000L, System.currentTimeMillis() + 30000);
    }

    public final void d() {
    }

    public void disconnect(Throwable th) {
        com.abupdate.iot_libs.a.b.a().a(b.a.Disconnected);
        List<IListener> g2 = g(Action.CONNECT);
        if (g2 != null) {
            Iterator<IListener> it = g2.iterator();
            while (it.hasNext()) {
                ((IStatusListener) it.next()).onError(f(th));
            }
        }
        if (th == null) {
            return;
        }
        Trace.e("OtaListener", "disconnect() exception:" + th.toString());
        th.printStackTrace();
    }

    public final void e(List<IListener> list) {
        f.a().a(new a(list));
    }

    public final int f(Throwable th) {
        if (th instanceof MqttException) {
            return ((MqttException) th).getReasonCode();
        }
        return 6;
    }

    public final List<IListener> g(Action action) {
        return this.f.get(action);
    }

    public final void h() {
        Object obj = this.f1903a;
        if (obj != null) {
            synchronized (obj) {
                this.f1903a.notify();
            }
        }
    }

    public final void i() {
        while (this.d > 0) {
            int size = this.b.size();
            Action[] actionArr = this.c;
            if (size >= actionArr.length) {
                break;
            }
            this.d--;
            if (actionArr == null || actionArr.length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                Action[] actionArr2 = this.c;
                if (i < actionArr2.length) {
                    if (!this.b.contains(actionArr2[i])) {
                        Action[] actionArr3 = this.c;
                        if (actionArr3[i] == Action.SUB_LOGIN) {
                            com.abupdate.iot_libs.a.b.a().d();
                            j();
                        } else if (actionArr3[i] == Action.SUB_LOGOUT) {
                            com.abupdate.iot_libs.a.b.a().e();
                            j();
                        } else if (actionArr3[i] == Action.SUB_NOTIFY) {
                            com.abupdate.iot_libs.a.b.a().f();
                            j();
                        }
                    }
                    i++;
                }
            }
        }
        this.d = 3;
    }

    public final void j() {
        Object obj = this.f1903a;
        if (obj != null) {
            synchronized (obj) {
                try {
                    this.f1903a.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // com.abupdate.iot_libs.inter.MessageListener.ConnectionLostListener
    public void onConnectLost(int i) {
        List<IListener> g2 = g(Action.CONNECT);
        if (-1 != i) {
            if (g2 != null) {
                Iterator<IListener> it = g2.iterator();
                while (it.hasNext()) {
                    ((IStatusListener) it.next()).onAbnormalDisconnected(i);
                }
            }
            if (5 != i && 4 != i) {
                MqttManager.getInstance().keepConnect(1800000L, System.currentTimeMillis() + 30000);
            }
        } else if (g2 != null) {
            Iterator<IListener> it2 = g2.iterator();
            while (it2.hasNext()) {
                ((IStatusListener) it2.next()).onDisconnected();
            }
        }
        setAction(Action.CONNECT);
        com.abupdate.iot_libs.a.b.a().a(b.a.Disconnected);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
    public void onFailure(IMqttToken iMqttToken, Throwable th) {
        switch (b.f1905a[this.e.ordinal()]) {
            case 1:
                Trace.d("OtaListener", "onFailure() connect");
                connect(th);
                return;
            case 2:
                Trace.d("OtaListener", "onFailure() disconnect");
                disconnect(th);
                return;
            case 3:
                Trace.d("OtaListener", "onFailure() pub report deviceinfo");
                return;
            case 4:
                Trace.d("OtaListener", "onFailure() pub login");
                return;
            case 5:
                Trace.d("OtaListener", "onFailure() pub logout");
                return;
            case 6:
                Trace.d("OtaListener", "onFailure() sub login");
                h();
                return;
            case 7:
                Trace.d("OtaListener", "onFailure() sub logout");
                h();
                return;
            case 8:
                Trace.d("OtaListener", "onFailure() sub notify");
                h();
                return;
            case 9:
                Trace.d("OtaListener", "onFailure() sub report deviceinfo");
                h();
                return;
            default:
                return;
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
    public void onSuccess(IMqttToken iMqttToken) {
        switch (b.f1905a[this.e.ordinal()]) {
            case 1:
                Trace.d("OtaListener", "onSuccess() socket connect success");
                c();
                return;
            case 2:
                Trace.d("OtaListener", "onSuccess() socket disconnect success");
                d();
                return;
            case 3:
                Trace.d("OtaListener", "onSuccess() pub report deviceinfo");
                return;
            case 4:
                Trace.d("OtaListener", "onSuccess() pub login");
                return;
            case 5:
                Trace.d("OtaListener", "onSuccess() pub logout");
                return;
            case 6:
                h();
                b(Action.SUB_LOGIN);
                return;
            case 7:
                h();
                b(Action.SUB_LOGOUT);
                return;
            case 8:
                h();
                b(Action.SUB_NOTIFY);
                return;
            case 9:
                h();
                return;
            default:
                return;
        }
    }

    public void removeAllListener(Action action) {
        if (this.f.containsKey(action)) {
            this.f.get(action).clear();
        }
    }

    public boolean removeListener(Action action, IListener iListener) {
        if (this.f.containsKey(action)) {
            return this.f.get(action).remove(iListener);
        }
        return false;
    }

    public OtaListener setAction(Action action) {
        this.e = action;
        if (Action.CONNECT == action) {
            MessageListener.getInstance().setConnectionLostListener(this);
        }
        return this;
    }
}
