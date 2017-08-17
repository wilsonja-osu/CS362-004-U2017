/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.Assert;
import junit.framework.TestCase;





/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {     
     System.out.println("---- Manual Testing ----");
     UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
     
     // loop to test each of the provided URLs
     for (int i = 0; i < testUrls.length; i++) {
       Boolean testResult = urlVal.isValid(testUrls[i].item);
       Boolean expectedResult = testUrls[i].valid;
       
       System.out.println(testUrls[i].item + " is " + testResult + ", expected " + expectedResult + ": "
            + ((testResult == expectedResult) ? "PASS" : "FAIL"));
     }
   }
   
   
   public void testYourFirstPartition()
   {
     System.out.println();
     System.out.println("---- First Partition Testing (Schemes) ----");
     UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);     
     
     // loop to test each of the possible Schemes
     for (int i = 0; i < testSchemes.length; i++) {
       String testUrl = testSchemes[i].item + "www.amazon.com";
       Boolean testResult = urlVal.isValid(testSchemes[i].item + "www.amazon.com");
       Boolean expectedResult = testSchemes[i].valid;
       
       System.out.println(testUrl + " is " + testResult + ", expected " + expectedResult + ": "
                      + ((testResult == expectedResult) ? "PASS" : "FAIL"));
     }
   }
   
   public void testYourSecondPartition(){
     System.out.println();
     System.out.println("---- Second Partition Testing (Domains) ----");
     UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);     
     
     // loop to test each of the possible Domains
     for (int i = 0; i < testDomains.length; i++) {
       String testUrl = "http://" + testDomains[i].item;
       Boolean testResult = urlVal.isValid(testUrl);
       Boolean expectedResult = testDomains[i].valid;
       
       System.out.println(testUrl + " is " + testResult + ", expected " + expectedResult + ": "
                      + ((testResult == expectedResult) ? "PASS" : "FAIL"));
     }
   }
   
   public void testYourThirdPartition(){
     System.out.println();
     System.out.println("---- Third Partition Testing (Ports) ----");
     UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);     
     
     // loop to test each of the possible Ports
     for (int i = 0; i < testPorts.length; i++) {
       String testUrl = "http://www.amazon.com" + testPorts[i].item;
       Boolean testResult = urlVal.isValid(testUrl);
       Boolean expectedResult = testPorts[i].valid;
       
       System.out.println(testUrl + " is " + testResult + ", expected " + expectedResult + ": "
                      + ((testResult == expectedResult) ? "PASS" : "FAIL"));
     }
   }
   
   public void testYourFourthPartition(){
     System.out.println();
     System.out.println("---- Fourth Partition Testing (Paths) ----");
     UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);     
     
     // loop to test each of the possible Paths
     for (int i = 0; i < testPaths.length; i++) {
       String testUrl = "http://www.amazon.com:80" +  testPaths[i].item;
       Boolean testResult = urlVal.isValid(testUrl);
       Boolean expectedResult =  testPaths[i].valid;
       
       System.out.println(testUrl + " is " + testResult + ", expected " + expectedResult + ": "
                      + ((testResult == expectedResult) ? "PASS" : "FAIL"));
     }
   }
   
   public void testYourFifthPartition(){
     System.out.println();
     System.out.println("---- Fifth Partition Testing (Queries) ----");
     UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
     
     // loop to test each of the possible Queries
     for (int i = 0; i < testQueries.length; i++) {
       String testUrl = "http://www.amazon.com:80/path" +  testQueries[i].item;
       Boolean testResult = urlVal.isValid(testUrl);
       Boolean expectedResult =  testQueries[i].valid;
       
       System.out.println(testUrl + " is " + testResult + ", expected " + expectedResult + ": "
                      + ((testResult == expectedResult) ? "PASS" : "FAIL"));
     }
   }
   
   
   public void testIsValid()
   {
     System.out.println();
     System.out.println("---- Full URL Testing ----");
     UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
     
     // multiple loops to test every combination of URL partitions
     for (int i = 0; i < testSchemes.length; i++) {
       for (int j = 0; j < testDomains.length; j++) {
         for (int k = 0; k < testPorts.length; k++) {
           for (int m = 0; m < testPaths.length; m++) {
             for (int n = 0; n < testQueries.length; n++) {
               String testUrl = testSchemes[i].item + testDomains[j].item + testPorts[k].item
                        + testPaths[m].item + testQueries[n].item;
               Boolean testResult = urlVal.isValid(testUrl);
               Boolean expectedResult = testSchemes[i].valid && testDomains[j].valid && testPorts[k].valid
                            && testPaths[m].valid && testQueries[n].valid;
               
               // only print the failed tests
               if (testResult != expectedResult) {
                 System.out.println(testUrl + " is " + testResult + ", expected " + expectedResult + ": "
                                      + ((testResult == expectedResult) ? "PASS" : "FAIL"));
               }
             }
           }
         }
       }
     }
   }
   
   public void testAnyOtherUnitTest()
   {
     // Unit Test 1: use of legal local URLs
     System.out.println();
     System.out.println("---- Unit test for local URLs ----");
     UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_LOCAL_URLS);
     Boolean localResult1 = urlVal.isValid("http://hostname");
     Boolean localResult2 = urlVal.isValid("http://localhost");
     Boolean expectedLocal1 = true, expectedLocal2 = true;
     
     System.out.println("http://hostname is " + localResult1 + ", expected " + expectedLocal1 + ": "
                          + ((localResult1 == expectedLocal1) ? "PASS" : "FAIL"));
     System.out.println("http://localhost is " + localResult2 + ", expected " + expectedLocal2 + ": "
                           + ((localResult2 == expectedLocal2) ? "PASS" : "FAIL"));
     
     // Unit Test 2: use of legal country code
     System.out.println();
     System.out.println("---- Unit test for country code ----");
     UrlValidator urlVal1 = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
     
     // test for multiple known valid country codes, range across the alphabet
     Boolean ccResult1 = urlVal1.isValid("http://www.amazon.ca");
     Boolean ccResult2 = urlVal1.isValid("http://www.amazon.co.it");
     Boolean ccResult3 = urlVal1.isValid("http://www.amazon.co.jp");
     Boolean ccResult4 = urlVal1.isValid("http://www.amazon.co.uk");
     Boolean expectedCC1 = true, expectedCC2 = true, expectedCC3 = true, expectedCC4 = true;
    
     
     System.out.println("http://www.amazon.ca is " + ccResult1 + ", expected " + expectedCC1 + ": "
                             + ((ccResult1 == expectedCC1) ? "PASS" : "FAIL"));
     System.out.println("http://www.amazon.co.it is " + ccResult2 + ", expected " + expectedCC2 + ": "
                                + ((ccResult2 == expectedCC2) ? "PASS" : "FAIL"));
     System.out.println("http://www.amazon.co.jp is " + ccResult3 + ", expected " + expectedCC3 + ": "
                                +((ccResult3 == expectedCC3) ? "PASS" : "FAIL"));
     System.out.println("http://www.amazon.co.uk is " + ccResult4 + ", expected " + expectedCC4 + ": "
                                + ((ccResult4 == expectedCC4) ? "PASS" : "FAIL"));
     
     // Unit Test 3: port length
     System.out.println();
     System.out.println("---- Unit test for port length ----");
     UrlValidator urlVal2 = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
     
     // run tests for increasing length of port number (valid port values: 1 - 65535)
     Boolean plResult1 = urlVal2.isValid("http://www.amazon.com:1");
     Boolean plResult2 = urlVal2.isValid("http://www.amazon.com:12");
     Boolean plResult3 = urlVal2.isValid("http://www.amazon.com:123");
     Boolean plResult4 = urlVal2.isValid("http://www.amazon.com:1234");
     Boolean plResult5 = urlVal2.isValid("http://www.amazon.com:12345");
     Boolean plResult6 = urlVal2.isValid("http://www.amazon.com:123456");
     Boolean plResult7 = urlVal2.isValid("http://www.amazon.com:1234567");
     Boolean expectedPL1 = true, expectedPL2 = true, expectedPL3 = true, expectedPL4 = true,
         expectedPL5 = true, expectedPL6 = false, expectedPL7 = false;
     
     System.out.println("http://www.amazon.com:1 is " + plResult1 + ", expected " + expectedPL1 + ": "
                              + ((plResult1 == expectedPL1) ? "PASS" : "FAIL"));
     System.out.println("http://www.amazon.com:12 is " + plResult2 + ", expected " + expectedPL2 + ": "
                             + ((plResult2 == expectedPL2) ? "PASS" : "FAIL"));
     System.out.println("http://www.amazon.com:123 is " + plResult3 + ", expected " +  expectedPL3 + ": "
                              + ((plResult3 ==  expectedPL3) ? "PASS" : "FAIL"));
     System.out.println("http://www.amazon.com:1234 is " + plResult4 + ", expected " + expectedPL4 + ": "
                               + ((plResult4 == expectedPL4) ? "PASS" : "FAIL"));
     System.out.println("http://www.amazon.com:12345 is " + plResult5 + ", expected " + expectedPL5 + ": "
                              + ((plResult5 == expectedPL5) ? "PASS" : "FAIL"));
     System.out.println("http://www.amazon.com:123456 is " + plResult6 + ", expected " + expectedPL6 + ": "
                               + ((plResult6 == expectedPL6) ? "PASS" : "FAIL"));
     System.out.println("http://www.amazon.com:1234567 is " + plResult7 + ", expected " + expectedPL7 + ": "
                                + ((plResult7 == expectedPL7) ? "PASS" : "FAIL"));
   }
   
   
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
  static ResultPair[] testUrls = {
       new ResultPair("http://www.amazon.com", true),
       new ResultPair("http://www.google.com:80", true),
       new ResultPair("http://255.255.255.255", true),
       new ResultPair("https://teach.engr.oregonstate.edu/teach.php", true),
       new ResultPair("ftp://example.com/pub/file.txt", true),
       new ResultPair("http://www.oregonstate.edu", true),
       new ResultPair("http://128.29.201.45", true),
       new ResultPair("http://www.google.com:5000", true),
       new ResultPair("http://www.facebook.com", true),
       new ResultPair("http://www.yahoo.com", true),
       new ResultPair("http:\\\\www.amazon.com", false),
       new ResultPair("http://www.google.com:abc", false),
       new ResultPair("http://256.256.256.256", false),
       new ResultPair("htpp://www.amazon.com", false),
       new ResultPair("http://www.google.com:abc", false),
       new ResultPair("htt://www.amazon.com", false),
       new ResultPair("://www.google.com", false),
       new ResultPair("http://abcdefg", false),
       new ResultPair("http://...../../..", false),
       new ResultPair("http://www.apple.", false)
   };
   
   static ResultPair[] testSchemes = {
       new ResultPair("", true),
       new ResultPair("http://", true),
       new ResultPair("ftp://", true),
       new ResultPair("h3t://", true),
       new ResultPair("://", false),
       new ResultPair("http:\\\\", false),
       new ResultPair("http:/", false),
       new ResultPair("htpp://", false)
   };
   
   static ResultPair[] testDomains = {
       new ResultPair("www.amazon.com", true),
       new ResultPair("go.com", true),
       new ResultPair("128.29.255.90", true),
       new ResultPair("amazon.co.jp", true),
       new ResultPair("", false),
       new ResultPair(".abcd", false),
       new ResultPair("128.29.278.90", false),
       new ResultPair("google.amazon", false)
   };
   
   static ResultPair[] testPorts = {
       new ResultPair("", true),
       new ResultPair(":80", true),
       new ResultPair(":808", true),
       new ResultPair(":8080", true),
       new ResultPair(":65535", true),
       new ResultPair(":80ag", false),
       new ResultPair(":com", false),
       new ResultPair(":-55", false),
   };
   
   static ResultPair[] testPaths = {
       new ResultPair("/path", true),
       new ResultPair("/tre231", true),
       new ResultPair("/path/morepath", true),
       new ResultPair("/$path$/#$@path", true),
       new ResultPair("/..", false),
       new ResultPair("/#/path", false),
       new ResultPair("///../.", false)
   };
   
   static ResultPair[] testQueries = {
       new ResultPair("?search", true),
       new ResultPair("?search=item&value", true),
       new ResultPair("", true),
       new ResultPair("...", false),
       new ResultPair("$search", false),
       new ResultPair("=item&search", false)
   };
}
