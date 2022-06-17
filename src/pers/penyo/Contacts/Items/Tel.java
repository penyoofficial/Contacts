package pers.penyo.Contacts.Items;

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
    private static final long serialVersionUID = pers.penyo.Contacts.Main.USVUID;

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
        this.tel = tel;
    }

    /**
     * 该构造器用于接受电话号码和它所对应的注释为参数以实例化。
     * 
     * @param tel    电话号码。
     * @param remark 电话号码所对应的注释。
     */
    public Tel(String tel, String remark) {
        this.tel = tel;
        this.remark = remark;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRemark() {
        return remark;
    }

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
