package pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {   //response body
    /*
    custom response we use this class to read value of our responses
     */

    private int category_id;
    private String category_title;
    private String category_description;

    // category as array
    private CustomResponse[] category_response;       //x[0].category_response

    /*
     "category_id": 987,
    "category_title": "Repare",
    "category_description": "Technic repare",
     */

    private int id;
    private String bank_account_name;
    private double balance;

    /* bank account response body variables
    {
    "id": "1208",
    "fake_id": 32,
    "bank_account_name": " Featurea bank03",
    "description": "Finacial company",
    "type_of_pay": "BANK",
    "balance": 150090.0,
    "history_balance_responses": []
}
     */

    private CustomResponse[] responses;
    private int seller_id;
    private String seller_name;
    private String email;
    private String address;
    private String phone_number;


/* for sellers to take variables
{
    "seller_id": 3688,
    "company_name": "Apple1 inc",
    "seller_name": "Steve",
    "seller_surname": null,
    "email": "jayszonqd46@gmail.com",
    "phone_number": "1233454567",
    "address": "Monaco",
    "created": "2024-02-02",
    "income": false,
    "number_of_invoices": 0
}

 */
//product
    private String product_title;
    private int product_id;
    private double product_price;
    private String product_description;
    private String client_name;
    private int [] tagId;
    private String Client_id;
    private String companyName;



    //payment controller
    private CustomResponse response;  //x[0].response
    private String invoice_title;  //x[0].response.invoice_title
    private double total_pay;     //x[0].response.total_pay



}
