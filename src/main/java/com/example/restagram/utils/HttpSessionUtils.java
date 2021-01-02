package com.example.restagram.utils;

import com.example.restagram.domain.users.Users;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public final static String USER_SESSION_KEY="sessionedUser";

    public static boolean isLoginUser(HttpSession session) {
        Object sessionedUser=session.getAttribute(USER_SESSION_KEY);

        if(sessionedUser==null) {
            return false;
        }
        return true;
    }

    public static Users getUserFromSession(HttpSession session) {
//        if(!isLoginUser(session)) {
//            return null;
//        }
        return (Users)session.getAttribute(USER_SESSION_KEY);
    }
}
