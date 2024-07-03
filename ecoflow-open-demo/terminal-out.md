```
# f5 start debugging original 20240702-1100
(ecoflow) C:\dl\ecoflow-dev> cmd /C ""C:\Program Files\Java\jre1.8.0_291\bin\java.exe" -agentlib:jdwp=transport=dt_socket,server=n,suspend=y,address=localhost:55025 -cp C:\Users\guent\AppData\Local\Temp\cp_1oortfbamidhzh9p9tf3sqldl.jar com.ecoflow.iot.open.Main "
response: getMQTTCertification|{"code":"0","message":"Success","data":{"certificateAccount":"open-cf413a4b31a64ef8a8960727a14e3b23","certificatePassword":"c054fe85acb144beb1f8fead89b00a59","url":"mqtt-e.ecoflow.com","port":"8883","protocol":"mqtts"},"eagleEyeTraceId":"ea1a2a582217199099879053016d0007","tid":""}
#ok
response: deivceList|{"code":"0","message":"Success","data":[{"sn":"HW51ZEH4SF5H0990","online":1,"productName":"PowerStream"},{"sn":"R351ZEB4HF3A0115","online":1,"productName":"DELTA 2 Max"}],"eagleEyeTraceId":"ea1a2a5c2b17199099882742951d0007","tid":""}
#no permission
response: setQuota|{"code":"8512","message":"no permission to do it","eagleEyeTraceId":"ea1a2a5c2b17199099886653076d0007","tid":""}
response: getQuota|{"code":"8512","message":"no permission to do it","eagleEyeTraceId":"ea1a2a582217199099889753387d0007","tid":""}
response: getAllQuota|{"code":"8521","message":"signature is wrong","eagleEyeTraceId":"ea1a2a582217199099892563492d0007","tid":""}
#error maybe due to network 
ERROR: JDWP Unable to get JNI 1.2 environment, jvm->GetEnv() return code = -2
JDWP exit error AGENT_ERROR_NO_JNI_ENV(183):  [util.c:840]

# f5 20240702-1135 ok
#moved up getMQTTCertification() right after main()
#main(): commented out (execution of) setQuota() getQuota() getAllQuota()
```