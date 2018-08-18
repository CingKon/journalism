package com.journalism.common;

public class Const {

    public static final String CURRENT_USER = "currentUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_MANAGER = 1;//经理
        int ROLE_ADMIN = 2;//管理员
    }

    public enum ArticleStatus{
        UNPUBLISHED(0,"未发布"),
        APPLICATION_FOR_PUBLISH(10,"申请发布"),
        PUBLISHED(20,"已发布"),
        CANT_PUBLISH(30,"审核失败"),
        SHELVE(40,"其他处理")//搁置
        ;

        ArticleStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        private int code;
        private String desc;

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
