package com.albert.toolkit.db.aspect;

import com.albert.toolkit.db.annotation.DbSource;
import com.albert.toolkit.db.dynamicds.DynamicDatasourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class DynamicDatasourceAspect {
    @Pointcut(value = "@annotation(com.albert.toolkit.db.annotation.DbSource)")
    public void pointCut() {
    }

    /**
     * 方法执行前切换数据源
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DbSource dbSource = methodSignature.getMethod().getAnnotation(DbSource.class);
        DynamicDatasourceContextHolder.setDatasourceKey(dbSource.value());
    }

    /**
     * 方法执行后切回数据源
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @After("pointCut()")
    public void after(JoinPoint joinPoint) throws Throwable {
        DynamicDatasourceContextHolder.removeContextKey();
    }

}
