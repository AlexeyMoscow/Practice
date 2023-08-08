package utils;

import com.google.common.collect.Ordering;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortChecker {

    public static boolean isIdSortedByAsc(List<Integer> usersId){
        return Ordering.natural().isOrdered(usersId);
    }

    public static boolean isIdSortedByDesc(List<Integer> usersId){
        return Ordering.natural().reverse().isOrdered(usersId);
    }

    public static boolean isSurnameSortedByAcs(List<String> usersSurname){
        List<String> tempSurnameList = usersSurname.stream().sorted().collect(Collectors.toList());
        return usersSurname.equals(tempSurnameList);
    }

    public static boolean isSurnameSortedByDesc(List<String> usersSurname){
        List<String> tempSurnameList = usersSurname.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return usersSurname.equals(tempSurnameList);
    }


}
