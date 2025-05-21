package client.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class PageParser {
    public static ArrayList<TableElement> parsePage(String response) {
        if (response == null || response.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String dataToParse = response;
        if (dataToParse.startsWith("NextPage ")) {
            dataToParse = dataToParse.substring("NextPage ".length());
        }

        return Arrays.stream(dataToParse.split("\\R"))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(line -> {
                    String[] parts = line.split("\\s+", 2);
                    if (parts.length == 2) {
                        try {
                            long id = Long.parseLong(parts[0]);
                            String name = parts[1];
                            return new TableElement(id, name);
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
