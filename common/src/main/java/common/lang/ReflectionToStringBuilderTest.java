package common.lang;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created with IntelliJ IDEA.
 * User: gaoxingang
 * Date: 14-11-10
 * Time: 下午9:45
 * To change this template use File | Settings | File Templates.
 */
public class ReflectionToStringBuilderTest {
    static class TestVO {
        private int id;
        private String name;

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        int getId() {
            return id;
        }

        void setId(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        TestVO testVO = new TestVO();
        testVO.setId(1);
        testVO.setName("test");
        System.out.println(ToStringBuilder.reflectionToString(testVO, ToStringStyle.DEFAULT_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(testVO, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(testVO, ToStringStyle.NO_FIELD_NAMES_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(testVO, ToStringStyle.SHORT_PREFIX_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(testVO, ToStringStyle.SIMPLE_STYLE));
    }
}
