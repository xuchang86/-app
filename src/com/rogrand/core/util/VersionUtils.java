package com.rogrand.core.util;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2014-2-28 <br/>
 * 描述：〈版本工具〉
 */
public class VersionUtils {

    /**
     * 描述：〈判断源版本是否新于目标版本〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-2-28 <br/>
     * 
     * @param source 源版本
     * @param target 目标版本
     * @return
     * @throws Exception
     */
    public static boolean newer(String source, String target) throws Exception {
        String[] av1 = source.split("\\.");
        String[] av2 = target.split("\\.");

        if (source.equals(target)) {// 版本号相同，直接返回 false
            return false;
        }

        // 取长度小的作为对比次数
        int len = av1.length <= av2.length ? av1.length : av2.length;

        int result = 0;
        for (int i = 0; i < len; i++) {
            result = compare(Integer.parseInt(av1[i]), Integer.parseInt(av2[i]));
            if (result == 0) {
                // 如果相等则判断下一位
            } else if (result > 0) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static int compare(int num1, int num2) {
        if (num1 > num2)
            return 1;
        if (num1 < num2)
            return -1;
        return 0;
    }
}
