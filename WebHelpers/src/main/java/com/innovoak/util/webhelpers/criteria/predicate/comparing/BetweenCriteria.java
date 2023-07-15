package com.innovoak.util.webhelpers.criteria.predicate.comparing;

import java.util.ArrayList;
import java.util.List;

public class BetweenCriteria implements ComparisonOperator {
    private static final long serialVersionUID = 1L;

    private String column;
    private Object lowerBound;
    private Object upperBound;
    private String embeddedSelectQuery; // Additional field for embedded SELECT

    public BetweenCriteria(String column, Object lowerBound, Object upperBound) {
        this.column = column;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    // Setter for embedded SELECT query
    public void setEmbeddedSelectQuery(String embeddedSelectQuery) {
        this.embeddedSelectQuery = embeddedSelectQuery;
    }

    public BetweenCriteria() {
    }

    @Override
    public String toString() {
        if (embeddedSelectQuery != null) {
            // If embedded SELECT query is provided, include it in the criteria
            return String.format("%s BETWEEN (%s) AND ?", column, embeddedSelectQuery);
        } else {
            return String.format("%s BETWEEN ? AND ?", column);
        }
    }

    @Override
    public List<Object> getParameters() {
        List<Object> parameters = new ArrayList<>();
        if (embeddedSelectQuery != null) {
            // If embedded SELECT query is provided, no need to include lowerBound
            parameters.add(upperBound);
        } else {
            parameters.add(lowerBound);
            parameters.add(upperBound);
        }
        return parameters;
    }
}