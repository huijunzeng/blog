package com.teeya.common.constant;

public interface BaseEnum<C> {
    C code();

    static <T extends BaseEnum<?>> T parse(Class<T> enumType, Object code) {
        BaseEnum[] enums = (BaseEnum[])enumType.getEnumConstants();
        BaseEnum[] var6 = enums;
        int var5 = enums.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            T t = (T) var6[var4];
            if (t.code().equals(code)) {
                return t;
            }
        }

        throw new IllegalArgumentException("No enum constant " + enumType.getCanonicalName() + "." + code);
    }
}

