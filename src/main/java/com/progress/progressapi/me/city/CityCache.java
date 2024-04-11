package com.progress.progressapi.me.city;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 全国省市缓存
 *
 * @author xiaoning.wang
 * @date 2024-04-10 17:06
 */
@Component
@Slf4j
public class CityCache {

    /**
     * 省份缓存,包含省、自治区、直辖市、特别行政区 名称-区划代码
     */
    private Map<String, String> provinceMap;

    /**
     * 城市缓存,包含地级市和县级市 名称-区划代码
     */
    private Map<String, String> cityMap;

    /**
     * 初始化县级市开关 on-off
     */
    @Value("${county.level.city.switch:on}")
    private String initSwitch;

    /**
     * 省份、城市数据初始化
     */
    @PostConstruct
    public void initializeCache() {
        log.info("全国省市数据初始化start");
        provinceMap = new LinkedHashMap<>();
        cityMap = new LinkedHashMap<>();

        // 初始化省省、自治区、直辖市、特别行政区
        initProvince();
        // 初始化地级市
        initPrefectureLevelCity();
        // 初始化县级市
        initCountyLevelCity();
        log.info("全国省市数据初始化end");
    }

    /**
     * 根据关键词模糊检索省份
     *
     * @param keyword 关键词
     * @return
     */
    public List<String> searchProvince(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return Collections.emptyList();
        }
        String finalKey = keyword.trim();
        List<String> result = provinceMap.keySet().stream()
                .filter(province -> province.contains(finalKey))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 根据关键词模糊检索城市
     *
     * @param keyword 关键词
     * @return
     */
    public List<String> searchCity(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return Collections.emptyList();
        }
        String finalKey = keyword.trim();
        List<String> result = cityMap.keySet().stream()
                .filter(city -> city.contains(finalKey))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 初始化省份
     */
    private void initProvince() {
        // 直辖市
        provinceMap.put("北京市", "110000");
        provinceMap.put("天津市", "120000");
        provinceMap.put("上海市", "310000");
        provinceMap.put("重庆市", "500000");
        // 省
        provinceMap.put("河北省", "130000");
        provinceMap.put("山西省", "140000");
        provinceMap.put("辽宁省", "210000");
        provinceMap.put("吉林省", "220000");
        provinceMap.put("黑龙江省", "230000");
        provinceMap.put("江苏省", "320000");
        provinceMap.put("浙江省", "330000");
        provinceMap.put("安徽省", "340000");
        provinceMap.put("福建省", "350000");
        provinceMap.put("江西省", "360000");
        provinceMap.put("山东省", "370000");
        provinceMap.put("河南省", "410000");
        provinceMap.put("湖北省", "420000");
        provinceMap.put("湖南省", "430000");
        provinceMap.put("广东省", "440000");
        provinceMap.put("海南省", "460000");
        provinceMap.put("四川省", "510000");
        provinceMap.put("贵州省", "520000");
        provinceMap.put("云南省", "530000");
        provinceMap.put("陕西省", "610000");
        provinceMap.put("甘肃省", "620000");
        provinceMap.put("青海省", "630000");
        provinceMap.put("台湾省", "710000");
        // 自治区
        provinceMap.put("内蒙古自治区", "150000");
        provinceMap.put("广西壮族自治区", "450000");
        provinceMap.put("西藏自治区", "540000");
        provinceMap.put("宁夏回族自治区", "640000");
        provinceMap.put("新疆维吾尔自治区", "650000");
        // 特别行政区
        provinceMap.put("香港特别行政区", "810000");
        provinceMap.put("澳门特别行政区", "820000");
    }

    /**
     * 初始化城市-地级市
     */
    private void initPrefectureLevelCity() {
        // 北京市
        cityMap.put("北京市", "110100");
        // 天津市
        cityMap.put("天津市", "120100");
        // 上海市
        cityMap.put("上海市", "310100");
        // 重庆市
        cityMap.put("重庆市", "500100");

        // 河北省
        cityMap.put("石家庄市", "130100");
        cityMap.put("唐山市", "130200");
        cityMap.put("秦皇岛市", "130300");
        cityMap.put("邯郸市", "130400");
        cityMap.put("邢台市", "130500");
        cityMap.put("保定市", "130600");
        cityMap.put("张家口市", "130700");
        cityMap.put("承德市", "130800");
        cityMap.put("沧州市", "130900");
        cityMap.put("廊坊市", "131000");
        cityMap.put("衡水市", "131100");
        cityMap.put("雄安新区", "");
        // 山西省
        cityMap.put("太原市", "140100");
        cityMap.put("大同市", "140200");
        cityMap.put("阳泉市", "140300");
        cityMap.put("长治市", "140400");
        cityMap.put("晋城市", "140500");
        cityMap.put("朔州市", "140600");
        cityMap.put("晋中市", "140700");
        cityMap.put("运城市", "140800");
        cityMap.put("忻州市", "140900");
        cityMap.put("临汾市", "141000");
        cityMap.put("吕梁市", "141100");
        // 内蒙古自治区
        cityMap.put("呼和浩特市", "150100");
        cityMap.put("包头市", "150200");
        cityMap.put("乌海市", "150300");
        cityMap.put("赤峰市", "150400");
        cityMap.put("通辽市", "150500");
        cityMap.put("鄂尔多斯市", "150600");
        cityMap.put("呼伦贝尔市", "150700");
        cityMap.put("巴彦淖尔市", "150800");
        cityMap.put("乌兰察布市", "150900");
        cityMap.put("兴安盟", "152200");
        cityMap.put("锡林郭勒盟", "152500");
        cityMap.put("阿拉善盟", "152900");
        // 辽宁省
        cityMap.put("沈阳市", "210100");
        cityMap.put("大连市", "210200");
        cityMap.put("鞍山市", "210300");
        cityMap.put("抚顺市", "210400");
        cityMap.put("本溪市", "210500");
        cityMap.put("丹东市", "210600");
        cityMap.put("锦州市", "210700");
        cityMap.put("营口市", "210800");
        cityMap.put("阜新市", "210900");
        cityMap.put("辽阳市", "211000");
        cityMap.put("盘锦市", "211100");
        cityMap.put("铁岭市", "211200");
        cityMap.put("朝阳市", "211300");
        cityMap.put("葫芦岛市", "211400");
        // 吉林省
        cityMap.put("长春市", "220100");
        cityMap.put("吉林市", "220200");
        cityMap.put("四平市", "220300");
        cityMap.put("辽源市", "220400");
        cityMap.put("通化市", "220500");
        cityMap.put("白山市", "220600");
        cityMap.put("松原市", "220700");
        cityMap.put("白城市", "220800");
        cityMap.put("延边朝鲜族自治州", "222400");
        // 黑龙江省
        cityMap.put("哈尔滨市", "230100");
        cityMap.put("齐齐哈尔市", "230200");
        cityMap.put("鸡西市", "230300");
        cityMap.put("鹤岗市", "230400");
        cityMap.put("双鸭山市", "230500");
        cityMap.put("大庆市", "230600");
        cityMap.put("伊春市", "230700");
        cityMap.put("佳木斯市", "230800");
        cityMap.put("七台河市", "230900");
        cityMap.put("牡丹江市", "231000");
        cityMap.put("黑河市", "231100");
        cityMap.put("绥化市", "231200");
        cityMap.put("大兴安岭地区", "232700");
        // 江苏省
        cityMap.put("南京市", "320100");
        cityMap.put("无锡市", "320200");
        cityMap.put("徐州市", "320300");
        cityMap.put("常州市", "320400");
        cityMap.put("苏州市", "320500");
        cityMap.put("南通市", "320600");
        cityMap.put("连云港市", "320700");
        cityMap.put("淮安市", "320800");
        cityMap.put("盐城市", "320900");
        cityMap.put("扬州市", "321000");
        cityMap.put("镇江市", "321100");
        cityMap.put("泰州市", "321200");
        cityMap.put("宿迁市", "321300");
        // 浙江省
        cityMap.put("杭州市", "330100");
        cityMap.put("宁波市", "330200");
        cityMap.put("温州市", "330300");
        cityMap.put("嘉兴市", "330400");
        cityMap.put("湖州市", "330500");
        cityMap.put("绍兴市", "330600");
        cityMap.put("金华市", "330700");
        cityMap.put("衢州市", "330800");
        cityMap.put("舟山市", "330900");
        cityMap.put("台州市", "331000");
        cityMap.put("丽水市", "331100");
        // 安徽省
        cityMap.put("合肥市", "340100");
        cityMap.put("芜湖市", "340200");
        cityMap.put("蚌埠市", "340300");
        cityMap.put("淮南市", "340400");
        cityMap.put("马鞍山市", "340500");
        cityMap.put("淮北市", "340600");
        cityMap.put("铜陵市", "340700");
        cityMap.put("安庆市", "340800");
        cityMap.put("黄山市", "341000");
        cityMap.put("滁州市", "341100");
        cityMap.put("阜阳市", "341200");
        cityMap.put("宿州市", "341300");
        cityMap.put("六安市", "341500");
        cityMap.put("亳州市", "341600");
        cityMap.put("池州市", "341700");
        cityMap.put("宣城市", "341800");
        // 福建省
        cityMap.put("福州市", "350100");
        cityMap.put("厦门市", "350200");
        cityMap.put("莆田市", "350300");
        cityMap.put("三明市", "350400");
        cityMap.put("泉州市", "350500");
        cityMap.put("漳州市", "350600");
        cityMap.put("南平市", "350700");
        cityMap.put("龙岩市", "350800");
        cityMap.put("宁德市", "350900");
        // 江西省
        cityMap.put("南昌市", "360100");
        cityMap.put("景德镇市", "360200");
        cityMap.put("萍乡市", "360300");
        cityMap.put("九江市", "360400");
        cityMap.put("新余市", "360500");
        cityMap.put("鹰潭市", "360600");
        cityMap.put("赣州市", "360700");
        cityMap.put("吉安市", "360800");
        cityMap.put("宜春市", "360900");
        cityMap.put("抚州市", "361000");
        cityMap.put("上饶市", "361100");
        // 山东省
        cityMap.put("济南市", "370100");
        cityMap.put("青岛市", "370200");
        cityMap.put("淄博市", "370300");
        cityMap.put("枣庄市", "370400");
        cityMap.put("东营市", "370500");
        cityMap.put("烟台市", "370600");
        cityMap.put("潍坊市", "370700");
        cityMap.put("济宁市", "370800");
        cityMap.put("泰安市", "370900");
        cityMap.put("威海市", "371000");
        cityMap.put("日照市", "371100");
        cityMap.put("莱芜市", "371200");
        cityMap.put("临沂市", "371300");
        cityMap.put("德州市", "371400");
        cityMap.put("聊城市", "371500");
        cityMap.put("滨州市", "371600");
        cityMap.put("菏泽市", "371700");
        // 河南省
        cityMap.put("郑州市", "410100");
        cityMap.put("开封市", "410200");
        cityMap.put("洛阳市", "410300");
        cityMap.put("平顶山市", "410400");
        cityMap.put("安阳市", "410500");
        cityMap.put("鹤壁市", "410600");
        cityMap.put("新乡市", "410700");
        cityMap.put("焦作市", "410800");
        cityMap.put("濮阳市", "410900");
        cityMap.put("许昌市", "411000");
        cityMap.put("漯河市", "411100");
        cityMap.put("三门峡市", "411200");
        cityMap.put("南阳市", "411300");
        cityMap.put("商丘市", "411400");
        cityMap.put("信阳市", "411500");
        cityMap.put("周口市", "411600");
        cityMap.put("驻马店市", "411700");
        // 湖北省
        cityMap.put("武汉市", "420100");
        cityMap.put("黄石市", "420200");
        cityMap.put("十堰市", "420300");
        cityMap.put("宜昌市", "420500");
        cityMap.put("襄阳市", "420600");
        cityMap.put("鄂州市", "420700");
        cityMap.put("荆门市", "420800");
        cityMap.put("孝感市", "420900");
        cityMap.put("荆州市", "421000");
        cityMap.put("黄冈市", "421100");
        cityMap.put("咸宁市", "421200");
        cityMap.put("随州市", "421300");
        cityMap.put("恩施土家族苗族自治州", "422800");
        cityMap.put("神农架林区", "429021");
        // 湖南省
        cityMap.put("长沙市", "430100");
        cityMap.put("株洲市", "430200");
        cityMap.put("湘潭市", "430300");
        cityMap.put("衡阳市", "430400");
        cityMap.put("邵阳市", "430500");
        cityMap.put("岳阳市", "430600");
        cityMap.put("常德市", "430700");
        cityMap.put("张家界市", "430800");
        cityMap.put("益阳市", "430900");
        cityMap.put("郴州市", "431000");
        cityMap.put("永州市", "431100");
        cityMap.put("怀化市", "431200");
        cityMap.put("娄底市", "431300");
        cityMap.put("湘西土家族苗族自治州", "433100");
        // 广东省
        cityMap.put("广州市", "440100");
        cityMap.put("韶关市", "440200");
        cityMap.put("深圳市", "440300");
        cityMap.put("珠海市", "440400");
        cityMap.put("汕头市", "440500");
        cityMap.put("佛山市", "440600");
        cityMap.put("江门市", "440700");
        cityMap.put("湛江市", "440800");
        cityMap.put("茂名市", "440900");
        cityMap.put("肇庆市", "441200");
        cityMap.put("惠州市", "441300");
        cityMap.put("梅州市", "441400");
        cityMap.put("汕尾市", "441500");
        cityMap.put("河源市", "441600");
        cityMap.put("阳江市", "441700");
        cityMap.put("清远市", "441800");
        cityMap.put("东莞市", "441900");
        cityMap.put("中山市", "442000");
        cityMap.put("潮州市", "445100");
        cityMap.put("揭阳市", "445200");
        cityMap.put("云浮市", "445300");
        // 广西壮族自治区
        cityMap.put("南宁市", "450100");
        cityMap.put("柳州市", "450200");
        cityMap.put("桂林市", "450300");
        cityMap.put("梧州市", "450400");
        cityMap.put("北海市", "450500");
        cityMap.put("防城港市", "450600");
        cityMap.put("钦州市", "450700");
        cityMap.put("贵港市", "450800");
        cityMap.put("玉林市", "450900");
        cityMap.put("百色市", "451000");
        cityMap.put("贺州市", "451100");
        cityMap.put("河池市", "451200");
        cityMap.put("来宾市", "451300");
        cityMap.put("崇左市", "451400");
        // 海南省
        cityMap.put("海口市", "460100");
        cityMap.put("三亚市", "460200");
        cityMap.put("三沙市", "460300");
        cityMap.put("儋州市", "460400");
        // 四川省
        cityMap.put("成都市", "510100");
        cityMap.put("自贡市", "510300");
        cityMap.put("攀枝花市", "510400");
        cityMap.put("泸州市", "510500");
        cityMap.put("德阳市", "510600");
        cityMap.put("绵阳市", "510700");
        cityMap.put("广元市", "510800");
        cityMap.put("遂宁市", "510900");
        cityMap.put("内江市", "511000");
        cityMap.put("乐山市", "511100");
        cityMap.put("南充市", "511300");
        cityMap.put("眉山市", "511400");
        cityMap.put("宜宾市", "511500");
        cityMap.put("广安市", "511600");
        cityMap.put("达州市", "511700");
        cityMap.put("雅安市", "511800");
        cityMap.put("巴中市", "511900");
        cityMap.put("资阳市", "512000");
        cityMap.put("阿坝藏族羌族自治州", "513200");
        cityMap.put("甘孜藏族自治州", "513300");
        cityMap.put("凉山彝族自治州", "513400");
        // 贵州省
        cityMap.put("贵阳市", "520100");
        cityMap.put("六盘水市", "520200");
        cityMap.put("遵义市", "520300");
        cityMap.put("安顺市", "520400");
        cityMap.put("毕节市", "520500");
        cityMap.put("铜仁市", "520600");
        cityMap.put("黔西南布依族苗族自治州", "522300");
        cityMap.put("黔东南苗族侗族自治州", "522600");
        cityMap.put("黔南布依族苗族自治州", "522700");
        // 云南省
        cityMap.put("昆明市", "530100");
        cityMap.put("曲靖市", "530300");
        cityMap.put("玉溪市", "530400");
        cityMap.put("保山市", "530500");
        cityMap.put("昭通市", "530600");
        cityMap.put("丽江市", "530700");
        cityMap.put("普洱市", "530800");
        cityMap.put("临沧市", "530900");
        cityMap.put("楚雄彝族自治州", "532300");
        cityMap.put("红河哈尼族彝族自治州", "532500");
        cityMap.put("文山壮族苗族自治州", "532600");
        cityMap.put("西双版纳傣族自治州", "532800");
        cityMap.put("大理白族自治州", "532900");
        cityMap.put("德宏傣族景颇族自治州", "533100");
        cityMap.put("怒江傈僳族自治州", "533300");
        cityMap.put("迪庆藏族自治州", "533400");
        // 西藏自治区
        cityMap.put("拉萨市", "540100");
        cityMap.put("日喀则市", "540200");
        cityMap.put("昌都市", "540300");
        cityMap.put("林芝市", "540400");
        cityMap.put("山南市", "540500");
        cityMap.put("那曲市", "540600");
        cityMap.put("阿里地区", "542500");
        // 陕西省
        cityMap.put("西安市", "610100");
        cityMap.put("铜川市", "610200");
        cityMap.put("宝鸡市", "610300");
        cityMap.put("咸阳市", "610400");
        cityMap.put("渭南市", "610500");
        cityMap.put("延安市", "610600");
        cityMap.put("汉中市", "610700");
        cityMap.put("榆林市", "610800");
        cityMap.put("安康市", "610900");
        cityMap.put("商洛市", "611000");
        // 甘肃省
        cityMap.put("兰州市", "620100");
        cityMap.put("嘉峪关市", "620200");
        cityMap.put("金昌市", "620300");
        cityMap.put("白银市", "620400");
        cityMap.put("天水市", "620500");
        cityMap.put("武威市", "620600");
        cityMap.put("张掖市", "620700");
        cityMap.put("平凉市", "620800");
        cityMap.put("酒泉市", "620900");
        cityMap.put("庆阳市", "621000");
        cityMap.put("定西市", "621100");
        cityMap.put("陇南市", "621200");
        cityMap.put("临夏回族自治州", "622900");
        cityMap.put("甘南藏族自治州", "623000");
        // 青海省
        cityMap.put("西宁市", "630100");
        cityMap.put("海东市", "630200");
        cityMap.put("海北藏族自治州", "632200");
        cityMap.put("黄南藏族自治州", "632300");
        cityMap.put("海南藏族自治州", "632500");
        cityMap.put("果洛藏族自治州", "632600");
        cityMap.put("玉树藏族自治州", "632700");
        cityMap.put("海西蒙古族藏族自治州", "632800");
        // 宁夏回族自治区
        cityMap.put("银川市", "640100");
        cityMap.put("石嘴山市", "640200");
        cityMap.put("吴忠市", "640300");
        cityMap.put("固原市", "640400");
        cityMap.put("中卫市", "640500");
        // 新疆维吾尔自治区
        cityMap.put("乌鲁木齐市", "650100");
        cityMap.put("克拉玛依市", "650200");
        cityMap.put("吐鲁番市", "650400");
        cityMap.put("哈密市", "650500");
        cityMap.put("昌吉回族自治州", "652300");
        cityMap.put("博尔塔拉蒙古自治州", "652700");
        cityMap.put("巴音郭楞蒙古自治州", "652800");
        cityMap.put("阿克苏地区", "652900");
        cityMap.put("克孜勒苏柯尔克孜自治州", "653000");
        cityMap.put("喀什地区", "653100");
        cityMap.put("和田地区", "653200");
        cityMap.put("伊犁哈萨克自治州", "654000");
        cityMap.put("塔城地区", "654200");
        cityMap.put("阿勒泰地区", "654300");
        cityMap.put("自治区直辖县级行政区划", "659000");
        // 香港特别行政区
        cityMap.put("香港岛", "810100");
        cityMap.put("九龙", "810200");
        cityMap.put("新界", "810300");
        // 澳门特别行政区
        cityMap.put("澳门半岛", "820100");
        cityMap.put("氹仔岛", "820200");
        cityMap.put("路环岛", "820300");
        // 台湾省地级市
        cityMap.put("基隆市", "基隆市");
        cityMap.put("台北市", "台北市");
        cityMap.put("新竹市", "新竹市");
        cityMap.put("台中市", "台中市");
        cityMap.put("彰化市", "彰化市");
        cityMap.put("嘉义市", "嘉义市");
        cityMap.put("台南市", "台南市");
        cityMap.put("高雄市", "高雄市");
        cityMap.put("屏东市", "屏东市");
    }

    /**
     * 初始化县级市
     */
    private void initCountyLevelCity() {
        if (!Objects.equals(initSwitch, "on")) {
            return;
        }
        // 河北省
        cityMap.put("泊头市", "130981");
        cityMap.put("定州市", "130984");
        cityMap.put("辛集市", "130983");
        cityMap.put("南宫市", "130581");
        cityMap.put("任丘市", "130982");
        cityMap.put("涿州市", "130681");
        cityMap.put("沙河市", "130582");
        cityMap.put("武安市", "130481");
        cityMap.put("黄骅市", "130983");
        cityMap.put("霸州市", "131081");
        cityMap.put("河间市", "130984");
        cityMap.put("安国市", "130683");
        cityMap.put("晋州市", "130183");
        cityMap.put("遵化市", "130281");
        cityMap.put("新乐市", "130184");
        cityMap.put("三河市", "130281");
        cityMap.put("高碑店市", "130984");
        cityMap.put("深州市", "131182");
        cityMap.put("迁安市", "130983");
        cityMap.put("平泉市", "130581");
        cityMap.put("滦州市", "130284");

        // 山西省
        cityMap.put("侯马市", "141081");
        cityMap.put("古交市", "140181");
        cityMap.put("霍州市", "141082");
        cityMap.put("孝义市", "141181");
        cityMap.put("介休市", "140781");
        cityMap.put("高平市", "140581");
        cityMap.put("原平市", "140981");
        cityMap.put("永济市", "140881");
        cityMap.put("河津市", "140882");
        cityMap.put("汾阳市", "141182");
        cityMap.put("怀仁市", "140681");

        // 内蒙古自治区
        cityMap.put("满洲里市", "150781");
        cityMap.put("二连浩特市", "152501");
        cityMap.put("乌兰浩特市", "152201");
        cityMap.put("牙克石市", "152221");
        cityMap.put("扎兰屯市", "150783");
        cityMap.put("锡林浩特市", "152502");
        cityMap.put("霍林郭勒市", "150581");
        cityMap.put("丰镇市", "150981");
        cityMap.put("根河市", "150785");
        cityMap.put("额尔古纳市", "150784");
        cityMap.put("阿尔山市", "152202");

        // 辽宁省
        cityMap.put("北票市", "211381");
        cityMap.put("海城市", "210381");
        cityMap.put("瓦房店市", "210281");
        cityMap.put("调兵山市", "211281");
        cityMap.put("兴城市", "211481");
        cityMap.put("开原市", "211282");
        cityMap.put("凌源市", "211382");
        cityMap.put("庄河市", "210283");
        cityMap.put("盖州市", "210881");
        cityMap.put("大石桥市", "210882");
        cityMap.put("新民市", "210181");
        cityMap.put("东港市", "210681");
        cityMap.put("凌海市", "210781");
        cityMap.put("凤城市", "210682");
        cityMap.put("北镇市", "210782");
        cityMap.put("灯塔市", "211081");

        // 吉林省
        cityMap.put("延吉市", "222401");
        cityMap.put("图们市", "222402");
        cityMap.put("敦化市", "222403");
        cityMap.put("公主岭市", "220381");
        cityMap.put("梅河口市", "220581");
        cityMap.put("洮南市", "220881");
        cityMap.put("集安市", "220582");
        cityMap.put("珲春市", "222404");
        cityMap.put("龙井市", "222405");
        cityMap.put("桦甸市", "220282");
        cityMap.put("大安市", "220882");
        cityMap.put("蛟河市", "220281");
        cityMap.put("榆树市", "220182");
        cityMap.put("舒兰市", "220283");
        cityMap.put("和龙市", "222406");
        cityMap.put("临江市", "220681");
        cityMap.put("德惠市", "220183");
        cityMap.put("磐石市", "220284");
        cityMap.put("双辽市", "220382");
        cityMap.put("扶余市", "220724");

        // 黑龙江省
        cityMap.put("绥芬河市", "231081");
        cityMap.put("北安市", "231181");
        cityMap.put("五大连池市", "231182");
        cityMap.put("安达市", "231281");
        cityMap.put("肇东市", "231282");
        cityMap.put("同江市", "231283");
        cityMap.put("富锦市", "231084");
        cityMap.put("铁力市", "230781");
        cityMap.put("尚志市", "230183");
        cityMap.put("密山市", "230382");
        cityMap.put("海伦市", "231283");
        cityMap.put("海林市", "231083");
        cityMap.put("讷河市", "230281");
        cityMap.put("宁安市", "231084");
        cityMap.put("五常市", "230184");
        cityMap.put("穆棱市", "231085");
        cityMap.put("虎林市", "230381");
        cityMap.put("东宁市", "231086");
        cityMap.put("抚远市", "230833");
        cityMap.put("漠河市", "232701");
        cityMap.put("嫩江市", "232701");

        // 江苏省
        cityMap.put("常熟市", "320581");
        cityMap.put("仪征市", "321081");
        cityMap.put("张家港市", "320582");
        cityMap.put("江阴市", "320281");
        cityMap.put("丹阳市", "321181");
        cityMap.put("东台市", "320981");
        cityMap.put("兴化市", "320982");
        cityMap.put("宜兴市", "320282");
        cityMap.put("昆山市", "320583");
        cityMap.put("启东市", "320681");
        cityMap.put("新沂市", "320381");
        cityMap.put("溧阳市", "320481");
        cityMap.put("如皋市", "320682");
        cityMap.put("高邮市", "321084");
        cityMap.put("邳州市", "320382");
        cityMap.put("泰兴市", "321283");
        cityMap.put("太仓市", "320585");
        cityMap.put("靖江市", "321282");
        cityMap.put("扬中市", "321182");
        cityMap.put("句容市", "321183");
        cityMap.put("海安市", "320685");

        // 浙江省
        cityMap.put("兰溪市", "330781");
        cityMap.put("余姚市", "330281");
        cityMap.put("临海市", "331082");
        cityMap.put("海宁市", "330481");
        cityMap.put("瑞安市", "330381");
        cityMap.put("江山市", "330881");
        cityMap.put("义乌市", "330782");
        cityMap.put("东阳市", "330783");
        cityMap.put("慈溪市", "330282");
        cityMap.put("诸暨市", "330681");
        cityMap.put("龙泉市", "331181");
        cityMap.put("平湖市", "330482");
        cityMap.put("建德市", "330182");
        cityMap.put("永康市", "330784");
        cityMap.put("桐乡市", "330483");
        cityMap.put("乐清市", "330382");
        cityMap.put("温岭市", "331081");
        cityMap.put("嵊州市", "330683");
        cityMap.put("玉环市", "331083");
        cityMap.put("龙港市", "331082");

        // 安徽省
        cityMap.put("界首市", "341282");
        cityMap.put("天长市", "341181");
        cityMap.put("明光市", "341182");
        cityMap.put("桐城市", "340881");
        cityMap.put("宁国市", "341881");
        cityMap.put("巢湖市", "340181");
        cityMap.put("潜山市", "340824");
        cityMap.put("广德市", "341882");
        cityMap.put("无为市", "340225");

        // 福建省
        cityMap.put("邵武市", "350781");
        cityMap.put("永安市", "350481");
        cityMap.put("石狮市", "350581");
        cityMap.put("武夷山市", "350782");
        cityMap.put("福安市", "350982");
        cityMap.put("漳平市", "350881");
        cityMap.put("福清市", "350181");
        cityMap.put("晋江市", "350582");
        cityMap.put("建瓯市", "350783");
        cityMap.put("南安市", "350583");
        cityMap.put("福鼎市", "350982");

        // 江西省
        cityMap.put("井冈山市", "360881");
        cityMap.put("丰城市", "360981");
        cityMap.put("樟树市", "360982");
        cityMap.put("瑞昌市", "360481");
        cityMap.put("德兴市", "361181");
        cityMap.put("乐平市", "360281");
        cityMap.put("高安市", "360983");
        cityMap.put("瑞金市", "360781");
        cityMap.put("贵溪市", "360681");
        cityMap.put("共青城市", "360681");
        cityMap.put("庐山市", "360421");
        cityMap.put("龙南市", "360727");

        // 山东省
        cityMap.put("新泰市", "370982");
        cityMap.put("临清市", "371581");
        cityMap.put("青州市", "370781");
        cityMap.put("曲阜市", "370881");
        cityMap.put("龙口市", "370681");
        cityMap.put("胶州市", "370281");
        cityMap.put("莱阳市", "370682");
        cityMap.put("诸城市", "370782");
        cityMap.put("莱州市", "370683");
        cityMap.put("滕州市", "370481");
        cityMap.put("乐陵市", "371481");
        cityMap.put("荣成市", "371082");
        cityMap.put("平度市", "370283");
        cityMap.put("莱西市", "370285");
        cityMap.put("招远市", "370685");
        cityMap.put("肥城市", "370983");
        cityMap.put("邹城市", "370883");
        cityMap.put("寿光市", "370783");
        cityMap.put("乳山市", "371083");
        cityMap.put("禹城市", "371482");
        cityMap.put("安丘市", "370784");
        cityMap.put("高密市", "370785");
        cityMap.put("昌邑市", "370786");
        cityMap.put("栖霞市", "370686");
        cityMap.put("海阳市", "370687");
        cityMap.put("邹平市", "371681");

        // 河南省
        cityMap.put("义马市", "411281");
        cityMap.put("汝州市", "410482");
        cityMap.put("禹州市", "411081");
        cityMap.put("济源市", "419001");
        cityMap.put("卫辉市", "410781");
        cityMap.put("辉县市", "410782");
        cityMap.put("邓州市", "411381");
        cityMap.put("沁阳市", "410882");
        cityMap.put("舞钢市", "410481");
        cityMap.put("巩义市", "410181");
        cityMap.put("灵宝市", "411282");
        cityMap.put("长葛市", "411082");
        cityMap.put("项城市", "411681");
        cityMap.put("林州市", "410581");
        cityMap.put("新密市", "410183");
        cityMap.put("荥阳市", "410182");
        cityMap.put("新郑市", "410184");
        cityMap.put("登封市", "410185");
        cityMap.put("孟州市", "410883");
        cityMap.put("永城市", "411481");
        cityMap.put("长垣市", "410883");

        // 湖北省
        cityMap.put("老河口市", "420682");
        cityMap.put("恩施市", "422801");
        cityMap.put("丹江口市", "420381");
        cityMap.put("应城市", "420981");
        cityMap.put("赤壁市", "421281");
        cityMap.put("仙桃市", "429004");
        cityMap.put("石首市", "421081");
        cityMap.put("麻城市", "421181");
        cityMap.put("利川市", "422802");
        cityMap.put("洪湖市", "421083");
        cityMap.put("天门市", "429006");
        cityMap.put("安陆市", "420982");
        cityMap.put("武穴市", "421182");
        cityMap.put("宜都市", "420581");
        cityMap.put("枣阳市", "420683");
        cityMap.put("潜江市", "429005");
        cityMap.put("广水市", "421381");
        cityMap.put("当阳市", "420582");
        cityMap.put("钟祥市", "421081");
        cityMap.put("大冶市", "420281");
        cityMap.put("宜城市", "420684");
        cityMap.put("松滋市", "421087");
        cityMap.put("枝江市", "420583");
        cityMap.put("汉川市", "421182");
        cityMap.put("京山市", "420882");
        cityMap.put("监利市", "421023");

        // 湖南省
        cityMap.put("洪江市", "431281");
        cityMap.put("津市市", "431381");
        cityMap.put("吉首市", "433101");
        cityMap.put("冷水江市", "431381");
        cityMap.put("资兴市", "431081");
        cityMap.put("醴陵市", "430281");
        cityMap.put("湘乡市", "430381");
        cityMap.put("耒阳市", "430481");
        cityMap.put("涟源市", "431382");
        cityMap.put("汨罗市", "430681");
        cityMap.put("沅江市", "431281");
        cityMap.put("韶山市", "430382");
        cityMap.put("临湘市", "430682");
        cityMap.put("浏阳市", "430181");
        cityMap.put("武冈市", "430581");
        cityMap.put("常宁市", "430482");
        cityMap.put("宁乡市", "430981");
        cityMap.put("邵东市", "430582");
        cityMap.put("祁阳市", "431121");

        // 广东省
        cityMap.put("台山市", "440781");
        cityMap.put("开平市", "440783");
        cityMap.put("普宁市", "445281");
        cityMap.put("罗定市", "445381");
        cityMap.put("高州市", "440981");
        cityMap.put("鹤山市", "440784");
        cityMap.put("四会市", "441284");
        cityMap.put("廉江市", "440881");
        cityMap.put("英德市", "441881");
        cityMap.put("恩平市", "440785");
        cityMap.put("连州市", "441882");
        cityMap.put("雷州市", "440882");
        cityMap.put("乐昌市", "440281");
        cityMap.put("阳春市", "441781");
        cityMap.put("吴川市", "440883");
        cityMap.put("兴宁市", "441481");
        cityMap.put("化州市", "440982");
        cityMap.put("陆丰市", "441581");
        cityMap.put("信宜市", "440983");
        cityMap.put("南雄市", "440282");

        // 广西壮族自治区
        cityMap.put("凭祥市", "451481");
        cityMap.put("合山市", "451381");
        cityMap.put("北流市", "450981");
        cityMap.put("桂平市", "450881");
        cityMap.put("岑溪市", "451381");
        cityMap.put("东兴市", "450681");
        cityMap.put("靖西市", "451081");
        cityMap.put("荔浦市", "450331");
        cityMap.put("平果市", "451082");
        cityMap.put("横州市", "451282");

        // 海南省
        cityMap.put("五指山市", "469001");
        cityMap.put("琼海市", "469002");
        cityMap.put("文昌市", "469005");
        cityMap.put("万宁市", "469006");
        cityMap.put("东方市", "469007");

        // 四川省
        cityMap.put("西昌市", "513401");
        cityMap.put("华蓥市", "511681");
        cityMap.put("江油市", "510781");
        cityMap.put("广汉市", "510681");
        cityMap.put("都江堰市", "510181");
        cityMap.put("峨眉山市", "511181");
        cityMap.put("阆中市", "511381");
        cityMap.put("万源市", "511781");
        cityMap.put("彭州市", "510182");
        cityMap.put("简阳市", "510185");
        cityMap.put("邛崃市", "510183");
        cityMap.put("崇州市", "510184");
        cityMap.put("什邡市", "510682");
        cityMap.put("绵竹市", "510683");
        cityMap.put("康定市", "513301");
        cityMap.put("马尔康市", "513201");
        cityMap.put("隆昌市", "511083");
        cityMap.put("射洪市", "511111");
        cityMap.put("会理市", "513425");

        // 贵州省
        cityMap.put("都匀市", "522701");
        cityMap.put("凯里市", "522601");
        cityMap.put("兴义市", "522301");
        cityMap.put("赤水市", "520381");
        cityMap.put("清镇市", "520181");
        cityMap.put("仁怀市", "520382");
        cityMap.put("福泉市", "522702");
        cityMap.put("盘州市", "520281");
        cityMap.put("兴仁市", "522302");
        cityMap.put("黔西市", "522423");

        // 云南省
        cityMap.put("个旧市", "532501");
        cityMap.put("开远市", "532502");
        cityMap.put("大理市", "532901");
        cityMap.put("楚雄市", "532301");
        cityMap.put("瑞丽市", "533102");
        cityMap.put("景洪市", "532801");
        cityMap.put("宣威市", "530381");
        cityMap.put("安宁市", "530181");
        cityMap.put("芒市", "533103");
        cityMap.put("蒙自市", "532503");
        cityMap.put("文山市", "532601");
        cityMap.put("弥勒市", "532504");
        cityMap.put("香格里拉市", "533401");
        cityMap.put("腾冲市", "530581");
        cityMap.put("泸水市", "533301");
        cityMap.put("水富市", "530681");
        cityMap.put("澄江市", "530481");
        cityMap.put("禄丰市", "532901");

        // 西藏自治区
        cityMap.put("米林市", "542623");
        cityMap.put("错那市", "542622");

        // 陕西省
        cityMap.put("韩城市", "610581");
        cityMap.put("华阴市", "610582");
        cityMap.put("兴平市", "610481");
        cityMap.put("神木市", "610881");
        cityMap.put("彬州市", "610925");
        cityMap.put("子长市", "610927");
        cityMap.put("旬阳市", "611023");

        // 甘肃省
        cityMap.put("玉门市", "620981");
        cityMap.put("临夏市", "622901");
        cityMap.put("敦煌市", "620982");
        cityMap.put("合作市", "623001");
        cityMap.put("华亭市", "620881");

        // 青海省
        cityMap.put("格尔木市", "632801");
        cityMap.put("德令哈市", "632802");
        cityMap.put("玉树市", "632701");
        cityMap.put("茫崖市", "632803");
        cityMap.put("同仁市", "632301");

        // 宁夏回族自治区
        cityMap.put("青铜峡市", "640381");
        cityMap.put("灵武市", "640181");

        // 新疆维吾尔自治区
        cityMap.put("伊宁市", "654002");
        cityMap.put("喀什市", "653101");
        cityMap.put("奎屯市", "654003");
        cityMap.put("石河子市", "659001");
        cityMap.put("库尔勒市", "652801");
        cityMap.put("昌吉市", "652301");
        cityMap.put("阿克苏市", "652901");
        cityMap.put("和田市", "653201");
        cityMap.put("塔城市", "654201");
        cityMap.put("阿勒泰市", "654301");
        cityMap.put("博乐市", "652701");
        cityMap.put("阿图什市", "653001");
        cityMap.put("阜康市", "652302");
        cityMap.put("乌苏市", "659002");
        cityMap.put("阿拉尔市", "659002");
        cityMap.put("图木舒克市", "659003");
        cityMap.put("五家渠市", "659004");
        cityMap.put("北屯市", "654004");
        cityMap.put("阿拉山口市", "659005");
        cityMap.put("铁门关市", "659006");
        cityMap.put("双河市", "659007");
        cityMap.put("霍尔果斯市", "659008");
        cityMap.put("可克达拉市", "659009");
        cityMap.put("昆玉市", "659010");
        cityMap.put("胡杨河市", "659011");
        cityMap.put("库车市", "659012");
        cityMap.put("新星市", "659013");
        cityMap.put("沙湾市", "659014");
        cityMap.put("白杨市", "659015");
    }


}
