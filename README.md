# starbux-coffee

<h3 align="center">Starbux</h3> <br>
The RESTful API allows users to order drinks/toppings and admins can create/update/delete drinks/toppings and have access to the reports.

<h3> Functional Requirements </h3><br>
● Develop an API that will be used to order drinks with any of the topping combinations. <br>
● Visitor journeys should be transparent, the current amount of the cart and the products should be communicated back to the caller of the API. <br>
● When finalizing the order, the original amount and the discounted amount should be communicated back to the caller of the API. <br>
● Reports are present with the criteria suggested in the admin API requirements. <br>

<h3> Drinks:</h3>

● Black Coffee - 4 eur <br>
● Latte - 5 eur <br>
● Mocha - 6 eur <br>
● Tea - 3 eur <br>

<h3> Toppings/sides: </h3>
● Milk - 2 eur <br>
● Hazelnut syrup - 3 eur <br>
● Chocolate sauce - 5 eur <br>
● Lemon - 2 eur <br>

<h3> Discount logic: </h3>

● If the total of the cart is more than 12 euros, there should be a 25% discount. <br>
● If there are 3 or more drinks in the cart, the one with the lowest amount (including toppings) should be free. <br>
● If the cart is eligible for both promotions, the promotion with the lowest cart amount should be used and the other one should be ignored. <br>

<h3> Admin api: </h3>

● Should be able to create/update/delete products and toppings. <br>
● Reports: <br>
○ Total amount of the orders per customer. <br>
○ Most used toppings for drinks. <br>


<h3> Features:</h3>
Springboot<br>
H2<br>
Swagger<br>
Unit Test { Coverage 92% } <br>
<img src="covered.PNG" alt="Covered">
JWT Token<br>
Actuator<br>
<br><br><br>


| Method | Url | Description |
| ------ | --- | ----------- | 
| POST   | /api/create-order | Create order | |
| POST   | /api/add-product/order/{orderId} | Add product to order | |
| POST   | /api/add-topping/order/{orderId}/product/{orderProductId} | Add topping to product |
| PUT    | /api/update-product/order/{orderId}/product/{orderProductId} | Update product
| DELETE | /api/delete-topping/order/{orderId}/product/{orderProductId}/topping/{productToppingId} | Delete topping from product| |
| DELETE | /api/delete-order/{id} | Delete order |
| GET    | /api/report/customer-total-amount | This method is used to find total amounts of per customer |
| GET    | /api/report/report/max-topping | This method is used to find the popular topping of each product |
| POST   | /api/login | Get auth key| |


<h3> ADMIN_ROLE User: </h3>
Username = Cagla
Password = 123

<h3> USER_ROLE User: </h3>
Username = Mark
Password = 456

