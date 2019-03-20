package data.mappers;

import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public interface DataMapperInterface<T> {
    int add(T t) throws Exception;
    T get(T t) throws Exception;
    List<T> getAll() throws Exception;
}
