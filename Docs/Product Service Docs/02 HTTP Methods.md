HTTP defines a set of request methods to indicate the desired action to be performed for a given resource. Here are the details of the main HTTP methods:

### GET

- **Purpose**: Retrieve data from the server.
- **Characteristics**:
    - Safe: Doesn't alter the state of the resource.
    - Idempotent: Multiple identical requests have the same effect as a single request.
    - Cacheable: Responses can be cached.
- **Usage**: Used to request data from a specified resource.
- **Example**:
        
    `GET /products/123 HTTP/1.1`
    

### HEAD

- **Purpose**: Similar to GET, but only retrieves the headers.
- **Characteristics**:
    - Safe: Doesn't alter the state of the resource.
    - Idempotent: Multiple identical requests have the same effect as a single request.
    - Cacheable: Responses can be cached.
- **Usage**: Used to check what a GET request will return before actually making a GET request, often for testing or validation.
- **Example**:
    
    `HEAD /products/123 HTTP/1.1`
    

### POST

- **Purpose**: Send data to the server to create a new resource.
- **Characteristics**:
    - Not Safe: Alters the state of the resource.
    - Not Idempotent: Multiple identical requests will create multiple resources.
    - Not Cacheable.
- **Usage**: Used to submit data to the server, for example, creating a new user or submitting a form.
- **Example**:
    
    `POST /products HTTP/1.1 Content-Type: application/json  {   "name": "New Product",   "price": 19.99 }`
    

### PUT

- **Purpose**: Update a resource or create a new resource if it doesn't exist.
- **Characteristics**:
    - Not Safe: Alters the state of the resource.
    - Idempotent: Multiple identical requests have the same effect as a single request.
    - Not Cacheable.
- **Usage**: Used to update a resource or create a new one with a known URL.
- **Example**:
    
    `PUT /products/123 HTTP/1.1 Content-Type: application/json  {   "name": "Updated Product",   "price": 29.99 }`
    

### PATCH

- **Purpose**: Apply partial modifications to a resource.
- **Characteristics**:
    - Not Safe: Alters the state of the resource.
    - Not Idempotent (strictly): Multiple identical requests might not result in the same state as a single request, depending on the changes.
    - Not Cacheable.
- **Usage**: Used to apply partial updates to a resource.
- **Example**:
    `PATCH /products/123 HTTP/1.1 Content-Type: application/json  {   "price": 25.99 }`
    

### DELETE

- **Purpose**: Delete a resource.
- **Characteristics**:
    - Not Safe: Alters the state of the resource.
    - Idempotent: Multiple identical requests have the same effect as a single request.
    - Not Cacheable.
- **Usage**: Used to delete a resource identified by the URL.
- **Example**:`DELETE /products/123 HTTP/1.1`
    

#### OPTIONS 

**Purpose**: The OPTIONS method is used to describe the communication options for the target resource. It can also be used to check the capabilities of the web server and to identify which HTTP methods are supported by the resource.

**Characteristics**:

- **Safe**: The OPTIONS method is safe because it does not modify the state of the resource.
- **Idempotent**: The OPTIONS method is idempotent, meaning that multiple identical requests have the same effect as a single request.
- **Not Cacheable**: Responses to OPTIONS requests are not typically cached.

**Common Uses**:

1. **Preflight Requests in CORS**: When a web application running in a browser makes a cross-origin request, the browser sends an OPTIONS request to the server to check if the actual request is safe to send. This is known as a preflight request in Cross-Origin Resource Sharing (CORS).
2. **Discover Server Capabilities**: The OPTIONS method can be used by clients to discover the HTTP methods and other capabilities supported by the server for a specific resource.

**Example Usage**:

When a client sends an OPTIONS request to a server, it might look like this:

http

Copy code

`OPTIONS /products/123 HTTP/1.1 Host: example.com`

The server might respond with headers indicating the allowed methods:

`HTTP/1.1 204 No Content Allow: GET, POST, PUT, DELETE, OPTIONS, PATCH`
    

#### TRACE 

**Purpose**: The TRACE method is used to perform a message loop-back test along the path to the target resource. The TRACE method allows the client to see what is being received at the other end of the request chain and use that data for diagnostic purposes.

**Characteristics**:

- **Safe**: The TRACE method is safe because it does not modify the state of the resource.
- **Idempotent**: The TRACE method is idempotent, meaning that multiple identical requests have the same effect as a single request.
- **Not Cacheable**: Responses to TRACE requests are not typically cached.

**Common Uses**:

1. **Diagnostic Tool**: The TRACE method is mainly used for debugging and diagnostic purposes. It helps in tracing the request route and identifying any intermediate changes or modifications made to the request.
2. **Security Considerations**: The TRACE method can potentially be exploited by malicious users in Cross-Site Tracing (XST) attacks. Therefore, many servers disable or restrict the TRACE method.

**Example Usage**:

When a client sends a TRACE request to a server, it might look like this:

`TRACE /products/123 HTTP/1.1 Host: example.com`

The server would respond with the request message it received:

`HTTP/1.1 200 OK Content-Type: message/http  TRACE /products/123 HTTP/1.1 Host: example.com`


"Do not forget to disable the trace in application"