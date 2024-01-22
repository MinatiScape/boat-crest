package com.alibaba.fastjson.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
/* loaded from: classes.dex */
public class ParameterizedTypeImpl implements ParameterizedType {
    public final Type[] h;
    public final Type i;
    public final Type j;

    public ParameterizedTypeImpl(Type[] typeArr, Type type, Type type2) {
        this.h = typeArr;
        this.i = type;
        this.j = type2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ParameterizedTypeImpl parameterizedTypeImpl = (ParameterizedTypeImpl) obj;
        if (Arrays.equals(this.h, parameterizedTypeImpl.h)) {
            Type type = this.i;
            if (type == null ? parameterizedTypeImpl.i == null : type.equals(parameterizedTypeImpl.i)) {
                Type type2 = this.j;
                Type type3 = parameterizedTypeImpl.j;
                return type2 != null ? type2.equals(type3) : type3 == null;
            }
            return false;
        }
        return false;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return this.h;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return this.i;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return this.j;
    }

    public int hashCode() {
        Type[] typeArr = this.h;
        int hashCode = (typeArr != null ? Arrays.hashCode(typeArr) : 0) * 31;
        Type type = this.i;
        int hashCode2 = (hashCode + (type != null ? type.hashCode() : 0)) * 31;
        Type type2 = this.j;
        return hashCode2 + (type2 != null ? type2.hashCode() : 0);
    }
}
