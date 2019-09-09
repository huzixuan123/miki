package cn.miki.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties(prefix = "miki.abc")
public class MyProperties {

    private String username;

    private String password;

    @NestedConfigurationProperty
    private SonProperties sonProperties;
}
