package squareonex.evetrackerdata.services.map;

import squareonex.evetrackerdata.model.User;
import squareonex.evetrackerdata.services.UserService;

import java.util.HashSet;
import java.util.Set;

public class UserServiceMapImpl extends AbstractMapService<User, Long> implements UserService {
    private Long key = 0L;

    @Override
    public Set<User> findAll() {
        return super.findAll();
    }

    @Override
    public long count() {
        return super.count();
    }

    @Override
    public User findById(Long id) {
        return super.findById(id);
    }

    @Override
    public User save(User object) {
        if (object.getId() == null){
            while (map.containsKey(key))
                key++;
            object.setId(key);
            return super.save(key, object);
        }else {
            return super.save(object.getId(), object);
        }
    }
    @Override
    public Iterable<User> saveAll(Iterable<User> iterator) {
        Set<User> set = new HashSet<>();
        for (User activity : iterator) {
            set.add(this.save(activity));
        }
        return set;
    }

    @Override
    public void delete(User object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
