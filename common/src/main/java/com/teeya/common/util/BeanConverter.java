package com.teeya.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.teeya.common.constant.BaseEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * bean复制类
 */

public class BeanConverter {
    private static Map<String, Map<String, PropertyDescriptor>> propertyDescriptorCache = new ConcurrentHashMap();
    private static Map<String, List<String>> fieldCache = new HashMap();

    private BeanConverter() {
    }

    private static <T> T doCopy(Object src, T dest, boolean setDefaultValForNull, String... includeFields) throws BeanConverter.BeanConverterException {
        return doCopy(src, dest, setDefaultValForNull, includeFields, (String[])null);
    }

    private static <T> T doCopy(Object src, T dest, boolean setDefaultValForNull, String[] includeFields, String[] unIncludFieldList) throws BeanConverter.BeanConverterException {
        if (src == null) {
            return null;
        } else {
            List<String> unIncludeFieldList = unIncludFieldList != null && unIncludFieldList.length > 0 ? Arrays.asList(unIncludFieldList) : null;
            List includeFieldList = includeFields != null && includeFields.length > 0 ? Arrays.asList(includeFields) : null;

            try {
                Class<? extends Object> destClass = dest.getClass();
                Map<String, PropertyDescriptor> srcDescriptors = getCachePropertyDescriptors(src.getClass());
                Map<String, PropertyDescriptor> destDescriptors = getCachePropertyDescriptors(destClass);
                Set<String> keys = destDescriptors.keySet();
                Iterator var12 = keys.iterator();

                while(true) {
                    PropertyDescriptor srcDescriptor;
                    PropertyDescriptor destDescriptor;
                    Object value;
                    Class propertyType;
                    Method writeMethod;
                    do {
                        String name;
                        do {
                            do {
                                String key;
                                do {
                                    if (!var12.hasNext()) {
                                        return dest;
                                    }

                                    key = (String)var12.next();
                                    srcDescriptor = (PropertyDescriptor)srcDescriptors.get(key);
                                } while(srcDescriptor == null);

                                destDescriptor = (PropertyDescriptor)destDescriptors.get(key);
                                name = destDescriptor.getName();
                            } while(unIncludeFieldList != null && unIncludeFieldList.contains(name));
                        } while(includeFieldList != null && !includeFieldList.contains(name));

                        value = srcDescriptor.getReadMethod().invoke(src);
                        propertyType = destDescriptor.getPropertyType();
                        writeMethod = destDescriptor.getWriteMethod();
                        if (writeMethod == null) {
                            try {
                                writeMethod = destClass.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1), destDescriptor.getPropertyType());
                                destDescriptor.setWriteMethod(writeMethod);
                            } catch (Exception var20) {
                            }
                        }
                    } while(writeMethod == null);

                    boolean matched = propertyType == srcDescriptor.getPropertyType();
                    if (!matched && (value != null || setDefaultValForNull)) {
                        value = toValue(srcDescriptor, value, propertyType);
                    }

                    if (value == null && setDefaultValForNull) {
                        if (destDescriptor.getPropertyType() == Long.class) {
                            value = 0L;
                        } else if (destDescriptor.getPropertyType() == Integer.class) {
                            value = 0;
                        } else if (destDescriptor.getPropertyType() == Short.class) {
                            value = Short.valueOf((short)0);
                        } else if (destDescriptor.getPropertyType() == Byte.class) {
                            value = 0;
                        } else if (destDescriptor.getPropertyType() == Double.class) {
                            value = 0.0D;
                        } else if (destDescriptor.getPropertyType() == Float.class) {
                            value = 0.0F;
                        } else if (destDescriptor.getPropertyType() == String.class) {
                            value = "";
                        } else if (destDescriptor.getPropertyType() == BigDecimal.class) {
                            value = new BigDecimal("0");
                        }
                    }

                    if (value != null) {
                        writeMethod.invoke(dest, value);
                    }
                }
            } catch (Exception var21) {
                throw new BeanConverter.BeanConverterException(var21);
            }
        }
    }

    public static <T> T copy(Object src, T dest) throws BeanConverter.BeanConverterException {
        return doCopy(src, dest, false);
    }

    public static <T> List<T> copy(List<?> srcs, Class<T> destClass, boolean setDefaultValForNull) {
        if (srcs == null) {
            return new ArrayList();
        } else {
            List<T> dests = new ArrayList();
            Iterator var5 = srcs.iterator();

            while(var5.hasNext()) {
                Object src = var5.next();
                dests.add(copy(src, destClass, setDefaultValForNull));
            }

            return dests;
        }
    }

    public static <T> List<T> copy(List<?> srcs, Class<T> destClass) {
        return copy(srcs, destClass, false);
    }

    public static <T> T copy(Object src, Class<T> destClass, boolean setDefaultValForNull) throws BeanConverter.BeanConverterException {
        if (src == null) {
            return null;
        } else {
            try {
                T dest = destClass.newInstance();
                doCopy(src, dest, setDefaultValForNull);
                return dest;
            } catch (Exception var4) {
                throw new BeanConverter.BeanConverterException(var4);
            }
        }
    }

    public static <T> T copy(Object src, Class<T> destClass) throws BeanConverter.BeanConverterException {
        return copy(src, destClass, false);
    }

    public static <T> T copyProperties(Object src, T dest, String... includeFields) throws BeanConverter.BeanConverterException {
        doCopy(src, dest, false, includeFields);
        return dest;
    }

    public static <T> T copyProperties(Object src, Class<T> destClass, String... includeFields) throws BeanConverter.BeanConverterException {
        if (src == null) {
            return null;
        } else {
            try {
                T dest = destClass.newInstance();
                doCopy(src, dest, false, includeFields);
                return dest;
            } catch (Exception var4) {
                throw new BeanConverter.BeanConverterException(var4);
            }
        }
    }

    public static <T> T copyUnProperties(Object src, T dest, String... unIncludeFields) throws BeanConverter.BeanConverterException {
        doCopy(src, dest, false, (String[])null, unIncludeFields);
        return dest;
    }

    public static <T> T copyUnProperties(Object src, Class<T> destClass, String... unIncludeFields) throws BeanConverter.BeanConverterException {
        if (src == null) {
            return null;
        } else {
            try {
                T dest = destClass.newInstance();
                doCopy(src, dest, false, (String[])null, unIncludeFields);
                return dest;
            } catch (Exception var4) {
                throw new BeanConverter.BeanConverterException(var4);
            }
        }
    }

    public static void zeroWrapPropertiesToNull(Object bean, String... excludeFields) throws BeanConverter.BeanConverterException {
        try {
            Map<String, PropertyDescriptor> srcDescriptors = getCachePropertyDescriptors(bean.getClass());
            Set<String> keys = srcDescriptors.keySet();
            List<String> excludeFieldsList = null;
            if (excludeFields != null && excludeFields.length > 0 && StringUtils.isNotBlank(excludeFields[0])) {
                excludeFieldsList = Arrays.asList(excludeFields);
            }

            Iterator var6 = keys.iterator();

            while(true) {
                String key;
                PropertyDescriptor srcDescriptor;
                do {
                    do {
                        if (!var6.hasNext()) {
                            return;
                        }

                        key = (String)var6.next();
                        srcDescriptor = (PropertyDescriptor)srcDescriptors.get(key);
                    } while(srcDescriptor == null);
                } while(excludeFieldsList != null && excludeFieldsList.contains(key));

                Object value = srcDescriptor.getReadMethod().invoke(bean);
                boolean isWrapType = srcDescriptor.getPropertyType() == Long.class || srcDescriptor.getPropertyType() == Integer.class || srcDescriptor.getPropertyType() == Short.class || srcDescriptor.getPropertyType() == Double.class || srcDescriptor.getPropertyType() == Float.class;
                if (isWrapType && value != null && Integer.parseInt(value.toString()) == 0) {
                    value = null;
                    Method writeMethod = srcDescriptor.getWriteMethod();
                    if (writeMethod != null) {
                        writeMethod.invoke(bean, value);
                    }
                }
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            throw new BeanConverter.BeanConverterException(var11);
        }
    }

    private static Object toValue(PropertyDescriptor srcDescriptor, Object value, Class<?> propertyType) {
        boolean valueIsEnum = false;
        if (value == null || !propertyType.isEnum() && !(valueIsEnum = srcDescriptor.getPropertyType().isEnum())) {
            if (propertyType == BigDecimal.class) {
                value = value == null ? new BigDecimal("0") : new BigDecimal(value.toString());
            } else if (propertyType != Byte.TYPE && propertyType != Byte.class) {
                if (propertyType != Short.TYPE && propertyType != Short.class) {
                    if (propertyType != Integer.TYPE && propertyType != Integer.class) {
                        if (propertyType != Double.TYPE && propertyType != Double.class) {
                            if (propertyType == Date.class) {
                                if (value != null) {
                                    if (srcDescriptor.getPropertyType() == String.class) {
                                        value = DateUtils.parseDate(value.toString());
                                    } else if (srcDescriptor.getPropertyType() == Long.class || srcDescriptor.getPropertyType() == Integer.class || srcDescriptor.getPropertyType() == Long.TYPE || srcDescriptor.getPropertyType() == Integer.TYPE) {
                                        Long val = Long.valueOf(value.toString());
                                        if (val != 0L) {
                                            value = new Date(val);
                                        } else {
                                            value = null;
                                        }
                                    }
                                }
                            } else if (propertyType == String.class && srcDescriptor.getPropertyType() != String.class) {
                                if (value != null) {
                                    if (srcDescriptor.getPropertyType() == Date.class) {
                                        value = DateUtils.format((Date)value, new String[0]);
                                    } else {
                                        value = value.toString();
                                    }
                                }
                            } else if ((propertyType == Boolean.TYPE || propertyType == Boolean.class) && value.toString().matches("[0|1]")) {
                                value = "1".equals(value.toString());
                            }
                        } else {
                            value = value == null ? Double.valueOf("0") : Double.valueOf(value.toString());
                        }
                    } else if (srcDescriptor.getPropertyType() != Boolean.TYPE && srcDescriptor.getPropertyType() != Boolean.class) {
                        value = value == null ? Integer.valueOf("0") : Integer.valueOf(value.toString());
                    } else {
                        value = Boolean.parseBoolean(value.toString()) ? 1 : 0;
                    }
                } else {
                    value = value == null ? Short.valueOf("0") : Short.valueOf(value.toString());
                }
            } else {
                value = value == null ? Byte.valueOf("0") : Byte.valueOf(value.toString());
            }
        } else {
            Class<?> enumClass = valueIsEnum ? srcDescriptor.getPropertyType() : propertyType;
            boolean isBaseEnum = BaseEnum.class.isAssignableFrom(enumClass);
            if (!isBaseEnum) {
                return null;
            }

            Object[] enumConstants = enumClass.getEnumConstants();
            boolean matchEnum = false;
            Object[] var11 = enumConstants;
            int var10 = enumConstants.length;

            for(int var9 = 0; var9 < var10; ++var9) {
                Object enumConstant = var11[var9];
                matchEnum = valueIsEnum ? enumConstant.equals(value) : ((BaseEnum)enumConstant).code().equals(value);
                if (matchEnum) {
                    value = valueIsEnum ? ((BaseEnum)value).code() : enumConstant;
                    break;
                }
            }

            if (!matchEnum) {
                value = null;
            }
        }

        return value;
    }

    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        if (map != null && !map.isEmpty()) {
            try {
                T bean = clazz.newInstance();
                Map<String, PropertyDescriptor> descriptors = getCachePropertyDescriptors(clazz);
                Iterator var5 = descriptors.values().iterator();

                while(var5.hasNext()) {
                    PropertyDescriptor descriptor = (PropertyDescriptor)var5.next();
                    String propertyName = descriptor.getName();
                    if (map.containsKey(propertyName)) {
                        Object object = map.get(propertyName);
                        if (object != null) {
                            if (object instanceof String) {
                                object = stringConvertTo(object.toString(), descriptor.getPropertyType());
                            }

                            descriptor.getWriteMethod().invoke(bean, object);
                        }
                    }
                }

                return bean;
            } catch (Exception var8) {
                throw new BeanConverter.BeanConverterException(var8);
            }
        } else {
            return null;
        }
    }

    public static Map<String, Object> beanToMap(Object bean) {
        HashMap returnMap = new HashMap();

        try {
            Map<String, PropertyDescriptor> descriptors = getCachePropertyDescriptors(bean.getClass());
            Iterator var4 = descriptors.values().iterator();

            while(var4.hasNext()) {
                PropertyDescriptor descriptor = (PropertyDescriptor)var4.next();
                String propertyName = descriptor.getName();
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    returnMap.put(propertyName, result);
                }
            }

            return returnMap;
        } catch (Exception var8) {
            throw new BeanConverter.BeanConverterException(var8);
        }
    }

    private static Map<String, PropertyDescriptor> getCachePropertyDescriptors(Class<?> clazz) throws IntrospectionException {
        String canonicalName = clazz.getCanonicalName();
        Map<String, PropertyDescriptor> map = (Map)propertyDescriptorCache.get(canonicalName);
        if (map == null) {
            map = doCacheClass(clazz, canonicalName);
        }

        return map;
    }

    public static List<String> getClassFields(Class<?> clazz) throws IntrospectionException {
        String canonicalName = clazz.getCanonicalName();
        if (!fieldCache.containsKey(canonicalName)) {
            doCacheClass(clazz, canonicalName);
        }

        return (List)fieldCache.get(canonicalName);
    }

    public static Method getWriteMethod(Class<?> clazz, String fieldName) {
        try {
            String canonicalName = clazz.getCanonicalName();
            if (!propertyDescriptorCache.containsKey(canonicalName)) {
                doCacheClass(clazz, canonicalName);
            }

            if (!propertyDescriptorCache.containsKey(canonicalName)) {
                return null;
            } else {
                PropertyDescriptor propertyDescriptor = (PropertyDescriptor)((Map)propertyDescriptorCache.get(canonicalName)).get(fieldName);
                return propertyDescriptor == null ? null : propertyDescriptor.getWriteMethod();
            }
        } catch (Exception var4) {
            throw new BeanConverter.BeanConverterException(var4);
        }
    }

    private static synchronized Map<String, PropertyDescriptor> doCacheClass(Class<?> clazz, String canonicalName) throws IntrospectionException {
        if (propertyDescriptorCache.containsKey(canonicalName)) {
            return (Map)propertyDescriptorCache.get(canonicalName);
        } else {
            Map<String, PropertyDescriptor> map = new ConcurrentHashMap();
            List<String> fieldNames = new ArrayList();
            BeanInfo srcBeanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] descriptors = srcBeanInfo.getPropertyDescriptors();
            PropertyDescriptor[] var9 = descriptors;
            int var8 = descriptors.length;

            for(int var7 = 0; var7 < var8; ++var7) {
                PropertyDescriptor descriptor = var9[var7];
                String name = descriptor.getName();
                fieldNames.add(name);
                Method readMethod = descriptor.getReadMethod();
                Method writeMethod = descriptor.getWriteMethod();
                if (readMethod == null) {
                    try {
                        readMethod = clazz.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                        descriptor.setReadMethod(readMethod);
                    } catch (SecurityException | NoSuchMethodException var14) {
                    }
                }

                if (writeMethod == null) {
                    try {
                        writeMethod = clazz.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1), descriptor.getPropertyType());
                        descriptor.setWriteMethod(writeMethod);
                    } catch (SecurityException | NoSuchMethodException var15) {
                    }
                }

                if (readMethod != null && writeMethod != null) {
                    map.put(descriptor.getName(), descriptor);
                }
            }

            propertyDescriptorCache.put(canonicalName, map);
            fieldCache.put(canonicalName, fieldNames);
            return map;
        }
    }

    private static Object stringConvertTo(String value, Class<?> propertyType) {
        Object result = null;
        if (propertyType == BigDecimal.class) {
            result = new BigDecimal(value);
        } else if (propertyType != Byte.TYPE && propertyType != Byte.class) {
            if (propertyType != Short.TYPE && propertyType != Short.class) {
                if (propertyType != Integer.TYPE && propertyType != Integer.class) {
                    if (propertyType != Double.TYPE && propertyType != Double.class) {
                        if (propertyType == Date.class) {
                            if (value != null) {
                                result = DateUtils.parseDate(value);
                            }
                        } else if (propertyType == Boolean.TYPE || propertyType == Boolean.class) {
                            result = Boolean.parseBoolean(value);
                        }
                    } else {
                        result = Double.valueOf(value.toString());
                    }
                } else {
                    result = Integer.parseInt(value);
                }
            } else {
                result = Short.valueOf(value.toString());
            }
        } else {
            result = Byte.valueOf(value);
        }

        return result;
    }

    public static boolean isSimpleDataType(Object o) {
        Class<? extends Object> clazz = o.getClass();
        return clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Byte.class) || clazz.equals(Long.class) || clazz.equals(Double.class) || clazz.equals(Float.class) || clazz.equals(Character.class) || clazz.equals(Short.class) || clazz.equals(BigDecimal.class) || clazz.equals(Boolean.class) || clazz.equals(Date.class) || clazz.isPrimitive();
    }

    /*public static void main(String[] args) {
        boolean isImplementInterface = BaseEnum.class.isAssignableFrom(AppType.class);
    }*/

    public static class BeanConverterException extends RuntimeException {
        private static final long serialVersionUID = 152873897614690397L;

        public BeanConverterException(Throwable cause) {
            super(cause);
        }
    }
}
