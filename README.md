# ecoflow-api-java
Enumerate your EcoFlow gadgets and get / set parameters.  
Version: 0.0.1 quick and dirty to see something working without a gazillion prerequisites or dependencies  
Prerequisite: apply for developer status  
Optional Prerequisite to make things exactly work as advertized here: VS Code with MS Java extensions  
Dependency: some Java runtime default installation (my sheer luck: I had some 2021 version installed)  
Before I had tried with the Home Assistant Supervised behemoth + Community Store & PyScript extensions + lengthy code for EcoFlow, needing a dedicated Pi, nothing else on it and the kitchen-sink.  
<br>
You should have seen my jaw drop when this ran (listed my EcoFlow gadgets) at the first attempt.  
Prerequisite: Apply for developer status at [EcoFlow](https://developer.ecoflow.com/) to get access and security keys to the EcoFlow cloud.  
You get the Java sample code when you `git clone https://github.com/ai-bits/ecoflow-api-java` (or download the repo-zip)  
`cd ecoflow-api-java`  
`code .`  
With `code .` (mind the period) you start VS Code in the Present (Current) Working Directory  
Open ecoflow-open-demo\src\main\java\com\ecoflow\iot\open\Main.java in the Code editor, paste AccessKey and SecretKey and save.  
Run > Start debugging  
Fingers crossed...  
