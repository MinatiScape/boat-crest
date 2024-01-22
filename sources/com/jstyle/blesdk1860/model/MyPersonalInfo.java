package com.jstyle.blesdk1860.model;
/* loaded from: classes11.dex */
public class MyPersonalInfo extends SendData {

    /* renamed from: a  reason: collision with root package name */
    public int f12528a;
    public int b;
    public int c;
    public int d;
    public int e = 70;

    public int getAge() {
        return this.b;
    }

    public int getHeight() {
        return this.c;
    }

    public int getSex() {
        return this.f12528a;
    }

    public int getStepLength() {
        return this.e;
    }

    public int getWeight() {
        return this.d;
    }

    public void setAge(int i) {
        this.b = i;
    }

    public void setHeight(int i) {
        this.c = i;
    }

    public void setSex(int i) {
        this.f12528a = i;
    }

    public void setStepLength(int i) {
        this.e = i;
    }

    public void setWeight(int i) {
        this.d = i;
    }

    public String toString() {
        return "MyPersonalInfo{sex=" + this.f12528a + ", age=" + this.b + ", height=" + this.c + ", weight=" + this.d + ", stepLength=" + this.e + '}';
    }
}
