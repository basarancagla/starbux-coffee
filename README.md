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
| PUT    | /api/update-product/order/{orderId}/product/{orderProductId}/topping/{productToppingId} | Update topping
| DELETE | /api/delete-topping/order/{orderId}/product/{orderProductId}/topping/{productToppingId} | Delete topping from product| 
| DELETE | /api/delete-topping/order/{orderId}/product/{orderProductId} | Delete product from order| 
| DELETE | /api/delete-order/{id} | Delete order |
| GET    | /api/report/customer-total-amount | This method is used to find total amounts of per customer |
| GET    | /api/report/report/max-topping | This method is used to find the popular topping of each product |
| POST   | /api/login | Get auth key| |


<h3> ADMIN_ROLE User: </h3>
Username = Cagla <br>
Password = 123  <br><br>

Username = Bestseller <br>
Password = 234 <br><br>

<h3> USER_ROLE User: </h3>
Username = Mark <br>
Password = 456 <br><br>

 <h3> Tables </h3>

| Table Name | Description |
| ---------- | ----------- | 
| TBL_USER   | User informations
| TBL_PRODUCT   | Product informations
| TBL_TOPPING   | Topping informations
| TBL_ORDER    | Stores 1 row for each order. It includes original price, discount price, discounted price, create user, create date, update user, update date
| TBL_ORDER_PRODUCT_DETAIL  | Stores products and quantities for each order.
| TBL_PRODUCT_TOPPING_DETAIL | Stores toppings and quantities for each product in the order.


<h3> Steps</h3>

Swagger Link:
http://localhost:8080/swagger-ui/#/

●  <b>Login </b>

/login 

It returns token or 403(Forbidden) error.<br>

payload:<br>

For ADMIN Role: <br>

 { <br>
   "password": "123", <br>
   "userName": "Cagla" <br>
 }<br>

Response example: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDYWdsYSIsImlhdCI6MTYzMTgwNjA4NywiZXhwIjoxNjMxODA2Mzg3fQ.M_5osl0uxvwpONdszH4oWTfWMlEr-MwG6kEzv3W5Rb8 <br><br>
Please authorize with Bearer keyword: "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDYWdsYSIsImlhdCI6MTYzMTgwNjA4NywiZXhwIjoxNjMxODA2Mzg3fQ.M_5osl0uxvwpONdszH4oWTfWMlEr-MwG6kEzv3W5Rb8
"<br><br>


<img src="Authorize.PNG" alt="Authorize">

●  <b>Create Order </b>

/api/create-order

{
    "orderProductDetail": [
        {
            "productId": 3,
            "quantity": 2,
            "amount": 0,
            "productToppingDetail": [
                {
                    "toppingId": 1,
                    "quantity": 1
                },
                {
                    "toppingId": 2,
                    "quantity": 1
                }
            ]
        }
    ]
}

The result in h2 db:

Db link : http://localhost:8080/h2-console/

<img src="h2db.PNG" alt="h2db">

●  <b> Update Order </b>

         ●●   Add Product to Order 
         
         /api/add-product/order/{orderId}
         
         {orderId}: 1
         
         Payload:
         {
            "productId": 1,
            "quantity": 1,
            "productToppingDetail": [
              {
                "quantity": 2,
                "toppingId": 3
              }
            ]
          }
          
          ●●  Add Topping to Product 
         
         /api/add-topping/order/{orderId}/product/{orderProductId}
         
         {orderId}: 1
         {orderProductId}: 2
         
         Payload:
         {
            "quantity": 1,
            "toppingId": 3
          }
          
          ●●  Update product 
         
         /api/update-product/order/{orderId}/product/{orderProductId}
         
         {orderId}: 1
         {orderProductId}: 1
         
         {
            "productId": 3,
             "quantity": 1
            "productToppingDetail": [
              {
                "quantity": 1,
                "toppingId": 1
              },
              {
                "quantity": 1,
                "toppingId": 2
              }
            ],
          }
          
          ●●  Update topping 
          
          /api/update-product/order/{orderId}/product/{orderProductId}/topping/{productToppingId}
          
         {orderId}: 1
         {orderProductId}: 2
         {productToppingId}:3
          
          {
            "quantity": 1,
            "toppingId": 3
          }
        
         ●●  Delete topping from product 
          
          /api/delete-topping/order/{orderId}/product/{orderProductId}/topping/{productToppingId}
          
         {orderId}: 1
         {orderProductId}: 2
         {productToppingId}:3
         
         ●●  Delete product from order
          
          /api/delete-topping/order/{orderId}/product/{orderProductId}
          
         {orderId}: 1
         {orderProductId}: 2
         
● <b> Delete order </b>
          
      /api/delete-order/{id}

     {orderId}: 1
     
     
● <b> Total amount of the orders per customer report </b>

/api/report/customer-total-amount

● <b> Most used toppings for drinks report  </b>

/api/report/report/max-topping
    
        
<h3> Actuator Trace</h3>

localhost:8080/actuator/httptrace

