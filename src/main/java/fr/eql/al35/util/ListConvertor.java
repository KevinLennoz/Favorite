package fr.eql.al35.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListConvertor {

	public static <T> List<T> convertToList(Set<T> set) {
	    return new ArrayList<>(set);
	}

}
