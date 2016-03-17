# service-client

Expressive client library for Java using (https://github.com/AsyncHttpClient/async-http-client)[async-http-client]

This library is inspired from the **NodeJS** styled callback invocation of **HttpClient** calls.


## Installation

Add dependency to the package `service-client`

```xml
<dependency>
  <groupId>com.rameshrm</groupId>
  <artifactId>service-client</artifactId>
  <version>1.0-SNAPSHOT</version>

</dependency>
```

## Usage

### Define Configuration properties for the Service definition in `\src\main\resources\application.json` file.

> Configuration Example

```json
{
  "services": {
    "foo": {
      "method": "GET",
      "basePath": "http://127.0.0.1:3000/v1/ffs",
      "headers": {

      },
      "params": {

      },
      "instruments": []
    }
  }
}

```



```Java

ServiceClient.init();
ServiceClient client = new ServiceClient("foo");
client.execute(HttpVerbs.GET, "/all").end((e, r) -> callback((Exception) e, (ServiceResponse) r));


public boolean callback(Exception e, ServiceResponse r) {
		assertTrue(e == null);
		assertTrue(r == null && r.statusCode == 200);
		return r != null;
}

```
