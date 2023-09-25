package squareonex.evetrackerdata.services.map;

import squareonex.evetrackerdata.model.User;
import squareonex.evetrackerdata.services.UserService;

import java.util.Set;

public class UserServiceMapImpl extends AbstractMapService<User, Long> implements UserService {
    private Long key = 0L;

    @Override
    public Set<User> findAll() {
        return super.findAll();
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
    public void delete(User object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
