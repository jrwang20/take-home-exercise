# take-home-exercise

## Introduction
This project is the take home exercise of validity software engineer intern interview

## How to use (please ensure the port 8099 has not been used)
1. git clone or download the project
2. go to the /takehome directory, then run the following codes to package
```Bash
cd /takehome
mvn clean package -Dmaven.test.skip=true
```
4. go to the /target directory, then run the following codes to start the server
```Bash
cd /target
java - jar takehome-0.0.1-SNAPSHOT.jar
```
4. the links of this project is http://127.0.0.1:8099/upload
5. upload the csv file, just with the same format and exact fields as normal.csv and advanced.csv
6. submit it, and in result page you will find the duplicated data and non-duplicated data
