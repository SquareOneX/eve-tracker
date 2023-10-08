package squareonex.evetrackerdata.services.datajpa;

import squareonex.evetrackerdata.model.User;
import squareonex.evetrackerdata.repositories.UserRepository;
import squareonex.evetrackerdata.services.UserService;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> findAll() {
        Set<User> set = new HashSet<>();
        for (User user : userRepository.findAll()) {
            set.add(user);
        }
        return set;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Iterable<User> saveAll(Iterable<User> iterator) {
        return userRepository.saveAll(iterator);
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
