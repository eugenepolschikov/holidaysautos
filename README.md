# holidaysautos
Test framework for e2e functional UI and API tests. 
Based on java 18+, maven, TestNG and Allure for reporting.

### Setup pre-requisites. 
1. Install java jdk#18 https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html

2. Install maven https://maven.apache.org/download.cgi

3. download chromedriver   https://developer.chrome.com/docs/chromedriver/downloads
NOTE: should be in match with current chrome browser version which is >=136.

### Tests run,  command-line
`mvn clean test -D"testng.suite.name=functional"  -Pprod`
Executing TestNG test suite  src/test/java/com/holidaysautos/testngconfigs/functional.xml against 
prod environment profile.


### HTML report. Publishing the report. 
Install allure command-line tool (per the guidelines)
`https://allurereport.org/docs/install/` 

Post-run test results are in the folder
`[project_root]\target` 
open the directory, and execute `allure serve` command. 
