package com.ido.ble.bluetooth.connect;

import com.ido.ble.common.n;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class o implements j {

    /* renamed from: a  reason: collision with root package name */
    private int f12080a = -1;
    private int b = -1;
    private int c = -1;

    /* loaded from: classes11.dex */
    public class a implements n.b {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Runnable f12081a;

        /* renamed from: com.ido.ble.bluetooth.connect.o$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class RunnableC0573a implements Runnable {
            public RunnableC0573a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                String str = com.ido.ble.bluetooth.f.b.f12116a;
                LogTool.b(str, "[TimeOutPresenter] connect task time out, task id = " + o.this.c);
                a.this.f12081a.run();
                o.this.f12080a = -1;
            }
        }

        public a(Runnable runnable) {
            this.f12081a = runnable;
        }

        @Override // com.ido.ble.common.n.b
        public void a() {
            if (o.this.f12080a < 0) {
                return;
            }
            com.ido.ble.common.e.a(new RunnableC0573a());
        }
    }

    /* loaded from: classes11.dex */
    public class b implements n.b {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Runnable f12083a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                String str = com.ido.ble.bluetooth.f.b.f12116a;
                LogTool.b(str, "[TimeOutPresenter] disconnect task time out, task id = " + o.this.c);
                b.this.f12083a.run();
                o.this.b = -1;
            }
        }

        public b(Runnable runnable) {
            this.f12083a = runnable;
        }

        @Override // com.ido.ble.common.n.b
        public void a() {
            if (o.this.b < 0) {
                return;
            }
            com.ido.ble.common.e.a(new a());
        }
    }

    /* loaded from: classes11.dex */
    public class c implements n.b {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Runnable f12085a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                String str = com.ido.ble.bluetooth.f.b.f12116a;
                LogTool.b(str, "[TimeOutPresenter] discover services task time out, task id = " + o.this.c);
                c.this.f12085a.run();
                o.this.c = -1;
            }
        }

        public c(Runnable runnable) {
            this.f12085a = runnable;
        }

        @Override // com.ido.ble.common.n.b
        public void a() {
            if (o.this.c < 0) {
                return;
            }
            com.ido.ble.common.e.a(new a());
        }
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void a() {
        if (this.b < 0) {
            return;
        }
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[TimeOutPresenter] stop disconnect timeout task , task id = " + this.b);
        com.ido.ble.common.n.a(this.b);
        this.b = -1;
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void a(Runnable runnable, long j) {
        if (this.b >= 0) {
            return;
        }
        this.b = com.ido.ble.common.n.a(new b(runnable), j);
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[TimeOutPresenter] start disconnect timeout task , task id = " + this.b);
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void b() {
        if (this.f12080a < 0) {
            return;
        }
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[TimeOutPresenter] stop connect timeout task , task id = " + this.f12080a);
        com.ido.ble.common.n.a(this.f12080a);
        this.f12080a = -1;
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void b(Runnable runnable, long j) {
        if (this.c >= 0) {
            return;
        }
        this.c = com.ido.ble.common.n.a(new c(runnable), j);
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[TimeOutPresenter] start discover services timeout task , task id = " + this.c);
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void c() {
        if (this.c < 0) {
            return;
        }
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[TimeOutPresenter] stop discover services timeout task , task id = " + this.c);
        com.ido.ble.common.n.a(this.c);
        this.c = -1;
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void c(Runnable runnable, long j) {
        if (this.f12080a >= 0) {
            return;
        }
        this.f12080a = com.ido.ble.common.n.a(new a(runnable), j);
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[TimeOutPresenter] start connect timeout task , task id = " + this.f12080a);
    }
}
