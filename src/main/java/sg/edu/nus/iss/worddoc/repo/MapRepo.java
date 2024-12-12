package sg.edu.nus.iss.worddoc.repo;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MapRepo {
    @Autowired
    private RedisTemplate<String, Object> template;

    public void setMap(String key, String mapkey, Object value) {
        template.opsForHash().put(key, mapkey, value);
    }

    public Object getMap(String key, Object value) {
        return template.opsForHash().get(key, value);
    }

    public boolean hasKey(String key, String mapkey) {
        return template.opsForHash().hasKey(key, mapkey);
    }

    public Set<Object> allKeys(String key) {
        return template.opsForHash().keys(key);
    }
    
    public List<Object> allValues(String key) {
        return template.opsForHash().values(key);
    }
    public Long sizeOfMap(String key) {
        return template.opsForHash().size(key);
    }

    public void deleteMap(String key, Object value) {
        template.opsForHash().delete(key, value);
    }
}
