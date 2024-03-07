package pojo;

import lombok.Data;

@Data // automatically generate getters and setters methods
public class RequestBody {
    /*
    request body purpose is describe all variables from
    our request body
    request body data
     */
    private String email;
    private String password;
    /*
    category controller
     */
    private String category_title;
    private String category_description;
    private boolean flag;

    /*
      {
      "type_of_pay": "CASH",
      "bank_account_name": "bank of postman17",
      "description": "financial",
      "balance": 5000000
    }
       */
    private String type_of_pay;
    private String bank_account_name;
    private String description;
    private double balance;


    /* FOR SELLERS
   {
   "company_name": "ElizaCom",
   "seller_name": "EK",
   "email": "777@gmail.com",
   "phone_number": "+3232034033",
   "address": "LA"
 }
    */
    private String company_name;
    private String seller_name;
//    private String email;
    private String phone_number;
    private String address;


    private String product_title;
    private double product_price;
    private int service_type_id;
    private int category_id;
    private String product_description;
    private String client_name;
    private int client_id;
    private int [] tagId;


/*
{
    "product_title": "{{product_title}}",
    "product_price": {{product_price}},
    "service_type_id": {{service_type_id}},
    "category_id": {{category_id}},
    "product_description": "{{product_description}}"
}
 */


}
