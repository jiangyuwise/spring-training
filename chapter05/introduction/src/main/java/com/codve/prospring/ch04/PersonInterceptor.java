package com.codve.prospring.ch04;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 引入可以扩展现有的方法, 也可以动态扩展接口和类
 * 引入可以视为环绕切片, 仅适用于类级别. 不可以与切入点同时使用
 * 标准 advisor 是一个实例对于多个对象; introduction advisor 是每个对象一个实例
 * 使用引入添加一个新功能: 检测属性是否发生修改
 */
public class PersonInterceptor extends DelegatingIntroductionInterceptor
        implements IsModified {
    private boolean isModified = false;
    // methodMap key 为 setter, value 为 getter, 作为缓存使用
    private Map<Method, Method> methodMap = new HashMap<>();

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if ((invocation.getMethod().getName().startsWith("set"))
                && (invocation.getArguments().length == 1)) {
            Method getter = getGetter(invocation.getMethod());
            Object[] arguments = invocation.getArguments();
            Object newValue = arguments[0];
            Object oldValue = getter.invoke(invocation.getThis());
            if (newValue == null && oldValue == null) {
                isModified = false;
            } else if (newValue == null && oldValue != null) {
                isModified = true;
            } else if (newValue != null && oldValue == null) {
                isModified = true;
            } else {
                isModified = !newValue.equals(oldValue);
            }
        }
        return super.invoke(invocation);
    }

    // 获取 getter
    private Method getGetter(Method setter) throws NoSuchMethodException {
        Method getter = methodMap.get(setter);
        if (getter != null) {
            return getter;
        }
        String getterName = setter.getName().replaceFirst("set", "get");
        getter = setter.getDeclaringClass().getMethod(getterName);
        synchronized (methodMap) {
            methodMap.put(setter, getter);
        }
        return getter;
    }
}
