package exceptions;//: exceptions/NeverCaught.java
// Ignoring RuntimeExceptions.
// {ThrowsException}

/**
 * RuntimeException（不受检查异常） 这种异常属于错误 将被自动捕获
 */
public class NeverCaught {
  static void f() {
    throw new RuntimeException("From f()");
  }
  static void g() {
    f();
  }
  public static void main(String[] args) {
    g();
  }
} ///:~
