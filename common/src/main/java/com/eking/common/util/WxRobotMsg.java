
package com.eking.common.util;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zeke929
 */
@Data
public class WxRobotMsg {
    public static final String TEXT = "text";
    private String msgType;
    private String content;
    private List<String> mentionedList;
    private List<String> mentionedMobileList;
    private String base64;
    private String md5;

    public String toJson() {
        Map<String, Object> param = new HashMap<>();
        param.put("msgtype", this.getMsgType());
        Map<String, Object> msg = new HashMap<>();
        if (StringUtils.isNotEmpty(this.getContent()) && this.getContent().length() > 1800) {
            this.setContent(this.getContent().substring(0, 1800));
        }

        msg.put("content", this.getContent());
        JSONArray uidJsonArray = new JSONArray();
        JSONArray mobileJsonArray = new JSONArray();
        if (this.getMentionedList() != null) {
            uidJsonArray.addAll(this.getMentionedList());
        }

        if (this.getMentionedMobileList() != null) {
            mobileJsonArray.addAll(this.getMentionedMobileList());
        }

        msg.put("mentioned_list", uidJsonArray);
        msg.put("mentioned_mobile_list", mobileJsonArray);
        param.put(this.msgType, msg);
        return JSON.toJSONString(param);
    }

}
