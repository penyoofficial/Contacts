package com.penyo.contacts.items;

import java.io.Serializable;

/**
 * {@code Tel} 类用于包装电话号码。
 * 包含电话号码 {@code tel} 和注释 {@code remark} 。
 * 
 * <p>
 * 一般在一个 {@link Person} 对象具有多个电话号码时才会用到注释来区分。
 * 
 * @author Penyo
 * @see Email
 * @see Person
 */
public class Tel implements Serializable {
    /**
     * 序列化校验码
     */
    private static final long serialVersionUID = com.penyo.contacts.Main.USVUID;

    /**
     * 电话号码。
     */
    private String tel;

    /**
     * 电话号码所对应的注释。
     */
    private String remark;

    /**
     * 该构造器用于接受电话号码为参数以实例化。
     * 
     * @param tel 电话号码。
     */
    public Tel(String tel) {
        if (tel.matches("1[3-9]\\d{9}"))
            tel = tel.substring(0, 3) + " " + tel.substring(3, 7) + " " + tel.substring(7);
        this.tel = tel;
    }

    /**
     * 该构造器用于接受电话号码和它所对应的注释为参数以实例化。
     * 
     * @param tel    电话号码。
     * @param remark 电话号码所对应的注释。
     */
    public Tel(String tel, String remark) {
        if (tel.matches("1[3-9]\\d{9}"))
            tel = tel.substring(0, 3) + " " + tel.substring(3, 7) + " " + tel.substring(7);
        this.tel = tel;
        this.remark = remark;
    }

    /**
     * 该方法用于获取电话号码。
     * 
     * @return 电话号码。
     */
    public String getTel() {
        return tel;
    }

    /**
     * 该方法用于设置电话号码。
     * 
     * @param tel 电话号码。
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 该方法用于获取电话号码所对应的注释。
     * 
     * @return 电话号码所对应的注释。
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 该方法用于设置电话号码所对应的注释。
     * 
     * @param remark 电话号码所对应的注释。
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        if (remark == null)
            return tel.toString();
        return tel + "（" + remark + "）";
    }
}
