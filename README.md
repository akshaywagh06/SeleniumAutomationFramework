# Selenium WebDriver Methods

- [Get Methods](#get-methods)
- [Conditional Methods](#conditional-methods)
- [Browser Methods](#browser-methods)
- [Wait Methods](#wait-methods)
  - [Sleep](#sleep)
  - [Implicit Wait](#implicit-wait)
  - [Explicit Wait](#explicit-wait)
  - [Fluent Wait](#fluent-wait)

---

## Get Methods

Get methods are accessed through the **WebDriver instance**. They work on **web pages**, not on web elements.

| Method 						| Description 				|
|-------------------------------|---------------------------|
| `get("URL")` 				| Opens the URL in the browser. |
| `getTitle()` 				| Returns the title of the page. |
| `getCurrentUrl()` 			| Returns the URL of the page. |
| `getPageSource()` 			| Returns the source code of the page. |
| `getWindowHandle()` 		| Returns the ID of the single browser window. |
| `getWindowHandles()` 		| Returns the IDs of multiple browser windows. |

```java
WebDriver driver = new ChromeDriver();

// get("URL") -- opens the URL in the browser
driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

// getTitle() -- returns the title of the page
System.out.println(driver.getTitle());

// getCurrentUrl() -- returns the URL of the page
System.out.println(driver.getCurrentUrl());

// getPageSource() -- returns the source code of the page
System.out.println(driver.getPageSource());

// getWindowHandle() -- returns the ID of the single browser window
String windowId = driver.getWindowHandle();   // e.g. CAA2B958EA718D3F55228AEBECD4819C (different every time)
System.out.println(windowId);

// getWindowHandles() -- returns the IDs of multiple browser windows
driver.findElement(By.linkText("OrangeHRM, Inc")).click();

Set<String> windowIds = driver.getWindowHandles();
System.out.println(windowIds);   // e.g. [50603C56C4256654946315CE994C9643, FFC235AB453C0302824F4927A1BFE441]
```

---

## Conditional Methods

Conditional methods are accessed through a **web element** and return a **`true` / `false`** value.

| Method 					| Description 	|
|---------------------------|-----------------|
| `isDisplayed()` 		| Checks the displayed status of the element. |
| `isEnabled()` 			| Checks whether the element is enabled or disabled. |
| `isSelected()`			| Checks whether the element is selected or not, e.g. radio buttons, checkboxes, dropdowns. |

**isDisplayed()**

```java
WebDriver driver = new ChromeDriver();
driver.get("https://kalyanifoods.co/");

driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
boolean logo = driver.findElement(By.xpath("//img[@alt='Kalyani Foods Logo']")).isDisplayed();
System.out.println("Logo is displayed: " + logo);
```

**isEnabled()**

```java
WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search products...']"));
boolean status = search.isEnabled();
System.out.println("Search box is enabled: " + status);
```

**isSelected()**

```java
driver.get("https://demoqa.com/radio-button");

WebElement yes = driver.findElement(By.xpath("(//input[@name='like'])[1]"));
WebElement impressive = driver.findElement(By.xpath("(//input[@name='like'])[2]"));

System.out.println("Before selecting............");
System.out.println(yes.isSelected());
System.out.println(impressive.isSelected());

System.out.println("After selecting Yes..............");
yes.click();
System.out.println(yes.isSelected());
System.out.println(impressive.isSelected());
```

---

## Browser Methods

| Method 			| Description |
|-------------------|-------------|
| `close()` 		| Closes the currently focused browser window (tab). |
| `quit()` 		| Closes all the browser windows and ends the session. |

---

## Wait Methods

### Sleep

`Thread.sleep()` is a **Java method**, not a Selenium method. It can be used in Selenium to pause the execution of the code.

| Advantages | Disadvantages |
|------------|---------------|
| Easy to use. | If the time mentioned is not sufficient to load the web page, an exception occurs. |
| | It always waits for the maximum time, which reduces the performance of the script. |
| | It needs to be used multiple times. |

### Implicit Wait

```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
```

| Advantages | Disadvantages |
|------------|---------------|
| Written once, as a single statement. | If the time is not sufficient, an exception occurs. |
| Does not wait for the maximum time if the element is available. | |
| Applicable for elements. | |

### Explicit Wait

- It is **condition-based**, so it works more effectively.
- It waits for the condition to be true.
- Multiple statements are needed for multiple elements.

| Condition | Purpose |
|-----------|---------|
| `visibilityOf()` | Waits until an existing WebElement becomes visible. |
| `visibilityOfElementLocated()` | Waits until the element found by the locator becomes visible. |
| `presenceOfAllElementsLocatedBy()` | Waits until all matching elements are present in the DOM. |
| `visibilityOfAllElementsLocatedBy()` | Waits until all matching elements are visible. |
| `visibilityOfAnyElementsLocatedBy()` | Waits until at least one matching element is visible. |
| `numberOfElementsToBe()` | Waits until the number of matching elements equals the expected count. |
| `stalenessOf()` | Waits until an element is detached from the DOM. |
| `attributeToBe()` | Waits until an element's attribute has the expected value. |
| `textToBePresentInElement()` | Waits until specific text appears in an existing WebElement. |
| `invisibilityOf()` | Waits until a WebElement becomes invisible or stale. |
| `urlContains()` | Waits until the URL contains the specified text. |
| `urlToBe()` | Waits until the URL matches the expected URL. |

```java
WebDriver driver = new ChromeDriver();
WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));   // declaration
driver.get("URL");
driver.manage().window().maximize();

WebElement txtUsername = myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("xpath")));

txtUsername.sendKeys("Username");
```

### Fluent Wait

```java
WebDriver driver = new ChromeDriver();

Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)   // declaration
        .withTimeout(Duration.ofSeconds(30))               // maximum time to wait for a condition
        .pollingEvery(Duration.ofSeconds(5))               // time interval between checking the condition
        .ignoring(NoSuchElementException.class);           // ignores specified exceptions during waiting

WebElement element = wait.until(d ->                       // waits until the given condition becomes true
        d.findElement(By.name("q"))
);

element.sendKeys("Selenium Java");
```

| Method | Purpose |
|--------|---------|
| `withTimeout()` | Maximum time to wait for a condition. |
| `pollingEvery()` | Time interval between checking the condition. |
| `ignoring()` | Ignores specified exceptions during waiting. |
| `until()` | Waits until the given condition becomes true. |