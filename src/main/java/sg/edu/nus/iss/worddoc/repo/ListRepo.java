package sg.edu.nus.iss.worddoc.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ListRepo {
    @Autowired
    @Qualifier("redisTemplateString")
    private RedisTemplate<String, String> template;

    public void setList(String key, String value, int index) {
        template.opsForList().set(key, index, value);
    }

    public void addToBackList(String key, String value) {
        template.opsForList().rightPush(key, value);
    }
    public void addAllToBackList(String key, List<String> values) {
        template.opsForList().rightPushAll(key, values);
    }

    public void addToFrontList(String key, String value) {
        template.opsForList().leftPush(key, value);
    }

    public List<String> getList(String key) {
        return template.opsForList().range(key, 0, -1); //key,start, end
    }

    public void removeFromList(String key, String value) {
        template.opsForList().remove(key, 0, value);
    }
    public Long sizeOfList(String key) {
        return template.opsForList().size(key);
    }
    public Long indexOfList(String key, String value) {
        return template.opsForList().indexOf(key, value);
    }
    public Object getValueAtIndex(String key, int index) {
        return template.opsForList().index(key, index);
    }
    public void deleteList(String key) {
        template.delete(key);
    }
}
