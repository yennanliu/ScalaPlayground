# Download API
- Implement download API via scala
```
                      --msg queue1-->  
client -> API service --msg queue2-->  website or cache <---> DB
                      --msg queue3-->

      <-----------------result----------------------------
```


### Components
- API app
- msg queue
- cache
- DB