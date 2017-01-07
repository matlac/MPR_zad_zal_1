package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.mappers.IMapResultSetToEntity;
import dao.uow.IUnitOfWork;
import dao.uow.IUnitOfWorkRepository;
import domain.Entity;
import domain.IHaveId;

public abstract class RepositoryBase<TEntity extends IHaveId>
	implements IUnitOfWorkRepository, IRepository<TEntity>{

	protected Connection connection;
	protected Statement createTable;
	protected PreparedStatement insert;
	protected PreparedStatement delete;
	protected PreparedStatement update;
	protected PreparedStatement get;
	protected PreparedStatement list;
	protected IMapResultSetToEntity<TEntity> mapper;
	protected IUnitOfWork uow;
	
	public RepositoryBase(Connection connection,
			IMapResultSetToEntity<TEntity> mapper,
			IUnitOfWork uow) {

		try {
			this.uow=uow;
			this.mapper = mapper;
			this.connection = connection;
			createTable = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if (tableName().equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			if (!tableExists)
				createTable.executeUpdate(createTableSql());

			insert = connection.prepareStatement(insertSql());
			delete = connection.prepareStatement(deleteSql());
			update = connection.prepareStatement(updateSql());
			get = connection.prepareStatement(getSql());
			list = connection.prepareStatement(listSql());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void persistDelete(Entity p) {
		try {
			delete.setInt(1, p.getId());
			delete.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void delete(TEntity entity) {
		uow.markAsDeleted((Entity)entity, this);
	}

	public void persistUpdate(Entity p) {
		try {
			setUpdateQuery((TEntity)p);
			update.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(TEntity entity) {
		uow.markAsChanged((Entity)entity, this);
	}

	public void persistAdd(Entity p) {
		try {
			setInsertQuery((TEntity)p);
			insert.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void add(TEntity entity) {
		uow.markAsNew((Entity)entity, this);
	}

	public List<TEntity> getAll() {
		List<TEntity> el = new ArrayList<TEntity>();

		try {
			ResultSet rs = list.executeQuery();

			while (rs.next()) {
				el.add(mapper.map(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return el;
	}

	public TEntity get(int id) {

		try {
			get.setInt(1, id);
			ResultSet rs = get.executeQuery();
			rs.next();
			return mapper.map(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected TEntity getOne(PreparedStatement statement){
		try {
			ResultSet rs = statement.executeQuery();
			rs.next();
			return mapper.map(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected List<TEntity> getList(PreparedStatement statement){
		List<TEntity> el = new ArrayList<TEntity>();

		try {
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				el.add(mapper.map(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return el;
	}

	protected String deleteSql() {
		return "DELETE FROM " + tableName() + " WHERE id=?";
	}

	protected String getSql() {
		return "SELECT * FROM " + tableName() + " WHERE id = ?";
	}

	protected String listSql() {
		return "SELECT * FROM " + tableName();
	}

	protected abstract void setUpdateQuery(TEntity p) throws SQLException;

	protected abstract void setInsertQuery(TEntity p) throws SQLException;

	protected abstract String tableName();

	protected abstract String createTableSql();

	protected abstract String insertSql();

	protected abstract String updateSql();

}
