package guru.springframework.sfgpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    T findById(ID id) {
        return this.map.get(id);
    }

    Set<T> findAll() {
        return new HashSet<>(this.map.values());
    }

    T save(ID id, T object) {
        this.map.put(id, object);
        return object;
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    void deleteById(ID id) {
        this.map.remove(id);
    }

}
