package com.penyo.contacts;

/**
 * <strong>欢迎使用安全的通讯录！</strong>
 * 
 * <p>
 * 在信息泄露越发严重的今天，尤其是移动设备用户，饱受软件监听的骚扰。
 * 带着保卫数据安全的愿景，Penyo写就本Java通讯录。
 * 它完全开源，确保你的信息是安全无虞的。
 * 除了“更改”功能，其它交互逻辑上尽量做到与现代设计同步。
 * 
 * @author Penyo
 */
public class Main {
    /**
     * 统一的序列化校验码。
     */
    public static final long USVUID = 0;

    public static void main(String[] args) {
        new GUI();
    }
}
