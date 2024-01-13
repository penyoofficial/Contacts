package net.penyo.contacts.po;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code Contacts} 是 {@link Contact} 类的集合（本质是按照姓名顺序排序的 {@link TreeSet}
 * ）表达，并集成了一些常用方法。
 *
 * @author Penyo
 * @see Contact
 */
public class Contacts extends TreeSet<Contact> implements PersistentObject {
  /**
   * 默认对应文件路径
   */
  private static final String defaultFilePath = "src/main/resources/contacts.vcf";
  /**
   * 对应文件路径
   */
  private String filePath = defaultFilePath;

  public Contacts() {
  }

  public Contacts(String vcf) {
    String[] contactsRaw = vcf.split("BEGIN:VCARD");
    for (String contactRaw : contactsRaw) {
      String[] lines = contactRaw.split("\n");
      Contact contact = new Contact();
      for (String line : lines) {
        if (line.startsWith("FN")) {
          Matcher matcher = Pattern.compile("FN:(.+)").matcher(line);
          while (matcher.find()) {
            String name = matcher.group(1);
            contact.setName(name);
          }
        } else if (line.startsWith("TEL")) {
          Matcher matcher = Pattern.compile("TEL;(TYPE=(.+);?)+:(.+)").matcher(line);
          while (matcher.find()) {
            String type = matcher.group(2).split(";")[0];
            String tel = matcher.group(3);
            contact.addTel(tel, type);
          }
        } else if (line.startsWith("EMAIL")) {
          Matcher matcher = Pattern.compile("EMAIL;(TYPE=(.+);?)+:(.+)").matcher(line);
          while (matcher.find()) {
            String type = matcher.group(2).split(";")[0];
            String email = matcher.group(3);
            contact.addEmail(email, type);
          }
        }
      }
      if (contact.getName() != null) this.add(contact);
    }
  }

  /**
   * 获取文件路径。
   */
  public String getFilePath() {
    return filePath;
  }

  /**
   * 设置文件路径。
   *
   * @param filePath 文件路径
   */
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  /**
   * 从类路径读取 {@code contacts.vcf} 文件，并载入到集合中。
   * 如果本地文件不可用，则集合默认为空。
   *
   * @see #load(String)
   * @see #save()
   */
  public static Contacts load() {
    return load(defaultFilePath);
  }

  /**
   * 从指定路径读取文件，并载入到集合中。
   * 如果本地文件不可用，则集合默认为空。
   *
   * @see #save()
   */
  public static Contacts load(String filePath) {
    try (FileReader reader = new FileReader(filePath, StandardCharsets.UTF_8)) {
      StringBuilder raw = new StringBuilder();
      char[] c = new char[1024];
      while (reader.read(c) != -1) raw.append(new String(c));

      Contacts contacts = new Contacts(raw.toString());
      contacts.setFilePath(filePath);
      return contacts;
    } catch (Exception ignored) {
    }
    return new Contacts();
  }

  /**
   * 从集合中读取数据，并向类路径 {@code contacts.vcf} 文件写入。
   *
   * @see #load()
   */
  public void save() {
    try (FileWriter writer = new FileWriter(filePath, StandardCharsets.UTF_8)) {
      writer.write(toString());
    } catch (Exception ignored) {
    }
  }

  /**
   * 向集合添加元素。
   *
   * @param contact 联系人
   */
  public boolean add(Contact contact) {
    return super.add(contact);
  }

  /**
   * 根据姓名，从集合移除元素。
   *
   * @param name 姓名
   */
  public boolean remove(String name) {
    AtomicBoolean flag = new AtomicBoolean(false);
    this.forEach((c) -> {
      if (c.getName().equals(name)) flag.set(remove(c));
    });
    return flag.get();
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (Contact contact : this) {
      str.append("BEGIN:VCARD\nVERSION:3.0\n");
      str.append("FN:").append(contact.getName()).append("\n");
      contact.getTels().forEach((k, v) -> str.append("TEL;TYPE=").append(v.isEmpty() ? "CELL" : v).append(":").append(k).append("\n"));
      contact.getEmails().forEach((k, v) -> str.append("EMAIL;TYPE=").append(v.isEmpty() ? "WORK" : v).append(":").append(k).append("\n"));
      str.append("END:VCARD\n");
    }
    return str.toString();
  }
}
