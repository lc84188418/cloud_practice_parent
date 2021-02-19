//package com.lc.homepage.config.aspect;
//
//
//import java.lang.reflect.Method;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.alibaba.fastjson.JSON;
//import com.lc.homepage.annotation.OperLog;
//import com.lc.homepage.service.TLogExceptionService;
//import com.lc.homepage.service.TLogRecordService;
//import com.lc.homepage.utils.IDUtils;
//import com.lc.homepage.utils.IPUtil;
//import com.lc.homepage.entity.TLogException;
//import com.lc.homepage.entity.TLogRecord;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//
//
///**
// * 切面处理类，操作日志异常日志记录处理
// *
// * @author liuchun
// */
//@Aspect
//@Component
//public class OperLogAspect {
//
//    /**
//     * 操作版本号
//     */
//    private static final String operVer = "2020-10-15";
//
//
//    @Autowired
//    private TLogRecordService tLogRecordService;
//
//    @Autowired
//    private TLogExceptionService tLogExceptionService;
//
//    /**
//     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
//     */
//    @Pointcut("@annotation(com.lc.homepage.annotation.OperLog)")
//    public void operLogPoinCut() {
//    }
//
//    /**
//     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
//     */
//    @Pointcut("execution(* com.lc.homepage.*.controller.*.*(..))")
//    public void operExceptionLogPoinCut() {
//    }
//
//    /**
//     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
//     *
//     * @param joinPoint 切入点
//     * @param keys      返回结果
//     */
//    @AfterReturning(value = "operLogPoinCut()", returning = "keys")
//    public void saveOperLog(JoinPoint joinPoint, Object keys) {
//        // 获取RequestAttributes
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        // 从获取RequestAttributes中获取HttpServletRequest的信息
//        HttpServletRequest request = (HttpServletRequest) requestAttributes
//                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        TLogRecord logRecord = new TLogRecord();
//        try {
//            logRecord.setPkRecordId(IDUtils.getUUID()); // 主键ID
//            // 从切面织入点处通过反射机制获取织入点处的方法
//            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//            // 获取切入点所在的方法
//            Method method = signature.getMethod();
//            // 获取操作
//            OperLog opLog = method.getAnnotation(OperLog.class);
//            if (opLog != null) {
//                String operModul = opLog.operModul();
//                String operType = opLog.operType();
//                String operDesc = opLog.operDesc();
//                logRecord.setRecordModule(operModul); // 操作模块
//                logRecord.setRecordType(operType); // 操作类型
//                logRecord.setRecordDesc(operDesc); // 操作描述
//            }
//            // 获取请求的类名
//            String className = joinPoint.getTarget().getClass().getName();
//            // 获取请求的方法名
//            String methodName = method.getName();
//            methodName = className + "." + methodName;
//
//            logRecord.setRecordMethod(methodName); // 请求方法
//            // 请求的参数
//            Map<String, String> rtnMap = converMap(request.getParameterMap());
//            // 将参数所在的数组转换成json
//            String params = JSON.toJSONString(rtnMap);
//            logRecord.setRecordRequParam(params); // 请求参数
//            logRecord.setRecordRespParam(JSON.toJSONString(keys)); // 返回结果
////            logRecord.setRecordUserId(UserShiroUtil.getCurrentUserId(request)); // 请求用户ID
////            logRecord.setRecordUserName(UserShiroUtil.getCurrentUserName(request)); // 请求用户名称
//            logRecord.setRecordIp(IPUtil.getIpAddress(request)); // 请求IP
//            logRecord.setRecordUrl(request.getRequestURI()); // 请求URI
//            logRecord.setRecordTime(new Date()); // 创建时间
//            logRecord.setRecordVersion(operVer); // 操作版本
//            tLogRecordService.insert(logRecord);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
//     *
//     * @param joinPoint 切入点
//     * @param e         异常信息
//     */
//    @AfterThrowing(pointcut = "operExceptionLogPoinCut()", throwing = "e")
//    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
//        // 获取RequestAttributes
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        // 从获取RequestAttributes中获取HttpServletRequest的信息
//        HttpServletRequest request = (HttpServletRequest) requestAttributes
//                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        TLogException excepLog = new TLogException();
//        try {
//            // 从切面织入点处通过反射机制获取织入点处的方法
//            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//            // 获取切入点所在的方法
//            Method method = signature.getMethod();
//            excepLog.setPkExcId(IDUtils.getUUID());
//            // 获取请求的类名
//            String className = joinPoint.getTarget().getClass().getName();
//            // 获取请求的方法名
//            String methodName = method.getName();
//            methodName = className + "." + methodName;
//            // 请求的参数
//            Map<String, String> rtnMap = converMap(request.getParameterMap());
//            // 将参数所在的数组转换成json
//            String params = JSON.toJSONString(rtnMap);
//            excepLog.setExcRequParam(params); // 请求参数
//            excepLog.setOperMethod(methodName); // 请求方法名
//            excepLog.setExcName(e.getClass().getName()); // 异常名称
//            excepLog.setExcMsg(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())); // 异常信息
////            excepLog.setOperUserId(UserShiroUtil.getCurrentUserId(request)); // 操作员ID
////            excepLog.setOperUserName(UserShiroUtil.getCurrentUserName(request)); // 操作员名称
//            excepLog.setOperUrl(request.getRequestURI()); // 操作URl
//            excepLog.setOperIp(IPUtil.getIpAddress(request)); // 操作员IP
//            excepLog.setOperVersion(operVer); // 操作版本号
//            excepLog.setExcTime(new Date()); // 发生异常时间
//            tLogExceptionService.insert(excepLog);
//        } catch (Exception e2) {
//            e2.printStackTrace();
//        }
//
//    }
//
//    /**
//     * 转换request 请求参数
//     *
//     * @param paramMap request获取的参数数组
//     */
//    public Map<String, String> converMap(Map<String, String[]> paramMap) {
//        Map<String, String> rtnMap = new HashMap<String, String>();
//        for (String key : paramMap.keySet()) {
//            rtnMap.put(key, paramMap.get(key)[0]);
//        }
//        return rtnMap;
//    }
//
//    /**
//     * 转换异常信息为字符串
//     * @param exceptionName    异常名称
//     * @param exceptionMessage 异常信息
//     * @param elements         堆栈信息
//     */
//    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
//        StringBuffer strbuff = new StringBuffer();
//        for (StackTraceElement stet : elements) {
//            strbuff.append(stet + "\n");
//        }
//        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
//        return message;
//    }
//}
//
