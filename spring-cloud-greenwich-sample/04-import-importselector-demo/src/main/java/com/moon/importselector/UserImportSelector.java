package com.moon.importselector;

import com.moon.config.UserConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义导入器，实现 ImportSelector 接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-10 16:33
 * @description
 */
public class UserImportSelector implements ImportSelector {

    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 返回需要加载的配置类名称数组，此示例直接返回UserConfiguration类全限定名
        return new String[]{UserConfiguration.class.getName()};
    }

}
