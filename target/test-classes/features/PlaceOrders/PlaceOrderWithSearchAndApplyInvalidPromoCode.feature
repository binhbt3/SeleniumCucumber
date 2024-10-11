Feature: Place order with search and apply invalid promocode
  As an user, place order and apply invalid promocode in commerce website

  Scenario: Place order with search and apply invalid promocode
    Given User is on the product page "https://rahulshettyacademy.com/seleniumPractise/#/"
    When User search products and add to cart
      | search item	               | item              | quality | price | Price in Cart | promo code        | Discount | Apply code message | Country | Agree to the Terms & Conditions|
      | Raspberry - 1/4 Kg         |Raspberry - 1/4 Kg | 1       | 160   | 160           |  invalid          |     0    | Invalid code ..!   | Viet Nam| yes                            |
    And The user proceeds to checkout and apply promoCode
      | search item	               | item              | quality | price | Price in Cart | promo code        | Discount | Apply code message | Country | Agree to the Terms & Conditions|
      | Raspberry - 1/4 Kg         |Raspberry - 1/4 Kg | 1       | 160   | 160           |  invalid          |    0     | Invalid code ..!   | Viet Nam| yes                            |
    And The user enter country "Vietnam"
    And The user agree terms conditions
    And The user confirm order
    Then The user should see the order confirmation message
