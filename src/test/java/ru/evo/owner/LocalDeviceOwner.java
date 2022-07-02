package ru.evo.owner;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties"
})

public interface LocalDeviceOwner extends Config {
    @DefaultValue("http://localhost:4723/wd/hub")
    String localURL();

    @DefaultValue("Pixel 4 API 30")
    String deviceName();

    @DefaultValue("11.0")
    String osVersion();

    @DefaultValue("android")
    String platformName();
}
