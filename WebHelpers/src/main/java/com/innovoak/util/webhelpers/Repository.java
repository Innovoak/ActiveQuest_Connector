package com.innovoak.util.webhelpers;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.criteria.PredicateCriteria;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.comparing.EqualsCriteria;

// This interface acts as a method of data transfer from the client on the server
public interface Repository<T extends Serializable> {

	// GET VALUES
	default void get(Serializable id) throws Exception {
		getAllBy(new SelectCriteria(new EqualsCriteria("id", id)));
	}

	default List<T> getAll() throws Exception {
		return getAllBy(new SelectCriteria(Criteria.NoneHolder.NONE));
	}

	List<T> getAllBy(SelectCriteria criteria) throws Exception;

	/// DELETE VALUES
	default void delete(Serializable id) throws Exception {
		deleteAllBy(new EqualsCriteria("id", id));
	}

	default void deleteAll() throws Exception {
		deleteAllBy(Criteria.NoneHolder.NONE);
	}

	void deleteAllBy(PredicateCriteria criteria) throws Exception;

	// INSERT VALUES
	default void insert(T object) throws Exception {
		insertAll(Collections.singletonList(object));
	}

	void insertAll(List<T> objects) throws Exception;

	// UPDATE VALUES
	default void update(T object, Serializable id) throws Exception {
		updateAllBy(object, new EqualsCriteria("id", id));
	}

	default void updateAll(T object) throws Exception {
		updateAllBy(object, Criteria.NoneHolder.NONE);
	}

	void updateAllBy(T object, PredicateCriteria criteria) throws Exception;
}
