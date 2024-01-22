package com.goodix.ble.libcomx.ptmodel;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes5.dex */
public final class PtJudge {

    /* renamed from: a  reason: collision with root package name */
    public PtCriterion f8041a;
    public ArrayList<PtJudge> b = null;
    public Boolean c;
    public String exception;
    public boolean logicalNot;
    public boolean logicalOr;
    public String name;
    public String note;
    public long timestamp;
    public String value;

    public PtJudge(PtCriterion ptCriterion) {
        this.f8041a = ptCriterion;
        if (ptCriterion != null) {
            this.name = ptCriterion.name;
            this.logicalNot = ptCriterion.logicalNot;
        }
    }

    public final boolean a() {
        ArrayList<PtJudge> arrayList = this.b;
        return (arrayList == null || arrayList.isEmpty()) ? false : true;
    }

    public PtJudge createSubJudge(PtCriterion ptCriterion) {
        if (this.b == null) {
            this.b = new ArrayList<>(8);
        }
        PtJudge ptJudge = new PtJudge(ptCriterion);
        this.b.add(ptJudge);
        return ptJudge;
    }

    public PtCriterion getCriterion() {
        return this.f8041a;
    }

    public String getReportCaption() {
        HexStringBuilder hexStringBuilder = new HexStringBuilder(128);
        hexStringBuilder.a(this.name);
        hexStringBuilder.a("(");
        if (this.logicalNot) {
            hexStringBuilder.a("NOT ");
        }
        PtCriterion ptCriterion = this.f8041a;
        if (ptCriterion != null) {
            String str = ptCriterion.valueExact;
            if (str != null) {
                hexStringBuilder.a(str);
            } else {
                Long l = ptCriterion.valueMin;
                if (l != null || ptCriterion.valueMax != null) {
                    if (l != null) {
                        hexStringBuilder.getStringBuilder().append(this.f8041a.valueMin);
                    } else {
                        hexStringBuilder.a("N");
                    }
                    hexStringBuilder.a("--");
                    if (this.f8041a.valueMax != null) {
                        hexStringBuilder.getStringBuilder().append(this.f8041a.valueMax);
                    } else {
                        hexStringBuilder.a("N");
                    }
                }
            }
            String str2 = this.f8041a.valueUnit;
            if (str2 != null) {
                hexStringBuilder.a(str2);
            }
        }
        hexStringBuilder.a(")");
        return hexStringBuilder.toString();
    }

    public String getReportContent() {
        HexStringBuilder hexStringBuilder = new HexStringBuilder(128);
        hexStringBuilder.a(isPass() ? "PASS" : "FAIL");
        if (this.exception != null) {
            hexStringBuilder.a("(").a(this.exception).a(")");
        } else {
            hexStringBuilder.a("(");
            if (a()) {
                for (int i = 0; i < this.b.size(); i++) {
                    hexStringBuilder.a("(").a(this.b.get(i).name).a(":").a(this.b.get(i).value).a(")");
                }
            } else {
                hexStringBuilder.a(this.value);
            }
            hexStringBuilder.a(")");
        }
        return hexStringBuilder.toString();
    }

    public PtJudge getSubJudge(int i) {
        if (i < this.b.size()) {
            return this.b.get(i);
        }
        return null;
    }

    public int getSubJudgeCount() {
        return this.b.size();
    }

    public boolean hasValue() {
        return this.value != null;
    }

    public boolean isPass() {
        Boolean bool = this.c;
        return bool != null && bool.booleanValue();
    }

    public boolean isTested() {
        return (this.c == null && this.exception == null) ? false : true;
    }

    public boolean judge(String str) {
        PtCriterion ptCriterion;
        this.value = str;
        this.timestamp = System.currentTimeMillis();
        if (a()) {
            if (this.logicalOr) {
                this.c = Boolean.FALSE;
                Iterator<PtJudge> it = this.b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (it.next().isPass()) {
                        this.c = Boolean.TRUE;
                        break;
                    }
                }
            } else {
                this.c = Boolean.TRUE;
                Iterator<PtJudge> it2 = this.b.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (!it2.next().isPass()) {
                        this.c = Boolean.FALSE;
                        break;
                    }
                }
            }
            if (this.logicalNot) {
                this.c = Boolean.valueOf(!this.c.booleanValue());
            }
        } else if (str != null && (ptCriterion = this.f8041a) != null) {
            String str2 = ptCriterion.valueExact;
            if (str2 != null) {
                this.c = Boolean.valueOf(str.equals(str2));
            } else if (ptCriterion.valueMin != null || ptCriterion.valueMax != null) {
                this.c = Boolean.TRUE;
                if (ptCriterion.valuePrecision == null) {
                    long parseLong = Long.parseLong(str);
                    Long l = this.f8041a.valueMin;
                    if (l != null && parseLong < l.longValue()) {
                        this.c = Boolean.FALSE;
                    }
                    Long l2 = this.f8041a.valueMax;
                    if (l2 != null && parseLong > l2.longValue()) {
                        this.c = Boolean.FALSE;
                    }
                } else {
                    float parseFloat = Float.parseFloat(str);
                    PtCriterion ptCriterion2 = this.f8041a;
                    if (ptCriterion2.valueMin != null && ((int) (((float) ptCriterion2.valuePrecision.longValue()) * parseFloat)) < this.f8041a.valueMin.longValue()) {
                        this.c = Boolean.FALSE;
                    }
                    PtCriterion ptCriterion3 = this.f8041a;
                    if (ptCriterion3.valueMax != null && ((int) (parseFloat * ((float) ptCriterion3.valuePrecision.longValue()))) > this.f8041a.valueMax.longValue()) {
                        this.c = Boolean.FALSE;
                    }
                }
            }
            Boolean bool = this.c;
            if (bool != null && this.logicalNot) {
                this.c = Boolean.valueOf(!bool.booleanValue());
            }
        }
        return isPass();
    }

    public void reset() {
        this.value = null;
        this.timestamp = 0L;
        this.c = null;
        this.exception = null;
        this.note = null;
        if (a()) {
            Iterator<PtJudge> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().reset();
            }
        }
    }

    public void setCriterion(PtCriterion ptCriterion) {
        this.f8041a = ptCriterion;
    }

    public void setPass(Boolean bool) {
        this.c = bool;
        if (a()) {
            Iterator<PtJudge> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().setPass(bool);
            }
        }
    }

    public boolean judge(int i) {
        return judge(String.valueOf(i));
    }

    public boolean judge(long j) {
        return judge(String.valueOf(j));
    }

    public boolean judge(float f) {
        return judge(String.valueOf(f));
    }
}
