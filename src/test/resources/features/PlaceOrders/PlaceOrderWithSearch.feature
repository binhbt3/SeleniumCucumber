Feature: Place oder with search
  As an user, place order with search in website ecomerce

Scenario: Successfully place order with search
  Given User is on the product page "https://rahulshettyacademy.com/seleniumPractise/#/"
  When User search products and add to cart
    | search item	               | item              | quality | price | Price in Cart | promo code        | Discount | Apply code message | Country | Agree to the Terms & Conditions|
    | Raspberry - 1/4 Kg           |Raspberry - 1/4 Kg | 1       | 160   | 160           |                   |     0    |                    | Viet Nam| yes                            |
  And The user proceeds to checkout and apply promoCode
    | search item	               | item              | quality | price | Price in Cart | promo code        | Discount | Apply code message | Country | Agree to the Terms & Conditions|
    | Raspberry - 1/4 Kg           |Raspberry - 1/4 Kg | 1       | 160   | 160           |                   |    0     |                    | Viet Nam| yes                            |
  And The user enter country "Vietnam"
  And The user agree terms conditions
  And The user confirm order
  Then The user should see the order confirmation message
