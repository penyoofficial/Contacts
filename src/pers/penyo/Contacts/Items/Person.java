package pers.penyo.Contacts.Items;

import java.io.Serializable;
import java.util.*;

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
    private static final long serialVersionUID = pers.penyo.Contacts.Main.USVUID;

    /**
     * 姓名。
     */
    private String name;

    /**
     * 电话号码的集合。
     */
    private ArrayList<Tel> tel = new ArrayList<>();

    /**
     * 电子邮箱地址的集合。
     */
    private ArrayList<Email> email = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    /***/
    public Person(String name, String tel) {
        this.name = name;
        this.tel.add(new Tel(tel));
    }

    /***/
    public Person(String name, Tel tel) {
        this.name = name;
        this.tel.add(tel);
    }

    /***/
    public Person(String name, Email email) {
        this.name = name;
        this.email.add(email);
    }

    /***/
    public Person(String name, String tel, String email) {
        this.name = name;
        this.tel.add(new Tel(tel));
        this.email.add(new Email(email));
    }

    /***/
    public Person(String name, String tel, Email email) {
        this.name = name;
        this.tel.add(new Tel(tel));
        this.email.add(email);
    }

    /***/
    public Person(String name, Tel tel, String email) {
        this.name = name;
        this.tel.add(tel);
        this.email.add(new Email(email));
    }

    /***/
    public Person(String name, Tel tel, Email email) {
        this.name = name;
        this.tel.add(tel);
        this.email.add(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Tel> getTel() {
        return tel;
    }

    public void setTel(ArrayList<Tel> tel) {
        this.tel = tel;
    }

    public ArrayList<Email> getEmail() {
        return email;
    }

    public void setEmail(ArrayList<Email> email) {
        this.email = email;
    }

    /***/
    public Person addTel(String tel) {
        this.tel.add(new Tel(tel));
        return this;
    }

    /***/
    public Person addTel(Tel tel) {
        this.tel.add(tel);
        return this;
    }

    /***/
    public Person addEmail(String email) {
        this.email.add(new Email(email));
        return this;
    }

    /***/
    public Person addEmail(Email email) {
        this.email.add(email);
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((tel == null) ? 0 : tel.hashCode());
        return result;
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