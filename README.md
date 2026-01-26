## Step 1 Start Zipkin run following command in cmd

1. Start Docker Desktop application first
2. Now run below command in cmd

```
    docker run -d -p 9411:9411 openzipkin/zipkin:latest
```

## Step 2 , Run all  MicroService in the order -

- Make sure to connect with Internet as it is required throughout the project

1. Discovery / Eureka Server
2. Config Server
3. Inventory , Order Service
4. Api Gateway


- hit any request to any microservice via Postman and then trace the response time
  at http://localhost:9411/zipkin/

## Step 3 Paste the below code inside the file logstash.conf (create if not exists) located at C:\Spring Boot ELK Stack\logstash-9.2.4\config\logstash.conf

```
    input {
      file {
        type => "log"
        path => "C:/Users/MANISH/Desktop/My Files/Spring Boot/eCommerce/logs/*/application-*.log"
        start_position => "beginning"
    
        # This line is CRITICAL for testing. It tells Logstash NOT to remember
        # where it left off, so it reads the logs from the start every time you restart.
        sincedb_path => "NUL"
      }
    }
    
    output {
      stdout {
        codec => rubydebug
      }
    
      elasticsearch {
        hosts => ["https://localhost:9200"]
        index => "spring-boot-logs-%{+YYYY.MM.dd}"
    
        user => "elastic"
        password => "Manish@2009"
    
        # CHANGE THIS LINE:
        ssl_enabled => true
    
        # This one is already correct:
        ssl_verification_mode => "none"
      }
    }
```

## Step 4 For Logs , Run ELK Stack by pasting the commands below in cmd terminal -

```
    @echo off
    TITLE ELK Stack Tabbed Loader
    
    :: Using 'wt' (Windows Terminal) to launch all three in tabs.
    :: Fixed Logstash path to point exactly to logstash.conf
    
    wt -w 0 ^
     nt --title "Elasticsearch" -d "C:\Spring Boot ELK Stack\elasticsearch-9.2.4\bin" cmd /k "elasticsearch.bat" ; ^
     nt --title "Kibana" -d "C:\Spring Boot ELK Stack\kibana-9.2.4\bin" cmd /k "timeout /t 20 && kibana.bat" ; ^
     nt --title "Logstash" -d "C:\Spring Boot ELK Stack\logstash-9.2.4\bin" cmd /k "timeout /t 40 && logstash.bat -f \"C:\Spring Boot ELK Stack\logstash-9.2.4\config\logstash.conf\""
    
    exit
```

- To get Token paste the command in terminal

```
    cd /d "C:\Spring Boot ELK Stack\elasticsearch-9.2.4\bin"
    
    elasticsearch-create-enrollment-token.bat --scope kibana --url https://127.0.0.1:9200
```

login at Browser at and paste the token so generated above via terminal

```
    http://localhost:5601/?code=510625 
```

# Enjoy Coding !