package com.alibaba.fastjson.parser;

import java.lang.reflect.Type;
/* loaded from: classes.dex */
public class ParseContext {

    /* renamed from: a  reason: collision with root package name */
    public transient String f2119a;
    public final Object fieldName;
    public Object object;
    public final ParseContext parent;
    public Type type;

    public ParseContext(ParseContext parseContext, Object obj, Object obj2) {
        this.parent = parseContext;
        this.object = obj;
        this.fieldName = obj2;
    }

    public String toString() {
        if (this.f2119a == null) {
            if (this.parent == null) {
                this.f2119a = "$";
            } else if (this.fieldName instanceof Integer) {
                this.f2119a = this.parent.toString() + "[" + this.fieldName + "]";
            } else {
                this.f2119a = this.parent.toString() + "." + this.fieldName;
            }
        }
        return this.f2119a;
    }
}
