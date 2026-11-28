# Locators in Selenium

- We can identify various elements on the web using **Locators**.
- Locators are addresses that identify a web element uniquely within the page.

## Types of Locators

```text
                         Locators
                            |
          +-----------------+------------------+
          |                                    |
     Basic Locators                    Customized Locators
          |                                    |
     +----+-----+                         +----+---------------------+
     |    |     |                         |        						 |
     ID  Name  LinkText              CSS Selector  						XPath
                |                         |        						 |
          PartialLinkText            +----+------+ 						 +------------+
                                     |         	|						 |            |
                                Tag+ID  Tag+Class 					Absolute    Relative
                                        Tag+Attribute 					XPath    XPath
                                        Tag+Class+ Attribute
     
     Other Basic Locators:
     - Class Name
     - TagName
```

### 1. Basic Locators
- ID
- Name
- LinkText
- PartialLinkText
- Class Name
- TagName

Example-

```java

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("https://demo.opencart.com/");
        driver.manage().window().maximize();

        // 1. ID
        driver.findElement(By.id("logo")).isDisplayed();

        // 2. Name
        driver.findElement(By.name("search")).sendKeys("ice");

        // 3. LinkText
        driver.findElement(By.linkText("Tablet"));

        // 4. PartialLinkText
        driver.findElement(By.partialLinkText("Tabl"));

        // 5. ClassName
        driver.findElement(By.className("class-name"));

        // 6. TagName
        driver.findElement(By.tagName("input"));

        // findElements() using ClassName
        List<WebElement> headerlinks =
                driver.findElements(By.className("list-inline-item"));

        System.out.println("Total Number of HeaderLinks = "
                + headerlinks.size());

    }
}
```

#### findElement() and findElements()

| findElement() | findElements() |
|---|---|
| Returns a single WebElement. | Returns a List of WebElements. |
| Returns the first matching element. | Returns all matching elements. |
| Throws NoSuchElementException if no match is found. | Returns an empty list if no match is found. |

##### Three Scenarios

**Scenario 1: Locator matches a single element**
- `findElement()` → Single WebElement
- `findElements()` → List containing one WebElement

**Scenario 2: Locator matches multiple elements**
- `findElement()` → First matching WebElement
- `findElements()` → All matching WebElements

**Scenario 3: Locator does not match any element**
- `findElement()` → NoSuchElementException
- `findElements()` → Empty List (0 elements)


### 2. Customized Locators

**A. CSS Selector**
- Tag and ID
- Tag and Class
- Tag and Attribute
- Tag, Class and Attribute

**B. XPath**
- Absolute XPath
- Relative XPath

####  
# Customized Locators in Selenium

## 1. CSS Selector

CSS Selector is a customized locator used to identify web elements using HTML tags, IDs, classes, and attributes.

### Types of CSS Selectors

**1. Tag and ID**
- Syntax: `tagname#idvalue`
- Example:
```java
driver.findElement(By.cssSelector("input#username"));
```

**2. Tag and Class**
- Syntax: `tagname.classname`
- Example:
```java
driver.findElement(By.cssSelector("input.form-control"));
```

**3. Tag and Attribute**
- Syntax: `tagname[attribute='value']`
- Example:
```java
driver.findElement(By.cssSelector("input[name='email']"));
```

**4. Tag, Class and Attribute**
- Syntax: `tagname.classname[attribute='value']`
- Example:
```java
driver.findElement(By.cssSelector("input.form-control[name='email']"));
```

| Locator | Syntax | Example |
|---|---|---|
| CSS Tag + ID | `tag#id` | `input#username` |
| CSS Tag + Class | `tag.class` | `input.form-control` |
| CSS Tag + Attribute | `tag[attr='value']` | `input[name='email']` |
| CSS Tag + Class + Attribute | `tag.class[attr='value']` | `input.form-control[name='email']` |

## 2. XPath

XPath stands for XML Path Language. It is used to navigate through HTML documents and identify web elements using their attributes and DOM structure.

### Types of XPath

**1. Absolute XPath**
- Starts from the root node of the HTML document.
- Uses a single slash (`/`).
- Represents the complete path to the target element.
- More fragile because changes in HTML structure can break it.
- Does not use any attributes.
- traverse through each node till it finds element
- 

Example:
```java
driver.findElement(By.xpath("/html/body/div/div/input"));
```

**2. Relative XPath**
- Can locate an element from anywhere in the HTML document.
- Starts with a double slash (`//`).
- Uses tags, attributes, and conditions to identify elements.
- Generally preferred because it is more flexible.
- Works with Attributes.
- directly jump and find the elements


Example:

```java
driver.findElement(By.xpath("//input[@id='username']"));

// 1. XPath with Single Attribute
// Use when one attribute is sufficient to identify an element.
driver.findElement(By.xpath("//input[@id='username']"));


// 2. XPath with Multiple Attributes
// Use when one attribute is not sufficient to identify an element.
// Matches the element using multiple attribute conditions.
driver.findElement(By.xpath("//input[@id='username'][@name='user']"));


// 3. XPath with AND Operator
// Use when both conditions must be satisfied.
// Returns elements matching both conditions.
driver.findElement(By.xpath("//input[@id='username' and @name='user']"));


// 4. XPath with OR Operator
// Use when either one of the conditions is sufficient.
// Returns elements matching at least one condition.
driver.findElement(By.xpath("//input[@id='username' or @name='user']"));


// 5. XPath with Inner Text (text())
// Use when an element needs to be identified using its exact text.
// Matches the element containing the specified text.
driver.findElement(By.xpath("//a[text()='Desktops']"));


// 6. XPath with contains()
// Use when the complete attribute value or text is not known.
// Matches elements containing the specified partial value.
driver.findElement(By.xpath("//input[contains(@id,'user')]"));


// 7. XPath with starts-with()
// Use when the starting part of an attribute value is fixed.
// Matches elements whose attribute value starts with the given text.
driver.findElement(By.xpath("//input[starts-with(@id,'user')]"));


// 8. Handling Dynamic Attributes

// Using Multiple Attributes
// Use when some attributes remain constant even if others change.
driver.findElement(By.xpath("//*[@id='username' and @name='user']"));

// Using contains()
// Use when part of the dynamic attribute value remains constant.
driver.findElement(By.xpath("//*[contains(@id,'user')]"));

// Using starts-with()
// Use when the starting characters of a dynamic attribute remain constant.
driver.findElement(By.xpath("//*[starts-with(@id,'user')]"));

// Using contains() with Text
// Use when the complete text is dynamic or partially known.
driver.findElement(By.xpath("//*[contains(text(),'Desktop')]"));


// 9. Chained XPath
// Use when an element is identified through its parent-child hierarchy.
// Navigates through the HTML structure to reach the target element.
driver.findElement(By.xpath("//div[@id='XYZ']/a/img"));
```



### XPath Axes in Selenium


```java
// 1. Parent
// Selects the immediate parent of the current element.
// Use when you know the child element and want to locate its parent.
driver.findElement(By.xpath("//input[@id='username']/parent::div"));


// 2. Child
// Selects only the direct child elements of the current element.
// Use when you want to locate elements directly inside a parent.
driver.findElement(By.xpath("//div[@id='user-details']/child::input"));


// 3. Ancestor
// Selects all parent, grandparent and higher-level elements.
// Use when you want to navigate upwards through the DOM hierarchy.
driver.findElement(By.xpath("//input[@id='username']/ancestor::div"));


// 4. Descendant
// Selects all child elements at any depth.
// Use when you want to locate elements inside a parent, including nested elements.
driver.findElement(By.xpath("//div[@id='user-details']/descendant::input"));


// 5. Following-Sibling
// Selects all sibling elements appearing after the current element.
// Use when you want to locate an element positioned after another element at the same level.
driver.findElement(By.xpath("//label[text()='Username']/following-sibling::input"));

driver.findElement(By.xpath("//label[text()='Username']/following-sibling::*"));


// 6. Preceding-Sibling
// Selects all sibling elements appearing before the current element.
// Use when you want to locate an element positioned before another element at the same level.
driver.findElement(By.xpath("//input[@id='password']/preceding-sibling::label"));

driver.findElement(By.xpath("//input[@id='password']/preceding-sibling::*"));


// 7. Following
// Selects elements appearing after the current element in document order.
// Excludes descendants.
// Use when the target element is located later in the HTML structure.
driver.findElement(By.xpath("//label[text()='Username']/following::button"));

driver.findElement(By.xpath("//label[text()='Username']/following::*"));


// 8. Preceding
// Selects elements appearing before the current element in document order.
// Excludes ancestors.
// Use when the target element is located earlier in the HTML structure.
driver.findElement(By.xpath("//button[text()='Login']/preceding::input"));

driver.findElement(By.xpath("//button[text()='Login']/preceding::*"));


// 9. Self
// Selects the current element itself.
// Use when you want to refer to the same element.
driver.findElement(By.xpath("//input[@id='username']/self::input"));


// 10. Ancestor-or-Self
// Selects the current element and all its ancestors.
// Use when you want the current element along with its parent hierarchy.
driver.findElement(By.xpath("//input[@id='username']/ancestor-or-self::*"));


// 11. Descendant-or-Self
// Selects the current element and all its descendants.
// Use when you want the current element along with all elements inside it.
driver.findElement(By.xpath("//div[@id='user-details']/descendant-or-self::*"));

```

| Absolute XPath | `/` | `/html/body/div/input` |
| Relative XPath | `//` | `//input[@id='username']` |

	

# Selenium WebDriver Methods

- [Get Methods](#get-methods)
- [Conditional Methods](#conditional-methods)
- [Browser Methods](#browser-methods)
- [Wait Methods](#wait-methods)
  - [Sleep](#sleep)
  - [Implicit Wait](#implicit-wait)
  - [Explicit Wait](#explicit-wait)
  - [Fluent Wait](#fluent-wait)
- [Navigation Methods](#navigational-methods)

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

`Thread.sleep()` is a Java method that pauses the execution of the script for a fixed time, whether or not the element is ready. The script always waits for the full duration, even if the element appears earlier. If the time is too short, the script fails; if it is too long, execution is slowed down.


| Advantages | Disadvantages |
|------------|---------------|
| Easy to use. | If the time mentioned is not sufficient to load the web page, an exception occurs. |
| | It always waits for the maximum time, which reduces the performance of the script. |
| | It needs to be used multiple times. |

### Implicit Wait
Implicit wait is global and applies to element lookup.
Implicit wait is set once for the driver and applies to every `findElement()` call in the script. If the element is not found immediately, Selenium keeps checking the DOM until the element appears or the timeout ends. If the element appears earlier, it moves on without waiting for the full time. If the element is still not found after the timeout, it throws `NoSuchElementException`.

```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
```

| Advantages | Disadvantages |
|------------|---------------|
| Written once, as a single statement. | If the time is not sufficient, an exception occurs. |
| Does not wait for the maximum time if the element is available. | |
| Applicable for elements. | |

### Explicit Wait

Explicit wait is applied to a specific element and waits for a particular condition, such as visibility or clickability. `WebDriverWait` checks the condition repeatedly (every 500 ms by default) until it becomes true or the timeout expires. If the timeout expires, it throws `TimeoutException`. It is more flexible than implicit wait because each element can have its own condition and timeout.


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

Fluent wait is an advanced form of explicit wait in which we configure the maximum timeout, the polling interval, and the exceptions to ignore. Selenium checks the condition at every polling interval and ignores the specified exceptions, such as `NoSuchElementException`, until the condition is met or the timeout expires. If the timeout expires, it throws `TimeoutException`.

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




## Navigational Methods

Navigation commands are used to navigate between web pages, such as opening a URL, going back, going forward, and refreshing a page. Selenium provides them through the `navigate()` method.

| Command | Description |
|---------|-------------|
| `navigate().to()` | Opens a specified URL. |
| `navigate().back()` | Navigates to the previous page. |
| `navigate().forward()` | Navigates to the next page. |
| `navigate().refresh()` | Refreshes the current webpage. |

**navigate().to()**: opens a specific URL.


```java
driver.navigate().to("https://www.google.com");
```

**navigate().back()**: navigates to the previous page.

```java
driver.navigate().back();
```

**navigate().forward()**: navigates to the next page.

```java
driver.navigate().forward();
```

**navigate().refresh()**: refreshes the current webpage.

```java
driver.navigate().refresh();
```

###  Example

```java
package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationCommands {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        // Open Google
        driver.navigate().to("https://www.google.com");

        // Open Facebook
        driver.navigate().to("https://www.facebook.com");

        // Navigate back to Google
        driver.navigate().back();

        // Navigate forward to Facebook
        driver.navigate().forward();

        // Refresh the current page
        driver.navigate().refresh();

        // Close browser
        driver.quit();
    }
}
```

### Difference Between get() and navigate().to()

driver.get() accepts only a String URL, whereas driver.navigate().to() accepts both String and URL object parameters.


**Syntax**

```java
driver.get("https://www.google.com");

driver.navigate().to("https://www.google.com");

// Using URL object

URL url = new URL("https://www.google.com");

driver.navigate().to(url);
```

# Checkboxes and Alerts
- [Checkboxes](#checkboxes)
- [Alerts](#alerts)
  - [Simple Alert]
  - [Confirmation Alert]
  - [Prompt Alert]
  - [Alert Using Explicit Wait]
- [Authentication Popup](#authentication-popup)

---

## Checkboxes

- `click()` toggles a checkbox: it selects an unselected one and unselects a selected one.
- `isSelected()` returns `true` if the checkbox is currently selected, so it is used to check the state before clicking.

---

```java

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckBox {

    public static void main(String[] args) throws InterruptedException {   // required because of Thread.sleep()

        WebDriver driver = new ChromeDriver();

        driver.get("https://testautomationpractice.blogspot.com/");

        // Implicit wait: applies to every element lookup in the script
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.manage().window().maximize();

        // 1. Select a specific checkbox: locate it using its own id and click it
        driver.findElement(By.xpath("//input[@id='sunday']")).click();
        Thread.sleep(2000);   // pause so the result can be seen

        // Locate ALL checkboxes: findElements() returns a list of all matching elements
        List<WebElement> checkBoxes = driver.findElements(
                By.xpath("//input[@class='form-check-input' and @type='checkbox']"));

 

        // 2. Select all checkboxes: loop through the list and click each one
        for (int i = 0; i < checkBoxes.size(); i++) {
            checkBoxes.get(i).click();   // get(i) returns the checkbox at index i
        }

        // Simplest way: the enhanced for loop does the same without using an index
         for (WebElement checkBox : checkBoxes) {
             checkBox.click();
         }

        Thread.sleep(2000);
   

        // 3. Select the last 3 checkboxes
        // Starting index = total size - number of checkboxes to select
        // Example: total checkboxes = 7, so starting index = 7 - 3 = 4
        for (int i = checkBoxes.size() - 3; i < checkBoxes.size(); i++) {
            checkBoxes.get(i).click();
            System.out.println(i);   // prints the index of the clicked checkbox
        }

        Thread.sleep(2000);
        

        // 4. Select the first 3 checkboxes
        // Indexes 0, 1 and 2 are the first three checkboxes
        for (int i = 0; i <= 2; i++) {
            checkBoxes.get(i).click();
            System.out.println(i);   
        }

        Thread.sleep(5000);   
  

        driver.quit();   // closes all browser windows and ends the session
    }
}
```

## Alerts

JavaScript alerts are **popups generated by the browser**, not part of the web page's DOM. Because of this, they cannot be inspected or located with `findElement()`. To work with an alert, Selenium must first switch the driver's focus to it using `driver.switchTo().alert()`, which returns an `Alert` object.

**Types of JavaScript alerts**

| Alert type | Description | Buttons / input |
|------------|-------------|-----------------|
| Simple alert | Shows a message only. | OK |
| Confirmation alert | Asks the user to confirm an action. | OK and Cancel |
| Prompt alert | Asks the user to enter some text. | Text box, OK and Cancel |

**Methods of the `Alert` interface**

| Method | Description |
|--------|-------------|
| `getText()` | Returns the message shown on the alert. |
| `accept()` | Closes the alert by clicking the OK button. |
| `dismiss()` | Closes the alert by clicking the Cancel button. |
| `sendKeys("text")` | Types text into the prompt alert's text box. |

```java

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Alerts {

    public static void main(String[] args) throws InterruptedException {   // required because of Thread.sleep()

        WebDriver driver = new ChromeDriver();

        // Explicit wait declaration: wait up to 10 seconds for a condition (used in alert 4)
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // Implicit wait: applies to every element lookup in the script
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.manage().window().maximize();

        // 1. Simple alert: has only an OK button
        // Click the button that opens the simple alert
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();

        Thread.sleep(5000);   // pause so the alert can be seen

        // Switch the driver's focus from the web page to the alert
        Alert myAlert = driver.switchTo().alert();

        // Read the message shown on the alert
        System.out.println(myAlert.getText());

        // Close the alert using the OK button
        myAlert.accept();

        // 2. Confirmation alert: has OK and Cancel buttons
        // Click the button that opens the confirmation alert
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();

        Thread.sleep(5000);

        // Switch the driver's focus to the alert
        myAlert = driver.switchTo().alert();

        // Use ONE of the following:
        // myAlert.accept();    // closes the alert using the OK button
        myAlert.dismiss();      // closes the alert using the Cancel button

        // 3. Prompt alert: has a text box along with OK and Cancel buttons
        // Click the button that opens the prompt alert
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();

        // Switch the driver's focus to the alert
        Alert myPromptAlert = driver.switchTo().alert();

        // Type the text into the alert's text box
        myPromptAlert.sendKeys("This is input");

        // Close the alert using the OK button (the entered text is submitted)
        myPromptAlert.accept();

        // 4. Alert using explicit wait
        // Instead of pausing with Thread.sleep(), WebDriverWait waits until the alert is present.
        // alertIsPresent() checks repeatedly until the alert appears and then returns the Alert
        // object, so switchTo().alert() is not needed.
        // If the alert does not appear within the timeout, a TimeoutException is thrown.
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();

        // Wait until the alert is present and capture it
        Alert waitedAlert = myWait.until(ExpectedConditions.alertIsPresent());

        // Read the message shown on the alert
        System.out.println(waitedAlert.getText());

        // Close the alert using the OK button
        waitedAlert.accept();

        driver.quit();   // closes all browser windows and ends the session
    }
}
```

---

## Authentication Popup

An authentication popup (basic authentication) is a **browser-level login popup** that asks for a username and password. It is not a JavaScript alert and not part of the web page, so neither `findElement()` nor `switchTo().alert()` can handle it.

The simplest solution is to pass the username and password **inside the URL** in this format:

```text
https://username:password@website-url
```

The browser sends the credentials automatically, so the popup does not appear.

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

WebDriver driver = new ChromeDriver();

driver.manage().window().maximize();

// Normal URL: the authentication popup appears and cannot be handled by Selenium
// driver.get("https://the-internet.herokuapp.com/basic_auth");

// URL with credentials (username: admin, password: admin): the popup is bypassed
driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
```



