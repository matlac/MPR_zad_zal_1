package dao;

import domain.Groups;

import java.util.List;

public interface IGroupsRepository extends IRepository<Groups> {

    public List<Groups> withName(String name);

}
