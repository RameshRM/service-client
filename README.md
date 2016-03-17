# service-client

Expressive client library for Java using (async-http-client)[https://github.com/AsyncHttpClient/async-http-client]

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
