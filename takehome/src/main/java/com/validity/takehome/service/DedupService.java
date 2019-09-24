package com.validity.takehome.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class DedupService {

    public List<String[]> readFile(InputStream in) {

        List<String[]> res = new ArrayList<>();

        try(CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(in))).build()) {

            Iterator<String[]> iterator = csvReader.iterator();
            while(iterator.hasNext()) {

                String[] currentLine = iterator.next();

                res.add(currentLine);

            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        res.remove(0);

        return res;
    }


    public List<String[]> checkDuplicate(List<String[]> list) {

        List<String[]> listOfDuplicates = new ArrayList<>();

        if(list == null) {
            return listOfDuplicates;
        }

        Collections.sort(list, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if(o1[1].equals(o2[1])) {
                    return 0;
                }
                String[] strs = new String[]{o1[1], o2[1]};
                Arrays.sort(strs);
                return strs[0].equals(o1[1]) ? -1 : 1;
            }
        });


        for (int i = 0; i < list.size() - 1; i++) {

            String[] record1 = list.get(i);
            String[] record2 = list.get(i + 1);

            int len = record1.length;
            int count = 0;

            for (int k = 1; k < len; k++) {

                int distance = 0;
                double similarity = 1;

                String word1 = record1[k];
                String word2 = record2[k];

                if (word1 == null || word2 == null) {

                } else {
                    distance = wordDistance(word1.toLowerCase(), word2.toLowerCase());
                    similarity = 1 - ((double) distance) / Math.max(word1.length(), word2.length());
                }

                if (similarity > 0.7) {
                    count++;
                }

            }

            if(count > len / 2) {
                if(!listOfDuplicates.contains(record1)) {
                    listOfDuplicates.add(record1);
                }
                if(!listOfDuplicates.contains(record2)) {
                    listOfDuplicates.add(record2);
                }
            }
        }



        return listOfDuplicates;
    }

    private int wordDistance(String word1, String word2) {

        if(word1.length() == 0 && word2.length() == 0) {
            return 0;
        }else if(word1.length() == 0) {
            return 0;
        }else if(word2.length() == 0) {
            return 0;
        }

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = 0;
                } else if(i == 0 || j == 0) {
                    dp[i][j] = (i == 0) ? j : i;
                } else {
                    if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    }
                }
            }
        }

        return dp[m][n];

    }


}
