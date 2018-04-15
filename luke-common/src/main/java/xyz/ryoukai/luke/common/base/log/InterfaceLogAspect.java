/*
 * Copyright 2018 ryoukai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.ryoukai.luke.common.base.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import xyz.ryoukai.luke.common.util.DateUtil;

import java.lang.reflect.Method;

/**
 * <p>
 * 服务接口日志切面处理类
 * </p> 
 *
 * @author ryoukai
 * @date 2018/4/15 20:12
 * @since V1.0
 */
@Slf4j
class InterfaceLogAspect {

    @Pointcut("@annotation(xyz.ryoukai.luke.common.annotation.InterfaceLog)")
    public void interfaceLogPointCut(){};

    @Before("interfaceLogPointCut()")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("{}() 方法开始执行,参数{}|======|开始时间：{}",
                method.getName(), JSON.toJSONString(joinPoint.getArgs()), DateUtil.getDate());
    }

    @AfterReturning(pointcut = "interfaceLogPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("{}() 方法执行结束,返回值{}|======|结束时间：{}",
                method.getName(), result, DateUtil.getDate());
    }
}
