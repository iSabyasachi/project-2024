Set Up:
    1. Set Up Project Directory
        mkdir software-design
        cd software-design
    2. Initialize a Node.js project for TypeScript:
        npm init -y
    3. Create a Maven project for Java:
        mvn archetype:generate -DgroupId=com.example -DartifactId=java -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
    4. Install TypeScript and related tools:
        npm install typescript ts-node @types/node --save-dev
    5. Initialize a tsconfig.json file:
        npx tsc --init
    6. To run the TypeScript code:
        npx ts-node src/index.ts
    7. To run the Java application:
        cd java
        mvn spring-boot:run
        cd ..

    8. Build both typescript and java projects
        Make the script executable:
            chmod +x build-all.sh
        Execute the script using:
            ./build-all.sh
        Run it with debug output to see each command:
            bash -x build-all.sh