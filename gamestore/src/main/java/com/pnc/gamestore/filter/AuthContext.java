package com.pnc.gamestore.filter;

import com.pnc.gamestore.model.User;

public class AuthContext {

    private static final ThreadLocal<User> holder = new ThreadLocal<>();

    public static void set(User user) {
        holder.set(user);
    }

    public static User getUser() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }
}
