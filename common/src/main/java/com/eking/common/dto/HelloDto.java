package com.eking.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * <B>主类名称：</B>class<BR>
 * <B>概要说明：</B>desc<BR>
 *
 * @author 00165650
 * @version 1.0
 * @since 2025/3/18
 */
@Tag(name = "helloDto", description = "helloDto描述")
@Data
public class HelloDto {
    @Schema(name = "name", description = "名字", required = true)
    private String name;

}
