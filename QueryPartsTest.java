/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import static com.mycompany.queryparser.QueryParser.*;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author niketh
 */
public class QueryPartsTest {
    
    public QueryPartsTest() {
    }
    ArrayList<String> array;
    ArrayList<String> compare;
    String query;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        array = new ArrayList();
        compare = new ArrayList();
    }
    
    @AfterEach
    public void tearDown() {
        array = null;
        compare = null;
        query = "";
    }

    
     @Test
     public void s1t1Positive() {
        query = "select * from ipl.csv where season > 2014 and city = Bangalore or age = 40 group by name";
        array =  stepOneTaskOne(query);
        Collections.addAll(compare,"select","*","from","ipl.csv","where","season",">","2014","and","city","=","Bangalore","or","age","=","40","group","by","name");
        assertIterableEquals(array,compare);
     }
     @Test
     public void s1t2Positive() {
        query = "select sum(name),age,max(country) from file_name.txt where season > 2014 and city = Bangalore or age = 40 group by name";
        assertEquals("file_name.txt",stepOneTaskTwo(query.split(" ")));
     }
     
     @Test
     public void s1t3Positive() {
        query = "select * from file_name.txt where season > 2014 and city = Bangalore or age = 40 group by name";
        assertEquals("select * from file_name.txt",stepOneTaskThree(query.split(" ")));
     }
     
     @Test
     public void s2t1Positive() {
        query = "select city,winner,player_match from ipl.csv where season > 2014 and city = 'Bangalore'";
        array = stepTwoTaskOne(query.split(" "));
        Collections.addAll(compare,"city","winner","player_match");
        assertIterableEquals(array,compare);
     }
     
     @Test
     public void s2t2Positive() {
        query = "select * from ipl.csv where season > 2014 and city = 'Bangalore' or age >= 30 and people between 30 and 40 group by percentage";
        assertEquals("season > 2014 and city = 'Bangalore' or age >= 30 and people between 30 and 40",stepTwoTaskTwo(query.split(" ")));
     }
     
     @Test
     public void s2t3Positive(){
        query = "select * from ipl.csv where season > 2014 and city = 'Bangalore' or age >= 30 group by percentage";
        array = stepTwoTaskThree(query.split(" "));
        Collections.addAll(compare,"season > 2014","city = 'Bangalore'","age >= 30");
        assertIterableEquals(array,compare);
     }
     
     @Test
     public void s3t1Positive(){
        query = "select * from ipl.csv where season > 2014 and city = 'Bangalore' or age >= 30 group by percentage";
        array = stepThreeTaskOne(query.split(" "));
        Collections.addAll(compare,"and","or");
        assertIterableEquals(array,compare);
     }
     
     @Test
     public void s3t2Positive(){
        query = "select * from ipl.csv where season > 2014 and city = 'Bangalore' or age >= 30 order by percentage";
        assertEquals("percentage",stepThreeTaskTwo(query.split(" ")));
     }
     
     @Test
     public void s3t2Negative(){
        query = "select * from ipl.csv where season > 2014 and city = 'Bangalore' or age >= 30 group by percentage";
        assertEquals("Invalid query or query doesn't contain order by",stepThreeTaskTwo(query.split(" ")));
     }
     
     @Test
     public void s4t1Positive(){
        query = "select * from ipl.csv where season > 2014 and city = 'Bangalore' or age >= 30 group by some_random_name";
        assertEquals("some_random_name",stepFourTaskOne(query.split(" ")));
     }
     
     @Test
     public void s4t1Negative(){
        query = "select * from ipl.csv where season > 2014 and city = 'Bangalore' or age >= 30 order by percentage";
        assertEquals("Invalid query or query doesn't contain group by",stepFourTaskOne(query.split(" ")));
     }
     
     @Test
     public void s4t2Positive(){
        query = "select avg(people),sum(total),max(numbers),name,count(value) from ipl.csv where season > 2014 and city = 'Bangalore' or age >= 30 order by percentage";
        array = stepFourTaskTwo(query.split(" "));
        Collections.addAll(compare,"avg(people)","sum(total)","max(numbers)","count(value)");
        assertEquals(array,compare);
     }
     
     @Test
     public void s4t2Negative(){
        query = "select * from ipl.csv where season > 2014 and city = 'Bangalore' or age >= 30 order by percentage";
        array = stepFourTaskTwo(query.split(" "));
        assertIterableEquals(compare,array);
     }
}
