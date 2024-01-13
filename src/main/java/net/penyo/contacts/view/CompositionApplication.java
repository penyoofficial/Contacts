package net.penyo.contacts.view;

import java.lang.reflect.InvocationTargetException;

/**
 * {@code CompositionApplication} 类定义了组合式图形化应用程序的生命周期。
 *
 * @author Penyo
 */
public interface CompositionApplication {
  /**
   * 运行应用程序。
   */
  static <MyApplication extends CompositionApplication> void run(Class<MyApplication> clazz) {
    try {
      CompositionApplication app = clazz.getDeclaredConstructor().newInstance();
      app.loadStructure();
      app.loadStyle();
      app.loadAction();
    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
             IllegalAccessException ignored) {
    }
  }

  /**
   * 载入结构。
   */
  void loadStructure();

  /**
   * 载入样式。
   */
  void loadStyle();

  /**
   * 载入行为。
   */
  void loadAction();
}
