package com.innovoak.util.webhelpers;

import java.io.Serializable;
import java.util.List;

import com.innovoak.util.webhelpers.criteria.Criteria;

// This interface acts as a method of data transfer from the client on the server
public interface Repository<T extends Serializable> {

	// GET VALUES
	default void get(Serializable id) throws Exception {
		getAllBy(Criteria.equalsCriteria("id", id));
	}

	default List<T> getAll() throws Exception {
		return getAllBy(Criteria.NoneHolder.NONE);
	}

	List<T> getAllBy(Criteria criteria) throws Exception;

	/// DELETE VALUES
	default void delete(Serializable id) throws Exception {
		deleteAllBy(Criteria.equalsCriteria("id", id));
	}

	default void deleteAll() throws Exception {
		deleteAllBy(Criteria.NoneHolder.NONE);
	}

	void deleteAllBy(Criteria criteria) throws Exception;

	// INSERT VALUES
	void insert(T object) throws Exception;

	void insertAll(List<T> objects) throws Exception;

	// UPDATE VALUES
	default void update(T object, Serializable id) throws Exception {
		updateAllBy(object, Criteria.equalsCriteria("id", id));
	}

	default void updateAll(T object) throws Exception {
		updateAllBy(object, Criteria.NoneHolder.NONE);
	}

	void updateAllBy(T object, Criteria criteria) throws Exception;
}
