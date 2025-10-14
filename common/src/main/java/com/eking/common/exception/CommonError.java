package com.eking.common.exception;

import lombok.Getter;

/**
 * <B>主类名称：</B>CommonError<BR>
 * <B>概要说明：</B>公用异常码定义<BR>
 *
 * @author zeke929
 * @since 2025/5/31 17:47
 */
@Getter
public enum CommonError {

    NULL_PARAMS("NULL_PARAMS", "参数为空"),
    ERROR_PARAMS("ERROR_PARAMS", "参数错误"),
    NOT_EXIST_ERROR("NOT_EXIST_ERROR", "不存在的错误"),
    UNKNOWN_ERROR("UNKNOWN_ERROR", "未知错误");

    private final String code;
    private final String desc;

    CommonError(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}