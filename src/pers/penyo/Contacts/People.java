package pers.penyo.Contacts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.TreeSet;

import pers.penyo.Contacts.Items.Email;
import pers.penyo.Contacts.Items.Person;
import pers.penyo.Contacts.Items.Tel;

/**
 * {@code People} 是 {@link Person} 类的集合（本质是按照姓名顺序排序的 {@link TreeSet}
 * ）表达，并集成了一些操作方法。该类在初始化时，会自动调用 {@link #initialize()} 方法以尝试读取本地的通讯录文件。
 * 
 * <p>
 * 我们已按照程序的实际需要重写了 {@link Collection} 接口的部分方法，你可以直接调用它们。
 * 
 * @author Penyo
 * @see Person
 */
public class People {
    /**
     * 通讯录数据文件在本地的位置。
     */
    private File contacts = new File("pers\\penyo\\Contacts\\Contacts.oos");

    /**
     * {@link Person} 类的集合（本质是按照姓名顺序排序的 {@link TreeSet} ）。
     */
    private TreeSet<Person> people = new TreeSet<>((p1, p2) -> {
        return Collator.getInstance(Locale.CHINA).compare(p1.getName(), p2.getName());
    });

    /**
     * 该构造器用于在本地生成通讯录数据文件（如果不存在），并调用 {@link #initialize()} 方法。
     */
    public People() {
        try {
            contacts.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initialize();
    }

    /**
     * 该方法用于从本地读取 {@code Contacts.oos} 文件，并载入到集合中。
     * 如果本地文件不可用，则集合默认为空。
     * 
     * @see #save()
     */
    @SuppressWarnings("unchecked")
    private void initialize() {
        try (ObjectInputStream contacts = new ObjectInputStream(new FileInputStream(this.contacts))) {
            people.addAll((ArrayList<Person>) contacts.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 该方法用于从集合中读取数据，并向本地写入 {@code Contacts.oos} 文件。
     * 
     * @see #initialize()
     */
    private void save() {
        try (ObjectOutputStream contacts = new ObjectOutputStream(new FileOutputStream(this.contacts))) {
            contacts.writeObject(new ArrayList<>(people));
        } catch (Exception e) {
            e.printStackTrace();
        }
        initialize();
    }

    /**
     * 该方法用于向集合添加元素。
     * 
     * @param p {@link Person} 的对象。
     */
    public People add(Person p) {
        if (p.getTel().size() > 0 || p.getEmail().size() > 0)
            people.add(p);
        save();
        return this;
    }

    /**
     * 该方法用于移除集合中的元素。与 {@link Collection} 中的 {@code remove()}
     * 方法不同，该方法只需要接受姓名就可以移除集合中对应的元素。
     * 
     * @param name 姓名。
     * @return 移除的结果。
     */
    public People remove(String name) {
        for (Person p : people)
            if (p.getName().equals(name)){
                people.remove(p);
                break;
            }
        save();
        return this;
    }

    /**
     * 该方法用于返回集合的尺寸。
     * 
     * @return 集合的尺寸。
     */
    public int size() {
        return people.size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int target = 0;
        for (Person p : people) {
            str.append(p.getName() + "\n");

            target = 0;
            for (Tel t : p.getTel()) {
                str.append(t + "\t");
                target++;
            }
            if (target != 0)
                str.append("\n");

            target = 0;
            for (Email e : p.getEmail()) {
                str.append(e + "\t");
                target++;
            }
            if (target != 0)
                str.append("\n");

            str.append("\n");
        }
        return str.toString();
    }
}
