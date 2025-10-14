package com.eking.common.mvc;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <B>主类名称：</B>BaseResp<BR>
 * <B>概要说明：</B>BaseResp<BR>
 *
 * @author zeke929
 * @since 2025/6/19 10:59
 */
public class BaseResp<T> implements Serializable {

    private static final long serialVersionUID = -6753376556266142120L;

    private static final String SUCCESS_CODE = "200";

    private static final String FAILURE_CODE = "500";

    private static final String DEFAULT_SUCCESS_MSG = "操作成功";

    @Getter
    @Setter
    private T data;

    /**
     * 错误码，200：成功，500：异常
     **/
    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String msg = "";

    public static <T> BaseResp<T> success() {
        ResultBuilder<T> builder = builder();
        return builder.success().build();
    }

    public static <T> BaseResp<T> success(T data) {
        ResultBuilder<T> builder = builder();
        return builder.success().data(data).build();
    }

    public static BaseResp<?> failure() {
        return builder().fail().build();
    }

    public static BaseResp<?> failure(String msg) {
        return builder().fail().msg(msg).build();
    }

    public BaseResp() {
    }

    public BaseResp(T data, String code, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> ResultBuilder<T> builder() {
        return new ResultBuilder<>();
    }

    public static class ResultBuilder<T> {

        private T data;

        private String code;

        private String msg;

        public ResultBuilder() {
        }

        public ResultBuilder<T> success() {
            this.code = SUCCESS_CODE;
            this.msg = DEFAULT_SUCCESS_MSG;
            return this;
        }

        public ResultBuilder<T> fail() {
            this.code = FAILURE_CODE;
            return this;
        }

        public ResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResultBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public BaseResp<T> build() {
            return new BaseResp<>(this.data, this.code, this.msg);
        }
    }
}
