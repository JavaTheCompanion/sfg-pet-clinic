package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    T findById(ID id) {
        return this.map.get(id);
    }

    Set<T> findAll() {
        return new HashSet<>(this.map.values());
    }

    T save(T object) {

        if (object != null) {
            if (object.getId() == null) {
                object.setId(this.getNextId());
            }

            this.map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    void deleteById(ID id) {
        this.map.remove(id);
    }

    private Long getNextId() {
        final OptionalLong max = map.keySet().stream().mapToLong(v -> v).max();
        return max.isPresent() ? max.getAsLong() + 1 : 1;
    }

}
