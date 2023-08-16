
## CLUSTEREDDATA WAREHOUSE
---
:scroll: **START**
## Introduction
Suppose you are part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to accept deals details from and persist them into DB.
Data warehouse is a central repository of integrated data from one or more disparate sources. It is used for reporting and data analysis. Data warehouse is a subject-oriented, integrated, nonvolatile and time-variant collection of data in support of management's decision making process. Data warehouse is a copy of transaction data specifically structured for querying and analysis. It is a subject-oriented, integrated, nonvolatile and time-variant collection of data in support of management's decision making process. Data warehouse is a copy of transaction data specifically structured for querying and analysis.

---
### Task description
**Request Fields** should contain::
- Deal Unique Id;
- From Currency ISO Code "Ordering Currency";
- To Currency ISO Code;
- Deal timestamp;
- Deal Amount in ordering currency;

---
### Requirements

- System should not import same request twice.
- Validate row structure.(e.g: Missing fields, Type format..etc.
- No rollback allowed, what every rows imported should be saved in DB

---
#### How to build
#### Requirements
- Java 11;
- Java IDE : IntelliJ (or Eclipse, Vscode, Netbeans);
- Database: Postgres;
- Postman(For testing);
  Steps by step for building and running the project locally;
  Clone from the link git clone https://github.com/Thelma-21/ClusteredDataWareHouse
- Open the cloned project in intelliJ Idea;
- Go to POM.xml the update Project to update all the maven dependencies;
- Maven Build the project and run;
---
#### Testing The API Endpoints
---
**Accepting Deal details**
- Endpoint: 'http://127.0.0.1:8080/api/deals';
```
curl --location --request POST 'http://127.0.0.1:8080/api/deals' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
                    "dealId": "3940046",
                    "orderingCurrency": "usd",
                    "convertedCurrency": "ngn",
                    "amount": "45987"
             }'
````
**Response**
```
'{
                 "message": "Deal with Id number: 3940046 saved successfully",
                 "timeStamp": "2023-01-27T13:19:59.841533",
                 "data": "CREATED"
            }'
```
---

**Getting deal details by unique id**
- Endpoint: 'http://127.0.0.1:8080/api/{deal_id}';
  **Payload**
```
curl --location --request GET 'http://127.0.0.1:8080/api/550e8400-e29b-41d4-a716-446655440000' \

````
**Response**
```
'{
        "dealId": "550e8400-e29b-41d4-a716-446655440000",
        "orderingCurrency": "USD",
        "convertedCurrency": "NGN",
        "amount": "¤45,987.00",
        "dealTimeStamp": "2023-01-27T13:19:59.722176"
            }'
```
---

**Getting all deals details**
- Endpoint: '(http://localhost:8080/api/deals)';
  **Payload**
```
curl --location --request GET '(http://localhost:8080/api/deals)'
````
**Response**
```
{
    "message": "status",
    "data": [
        {
        "dealId": "550e8400-e29b-41d4-a716-446655440000",
        "orderingCurrency": "USD",
        "convertedCurrency": "NGN",
        "amount": "¤45,987.00",
        "dealTimeStamp": "2023-01-27T13:19:59.722176"
    },
    {
        "dealId": "	123e4567-e89b-12d3-a456-426655440000",
        "orderingCurrency": "PLN",
        "convertedCurrency": "EUR",
        "amount": "¤ 1,500.00",
        "dealTimeStamp": "2023-01-27T13:31:05.477708"
    }
    ]
}
```

