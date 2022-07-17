package com.penyo.contacts.items;

import java.io.Serializable;

/**
 * {@code Email} 类用于包装电子邮箱地址。
 * 包含电子邮箱地址 {@code email} 和注释 {@code remark} 。
 * 
 * <p>
 * 一般在一个 {@link Person} 对象具有多个电子邮箱时才会用到注释来区分。
 * 
 * @author Penyo
 * @see Person
 * @see Tel
 */
public class Email implements Serializable {
    /**
     * 序列化校验码
     */
    private static final long serialVersionUID = com.penyo.contacts.Main.USVUID;

    /**
     * 电子邮箱地址。
     */
    private String email;

    /**
     * 电子邮箱地址所对应的注释。
     */
    private String remark;

    /**
     * 该构造器用于接受电子邮箱地址为参数以实例化。
     * 
     * @param email 电子邮箱地址。
     */
    public Email(String email) {
        this.email = email;
    }

    /**
     * 该构造器用于接受电子邮箱地址和它所对应的注释为参数以实例化。
     * 
     * @param email  电子邮箱地址。
     * @param remark 电子邮箱地址所对应的注释。
     */
    public Email(String email, String remark) {
        this.email = email;
        this.remark = remark;
    }

    /**
     * 该方法用于获取电子邮箱地址。
     * 
     * @return 电子邮箱地址。
     */
    public String getEmail() {
        return email;
    }

    /**
     * 该方法用于设置电子邮箱地址。
     * 
     * @param email 电子邮箱地址。
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 该方法用于获取电子邮箱地址所对应的注释。
     * 
     * @return 电子邮箱地址所对应的注释。
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 该方法用于设置电子邮箱地址所对应的注释。
     * 
     * @param remark 电子邮箱地址所对应的注释。
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        if (remark == null)
            return email;
        return email + "（" + remark + "）";
    }
}
