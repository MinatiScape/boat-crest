package com.touchgui.sdk;

import com.touchgui.sdk.TGHealthDataManager;
import com.touchgui.sdk.bean.TGBreathTrain;
import com.touchgui.sdk.bean.TGFunctions;
import com.touchgui.sdk.bean.TGHeartRateData;
import com.touchgui.sdk.bean.TGSleepData;
import com.touchgui.sdk.bean.TGStepData;
import com.touchgui.sdk.bean.TGStressData;
import com.touchgui.sdk.bean.TGSyncSpo2;
import com.touchgui.sdk.internal.ba;
import com.touchgui.sdk.internal.e4;
import com.touchgui.sdk.internal.f4;
import com.touchgui.sdk.internal.g4;
import com.touchgui.sdk.internal.l4;
import com.touchgui.sdk.internal.p4;
import com.touchgui.sdk.internal.r4;
import com.touchgui.sdk.internal.s4;
import com.touchgui.sdk.internal.t4;
import com.touchgui.sdk.internal.v8;
import com.touchgui.sdk.internal.x6;
import com.touchgui.sdk.internal.y8;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    public final com.touchgui.sdk.internal.a0 f13736a;
    public int d;
    public ba f;
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final AtomicBoolean e = new AtomicBoolean(false);

    public g(com.touchgui.sdk.internal.a0 a0Var) {
        this.f13736a = a0Var;
    }

    public static void a(g gVar, Object obj, boolean z) {
        TGLogger.d(gVar.f13736a, obj.toString());
        ba baVar = gVar.f;
        if (baVar != null) {
            Iterator it = baVar.f13747a.b.iterator();
            while (it.hasNext()) {
                TGHealthDataCallback tGHealthDataCallback = (TGHealthDataCallback) it.next();
                if (tGHealthDataCallback instanceof TGHealthDataManager.OnHealthDataListener) {
                    TGHealthDataManager.OnHealthDataListener onHealthDataListener = (TGHealthDataManager.OnHealthDataListener) tGHealthDataCallback;
                    if (obj instanceof TGHeartRateData) {
                        onHealthDataListener.onHeartRateData((TGHeartRateData) obj, z);
                    } else if (obj instanceof TGStepData) {
                        onHealthDataListener.onStepData((TGStepData) obj, z);
                    } else if (obj instanceof TGSleepData) {
                        onHealthDataListener.onSleepData((TGSleepData) obj, z);
                    } else if (obj instanceof TGSyncSpo2) {
                        onHealthDataListener.onSpo2Data((TGSyncSpo2) obj, z);
                    } else if (obj instanceof TGStressData) {
                        onHealthDataListener.onStressData((TGStressData) obj, z);
                    } else if (obj instanceof TGBreathTrain) {
                        onHealthDataListener.onBreathTrainData((TGBreathTrain) obj);
                    }
                } else {
                    tGHealthDataCallback.onHealthData(obj);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i) {
        if (i == 1 || i == -1) {
            int i2 = this.d + 1;
            this.d = i2;
            a(i2);
        }
    }

    public final void a(int i) {
        boolean z;
        if (i < this.c.size()) {
            int size = this.e.get() ? this.c.size() : 0;
            if (size > 0) {
                int i2 = (i * 100) / size;
                ba baVar = this.f;
                if (baVar != null) {
                    Iterator it = baVar.f13747a.b.iterator();
                    while (it.hasNext()) {
                        ((TGHealthDataCallback) it.next()).onProgress(i2);
                    }
                }
            }
            ((g4) this.c.get(i)).a(new f4() { // from class: com.touchgui.sdk.u
                @Override // com.touchgui.sdk.internal.f4
                public final void a(int i3) {
                    g.this.b(i3);
                }
            });
            return;
        }
        Iterator it2 = this.b.iterator();
        while (it2.hasNext()) {
            int i3 = ((f) it2.next()).dataType;
            if (i3 != 2 && i3 != 3) {
                if (i3 == 1) {
                    z = true;
                    break;
                }
            } else {
                z = true;
                break;
            }
        }
        z = false;
        if (z) {
            new v8(this.f13736a, new y8((byte) 8, (byte) 2, 1)).execute(new e4(this));
            return;
        }
        int size2 = this.c.size();
        int size3 = this.e.get() ? this.c.size() : 0;
        if (size3 > 0) {
            int i4 = (size2 * 100) / size3;
            ba baVar2 = this.f;
            if (baVar2 != null) {
                Iterator it3 = baVar2.f13747a.b.iterator();
                while (it3.hasNext()) {
                    ((TGHealthDataCallback) it3.next()).onProgress(i4);
                }
            }
        }
        ba baVar3 = this.f;
        if (baVar3 != null) {
            Iterator it4 = baVar3.f13747a.b.iterator();
            while (it4.hasNext()) {
                ((TGHealthDataCallback) it4.next()).onCompleted();
            }
        }
        this.e.set(false);
    }

    public final void a(int i, String str) {
        ba baVar = this.f;
        if (baVar != null) {
            Iterator it = baVar.f13747a.b.iterator();
            while (it.hasNext()) {
                ((TGHealthDataCallback) it.next()).onError(i, str);
            }
        }
        this.e.set(false);
    }

    public final boolean a(int i, int i2) {
        if (this.e.getAndSet(true)) {
            TGLogger.w(this.f13736a, "Synchronization is in progress. Please do not call repeatedly");
            return false;
        } else if (i == 6 && !this.f13736a.i()) {
            a(TGErrorCode.ERROR_HEALTH_DATA_NOT_SUPPORT, "This device does not support breath train.");
            return false;
        } else if (i == 5 && !this.f13736a.a(TGFunctions.FUNC_STRESS)) {
            a(TGErrorCode.ERROR_HEALTH_DATA_NOT_SUPPORT, "This device does not support stress testing.");
            return false;
        } else {
            f[] fVarArr = {new f(i, i2)};
            this.b.clear();
            f fVar = fVarArr[0];
            this.b.add(new f(fVar.dataType, fVar.dataScope));
            a();
            return true;
        }
    }

    public final void a(ba baVar) {
        this.f = baVar;
    }

    public final void a() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i;
        ArrayList arrayList;
        g4 r4Var;
        TGLogger.d(this.f13736a, "Synchronization of health data started");
        ba baVar = this.f;
        if (baVar != null) {
            Iterator it = baVar.f13747a.b.iterator();
            while (it.hasNext()) {
                ((TGHealthDataCallback) it.next()).onStart();
            }
        }
        this.d = 0;
        this.c.clear();
        Iterator it2 = this.b.iterator();
        while (it2.hasNext()) {
            int i2 = ((f) it2.next()).dataType;
            if (i2 == 2) {
                arrayList = this.c;
                r4Var = new r4(this, false, 1);
            } else if (i2 == 3) {
                arrayList = this.c;
                r4Var = new p4(this, false, 1);
            } else if (i2 == 1) {
                arrayList = this.c;
                r4Var = new l4(this, false, 1);
            }
            arrayList.add(r4Var);
        }
        Iterator it3 = this.b.iterator();
        while (it3.hasNext()) {
            int i3 = ((f) it3.next()).dataType;
            if (i3 != 2 && i3 != 3) {
                if (i3 == 1) {
                    z = true;
                    break;
                }
            } else {
                z = true;
                break;
            }
        }
        z = false;
        if (z) {
            new v8(this.f13736a, new t4()).execute(new d(this));
            return;
        }
        Iterator it4 = this.b.iterator();
        while (true) {
            if (!it4.hasNext()) {
                z2 = false;
                break;
            } else if (((f) it4.next()).dataType == 4) {
                z2 = true;
                break;
            }
        }
        if (!z2) {
            Iterator it5 = this.b.iterator();
            while (true) {
                if (!it5.hasNext()) {
                    z3 = false;
                    break;
                } else if (((f) it5.next()).dataType == 6) {
                    z3 = true;
                    break;
                }
            }
            if (!z3) {
                Iterator it6 = this.b.iterator();
                while (true) {
                    if (!it6.hasNext()) {
                        z4 = false;
                        break;
                    } else if (((f) it6.next()).dataType == 5) {
                        z4 = true;
                        break;
                    }
                }
                if (z4) {
                    Iterator it7 = this.b.iterator();
                    while (true) {
                        if (!it7.hasNext()) {
                            i = 0;
                            break;
                        }
                        f fVar = (f) it7.next();
                        if (fVar.dataType == 5) {
                            i = fVar.dataScope;
                            break;
                        }
                    }
                    if (i == 1 || i == 4) {
                        this.c.add(new s4(this, false));
                    }
                    if (i == 2 || i == 4) {
                        this.c.add(new s4(this, true));
                    }
                }
                a(0);
                return;
            }
        }
        new v8(this.f13736a, new x6()).execute(new e(this));
    }
}
