package com.eking.common.util;

import cn.hutool.http.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zeke929
 */
public class QYWXUtil {
    private static final Logger log = LoggerFactory.getLogger(QYWXUtil.class);
    private static String weixinUrl = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send";

    public static void sendMsg(String content) {
        try {
            if (StringUtils.isEmpty(QYWXUtil.WeixinGlobalConfig.key)) {
                return;
            }

            String msgUrl = String.format(weixinUrl + "?key=%s", QYWXUtil.WeixinGlobalConfig.key);
            WxRobotMsg robotMessage = new WxRobotMsg();
            robotMessage.setMsgType("text");
            robotMessage.setContent(content);
            List<String> list = new ArrayList<>();
            list.add("@all");
            robotMessage.setMentionedList(list);
            HttpUtil.post(msgUrl, robotMessage.toJson());
        } catch (Exception e) {
            log.error("【发送企微预警】操作失败,原因：{}", e.getMessage(), e);
        }

    }

    @Component
    public static class WeixinGlobalConfig {
        private static String key;

        @Value("${framework.alert.weixin.key:''}")
        public void setKey(String key) {
            QYWXUtil.WeixinGlobalConfig.key = key;
        }
    }
}
