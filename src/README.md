#https://global.direct.asda.com/

##**com.george.taf.data**
package contains classes with test data.

###**TestData**
test data for conducting API and UI tests (menu and product names, size, product identifier, etc.).

###**ApiResponseObjects**
objects with expected and actual information from API test responses.

###**UiExpectedResults**
information with expected results of UI tests.

###**UiLocators**
class with locators for finding web elements on the page.

##**com.george.taf.data.ro**
Package with object structures for forming comparable results of API tests.

###**Error**
class for error response.

###**BasketItem**
class for responses with information about a product in the basket.

###**BasketInfo**
class with information about the basket.

##**com.george.taf.data.po**
package with the implementation of methods for interacting with page elements for UI tests.

###**HomePage**
class for working with elements on the home page.

###**CategoryPage**
class for working with elements on the categories and subcategories page.

###**ProductPage**
class for working with elements on the selected product page.

###**ProductPage**
class for working with elements on the product basket page.

###**LoginPage**
class for working with the login form.

##**com.george.taf.data.util**
package with utility classes.

###**Responses**
class for sending requests with prepared body and headers for various actions on the trading platform (login, add to cart, etc.).

###**Driver**
class for working with the web driver (open/close, automation of various actions required for UI tests).

###**Utils**
class with auxiliary actions not directly related to page elements (generation of random and data type conversion).


##**test.java.api**
API tests for the george.com site.

###**LoginApiTest**
tests with sending data to log in a user to the system.

###**BasketApiTest**
tests with sending data to move items to/from the basket.

###**SearchApiTest**
tests for searching products.


##**test.java.ui**
UI tests for the george.com site.

###**BaseTest**
class implementing Before/After methods for use in all child test classes.

###**HomeTest**
tests for the home page.

###**CategoryTest**
tests for the page with categories and subcategories of products.

###**BasketTest**
tests for checking the shopping basket.

###**LoginTest**
tests for the login form.