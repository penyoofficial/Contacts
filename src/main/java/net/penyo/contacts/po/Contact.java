package net.penyo.contacts.po;

import java.text.Collator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * {@code Contact} 类是对联系人的抽象。
 * 它包含有一个姓名 {@code name} 和电话号码 {@code tel} 与电子邮箱 {@code email} 的集合。
 *
 * <p>
 * 你可以使用如 {@link #addTel(String)} 或 {@link #addEmail(String)}
 * 的方法增加电话号码或电子邮箱，且所有非 {@code getter} 方法都支持<strong>链式调用</strong>。
 * </p>
 *
 * @author Penyo
 */
public class Contact implements PersistentObject, Comparable<Contact> {
  /**
   * 姓名
   */
  private String name;

  /**
   * 电话号码表
   *
   * <p>
   * 电话号码为键，其注释为值。
   * </p>
   */
  private final Map<String, String> tels = new TreeMap<>();

  /**
   * 电子邮箱表
   *
   * <p>
   * 电话号码为键，其注释为值。
   * </p>
   */
  private final Map<String, String> emails = new TreeMap<>();

  public Contact() {
  }

  /**
   * 获取姓名。
   */
  public String getName() {
    return name;
  }

  /**
   * 设置姓名。
   */
  public Contact setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * 获取电话号码表。
   */
  public Map<String, String> getTels() {
    return new TreeMap<>(tels);
  }

  /**
   * 增加电话号码。
   *
   * @param tel 电话号码
   */
  public Contact addTel(String tel) {
    tels.put(tel, "");
    return this;
  }

  /**
   * 增加电话号码。
   *
   * @param tel    电话号码
   * @param remark 注释
   */
  public Contact addTel(String tel, String remark) {
    if (remark == null) remark = "";
    tels.put(tel, remark);
    return this;
  }

  /**
   * 移除电话号码。
   *
   * @param tel 电话号码
   */
  public Contact removeTel(String tel) {
    tels.remove(tel);
    return this;
  }

  /**
   * 获取电子邮箱表。
   */
  public Map<String, String> getEmails() {
    return new TreeMap<>(emails);
  }

  /**
   * 增加电子邮箱。
   *
   * @param email 电子邮箱
   */
  public Contact addEmail(String email) {
    emails.put(email, "");
    return this;
  }

  /**
   * 增加电子邮箱。
   *
   * @param email  电子邮箱
   * @param remark 注释
   */
  public Contact addEmail(String email, String remark) {
    if (remark == null) remark = "";
    emails.put(email, remark);
    return this;
  }

  /**
   * 移除电子邮箱。
   *
   * @param email 电话号码
   */
  public Contact removeEmail(String email) {
    emails.remove(email);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Contact contact = (Contact) o;
    return Objects.equals(getName(), contact.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  @Override
  public int compareTo(Contact o) {
    return Collator.getInstance(Locale.PRC).compare(getName(), o.getName());
  }
}
