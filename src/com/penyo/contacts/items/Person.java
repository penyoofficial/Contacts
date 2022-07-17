package com.penyo.contacts.items;

import java.io.Serializable;
import java.util.TreeSet;

/**
 * {@code Person} 类是对个体的抽象表达。
 * 它包含有一个姓名 {@code name} 和可变数量的电话号码 {@code tel} 和电子邮箱地址 {@code email} 。
 * 
 * <p>
 * 构造器只考虑了<strong>初始传参</strong>姓名、最多一个电话号码或和电子邮箱地址的情况。
 * 你可以使用 {@link #addTel(long)} 和 {@link #addEmail(String)}
 * 等方法追加电话号码或和电子邮箱地址，且这些方法支持<strong>链式调用</strong>。
 * 
 * @author Penyo
 * @see Email
 * @see Tel
 */
public class Person implements Serializable {
    /**
     * 序列化校验码
     */
    private static final long serialVersionUID = com.penyo.contacts.Main.USVUID;

    /**
     * 姓名。
     */
    private String name;

    /**
     * 电话号码包装类的集合。
     */
    private TreeSet<Tel> tel = new TreeSet<>((t1, t2) -> {
        return (t1.getTel()).compareTo(t2.getTel());
    });

    /**
     * 电子邮箱地址包装类的集合。
     */
    private TreeSet<Email> email = new TreeSet<>((e1, e2) -> {
        return (e1.getEmail()).compareTo(e2.getEmail());
    });

    public Person() {
    }

    /**
     * 该构造器用于接受姓名为参数以实例化。
     * 
     * @param name 姓名。
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * 该构造器用于接受姓名和电话号码为参数以实例化。
     * 
     * @param name 姓名。
     * @param tel  电话号码。
     */
    public Person(String name, String tel) {
        this.name = name;
        this.tel.add(new Tel(tel));
    }

    /**
     * 该构造器用于接受姓名和 {@link Tel} 对象为参数以实例化。
     * 
     * @param name 姓名。
     * @param tel  {@link Tel} 对象。
     */
    public Person(String name, Tel tel) {
        this.name = name;
        this.tel.add(tel);
    }

    /**
     * 该构造器用于接受姓名和 {@link Email} 对象为参数以实例化。
     * 
     * @param name  姓名。
     * @param email {@link Email} 对象。
     */
    public Person(String name, Email email) {
        this.name = name;
        this.email.add(email);
    }

    /**
     * 该构造器用于接受姓名、电话号码和电子邮箱地址为参数以实例化。
     * 
     * @param name  姓名。
     * @param tel   电话号码。
     * @param email 电子邮箱地址。
     */
    public Person(String name, String tel, String email) {
        this.name = name;
        this.tel.add(new Tel(tel));
        this.email.add(new Email(email));
    }

    /**
     * 该构造器用于接受姓名、电话号码和 {@link Email} 对象为参数以实例化。
     * 
     * @param name  姓名。
     * @param tel   电话号码。
     * @param email {@link Email} 对象。
     */
    public Person(String name, String tel, Email email) {
        this.name = name;
        this.tel.add(new Tel(tel));
        this.email.add(email);
    }

    /**
     * 该构造器用于接受姓名、 {@link Tel} 对象和电子邮箱地址为参数以实例化。
     * 
     * @param name  姓名。
     * @param tel   {@link Tel} 对象。
     * @param email 电子邮箱地址。
     */
    public Person(String name, Tel tel, String email) {
        this.name = name;
        this.tel.add(tel);
        this.email.add(new Email(email));
    }

    /**
     * 该构造器用于接受姓名、 {@link Tel} 对象和 {@link Email} 对象为参数以实例化。
     * 
     * @param name  姓名。
     * @param tel   {@link Tel} 对象。
     * @param email {@link Email} 对象。
     */
    public Person(String name, Tel tel, Email email) {
        this.name = name;
        this.tel.add(tel);
        this.email.add(email);
    }

    /**
     * 该方法用于获取姓名。
     */
    public String getName() {
        return name;
    }

    /**
     * 该方法用于设置姓名。
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 该方法用于获取电话号码包装类的集合。
     * 
     * @return 电话号码包装类的集合。
     */
    public TreeSet<Tel> getTel() {
        return tel;
    }

    /**
     * 该方法用于设置电话号码包装类的集合。
     * 
     * @param tel 电话号码包装类的集合。
     */
    public void setTel(TreeSet<Tel> tel) {
        this.tel = tel;
    }

    /**
     * 该方法用于获取电子邮箱地址包装类的集合。
     * 
     * @return 电子邮箱地址包装类的集合。
     */
    public TreeSet<Email> getEmail() {
        return email;
    }

    /**
     * 该方法用于设置电子邮箱地址包装类的集合。
     * 
     * @param email 电子邮箱地址包装类的集合。
     */
    public void setEmail(TreeSet<Email> email) {
        this.email = email;
    }

    /**
     * 该方法用于向电话号码包装类的集合追加元素。
     * 
     * @param tel 电话号码。
     */
    public Person addTel(String tel) {
        this.tel.add(new Tel(tel));
        return this;
    }

    /**
     * 该方法用于向电话号码包装类的集合追加元素。
     * 
     * @param tel {@link Tel} 对象。
     */
    public Person addTel(Tel tel) {
        this.tel.add(tel);
        return this;
    }

    /**
     * 该方法用于向电子邮箱地址包装类的集合追加元素。
     * 
     * @param email 电子邮箱地址。
     */
    public Person addEmail(String email) {
        this.email.add(new Email(email));
        return this;
    }

    /**
     * 该方法用于向电子邮箱地址包装类的集合追加元素。
     * 
     * @param email {@link Email} 对象。
     */
    public Person addEmail(Email email) {
        this.email.add(email);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (tel == null) {
            if (other.tel != null)
                return false;
        } else if (!tel.equals(other.tel))
            return false;
        return true;
    }
}
