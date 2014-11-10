package common.lang;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Created with IntelliJ IDEA.
 * User: gaoxingang
 * Date: 14-11-10
 * Time: 下午9:50
 * To change this template use File | Settings | File Templates.
 */
public class StringEscapeUtilsTest {
    public static void main(String[] args) {
        System.out.println(StringEscapeUtils.escapeHtml("test<html><body></body></html>"));
    }
}
