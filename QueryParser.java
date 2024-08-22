/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


/**
 *
 * @author niketh
 */
package com.mycompany.queryparser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class QueryParser {
    public static ArrayList<String>stepOneTaskOne(String word)
    {
        ArrayList<String> array = new ArrayList<>();
        String[] words = word.split(" ");
        Collections.addAll(array,words);
        return array;
    }
    public static String stepOneTaskTwo(String[] words){
        String answer = "";
        for(String word : words){
            if(word.contains(".csv") || word.contains(".txt")){
                return word;
            }
        }
        return "";
    }
    public static String stepOneTaskThree(String[] words){
        String answer = "";
        for(String word : words){
            if(word.toLowerCase().equals("where"))
                  break;
            else
                answer = answer + word + " ";
        }
        
        return answer.trim();
    }
    public static ArrayList<String> stepTwoTaskOne(String[] words){
        ArrayList<String> array = new ArrayList();
        String[] fields = words[1].split(",");
        Collections.addAll(array,fields);
        return array;
    }
    public static String stepTwoTaskTwo(String[] words){
        String filterPart = "";
        boolean flag = false;
        for(String word : words){
            if(word.equals("where")){
                flag = true;
            }
            if(word.equals("order") || word.equals("group")){
                flag = false;
                break;
            }
            if(flag == true && word.equals("where") == false){
                filterPart = filterPart + word +" ";
            }
        }
        return filterPart.trim();
    }
    public static ArrayList<String> stepTwoTaskThree(String[] words){
        String[] filterParts = stepTwoTaskTwo(words).split(" ");
        ArrayList<String> conditions = new ArrayList();
        String temp = "";
        int index = 0;
        while(index!=filterParts.length){
            if(filterParts[index].equals("and") || filterParts[index].equals("or") || filterParts[index].equals("not")){
                conditions.add(temp.trim());
                temp = "";
            }
            else
                temp = temp+filterParts[index]+" ";
            index++;
        }
        conditions.add(temp.trim());
        return conditions;
    }
    public static ArrayList<String> stepThreeTaskOne(String[] words){
        ArrayList<String> array = new ArrayList();
        for(String word : words){
            if("andornot".contains(word) && array.contains(word) == false)
                array.add(word);
        }
        return array;
    }
    public static String stepThreeTaskTwo(String[] words){
        int i = 0;
        for(i = 0;i<words.length;i++){
            if(words[i].equals("order"))
                break;
        }
        if(i+2>=words.length)
            return "Invalid query or query doesn't contain order by";
        return words[i+2];
    }
    public static String stepFourTaskOne(String[] words){
        int i = 0;
        for(i = 0;i<words.length;i++){
            if(words[i].equals("group"))
                break;
        }
        if(i+2>=words.length)
            return "Invalid query or query doesn't contain group by";
        return words[i+2];
    }
    public static ArrayList<String> stepFourTaskTwo(String[] words){
        String[] aggregate = words[1].split(",");
        ArrayList<String> array = new ArrayList();
        for(String word : aggregate){
            if(word.startsWith("avg") || word.startsWith("max") || word.startsWith("min") || word.startsWith("count") ||word.startsWith("sum")){
                array.add(word);
            }
        }
        return array;
    }
    public static void printWords(ArrayList<String> array){
        for(String arr : array)
            System.out.println(arr);
    }
    public static void main(String[] args) {
        String query = "select sum(name),age,max(country) from ipl.csv where season > 2014 and city = 'Bangalore' or age = 40 group by name";
        String[] words = query.split(" ");
        //printWords(stepOneTaskOne(query)); 
        //System.out.println(stepOneTaskTwo(words));
        //System.out.println(stepOneTaskThree(words));
        //printWords(stepTwoTaskOne(words));
        //System.out.println(stepTwoTaskTwo(words));
        //printWords(stepTwoTaskThree(words));
        //printWords(stepThreeTaskOne(words));
        //System.out.println(stepFourTaskOne(words));
        //printWords(stepFourTaskTwo(words));
    }
}
