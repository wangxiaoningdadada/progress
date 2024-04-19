package com.progress.progressapi.test.util;

import com.progress.progressapi.test.dto.CodeDto;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    public static void main(String[] args) {

/*        List<String> code = new ArrayList<>();
        code.add("2.1");
        code.add("1.2");
        code.add("1.1.1");
        code.sort((a, b) -> new ProcessCodeComparator().compare(a, b));
        System.out.println(code);*/


        List<CodeDto> codes = new ArrayList<>();
        codes.add(new CodeDto("2.1"));
        codes.add(new CodeDto("1.2"));
        codes.add(new CodeDto("1.1.1"));
        codes.sort((a, b) -> new ProcessCodeComparator().compare(a.getCode(), b.getCode()));
        //System.out.println(codes);
        //codes.stream().forEach(System.out::println);
        codes.stream().forEach(codeDto -> System.out.println(codeDto.getCode()));
    }


}
