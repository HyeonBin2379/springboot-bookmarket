package org.project.springbootbookmarket.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class MonitoringInterceptor implements HandlerInterceptor {

    ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        StopWatch stopWatch = new StopWatch(handler.toString());

        stopWatch.start(handler.toString());
        stopWatchLocal.set(stopWatch);
        log.info("접근한 URL 경로: {}", getURLPath(request));
        log.info("요청 처리 시작 시각 : {}", getCurrentTime());

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest arg0,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {
        log.info("요청 처리 종료 시각 : {}", getCurrentTime());
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception exception) throws Exception {
        StopWatch stopWatch = stopWatchLocal.get();
        stopWatch.stop();

        log.info("요청 처리 소요 시간 : {} ms", stopWatch.getTotalTimeMillis());
        stopWatchLocal.remove();
        log.info("==================================");
    }

    private String getURLPath(HttpServletRequest request) {
        String currentPath = request.getRequestURI();
        String queryString = request.getQueryString();

        queryString = queryString == null ? "" : "?" + queryString;

        return currentPath + queryString;
    }

    private String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());

        return formatter.format(calendar.getTime());
    }
}
