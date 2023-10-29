package com.balamurugan;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DateAndTimeMapStreamTest {

    public static void main(String[] args) {

        // Format specification
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss z");

        // Sample data
        List<Map<String, Object>> listOfMaps = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("dateTime", ZonedDateTime.now().minusDays(1).format(formatter));  // Yesterday
        map1.put("otherKey", "value1");
        listOfMaps.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("dateTime", ZonedDateTime.now().plusDays(1).format(formatter));  // Tomorrow
        map2.put("otherKey", "value2");
        listOfMaps.add(map2);

        // The target date and time to compare against
        ZonedDateTime targetDateTime = ZonedDateTime.now();

        // Use streams to filter out the maps with dateTime earlier than targetDateTime
        List<Map<String, Object>> filteredMaps = listOfMaps.stream()
                .filter(m -> m.containsKey("dateTime") &&
                        isBeforeTarget((String) m.get("dateTime"), targetDateTime, formatter))
                .collect(Collectors.toList());

        // Print the filtered maps
        filteredMaps.forEach(System.out::println);

        // Use streams to filter out the maps with dateTime AFTER the targetDateTime
        List<Map<String, Object>> filteredMaps2 = listOfMaps.stream()
                .filter(m -> m.containsKey("dateTime") &&
                        isAfterTarget((String) m.get("dateTime"), targetDateTime, formatter))
                .collect(Collectors.toList());

// Print the filtered maps
        filteredMaps2.forEach(System.out::println);
    }

    private static boolean isBeforeTarget(String dateTimeStr, ZonedDateTime targetDateTime, DateTimeFormatter formatter) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.parse(dateTimeStr, formatter);
            return dateTime.isBefore(targetDateTime);
        } catch (Exception e) {
            return false;  // If parsing fails, treat as not before target
        }
    }

    private static boolean isAfterTarget(String dateTimeStr, ZonedDateTime targetDateTime, DateTimeFormatter formatter) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.parse(dateTimeStr, formatter);
            return dateTime.isAfter(targetDateTime);
        } catch (Exception e) {
            return false;  // If parsing fails, treat as not after target
        }
    }

}
