## Data Cleaning

### Common Types of Dirty Data
- **Incomplete data:**

most common occurrence of dirty data.  Important fields on mater data records, useful to the business, are often left blank. For example, if you haven't classified your customers by industry, you cannot segment your sales and marketing initiatives by industry.

- Duplicate data:

Very common. Most companies deal with issues with duplicate customer records, but duplicate materials are also very common. This can be costly to companies due to excess in inventory and sub-optimal procurement decisions.


- Incorrect data:

Incorrect data can occur when field values are created outside of the valid range of values. For example,the value in a month field should range from 1 to 12 or a street address should be a real address.

- Inaccurate data:

It is possible or data to be technically correct but inaccurate given the business context. Costyly business interruptions are often rooted inaccurate data.For example, minor errors in customer addresses can result in deliveries at the wrong locations even though the addresses are actual addresses.

- Business rule violation:

There are often large collections of poorly documented business rules associated with master datt that are specific to the industry or business context. For example, beverage products should have a Unit of Measure in 'fl.oz'or payment terms for a certain type of customers should always be 'Net 30'

- Inconsistent data

 Data redundancy–i.e., the same field values stored in different places-often leads to inconsistencies. For example, most companies have customer information in multiple systems and the data is often not kept in sync. 
 ### Where Does the "Dirt" Come From?

**Incomplete** The data has missing values. Not data value is stored in a field. For example, the street address is missing in a customer record. 
 
**Incorrect**  The value entered does not comply with the field's valid values. For example, the value entered for month is likely to be a number from 1 to 12. This vlaue can be enforced with lookup tables or edit checks.

**Inaccurate** The value entered is not accurate. Sometimes, the system can evaluate the data value for accuracy based on context. For most systems, accuracy validation requires a manual process.

**Inconsistent** The value in one field is inconsistent with the value in a field that should have the same data. Particularly common with customer data, one source of data inconsistencies is manual or unchecked data redundancy.

**Duplicate** The data appears more than once in a system of record. Common causes include repeat submissions, improper data joining or blending, and user error.

**In violation of business rules** The value is not valid or allowed, based on the business rules(e.g. An effective date must always come before an expiration date)
