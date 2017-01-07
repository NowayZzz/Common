package com.util.缓存.memcache;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.danga.MemCached.MemCachedClient;
import com.util.辅助类.MD5Util;
import com.util.辅助类.MemcachedUtil;

@Aspect
public class MethodCacheAspectJ {

	private static final Log log = Logs.get();

	@Pointcut("@annotation(com.noway.cache.MethodCache)")
	public void methodCachePointcut() {

	}

	@Around("methodCachePointcut()")
	public Object methodCacheHold(ProceedingJoinPoint joinPoint) throws Throwable {

		final String targetName = joinPoint.getTarget().getClass().getName();
		final String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Object result = null;
		Object cacheObject = null;
		final String cacheKey = getCacheKey(targetName, methodName, arguments);

		if (log.isDebugEnabled()) {
			log.debugf("方法的结果 (%s)将保存到缓存 key(%s)中 ", methodName, cacheKey);
		}

		MemCachedClient client = MemcachedUtil.getInstance().client();
		cacheObject = client.get(cacheKey);

		if (cacheObject != null) {
			log.debug("结果直接从缓存取得 ");
			return cacheObject;
		}

		// 缓存为空时
		try {
			if (log.isDebugEnabled()) {
				log.debugf("没有值key(%s)", cacheKey);
			}
			// 进行原来的方法
			result = joinPoint.proceed();
		} catch (Exception e) {
			System.err.println("缓存获取结果出错");
			log.error(e, e);
		}

		// 结果不为空，将它放到缓存里
		if (result != null) {
			try {
				Class targetClass = Class.forName(targetName);
				Method[] method = targetClass.getMethods();
				int second = 0;
				for (Method m : method) {

					// 如果没有注释
					if (!m.isAnnotationPresent(MethodCache.class)) {
						continue;
					}

					// 如果方法名不一样
					if (!m.getName().equals(methodName)) {
						continue;
					}

					// 参数个数相同
					if (m.getParameterTypes().length != arguments.length) {
						continue;
					}

					// 得到秒数
					MethodCache methodCache = m.getAnnotation(MethodCache.class);

					second = methodCache.second();
					break;
				}

				if (log.isDebugEnabled()) {
					log.debugf("Put to the Cache, %d seconds ", second);
				}
				// 毫秒数大于0时
				if (second > 0) {
					client.set(cacheKey, result, new java.util.Date(1000 * second));
				} else {
					client.set(cacheKey, result);
				}

				if (log.isDebugEnabled()) {
					log.debug("加入到缓存，稳稳的~");
				}

			} catch (Exception e) {
				log.error("!!!!!!!!!" + cacheKey + "!!!!!!!!!未能执行方法缓存" + e, e);
			}
		}

		return result;

	}

	private String getCacheKey(String targetName, String methodName, Object[] arguments) {

		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);

		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {

				Object obj = arguments[i];
				if (obj != null) {
					if (obj.getClass().isArray()) {
						Object[] objs = (Object[]) obj;
						for (int j = 0; j < objs.length; j++) {
							if (objs[j] instanceof java.util.Date) {
								sb.append(".").append(((java.util.Date) objs[j]).getTime());
							} else {
								sb.append(".").append(objs[j]);
							}
						}

					} else {
						if (arguments[i] instanceof java.util.Date) {
							sb.append(".").append(((java.util.Date) arguments[i]).getTime());
						} else {
							sb.append(".").append(arguments[i]);
						}
					}
				} else {
					sb.append(".").append(arguments[i]);
				}

			}
		}
		String before = sb.toString();
		String md5 = MD5Util.md5(before);
		// log.debugf("md5后%s,前%s", md5, before);

		return md5;
	}
}
