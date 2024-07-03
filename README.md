# ecoflow-api-java
<b>List your EcoFlow gadgets and get / set parameters.</b>  
Version: 0.0.2 quick and dirty to see something working without a gazillion prerequisites or dependencies  
Functionality: currently minimal, but showing principles in Java; lists EcoFlow devices based on access key; Quota() functions commented out as they throw errors; will check later  
[EcoFlow documentation](https://developer-eu.ecoflow.com/us/document/introduction) for own functionality, e.g. [PowerStream micro inverter](https://developer-eu.ecoflow.com/us/document/powerStreamMicroInverter) or [Delta 2 Max battery](https://developer-eu.ecoflow.com/us/document/delta2max)
    
Prerequisite: Apply for developer status at [EcoFlow](https://developer.ecoflow.com/) to get access and security keys to the EcoFlow cloud.   
Optional Prerequisite to make things exactly work as advertized here: VS Code with MS Java extensions  
Dependency: some Java runtime default installation (my sheer luck: I had some 2021 version installed)
    
Before I had tried with the Home Assistant Supervised behemoth + Community Store & PyScript extensions + lengthy code for EcoFlow, needing a dedicated Pi, nothing else on it and the kitchen-sink.  
  
<b>You should have seen my jaw drop when this ran (listed my EcoFlow gadgets) at the first attempt.</b>  
You get the Java sample code [(original here)](https://developer-eu.ecoflow.com/us/document/download) when you `git clone https://github.com/ai-bits/ecoflow-api-java` (or download the repo-zip)  
`cd ecoflow-api-java`  
`code .` (example without VS Code may follow)  
With `code .` (mind the period) you start VS Code in the Present (Current) Working Directory  
Open ecoflow-open-demo\src\main\java\com\ecoflow\iot\open\Main.java in the Code editor, paste AccessKey and SecretKey and save.  
Run > Start debugging  
Fingers crossed...
    
<b>Find Python and shell script variants in [Mark Hicks' repo](https://github.com/Mark-Hicks/ecoflow-api-examples)</b>
    
<b>Check out my [AI-bits.org info site for no-nonsense professional AI application](https://ai-bits.org/index-en.html)</b>  
