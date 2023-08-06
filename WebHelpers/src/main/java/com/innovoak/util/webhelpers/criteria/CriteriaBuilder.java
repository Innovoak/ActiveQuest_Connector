package com.innovoak.util.webhelpers.criteria;

// imports
import com.innovoak.util.webhelpers.criteria.predicate.comparing.*;
import com.innovoak.util.webhelpers.criteria.predicate.logical.*;
import com.innovoak.util.webhelpers.data.query.SelectQuery;

public class CriteriaBuilder implements Criteria{
	
    private Criteria criteria;

    // Constructor: Initializes the criteria as Criteria.NoneHolder.NONE
    public CriteriaBuilder() {
        criteria = Criteria.NoneHolder.NONE;
    }

    // Method for adding an "AND" condition to the existing criteria
    public CriteriaBuilder and(Criteria criteria) {
        // Combine the existing criteria and the new criteria with an "AND" operator
        this.criteria = new AndCriteria((PredicateCriteria)this.criteria, (PredicateCriteria)criteria);
        return this;
    }

    // Method for adding an "OR" condition to the existing criteria
    public CriteriaBuilder or(Criteria criteria) {
        // Combine the existing criteria and the new criteria with an "OR" operator
        this.criteria = new OrCriteria((PredicateCriteria)this.criteria, (PredicateCriteria)criteria);
        return this;
    }

    // Method for adding a "NOT" condition to the existing criteria
    public CriteriaBuilder not(Criteria criteria) {
        // Negate the new criteria with a "NOT" operator
        this.criteria = new NotCriteria((PredicateCriteria)criteria);
        return this;
    }

    // Methods for adding comparing operators to the criteria

    // Add an "EQUALS" condition to the existing criteria
    public CriteriaBuilder eq(String field, Object value) {
        this.criteria = new EqualsCriteria(field, value);
        return this;
    }

    // Add a "NOT EQUALS" condition to the existing criteria
    public CriteriaBuilder neq(String field, Object value) {
        this.criteria = new NotEqualCriteria(field, value);
        return this;
    }

    // Add a "LESS THAN" condition to the existing criteria
    public CriteriaBuilder lt(String field, Object value) {
        this.criteria = new LessThanCriteria(field, value);
        return this;
    }

    // Add a "LESS THAN OR EQUAL TO" condition to the existing criteria
    public CriteriaBuilder lte(String field, Object value) {
        this.criteria = new LessThanOrEqualTo(field, value);
        return this;
    }

    // Add a "GREATER THAN" condition to the existing criteria
    public CriteriaBuilder gt(String field, Object value) {
        this.criteria = new GreaterThanCriteria(field, value);
        return this;
    }

    // Add a "GREATER THAN OR EQUAL TO" condition to the existing criteria
    public CriteriaBuilder gte(String field, Object value) {
        this.criteria = new GreaterThanOrEqualToCriteria(field, value);
        return this;
    }

    // Add a "BETWEEN" condition to the existing criteria
    public CriteriaBuilder between(String field, Object lowerBound, Object upperBound) {
        this.criteria = new BetweenCriteria(field, lowerBound, upperBound);
        return this;
    }

    // Add an "IN" condition with a collection of values to the existing criteria
    public CriteriaBuilder in(String field, Iterable<Object> values) {
        this.criteria = new InCriteria(field, values);
        return this;
    }

    // Add an "IN" condition with a SELECT query to the existing criteria
    public CriteriaBuilder in(String field, SelectQuery selectQuery) {
        this.criteria = new InCriteria(field, selectQuery);
        return this;
    }

    // Add a "LIKE" condition to the existing criteria
    public CriteriaBuilder like(String field, String pattern) {
        this.criteria = new LikeCriteria(field, pattern);
        return this;
    }

    // Add a "NOT LIKE" condition to the existing criteria
    public CriteriaBuilder notLike(String field, String pattern) {
        this.criteria = new NotLikeCriteria(field, pattern);
        return this;
    }

    // Method to build and return the final criteria
    public Criteria build() {
        return criteria;
    }
}
