import com.eking.infra.cache.CustomCacheService;
import com.eking.web.WebApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * CustomCacheService 单元测试类
 */
@SpringBootTest(classes = WebApplication.class)
public class CaffeineCacheTest {

    @Autowired
    private CustomCacheService cacheService;

    /**
     * 测试带过期时间的缓存操作
     * 验证是否调用 put 并存储 ExpirableValue 对象
     */
    @Test
    void testExpireTime() throws Exception {
        String key = "testKey";
        Object value = "testValue";
        // Act
        cacheService.put(key, value, TimeUnit.SECONDS, 10);
        System.out.println((String) cacheService.get(key));
        System.out.println((String) cacheService.get(key));
        Thread.sleep(1000);
        System.out.println((String) cacheService.get(key));
        Thread.sleep(5000);
        System.out.println((String) cacheService.get(key));
        Thread.sleep(20000);
        System.out.println((String) cacheService.get(key));
        // Assert
    }

    @Test
    void testGet() {
        String key = "testKey";
        Object value = List.of("testValue1", "testValue2");
        // Act
        cacheService.put(key, value, TimeUnit.SECONDS, 10);
        System.out.println((List) cacheService.get(key));
        // Assert
    }

    @Test
    void testRemove() {
        String key = "testKey";
        Object value = List.of("testValue1", "testValue2");
        // Act
        cacheService.put(key, value, TimeUnit.SECONDS, 10);
        System.out.println((List) cacheService.get(key));
        cacheService.remove(key);
        System.out.println((List) cacheService.get(key));
        // Assert
    }

}
