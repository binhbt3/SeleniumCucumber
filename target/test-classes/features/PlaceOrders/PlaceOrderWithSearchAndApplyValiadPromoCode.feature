Feature: Place order with search and apply valid promoCode
  As an user, I want to place orders on e-commerce website

    @successfullyOrderWithPromoCode
  Scenario: Successfully order placement single item
    Given User is on the product page "https://rahulshettyacademy.com/seleniumPractise/#/"
    When User search products and add to cart
    | search item	               | item              | quality | price | Price in Cart | promo code        | Discount | Apply code message | Country | Agree to the Terms & Conditions|
    | Raspberry - 1/4 Kg           |Raspberry - 1/4 Kg | 1       | 160   | 160           | rahulshettyacademy| 10       | Code applied ..!   | Viet Nam| yes                            |
    And The user proceeds to checkout and apply promoCode
      | search item	               | item              | quality | price | Price in Cart | promo code        | Discount | Apply code message | Country | Agree to the Terms & Conditions|
      | Raspberry - 1/4 Kg         |Raspberry - 1/4 Kg | 1       | 160   | 160           | rahulshettyacademy| 10       | Code applied ..!   | Viet Nam| yes                            |
    And The user enter country "Vietnam"
    And The user agree terms conditions
    And The user confirm order
    Then The user should see the order confirmation message

  Scenario: Successfully order placement multiple items
    Given User is on the product page "https://rahulshettyacademy.com/seleniumPractise/#/"
    When User search products and add to cart
      | search item	               | item              | quality | price | Price in Cart | promo code        | Discount | Apply code message | Country | Agree to the Terms & Conditions|
      | Raspberry - 1/4 Kg         |Raspberry - 1/4 Kg | 1       | 160   | 160           | rahulshettyacademy| 10       | Code applied ..!   | Vietnam | yes                            |
      | Tomato - 1 Kg              |Tomato - 1 Kg      | 2       | 16    | 32            | rahulshettyacademy| 10       | Code applied ..!   | Vietnam | yes                            |
    And The user proceeds to checkout and apply promoCode
      | search item	               | item              | quality | price | Price in Cart | promo code        | Discount | Apply code message | Country | Agree to the Terms & Conditions|
      | Raspberry - 1/4 Kg         |Raspberry - 1/4 Kg | 1       | 160   | 160           | rahulshettyacademy| 10       | Code applied ..!   | Vietnam | yes                            |
      | Tomato - 1 Kg              |Tomato - 1 Kg      | 2       | 16    | 32            | rahulshettyacademy| 10       | Code applied ..!   | Vietnam | yes                            |

    And The user enter country "Vietnam"
    And The user agree terms conditions
    And The user confirm order
    Then The user should see the order confirmation message


  Scenario: Successfully order placement multiple items with 1 keyword
    Given User is on the product page "https://rahulshettyacademy.com/seleniumPractise/#/"
    When User search products and add to cart
      | search item	               | item                  | quality | price    | Price in Cart | promo code        | Discount | Apply code message | Country | Agree to the Terms & Conditions|
      | Ap                         |Capsicum, Apple - 1 Kg | 1, 2    | 60, 72   | 60 , 144      | rahulshettyacademy| 10       | Code applied ..!   | Vietnam | yes                            |
    And The user proceeds to checkout and apply promoCode
      | search item	               | item                  | quality | price    | Price in Cart | promo code        | Discount | Apply code message | Country | Agree to the Terms & Conditions|
      | Ap                         |Capsicum, Apple - 1 Kg | 1, 2    | 60, 72   | 60 , 144      | rahulshettyacademy| 10       | Code applied ..!   | Vietnam | yes                            |
    And The user enter country "Vietnam"
    And The user agree terms conditions
    And The user confirm order
    Then The user should see the order confirmation message