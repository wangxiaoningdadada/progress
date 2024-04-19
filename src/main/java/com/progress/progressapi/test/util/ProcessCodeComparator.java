package com.lixiang.vrdos.review.common.utils;

import java.util.Comparator;

/**
 * description: 流程编码排序
 * date: 2023/8/19 11:11
 *
 * @author: wangxiaoning
 */
public class ProcessCodeComparator implements Comparator<String> {

    @Override
    public int compare(String pre, String suf) {
        String[] preArrs = pre.split("\\.");
        String[] sufArrs = suf.split("\\.");
        int len = preArrs.length > sufArrs.length ? preArrs.length : sufArrs.length;
        for (int i = 0; i < len; i++) {
            int preNum = 0;
            int sufNum = 0;
            if (i < preArrs.length) {
                preNum = Integer.parseInt(preArrs[i]);
            }
            if (i < sufArrs.length) {
                sufNum = Integer.parseInt(sufArrs[i]);
            }
            if (preNum > sufNum) {
                return 1;
            } else if (preNum < sufNum) {
                return -1;
            }
        }
        return 0;
    }
}
