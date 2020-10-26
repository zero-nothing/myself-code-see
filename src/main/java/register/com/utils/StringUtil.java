package register.com.utils;

public class StringUtil {
    /**
     * 实现字符串的增加，如P01到P02，P0Z到P10，PZZ到P001
     * @param oldString 字符串增加的源字符串
     * @return 增加过后的字符串
     */
    public static String increaseString(String oldString) {
        if (oldString == null) {
            return "P01";
        }

        byte[] byteArray = oldString.getBytes();
        for (int i = byteArray.length - 1; i >= 0; i--) {
            if (byteArray[i] != 'Z' & i != 0) {
                if (byteArray[i] != '9') {
                    byteArray[i]++;
                    break;
                }
                byteArray[i] = 'A';
                break;
            } else {
                byteArray[i] = '0';
            }
        }

        return byteArray[0] == '0' ? 'P' + new String(byteArray) : new String(byteArray);
    }
}
