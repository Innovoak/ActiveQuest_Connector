package com.innovoak.util.webhelpers.criteria;

import java.util.List;

// Acts as a branch for a tree of criterias
public interface BranchCriteria extends Criteria {

	// Get child criterias
	public List<Criteria> getChildCriteria();
}
