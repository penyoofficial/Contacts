package net.penyo.contacts;

import net.penyo.contacts.view.CompositionApplication;
import net.penyo.contacts.view.MainWindow;

/**
 * <strong>欢迎使用通讯录！</strong>
 *
 * <p>
 * 应用程序从这里启动。
 * </p>
 *
 * @author Penyo
 */
public class ContactsApplication {
  public static void main(String[] args) {
    CompositionApplication.run(MainWindow.class);
  }
}
