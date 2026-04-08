package org.example.cs151assignment5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MLDataStore {
    private final String fileName;

    public MLDataStore(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, int[]> load() {
        Map<String, int[]> patternMap = new HashMap<>();
        File file = new File(fileName);

        if (!file.exists()) {
            return patternMap;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(":", 2);
                if (parts.length != 2) {
                    continue;
                }

                String key = parts[0];
                String[] nums = parts[1].split(",");
                if (nums.length != 3) {
                    continue;
                }

                int[] counts = new int[3];
                counts[0] = Integer.parseInt(nums[0]);
                counts[1] = Integer.parseInt(nums[1]);
                counts[2] = Integer.parseInt(nums[2]);

                patternMap.put(key, counts);
            }
        } catch (IOException e) {
            System.out.println("Error loading ML data: " + e.getMessage());
        }

        return patternMap;
    }

    public void save(Map<String, int[]> patternMap) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, int[]> entry : patternMap.entrySet()) {
                int[] counts = entry.getValue();
                writer.println(entry.getKey() + ":" + counts[0] + "," + counts[1] + "," + counts[2]);
            }
        } catch (IOException e) {
            System.out.println("Error saving ML data: " + e.getMessage());
        }
    }
}