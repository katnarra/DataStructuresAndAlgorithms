package oy.interact.tira.model;

import java.util.Comparator;

import oy.interact.tira.student.CoderFullNameComparator;
import oy.interact.tira.student.CoderNameComparator;

public enum CoderSortOrder {
	FULLNAME_ASCENDING ("Full name (ascending)"),
	FULLNAME_DESCENDING ("Full name (descending)"),
	CODER_NAME_ASCENDING ("Coder name (ascending)"),
	CODER_NAME_DESCENDING ("Coder name (descending)");

	private String name;

	CoderSortOrder(final String name) {
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public static CoderSortOrder getOrder(String usingStringValue) {
		switch (usingStringValue) {
			case "Full name (ascending)":
				return FULLNAME_ASCENDING;
			case "Full name (descending)":
				return FULLNAME_DESCENDING;
			case "Coder name (ascending)":
				return CODER_NAME_ASCENDING;
			case "Coder name (descending)":
				return CODER_NAME_DESCENDING;
			default:
				return null;
		}
	}

	public boolean isReversed(CoderSortOrder another) {
		return ( 
			(this == FULLNAME_ASCENDING && another == FULLNAME_DESCENDING) || 
			(this == FULLNAME_DESCENDING && another == FULLNAME_ASCENDING) || 
			(this == CODER_NAME_ASCENDING && another == CODER_NAME_DESCENDING) || 
			(this == CODER_NAME_DESCENDING && another == CODER_NAME_ASCENDING)
		);
	}

	public static final String [] getNames() {
		return new String [] {
			FULLNAME_ASCENDING.getName(),
			FULLNAME_DESCENDING.getName(),
			CODER_NAME_ASCENDING.getName(),
			CODER_NAME_DESCENDING.getName()
		};
	}


	public Comparator<Coder> getComparator(CoderSortOrder this) {
		switch (this) {
			case FULLNAME_ASCENDING:
				CoderFullNameComparator ascending = new CoderFullNameComparator();
				return ascending;
			case FULLNAME_DESCENDING:
				CoderFullNameComparator descending = new CoderFullNameComparator();
				return descending.reversed();
			case CODER_NAME_ASCENDING:
				CoderNameComparator ascending_code = new CoderNameComparator();
				return ascending_code;
			case CODER_NAME_DESCENDING:
				CoderNameComparator descending_code = new CoderNameComparator();
				return descending_code.reversed();
		}
		return null;
	}
}
