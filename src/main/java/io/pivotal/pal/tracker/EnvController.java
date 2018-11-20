package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private final String port;
    private final String memLimit;
    private final String index;
    private final String addr;
    public EnvController(
            @Value("${PORT:NOT SET}") String port,
            @Value("${MEMORY_LIMIT:NOT SET}") String memLimit,
            @Value("${CF_INSTANCE_INDEX:NOT SET}") String index,
            @Value("${CF_INSTANCE_ADDR:NOT SET}") String addr)
    {
        this.port = port;
        this.memLimit = memLimit;
        this.index = index;
        this.addr = addr;

    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> env = new HashMap<>();

        env.put("PORT", port);
        env.put("MEMORY_LIMIT", memLimit);
        env.put("CF_INSTANCE_INDEX", index);
        env.put("CF_INSTANCE_ADDR", addr);

        return env;
    }
}
