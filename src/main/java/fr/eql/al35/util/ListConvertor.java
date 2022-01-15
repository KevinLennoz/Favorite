package fr.eql.al35.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListConvertor {
	
	public static <T> List<T> convertToList(Set<T> set) {
	    return set.stream().collect(Collectors.toList());
	}

}
