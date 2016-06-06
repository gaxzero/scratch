# Guess My Number

Install a JDK.
Install Maven if you don't have it already
Download or clone from GitHub
Run 'mvn clean install verify site'
'java -jar target/org.collegeboard-XXXXXX.jar'

To generate EvoSuite tests : mvn -DmemoryInMB=2000 -Dcores=2 evosuite:generate evosuite:export

Some fun resources worth considering:
http://pages.bloomsbury.com/favouritenumber
