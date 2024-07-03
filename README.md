# ecoflow-api-java
<b>Enumerate your EcoFlow gadgets and get / set parameters.</b>  
Version: 0.0.1 quick and dirty to see something working without a gazillion prerequisites or dependencies<br>  
Prerequisite: Apply for developer status at [EcoFlow](https://developer.ecoflow.com/) to get access and security keys to the EcoFlow cloud.   
Optional Prerequisite to make things exactly work as advertized here: VS Code with MS Java extensions  
Dependency: some Java runtime default installation (my sheer luck: I had some 2021 version installed)<br>  
Before I had tried with the Home Assistant Supervised behemoth + Community Store & PyScript extensions + lengthy code for EcoFlow, needing a dedicated Pi, nothing else on it and the kitchen-sink.  
<br>
<b>You should have seen my jaw drop when this ran (listed my EcoFlow gadgets) at the first attempt.</b>  
You get the Java sample code [(original here)](https://developer-eu.ecoflow.com/us/document/download) when you `git clone https://github.com/ai-bits/ecoflow-api-java` (or download the repo-zip)  
`cd ecoflow-api-java`  
`code .` (Example without VS Code may follow)  
With `code .` (mind the period) you start VS Code in the Present (Current) Working Directory  
Open ecoflow-open-demo\src\main\java\com\ecoflow\iot\open\Main.java in the Code editor, paste AccessKey and SecretKey and save.  
Run > Start debugging  
Fingers crossed...  
