package dao;

import domain.EnumerationValue;

import java.util.List;

public interface IEnumerationValueRepository extends IRepository<EnumerationValue> {

    public List<EnumerationValue> withName(String name);
    public List<EnumerationValue> withIntKey(int key, String name);
    public List<EnumerationValue> withStringKey(String key, String name);

}
