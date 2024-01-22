package com.goodix.ble.libcomx.ptmodel;

import com.goodix.ble.libcomx.task.ITask;
import com.goodix.ble.libcomx.task.TaskQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes5.dex */
public class PtCase<CTX> extends TaskQueue {
    public ArrayList<PtStep> I = new ArrayList<>(8);
    public final CTX targetCtx;

    public PtCase(CTX ctx) {
        this.targetCtx = ctx;
    }

    public final <T extends ITask> T addExtraAction(T t) {
        addTask(t);
        return t;
    }

    public final PtStep createStep(String str, PtCriterion ptCriterion) {
        PtJudge ptJudge = new PtJudge(ptCriterion);
        PtStep ptStep = new PtStep(ptJudge);
        this.I.add(ptStep);
        addTask(ptStep);
        if (str == null) {
            if (ptJudge.name == null) {
                ptJudge.name = getName() + MqttTopic.MULTI_LEVEL_WILDCARD + this.I.size();
            }
        } else {
            ptJudge.name = str;
        }
        ptStep.setName(ptJudge.name);
        return ptStep;
    }

    public void getResults(List<PtJudge> list) {
        if (list == null) {
            return;
        }
        Iterator<PtStep> it = this.I.iterator();
        while (it.hasNext()) {
            PtStep next = it.next();
            PtJudge judge = next.getJudge();
            if (judge.name == null) {
                judge.name = next.getName();
            }
            list.add(judge);
        }
    }

    public final PtStep getStep(int i) {
        return this.I.get(i);
    }

    public final int getStepCount() {
        return this.I.size();
    }

    public final boolean isPass() {
        Iterator<PtStep> it = this.I.iterator();
        while (it.hasNext()) {
            if (!it.next().getJudge().isPass()) {
                return false;
            }
        }
        return true;
    }

    public final boolean isTested(boolean z) {
        if (z) {
            Iterator<PtStep> it = this.I.iterator();
            while (it.hasNext()) {
                if (!it.next().getJudge().isTested()) {
                    return false;
                }
            }
            return true;
        }
        Iterator<PtStep> it2 = this.I.iterator();
        while (it2.hasNext()) {
            if (it2.next().getJudge().isTested()) {
                return true;
            }
        }
        return false;
    }

    public final PtStep createStep(PtCriterion ptCriterion) {
        return createStep(ptCriterion != null ? ptCriterion.name : null, ptCriterion);
    }
}
