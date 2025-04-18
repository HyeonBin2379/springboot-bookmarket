package org.project.springbootbookmarket.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class AuditingInterceptor implements HandlerInterceptor {

    private String user;
    private String bookId;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getRequestURI().endsWith("/books/add") && request.getMethod().equals("POST")) {
            user = request.getRemoteUser();
            bookId = request.getParameterValues("bookId")[0];
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (request.getRequestURI().endsWith("/books/add")) {
            log.warn(String.format("신규등록 도서 ID : %s, 접근자 : %s, 접근시각 : %s", bookId, user, getCurrentTime()));
        }
    }

    private String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());

        return formatter.format(calendar.getTime());
    }
}
