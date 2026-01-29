# eCommerce App
#0. start the postgres Sql first in termux via command
```
   pg_ctl -D $PREFIX/var/lib/postgresql start
```
#!/bin/bash

# 1. Kill all existing Java processes and running Services automatically in order
```
    echo "🧹 Cleaning up existing Java processes..."
    pkill -f java
    sleep 2
    
    # Services List
    SERVICES=("Discovery_Service" "Api_Gateway" "Inventory_Service" "Order_Service" "config-server")
    
    # 3. Run mvn install for all services
    echo "📦 Building all projects (mvn install)..."
    for service in "${SERVICES[@]}"; do
    echo "🛠️ Installing $service..."
    (cd "$service" && mvn clean install -DskipTests)
    if [ $? -ne 0 ]; then
    echo "❌ Build failed for $service. Exiting."
    exit 1
    fi
    done
    
    echo "🚀 Starting Services in Order..."
    
    # 4. Run mvn spring-boot:run
    
    # 1. Config Server FIRST
    echo "⚙️ Launching Config Server..."
    (cd config-server && mvn spring-boot:run) &
    sleep 30 # Give it time to bind to its port
    
    # 2. Discovery Service SECOND
    echo "📡 Launching Discovery_Service..."
    (cd Discovery_Service && mvn spring-boot:run) &
    sleep 45
    
    # 3. Business Services
    echo "📦 Launching Inventory_Service..."
    (cd Inventory_Service && mvn spring-boot:run) &
    
    echo "🛒 Launching Order_Service..."
    (cd Order_Service && mvn spring-boot:run) &
    sleep 15
    
    # 4. API Gateway LAST
    echo "🛣️ Launching Api_Gateway..."
    (cd Api_Gateway && mvn spring-boot:run) &
    
    echo "✅ All services are up! Check http://localhost:8761"
    wait

```