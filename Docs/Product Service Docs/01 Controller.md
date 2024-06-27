### 1. `@RestController`

When `@RestController` is used:

- **Purpose**: It combines `@Controller` and `@ResponseBody`. It is typically used to create RESTful web services that directly return JSON or XML responses.
    
- **Response Handling**:
    
    - Methods annotated with `@GetMapping`, `@PostMapping`, etc., return the actual response data (objects, lists, maps, etc.) serialized into JSON/XML format.
    - There is no view resolution involved; the returned data is directly serialised into the HTTP response body.
    
### 2. `@Controller`

When `@Controller` is used:

- **Purpose**: It marks the class as a Spring MVC controller, suitable for traditional web applications where views (HTML pages or templates) are rendered.
    
- **Response Handling**:
    
    - Methods typically return a view name (String) or `ModelAndView` object.
    - The returned view name is resolved by a view resolver configured in Spring MVC, which determines the actual view (HTML page or template) to render.

While `@RestController` is specifically designed for building RESTful APIs where JSON (or XML) responses are common, `@Controller` can also handle JSON responses with a bit of configuration. Here's how you can achieve this:

#### 1. Return `@ResponseBody` from Methods

In Spring MVC with `@Controller`, you can use `@ResponseBody` annotation on methods to indicate that the return value should be serialized directly into the response body as JSON (or XML). Here’s an example:


	@Controller
	@RequestMapping("/products")
	public class ProductController {

	    private final ProductService productService;
	
	    @Autowired
	    public ProductController(ProductService productService) {
	        this.productService = productService;
	    }
	
	    @GetMapping
	    @ResponseBody // Serialize the return value directly to the response body
	    public List<ProductDto> getAllProducts() {
	        return productService.getAllProducts();
	    }
	}

**Explanation**:

- `@ResponseBody` instructs Spring to serialize the return value of `getAllProducts()` directly into the HTTP response body.
- The `ProductDto` objects returned by `getAllProducts()` will be automatically converted to JSON (or XML) format and sent as the response.

#### 2. Using `ResponseEntity`

Alternatively, you can also use `ResponseEntity` to have more control over the response status and headers:


	@Controller
	@RequestMapping("/products")
	public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
	    }
	}

**Explanation**:

- `ResponseEntity` allows you to specify the response status (e.g., `HttpStatus.OK`) and return any object as the response body.
- Spring will automatically serialize the `List<ProductDto>` to JSON (or XML) format before sending it as the response.


### `@RequestMapping` 
annotation in Spring MVC (and Spring Boot) is used to map web requests to handler methods in controller classes. It provides a flexible way to define the URL path and HTTP methods (GET, POST, PUT, DELETE, etc.) that a controller method should handle.

	@PostMapping  
	public ProductDto addProduct(@RequestBody ProductDto productDto) {  
	    return productService.addProduct(productDto);  
	}  
	
	// method level configuration of requestMapping
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)  
	public ProductDto replaceProduct(@PathVariable long id, @RequestBody ProductDto productDto) {  
	    return productService.replaceProduct(id, productDto);  
	}


